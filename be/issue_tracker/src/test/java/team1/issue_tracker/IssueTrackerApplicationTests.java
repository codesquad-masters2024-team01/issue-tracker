package team1.issue_tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import team1.issue_tracker.Issue.IssueRepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@ActiveProfiles("dev")
class IssueTrackerApplicationTests {

    @Autowired
    IssueRepository issueRepository;

    @Test
    @DisplayName("MySql DB 연결에 성공한다")
    void testDbConnection(){
        assertDoesNotThrow(() -> issueRepository.findAll());
    }
}
