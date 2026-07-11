package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.z;

/* JADX INFO: loaded from: classes.dex */
public final class InitializerViewModelFactoryKt {
    public static final /* synthetic */ <VM extends ViewModel> void initializer(InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder, Function1 initializer) {
        n.g(initializerViewModelFactoryBuilder, "<this>");
        n.g(initializer, "initializer");
        n.l(4, "VM");
        initializerViewModelFactoryBuilder.addInitializer(z.b(ViewModel.class), initializer);
    }

    public static final ViewModelProvider.Factory viewModelFactory(Function1 builder) {
        n.g(builder, "builder");
        InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder = new InitializerViewModelFactoryBuilder();
        builder.invoke(initializerViewModelFactoryBuilder);
        return initializerViewModelFactoryBuilder.build();
    }
}
