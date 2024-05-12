package team1.issue_tracker.label;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table("LABEL")
@Getter
@Setter
@NoArgsConstructor
public class Label {
    @Id
    @Generated
    private Long id;
    private String name;
    private String description;
    private String color;
    private Timestamp created_at;

    public Label(String name, String description, String color) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.created_at = Timestamp.valueOf(LocalDateTime.now());
    }
}
