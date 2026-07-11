package miuix.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Insets;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import miuix.appcompat.R;
import miuix.core.util.EnvStateManager;
import miuix.core.util.MiuixUIUtils;
import miuix.core.util.WindowUtils;
import miuix.os.Build;
import miuix.os.DeviceHelper;

/* JADX INFO: loaded from: classes3.dex */
public class AlertActionSheetPanel extends LinearLayout {
    private static final float mMaxHeightMajor = 0.7f;
    private final Context mContext;
    private int mFreePhoneCompatHeight;
    private int mFreeTabletCompatHeight;
    private int mMaxHeight;
    private int mNormalMargin;
    private final Point mScreenSize;
    private int mSeparateItemMarginTop;

    public AlertActionSheetPanel(Context context) {
        this(context, null, 0);
    }

    private int getAvailableMaxHeightInFreeForm() {
        int i2;
        int i3;
        WindowInsets rootWindowInsets = getRootWindowInsets();
        if (rootWindowInsets != null) {
            Insets insets = rootWindowInsets.getInsets(WindowInsets.Type.systemBars());
            i2 = insets.top;
            i3 = insets.bottom;
        } else {
            i2 = 0;
            i3 = 0;
        }
        if (i2 == 0) {
            i2 = (Build.IS_TABLET ? this.mFreeTabletCompatHeight : this.mFreePhoneCompatHeight) + this.mNormalMargin;
        }
        if (i3 == 0) {
            i3 = (Build.IS_TABLET ? this.mFreeTabletCompatHeight : this.mFreePhoneCompatHeight) + this.mNormalMargin;
        }
        return EnvStateManager.getWindowSize(this.mContext).y - (i2 + i3);
    }

    private void init(Context context) {
        this.mSeparateItemMarginTop = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_sheet_content_separate_item_margin_top);
        WindowUtils.getScreenSize(context, this.mScreenSize);
        this.mFreePhoneCompatHeight = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_dialog_freeform_bottom_height_phone_t);
        this.mFreeTabletCompatHeight = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_dialog_freeform_bottom_height_tablet_t);
        this.mNormalMargin = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_dialog_width_margin);
        this.mMaxHeight = (int) (this.mScreenSize.y * 0.7f);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        WindowUtils.getScreenSize(this.mContext, this.mScreenSize);
        this.mMaxHeight = (int) (this.mScreenSize.y * 0.7f);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4;
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        int measuredHeight;
        int size = View.MeasureSpec.getSize(i3);
        int measuredHeight2 = 0;
        boolean z2 = Build.IS_FLIP && DeviceHelper.isTinyScreen(this.mContext);
        Point point = this.mScreenSize;
        int i5 = point.y;
        boolean z3 = i5 > point.x;
        boolean z4 = MiuixUIUtils.px2dp(this.mContext, (float) i5) >= 500;
        if (EnvStateManager.isFreeFormMode(this.mContext)) {
            i3 = View.MeasureSpec.makeMeasureSpec(getAvailableMaxHeightInFreeForm(), Integer.MIN_VALUE);
        } else if (!z2 && ((z3 || z4) && size > (i4 = this.mMaxHeight))) {
            i3 = View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE);
        }
        super.onMeasure(i2, i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (getChildCount() >= 2) {
            viewGroup = (ViewGroup) getChildAt(0);
            viewGroup2 = (ViewGroup) getChildAt(1);
        } else {
            viewGroup = null;
            viewGroup2 = null;
        }
        int i6 = this.mSeparateItemMarginTop;
        if (viewGroup2 != null) {
            measureChild(viewGroup2, i2, i3);
            measuredHeight = viewGroup2.getMeasuredHeight();
            i6 += measuredHeight;
        } else {
            measuredHeight = 0;
        }
        if (viewGroup != null) {
            measureChild(viewGroup, i2, i3);
            measuredHeight2 = viewGroup.getMeasuredHeight();
        }
        int i7 = this.mSeparateItemMarginTop;
        if (measuredHeight2 + measuredHeight + i7 <= size2 || viewGroup == null) {
            return;
        }
        viewGroup.measure(i2, View.MeasureSpec.makeMeasureSpec((size2 - measuredHeight) - i7, BasicMeasure.EXACTLY));
        setMeasuredDimension(getMeasuredWidth(), i6 + viewGroup.getMeasuredHeight());
    }

    public AlertActionSheetPanel(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AlertActionSheetPanel(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mScreenSize = new Point();
        this.mContext = context;
        init(context);
    }
}
