package com.android.systemui;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import miuix.security.DigestUtils;

/* JADX INFO: loaded from: classes.dex */
public class HashUtils {
    public static String MD5(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(DigestUtils.ALGORITHM_MD5);
            byte[] bytes = str.getBytes("iso-8859-1");
            messageDigest.update(bytes, 0, bytes.length);
            return convertToHex(messageDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String SHA1(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(DigestUtils.ALGORITHM_SHA_1);
            byte[] bytes = str.getBytes("iso-8859-1");
            messageDigest.update(bytes, 0, bytes.length);
            return convertToHex(messageDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String convertToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            int i2 = (b2 >>> 4) & 15;
            int i3 = 0;
            while (true) {
                sb.append((char) ((i2 < 0 || i2 > 9) ? i2 + 87 : i2 + 48));
                i2 = b2 & 15;
                int i4 = i3 + 1;
                if (i3 >= 1) {
                    break;
                }
                i3 = i4;
            }
        }
        return sb.toString();
    }
}
