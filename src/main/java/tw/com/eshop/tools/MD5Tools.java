package tw.com.eshop.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * MD5技術加密解密
 */
public class MD5Tools {

	/***
	 * MD5加碼 生成32位md5碼
	 */
	public String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 執行一次加密，兩次解密
	 */
	public String convertMD5(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	/**
	 * base64加密
	 */
	public String base64Encryption(String str) {
		String result = null;
		final Base64.Encoder encoder = Base64.getEncoder();
		byte[] strarr = null;
		try {
			strarr = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 編碼
		final String encodedText = encoder.encodeToString(strarr);
		result = encodedText;

		return result;
	}

	/**
	 * base64解密
	 */
	public String base64Decryption(String str) {
		String result = null;
		final Base64.Decoder decoder = Base64.getDecoder();
		// 解碼
		try {
			result = new String(decoder.decode(str), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public String replacer(String data) {
	      
	      try {
	         data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
	         data = data.replaceAll("\\+", "%2B");
	         data = URLDecoder.decode(data, "utf-8");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return data;
	   }

}