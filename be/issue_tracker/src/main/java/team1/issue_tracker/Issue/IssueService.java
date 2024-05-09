package team1.issue_tracker.Issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team1.issue_tracker.label.IssueLabel;
import team1.issue_tracker.label.IssueLabelRepository;
import team1.issue_tracker.label.Label;
import team1.issue_tracker.label.LabelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final LabelRepository labelRepository;
    private final IssueLabelRepository issueLabelRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository, LabelRepository labelRepository, IssueLabelRepository issueLabelRepository) {
        this.issueRepository = issueRepository;
        this.labelRepository = labelRepository;
        this.issueLabelRepository = issueLabelRepository;
    }


    public List<IssueListRes> getList(){
        List<Issue> issueList = (List<Issue>) issueRepository.findAll();

        List<IssueListRes> toReturn = new ArrayList<>();
        issueList.forEach(issue -> toReturn.add(
                new IssueListRes(
                        issue.getId(),
                        issue.getTitle(),
                        issue.getComment(),
                        labelsAtIssue(issue.getId()),
                        new ArrayList<>())
                ));

        return toReturn;
    }


    private List<Label> labelsAtIssue(Long issueId){
        List<IssueLabel> byIssueId = issueLabelRepository.findAllByIssueId(issueId);

        return byIssueId.stream().map(issueLabel -> labelRepository.findById(issueLabel.getLabelId()).get()
        ).collect(Collectors.toList());
    }
}
