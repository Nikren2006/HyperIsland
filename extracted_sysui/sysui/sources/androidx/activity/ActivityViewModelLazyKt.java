package androidx.activity;

import androidx.annotation.MainThread;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.z;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityViewModelLazyKt {

    /* JADX INFO: renamed from: androidx.activity.ActivityViewModelLazyKt$viewModels$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        final /* synthetic */ ComponentActivity $this_viewModels;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ComponentActivity componentActivity) {
            super(0);
            this.$this_viewModels = componentActivity;
        }

        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            return this.$this_viewModels.getViewModelStore();
        }
    }

    /* JADX INFO: renamed from: androidx.activity.ActivityViewModelLazyKt$viewModels$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function0 {
        final /* synthetic */ ComponentActivity $this_viewModels;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ComponentActivity componentActivity) {
            super(0);
            this.$this_viewModels = componentActivity;
        }

        @Override // kotlin.jvm.functions.Function0
        public final CreationExtras invoke() {
            return this.$this_viewModels.getDefaultViewModelCreationExtras();
        }
    }

    /* JADX INFO: renamed from: androidx.activity.ActivityViewModelLazyKt$viewModels$3, reason: invalid class name */
    public static final class AnonymousClass3 extends o implements Function0 {
        final /* synthetic */ ComponentActivity $this_viewModels;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(ComponentActivity componentActivity) {
            super(0);
            this.$this_viewModels = componentActivity;
        }

        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            return this.$this_viewModels.getViewModelStore();
        }
    }

    /* JADX INFO: renamed from: androidx.activity.ActivityViewModelLazyKt$viewModels$4, reason: invalid class name */
    public static final class AnonymousClass4 extends o implements Function0 {
        final /* synthetic */ Function0 $extrasProducer;
        final /* synthetic */ ComponentActivity $this_viewModels;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(Function0 function0, ComponentActivity componentActivity) {
            super(0);
            this.$extrasProducer = function0;
            this.$this_viewModels = componentActivity;
        }

        @Override // kotlin.jvm.functions.Function0
        public final CreationExtras invoke() {
            CreationExtras creationExtras;
            Function0 function0 = this.$extrasProducer;
            return (function0 == null || (creationExtras = (CreationExtras) function0.invoke()) == null) ? this.$this_viewModels.getDefaultViewModelCreationExtras() : creationExtras;
        }
    }

    @MainThread
    public static final /* synthetic */ <VM extends ViewModel> H0.d viewModels(ComponentActivity componentActivity, Function0 function0) {
        if (function0 == null) {
            function0 = new ActivityViewModelLazyKt$viewModels$factoryPromise$1(componentActivity);
        }
        n.l(4, "VM");
        return new ViewModelLazy(z.b(ViewModel.class), new AnonymousClass1(componentActivity), function0, new AnonymousClass2(componentActivity));
    }

    public static /* synthetic */ H0.d viewModels$default(ComponentActivity componentActivity, Function0 function0, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            function0 = null;
        }
        if (function0 == null) {
            function0 = new ActivityViewModelLazyKt$viewModels$factoryPromise$1(componentActivity);
        }
        n.l(4, "VM");
        return new ViewModelLazy(z.b(ViewModel.class), new AnonymousClass1(componentActivity), function0, new AnonymousClass2(componentActivity));
    }

    @MainThread
    public static final /* synthetic */ <VM extends ViewModel> H0.d viewModels(ComponentActivity componentActivity, Function0 function0, Function0 function02) {
        if (function02 == null) {
            function02 = new ActivityViewModelLazyKt$viewModels$factoryPromise$2(componentActivity);
        }
        n.l(4, "VM");
        return new ViewModelLazy(z.b(ViewModel.class), new AnonymousClass3(componentActivity), function02, new AnonymousClass4(function0, componentActivity));
    }

    public static /* synthetic */ H0.d viewModels$default(ComponentActivity componentActivity, Function0 function0, Function0 function02, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            function0 = null;
        }
        if ((i2 & 2) != 0) {
            function02 = null;
        }
        if (function02 == null) {
            function02 = new ActivityViewModelLazyKt$viewModels$factoryPromise$2(componentActivity);
        }
        n.l(4, "VM");
        return new ViewModelLazy(z.b(ViewModel.class), new AnonymousClass3(componentActivity), function02, new AnonymousClass4(function0, componentActivity));
    }
}
