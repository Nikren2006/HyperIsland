package miui.systemui.flashlight.effect.particle;

import miuix.mgl.Shader;
import miuix.mgl.Texture;
import miuix.mgl.particle.Effector;

/* JADX INFO: loaded from: classes3.dex */
public class DustEffector extends Effector {
    private float mSpeed;

    public DustEffector(Shader shader) {
        super(shader);
        this.mSpeed = -5.0f;
        this.mMaterial.setFloat("speed", -5.0f);
    }

    @Override // miuix.mgl.particle.Effector
    public void onDestroy(boolean z2) {
    }

    public void setSpeed(float f2) {
        this.mSpeed = f2;
        this.mMaterial.setFloat("speed", f2);
    }

    public DustEffector(Shader shader, Texture texture) {
        super(shader);
        this.mSpeed = -5.0f;
        this.mMaterial.setFloat("speed", -5.0f);
        this.mMaterial.setTexture("perlinNoise", texture);
    }
}
