package net.thumbtack.controller;

import javax.servlet.http.Cookie;
import net.thumbtack.dto.ProductDto;
import net.thumbtack.service.iface.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/baskets")
public class BasketsController {

  private final ClientService clientService;

  public BasketsController(ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping
  public ResponseEntity<?> addItemInBasket
      (@RequestBody ProductDto productDto,
          @CookieValue(value = "role_id", required = false) Cookie cookieName) {
    Object responseClass = clientService.addItemInBasket(productDto, cookieName);
    if (responseClass instanceof ProductDto) {
      return ResponseEntity.ok(responseClass);
    }
    else return new ResponseEntity<>(responseClass, HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItemFromBasket
      (@PathVariable long id,
          @CookieValue(value = "role_id", required = false) Cookie cookieName) {
    clientService.deleteItemFromBasket(id, cookieName);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<?> editCountItemInBasket
      (@RequestBody ProductDto productDto,
          @CookieValue(value = "role_id", required = false) Cookie cookieName) {
    return new ResponseEntity<>
        (clientService.editCountItemInBasket(productDto, cookieName), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<?> getBasket
      (@CookieValue(value = "role_id", required = false) Cookie cookieName) {
    return new ResponseEntity<>
        (clientService.getBasket(cookieName), HttpStatus.OK);
  }
}
