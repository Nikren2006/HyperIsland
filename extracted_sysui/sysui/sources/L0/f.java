package L0;

import H0.j;
import H0.s;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f {
    public static final void a(Function2 function2, Object obj, d completion) {
        n.g(function2, "<this>");
        n.g(completion, "completion");
        d dVarB = M0.b.b(M0.b.a(function2, obj, completion));
        j.a aVar = j.f299a;
        dVarB.resumeWith(j.a(s.f314a));
    }
}
