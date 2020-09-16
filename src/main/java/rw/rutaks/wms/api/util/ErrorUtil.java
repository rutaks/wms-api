package rw.rutaks.wms.api.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import rw.rutaks.wms.api.exceptions.ValidationException;

public class ErrorUtil {
  public void checkForErrors(BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      Map<String, String> errors = new HashMap<>();
      String messages =
          bindingResult.getAllErrors().stream()
              .map(
                  error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                    return error.getDefaultMessage();
                  })
              .collect(Collectors.joining("::"));
      throw new ValidationException(messages, errors);
    }
  }

  public static void checkForError(BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
      throw new ValidationException(error);
    }
  }
}
