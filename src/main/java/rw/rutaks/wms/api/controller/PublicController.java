package rw.rutaks.wms.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
  @GetMapping("/test")
  public String testRoute() {
    return "Testing Public Response";
  }
}
