package team1.issuetracker.milestone.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MilestoneMakeRequest {
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date deadline;
}