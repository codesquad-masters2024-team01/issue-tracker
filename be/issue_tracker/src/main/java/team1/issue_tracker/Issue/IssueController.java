package team1.issue_tracker.Issue;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team1.issue_tracker.Issue.dto.IssueListResponse;
import team1.issue_tracker.Issue.dto.IssueShowResponse;

@RequestMapping("/issue")
@Slf4j
@RestController
public class IssueController {
    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/list")
    public List<IssueListResponse> issueList() {
        log.debug("Show issue list");
        return issueService.getOpenIssues();
    }

    @PostMapping("/{id}/close")
    public void closeIssue(@PathVariable("id") Long id) throws NoSuchElementException, IllegalStateException {
        log.debug("Close issue.{}", id);
        issueService.closeIssue(id);
    }

    @PostMapping("/multi/close")
    public void closeMultiIssue(@RequestBody List<Long> issueIds) throws RuntimeException{
        log.debug("Close all issues.{}", issueIds);
        issueService.closeIssues(issueIds);
    }

    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable("id") Long id) {
        log.debug("Delete issue.{}", id);
        issueService.deleteIssue(id);
    }

    @GetMapping("/{id}")
    public IssueShowResponse showIssue(@PathVariable("id") Long id) {
        log.debug("Show issue.{} detail", id);
        return issueService.showIssue(id);
    }
}