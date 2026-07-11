package e1;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.n;

/* JADX INFO: renamed from: e1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0336a implements InterfaceC0340e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReference f4045a;

    public C0336a(InterfaceC0340e sequence) {
        n.g(sequence, "sequence");
        this.f4045a = new AtomicReference(sequence);
    }

    @Override // e1.InterfaceC0340e
    public Iterator iterator() {
        InterfaceC0340e interfaceC0340e = (InterfaceC0340e) this.f4045a.getAndSet(null);
        if (interfaceC0340e != null) {
            return interfaceC0340e.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
