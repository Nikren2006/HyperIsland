package miuix.mgl;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class PngParser extends TextureParser {
    private int mChannels;

    private PngParser(long j2, int i2) {
        super(j2);
        this.mChannels = i2;
    }

    public static PngParser create(int i2) {
        return new PngParser(nCreatePngParser(), i2);
    }

    private static native long nCreatePngParser();

    private static native void nParsePng(long j2, Buffer buffer, int i2, int i3);

    @Override // miuix.mgl.AssetParser
    public void parseFromBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return;
        }
        nParsePng(getNativeObject(), byteBuffer, byteBuffer.remaining(), this.mChannels);
    }
}
