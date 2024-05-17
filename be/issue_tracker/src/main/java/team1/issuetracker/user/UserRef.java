package team1.issuetracker.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ISSUE_HAS_ASSIGNEE")
@Getter
@Builder
@AllArgsConstructor
public class UserRef {
    @Id
    @Generated
    private Long id;
    private String userId;
}