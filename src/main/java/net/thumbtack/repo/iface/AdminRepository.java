package net.thumbtack.repo.iface;

import net.thumbtack.dto.EditAdminDto;
import net.thumbtack.model.Admin;

public interface AdminRepository {

  Object addAdmin(Admin admin);
  Object editAdmin(EditAdminDto editAdminDto, long id);
  Admin getAdminById(long id);
  Object clientPurchases(long id);
  Object categoryPurchases(long id);
  Object productPurchases(long id);



}
