package g;

import java.util.List;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public class d extends g {
    public d(List list) {
        super(list);
    }

    public float p() {
        return q(b(), d());
    }

    public float q(com.airbnb.lottie.value.a aVar, float f2) {
        Float f3;
        if (aVar.f1393b == null || aVar.f1394c == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        com.airbnb.lottie.value.c cVar = this.f4265e;
        return (cVar == null || (f3 = (Float) cVar.getValueInternal(aVar.f1398g, aVar.f1399h.floatValue(), (Float) aVar.f1393b, (Float) aVar.f1394c, f2, e(), f())) == null) ? AbstractC0727g.i(aVar.f(), aVar.c(), f2) : f3.floatValue();
    }

    @Override // g.AbstractC0355a
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public Float i(com.airbnb.lottie.value.a aVar, float f2) {
        return Float.valueOf(q(aVar, f2));
    }
}
