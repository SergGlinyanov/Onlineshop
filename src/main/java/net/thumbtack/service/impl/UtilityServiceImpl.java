package net.thumbtack.service.impl;

import javax.servlet.http.Cookie;
import net.thumbtack.dto.AdminResponseDto;
import net.thumbtack.dto.ClientResponseDto;
import net.thumbtack.dto.LogInDto;
import net.thumbtack.exception.ErrorList;
import net.thumbtack.model.Admin;
import net.thumbtack.model.Client;
import net.thumbtack.repo.iface.UtilityRepository;
import net.thumbtack.service.iface.UtilityService;
import org.springframework.beans.factory.annotation.Value;

public class UtilityServiceImpl implements UtilityService {

  private final UtilityRepository utilityRepository;

  public UtilityServiceImpl(UtilityRepository utilityRepository) {
    this.utilityRepository = utilityRepository;
  }

  @Value("${min_password_length}")
  private int min_password_length;

  public int getMin_password_length() {
    return min_password_length;
  }

  @Override
  public Object logIn(LogInDto logInDto) {
    Object responseFromDataBase = utilityRepository.logIn(logInDto);
    Object response = null;
    AdminResponseDto adminResponseDto;
    if (responseFromDataBase instanceof Admin) {
      response = new AdminResponseDto((Admin) responseFromDataBase);
    }
    if (responseFromDataBase instanceof Client) {
      response = new ClientResponseDto((Client) responseFromDataBase);
    }
    if (responseFromDataBase instanceof ErrorList) {
      response = responseFromDataBase;
    }
    return response;
  }

  @Override
  public Object getAccaunt(Cookie cookie) {
    String[] cookieRequest = cookie.getValue().split("!");
    String role = cookieRequest[0];
    long id = Long.parseLong(cookieRequest[1]);
    return utilityRepository.getAccaunt(role, id);
  }
}
