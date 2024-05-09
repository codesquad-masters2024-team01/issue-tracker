package team1.issue_tracker.temp;

import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table("COMMENT")
public class Comment {
    @Id
    @Generated
    private Long id;
    @Column("user_id")
    private String userId;
    @Column("issue_id")
    private Long issueId;
    @Column("created_at")
    private Timestamp createdAt;
    @Column("last_modified_at")
    private Timestamp lastModifiedAt;
    @Column("hasEmoji")
    private boolean hasEmoji;
}