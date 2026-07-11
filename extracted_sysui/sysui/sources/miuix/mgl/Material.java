package miuix.mgl;

import java.util.HashMap;
import miuix.mgl.MaterialEnums;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Material extends MglObject {
    HashMap<Integer, MglObject> mRefIntKey;
    HashMap<String, MglObject> mRefStringKey;

    public Material(long j2) {
        super(j2);
        this.mRefStringKey = new HashMap<>();
        this.mRefIntKey = new HashMap<>();
    }

    private static native void nActive(long j2);

    private static native void nActiveTexture(long j2, String str, int i2);

    private static native void nDeActive(long j2);

    private static native int nGetPropertyIndex(long j2, String str);

    private static native long nGetShader(long j2);

    private static native void nSetBool(long j2, String str, boolean z2);

    private static native void nSetBoolArray(long j2, String str, int i2, boolean[] zArr);

    private static native void nSetBoolArrayWithIndex(long j2, int i2, int i3, boolean[] zArr);

    private static native void nSetBoolWithIndex(long j2, int i2, boolean z2);

    private static native void nSetFloat(long j2, String str, float f2);

    private static native void nSetFloatArray(long j2, String str, int i2, float[] fArr);

    private static native void nSetFloatArrayWithIndex(long j2, int i2, int i3, float[] fArr);

    private static native void nSetFloatWithIndex(long j2, int i2, float f2);

    private static native void nSetInt(long j2, String str, int i2);

    private static native void nSetIntArray(long j2, String str, int i2, int[] iArr);

    private static native void nSetIntArrayWithIndex(long j2, int i2, int i3, int[] iArr);

    private static native void nSetIntWithIndex(long j2, int i2, int i3);

    private static native void nSetShaderStorageBuffer(long j2, String str, long j3);

    private static native void nSetShaderStorageBufferWithIndex(long j2, int i2, long j3);

    private static native void nSetTexture(long j2, String str, long j3);

    private static native void nSetTextureWithIndex(long j2, int i2, long j3);

    private static native void nSetUInt(long j2, String str, int i2);

    private static native void nSetUIntArray(long j2, String str, int i2, int[] iArr);

    private static native void nSetUIntArrayWithIndex(long j2, int i2, int i3, int[] iArr);

    private static native void nSetUIntWithIndex(long j2, int i2, int i3);

    private static native void nSetUniformBuffer(long j2, String str, long j3);

    private static native void nSetUniformBufferWithIndex(long j2, int i2, long j3);

    public void active() {
        nActive(getNativeObject());
    }

    public void activeTexture(String str, int i2) {
        nActiveTexture(getNativeObject(), str, i2);
    }

    public void addRef(String str, MglObject mglObject) {
        this.mRefStringKey.put(str, mglObject);
    }

    public void deActive() {
        nDeActive(getNativeObject());
    }

    public int getPropertyIndex(String str) {
        return nGetPropertyIndex(getNativeObject(), str);
    }

    public void setBool(String str, boolean z2) {
        nSetBool(getNativeObject(), str, z2);
    }

    public void setBoolArray(String str, MaterialEnums.UniformBoolType uniformBoolType, boolean[] zArr) {
        nSetBoolArray(getNativeObject(), str, uniformBoolType.type, zArr);
    }

    public void setFloat(String str, float f2) {
        nSetFloat(getNativeObject(), str, f2);
    }

    public void setFloatArray(String str, MaterialEnums.UniformFloatType uniformFloatType, float[] fArr) {
        nSetFloatArray(getNativeObject(), str, uniformFloatType.type, fArr);
    }

    public void setInt(String str, int i2) {
        nSetInt(getNativeObject(), str, i2);
    }

    public void setIntArray(String str, MaterialEnums.UniformIntType uniformIntType, int[] iArr) {
        nSetIntArray(getNativeObject(), str, uniformIntType.type, iArr);
    }

    public void setShaderStorageBuffer(String str, BufferObject bufferObject) {
        addRef(str, bufferObject);
        nSetShaderStorageBuffer(getNativeObject(), str, bufferObject == null ? 0L : bufferObject.getNativeObject());
    }

    public void setTexture(String str, Texture texture) {
        addRef(str, texture);
        nSetTexture(getNativeObject(), str, texture == null ? 0L : texture.getNativeObject());
    }

    public void setUInt(String str, int i2) {
        nSetUInt(getNativeObject(), str, i2);
    }

    public void setUIntArray(String str, MaterialEnums.UniformUIntType uniformUIntType, int[] iArr) {
        nSetUIntArray(getNativeObject(), str, uniformUIntType.type, iArr);
    }

    public void setUniformBuffer(String str, UniformBuffer uniformBuffer) {
        addRef(str, uniformBuffer);
        nSetUniformBuffer(getNativeObject(), str, uniformBuffer == null ? 0L : uniformBuffer.getNativeObject());
    }

    public void addRef(int i2, MglObject mglObject) {
        this.mRefIntKey.put(Integer.valueOf(i2), mglObject);
    }

    public void setBool(int i2, boolean z2) {
        nSetBoolWithIndex(getNativeObject(), i2, z2);
    }

    public void setBoolArray(int i2, MaterialEnums.UniformBoolType uniformBoolType, boolean[] zArr) {
        nSetBoolArrayWithIndex(getNativeObject(), i2, uniformBoolType.type, zArr);
    }

    public void setFloat(int i2, float f2) {
        nSetFloatWithIndex(getNativeObject(), i2, f2);
    }

    public void setFloatArray(int i2, MaterialEnums.UniformFloatType uniformFloatType, float[] fArr) {
        nSetFloatArrayWithIndex(getNativeObject(), i2, uniformFloatType.type, fArr);
    }

    public void setInt(int i2, int i3) {
        nSetIntWithIndex(getNativeObject(), i2, i3);
    }

    public void setIntArray(int i2, MaterialEnums.UniformIntType uniformIntType, int[] iArr) {
        nSetIntArrayWithIndex(getNativeObject(), i2, uniformIntType.type, iArr);
    }

    public void setUInt(int i2, int i3) {
        nSetUIntWithIndex(getNativeObject(), i2, i3);
    }

    public void setUIntArray(int i2, MaterialEnums.UniformUIntType uniformUIntType, int[] iArr) {
        nSetUIntArrayWithIndex(getNativeObject(), i2, uniformUIntType.type, iArr);
    }

    public void setShaderStorageBuffer(int i2, BufferObject bufferObject) {
        addRef(i2, bufferObject);
        nSetShaderStorageBufferWithIndex(getNativeObject(), i2, bufferObject.getNativeObject());
    }

    public void setTexture(int i2, Texture texture) {
        addRef(i2, texture);
        nSetTextureWithIndex(getNativeObject(), i2, texture.getNativeObject());
    }

    public void setUniformBuffer(int i2, UniformBuffer uniformBuffer) {
        addRef(i2, uniformBuffer);
        nSetUniformBufferWithIndex(getNativeObject(), i2, uniformBuffer.getNativeObject());
    }
}
