package team1.issuetracker.domain.comment;

import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Service;
import team1.issuetracker.domain.Issue.Issue;
import team1.issuetracker.domain.Issue.IssueService;
import team1.issuetracker.domain.comment.dto.CommentListResponse;
import team1.issuetracker.domain.comment.dto.CommentPostRequest;
import team1.issuetracker.domain.user.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final IssueService issueService;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, IssueService issueService, UserService userService) {
        this.commentRepository = commentRepository;
        this.issueService = issueService;
        this.userService = userService;
    }

    public List<CommentListResponse> getCommentsAtIssue(long issueId) {
        List<Comment> comments = commentRepository.findAllByIssueId(issueId);

        return comments.stream()
                .map(comment -> CommentListResponse.of(comment, userService.getNameById(comment.getUserId()))
                ).toList();
    }

    public void addComment(long issueId, String userId, CommentPostRequest request) throws NoSuchElementException {
        issueService.getIssueById(issueId);
        Comment newComment = Comment.makeOnlyComment(issueId, userId, request.content());
        commentRepository.save(newComment);
    }

    public int likeComment(String userId, long commentId) {
        Comment comment = getCommentById(commentId);
        List<Like> likes = comment.getLikes();
        likes.add(Like.of(userId, commentId));

        try {
            commentRepository.save(comment);
        } catch (DbActionExecutionException fail) {
            throw new IllegalStateException("이미 좋아요 하신 댓글입니다");
        }

        return likes.size();
    }

    public int unlikeComment(String userId, long commentId) {
        Comment comment = getCommentById(commentId);
        List<Like> likes = comment.getLikes();

        Optional<Like> optionalLike = likes.stream()
                .filter(isSame(userId, commentId))
                .findAny();

        Like like = optionalLike.orElseThrow(() -> new IllegalStateException("좋아요 한 댓글에만 좋아요 취소할 수 있습니다."));
        likes.remove(like);
        commentRepository.save(comment);

        return likes.size();
    }

    public String getFirstCommentTextAtIssue(Issue issue) {
        return commentRepository.findFirstAtIssue(issue.getId()).getContent();
    }


    public boolean canModify(long id, String userId) throws NoSuchElementException {
        return getCommentById(id).getUserId().equals(userId);
    }

    public Comment modifyComment(long id, String userId, CommentPostRequest commentInfo) throws NoSuchElementException {
        Comment origin = getCommentById(id);
        Comment newComment = Comment.makeOnlyComment(origin.getIssueId(), userId, commentInfo.content());
        commentRepository.save(newComment);
        return getCommentById(id);
    }

    public void deleteComment(Long commentId) throws NoSuchElementException {
        commentRepository.delete(getCommentById(commentId));
    }

    private Comment getCommentById(long commentId) {
        Optional<Comment> byId = commentRepository.findById(commentId);
        if (byId.isEmpty()) throw new NoSuchElementException("존재하지 않는 댓글입니다");

        return byId.get();
    }

    private Predicate<Like> isSame(String userId, long commentId) {
        return like -> like.getCommentId().equals(commentId) && like.getUserId().equals(userId);
    }
}
