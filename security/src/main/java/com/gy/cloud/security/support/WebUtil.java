package com.gy.cloud.security.support;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WebUtil {

  public static void responseText(HttpServletResponse response, String content) throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
    response.setContentLength(bytes.length);
    response.getOutputStream().write(bytes);
    response.flushBuffer();
  }

  public static String resultOk(Object object) {
    JSONObject root = new JSONObject();
    root.put("code", 0);
    root.put("data", object);
    return root.toString();
  }

  public static String result(int code, Object object) {
    JSONObject root = new JSONObject();
    root.put("code", code);
    root.put("data", object);
    return root.toString();
  }

}
