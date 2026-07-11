package miui.systemui.devicecontrols.ui;

import android.service.controls.Control;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultBehavior implements Behavior {
    public ControlViewHolder cvh;

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void bind(ControlWithState cws, int i2) {
        kotlin.jvm.internal.n.g(cws, "cws");
        ControlViewHolder cvh = getCvh();
        Control control = cws.getControl();
        CharSequence statusText = control != null ? control.getStatusText() : null;
        if (statusText == null) {
            statusText = "";
        }
        ControlViewHolder.setStatusText$default(cvh, statusText, false, 2, null);
        ControlViewHolder.applyRenderInfo$miui_devicecontrols_release$default(getCvh(), false, i2, false, 4, null);
    }

    public final ControlViewHolder getCvh() {
        ControlViewHolder controlViewHolder = this.cvh;
        if (controlViewHolder != null) {
            return controlViewHolder;
        }
        kotlin.jvm.internal.n.w("cvh");
        return null;
    }

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void initialize(ControlViewHolder cvh) {
        kotlin.jvm.internal.n.g(cvh, "cvh");
        setCvh(cvh);
    }

    public final void setCvh(ControlViewHolder controlViewHolder) {
        kotlin.jvm.internal.n.g(controlViewHolder, "<set-?>");
        this.cvh = controlViewHolder;
    }
}
