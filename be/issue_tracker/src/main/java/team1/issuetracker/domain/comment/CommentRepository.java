package team1.issuetracker.domain.comment;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAllByIssueId(Long issueId);
    @Query("SELECT * FROM COMMENT WHERE ISSUE_ID = :issueId ORDER BY CREATED_AT LIMIT 1")
    Comment findFirstAtIssue(Long issueId);
}