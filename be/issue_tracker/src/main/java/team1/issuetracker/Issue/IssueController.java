package team1.issuetracker.Issue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team1.issuetracker.Issue.dto.IssueListResponse;
import team1.issuetracker.Issue.dto.IssueMakeRequest;
import team1.issuetracker.Issue.dto.IssueShowResponse;
import team1.issuetracker.comment.CommentService;
import team1.issuetracker.comment.dto.CommentPostRequest;
import team1.issuetracker.label.LabelService;
import team1.issuetracker.milestone.MilestoneService;
import team1.issuetracker.user.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("/issue")
@Slf4j
@RestController
public class IssueController {
    private final IssueService issueService;
    private final CommentService commentService;
    private final LabelService labelService;
    private final MilestoneService milestoneService;
    private final UserService userService;

    @Autowired
    public IssueController(IssueService issueService, CommentService commentService, LabelService labelService,
                           MilestoneService milestoneService, UserService userService) {
        this.issueService = issueService;
        this.commentService = commentService;
        this.labelService = labelService;
        this.milestoneService = milestoneService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public IssueShowResponse showIssue(@PathVariable("id") Long id) {
        log.debug("Show issue.{} detail", id);
        Issue issue = issueService.showIssue(id);
        String authorName = userService.getNameById(issue.getUserId());

        return IssueShowResponse.of(
                issue,
                authorName,
                userService.getAssigneesAtIssue(issue),
                labelService.getLabelsAyIssue(issue),
                milestoneService.getMilestoneAtIssue(issue),
                commentService.getCommentsAtIssue(issue.getId()));
    }

    @GetMapping("/list")
    public List<IssueListResponse> issueList() {
        log.debug("Show issue list");
        List<Issue> issueList = issueService.getOpenIssues();

        return issueList.stream().map(issue -> IssueListResponse.of(
                        issue,
                        commentService.getFirstCommentTextAtIssue(issue),
                        labelService.getLabelsAyIssue(issue),
                        milestoneService.getMilestoneAtIssue(issue)))
                .toList();
    }

    @PostMapping
    public void createIssue(@RequestBody IssueMakeRequest issueMakeRequest) {
        log.debug("Create issue");
        Issue issue = Issue.from(issueMakeRequest, "test1");
        Issue saved = issueService.createIssue(issue);

        String comment = issueMakeRequest.getComment();
        commentService.addComment(saved.getId(), "test1", new CommentPostRequest(comment));
    }

    @PostMapping("/{id}/close")
    public void closeIssue(@PathVariable("id") Long id) throws NoSuchElementException, IllegalStateException {
        log.debug("Close issue.{}", id);
        issueService.closeIssue(id);
    }

    @PostMapping("/multi/close")
    public void closeMultiIssue(@RequestBody List<Long> issueIds) throws RuntimeException {
        log.debug("Close all issues.{}", issueIds);
        issueService.closeIssues(issueIds);
    }

    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable("id") Long id) {
        log.debug("Delete issue.{}", id);
        issueService.deleteIssue(id);
    }
}