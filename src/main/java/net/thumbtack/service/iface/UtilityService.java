package net.thumbtack.service.iface;

import javax.servlet.http.Cookie;
import net.thumbtack.dto.LogInDto;

public interface UtilityService {

  Object logIn(LogInDto logInDto);
  Object getAccaunt(Cookie cookie);


}
