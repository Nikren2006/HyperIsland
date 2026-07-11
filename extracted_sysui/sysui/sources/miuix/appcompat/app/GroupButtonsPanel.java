package miuix.appcompat.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import miuix.appcompat.R;
import miuix.internal.util.ViewUtils;
import miuix.view.DensityChangedHelper;

/* JADX INFO: loaded from: classes2.dex */
public class GroupButtonsPanel extends FrameLayout {
    public static final float BUTTON_TEXT_SIZE_NORMAL = 17.0f;
    public static final float BUTTON_TEXT_SIZE_SMALL = 14.0f;
    private int mButtonGroupDividerSize;
    private int mButtonGroupMaxWidth;
    private LinearLayout mContentView;
    private int mExtraPadding;
    private int mOriginPaddingBottom;
    private int mOriginPaddingLeft;
    private int mOriginPaddingRight;
    private Runnable mResetPanelPaddingBottomRunnable;

    public GroupButtonsPanel(Context context) {
        super(context);
        init(context);
    }

    private void applyWindowInsets() {
        ViewUtils.doOnApplyWindowInsets(this, new ViewUtils.OnApplyWindowInsetsListener() { // from class: miuix.appcompat.app.r
            @Override // miuix.internal.util.ViewUtils.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat, ViewUtils.RelativePadding relativePadding) {
                return this.f6055a.lambda$applyWindowInsets$1(view, windowInsetsCompat, relativePadding);
            }
        });
    }

    private void init(Context context) {
        this.mButtonGroupMaxWidth = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_button_max_width);
        this.mButtonGroupDividerSize = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_group_buttons_divider_size);
        this.mOriginPaddingBottom = getPaddingBottom();
        this.mOriginPaddingLeft = getPaddingLeft();
        this.mOriginPaddingRight = getPaddingRight();
        this.mResetPanelPaddingBottomRunnable = new Runnable() { // from class: miuix.appcompat.app.s
            @Override // java.lang.Runnable
            public final void run() {
                this.f6056a.lambda$init$0();
            }
        };
        applyWindowInsets();
    }

    private boolean isEllipsized(Button button, int i2) {
        return ((int) button.getPaint().measureText(button.getText().toString())) > Math.min(i2, Math.max(0, (button.getMaxWidth() - button.getPaddingStart()) - button.getPaddingEnd()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ WindowInsetsCompat lambda$applyWindowInsets$1(View view, WindowInsetsCompat windowInsetsCompat, ViewUtils.RelativePadding relativePadding) {
        post(this.mResetPanelPaddingBottomRunnable);
        return windowInsetsCompat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0() {
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(this);
        ViewUtils.resetPaddingBottom(this, this.mOriginPaddingBottom + (rootWindowInsets != null ? rootWindowInsets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom : 0));
    }

    private void resizeButtonTextSize(int i2) {
        for (int i3 = 0; i3 < this.mContentView.getChildCount(); i3++) {
            View childAt = this.mContentView.getChildAt(i3);
            if ((childAt instanceof Button) && childAt.getVisibility() == 0) {
                Button button = (Button) childAt;
                DensityChangedHelper.updateTextSizeSpUnit(button, 17.0f);
                if (isEllipsized(button, i2)) {
                    for (int i4 = 0; i4 < this.mContentView.getChildCount(); i4++) {
                        View childAt2 = this.mContentView.getChildAt(i4);
                        if (childAt2 instanceof Button) {
                            DensityChangedHelper.updateTextSizeSpUnit((Button) childAt2, 14.0f);
                        }
                    }
                    return;
                }
            }
        }
    }

    public int getContentVisibleChildCount() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.mContentView.getChildCount(); i3++) {
            if (this.mContentView.getChildAt(i3).getVisibility() != 8) {
                i2++;
            }
        }
        return i2;
    }

    public boolean isAllChildrenInvisible() {
        boolean z2 = true;
        for (int i2 = 0; i2 < this.mContentView.getChildCount(); i2++) {
            z2 = z2 && (this.mContentView.getChildAt(i2).getVisibility() != 0);
        }
        return z2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.mResetPanelPaddingBottomRunnable;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.mResetPanelPaddingBottomRunnable = null;
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mContentView = (LinearLayout) getChildAt(0);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        boolean z2 = this.mContentView.getOrientation() == 1;
        int size = (View.MeasureSpec.getSize(i2) - this.mOriginPaddingLeft) - this.mOriginPaddingRight;
        int iMin = Math.min(this.mButtonGroupMaxWidth, size);
        int i4 = this.mButtonGroupMaxWidth;
        if (i4 < size && !z2) {
            this.mExtraPadding = (size - i4) / 2;
        }
        if (z2) {
            resizeButtonTextSize(iMin);
        } else {
            int contentVisibleChildCount = getContentVisibleChildCount();
            if (contentVisibleChildCount >= 1) {
                resizeButtonTextSize((iMin - (this.mButtonGroupDividerSize * (contentVisibleChildCount - 1))) / contentVisibleChildCount);
            }
        }
        super.onMeasure(i2, i3);
        int i5 = this.mExtraPadding;
        if (i5 > 0) {
            measureChild(this.mContentView, View.MeasureSpec.makeMeasureSpec((size - (i5 * 2)) + this.mOriginPaddingLeft + this.mOriginPaddingRight, View.MeasureSpec.getMode(i2)), i3);
        }
    }

    public GroupButtonsPanel(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public GroupButtonsPanel(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context);
    }

    public GroupButtonsPanel(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        init(context);
    }
}
