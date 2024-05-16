//
//  IssueDetailViewController.swift
//  issue-tracker-01
//
//  Created by 조호근 on 5/14/24.
//

import UIKit

class IssueDetailViewController: UIViewController {
    
    static let identifier: String = "IssueDetailViewController"
    
    @IBOutlet weak var tableView: UITableView!
    
    var issueId: Int?
    var issueDetail: IssueDetail?
    let commentViewModel = BaseViewModel<Comment>()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        configureNavigationBar()
        setupTableView()
        
        if let issueId = issueId {
            self.fetchIssueDetail(issueId: issueId)
        }
    }
    
    private func setupTableView() {
        tableView.register(UINib(nibName: "IssueDetailCell", bundle: .main), forCellReuseIdentifier: IssueDetailCell.identifier)
        tableView.dataSource = self
        tableView.delegate = self
        tableView.rowHeight = 120
    }
    
    private func fetchIssueDetail(issueId: Int) {
        NetworkManager.shared.fetchIssueDetail(issueId: issueId) { [weak self] issueDetail in
            if let issueDetail = issueDetail {
                self?.issueDetail = issueDetail
                self?.commentViewModel.updateItems(with: issueDetail.comments)
                self?.tableView.reloadData()
            }
        }
    }

    private func configureNavigationBar() {
        let backButton = UIBarButtonItem(image: UIImage(systemName: "chevron.left"),
                                         style: .plain,
                                         target: self,
                                         action: #selector(backBtnTapped)
        )
        navigationItem.leftBarButtonItem = backButton
        
        let moreButton = UIBarButtonItem(image: UIImage(systemName: "ellipsis"),
                                         style: .plain,
                                         target: self,
                                         action: #selector(moreBtnTapped)
        )
        navigationItem.rightBarButtonItem = moreButton
    }
    
    @objc private func backBtnTapped() {
        navigationController?.popViewController(animated: true)
    }
    
    @objc private func moreBtnTapped() {
        print("이슈에 대한moreBtnTapped!!")
    }
}

extension IssueDetailViewController: UITableViewDataSource, UITableViewDelegate {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return commentViewModel.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: IssueDetailCell.identifier, for: indexPath) as? IssueDetailCell,
              let issueDetail = issueDetail else {
            return UITableViewCell()
        }
        
        if let comment = commentViewModel.item(at: indexPath.row) {
            cell.setDetail(with: comment, issueAuthor: issueDetail.author)
        }
        
        return cell
    }
}