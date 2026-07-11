package com.airbnb.lottie.value;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import d.C0307h;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0307h f1392a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1393b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f1394c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Interpolator f1395d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Interpolator f1396e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Interpolator f1397f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f1398g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Float f1399h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f1400i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f1401j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1402k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1403l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f1404m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f1405n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public PointF f1406o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public PointF f1407p;

    public a(C0307h c0307h, Object obj, Object obj2, Interpolator interpolator, float f2, Float f3) {
        this.f1400i = -3987645.8f;
        this.f1401j = -3987645.8f;
        this.f1402k = 784923401;
        this.f1403l = 784923401;
        this.f1404m = Float.MIN_VALUE;
        this.f1405n = Float.MIN_VALUE;
        this.f1406o = null;
        this.f1407p = null;
        this.f1392a = c0307h;
        this.f1393b = obj;
        this.f1394c = obj2;
        this.f1395d = interpolator;
        this.f1396e = null;
        this.f1397f = null;
        this.f1398g = f2;
        this.f1399h = f3;
    }

    public boolean a(float f2) {
        return f2 >= e() && f2 < b();
    }

    public float b() {
        if (this.f1392a == null) {
            return 1.0f;
        }
        if (this.f1405n == Float.MIN_VALUE) {
            if (this.f1399h == null) {
                this.f1405n = 1.0f;
            } else {
                this.f1405n = e() + ((this.f1399h.floatValue() - this.f1398g) / this.f1392a.e());
            }
        }
        return this.f1405n;
    }

    public float c() {
        if (this.f1401j == -3987645.8f) {
            this.f1401j = ((Float) this.f1394c).floatValue();
        }
        return this.f1401j;
    }

    public int d() {
        if (this.f1403l == 784923401) {
            this.f1403l = ((Integer) this.f1394c).intValue();
        }
        return this.f1403l;
    }

    public float e() {
        C0307h c0307h = this.f1392a;
        if (c0307h == null) {
            return 0.0f;
        }
        if (this.f1404m == Float.MIN_VALUE) {
            this.f1404m = (this.f1398g - c0307h.p()) / this.f1392a.e();
        }
        return this.f1404m;
    }

    public float f() {
        if (this.f1400i == -3987645.8f) {
            this.f1400i = ((Float) this.f1393b).floatValue();
        }
        return this.f1400i;
    }

    public int g() {
        if (this.f1402k == 784923401) {
            this.f1402k = ((Integer) this.f1393b).intValue();
        }
        return this.f1402k;
    }

    public boolean h() {
        return this.f1395d == null && this.f1396e == null && this.f1397f == null;
    }

    public String toString() {
        return "Keyframe{startValue=" + this.f1393b + ", endValue=" + this.f1394c + ", startFrame=" + this.f1398g + ", endFrame=" + this.f1399h + ", interpolator=" + this.f1395d + '}';
    }

    public a(C0307h c0307h, Object obj, Object obj2, Interpolator interpolator, Interpolator interpolator2, float f2, Float f3) {
        this.f1400i = -3987645.8f;
        this.f1401j = -3987645.8f;
        this.f1402k = 784923401;
        this.f1403l = 784923401;
        this.f1404m = Float.MIN_VALUE;
        this.f1405n = Float.MIN_VALUE;
        this.f1406o = null;
        this.f1407p = null;
        this.f1392a = c0307h;
        this.f1393b = obj;
        this.f1394c = obj2;
        this.f1395d = null;
        this.f1396e = interpolator;
        this.f1397f = interpolator2;
        this.f1398g = f2;
        this.f1399h = f3;
    }

    public a(C0307h c0307h, Object obj, Object obj2, Interpolator interpolator, Interpolator interpolator2, Interpolator interpolator3, float f2, Float f3) {
        this.f1400i = -3987645.8f;
        this.f1401j = -3987645.8f;
        this.f1402k = 784923401;
        this.f1403l = 784923401;
        this.f1404m = Float.MIN_VALUE;
        this.f1405n = Float.MIN_VALUE;
        this.f1406o = null;
        this.f1407p = null;
        this.f1392a = c0307h;
        this.f1393b = obj;
        this.f1394c = obj2;
        this.f1395d = interpolator;
        this.f1396e = interpolator2;
        this.f1397f = interpolator3;
        this.f1398g = f2;
        this.f1399h = f3;
    }

    public a(Object obj) {
        this.f1400i = -3987645.8f;
        this.f1401j = -3987645.8f;
        this.f1402k = 784923401;
        this.f1403l = 784923401;
        this.f1404m = Float.MIN_VALUE;
        this.f1405n = Float.MIN_VALUE;
        this.f1406o = null;
        this.f1407p = null;
        this.f1392a = null;
        this.f1393b = obj;
        this.f1394c = obj;
        this.f1395d = null;
        this.f1396e = null;
        this.f1397f = null;
        this.f1398g = Float.MIN_VALUE;
        this.f1399h = Float.valueOf(Float.MAX_VALUE);
    }
}
