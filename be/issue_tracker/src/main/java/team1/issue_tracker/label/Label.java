package team1.issue_tracker.label;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import team1.issue_tracker.label.dto.LabelMakeRequest;

@Table("LABEL")
@Getter
@Builder
@AllArgsConstructor
public class Label {
    @Id
    @Generated
    private Long id;
    private String name;
    private String description;
    private String color;
    @CreatedDate
    private LocalDateTime createdAt;

    public static Label of(LabelMakeRequest labelMakeRequest) {
        return Label.builder()
            .name(labelMakeRequest.getName())
            .description(labelMakeRequest.getDescription())
            .color(labelMakeRequest.getColor())
            .build();
    }
}
