package miui.systemui.devicecontrols.ui;

import android.service.controls.Control;

/* JADX INFO: loaded from: classes3.dex */
public interface ControlActionCoordinator {
    void closeDialogs();

    void drag(boolean z2);

    void enableActionOnTouch(String str);

    void longPress(ControlViewHolder controlViewHolder);

    void runPendingAction(String str);

    void setValue(ControlViewHolder controlViewHolder, String str, float f2);

    void toggle(ControlViewHolder controlViewHolder, String str, boolean z2);

    void touch(ControlViewHolder controlViewHolder, String str, Control control);
}
