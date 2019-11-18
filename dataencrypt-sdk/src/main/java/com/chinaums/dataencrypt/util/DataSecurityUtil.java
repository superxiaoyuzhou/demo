package com.chinaums.dataencrypt.util;

import com.chinaums.commons.utils.FuzzyUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author LiXun
 * @date 2019-07-25 11:09
 */
public class DataSecurityUtil {

  private static final String ALGORITHM = SymmetricalCipher.AlgorithmEnum.DES_ECB_PKCS5Padding.value;
  private static final byte[] KEY_BYTES = ("3O" + "8l" + "$#" + "1@").getBytes();
  private static final SymmetricalCipher dataCipher = new SymmetricalCipher(ALGORITHM)
      .setKey(KEY_BYTES);


  /**
   * 解密
   */
  public static String decrypt(String data) {
    try {
      if (StringUtils.isBlank(data)) {
        return data;
      }
      return new String(dataCipher.decrypt(Base64.getDecoder().decode(data)), "utf-8");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("解密失败", e);
    }
  }

  /**
   * 加密
   */
  public static String encrypt(String data) {
    try {
      if (StringUtils.isBlank(data)) {
        return data;
      }
      return Base64.getEncoder().encodeToString(dataCipher.encrypt(data.getBytes("utf-8")));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("加密失败", e);
    }
  }

  /**
   * 身份证脱敏
   * @param idCard
   * @return
   */
  public static String fuzzyIdCard(String idCard) {
    return FuzzyUtils.certifId(idCard);
  }

  /**
   * 银行卡号脱敏
   * @param cardNo
   * @return
   */
  public static String fuzzyCardNo(String cardNo){
    return FuzzyUtils.bankCard(cardNo);
  }

  /**
   * 手机号脱敏
   * @param phone
   * @return
   */
  public static String fuzzyPhone(String phone) {
    if (StringUtils.isBlank(phone)) {
      return "";
    }
    return FuzzyUtils.mobilePhone(phone);
  }

  /**
   * 固定电话脱敏
   * @param phone
   * @return
   */
  public static String fuzzyLandlinePhone(String phone) {
    if (StringUtils.isBlank(phone)) {
      return "";
    }
    return FuzzyUtils.telePhone(phone);
  }

  /**
   * 姓名脱敏
   * @param name
   * @return
   */
  public static String fuzzyName(String name) {
    if (StringUtils.isBlank(name)) {
      return "";
    }
    return FuzzyUtils.personName(name);
  }

  public static void main(String[] args) {

    System.out.println(Base64.getEncoder().encodeToString(new SymmetricalCipher(ALGORITHM).getKey().getEncoded()));
    new SymmetricalCipher(ALGORITHM).setKey(KEY_BYTES).encrypt("123".getBytes());
    System.out.println(fuzzyIdCard("500236199107251835"));
    System.out.println(fuzzyPhone("18223132221"));
    System.out.println(fuzzyLandlinePhone("23132221"));
    System.out.println(fuzzyName("23132221"));
    System.out.println(DataSecurityUtil.encrypt("13384865927"));
  }


}
