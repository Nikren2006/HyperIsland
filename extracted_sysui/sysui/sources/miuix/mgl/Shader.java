package miuix.mgl;

import androidx.annotation.Nullable;
import miuix.mgl.utils.NativeObject;

/* JADX INFO: loaded from: classes3.dex */
public class Shader extends MglObject {

    public static class Builder extends NativeObject {
        public Builder() {
            initNativeObject(nCreateBuilder());
        }

        public static Builder create() {
            return new Builder();
        }

        private static native long nBuild(long j2, long j3);

        private static native void nComputeSource(long j2, String str);

        private static native long nCreateBuilder();

        private static native void nDestroyBuilder(long j2);

        private static native void nFragmentSource(long j2, String str);

        private static native void nName(long j2, String str);

        private static native void nType(long j2, int i2);

        private static native void nVertexSource(long j2, String str);

        public Shader build() {
            return build(null);
        }

        public Builder computeSource(String str) {
            nComputeSource(getNativeObject(), str);
            return this;
        }

        public Builder fragmentSource(String str) {
            nFragmentSource(getNativeObject(), str);
            return this;
        }

        public Builder name(String str) {
            nName(getNativeObject(), str);
            return this;
        }

        @Override // miuix.mgl.utils.NativeObject
        public void onDestroyNativeObject(long j2) {
            nDestroyBuilder(j2);
        }

        public Builder type(ShaderType shaderType) {
            nType(getNativeObject(), shaderType.ordinal());
            return this;
        }

        public Builder vertexSource(String str) {
            nVertexSource(getNativeObject(), str);
            return this;
        }

        public Shader build(@Nullable MglContext mglContext) {
            long jNBuild = nBuild(getNativeObject(), mglContext == null ? 0L : mglContext.getNativeObject());
            destroyInternal();
            Shader shader = new Shader(jNBuild);
            shader.mMglContext = mglContext;
            return shader;
        }
    }

    public enum ShaderType {
        VERTEX_FRAGMENT,
        COMPUTE
    }

    public Shader(long j2) {
        super(j2);
    }

    private static native int nGetVertexAttLocation(long j2, String str);

    private static native void nSetName(long j2, String str);

    public int getVertexAttLocation(String str) {
        return nGetVertexAttLocation(getNativeObject(), str);
    }

    public void setName(String str) {
        nSetName(getNativeObject(), str);
    }
}
