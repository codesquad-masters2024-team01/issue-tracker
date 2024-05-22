package team1.issuetracker.domain.comment;

import org.springframework.web.bind.annotation.*;
import team1.issuetracker.domain.comment.dto.CommentPostRequest;
import team1.issuetracker.domain.user.auth.annotation.Authenticate;
import team1.issuetracker.domain.user.auth.annotation.AuthenticatedUserId;
import team1.issuetracker.domain.user.auth.AuthorizeException;

import java.util.NoSuchElementException;


@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Authenticate
    @PostMapping("/comment")
    public void createComment(@RequestBody CommentPostRequest commentInfo, @AuthenticatedUserId String userId) {
        commentService.addComment(commentInfo.issueId(), userId, commentInfo);
    }

    @Authenticate
    @PatchMapping("/comment/{id}")
    public Comment updateComment(@PathVariable long id, @RequestBody CommentPostRequest commentInfo,
                                 @AuthenticatedUserId String userId) throws NoSuchElementException, AuthorizeException {
        return commentService.updateComment(id, userId, commentInfo);
    }

    @Authenticate
    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable long id, @AuthenticatedUserId String userId) throws NoSuchElementException, AuthorizeException {
        commentService.deleteComment(id, userId);
    }

    @Authenticate
    @PostMapping("/comment/{id}/like")
    public int likeComment(@PathVariable long id, @AuthenticatedUserId String userId) {
        return commentService.likeComment(userId, id);
    }

    @Authenticate
    @PostMapping("/comment/{id}/unlike")
    public int unLikeComment(@PathVariable long id, @AuthenticatedUserId String userId) {
        return commentService.unlikeComment(userId, id);
    }
}
