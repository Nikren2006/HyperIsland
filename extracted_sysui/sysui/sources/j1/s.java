package j1;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class s {

    public static final class a implements InterfaceC0418f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0418f[] f4770a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ V0.n f4771b;

        /* JADX INFO: renamed from: j1.s$a$a, reason: collision with other inner class name */
        public static final class C0098a extends N0.l implements Function3 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f4772a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public /* synthetic */ Object f4773b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public /* synthetic */ Object f4774c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ V0.n f4775d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0098a(L0.d dVar, V0.n nVar) {
                super(3, dVar);
                this.f4775d = nVar;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(InterfaceC0419g interfaceC0419g, Object[] objArr, L0.d dVar) {
                C0098a c0098a = new C0098a(dVar, this.f4775d);
                c0098a.f4773b = interfaceC0419g;
                c0098a.f4774c = objArr;
                return c0098a.invokeSuspend(H0.s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                InterfaceC0419g interfaceC0419g;
                Object objC = M0.c.c();
                int i2 = this.f4772a;
                if (i2 == 0) {
                    H0.k.b(obj);
                    interfaceC0419g = (InterfaceC0419g) this.f4773b;
                    Object[] objArr = (Object[]) this.f4774c;
                    V0.n nVar = this.f4775d;
                    Object obj2 = objArr[0];
                    Object obj3 = objArr[1];
                    Object obj4 = objArr[2];
                    this.f4773b = interfaceC0419g;
                    this.f4772a = 1;
                    kotlin.jvm.internal.m.c(6);
                    obj = nVar.invoke(obj2, obj3, obj4, this);
                    kotlin.jvm.internal.m.c(7);
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
                    interfaceC0419g = (InterfaceC0419g) this.f4773b;
                    H0.k.b(obj);
                }
                this.f4773b = null;
                this.f4772a = 2;
                if (interfaceC0419g.emit(obj, this) == objC) {
                    return objC;
                }
                return H0.s.f314a;
            }
        }

        public a(InterfaceC0418f[] interfaceC0418fArr, V0.n nVar) {
            this.f4770a = interfaceC0418fArr;
            this.f4771b = nVar;
        }

        @Override // j1.InterfaceC0418f
        public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
            Object objA = k1.k.a(interfaceC0419g, this.f4770a, s.g(), new C0098a(null, this.f4771b), dVar);
            return objA == M0.c.c() ? objA : H0.s.f314a;
        }
    }

    public static final class b implements InterfaceC0418f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0418f[] f4776a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ V0.o f4777b;

        public static final class a extends N0.l implements Function3 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f4778a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public /* synthetic */ Object f4779b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public /* synthetic */ Object f4780c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ V0.o f4781d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(L0.d dVar, V0.o oVar) {
                super(3, dVar);
                this.f4781d = oVar;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(InterfaceC0419g interfaceC0419g, Object[] objArr, L0.d dVar) {
                a aVar = new a(dVar, this.f4781d);
                aVar.f4779b = interfaceC0419g;
                aVar.f4780c = objArr;
                return aVar.invokeSuspend(H0.s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                InterfaceC0419g interfaceC0419g;
                Object objC = M0.c.c();
                int i2 = this.f4778a;
                if (i2 == 0) {
                    H0.k.b(obj);
                    interfaceC0419g = (InterfaceC0419g) this.f4779b;
                    Object[] objArr = (Object[]) this.f4780c;
                    V0.o oVar = this.f4781d;
                    Object obj2 = objArr[0];
                    Object obj3 = objArr[1];
                    Object obj4 = objArr[2];
                    Object obj5 = objArr[3];
                    this.f4779b = interfaceC0419g;
                    this.f4778a = 1;
                    kotlin.jvm.internal.m.c(6);
                    obj = oVar.invoke(obj2, obj3, obj4, obj5, this);
                    kotlin.jvm.internal.m.c(7);
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
                    interfaceC0419g = (InterfaceC0419g) this.f4779b;
                    H0.k.b(obj);
                }
                this.f4779b = null;
                this.f4778a = 2;
                if (interfaceC0419g.emit(obj, this) == objC) {
                    return objC;
                }
                return H0.s.f314a;
            }
        }

        public b(InterfaceC0418f[] interfaceC0418fArr, V0.o oVar) {
            this.f4776a = interfaceC0418fArr;
            this.f4777b = oVar;
        }

        @Override // j1.InterfaceC0418f
        public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
            Object objA = k1.k.a(interfaceC0419g, this.f4776a, s.g(), new a(null, this.f4777b), dVar);
            return objA == M0.c.c() ? objA : H0.s.f314a;
        }
    }

    public static final class c implements InterfaceC0418f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0418f[] f4782a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ V0.p f4783b;

        public static final class a extends N0.l implements Function3 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f4784a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public /* synthetic */ Object f4785b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public /* synthetic */ Object f4786c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ V0.p f4787d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(L0.d dVar, V0.p pVar) {
                super(3, dVar);
                this.f4787d = pVar;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(InterfaceC0419g interfaceC0419g, Object[] objArr, L0.d dVar) {
                a aVar = new a(dVar, this.f4787d);
                aVar.f4785b = interfaceC0419g;
                aVar.f4786c = objArr;
                return aVar.invokeSuspend(H0.s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                InterfaceC0419g interfaceC0419g;
                Object objC = M0.c.c();
                int i2 = this.f4784a;
                if (i2 == 0) {
                    H0.k.b(obj);
                    interfaceC0419g = (InterfaceC0419g) this.f4785b;
                    Object[] objArr = (Object[]) this.f4786c;
                    V0.p pVar = this.f4787d;
                    Object obj2 = objArr[0];
                    Object obj3 = objArr[1];
                    Object obj4 = objArr[2];
                    Object obj5 = objArr[3];
                    Object obj6 = objArr[4];
                    this.f4785b = interfaceC0419g;
                    this.f4784a = 1;
                    kotlin.jvm.internal.m.c(6);
                    obj = pVar.invoke(obj2, obj3, obj4, obj5, obj6, this);
                    kotlin.jvm.internal.m.c(7);
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
                    interfaceC0419g = (InterfaceC0419g) this.f4785b;
                    H0.k.b(obj);
                }
                this.f4785b = null;
                this.f4784a = 2;
                if (interfaceC0419g.emit(obj, this) == objC) {
                    return objC;
                }
                return H0.s.f314a;
            }
        }

        public c(InterfaceC0418f[] interfaceC0418fArr, V0.p pVar) {
            this.f4782a = interfaceC0418fArr;
            this.f4783b = pVar;
        }

        @Override // j1.InterfaceC0418f
        public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
            Object objA = k1.k.a(interfaceC0419g, this.f4782a, s.g(), new a(null, this.f4783b), dVar);
            return objA == M0.c.c() ? objA : H0.s.f314a;
        }
    }

    public static final class d implements InterfaceC0418f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0418f f4788a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0418f f4789b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Function3 f4790c;

        public d(InterfaceC0418f interfaceC0418f, InterfaceC0418f interfaceC0418f2, Function3 function3) {
            this.f4788a = interfaceC0418f;
            this.f4789b = interfaceC0418f2;
            this.f4790c = function3;
        }

        @Override // j1.InterfaceC0418f
        public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
            Object objA = k1.k.a(interfaceC0419g, new InterfaceC0418f[]{this.f4788a, this.f4789b}, s.g(), new e(this.f4790c, null), dVar);
            return objA == M0.c.c() ? objA : H0.s.f314a;
        }
    }

    public static final class e extends N0.l implements Function3 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4791a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public /* synthetic */ Object f4792b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public /* synthetic */ Object f4793c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Function3 f4794d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(Function3 function3, L0.d dVar) {
            super(3, dVar);
            this.f4794d = function3;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(InterfaceC0419g interfaceC0419g, Object[] objArr, L0.d dVar) {
            e eVar = new e(this.f4794d, dVar);
            eVar.f4792b = interfaceC0419g;
            eVar.f4793c = objArr;
            return eVar.invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            InterfaceC0419g interfaceC0419g;
            Object objC = M0.c.c();
            int i2 = this.f4791a;
            if (i2 == 0) {
                H0.k.b(obj);
                interfaceC0419g = (InterfaceC0419g) this.f4792b;
                Object[] objArr = (Object[]) this.f4793c;
                Function3 function3 = this.f4794d;
                Object obj2 = objArr[0];
                Object obj3 = objArr[1];
                this.f4792b = interfaceC0419g;
                this.f4791a = 1;
                obj = function3.invoke(obj2, obj3, this);
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
                interfaceC0419g = (InterfaceC0419g) this.f4792b;
                H0.k.b(obj);
            }
            this.f4792b = null;
            this.f4791a = 2;
            if (interfaceC0419g.emit(obj, this) == objC) {
                return objC;
            }
            return H0.s.f314a;
        }
    }

    public static final class f extends kotlin.jvm.internal.o implements Function0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f4795a = new f();

        public f() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final Void invoke() {
            return null;
        }
    }

    public static final InterfaceC0418f b(InterfaceC0418f interfaceC0418f, InterfaceC0418f interfaceC0418f2, InterfaceC0418f interfaceC0418f3, V0.n nVar) {
        return new a(new InterfaceC0418f[]{interfaceC0418f, interfaceC0418f2, interfaceC0418f3}, nVar);
    }

    public static final InterfaceC0418f c(InterfaceC0418f interfaceC0418f, InterfaceC0418f interfaceC0418f2, InterfaceC0418f interfaceC0418f3, InterfaceC0418f interfaceC0418f4, V0.o oVar) {
        return new b(new InterfaceC0418f[]{interfaceC0418f, interfaceC0418f2, interfaceC0418f3, interfaceC0418f4}, oVar);
    }

    public static final InterfaceC0418f d(InterfaceC0418f interfaceC0418f, InterfaceC0418f interfaceC0418f2, InterfaceC0418f interfaceC0418f3, InterfaceC0418f interfaceC0418f4, InterfaceC0418f interfaceC0418f5, V0.p pVar) {
        return new c(new InterfaceC0418f[]{interfaceC0418f, interfaceC0418f2, interfaceC0418f3, interfaceC0418f4, interfaceC0418f5}, pVar);
    }

    public static final InterfaceC0418f e(InterfaceC0418f interfaceC0418f, InterfaceC0418f interfaceC0418f2, Function3 function3) {
        return AbstractC0420h.v(interfaceC0418f, interfaceC0418f2, function3);
    }

    public static final InterfaceC0418f f(InterfaceC0418f interfaceC0418f, InterfaceC0418f interfaceC0418f2, Function3 function3) {
        return new d(interfaceC0418f, interfaceC0418f2, function3);
    }

    public static final Function0 g() {
        return f.f4795a;
    }
}
