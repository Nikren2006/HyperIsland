package com.xiaomi.onetrack.d;

import android.annotation.TargetApi;
import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import android.util.Base64;
import com.xiaomi.onetrack.util.q;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.GregorianCalendar;
import javax.crypto.Cipher;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3247a = b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3248b = "AndroidKeyStore";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3249c = "AndroidKeyStore";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3250d = "RSA/ECB/PKCS1Padding";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3251e = "RSA_KEY";

    public static synchronized String a(Context context, String str) {
        Cipher cipher = Cipher.getInstance(f3250d);
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        a(context, keyStore);
        Certificate certificate = keyStore.getCertificate(f3251e);
        if (certificate == null) {
            return null;
        }
        cipher.init(1, certificate.getPublicKey());
        return Base64.encodeToString(cipher.doFinal(str.getBytes("UTF-8")), 0);
    }

    public static synchronized String b(Context context, String str) {
        Cipher cipher;
        cipher = Cipher.getInstance(f3250d);
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        a(context, keyStore);
        cipher.init(2, (PrivateKey) keyStore.getKey(f3251e, null));
        return new String(cipher.doFinal(Base64.decode(str, 0)), "UTF-8");
    }

    private static String b() {
        try {
            return new String("h*Y31]c!@5Ns_jPsvej!@3[(*w(!l".getBytes(), "UTF-8");
        } catch (Exception e2) {
            q.b("AndroidKeyStore", e2.getMessage());
            return "";
        }
    }

    private static void a(Context context, KeyStore keyStore) {
        try {
            if (keyStore.containsAlias(f3251e)) {
                return;
            }
            a();
        } catch (Throwable th) {
            q.b("AndroidKeyStore", "createKey e", th);
        }
    }

    @TargetApi(18)
    private static void a(Context context) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
        gregorianCalendar2.add(1, 1);
        KeyPairGeneratorSpec keyPairGeneratorSpecBuild = new KeyPairGeneratorSpec.Builder(context).setAlias(f3251e).setSubject(new X500Principal("CN=RSA_KEY")).setSerialNumber(BigInteger.valueOf(1337L)).setStartDate(gregorianCalendar.getTime()).setEndDate(gregorianCalendar2.getTime()).build();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
        keyPairGenerator.initialize(keyPairGeneratorSpecBuild);
        keyPairGenerator.generateKeyPair();
    }

    private static void a() throws IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Class<?> cls = Class.forName("android.security.keystore.KeyGenParameterSpec$Builder");
        Constructor<?> constructor = cls.getConstructor(String.class, Integer.TYPE);
        Class<?> cls2 = Class.forName("android.security.keystore.KeyProperties");
        Object objNewInstance = constructor.newInstance(f3251e, Integer.valueOf(cls2.getDeclaredField("PURPOSE_ENCRYPT").getInt(null) | cls2.getDeclaredField("PURPOSE_DECRYPT").getInt(null)));
        cls.getMethod("setDigests", String[].class).invoke(objNewInstance, new String[]{(String) cls2.getDeclaredField("DIGEST_SHA256").get(null), (String) cls2.getDeclaredField("DIGEST_SHA512").get(null)});
        cls.getMethod("setEncryptionPaddings", String[].class).invoke(objNewInstance, new String[]{(String) cls2.getDeclaredField("ENCRYPTION_PADDING_RSA_PKCS1").get(null)});
        Object objInvoke = cls.getMethod("build", null).invoke(objNewInstance, null);
        Class<?> cls3 = Class.forName("java.security.KeyPairGenerator");
        KeyPairGenerator keyPairGenerator = (KeyPairGenerator) cls3.getMethod("getInstance", String.class, String.class).invoke(null, "RSA", "AndroidKeyStore");
        cls3.getMethod("initialize", AlgorithmParameterSpec.class).invoke(keyPairGenerator, objInvoke);
        keyPairGenerator.generateKeyPair();
    }
}
