package team1.issue_tracker.milestone;

import java.time.LocalDateTime;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("MILESTONE")
@Getter
@Setter
@NoArgsConstructor
public class Milestone {

    @Id
    @Generated
    private Long id;
    private String name; // 제목
    private String description; // 설명
    @Column("created_at")
    private LocalDateTime createdAt;
    private LocalDateTime deadline; // 완료일

    public Milestone(Long id, String name, String description, LocalDateTime createdAt, LocalDateTime deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.deadline = deadline;
    }
}