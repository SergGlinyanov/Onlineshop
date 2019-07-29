package net.thumbtack.repo.iface;

import java.util.List;
import javafx.util.Pair;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.dto.EditClientDto;
import net.thumbtack.dto.ProductDto;
import net.thumbtack.exception.ErrorList;
import net.thumbtack.model.Client;

public interface ClientRepository {

  Object addClient(Client client);
  Object editClient(EditClientDto editClientDto, long id);
  List<ClientListDto> getAllClients();
  Object toDeposit(long idClient, int deposit);
  Client getDeposit(long idClient);
  ErrorList purchaseProduct(ProductDto productDto, long idClient);
  Object addItemInBasket(ProductDto productDto, long idClient);
  void deleteItemFromBasket(long idProduct, long idClient);
  Object editCountItemInBasket(ProductDto productDto, long idClient);
  Object getBasket(long idClient);
  Object purchaseFromBasket(List<ProductDto> productDtoList, long idClient);



}
