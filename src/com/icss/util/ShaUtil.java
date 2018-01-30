package com.icss.util;

import java.security.MessageDigest;

/**
 * 采用SHA对密码进行加密
 * 
 */
public class ShaUtil {
//	private static final String TAG = "ShaUtil";
	/**
	 * SHA加密 生成40位SHA码
	 * 
	 * @param 待加密字符串
	 * @return 返回40位SHA码
	 */
	public static String shaEncode(String inStr) {
		MessageDigest sha = null;
		StringBuffer hexValue = null;
		try {
			sha = MessageDigest.getInstance("SHA-512");
			byte[] byteArray = inStr.getBytes("UTF-8");
			byte[] shaBytes = sha.digest(byteArray);
			hexValue = new StringBuffer();
			for (int i = 0; i < shaBytes.length; i++) {
				int val = ((int) shaBytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
		} catch (Exception e) {
//			Log.e(TAG, "密码加密失败");
			return "";
		}
		return hexValue.toString();
	}

	/**
	 * 测试主函数
	 * 
	 * @param 待加密字符串
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		String str = new String("盐123456");
		System.out.println("原始：" + str);
		System.out.println("SHA后：" + shaEncode(str));
	}
}
