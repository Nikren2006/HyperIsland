package w1;

import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u1.c f6991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f6992b;

    public /* synthetic */ class a extends kotlin.jvm.internal.l implements Function2 {
        public a(Object obj) {
            super(2, obj, g.class, "readIfAbsent", "readIfAbsent(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Z", 0);
        }

        public final Boolean b(s1.c p02, int i2) {
            kotlin.jvm.internal.n.g(p02, "p0");
            return Boolean.valueOf(((g) this.receiver).d(p02, i2));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return b((s1.c) obj, ((Number) obj2).intValue());
        }
    }

    public g(s1.c descriptor) {
        kotlin.jvm.internal.n.g(descriptor, "descriptor");
        this.f6991a = new u1.c(descriptor, new a(this));
    }

    public final void b(int i2) {
        this.f6991a.a(i2);
    }

    public final int c() {
        return this.f6991a.d();
    }

    public final boolean d(s1.c cVar, int i2) {
        boolean z2 = !cVar.i(i2) && cVar.g(i2).a();
        this.f6992b = z2;
        return z2;
    }
}
