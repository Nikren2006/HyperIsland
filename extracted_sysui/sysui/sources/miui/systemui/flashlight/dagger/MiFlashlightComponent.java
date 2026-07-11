package miui.systemui.flashlight.dagger;

import android.view.View;
import miui.systemui.flashlight.MiFlashlightController;
import miui.systemui.flashlight.view.MiFlashlightLayout;

/* JADX INFO: loaded from: classes3.dex */
@MiFlashlightScope
public interface MiFlashlightComponent {

    public interface Factory {
        MiFlashlightComponent create(MiFlashlightLayout miFlashlightLayout);
    }

    MiFlashlightController createController();

    View getRootView();
}
