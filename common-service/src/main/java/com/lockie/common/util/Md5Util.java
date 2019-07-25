package com.lockie.common.util;

import java.security.MessageDigest;

public final class Md5Util {
	/**
	 * 给指定的字符串进行MD5加密
	 * 
	 * @param plainText
	 *            需要加密的串
	 * @return 返回加密后的串
	 */
	public static String getMD5(String plainText) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes("UTF-8"));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}

				if (i < 16) {
					buf.append("0");
				}

				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
