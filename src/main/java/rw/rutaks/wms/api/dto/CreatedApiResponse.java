package rw.rutaks.wms.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CreatedApiResponse extends ApiResponse {
  public CreatedApiResponse(String message, Object payload) {
    super(HttpStatus.CREATED, message, payload);
  }
}
