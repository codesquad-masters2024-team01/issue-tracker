package team1.issue_tracker.Issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team1.issue_tracker.comment.Comment;
import team1.issue_tracker.comment.CommentRepository;
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
    private final CommentRepository commentRepository;
    private final LabelRepository labelRepository;
    private final IssueLabelRepository issueLabelRepository;
    private final MilestoneRepository milestoneRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository, CommentRepository commentRepository,
                        LabelRepository labelRepository,
                        IssueLabelRepository issueLabelRepository,
                        MilestoneRepository milestoneRepository) {
        this.issueRepository = issueRepository;
        this.commentRepository = commentRepository;
        this.labelRepository = labelRepository;
        this.issueLabelRepository = issueLabelRepository;
        this.milestoneRepository = milestoneRepository;
    }

    public List<IssueListRes> getList() {
        List<Issue> issueList = (List<Issue>) issueRepository.findAll();

        return issueList.stream().map(issue -> new IssueListRes(issue.getId(), issue.getTitle(),
                commentAtIssue(issue.getId()),
                labelsAtIssue(issue.getId()), getMilestone(issue))).toList();
    }

    private Milestone getMilestone(Issue issue) {
        if (issue.getMilestoneId() == null) {
            return null;
        }
        return milestoneRepository.findById(issue.getMilestoneId()).get();
    }

    private List<Label> labelsAtIssue(Long issueId) {
        List<IssueLabel> labels = issueLabelRepository.findAllByIssueId(issueId);

        return labels.stream().map(label -> labelRepository.findById(label.getLabelId()).get()
        ).toList();
    }

    private Comment commentAtIssue(Long issueId) {
        return commentRepository.findFirstBy(issueId);
    }

//    private List<Comment> commentsAtIssue(Long issueId) {
//        return commentRepository.findAllByIssueId(issueId);
//    }
}
