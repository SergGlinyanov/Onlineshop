package net.thumbtack.repository;

import static org.junit.Assert.*;

import net.thumbtack.dto.AdminResponseDto;
import net.thumbtack.model.Admin;
import net.thumbtack.repo.iface.AdminRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AdminRepositoryImplTest {

  @Autowired
  TestEntityManager entityManager;

  @Autowired
  AdminRepository adminRepository;

  @Ignore
  @Test
  public void addAdmin() {
    Admin admin = (Admin) this.entityManager.persistAndGetId(new Admin((long) 1, "Иванов",
        "Иван", "Иванович", "admin", "qwerty", "manager"));
    AdminResponseDto adminResponseDto = (AdminResponseDto) adminRepository.addAdmin(admin);
    assertEquals(admin.getLastName(), adminResponseDto.getLastName());
//    Admin admin = new Admin();
//    admin.setLogin("admin");
//    admin = (Admin) entityManager.persistAndGetId(admin);
//    assertEquals(adminRepository.getAdminById(admin.getId()),admin);
  }

  @Test
  public void editAdmin() {
  }

  @Test
  public void getAdminById() {
  }
}