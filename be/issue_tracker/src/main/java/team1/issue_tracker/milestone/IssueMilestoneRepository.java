package team1.issue_tracker.milestone;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueMilestoneRepository extends CrudRepository<IssueMilestone, Long> {
    List<IssueMilestone> findAllByIssueId(Long issueId);
}