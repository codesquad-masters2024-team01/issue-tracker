package team1.issue_tracker.Issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team1.issue_tracker.Issue.dto.IssueListResponse;
import team1.issue_tracker.Issue.dto.IssueShowResponse;
import team1.issue_tracker.comment.Comment;
import team1.issue_tracker.comment.CommentRepository;
import team1.issue_tracker.comment.dto.CommentListResponse;
import team1.issue_tracker.label.IssueLabel;
import team1.issue_tracker.label.Label;
import team1.issue_tracker.label.LabelRepository;
import team1.issue_tracker.milestone.Milestone;
import team1.issue_tracker.milestone.MilestoneRepository;
import team1.issue_tracker.milestone.dto.MilestoneShowResponse;
import team1.issue_tracker.user.IssueAssignee;
import team1.issue_tracker.user.UserService;

import java.util.*;

import static team1.issue_tracker.Issue.IssueStatus.CLOSE;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final LabelRepository labelRepository;
    private final MilestoneRepository milestoneRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    @Autowired
    public IssueService(IssueRepository issueRepository, LabelRepository labelRepository,
                        MilestoneRepository milestoneRepository, CommentRepository commentRepository,
                        UserService userService) {
        this.issueRepository = issueRepository;
        this.labelRepository = labelRepository;
        this.milestoneRepository = milestoneRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    public List<IssueListResponse> getOpenIssues() {
        List<Issue> issueList = issueRepository.findAllByStatus(IssueStatus.OPEN);

        return issueList.stream().map(issue -> new IssueListResponse(
                issue.getId(),
                issue.getTitle(),
                commentRepository.findFirstByIssueId(issue.getId()).getContent(),
                labelsAtIssue(issue),
                milestoneAtIssue(issue))).toList();
    }

    public IssueShowResponse showIssue(Long id) throws NoSuchElementException {
        Issue issue = getIssueById(id);
        String name = userService.getNameById(issue.getUserId());

        return IssueShowResponse.of(issue, name, assigneesAtIssue(issue), labelsAtIssue(issue), milestoneAtIssue(issue),
                commentsAtIssue(issue));
    }

    public void closeIssue(Long id) throws NoSuchElementException {
<<<<<<< HEAD
        Issue issue = getIssueById(id);
        if (issue.getStatus() == CLOSE) throw new IllegalStateException(id + "번 이슈는 이미 닫힌 상태입니다!");
=======
        Issue issue = getIssue(id);
        if (issue.getStatus() == CLOSE) {
            throw new IllegalStateException(id + "번 이슈는 이미 닫힌 상태입니다!");
        }
>>>>>>> a0b9d21 (feat(#52): Milestone DTO 적용)

        issue.setStatus(CLOSE);
        issueRepository.save(issue);
    }

    public void deleteIssue(Long id) throws NoSuchElementException {
        issueRepository.delete(getIssueById(id));
    }

    public Issue getIssueById(Long id) throws NoSuchElementException {
        Optional<Issue> optionalIssue = issueRepository.findById(id);
        if (optionalIssue.isEmpty()) {
            throw new NoSuchElementException(id + "번 이슈가 존재하지 않습니다!");
        }

        return optionalIssue.get();
    }

    private List<CommentListResponse> commentsAtIssue(Issue issue) {
        List<Comment> comments = commentRepository.findAllByIssueId(issue.getId());

        return comments.stream()
                .map(comment -> CommentListResponse.of(comment, userService.getNameById(comment.getUserId()))
                ).toList();
    }

    private List<Label> labelsAtIssue(Issue issue) {
        Set<IssueLabel> issueLabels = issue.getIssueHasLabel();
        List<Long> labelIds = issueLabels.stream().map(IssueLabel::getLabelId).toList();

        return labelRepository.findByIdIn(labelIds);
    }

    private MilestoneShowResponse milestoneAtIssue(Issue issue) {
        Long milestoneId = issue.getMilestoneId();
        if (milestoneId == null) {
            return null;
        }

        Milestone milestone = milestoneRepository.findById(milestoneId).get();
        return new MilestoneShowResponse(milestoneId, milestone.getName());
    }

    private List<String> assigneesAtIssue(Issue issue) {
        Set<IssueAssignee> issueAssignees = issue.getIssueAssignees();
        return issueAssignees.stream()
                .map(IssueAssignee::getAssigneeId)
                .map(userService::getNameById).toList();
    }

    public void closeIssues(List<Long> issueIds) {
        StringJoiner exceptionMessages = new StringJoiner("\n");
        issueIds.forEach(id -> {
            try {
                closeIssue(id);
            } catch (RuntimeException exception) {
                exceptionMessages.add(exception.getMessage());
            }
        });

        if (exceptionMessages.length() != 0) {
            throw new RuntimeException(exceptionMessages.toString());
        }
    }
}