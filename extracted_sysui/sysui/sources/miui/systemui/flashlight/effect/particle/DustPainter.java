package miui.systemui.flashlight.effect.particle;

import miuix.mgl.Shader;
import miuix.mgl.Texture;
import miuix.mgl.particle.Painter;

/* JADX INFO: loaded from: classes3.dex */
public class DustPainter extends Painter {
    public DustPainter(Shader shader, Texture texture) {
        super(shader);
        if (texture != null) {
            this.mMaterial.setTexture("uTexColor", texture);
        }
        this.mMaterial.setFloat("alpha", 1.0f);
    }

    @Override // miuix.mgl.particle.Painter
    public void onDestroy(boolean z2) {
    }

    public void setAlpha(float f2) {
        this.mMaterial.setFloat("alpha", f2);
    }
}
