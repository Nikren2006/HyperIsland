package miui.systemui.flashlight.effect.particle;

import miuix.mgl.BufferObject;
import miuix.mgl.MaterialEnums;
import miuix.mgl.Shader;
import miuix.mgl.Texture;
import miuix.mgl.UniformBuffer;
import miuix.mgl.particle.Emitter;

/* JADX INFO: loaded from: classes3.dex */
public class DustEmitter extends Emitter {
    private final float[] mEndPoint;
    private final UniformBuffer mLineUniform;
    private final float[] mStartPoint;

    public DustEmitter(Shader shader, Texture texture) {
        super(shader);
        this.mStartPoint = new float[]{0.0f, 0.0f, 0.0f};
        this.mEndPoint = new float[]{0.0f, 0.0f, 0.0f};
        UniformBuffer.Builder builderUsage = UniformBuffer.Builder.create().usage(BufferObject.Usage.DYNAMIC);
        MaterialEnums.UniformValueType uniformValueType = MaterialEnums.UniformValueType.FLOAT3;
        UniformBuffer uniformBufferBuild = builderUsage.property("startPoint", uniformValueType).property("endPoint", uniformValueType).build(this.mContext);
        this.mLineUniform = uniformBufferBuild;
        this.mMaterial.setUniformBuffer("LineEmitterParam", uniformBufferBuild);
        if (texture != null) {
            this.mMaterial.setTexture("uTexMask", texture);
        }
    }

    @Override // miuix.mgl.particle.Emitter
    public void onDestroy(boolean z2) {
        this.mLineUniform.destroy(z2);
    }

    @Override // miuix.mgl.particle.Emitter
    public void onPreCompute() {
    }

    public void setLineY(float f2) {
        float f3 = (f2 * 2.0f) - 1.0f;
        float[] fArr = this.mStartPoint;
        fArr[0] = -0.5f;
        fArr[1] = f3;
        float[] fArr2 = this.mEndPoint;
        fArr2[0] = 0.5f;
        fArr2[1] = f3;
        UniformBuffer uniformBuffer = this.mLineUniform;
        MaterialEnums.UniformFloatType uniformFloatType = MaterialEnums.UniformFloatType.FLOAT3;
        uniformBuffer.setFloatArray("startPoint", uniformFloatType, fArr);
        this.mLineUniform.setFloatArray("endPoint", uniformFloatType, this.mEndPoint);
    }
}
