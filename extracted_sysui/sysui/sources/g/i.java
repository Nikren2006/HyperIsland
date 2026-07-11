package g;

import android.graphics.Path;
import android.graphics.PointF;
import d.C0307h;

/* JADX INFO: loaded from: classes.dex */
public class i extends com.airbnb.lottie.value.a {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Path f4288q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final com.airbnb.lottie.value.a f4289r;

    public i(C0307h c0307h, com.airbnb.lottie.value.a aVar) {
        super(c0307h, (PointF) aVar.f1393b, (PointF) aVar.f1394c, aVar.f1395d, aVar.f1396e, aVar.f1397f, aVar.f1398g, aVar.f1399h);
        this.f4289r = aVar;
        i();
    }

    public void i() {
        Object obj;
        Object obj2;
        Object obj3 = this.f1394c;
        boolean z2 = (obj3 == null || (obj2 = this.f1393b) == null || !((PointF) obj2).equals(((PointF) obj3).x, ((PointF) obj3).y)) ? false : true;
        Object obj4 = this.f1393b;
        if (obj4 == null || (obj = this.f1394c) == null || z2) {
            return;
        }
        com.airbnb.lottie.value.a aVar = this.f4289r;
        this.f4288q = p.h.d((PointF) obj4, (PointF) obj, aVar.f1406o, aVar.f1407p);
    }

    public Path j() {
        return this.f4288q;
    }
}
