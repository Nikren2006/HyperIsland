package miuix.mgl;

import android.content.res.Resources;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import miuix.mgl.Primitive;
import miuix.mgl.utils.IOUtils;
import miuix.mgl.utils.NativeObject;

/* JADX INFO: loaded from: classes3.dex */
public class GLTFBaseParser extends NativeObject {
    public GLTFBaseParser(long j2) {
        initNativeObject(j2);
    }

    private static native void nAttributeIndex(long j2, int i2, int i3);

    private static native void nAttributeIndexName(long j2, String str, int i2);

    private static native void nDestroy(long j2);

    private static native void nParseFromBuffer(long j2, Buffer buffer, int i2, long j3, boolean z2);

    private static native long nPopPrimitive(long j2);

    private static native void nPrimitiveBuildMod(long j2, int i2);

    public GLTFBaseParser attributeIndex(Primitive.VertexAttributeIndex vertexAttributeIndex, int i2) {
        nAttributeIndex(getNativeObject(), vertexAttributeIndex.index, i2);
        return this;
    }

    public void destroy() {
        destroyInternal();
    }

    @Override // miuix.mgl.utils.NativeObject
    public void onDestroyNativeObject(long j2) {
        nDestroy(getNativeObject());
    }

    public void parseFromBuffer(ByteBuffer byteBuffer, MglContext mglContext, boolean z2) {
        nParseFromBuffer(getNativeObject(), byteBuffer, byteBuffer.remaining(), mglContext == null ? 0L : mglContext.getNativeObject(), z2);
    }

    public void parseFromFile(String str, MglContext mglContext, boolean z2) {
        parseFromBuffer(IOUtils.loadBufferFromFile(str), mglContext, z2);
    }

    public void parseFromRes(int i2, Resources resources, MglContext mglContext, boolean z2) {
        parseFromBuffer(IOUtils.loadBufferFromRes(i2, resources), mglContext, z2);
    }

    public Primitive popPrimitive() {
        long jNPopPrimitive = nPopPrimitive(getNativeObject());
        if (jNPopPrimitive == 0) {
            return null;
        }
        return Primitive.create(jNPopPrimitive);
    }

    public GLTFBaseParser primitiveBuildMod(Primitive.Builder.Mod mod) {
        nPrimitiveBuildMod(getNativeObject(), mod.ordinal());
        return this;
    }

    public GLTFBaseParser attributeIndex(String str, int i2) {
        nAttributeIndexName(getNativeObject(), str, i2);
        return this;
    }
}
