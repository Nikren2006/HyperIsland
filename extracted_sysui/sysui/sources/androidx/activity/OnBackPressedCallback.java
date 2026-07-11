package androidx.activity;

import androidx.annotation.MainThread;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class OnBackPressedCallback {
    private final CopyOnWriteArrayList<Cancellable> cancellables = new CopyOnWriteArrayList<>();
    private Function0 enabledChangedCallback;
    private boolean isEnabled;

    public OnBackPressedCallback(boolean z2) {
        this.isEnabled = z2;
    }

    public final void addCancellable(Cancellable cancellable) {
        n.g(cancellable, "cancellable");
        this.cancellables.add(cancellable);
    }

    public final Function0 getEnabledChangedCallback$activity_release() {
        return this.enabledChangedCallback;
    }

    @MainThread
    public void handleOnBackCancelled() {
    }

    @MainThread
    public abstract void handleOnBackPressed();

    @MainThread
    public void handleOnBackProgressed(BackEventCompat backEvent) {
        n.g(backEvent, "backEvent");
    }

    @MainThread
    public void handleOnBackStarted(BackEventCompat backEvent) {
        n.g(backEvent, "backEvent");
    }

    @MainThread
    public final boolean isEnabled() {
        return this.isEnabled;
    }

    @MainThread
    public final void remove() {
        Iterator<T> it = this.cancellables.iterator();
        while (it.hasNext()) {
            ((Cancellable) it.next()).cancel();
        }
    }

    public final void removeCancellable(Cancellable cancellable) {
        n.g(cancellable, "cancellable");
        this.cancellables.remove(cancellable);
    }

    @MainThread
    public final void setEnabled(boolean z2) {
        this.isEnabled = z2;
        Function0 function0 = this.enabledChangedCallback;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final void setEnabledChangedCallback$activity_release(Function0 function0) {
        this.enabledChangedCallback = function0;
    }
}
