package rw.rutaks.wms.api.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rw.rutaks.wms.api.dto.ApiResponse;
import rw.rutaks.wms.api.exceptions.AuthenticationException;
import rw.rutaks.wms.api.exceptions.InvalidOperationException;
import rw.rutaks.wms.api.exceptions.ValidationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
    log.error("InternalException", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null));
  }

  @ExceptionHandler({UsernameNotFoundException.class, AuthenticationException.class})
  protected ResponseEntity<Object> handleNotFoundException(Exception ex) {
    log.error("NotFoundException", ex);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new ApiResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), null));
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ApiResponse> handleValidationException(ValidationException ex) {
    ApiResponse body = new ApiResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getErrors());
    log.error("ValidationException", ex);
    return ResponseEntity.badRequest().body(body);
  }

  @ExceptionHandler({InvalidOperationException.class})
  public ResponseEntity<ApiResponse> handleInvalidOperationException(Exception ex) {
    ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    log.error("InvalidOperationException", ex);
    return ResponseEntity.badRequest().body(response);
  }
}
