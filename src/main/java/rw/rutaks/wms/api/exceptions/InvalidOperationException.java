package rw.rutaks.wms.api.exceptions;

import lombok.Getter;

@Getter
public class InvalidOperationException extends RuntimeException {
  public InvalidOperationException(String message) {
    super(message);
  }
}
