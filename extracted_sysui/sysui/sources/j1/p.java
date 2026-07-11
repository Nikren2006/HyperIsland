package j1;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f4740a = l1.G.b("kotlinx.coroutines.flow.defaultConcurrency", 16, 1, Integer.MAX_VALUE);

    public static final class a extends N0.l implements Function3 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4741a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public /* synthetic */ Object f4742b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public /* synthetic */ Object f4743c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Function2 f4744d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Function2 function2, L0.d dVar) {
            super(3, dVar);
            this.f4744d = function2;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(InterfaceC0419g interfaceC0419g, Object obj, L0.d dVar) {
            a aVar = new a(this.f4744d, dVar);
            aVar.f4742b = interfaceC0419g;
            aVar.f4743c = obj;
            return aVar.invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            InterfaceC0419g interfaceC0419g;
            Object objC = M0.c.c();
            int i2 = this.f4741a;
            if (i2 == 0) {
                H0.k.b(obj);
                interfaceC0419g = (InterfaceC0419g) this.f4742b;
                Object obj2 = this.f4743c;
                Function2 function2 = this.f4744d;
                this.f4742b = interfaceC0419g;
                this.f4741a = 1;
                obj = function2.invoke(obj2, this);
                if (obj == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    H0.k.b(obj);
                    return H0.s.f314a;
                }
                interfaceC0419g = (InterfaceC0419g) this.f4742b;
                H0.k.b(obj);
            }
            this.f4742b = null;
            this.f4741a = 2;
            if (interfaceC0419g.emit(obj, this) == objC) {
                return objC;
            }
            return H0.s.f314a;
        }
    }

    public static final InterfaceC0418f a(InterfaceC0418f interfaceC0418f, Function2 function2) {
        return AbstractC0420h.C(interfaceC0418f, new a(function2, null));
    }

    public static final InterfaceC0418f b(InterfaceC0418f interfaceC0418f, Function3 function3) {
        return new k1.i(function3, interfaceC0418f, null, 0, null, 28, null);
    }
}
