package team1.issuetracker.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import team1.issuetracker.milestone.dto.MilestoneMakeRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MilestoneTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    Request request = new Request();

    @DisplayName("마일스톤을 생성할 수 있다.")
    @Test
    void createMilestone() {
        //given
        MilestoneMakeRequest makeRequest = new MilestoneMakeRequest("마일스톤1", "설명", 2020.07.13);

        //when
        ExtractableResponse<Response> response = request.post(makeRequest, "/milestone");

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("마일스톤 목록을 조회할 수 있다.")
    @Test
    void getMilestoneList() {
        //given
        MilestoneMakeRequest makeRequest = new MilestoneMakeRequest("마일스톤1", "설명", "2000.07.13");
        request.post(makeRequest, "/milestone");
        request.post(makeRequest, "/milestone");
        request.post(makeRequest, "/milestone");

        //when
        ExtractableResponse<Response> response = request.get("/milestone/list");

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.contentType()).isEqualTo(ContentType.JSON.toString());
    }


}
