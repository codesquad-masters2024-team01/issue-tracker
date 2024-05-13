package team1.issue_tracker.Issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import team1.issue_tracker.Issue.dto.IssueListResponse;
import team1.issue_tracker.Issue.dto.IssueShowResponse;

@RequestMapping("/issue")
@RestController
public class IssueController {

    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/list")
    public List<IssueListResponse> issueList() {
        return issueService.getList();
    }

    @PostMapping("/{id}/close")
    public void closeIssue(@PathVariable("id") Long id) {
        issueService.closeIssue(id);
    }

    @PostMapping("/multi/close")
    public void closeMultiIssue(@RequestBody List<Long> issueIds) {
        issueIds.forEach(issueService::closeIssue);
    }

    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable("id") Long id) {
        issueService.deleteIssue(id);
    }

    @GetMapping("/{id}")
    public IssueShowResponse showIssue(@PathVariable("id") Long id) {
        return issueService.showIssue(id);
    }

}