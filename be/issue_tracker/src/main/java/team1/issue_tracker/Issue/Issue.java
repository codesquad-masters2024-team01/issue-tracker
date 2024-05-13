package team1.issue_tracker.Issue;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import team1.issue_tracker.comment.Comment;
import team1.issue_tracker.label.IssueLabel;


import java.time.LocalDateTime;
import java.util.List;
import team1.issue_tracker.user.IssueAssignee;


@Table("ISSUE")
@Getter
@Builder
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
    @MappedCollection(idColumn = "id", keyColumn = "issue_id")
    private List<IssueLabel> issueHasLabel;
    @MappedCollection(idColumn = "id", keyColumn = "issue_id")
    private List<IssueAssignee> issueAssignees;
    private IssueStatus status;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
    @CreatedDate
    private LocalDateTime createdAt;

    public void setStatus(IssueStatus status) {
        this.status = status;
    }
}