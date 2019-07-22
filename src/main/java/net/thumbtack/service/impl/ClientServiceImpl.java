package net.thumbtack.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.dto.ClientRegistrationDto;
import net.thumbtack.dto.ClientResponseDto;
import net.thumbtack.dto.ProductDto;
import net.thumbtack.exception.ErrorList;
import net.thumbtack.exception.MyError;
import net.thumbtack.model.Client;
import net.thumbtack.repo.iface.ClientRepository;
import net.thumbtack.service.iface.ClientService;

public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }


  @Override
  public Object addClient(ClientRegistrationDto clientRegistrationDto) {
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

    List<MyError> errors = new ArrayList<>();
    if(!clientRegistrationDto.getLastName().matches("^[а-яА-ЯёЁ]+$")
        ||!clientRegistrationDto.getFirstName().matches("^[а-яА-ЯёЁ]+$")
        ||!clientRegistrationDto.getPatronymic().matches("^[а-яА-ЯёЁ]+$")){
      errors.add(new MyError("WRONG_FORMAT",
          "Ф.И.О.", "Только кириллица."));
      return new ErrorList(errors);
    } else return new ClientResponseDto(clientRepository.addClient(client),
        client.getLastName(), client.getFirstName(), client.getPatronymic(),
        client.getEmail(), client.getAddress(), client.getPhone(), 0);
  }

  @Override
  public List<ClientListDto> getAllClients() {
    return clientRepository.getAllClients();
  }

  @Override
  public ClientResponseDto toDeposit(String deposit, Cookie cookie) {
    String[] cookieRequest = cookie.getValue().split("!");
    long idClient = Long.parseLong(cookieRequest[1]);
    int summ = Integer.parseInt(deposit.replaceAll("[^\\d]", ""));
    Client client = clientRepository.toDeposit(idClient, summ);
    return new ClientResponseDto(client.getId(),
        client.getLastName(), client.getFirstName(), client.getPatronymic(),
        client.getEmail(), client.getAddress(), client.getPhone(), client.getDeposit());
  }

  @Override
  public ClientResponseDto getDeposit(Cookie cookie) {
    String[] cookieRequest = cookie.getValue().split("!");
    long idClient = Long.parseLong(cookieRequest[1]);
    Client client = clientRepository.getDeposit(idClient);
    return new ClientResponseDto(client.getId(),
        client.getLastName(), client.getFirstName(), client.getPatronymic(),
        client.getEmail(), client.getAddress(), client.getPhone(), client.getDeposit());
  }

  @Override
  public ErrorList purchaseProduct(ProductDto productDto, Cookie cookie) {
    String[] cookieRequest = cookie.getValue().split("!");
    long idClient = Long.parseLong(cookieRequest[1]);
    return clientRepository.purchaseProduct(productDto, idClient);
  }

  @Override
  public Object addItemInBasket(ProductDto productDto, Cookie cookie) {
    String[] cookieRequest = cookie.getValue().split("!");
    long idClient = Long.parseLong(cookieRequest[1]);
    return clientRepository.addItemInBasket(productDto, idClient);
  }

  @Override
  public void deleteItemFromBasket(long idProduct, Cookie cookie) {
    String[] cookieRequest = cookie.getValue().split("!");
    long idClient = Long.parseLong(cookieRequest[1]);
    clientRepository.deleteItemFromBasket(idProduct, idClient);
  }

  @Override
  public Object editCountItemInBasket(ProductDto productDto, Cookie cookie) {
    String[] cookieRequest = cookie.getValue().split("!");
    long idClient = Long.parseLong(cookieRequest[1]);
    return clientRepository.editCountItemInBasket(productDto, idClient);
  }

  @Override
  public Object getBasket(Cookie cookie) {
    String[] cookieRequest = cookie.getValue().split("!");
    long idClient = Long.parseLong(cookieRequest[1]);
    return clientRepository.getBasket(idClient);
  }

  @Override
  public Object purchaseFromBasket(List<ProductDto> productDtoList, Cookie cookie) {
    String[] cookieRequest = cookie.getValue().split("!");
    long idClient = Long.parseLong(cookieRequest[1]);
    return clientRepository.purchaseFromBasket(productDtoList, idClient);
  }
}
