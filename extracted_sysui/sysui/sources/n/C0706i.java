package n;

import i.b;
import o.AbstractC0715c;

/* JADX INFO: renamed from: n.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0706i implements N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0706i f6194a = new C0706i();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AbstractC0715c.a f6195b = AbstractC0715c.a.a("t", "f", "s", "j", "tr", "lh", "ls", "fc", "sc", "sw", "of");

    @Override // n.N
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public i.b a(AbstractC0715c abstractC0715c, float f2) {
        b.a aVar = b.a.CENTER;
        abstractC0715c.d();
        b.a aVar2 = aVar;
        String strX = null;
        String strX2 = null;
        float fT = 0.0f;
        float fT2 = 0.0f;
        float fT3 = 0.0f;
        float fT4 = 0.0f;
        int iU = 0;
        int iD = 0;
        int iD2 = 0;
        boolean zR = true;
        while (abstractC0715c.n()) {
            switch (abstractC0715c.C(f6195b)) {
                case 0:
                    strX = abstractC0715c.x();
                    break;
                case 1:
                    strX2 = abstractC0715c.x();
                    break;
                case 2:
                    fT = (float) abstractC0715c.t();
                    break;
                case 3:
                    int iU2 = abstractC0715c.u();
                    aVar2 = b.a.CENTER;
                    if (iU2 <= aVar2.ordinal() && iU2 >= 0) {
                        aVar2 = b.a.values()[iU2];
                    }
                    break;
                case 4:
                    iU = abstractC0715c.u();
                    break;
                case 5:
                    fT2 = (float) abstractC0715c.t();
                    break;
                case 6:
                    fT3 = (float) abstractC0715c.t();
                    break;
                case 7:
                    iD = s.d(abstractC0715c);
                    break;
                case 8:
                    iD2 = s.d(abstractC0715c);
                    break;
                case 9:
                    fT4 = (float) abstractC0715c.t();
                    break;
                case 10:
                    zR = abstractC0715c.r();
                    break;
                default:
                    abstractC0715c.D();
                    abstractC0715c.E();
                    break;
            }
        }
        abstractC0715c.f();
        return new i.b(strX, strX2, fT, aVar2, iU, fT2, fT3, iD, iD2, fT4, zR);
    }
}
