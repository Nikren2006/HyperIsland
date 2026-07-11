package miuix.mgl;

import miuix.mgl.math.Matrix4x4;
import miuix.mgl.math.Vector3;
import miuix.mgl.utils.NativeObject;

/* JADX INFO: loaded from: classes3.dex */
public class Camera extends MglObject {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    Matrix4x4 mProjectionMatrix;
    Transform mTransform;
    Matrix4x4 mVPMatrix;
    Matrix4x4 mViewMatrix;

    public static class Builder extends NativeObject {
        private Transform mTransform;

        public Builder() {
            initNativeObject(nCreateBuilder());
        }

        public static Builder create() {
            return new Builder();
        }

        private static native long nBuild(long j2);

        private static native long nCreateBuilder();

        private static native void nDestroyBuilder(long j2);

        private static native void nFarClip(long j2, float f2);

        private static native void nFov(long j2, float f2);

        private static native void nNearClip(long j2, float f2);

        private static native void nOrthographicSize(long j2, float f2);

        private static native void nProjectionType(long j2, int i2);

        private static native void nTransform(long j2, long j3);

        public Camera build() {
            long jNBuild = nBuild(getNativeObject());
            destroyInternal();
            return new Camera(jNBuild, this.mTransform);
        }

        public Builder farClip(float f2) {
            nFarClip(getNativeObject(), f2);
            return this;
        }

        public Builder fov(float f2) {
            nFov(getNativeObject(), f2);
            return this;
        }

        public Builder nearClip(float f2) {
            nNearClip(getNativeObject(), f2);
            return this;
        }

        @Override // miuix.mgl.utils.NativeObject
        public void onDestroyNativeObject(long j2) {
            nDestroyBuilder(getNativeObject());
        }

        public Builder orthographicSize(float f2) {
            nOrthographicSize(getNativeObject(), f2);
            return this;
        }

        public Builder projectionType(ProjectionType projectionType) {
            nProjectionType(getNativeObject(), projectionType.ordinal());
            return this;
        }

        public Builder transform(Transform transform) {
            this.mTransform = transform;
            nTransform(getNativeObject(), transform.getNativeObject());
            return this;
        }
    }

    public enum ProjectionType {
        PERSPECTIVE,
        ORTHO
    }

    public Camera(long j2, Transform transform) {
        super(j2);
        this.mProjectionMatrix = new Matrix4x4();
        this.mViewMatrix = new Matrix4x4();
        this.mVPMatrix = new Matrix4x4();
        this.mTransform = transform;
    }

    public static float calculateAabbScreenSize(Camera camera, Transform transform, Primitive primitive, float f2) {
        return nCalculateAabbScreenSize(camera.getNativeObject(), transform.getNativeObject(), primitive.getNativeObject(), f2);
    }

    public static void getMVP(Matrix4x4 matrix4x4, Matrix4x4 matrix4x42, Matrix4x4 matrix4x43) {
        Matrix4x4.Companion.times(matrix4x4, matrix4x42, matrix4x43);
    }

    private static native float nCalculateAabbScreenSize(long j2, long j3, long j4, float f2);

    private static native float nCalculateAabbScreenSizeV2(long j2, long j3, float[] fArr, float f2);

    private static native float nCalculateAabbScreenSizeV3(long j2, float[] fArr, float[] fArr2, float f2);

    private static native float nGetFar(long j2);

    private static native float nGetFov(long j2);

    private static native float nGetNear(long j2);

    private static native void nGetProjectionMatrix(long j2, float[] fArr);

    private static native long nGetTransform(long j2);

    private static native void nGetVPMatrix(long j2, float[] fArr);

    private static native void nGetViewMatrix(long j2, float[] fArr);

    private static native void nGetViewPlanes(long j2, float[] fArr);

    private static native boolean nIsAABBInView(float[] fArr, float[] fArr2, float[] fArr3);

    private static native boolean nIsAABBInViewSelf(long j2, float[] fArr, float[] fArr2);

    private static native boolean nIsAABBInViewSelfV2(long j2, float f2, float f3, float f4, float f5, float f6, float f7);

    private static native void nLookAt(long j2, float[] fArr, float[] fArr2, float[] fArr3);

    private static native void nSetFarClip(long j2, float f2);

    private static native void nSetFov(long j2, float f2);

    private static native void nSetNearClip(long j2, float f2);

    private static native void nSetViewSize(long j2, float f2, float f3);

    private static native void nUpdateViewPlanes(long j2);

    private static native void nViewPortToWorldPosition(long j2, float f2, float f3, float f4, float[] fArr);

    private static native void nWorldPositionToViewPort(long j2, float f2, float f3, float f4, float[] fArr);

    public float getFarClip() {
        return nGetFar(getNativeObject());
    }

    public float getFov() {
        return nGetFov(getNativeObject());
    }

    public float getNearClip() {
        return nGetNear(getNativeObject());
    }

    public Matrix4x4 getProjectionMatrix() {
        nGetProjectionMatrix(getNativeObject(), this.mProjectionMatrix.getData());
        return this.mProjectionMatrix;
    }

    public Transform getTransform() {
        if (this.mTransform == null) {
            this.mTransform = Transform.createFromNative(nGetTransform(getNativeObject()));
        }
        return this.mTransform;
    }

    public Matrix4x4 getVPMatrix() {
        nGetVPMatrix(getNativeObject(), this.mVPMatrix.getData());
        return this.mVPMatrix;
    }

    public Matrix4x4 getViewMatrix() {
        nGetViewMatrix(getNativeObject(), this.mViewMatrix.getData());
        return this.mViewMatrix;
    }

    public float[] getViewPlanes() {
        float[] fArr = new float[24];
        nGetViewPlanes(getNativeObject(), fArr);
        return fArr;
    }

    public boolean isAABBInView(float[] fArr, float[] fArr2) {
        return nIsAABBInViewSelf(getNativeObject(), fArr, fArr2);
    }

    public void lookAt(float[] fArr, float[] fArr2, float[] fArr3) {
        nLookAt(getNativeObject(), fArr, fArr2, fArr3);
    }

    public void setFarClip(float f2) {
        nSetFarClip(getNativeObject(), f2);
    }

    public void setFov(float f2) {
        nSetFov(getNativeObject(), f2);
    }

    public void setNearClip(float f2) {
        nSetNearClip(getNativeObject(), f2);
    }

    public void setViewSize(float f2, float f3) {
        nSetViewSize(getNativeObject(), f2, f3);
    }

    public void updateViewPlanes() {
        nUpdateViewPlanes(getNativeObject());
    }

    public void viewPortToWorldPosition(float f2, float f3, float f4, float[] fArr) {
        nViewPortToWorldPosition(getNativeObject(), f2, f3, f4, fArr);
    }

    public void worldPositionToViewPort(float f2, float f3, float f4, float[] fArr) {
        nWorldPositionToViewPort(getNativeObject(), f2, f3, f4, fArr);
    }

    public static float calculateAabbScreenSize(Camera camera, Transform transform, float[] fArr, float f2) {
        return nCalculateAabbScreenSizeV2(camera.getNativeObject(), transform.getNativeObject(), fArr, f2);
    }

    public boolean isAABBInView(float f2, float f3, float f4, float f5, float f6, float f7) {
        return nIsAABBInViewSelfV2(getNativeObject(), f2, f3, f4, f5, f6, f7);
    }

    public void lookAt(Vector3 vector3, Vector3 vector32, Vector3 vector33) {
        lookAt(vector3.getData(), vector32.getData(), vector32.getData());
    }

    public static float calculateAabbScreenSize(Camera camera, float[] fArr, float[] fArr2, float f2) {
        return nCalculateAabbScreenSizeV3(camera.getNativeObject(), fArr, fArr2, f2);
    }

    public static boolean isAABBInView(float[] fArr, float[] fArr2, float[] fArr3) {
        return nIsAABBInView(fArr, fArr2, fArr3);
    }

    public void getViewPlanes(float[] fArr) {
        nGetViewPlanes(getNativeObject(), fArr);
    }
}
