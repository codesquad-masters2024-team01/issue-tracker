package team1.issue_tracker.milestone;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.ActiveProfiles;

@DataJdbcTest
class MilestoneRepositoryTest {

    @Autowired
    MilestoneRepository milestoneRepository;

    @DisplayName("마일스톤을 저장, 조회할 수 있다")
    @Test
    void saveAndFindById() {
        // given
        Milestone milestone = new Milestone("이름", "설명", LocalDateTime.of(2024, 7, 13, 0, 0));

        // when
        milestoneRepository.save(milestone);
        Optional<Milestone> byId = milestoneRepository.findById(1L);

        // then
        assertThat(byId.isPresent()).isTrue();
        assertThat(byId.get().getName()).isEqualTo("이름");
        assertThat(byId.get().getDescription()).isEqualTo("설명");
        assertThat(byId.get().getDeadline()).isEqualTo("2024-07-13T00:00:00");
    }
}