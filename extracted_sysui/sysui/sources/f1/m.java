package f1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class m extends l {
    public static Integer f(String str) {
        kotlin.jvm.internal.n.g(str, "<this>");
        return g(str, 10);
    }

    public static final Integer g(String str, int i2) {
        boolean z2;
        int i3;
        int i4;
        kotlin.jvm.internal.n.g(str, "<this>");
        a.a(i2);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i5 = 0;
        char cCharAt = str.charAt(0);
        int i6 = -2147483647;
        if (kotlin.jvm.internal.n.i(cCharAt, 48) < 0) {
            i3 = 1;
            if (length == 1) {
                return null;
            }
            if (cCharAt == '-') {
                i6 = Integer.MIN_VALUE;
                z2 = true;
            } else {
                if (cCharAt != '+') {
                    return null;
                }
                z2 = false;
            }
        } else {
            z2 = false;
            i3 = 0;
        }
        int i7 = -59652323;
        while (i3 < length) {
            int iB = a.b(str.charAt(i3), i2);
            if (iB < 0) {
                return null;
            }
            if ((i5 < i7 && (i7 != -59652323 || i5 < (i7 = i6 / i2))) || (i4 = i5 * i2) < i6 + iB) {
                return null;
            }
            i5 = i4 - iB;
            i3++;
        }
        return z2 ? Integer.valueOf(i5) : Integer.valueOf(-i5);
    }

    public static Long h(String str) {
        kotlin.jvm.internal.n.g(str, "<this>");
        return i(str, 10);
    }

    public static final Long i(String str, int i2) {
        boolean z2;
        kotlin.jvm.internal.n.g(str, "<this>");
        a.a(i2);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i3 = 0;
        char cCharAt = str.charAt(0);
        long j2 = -9223372036854775807L;
        if (kotlin.jvm.internal.n.i(cCharAt, 48) < 0) {
            z2 = true;
            if (length == 1) {
                return null;
            }
            if (cCharAt == '-') {
                j2 = Long.MIN_VALUE;
                i3 = 1;
            } else {
                if (cCharAt != '+') {
                    return null;
                }
                z2 = false;
                i3 = 1;
            }
        } else {
            z2 = false;
        }
        long j3 = -256204778801521550L;
        long j4 = 0;
        long j5 = -256204778801521550L;
        while (i3 < length) {
            int iB = a.b(str.charAt(i3), i2);
            if (iB < 0) {
                return null;
            }
            if (j4 < j5) {
                if (j5 == j3) {
                    j5 = j2 / ((long) i2);
                    if (j4 < j5) {
                    }
                }
                return null;
            }
            long j6 = j4 * ((long) i2);
            long j7 = iB;
            if (j6 < j2 + j7) {
                return null;
            }
            j4 = j6 - j7;
            i3++;
            j3 = -256204778801521550L;
        }
        return z2 ? Long.valueOf(j4) : Long.valueOf(-j4);
    }
}
