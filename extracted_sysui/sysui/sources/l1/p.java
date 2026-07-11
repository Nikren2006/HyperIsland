package l1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f5236a = new F("CONDITION_FALSE");

    public static final Object a() {
        return f5236a;
    }

    public static final q b(Object obj) {
        q qVar;
        z zVar = obj instanceof z ? (z) obj : null;
        if (zVar != null && (qVar = zVar.f5257a) != null) {
            return qVar;
        }
        kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        return (q) obj;
    }
}
