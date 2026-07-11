package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ViewModelInitializer<T extends ViewModel> {
    private final Class<T> clazz;
    private final Function1 initializer;

    public ViewModelInitializer(Class<T> clazz, Function1 initializer) {
        n.g(clazz, "clazz");
        n.g(initializer, "initializer");
        this.clazz = clazz;
        this.initializer = initializer;
    }

    public final Class<T> getClazz$lifecycle_viewmodel_release() {
        return this.clazz;
    }

    public final Function1 getInitializer$lifecycle_viewmodel_release() {
        return this.initializer;
    }
}
