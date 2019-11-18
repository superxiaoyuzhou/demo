package com.chinaums.commons.utils;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Base64;


/**
 * @Description :DES加密工具类
 * @Author : yuanxh
 * @CreateDate : 2019/8/6
 */
public class EncryptUtils {

    /**
     * DES加密秘钥
     */
    private static byte[] KEY = ("3O" + "8l" + "$#" + "1@").getBytes();

    public static byte[] encrypt(final byte[] plainData) {
        try {
            final DESKeySpec desKey = new DESKeySpec(KEY);
            final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            final SecretKey secureKey = keyFactory.generateSecret(desKey);
            final Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, new SecureRandom());
            return cipher.doFinal(plainData);
        } catch (Throwable e) {
            throw new RuntimeException("数据加密异常：" + e.getLocalizedMessage(), e);
        }
    }

    public static byte[] decrypt(final byte[] content) {
        try {
            final DESKeySpec desKey = new DESKeySpec(KEY);
            final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            final SecretKey secureKey = keyFactory.generateSecret(desKey);
            final Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secureKey, new SecureRandom());
            return cipher.doFinal(content);
        } catch (Throwable e) {
            throw new RuntimeException("数据解密异常：" + e.getLocalizedMessage(), e);
        }
    }

    /**
     * 加密
     * @param plainData
     * @return
     */
    public static String encrypt(final String plainData) {
        try {
            if(StringUtils.isBlank(plainData)){
                return plainData;
            }
            final DESKeySpec desKey = new DESKeySpec(KEY);
            final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            final SecretKey secureKey = keyFactory.generateSecret(desKey);
            final Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, new SecureRandom());
            return  Base64.getEncoder().encodeToString(cipher.doFinal(plainData.getBytes()));
        } catch (Throwable e) {
            throw new RuntimeException("数据加密异常：" + e.getLocalizedMessage(), e);
        }
    }

    /**
     * 解密
     * @param content
     * @return
     */
    public static String decrypt(final String content) {
        try {
            if(StringUtils.isBlank(content)){
                return content;
            }
            final DESKeySpec desKey = new DESKeySpec(KEY);
            final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            final SecretKey secureKey = keyFactory.generateSecret(desKey);
            final Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secureKey, new SecureRandom());
            return new String(cipher.doFinal(Base64.getDecoder().decode(content)));
        } catch (Throwable e) {
            throw new RuntimeException("数据解密异常：" + e.getLocalizedMessage(), e);
        }
    }

}