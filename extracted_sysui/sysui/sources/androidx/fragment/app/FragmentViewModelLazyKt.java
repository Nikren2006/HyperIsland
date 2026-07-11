package androidx.fragment.app;

import H0.d;
import androidx.annotation.MainThread;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import d1.InterfaceC0324c;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.z;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentViewModelLazyKt {

    /* JADX INFO: renamed from: androidx.fragment.app.FragmentViewModelLazyKt$activityViewModels$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        final /* synthetic */ Fragment $this_activityViewModels;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Fragment fragment) {
            super(0);
            this.$this_activityViewModels = fragment;
        }

        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            FragmentActivity fragmentActivityRequireActivity = this.$this_activityViewModels.requireActivity();
            n.f(fragmentActivityRequireActivity, "requireActivity()");
            ViewModelStore viewModelStore = fragmentActivityRequireActivity.getViewModelStore();
            n.f(viewModelStore, "requireActivity().viewModelStore");
            return viewModelStore;
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.FragmentViewModelLazyKt$activityViewModels$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function0 {
        final /* synthetic */ Fragment $this_activityViewModels;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Fragment fragment) {
            super(0);
            this.$this_activityViewModels = fragment;
        }

        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            FragmentActivity fragmentActivityRequireActivity = this.$this_activityViewModels.requireActivity();
            n.f(fragmentActivityRequireActivity, "requireActivity()");
            return fragmentActivityRequireActivity.getDefaultViewModelProviderFactory();
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.FragmentViewModelLazyKt$viewModels$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02121 extends o implements Function0 {
        final /* synthetic */ Fragment $this_viewModels;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02121(Fragment fragment) {
            super(0);
            this.$this_viewModels = fragment;
        }

        @Override // kotlin.jvm.functions.Function0
        public final Fragment invoke() {
            return this.$this_viewModels;
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.FragmentViewModelLazyKt$viewModels$2, reason: invalid class name and case insensitive filesystem */
    public static final class C02132 extends o implements Function0 {
        final /* synthetic */ Function0 $ownerProducer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02132(Function0 function0) {
            super(0);
            this.$ownerProducer = function0;
        }

        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = ((ViewModelStoreOwner) this.$ownerProducer.invoke()).getViewModelStore();
            n.f(viewModelStore, "ownerProducer().viewModelStore");
            return viewModelStore;
        }
    }

    @MainThread
    public static final /* synthetic */ <VM extends ViewModel> d activityViewModels(Fragment activityViewModels, Function0 function0) {
        n.g(activityViewModels, "$this$activityViewModels");
        n.l(4, "VM");
        InterfaceC0324c interfaceC0324cB = z.b(ViewModel.class);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(activityViewModels);
        if (function0 == null) {
            function0 = new AnonymousClass2(activityViewModels);
        }
        return createViewModelLazy(activityViewModels, interfaceC0324cB, anonymousClass1, function0);
    }

    public static /* synthetic */ d activityViewModels$default(Fragment activityViewModels, Function0 function0, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            function0 = null;
        }
        n.g(activityViewModels, "$this$activityViewModels");
        n.l(4, "VM");
        InterfaceC0324c interfaceC0324cB = z.b(ViewModel.class);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(activityViewModels);
        if (function0 == null) {
            function0 = new AnonymousClass2(activityViewModels);
        }
        return createViewModelLazy(activityViewModels, interfaceC0324cB, anonymousClass1, function0);
    }

    @MainThread
    public static final <VM extends ViewModel> d createViewModelLazy(Fragment createViewModelLazy, InterfaceC0324c viewModelClass, Function0 storeProducer, Function0 function0) {
        n.g(createViewModelLazy, "$this$createViewModelLazy");
        n.g(viewModelClass, "viewModelClass");
        n.g(storeProducer, "storeProducer");
        if (function0 == null) {
            function0 = new FragmentViewModelLazyKt$createViewModelLazy$factoryPromise$1(createViewModelLazy);
        }
        return new ViewModelLazy(viewModelClass, storeProducer, function0);
    }

    public static /* synthetic */ d createViewModelLazy$default(Fragment fragment, InterfaceC0324c interfaceC0324c, Function0 function0, Function0 function02, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            function02 = null;
        }
        return createViewModelLazy(fragment, interfaceC0324c, function0, function02);
    }

    @MainThread
    public static final /* synthetic */ <VM extends ViewModel> d viewModels(Fragment viewModels, Function0 ownerProducer, Function0 function0) {
        n.g(viewModels, "$this$viewModels");
        n.g(ownerProducer, "ownerProducer");
        n.l(4, "VM");
        return createViewModelLazy(viewModels, z.b(ViewModel.class), new C02132(ownerProducer), function0);
    }

    public static /* synthetic */ d viewModels$default(Fragment viewModels, Function0 ownerProducer, Function0 function0, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            ownerProducer = new C02121(viewModels);
        }
        if ((i2 & 2) != 0) {
            function0 = null;
        }
        n.g(viewModels, "$this$viewModels");
        n.g(ownerProducer, "ownerProducer");
        n.l(4, "VM");
        return createViewModelLazy(viewModels, z.b(ViewModel.class), new C02132(ownerProducer), function0);
    }
}
