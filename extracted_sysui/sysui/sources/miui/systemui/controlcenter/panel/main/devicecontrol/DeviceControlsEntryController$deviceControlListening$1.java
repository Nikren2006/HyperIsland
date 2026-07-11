package miui.systemui.controlcenter.panel.main.devicecontrol;

import H0.s;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.devicecontrols.DeviceControlsPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceControlsEntryController$deviceControlListening$1 extends o implements Function1 {
    final /* synthetic */ boolean $value;
    final /* synthetic */ DeviceControlsEntryController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceControlsEntryController$deviceControlListening$1(boolean z2, DeviceControlsEntryController deviceControlsEntryController) {
        super(1);
        this.$value = z2;
        this.this$0 = deviceControlsEntryController;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DeviceControlsPresenter) obj);
        return s.f314a;
    }

    public final void invoke(DeviceControlsPresenter it) {
        n.g(it, "it");
        if (this.$value) {
            it.addDCEntryInfoCallback(this.this$0.dcEntryInfoCallback);
        } else {
            it.removeDCEntryInfoCallback(this.this$0.dcEntryInfoCallback);
        }
        it.setListening(this.$value);
    }
}
