package miuix.appcompat.internal.view.menu.action;

import android.R;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.appcompat.internal.view.menu.MenuItemImpl;
import miuix.appcompat.internal.view.menu.MenuView;
import miuix.internal.util.DeviceHelper;
import miuix.view.BlurableWidget;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ActionMenuView extends LinearLayout implements MenuBuilder.ItemInvoker, MenuView, BlurableWidget {
    protected boolean mBackgroundViewApplyBlur;
    private MenuBuilder mMenu;
    private OpenCloseAnimator mOpenCloseAnimator;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;

    public interface ActionMenuChildView {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {

        @ViewDebug.ExportedProperty
        public int cellsUsed;

        @ViewDebug.ExportedProperty
        public boolean expandable;
        public boolean expanded;

        @ViewDebug.ExportedProperty
        public int extraPixels;

        @ViewDebug.ExportedProperty
        public boolean isOverflowButton;

        @ViewDebug.ExportedProperty
        public boolean preventEdgeOffset;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((LinearLayout.LayoutParams) layoutParams);
            this.isOverflowButton = layoutParams.isOverflowButton;
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.isOverflowButton = false;
        }

        public LayoutParams(int i2, int i3, boolean z2) {
            super(i2, i3);
            this.isOverflowButton = z2;
        }
    }

    public class OpenCloseAnimator implements Animator.AnimatorListener {
        Animator mCloseAnimator;
        Animator mCurrentAnimator;
        boolean mIsOpen;

        public OpenCloseAnimator() {
        }

        public void cancel() {
            initialize();
            Animator animator = this.mCurrentAnimator;
            if (animator != null) {
                animator.cancel();
                this.mCurrentAnimator = null;
            }
        }

        public void close() {
            cancel();
            this.mIsOpen = false;
            this.mCloseAnimator.start();
        }

        public void initialize() {
            if (this.mCloseAnimator == null) {
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "TranslationY", ActionMenuView.this.getHeight());
                this.mCloseAnimator = objectAnimatorOfFloat;
                objectAnimatorOfFloat.setDuration(ActionMenuView.this.getResources().getInteger(R.integer.config_shortAnimTime));
                this.mCloseAnimator.addListener(this);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.mCurrentAnimator = null;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.mCurrentAnimator = animator;
        }

        public void open() {
            cancel();
            this.mIsOpen = true;
            setTranslationY(0.0f);
            ActionMenuView.this.startLayoutAnimation();
        }

        public void setTranslationY(float f2) {
            for (int i2 = 0; i2 < ActionMenuView.this.getChildCount(); i2++) {
                ActionMenuView.this.getChildAt(i2).setTranslationY(f2);
            }
        }
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof LayoutParams);
    }

    public void clearBackground() {
    }

    public float computeAlpha(float f2, boolean z2, boolean z3) {
        int i2;
        if (z2 && z3) {
            return 1.0f;
        }
        if (z2) {
            i2 = (int) ((1.0f - f2) * 10.0f);
        } else {
            if (!z3) {
                return 1.0f;
            }
            i2 = (int) (f2 * 10.0f);
        }
        return i2 / 10.0f;
    }

    public float computeTranslationY(float f2, boolean z2, boolean z3) {
        float collapsedHeight = getCollapsedHeight();
        if (z2 && z3) {
            f2 = ((double) f2) < 0.5d ? f2 * 2.0f : (1.0f - f2) * 2.0f;
        } else if (z3) {
            f2 = 1.0f - f2;
        }
        return f2 * collapsedHeight;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView
    public boolean filterLeftoverView(int i2) {
        View childAt = getChildAt(i2);
        childAt.clearAnimation();
        removeView(childAt);
        return true;
    }

    public LayoutParams generateOverflowButtonLayoutParams(@NonNull View view) {
        LayoutParams layoutParamsGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        layoutParamsGenerateDefaultLayoutParams.isOverflowButton = true;
        return layoutParamsGenerateDefaultLayoutParams;
    }

    public abstract int getCollapsedHeight();

    public ActionMenuPresenter getPresenter() {
        return this.mPresenter;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView
    public int getWindowAnimations() {
        return 0;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView
    public boolean hasBackgroundView() {
        return false;
    }

    public boolean hasDividerBeforeChildAt(int i2) {
        KeyEvent.Callback childAt = getChildAt(i2 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i2);
        boolean zNeedsDividerAfter = (i2 >= getChildCount() || !(childAt instanceof ActionMenuChildView)) ? false : ((ActionMenuChildView) childAt).needsDividerAfter();
        return (i2 <= 0 || !(childAt2 instanceof ActionMenuChildView)) ? zNeedsDividerAfter : zNeedsDividerAfter | ((ActionMenuChildView) childAt2).needsDividerBefore();
    }

    public boolean hasOnlyCustomView() {
        return false;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView
    public void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuBuilder.ItemInvoker
    public boolean invokeItem(MenuItemImpl menuItemImpl, int i2) {
        return this.mMenu.performItemAction(menuItemImpl, i2);
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.updateMenuView(false);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mPresenter.dismissPopupMenus(false);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else {
            super.onMeasure(i2, i3);
        }
    }

    public void onPageScrolled(int i2, float f2, boolean z2, boolean z3) {
        if (DeviceHelper.isFeatureWholeAnim()) {
            setAlpha(computeAlpha(f2, z2, z3));
        }
        float fComputeTranslationY = computeTranslationY(f2, z2, z3);
        if (!z2 || !z3 || getTranslationY() != 0.0f) {
            setTranslationY(fComputeTranslationY);
        }
        this.mOpenCloseAnimator.setTranslationY(fComputeTranslationY);
    }

    public void onWillRemoved() {
    }

    public void playCloseAnimator() {
        this.mOpenCloseAnimator.close();
    }

    public void playOpenAnimator() {
        this.mOpenCloseAnimator.open();
    }

    public void resetBackground() {
    }

    public void setOverflowReserved(boolean z2) {
        this.mReserveOverflow = z2;
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.mPresenter = actionMenuPresenter;
    }

    public void updateBackground(boolean z2) {
        this.mBackgroundViewApplyBlur = z2;
        if (z2) {
            clearBackground();
        } else {
            resetBackground();
        }
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBackgroundViewApplyBlur = false;
        setBaselineAligned(false);
        this.mOpenCloseAnimator = new OpenCloseAnimator();
        setLayoutAnimation(null);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        return generateDefaultLayoutParams();
    }
}
