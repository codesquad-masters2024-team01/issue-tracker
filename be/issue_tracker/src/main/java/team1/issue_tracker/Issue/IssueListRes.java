package team1.issue_tracker.Issue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team1.issue_tracker.label.Label;
import team1.issue_tracker.temp.Milestone;

import java.util.List;

@Getter
@AllArgsConstructor
public class IssueListRes {
    private long id;
    private String title;
    private String comment;
    private List<Label> labels;
    private List<Milestone> milestones;
}
