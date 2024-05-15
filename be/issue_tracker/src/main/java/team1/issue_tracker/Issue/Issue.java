package team1.issue_tracker.Issue;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import team1.issue_tracker.label.IssueLabel;
import team1.issue_tracker.user.IssueAssignee;

@Table("ISSUE")
@Getter
@Builder
@ToString
@AllArgsConstructor
public class Issue {
    @Id
    private Long id;
    private String userId;
    private Long milestoneId;
    private String title;
    @MappedCollection(idColumn = "ISSUE_ID")
    private Set<IssueLabel> issueHasLabel;
    @MappedCollection(idColumn = "ISSUE_ID")
    private Set<IssueAssignee> issueAssignees;
    private IssueStatus status;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
    @CreatedDate
    private LocalDateTime createdAt;

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

//    public void attacheLabel(Long labelId){
//        this.issueHasLabel.add(IssueLabel.of(this.id , labelId));
//    }

    public static Issue makeOnlyIssue(String authorId, String title){
        return Issue.builder()
                .userId(authorId)
                .title(title)
                .status(IssueStatus.OPEN)
                .build();
    }
}