package net.thumbtack.controller;

import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.dto.ClientRegistrationDto;
import net.thumbtack.dto.ClientResponseDto;
import net.thumbtack.service.iface.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping
  public ResponseEntity<?> addClient
      (@RequestBody ClientRegistrationDto clientRegistrationDto, HttpServletResponse response) {
    Object responseClass = clientService.addClient(clientRegistrationDto);
    if (responseClass instanceof ClientResponseDto) {
      Cookie cookie = new Cookie("role_id", "admin!" + ((ClientResponseDto) responseClass).getId());
      response.addCookie(cookie);
      return ResponseEntity.ok(responseClass);
    }
    else return new ResponseEntity<>(responseClass, HttpStatus.BAD_REQUEST);
  }

  @GetMapping
  public ResponseEntity<List<ClientListDto>> getAllClients() {
    List<ClientListDto> clientListDtos = this.clientService.getAllClients();
    return new ResponseEntity<>(clientListDtos, HttpStatus.OK);
  }


}
