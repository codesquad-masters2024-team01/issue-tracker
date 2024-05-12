package team1.issue_tracker.Issue;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team1.issue_tracker.label.IssueLabel;
import team1.issue_tracker.label.IssueLabelRepository;
import team1.issue_tracker.label.Label;
import team1.issue_tracker.label.LabelRepository;

import java.util.List;
import team1.issue_tracker.milestone.Milestone;
import team1.issue_tracker.milestone.MilestoneRepository;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final LabelRepository labelRepository;
    private final IssueLabelRepository issueLabelRepository;
    private final MilestoneRepository milestoneRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository, LabelRepository labelRepository,
                        IssueLabelRepository issueLabelRepository,
                        MilestoneRepository milestoneRepository) {
        this.issueRepository = issueRepository;
        this.labelRepository = labelRepository;
        this.issueLabelRepository = issueLabelRepository;
        this.milestoneRepository = milestoneRepository;
    }

    public List<IssueListRes> getList() {
        List<Issue> issueList = (List<Issue>) issueRepository.findAll();

        return issueList.stream().map(issue -> new IssueListRes(issue.getId(), issue.getTitle(), issue.getComment(),
                labelsAtIssue(issue.getId()), getMilestone(issue))).toList();
    }

    private Milestone getMilestone(Issue issue) {
        if (issue.getMilestoneId() == null) {
            return null;
        }
        return milestoneRepository.findById(issue.getMilestoneId()).get();
    }

    private List<Label> labelsAtIssue(Long issueId) {
        List<IssueLabel> byIssueId = issueLabelRepository.findAllByIssueId(issueId);

        return byIssueId.stream().map(issueLabel -> labelRepository.findById(issueLabel.getLabelId()).get()
        ).toList();
    }
}
