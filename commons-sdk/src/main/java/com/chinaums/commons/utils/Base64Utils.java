package com.chinaums.commons.utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class Base64Utils {
	private static final int  BUFFER_SIZE = 2 * 1024;
	/**
	 * @Description: 将base64编码字符串转换为文件
	 * @Author:
	 * @CreateTime:
	 * @param fileStr base64编码字符串
	 * @param path 路径-具体到文件
	 * @return
	 */
	public static boolean generateFile(String fileStr, String path) {
		if (fileStr == null) {
			return false;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// 解密
			byte[] b = decoder.decodeBuffer(fileStr);
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			File descFile = new File(path);
			if (!descFile.getParentFile().exists()) {
				// 如果目标文件所在的目录不存在，则创建目录
				// 创建目标文件所在的目录
				if (!descFile.getParentFile().mkdirs()) {
					return false;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * @Description: 根据文件地址转换为base64编码字符串
	 * @Author:
	 * @CreateTime:
	 * @return
	 */
	public static String getFileStr(String filePath) {
		InputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(filePath);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 加密
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	/**
	 * 将文件转成base64 字符串
	 * @param file
	 * @return  String
	 * @throws Exception
	 */

	public static String getFileStr(File file){
		byte[] buffer = null;
		try {
			FileInputStream inputFile = new FileInputStream(file);
			buffer = new byte[(int) file.length()];
			inputFile.read(buffer);
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new BASE64Encoder().encode(buffer);
	}

	/**
	 * base64编码
	 * @param bytes
	 * @return
	 */
	public static String encode(byte[] bytes){
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(bytes);
	}

	/**
	 * base64解码
	 * @param base64Str
	 * @return
	 */
	public static byte[] decode(String base64Str){
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			return decoder.decodeBuffer(base64Str);
		} catch (IOException e) {
			throw new RuntimeException("base64.decode.error", e);
		}
	}
}