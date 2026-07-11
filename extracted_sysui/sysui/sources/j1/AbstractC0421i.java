package j1;

import kotlin.jvm.functions.Function2;

/* JADX INFO: renamed from: j1.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class AbstractC0421i {

    /* JADX INFO: renamed from: j1.i$a */
    public static final class a implements InterfaceC0418f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Object f4701a;

        public a(Object obj) {
            this.f4701a = obj;
        }

        @Override // j1.InterfaceC0418f
        public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
            Object objEmit = interfaceC0419g.emit(this.f4701a, dVar);
            return objEmit == M0.c.c() ? objEmit : H0.s.f314a;
        }
    }

    public static final InterfaceC0418f a(Function2 function2) {
        return new C0414b(function2, null, 0, null, 14, null);
    }

    public static final InterfaceC0418f b(Function2 function2) {
        return new C0415c(function2, null, 0, null, 14, null);
    }

    public static final InterfaceC0418f c() {
        return C0417e.f4700a;
    }

    public static final InterfaceC0418f d(Function2 function2) {
        return new x(function2);
    }

    public static final InterfaceC0418f e(Object obj) {
        return new a(obj);
    }
}
