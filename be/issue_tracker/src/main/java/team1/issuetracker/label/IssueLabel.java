<<<<<<<< HEAD:be/issue_tracker/src/main/java/team1/issuetracker/label/IssueLabel.java
package team1.issuetracker.label;
========
package team1.issue_tracker.Issue;
>>>>>>>> 2860eee (feat(#41): 이슈 생성하기 기능):be/issue_tracker/src/main/java/team1/issuetracker/Issue/LabelRef.java

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ISSUE_HAS_LABEL")
@Getter
@Builder
@AllArgsConstructor
public class LabelRef {
    @Id
    @Generated
    private Long id;
    private Long labelId;

    public static LabelRef of(Long issueId , Long labelId){
        return LabelRef.builder()
                .labelId(labelId)
                .build();
    }
}