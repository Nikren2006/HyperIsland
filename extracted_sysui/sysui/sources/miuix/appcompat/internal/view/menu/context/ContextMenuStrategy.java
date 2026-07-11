package miuix.appcompat.internal.view.menu.context;

import android.content.Context;
import android.graphics.Rect;
import miuix.appcompat.R;
import miuix.popupwidget.internal.strategy.IPopupWindowStrategy;
import miuix.popupwidget.internal.strategy.PopupWindowSpec;

/* JADX INFO: loaded from: classes3.dex */
public class ContextMenuStrategy implements IPopupWindowStrategy {
    private static final float SCREEN_MARGIN_BOTTOM_PROPORTION = 0.1f;
    private static final float SCREEN_MARGIN_TOP_PROPORTION = 0.1f;
    private int marginScreen;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private float f6087x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private float f6088y;

    public ContextMenuStrategy(Context context, float f2, float f3) {
        this.f6087x = f2;
        this.f6088y = f3;
        this.marginScreen = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_context_menu_window_margin_screen);
    }

    @Override // miuix.popupwidget.internal.strategy.IPopupWindowStrategy
    public int getXInWindow(PopupWindowSpec popupWindowSpec) {
        Rect rect = popupWindowSpec.mAnchorViewBounds;
        Rect rect2 = popupWindowSpec.mDecorViewBounds;
        int i2 = rect.left + ((int) this.f6087x);
        return i2 <= popupWindowSpec.mContentWidth ? this.marginScreen : i2 >= rect2.width() - popupWindowSpec.mContentWidth ? (rect2.width() - this.marginScreen) - popupWindowSpec.mContentWidth : i2;
    }

    @Override // miuix.popupwidget.internal.strategy.IPopupWindowStrategy
    public int getYInWindow(PopupWindowSpec popupWindowSpec) {
        Rect rect = popupWindowSpec.mAnchorViewBounds;
        Rect rect2 = popupWindowSpec.mDecorViewBounds;
        float fHeight = (rect.top + ((int) this.f6088y)) - (popupWindowSpec.mContentHeight / 2);
        if (fHeight < rect2.height() * 0.1f) {
            fHeight = rect2.height() * 0.1f;
        }
        float f2 = popupWindowSpec.mContentHeight;
        if (fHeight + f2 > rect2.height() * 0.9f) {
            fHeight = (rect2.height() * 0.9f) - f2;
        }
        if (fHeight < rect2.height() * 0.1f) {
            fHeight = rect2.height() * 0.1f;
            popupWindowSpec.mFinalPopupHeight = (int) (rect2.height() * 0.79999995f);
        }
        return (int) fHeight;
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
        popupWindowSpec.mFinalPopupHeight = i3;
        int iMax2 = Math.max(iMax, popupWindowSpec.mMinWidth);
        popupWindowSpec.mContentWidth = iMax2;
        popupWindowSpec.mFinalPopupWidth = iMax2;
    }
}
