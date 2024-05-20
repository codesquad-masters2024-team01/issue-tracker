package team1.issuetracker.domain.milestone.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public record MilestoneMakeRequest(String name, String description,
                                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd") Date deadline) {
}