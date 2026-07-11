package miuix.popupwidget.widget;

import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import miuix.core.util.WindowUtils;
import miuix.popupwidget.R;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes5.dex */
public class GuidePopupWindow extends ArrowPopupWindow {
    public static final int ARROW_BOTTOM_LEFT_MODE = 18;
    public static final int ARROW_BOTTOM_MODE = 16;
    public static final int ARROW_BOTTOM_RIGHT_MODE = 17;
    public static final int ARROW_LEFT_MODE = 32;
    public static final int ARROW_RIGHT_MODE = 64;
    public static final int ARROW_TOP_LEFT_MODE = 9;
    public static final int ARROW_TOP_MODE = 8;
    public static final int ARROW_TOP_RIGHT_MODE = 10;
    private static final int DEFAULT_SHOW_DURATION = 5000;
    private Runnable mDismissRunnable;
    private LinearLayout mGuideView;
    private int mShowDuration;
    private int mTextViewHeight;
    private int mTextViewWidth;
    private boolean mUseWrapContent;

    public GuidePopupWindow(Context context) {
        super(context);
        this.mTextViewWidth = 0;
        this.mDismissRunnable = new Runnable() { // from class: miuix.popupwidget.widget.GuidePopupWindow.2
            @Override // java.lang.Runnable
            public void run() {
                GuidePopupWindow.this.dismiss(true);
            }
        };
    }

    private void addGuideTextView(String str) {
        String[] strArrSplit;
        if (TextUtils.isEmpty(str) || (strArrSplit = str.split("\n")) == null || strArrSplit.length == 0) {
            return;
        }
        Point point = new Point();
        WindowUtils.getWindowSize(getContext(), point);
        for (String str2 : strArrSplit) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, R.attr.guidePopupTextStyle);
            appCompatTextView.setMaxWidth(getContext().getResources().getDimensionPixelSize(R.dimen.miuix_popup_guide_text_view_max_width));
            appCompatTextView.setText(str2);
            appCompatTextView.setTextDirection(5);
            int[] textViewHeightAndWidth = getTextViewHeightAndWidth(appCompatTextView, point);
            this.mTextViewHeight += textViewHeightAndWidth[0];
            this.mTextViewWidth = Math.max(this.mTextViewWidth, textViewHeightAndWidth[1]);
            this.mGuideView.addView(appCompatTextView);
        }
    }

    private int[] getTextViewHeightAndWidth(View view, Point point) {
        view.measure(View.MeasureSpec.makeMeasureSpec(point.x, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(point.y, Integer.MIN_VALUE));
        return new int[]{view.getMeasuredHeight(), view.getMeasuredWidth()};
    }

    private boolean isSideMode() {
        return getArrowMode() == 32 || getArrowMode() == 64;
    }

    private void setAccessibilityDelegate() {
        this.mGuideView.setImportantForAccessibility(1);
        this.mGuideView.setFocusableInTouchMode(true);
        ViewCompat.setAccessibilityDelegate(this.mGuideView, new AccessibilityDelegateCompat() { // from class: miuix.popupwidget.widget.GuidePopupWindow.1
            private boolean shouldDismissForAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
                int eventType = accessibilityEvent.getEventType();
                return eventType == 256 || eventType == 65536;
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean dispatchPopulateAccessibilityEvent(@NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
                boolean zDispatchPopulateAccessibilityEvent = super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
                if (shouldDismissForAccessibilityEvent(accessibilityEvent)) {
                    GuidePopupWindow.this.dismiss(true);
                }
                return zDispatchPopulateAccessibilityEvent;
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityEvent(@NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
                super.onInitializeAccessibilityEvent(view, accessibilityEvent);
                if (shouldDismissForAccessibilityEvent(accessibilityEvent)) {
                    GuidePopupWindow.this.dismiss(true);
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0142  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void showWithWrapContent(android.view.View r18) {
        /*
            Method dump skipped, instruction units count: 422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.popupwidget.widget.GuidePopupWindow.showWithWrapContent(android.view.View):void");
    }

    @Override // miuix.popupwidget.widget.ArrowPopupWindow
    public void onPrepareWindow() {
        super.onPrepareWindow();
        this.mShowDuration = 5000;
        setFocusable(true);
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.miuix_appcompat_guide_popup_content_view, (ViewGroup) null, false);
        this.mGuideView = linearLayout;
        setContentView(linearLayout);
        this.mArrowPopupView.enableShowingAnimation(false);
        setAccessibilityDelegate();
    }

    public void setGuideText(String str) {
        addGuideTextView(str);
    }

    public void setOffset(int i2, int i3) {
        this.mArrowPopupView.setOffset(i2, i3);
    }

    public void setShowDuration(int i2) {
        this.mShowDuration = i2;
    }

    public void setWrapContent(boolean z2) {
        this.mUseWrapContent = z2;
    }

    @Override // miuix.popupwidget.widget.ArrowPopupWindow
    public void show(View view, int i2, int i3) {
        if (this.mUseWrapContent) {
            showWithWrapContent(view);
        } else {
            super.show(view, i2, i3);
        }
    }

    public void setGuideText(int i2) {
        setGuideText(getContext().getString(i2));
    }

    public GuidePopupWindow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTextViewWidth = 0;
        this.mDismissRunnable = new Runnable() { // from class: miuix.popupwidget.widget.GuidePopupWindow.2
            @Override // java.lang.Runnable
            public void run() {
                GuidePopupWindow.this.dismiss(true);
            }
        };
    }

    public void show(View view, boolean z2) {
        show(view, 0, 0, z2);
    }

    public void show(View view, int i2, int i3, boolean z2) {
        setAutoDismiss(z2);
        show(view, i2, i3);
        if (z2) {
            this.mArrowPopupView.postDelayed(this.mDismissRunnable, this.mShowDuration);
        }
        if (HapticCompat.doesSupportHaptic(HapticCompat.HapticVersion.HAPTIC_VERSION_2)) {
            return;
        }
        HapticCompat.performHapticFeedback(view, HapticFeedbackConstants.MIUI_POPUP_LIGHT);
    }

    public GuidePopupWindow(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTextViewWidth = 0;
        this.mDismissRunnable = new Runnable() { // from class: miuix.popupwidget.widget.GuidePopupWindow.2
            @Override // java.lang.Runnable
            public void run() {
                GuidePopupWindow.this.dismiss(true);
            }
        };
    }

    public GuidePopupWindow(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mTextViewWidth = 0;
        this.mDismissRunnable = new Runnable() { // from class: miuix.popupwidget.widget.GuidePopupWindow.2
            @Override // java.lang.Runnable
            public void run() {
                GuidePopupWindow.this.dismiss(true);
            }
        };
    }
}
