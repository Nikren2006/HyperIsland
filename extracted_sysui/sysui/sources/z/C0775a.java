package z;

import androidx.core.math.MathUtils;

/* JADX INFO: renamed from: z.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0775a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7110a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f7111b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f7112c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f7113d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f7114e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f7115f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f7116g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f7117h;

    public C0775a(int i2, float f2, float f3, float f4, int i3, float f5, int i4, float f6, int i5, float f7) {
        this.f7110a = i2;
        this.f7111b = MathUtils.clamp(f2, f3, f4);
        this.f7112c = i3;
        this.f7114e = f5;
        this.f7113d = i4;
        this.f7115f = f6;
        this.f7116g = i5;
        d(f7, f3, f4, f6);
        this.f7117h = b(f6);
    }

    public static C0775a c(float f2, float f3, float f4, float f5, int[] iArr, float f6, int[] iArr2, float f7, int[] iArr3) {
        C0775a c0775a = null;
        int i2 = 1;
        for (int i3 : iArr3) {
            int length = iArr2.length;
            int i4 = 0;
            while (i4 < length) {
                int i5 = iArr2[i4];
                int length2 = iArr.length;
                int i6 = 0;
                while (i6 < length2) {
                    int i7 = i6;
                    int i8 = length2;
                    int i9 = i4;
                    int i10 = length;
                    C0775a c0775a2 = new C0775a(i2, f3, f4, f5, iArr[i6], f6, i5, f7, i3, f2);
                    if (c0775a == null || c0775a2.f7117h < c0775a.f7117h) {
                        if (c0775a2.f7117h == 0.0f) {
                            return c0775a2;
                        }
                        c0775a = c0775a2;
                    }
                    i2++;
                    i6 = i7 + 1;
                    length2 = i8;
                    i4 = i9;
                    length = i10;
                }
                i4++;
            }
        }
        return c0775a;
    }

    public final float a(float f2, int i2, float f3, int i3, int i4) {
        if (i2 <= 0) {
            f3 = 0.0f;
        }
        float f4 = i2;
        float f5 = i3 / 2.0f;
        return (f2 - ((f4 + f5) * f3)) / (i4 + f5);
    }

    public final float b(float f2) {
        if (g()) {
            return Math.abs(f2 - this.f7115f) * this.f7110a;
        }
        return Float.MAX_VALUE;
    }

    public final void d(float f2, float f3, float f4, float f5) {
        float f6 = f2 - f();
        int i2 = this.f7112c;
        if (i2 > 0 && f6 > 0.0f) {
            float f7 = this.f7111b;
            this.f7111b = f7 + Math.min(f6 / i2, f4 - f7);
        } else if (i2 > 0 && f6 < 0.0f) {
            float f8 = this.f7111b;
            this.f7111b = f8 + Math.max(f6 / i2, f3 - f8);
        }
        int i3 = this.f7112c;
        float f9 = i3 > 0 ? this.f7111b : 0.0f;
        this.f7111b = f9;
        float fA = a(f2, i3, f9, this.f7113d, this.f7116g);
        this.f7115f = fA;
        float f10 = (this.f7111b + fA) / 2.0f;
        this.f7114e = f10;
        int i4 = this.f7113d;
        if (i4 <= 0 || fA == f5) {
            return;
        }
        float f11 = (f5 - fA) * this.f7116g;
        float fMin = Math.min(Math.abs(f11), f10 * 0.1f * i4);
        if (f11 > 0.0f) {
            this.f7114e -= fMin / this.f7113d;
            this.f7115f += fMin / this.f7116g;
        } else {
            this.f7114e += fMin / this.f7113d;
            this.f7115f -= fMin / this.f7116g;
        }
    }

    public int e() {
        return this.f7112c + this.f7113d + this.f7116g;
    }

    public final float f() {
        return (this.f7115f * this.f7116g) + (this.f7114e * this.f7113d) + (this.f7111b * this.f7112c);
    }

    public final boolean g() {
        int i2 = this.f7116g;
        if (i2 <= 0 || this.f7112c <= 0 || this.f7113d <= 0) {
            return i2 <= 0 || this.f7112c <= 0 || this.f7115f > this.f7111b;
        }
        float f2 = this.f7115f;
        float f3 = this.f7114e;
        return f2 > f3 && f3 > this.f7111b;
    }

    public String toString() {
        return "Arrangement [priority=" + this.f7110a + ", smallCount=" + this.f7112c + ", smallSize=" + this.f7111b + ", mediumCount=" + this.f7113d + ", mediumSize=" + this.f7114e + ", largeCount=" + this.f7116g + ", largeSize=" + this.f7115f + ", cost=" + this.f7117h + "]";
    }
}
