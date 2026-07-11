package n;

import com.miui.circulate.device.api.Constant;
import d.C0307h;
import k.C0425a;
import o.AbstractC0715c;

/* JADX INFO: renamed from: n.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0702e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6189a = AbstractC0715c.a.a("ef");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AbstractC0715c.a f6190b = AbstractC0715c.a.a("ty", Constant.KeyValue.VALUE_COLUMN);

    public static C0425a a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        abstractC0715c.d();
        C0425a c0425a = null;
        while (true) {
            boolean z2 = false;
            while (abstractC0715c.n()) {
                int iC = abstractC0715c.C(f6190b);
                if (iC != 0) {
                    if (iC != 1) {
                        abstractC0715c.D();
                        abstractC0715c.E();
                    } else if (z2) {
                        c0425a = new C0425a(AbstractC0701d.e(abstractC0715c, c0307h));
                    } else {
                        abstractC0715c.E();
                    }
                } else if (abstractC0715c.u() == 0) {
                    z2 = true;
                }
            }
            abstractC0715c.f();
            return c0425a;
        }
    }

    public static C0425a b(AbstractC0715c abstractC0715c, C0307h c0307h) {
        C0425a c0425a = null;
        while (abstractC0715c.n()) {
            if (abstractC0715c.C(f6189a) != 0) {
                abstractC0715c.D();
                abstractC0715c.E();
            } else {
                abstractC0715c.c();
                while (abstractC0715c.n()) {
                    C0425a c0425aA = a(abstractC0715c, c0307h);
                    if (c0425aA != null) {
                        c0425a = c0425aA;
                    }
                }
                abstractC0715c.e();
            }
        }
        return c0425a;
    }
}
