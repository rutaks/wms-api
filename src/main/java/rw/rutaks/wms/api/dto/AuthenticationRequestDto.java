package rw.rutaks.wms.api.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rw.rutaks.wms.api.annotation.ValidPassword;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDto {
  @NotBlank(message = "Username can not be empty")
  private String username;

  @ValidPassword(message = "Password is not valid")
  private String password;
}
