package i;

import android.graphics.PointF;

/* JADX INFO: renamed from: i.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0402a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final PointF f4495a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final PointF f4496b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final PointF f4497c;

    public C0402a() {
        this.f4495a = new PointF();
        this.f4496b = new PointF();
        this.f4497c = new PointF();
    }

    public PointF a() {
        return this.f4495a;
    }

    public PointF b() {
        return this.f4496b;
    }

    public PointF c() {
        return this.f4497c;
    }

    public void d(float f2, float f3) {
        this.f4495a.set(f2, f3);
    }

    public void e(float f2, float f3) {
        this.f4496b.set(f2, f3);
    }

    public void f(float f2, float f3) {
        this.f4497c.set(f2, f3);
    }

    public String toString() {
        return String.format("v=%.2f,%.2f cp1=%.2f,%.2f cp2=%.2f,%.2f", Float.valueOf(this.f4497c.x), Float.valueOf(this.f4497c.y), Float.valueOf(this.f4495a.x), Float.valueOf(this.f4495a.y), Float.valueOf(this.f4496b.x), Float.valueOf(this.f4496b.y));
    }

    public C0402a(PointF pointF, PointF pointF2, PointF pointF3) {
        this.f4495a = pointF;
        this.f4496b = pointF2;
        this.f4497c = pointF3;
    }
}
