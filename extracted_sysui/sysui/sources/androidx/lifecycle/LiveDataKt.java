package androidx.lifecycle;

import androidx.annotation.MainThread;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class LiveDataKt {
    @MainThread
    public static final <T> Observer<T> observe(LiveData<T> liveData, LifecycleOwner owner, final Function1 onChanged) {
        n.g(liveData, "<this>");
        n.g(owner, "owner");
        n.g(onChanged, "onChanged");
        Observer<T> observer = new Observer() { // from class: androidx.lifecycle.LiveDataKt$observe$wrappedObserver$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t2) {
                onChanged.invoke(t2);
            }
        };
        liveData.observe(owner, observer);
        return observer;
    }
}
