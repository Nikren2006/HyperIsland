package miui.systemui.lifecycle;

import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class ViewLifecycleOwner implements LifecycleOwner {
    private final LifecycleRegistry registry;
    private final View view;

    public ViewLifecycleOwner(View view) {
        n.g(view, "view");
        this.view = view;
        this.registry = new LifecycleRegistry(this);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public androidx.lifecycle.Lifecycle getLifecycle() {
        return this.registry;
    }

    public final void onCreate() {
        this.registry.setCurrentState(Lifecycle.State.CREATED);
    }

    public final void onDestroy() {
        this.registry.setCurrentState(Lifecycle.State.DESTROYED);
    }
}
