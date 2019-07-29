package net.thumbtack.repo.iface;

import net.thumbtack.dto.LogInDto;

public interface UtilityRepository {

  Object logIn(LogInDto logInDto);
  Object getAccaunt(String role, long id);



}
