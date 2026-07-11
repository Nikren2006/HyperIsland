package u1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class s extends b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1.c f6895b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(q1.b primitiveSerializer) {
        super(primitiveSerializer, null);
        kotlin.jvm.internal.n.g(primitiveSerializer, "primitiveSerializer");
        this.f6895b = new r(primitiveSerializer.getDescriptor());
    }

    @Override // u1.a, q1.a
    public final Object deserialize(t1.d decoder) {
        kotlin.jvm.internal.n.g(decoder, "decoder");
        return d(decoder, null);
    }

    @Override // q1.b, q1.a
    public final s1.c getDescriptor() {
        return this.f6895b;
    }

    @Override // u1.a
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final q a() {
        return (q) i(n());
    }

    @Override // u1.a
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final int b(q qVar) {
        kotlin.jvm.internal.n.g(qVar, "<this>");
        return qVar.d();
    }

    @Override // u1.a
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public final void c(q qVar, int i2) {
        kotlin.jvm.internal.n.g(qVar, "<this>");
        qVar.b(i2);
    }

    public abstract Object n();

    @Override // u1.a
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public final Object j(q qVar) {
        kotlin.jvm.internal.n.g(qVar, "<this>");
        return qVar.a();
    }
}
