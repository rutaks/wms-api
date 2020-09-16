package rw.rutaks.wms.api.exceptions;

import java.util.Map;
import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
  private Map<String, String> errors;

  public ValidationException(String message, Map<String, String> errors) {
    super(message);
    this.errors = errors;
  }

  public ValidationException(String message) {
    super(message);
  }
}
