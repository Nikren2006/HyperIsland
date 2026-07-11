package miuix.mgl.particle;

import miuix.mgl.ComputeMaterial;
import miuix.mgl.Shader;
import miuix.mgl.ShaderStorageBuffer;
import miuix.mgl.UniformBuffer;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Effector {
    int mGroupX;
    int mGroupY;
    int mGroupZ;
    protected ComputeMaterial mMaterial;

    public Effector(Shader shader) {
        this.mGroupX = 0;
        this.mGroupY = 0;
        this.mGroupZ = 0;
        ComputeMaterial computeMaterialCreate = ComputeMaterial.create(shader);
        this.mMaterial = computeMaterialCreate;
        this.mGroupX = computeMaterialCreate.getWorkGroupX();
        this.mGroupY = this.mMaterial.getWorkGroupY();
        this.mGroupZ = this.mMaterial.getWorkGroupZ();
        this.mMaterial.setMemoryBarrier(ComputeMaterial.MemoryBarrier.SHADER_STORAGE_BARRIER);
    }

    public void compute(int i2, ShaderStorageBuffer shaderStorageBuffer) {
        compute(i2, 1, 1, shaderStorageBuffer);
    }

    public final void destroy(boolean z2) {
        onDestroy(z2);
        ComputeMaterial computeMaterial = this.mMaterial;
        if (computeMaterial != null) {
            computeMaterial.destroy(z2);
        }
    }

    public abstract void onDestroy(boolean z2);

    public void setTimeParam(UniformBuffer uniformBuffer) {
        this.mMaterial.setUniformBuffer("TimeParam", uniformBuffer);
    }

    public void compute(int i2, int i3, int i4, ShaderStorageBuffer shaderStorageBuffer) {
        this.mMaterial.setShaderStorageBuffer("Particles", shaderStorageBuffer);
        this.mMaterial.setDispatch(i2 / this.mGroupX, i3 / this.mGroupY, i4 / this.mGroupZ);
        this.mMaterial.active();
    }
}
