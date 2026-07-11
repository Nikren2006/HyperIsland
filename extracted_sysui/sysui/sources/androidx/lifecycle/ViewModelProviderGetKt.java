package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ViewModelProviderGetKt {
    public static final CreationExtras defaultCreationExtras(ViewModelStoreOwner owner) {
        n.g(owner, "owner");
        return owner instanceof HasDefaultViewModelProviderFactory ? ((HasDefaultViewModelProviderFactory) owner).getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE;
    }

    @MainThread
    public static final /* synthetic */ <VM extends ViewModel> VM get(ViewModelProvider viewModelProvider) {
        n.g(viewModelProvider, "<this>");
        n.l(4, "VM");
        return (VM) viewModelProvider.get(ViewModel.class);
    }
}
