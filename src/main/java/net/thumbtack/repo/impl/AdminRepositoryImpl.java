package net.thumbtack.repo.impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.thumbtack.dto.AdminResponseDto;
import net.thumbtack.dto.CategoryPurchasesDto;
import net.thumbtack.dto.ClientPurchasesDto;
import net.thumbtack.dto.EditAdminDto;
import net.thumbtack.dto.ProductDto;
import net.thumbtack.dto.ProductPurchasesDto;
import net.thumbtack.exception.ErrorList;
import net.thumbtack.exception.MyError;
import net.thumbtack.model.Admin;
import net.thumbtack.repo.iface.AdminRepository;
import net.thumbtack.repo.mapper.AdminMapper;
import net.thumbtack.repo.mapper.CategoryIdMapper;
import net.thumbtack.repo.mapper.ClientPurchasesMapper;
import net.thumbtack.repo.mapper.ProductPurchasesMapper;
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
  public Object addAdmin(Admin admin) {
    int check = 0;
    List<MyError> errors = new ArrayList<>();
    List<String> logins = new ArrayList<>();
    List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT login FROM admins");
    rows.forEach(row -> {
      String login = (String) row.get("login");
      logins.add(login);
    });
    if (logins.contains(admin.getLogin())) {
      errors.add(new MyError("LOGIN_ALREADY_EXISTS",
          "login", "Пользователь с таким логином уже есть."));
      check++;
    }
    if (!admin.getLastName().matches("^[а-яА-ЯёЁ]+$")
        || !admin.getFirstName().matches("^[а-яА-ЯёЁ]+$")
        || !admin.getPatronymic().matches("^[а-яА-ЯёЁ]+$")) {
      errors.add(new MyError("WRONG_FORMAT",
          "Ф.И.О.", "Только кириллица."));
      check++;
    }
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO admins (lastName,firstName,patronymic,position,"
        + "login,password) values (?,?,?,?,?,?)";
    jdbcTemplate.update(
        con -> {
          PreparedStatement pst =
              con.prepareStatement(sql, new String[]{"id"});
          pst.setString(1, admin.getLastName());
          pst.setString(2, admin.getFirstName());
          pst.setString(3, admin.getPatronymic());
          pst.setString(4, admin.getPosition());
          pst.setString(5, admin.getLogin());
          pst.setString(6, admin.getPassword());
          return pst;
        },
        keyHolder);
    Long id = (Long) keyHolder.getKey();
    if (check == 0) {
      return id;
    } else {
      return new ErrorList(errors);
    }
  }

  @Override
  public Object editAdmin(EditAdminDto editAdminDto, long id) {
    int check = 0;
    List<MyError> errors = new ArrayList<>();
    String oldPassword = jdbcTemplate.queryForObject
        ("SELECT password FROM admins WHERE id = " + id, String.class);
    if (!editAdminDto.getOldPassword().equals(oldPassword)) {
      errors.add(new MyError("WRONG_PASSWORD",
          "oldPassword", "Неверный пароль."));
      check++;
    }
    if (!editAdminDto.getLastName().matches("^[а-яА-ЯёЁ]+$")
        || !editAdminDto.getFirstName().matches("^[а-яА-ЯёЁ]+$")
        || !editAdminDto.getPatronymic().matches("^[а-яА-ЯёЁ]+$")) {
      errors.add(new MyError("WRONG_FORMAT",
          "Ф.И.О.", "Только кириллица."));
      check++;
    }
    if (check == 0) {
      jdbcTemplate.update("UPDATE admins SET lastName = ?,"
              + " firstName = ?, patronymic = ?, position =?, password=? WHERE id = ?",
          editAdminDto.getLastName(),
          editAdminDto.getFirstName(),
          editAdminDto.getPatronymic(),
          editAdminDto.getPosition(),
          editAdminDto.getNewPassword(),
          id);
      return new AdminResponseDto(id, editAdminDto.getLastName(), editAdminDto.getFirstName(),
          editAdminDto.getPatronymic(), editAdminDto.getPosition());
    } else {
      return new ErrorList(errors);
    }
  }

  @Override
  public Admin getAdminById(long id) {
    String sql = "SELECT * FROM admins WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{id}, adminMapper);
  }

  @Override
  public Object clientPurchases(long id) {
    Object response;
    List<MyError> errors = new ArrayList<>();
    List<Long> idClients = new ArrayList<>();
    List<Map<String, Object>> rowsClient = jdbcTemplate.queryForList("SELECT id FROM clients");
    rowsClient.forEach(row -> {
      long idClient = (long) row.get("id");
      idClients.add(idClient);
    });
    if (!idClients.contains(id)) {
      errors.add(new MyError("WRONG_ID",
          "id", "Нет пользователя с ID = " + id));
      response = new ErrorList(errors);
    } else {
      ClientPurchasesDto clientPurchasesDto = jdbcTemplate.queryForObject
          ("SELECT * FROM clients WHERE id =" + id, new ClientPurchasesMapper());

      List<ProductDto> result = new ArrayList<>();
      List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM purchases");
      rows.forEach(row -> {
        ProductDto productDto = new ProductDto();
        productDto.setId((long) row.get("id_product"));
        productDto.setName((String) row.get("name"));
        productDto.setPrice(((int) row.get("price")));
        productDto.setCount(((int) row.get("count")));
        result.add(productDto);
      });
      clientPurchasesDto.setPurchaseProducts(result);
      int total = 0;
      for (ProductDto productDto : result) {
        total = total + (productDto.getCount() * productDto.getPrice());
      }
      clientPurchasesDto.setTotal(total);
      response = clientPurchasesDto;
    }
    return response;
  }

  @Override
  public Object categoryPurchases(long id) {
    Object response;
    CategoryPurchasesDto categoryPurchasesDto = new CategoryPurchasesDto();
    List<MyError> errors = new ArrayList<>();
    String nameCategory = jdbcTemplate.queryForObject
        ("SELECT nameCategory FROM categories WHERE id = " + id, String.class);
    categoryPurchasesDto.setName(nameCategory);
    List<Long> idCategories = new ArrayList<>();
    List<Map<String, Object>> rowsClient = jdbcTemplate.queryForList("SELECT id FROM categories");
    rowsClient.forEach(row -> {
      long idClient = (long) row.get("id");
      idCategories.add(idClient);
    });
    if (!idCategories.contains(id)) {
      errors.add(new MyError("WRONG_ID",
          "id", "В истории покупок нет категории с ID = " + id));
      response = new ErrorList(errors);
    } else {

      List<Long> idProducts = new ArrayList<>();
      List<Map<String, Object>> rowsIdProduct = jdbcTemplate.queryForList("SELECT * FROM purchases");
      rowsIdProduct.forEach(row -> {
        long idProduct = (long) row.get("id_product");
        idProducts.add(idProduct);
      });

      List<Long> idProductWithFindCategory = new ArrayList<>();
      for (Long idProduct:idProducts){
        String SQL = "SELECT id_category FROM products_categories WHERE id_product = "
            + idProduct;
        List<Long> categories = jdbcTemplate.query(SQL, new CategoryIdMapper());
        if(categories.contains(id)){
          idProductWithFindCategory.add(idProduct);
        }
      }
      List<ProductDto> productOfFindCategory = new ArrayList<>();
      for (Long idProduct: idProductWithFindCategory) {
        List<Map<String, Object>> rowsProduct = jdbcTemplate.queryForList
            ("SELECT * FROM purchases WHERE id_product = " + idProduct);
        rowsProduct.forEach(row -> {
          ProductDto productDto = new ProductDto();
          productDto.setId((long) row.get("id_product"));
          productDto.setName((String) row.get("name"));
          productDto.setPrice(((int) row.get("price")));
          productDto.setCount(((int) row.get("count")));
          productOfFindCategory.add(productDto);
        });
      }
      categoryPurchasesDto.setPurchaseProducts(productOfFindCategory);

      int total = 0;
      for (ProductDto productDto : productOfFindCategory) {
        total = total + (productDto.getCount() * productDto.getPrice());
      }
      categoryPurchasesDto.setTotal(total);
      response = categoryPurchasesDto;
    }
    return response;
  }

  @Override
  public Object productPurchases(long id) {
    Object response;
    List<MyError> errors = new ArrayList<>();
    List<Long> idProducts = new ArrayList<>();

    List<Map<String, Object>> rowsIdProduct = jdbcTemplate.queryForList("SELECT * FROM purchases");
    rowsIdProduct.forEach(row -> {
      long idProduct = (long) row.get("id_product");
      idProducts.add(idProduct);
    });
    if (!idProducts.contains(id)) {
      errors.add(new MyError("WRONG_ID",
          "id", "В истории покупок нет товара с ID = " + id));
      response = new ErrorList(errors);
    } else {
      ProductPurchasesDto productPurchasesDto = jdbcTemplate.queryForObject
          ("SELECT name, price, count FROM purchases WHERE id_product =" + id,
              new ProductPurchasesMapper());
      int total = productPurchasesDto.getCount()*productPurchasesDto.getPrice();
      productPurchasesDto.setTotal(total);
      response = productPurchasesDto;
    }
    return response;
  }
}
