package miui.systemui.devicecontrols;

import android.content.ComponentName;
import android.view.View;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import miui.systemui.controlcenter.ConfigUtils;

/* JADX INFO: loaded from: classes3.dex */
public interface DeviceControlsPresenter extends ConfigUtils.OnConfigChangeListener {
    static /* synthetic */ void create$default(DeviceControlsPresenter deviceControlsPresenter, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: create");
        }
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        deviceControlsPresenter.create(z2);
    }

    static /* synthetic */ void onDCPreShow$default(DeviceControlsPresenter deviceControlsPresenter, ComponentName componentName, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onDCPreShow");
        }
        if ((i2 & 1) != 0) {
            componentName = null;
        }
        deviceControlsPresenter.onDCPreShow(componentName);
    }

    void addDCEntryInfoCallback(Consumer<DCEntryInfo> consumer);

    void create(boolean z2);

    void destroy();

    View getOrCreateControlsView(Function0 function0, Function1 function1, Function0 function02);

    void onDCPreHide();

    void onDCPreHideFinished();

    void onDCPreShow(ComponentName componentName);

    void onDCShowFinished();

    void removeDCEntryInfoCallback(Consumer<DCEntryInfo> consumer);

    void setListening(boolean z2);
}
