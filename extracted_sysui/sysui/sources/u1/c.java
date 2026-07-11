package u1;

import I0.AbstractC0181i;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f6857e = new a(null);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long[] f6858f = new long[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1.c f6859a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Function2 f6860b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f6861c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long[] f6862d;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    public c(s1.c descriptor, Function2 readIfAbsent) {
        kotlin.jvm.internal.n.g(descriptor, "descriptor");
        kotlin.jvm.internal.n.g(readIfAbsent, "readIfAbsent");
        this.f6859a = descriptor;
        this.f6860b = readIfAbsent;
        int iD = descriptor.d();
        if (iD <= 64) {
            this.f6861c = iD != 64 ? (-1) << iD : 0L;
            this.f6862d = f6858f;
        } else {
            this.f6861c = 0L;
            this.f6862d = e(iD);
        }
    }

    public final void a(int i2) {
        if (i2 < 64) {
            this.f6861c |= 1 << i2;
        } else {
            b(i2);
        }
    }

    public final void b(int i2) {
        int i3 = (i2 >>> 6) - 1;
        long[] jArr = this.f6862d;
        jArr[i3] = jArr[i3] | (1 << (i2 & 63));
    }

    public final int c() {
        int length = this.f6862d.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            int i4 = i3 * 64;
            long j2 = this.f6862d[i2];
            while (j2 != -1) {
                int iNumberOfTrailingZeros = Long.numberOfTrailingZeros(~j2);
                j2 |= 1 << iNumberOfTrailingZeros;
                int i5 = iNumberOfTrailingZeros + i4;
                if (((Boolean) this.f6860b.invoke(this.f6859a, Integer.valueOf(i5))).booleanValue()) {
                    this.f6862d[i2] = j2;
                    return i5;
                }
            }
            this.f6862d[i2] = j2;
            i2 = i3;
        }
        return -1;
    }

    public final int d() {
        int iNumberOfTrailingZeros;
        int iD = this.f6859a.d();
        do {
            long j2 = this.f6861c;
            if (j2 == -1) {
                if (iD > 64) {
                    return c();
                }
                return -1;
            }
            iNumberOfTrailingZeros = Long.numberOfTrailingZeros(~j2);
            this.f6861c |= 1 << iNumberOfTrailingZeros;
        } while (!((Boolean) this.f6860b.invoke(this.f6859a, Integer.valueOf(iNumberOfTrailingZeros))).booleanValue());
        return iNumberOfTrailingZeros;
    }

    public final long[] e(int i2) {
        long[] jArr = new long[(i2 - 1) >>> 6];
        if ((i2 & 63) != 0) {
            jArr[AbstractC0181i.A(jArr)] = (-1) << i2;
        }
        return jArr;
    }
}
