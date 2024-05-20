package team1.issuetracker.unit.milestone;

import static org.assertj.core.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import team1.issuetracker.domain.milestone.Milestone;
import team1.issuetracker.domain.milestone.MilestoneRepository;

@DataJdbcTest
class MilestoneRepositoryTest {

    @Autowired
    MilestoneRepository milestoneRepository;

    @DisplayName("마일스톤을 저장, 조회할 수 있다")
    @Test
    void saveAndFindById() {
        // given
        Milestone milestone = Milestone.builder().name("마일스톤").deadline(Date.valueOf("2000.07.13")).build();

        // when
        milestoneRepository.save(milestone);
        Milestone found = milestoneRepository.findById(1L).get();

        Milestone expected = Milestone.builder()
                .name("이름")
                .description("설명")
                .deadline(Date.valueOf("2000.07.13"))
                .build();

        // then
        assertThat(found).usingRecursiveComparison().isEqualTo(expected);
//        assertThat(found.getName()).isEqualTo("이름");
//        assertThat(found.getDescription()).isEqualTo("설명");
//        assertThat(found.getDeadline()).isEqualTo("2024-07-13T00:00:00");
    }
}