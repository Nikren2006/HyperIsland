package miuix.mgl;

import android.content.res.Resources;
import java.nio.ByteBuffer;
import miuix.mgl.utils.IOUtils;
import miuix.mgl.utils.NativeObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AssetParser extends NativeObject {
    public AssetParser(long j2) {
        initNativeObject(j2);
    }

    private static native void nDestroyParser(long j2);

    public void destroy() {
        destroyInternal();
    }

    @Override // miuix.mgl.utils.NativeObject
    public void onDestroyNativeObject(long j2) {
        nDestroyParser(getNativeObject());
    }

    public abstract void parseFromBuffer(ByteBuffer byteBuffer);

    public void parseFromFile(String str) {
        parseFromBuffer(IOUtils.loadBufferFromFile(str));
    }

    public void parseFromRes(int i2, Resources resources) {
        parseFromBuffer(IOUtils.loadBufferFromRes(i2, resources));
    }
}
