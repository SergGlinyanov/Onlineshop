package net.thumbtack.service.impl;

import java.util.ArrayList;
import java.util.List;
import net.thumbtack.dto.AdminResponseDto;
import net.thumbtack.dto.EditAdminDto;
import net.thumbtack.exception.ErrorList;
import net.thumbtack.exception.MyError;
import net.thumbtack.model.Admin;
import net.thumbtack.repo.iface.AdminRepository;
import net.thumbtack.service.iface.AdminService;

public class AdminServiceImpl implements AdminService {

  private final AdminRepository adminRepository;

  public AdminServiceImpl(AdminRepository adminRepository) {
    this.adminRepository = adminRepository;
  }

  @Override
  public Object addAdmin(Admin admin) {
    List<MyError> errors = new ArrayList<>();
    if(!admin.getLastName().matches("^[а-яА-ЯёЁ]+$")
        ||!admin.getFirstName().matches("^[а-яА-ЯёЁ]+$")
        ||!admin.getPatronymic().matches("^[а-яА-ЯёЁ]+$")){
      errors.add(new MyError("WRONG_FORMAT",
          "Ф.И.О.", "Только кириллица."));
      return new ErrorList(errors);
    }
    else return new AdminResponseDto(adminRepository.addAdmin(admin),
        admin.getLastName(), admin.getFirstName(), admin.getPatronymic(),
        admin.getPosition());
  }

  @Override
  public void editAdmin(EditAdminDto editAdminDto, long id) {
    adminRepository.editAdmin(editAdminDto, id);
  }
}
