package miuix.appcompat.app.strategy;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import miuix.appcompat.app.DialogContract;

/* JADX INFO: loaded from: classes2.dex */
public class DialogPanelBehaviorImpl implements IDialogPanelBehavior {
    private boolean judgeLandscape(Point point, int i2) {
        int i3 = point.x;
        int i4 = point.y;
        if (i3 > i4) {
            return true;
        }
        return i3 >= i4 && i2 == 2;
    }

    @Override // miuix.appcompat.app.strategy.IDialogPanelBehavior
    public int calcDesignedPanelWidth(DialogContract.PanelWidthSpec panelWidthSpec, DialogContract.DimensConfig dimensConfig) {
        boolean zShouldLimitPanelWidth = shouldLimitPanelWidth(panelWidthSpec.mUsableWindowWidthDp);
        int i2 = panelWidthSpec.mUseLandscapeLayout ? dimensConfig.panelMaxWidthLand : zShouldLimitPanelWidth ? dimensConfig.panelMaxWidth : panelWidthSpec.mIsLandscapeWindow ? panelWidthSpec.mMarkLandscapeWindow ? dimensConfig.fakeLandScreenMinorSize : panelWidthSpec.mScreenMinorSize : -1;
        if (i2 != -1 && panelWidthSpec.mIsCarWithScreen) {
            i2 = (int) (i2 * 0.8f);
        }
        if (panelWidthSpec.mIsDebugMode) {
            Log.d(IDialogPanelBehavior.TAG, "calcDesignedPanelWidth: panelWidthSpec = " + panelWidthSpec);
            Log.d(IDialogPanelBehavior.TAG, "calcDesignedPanelWidth: shouldLimitPanelLimit = " + zShouldLimitPanelWidth);
            Log.d(IDialogPanelBehavior.TAG, "calcDesignedPanelWidth: panelWidth = " + i2);
        }
        return i2;
    }

    @Override // miuix.appcompat.app.strategy.IDialogPanelBehavior
    public int calcDesignedWidthMargin(DialogContract.DimensConfig dimensConfig, int i2) {
        if (i2 < 360) {
            return dimensConfig.widthSmallMargin;
        }
        if (i2 <= 394) {
            return dimensConfig.widthMargin;
        }
        return 0;
    }

    @Override // miuix.appcompat.app.strategy.IDialogPanelBehavior
    public int calcPanelPosition(DialogContract.PanelPosSpec panelPosSpec, DialogContract.DimensConfig dimensConfig, Rect rect) {
        int i2;
        int i3;
        Rect rect2 = rect == null ? new Rect() : rect;
        int iMax = Math.max(panelPosSpec.mRootViewSizeX, panelPosSpec.mRootViewWidth);
        boolean z2 = panelPosSpec.mRootViewPaddingLeft + panelPosSpec.mRootViewPaddingRight > 0;
        int i4 = panelPosSpec.mDesignedPanelWidth;
        int iCalcDesignedWidthMargin = calcDesignedWidthMargin(dimensConfig, panelPosSpec.mUsableWindowWidthDp);
        if (i4 == -1) {
            i4 = panelPosSpec.mUsableWindowWidth - (iCalcDesignedWidthMargin * 2);
        }
        int i5 = panelPosSpec.mIsFlipTiny ? dimensConfig.widthSmallMargin : dimensConfig.extraImeMargin;
        int iMax2 = Math.max(panelPosSpec.mBoundInsets.top, i5);
        Rect rect3 = panelPosSpec.mBoundInsets;
        int i6 = rect3.left;
        int i7 = rect3.right;
        int i8 = (i6 + i7) / 2;
        int i9 = (iMax - i4) / 2;
        boolean z3 = i9 < i6 || i9 < i7;
        if (panelPosSpec.mIsDebugMode) {
            Log.d(IDialogPanelBehavior.TAG, "calcPanelPosition: panelPosSpec = " + panelPosSpec);
            Log.d(IDialogPanelBehavior.TAG, "calcPanelPosition: avoidMoved = " + i8);
            Log.d(IDialogPanelBehavior.TAG, "calcPanelPosition: horizontalMargin = " + iCalcDesignedWidthMargin);
            Log.d(IDialogPanelBehavior.TAG, "calcPanelPosition: centerBlankSpace = " + i9);
            Log.d(IDialogPanelBehavior.TAG, "calcPanelPosition: widthSmallMargin = " + dimensConfig.widthSmallMargin);
        }
        if (i8 == 0 || !z3 || z2) {
            i2 = iCalcDesignedWidthMargin;
            i3 = i2;
        } else {
            Rect rect4 = panelPosSpec.mBoundInsets;
            int i10 = rect4.left;
            int i11 = rect4.right;
            if (i10 > i11) {
                i2 = i8 + iCalcDesignedWidthMargin;
                i3 = iCalcDesignedWidthMargin;
            } else if (i10 < i11) {
                i3 = i8 + iCalcDesignedWidthMargin;
                i2 = iCalcDesignedWidthMargin;
            } else {
                i2 = iCalcDesignedWidthMargin;
                i3 = i2;
            }
            if (panelPosSpec.mIsDebugMode) {
                Log.d(IDialogPanelBehavior.TAG, "calcPanelPosition: leftMargin = " + i2);
                Log.d(IDialogPanelBehavior.TAG, "calcPanelPosition: rightMargin = " + i3);
                Log.d(IDialogPanelBehavior.TAG, "calcPanelPosition: realRootViewWidth = " + iMax);
            }
        }
        boolean z4 = i9 < i8;
        if (z4) {
            i4 = panelPosSpec.mUsableWindowWidth - (iCalcDesignedWidthMargin * 2);
        }
        if (panelPosSpec.mIsDebugMode) {
            Log.d(IDialogPanelBehavior.TAG, "calcPanelPosition: isOverflow = " + z4);
            Log.d(IDialogPanelBehavior.TAG, "calcPanelPosition: panelWidth = " + i4);
        }
        rect2.left = i2;
        rect2.top = iMax2;
        rect2.right = i3;
        rect2.bottom = i5;
        return i4;
    }

    @Override // miuix.appcompat.app.strategy.IDialogPanelBehavior
    public boolean isLandscapeWindow(DialogContract.OrientationSpec orientationSpec) {
        if (orientationSpec.mMarkLandscape) {
            return true;
        }
        if (orientationSpec.mInFreeFrom) {
            return judgeLandscape(orientationSpec.mWindowSize, orientationSpec.mScreenOrientation);
        }
        if (orientationSpec.mScreenOrientation != 2) {
            return false;
        }
        if (orientationSpec.mIsCarWithScreen || orientationSpec.mIsSynergy) {
            Point point = orientationSpec.mRealScreenSize;
            return point.x > point.y;
        }
        Point point2 = orientationSpec.mUsableWindowSizeDp;
        int i2 = point2.x;
        return i2 >= 394 && i2 > point2.y;
    }

    @Override // miuix.appcompat.app.strategy.IDialogPanelBehavior
    public boolean shouldLimitPanelWidth(int i2) {
        return i2 >= 394;
    }
}
