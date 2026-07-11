package miuix.appcompat.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import miuix.appcompat.R;
import miuix.core.util.EnvStateManager;
import miuix.core.util.MiuixUIUtils;
import miuix.core.util.RomUtils;
import miuix.internal.util.ViewUtils;
import miuix.internal.widget.GroupButton;
import miuix.view.DensityChangedHelper;

/* JADX INFO: loaded from: classes3.dex */
public class DialogButtonPanel extends LinearLayout {
    private boolean isContentLandscape;
    private int mButtonMarginHorizontal;
    private int mButtonMarginVertical;
    private int mButtonMinHeight;
    private float mButtonTextSize;
    private int mButtonsFullyVisibleHeight;
    private Context mContext;
    private int mCurrentDensityDpi;
    private boolean mCustomPaddingEnabled;
    private int mCustomPaddingHorizontal;
    private boolean mForceVertical;
    private int mLastDensityDpi;
    private final List<GroupButton> mNegativeStyleButtons;
    private final List<GroupButton> mNeutralStyleButtons;
    private int mPanelPaddingHorizontal;
    private boolean mPrimaryButtonFirstEnabled;
    private final List<GroupButton> mPrimaryStyleButtons;
    private boolean mVerticalPositionConfirmed;

    public DialogButtonPanel(Context context) {
        this(context, null);
    }

    private void adjustButtonScrollIfNeed() {
        if (this.isContentLandscape) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        boolean z2 = (((float) this.mButtonsFullyVisibleHeight) * 1.0f) / ((float) Math.max(EnvStateManager.getWindowSize(this.mContext).y, 1)) >= 0.4f;
        if (viewGroup == null || !z2 || (viewGroup instanceof NestedScrollViewExpander) || !(viewGroup instanceof DialogParentPanel2)) {
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.contentPanel);
        viewGroup.removeView(this);
        if (viewGroup2 != null) {
            viewGroup2.addView(this);
            ((NestedScrollViewExpander) viewGroup2).setExpandView(null);
        }
    }

    private void handleButtonLayout(int i2) {
        boolean zIsVerticalNeeded = isVerticalNeeded((i2 - getPaddingStart()) - getPaddingEnd());
        int childCount = getChildCount();
        if (!zIsVerticalNeeded) {
            handleHorizontalLayout(childCount);
            return;
        }
        resizeButtonTextSize((i2 - getPaddingStart()) - getPaddingEnd());
        if (RomUtils.getHyperOsVersion() > 2 || this.mPrimaryButtonFirstEnabled) {
            resortButtonPositionWhenVertical();
        }
        handleVerticalLayout(childCount);
    }

    private void handleHorizontalLayout(int i2) {
        setOrientation(0);
        int i3 = this.mCustomPaddingEnabled ? this.mCustomPaddingHorizontal : this.mPanelPaddingHorizontal;
        setPadding(i3, getPaddingTop(), i3, getPaddingBottom());
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            View childAt = getChildAt(i5);
            boolean z2 = childAt.getVisibility() == 0;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            childAt.setMinimumHeight(this.mButtonMinHeight);
            layoutParams.width = 0;
            layoutParams.height = -2;
            layoutParams.weight = 1.0f;
            layoutParams.topMargin = 0;
            if (z2) {
                if (zIsLayoutRtl) {
                    layoutParams.rightMargin = i4;
                } else {
                    layoutParams.leftMargin = i4;
                }
                if (MiuixUIUtils.isTallFontLang(this.mContext)) {
                    setFallbackLineSpacing(childAt, true);
                }
            } else {
                layoutParams.rightMargin = 0;
                layoutParams.leftMargin = 0;
            }
            if (z2) {
                i4 = this.mButtonMarginHorizontal;
            }
        }
        this.mButtonsFullyVisibleHeight = i2 > 0 ? this.mButtonMinHeight : 0;
        this.mVerticalPositionConfirmed = false;
    }

    private void handleVerticalLayout(int i2) {
        setOrientation(1);
        int i3 = this.mCustomPaddingEnabled ? this.mCustomPaddingHorizontal : this.mPanelPaddingHorizontal;
        setPadding(i3, getPaddingTop(), i3, getPaddingBottom());
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            View childAt = getChildAt(i6);
            boolean z2 = childAt.getVisibility() == 0;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            childAt.setMinimumHeight(this.mButtonMinHeight);
            layoutParams.width = -1;
            layoutParams.height = -2;
            layoutParams.weight = 0.0f;
            layoutParams.topMargin = z2 ? i5 : 0;
            layoutParams.rightMargin = 0;
            layoutParams.leftMargin = 0;
            if (z2) {
                i5 = this.mButtonMarginVertical;
            }
            if (z2) {
                i4++;
            }
            if (z2 && MiuixUIUtils.isTallFontLang(this.mContext)) {
                setFallbackLineSpacing(childAt, true);
            }
        }
        this.mButtonsFullyVisibleHeight = i4 > 0 ? (this.mButtonMinHeight * i4) + ((i4 - 1) * this.mButtonMarginVertical) : 0;
    }

    private boolean isEllipsized(TextView textView, int i2) {
        return ((int) textView.getPaint().measureText(textView.getText().toString())) > (i2 - textView.getPaddingStart()) - textView.getPaddingEnd();
    }

    private boolean isVerticalNeeded(int i2) {
        if (this.mForceVertical) {
            return true;
        }
        int childCount = getChildCount();
        int i3 = childCount;
        for (int i4 = childCount - 1; i4 >= 0; i4--) {
            if (getChildAt(i4).getVisibility() == 8) {
                i3--;
            }
        }
        if (i3 < 2) {
            return false;
        }
        if (i3 >= 3) {
            return true;
        }
        int i5 = (i2 - this.mButtonMarginHorizontal) / 2;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if ((childAt instanceof TextView) && childAt.getVisibility() == 0 && isEllipsized((TextView) childAt, i5)) {
                return true;
            }
        }
        return false;
    }

    private void resizeButtonTextSize(int i2) {
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if ((childAt instanceof TextView) && childAt.getVisibility() == 0) {
                TextView textView = (TextView) childAt;
                DensityChangedHelper.updateTextSizeSpUnit(textView, 17.0f);
                if (isEllipsized(textView, i2)) {
                    for (int i4 = 0; i4 < getChildCount(); i4++) {
                        View childAt2 = getChildAt(i4);
                        if (childAt2 instanceof TextView) {
                            DensityChangedHelper.updateTextSizeSpUnit((TextView) childAt2, 14.0f);
                        }
                    }
                    return;
                }
            }
        }
    }

    private void resortButtonPositionWhenVertical() {
        if (this.mVerticalPositionConfirmed) {
            return;
        }
        int i2 = 0;
        for (GroupButton groupButton : this.mPrimaryStyleButtons) {
            removeView(groupButton);
            addView(groupButton, i2);
            i2++;
        }
        for (GroupButton groupButton2 : this.mNeutralStyleButtons) {
            removeView(groupButton2);
            addView(groupButton2, i2);
            i2++;
        }
        for (GroupButton groupButton3 : this.mNegativeStyleButtons) {
            removeView(groupButton3);
            addView(groupButton3, i2);
            i2++;
        }
        this.mVerticalPositionConfirmed = true;
    }

    private void setFallbackLineSpacing(View view, boolean z2) {
        if (view instanceof TextView) {
            ((TextView) view).setFallbackLineSpacing(z2);
        }
    }

    public void addNegativeStyleButtons(GroupButton groupButton) {
        this.mNegativeStyleButtons.add(groupButton);
    }

    public void addNeutralStyleButtons(GroupButton groupButton) {
        this.mNeutralStyleButtons.add(groupButton);
    }

    public void addPrimaryStyleButtons(GroupButton groupButton) {
        this.mPrimaryStyleButtons.add(groupButton);
    }

    public void clearNegativeStyleButtonList() {
        this.mNegativeStyleButtons.clear();
    }

    public void clearNeutralStyleButtonList() {
        this.mNeutralStyleButtons.clear();
    }

    public void clearPrimaryStyleButtonList() {
        this.mPrimaryStyleButtons.clear();
    }

    public int getButtonFullyVisibleHeight() {
        return this.mButtonsFullyVisibleHeight;
    }

    public void isContentLandscape(boolean z2) {
        this.isContentLandscape = z2;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i2 = this.mCurrentDensityDpi;
        this.mLastDensityDpi = i2;
        int i3 = configuration.densityDpi;
        if (i2 != i3) {
            this.mCurrentDensityDpi = i3;
            float f2 = (i3 * 1.0f) / i2;
            this.mPanelPaddingHorizontal = (int) (this.mPanelPaddingHorizontal * f2);
            this.mButtonMarginHorizontal = (int) (this.mButtonMarginHorizontal * f2);
            this.mButtonMarginVertical = (int) (this.mButtonMarginVertical * f2);
            this.mButtonMinHeight = (int) (this.mButtonMinHeight * f2);
            int childCount = getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (childAt instanceof TextView) {
                    DensityChangedHelper.updateTextSizeSpUnit((TextView) childAt, this.mButtonTextSize);
                }
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        adjustButtonScrollIfNeed();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        handleButtonLayout(View.MeasureSpec.getSize(i2));
        super.onMeasure(i2, i3);
    }

    public void setCustomPaddingEnabled(boolean z2) {
        this.mCustomPaddingEnabled = z2;
    }

    public void setCustomPaddingHorizontal(int i2) {
        this.mCustomPaddingHorizontal = i2;
    }

    public void setForceVertical(boolean z2) {
        if (this.mForceVertical != z2) {
            this.mForceVertical = z2;
            requestLayout();
        }
    }

    public void setPrimaryButtonFirstEnabled(boolean z2) {
        this.mPrimaryButtonFirstEnabled = z2;
    }

    public DialogButtonPanel(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DialogButtonPanel(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mCustomPaddingHorizontal = 0;
        this.mCustomPaddingEnabled = false;
        this.mPrimaryButtonFirstEnabled = false;
        this.mButtonTextSize = 17.0f;
        this.mPrimaryStyleButtons = new ArrayList();
        this.mNegativeStyleButtons = new ArrayList();
        this.mNeutralStyleButtons = new ArrayList();
        this.mVerticalPositionConfirmed = false;
        this.mContext = context;
        Resources resources = getResources();
        this.mPanelPaddingHorizontal = resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_dialog_button_panel_horizontal_margin);
        this.mButtonMarginHorizontal = resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_dialog_btn_margin_horizontal);
        this.mButtonMarginVertical = resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_dialog_btn_margin_vertical);
        this.mButtonMinHeight = resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_button_height);
        int i3 = resources.getConfiguration().densityDpi;
        this.mCurrentDensityDpi = i3;
        this.mLastDensityDpi = i3;
    }
}
