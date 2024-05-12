package team1.issue_tracker.Issue;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import team1.issue_tracker.comment.Comment;

import java.time.LocalDateTime;
import java.util.List;


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
    @MappedCollection(idColumn = "id", keyColumn = "issue_id")
    private List<Comment> comments;
    private IssueStatus status;
    private LocalDateTime lastModifiedAt;
    private LocalDateTime createdAt;
}