package miui.systemui.devicecontrols.controller;

import android.service.controls.IControlsSubscription;
import kotlin.jvm.functions.Function0;
import miui.systemui.devicecontrols.controller.ControlsBindingControllerImpl;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsBindingControllerImpl$LoadSubscriber$onSubscribe$1 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ ControlsBindingControllerImpl this$0;
    final /* synthetic */ ControlsBindingControllerImpl.LoadSubscriber this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlsBindingControllerImpl$LoadSubscriber$onSubscribe$1(ControlsBindingControllerImpl controlsBindingControllerImpl, ControlsBindingControllerImpl.LoadSubscriber loadSubscriber) {
        super(0);
        this.this$0 = controlsBindingControllerImpl;
        this.this$1 = loadSubscriber;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m111invoke();
        return H0.s.f314a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m111invoke() {
        ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.this$0.currentProvider;
        if (controlsProviderLifecycleManager != null) {
            IControlsSubscription iControlsSubscription = this.this$1.subscription;
            if (iControlsSubscription == null) {
                kotlin.jvm.internal.n.w("subscription");
                iControlsSubscription = null;
            }
            controlsProviderLifecycleManager.cancelSubscription(iControlsSubscription);
        }
    }
}
