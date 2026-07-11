package j1;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Function1 f4708a = b.f4711a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Function2 f4709b = a.f4710a;

    public static final class a extends kotlin.jvm.internal.o implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f4710a = new a();

        public a() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Boolean invoke(Object obj, Object obj2) {
            return Boolean.valueOf(kotlin.jvm.internal.n.c(obj, obj2));
        }
    }

    public static final class b extends kotlin.jvm.internal.o implements Function1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f4711a = new b();

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return obj;
        }
    }

    public static final InterfaceC0418f a(InterfaceC0418f interfaceC0418f) {
        return interfaceC0418f instanceof I ? interfaceC0418f : b(interfaceC0418f, f4708a, f4709b);
    }

    public static final InterfaceC0418f b(InterfaceC0418f interfaceC0418f, Function1 function1, Function2 function2) {
        if (interfaceC0418f instanceof C0416d) {
            C0416d c0416d = (C0416d) interfaceC0418f;
            if (c0416d.f4692b == function1 && c0416d.f4693c == function2) {
                return interfaceC0418f;
            }
        }
        return new C0416d(interfaceC0418f, function1, function2);
    }
}
