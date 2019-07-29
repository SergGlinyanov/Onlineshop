package net.thumbtack.repo.impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.thumbtack.dto.ClientListDto;
import net.thumbtack.dto.ClientResponseDto;
import net.thumbtack.dto.EditClientDto;
import net.thumbtack.dto.ProductDto;
import net.thumbtack.dto.PurchaseFromBasketDto;
import net.thumbtack.exception.ErrorList;
import net.thumbtack.exception.MyError;
import net.thumbtack.model.Client;
import net.thumbtack.repo.iface.ClientRepository;
import net.thumbtack.repo.mapper.BasketMapper;
import net.thumbtack.repo.mapper.ClientMapper;
import net.thumbtack.repo.mapper.IdMapper;
import net.thumbtack.repo.mapper.ProductInBasketMapper;
import net.thumbtack.repo.mapper.PurchaseProductDtoMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ClientRepositoryImpl implements ClientRepository {

  private JdbcTemplate jdbcTemplate;
  private ClientMapper clientMapper;
  private PurchaseProductDtoMapper purchaseProductDtoMapper;

  public ClientRepositoryImpl(JdbcTemplate jdbcTemplate, ClientMapper clientMapper,
      PurchaseProductDtoMapper purchaseProductDtoMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.clientMapper = clientMapper;
    this.purchaseProductDtoMapper = purchaseProductDtoMapper;
  }

  @Override
  public Object addClient(Client client) {
    int check = 0;
    List<MyError> errors = new ArrayList<>();
    List<String> logins = new ArrayList<>();
    List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT login FROM clients");
    rows.forEach(row -> {
      String login = (String) row.get("login");
      logins.add(login);
    });
    if (logins.contains(client.getLogin())) {
      errors.add(new MyError("LOGIN_ALREADY_EXISTS",
          "login", "Пользователь с таким логином уже есть."));
      check++;
    }
    if (!client.getLastName().matches("^[а-яА-ЯёЁ]+$")
        || !client.getFirstName().matches("^[а-яА-ЯёЁ]+$")
        || !client.getPatronymic().matches("^[а-яА-ЯёЁ]+$")) {
      errors.add(new MyError("WRONG_FORMAT",
          "Ф.И.О.", "Только кириллица."));
      check++;
    }
    if (check == 0) {
      KeyHolder keyHolder = new GeneratedKeyHolder();
      String sql = "INSERT INTO clients (lastName,firstName,patronymic,email,"
          + "postalAddress,phoneNumber,login,password) values (?,?,?,?,?,?,?,?)";
      jdbcTemplate.update(
          con -> {
            PreparedStatement pst =
                con.prepareStatement(sql, new String[]{"id"});
            pst.setString(1, client.getLastName());
            pst.setString(2, client.getFirstName());
            pst.setString(3, client.getPatronymic());
            pst.setString(4, client.getEmail());
            pst.setString(5, client.getAddress());
            pst.setString(6, client.getPhone());
            pst.setString(7, client.getLogin());
            pst.setString(8, client.getPassword());
            return pst;
          },
          keyHolder);
      Long id = (Long) keyHolder.getKey();
      jdbcTemplate.update("INSERT INTO deposits (id_client,summ) values (?,?)", id, 0);
      return id;
    } else {
      return new ErrorList(errors);
    }
  }

  @Override
  public Object editClient(EditClientDto editClientDto, long id) {
    int check = 0;
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
      return new ErrorList(errors);
    } else {
      Integer deposit = jdbcTemplate.queryForObject
          ("SELECT summ FROM deposits WHERE id_client = " + id, Integer.class);
      String oldPassword = jdbcTemplate.queryForObject
          ("SELECT password FROM clients WHERE id = " + id, String.class);
      if (!editClientDto.getOldPassword().equals(oldPassword)) {
        errors.add(new MyError("WRONG_PASSWORD",
            "oldPassword", "Неверный пароль."));
        check++;
      }

      if (!editClientDto.getLastName().matches("^[а-яА-ЯёЁ]+$")
          || !editClientDto.getFirstName().matches("^[а-яА-ЯёЁ]+$")
          || !editClientDto.getPatronymic().matches("^[а-яА-ЯёЁ]+$")) {
        errors.add(new MyError("WRONG_FORMAT",
            "Ф.И.О.", "Только кириллица."));
        check++;
      }
      if (check == 0) {
        jdbcTemplate.update("UPDATE clients SET lastName = ?,"
                + " firstName = ?, patronymic = ?, email =?, "
                + "postalAddress=?, phoneNumber=?, password =? WHERE id = ?",
            editClientDto.getLastName(),
            editClientDto.getFirstName(),
            editClientDto.getPatronymic(),
            editClientDto.getEmail(),
            editClientDto.getAddress(),
            editClientDto.getPhone(),
            editClientDto.getNewPassword(),
            id);
        return new ClientResponseDto(id, editClientDto.getLastName(), editClientDto.getFirstName(),
            editClientDto.getPatronymic(), editClientDto.getEmail(), editClientDto.getAddress(),
            editClientDto.getPhone(), deposit);
      } else {
        return new ErrorList(errors);
      }
    }
  }

  @Override
  public List<ClientListDto> getAllClients() {
    List<ClientListDto> result = new ArrayList<>();

    List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM clients");
    rows.forEach(row -> {
      ClientListDto clientListDto = new ClientListDto();
      clientListDto.setId((long) row.get("id"));
      clientListDto.setFirstName((String) row.get("firstName"));
      clientListDto.setLastName((String) row.get("lastName"));
      clientListDto.setPatronymic((String) row.get("patronymic"));
      clientListDto.setEmail((String) row.get("email"));
      clientListDto.setAddress((String) row.get("postalAddress"));
      clientListDto.setPhone((String) row.get("phoneNumber"));
      clientListDto.setUserType("client");
      result.add(clientListDto);
    });
    return result;
  }

  @Override
  public Object toDeposit(long idClient, int deposit) {
    List<MyError> errors = new ArrayList<>();
    List<Long> idClients = new ArrayList<>();
    List<Map<String, Object>> rowsClient = jdbcTemplate.queryForList("SELECT id FROM clients");
    rowsClient.forEach(row -> {
      long idClientFromDataBase = (long) row.get("id");
      idClients.add(idClientFromDataBase);
    });
    if (!idClients.contains(idClient)) {
      errors.add(new MyError("INVALID_ACCOUNT",
          "", "Зарегистрируйтесь на сервере."));
      return new ErrorList(errors);
    } else {
      String sql = "SELECT * FROM clients WHERE id = ?";
      int oldSumm = jdbcTemplate.queryForObject("SELECT summ FROM deposits WHERE id_client = ?",
          new Object[]{idClient}, Integer.class);
      int newSumm = oldSumm + deposit;
      jdbcTemplate.update("UPDATE deposits SET summ = ? WHERE id_client = ?",
          newSumm, idClient);
      Client client = jdbcTemplate.queryForObject(sql, new Object[]{idClient}, clientMapper);
      client.setDeposit(jdbcTemplate.queryForObject
          ("SELECT summ FROM deposits WHERE id_client = ?",
              new Object[]{idClient}, Integer.class));
      return client;
    }
  }

  @Override
  public Client getDeposit(long idClient) {
    String sql = "SELECT * FROM clients WHERE id = ?";
    Client client = jdbcTemplate.queryForObject(sql, new Object[]{idClient}, clientMapper);
    client.setDeposit(jdbcTemplate.queryForObject
        ("SELECT summ FROM deposits WHERE id_client = ?",
            new Object[]{idClient}, Integer.class));
    return client;
  }

  @Override
  public ErrorList purchaseProduct(ProductDto productDto, long idClient) {
    int check = 0;
    ErrorList errorList;
    List<MyError> errors = new ArrayList<>();
    if (productDto.getCount() == 0) {
      productDto.setCount(1);
    }
    String sql = "SELECT * FROM products WHERE id = ?";
    ProductDto productFromDataBase = jdbcTemplate
        .queryForObject(sql, new Object[]{productDto.getId()}, purchaseProductDtoMapper);
    int newCount = productFromDataBase.getCount() - productDto.getCount();
    int deposit = jdbcTemplate.queryForObject
        ("SELECT summ FROM deposits WHERE id_client = ?",
            new Object[]{idClient}, Integer.class);
    int summPurchase = productDto.getCount() * productDto.getPrice();
    if (productDto.getCount() >= productFromDataBase.getCount()) {
      errors.add(new MyError("PRODUCT_COUNT_LESS",
          "count", "Нет такого количества товара."));
      check++;
    }
    if (summPurchase > deposit) {
      errors.add(new MyError("MONEY_LESS",
          "", "Недостаточно денег на счете."));
      check++;
    }
    if (check == 0) {
      jdbcTemplate.update("UPDATE products SET count = ? WHERE id = ?",
          newCount, productDto.getId());
      List<Long> idProducts = jdbcTemplate.query
          ("SELECT id_product FROM purchases WHERE id_client = " + idClient, new BasketMapper());
      if (idProducts.contains(productDto.getId())) {
        int oldCountPurchase = jdbcTemplate.queryForObject
            ("SELECT count FROM purchases WHERE id_product = " + productDto.getId(), Integer.class);
        int newCountPurchse = oldCountPurchase + productDto.getCount();
        jdbcTemplate.update("UPDATE purchases SET count = ? WHERE id_product = ?",
            newCountPurchse, productDto.getId());

      } else {
        jdbcTemplate.update
            ("INSERT INTO purchases (id_client,id_product,name,price,count) values (?,?,?,?,?)"
                ,
                idClient,
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getCount()
            );
      }
      errorList = null;
    } else

    {
      errorList = new ErrorList(errors);
    }
    return errorList;
  }

  @Override
  public Object addItemInBasket(ProductDto productDto, long idClient) {
    int check = 0;
    List<MyError> errors = new ArrayList<>();
    List<ProductDto> productDtoList = new ArrayList<>();

    if (productDto.getCount() == 0) {
      productDto.setCount(1);
    }

    //Проверка названия и цены
    ProductDto productFromDataBase = jdbcTemplate
        .queryForObject("SELECT * FROM products WHERE id = ?",
            new Object[]{productDto.getId()}, purchaseProductDtoMapper);
    if (!productDto.getName().equals(productFromDataBase.getName())) {
      errors.add(new MyError("WRONG_PRODUCT_NAME",
          "name", "Нет товара с таким названием."));
      check++;
    }
    if (productDto.getPrice() != productFromDataBase.getPrice()) {
      errors.add(new MyError("WRONG_PRICE",
          "price", "Неверная цена."));
      check++;
    }

    //Если название и цена совпадают, добавляем товар в корзину
    if (check == 0) {

      //Если в корзине уже есть такой товар, увеличиваем его количество
      List<Long> idProducts = jdbcTemplate.query
          ("SELECT id_product FROM baskets WHERE id_client = " + idClient, new BasketMapper());
      if (idProducts.contains(productDto.getId())) {
        int oldCount = jdbcTemplate.queryForObject
            ("SELECT count FROM baskets WHERE id_product = " + productDto.getId(), Integer.class);
        int newCount = oldCount + productDto.getCount();
        jdbcTemplate.update("UPDATE baskets SET count = ? WHERE id_product = ?",
            newCount, productDto.getId());
        String SQL = "SELECT id_product FROM baskets WHERE id_client = "
            + idClient;
        List<Long> idProductList = jdbcTemplate.query(SQL, new BasketMapper());
        for (Long anIdProductList : idProductList) {
          productDtoList.add(jdbcTemplate.queryForObject
              ("SELECT * FROM baskets WHERE id_product = ?",
                  new Object[]{anIdProductList}, new ProductInBasketMapper()));
        }
        return productDtoList;

        //если в корзине нет такого товара, то добавляем его
      } else {
        jdbcTemplate.update
            ("INSERT INTO baskets (id_client,id_product,name,price,count) values (?,?,?,?,?)"
                ,
                idClient,
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getCount()
            );

        String SQL = "SELECT id_product FROM baskets WHERE id_client = "
            + idClient;
        List<Long> idProductList = jdbcTemplate.query(SQL, new BasketMapper());
        for (Long anIdProductList : idProductList) {
          productDtoList.add(jdbcTemplate.queryForObject
              ("SELECT * FROM baskets WHERE id_product = ?",
                  new Object[]{anIdProductList}, new ProductInBasketMapper()));
        }
        return productDtoList;
      }
    } else {
      return new ErrorList(errors);
    }
  }

  @Override
  public void deleteItemFromBasket(long idProduct, long idClient) {
    jdbcTemplate.update("DELETE FROM baskets WHERE id_client = "
        + idClient + " AND id_product = " + idProduct);
  }

  @Override
  public Object editCountItemInBasket(ProductDto productDto, long idClient) {
    int check = 0;
    List<MyError> errors = new ArrayList<>();
    List<ProductDto> productDtoList = new ArrayList<>();

    //Проверка названия и цены
    ProductDto productFromDataBase = jdbcTemplate
        .queryForObject("SELECT * FROM products WHERE id = ?",
            new Object[]{productDto.getId()}, purchaseProductDtoMapper);
    if (!productDto.getName().equals(productFromDataBase.getName())) {
      errors.add(new MyError("WRONG_PRODUCT_NAME",
          "name", "Нет товара с таким названием."));
      check++;
    }
    if (productDto.getPrice() != productFromDataBase.getPrice()) {
      errors.add(new MyError("WRONG_PRICE",
          "price", "Неверная цена."));
      check++;
    }

    if (check == 0) {
      int oldCount = jdbcTemplate.queryForObject
          ("SELECT count FROM baskets WHERE id_product = " + productDto.getId(), Integer.class);
      int newCount = oldCount + productDto.getCount();
      jdbcTemplate.update("UPDATE baskets SET count = ? WHERE id_product = ?",
          newCount, productDto.getId());
      String SQL = "SELECT id_product FROM baskets WHERE id_client = "
          + idClient;
      List<Long> idProductList = jdbcTemplate.query(SQL, new BasketMapper());
      for (Long anIdProductList : idProductList) {
        productDtoList.add(jdbcTemplate.queryForObject
            ("SELECT * FROM baskets WHERE id_product = ?",
                new Object[]{anIdProductList}, new ProductInBasketMapper()));
      }
      return productDtoList;
    } else {
      return new ErrorList(errors);
    }
  }

  @Override
  public Object getBasket(long idClient) {
    List<ProductDto> productDtoList = new ArrayList<>();
    String SQL = "SELECT id_product FROM baskets WHERE id_client = "
        + idClient;
    List<Long> idProductList = jdbcTemplate.query(SQL, new BasketMapper());
    for (Long anIdProductList : idProductList) {
      productDtoList.add(jdbcTemplate.queryForObject
          ("SELECT * FROM baskets WHERE id_product = ?",
              new Object[]{anIdProductList}, new ProductInBasketMapper()));
    }
    return productDtoList;
  }

  @Override
  public Object purchaseFromBasket(List<ProductDto> productDtoList, long idClient) {
    int check = 0;
    List<ProductDto> bought = new ArrayList<>();
    List<ProductDto> remaining = new ArrayList<>();
    List<ProductDto> productListFromBasketInDataBase = new ArrayList<>();
    List<MyError> errors = new ArrayList<>();
    List<Long> idProductListFromBasket = jdbcTemplate.query
        ("SELECT id_product FROM baskets WHERE id_client = " + idClient, new BasketMapper());
    for (Long anIdProductList : idProductListFromBasket) {
      productListFromBasketInDataBase.add(jdbcTemplate.queryForObject
          ("SELECT * FROM baskets WHERE id_product = ?",
              new Object[]{anIdProductList}, new ProductInBasketMapper()));
    }

    //Если количество товара в запросе не указано, то устанавливаем количество товара из корзины
    //Если количество товара в запросе больше чем в корзине,
    // то устанавливаем количество товара из корзины
    for (ProductDto product : productDtoList) {
      for (ProductDto productFromBasketDataBase : productListFromBasketInDataBase) {
        if (product.getId() == productFromBasketDataBase.getId() && product.getCount() == 0) {
          product.setCount(productFromBasketDataBase.getCount());
        }
        if (product.getId() == productFromBasketDataBase.getId()
            && product.getCount() > productFromBasketDataBase.getCount()) {
          product.setCount(productFromBasketDataBase.getCount());
        }
      }
    }

    //Проверка названия, цены
    for (ProductDto product : productDtoList) {
      for (ProductDto productFromBasketDataBase : productListFromBasketInDataBase) {
        if (product.getId() == productFromBasketDataBase.getId()
            && !product.getName().equals(productFromBasketDataBase.getName())) {
          errors.add(new MyError("WRONG_PRODUCT_NAME",
              "name", "Нет товара " + product.getName() + "."));
          check++;
        }
        if (product.getId() == productFromBasketDataBase.getId()
            && product.getPrice() != productFromBasketDataBase.getPrice()) {
          errors.add(new MyError("WRONG_PRICE",
              "price", "Неверная цена у товара " + product.getName() + "."));
          check++;
        }
      }
    }

    //Проверка необходимой для покупки суммы на депозите
    int deposit = jdbcTemplate.queryForObject
        ("SELECT summ FROM deposits WHERE id_client = ?",
            new Object[]{idClient}, Integer.class);
    int summ = 0;
    for (ProductDto productDto : bought) {
      summ = summ + productDto.getPrice() * productDto.getCount();
    }
    if (summ > deposit) {
      errors.add(new MyError("MONEY_LESS",
          "", "Недостаточно денег на счете."));
      check++;
    }

    //Проверка наличия товара на складе и покупка
    List<Long> idProductFromBasketInDataBaseList = jdbcTemplate.query
        ("SELECT id FROM products", new IdMapper());
    for (ProductDto productDto : productDtoList) {
      if (idProductFromBasketInDataBaseList.contains(productDto.getId())) {
        bought.add(productDto);
        jdbcTemplate.update("DELETE FROM baskets WHERE id=" + productDto.getId());
        List<Long> idProducts = jdbcTemplate.query
            ("SELECT id_product FROM purchases WHERE id_client = " + idClient, new BasketMapper());
        if (idProducts.contains(productDto.getId())) {
          int oldCountPurchase = jdbcTemplate.queryForObject
              ("SELECT count FROM purchases WHERE id_product = " + productDto.getId(),
                  Integer.class);
          int newCountPurchse = oldCountPurchase + productDto.getCount();
          jdbcTemplate.update("UPDATE purchases SET count = ? WHERE id_product = ?",
              newCountPurchse, productDto.getId());

        } else {
          jdbcTemplate.update
              ("INSERT INTO purchases (id_client,id_product,name,price,count) values (?,?,?,?,?)"
                  ,
                  idClient,
                  productDto.getId(),
                  productDto.getName(),
                  productDto.getPrice(),
                  productDto.getCount()
              );
        }
      } else {
        remaining.add(productDto);
      }
    }

    if (check == 0) {
      return new PurchaseFromBasketDto(bought, remaining);
    } else {
      return new ErrorList(errors);
    }
  }

}
