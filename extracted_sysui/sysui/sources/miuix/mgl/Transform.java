package miuix.mgl;

import miuix.mgl.math.Matrix4x4;
import miuix.mgl.math.Quaternion;
import miuix.mgl.math.Vector3;

/* JADX INFO: loaded from: classes3.dex */
public class Transform extends MglObject {
    Matrix4x4 mLocalMatrix;
    Vector3 mLocalPosition;
    Quaternion mLocalRotation;
    Vector3 mLocalScale;
    Matrix4x4 mLocalToWorldMatrix;
    Transform mParent;
    Vector3 mPosition;
    Quaternion mRotation;
    Vector3 mScale;
    Matrix4x4 mWorldToLocalMatrix;

    public Transform(long j2) {
        super(j2);
        this.mLocalToWorldMatrix = new Matrix4x4();
        this.mWorldToLocalMatrix = new Matrix4x4();
        this.mLocalMatrix = new Matrix4x4();
        this.mLocalPosition = new Vector3();
        this.mLocalScale = new Vector3();
        this.mLocalRotation = new Quaternion();
        this.mPosition = new Vector3();
        this.mScale = new Vector3();
        this.mRotation = new Quaternion();
    }

    public static Transform create() {
        return new Transform(nMake());
    }

    public static Transform createFromNative(long j2) {
        Transform transform = new Transform(j2);
        transform.setTempRef();
        return transform;
    }

    private static native void nGetLocalPosition(long j2, float[] fArr);

    private static native void nGetLocalScale(long j2, float[] fArr);

    private static native void nGetLocalToWorldMatrix(long j2, float[] fArr);

    private static native long nMake();

    private static native long nMakeWithParam(long j2, float[] fArr, float[] fArr2, float[] fArr3);

    private static native void nSetLocalEulerAngle(long j2, float f2, float f3, float f4);

    private static native void nSetLocalPosition(long j2, float f2, float f3, float f4);

    private static native void nSetLocalScale(long j2, float f2, float f3, float f4);

    private static native void nSetParent(long j2, long j3);

    public Vector3 getLocalPosition() {
        nGetLocalPosition(getNativeObject(), this.mLocalPosition.getData());
        return this.mLocalPosition;
    }

    public Vector3 getLocalScale() {
        nGetLocalScale(getNativeObject(), this.mLocalScale.getData());
        return this.mLocalScale;
    }

    public Matrix4x4 getLocalToWorldMatrix() {
        nGetLocalToWorldMatrix(getNativeObject(), this.mLocalToWorldMatrix.getData());
        return this.mLocalToWorldMatrix;
    }

    public Transform getParent() {
        return this.mParent;
    }

    public void setLocalEulerAngle(float f2, float f3, float f4) {
        nSetLocalEulerAngle(getNativeObject(), f2, f3, f4);
    }

    public void setLocalPosition(float f2, float f3, float f4) {
        nSetLocalPosition(getNativeObject(), f2, f3, f4);
    }

    public void setLocalScale(float f2, float f3, float f4) {
        nSetLocalScale(getNativeObject(), f2, f3, f4);
    }

    public void setParent(Transform transform) {
        this.mParent = transform;
        nSetParent(getNativeObject(), transform == null ? 0L : transform.getNativeObject());
    }

    public void setLocalEulerAngle(Vector3 vector3) {
        setLocalEulerAngle(vector3.getX(), vector3.getY(), vector3.getZ());
    }

    public void setLocalPosition(Vector3 vector3) {
        setLocalPosition(vector3.getX(), vector3.getY(), vector3.getZ());
    }

    public void setLocalScale(Vector3 vector3) {
        setLocalScale(vector3.getX(), vector3.getY(), vector3.getZ());
    }

    public static Transform create(Transform transform, Vector3 vector3, Quaternion quaternion, Vector3 vector32) {
        Transform transform2 = new Transform(nMakeWithParam(transform == null ? 0L : transform.getNativeObject(), vector3.getData(), quaternion.getData(), vector32.getData()));
        transform2.mParent = transform;
        return transform2;
    }
}
