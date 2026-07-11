package miui.systemui.devicecontrols.dagger;

import android.content.Context;
import miui.systemui.devicecontrols.DeviceControlsModel;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControls;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;

/* JADX INFO: loaded from: classes3.dex */
@DeviceControlsScope
public interface DeviceControlsComponent {

    public interface Factory {
        DeviceControlsComponent create(@DeviceControls Context context);
    }

    DeviceControlsModel getDeviceControlsModel();
}
