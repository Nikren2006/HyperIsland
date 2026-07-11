package g;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class e extends g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final k.d f4284i;

    public e(List list) {
        super(list);
        k.d dVar = (k.d) ((com.airbnb.lottie.value.a) list.get(0)).f1393b;
        int iC = dVar != null ? dVar.c() : 0;
        this.f4284i = new k.d(new float[iC], new int[iC]);
    }

    @Override // g.AbstractC0355a
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public k.d i(com.airbnb.lottie.value.a aVar, float f2) {
        this.f4284i.d((k.d) aVar.f1393b, (k.d) aVar.f1394c, f2);
        return this.f4284i;
    }
}
