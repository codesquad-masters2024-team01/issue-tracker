package team1.issuetracker.milestone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MilestoneMakeRequest {
    private String name;
    private String description;
    private String deadline;
}