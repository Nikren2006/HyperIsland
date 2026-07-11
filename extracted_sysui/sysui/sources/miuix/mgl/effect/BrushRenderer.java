package miuix.mgl.effect;

import miuix.mgl.MglContext;
import miuix.mgl.RenderMaterial;
import miuix.mgl.utils.NativeObject;

/* JADX INFO: loaded from: classes3.dex */
public class BrushRenderer extends NativeObject {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean mDestroyGraphic = false;
    private RenderMaterial mMaterial;

    public BrushRenderer(MglContext mglContext) {
        initNativeObject(nCreate(mglContext == null ? 0L : mglContext.getNativeObject()));
    }

    private static native void nAddPoint(long j2, float f2, float f3, float f4);

    private static native void nClear(long j2);

    private static native void nClose(long j2);

    private static native void nCloseWithCrossPoint(long j2, float f2, float f3, float f4);

    private static native long nCreate(long j2);

    private static native void nDestroy(long j2, boolean z2);

    private static native void nDraw(long j2, float f2);

    private static native void nGetAabb(long j2, float[] fArr);

    private static native float nGetLength(long j2);

    private static native int nGetPointCount(long j2);

    private static native void nGetPoints(long j2, float[] fArr);

    private static native void nSetInterval(long j2, float f2);

    private static native void nSetMaterial(long j2, long j3);

    private static native void nSetMinDistance(long j2, float f2);

    private static native void nSetPath(long j2, float[] fArr);

    private static native void nSetSplineAlpha(long j2, float f2);

    private static native void nSetWidth(long j2, float f2, float f3);

    public void addPoint(float f2, float f3, float f4) {
        nAddPoint(getNativeObject(), f2, f3, f4);
    }

    public void clear() {
        nClear(getNativeObject());
    }

    public void close() {
        nClose(getNativeObject());
    }

    public void closeWithCrossPoint(float f2, float f3, float f4) {
        nCloseWithCrossPoint(getNativeObject(), f2, f3, f4);
    }

    public void destroy(boolean z2) {
        this.mDestroyGraphic = z2;
        destroyInternal();
    }

    public void draw(float f2) {
        nDraw(getNativeObject(), f2);
    }

    public void getAabb(float[] fArr) {
        nGetAabb(getNativeObject(), fArr);
    }

    public float getLength() {
        return nGetLength(getNativeObject());
    }

    public int getPointCount() {
        return nGetPointCount(getNativeObject());
    }

    public void getPoints(float[] fArr) {
        nGetPoints(getNativeObject(), fArr);
    }

    @Override // miuix.mgl.utils.NativeObject
    public void onDestroyNativeObject(long j2) {
        nDestroy(getNativeObject(), this.mDestroyGraphic);
    }

    public void setInterval(float f2) {
        nSetInterval(getNativeObject(), f2);
    }

    public void setMaterial(RenderMaterial renderMaterial) {
        this.mMaterial = renderMaterial;
        nSetMaterial(getNativeObject(), renderMaterial == null ? 0L : renderMaterial.getNativeObject());
    }

    public void setMinDistance(float f2) {
        nSetMinDistance(getNativeObject(), f2);
    }

    public void setPath(float[] fArr) {
        nSetPath(getNativeObject(), fArr);
    }

    public void setSplineAlpha(float f2) {
        nSetSplineAlpha(getNativeObject(), f2);
    }

    public void setWidth(float f2, float f3) {
        nSetWidth(getNativeObject(), f2, f3);
    }
}
