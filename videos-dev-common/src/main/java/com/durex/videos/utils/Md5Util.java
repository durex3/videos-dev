package com.durex.videos.utils;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;

/**
 * @author gelong
 */
public class Md5Util {

	/**
	 * 对字符串进行md5加密
	 */
	public static String getMd5Str(String strValue) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		return Base64.encodeBase64String(md5.digest(strValue.getBytes()));
	}

	public static void main(String[] args) {
		try {
			String md5 = getMd5Str("durex");
			System.out.println(md5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
