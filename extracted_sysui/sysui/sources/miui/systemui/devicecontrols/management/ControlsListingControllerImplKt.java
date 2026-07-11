package miui.systemui.devicecontrols.management;

import android.content.Context;
import java.util.concurrent.Executor;
import miui.systemui.devicecontrols.management.ServiceListing;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsListingControllerImplKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final ServiceListing createServiceListing(Context context, Executor executor) {
        ServiceListing.Builder builder = new ServiceListing.Builder(context);
        builder.setIntentAction("android.service.controls.ControlsProviderService");
        builder.setPermission("android.permission.BIND_CONTROLS");
        builder.setNoun("Controls Provider");
        builder.setSetting("controls_providers");
        builder.setTag("controls_providers");
        builder.setAddDeviceLockedFlags(true);
        builder.setBgExecutor(executor);
        ServiceListing serviceListingBuild = builder.build();
        kotlin.jvm.internal.n.f(serviceListingBuild, "build(...)");
        return serviceListingBuild;
    }
}
