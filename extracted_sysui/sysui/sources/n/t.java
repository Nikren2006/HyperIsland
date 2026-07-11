package n;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.miui.maml.folme.AnimatedProperty;
import d.C0307h;
import java.lang.ref.WeakReference;
import o.AbstractC0715c;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public abstract class t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static SparseArrayCompat f6222b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Interpolator f6221a = new LinearInterpolator();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static AbstractC0715c.a f6223c = AbstractC0715c.a.a("t", "s", "e", "o", "i", AnimatedProperty.PROPERTY_NAME_H, "to", "ti");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static AbstractC0715c.a f6224d = AbstractC0715c.a.a(AnimatedProperty.PROPERTY_NAME_X, AnimatedProperty.PROPERTY_NAME_Y);

    public static WeakReference a(int i2) {
        WeakReference weakReference;
        synchronized (t.class) {
            weakReference = (WeakReference) g().get(i2);
        }
        return weakReference;
    }

    public static Interpolator b(PointF pointF, PointF pointF2) {
        Interpolator interpolatorCreate;
        pointF.x = AbstractC0727g.b(pointF.x, -1.0f, 1.0f);
        pointF.y = AbstractC0727g.b(pointF.y, -100.0f, 100.0f);
        pointF2.x = AbstractC0727g.b(pointF2.x, -1.0f, 1.0f);
        float fB = AbstractC0727g.b(pointF2.y, -100.0f, 100.0f);
        pointF2.y = fB;
        int i2 = p.h.i(pointF.x, pointF.y, pointF2.x, fB);
        WeakReference weakReferenceA = a(i2);
        Interpolator interpolator = weakReferenceA != null ? (Interpolator) weakReferenceA.get() : null;
        if (weakReferenceA == null || interpolator == null) {
            try {
                interpolatorCreate = PathInterpolatorCompat.create(pointF.x, pointF.y, pointF2.x, pointF2.y);
            } catch (IllegalArgumentException e2) {
                interpolatorCreate = "The Path cannot loop back on itself.".equals(e2.getMessage()) ? PathInterpolatorCompat.create(Math.min(pointF.x, 1.0f), pointF.y, Math.max(pointF2.x, 0.0f), pointF2.y) : new LinearInterpolator();
            }
            interpolator = interpolatorCreate;
            try {
                h(i2, new WeakReference(interpolator));
            } catch (ArrayIndexOutOfBoundsException unused) {
            }
        }
        return interpolator;
    }

    public static com.airbnb.lottie.value.a c(AbstractC0715c abstractC0715c, C0307h c0307h, float f2, N n2, boolean z2, boolean z3) {
        return (z2 && z3) ? e(c0307h, abstractC0715c, f2, n2) : z2 ? d(c0307h, abstractC0715c, f2, n2) : f(abstractC0715c, f2, n2);
    }

    public static com.airbnb.lottie.value.a d(C0307h c0307h, AbstractC0715c abstractC0715c, float f2, N n2) {
        Interpolator interpolatorB;
        Object obj;
        abstractC0715c.d();
        PointF pointFE = null;
        Object objA = null;
        Object objA2 = null;
        PointF pointFE2 = null;
        PointF pointFE3 = null;
        float fT = 0.0f;
        boolean z2 = false;
        PointF pointFE4 = null;
        while (abstractC0715c.n()) {
            switch (abstractC0715c.C(f6223c)) {
                case 0:
                    fT = (float) abstractC0715c.t();
                    break;
                case 1:
                    objA2 = n2.a(abstractC0715c, f2);
                    break;
                case 2:
                    objA = n2.a(abstractC0715c, f2);
                    break;
                case 3:
                    pointFE = s.e(abstractC0715c, 1.0f);
                    break;
                case 4:
                    pointFE4 = s.e(abstractC0715c, 1.0f);
                    break;
                case 5:
                    z2 = abstractC0715c.u() == 1;
                    break;
                case 6:
                    pointFE2 = s.e(abstractC0715c, f2);
                    break;
                case 7:
                    pointFE3 = s.e(abstractC0715c, f2);
                    break;
                default:
                    abstractC0715c.E();
                    break;
            }
        }
        abstractC0715c.f();
        if (z2) {
            interpolatorB = f6221a;
            obj = objA2;
        } else {
            interpolatorB = (pointFE == null || pointFE4 == null) ? f6221a : b(pointFE, pointFE4);
            obj = objA;
        }
        com.airbnb.lottie.value.a aVar = new com.airbnb.lottie.value.a(c0307h, objA2, obj, interpolatorB, fT, null);
        aVar.f1406o = pointFE2;
        aVar.f1407p = pointFE3;
        return aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:93:0x01ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.airbnb.lottie.value.a e(d.C0307h r21, o.AbstractC0715c r22, float r23, n.N r24) {
        /*
            Method dump skipped, instruction units count: 532
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: n.t.e(d.h, o.c, float, n.N):com.airbnb.lottie.value.a");
    }

    public static com.airbnb.lottie.value.a f(AbstractC0715c abstractC0715c, float f2, N n2) {
        return new com.airbnb.lottie.value.a(n2.a(abstractC0715c, f2));
    }

    public static SparseArrayCompat g() {
        if (f6222b == null) {
            f6222b = new SparseArrayCompat();
        }
        return f6222b;
    }

    public static void h(int i2, WeakReference weakReference) {
        synchronized (t.class) {
            f6222b.put(i2, weakReference);
        }
    }
}
