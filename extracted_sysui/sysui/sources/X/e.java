package X;

import U.q;
import U.r;
import b0.C0223a;

/* JADX INFO: loaded from: classes2.dex */
public final class e implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final W.c f847a;

    public e(W.c cVar) {
        this.f847a = cVar;
    }

    @Override // U.r
    public q a(U.d dVar, C0223a c0223a) {
        V.b bVar = (V.b) c0223a.getRawType().getAnnotation(V.b.class);
        if (bVar == null) {
            return null;
        }
        return b(this.f847a, dVar, c0223a, bVar);
    }

    public q b(W.c cVar, U.d dVar, C0223a c0223a, V.b bVar) {
        q qVarA;
        Object objA = cVar.b(C0223a.get(bVar.value())).a();
        boolean zNullSafe = bVar.nullSafe();
        if (objA instanceof q) {
            qVarA = (q) objA;
        } else {
            if (!(objA instanceof r)) {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + objA.getClass().getName() + " as a @JsonAdapter for " + c0223a.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
            qVarA = ((r) objA).a(dVar, c0223a);
        }
        return (qVarA == null || !zNullSafe) ? qVarA : qVarA.a();
    }
}
