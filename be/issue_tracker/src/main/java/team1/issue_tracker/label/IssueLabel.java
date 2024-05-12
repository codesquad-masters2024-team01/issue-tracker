package team1.issue_tracker.label;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ISSUE_HAS_LABEL")
@Getter
@AllArgsConstructor
public class IssueLabel {
    @Id
    private Long id;
    private Long issueId;
    private Long labelId;
}