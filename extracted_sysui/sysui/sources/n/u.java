package n;

import com.miui.circulate.device.api.Constant;
import d.C0307h;
import java.util.ArrayList;
import java.util.List;
import o.AbstractC0715c;

/* JADX INFO: loaded from: classes.dex */
public abstract class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AbstractC0715c.a f6225a = AbstractC0715c.a.a(Constant.KeyValue.KEY_COLUMN);

    public static List a(AbstractC0715c abstractC0715c, C0307h c0307h, float f2, N n2, boolean z2) {
        ArrayList arrayList = new ArrayList();
        if (abstractC0715c.A() == AbstractC0715c.b.STRING) {
            c0307h.a("Lottie doesn't support expressions.");
            return arrayList;
        }
        abstractC0715c.d();
        while (abstractC0715c.n()) {
            if (abstractC0715c.C(f6225a) != 0) {
                abstractC0715c.E();
            } else if (abstractC0715c.A() == AbstractC0715c.b.BEGIN_ARRAY) {
                abstractC0715c.c();
                if (abstractC0715c.A() == AbstractC0715c.b.NUMBER) {
                    arrayList.add(t.c(abstractC0715c, c0307h, f2, n2, false, z2));
                } else {
                    while (abstractC0715c.n()) {
                        arrayList.add(t.c(abstractC0715c, c0307h, f2, n2, true, z2));
                    }
                }
                abstractC0715c.e();
            } else {
                arrayList.add(t.c(abstractC0715c, c0307h, f2, n2, false, z2));
            }
        }
        abstractC0715c.f();
        b(arrayList);
        return arrayList;
    }

    public static void b(List list) {
        int i2;
        Object obj;
        int size = list.size();
        int i3 = 0;
        while (true) {
            i2 = size - 1;
            if (i3 >= i2) {
                break;
            }
            com.airbnb.lottie.value.a aVar = (com.airbnb.lottie.value.a) list.get(i3);
            i3++;
            com.airbnb.lottie.value.a aVar2 = (com.airbnb.lottie.value.a) list.get(i3);
            aVar.f1399h = Float.valueOf(aVar2.f1398g);
            if (aVar.f1394c == null && (obj = aVar2.f1393b) != null) {
                aVar.f1394c = obj;
                if (aVar instanceof g.i) {
                    ((g.i) aVar).i();
                }
            }
        }
        com.airbnb.lottie.value.a aVar3 = (com.airbnb.lottie.value.a) list.get(i2);
        if ((aVar3.f1393b == null || aVar3.f1394c == null) && list.size() > 1) {
            list.remove(aVar3);
        }
    }
}
