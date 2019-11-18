package com.chinaums.dataencrypt.util;

import lombok.AllArgsConstructor;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

/**
 * 对称加密 密码器
 *
 * @author kris
 */
public class SymmetricalCipher {

  /**
   * algorithm枚举
   */
  @AllArgsConstructor
  public enum AlgorithmEnum {
//  AES_CBC_NoPadding("AES/CBC/NoPadding"),
    AES_CBC_PKCS5Padding("AES/CBC/PKCS5Padding"),
//  AES_ECB_NoPadding("AES/ECB/NoPadding"),
    AES_ECB_PKCS5Padding("AES/ECB/PKCS5Padding"),
//  DES_CBC_NoPadding("DES/CBC/NoPadding"),
    DES_CBC_PKCS5Padding("DES/CBC/PKCS5Padding"),
//  DES_ECB_NoPadding("DES/ECB/NoPadding"),
    DES_ECB_PKCS5Padding("DES/ECB/PKCS5Padding"),
//  DESede_CBC_NoPadding("DESede/CBC/NoPadding"),
    DESede_CBC_PKCS5Padding("DESede/CBC/PKCS5Padding"),
//  DESede_ECB_NoPadding("DESede/ECB/NoPadding"),
    DESede_ECB_PKCS5Padding("DESede/ECB/PKCS5Padding"),
    ;
    public String value;
  }

  /** 算法/工作模式/填充方式 */
  private String algorithm;
  /** 密钥 */
  private Key key;
  /** java密码器 */
  private Cipher cipher;

  /** 偏移量 */
  private byte[] iv;

  /**
   * 构造器，将自动生成密钥（默认长度）
   *
   * @param algorithm 算法/工作模式/填充方式, 如："AES/ECB/PKCS5Padding","DESede/ECB/PKCS5Padding"
   */
  public SymmetricalCipher(String algorithm) {
    this.algorithm = algorithm;
    this.generateKey();
    try {
      this.cipher = Cipher.getInstance(this.algorithm);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 构造器
   *
   * @param algorithm 算法/工作模式/填充方式, 如："AES/ECB/PKCS5Padding","DESede/ECB/PKCS5Padding"
   * @param key 密钥
   */
  public SymmetricalCipher(String algorithm, Key key) {
    this.algorithm = algorithm;
    this.key = key;
    try {
      this.cipher = Cipher.getInstance(this.algorithm);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 构造器
   *
   * @param algorithm 算法/工作模式/填充方式, 如："AES/ECB/PKCS5Padding","DESede/ECB/PKCS5Padding"
   * @param keySize 密钥长度
   */
  public SymmetricalCipher(String algorithm, Integer keySize) {
    this.algorithm = algorithm;
    try {
      this.cipher = Cipher.getInstance(this.algorithm);
      this.generateKey(keySize);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Key getKey() {
    return key;
  }

  public SymmetricalCipher setKey(Key key) {
    this.key = key;
    return this;
  }

  public SymmetricalCipher setKey(byte[] keyBytes) {
    this.setKey(new SecretKeySpec(keyBytes, matchKeyAlgorithm(algorithm)));
    return this;
  }

  public String getAlgorithm() {
    return algorithm;
  }

  public byte[] getIv() {
    return iv;
  }

  public SymmetricalCipher setIv(byte[] iv) {
    this.iv = iv;
    return this;
  }

  private void initCipher(int mode) {
    String schema = this.algorithm.split("/")[1];
    try {
      if ("CBC".equals(schema)) {
        if (iv == null) {
          throw new RuntimeException("CBC模式必须setIv");
        }
        this.cipher.init(mode, this.key, new IvParameterSpec(iv));
      } else {
        this.cipher.init(mode, this.key);
      }
    } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
      throw new RuntimeException("Cipher 初始化失败", e);
    }
  }

  public Key generateKey() {
    return generateKey(null);
  }

  /**
   * 生成密钥
   *
   * @param keySize 密钥长度
   */
  public Key generateKey(Integer keySize) {
    KeyGenerator kg = null;
    try {
      kg = KeyGenerator.getInstance(matchKeyAlgorithm(this.algorithm));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    if (keySize != null) {
      // 生成一个随机数来打乱密钥。如果不传这个值，keyPairGenerator.initialize(SIZE_KEY)会自己生成一个
      SecureRandom secureRandom = new SecureRandom(new Date().toString().getBytes());
      kg.init(keySize, secureRandom);
    }
    this.key = kg.generateKey();
    return this.key;
  }

  /**
   * 加密
   *
   * @param data 待加密数据
   * @return 密文
   */
  public byte[] encrypt(byte[] data) {
    if (this.key == null) {
      throw new RuntimeException("当前密码器中无密钥，请先setKey");
    }
    try {
      this.initCipher(Cipher.ENCRYPT_MODE);
      return cipher.doFinal(data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 解密
   *
   * @param data 待解密数据
   * @return 明文
   */
  public byte[] decrypt(byte[] data) {
    if (this.key == null) {
      throw new RuntimeException("当前密码器中无密钥，请先setKey");
    }
    try {
      this.initCipher(Cipher.DECRYPT_MODE);
      return cipher.doFinal(data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static String matchKeyAlgorithm(String algorithm) {
    if (!algorithm.contains("/")) {
      return algorithm;
    }
    return algorithm.substring(0, algorithm.indexOf("/"));
  }

}
