package team1.issue_tracker.milestone;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import team1.issue_tracker.Issue.Issue;
import team1.issue_tracker.Issue.IssueStatus;

@Table("MILESTONE")
@Getter
@Builder
@AllArgsConstructor
public class Milestone {
    @Id
    @Generated
    private Long id;
    private String name;
    private String description;
    @MappedCollection(idColumn = "MILESTONE_ID")
    private Set<Issue> issues;
    private LocalDateTime deadline;
    @CreatedDate
    private LocalDateTime createdAt;

    public int getOpenIssueCount() {
        return (int) issues.stream().filter(issue -> issue.getStatus() == IssueStatus.OPEN).count();
    }

    public int getClosedIssueCount() {
        return (int) issues.stream().filter(issue -> issue.getStatus() == IssueStatus.CLOSE).count();
    }

    public int getCompletionPercentage() {
        int total = issues.size();
        if (total == 0) {
            return 0;
        }
        return (int) ((double) getClosedIssueCount() / total * 100);
    }
}