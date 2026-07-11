package miuix.mgl;

import androidx.annotation.Nullable;
import java.nio.Buffer;
import miuix.mgl.BufferObject;
import miuix.mgl.MaterialEnums;

/* JADX INFO: loaded from: classes3.dex */
public class UniformBuffer extends BufferObject {

    public static class Builder extends BufferObject.Builder {
        public Builder() {
            super(nCreateBuilder());
        }

        public static Builder create() {
            return new Builder();
        }

        private static native long nBuild(long j2, long j3);

        private static native long nCreateBuilder();

        private static native void nProperty(long j2, String str, int i2);

        private static native void nPropertyArray(long j2, String str, int i2, int i3);

        public Builder property(String str, MaterialEnums.UniformValueType uniformValueType) {
            nProperty(getNativeObject(), str, uniformValueType.type);
            return this;
        }

        public Builder propertyArray(String str, MaterialEnums.UniformValueType uniformValueType, int i2) {
            nPropertyArray(getNativeObject(), str, uniformValueType.type, i2);
            return this;
        }

        @Override // miuix.mgl.BufferObject.Builder
        public Builder size(int i2) {
            super.size(i2);
            return this;
        }

        @Override // miuix.mgl.BufferObject.Builder
        public Builder usage(BufferObject.Usage usage) {
            super.usage(usage);
            return this;
        }

        @Override // miuix.mgl.BufferObject.Builder
        public UniformBuffer build() {
            return build((MglContext) null);
        }

        @Override // miuix.mgl.BufferObject.Builder
        public Builder data(float[] fArr) {
            super.data(fArr);
            return this;
        }

        @Override // miuix.mgl.BufferObject.Builder
        public UniformBuffer build(@Nullable MglContext mglContext) {
            long jNBuild = nBuild(getNativeObject(), mglContext == null ? 0L : mglContext.getNativeObject());
            destroyInternal();
            return new UniformBuffer(jNBuild);
        }

        @Override // miuix.mgl.BufferObject.Builder
        public Builder data(Buffer buffer) {
            super.data(buffer);
            return this;
        }
    }

    public UniformBuffer(long j2) {
        super(j2);
    }

    public static UniformBuffer createTemp(long j2) {
        UniformBuffer uniformBuffer = new UniformBuffer(0L);
        uniformBuffer.initTempNativeObject(j2);
        return uniformBuffer;
    }

    private static native void nSetBool(long j2, String str, boolean z2);

    private static native void nSetBoolArray(long j2, String str, int i2, boolean[] zArr);

    private static native void nSetFloat(long j2, String str, float f2);

    private static native void nSetFloatArray(long j2, String str, int i2, float[] fArr);

    private static native void nSetInt(long j2, String str, int i2);

    private static native void nSetIntArray(long j2, String str, int i2, int[] iArr);

    private static native void nSetUInt(long j2, String str, int i2);

    private static native void nSetUIntArray(long j2, String str, int i2, int[] iArr);

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

    public void setUInt(String str, int i2) {
        nSetUInt(getNativeObject(), str, i2);
    }

    public void setUIntArray(String str, MaterialEnums.UniformUIntType uniformUIntType, int[] iArr) {
        nSetUIntArray(getNativeObject(), str, uniformUIntType.type, iArr);
    }
}
