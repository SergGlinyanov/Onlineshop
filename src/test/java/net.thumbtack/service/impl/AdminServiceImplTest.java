package net.thumbtack.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import net.thumbtack.model.Admin;
import net.thumbtack.repo.iface.AdminRepository;
import net.thumbtack.service.iface.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AdminServiceImplTest {

  private AdminService underTest;

  @Captor
  private ArgumentCaptor<Admin> captor;

  @Mock
  private AdminRepository adminRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    underTest = new AdminServiceImpl(adminRepository);
  }

  @Test
  public void testAddAdmin() {
    Admin admin = new Admin((long) 1, "Ivanov", "Ivan",
        "Ivanovich", "admin", "qwerty", "manager");
    underTest.addAdmin(admin);
    verify(adminRepository).addAdmin(captor.capture());

    Admin value = captor.getValue();
    assertEquals(admin, value);
  }

}
