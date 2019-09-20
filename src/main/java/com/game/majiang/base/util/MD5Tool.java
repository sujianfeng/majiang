package com.game.majiang.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Tool {
    public static String md5Encryption(String str) {
        String result = null;
        if (str != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(str.getBytes());
                byte b[] = md.digest();

                int i;

                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        buf.append("0");
                    buf.append(Integer.toHexString(i));
                }
                result = buf.toString();

                // 16位
                // result = buf.toString().substring(8, 24);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

//	public static void main(String[] args) {
//		// 加密
//		System.out
//				.println(md5Encryption("chargemsg=支付成功&chargestatus=0&cpparam=5_20160302110455_5886977&linkid=540fbbeb7feb4828abf5e9a3a5cb3c2b&paytype=6&price=300&sdkno=10&encryptkey=ec77e0363bed3ae6b16fe50d094fe1ca"));
//	}

}
