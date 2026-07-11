package miui.systemui.flashlight.dagger;

import android.app.Activity;
import android.content.BroadcastReceiver;
import miui.systemui.flashlight.MiFlashlightActivity;
import miui.systemui.flashlight.MiFlashlightReceiver;

/* JADX INFO: loaded from: classes3.dex */
public interface MiFlashlightModule {
    BroadcastReceiver provideMiFlashLightReceiver(MiFlashlightReceiver miFlashlightReceiver);

    Activity provideMiFlashlightActivity(MiFlashlightActivity miFlashlightActivity);
}
