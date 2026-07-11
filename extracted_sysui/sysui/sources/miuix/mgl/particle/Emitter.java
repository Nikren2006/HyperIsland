package miuix.mgl.particle;

import android.util.Log;
import miuix.mgl.BufferObject;
import miuix.mgl.ComputeMaterial;
import miuix.mgl.MaterialEnums;
import miuix.mgl.MglContext;
import miuix.mgl.Shader;
import miuix.mgl.ShaderStorageBuffer;
import miuix.mgl.UniformBuffer;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Emitter {
    int mAmountX;
    int mAmountY;
    int mAmountZ;
    protected MglContext mContext;
    int mFixedAmountX;
    int mFixedAmountY;
    int mFixedAmountZ;
    int mGroupX;
    int mGroupY;
    int mGroupZ;
    protected ComputeMaterial mMaterial;
    ShaderStorageBuffer mParticleBuffer;
    private UniformBuffer mTimeUniformBuffer;
    protected UniformBuffer mUniformBuffer;
    float mLifeMin = 0.5f;
    float mLifeMax = 0.5f;
    int mEmitterType = 0;
    int mLoop = 1;
    float mFrequency = 10.0f;
    float mStartSizeMin = 0.1f;
    float mStartSizeMax = 0.1f;
    float[] mScale = {1.0f, 1.0f, 1.0f};
    boolean mReset = true;
    float mPreWarmScale = 0.0f;
    boolean mEnablePreWarm = false;
    boolean mSSBODirty = true;

    public Emitter(Shader shader) {
        this.mGroupX = 0;
        this.mGroupY = 0;
        this.mGroupZ = 0;
        this.mContext = shader.getContext();
        this.mMaterial = ComputeMaterial.create(shader);
        UniformBuffer.Builder builderUsage = UniformBuffer.Builder.create().usage(BufferObject.Usage.DYNAMIC);
        MaterialEnums.UniformValueType uniformValueType = MaterialEnums.UniformValueType.FLOAT;
        UniformBuffer.Builder builderProperty = builderUsage.property("lifeMin", uniformValueType).property("lifeMax", uniformValueType);
        MaterialEnums.UniformValueType uniformValueType2 = MaterialEnums.UniformValueType.INT;
        UniformBuffer uniformBufferBuild = builderProperty.property("emitterType", uniformValueType2).property("loop", uniformValueType2).property("frequency", uniformValueType).property("startSizeMin", uniformValueType).property("startSizeMax", uniformValueType).property("scale", MaterialEnums.UniformValueType.FLOAT3).property("amount", uniformValueType2).property("reset", uniformValueType2).property("preWarmScale", uniformValueType).build(this.mContext);
        this.mUniformBuffer = uniformBufferBuild;
        uniformBufferBuild.setFloat("lifeMin", this.mLifeMin);
        this.mUniformBuffer.setFloat("lifeMax", this.mLifeMax);
        this.mUniformBuffer.setInt("emitterType", this.mEmitterType);
        this.mUniformBuffer.setInt("loop", this.mLoop);
        this.mUniformBuffer.setFloat("frequency", this.mFrequency);
        this.mUniformBuffer.setFloat("startSizeMin", this.mStartSizeMin);
        this.mUniformBuffer.setFloat("startSizeMax", this.mStartSizeMax);
        this.mUniformBuffer.setFloatArray("scale", MaterialEnums.UniformFloatType.FLOAT3, this.mScale);
        this.mUniformBuffer.setFloat("preWarmScale", this.mPreWarmScale);
        this.mMaterial.setUniformBuffer("EmitterParam", this.mUniformBuffer);
        this.mMaterial.setMemoryBarrier(ComputeMaterial.MemoryBarrier.SHADER_STORAGE_BARRIER);
        this.mGroupX = this.mMaterial.getWorkGroupX();
        this.mGroupY = this.mMaterial.getWorkGroupY();
        this.mGroupZ = this.mMaterial.getWorkGroupZ();
    }

    public static int calculateAmount(int i2, int i3) {
        return i2 > i3 ? i2 - (i2 % i3) : i3;
    }

    private void setPreWarmScale(float f2) {
        this.mPreWarmScale = f2;
        this.mUniformBuffer.setFloat("preWarmScale", f2);
    }

    private void updateSSBO() {
        int i2;
        if (this.mSSBODirty) {
            this.mSSBODirty = false;
            this.mReset = true;
            if (this.mEnablePreWarm) {
                setPreWarmScale(0.8f);
            }
            if (this.mEmitterType == 1 && (i2 = this.mFixedAmountX) > 0) {
                this.mAmountX = calculateAmount(i2, this.mGroupX);
                this.mAmountY = calculateAmount(this.mFixedAmountY, this.mGroupY);
                this.mAmountZ = calculateAmount(this.mFixedAmountZ, this.mGroupZ);
            } else if (this.mMaterial.getWorkGroupY() != 1 || this.mMaterial.getWorkGroupZ() != 1) {
                Log.e("mgl_native", "Particle emitter updateSSBO fail, shader group y or z is not 1");
                return;
            } else {
                this.mAmountX = calculateAmount((int) Math.ceil((((double) (this.mLifeMin + this.mLifeMax)) * 0.5d) / (1.0d / ((double) this.mFrequency))), this.mGroupX);
                this.mAmountY = 1;
                this.mAmountZ = 1;
            }
            int iByteSizeSTD430 = this.mAmountX * this.mAmountY * this.mAmountZ * Particle.byteSizeSTD430();
            ShaderStorageBuffer shaderStorageBuffer = this.mParticleBuffer;
            if (shaderStorageBuffer == null) {
                ShaderStorageBuffer shaderStorageBufferBuild = ShaderStorageBuffer.Builder.create().usage(BufferObject.Usage.STATIC).size(iByteSizeSTD430).build(this.mContext);
                this.mParticleBuffer = shaderStorageBufferBuild;
                this.mMaterial.setShaderStorageBuffer("Particles", shaderStorageBufferBuild);
            } else if (shaderStorageBuffer.getSize() < iByteSizeSTD430) {
                this.mParticleBuffer.resize(iByteSizeSTD430 * 2);
                this.mReset = true;
            }
            this.mUniformBuffer.setInt("amount", this.mAmountX * this.mAmountY * this.mAmountZ);
            this.mMaterial.setDispatch(this.mAmountX / this.mGroupX, this.mAmountY / this.mGroupY, this.mAmountZ / this.mGroupZ);
        }
    }

    public final void compute() {
        updateSSBO();
        if (this.mParticleBuffer != null) {
            if (this.mReset) {
                this.mUniformBuffer.setInt("reset", 1);
                if (this.mEnablePreWarm) {
                    setPreWarmScale(0.8f);
                }
            }
            onPreCompute();
            this.mMaterial.active();
            if (this.mReset) {
                this.mReset = false;
                this.mUniformBuffer.setInt("reset", 0);
            }
            if (this.mPreWarmScale > 0.0f) {
                setPreWarmScale(0.0f);
            }
        }
    }

    public final void destroy(boolean z2) {
        onDestroy(z2);
        ComputeMaterial computeMaterial = this.mMaterial;
        if (computeMaterial != null) {
            computeMaterial.destroy(z2);
        }
        UniformBuffer uniformBuffer = this.mUniformBuffer;
        if (uniformBuffer != null) {
            uniformBuffer.destroy(z2);
        }
        ShaderStorageBuffer shaderStorageBuffer = this.mParticleBuffer;
        if (shaderStorageBuffer != null) {
            shaderStorageBuffer.destroy(z2);
        }
    }

    public void enablePreWarm(boolean z2) {
        this.mEnablePreWarm = z2;
    }

    public UniformBuffer getBaseUniformBuffer() {
        return this.mUniformBuffer;
    }

    public MglContext getContext() {
        return this.mContext;
    }

    public int getGroupX() {
        return this.mGroupX;
    }

    public int getGroupY() {
        return this.mGroupY;
    }

    public int getGroupZ() {
        return this.mGroupZ;
    }

    public int getParticleAmountX() {
        return this.mAmountX;
    }

    public int getParticleAmountY() {
        return this.mAmountY;
    }

    public int getParticleAmountZ() {
        return this.mAmountZ;
    }

    public ShaderStorageBuffer getParticleBuffer() {
        return this.mParticleBuffer;
    }

    public abstract void onDestroy(boolean z2);

    public abstract void onPreCompute();

    public void reset() {
        this.mReset = true;
    }

    public void setExplode(boolean z2) {
        this.mEmitterType = z2 ? 1 : 0;
        this.mReset = true;
        this.mUniformBuffer.setInt("emitterType", z2 ? 1 : 0);
    }

    public void setFixedAmount(int i2, int i3, int i4) {
        this.mFixedAmountX = i2;
        this.mFixedAmountY = i3;
        this.mFixedAmountZ = i4;
        this.mSSBODirty = true;
    }

    public void setFrequency(float f2) {
        if (this.mFrequency != f2) {
            this.mFrequency = f2;
            this.mSSBODirty = true;
            this.mUniformBuffer.setFloat("frequency", f2);
        }
    }

    public void setLife(float f2, float f3) {
        this.mLifeMin = f2;
        this.mLifeMax = f3;
        this.mSSBODirty = true;
        this.mUniformBuffer.setFloat("lifeMin", f2);
        this.mUniformBuffer.setFloat("lifeMax", this.mLifeMax);
    }

    public void setLoop(boolean z2) {
        this.mLoop = z2 ? 1 : 0;
        this.mUniformBuffer.setInt("loop", z2 ? 1 : 0);
    }

    public void setScale(float f2, float f3, float f4) {
        float[] fArr = this.mScale;
        fArr[0] = f2;
        fArr[1] = f3;
        fArr[2] = f4;
        this.mUniformBuffer.setFloatArray("scale", MaterialEnums.UniformFloatType.FLOAT3, fArr);
    }

    public void setStartSize(float f2, float f3) {
        this.mStartSizeMin = f2;
        this.mStartSizeMax = f3;
        this.mUniformBuffer.setFloat("startSizeMin", f2);
        this.mUniformBuffer.setFloat("startSizeMax", this.mStartSizeMax);
    }

    public void setTimeParam(UniformBuffer uniformBuffer) {
        this.mTimeUniformBuffer = uniformBuffer;
        this.mMaterial.setUniformBuffer("TimeParam", uniformBuffer);
    }
}
