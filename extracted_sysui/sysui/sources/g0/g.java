package g0;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f4354a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f4355b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f4356c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f4357d;

    public g(float f2, float f3, float f4, float f5) {
        this.f4354a = f2;
        this.f4355b = f3;
        this.f4356c = f4;
        this.f4357d = f5;
    }

    public final float a() {
        return this.f4355b;
    }

    public final float b() {
        return this.f4354a;
    }

    public final void c(float f2) {
        this.f4355b = f2;
    }

    public final void d(float f2) {
        this.f4356c = f2;
    }

    public final void e(float f2) {
        this.f4357d = f2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return Float.compare(this.f4354a, gVar.f4354a) == 0 && Float.compare(this.f4355b, gVar.f4355b) == 0 && Float.compare(this.f4356c, gVar.f4356c) == 0 && Float.compare(this.f4357d, gVar.f4357d) == 0;
    }

    public final void f(float f2) {
        this.f4354a = f2;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(float r10) {
        /*
            r9 = this;
            float r0 = r9.f4354a
            float r1 = r9.f4357d
            r2 = 1065353216(0x3f800000, float:1.0)
            float r2 = r2 - r1
            float r2 = r2 * r0
            float r10 = r10 * r1
            float r2 = r2 + r10
            float r2 = r2 - r0
            double r3 = (double) r2
            r5 = 0
            int r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r10 != 0) goto L13
            goto L3f
        L13:
            float r10 = r9.f4355b
            float r1 = r9.f4356c
            float r7 = r2 - r10
            int r8 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r8 > 0) goto L26
            float r8 = -r1
            int r8 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r8 > 0) goto L24
            float r10 = r10 + r7
            goto L27
        L24:
            float r10 = r10 - r1
            goto L27
        L26:
            float r10 = r10 + r1
        L27:
            r9.f4355b = r10
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L34
            int r1 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r1 >= 0) goto L3b
            r9.f4355b = r2
            goto L3c
        L34:
            int r1 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r1 >= 0) goto L3b
            r9.f4355b = r2
            goto L3c
        L3b:
            r2 = r10
        L3c:
            float r0 = r0 + r2
            r9.f4354a = r0
        L3f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: g0.g.g(float):void");
    }

    public int hashCode() {
        return (((((Float.hashCode(this.f4354a) * 31) + Float.hashCode(this.f4355b)) * 31) + Float.hashCode(this.f4356c)) * 31) + Float.hashCode(this.f4357d);
    }

    public String toString() {
        return "Spring(value=" + this.f4354a + ", curVelocity=" + this.f4355b + ", maxAcceleration=" + this.f4356c + ", springAmount=" + this.f4357d + ")";
    }

    public /* synthetic */ g(float f2, float f3, float f4, float f5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0.0f : f2, (i2 & 2) != 0 ? 0.0f : f3, (i2 & 4) != 0 ? 0.0f : f4, (i2 & 8) != 0 ? 0.0f : f5);
    }
}
