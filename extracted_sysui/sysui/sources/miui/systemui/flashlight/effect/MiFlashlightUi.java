package miui.systemui.flashlight.effect;

import android.content.Context;
import android.view.ViewStub;
import miui.systemui.flashlight.effect.FlashlightRender;

/* JADX INFO: loaded from: classes3.dex */
public abstract class MiFlashlightUi {
    public abstract void init(Context context, boolean z2, ViewStub viewStub, FlashlightRender.OnFirstFrameDrawListener onFirstFrameDrawListener);

    public void pause() {
    }

    public void resume() {
    }

    public void setProgress(float f2) {
    }

    public void setStatus(boolean z2) {
    }
}
