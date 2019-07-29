package net.thumbtack.service.iface;

import net.thumbtack.dto.EditAdminDto;
import net.thumbtack.model.Admin;

public interface AdminService {

  Object addAdmin(Admin admin) ;
  Object editAdmin(EditAdminDto editAdminDto,long id);
  Object clientPurchases(long id);
  Object categoryPurchases(long id);
  Object productPurchases(long id);




}
