package net.thumbtack.service.impl;

import net.thumbtack.dto.EditAdminDto;
import net.thumbtack.dto.ResponseAdminDto;
import net.thumbtack.model.Admin;
import net.thumbtack.repo.iface.AdminRepository;
import net.thumbtack.service.iface.AdminService;

public class AdminServiceImpl implements AdminService {

  private final AdminRepository adminRepository;

  public AdminServiceImpl(AdminRepository adminRepository) {
    this.adminRepository = adminRepository;
  }

  @Override
  public ResponseAdminDto addAdmin(Admin admin) {
    return new ResponseAdminDto(adminRepository.addAdmin(admin),
        admin.getLastName(), admin.getFirstName(), admin.getPatronymic(),
        admin.getPosition());
  }

  @Override
  public void editAdmin(EditAdminDto editAdminDto, long id) {
    Admin admin = new Admin(id, editAdminDto.getLastName(), editAdminDto.getFirstName(),
        editAdminDto.getPatronymic(), "", editAdminDto.getNewPassword(), editAdminDto.getPosition());
    adminRepository.editAdmin(admin);
  }
}
