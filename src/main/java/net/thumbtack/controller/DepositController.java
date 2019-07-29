package net.thumbtack.controller;

import javax.servlet.http.Cookie;
import net.thumbtack.dto.ClientResponseDto;
import net.thumbtack.service.iface.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(name = "api/deposits")
public class DepositController {

  private final ClientService clientService;

  public DepositController(ClientService clientService) {
    this.clientService = clientService;
  }

  @PutMapping(name = "api/deposits", produces = "application/json")
  public ResponseEntity<?> toDeposit
      (@RequestBody String deposit,
      @CookieValue(value = "role_id", required = false) Cookie cookieName){
    Object responseClass = clientService.toDeposit(deposit, cookieName);
    if (responseClass instanceof ClientResponseDto) {
      return ResponseEntity.ok(responseClass);
    }
    else return new ResponseEntity<>(responseClass, HttpStatus.BAD_REQUEST);
  }

  @GetMapping(name = "api/deposits", produces = "application/json")
  public ResponseEntity<ClientResponseDto> getDeposit
      (@CookieValue(value = "role_id", required = false) Cookie cookieName){
    return ResponseEntity.ok(clientService.getDeposit(cookieName));
  }
}
