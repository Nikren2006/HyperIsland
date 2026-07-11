package M0;

import H0.k;
import L0.d;
import L0.g;
import N0.h;
import N0.j;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.D;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {

    public static final class a extends j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f423a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Function2 f424b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Object f425c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(d dVar, Function2 function2, Object obj) {
            super(dVar);
            this.f424b = function2;
            this.f425c = obj;
            n.e(dVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
        }

        @Override // N0.a
        public Object invokeSuspend(Object obj) throws Throwable {
            int i2 = this.f423a;
            if (i2 == 0) {
                this.f423a = 1;
                k.b(obj);
                n.e(this.f424b, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                return ((Function2) D.e(this.f424b, 2)).invoke(this.f425c, this);
            }
            if (i2 != 1) {
                throw new IllegalStateException("This coroutine had already completed");
            }
            this.f423a = 2;
            k.b(obj);
            return obj;
        }
    }

    /* JADX INFO: renamed from: M0.b$b, reason: collision with other inner class name */
    public static final class C0016b extends N0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f426a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Function2 f427b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Object f428c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0016b(d dVar, g gVar, Function2 function2, Object obj) {
            super(dVar, gVar);
            this.f427b = function2;
            this.f428c = obj;
            n.e(dVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
        }

        @Override // N0.a
        public Object invokeSuspend(Object obj) throws Throwable {
            int i2 = this.f426a;
            if (i2 == 0) {
                this.f426a = 1;
                k.b(obj);
                n.e(this.f427b, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                return ((Function2) D.e(this.f427b, 2)).invoke(this.f428c, this);
            }
            if (i2 != 1) {
                throw new IllegalStateException("This coroutine had already completed");
            }
            this.f426a = 2;
            k.b(obj);
            return obj;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static d a(Function2 function2, Object obj, d completion) {
        n.g(function2, "<this>");
        n.g(completion, "completion");
        d dVarA = h.a(completion);
        if (function2 instanceof N0.a) {
            return ((N0.a) function2).create(obj, dVarA);
        }
        g context = dVarA.getContext();
        return context == L0.h.f402a ? new a(dVarA, function2, obj) : new C0016b(dVarA, context, function2, obj);
    }

    public static d b(d dVar) {
        d dVarIntercepted;
        n.g(dVar, "<this>");
        N0.d dVar2 = dVar instanceof N0.d ? (N0.d) dVar : null;
        return (dVar2 == null || (dVarIntercepted = dVar2.intercepted()) == null) ? dVar : dVarIntercepted;
    }
}
