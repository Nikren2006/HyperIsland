package miuix.mgl;

import miuix.mgl.utils.NativeObject;

/* JADX INFO: loaded from: classes3.dex */
public class MglContext extends NativeObject {
    private static volatile MglContext sMglContext;

    public MglContext() {
        initNativeObject(nCreateMglContext());
    }

    public static MglContext getInstance() {
        if (sMglContext == null) {
            synchronized (MglContext.class) {
                try {
                    if (sMglContext == null) {
                        sMglContext = new MglContext(nGetContextInstance());
                    }
                } finally {
                }
            }
        }
        if (!sMglContext.isValid()) {
            sMglContext.initTempNativeObject(nGetContextInstance());
        }
        return sMglContext;
    }

    private static native long nCreateMglContext();

    private static native void nDestroyMglContext(long j2);

    private static native long nGetContextInstance();

    private static native int nGetMaxVertexSSBOCount(long j2);

    private static native void nResetDefaultFBO(long j2, int i2, int i3);

    private static native void nResetMglContext(long j2);

    public void destroy() {
        destroyInternal();
    }

    public int getMaxVertexSSBOCount() {
        return nGetMaxVertexSSBOCount(getNativeObject());
    }

    @Override // miuix.mgl.utils.NativeObject
    public void onDestroyNativeObject(long j2) {
        nDestroyMglContext(j2);
    }

    public void reset() {
        nResetMglContext(getNativeObject());
    }

    public void resetDefaultFBO(int i2, int i3) {
        nResetDefaultFBO(getNativeObject(), i2, i3);
    }

    private MglContext(long j2) {
        initTempNativeObject(j2);
    }
}
