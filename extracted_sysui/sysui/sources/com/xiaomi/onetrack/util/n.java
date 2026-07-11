package com.xiaomi.onetrack.util;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import miuix.animation.internal.TransitionInfo;

/* JADX INFO: loaded from: classes2.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3525a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3526b = "onetrack";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f3527c = 4096;

    public static void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e2) {
                Log.e("onetrack", "close e", e2);
            }
        }
    }

    public static byte[] b(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void a(InputStream inputStream) {
        a((Closeable) inputStream);
    }

    public static void a(OutputStream outputStream) {
        a((Closeable) outputStream);
    }

    public static void b(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e2) {
                Log.e("onetrack", "closeSafely e", e2);
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e2) {
                Log.e("onetrack", "closeQuietly e", e2);
            }
        }
    }

    public static long a(InputStream inputStream, OutputStream outputStream) {
        return a(inputStream, outputStream, 4096);
    }

    public static long a(InputStream inputStream, OutputStream outputStream, int i2) throws IOException {
        byte[] bArr = new byte[i2];
        long j2 = 0;
        while (true) {
            int i3 = inputStream.read(bArr);
            if (-1 == i3) {
                return j2;
            }
            outputStream.write(bArr, 0, i3);
            j2 += (long) i3;
        }
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & TransitionInfo.INIT);
            while (hexString.length() < 2) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}
