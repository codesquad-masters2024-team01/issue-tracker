package team1.issuetracker.Issue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import team1.issuetracker.label.IssueLabel;
import team1.issuetracker.user.IssueAssignee;

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
    private Set<LabelRef> issueHasLabel;
    @MappedCollection(idColumn = "ISSUE_ID")
    private Set<UserRef> issueAssignees;
    private IssueStatus status;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
    @CreatedDate
    private LocalDateTime createdAt;

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public static Issue makeOnlyIssue(String authorId, String title) {
        return Issue.builder()
                .userId(authorId)
                .title(title)
                .status(IssueStatus.OPEN)
                .build();
    }

    public static Issue from(IssueMakeRequest request, String userId) {
        String title = request.getTitle();
        String comment = request.getComment();
        List<String> assigneeIds = request.getAssigneeIds();
        List<Long> labelIds = request.getLabelIds();
        Long milestoneId = request.getMilestoneId();

        Set<UserRef> issueAssignees = assigneeIds.stream()
                .map(assigneeId -> UserRef.builder().userId(assigneeId).build())
                .collect(Collectors.toSet());

        Set<LabelRef> issueHasLabel = labelIds.stream().map(labelId -> LabelRef.builder().labelId(labelId).build())
                .collect(Collectors.toSet());

        Issue build = Issue.builder()
                .userId(userId)
                .title(title)
                .issueHasLabel(issueHasLabel)
                .issueAssignees(issueAssignees)
                .milestoneId(milestoneId)
                .status(IssueStatus.OPEN)
                .build();

        return build;
    }
}