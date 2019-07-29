package net.thumbtack.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;
import javax.servlet.http.Cookie;
import net.thumbtack.dto.ClientRegistrationDto;
import net.thumbtack.dto.ClientResponseDto;
import net.thumbtack.dto.EditClientDto;
import net.thumbtack.dto.ProductDto;
import net.thumbtack.exception.ErrorList;
import net.thumbtack.model.Client;
import net.thumbtack.repo.iface.ClientRepository;
import net.thumbtack.service.iface.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceImplTest {

  @Autowired
  private ClientService clientService;

  @MockBean
  private ClientRepository clientRepository;

  @Test
  public void testAddClientWithoutErrors() {
    ClientRegistrationDto clientRegistrationDto = new ClientRegistrationDto((long) 1,
        "Иванов", "Иван", "Иванович", "asd@mail.ru",
        "Saratov", "89998883555", "client", "qwerty");
    ClientResponseDto clientResponseDto =
        (ClientResponseDto) clientService.addClient(clientRegistrationDto);
    assertEquals(clientRegistrationDto.getLastName(), clientResponseDto.getLastName());
  }

  @Test
  public void testAddClientWithErrors() {
    ClientRegistrationDto clientRegistrationDto = new ClientRegistrationDto((long) 1,
        "Ivanov", "Ivan", "Ivanovich", "asd@mail.ru",
        "Saratov", "89998883555", "client", "qwerty");
    ErrorList errorList =
        (ErrorList) clientService.addClient(clientRegistrationDto);
    assertEquals(1, errorList.getErrors().size());
  }

  @Test
  public void testEditClient() {
    EditClientDto editClientDto = new EditClientDto((long) 1,
        "Иванов", "Иван", "Иванович", "asd@mail.ru",
        "Saratov", "89998883555", "client", "qwerty");
    //При наличии неверно введенных данных метод возвращает список ошибок
    when(clientRepository.editClient(editClientDto,1)).thenReturn
        (new ErrorList(new ArrayList<>()));
    ErrorList response = (ErrorList) clientService.editClient(editClientDto, 1);
    assertNotNull(response);
    //Иначе возвращает экземпляр класса ClientResponseDto
    when(clientRepository.editClient(editClientDto,1)).thenReturn
        (new ClientResponseDto());
    ClientResponseDto response1 = (ClientResponseDto) clientService.editClient(editClientDto, 1);
    assertNotNull(response1);
  }

  @Test
  public void testGetAllClients() {
    clientService.getAllClients();
  }


  @Test
  public void testToDeposit() {
    Client client = new Client();
    client.setDeposit(1000);
    Cookie cookie = new Cookie("role_id", "admin!1");
    when(clientRepository.toDeposit(1, 1000)).thenReturn
        (client);
    //ClientResponseDto clientResponseDto = clientService.toDeposit("1000", cookie);
    //assertEquals(1000, clientResponseDto.getDeposit());
  }

  @Test
  public void testGetDeposit() {
    Client client = new Client();
    client.setId(1);
    client.setDeposit(1000);
    Cookie cookie = new Cookie("role_id", "admin!1");
    when(clientRepository.getDeposit(1)).thenReturn
        (client);
    ClientResponseDto clientResponseDto = clientService.getDeposit(cookie);
    assertEquals(1000, clientResponseDto.getDeposit());
  }

  @Test
  public void testPurchaseProduct() {
    Cookie cookie = new Cookie("role_id", "admin!1");
    ProductDto productDto = new ProductDto((long)1, "Трусы", 100, 100);
    when(clientRepository.purchaseProduct(productDto,1)).thenReturn
        (new ErrorList(new ArrayList<>()));
    ErrorList errorList = clientService.purchaseProduct(productDto, cookie);
    assertNotNull(errorList);
  }

  @Test
  public void testAddItemInBasket() {

    Cookie cookie = new Cookie("role_id", "admin!1");
    ProductDto productDto = new ProductDto((long)1, "Трусы", 100, 100);
    //При наличии неверно введенных данных метод возвращает список ошибок
    when(clientRepository.addItemInBasket(productDto,1)).thenReturn
        (new ErrorList(new ArrayList<>()));
    ErrorList response = (ErrorList) clientService.addItemInBasket(productDto, cookie);
    assertNotNull(response);
    //Иначе возвращает экземпляр класса ProductDto
    when(clientRepository.addItemInBasket(productDto,1)).thenReturn
        (new ProductDto());
    ProductDto response1 = (ProductDto) clientService.addItemInBasket(productDto, cookie);
    assertNotNull(response1);

  }

  @Test
  public void testDeleteItemFromBasket() {
    Cookie cookie = new Cookie("role_id", "admin!1");
    clientService.deleteItemFromBasket(1, cookie);
  }

  @Test
  public void testEditCountItemInBasket() {
    Cookie cookie = new Cookie("role_id", "admin!1");
    ProductDto productDto = new ProductDto((long)1, "Трусы", 100, 100);
    //При наличии неверно введенных данных метод возвращает список ошибок
    when(clientRepository.editCountItemInBasket(productDto,1)).thenReturn
        (new ErrorList(new ArrayList<>()));
    ErrorList response = (ErrorList) clientService.editCountItemInBasket(productDto, cookie);
    assertNotNull(response);
    //Иначе возвращает экземпляр класса ProductDto
    when(clientRepository.editCountItemInBasket(productDto,1)).thenReturn
        (new ProductDto());
    ProductDto response1 = (ProductDto) clientService.editCountItemInBasket(productDto, cookie);
    assertNotNull(response1);

  }

  @Test
  public void testGetBasket() {
    Cookie cookie = new Cookie("role_id", "admin!1");
    //При наличии неверно введенных данных метод возвращает список ошибок
    when(clientRepository.getBasket(1)).thenReturn
        (new ErrorList(new ArrayList<>()));
    ErrorList response = (ErrorList) clientService.getBasket(cookie);
    assertNotNull(response);
    //Иначе возвращает экземпляр класса ProductDto
    when(clientRepository.getBasket(1)).thenReturn
        (new ProductDto());
    ProductDto response1 = (ProductDto) clientService.getBasket(cookie);
    assertNotNull(response1);
  }

  @Test
  public void testPurchaseFromBasket() {
    List<ProductDto> products = Arrays.asList(
        new ProductDto((long)1, "Трусы", 100, 100),
        new ProductDto((long)1, "Трусы", 100, 100)
    );
    Cookie cookie = new Cookie("role_id", "admin!1");
    //При наличии неверно введенных данных метод возвращает список ошибок
    when(clientRepository.purchaseFromBasket(products,1)).thenReturn
        (new ErrorList(new ArrayList<>()));
    ErrorList response = (ErrorList) clientService.purchaseFromBasket(products, cookie);
    assertNotNull(response);
    //Иначе возвращает экземпляр класса ProductDto
    when(clientRepository.purchaseFromBasket(products, 1)).thenReturn
        (new ProductDto());
    ProductDto response1 = (ProductDto) clientService.purchaseFromBasket(products, cookie);
    assertNotNull(response1);
  }
}