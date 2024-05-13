package team1.issue_tracker.Issue.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IssueMakeRequest {
    private String title;
    private String comment;
    private List<String> assigneeIds;
    private List<Long> labelIds;
    private Long milestoneId;
}
