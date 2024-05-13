package team1.issue_tracker.Issue;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team1.issue_tracker.Issue.dto.IssueListResponse;
import team1.issue_tracker.Issue.dto.IssueShowResponse;
import team1.issue_tracker.label.IssueLabel;
import team1.issue_tracker.label.Label;
import team1.issue_tracker.label.LabelRepository;

import java.util.List;
import team1.issue_tracker.milestone.Milestone;
import team1.issue_tracker.milestone.MilestoneRepository;
import team1.issue_tracker.user.IssueAssignee;
import team1.issue_tracker.user.User;
import team1.issue_tracker.user.UserRepository;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final LabelRepository labelRepository;
    private final MilestoneRepository milestoneRepository;
    private final UserRepository userRepository;


    @Autowired
    public IssueService(IssueRepository issueRepository, LabelRepository labelRepository,
                        MilestoneRepository milestoneRepository, UserRepository userRepository) {
        this.issueRepository = issueRepository;
        this.labelRepository = labelRepository;
        this.milestoneRepository = milestoneRepository;
        this.userRepository = userRepository;
    }

    public List<IssueListResponse> getList() {
        List<Issue> issueList = (List<Issue>) issueRepository.findAll();

        return issueList.stream().map(issue -> new IssueListResponse(
                issue.getId(),
                issue.getTitle(),
                issue.getComments().get(0).getContent(),
                labelsAtIssue(issue),
                getMilestone(issue))).toList();
    }

    private Milestone getMilestone(Issue issue) {
        if (issue.getMilestoneId() == null) {
            return null;
        }

        return milestoneRepository.findById(issue.getMilestoneId()).get();
    }

    private List<Label> labelsAtIssue(Issue issue) {
        List<IssueLabel> byIssueId = issue.getIssueHasLabel();
        List<Long> labelIds = byIssueId.stream().map(IssueLabel::getLabelId).toList();

        return (List<Label>) labelRepository.findAllById(labelIds);
    }

    public void closeIssue(Long id) {
        Optional<Issue> byId = issueRepository.findById(id);
        if (byId.isPresent()) {
            Issue issue = byId.get();
            issue.setStatus(IssueStatus.CLOSE);
            issueRepository.save(issue);
        }
    }

    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);
    }

    public IssueShowResponse showIssue(Long id) {
        Optional<Issue> byId = issueRepository.findById(id);
        if (byId.isPresent()) {
            Issue issue = byId.get();

            String userId = issue.getUserId();
            User user = userRepository.findById(userId).get();

            List<IssueAssignee> issueAssignees = issue.getIssueAssignees();
            List<String> assignees = issueAssignees.stream().map(IssueAssignee::getAssigneeId)
                    .map(userRepository::findById)
                    .map(optionalUser -> optionalUser.get().getName()).toList();

            return new IssueShowResponse(id, issue.getTitle(), user.getName(),
                    issue.getLastModifiedAt(), issue.getStatus(), assignees, labelsAtIssue(issue),
                    getMilestone(issue), issue.getComments()
            );
        }
        return null;
    }

}
