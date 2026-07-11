package miui.systemui.flashlight.effect;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import kotlin.jvm.internal.n;
import miui.systemui.flashlight.effect.FlashlightRender;
import miui.systemui.util.DeviceUtils;
import miuix.mgl.android.GLTextureView;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightUiOpenGl extends MiFlashlightUi {
    private FlashlightRender flashlightRender;

    @Override // miui.systemui.flashlight.effect.MiFlashlightUi
    public void init(Context context, boolean z2, ViewStub flashlightViewStub, FlashlightRender.OnFirstFrameDrawListener firstFrameDrawListener) {
        n.g(context, "context");
        n.g(flashlightViewStub, "flashlightViewStub");
        n.g(firstFrameDrawListener, "firstFrameDrawListener");
        GLTextureView gLTextureView = new GLTextureView(context);
        ViewParent parent = flashlightViewStub.getParent();
        n.e(parent, "null cannot be cast to non-null type android.view.ViewGroup");
        ViewGroup viewGroup = (ViewGroup) parent;
        int iIndexOfChild = viewGroup.indexOfChild(flashlightViewStub);
        viewGroup.removeViewAt(iIndexOfChild);
        viewGroup.addView(gLTextureView, iIndexOfChild);
        FlashlightRender flashlightRender = new FlashlightRender(context);
        flashlightRender.setProgress(z2 ? 1.0f : 0.0f);
        flashlightRender.attachToView(gLTextureView);
        flashlightRender.setOnFirstFrameDrawListener(firstFrameDrawListener);
        this.flashlightRender = flashlightRender;
    }

    @Override // miui.systemui.flashlight.effect.MiFlashlightUi
    public void pause() {
        FlashlightRender flashlightRender;
        if (DeviceUtils.isLowEndDevice() || (flashlightRender = this.flashlightRender) == null) {
            return;
        }
        flashlightRender.pause();
    }

    @Override // miui.systemui.flashlight.effect.MiFlashlightUi
    public void resume() {
        FlashlightRender flashlightRender;
        if (DeviceUtils.isLowEndDevice() || (flashlightRender = this.flashlightRender) == null) {
            return;
        }
        flashlightRender.resume();
    }

    @Override // miui.systemui.flashlight.effect.MiFlashlightUi
    public void setProgress(float f2) {
        if (!DeviceUtils.isLowEndDevice()) {
            FlashlightRender flashlightRender = this.flashlightRender;
            if (flashlightRender == null) {
                return;
            }
            flashlightRender.setProgress(f2);
            return;
        }
        FlashlightRender flashlightRender2 = this.flashlightRender;
        if (flashlightRender2 != null) {
            flashlightRender2.resume();
        }
        FlashlightRender flashlightRender3 = this.flashlightRender;
        if (flashlightRender3 != null) {
            flashlightRender3.setProgress(f2);
        }
        FlashlightRender flashlightRender4 = this.flashlightRender;
        if (flashlightRender4 != null) {
            flashlightRender4.pause();
        }
    }
}
