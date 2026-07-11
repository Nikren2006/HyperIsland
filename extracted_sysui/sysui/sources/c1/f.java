package c1;

import c1.C0230b;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f extends AbstractC0233e {
    public static float b(float f2, float f3) {
        return f2 < f3 ? f3 : f2;
    }

    public static int c(int i2, int i3) {
        return i2 < i3 ? i3 : i2;
    }

    public static long d(long j2, long j3) {
        return j2 < j3 ? j3 : j2;
    }

    public static float e(float f2, float f3) {
        return f2 > f3 ? f3 : f2;
    }

    public static int f(int i2, int i3) {
        return i2 > i3 ? i3 : i2;
    }

    public static long g(long j2, long j3) {
        return j2 > j3 ? j3 : j2;
    }

    public static float h(float f2, float f3, float f4) {
        if (f3 <= f4) {
            return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + f4 + " is less than minimum " + f3 + '.');
    }

    public static int i(int i2, int i3, int i4) {
        if (i3 <= i4) {
            return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i4 + " is less than minimum " + i3 + '.');
    }

    public static C0230b j(int i2, int i3) {
        return C0230b.f1351d.a(i2, i3, -1);
    }

    public static C0230b k(C0230b c0230b, int i2) {
        n.g(c0230b, "<this>");
        AbstractC0233e.a(i2 > 0, Integer.valueOf(i2));
        C0230b.a aVar = C0230b.f1351d;
        int iC = c0230b.c();
        int iD = c0230b.d();
        if (c0230b.e() <= 0) {
            i2 = -i2;
        }
        return aVar.a(iC, iD, i2);
    }

    public static C0232d l(int i2, int i3) {
        return i3 <= Integer.MIN_VALUE ? C0232d.f1359e.a() : new C0232d(i2, i3 - 1);
    }
}
