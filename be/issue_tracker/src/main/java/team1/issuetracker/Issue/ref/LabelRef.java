package team1.issuetracker.Issue.ref;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;

@Table("ISSUE_HAS_LABEL")
@Getter
@Builder
@AllArgsConstructor
public class LabelRef {
    private Long labelId;
}