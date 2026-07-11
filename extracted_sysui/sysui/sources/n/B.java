package n;

import android.graphics.PointF;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public class B implements N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final B f6170a = new B();

    @Override // n.N
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public PointF a(AbstractC0715c abstractC0715c, float f2) {
        AbstractC0715c.b bVarA = abstractC0715c.A();
        if (bVarA != AbstractC0715c.b.BEGIN_ARRAY && bVarA != AbstractC0715c.b.BEGIN_OBJECT) {
            if (bVarA == AbstractC0715c.b.NUMBER) {
                PointF pointF = new PointF(((float) abstractC0715c.t()) * f2, ((float) abstractC0715c.t()) * f2);
                while (abstractC0715c.n()) {
                    abstractC0715c.E();
                }
                return pointF;
            }
            throw new IllegalArgumentException("Cannot convert json to point. Next token is " + bVarA);
        }
        return s.e(abstractC0715c, f2);
    }
}
