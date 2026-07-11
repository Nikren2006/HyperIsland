package miuix.androidbasewidget.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import miuix.androidbasewidget.R;

/* JADX INFO: loaded from: classes4.dex */
public final class IndicatorViewController {
    private static final int CAPTION_STATE_ERROR = 1;
    private static final int CAPTION_STATE_NONE = 0;
    static final int ERROR_INDEX = 0;
    static final int ERROR_SIZE = 14;
    private FrameLayout mCaptionArea;
    private int mCaptionDisplayed;
    private int mCaptionToShow;
    private final Context mContext;

    @Nullable
    private CharSequence mError;
    private boolean mErrorEnabled;

    @Nullable
    private TextView mErrorView;
    private int mErrorViewAccessibilityLiveRegion;

    @Nullable
    private CharSequence mErrorViewContentDescription;
    private LinearLayout mIndicatorArea;
    private int mIndicatorsAdded;

    @NonNull
    private final TextInputLayout mTextInputView;
    private final int INDICATOR_AREA_MARGIN_LEFT = 8;

    @Nullable
    private ColorStateList mErrorViewTextColor = initErrorColor(R.attr.miuixTextInputLayoutErrorColor);
    private int mErrorTextAppearance = initErrorAppearance(R.attr.miuixTextInputLayoutErrorStyle);

    @Retention(RetentionPolicy.SOURCE)
    public @interface IndicatorIndex {
    }

    public IndicatorViewController(@NonNull TextInputLayout textInputLayout) {
        this.mContext = textInputLayout.getContext();
        this.mTextInputView = textInputLayout;
    }

    @Nullable
    private TextView getCaptionViewFromDisplayState(int i2) {
        if (i2 != 1) {
            return null;
        }
        return this.mErrorView;
    }

    private int initErrorAppearance(int i2) {
        TypedValue typedValue = new TypedValue();
        return this.mContext.getTheme().resolveAttribute(i2, typedValue, true) ? typedValue.resourceId : R.style.Widget_TextInputLayout_Error_DayNight;
    }

    private ColorStateList initErrorColor(int i2) {
        TypedValue typedValue = new TypedValue();
        return ContextCompat.getColorStateList(this.mContext, this.mContext.getTheme().resolveAttribute(i2, typedValue, true) ? typedValue.resourceId : R.color.miuix_color_red_light_level1);
    }

    private void setCaptionViewVisibilities(int i2, int i3) {
        TextView captionViewFromDisplayState;
        TextView captionViewFromDisplayState2;
        if (i2 == i3) {
            return;
        }
        if (i3 != 0 && (captionViewFromDisplayState2 = getCaptionViewFromDisplayState(i3)) != null) {
            captionViewFromDisplayState2.setVisibility(0);
            captionViewFromDisplayState2.setAlpha(1.0f);
        }
        if (i2 != 0 && (captionViewFromDisplayState = getCaptionViewFromDisplayState(i2)) != null) {
            captionViewFromDisplayState.setVisibility(4);
            if (i2 == 1) {
                captionViewFromDisplayState.setText((CharSequence) null);
            }
        }
        this.mCaptionDisplayed = i3;
    }

    private void setIndicatorAreaMarginLeft(int i2) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mIndicatorArea.getLayoutParams();
        layoutParams.setMargins((int) TypedValue.applyDimension(1, i2, this.mContext.getResources().getDisplayMetrics()), 0, 0, 0);
        this.mIndicatorArea.setLayoutParams(layoutParams);
    }

    private void setViewGroupGoneIfEmpty(@NonNull ViewGroup viewGroup, int i2) {
        if (i2 == 0) {
            viewGroup.setVisibility(8);
        }
    }

    private void updateCaptionViewsVisibility(int i2, int i3) {
        if (i2 == i3) {
            return;
        }
        setCaptionViewVisibilities(i2, i3);
    }

    public void addIndicator(TextView textView, int i2) {
        if (this.mIndicatorArea == null && this.mCaptionArea == null) {
            LinearLayout linearLayout = new LinearLayout(this.mContext);
            this.mIndicatorArea = linearLayout;
            linearLayout.setOrientation(0);
            this.mTextInputView.addView(this.mIndicatorArea, -2, -2);
            setIndicatorAreaMarginLeft(8);
            this.mCaptionArea = new FrameLayout(this.mContext);
            this.mIndicatorArea.addView(this.mCaptionArea, new LinearLayout.LayoutParams(0, -2, 1.0f));
        }
        if (isCaptionView(i2)) {
            this.mCaptionArea.setVisibility(0);
            this.mCaptionArea.addView(textView);
        } else {
            this.mIndicatorArea.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.mIndicatorArea.setVisibility(0);
        this.mIndicatorsAdded++;
    }

    @Nullable
    public CharSequence getError() {
        return this.mError;
    }

    public int getErrorAccessibilityLiveRegion() {
        return this.mErrorViewAccessibilityLiveRegion;
    }

    @Nullable
    public CharSequence getErrorContentDescription() {
        return this.mErrorViewContentDescription;
    }

    public int getErrorTextAppearance() {
        return this.mErrorTextAppearance;
    }

    public TextView getErrorView() {
        return this.mErrorView;
    }

    @ColorInt
    public int getErrorViewCurrentTextColor() {
        TextView textView = this.mErrorView;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public void hideError() {
        int i2 = this.mCaptionDisplayed;
        if (i2 == 1) {
            this.mCaptionToShow = 0;
        }
        updateCaptionViewsVisibility(i2, this.mCaptionToShow);
    }

    public boolean isCaptionView(int i2) {
        return i2 == 0;
    }

    public boolean isErrorEnabled() {
        return this.mErrorEnabled;
    }

    public void removeIndicator(TextView textView, int i2) {
        FrameLayout frameLayout;
        if (this.mIndicatorArea == null) {
            return;
        }
        if (!isCaptionView(i2) || (frameLayout = this.mCaptionArea) == null) {
            this.mIndicatorArea.removeView(textView);
        } else {
            frameLayout.removeView(textView);
        }
        int i3 = this.mIndicatorsAdded - 1;
        this.mIndicatorsAdded = i3;
        setViewGroupGoneIfEmpty(this.mIndicatorArea, i3);
    }

    public void setErrorAccessibilityLiveRegion(int i2) {
        this.mErrorViewAccessibilityLiveRegion = i2;
        TextView textView = this.mErrorView;
        if (textView != null) {
            textView.setAccessibilityLiveRegion(i2);
        }
    }

    public void setErrorContentDescription(@Nullable CharSequence charSequence) {
        this.mErrorViewContentDescription = charSequence;
        TextView textView = this.mErrorView;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    @SuppressLint({"WrongConstant"})
    public void setErrorEnabled(boolean z2) {
        if (this.mErrorEnabled == z2) {
            return;
        }
        if (z2) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.mContext);
            this.mErrorView = appCompatTextView;
            appCompatTextView.setId(R.id.miuix_textinput_error);
            this.mErrorView.setTextAlignment(5);
            setErrorTextAppearance(this.mErrorTextAppearance);
            setErrorViewTextColor(this.mErrorViewTextColor);
            setErrorContentDescription(this.mErrorViewContentDescription);
            setErrorAccessibilityLiveRegion(this.mErrorViewAccessibilityLiveRegion);
            this.mErrorView.setVisibility(4);
            addIndicator(this.mErrorView, 0);
        } else {
            hideError();
            removeIndicator(this.mErrorView, 0);
            this.mErrorView = null;
        }
        this.mErrorEnabled = z2;
    }

    public void setErrorTextAppearance(@StyleRes int i2) {
        this.mErrorTextAppearance = i2;
        TextView textView = this.mErrorView;
        if (textView != null) {
            this.mTextInputView.setTextAppearanceCompatWithErrorFallback(textView, i2);
        }
    }

    public void setErrorViewTextColor(@Nullable ColorStateList colorStateList) {
        this.mErrorViewTextColor = colorStateList;
        TextView textView = this.mErrorView;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public void showError(CharSequence charSequence) {
        this.mError = charSequence;
        this.mErrorView.setText(charSequence);
        int i2 = this.mCaptionDisplayed;
        if (i2 != 1) {
            this.mCaptionToShow = 1;
        }
        updateCaptionViewsVisibility(i2, this.mCaptionToShow);
        this.mErrorView.announceForAccessibility(charSequence);
    }
}
