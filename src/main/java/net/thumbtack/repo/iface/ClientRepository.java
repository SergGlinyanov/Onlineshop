package net.thumbtack.repo.iface;

import java.util.List;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.model.Client;

public interface ClientRepository {

  void addClient(Client client);
  List<ClientListDto> getAllClients();



}
