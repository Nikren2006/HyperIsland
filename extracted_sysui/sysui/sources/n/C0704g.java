package n;

import android.graphics.Color;
import o.AbstractC0715c;

/* JADX INFO: renamed from: n.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0704g implements N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0704g f6192a = new C0704g();

    @Override // n.N
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer a(AbstractC0715c abstractC0715c, float f2) {
        boolean z2 = abstractC0715c.A() == AbstractC0715c.b.BEGIN_ARRAY;
        if (z2) {
            abstractC0715c.c();
        }
        double dT = abstractC0715c.t();
        double dT2 = abstractC0715c.t();
        double dT3 = abstractC0715c.t();
        double dT4 = abstractC0715c.A() == AbstractC0715c.b.NUMBER ? abstractC0715c.t() : 1.0d;
        if (z2) {
            abstractC0715c.e();
        }
        if (dT <= 1.0d && dT2 <= 1.0d && dT3 <= 1.0d) {
            dT *= 255.0d;
            dT2 *= 255.0d;
            dT3 *= 255.0d;
            if (dT4 <= 1.0d) {
                dT4 *= 255.0d;
            }
        }
        return Integer.valueOf(Color.argb((int) dT4, (int) dT, (int) dT2, (int) dT3));
    }
}
