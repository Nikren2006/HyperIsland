package miuix.mgl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import miuix.mgl.utils.NativeObject;

/* JADX INFO: loaded from: classes3.dex */
public class BufferObject extends MglObject {

    public static class Builder extends NativeObject {
        public Builder() {
            initNativeObject(nCreateBuilder());
        }

        public static Builder create() {
            return new Builder();
        }

        private static native long nBuild(long j2, long j3);

        private static native long nCreateBuilder();

        private static native void nType(long j2, int i2);

        public BufferObject build() {
            return build(null);
        }

        public Builder data(float[] fArr) {
            FloatBuffer floatBufferPut = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(fArr);
            floatBufferPut.position(0);
            return data(floatBufferPut);
        }

        @Override // miuix.mgl.utils.NativeObject
        public void onDestroyNativeObject(long j2) {
            BufferObject.nBuilderDestroyBuilder(getNativeObject());
        }

        public Builder size(int i2) {
            BufferObject.nBuilderSize(getNativeObject(), i2);
            return this;
        }

        public Builder type(Type type) {
            nType(getNativeObject(), type.type);
            return this;
        }

        public Builder usage(Usage usage) {
            BufferObject.nBuilderUsage(getNativeObject(), usage.usage);
            return this;
        }

        public BufferObject build(@Nullable MglContext mglContext) {
            long jNBuild = nBuild(getNativeObject(), mglContext == null ? 0L : mglContext.getNativeObject());
            destroyInternal();
            return new BufferObject(jNBuild);
        }

        public Builder(long j2) {
            initNativeObject(j2);
        }

        public Builder data(Buffer buffer) {
            BufferObject.nBuilderData(getNativeObject(), buffer, buffer.remaining());
            return this;
        }
    }

    public enum Type {
        UNIFORM(35345),
        SHADER_STORAGE(37074),
        ARRAY_BUFFER(34962);

        int type;

        Type(int i2) {
            this.type = i2;
        }
    }

    public enum Usage {
        STATIC(35044),
        DYNAMIC(35048),
        STATIC_READ(35045),
        DYNAMIC_READ(35049);

        int usage;

        Usage(int i2) {
            this.usage = i2;
        }
    }

    public BufferObject(long j2) {
        super(j2);
    }

    public static void copy(@NonNull BufferObject bufferObject, @NonNull BufferObject bufferObject2) {
        nCopy(bufferObject.getNativeObject(), bufferObject2.getNativeObject());
    }

    public static BufferObject createTemp(long j2) {
        BufferObject bufferObject = new BufferObject(0L);
        bufferObject.initTempNativeObject(j2);
        return bufferObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nBuilderData(long j2, Buffer buffer, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nBuilderDestroyBuilder(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nBuilderSize(long j2, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nBuilderUsage(long j2, int i2);

    private static native void nCopy(long j2, long j3);

    private static native int nGetBufferID(long j2);

    private static native int nGetSize(long j2);

    private static native void nResize(long j2, int i2);

    private static native void nSetData(long j2, Buffer buffer, int i2);

    public int getBufferID() {
        return nGetBufferID(getNativeObject());
    }

    public int getSize() {
        return nGetSize(getNativeObject());
    }

    public void resize(int i2) {
        nResize(getNativeObject(), i2);
    }

    public void setData(float[] fArr) {
        FloatBuffer floatBufferPut = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(fArr);
        floatBufferPut.position(0);
        setData(floatBufferPut);
    }

    public void setData(Buffer buffer) {
        nSetData(getNativeObject(), buffer, buffer.remaining());
    }
}
