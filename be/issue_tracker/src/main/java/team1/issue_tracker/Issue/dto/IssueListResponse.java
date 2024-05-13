package team1.issue_tracker.Issue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team1.issue_tracker.comment.Comment;
import team1.issue_tracker.label.Label;

import java.util.List;
import team1.issue_tracker.milestone.Milestone;

@Getter
@AllArgsConstructor
public class IssueListResponse {
    private long id;
    private String title;
    private String comment;
    private List<Label> labels;
    private Milestone milestone;
}
