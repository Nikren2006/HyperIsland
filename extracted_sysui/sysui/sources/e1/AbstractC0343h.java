package e1;

import java.util.Iterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;

/* JADX INFO: renamed from: e1.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0343h {

    /* JADX INFO: renamed from: e1.h$a */
    public static final class a implements InterfaceC0340e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Function2 f4063a;

        public a(Function2 function2) {
            this.f4063a = function2;
        }

        @Override // e1.InterfaceC0340e
        public Iterator iterator() {
            return AbstractC0343h.a(this.f4063a);
        }
    }

    public static Iterator a(Function2 block) {
        n.g(block, "block");
        C0341f c0341f = new C0341f();
        c0341f.j(M0.b.a(block, c0341f, c0341f));
        return c0341f;
    }

    public static InterfaceC0340e b(Function2 block) {
        n.g(block, "block");
        return new a(block);
    }
}
