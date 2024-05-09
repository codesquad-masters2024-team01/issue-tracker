package team1.issue_tracker.milestone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ISSUE_MILESTONE")
@Getter
@AllArgsConstructor
public class IssueMilestone {
    @Id
    private Long id;
    @Column("issue_id")
    private Long issueId;
    @Column("milestone_id")
    private Long milestoneId;
}