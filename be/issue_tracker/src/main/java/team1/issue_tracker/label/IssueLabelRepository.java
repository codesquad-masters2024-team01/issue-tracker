package team1.issue_tracker.label;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueLabelRepository extends CrudRepository<IssueLabel, Long> {
    List<IssueLabel> findAllByIssueId(Long issueId);
}
