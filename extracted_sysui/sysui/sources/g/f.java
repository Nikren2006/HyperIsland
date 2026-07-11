package g;

import java.util.List;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public class f extends g {
    public f(List list) {
        super(list);
    }

    public int p() {
        return q(b(), d());
    }

    public int q(com.airbnb.lottie.value.a aVar, float f2) {
        Integer num;
        if (aVar.f1393b == null || aVar.f1394c == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        com.airbnb.lottie.value.c cVar = this.f4265e;
        return (cVar == null || (num = (Integer) cVar.getValueInternal(aVar.f1398g, aVar.f1399h.floatValue(), (Integer) aVar.f1393b, (Integer) aVar.f1394c, f2, e(), f())) == null) ? AbstractC0727g.j(aVar.g(), aVar.d(), f2) : num.intValue();
    }

    @Override // g.AbstractC0355a
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public Integer i(com.airbnb.lottie.value.a aVar, float f2) {
        return Integer.valueOf(q(aVar, f2));
    }
}
