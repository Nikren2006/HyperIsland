package n;

import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public class G implements N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final G f6175a = new G();

    @Override // n.N
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public com.airbnb.lottie.value.d a(AbstractC0715c abstractC0715c, float f2) {
        boolean z2 = abstractC0715c.A() == AbstractC0715c.b.BEGIN_ARRAY;
        if (z2) {
            abstractC0715c.c();
        }
        float fT = (float) abstractC0715c.t();
        float fT2 = (float) abstractC0715c.t();
        while (abstractC0715c.n()) {
            abstractC0715c.E();
        }
        if (z2) {
            abstractC0715c.e();
        }
        return new com.airbnb.lottie.value.d((fT / 100.0f) * f2, (fT2 / 100.0f) * f2);
    }
}
