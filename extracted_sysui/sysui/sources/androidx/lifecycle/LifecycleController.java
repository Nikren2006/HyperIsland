package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.lifecycle.Lifecycle;
import g1.InterfaceC0380l0;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@MainThread
public final class LifecycleController {
    private final DispatchQueue dispatchQueue;
    private final Lifecycle lifecycle;
    private final Lifecycle.State minState;
    private final LifecycleEventObserver observer;

    public LifecycleController(Lifecycle lifecycle, Lifecycle.State minState, DispatchQueue dispatchQueue, final InterfaceC0380l0 parentJob) {
        n.g(lifecycle, "lifecycle");
        n.g(minState, "minState");
        n.g(dispatchQueue, "dispatchQueue");
        n.g(parentJob, "parentJob");
        this.lifecycle = lifecycle;
        this.minState = minState;
        this.dispatchQueue = dispatchQueue;
        LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: androidx.lifecycle.d
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                LifecycleController.observer$lambda$0(this.f1259a, parentJob, lifecycleOwner, event);
            }
        };
        this.observer = lifecycleEventObserver;
        if (lifecycle.getCurrentState() != Lifecycle.State.DESTROYED) {
            lifecycle.addObserver(lifecycleEventObserver);
        } else {
            InterfaceC0380l0.a.a(parentJob, null, 1, null);
            finish();
        }
    }

    private final void handleDestroy(InterfaceC0380l0 interfaceC0380l0) {
        InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void observer$lambda$0(LifecycleController this$0, InterfaceC0380l0 parentJob, LifecycleOwner source, Lifecycle.Event event) {
        n.g(this$0, "this$0");
        n.g(parentJob, "$parentJob");
        n.g(source, "source");
        n.g(event, "<anonymous parameter 1>");
        if (source.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            InterfaceC0380l0.a.a(parentJob, null, 1, null);
            this$0.finish();
        } else if (source.getLifecycle().getCurrentState().compareTo(this$0.minState) < 0) {
            this$0.dispatchQueue.pause();
        } else {
            this$0.dispatchQueue.resume();
        }
    }

    @MainThread
    public final void finish() {
        this.lifecycle.removeObserver(this.observer);
        this.dispatchQueue.finish();
    }
}
