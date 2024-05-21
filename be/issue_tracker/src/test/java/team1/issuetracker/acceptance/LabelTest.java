package team1.issuetracker.acceptance;


import static org.assertj.core.api.Assertions.assertThat;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import team1.issuetracker.domain.label.dto.LabelMakeRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LabelTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    Request request = new Request();

    @DisplayName("레이블을 등록할 수 있다.")
    @Test
    void postLabelTest() {
        //public record LabelMakeRequest(String name, String description, String color)
        LabelMakeRequest labelMakeRequest = new LabelMakeRequest("testLabel", "testDescription"
            , "#000000");

        // when
        ExtractableResponse<Response> response = request.post(labelMakeRequest, "/label");

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("레이블을 삭제할 수 있다")
    @Test
    void deleteLabelTest() {
        //public record LabelMakeRequest(String name, String description, String color)
        LabelMakeRequest labelMakeRequest = new LabelMakeRequest("testLabel", "testDescription"
            , "#000000");
        request.post(labelMakeRequest, "/label");

        // when
        ExtractableResponse<Response> response = request.delete("/label/1");
            //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("레이블을 업데이트할 수 있다")
    @Test
    void updateLabelTest() {
        //public record LabelMakeRequest(String name, String description, String color)
        LabelMakeRequest labelMakeRequest = new LabelMakeRequest("testLabel", "testDescription"
            , "#000000");
        request.post(labelMakeRequest, "/label");

        //when
        LabelMakeRequest updateLabelRequest = new LabelMakeRequest("updateLabel", "testDescription"
            , "#000000");
        ExtractableResponse<Response> response = request.patch(updateLabelRequest, "/label/1");

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.contentType()).isEqualTo(ContentType.JSON.toString());
        assertThat(response.body().jsonPath().getString("name")).isEqualTo("updateLabel");
    }

    @DisplayName("레이블 목록을 조회할 수 있따")
    @Test
    void listLabelTest() {
        //given
        LabelMakeRequest labelMakeRequest = new LabelMakeRequest("testLabel", "testDescription"
            , "#000000");
        request.post(labelMakeRequest, "/label");

        //when
        ExtractableResponse<Response> response = request.get("/label/list");

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.contentType()).isEqualTo(ContentType.JSON.toString());
        // JSON 응답에서 목록(List)을 가져옴
        List<Map<String, Object>> itemList = response.body().jsonPath().getList("$");

        // 첫 번째 항목의 id 값 가져오기
        String id = itemList.get(0).get("id").toString();

        // 가져온 ID 값 비교
        assertThat(id).isEqualTo("1");
    }


}
