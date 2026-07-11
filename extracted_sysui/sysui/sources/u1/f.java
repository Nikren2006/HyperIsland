package u1;

import s1.b;

/* JADX INFO: loaded from: classes2.dex */
public final class f implements q1.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f6866a = new f();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final s1.c f6867b = new t("kotlin.Float", b.a.f6473a);

    @Override // q1.a
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Float deserialize(t1.d decoder) {
        kotlin.jvm.internal.n.g(decoder, "decoder");
        return Float.valueOf(decoder.f());
    }

    @Override // q1.b, q1.a
    public s1.c getDescriptor() {
        return f6867b;
    }
}
