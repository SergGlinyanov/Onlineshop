package net.thumbtack.controller;

import java.util.List;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.dto.ClientRegistrationDto;
import net.thumbtack.service.iface.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping(value = "/api/clients")
  public void addClient(@RequestBody ClientRegistrationDto clientRegistrationDto) {
    this.clientService.addClient(clientRegistrationDto);
  }

  @GetMapping("/api/clients")
  public ResponseEntity<List<ClientListDto>> getAllClients() {
    List<ClientListDto> clientListDtos = this.clientService.getAllClients();
    return new ResponseEntity<>(clientListDtos, HttpStatus.OK);
  }
}
