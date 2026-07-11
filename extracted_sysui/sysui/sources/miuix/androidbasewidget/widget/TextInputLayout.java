package miuix.androidbasewidget.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;
import miuix.androidbasewidget.R;

/* JADX INFO: loaded from: classes4.dex */
public class TextInputLayout extends LinearLayout {
    public final int ACCESSIBILITY_MODE_ASSERTIVE;
    public final int ACCESSIBILITY_MODE_NONE;
    public final int ACCESSIBILITY_MODE_POLITE;
    private final int PARENT_LAYOUT_PADDING_BOTTOM;
    private final int PARENT_LAYOUT_PADDING_LEFT;
    private final int PARENT_LAYOUT_PADDING_RIGHT;
    private final int PARENT_LAYOUT_PADDING_TOP;
    private android.widget.EditText mEditText;
    private CharSequence mError;
    private final IndicatorViewController mIndicatorViewController;

    public TextInputLayout(Context context) {
        this(context, null, R.attr.miuixTextInputStyle);
    }

    private int getErrorTextAppearance() {
        return this.mIndicatorViewController.getErrorTextAppearance();
    }

    private void setTextInputLayoutPadding(int i2, int i3, int i4, int i5) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        setPadding((int) TypedValue.applyDimension(1, i2, displayMetrics), (int) TypedValue.applyDimension(1, i4, displayMetrics), (int) TypedValue.applyDimension(1, i3, displayMetrics), (int) TypedValue.applyDimension(1, i5, displayMetrics));
    }

    @Nullable
    public android.widget.EditText getEditText() {
        this.mEditText = null;
        int i2 = 0;
        while (true) {
            if (i2 >= getChildCount()) {
                break;
            }
            View childAt = getChildAt(i2);
            if (childAt instanceof android.widget.EditText) {
                this.mEditText = (android.widget.EditText) childAt;
                break;
            }
            i2++;
        }
        return this.mEditText;
    }

    public CharSequence getError() {
        return this.mError;
    }

    public int getErrorAccessibilityLiveRegion() {
        return this.mIndicatorViewController.getErrorAccessibilityLiveRegion();
    }

    public CharSequence getErrorContentDescription() {
        return this.mIndicatorViewController.getErrorContentDescription();
    }

    @ColorInt
    public int getErrorCurrentTextColors() {
        return this.mIndicatorViewController.getErrorViewCurrentTextColor();
    }

    public TextView getErrorView() {
        return this.mIndicatorViewController.getErrorView();
    }

    public void hideError() {
        setErrorEnabled(false);
    }

    public boolean isErrorEnabled() {
        return this.mIndicatorViewController.isErrorEnabled();
    }

    public void setError(@Nullable CharSequence charSequence) {
        if (!this.mIndicatorViewController.isErrorEnabled()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.mError = null;
            this.mIndicatorViewController.hideError();
            return;
        }
        this.mError = charSequence;
        this.mIndicatorViewController.showError(charSequence);
        android.widget.EditText editText = this.mEditText;
        if (editText != null) {
            Editable text = editText.getText();
            if (text != null) {
                charSequence = ((Object) charSequence) + text.toString();
            }
            setContentDescription(charSequence);
        }
    }

    public void setErrorAccessibilityLiveRegion(int i2) {
        this.mIndicatorViewController.setErrorAccessibilityLiveRegion(i2);
    }

    public void setErrorContentDescription(@Nullable CharSequence charSequence) {
        this.mIndicatorViewController.setErrorContentDescription(charSequence);
    }

    public void setErrorEnabled(boolean z2) {
        this.mIndicatorViewController.setErrorEnabled(z2);
    }

    public void setErrorTextAppearance(@StyleRes int i2) {
        this.mIndicatorViewController.setErrorTextAppearance(i2);
    }

    public void setErrorTextColor(@Nullable ColorStateList colorStateList) {
        this.mIndicatorViewController.setErrorViewTextColor(colorStateList);
    }

    public void setTextAppearanceCompatWithErrorFallback(@NonNull TextView textView, @StyleRes int i2) {
        try {
            TextViewCompat.setTextAppearance(textView, i2);
            if (textView.getTextColors().getDefaultColor() != -65281) {
                return;
            }
        } catch (Exception unused) {
        }
        TextViewCompat.setTextAppearance(textView, R.style.TextAppearance_AppCompat_Caption);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.design_error));
    }

    public void showError() {
        setError(getError());
    }

    public TextInputLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.miuixTextInputStyle);
    }

    public TextInputLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.PARENT_LAYOUT_PADDING_TOP = 8;
        this.PARENT_LAYOUT_PADDING_BOTTOM = 16;
        this.PARENT_LAYOUT_PADDING_LEFT = 12;
        this.PARENT_LAYOUT_PADDING_RIGHT = 12;
        this.ACCESSIBILITY_MODE_NONE = 0;
        this.ACCESSIBILITY_MODE_POLITE = 1;
        this.ACCESSIBILITY_MODE_ASSERTIVE = 2;
        this.mIndicatorViewController = new IndicatorViewController(this);
        setOrientation(1);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TextInputLayout, i2, R.style.Widget_TextInputLayout_DayNight);
        int i3 = R.styleable.TextInputLayout_miuixError;
        this.mError = typedArrayObtainStyledAttributes.hasValue(i3) ? typedArrayObtainStyledAttributes.getText(i3) : null;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_miuixErrorTextAppearance, getErrorTextAppearance());
        int i4 = R.styleable.TextInputLayout_miuixErrorContentDescription;
        CharSequence text = typedArrayObtainStyledAttributes.hasValue(i4) ? typedArrayObtainStyledAttributes.getText(i4) : this.mError;
        int i5 = typedArrayObtainStyledAttributes.getInt(R.styleable.TextInputLayout_miuixErrorAccessibilityLiveRegion, 0);
        int i6 = R.styleable.TextInputLayout_miuixErrorTextColor;
        if (typedArrayObtainStyledAttributes.hasValue(i6)) {
            setErrorTextColor(typedArrayObtainStyledAttributes.getColorStateList(i6));
        }
        typedArrayObtainStyledAttributes.recycle();
        setTextInputLayoutPadding(12, 12, 8, 16);
        setErrorTextAppearance(resourceId);
        setErrorContentDescription(text);
        setErrorAccessibilityLiveRegion(i5);
        setErrorEnabled(false);
    }
}
