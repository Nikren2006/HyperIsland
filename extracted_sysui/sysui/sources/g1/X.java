package g1;

import g1.W;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes2.dex */
public abstract class X extends V {
    public abstract Thread J();

    public void K(long j2, W.b bVar) {
        J.f4376g.W(j2, bVar);
    }

    public final void L() {
        Thread threadJ = J();
        if (Thread.currentThread() != threadJ) {
            AbstractC0361c.a();
            LockSupport.unpark(threadJ);
        }
    }
}
