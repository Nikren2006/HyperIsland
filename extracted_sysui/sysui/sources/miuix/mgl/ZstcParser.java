package miuix.mgl;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class ZstcParser extends TextureParser {
    public ZstcParser(long j2) {
        super(j2);
    }

    public static ZstcParser create() {
        return new ZstcParser(nCreateZstcParser());
    }

    private static native long nCreateZstcParser();

    private static native void nParseZstc(long j2, Buffer buffer, int i2);

    @Override // miuix.mgl.AssetParser
    public void parseFromBuffer(ByteBuffer byteBuffer) {
        nParseZstc(getNativeObject(), byteBuffer, byteBuffer.remaining());
    }
}
