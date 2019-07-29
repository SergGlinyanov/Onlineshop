package net.thumbtack.e2eTests;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.dto.ClientRegistrationDto;
import net.thumbtack.dto.EditAdminDto;
import net.thumbtack.dto.EditClientDto;
import net.thumbtack.model.Admin;
import net.thumbtack.model.Client;
import net.thumbtack.repo.iface.AdminRepository;
import net.thumbtack.repo.iface.ClientRepository;
import net.thumbtack.service.iface.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientEndToEndTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ClientRepository clientRepository;

  @Test
  public void testAddClient() throws Exception {
    Client client = new Client((long) 1, "Иванов", "Иван",
        "Иванович", "asd@mail.ru", "Saratov", "89998883555",
        "client", "qwerty");

    mockMvc.perform(post("/api/clients", 42L)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(client)))
        .andExpect(status().isOk());

//    Long idAddedClient  = clientRepository.addClient(client);
//    assertEquals(idAddedClient-1, client.getId());
  }

  @Test
  public void testEditClient() throws Exception {
    Client client = new Client((long) 1, "Иванов", "Иван",
        "Иванович", "asd@mail.ru", "Saratov", "89998883555",
        "client", "qwerty");
    mockMvc.perform(post("/api/clients", 42L)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(client)))
        .andExpect(status().isOk());

    EditClientDto editClientDto = new EditClientDto((long) 1,
        "Иванов", "Иван", "Иванович", "asd@mail.ru",
        "Saratov", "89998883555", "qwerty", "qwerty123");

    mockMvc.perform(put("/api/clients/1", 42L)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(editClientDto)))
        .andExpect(status().isOk());

    List<ClientListDto> updatedClient = clientRepository.getAllClients();
    assertEquals(editClientDto.getLastName(), updatedClient.get(0).getLastName());
  }
}
