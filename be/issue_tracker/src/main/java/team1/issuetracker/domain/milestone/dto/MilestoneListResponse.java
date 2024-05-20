package team1.issuetracker.domain.milestone.dto;

import java.util.Date;
import team1.issuetracker.domain.milestone.Milestone;


public record MilestoneListResponse(long id, String name, String description, Date deadline, int openCounts,
                                    int closedCounts, int completion) {
    public static MilestoneListResponse of(Milestone milestone) {
        return new MilestoneListResponse(milestone.getId(), milestone.getName(),
                milestone.getDescription(), milestone.getDeadline(), milestone.getOpenIssueCount(),
                milestone.getClosedIssueCount(),
                milestone.getCompletionPercentage());
    }
}