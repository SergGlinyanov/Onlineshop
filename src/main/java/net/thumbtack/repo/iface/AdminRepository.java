package net.thumbtack.repo.iface;

import net.thumbtack.dto.EditAdminDto;
import net.thumbtack.model.Admin;

public interface AdminRepository {

  Long addAdmin(Admin admin);
  void editAdmin(EditAdminDto editAdminDto, long id);
  Admin getAdminById(long id);



}
