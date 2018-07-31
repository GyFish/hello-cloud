package com.gy.cloud.security.handler;

import com.gy.cloud.security.support.WebUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutHandler implements LogoutHandler, LogoutSuccessHandler {

  // Before Logout
  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

  }

  // After Logout
  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    WebUtil.responseText(response, WebUtil.resultOk(null));
  }
}