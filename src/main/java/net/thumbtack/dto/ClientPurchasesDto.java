package net.thumbtack.dto;

import java.util.List;
import net.thumbtack.model.Client;
import net.thumbtack.model.Product;

public class ClientPurchasesDto {

  private String lastName;
  private String firstName;
  private String patronymic;
  private String email;
  private String address;
  private String phone;
  private List<ProductDto> purchaseProducts;
  private int total;

  public ClientPurchasesDto(String lastName, String firstName, String patronymic,
      String email, String address, String phone) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.patronymic = patronymic;
    this.email = email;
    this.address = address;
    this.phone = phone;
  }

  public ClientPurchasesDto(Client client) {
    this.lastName = client.getLastName();
    this.firstName = client.getFirstName();
    this.patronymic = client.getPatronymic();
    this.email = client.getEmail();
    this.address = client.getAddress();
    this.phone = client.getPhone();
  }

  public ClientPurchasesDto() {
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getPatronymic() {
    return patronymic;
  }

  public void setPatronymic(String patronymic) {
    this.patronymic = patronymic;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<ProductDto> getPurchaseProducts() {
    return purchaseProducts;
  }

  public void setPurchaseProducts(List<ProductDto> purchaseProducts) {
    this.purchaseProducts = purchaseProducts;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }
}
