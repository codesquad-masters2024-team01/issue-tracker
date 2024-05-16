package team1.issue_tracker.Issue.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team1.issue_tracker.label.Label;
import team1.issue_tracker.milestone.dto.MilestoneShowResponse;

@Getter
@AllArgsConstructor
public class IssueListResponse {
    private long id;
    private String title;
    private String comment;
    private List<Label> labels;
    private MilestoneShowResponse milestone;
}