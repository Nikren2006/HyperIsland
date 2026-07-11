package miuix.mgl;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class WebpParser extends TextureParser {
    public WebpParser(long j2) {
        super(j2);
    }

    public static WebpParser create() {
        return new WebpParser(nCreateWebpParser());
    }

    private static native long nCreateWebpParser();

    private static native void nParseWebp(long j2, Buffer buffer, int i2);

    @Override // miuix.mgl.AssetParser
    public void parseFromBuffer(ByteBuffer byteBuffer) {
        nParseWebp(getNativeObject(), byteBuffer, byteBuffer.remaining());
    }
}
