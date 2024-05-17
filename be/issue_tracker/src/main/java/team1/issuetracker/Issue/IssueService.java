package team1.issuetracker.Issue;

import static team1.issuetracker.Issue.IssueStatus.CLOSE;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.StringJoiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public List<Issue> getOpenIssues() {
        return issueRepository.findAllByStatus(IssueStatus.OPEN);
    }

    public Issue showIssue(Long id) throws NoSuchElementException {
        return getIssueById(id);
    }

    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    public void closeIssue(Long id) throws NoSuchElementException {
        Issue issue = getIssueById(id);
        if (issue.getStatus() == CLOSE) throw new IllegalStateException(id + "번 이슈는 이미 닫힌 상태입니다!");

        issue.setStatus(CLOSE);
        issueRepository.save(issue);
    }

    public void deleteIssue(Long id) throws NoSuchElementException {
        issueRepository.delete(getIssueById(id));
    }

    public Issue getIssueById(Long id) throws NoSuchElementException {
        Optional<Issue> optionalIssue = issueRepository.findById(id);
        if (optionalIssue.isEmpty()) {
            throw new NoSuchElementException(id + "번 이슈가 존재하지 않습니다!");
        }

        return optionalIssue.get();
    }

    public void closeIssues(List<Long> issueIds) {
        StringJoiner exceptionMessages = new StringJoiner("\n");
        issueIds.forEach(id -> {
            try {
                closeIssue(id);
            } catch (RuntimeException exception) {
                exceptionMessages.add(exception.getMessage());
            }
        });

        if (exceptionMessages.length() != 0) {
            throw new RuntimeException(exceptionMessages.toString());
        }
    }
}