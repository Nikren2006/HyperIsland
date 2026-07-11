package e1;

import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: renamed from: e1.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0345j extends AbstractC0344i {

    /* JADX INFO: renamed from: e1.j$a */
    public static final class a implements InterfaceC0340e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Iterator f4064a;

        public a(Iterator it) {
            this.f4064a = it;
        }

        @Override // e1.InterfaceC0340e
        public Iterator iterator() {
            return this.f4064a;
        }
    }

    /* JADX INFO: renamed from: e1.j$b */
    public static final class b extends o implements Function0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Object f4065a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Object obj) {
            super(0);
            this.f4065a = obj;
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return this.f4065a;
        }
    }

    public static InterfaceC0340e c(Iterator it) {
        n.g(it, "<this>");
        return d(new a(it));
    }

    public static final InterfaceC0340e d(InterfaceC0340e interfaceC0340e) {
        n.g(interfaceC0340e, "<this>");
        return interfaceC0340e instanceof C0336a ? interfaceC0340e : new C0336a(interfaceC0340e);
    }

    public static InterfaceC0340e e(Object obj, Function1 nextFunction) {
        n.g(nextFunction, "nextFunction");
        return obj == null ? C0337b.f4046a : new C0339d(new b(obj), nextFunction);
    }
}
