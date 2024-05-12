package team1.issue_tracker.Issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team1.issue_tracker.label.IssueLabel;
import team1.issue_tracker.label.Label;
import team1.issue_tracker.label.LabelRepository;

import java.util.List;
import team1.issue_tracker.milestone.Milestone;
import team1.issue_tracker.milestone.MilestoneRepository;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final LabelRepository labelRepository;
    private final MilestoneRepository milestoneRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository, LabelRepository labelRepository,
                        MilestoneRepository milestoneRepository) {
        this.issueRepository = issueRepository;
        this.labelRepository = labelRepository;
        this.milestoneRepository = milestoneRepository;
    }

    public List<IssueListResponse> getList() {
        List<Issue> issueList = (List<Issue>) issueRepository.findAll();

        return issueList.stream().map(issue -> new IssueListResponse(
                issue.getId(),
                issue.getTitle(),
                issue.getComments().get(0).getContent(),
                labelsAtIssue(issue),
                getMilestone(issue))).toList();
    }

    private Milestone getMilestone(Issue issue) {
        if (issue.getMilestoneId() == null) return null;

        return milestoneRepository.findById(issue.getMilestoneId()).get();
    }

    private List<Label> labelsAtIssue(Issue issue) {
        List<IssueLabel> byIssueId = issue.getIssueHasLabel();
        List<Long> labelIds = byIssueId.stream().map(IssueLabel::getLabelId).toList();

        return (List<Label>) labelRepository.findAllById(labelIds);
    }
}
