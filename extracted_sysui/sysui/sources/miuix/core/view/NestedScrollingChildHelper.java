package miuix.core.view;

import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes3.dex */
public class NestedScrollingChildHelper extends androidx.core.view.NestedScrollingChildHelper {
    private boolean mIsNestedScrollingEnabled;
    private ViewParent mNestedScrollingParentNonTouch;
    private ViewParent mNestedScrollingParentTouch;
    private int[] mTempNestedScrollConsumed;
    private final View mView;

    private NestedScrollingChildHelper(@NonNull View view) {
        super(view);
        this.mView = view;
    }

    private boolean dispatchNestedScrollInternal(int i2, int i3, int i4, int i5, @Nullable int[] iArr, int i6, @Nullable int[] iArr2) {
        ViewParent nestedScrollingParentForType;
        int i7;
        int i8;
        int[] iArr3;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(i6)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.mView.getLocationInWindow(iArr);
            i7 = iArr[0];
            i8 = iArr[1];
        } else {
            i7 = 0;
            i8 = 0;
        }
        if (iArr2 == null) {
            int[] tempNestedScrollConsumed = getTempNestedScrollConsumed();
            tempNestedScrollConsumed[0] = 0;
            tempNestedScrollConsumed[1] = 0;
            iArr3 = tempNestedScrollConsumed;
        } else {
            iArr3 = iArr2;
        }
        ViewParentCompat.onNestedScroll(nestedScrollingParentForType, this.mView, i2, i3, i4, i5, i6, iArr3);
        if (iArr != null) {
            this.mView.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i7;
            iArr[1] = iArr[1] - i8;
        }
        return true;
    }

    private ViewParent getNestedScrollingParentForType(int i2) {
        if (i2 == 0) {
            return this.mNestedScrollingParentTouch;
        }
        if (i2 != 1) {
            return null;
        }
        return this.mNestedScrollingParentNonTouch;
    }

    private int[] getTempNestedScrollConsumed() {
        if (this.mTempNestedScrollConsumed == null) {
            this.mTempNestedScrollConsumed = new int[2];
        }
        return this.mTempNestedScrollConsumed;
    }

    public static androidx.core.view.NestedScrollingChildHelper obtain(View view) {
        return new androidx.core.view.NestedScrollingChildHelper(view);
    }

    private void setNestedScrollingParentForType(int i2, ViewParent viewParent) {
        if (i2 == 0) {
            this.mNestedScrollingParentTouch = viewParent;
        } else {
            if (i2 != 1) {
                return;
            }
            this.mNestedScrollingParentNonTouch = viewParent;
        }
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        ViewParent nestedScrollingParentForType;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(0)) == null) {
            return false;
        }
        return ViewParentCompat.onNestedFling(nestedScrollingParentForType, this.mView, f2, f3, z2);
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public boolean dispatchNestedPreFling(float f2, float f3) {
        ViewParent nestedScrollingParentForType;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(0)) == null) {
            return false;
        }
        return ViewParentCompat.onNestedPreFling(nestedScrollingParentForType, this.mView, f2, f3);
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public boolean dispatchNestedPreScroll(int i2, int i3, @Nullable int[] iArr, @Nullable int[] iArr2) {
        return dispatchNestedPreScroll(i2, i3, iArr, iArr2, 0);
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, @Nullable int[] iArr) {
        return dispatchNestedScrollInternal(i2, i3, i4, i5, iArr, 0, null);
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public boolean hasNestedScrollingParent() {
        return hasNestedScrollingParent(0);
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public boolean isNestedScrollingEnabled() {
        return this.mIsNestedScrollingEnabled;
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public void onDetachedFromWindow() {
        ViewCompat.stopNestedScroll(this.mView);
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public void onStopNestedScroll(@NonNull View view) {
        ViewCompat.stopNestedScroll(this.mView);
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public void setNestedScrollingEnabled(boolean z2) {
        if (this.mIsNestedScrollingEnabled) {
            ViewCompat.stopNestedScroll(this.mView);
        }
        this.mIsNestedScrollingEnabled = z2;
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public boolean startNestedScroll(int i2) {
        return startNestedScroll(i2, 0);
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public boolean dispatchNestedPreScroll(int i2, int i3, @Nullable int[] iArr, @Nullable int[] iArr2, int i4) {
        ViewParent nestedScrollingParentForType;
        int i5;
        int i6;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(i4)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        if (iArr2 != null) {
            this.mView.getLocationInWindow(iArr2);
            i5 = iArr2[0];
            i6 = iArr2[1];
        } else {
            i5 = 0;
            i6 = 0;
        }
        if (iArr == null) {
            iArr = getTempNestedScrollConsumed();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        ViewParentCompat.onNestedPreScroll(nestedScrollingParentForType, this.mView, i2, i3, iArr, i4);
        if (iArr2 != null) {
            this.mView.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i5;
            iArr2[1] = iArr2[1] - i6;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, @Nullable int[] iArr, int i6) {
        return dispatchNestedScrollInternal(i2, i3, i4, i5, iArr, i6, null);
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public boolean hasNestedScrollingParent(int i2) {
        return getNestedScrollingParentForType(i2) != null;
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public boolean startNestedScroll(int i2, int i3) {
        if (hasNestedScrollingParent(i3)) {
            return true;
        }
        if (!isNestedScrollingEnabled()) {
            return false;
        }
        View view = this.mView;
        for (ViewParent parent = this.mView.getParent(); parent != null; parent = parent.getParent()) {
            if (ViewParentCompat.onStartNestedScroll(parent, view, this.mView, i2, i3)) {
                setNestedScrollingParentForType(i3, parent);
                ViewParentCompat.onNestedScrollAccepted(parent, view, this.mView, i2, i3);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public void stopNestedScroll(int i2) {
        ViewParent nestedScrollingParentForType = getNestedScrollingParentForType(i2);
        if (nestedScrollingParentForType != null) {
            ViewParentCompat.onStopNestedScroll(nestedScrollingParentForType, this.mView, i2);
            setNestedScrollingParentForType(i2, null);
        }
    }

    @Override // androidx.core.view.NestedScrollingChildHelper
    public void dispatchNestedScroll(int i2, int i3, int i4, int i5, @Nullable int[] iArr, int i6, @Nullable int[] iArr2) {
        dispatchNestedScrollInternal(i2, i3, i4, i5, iArr, i6, iArr2);
    }
}
