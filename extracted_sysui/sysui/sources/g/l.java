package g;

import java.util.List;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public class l extends g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final com.airbnb.lottie.value.d f4295i;

    public l(List list) {
        super(list);
        this.f4295i = new com.airbnb.lottie.value.d();
    }

    @Override // g.AbstractC0355a
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public com.airbnb.lottie.value.d i(com.airbnb.lottie.value.a aVar, float f2) {
        Object obj;
        com.airbnb.lottie.value.d dVar;
        Object obj2 = aVar.f1393b;
        if (obj2 == null || (obj = aVar.f1394c) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        com.airbnb.lottie.value.d dVar2 = (com.airbnb.lottie.value.d) obj2;
        com.airbnb.lottie.value.d dVar3 = (com.airbnb.lottie.value.d) obj;
        com.airbnb.lottie.value.c cVar = this.f4265e;
        if (cVar != null && (dVar = (com.airbnb.lottie.value.d) cVar.getValueInternal(aVar.f1398g, aVar.f1399h.floatValue(), dVar2, dVar3, f2, e(), f())) != null) {
            return dVar;
        }
        this.f4295i.d(AbstractC0727g.i(dVar2.b(), dVar3.b(), f2), AbstractC0727g.i(dVar2.c(), dVar3.c(), f2));
        return this.f4295i;
    }
}
