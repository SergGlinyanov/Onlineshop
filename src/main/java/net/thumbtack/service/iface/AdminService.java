package net.thumbtack.service.iface;

import net.thumbtack.dto.EditAdminDto;
import net.thumbtack.dto.ResponseAdminDto;
import net.thumbtack.model.Admin;

public interface AdminService {

  ResponseAdminDto addAdmin(Admin admin);
  void editAdmin(EditAdminDto editAdminDto,long id);




}
