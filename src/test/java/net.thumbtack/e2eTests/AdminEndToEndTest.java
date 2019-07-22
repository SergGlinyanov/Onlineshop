package net.thumbtack.e2eTests;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.dto.EditAdminDto;
import net.thumbtack.model.Admin;
import net.thumbtack.repo.iface.AdminRepository;
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
public class AdminEndToEndTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private AdminRepository adminRepository;

  @Test
  public void AdminWorksThroughAllLayers() throws Exception {

    //addAdmin E2E test
    Admin admin = new Admin((long)2,"Иванов", "Иван",
        "Иванович", "ivanov", "qwerty", "admin");

    mockMvc.perform(post("/api/admins", 42L)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(admin)))
            .andExpect(status().isOk());

    Long idAddedAdmin  = adminRepository.addAdmin(admin);
    assertEquals((long)idAddedAdmin, admin.getId());

    //editAdmin E2E test
    EditAdminDto editAdminDto = new EditAdminDto((long)2,"Иванов", "Иван",
        "Иванович", "ivanov", "qwerty", "admin");

    mockMvc.perform(put("/api/admins/2", 42L)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(editAdminDto)))
        .andExpect(status().isOk());

    Admin updatedAdmin = adminRepository.getAdminById(2);
    assertEquals(editAdminDto.getNewPassword(), updatedAdmin.getPassword());
  }
}