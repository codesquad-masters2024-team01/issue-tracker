package team1.issue_tracker.milestone;

import java.time.LocalDateTime;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("MILESTONE")
@Getter
@Setter
@NoArgsConstructor
public class Milestone {

    @Id
    @Generated
    private Long id;
    private String name;
    private String description;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;

    public Milestone(String name, String description,  LocalDateTime deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }
}