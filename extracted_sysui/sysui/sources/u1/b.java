package u1;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q1.b f6856a;

    public /* synthetic */ b(q1.b bVar, DefaultConstructorMarker defaultConstructorMarker) {
        this(bVar);
    }

    @Override // u1.a
    public final void e(t1.b decoder, Object obj, int i2, int i3) {
        kotlin.jvm.internal.n.g(decoder, "decoder");
        if (i3 < 0) {
            throw new IllegalArgumentException("Size must be known in advance when using READ_ALL");
        }
        for (int i4 = 0; i4 < i3; i4++) {
            f(decoder, i2 + i4, obj, false);
        }
    }

    @Override // u1.a
    public abstract void f(t1.b bVar, int i2, Object obj, boolean z2);

    public b(q1.b bVar) {
        super(null);
        this.f6856a = bVar;
    }
}
