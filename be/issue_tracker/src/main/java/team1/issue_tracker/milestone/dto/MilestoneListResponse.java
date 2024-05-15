package team1.issue_tracker.milestone.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team1.issue_tracker.milestone.Milestone;

@Getter
@AllArgsConstructor
public class MilestoneListResponse {
    private long id;
    private String name;
    private String description;
    private LocalDateTime deadline;
    private int openCounts;
    private int closedCounts;
    private int completion;

    public static MilestoneListResponse of(Milestone milestone) {
        return new MilestoneListResponse(milestone.getId(), milestone.getName(),
                milestone.getDescription(), milestone.getDeadline(), milestone.getOpenIssueCount(),
                milestone.getClosedIssueCount(),
                milestone.getCompletionPercentage());
    }
}