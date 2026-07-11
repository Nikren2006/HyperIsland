package miui.systemui.dynamicisland.window.content.ui.viewbinder;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import g1.AbstractC0369g;
import g1.E;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.window.DynamicIslandViewModel;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandContentViewBinder {
    public static final DynamicIslandContentViewBinder INSTANCE = new DynamicIslandContentViewBinder();

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandContentViewBinder$bind$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandContentViewBinder$bind$1", f = "DynamicIslandContentViewBinder.kt", l = {16}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ DynamicIslandContentView $view;
        final /* synthetic */ DynamicIslandViewModel $viewModel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DynamicIslandViewModel dynamicIslandViewModel, DynamicIslandContentView dynamicIslandContentView, d dVar) {
            super(2, dVar);
            this.$viewModel = dynamicIslandViewModel;
            this.$view = dynamicIslandContentView;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return new AnonymousClass1(this.$viewModel, this.$view, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                InterfaceC0418f interfaceC0418fIsExpanded = this.$viewModel.isExpanded();
                final DynamicIslandContentView dynamicIslandContentView = this.$view;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandContentViewBinder.bind.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, d dVar) {
                        dynamicIslandContentView.setBlockChildrenTouch(!z2);
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (interfaceC0418fIsExpanded.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            return s.f314a;
        }
    }

    private DynamicIslandContentViewBinder() {
    }

    public final void bind(DynamicIslandContentView view, E scope, DynamicIslandViewModel viewModel) {
        n.g(view, "view");
        n.g(scope, "scope");
        n.g(viewModel, "viewModel");
        AbstractC0369g.b(scope, null, null, new AnonymousClass1(viewModel, view, null), 3, null);
    }
}
