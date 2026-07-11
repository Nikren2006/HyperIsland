package miui.systemui.controlcenter.windowview;

import H0.s;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.RepeatOnLifecycleKt;
import g1.E;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "miui.systemui.controlcenter.windowview.ControlCenterExpandController$onCreate$1$1", f = "ControlCenterExpandController.kt", l = {186}, m = "invokeSuspend")
public final class ControlCenterExpandController$onCreate$1$1 extends N0.l implements Function2 {
    final /* synthetic */ ControlCenterWindowViewImpl $this_apply;
    int label;
    final /* synthetic */ ControlCenterExpandController this$0;

    /* JADX INFO: renamed from: miui.systemui.controlcenter.windowview.ControlCenterExpandController$onCreate$1$1$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.controlcenter.windowview.ControlCenterExpandController$onCreate$1$1$1", f = "ControlCenterExpandController.kt", l = {187}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        int label;
        final /* synthetic */ ControlCenterExpandController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ControlCenterExpandController controlCenterExpandController, L0.d dVar) {
            super(2, dVar);
            this.this$0 = controlCenterExpandController;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new AnonymousClass1(this.this$0, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                InterfaceC0418f routingStateFlow = ((SecondaryPanelRouter) this.this$0.secondaryPanelRouter.get()).getRoutingStateFlow();
                final ControlCenterExpandController controlCenterExpandController = this.this$0;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.controlcenter.windowview.ControlCenterExpandController.onCreate.1.1.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Number) obj2).intValue(), dVar);
                    }

                    public final Object emit(int i3, L0.d dVar) {
                        if (i3 == 2) {
                            Log.i("ControlCenterExpandController", "idle state. update lifecycle " + controlCenterExpandController.getAppearance() + " " + ControlCenterExpandController.access$getView(controlCenterExpandController).getLifecycle().getCurrentState());
                            if (controlCenterExpandController.getAppearance() && ControlCenterExpandController.access$getView(controlCenterExpandController).getLifecycle().getCurrentState().compareTo(Lifecycle.State.RESUMED) < 0) {
                                controlCenterExpandController.handleAnimFinish();
                            }
                        }
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (routingStateFlow.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            return s.f314a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlCenterExpandController$onCreate$1$1(ControlCenterWindowViewImpl controlCenterWindowViewImpl, ControlCenterExpandController controlCenterExpandController, L0.d dVar) {
        super(2, dVar);
        this.$this_apply = controlCenterWindowViewImpl;
        this.this$0 = controlCenterExpandController;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new ControlCenterExpandController$onCreate$1$1(this.$this_apply, this.this$0, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, L0.d dVar) {
        return ((ControlCenterExpandController$onCreate$1$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            H0.k.b(obj);
            ControlCenterWindowViewImpl controlCenterWindowViewImpl = this.$this_apply;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(controlCenterWindowViewImpl, state, anonymousClass1, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
        }
        return s.f314a;
    }
}
