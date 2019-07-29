package net.thumbtack.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.dto.ClientRegistrationDto;
import net.thumbtack.dto.ClientResponseDto;
import net.thumbtack.dto.EditClientDto;
import net.thumbtack.dto.ProductDto;
import net.thumbtack.exception.ErrorList;
import net.thumbtack.exception.MyError;
import net.thumbtack.model.Client;
import net.thumbtack.repo.iface.ClientRepository;
import net.thumbtack.service.iface.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }


  @Override
  public Object addClient(ClientRegistrationDto clientRegistrationDto) {
    Client client = new Client(clientRegistrationDto);
    Object responseFromDataBase = clientRepository.addClient(client);
    Object response = null;
    if (responseFromDataBase instanceof Long) {
      client.setId((long)responseFromDataBase);
      response = new ClientResponseDto(client);
    }
    if (responseFromDataBase instanceof ErrorList) {
      response = responseFromDataBase;
    }
    return response;
  }

  @Override
  public Object editClient(EditClientDto editClientDto, long id) {
    return clientRepository.editClient(editClientDto, id);
  }

  @Override
  public List<ClientListDto> getAllClients() {
    return clientRepository.getAllClients();
  }

  @Override
  public Object toDeposit(String deposit, Cookie cookie) {
    List<MyError> errors = new ArrayList<>();
    String[] cookieRequest = cookie.getValue().split("!");
    if (cookieRequest.length<2){
      errors.add(new MyError("INVALID_ACCOUNT",
          "", "Зарегистрируйтесь на сервере."));
      return new ErrorList(errors);
    } else {
      long idClient = Long.parseLong(cookieRequest[1]);
      int summ = Integer.parseInt(deposit.replaceAll("[^\\d]", ""));
      Object response = clientRepository.toDeposit(idClient, summ);
      if (response instanceof Client) {
        return new ClientResponseDto((Client) response);
      } else {
        return response;
      }
    }
  }

  @Override
  public ClientResponseDto getDeposit(Cookie cookie) {
    String[] cookieRequest = cookie.getValue().split("!");
    long idClient = Long.parseLong(cookieRequest[1]);
    Client client = clientRepository.getDeposit(idClient);
    return new ClientResponseDto(client);
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
