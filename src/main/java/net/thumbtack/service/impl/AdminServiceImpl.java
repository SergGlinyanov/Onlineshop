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
    Object responseFromDataBase = adminRepository.addAdmin(admin);
    Object response = null;
    if (responseFromDataBase instanceof Long) {
      admin.setId((long)responseFromDataBase);
      response = new AdminResponseDto(admin);
    }
    if (responseFromDataBase instanceof ErrorList) {
      response = responseFromDataBase;
    }
    return response;
  }

  @Override
  public Object editAdmin(EditAdminDto editAdminDto, long id) {
    return adminRepository.editAdmin(editAdminDto, id);
  }

  @Override
  public Object clientPurchases(long id) {
    return adminRepository.clientPurchases(id);
  }

  @Override
  public Object categoryPurchases(long id) {
    return adminRepository.categoryPurchases(id);
  }

  @Override
  public Object productPurchases(long id) {
    return adminRepository.productPurchases(id);
  }
}
