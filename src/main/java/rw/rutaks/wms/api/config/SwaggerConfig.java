package rw.rutaks.wms.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI swaggerApiConfig() {
    return new OpenAPI()
        .components(
            new Components()
                .addSecuritySchemes(
                    "token",
                    new SecurityScheme()
                        .description("Token is required to have `Bearer ` in front of it")
                        .type(Type.APIKEY)
                        .in(In.HEADER)))
        .info(
            new Info()
                .title("Water Management System")
                .version("v1")
                .description("API for the Water Management System")
                .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")));
  }
}
