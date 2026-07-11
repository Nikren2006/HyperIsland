package com.xiaomi.onetrack.d;

import com.xiaomi.onetrack.util.q;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3258a = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiH0r18h2G+lOzZz0mSZT9liZY\r6ibWUv/biAioduf0zuRbWUYGb3pHobyCOaw2LpVnlf8CeCYtbRJhxL9skOyoU1Qa\rwGtoJzvVR4GbCo1MBTmZ8XThMprr0unRfzsu9GNV4+twciOdS2cNJB7INcwAYBFQ\r9vKpgXFoEjWRhIgwMwIDAQAB\r";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3259b = "RsaUtils";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3260c = "RSA/ECB/PKCS1Padding";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3261d = "BC";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3262e = "RSA";

    public static byte[] a(byte[] bArr) {
        try {
            RSAPublicKey rSAPublicKeyA = a(f3258a);
            Cipher cipher = Cipher.getInstance(f3260c, f3261d);
            cipher.init(1, rSAPublicKeyA);
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            q.b(q.a(f3259b), "RsaUtils encrypt exception:", e2);
            return null;
        }
    }

    private static RSAPublicKey b(byte[] bArr) {
        return (RSAPublicKey) KeyFactory.getInstance(f3262e).generatePublic(new X509EncodedKeySpec(bArr));
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            RSAPublicKey rSAPublicKeyB = b(bArr);
            Cipher cipher = Cipher.getInstance(f3260c);
            cipher.init(1, rSAPublicKeyB);
            return cipher.doFinal(bArr2);
        } catch (Exception e2) {
            q.b(f3259b, "RsaUtil encrypt exception:", e2);
            return null;
        }
    }

    private static RSAPublicKey a(String str) {
        return (RSAPublicKey) KeyFactory.getInstance(f3262e).generatePublic(new X509EncodedKeySpec(c.a(str)));
    }
}
