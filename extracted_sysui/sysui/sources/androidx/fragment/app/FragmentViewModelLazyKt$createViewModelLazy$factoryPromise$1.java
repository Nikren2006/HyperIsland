package androidx.fragment.app;

import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentViewModelLazyKt$createViewModelLazy$factoryPromise$1 extends o implements Function0 {
    final /* synthetic */ Fragment $this_createViewModelLazy;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentViewModelLazyKt$createViewModelLazy$factoryPromise$1(Fragment fragment) {
        super(0);
        this.$this_createViewModelLazy = fragment;
    }

    @Override // kotlin.jvm.functions.Function0
    public final ViewModelProvider.Factory invoke() {
        return this.$this_createViewModelLazy.getDefaultViewModelProviderFactory();
    }
}
