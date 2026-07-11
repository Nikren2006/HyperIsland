package miuix.mgl;

import android.content.res.Resources;

/* JADX INFO: loaded from: classes3.dex */
public abstract class TextureParser extends AssetParser {
    public TextureParser(long j2) {
        super(j2);
    }

    private static native int nGetHeight(long j2);

    private static native int nGetTextureFormat(long j2);

    private static native int nGetWidth(long j2);

    @Override // miuix.mgl.AssetParser
    public /* bridge */ /* synthetic */ void destroy() {
        super.destroy();
    }

    public int getHeight() {
        return nGetHeight(getNativeObject());
    }

    public int getTextureFormat() {
        return nGetTextureFormat(getNativeObject());
    }

    public int getWidth() {
        return nGetWidth(getNativeObject());
    }

    @Override // miuix.mgl.AssetParser
    public /* bridge */ /* synthetic */ void parseFromFile(String str) {
        super.parseFromFile(str);
    }

    @Override // miuix.mgl.AssetParser
    public /* bridge */ /* synthetic */ void parseFromRes(int i2, Resources resources) {
        super.parseFromRes(i2, resources);
    }
}
