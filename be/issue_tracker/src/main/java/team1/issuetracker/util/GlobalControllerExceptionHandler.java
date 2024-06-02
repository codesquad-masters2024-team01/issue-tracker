package team1.issuetracker.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team1.issuetracker.domain.user.auth.exception.AuthenticateException;
import team1.issuetracker.domain.user.auth.exception.AuthorizeException;

@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException e) {
        log.error(e.getClass().getSimpleName() + " : " + e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizeException.class)
    public String handleAuthorizeException(AuthorizeException e) {
        log.error(e.getClass().getSimpleName() + " : " + e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticateException.class)
    public String handleAuthenticateException(AuthenticateException e) {
        log.error(e.getClass().getSimpleName() + " : " + e.getMessage());
        return e.getMessage();
    }
}
