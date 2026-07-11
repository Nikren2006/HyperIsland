package l1;

import androidx.core.location.LocationRequestCompat;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class I {
    public static final int a(String str, int i2, int i3, int i4) {
        return (int) G.c(str, i2, i3, i4);
    }

    public static final long b(String str, long j2, long j3, long j4) {
        String strD = G.d(str);
        if (strD == null) {
            return j2;
        }
        Long lH = f1.m.h(strD);
        if (lH == null) {
            throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + strD + '\'').toString());
        }
        long jLongValue = lH.longValue();
        if (j3 <= jLongValue && jLongValue <= j4) {
            return jLongValue;
        }
        throw new IllegalStateException(("System property '" + str + "' should be in range " + j3 + ".." + j4 + ", but is '" + jLongValue + '\'').toString());
    }

    public static final String c(String str, String str2) {
        String strD = G.d(str);
        return strD == null ? str2 : strD;
    }

    public static final boolean d(String str, boolean z2) {
        String strD = G.d(str);
        return strD != null ? Boolean.parseBoolean(strD) : z2;
    }

    public static /* synthetic */ int e(String str, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            i3 = 1;
        }
        if ((i5 & 8) != 0) {
            i4 = Integer.MAX_VALUE;
        }
        return G.b(str, i2, i3, i4);
    }

    public static /* synthetic */ long f(String str, long j2, long j3, long j4, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            j3 = 1;
        }
        long j5 = j3;
        if ((i2 & 8) != 0) {
            j4 = LocationRequestCompat.PASSIVE_INTERVAL;
        }
        return G.c(str, j2, j5, j4);
    }
}
