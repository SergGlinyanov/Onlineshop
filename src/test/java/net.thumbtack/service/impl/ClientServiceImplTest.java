package net.thumbtack.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.dto.ClientRegistrationDto;
import net.thumbtack.model.Client;
import net.thumbtack.repo.iface.ClientRepository;
import net.thumbtack.service.iface.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClientServiceImplTest {

  private ClientService underTest;

  @Captor
  private ArgumentCaptor<Client> captor;

  @Mock
  private ClientRepository clientRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    underTest = new ClientServiceImpl(clientRepository);
  }

  @Test
  public void testAddClient() {
    ClientRegistrationDto client = new ClientRegistrationDto((long) 1, "Ivanov", "Ivan",
        "Ivanovich", "asd@mail.ru", "Saratov", "89998883555",
        "client", "qwerty");
    underTest.addClient(client);
    verify(clientRepository).addClient(captor.capture());

    Client value = captor.getValue();
    assertEquals(client.getLogin(), value.getLogin());
  }

  @Test
  public void testGetAllCategoryAndDeleteCategory() {
    List<ClientListDto> clientList = Arrays.asList(
        new ClientListDto((long) 1, "Ivanov", "Ivan",
            "Ivanovich", "asd@mail.ru", "Saratov", "89998883555",
            "client"),
        new ClientListDto((long) 1, "Ivanov", "Ivan",
            "Ivanovich", "asd@mail.ru", "Saratov", "89998883555",
            "client")
    );
    when(clientRepository.getAllClients()).thenReturn(clientList);
    List<ClientListDto> categoryList = underTest.getAllClients();
    assertThat(categoryList, hasSize(clientList.size()));
  }

}
