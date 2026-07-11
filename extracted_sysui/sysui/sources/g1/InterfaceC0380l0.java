package g1;

import L0.g;
import java.util.concurrent.CancellationException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: renamed from: g1.l0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public interface InterfaceC0380l0 extends g.b {

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final b f4430z = b.f4431a;

    /* JADX INFO: renamed from: g1.l0$a */
    public static final class a {
        public static /* synthetic */ void a(InterfaceC0380l0 interfaceC0380l0, CancellationException cancellationException, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if ((i2 & 1) != 0) {
                cancellationException = null;
            }
            interfaceC0380l0.a(cancellationException);
        }

        public static Object b(InterfaceC0380l0 interfaceC0380l0, Object obj, Function2 function2) {
            return g.b.a.a(interfaceC0380l0, obj, function2);
        }

        public static g.b c(InterfaceC0380l0 interfaceC0380l0, g.c cVar) {
            return g.b.a.b(interfaceC0380l0, cVar);
        }

        public static /* synthetic */ S d(InterfaceC0380l0 interfaceC0380l0, boolean z2, boolean z3, Function1 function1, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: invokeOnCompletion");
            }
            if ((i2 & 1) != 0) {
                z2 = false;
            }
            if ((i2 & 2) != 0) {
                z3 = true;
            }
            return interfaceC0380l0.d(z2, z3, function1);
        }

        public static L0.g e(InterfaceC0380l0 interfaceC0380l0, g.c cVar) {
            return g.b.a.c(interfaceC0380l0, cVar);
        }

        public static L0.g f(InterfaceC0380l0 interfaceC0380l0, L0.g gVar) {
            return g.b.a.d(interfaceC0380l0, gVar);
        }
    }

    /* JADX INFO: renamed from: g1.l0$b */
    public static final class b implements g.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ b f4431a = new b();
    }

    void a(CancellationException cancellationException);

    Object c(L0.d dVar);

    S d(boolean z2, boolean z3, Function1 function1);

    CancellationException f();

    InterfaceC0380l0 getParent();

    boolean isActive();

    S l(Function1 function1);

    InterfaceC0389q r(InterfaceC0391s interfaceC0391s);

    boolean start();
}
