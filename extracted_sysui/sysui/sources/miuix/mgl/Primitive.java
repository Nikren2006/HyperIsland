package miuix.mgl;

import androidx.annotation.Nullable;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.util.HashMap;
import miuix.mgl.utils.NativeObject;

/* JADX INFO: loaded from: classes3.dex */
public class Primitive extends MglObject {
    private BufferObject mIndexBuffer;
    private HashMap<Integer, BufferObject> mVertexBuffers;

    public static class Builder extends NativeObject {

        public enum Mod {
            ONE,
            FIRST_ONE_OTHER_ONE,
            EACH_ONE
        }

        public Builder(int i2) {
            initNativeObject(nCreateBuilder(i2));
        }

        public static Builder create(int i2) {
            return new Builder(i2);
        }

        private static native long nBuild(long j2, long j3, int i2);

        private static native long nCreateBuilder(int i2);

        private static native void nDestroyBuilder(long j2);

        private static native void nIndices(long j2, int i2, Buffer buffer, int i3);

        private static native void nIndicesV2(long j2, int i2, int i3);

        private static native void nPrimitiveType(long j2, int i2);

        private static native void nVertexAttribute(long j2, int i2, Buffer buffer, int i3, int i4, int i5, boolean z2);

        private static native void nVertexAttributeEmpty(long j2, int i2, int i3, int i4, boolean z2);

        private static native void nVertexNormalized(long j2, int i2, boolean z2);

        public Primitive build(Mod mod) {
            return build(null, mod);
        }

        public Builder indices(int[] iArr) {
            IntBuffer intBufferPut = ByteBuffer.allocateDirect(iArr.length * 4).order(ByteOrder.nativeOrder()).asIntBuffer().put(iArr);
            intBufferPut.position(0);
            return indices(IndexElementType.UINT, intBufferPut);
        }

        @Override // miuix.mgl.utils.NativeObject
        public void onDestroyNativeObject(long j2) {
            nDestroyBuilder(j2);
        }

        public Builder primitiveType(PrimitiveType primitiveType) {
            nPrimitiveType(getNativeObject(), primitiveType.type);
            return this;
        }

        public Builder vertexAttribute(int i2, Buffer buffer, VertexElementType vertexElementType, ComponentSize componentSize, boolean z2) {
            nVertexAttribute(getNativeObject(), i2, buffer, buffer.remaining(), vertexElementType.type, componentSize.size, z2);
            return this;
        }

        public Builder vertexAttributeEmpty(int i2, VertexElementType vertexElementType, ComponentSize componentSize, boolean z2) {
            nVertexAttributeEmpty(getNativeObject(), i2, vertexElementType.type, componentSize.size, z2);
            return this;
        }

        public Builder vertexNormalized(int i2, boolean z2) {
            nVertexNormalized(getNativeObject(), i2, z2);
            return this;
        }

        public Primitive build(@Nullable MglContext mglContext, Mod mod) {
            long jNBuild = nBuild(getNativeObject(), mglContext == null ? 0L : mglContext.getNativeObject(), mod.ordinal());
            destroyInternal();
            return new Primitive(jNBuild);
        }

        public Builder vertexAttribute(int i2, float[] fArr, VertexElementType vertexElementType, ComponentSize componentSize, boolean z2) {
            FloatBuffer floatBufferPut = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(fArr);
            floatBufferPut.position(0);
            return vertexAttribute(i2, floatBufferPut, vertexElementType, componentSize, z2);
        }

        public Builder indices(byte[] bArr) {
            ByteBuffer byteBufferPut = ByteBuffer.allocateDirect(bArr.length).order(ByteOrder.nativeOrder()).put(bArr);
            byteBufferPut.position(0);
            return indices(IndexElementType.UBYTE, byteBufferPut);
        }

        public Builder indices(IndexElementType indexElementType, Buffer buffer) {
            nIndices(getNativeObject(), indexElementType.type, buffer, buffer.remaining());
            return this;
        }

        public Builder indices(IndexElementType indexElementType, int i2) {
            nIndicesV2(getNativeObject(), indexElementType.type, i2);
            return this;
        }
    }

    public enum ComponentSize {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4);

        int size;

        ComponentSize(int i2) {
            this.size = i2;
        }
    }

    public enum IndexElementType {
        UBYTE(5121),
        USHORT(5123),
        UINT(5125);

        int type;

        IndexElementType(int i2) {
            this.type = i2;
        }
    }

    public static class LegacyBuilder extends NativeObject {
        public LegacyBuilder(int i2) {
            initNativeObject(nCreateBuilder(i2));
        }

        public static Builder create(int i2) {
            return new Builder(i2);
        }

        private static native long nBuild(long j2, long j3);

        private static native long nCreateBuilder(int i2);

        private static native void nDestroyBuilder(long j2);

        private static native void nIndices(long j2, int i2, Buffer buffer, int i3);

        private static native void nIndicesV2(long j2, int i2, int i3);

        private static native void nInitialInstanceCount(long j2, int i2);

        private static native void nPrimitiveType(long j2, int i2);

        private static native void nVertexAttribute(long j2, int i2, int i3, int i4, int i5, int i6, int i7);

        private static native void nVertexBuffer(long j2, int i2, Buffer buffer, int i3);

        private static native void nVertexDivisor(long j2, int i2, int i3);

        private static native void nVertexNormalized(long j2, int i2, boolean z2);

        public Primitive build() {
            return build(null);
        }

        public LegacyBuilder indices(int[] iArr) {
            IntBuffer intBufferPut = ByteBuffer.allocateDirect(iArr.length * 4).order(ByteOrder.nativeOrder()).asIntBuffer().put(iArr);
            intBufferPut.position(0);
            return indices(IndexElementType.UINT, intBufferPut);
        }

        public LegacyBuilder initialInstanceCount(int i2) {
            nInitialInstanceCount(getNativeObject(), i2);
            return this;
        }

        @Override // miuix.mgl.utils.NativeObject
        public void onDestroyNativeObject(long j2) {
            nDestroyBuilder(j2);
        }

        public LegacyBuilder primitiveType(PrimitiveType primitiveType) {
            nPrimitiveType(getNativeObject(), primitiveType.type);
            return this;
        }

        public LegacyBuilder vertexAttribute(int i2, VertexElementType vertexElementType, ComponentSize componentSize, int i3, int i4, int i5) {
            nVertexAttribute(getNativeObject(), i2, vertexElementType.type, componentSize.size, i3, i4, i5);
            return this;
        }

        public LegacyBuilder vertexBuffer(int i2, Buffer buffer) {
            nVertexBuffer(getNativeObject(), i2, buffer, buffer.remaining());
            return this;
        }

        public LegacyBuilder vertexDivisor(int i2, int i3) {
            nVertexDivisor(getNativeObject(), i2, i3);
            return this;
        }

        public LegacyBuilder vertexNormalized(int i2, boolean z2) {
            nVertexNormalized(getNativeObject(), i2, z2);
            return this;
        }

        public Primitive build(@Nullable MglContext mglContext) {
            long jNBuild = nBuild(getNativeObject(), mglContext == null ? 0L : mglContext.getNativeObject());
            destroyInternal();
            return new Primitive(jNBuild);
        }

        public LegacyBuilder vertexBuffer(int i2, float[] fArr) {
            FloatBuffer floatBufferPut = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(fArr);
            floatBufferPut.position(0);
            return vertexBuffer(i2, floatBufferPut);
        }

        public LegacyBuilder indices(byte[] bArr) {
            ByteBuffer byteBufferPut = ByteBuffer.allocateDirect(bArr.length).order(ByteOrder.nativeOrder()).put(bArr);
            byteBufferPut.position(0);
            return indices(IndexElementType.UBYTE, byteBufferPut);
        }

        public LegacyBuilder indices(IndexElementType indexElementType, Buffer buffer) {
            nIndices(getNativeObject(), indexElementType.type, buffer, buffer.remaining());
            return this;
        }

        public LegacyBuilder indices(IndexElementType indexElementType, int i2) {
            nIndicesV2(getNativeObject(), indexElementType.type, i2);
            return this;
        }
    }

    public enum PrimitiveType {
        POINTS(0),
        LINES(1),
        LINE_STRIP(3),
        TRIANGLES(4),
        TRIANGLE_FAN(6),
        TRIANGLE_STRIP(5);

        int type;

        PrimitiveType(int i2) {
            this.type = i2;
        }
    }

    public enum VertexAttributeIndex {
        POSITION(0),
        NORMAL(1),
        TANGENT(2),
        UV(3),
        UV2(4),
        COLOR(5),
        CUSTOM1(6),
        CUSTOM2(7),
        CUSTOM3(8),
        CUSTOM4(9);

        int index;

        VertexAttributeIndex(int i2) {
            this.index = i2;
        }
    }

    public enum VertexElementType {
        UBYTE(5121),
        BYTE(5120),
        USHORT(5123),
        SHORT(5122),
        UINT(5125),
        INT(5124),
        HALF(5131),
        FLOAT(5126);

        int type;

        VertexElementType(int i2) {
            this.type = i2;
        }
    }

    public Primitive(long j2) {
        super(j2);
        this.mVertexBuffers = new HashMap<>();
    }

    public static Primitive create(long j2) {
        return new Primitive(j2);
    }

    public static Primitive createQuad(@Nullable MglContext mglContext) {
        return new Primitive(nCreateQuad(mglContext == null ? 0L : mglContext.getNativeObject()));
    }

    private static native void nActive(long j2);

    private static native long nCreateQuad(long j2);

    private static native void nDeActive(long j2);

    private static native void nDraw(long j2, int i2);

    private static native void nDrawV2(long j2, int i2, int i3, boolean z2);

    private static native void nGetAabb(long j2, float[] fArr, float[] fArr2);

    private static native long nGetIndexBuffer(long j2);

    private static native void nGetIndexBufferData(long j2, Buffer buffer, int i2);

    private static native void nGetIndexBufferDataLong(long j2, LongBuffer longBuffer, int i2);

    private static native int nGetIndexBufferSize(long j2);

    private static native int nGetIndexCount(long j2);

    private static native int nGetIndexElementType(long j2);

    private static native int nGetPrimitiveType(long j2);

    private static native int nGetVAO(long j2);

    private static native long nGetVertexBuffer(long j2, int i2);

    private static native int nGetVertexCount(long j2);

    private static native void nMoveVertexFromPrimitive(long j2, long j3, int i2, int i3);

    private static native void nSetVertexData(long j2, int i2, Buffer buffer, int i3);

    private static native void nSetVertexDataFromSSBO(long j2, int i2, long j3);

    private static native void nSetVertexDataWithVertexCount(long j2, int i2, int i3, Buffer buffer, int i4);

    private static native void nUpdateDivisorBufferSize(long j2, int i2);

    public void active() {
        nActive(getNativeObject());
    }

    public void deActive() {
        nDeActive(getNativeObject());
    }

    public void draw(int i2) {
        nDraw(getNativeObject(), i2);
    }

    public void getAabb(float[] fArr, float[] fArr2) {
        nGetAabb(getNativeObject(), fArr, fArr2);
    }

    public BufferObject getIndexBuffer() {
        BufferObject bufferObject = this.mIndexBuffer;
        if (bufferObject != null) {
            return bufferObject;
        }
        long jNGetIndexBuffer = nGetIndexBuffer(getNativeObject());
        if (jNGetIndexBuffer == 0) {
            return null;
        }
        BufferObject bufferObjectCreateTemp = BufferObject.createTemp(jNGetIndexBuffer);
        this.mIndexBuffer = bufferObjectCreateTemp;
        return bufferObjectCreateTemp;
    }

    public ByteBuffer getIndexBufferData() {
        ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(getIndexBufferSize()).order(ByteOrder.nativeOrder());
        byteBufferOrder.position(0);
        nGetIndexBufferData(getNativeObject(), byteBufferOrder, byteBufferOrder.remaining());
        return byteBufferOrder;
    }

    public int getIndexBufferSize() {
        return nGetIndexBufferSize(getNativeObject());
    }

    public int getIndexCount() {
        return nGetIndexCount(getNativeObject());
    }

    public int getIndexElementType() {
        return nGetIndexElementType(getNativeObject());
    }

    public LongBuffer getIndexLongBufferData() {
        LongBuffer longBufferAsLongBuffer = ByteBuffer.allocateDirect(getIndexCount() * 8).order(ByteOrder.nativeOrder()).asLongBuffer();
        longBufferAsLongBuffer.position(0);
        nGetIndexBufferDataLong(getNativeObject(), longBufferAsLongBuffer, longBufferAsLongBuffer.remaining());
        return longBufferAsLongBuffer;
    }

    public int getPrimitiveType() {
        return nGetPrimitiveType(getNativeObject());
    }

    public int getVAO() {
        return nGetVAO(getNativeObject());
    }

    public BufferObject getVertexBuffer(int i2) {
        BufferObject bufferObject = this.mVertexBuffers.get(Integer.valueOf(i2));
        if (bufferObject != null) {
            return bufferObject;
        }
        long jNGetVertexBuffer = nGetVertexBuffer(getNativeObject(), i2);
        if (jNGetVertexBuffer == 0) {
            return null;
        }
        BufferObject bufferObjectCreateTemp = BufferObject.createTemp(jNGetVertexBuffer);
        this.mVertexBuffers.put(Integer.valueOf(i2), bufferObjectCreateTemp);
        return bufferObjectCreateTemp;
    }

    public int getVertexCount() {
        return nGetVertexCount(getNativeObject());
    }

    public void moveVertexFromPrimitive(Primitive primitive, int i2, int i3) {
        nMoveVertexFromPrimitive(getNativeObject(), primitive.getNativeObject(), i2, i3);
    }

    public void setVertexData(int i2, Buffer buffer) {
        nSetVertexData(getNativeObject(), i2, buffer, buffer.remaining());
    }

    public void setVertexDataFromSSBO(int i2, ShaderStorageBuffer shaderStorageBuffer) {
        nSetVertexDataFromSSBO(getNativeObject(), i2, shaderStorageBuffer.getNativeObject());
    }

    public void updateDivisorBufferSize(int i2) {
        nUpdateDivisorBufferSize(getNativeObject(), i2);
    }

    public void draw() {
        draw(1);
    }

    public void setVertexData(int i2, int i3, Buffer buffer) {
        nSetVertexDataWithVertexCount(getNativeObject(), i2, i3, buffer, buffer.remaining());
    }

    public void draw(int i2, PrimitiveType primitiveType, boolean z2) {
        nDrawV2(getNativeObject(), i2, primitiveType.type, z2);
    }

    public void setVertexData(int i2, float[] fArr) {
        FloatBuffer floatBufferPut = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(fArr);
        floatBufferPut.position(0);
        setVertexData(i2, floatBufferPut);
    }
}
