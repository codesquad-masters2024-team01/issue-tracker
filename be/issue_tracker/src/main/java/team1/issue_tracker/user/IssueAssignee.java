package team1.issue_tracker.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ISSUE_HAS_ASSIGNEE")
@Getter
@Builder
@AllArgsConstructor
public class IssueAssignee {
    @Id
    private Long id;
    private Long issueId;
    private String assigneeId;
}
