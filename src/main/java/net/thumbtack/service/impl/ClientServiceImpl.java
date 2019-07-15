package net.thumbtack.service.impl;

import java.util.List;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.dto.ClientRegistrationDto;
import net.thumbtack.exception.OnlineShopErrorCode;
import net.thumbtack.exception.OnlineShopException;
import net.thumbtack.model.Client;
import net.thumbtack.repo.iface.ClientRepository;
import net.thumbtack.service.iface.ClientService;

public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }


  @Override
  public void addClient(ClientRegistrationDto clientRegistrationDto) {
    if (!clientRegistrationDto.getLogin().matches("^[а-яА-ЯёЁa-zA-Z0-9]{3,16}$")) {
      throw new OnlineShopException(OnlineShopErrorCode.USER_WRONG_LOGIN);
    }
    Client client = new Client();
    client.setId(clientRegistrationDto.getId());
    client.setLastName(clientRegistrationDto.getLastName());
    client.setFirstName(clientRegistrationDto.getFirstName());
    client.setPatronymic(clientRegistrationDto.getPatronymic());
    client.setEmail(clientRegistrationDto.getEmail());
    client.setAddress(clientRegistrationDto.getAddress());
    client.setPhone(clientRegistrationDto.getPhone());
    client.setLogin(clientRegistrationDto.getLogin());
    client.setPassword(clientRegistrationDto.getPassword());
    clientRepository.addClient(client);
  }

  @Override
  public List<ClientListDto> getAllClients() {
    return clientRepository.getAllClients();
  }
}
