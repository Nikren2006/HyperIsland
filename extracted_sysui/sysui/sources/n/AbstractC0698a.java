package n;

import com.miui.circulate.device.api.Constant;
import com.miui.maml.folme.AnimatedProperty;
import d.C0307h;
import j.C0409b;
import java.util.ArrayList;
import o.AbstractC0715c;

/* JADX INFO: renamed from: n.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0698a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AbstractC0715c.a f6184a = AbstractC0715c.a.a(Constant.KeyValue.KEY_COLUMN, AnimatedProperty.PROPERTY_NAME_X, AnimatedProperty.PROPERTY_NAME_Y);

    public static j.e a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        ArrayList arrayList = new ArrayList();
        if (abstractC0715c.A() == AbstractC0715c.b.BEGIN_ARRAY) {
            abstractC0715c.c();
            while (abstractC0715c.n()) {
                arrayList.add(z.a(abstractC0715c, c0307h));
            }
            abstractC0715c.e();
            u.b(arrayList);
        } else {
            arrayList.add(new com.airbnb.lottie.value.a(s.e(abstractC0715c, p.h.e())));
        }
        return new j.e(arrayList);
    }

    public static j.m b(AbstractC0715c abstractC0715c, C0307h c0307h) {
        abstractC0715c.d();
        j.e eVarA = null;
        C0409b c0409bE = null;
        boolean z2 = false;
        C0409b c0409bE2 = null;
        while (abstractC0715c.A() != AbstractC0715c.b.END_OBJECT) {
            int iC = abstractC0715c.C(f6184a);
            if (iC == 0) {
                eVarA = a(abstractC0715c, c0307h);
            } else if (iC != 1) {
                if (iC != 2) {
                    abstractC0715c.D();
                    abstractC0715c.E();
                } else if (abstractC0715c.A() == AbstractC0715c.b.STRING) {
                    abstractC0715c.E();
                    z2 = true;
                } else {
                    c0409bE = AbstractC0701d.e(abstractC0715c, c0307h);
                }
            } else if (abstractC0715c.A() == AbstractC0715c.b.STRING) {
                abstractC0715c.E();
                z2 = true;
            } else {
                c0409bE2 = AbstractC0701d.e(abstractC0715c, c0307h);
            }
        }
        abstractC0715c.f();
        if (z2) {
            c0307h.a("Lottie doesn't support expressions.");
        }
        return eVarA != null ? eVarA : new j.i(c0409bE2, c0409bE);
    }
}
