package k0;

import android.text.TextUtils;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap f4947a = new HashMap();

    public static String a(String str) {
        if ((AbstractC0426a.c() && AbstractC0426a.a()) || TextUtils.isEmpty(str)) {
            return str;
        }
        HashMap map = f4947a;
        if (map.containsKey(str)) {
            return (String) map.get(str);
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String str2 = "#enc_cnt=" + b(str, String.valueOf(jCurrentTimeMillis)) + "#enc_tag=" + jCurrentTimeMillis + "#enc_end";
            map.put(str, str2);
            return str2;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(String str, String str2) {
        byte[] bytes = str.getBytes();
        byte[] bytes2 = str2.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i2 = 0; i2 < bytes.length; i2++) {
            int length = (bytes2.length - (i2 % bytes2.length)) - 1;
            sb.append("0a1b2c34567d8e9f".charAt(((bytes[i2] + bytes2[length]) & 240) >> 4));
            sb.append("0a1b2c34567d8e9f".charAt((bytes[i2] + bytes2[length]) & 15));
        }
        return sb.toString();
    }
}
