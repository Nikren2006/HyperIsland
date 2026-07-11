package miuix.mgl.particle;

import com.miui.maml.data.VariableNames;
import miuix.mgl.BufferObject;
import miuix.mgl.MaterialEnums;
import miuix.mgl.MglContext;
import miuix.mgl.ShaderStorageBuffer;
import miuix.mgl.UniformBuffer;
import miuix.mgl.math.Matrix4x4;

/* JADX INFO: loaded from: classes3.dex */
public class ParticleRenderer {
    protected MglContext mContext;
    Effector mEffector;
    Emitter mEmitter;
    private float mLastTime;
    Painter mPainter;
    UniformBuffer mTimeUniform;
    private float mFixedDeltaTime = 0.016f;
    private boolean mEnableFixedTime = false;

    public ParticleRenderer(Emitter emitter, Effector effector, Painter painter) {
        this.mContext = emitter.getContext();
        UniformBuffer.Builder builderUsage = UniformBuffer.Builder.create().usage(BufferObject.Usage.DYNAMIC);
        MaterialEnums.UniformValueType uniformValueType = MaterialEnums.UniformValueType.FLOAT;
        UniformBuffer uniformBufferBuild = builderUsage.property(VariableNames.VAR_TIME, uniformValueType).property("deltaTime", uniformValueType).build(this.mContext);
        this.mTimeUniform = uniformBufferBuild;
        this.mEmitter = emitter;
        this.mEffector = effector;
        this.mPainter = painter;
        emitter.setTimeParam(uniformBufferBuild);
        this.mEffector.setTimeParam(this.mTimeUniform);
        this.mPainter.setTimeParam(this.mTimeUniform);
        this.mPainter.setBaseEmitterParam(this.mEmitter.getBaseUniformBuffer());
    }

    public void destroy(boolean z2) {
        Emitter emitter = this.mEmitter;
        if (emitter != null) {
            emitter.destroy(z2);
            this.mEmitter = null;
        }
        Effector effector = this.mEffector;
        if (effector != null) {
            effector.destroy(z2);
            this.mEffector = null;
        }
        Painter painter = this.mPainter;
        if (painter != null) {
            painter.destroy(z2);
            this.mPainter = null;
        }
        UniformBuffer uniformBuffer = this.mTimeUniform;
        if (uniformBuffer != null) {
            uniformBuffer.destroy(z2);
            this.mTimeUniform = null;
        }
    }

    public void draw(float f2, float[] fArr) {
        if (this.mEmitter == null || this.mEffector == null || this.mPainter == null) {
            return;
        }
        if (fArr == null) {
            fArr = Matrix4x4.Companion.identity().getData();
        }
        float f3 = this.mLastTime;
        if (f3 == 0.0f || f3 > f2) {
            this.mLastTime = f2;
        }
        float f4 = f2 - this.mLastTime;
        this.mLastTime = f2;
        this.mTimeUniform.setFloat(VariableNames.VAR_TIME, f2);
        if (this.mEnableFixedTime) {
            f4 = this.mFixedDeltaTime;
        }
        if (f4 > 0.033f) {
            f4 = 0.033f;
        }
        this.mTimeUniform.setFloat("deltaTime", f4);
        this.mEmitter.compute();
        ShaderStorageBuffer particleBuffer = this.mEmitter.getParticleBuffer();
        int particleAmountX = this.mEmitter.getParticleAmountX();
        int particleAmountY = this.mEmitter.getParticleAmountY();
        int particleAmountZ = this.mEmitter.getParticleAmountZ();
        if (particleBuffer == null || particleAmountX == 0 || particleAmountY == 0 || particleAmountZ == 0) {
            return;
        }
        this.mEffector.compute(particleAmountX, particleAmountY, particleAmountZ, particleBuffer);
        this.mPainter.draw(fArr, particleAmountX * particleAmountY * particleAmountZ, particleBuffer);
    }

    public void enableFixedTime(boolean z2) {
        this.mEnableFixedTime = z2;
    }

    public Painter getPainter() {
        return this.mPainter;
    }

    public void setFixedDeltaTime(float f2) {
        this.mFixedDeltaTime = f2;
    }
}
