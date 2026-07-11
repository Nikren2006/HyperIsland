package g;

import android.graphics.Path;
import f.s;
import java.util.List;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public class m extends AbstractC0355a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final k.n f4296i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Path f4297j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public List f4298k;

    public m(List list) {
        super(list);
        this.f4296i = new k.n();
        this.f4297j = new Path();
    }

    @Override // g.AbstractC0355a
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public Path i(com.airbnb.lottie.value.a aVar, float f2) {
        this.f4296i.c((k.n) aVar.f1393b, (k.n) aVar.f1394c, f2);
        k.n nVarC = this.f4296i;
        List list = this.f4298k;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                nVarC = ((s) this.f4298k.get(size)).c(nVarC);
            }
        }
        AbstractC0727g.h(nVarC, this.f4297j);
        return this.f4297j;
    }

    public void q(List list) {
        this.f4298k = list;
    }
}
