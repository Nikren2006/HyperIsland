package miuix.mgl;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLES32;
import android.opengl.GLUtils;
import androidx.annotation.Nullable;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import miuix.mgl.Texture;
import miuix.mgl.utils.IOUtils;
import miuix.mgl.utils.NativeObject;

/* JADX INFO: loaded from: classes3.dex */
public class Texture2D extends Texture {

    /* JADX INFO: renamed from: miuix.mgl.Texture2D$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            $SwitchMap$android$graphics$Bitmap$Config = iArr;
            try {
                iArr[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGBA_F16.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ALPHA_8.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
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

        private static native void nCompressedFormat(long j2, int i2);

        private static native long nCreateBuilder();

        private static native void nDestroyBuilder(long j2);

        private static native void nFormat(long j2, int i2);

        private static native void nHeight(long j2, int i2);

        private static native void nTexture(long j2, int i2);

        private static native void nWidth(long j2, int i2);

        public Texture2D build() {
            return build(null);
        }

        public Builder compressedFormat(Texture.CompressedTextureFormat compressedTextureFormat) {
            nCompressedFormat(getNativeObject(), compressedTextureFormat.format);
            return this;
        }

        public Builder format(Texture.TextureFormat textureFormat) {
            nFormat(getNativeObject(), textureFormat.format);
            return this;
        }

        public Builder height(int i2) {
            nHeight(getNativeObject(), i2);
            return this;
        }

        @Override // miuix.mgl.utils.NativeObject
        public void onDestroyNativeObject(long j2) {
            nDestroyBuilder(j2);
        }

        public Builder texture(int i2) {
            nTexture(getNativeObject(), i2);
            return this;
        }

        public Builder width(int i2) {
            nWidth(getNativeObject(), i2);
            return this;
        }

        public Texture2D build(@Nullable MglContext mglContext) {
            Texture2D texture2D = new Texture2D(nBuild(getNativeObject(), mglContext == null ? 0L : mglContext.getNativeObject()), null);
            destroyInternal();
            return texture2D;
        }

        public Builder compressedFormat(int i2) {
            nCompressedFormat(getNativeObject(), i2);
            return this;
        }

        public Builder format(int i2) {
            nFormat(getNativeObject(), i2);
            return this;
        }
    }

    public /* synthetic */ Texture2D(long j2, AnonymousClass1 anonymousClass1) {
        this(j2);
    }

    public static Texture2D createFromBitmap(Bitmap bitmap, MglContext mglContext) {
        Texture.TextureFormat bitmapFormat;
        if (bitmap == null || bitmap.isRecycled() || (bitmapFormat = getBitmapFormat(bitmap)) == Texture.TextureFormat.INVALID) {
            return null;
        }
        int[] iArr = new int[1];
        GLES32.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(3553, iArr[0]);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        GLES20.glBindTexture(3553, 0);
        Builder builderCreate = Builder.create();
        builderCreate.width(bitmap.getWidth()).height(bitmap.getHeight()).format(bitmapFormat.format).texture(iArr[0]);
        return builderCreate.build(mglContext);
    }

    private static Texture2D createFromParser(TextureParser textureParser, MglContext mglContext) {
        Texture2D texture2DBuild = new Builder().width(textureParser.getWidth()).height(textureParser.getHeight()).format(textureParser.getTextureFormat()).build(mglContext);
        texture2DBuild.setDataFromParser(0, textureParser);
        return texture2DBuild;
    }

    public static Texture2D createFromPngBuffer(ByteBuffer byteBuffer, int i2, MglContext mglContext) {
        PngParser pngParserCreate = PngParser.create(i2);
        pngParserCreate.parseFromBuffer(byteBuffer);
        Texture2D texture2DCreateFromParser = createFromParser(pngParserCreate, mglContext);
        pngParserCreate.destroy();
        return texture2DCreateFromParser;
    }

    public static Texture2D createFromPngFile(String str, int i2, MglContext mglContext) {
        return createFromPngBuffer(IOUtils.loadBufferFromFile(str), i2, mglContext);
    }

    public static Texture2D createFromPngRes(int i2, int i3, Resources resources, MglContext mglContext) {
        return createFromPngBuffer(IOUtils.loadBufferFromRes(i2, resources), i3, mglContext);
    }

    public static Texture2D createFromWebpBuffer(ByteBuffer byteBuffer, MglContext mglContext) {
        WebpParser webpParserCreate = WebpParser.create();
        webpParserCreate.parseFromBuffer(byteBuffer);
        Texture2D texture2DCreateFromParser = createFromParser(webpParserCreate, mglContext);
        webpParserCreate.destroy();
        return texture2DCreateFromParser;
    }

    public static Texture2D createFromWebpFile(String str, MglContext mglContext) {
        return createFromWebpBuffer(IOUtils.loadBufferFromFile(str), mglContext);
    }

    public static Texture2D createFromWebpRes(int i2, Resources resources, MglContext mglContext) {
        return createFromWebpBuffer(IOUtils.loadBufferFromRes(i2, resources), mglContext);
    }

    public static Texture2D createFromZstcBuffer(ByteBuffer byteBuffer, MglContext mglContext) {
        ZstcParser zstcParserCreate = ZstcParser.create();
        zstcParserCreate.parseFromBuffer(byteBuffer);
        Texture2D texture2DCreateFromParser = createFromParser(zstcParserCreate, mglContext);
        zstcParserCreate.destroy();
        return texture2DCreateFromParser;
    }

    public static Texture2D createFromZstcFile(String str, MglContext mglContext) {
        return createFromZstcBuffer(IOUtils.loadBufferFromFile(str), mglContext);
    }

    public static Texture2D createFromZstcRes(int i2, Resources resources, MglContext mglContext) {
        return createFromZstcBuffer(IOUtils.loadBufferFromRes(i2, resources), mglContext);
    }

    public static Texture.TextureFormat getBitmapFormat(Bitmap bitmap) {
        int i2 = AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[bitmap.getConfig().ordinal()];
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? Texture.TextureFormat.INVALID : Texture.TextureFormat.R8 : Texture.TextureFormat.RGBA16F : Texture.TextureFormat.RGB565 : Texture.TextureFormat.RGBA8;
    }

    private static native void nSetData(long j2, int i2, Buffer buffer, int i3);

    private static native void nSetDataFromParser(long j2, long j3, int i2);

    public void setDataFromBuffer(int i2, Buffer buffer) {
        nSetData(getNativeObject(), i2, buffer, buffer.remaining());
    }

    public void setDataFromParser(int i2, AssetParser assetParser) {
        nSetDataFromParser(getNativeObject(), assetParser.getNativeObject(), i2);
    }

    private Texture2D(long j2) {
        super(j2);
    }

    public static Texture2D createFromPngFile(String str, MglContext mglContext) {
        return createFromPngFile(str, 0, mglContext);
    }

    public static Texture2D createFromPngRes(int i2, Resources resources, MglContext mglContext) {
        return createFromPngRes(i2, 0, resources, mglContext);
    }
}
