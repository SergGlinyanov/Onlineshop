package net.thumbtack.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import net.thumbtack.model.Admin;
import net.thumbtack.service.iface.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AdminControllerTest {

  private AdminController underTest;

  @Mock
  private AdminService adminService;

  @Captor
  private ArgumentCaptor<Admin> captor;

  @Before
  public void setUpClass() {
    MockitoAnnotations.initMocks(this);
    underTest = new AdminController(this.adminService);
  }

  @Test
  public void testAddAdmin() {
    Admin admin = new Admin((long) 1, "Ivanov", "Ivan",
        "Ivanovich", "admin", "qwerty", "manager");
    underTest.addAdmin(admin);
    verify(adminService).addAdmin(captor.capture());

    Admin value = captor.getValue();
    assertEquals(admin, value);
  }

}
