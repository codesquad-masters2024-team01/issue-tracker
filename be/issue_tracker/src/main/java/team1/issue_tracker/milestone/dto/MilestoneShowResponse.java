package team1.issue_tracker.milestone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MilestoneShowResponse {
    private Long id;
    private String title;
}
