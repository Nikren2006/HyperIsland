package com.xiaomi.onetrack.d;

import android.util.Base64;
import com.xiaomi.onetrack.util.n;
import com.xiaomi.onetrack.util.q;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3242a = b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3243b = "AES";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3244c = "AES/ECB/PKCS5Padding";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3245d = "AES";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static KeyGenerator f3246e;

    static {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            f3246e = keyGenerator;
            keyGenerator.init(128);
        } catch (Exception e2) {
            q.b(q.a("AES"), "AesUtil e", e2);
        }
    }

    public static byte[] a() {
        return f3246e.generateKey().getEncoded();
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher cipher = Cipher.getInstance(f3244c);
            cipher.init(2, secretKeySpec);
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            q.b("AES", "decrypt exception:", e2);
            q.b("AES", "content len=" + bArr.length + ", passwd len=" + bArr2.length);
            return null;
        }
    }

    private static byte[] c(String str) {
        if (str != null) {
            return str.getBytes();
        }
        return null;
    }

    private static byte[] d(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i2 = 0; i2 < str.length() / 2; i2++) {
            int i3 = i2 * 2;
            int i4 = i3 + 1;
            bArr[i2] = (byte) ((Integer.parseInt(str.substring(i3, i4), 16) * 16) + Integer.parseInt(str.substring(i4, i3 + 2), 16));
        }
        return bArr;
    }

    private static byte[] e(String str, String str2) {
        return a(str.getBytes(), str2.getBytes());
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher cipher = Cipher.getInstance(f3244c);
            cipher.init(1, secretKeySpec);
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            q.b(q.a("AES"), "encrypt exception:", e2);
            return null;
        }
    }

    public static String c(String str, String str2) {
        return new String(a(d(str), str2));
    }

    private static byte[] a(byte[] bArr, String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(c(str), "AES");
            Cipher cipher = Cipher.getInstance(f3244c);
            cipher.init(2, secretKeySpec);
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            q.b(q.a("AES"), "decrypt exception:", e2);
            return null;
        }
    }

    public static String b(String str, String str2) {
        return Base64.encodeToString(e(str, str2), 10);
    }

    public static String d(String str, String str2) {
        return new String(a(Base64.decode(str, 10), str2));
    }

    private static String b() {
        try {
            return new String("6AiSfshj3pD/9r91".getBytes(), "UTF-8");
        } catch (Exception e2) {
            q.b("AES", e2.getMessage());
            return "";
        }
    }

    public static byte[] b(String str) {
        return a(str.getBytes(), d.a(c.a(), true).getBytes());
    }

    public static String a(String str, String str2) {
        return n.a(e(str, str2));
    }

    public static String a(String str) {
        try {
            char[] charArray = (str + b.f3247a).toCharArray();
            for (int i2 = 0; i2 < charArray.length; i2++) {
                for (int i3 = 0; i3 < charArray.length - 1; i3++) {
                    char c2 = charArray[i2];
                    char c3 = charArray[i3];
                    if (c2 < c3) {
                        charArray[i2] = c3;
                        charArray[i3] = c2;
                    }
                }
            }
            return d.h(new String(charArray));
        } catch (Exception unused) {
            q.b("AES", "encodeFromSalt ");
            return "";
        }
    }

    public static String a(byte[] bArr) {
        return new String(b(bArr, d.a(c.a(), true).getBytes()));
    }
}
