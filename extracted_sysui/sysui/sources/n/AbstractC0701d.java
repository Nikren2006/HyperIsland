package n;

import d.C0307h;
import j.C0408a;
import j.C0409b;
import j.C0410c;
import j.C0411d;
import java.util.List;
import o.AbstractC0715c;

/* JADX INFO: renamed from: n.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0701d {
    public static List a(AbstractC0715c abstractC0715c, float f2, C0307h c0307h, N n2) {
        return u.a(abstractC0715c, c0307h, f2, n2, false);
    }

    public static List b(AbstractC0715c abstractC0715c, C0307h c0307h, N n2) {
        return u.a(abstractC0715c, c0307h, 1.0f, n2, false);
    }

    public static C0408a c(AbstractC0715c abstractC0715c, C0307h c0307h) {
        return new C0408a(b(abstractC0715c, c0307h, C0704g.f6192a));
    }

    public static j.j d(AbstractC0715c abstractC0715c, C0307h c0307h) {
        return new j.j(b(abstractC0715c, c0307h, C0706i.f6194a));
    }

    public static C0409b e(AbstractC0715c abstractC0715c, C0307h c0307h) {
        return f(abstractC0715c, c0307h, true);
    }

    public static C0409b f(AbstractC0715c abstractC0715c, C0307h c0307h, boolean z2) {
        return new C0409b(a(abstractC0715c, z2 ? p.h.e() : 1.0f, c0307h, C0709l.f6208a));
    }

    public static C0410c g(AbstractC0715c abstractC0715c, C0307h c0307h, int i2) {
        return new C0410c(b(abstractC0715c, c0307h, new o(i2)));
    }

    public static C0411d h(AbstractC0715c abstractC0715c, C0307h c0307h) {
        return new C0411d(b(abstractC0715c, c0307h, r.f6218a));
    }

    public static j.f i(AbstractC0715c abstractC0715c, C0307h c0307h) {
        return new j.f(u.a(abstractC0715c, c0307h, p.h.e(), B.f6170a, true));
    }

    public static j.g j(AbstractC0715c abstractC0715c, C0307h c0307h) {
        return new j.g(b(abstractC0715c, c0307h, G.f6175a));
    }

    public static j.h k(AbstractC0715c abstractC0715c, C0307h c0307h) {
        return new j.h(a(abstractC0715c, p.h.e(), c0307h, H.f6176a));
    }
}
