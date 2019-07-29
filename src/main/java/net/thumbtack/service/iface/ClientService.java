package net.thumbtack.service.iface;

import java.util.List;
import javax.servlet.http.Cookie;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.dto.ClientRegistrationDto;
import net.thumbtack.dto.ClientResponseDto;
import net.thumbtack.dto.EditClientDto;
import net.thumbtack.dto.ProductDto;
import net.thumbtack.exception.ErrorList;
import org.springframework.web.servlet.ModelAndView;

public interface ClientService {

  Object addClient(ClientRegistrationDto clientRegistrationDto);
  Object editClient(EditClientDto editClientDto, long id);
  List<ClientListDto> getAllClients();
  Object toDeposit(String deposit, Cookie cookie);
  ClientResponseDto getDeposit(Cookie cookie);
  ErrorList purchaseProduct(ProductDto productDto, Cookie cookie);
  Object addItemInBasket(ProductDto productDto, Cookie cookie);
  void deleteItemFromBasket(long idProduct, Cookie cookie);
  Object editCountItemInBasket(ProductDto productDto, Cookie cookie);
  Object getBasket(Cookie cookie);
  Object purchaseFromBasket(List<ProductDto> productDtoList, Cookie cookie);


}
