package net.thumbtack.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import net.thumbtack.dto.AdminResponseDto;
import net.thumbtack.dto.EditAdminDto;
import net.thumbtack.exception.ErrorList;
import net.thumbtack.model.Admin;
import net.thumbtack.repo.iface.AdminRepository;
import net.thumbtack.service.iface.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceImplTest {

  @Autowired
  private AdminService adminService;

  @MockBean
  private AdminRepository adminRepository;

  @Test
  public void testAddAdminWithErrors() {
    Admin admin = new Admin((long) 1, "Ivanov", "Ivan",
        "Ivanovich", "admin", "qwerty", "manager");
    when(adminRepository.addAdmin(admin)).thenReturn
        ((long)1);
    ErrorList response = (ErrorList) adminService.addAdmin(admin);
    assertNotNull(response);
  }

  @Test
  public void testAddAdminWithoutErrors() {
    Admin admin = new Admin((long) 1, "Иванов", "Иван",
        "Иванович", "admin", "qwerty", "manager");
    when(adminRepository.addAdmin(admin)).thenReturn
        ((long)1);
    AdminResponseDto response = (AdminResponseDto) adminService.addAdmin(admin);
    assertNotNull(response);
  }

  @Test
  public void testEditAdmin() {
    EditAdminDto admin = new EditAdminDto((long) 1, "Ivanov", "Ivan",
        "Ivanovich", "admin", "qwerty", "manager");
    when(adminRepository.editAdmin(admin, 1)).thenReturn
        (new ErrorList(new ArrayList<>()));
    ErrorList response = (ErrorList) adminService.editAdmin(admin, 1);
    assertNotNull(response);

    when(adminRepository.editAdmin(admin, 1)).thenReturn
        (new AdminResponseDto());
    AdminResponseDto response1 = (AdminResponseDto) adminService.editAdmin(admin, 1);
    assertNotNull(response1);
  }
}