package team1.issue_tracker.comment;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("COMMENT")
@Getter
@AllArgsConstructor
public class Comment {
    @Id
    @Generated
    private Long id;
    private Long issueId;
    private String userId;
    private String content;

    private LocalDateTime lastModifiedAt;
    private LocalDateTime createdAt;

    public String getContent() {
        return content;
    }
}