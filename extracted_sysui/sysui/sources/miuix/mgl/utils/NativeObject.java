package miuix.mgl.utils;

import miuix.mgl.MglNative;

/* JADX INFO: loaded from: classes3.dex */
public abstract class NativeObject {
    private long mNativeObject = 0;
    private boolean mIsTempRef = false;

    static {
        MglNative.init();
    }

    public void clearNativeObject() {
        this.mNativeObject = 0L;
    }

    public void destroyInternal() {
        if (!this.mIsTempRef) {
            onDestroyNativeObject(getNativeObject());
        }
        clearNativeObject();
    }

    public void finalize() {
        try {
            super.finalize();
            if (!isValid()) {
                return;
            }
        } catch (Throwable unused) {
            if (!isValid()) {
                return;
            }
        }
        destroyInternal();
    }

    public long getNativeObject() {
        long j2 = this.mNativeObject;
        if (j2 != 0) {
            return j2;
        }
        throw new IllegalStateException("Calling method on destroyed NativeObject");
    }

    public void initNativeObject(long j2) {
        if (this.mNativeObject != 0) {
            throw new IllegalStateException("Init a valid NativeObject");
        }
        this.mNativeObject = j2;
        this.mIsTempRef = false;
    }

    public void initTempNativeObject(long j2) {
        if (this.mNativeObject != 0) {
            throw new IllegalStateException("Init a valid NativeObject");
        }
        this.mNativeObject = j2;
        this.mIsTempRef = true;
    }

    public boolean isValid() {
        return this.mNativeObject != 0;
    }

    public abstract void onDestroyNativeObject(long j2);

    public void setTempRef() {
        this.mIsTempRef = true;
    }
}
