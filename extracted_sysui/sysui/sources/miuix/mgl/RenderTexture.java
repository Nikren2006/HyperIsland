package miuix.mgl;

import androidx.annotation.Nullable;
import miuix.mgl.utils.NativeObject;

/* JADX INFO: loaded from: classes3.dex */
public class RenderTexture extends Texture {

    public enum AttachmentPoint {
        COLOR0(0),
        COLOR(0),
        COLOR1(1),
        COLOR2(2),
        COLOR3(3),
        COLOR4(4),
        COLOR5(5),
        COLOR6(6),
        COLOR7(7),
        DEPTH(8),
        STENCIL(9),
        DEPTH_STENCIL(10);

        int point;

        AttachmentPoint(int i2) {
            this.point = i2;
        }
    }

    public static class Builder extends NativeObject {
        public Builder() {
            initNativeObject(nCreateBuilder());
        }

        public static Builder create() {
            return new Builder();
        }

        private static native long nBuild(long j2, long j3);

        private static native void nClearColor(long j2, float f2, float f3, float f4, float f5);

        private static native void nClearStencil(long j2, int i2);

        private static native void nColorFormat(long j2, int i2);

        private static native void nColorFormatV2(long j2, int i2, int i3);

        private static native long nCreateBuilder();

        private static native void nDepthFormat(long j2, int i2);

        private static native void nDepthStencilFormat(long j2, int i2);

        private static native void nDestroyBuilder(long j2);

        private static native void nEnable(long j2, int i2);

        private static native void nHeight(long j2, int i2);

        private static native void nIsDefault(long j2, boolean z2);

        private static native void nLoadable(long j2, int i2, boolean z2);

        private static native void nSamplable(long j2, int i2, boolean z2);

        private static native void nStorable(long j2, int i2, boolean z2);

        private static native void nWidth(long j2, int i2);

        public RenderTexture build() {
            return build(null);
        }

        public Builder clearColor(float f2, float f3, float f4, float f5) {
            nClearColor(getNativeObject(), f2, f3, f4, f5);
            return this;
        }

        public Builder clearStencil(int i2) {
            nClearStencil(getNativeObject(), i2);
            return this;
        }

        public Builder colorFormat(ColorFormat colorFormat) {
            nColorFormat(getNativeObject(), colorFormat.format);
            return this;
        }

        public Builder depthFormat(DepthFormat depthFormat) {
            nDepthFormat(getNativeObject(), depthFormat.format);
            return this;
        }

        public Builder depthStencilFormat(DepthStencilFormat depthStencilFormat) {
            nDepthStencilFormat(getNativeObject(), depthStencilFormat.format);
            return this;
        }

        public Builder enable(AttachmentPoint attachmentPoint) {
            nEnable(getNativeObject(), attachmentPoint.point);
            return this;
        }

        public Builder height(int i2) {
            nHeight(getNativeObject(), i2);
            return this;
        }

        public Builder isDefault(boolean z2) {
            nIsDefault(getNativeObject(), z2);
            return this;
        }

        public Builder loadable(AttachmentPoint attachmentPoint, boolean z2) {
            nLoadable(getNativeObject(), attachmentPoint.point, z2);
            return this;
        }

        @Override // miuix.mgl.utils.NativeObject
        public void onDestroyNativeObject(long j2) {
            nDestroyBuilder(j2);
        }

        public Builder samplable(AttachmentPoint attachmentPoint, boolean z2) {
            nSamplable(getNativeObject(), attachmentPoint.point, z2);
            return this;
        }

        public Builder storable(AttachmentPoint attachmentPoint, boolean z2) {
            nStorable(getNativeObject(), attachmentPoint.point, z2);
            return this;
        }

        public Builder width(int i2) {
            nWidth(getNativeObject(), i2);
            return this;
        }

        public RenderTexture build(@Nullable MglContext mglContext) {
            RenderTexture renderTexture = new RenderTexture(nBuild(getNativeObject(), mglContext == null ? 0L : mglContext.getNativeObject()));
            destroyInternal();
            return renderTexture;
        }

        public Builder colorFormat(ColorAttachmentPoint colorAttachmentPoint, ColorFormat colorFormat) {
            nColorFormatV2(getNativeObject(), colorAttachmentPoint.point, colorFormat.format);
            return this;
        }
    }

    public enum ColorAttachmentPoint {
        COLOR0(AttachmentPoint.COLOR0.point),
        COLOR1(AttachmentPoint.COLOR1.point),
        COLOR2(AttachmentPoint.COLOR2.point),
        COLOR3(AttachmentPoint.COLOR3.point),
        COLOR4(AttachmentPoint.COLOR4.point),
        COLOR5(AttachmentPoint.COLOR5.point),
        COLOR6(AttachmentPoint.COLOR6.point),
        COLOR7(AttachmentPoint.COLOR7.point);

        int point;

        ColorAttachmentPoint(int i2) {
            this.point = i2;
        }
    }

    public enum ColorFormat {
        RGB8(32849),
        RGBA8(32856);

        int format;

        ColorFormat(int i2) {
            this.format = i2;
        }
    }

    public enum DepthFormat {
        DEPTH_16(33189),
        DEPTH_24(33190),
        DEPTH_32F(36012);

        int format;

        DepthFormat(int i2) {
            this.format = i2;
        }
    }

    public enum DepthStencilFormat {
        DEPTH24_STENCIL8(35056),
        DEPTH32F_STENCIL8(36013);

        int format;

        DepthStencilFormat(int i2) {
            this.format = i2;
        }
    }

    public RenderTexture(long j2) {
        super(j2);
    }

    private static native void nActive(long j2, int i2);

    private static native void nBlit(long j2, long j3, int i2, int i3);

    private static native void nDeActive(long j2);

    private static native void nEnableLoad(long j2, int i2, boolean z2);

    private static native void nEnableStore(long j2, int i2, boolean z2);

    private static native boolean nIsDefault(long j2);

    private static native void nResize(long j2, int i2, int i3);

    private static native void nSetClearColor(long j2, float f2, float f3, float f4, float f5);

    public void active(int i2) {
        nActive(getNativeObject(), i2);
    }

    public void blit(RenderTexture renderTexture, int i2, int i3) {
        nBlit(getNativeObject(), renderTexture == null ? 0L : renderTexture.getNativeObject(), i2, i3);
    }

    public void deActive() {
        nDeActive(getNativeObject());
    }

    public void enableLoad(AttachmentPoint attachmentPoint, boolean z2) {
        nEnableLoad(getNativeObject(), attachmentPoint.point, z2);
    }

    public void enableStore(AttachmentPoint attachmentPoint, boolean z2) {
        nEnableStore(getNativeObject(), attachmentPoint.point, z2);
    }

    public boolean isDefault() {
        return nIsDefault(getNativeObject());
    }

    public void resize(int i2, int i3) {
        nResize(getNativeObject(), i2, i3);
    }

    public void setClearColor(float f2, float f3, float f4, float f5) {
        nSetClearColor(getNativeObject(), f2, f3, f4, f5);
    }
}
