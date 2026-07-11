package l1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class D {
    public static Object a(Object obj) {
        return obj;
    }

    public static final C b(Object obj) {
        if (obj == AbstractC0449d.f5215a) {
            throw new IllegalStateException("Does not contain segment");
        }
        kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type S of kotlinx.coroutines.internal.SegmentOrClosed");
        return (C) obj;
    }

    public static final boolean c(Object obj) {
        return obj == AbstractC0449d.f5215a;
    }
}
