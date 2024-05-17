package team1.issuetracker.Issue;

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