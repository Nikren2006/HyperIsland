package g;

import android.graphics.PointF;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class k extends g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final PointF f4294i;

    public k(List list) {
        super(list);
        this.f4294i = new PointF();
    }

    @Override // g.AbstractC0355a
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public PointF i(com.airbnb.lottie.value.a aVar, float f2) {
        return j(aVar, f2, f2, f2);
    }

    @Override // g.AbstractC0355a
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public PointF j(com.airbnb.lottie.value.a aVar, float f2, float f3, float f4) {
        Object obj;
        PointF pointF;
        Object obj2 = aVar.f1393b;
        if (obj2 == null || (obj = aVar.f1394c) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        PointF pointF2 = (PointF) obj2;
        PointF pointF3 = (PointF) obj;
        com.airbnb.lottie.value.c cVar = this.f4265e;
        if (cVar != null && (pointF = (PointF) cVar.getValueInternal(aVar.f1398g, aVar.f1399h.floatValue(), pointF2, pointF3, f2, e(), f())) != null) {
            return pointF;
        }
        PointF pointF4 = this.f4294i;
        float f5 = pointF2.x;
        float f6 = f5 + (f3 * (pointF3.x - f5));
        float f7 = pointF2.y;
        pointF4.set(f6, f7 + (f4 * (pointF3.y - f7)));
        return this.f4294i;
    }
}
