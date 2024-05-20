package team1.issuetracker.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import team1.issuetracker.Issue.dto.IssueMakeRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IssueTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    Request request = new Request();

    @DisplayName("이슈를 등록할 수 있다.")
    @Test
    void postIssue() {
        // given
        IssueMakeRequest issueMakeRequest = new IssueMakeRequest("이슈등록테스트용제목", "내용", List.of(), List.of(), null);

        // when
        ExtractableResponse<Response> response = request.post(issueMakeRequest, "/issue");

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.contentType()).isEqualTo(ContentType.JSON.toString());
        assertThat(response.body().jsonPath().getString("title")).isEqualTo("이슈등록테스트용제목");
    }

    @DisplayName("이슈 등록시 존재하지 않는 라벨은 이슈에 추가할 수 없다.")
    @Test
    void postIssueWithNotExistsLabel() {
        // given
        IssueMakeRequest issueMakeRequest = new IssueMakeRequest("제목", "내용", List.of(), List.of(20L), null);

        // when
        ExtractableResponse<Response> response = request.post(issueMakeRequest, "/issue");

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("이슈 등록시 존재하지 않는 유저는 담당자로 추가 할 수 없다.")
    @Test
    void postIssueWithNotExistsUser() {
        // given
        IssueMakeRequest issueMakeRequest = new IssueMakeRequest("제목", "내용", List.of("noUser"), List.of(), null);

        // when
        ExtractableResponse<Response> response = request.post(issueMakeRequest, "/issue");

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("이슈 등록시 존재하지 않는 마일스톤은 이슈에 설정할 수 없다.")
    @Test
    void postIssueWithNotExistsMilestone() {
        // given
        IssueMakeRequest issueMakeRequest = new IssueMakeRequest("제목", "내용", List.of(), List.of(), 20L);

        // when
        ExtractableResponse<Response> response = request.post(issueMakeRequest, "/issue");

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("이슈목록을 조회할 수 있다")
    @Test
    void getIssueList() {
        // given
        IssueMakeRequest issueMakeRequest = new IssueMakeRequest("제목", "내용", List.of(), List.of(), null);
        request.post(issueMakeRequest, "/issue");
        request.post(issueMakeRequest, "/issue");
        request.post(issueMakeRequest, "/issue");
        request.post(issueMakeRequest, "/issue");

        // when
        ExtractableResponse<Response> response = request.get("/issue/list");

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.contentType()).isEqualTo(ContentType.JSON.toString());
    }
}
