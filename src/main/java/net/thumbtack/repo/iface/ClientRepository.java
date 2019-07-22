package net.thumbtack.repo.iface;

import java.util.List;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.dto.ProductDto;
import net.thumbtack.exception.ErrorList;
import net.thumbtack.model.Client;

public interface ClientRepository {

  Long addClient(Client client);
  List<ClientListDto> getAllClients();
  Client toDeposit(long idClient, int deposit);
  Client getDeposit(long idClient);
  ErrorList purchaseProduct(ProductDto productDto, long idClient);
  Object addItemInBasket(ProductDto productDto, long idClient);
  void deleteItemFromBasket(long idProduct, long idClient);
  Object editCountItemInBasket(ProductDto productDto, long idClient);
  Object getBasket(long idClient);
  Object purchaseFromBasket(List<ProductDto> productDtoList, long idClient);



}
