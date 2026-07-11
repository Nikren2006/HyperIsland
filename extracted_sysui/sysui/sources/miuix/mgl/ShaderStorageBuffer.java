package miuix.mgl;

import androidx.annotation.Nullable;
import java.nio.Buffer;
import miuix.mgl.BufferObject;

/* JADX INFO: loaded from: classes3.dex */
public class ShaderStorageBuffer extends BufferObject {

    public static class Builder extends BufferObject.Builder {
        public Builder() {
            super(nCreateBuilder());
        }

        public static Builder create() {
            return new Builder();
        }

        private static native long nBuild(long j2, long j3);

        private static native long nCreateBuilder();

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
        public ShaderStorageBuffer build() {
            return build((MglContext) null);
        }

        @Override // miuix.mgl.BufferObject.Builder
        public Builder data(float[] fArr) {
            super.data(fArr);
            return this;
        }

        @Override // miuix.mgl.BufferObject.Builder
        public ShaderStorageBuffer build(@Nullable MglContext mglContext) {
            long jNBuild = nBuild(getNativeObject(), mglContext == null ? 0L : mglContext.getNativeObject());
            destroyInternal();
            return new ShaderStorageBuffer(jNBuild);
        }

        @Override // miuix.mgl.BufferObject.Builder
        public Builder data(Buffer buffer) {
            super.data(buffer);
            return this;
        }
    }

    public ShaderStorageBuffer(long j2) {
        super(j2);
    }

    public static ShaderStorageBuffer create(long j2) {
        return new ShaderStorageBuffer(j2);
    }

    public static ShaderStorageBuffer createTemp(long j2) {
        ShaderStorageBuffer shaderStorageBuffer = new ShaderStorageBuffer(0L);
        shaderStorageBuffer.initTempNativeObject(j2);
        return shaderStorageBuffer;
    }
}
