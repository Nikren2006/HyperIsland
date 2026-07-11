package n;

import com.miui.circulate.device.api.Constant;
import d.C0307h;
import j.C0408a;
import j.C0409b;
import o.AbstractC0715c;

/* JADX INFO: renamed from: n.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0708k {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AbstractC0715c.a f6201f = AbstractC0715c.a.a("ef");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final AbstractC0715c.a f6202g = AbstractC0715c.a.a("nm", Constant.KeyValue.VALUE_COLUMN);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0408a f6203a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0409b f6204b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public C0409b f6205c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public C0409b f6206d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C0409b f6207e;

    public final void a(AbstractC0715c abstractC0715c, C0307h c0307h) {
        abstractC0715c.d();
        String strX = "";
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6202g);
            if (iC == 0) {
                strX = abstractC0715c.x();
            } else if (iC == 1) {
                strX.hashCode();
                switch (strX) {
                    case "Distance":
                        this.f6206d = AbstractC0701d.e(abstractC0715c, c0307h);
                        break;
                    case "Opacity":
                        this.f6204b = AbstractC0701d.f(abstractC0715c, c0307h, false);
                        break;
                    case "Direction":
                        this.f6205c = AbstractC0701d.f(abstractC0715c, c0307h, false);
                        break;
                    case "Shadow Color":
                        this.f6203a = AbstractC0701d.c(abstractC0715c, c0307h);
                        break;
                    case "Softness":
                        this.f6207e = AbstractC0701d.e(abstractC0715c, c0307h);
                        break;
                    default:
                        abstractC0715c.E();
                        break;
                }
            } else {
                abstractC0715c.D();
                abstractC0715c.E();
            }
        }
        abstractC0715c.f();
    }

    public C0707j b(AbstractC0715c abstractC0715c, C0307h c0307h) {
        C0409b c0409b;
        C0409b c0409b2;
        C0409b c0409b3;
        C0409b c0409b4;
        while (abstractC0715c.n()) {
            if (abstractC0715c.C(f6201f) != 0) {
                abstractC0715c.D();
                abstractC0715c.E();
            } else {
                abstractC0715c.c();
                while (abstractC0715c.n()) {
                    a(abstractC0715c, c0307h);
                }
                abstractC0715c.e();
            }
        }
        C0408a c0408a = this.f6203a;
        if (c0408a == null || (c0409b = this.f6204b) == null || (c0409b2 = this.f6205c) == null || (c0409b3 = this.f6206d) == null || (c0409b4 = this.f6207e) == null) {
            return null;
        }
        return new C0707j(c0408a, c0409b, c0409b2, c0409b3, c0409b4);
    }
}
