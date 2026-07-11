package k1;

import L0.g;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public final class l implements L0.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f5007a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ L0.g f5008b;

    public l(Throwable th, L0.g gVar) {
        this.f5007a = th;
        this.f5008b = gVar;
    }

    @Override // L0.g
    public Object fold(Object obj, Function2 function2) {
        return this.f5008b.fold(obj, function2);
    }

    @Override // L0.g
    public g.b get(g.c cVar) {
        return this.f5008b.get(cVar);
    }

    @Override // L0.g
    public L0.g minusKey(g.c cVar) {
        return this.f5008b.minusKey(cVar);
    }

    @Override // L0.g
    public L0.g plus(L0.g gVar) {
        return this.f5008b.plus(gVar);
    }
}
