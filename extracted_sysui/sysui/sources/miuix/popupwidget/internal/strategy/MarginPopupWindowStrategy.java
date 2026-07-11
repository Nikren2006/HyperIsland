package miuix.popupwidget.internal.strategy;

import android.graphics.Rect;
import android.view.Gravity;

/* JADX INFO: loaded from: classes5.dex */
public class MarginPopupWindowStrategy implements IPopupWindowStrategy {
    private static final int ANIMATION_MARGIN = 70;
    private static final int HALF_ANIMATION_MARGIN = 35;

    private int getXInWindowAlightRight(PopupWindowSpec popupWindowSpec) {
        Rect rect = popupWindowSpec.mAnchorViewBounds;
        Rect rect2 = popupWindowSpec.mDecorViewBounds;
        Rect rect3 = popupWindowSpec.mSafeInsets;
        int i2 = popupWindowSpec.mFinalPopupWidth;
        int i3 = rect.right + 35;
        int i4 = rect2.right;
        int i5 = rect3.right;
        if (i3 > (i4 - i5) + 35) {
            i3 = (i4 - i5) + 35;
        }
        int i6 = i3 - i2;
        int i7 = rect2.left;
        int i8 = rect3.left;
        if (i6 < (i7 + i8) - 35) {
            i6 = (i7 + i8) - 35;
        }
        if (i6 + i2 > (i4 - i5) + 35) {
            i2 = (i4 - i5) - i6;
        }
        popupWindowSpec.mFinalPopupWidth = i2;
        return i6;
    }

    private int getXInWindowAlignCenterHorizontal(PopupWindowSpec popupWindowSpec) {
        Rect rect = popupWindowSpec.mAnchorViewBounds;
        Rect rect2 = popupWindowSpec.mDecorViewBounds;
        Rect rect3 = popupWindowSpec.mSafeInsets;
        int i2 = popupWindowSpec.mFinalPopupWidth;
        int iCenterX = rect.centerX() - (i2 / 2);
        int i3 = iCenterX + i2;
        int i4 = rect2.right;
        int i5 = rect3.right;
        if (i3 > (i4 - i5) + 35) {
            iCenterX = ((i4 - i5) + 35) - i2;
        }
        int i6 = rect2.left;
        int i7 = rect3.left;
        if (iCenterX < (i6 + i7) - 35) {
            iCenterX = (i6 + i7) - 35;
        }
        if (iCenterX + i2 > (i4 - i5) + 35) {
            i2 = ((i4 - i5) + 35) - iCenterX;
        }
        popupWindowSpec.mFinalPopupWidth = i2;
        return iCenterX;
    }

    private int getXInWindowAlignLeft(PopupWindowSpec popupWindowSpec) {
        Rect rect = popupWindowSpec.mAnchorViewBounds;
        Rect rect2 = popupWindowSpec.mDecorViewBounds;
        Rect rect3 = popupWindowSpec.mSafeInsets;
        int i2 = popupWindowSpec.mFinalPopupWidth;
        int i3 = rect.left - 35;
        int i4 = rect2.left;
        int i5 = rect3.left;
        if (i3 < (i4 + i5) - 35) {
            i3 = (i4 + i5) - 35;
        }
        int i6 = i3 + i2;
        int i7 = rect2.right;
        int i8 = rect3.right;
        if (i6 > (i7 - i8) + 35) {
            i6 = (i7 - i8) + 35;
        }
        int i9 = i6 - i2;
        if (i9 >= (i4 + i5) - 35) {
            return i9;
        }
        int i10 = (i4 + i5) - 35;
        popupWindowSpec.mFinalPopupWidth = i6 - i10;
        return i10;
    }

    private int getYInWindowAlignBottom(PopupWindowSpec popupWindowSpec) {
        Rect rect = popupWindowSpec.mAnchorViewBounds;
        Rect rect2 = popupWindowSpec.mDecorViewBounds;
        Rect rect3 = popupWindowSpec.mSafeInsets;
        int i2 = popupWindowSpec.mFinalPopupHeight - 70;
        int i3 = rect.bottom;
        int i4 = rect2.top;
        int i5 = rect3.top;
        if (i3 < i4 + i5) {
            i3 = i4 + i5;
        }
        int i6 = i3 + i2;
        int i7 = rect2.bottom;
        int i8 = rect3.bottom;
        if (i6 < i7 - i8) {
            return i3 - 35;
        }
        int i9 = rect.top;
        if (i7 - i9 >= i9 - i4) {
            int iMin = (i7 - i8) - i3;
            if (iMin < popupWindowSpec.mMinHeight) {
                iMin = Math.min(i2, (rect2.height() - rect3.top) - rect3.bottom);
                i3 = (rect2.bottom - rect3.bottom) - iMin;
            }
            popupWindowSpec.mFinalPopupHeight = iMin + 70;
        } else {
            int iMin2 = Math.min(i2, (i9 - i4) - i5);
            if (iMin2 < popupWindowSpec.mMinHeight) {
                iMin2 = Math.min(i2, (rect2.height() - rect3.top) - rect3.bottom);
            }
            popupWindowSpec.mFinalPopupHeight = iMin2 + 70;
            i3 = rect.top - iMin2;
        }
        return i3 - 35;
    }

    private int getYInWindowAlignTop(PopupWindowSpec popupWindowSpec) {
        Rect rect = popupWindowSpec.mAnchorViewBounds;
        Rect rect2 = popupWindowSpec.mDecorViewBounds;
        Rect rect3 = popupWindowSpec.mSafeInsets;
        int i2 = popupWindowSpec.mFinalPopupHeight - 70;
        int i3 = rect.top;
        int i4 = rect2.top;
        int i5 = rect3.top;
        int i6 = i3 < i4 + i5 ? i4 + i5 : i3;
        int i7 = i6 + i2;
        int i8 = rect2.bottom;
        int i9 = rect3.bottom;
        if (i7 < i8 - i9) {
            return i6 - 35;
        }
        if (i8 - i3 >= i3 - i4) {
            int iMin = (i8 - i9) - i6;
            if (iMin < popupWindowSpec.mMinHeight) {
                iMin = Math.min(i2, (rect2.height() - rect3.top) - rect3.bottom);
                i6 = (rect2.bottom - rect3.bottom) - iMin;
            }
            popupWindowSpec.mFinalPopupHeight = iMin + 70;
            return i6;
        }
        int iMin2 = Math.min(i2, (i3 - i4) - i5);
        if (iMin2 < popupWindowSpec.mMinHeight) {
            iMin2 = Math.min(i2, (rect2.height() - rect3.top) - rect3.bottom);
        }
        int i10 = rect.top - iMin2;
        popupWindowSpec.mFinalPopupHeight = iMin2 + 70;
        return i10;
    }

    @Override // miuix.popupwidget.internal.strategy.IPopupWindowStrategy
    public int getXInWindow(PopupWindowSpec popupWindowSpec) {
        int absoluteGravity = Gravity.getAbsoluteGravity(popupWindowSpec.mGravity, popupWindowSpec.layoutDirection) & 7;
        return absoluteGravity != 1 ? absoluteGravity != 5 ? getXInWindowAlignLeft(popupWindowSpec) : getXInWindowAlightRight(popupWindowSpec) : getXInWindowAlignCenterHorizontal(popupWindowSpec);
    }

    @Override // miuix.popupwidget.internal.strategy.IPopupWindowStrategy
    public int getYInWindow(PopupWindowSpec popupWindowSpec) {
        return (popupWindowSpec.mGravity & 112) != 48 ? getYInWindowAlignBottom(popupWindowSpec) : getYInWindowAlignTop(popupWindowSpec);
    }

    @Override // miuix.popupwidget.internal.strategy.IPopupWindowStrategy
    public boolean isNeedScroll(int i2, PopupWindowSpec popupWindowSpec) {
        int i3 = popupWindowSpec.mContentHeight;
        return i3 > i2 || i3 > popupWindowSpec.mMaxHeight;
    }

    @Override // miuix.popupwidget.internal.strategy.IPopupWindowStrategy
    public void measureContentSize(PopupWindowSpec popupWindowSpec) {
        int[][] iArr = popupWindowSpec.mItemViewBounds;
        if (iArr == null) {
            Rect rect = popupWindowSpec.mContentViewBounds;
            popupWindowSpec.mContentHeight = rect.height();
            popupWindowSpec.mFinalPopupWidth = rect.width();
            popupWindowSpec.mFinalPopupHeight = rect.height();
            return;
        }
        int i2 = popupWindowSpec.mMaxWidth;
        int i3 = popupWindowSpec.mMaxHeight;
        int i4 = 0;
        int iMax = 0;
        for (int[] iArr2 : iArr) {
            int i5 = iArr2[0];
            int i6 = iArr2[1];
            if (i5 > i2) {
                i5 = i2;
            }
            iMax = Math.max(i5, iMax);
            i4 += i6;
        }
        popupWindowSpec.mContentHeight = i4;
        if (i4 <= i3) {
            i3 = i4;
        }
        popupWindowSpec.mFinalPopupHeight = i3 + 70;
        int iMax2 = Math.max(iMax, popupWindowSpec.mMinWidth);
        popupWindowSpec.mContentWidth = iMax2;
        popupWindowSpec.mFinalPopupWidth = iMax2 + 70;
    }
}
