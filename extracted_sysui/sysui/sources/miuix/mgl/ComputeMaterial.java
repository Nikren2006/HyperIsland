package miuix.mgl;

/* JADX INFO: loaded from: classes3.dex */
public class ComputeMaterial extends Material {

    public enum ImageAccessType {
        READ_ONLY(35000),
        WRITE_ONLY(35001),
        READ_WRITE(35002);

        int type;

        ImageAccessType(int i2) {
            this.type = i2;
        }
    }

    public enum MemoryBarrier {
        VERTEX_ARRAY(1),
        SHADER_IMAGE_ACCESS(32),
        SHADER_STORAGE_BARRIER(8192),
        BUFFER_UPDATE(512);

        int value;

        MemoryBarrier(int i2) {
            this.value = i2;
        }
    }

    public ComputeMaterial(long j2) {
        super(j2);
    }

    public static ComputeMaterial create(Shader shader) {
        return new ComputeMaterial(nCreate(shader.getNativeObject()));
    }

    public static ComputeMaterial createTemp(long j2) {
        ComputeMaterial computeMaterial = new ComputeMaterial(0L);
        computeMaterial.initTempNativeObject(j2);
        return computeMaterial;
    }

    private static native long nCreate(long j2);

    private static native int nGetWorkGroupX(long j2);

    private static native int nGetWorkGroupY(long j2);

    private static native int nGetWorkGroupZ(long j2);

    private static native void nSetDispatch(long j2, int i2, int i3, int i4);

    private static native void nSetImage(long j2, String str, long j3, int i2, int i3, int i4);

    private static native void nSetImageWithIndex(long j2, int i2, long j3, int i3, int i4, int i5);

    private static native void nSetMemoryBarrier(long j2, int i2);

    public int getWorkGroupX() {
        return nGetWorkGroupX(getNativeObject());
    }

    public int getWorkGroupY() {
        return nGetWorkGroupY(getNativeObject());
    }

    public int getWorkGroupZ() {
        return nGetWorkGroupZ(getNativeObject());
    }

    public void setDispatch(int i2, int i3, int i4) {
        nSetDispatch(getNativeObject(), i2, i3, i4);
    }

    public void setImage(String str, Texture texture, ImageAccessType imageAccessType, int i2, int i3) {
        addRef(str, texture);
        nSetImage(getNativeObject(), str, texture == null ? 0L : texture.getNativeObject(), imageAccessType.type, i2, i3);
    }

    public void setMemoryBarrier(int i2) {
        nSetMemoryBarrier(getNativeObject(), i2);
    }

    public void setMemoryBarrier(MemoryBarrier memoryBarrier) {
        nSetMemoryBarrier(getNativeObject(), memoryBarrier.value);
    }

    public void setImage(int i2, Texture texture, ImageAccessType imageAccessType, int i3, int i4) {
        addRef(i2, texture);
        nSetImageWithIndex(getNativeObject(), i2, texture == null ? 0L : texture.getNativeObject(), imageAccessType.type, i3, i4);
    }
}
