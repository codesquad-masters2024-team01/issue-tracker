//
//  LabelCell.swift
//  issue-tracker-01
//
//  Created by 조호근 on 5/9/24.
//

import UIKit

class LabelCell: UICollectionViewCell {

    static let identifier: String = "labelCell"
    
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var containerCell: UIView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.containerCell.layer.cornerRadius = 12
        self.containerCell.clipsToBounds = true
        configureFont()
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
        self.titleLabel.text = nil
        self.titleLabel.backgroundColor = .clear
    }

    private func configureFont() {
        self.titleLabel.applyStyle(fontManager: FontManager(weight: .bold, size: .medium), textColor: .gray50)
        self.titleLabel.backgroundColor = .brown
    }
    
    func setLabel(_ data: Issue.Label) {
        self.titleLabel.text = data.name
        self.titleLabel.backgroundColor = UIColor(hex: data.color)
    }
}
