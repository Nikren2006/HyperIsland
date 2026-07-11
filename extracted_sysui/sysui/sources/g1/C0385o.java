package g1;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: renamed from: g1.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0385o extends C0394v {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f4434c = AtomicIntegerFieldUpdater.newUpdater(C0385o.class, "_resumed");
    private volatile int _resumed;

    public C0385o(L0.d dVar, Throwable th, boolean z2) {
        if (th == null) {
            th = new CancellationException("Continuation " + dVar + " was cancelled normally");
        }
        super(th, z2);
        this._resumed = 0;
    }

    public final boolean c() {
        return f4434c.compareAndSet(this, 0, 1);
    }
}
