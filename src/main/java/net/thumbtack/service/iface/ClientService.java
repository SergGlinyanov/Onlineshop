package net.thumbtack.service.iface;

import java.util.List;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.dto.ClientRegistrationDto;

public interface ClientService {

  void addClient(ClientRegistrationDto clientRegistrationDto);
  List<ClientListDto> getAllClients();

}
