package p;

import android.graphics.Path;
import android.graphics.PointF;
import f.k;
import i.C0402a;
import java.util.List;
import k.n;

/* JADX INFO: renamed from: p.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0727g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final PointF f6339a = new PointF();

    public static PointF a(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x + pointF2.x, pointF.y + pointF2.y);
    }

    public static float b(float f2, float f3, float f4) {
        return Math.max(f3, Math.min(f4, f2));
    }

    public static int c(int i2, int i3, int i4) {
        return Math.max(i3, Math.min(i4, i2));
    }

    public static boolean d(float f2, float f3, float f4) {
        return f2 >= f3 && f2 <= f4;
    }

    public static int e(int i2, int i3) {
        int i4 = i2 / i3;
        return (((i2 ^ i3) >= 0) || i2 % i3 == 0) ? i4 : i4 - 1;
    }

    public static int f(float f2, float f3) {
        return g((int) f2, (int) f3);
    }

    public static int g(int i2, int i3) {
        return i2 - (i3 * e(i2, i3));
    }

    public static void h(n nVar, Path path) {
        path.reset();
        PointF pointFB = nVar.b();
        path.moveTo(pointFB.x, pointFB.y);
        f6339a.set(pointFB.x, pointFB.y);
        for (int i2 = 0; i2 < nVar.a().size(); i2++) {
            C0402a c0402a = (C0402a) nVar.a().get(i2);
            PointF pointFA = c0402a.a();
            PointF pointFB2 = c0402a.b();
            PointF pointFC = c0402a.c();
            PointF pointF = f6339a;
            if (pointFA.equals(pointF) && pointFB2.equals(pointFC)) {
                path.lineTo(pointFC.x, pointFC.y);
            } else {
                path.cubicTo(pointFA.x, pointFA.y, pointFB2.x, pointFB2.y, pointFC.x, pointFC.y);
            }
            pointF.set(pointFC.x, pointFC.y);
        }
        if (nVar.d()) {
            path.close();
        }
    }

    public static float i(float f2, float f3, float f4) {
        return f2 + (f4 * (f3 - f2));
    }

    public static int j(int i2, int i3, float f2) {
        return (int) (i2 + (f2 * (i3 - i2)));
    }

    public static void k(i.e eVar, int i2, List list, i.e eVar2, k kVar) {
        if (eVar.c(kVar.getName(), i2)) {
            list.add(eVar2.a(kVar.getName()).i(kVar));
        }
    }
}
