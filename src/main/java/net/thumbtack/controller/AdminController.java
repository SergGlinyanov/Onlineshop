package net.thumbtack.controller;

import net.thumbtack.dto.EditAdminDto;
import net.thumbtack.dto.ResponseAdminDto;
import net.thumbtack.model.Admin;
import net.thumbtack.service.iface.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

  private final net.thumbtack.service.iface.AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  @PostMapping(value = "api/admins")
  public ResponseEntity<ResponseAdminDto> addAdmin(@RequestBody Admin admin) {
    return new ResponseEntity<>(this.adminService.addAdmin(admin), HttpStatus.OK);
  }

  @PutMapping("api/admins/{id}")
  public ResponseEntity<ResponseAdminDto> editAdmin(@RequestBody EditAdminDto editAdminDto, @PathVariable long id){
    adminService.editAdmin(editAdminDto, id);
    ResponseAdminDto responseAdminDto = new ResponseAdminDto(id, editAdminDto.getLastName(),
        editAdminDto.getFirstName(), editAdminDto.getPatronymic(), editAdminDto.getPosition());
    return new ResponseEntity<>(responseAdminDto, HttpStatus.OK);
  }
}
