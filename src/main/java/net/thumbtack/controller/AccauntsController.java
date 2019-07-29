package net.thumbtack.controller;

import javax.servlet.http.Cookie;
import net.thumbtack.service.iface.UtilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccauntsController {

  private final UtilityService utilityService;

  public AccauntsController(UtilityService utilityService) {
    this.utilityService = utilityService;
  }

  @GetMapping
  public ResponseEntity<?> getAccaunt
      (@CookieValue(value = "role_id", required = false) Cookie cookieName) {
    return new ResponseEntity<>(utilityService.getAccaunt(cookieName), HttpStatus.OK);
  }

}
