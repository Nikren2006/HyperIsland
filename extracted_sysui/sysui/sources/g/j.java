package g;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class j extends g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final PointF f4290i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float[] f4291j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final PathMeasure f4292k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public i f4293l;

    public j(List list) {
        super(list);
        this.f4290i = new PointF();
        this.f4291j = new float[2];
        this.f4292k = new PathMeasure();
    }

    @Override // g.AbstractC0355a
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public PointF i(com.airbnb.lottie.value.a aVar, float f2) {
        PointF pointF;
        i iVar = (i) aVar;
        Path pathJ = iVar.j();
        if (pathJ == null) {
            return (PointF) aVar.f1393b;
        }
        com.airbnb.lottie.value.c cVar = this.f4265e;
        if (cVar != null && (pointF = (PointF) cVar.getValueInternal(iVar.f1398g, iVar.f1399h.floatValue(), (PointF) iVar.f1393b, (PointF) iVar.f1394c, e(), f2, f())) != null) {
            return pointF;
        }
        if (this.f4293l != iVar) {
            this.f4292k.setPath(pathJ, false);
            this.f4293l = iVar;
        }
        PathMeasure pathMeasure = this.f4292k;
        pathMeasure.getPosTan(f2 * pathMeasure.getLength(), this.f4291j, null);
        PointF pointF2 = this.f4290i;
        float[] fArr = this.f4291j;
        pointF2.set(fArr[0], fArr[1]);
        return this.f4290i;
    }
}
