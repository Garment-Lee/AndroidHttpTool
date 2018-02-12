package com.lgf.androidhttptool.util;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Random;

public class Md5Utils {

    public final static String MD5(String string) {
        try {
            byte[] btInput = string.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            return bytes2String(md);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String bytes2String(byte[] bytes) {
        if (bytes == null) {
            return "bytes is null";
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        char str[] = new char[bytes.length * 2];
        for (int i = 0, k = 0; i < bytes.length; i++) {
            byte byte0 = bytes[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }

        return new String(str).toLowerCase();
    }

    public static String decodeKey(String keyCode) {
        if (TextUtils.isEmpty(keyCode)) {
            return "";
        } else {
            String key = keyCode.substring(keyCode.length() - 6);
            String str = keyCode.substring(0, keyCode.length() - 6);
            Decrpy decrpy = new Decrpy(str, key);
            keyCode = decrpy.decrypt();
            return keyCode;
        }
    }

    public static String sortParam(String param) {
        if (param == null || "".equals(param))
            return null;

        StringBuilder sb = new StringBuilder();
        String params[] = param.split("&");
        Arrays.sort(params);
        int len = params.length;
        for (int i = 0; i < len; i++) {
            sb.append(params[i]);
            if (i != len - 1)
                sb.append('&');
        }
        return sb.toString();
    }

    public static String genRandomNum(int pwd_len) {
        final int maxNum = 36;
        int i;
        int count = 0;
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }
}
