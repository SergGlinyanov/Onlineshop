package net.thumbtack.repo.iface;

import net.thumbtack.model.Admin;

public interface AdminRepository {

  Long addAdmin(Admin admin);
  void editAdmin(Admin admin);



}
