package net.thumbtack.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import net.thumbtack.dto.EditAdminDto;
import net.thumbtack.dto.AdminResponseDto;
import net.thumbtack.model.Admin;
import net.thumbtack.service.iface.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

  private final net.thumbtack.service.iface.AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  @PostMapping
  public ResponseEntity<?> addAdmin(@RequestBody Admin admin,
      HttpServletResponse response) {
    Object responseClass = adminService.addAdmin(admin);
    if (responseClass instanceof AdminResponseDto) {
      Cookie cookie = new Cookie("role_id", "admin!" + ((AdminResponseDto) responseClass).getId());
      response.addCookie(cookie);
      return ResponseEntity.ok(responseClass);
    }
    else return new ResponseEntity<>(responseClass, HttpStatus.BAD_REQUEST);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> editAdmin(@RequestBody EditAdminDto editAdminDto,
      @PathVariable long id){
    Object response = adminService.editAdmin(editAdminDto, id);
    if (response instanceof AdminResponseDto) {
      return ResponseEntity.ok(response);
    }
    else return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
