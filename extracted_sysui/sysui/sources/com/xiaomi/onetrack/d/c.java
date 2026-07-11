package com.xiaomi.onetrack.d;

import com.xiaomi.onetrack.util.q;
import java.io.UnsupportedEncodingException;
import miuix.animation.internal.TransitionInfo;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3252a = "Base64Util";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static char[] f3253b = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static byte[] f3254c = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            int i3 = i2 + 1;
            byte b2 = bArr[i2];
            int i4 = b2 & TransitionInfo.INIT;
            if (i3 == length) {
                stringBuffer.append(f3253b[i4 >>> 2]);
                stringBuffer.append(f3253b[(b2 & 3) << 4]);
                stringBuffer.append("==");
                break;
            }
            int i5 = i2 + 2;
            byte b3 = bArr[i3];
            if (i5 == length) {
                stringBuffer.append(f3253b[i4 >>> 2]);
                stringBuffer.append(f3253b[((b2 & 3) << 4) | ((b3 & 240) >>> 4)]);
                stringBuffer.append(f3253b[(b3 & 15) << 2]);
                stringBuffer.append("=");
                break;
            }
            i2 += 3;
            byte b4 = bArr[i5];
            stringBuffer.append(f3253b[i4 >>> 2]);
            stringBuffer.append(f3253b[((b2 & 3) << 4) | ((b3 & 240) >>> 4)]);
            stringBuffer.append(f3253b[((b3 & 15) << 2) | ((b4 & 192) >>> 6)]);
            stringBuffer.append(f3253b[b4 & 63]);
        }
        return stringBuffer.toString();
    }

    private static byte[] b(String str) throws UnsupportedEncodingException {
        int i2;
        byte b2;
        int i3;
        byte b3;
        int i4;
        byte b4;
        int i5;
        byte b5;
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bytes = str.getBytes("US-ASCII");
        int length = bytes.length;
        int i6 = 0;
        while (i6 < length) {
            while (true) {
                i2 = i6 + 1;
                b2 = f3254c[bytes[i6]];
                if (i2 >= length || b2 != -1) {
                    break;
                }
                i6 = i2;
            }
            if (b2 == -1) {
                break;
            }
            while (true) {
                i3 = i2 + 1;
                b3 = f3254c[bytes[i2]];
                if (i3 >= length || b3 != -1) {
                    break;
                }
                i2 = i3;
            }
            if (b3 == -1) {
                break;
            }
            stringBuffer.append((char) ((b2 << 2) | ((b3 & 48) >>> 4)));
            while (true) {
                i4 = i3 + 1;
                byte b6 = bytes[i3];
                if (b6 == 61) {
                    return stringBuffer.toString().getBytes("iso8859-1");
                }
                b4 = f3254c[b6];
                if (i4 >= length || b4 != -1) {
                    break;
                }
                i3 = i4;
            }
            if (b4 == -1) {
                break;
            }
            stringBuffer.append((char) (((b3 & 15) << 4) | ((b4 & 60) >>> 2)));
            while (true) {
                i5 = i4 + 1;
                byte b7 = bytes[i4];
                if (b7 == 61) {
                    return stringBuffer.toString().getBytes("iso8859-1");
                }
                b5 = f3254c[b7];
                if (i5 >= length || b5 != -1) {
                    break;
                }
                i4 = i5;
            }
            if (b5 == -1) {
                break;
            }
            stringBuffer.append((char) (b5 | ((b4 & 3) << 6)));
            i6 = i5;
        }
        return stringBuffer.toString().getBytes("iso8859-1");
    }

    public static byte[] a(String str) {
        try {
            return b(str);
        } catch (UnsupportedEncodingException e2) {
            q.b(q.a(f3252a), "decode e", e2);
            return new byte[0];
        }
    }

    public static byte[] a() {
        return new byte[]{84, 123, 100, 101, 118, 33, 80, 100, 101, 116, 116, 37, 94, 52, 77, 73};
    }
}
