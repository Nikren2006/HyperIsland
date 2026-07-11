package miui.systemui.devicecontrols;

import android.content.ComponentName;
import android.view.View;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import miui.systemui.controlcenter.ConfigUtils;

/* JADX INFO: loaded from: classes3.dex */
public interface DeviceControlsModel extends ConfigUtils.OnConfigChangeListener {
    static /* synthetic */ void subscribeFavorite$default(DeviceControlsModel deviceControlsModel, ComponentName componentName, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: subscribeFavorite");
        }
        if ((i2 & 1) != 0) {
            componentName = null;
        }
        deviceControlsModel.subscribeFavorite(componentName);
    }

    void addDCEntryInfoCallback(Consumer<DCEntryInfo> consumer);

    void create();

    void destroy();

    View getOrCreateControlsView(Function0 function0, Function1 function1, Function0 function02);

    void removeDCEntryInfoCallback(Consumer<DCEntryInfo> consumer);

    void setListening(boolean z2);

    void showControlsView();

    void subscribeFavorite(ComponentName componentName);

    void unsubscribeFavorite();
}
