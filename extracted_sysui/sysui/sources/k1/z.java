package k1;

import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import l1.J;

/* JADX INFO: loaded from: classes2.dex */
public final class z implements InterfaceC0419g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L0.g f5027a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f5028b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Function2 f5029c;

    public static final class a extends N0.l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5030a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public /* synthetic */ Object f5031b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0419g f5032c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(InterfaceC0419g interfaceC0419g, L0.d dVar) {
            super(2, dVar);
            this.f5032c = interfaceC0419g;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            a aVar = new a(this.f5032c, dVar);
            aVar.f5031b = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public final Object invoke(Object obj, L0.d dVar) {
            return ((a) create(obj, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.f5030a;
            if (i2 == 0) {
                H0.k.b(obj);
                Object obj2 = this.f5031b;
                InterfaceC0419g interfaceC0419g = this.f5032c;
                this.f5030a = 1;
                if (interfaceC0419g.emit(obj2, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            return H0.s.f314a;
        }
    }

    public z(InterfaceC0419g interfaceC0419g, L0.g gVar) {
        this.f5027a = gVar;
        this.f5028b = J.b(gVar);
        this.f5029c = new a(interfaceC0419g, null);
    }

    @Override // j1.InterfaceC0419g
    public Object emit(Object obj, L0.d dVar) {
        Object objB = f.b(this.f5027a, obj, this.f5028b, this.f5029c, dVar);
        return objB == M0.c.c() ? objB : H0.s.f314a;
    }
}
