package team1.issue_tracker.Issue;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ISSUE")
@Getter
@AllArgsConstructor
public class Issue {
    @Id
    @Generated
    private Long id;
    private String userId;
    private Long milestoneId;
    private String title;
    private IssueStatus status;
    private LocalDateTime lastModifiedAt;
    private LocalDateTime createdAt;
}