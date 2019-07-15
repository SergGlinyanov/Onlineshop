package net.thumbtack.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.dto.ClientRegistrationDto;
import net.thumbtack.service.iface.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClientControllerTest {

  private ClientController underTest;

  @Mock
  private ClientService clientService;

  @Captor
  private ArgumentCaptor<ClientRegistrationDto> captor;

  @Before
  public void setUpClass() {
    MockitoAnnotations.initMocks(this);
    underTest = new ClientController(this.clientService);
  }

  @Test
  public void testAddClient() {
    ClientRegistrationDto client = new ClientRegistrationDto((long) 1, "Ivanov", "Ivan",
        "Ivanovich", "asd@mail.ru", "Saratov", "89998883555",
        "client", "qwerty");
    underTest.addClient(client);
    verify(clientService).addClient(captor.capture());

    ClientRegistrationDto value = captor.getValue();
    assertEquals(client, value);
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
    when(clientService.getAllClients()).thenReturn(clientList);
    List<ClientListDto> categoryList = underTest.getAllClients().getBody();
    assertThat(categoryList, hasSize(clientList.size()));
  }

}
