package team1.issue_tracker.label;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@ActiveProfiles("test")
public class LabelRepositoryTest {

    @Autowired
    LabelRepository labelRepository;

    @DisplayName("라벨을 저장하고 조회할 수 있다")
    @Test
    void saveAndFindById() {
        // given
        Label label = new Label("이름", "설명", "#000000");

        // when
        labelRepository.save(label);
        Optional<Label> byId = labelRepository.findById(1L);

        //then
        assertThat(byId.isPresent()).isTrue();
        assertThat(byId.get().getName()).isEqualTo("이름");
        assertThat(byId.get().getDescription()).isEqualTo("설명");
        assertThat(byId.get().getColor()).isEqualTo("#000000");
    }
}
