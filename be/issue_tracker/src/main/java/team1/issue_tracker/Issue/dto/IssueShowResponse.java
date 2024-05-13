package team1.issue_tracker.Issue.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team1.issue_tracker.Issue.IssueStatus;
import team1.issue_tracker.comment.Comment;
import team1.issue_tracker.label.Label;
import team1.issue_tracker.milestone.Milestone;

@Getter
@AllArgsConstructor
public class IssueShowResponse {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime lastModifiedAt;
    private IssueStatus status;
    private List<String> assignees;
    private List<Label> labels;
    private Milestone milestone;
    private List<Comment> comments;
}