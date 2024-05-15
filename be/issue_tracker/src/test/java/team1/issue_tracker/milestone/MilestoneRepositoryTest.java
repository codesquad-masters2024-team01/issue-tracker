package team1.issue_tracker.milestone;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

@DataJdbcTest
class MilestoneRepositoryTest {

    @Autowired
    MilestoneRepository milestoneRepository;

    @DisplayName("마일스톤을 저장, 조회할 수 있다")
    @Test
    void saveAndFindById() {
        // given
        Milestone milestone = Milestone.builder().name("마일스톤").deadline(LocalDateTime.of(2024, 7, 13, 0, 0)).build();

        // when
        milestoneRepository.save(milestone);
        Milestone found = milestoneRepository.findById(1L).get();

        Milestone expected = Milestone.builder()
                .name("이름")
                .description("설명")
                .deadline(LocalDateTime.of(2024, 7, 13, 0, 0))
                .build();

        // then
        assertThat(found).usingRecursiveComparison().isEqualTo(expected);
//        assertThat(found.getName()).isEqualTo("이름");
//        assertThat(found.getDescription()).isEqualTo("설명");
//        assertThat(found.getDeadline()).isEqualTo("2024-07-13T00:00:00");
    }
}