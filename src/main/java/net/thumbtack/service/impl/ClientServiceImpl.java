package net.thumbtack.service.impl;

import net.thumbtack.dto.ClientDto;
import net.thumbtack.model.Client;
import net.thumbtack.repo.iface.ClientRepository;
import net.thumbtack.service.iface.ClientService;

public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }


  @Override
  public void addClient(ClientDto clientDto) {
    Client client = new Client();
    client.setId(clientDto.getId());
    client.setLastName(clientDto.getLastName());
    client.setFirstName(clientDto.getFirstName());
    client.setPatronymic(clientDto.getPatronymic());
    client.setEmail(clientDto.getEmail());
    client.setAddress(clientDto.getAddress());
    client.setPhone(clientDto.getPhone());
    client.setLogin(clientDto.getLogin());
    client.setPassword(clientDto.getPassword());
    clientRepository.addClient(client);
  }
}
