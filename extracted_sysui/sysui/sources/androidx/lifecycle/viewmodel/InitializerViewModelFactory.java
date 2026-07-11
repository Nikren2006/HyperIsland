package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class InitializerViewModelFactory implements ViewModelProvider.Factory {
    private final ViewModelInitializer<?>[] initializers;

    public InitializerViewModelFactory(ViewModelInitializer<?>... initializers) {
        n.g(initializers, "initializers");
        this.initializers = initializers;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass, CreationExtras extras) {
        n.g(modelClass, "modelClass");
        n.g(extras, "extras");
        T t2 = null;
        for (ViewModelInitializer<?> viewModelInitializer : this.initializers) {
            if (n.c(viewModelInitializer.getClazz$lifecycle_viewmodel_release(), modelClass)) {
                Object objInvoke = viewModelInitializer.getInitializer$lifecycle_viewmodel_release().invoke(extras);
                t2 = objInvoke instanceof ViewModel ? (T) objInvoke : null;
            }
        }
        if (t2 != null) {
            return t2;
        }
        throw new IllegalArgumentException("No initializer set for given class " + modelClass.getName());
    }
}
