package com.gy.cloud.common.util;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Created by gy on 2018/8/14.
 */
public class AESUtil {

  private static final String ALGORITHM = "AES";
  private static final String ALGORITHM_DETAIL = "AES/CBC/PKCS5Padding";
  private static final String SEED = "1234567890123456";
  private static final String KEY = "1234567890123456";
  private static final String IV = "1234560123456789";

  /**
   * 生成密钥
   */
  public static String encrypt(String content) throws Exception {

//    // 设置种子
//    SecureRandom random = new SecureRandom();
//    random.setSeed(SEED.getBytes());
//
//    // 获取一个秘钥生成器实例，并使用种子初始化
//    KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
//    keyGenerator.init(random);
//
//    // 定义秘钥
//    SecretKey secretKey = keyGenerator.generateKey();
//    SecretKeySpec keySpec = new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);

    // 定义秘钥
    SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);

    // 定义偏移量
    IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());

    // 获取一个密码器，并使用生成的秘钥初始化
    Cipher cipher = Cipher.getInstance(ALGORITHM_DETAIL);
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

    // 使用密码器加密
    byte[] result = cipher.doFinal(content.getBytes("utf-8"));

    // 转为Base64字符串
    return Base64Utils.encodeToString(result);
  }

  public static void main(String[] args) throws Exception {
    System.out.println(AESUtil.encrypt("hello"));
  }

}
