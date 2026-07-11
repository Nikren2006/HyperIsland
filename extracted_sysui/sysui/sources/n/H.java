package n;

import android.graphics.PointF;
import com.miui.circulate.device.api.Constant;
import i.C0402a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import o.AbstractC0715c;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public class H implements N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final H f6176a = new H();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AbstractC0715c.a f6177b = AbstractC0715c.a.a("c", Constant.KeyValue.VALUE_COLUMN, "i", "o");

    @Override // n.N
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public k.n a(AbstractC0715c abstractC0715c, float f2) {
        if (abstractC0715c.A() == AbstractC0715c.b.BEGIN_ARRAY) {
            abstractC0715c.c();
        }
        abstractC0715c.d();
        List listF = null;
        List listF2 = null;
        List listF3 = null;
        boolean zR = false;
        while (abstractC0715c.n()) {
            int iC = abstractC0715c.C(f6177b);
            if (iC == 0) {
                zR = abstractC0715c.r();
            } else if (iC == 1) {
                listF = s.f(abstractC0715c, f2);
            } else if (iC == 2) {
                listF2 = s.f(abstractC0715c, f2);
            } else if (iC != 3) {
                abstractC0715c.D();
                abstractC0715c.E();
            } else {
                listF3 = s.f(abstractC0715c, f2);
            }
        }
        abstractC0715c.f();
        if (abstractC0715c.A() == AbstractC0715c.b.END_ARRAY) {
            abstractC0715c.e();
        }
        if (listF == null || listF2 == null || listF3 == null) {
            throw new IllegalArgumentException("Shape data was missing information.");
        }
        if (listF.isEmpty()) {
            return new k.n(new PointF(), false, Collections.emptyList());
        }
        int size = listF.size();
        PointF pointF = (PointF) listF.get(0);
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 1; i2 < size; i2++) {
            PointF pointF2 = (PointF) listF.get(i2);
            int i3 = i2 - 1;
            arrayList.add(new C0402a(AbstractC0727g.a((PointF) listF.get(i3), (PointF) listF3.get(i3)), AbstractC0727g.a(pointF2, (PointF) listF2.get(i2)), pointF2));
        }
        if (zR) {
            PointF pointF3 = (PointF) listF.get(0);
            int i4 = size - 1;
            arrayList.add(new C0402a(AbstractC0727g.a((PointF) listF.get(i4), (PointF) listF3.get(i4)), AbstractC0727g.a(pointF3, (PointF) listF2.get(0)), pointF3));
        }
        return new k.n(pointF, zR, arrayList);
    }
}
