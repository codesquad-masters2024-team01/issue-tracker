package team1.issue_tracker.Issue;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table("ISSUE")
@Getter
@AllArgsConstructor
public class Issue {
    @Id
    @Generated
    private Long id;
    @Column("user_id")
    private String userId;
    @Column("milestone_id")
    private Long milestoneId;
    private String title;
    private String comment;
    @Column("created_at")
    private Timestamp createdAt;
    @Column("last_modified_at")
    private Timestamp lastModifiedAt;
    private IssueStatus status;
}