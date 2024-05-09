package team1.issue_tracker.label;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ISSUE_LABEL")
@Getter
@AllArgsConstructor
public class IssueLabel {
    @Id
    private Long id;
    @Column("issue_id")
    private Long issueId;
    @Column("label_id")
    private Long labelId;
}