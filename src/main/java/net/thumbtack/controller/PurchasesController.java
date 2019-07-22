package net.thumbtack.controller;

import java.util.List;
import javax.servlet.http.Cookie;
import net.thumbtack.dto.ProductDto;
import net.thumbtack.dto.PurchaseFromBasketDto;
import net.thumbtack.exception.ErrorList;
import net.thumbtack.service.iface.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/purchases")
public class PurchasesController {

  private final ClientService clientService;

  public PurchasesController(ClientService clientService) {
    this.clientService = clientService;
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
}
