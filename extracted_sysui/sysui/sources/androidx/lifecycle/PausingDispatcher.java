package androidx.lifecycle;

import L0.g;
import g1.B;
import g1.Q;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class PausingDispatcher extends B {
    public final DispatchQueue dispatchQueue = new DispatchQueue();

    @Override // g1.B
    public void dispatch(g context, Runnable block) {
        n.g(context, "context");
        n.g(block, "block");
        this.dispatchQueue.dispatchAndEnqueue(context, block);
    }

    @Override // g1.B
    public boolean isDispatchNeeded(g context) {
        n.g(context, "context");
        if (Q.c().z().isDispatchNeeded(context)) {
            return true;
        }
        return !this.dispatchQueue.canRun();
    }
}
