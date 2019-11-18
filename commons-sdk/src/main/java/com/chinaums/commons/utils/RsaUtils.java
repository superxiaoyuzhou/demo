package com.chinaums.commons.utils;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * RSA工具类
 *
 * @author xionglei
 * @create 2018-05-11 18:18
 */
public class RsaUtils {

	/**
	 * 加密算法RSA
	 */
	private static final String KEY_ALGORITHM = "RSA";
	private static final String DEFAULT_ENCODE = "UTF-8";
	public static final String SIGNATURE = "signature";

	/**
	 * 证书内容长度
	 */
	private  static final Integer KEY_SIZE = 1024;

	public enum SignAlgorithm {
        SHA256withRSA, SHA1withRSA
	}

	/**
	 * 签名
	 * @param data
	 * @param priKey base64
	 * @return base64
	 * @throws Exception
	 */
	public static String sign(final String data, final String priKey) throws Exception {
		byte[] bytes = sign(data, priKey, SignAlgorithm.SHA256withRSA);
		return Base64.getEncoder().encodeToString(bytes);
	}

	/**
	 * 验签
	 * @param plain
	 * @param signature base64
	 * @param pubKey base64
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(final String plain, final String signature, final String pubKey) throws Exception {
		byte[] bytes = Base64.getDecoder().decode(signature);
		return verify(plain, bytes, pubKey, SignAlgorithm.SHA256withRSA);
	}

	public static byte[] sign(final String data, final String priKey, final SignAlgorithm signAlgorithm) throws Exception {
		final RSAPrivateKey key = genPriKey(priKey);
		final Signature st = Signature.getInstance(signAlgorithm.name());
		st.initSign(key);
		st.update(data.getBytes(DEFAULT_ENCODE));
		return st.sign();
	}

	public static boolean verify(final String plain, final byte[] signature, final String pubKey, final SignAlgorithm signAlgorithm) throws Exception {
		final RSAPublicKey key = genPubKey(pubKey);
		final Signature st = Signature.getInstance(signAlgorithm.name());
		st.initVerify(key);
		st.update(plain.getBytes(DEFAULT_ENCODE));
		return st.verify(signature);
	}

	private static RSAPublicKey genPubKey(final String pubKeyStr)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		final byte[] pubKeyBytes = Base64.getDecoder().decode(pubKeyStr);
		final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubKeyBytes);
		final KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		return (RSAPublicKey) keyFactory.generatePublic(keySpec);
	}

	private static RSAPrivateKey genPriKey(final String priKeyStr)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		final byte[] priKeyBytes = Base64.getDecoder().decode(priKeyStr);
		final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(priKeyBytes);
		final KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
	}


	/**
	 * 生成RSA密钥对(公钥和私钥)
	 * @throws Exception
	 */
	public static KeyPair genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(KEY_SIZE);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		System.out.println("公钥：\n" + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
		System.out.println("私钥：\n" + Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
		return keyPair;
	}

	private static boolean isNull(Object obj){
		return obj == null || StringUtils.isBlank(String.valueOf(obj));
	}

	/**
	 * 组装明文
	 * @param head
	 * @param body
	 * @return
	 */
	public static String assemblePlain(Map<String, String> head, Map<String, Object> body){
		TreeMap<String, String> signFieldMap = new TreeMap<>();

		head.entrySet().stream().forEach(entry -> {
			if(!SIGNATURE.equals(entry.getKey())){
				if(!isNull(entry.getValue())){
					signFieldMap.put(entry.getKey(), entry.getValue());
				}
			}
		});

		body.entrySet().stream().forEach(entry -> {
			if(!isNull(entry.getValue())){
				signFieldMap.put(entry.getKey(), String.valueOf(entry.getValue()));
			}
		});

		List<String> param = new ArrayList<>();
		for(String key : signFieldMap.keySet()){
			param.add(key + "=" + signFieldMap.get(key));
		}

		return Joiner.on("&").join(param);
	}
}