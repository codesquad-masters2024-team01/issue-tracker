package team1.issue_tracker.temp;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table("USERS")
public class User {
    @Id
    private String id;
    private String password;
    private String name;
    @Column("created_at")
    private Timestamp createdAt;
    @Column("profile_img")
    private String profileImg;
    private UserType type;
}