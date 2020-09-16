package rw.rutaks.wms.api.exceptions;

import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {
  public AuthenticationException(String message) {
    super(message);
  }
}
