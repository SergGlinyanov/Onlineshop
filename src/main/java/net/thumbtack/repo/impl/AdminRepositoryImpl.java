package net.thumbtack.repo.impl;

import java.sql.PreparedStatement;
import net.thumbtack.model.Admin;
import net.thumbtack.repo.iface.AdminRepository;
import net.thumbtack.repo.mapper.AdminMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class AdminRepositoryImpl implements AdminRepository {

  private JdbcTemplate jdbcTemplate;
  private AdminMapper adminMapper;

  public AdminRepositoryImpl(JdbcTemplate jdbcTemplate, AdminMapper adminMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.adminMapper = adminMapper;
  }

  @Override
  public Long addAdmin(Admin admin) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO admins (lastName,firstName,patronymic,position,"
        + "login,password) values (?,?,?,?,?,?)";
    jdbcTemplate.update(
        con -> {
          PreparedStatement pst =
              con.prepareStatement(sql,new String[]{"id"});
          pst.setString(1, admin.getLastName());
          pst.setString(2, admin.getFirstName());
          pst.setString(3, admin.getPatronymic());
          pst.setString(4, admin.getPosition());
          pst.setString(5, admin.getLogin());
          pst.setString(6, admin.getPassword());
          return pst;
        },
        keyHolder);
    return (Long)keyHolder.getKey();
  }

  @Override
  public void editAdmin(Admin admin) {
    jdbcTemplate.update("UPDATE admins SET lastName = ?,"
            + " firstName = ?, patronymic = ?, position =?, password=? WHERE id = ?",
        admin.getLastName(),
        admin.getFirstName(),
        admin.getPatronymic(),
        admin.getPosition(),
        admin.getPassword(),
        admin.getId());
  }
}
