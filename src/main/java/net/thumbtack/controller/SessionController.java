package net.thumbtack.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import net.thumbtack.dto.AdminResponseDto;
import net.thumbtack.dto.ClientResponseDto;
import net.thumbtack.dto.LogInDto;
import net.thumbtack.service.iface.UtilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sessions")
public class SessionController {

  private final UtilityService utilityService;

  public SessionController(UtilityService utilityService) {
    this.utilityService = utilityService;
  }

  @PostMapping
  public ResponseEntity<?> logIn (@RequestBody LogInDto logInDto,
      HttpServletResponse response) {
    Object responseClass = utilityService.logIn(logInDto);
    if (responseClass instanceof AdminResponseDto) {
      Cookie cookie = new Cookie("role_id", "admin!"
          + ((AdminResponseDto) responseClass).getId());
      response.addCookie(cookie);
      return ResponseEntity.ok(responseClass);
    }
    if (responseClass instanceof ClientResponseDto) {
      Cookie cookie = new Cookie("role_id", "client!"
          + ((ClientResponseDto) responseClass).getId());
      response.addCookie(cookie);
      return ResponseEntity.ok(responseClass);
    }
    else return new ResponseEntity<>(responseClass, HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping
  public ResponseEntity<String> logOut
      (HttpServletResponse response) {
    Cookie cookie = new Cookie("role_id", "");
    response.addCookie(cookie);
    return  new ResponseEntity<>("{}",HttpStatus.OK);
  }




}
