package k1;

import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.D;

/* JADX INFO: loaded from: classes2.dex */
public abstract class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Function3 f5021a;

    public /* synthetic */ class a extends kotlin.jvm.internal.l implements Function3 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5022a = new a();

        public a() {
            super(3, InterfaceC0419g.class, "emit", "emit(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
        }

        @Override // kotlin.jvm.functions.Function3
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final Object invoke(InterfaceC0419g interfaceC0419g, Object obj, L0.d dVar) {
            return interfaceC0419g.emit(obj, dVar);
        }
    }

    static {
        a aVar = a.f5022a;
        kotlin.jvm.internal.n.e(aVar, "null cannot be cast to non-null type kotlin.Function3<kotlinx.coroutines.flow.FlowCollector<kotlin.Any?>, kotlin.Any?, kotlin.coroutines.Continuation<kotlin.Unit>, kotlin.Any?>");
        f5021a = (Function3) D.e(aVar, 3);
    }
}
