package miui.systemui.devicecontrols.management;

import android.content.ComponentName;
import java.util.List;
import miui.systemui.devicecontrols.ControlsServiceInfo;
import miui.systemui.devicecontrols.util.UserAwareController;
import miui.systemui.policy.CallbackController;

/* JADX INFO: loaded from: classes3.dex */
public interface ControlsListingController extends CallbackController<ControlsListingCallback>, UserAwareController {

    @FunctionalInterface
    public interface ControlsListingCallback {
        void onServicesUpdated(List<? extends ControlsServiceInfo> list);
    }

    default CharSequence getAppLabel(ComponentName name) {
        kotlin.jvm.internal.n.g(name, "name");
        return "";
    }

    List<ControlsServiceInfo> getCurrentServices();

    void release();

    void reloadServices(boolean z2);
}
