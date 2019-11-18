package com.chinaums.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash摘要工具类
 * @author xionglei
 * @create 2018-05-16 14:14
 */

public class MessageDigestUtils {

    private static final String ALGORITHM_MD5 = "MD5";
    private static final String ALGORITHM_SHA1 = "SHA-1";
    private static final String ALGORITHM_SHA256 = "SHA-256";

    /**
     * md5摘要
     * @param bytes
     * @return
     */
    public static String md5(byte[] bytes){
        return getHashDigest(bytes, ALGORITHM_MD5);
    }

    /**
     * sha1摘要
     * @param bytes
     * @return
     */
    public static String sha1(byte[] bytes){
        return getHashDigest(bytes, ALGORITHM_SHA1);
    }

    /**
     * sha256摘要
     * @param bytes
     * @return
     */
    public static String sha256(byte[] bytes){
        return getHashDigest(bytes, ALGORITHM_SHA256);
    }

    /**
     * 获取hash摘要
     * @param bytes
     * @param algorithm 算法名称
     * @return 16进制
     */
    public static String getHashDigest(byte[] bytes, String algorithm){
        try {
            final MessageDigest mDigest = MessageDigest.getInstance(algorithm);
            mDigest.update(bytes);
            return bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转16进制
     * @param bytes
     * @return
     */
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
