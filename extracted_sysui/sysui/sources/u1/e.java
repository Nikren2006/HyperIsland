package u1;

/* JADX INFO: loaded from: classes2.dex */
public final class e extends s implements q1.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f6865c = new e();

    public e() {
        super(r1.a.b(kotlin.jvm.internal.h.f5053a));
    }

    @Override // u1.s
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public float[] n() {
        return new float[0];
    }

    @Override // u1.b, u1.a
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public void f(t1.b decoder, int i2, d builder, boolean z2) {
        kotlin.jvm.internal.n.g(decoder, "decoder");
        kotlin.jvm.internal.n.g(builder, "builder");
        builder.e(decoder.c(getDescriptor(), i2));
    }

    @Override // u1.a
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public d i(float[] fArr) {
        kotlin.jvm.internal.n.g(fArr, "<this>");
        return new d(fArr);
    }
}
