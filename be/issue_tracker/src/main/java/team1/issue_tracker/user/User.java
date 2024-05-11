package team1.issue_tracker.user;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERS")
@Getter
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String password;
    private String name;
    private String profileImg;
    private UserType type;
    private LocalDateTime createdAt;
}