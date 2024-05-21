package team1.issuetracker.domain.comment;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import team1.issuetracker.domain.comment.dto.CommentPostRequest;
import team1.issuetracker.domain.user.auth.Authenticator;
import team1.issuetracker.domain.user.auth.AuthorizeException;

import java.util.NoSuchElementException;


@RestController
public class CommentController {
    private final CommentService commentService;
    private final Authenticator authenticator;

    public CommentController(CommentService commentService, Authenticator authenticator) {
        this.commentService = commentService;
        this.authenticator = authenticator;
    }

    @PostMapping("/comment")
    public void createComment(@RequestBody CommentPostRequest commentInfo, HttpServletRequest request) {
        String userId = authenticator.authenticate(request);
        commentService.addComment(commentInfo.issueId(), userId, commentInfo); // 로그인 기능 구현 전 임시로 'test1' 유저 사용
    }

    @PatchMapping("/comment/{id}")
    public Comment updateComment(@PathVariable long id, @RequestBody CommentPostRequest commentInfo,
                                 HttpServletRequest request) throws NoSuchElementException, AuthorizeException {
        String userId = authenticator.authenticate(request);
        return commentService.updateComment(id, userId, commentInfo);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable long id, HttpServletRequest request) throws NoSuchElementException, AuthorizeException {
        String userId = authenticator.authenticate(request);
        commentService.deleteComment(id, userId);
    }

    @PostMapping("/comment/{id}/like")
    public int likeComment(@PathVariable long id, HttpServletRequest request) {
        String userId = authenticator.authenticate(request);
        return commentService.likeComment(userId, id); // 로그인 기능 구현 전 임시로 'test1' 유저 사용
    }

    @PostMapping("/comment/{id}/unlike")
    public int unLikeComment(@PathVariable long id, HttpServletRequest request) {
        String userId = authenticator.authenticate(request);
        return commentService.unlikeComment(userId, id);
    }
}
