package androidx.lifecycle.viewmodel;

import U0.a;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import d1.InterfaceC0324c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@ViewModelFactoryDsl
public final class InitializerViewModelFactoryBuilder {
    private final List<ViewModelInitializer<?>> initializers = new ArrayList();

    public final <T extends ViewModel> void addInitializer(InterfaceC0324c clazz, Function1 initializer) {
        n.g(clazz, "clazz");
        n.g(initializer, "initializer");
        this.initializers.add(new ViewModelInitializer<>(a.a(clazz), initializer));
    }

    public final ViewModelProvider.Factory build() {
        ViewModelInitializer[] viewModelInitializerArr = (ViewModelInitializer[]) this.initializers.toArray(new ViewModelInitializer[0]);
        return new InitializerViewModelFactory((ViewModelInitializer[]) Arrays.copyOf(viewModelInitializerArr, viewModelInitializerArr.length));
    }
}
