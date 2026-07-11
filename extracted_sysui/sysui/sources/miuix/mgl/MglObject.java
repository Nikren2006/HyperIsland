package miuix.mgl;

import androidx.annotation.Nullable;
import miuix.mgl.utils.NativeObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class MglObject extends NativeObject {
    boolean mDestroyGraphicResource = false;
    protected MglContext mMglContext;

    public MglObject(long j2) {
        initNativeObject(j2);
    }

    private static native void nCreate(long j2, long j3);

    private static native void nDestroyMglObject(long j2, boolean z2);

    public void create(@Nullable MglContext mglContext) {
        nCreate(getNativeObject(), mglContext == null ? 0L : mglContext.getNativeObject());
    }

    public void destroy(boolean z2) {
        this.mDestroyGraphicResource = z2;
        destroyInternal();
    }

    public MglContext getContext() {
        return this.mMglContext;
    }

    @Override // miuix.mgl.utils.NativeObject
    public void onDestroyNativeObject(long j2) {
        nDestroyMglObject(getNativeObject(), this.mDestroyGraphicResource);
    }
}
