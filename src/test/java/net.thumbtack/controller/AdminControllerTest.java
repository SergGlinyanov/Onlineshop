package net.thumbtack.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import net.thumbtack.dto.EditAdminDto;
import net.thumbtack.model.Admin;
import net.thumbtack.service.iface.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AdminControllerTest {

  private AdminController underTest;

  @Mock
  private AdminService adminService;

  @Captor
  private ArgumentCaptor<Admin> captor;

  @Captor
  private ArgumentCaptor<EditAdminDto> captor1 = ArgumentCaptor.forClass(EditAdminDto.class);

  @Before
  public void setUpClass() {
    MockitoAnnotations.initMocks(this);
    underTest = new AdminController(this.adminService);
  }

  @Test
  public void testAddAdmin() {
    Admin admin = new Admin((long) 1, "Ivanov", "Ivan",
        "Ivanovich", "admin", "qwerty", "manager");
    HttpServletResponse response = new HttpServletResponse() {
      @Override
      public void addCookie(Cookie cookie) {
      }

      @Override
      public boolean containsHeader(String s) {
        return false;
      }

      @Override
      public String encodeURL(String s) {
        return null;
      }

      @Override
      public String encodeRedirectURL(String s) {
        return null;
      }

      @Override
      public String encodeUrl(String s) {
        return null;
      }

      @Override
      public String encodeRedirectUrl(String s) {
        return null;
      }

      @Override
      public void sendError(int i, String s) throws IOException {

      }

      @Override
      public void sendError(int i) throws IOException {

      }

      @Override
      public void sendRedirect(String s) throws IOException {

      }

      @Override
      public void setDateHeader(String s, long l) {

      }

      @Override
      public void addDateHeader(String s, long l) {

      }

      @Override
      public void setHeader(String s, String s1) {

      }

      @Override
      public void addHeader(String s, String s1) {

      }

      @Override
      public void setIntHeader(String s, int i) {

      }

      @Override
      public void addIntHeader(String s, int i) {

      }

      @Override
      public void setStatus(int i) {

      }

      @Override
      public void setStatus(int i, String s) {

      }

      @Override
      public int getStatus() {
        return 0;
      }

      @Override
      public String getHeader(String s) {
        return null;
      }

      @Override
      public Collection<String> getHeaders(String s) {
        return null;
      }

      @Override
      public Collection<String> getHeaderNames() {
        return null;
      }

      @Override
      public String getCharacterEncoding() {
        return null;
      }

      @Override
      public String getContentType() {
        return null;
      }

      @Override
      public ServletOutputStream getOutputStream() throws IOException {
        return null;
      }

      @Override
      public PrintWriter getWriter() throws IOException {
        return null;
      }

      @Override
      public void setCharacterEncoding(String s) {

      }

      @Override
      public void setContentLength(int i) {

      }

      @Override
      public void setContentLengthLong(long l) {

      }

      @Override
      public void setContentType(String s) {

      }

      @Override
      public void setBufferSize(int i) {

      }

      @Override
      public int getBufferSize() {
        return 0;
      }

      @Override
      public void flushBuffer() throws IOException {

      }

      @Override
      public void resetBuffer() {

      }

      @Override
      public boolean isCommitted() {
        return false;
      }

      @Override
      public void reset() {

      }

      @Override
      public void setLocale(Locale locale) {

      }

      @Override
      public Locale getLocale() {
        return null;
      }
    };
    Cookie cookie = new Cookie("role_id", "admin!1");
    response.addCookie(cookie);
    underTest.addAdmin(admin, response);
    verify(adminService).addAdmin(captor.capture());
    Admin value = captor.getValue();
    assertEquals(admin, value);
  }

  @Test
  public void testEditAdmin() {
    EditAdminDto admin = new EditAdminDto((long) 1, "Ivanov", "Ivan",
        "Ivanovich", "admin", "qwerty", "manager");
    underTest.editAdmin(admin, (long) 1);
    verify(adminService).editAdmin(captor1.capture(), eq((long) 1));
    EditAdminDto value = captor1.getValue();
    assertEquals(admin.getOldPassword(), value.getOldPassword());
  }
}
