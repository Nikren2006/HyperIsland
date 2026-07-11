package androidx.lifecycle;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import d1.InterfaceC0324c;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class ViewModelLazy<VM extends ViewModel> implements H0.d {
    private VM cached;
    private final Function0 extrasProducer;
    private final Function0 factoryProducer;
    private final Function0 storeProducer;
    private final InterfaceC0324c viewModelClass;

    /* JADX INFO: renamed from: androidx.lifecycle.ViewModelLazy$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final CreationExtras.Empty invoke() {
            return CreationExtras.Empty.INSTANCE;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ViewModelLazy(InterfaceC0324c viewModelClass, Function0 storeProducer, Function0 factoryProducer) {
        this(viewModelClass, storeProducer, factoryProducer, null, 8, null);
        n.g(viewModelClass, "viewModelClass");
        n.g(storeProducer, "storeProducer");
        n.g(factoryProducer, "factoryProducer");
    }

    public boolean isInitialized() {
        return this.cached != null;
    }

    public ViewModelLazy(InterfaceC0324c viewModelClass, Function0 storeProducer, Function0 factoryProducer, Function0 extrasProducer) {
        n.g(viewModelClass, "viewModelClass");
        n.g(storeProducer, "storeProducer");
        n.g(factoryProducer, "factoryProducer");
        n.g(extrasProducer, "extrasProducer");
        this.viewModelClass = viewModelClass;
        this.storeProducer = storeProducer;
        this.factoryProducer = factoryProducer;
        this.extrasProducer = extrasProducer;
    }

    @Override // H0.d
    public VM getValue() {
        VM vm = this.cached;
        if (vm != null) {
            return vm;
        }
        VM vm2 = (VM) new ViewModelProvider((ViewModelStore) this.storeProducer.invoke(), (ViewModelProvider.Factory) this.factoryProducer.invoke(), (CreationExtras) this.extrasProducer.invoke()).get(U0.a.a(this.viewModelClass));
        this.cached = vm2;
        return vm2;
    }

    public /* synthetic */ ViewModelLazy(InterfaceC0324c interfaceC0324c, Function0 function0, Function0 function02, Function0 function03, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(interfaceC0324c, function0, function02, (i2 & 8) != 0 ? AnonymousClass1.INSTANCE : function03);
    }
}
