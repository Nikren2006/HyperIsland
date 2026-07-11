package miuix.appcompat.internal.view.menu.action;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import miuix.animation.ViewHoverListener;
import miuix.appcompat.R;
import miuix.appcompat.internal.view.menu.action.ActionMenuView;
import miuix.core.util.MiuixUIUtils;

/* JADX INFO: loaded from: classes3.dex */
class OverflowMenuButton extends LinearLayout implements ActionMenuView.ActionMenuChildView, ViewHoverListener {
    private final ActionMenuItemViewChildren mChildren;
    private boolean mIsHover;
    private OnOverflowMenuButtonClickListener mOnOverflowMenuButtonClickListener;

    public interface OnOverflowMenuButtonClickListener {
        void onOverflowMenuButtonClick();
    }

    public OverflowMenuButton(Context context) {
        this(context, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [miuix.appcompat.internal.view.menu.action.OverflowMenuButton] */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    private boolean isVisible() {
        while (this != 0 && this.getVisibility() == 0) {
            ViewParent parent = this.getParent();
            this = parent instanceof ViewGroup ? (ViewGroup) parent : 0;
        }
        return this == 0;
    }

    @Override // miuix.animation.ViewHoverListener
    public boolean isHover() {
        return this.mIsHover;
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView.ActionMenuChildView
    public boolean needsDividerAfter() {
        return false;
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView.ActionMenuChildView
    public boolean needsDividerBefore() {
        return false;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mChildren.onConfigurationChanged(configuration);
    }

    @Override // miuix.animation.ViewHoverListener
    public void onEnterHover() {
        this.mIsHover = true;
    }

    @Override // miuix.animation.ViewHoverListener
    public void onExitHover() {
        this.mIsHover = false;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // miuix.animation.ViewHoverListener
    public void onMoveHover() {
    }

    @Override // android.view.View
    public boolean performClick() {
        if (super.performClick() || !isVisible()) {
            return true;
        }
        playSoundEffect(0);
        OnOverflowMenuButtonClickListener onOverflowMenuButtonClickListener = this.mOnOverflowMenuButtonClickListener;
        if (onOverflowMenuButtonClickListener != null) {
            onOverflowMenuButtonClickListener.onOverflowMenuButtonClick();
        }
        return true;
    }

    @Override // android.view.View
    public void setEnabled(boolean z2) {
        super.setEnabled(z2);
        this.mChildren.setEnabled(z2);
    }

    public void setOnOverflowMenuButtonClickListener(OnOverflowMenuButtonClickListener onOverflowMenuButtonClickListener) {
        this.mOnOverflowMenuButtonClickListener = onOverflowMenuButtonClickListener;
    }

    public OverflowMenuButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OverflowMenuButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OverflowMenuButton, i2, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.OverflowMenuButton_android_drawableTop);
        CharSequence text = typedArrayObtainStyledAttributes.getText(R.styleable.OverflowMenuButton_android_text);
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.OverflowMenuButton_android_contentDescription);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.OverflowMenuButton_largeFontAdaptationEnabled, true) && MiuixUIUtils.getFontLevel(context) == 2;
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuItemViewChildren actionMenuItemViewChildren = new ActionMenuItemViewChildren(this);
        this.mChildren = actionMenuItemViewChildren;
        actionMenuItemViewChildren.setIcon(drawable);
        actionMenuItemViewChildren.setText(text);
        actionMenuItemViewChildren.setContentDescription(string);
        actionMenuItemViewChildren.setLargeFontEnabled(z2);
        setClickable(true);
        setFocusable(true);
        setVisibility(0);
        setEnabled(true);
    }
}
