package miuix.spring.view;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class SpringHelper implements SpringStateListener {
    private AxisHandler mHorizontal = new AxisHandler(0) { // from class: miuix.spring.view.SpringHelper.1
        @Override // miuix.spring.view.SpringHelper.AxisHandler
        public boolean canScroll() {
            return SpringHelper.this.canScrollHorizontally();
        }

        @Override // miuix.spring.view.SpringHelper.AxisHandler
        public int getSize() {
            return SpringHelper.this.getWidth();
        }

        @Override // miuix.spring.view.SpringHelper.AxisHandler
        public void onFlingReachEdge() {
            SpringHelper.this.vibrate();
        }
    };
    private AxisHandler mVertical = new AxisHandler(1) { // from class: miuix.spring.view.SpringHelper.2
        @Override // miuix.spring.view.SpringHelper.AxisHandler
        public boolean canScroll() {
            return SpringHelper.this.canScrollVertically();
        }

        @Override // miuix.spring.view.SpringHelper.AxisHandler
        public int getSize() {
            return SpringHelper.this.getHeight();
        }

        @Override // miuix.spring.view.SpringHelper.AxisHandler
        public void onFlingReachEdge() {
            SpringHelper.this.vibrate();
        }
    };

    public abstract class AxisHandler {
        private static final float DRAG_RATE = 0.5f;
        private static final float INVERSE_DRAG_RATE = 2.0f;
        private static final float SQUARE1 = 0.33333334f;
        private static final float SQUARE2 = 0.6666667f;
        float mAllDistance;
        int mAmount;
        int mAxis;
        float mDistance;

        public AxisHandler(int i2) {
            this.mAxis = i2;
        }

        private float obtainSpringBackDistance(float f2) {
            int size = getSize();
            if (size == 0) {
                return Math.abs(f2) * 0.5f;
            }
            float f3 = size;
            double dMin = Math.min(Math.abs(f2) / f3, 1.0f);
            return ((float) (((Math.pow(dMin, 3.0d) / 3.0d) - Math.pow(dMin, 2.0d)) + dMin)) * f3;
        }

        private float overScrollWeight() {
            float f2 = (float) (-Math.pow(Math.abs(this.mAmount / getSize()) - 1.0f, 3.0d));
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            return f2 / 1.5f;
        }

        private boolean pull(int i2, int[] iArr, boolean z2) {
            if (i2 == 0 || !canScroll()) {
                return false;
            }
            float f2 = i2;
            float f3 = this.mAllDistance + f2;
            this.mAllDistance = f3;
            if (z2) {
                this.mDistance = Math.signum(f3) * obtainSpringBackDistance(Math.abs(this.mAllDistance));
            } else {
                if (this.mDistance == 0.0f) {
                    onFlingReachEdge();
                }
                float f4 = this.mDistance + f2;
                this.mDistance = f4;
                this.mAllDistance = Math.signum(f4) * unObtainSpringBackDistance(Math.abs(this.mDistance));
            }
            int i3 = this.mAxis;
            iArr[i3] = iArr[i3] + i2;
            return true;
        }

        private int release(int i2, int[] iArr, boolean z2) {
            float f2 = this.mDistance;
            float f3 = this.mAllDistance;
            float fSignum = Math.signum(f2);
            float f4 = this.mAllDistance + i2;
            this.mAllDistance = f4;
            if (z2) {
                this.mDistance = Math.signum(f4) * obtainSpringBackDistance(Math.abs(this.mAllDistance));
                int i3 = this.mAxis;
                iArr[i3] = iArr[i3];
            }
            int i4 = (int) (this.mDistance + (this.mAllDistance - f3));
            float f5 = i4;
            if (fSignum * f5 >= 0.0f) {
                if (!z2) {
                    this.mDistance = f5;
                }
                iArr[this.mAxis] = i2;
            } else {
                this.mDistance = 0.0f;
                iArr[this.mAxis] = (int) (iArr[r6] + f2);
            }
            float f6 = this.mDistance;
            if (f6 == 0.0f) {
                this.mAllDistance = 0.0f;
            }
            if (!z2) {
                this.mAllDistance = Math.signum(f6) * unObtainSpringBackDistance(Math.abs(this.mDistance));
            }
            return i4;
        }

        private float unObtainSpringBackDistance(float f2) {
            int size = getSize();
            if (size == 0) {
                return Math.abs(f2) * INVERSE_DRAG_RATE;
            }
            if (Math.abs(f2) / size > SQUARE1) {
                return f2 * 3.0f;
            }
            double d2 = size;
            return (float) (d2 - (Math.pow(d2, 0.6666666865348816d) * Math.pow(r1 - (Math.abs(f2) * 3.0f), 0.3333333432674408d)));
        }

        public abstract boolean canScroll();

        public abstract int getSize();

        public boolean handleNestedPreScroll(@NonNull int[] iArr, @NonNull int[] iArr2, boolean z2) {
            int i2 = iArr[this.mAxis];
            if (i2 != 0 && canScroll()) {
                float f2 = this.mDistance;
                if (f2 == 0.0f || Integer.signum((int) f2) * i2 > 0) {
                    return false;
                }
                iArr[this.mAxis] = release(i2, iArr2, z2);
                return true;
            }
            return false;
        }

        public boolean handleNestedScroll(int i2, @Nullable int[] iArr, int i3, @NonNull int[] iArr2) {
            if (SpringHelper.this.springAvailable()) {
                return pull(i2, iArr2, i3 == 0);
            }
            return false;
        }

        public abstract void onFlingReachEdge();
    }

    public abstract boolean canScrollHorizontally();

    public abstract boolean canScrollVertically();

    public abstract boolean dispatchNestedPreScroll(int i2, int i3, @Nullable int[] iArr, @Nullable int[] iArr2, int i4);

    public abstract void dispatchNestedScroll(int i2, int i3, int i4, int i5, @Nullable int[] iArr, int i6, @Nullable int[] iArr2);

    public abstract int getHeight();

    public int getHorizontalDistance() {
        return (int) this.mHorizontal.mDistance;
    }

    public int getVerticalDistance() {
        return (int) this.mVertical.mDistance;
    }

    public abstract int getWidth();

    public boolean handleNestedPreScroll(int i2, int i3, @Nullable int[] iArr, @Nullable int[] iArr2, int i4) {
        int i5;
        int i6;
        boolean z2;
        int[] iArr3 = {0, 0};
        if (springAvailable()) {
            boolean z3 = i4 == 0;
            int[] iArr4 = {i2, i3};
            boolean zHandleNestedPreScroll = this.mVertical.handleNestedPreScroll(iArr4, iArr3, z3) | this.mHorizontal.handleNestedPreScroll(iArr4, iArr3, z3);
            i5 = iArr4[0];
            i6 = iArr4[1];
            if (zHandleNestedPreScroll) {
                onSpringDistanceChanged(this.mHorizontal.mAllDistance, this.mVertical.mAllDistance);
            }
            z2 = zHandleNestedPreScroll;
        } else {
            i5 = i2;
            i6 = i3;
            z2 = false;
        }
        if (z2) {
            i5 -= iArr3[0];
            i6 -= iArr3[1];
        }
        boolean zDispatchNestedPreScroll = dispatchNestedPreScroll(i5, i6, iArr, iArr2, i4) | z2;
        if (iArr != null) {
            iArr[0] = iArr[0] + iArr3[0];
            iArr[1] = iArr[1] + iArr3[1];
        }
        return zDispatchNestedPreScroll;
    }

    public void handleNestedScroll(int i2, int i3, int i4, int i5, @Nullable int[] iArr, int i6, @Nullable int[] iArr2) {
        int[] iArr3 = iArr2 == null ? new int[]{0, 0} : iArr2;
        dispatchNestedScroll(i2, i3, i4, i5, iArr, i6, iArr3);
        int i7 = i4 - iArr3[0];
        int i8 = i5 - iArr3[1];
        if (i7 == 0 && i8 == 0) {
            return;
        }
        boolean zHandleNestedScroll = this.mHorizontal.handleNestedScroll(i7, iArr, i6, iArr3);
        boolean zHandleNestedScroll2 = this.mVertical.handleNestedScroll(i8, iArr, i6, iArr3);
        if (zHandleNestedScroll || zHandleNestedScroll2) {
            onSpringDistanceChanged(this.mHorizontal.mAllDistance, this.mVertical.mAllDistance);
        }
    }

    public void resetDistance() {
        AxisHandler axisHandler = this.mHorizontal;
        boolean z2 = (axisHandler.mAllDistance == 0.0f && this.mVertical.mAllDistance == 0.0f) ? false : true;
        AxisHandler axisHandler2 = this.mVertical;
        axisHandler2.mDistance = 0.0f;
        axisHandler2.mAllDistance = 0.0f;
        axisHandler.mDistance = 0.0f;
        axisHandler.mAllDistance = 0.0f;
        if (z2) {
            onSpringDistanceChanged(0.0f, axisHandler2.mAllDistance);
        }
    }

    public void setHorizontalDistance(int i2) {
        this.mHorizontal.mDistance = i2;
    }

    public void setVerticalDistance(int i2) {
        this.mVertical.mDistance = i2;
    }

    public abstract boolean springAvailable();

    @Keep
    public abstract void vibrate();
}
