package net.thumbtack.controller;

import net.thumbtack.dto.ClientDto;
import net.thumbtack.service.iface.ClientService;
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
  public void addClient(@RequestBody ClientDto clientDto) {
    this.clientService.addClient(clientDto);
  }
}
