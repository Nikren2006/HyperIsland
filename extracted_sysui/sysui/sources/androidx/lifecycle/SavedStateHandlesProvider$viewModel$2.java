package androidx.lifecycle;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandlesProvider$viewModel$2 extends o implements Function0 {
    final /* synthetic */ ViewModelStoreOwner $viewModelStoreOwner;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SavedStateHandlesProvider$viewModel$2(ViewModelStoreOwner viewModelStoreOwner) {
        super(0);
        this.$viewModelStoreOwner = viewModelStoreOwner;
    }

    @Override // kotlin.jvm.functions.Function0
    public final SavedStateHandlesVM invoke() {
        return SavedStateHandleSupport.getSavedStateHandlesVM(this.$viewModelStoreOwner);
    }
}
