package com.android.systemui.settings;

import com.android.systemui.settings.UserTracker;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
final class DataItem {
    private final WeakReference<UserTracker.Callback> callback;
    private final Executor executor;

    public DataItem(WeakReference<UserTracker.Callback> callback, Executor executor) {
        n.g(callback, "callback");
        n.g(executor, "executor");
        this.callback = callback;
        this.executor = executor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DataItem copy$default(DataItem dataItem, WeakReference weakReference, Executor executor, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            weakReference = dataItem.callback;
        }
        if ((i2 & 2) != 0) {
            executor = dataItem.executor;
        }
        return dataItem.copy(weakReference, executor);
    }

    public final WeakReference<UserTracker.Callback> component1() {
        return this.callback;
    }

    public final Executor component2() {
        return this.executor;
    }

    public final DataItem copy(WeakReference<UserTracker.Callback> callback, Executor executor) {
        n.g(callback, "callback");
        n.g(executor, "executor");
        return new DataItem(callback, executor);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataItem)) {
            return false;
        }
        DataItem dataItem = (DataItem) obj;
        return n.c(this.callback, dataItem.callback) && n.c(this.executor, dataItem.executor);
    }

    public final WeakReference<UserTracker.Callback> getCallback() {
        return this.callback;
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public int hashCode() {
        return (this.callback.hashCode() * 31) + this.executor.hashCode();
    }

    public final boolean sameOrEmpty(UserTracker.Callback other) {
        n.g(other, "other");
        UserTracker.Callback callback = this.callback.get();
        if (callback != null) {
            return callback.equals(other);
        }
        return true;
    }

    public String toString() {
        return "DataItem(callback=" + this.callback + ", executor=" + this.executor + ")";
    }
}
