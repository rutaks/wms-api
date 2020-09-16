package rw.rutaks.wms.api.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rw.rutaks.wms.api.dto.AuthenticationRequestDto;
import rw.rutaks.wms.api.dto.AuthenticationResponseDto;
import rw.rutaks.wms.api.dto.CreatedApiResponse;
import rw.rutaks.wms.api.enums.Role;
import rw.rutaks.wms.api.exceptions.AuthenticationException;
import rw.rutaks.wms.api.model.Auth;
import rw.rutaks.wms.api.model.User;
import rw.rutaks.wms.api.service.AuthService;
import rw.rutaks.wms.api.util.ErrorUtil;
import rw.rutaks.wms.api.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private AuthService authService;
  @Autowired private JwtUtil jwtUtil;

  @PostMapping("/login/admin")
  public ResponseEntity<?> adminLogin(
      @RequestBody @Valid AuthenticationRequestDto authenticationRequestDto,
      BindingResult bindingResult) {
    try {
      ErrorUtil.checkForError(bindingResult);
      String username = authenticationRequestDto.getUsername();
      String password = authenticationRequestDto.getPassword();
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
      final Auth auth = authService.getAuthByUsername(username);
      final boolean isAdmin = auth.getRole() == Role.SUPER_ADMIN;

      if (!isAdmin) {
        throw new AuthenticationException("Invalid username or password");
      }

      String token = jwtUtil.generateToken(username, auth);
      User user = auth.getUser();

      AuthenticationResponseDto<User> authResponseDto =
          new AuthenticationResponseDto<>(token, user);
      CreatedApiResponse response =
          new CreatedApiResponse("Authentication successful", authResponseDto);

      return ResponseEntity.status(response.getStatus()).body(response);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("Invalid username or password");
    } catch (Exception e) {
      throw new AuthenticationException(e.getMessage());
    }
  }
}
