package team1.issue_tracker.Issue;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team1.issue_tracker.label.IssueLabel;
import team1.issue_tracker.label.IssueLabelRepository;
import team1.issue_tracker.label.Label;
import team1.issue_tracker.label.LabelRepository;

import java.util.List;
import team1.issue_tracker.milestone.IssueMilestone;
import team1.issue_tracker.milestone.IssueMilestoneRepository;
import team1.issue_tracker.milestone.Milestone;
import team1.issue_tracker.milestone.MilestoneRepository;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final LabelRepository labelRepository;
    private final IssueLabelRepository issueLabelRepository;
    private final MilestoneRepository milestoneRepository;
    private final IssueMilestoneRepository issueMilestoneRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository, LabelRepository labelRepository,
                        IssueLabelRepository issueLabelRepository,
                        MilestoneRepository milestoneRepository, IssueMilestoneRepository issueMilestoneRepository) {
        this.issueRepository = issueRepository;
        this.labelRepository = labelRepository;
        this.issueLabelRepository = issueLabelRepository;
        this.milestoneRepository = milestoneRepository;
        this.issueMilestoneRepository = issueMilestoneRepository;
    }

    public List<IssueListRes> getList() {
        List<Issue> issueList = (List<Issue>) issueRepository.findAll();

        return issueList.stream().map(issue -> new IssueListRes(issue.getId(), issue.getTitle(), issue.getComment(),
                labelsAtIssue(issue.getId()), milestoneAtIssue(issue.getId()))).toList();
    }

    private List<Label> labelsAtIssue(Long issueId) {
        List<IssueLabel> byIssueId = issueLabelRepository.findAllByIssueId(issueId);

        return byIssueId.stream().map(issueLabel -> labelRepository.findById(issueLabel.getLabelId()).get()
        ).toList();
    }

    private Milestone milestoneAtIssue(Long issueId) {
        Optional<IssueMilestone> milestoneOptional = issueMilestoneRepository.findAllByIssueId(issueId).stream()
                .findFirst();
        if (milestoneOptional.isPresent()) {
            return milestoneRepository.findById(milestoneOptional.get().getMilestoneId()).get();
        } else {
            return null;
        }
    }
}
