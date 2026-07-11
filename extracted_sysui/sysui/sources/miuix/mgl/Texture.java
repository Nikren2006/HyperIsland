package miuix.mgl;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Texture extends MglObject {

    public enum CompressedTextureFormat {
        RGBA_ASTC_4x4(37808),
        RGBA_ASTC_5x4(37809),
        RGBA_ASTC_5x5(37810),
        RGBA_ASTC_6x5(37811),
        RGBA_ASTC_6x6(37812),
        RGBA_ASTC_8x5(37813),
        RGBA_ASTC_8x6(37814),
        RGBA_ASTC_8x8(37815),
        RGBA_ASTC_10x5(37816),
        RGBA_ASTC_10x6(37817),
        RGBA_ASTC_10x8(37818),
        RGBA_ASTC_10x10(37819),
        RGBA_ASTC_12x10(37820),
        RGBA_ASTC_12x12(37821);

        int format;

        CompressedTextureFormat(int i2) {
            this.format = i2;
        }
    }

    public enum TextureFormat {
        INVALID(0),
        R8(33321),
        RG8(33323),
        RGB565(36194),
        R16UI(33332),
        RGB8(32849),
        RGBA8(32856),
        RG16UI(33338),
        RGB16UI(36215),
        RGBA16F(34842),
        RGBA16UI(36214),
        RGBA16I(36232),
        RGBA_ASTC_4x4(37808),
        RGBA_ASTC_5x4(37809),
        RGBA_ASTC_5x5(37810),
        RGBA_ASTC_6x5(37811),
        RGBA_ASTC_6x6(37812),
        RGBA_ASTC_8x5(37813),
        RGBA_ASTC_8x6(37814),
        RGBA_ASTC_8x8(37815),
        RGBA_ASTC_10x5(37816),
        RGBA_ASTC_10x6(37817),
        RGBA_ASTC_10x8(37818),
        RGBA_ASTC_10x10(37819),
        RGBA_ASTC_12x10(37820),
        RGBA_ASTC_12x12(37821),
        DEPTH_16(33189),
        DEPTH_24(33190),
        DEPTH_32F(36012);

        int format;

        TextureFormat(int i2) {
            this.format = i2;
        }
    }

    public enum TextureSwizzle {
        RED(6403),
        GREEN(6404),
        BLUE(6405),
        ALPHA(6406);

        int swizzle;

        TextureSwizzle(int i2) {
            this.swizzle = i2;
        }
    }

    public enum TextureWrapMod {
        CLAMP_TO_EDGE(33071),
        REPEAT(10497),
        MIRRORED_REPEAT(33648);

        int wrapMod;

        TextureWrapMod(int i2) {
            this.wrapMod = i2;
        }
    }

    public Texture(long j2) {
        super(j2);
    }

    private static native int nGetHeight(long j2);

    private static native int nGetTarget(long j2);

    private static native int nGetTextureID(long j2);

    private static native int nGetWidth(long j2);

    private static native void nSetSwizzle(long j2, int i2, int i3, int i4, int i5);

    private static native void nSetWrapMod(long j2, int i2);

    public int getHeight() {
        return nGetHeight(getNativeObject());
    }

    public int getTarget() {
        return nGetTarget(getNativeObject());
    }

    public int getTextureID() {
        return nGetTextureID(getNativeObject());
    }

    public int getWidth() {
        return nGetWidth(getNativeObject());
    }

    public void setSwizzle(TextureSwizzle textureSwizzle, TextureSwizzle textureSwizzle2, TextureSwizzle textureSwizzle3, TextureSwizzle textureSwizzle4) {
        nSetSwizzle(getNativeObject(), textureSwizzle.swizzle, textureSwizzle2.swizzle, textureSwizzle3.swizzle, textureSwizzle4.swizzle);
    }

    public void setWrapMod(TextureWrapMod textureWrapMod) {
        nSetWrapMod(getNativeObject(), textureWrapMod.wrapMod);
    }
}
