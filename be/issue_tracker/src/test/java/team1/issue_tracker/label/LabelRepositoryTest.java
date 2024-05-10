package team1.issue_tracker.label;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
public class LabelRepositoryTest {

    @Autowired
    LabelRepository labelRepository;

    @Test
    void idAutoGenerate() {
        Label label = Label.builder()
                .name("이름")
                .description("설명")
                .color("#000000")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        assertThat(label.getId()).isNull();

        Label saved = labelRepository.save(label);
        assertThat(saved.getId()).isNotNull();
    }

    @DisplayName("라벨을 저장하고 조회할 수 있다")
    @Test
    void saveAndFindById() {
        // given
        Label label = Label.builder()
                .name("이름")
                .description("설명")
                .color("#000000")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        // when
        labelRepository.save(label);
        System.out.println(labelRepository.count());
        Optional<Label> byId = labelRepository.findById(1L);

        //then
//        assertThat(byId.isPresent()).isTrue();
        assertThat(byId.get().getName()).isEqualTo("이름");
        assertThat(byId.get().getDescription()).isEqualTo("설명");
        assertThat(byId.get().getColor()).isEqualTo("#000000");
    }
}
