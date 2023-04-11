package ru.tinkoff.edu.java.bot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.tinkoff.edu.java.bot.DTO.respronses.ApiErrorResponse;
import java.util.List;

@Component
@RestControllerAdvice
public class DefaultExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception) {
        logger.error("Unexpected server error", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiErrorResponse("Application runtime error", "400", exception.getClass().getName(), exception.getMessage(), List.of()));
    }
}
