package team1.issue_tracker.label;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
public class LabelRepositoryTest {

    @Autowired
    LabelRepository labelRepository;

    @DisplayName("id와 createdAt을 지정하지 않고 저장하면 자동 생성되어 저장된다")
    @Test
    void autoGenerate() {
        // given
        Label label = Label.builder()
                .name("이름")
                .description("설명")
                .color("#000000")
                .build();

        // when
        assertThat(label.getId()).isNull();
        assertThat(label.getCreatedAt()).isNull();
        Label saved = labelRepository.save(label);

        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCreatedAt()).isNotNull();
    }

    @DisplayName("라벨을 저장하고 조회할 수 있다")
    @Test
    void saveAndFindById() {
        // given
        Label label = Label.builder()
                .name("이름")
                .description("설명")
                .color("#000000")
                .build();

        // when
        labelRepository.save(label);
        Label foundLabel = labelRepository.findAll().iterator().next();

        //then
        assertThat(foundLabel.getName()).isEqualTo("이름");
        assertThat(foundLabel.getDescription()).isEqualTo("설명");
        assertThat(foundLabel.getColor()).isEqualTo("#000000");
        assertThat(foundLabel.getCreatedAt()).isNotNull();
    }

    @DisplayName("Id 목록에 해당하는 모든 라벨을 한 번에 조회할 수 있다")
    @Test
    void findAllById() {
        // given
        Label label1 = Label.builder()
                .name("이름")
                .description("설명")
                .color("#000000")
                .build();

        Label label2 = Label.builder()
                .name("이름")
                .description("설명")
                .color("#000000")
                .build();

        Label label3 = Label.builder()
                .name("이름")
                .description("설명")
                .color("#000000")
                .build();

        Label label4 = Label.builder()
                .name("이름")
                .description("설명")
                .color("#000000")
                .build();

        // when
        labelRepository.save(label1);
        labelRepository.save(label2);
        labelRepository.save(label3);
        labelRepository.save(label4);
        List<Label> foundLabels = (List<Label>) labelRepository.findAllById(List.of(1L, 2L, 3L, 4L));

        //then
        assertThat(foundLabels.size()).isEqualTo(4);
        assertThat(foundLabels.get(3).getDescription()).isEqualTo("설명");
    }
}
