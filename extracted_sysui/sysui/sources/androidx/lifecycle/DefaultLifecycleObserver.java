package androidx.lifecycle;

import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public interface DefaultLifecycleObserver extends LifecycleObserver {
    default void onCreate(LifecycleOwner owner) {
        n.g(owner, "owner");
    }

    default void onDestroy(LifecycleOwner owner) {
        n.g(owner, "owner");
    }

    default void onPause(LifecycleOwner owner) {
        n.g(owner, "owner");
    }

    default void onResume(LifecycleOwner owner) {
        n.g(owner, "owner");
    }

    default void onStart(LifecycleOwner owner) {
        n.g(owner, "owner");
    }

    default void onStop(LifecycleOwner owner) {
        n.g(owner, "owner");
    }
}
