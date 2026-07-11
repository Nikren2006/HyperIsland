package miuix.mgl.effect;

import miuix.mgl.MglContext;
import miuix.mgl.RenderMaterial;
import miuix.mgl.Transform;
import miuix.mgl.utils.NativeObject;

/* JADX INFO: loaded from: classes3.dex */
public class TrailRenderer extends NativeObject {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean mDestroyGraphic = false;
    private RenderMaterial mMaterial;
    private Transform mTransform;

    public TrailRenderer(MglContext mglContext) {
        initNativeObject(nCreate(mglContext == null ? 0L : mglContext.getNativeObject()));
    }

    private static native void nClear(long j2);

    private static native void nClosePath(long j2);

    private static native long nCreate(long j2);

    private static native void nDestroy(long j2, boolean z2);

    private static native void nDraw(long j2, float f2);

    private static native void nEnableSmooth(long j2, boolean z2);

    private static native void nGetAabb(long j2, float[] fArr);

    private static native void nGetPathPoint(long j2, float[] fArr);

    private static native int nGetPathPointCount(long j2);

    private static native long nGetTransform(long j2);

    private static native void nSetLife(long j2, float f2);

    private static native void nSetMaterial(long j2, long j3);

    private static native void nSetMinInterval(long j2, float f2);

    private static native void nSetSplineAlpha(long j2, float f2);

    private static native void nSetTextureParam(long j2, float f2, float f3, float f4);

    private static native void nSetWidth(long j2, float f2);

    public void clear() {
        nClear(getNativeObject());
    }

    public void closePath() {
        nClosePath(getNativeObject());
    }

    public void destroy(boolean z2) {
        this.mDestroyGraphic = z2;
        destroyInternal();
    }

    public void draw(float f2) {
        nDraw(getNativeObject(), f2);
    }

    public void enableSmooth(boolean z2) {
        nEnableSmooth(getNativeObject(), z2);
    }

    public void getAabb(float[] fArr) {
        nGetAabb(getNativeObject(), fArr);
    }

    public void getPathPoint(float[] fArr) {
        nGetPathPoint(getNativeObject(), fArr);
    }

    public int getPathPointCount() {
        return nGetPathPointCount(getNativeObject());
    }

    public Transform getTransform() {
        if (this.mTransform == null) {
            this.mTransform = Transform.createFromNative(nGetTransform(getNativeObject()));
        }
        return this.mTransform;
    }

    @Override // miuix.mgl.utils.NativeObject
    public void onDestroyNativeObject(long j2) {
        nDestroy(getNativeObject(), this.mDestroyGraphic);
    }

    public void setLife(float f2) {
        nSetLife(getNativeObject(), f2);
    }

    public void setMaterial(RenderMaterial renderMaterial) {
        this.mMaterial = renderMaterial;
        nSetMaterial(getNativeObject(), renderMaterial == null ? 0L : renderMaterial.getNativeObject());
    }

    public void setMinInterval(float f2) {
        nSetMinInterval(getNativeObject(), f2);
    }

    public void setSplineAlpha(float f2) {
        nSetSplineAlpha(getNativeObject(), f2);
    }

    public void setTextureParam(float f2, float f3, float f4) {
        nSetTextureParam(getNativeObject(), f2, f3, f4);
    }

    public void setWidth(float f2) {
        nSetWidth(getNativeObject(), f2);
    }
}
