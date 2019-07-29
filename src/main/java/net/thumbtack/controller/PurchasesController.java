package net.thumbtack.controller;

import java.util.List;
import javax.servlet.http.Cookie;
import net.thumbtack.dto.CategoryPurchasesDto;
import net.thumbtack.dto.ClientPurchasesDto;
import net.thumbtack.dto.ProductDto;
import net.thumbtack.dto.ProductPurchasesDto;
import net.thumbtack.dto.PurchaseFromBasketDto;
import net.thumbtack.exception.ErrorList;
import net.thumbtack.service.iface.AdminService;
import net.thumbtack.service.iface.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/purchases")
public class PurchasesController {

  private final ClientService clientService;
  private final AdminService adminService;

  public PurchasesController(ClientService clientService,
      AdminService adminService) {
    this.clientService = clientService;
    this.adminService = adminService;
  }

  @PostMapping(produces = "application/json")
  public ResponseEntity<?> purchaseProduct
      (@RequestBody ProductDto productDto,
          @CookieValue(value = "role_id", required = false) Cookie cookieName){
    ErrorList errorList = clientService.purchaseProduct(productDto, cookieName);
    if(errorList == null) {
      return new ResponseEntity<Object>(productDto, HttpStatus.OK);
    }
    else return new ResponseEntity<Object>(errorList,HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/baskets")
  public ResponseEntity<?> purchaseFromBasket
      (@RequestBody List<ProductDto> productDtos,
          @CookieValue(value = "role_id", required = false) Cookie cookieName) {
    Object response = clientService.purchaseFromBasket(productDtos, cookieName);
    if(response instanceof PurchaseFromBasketDto) {
      return new ResponseEntity<>
          (clientService.purchaseFromBasket(productDtos, cookieName), HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>
          (clientService.purchaseFromBasket(productDtos, cookieName), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/client/{id}")
  public ResponseEntity<?> clientPurchases (@PathVariable long id){
    Object responseClass = adminService.clientPurchases(id);
    if (responseClass instanceof ClientPurchasesDto) {
      return ResponseEntity.ok(responseClass);
    }
    else return new ResponseEntity<>(responseClass, HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/category/{id}")
  public ResponseEntity<?> categoryPurchases (@PathVariable long id){
    Object responseClass = adminService.categoryPurchases(id);
    if (responseClass instanceof CategoryPurchasesDto) {
      return ResponseEntity.ok(responseClass);
    }
    else return new ResponseEntity<>(responseClass, HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<?> productPurchases (@PathVariable long id){
    Object responseClass = adminService.productPurchases(id);
    if (responseClass instanceof ProductPurchasesDto) {
      return ResponseEntity.ok(responseClass);
    }
    else return new ResponseEntity<>(responseClass, HttpStatus.BAD_REQUEST);
  }


}
