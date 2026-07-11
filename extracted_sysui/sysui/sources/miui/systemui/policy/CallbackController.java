package miui.systemui.policy;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

/* JADX INFO: loaded from: classes4.dex */
public interface CallbackController<T> {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* synthetic */ default void lambda$observe$0(Object obj, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_RESUME) {
            addCallback(obj);
        } else if (event == Lifecycle.Event.ON_PAUSE) {
            removeCallback(obj);
        }
    }

    void addCallback(@NonNull T t2);

    default T observe(LifecycleOwner lifecycleOwner, T t2) {
        return observe(lifecycleOwner.getLifecycle(), t2);
    }

    void removeCallback(@NonNull T t2);

    default T observe(Lifecycle lifecycle, final T t2) {
        lifecycle.addObserver(new LifecycleEventObserver() { // from class: B1.a
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                this.f39a.lambda$observe$0(t2, lifecycleOwner, event);
            }
        });
        return t2;
    }
}
