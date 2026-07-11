package miui.systemui.flashlight.dagger;

import android.view.View;
import androidx.lifecycle.Lifecycle;
import kotlin.jvm.internal.n;
import miui.systemui.flashlight.view.MiFlashlightLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightComponentModule {
    @MiFlashlightScope
    public final Lifecycle provideLifecycle(MiFlashlightLayout view) {
        n.g(view, "view");
        return view.getLifecycle();
    }

    @MiFlashlightScope
    public final View provideView(MiFlashlightLayout view) {
        n.g(view, "view");
        return view;
    }
}
