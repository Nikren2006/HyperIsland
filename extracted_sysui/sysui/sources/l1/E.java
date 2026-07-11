package l1;

import H0.j;
import a.C0195a;

/* JADX INFO: loaded from: classes2.dex */
public abstract class E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final StackTraceElement f5194a = new C0195a().a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f5195b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f5196c;

    static {
        Object objA;
        Object objA2;
        try {
            j.a aVar = H0.j.f299a;
            objA = H0.j.a(N0.a.class.getCanonicalName());
        } catch (Throwable th) {
            j.a aVar2 = H0.j.f299a;
            objA = H0.j.a(H0.k.a(th));
        }
        if (H0.j.b(objA) != null) {
            objA = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        }
        f5195b = (String) objA;
        try {
            objA2 = H0.j.a(E.class.getCanonicalName());
        } catch (Throwable th2) {
            j.a aVar3 = H0.j.f299a;
            objA2 = H0.j.a(H0.k.a(th2));
        }
        if (H0.j.b(objA2) != null) {
            objA2 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
        f5196c = (String) objA2;
    }

    public static final Throwable a(Throwable th) {
        return th;
    }
}
