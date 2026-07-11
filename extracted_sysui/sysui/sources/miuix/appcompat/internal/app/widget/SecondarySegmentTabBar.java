package miuix.appcompat.internal.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import miuix.appcompat.R;
import miuix.miuixbasewidget.widget.FilterSortView;
import miuix.os.DeviceHelper;

/* JADX INFO: loaded from: classes3.dex */
public class SecondarySegmentTabBar extends FilterSortView implements SecondaryTabBar {
    private static final int WIDE_LESS_THAN_TWO_ITEM_DP = 220;
    private static final int WIDE_MORE_THAN_FOUR_ITEM_DP = 150;
    private static final int WIDE_THREE_ITEM_DP = 180;
    private final int mDeviceType;

    @SecondarySegmentTabBarLayoutConfig
    private final int mLayoutConfig;
    private View.OnClickListener mOnTabClickListener;

    public static class SecondarySegmentTabView extends FilterSortView.TabView {
        private ActionBar.Tab mTab;

        public SecondarySegmentTabView(Context context) {
            this(context, null);
        }

        public void attach(@NonNull ActionBar.Tab tab) {
            this.mTab = tab;
        }

        public ActionBar.Tab getTab() {
            return this.mTab;
        }

        public SecondarySegmentTabView(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R.attr.segmentTabViewStyle);
        }

        public SecondarySegmentTabView(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
        }
    }

    public SecondarySegmentTabBar(Context context) {
        this(context, null);
    }

    private View.OnClickListener createOnTabClickListener() {
        return new View.OnClickListener() { // from class: miuix.appcompat.internal.app.widget.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SecondarySegmentTabBar.lambda$createOnTabClickListener$0(view);
            }
        };
    }

    private SecondarySegmentTabView createTabView(ActionBar.Tab tab) {
        SecondarySegmentTabView secondarySegmentTabView = new SecondarySegmentTabView(getContext());
        secondarySegmentTabView.attach(tab);
        return secondarySegmentTabView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$createOnTabClickListener$0(View view) {
        ActionBar.Tab tab;
        if (!(view instanceof SecondarySegmentTabView) || (tab = ((SecondarySegmentTabView) view).getTab()) == null) {
            return;
        }
        tab.select();
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void addTab(@NonNull ActionBar.Tab tab, boolean z2) {
        addTab(tab, -1, z2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void animateToTab(int i2) {
        setFilteredTab(i2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    @NonNull
    public ViewGroup asViewGroup() {
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0053  */
    @Override // miuix.miuixbasewidget.widget.FilterSortView, androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r9, int r10) {
        /*
            r8 = this;
            int r0 = android.view.View.MeasureSpec.getSize(r9)
            android.content.Context r1 = r8.getContext()
            android.content.res.Resources r1 = r1.getResources()
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()
            float r1 = r1.density
            int r2 = r8.mLayoutConfig
            r3 = 3
            r4 = 640(0x280, float:8.97E-43)
            r5 = 2
            r6 = 1065353216(0x3f800000, float:1.0)
            if (r2 != 0) goto L39
            float r2 = (float) r0
            float r2 = r2 * r6
            float r2 = r2 / r1
            int r2 = (int) r2
            android.content.Context r7 = r8.getContext()
            android.graphics.Point r7 = miuix.core.util.EnvStateManager.getWindowSize(r7)
            int r7 = r7.x
            float r7 = (float) r7
            float r7 = r7 * r6
            float r7 = r7 / r1
            int r6 = (int) r7
            int r7 = r8.mDeviceType
            if (r7 != r5) goto L7c
            r7 = 410(0x19a, float:5.75E-43)
            if (r2 <= r7) goto L7c
            if (r6 <= r4) goto L7c
            goto L53
        L39:
            r7 = 1
            if (r2 != r7) goto L51
            android.content.Context r2 = r8.getContext()
            android.graphics.Point r2 = miuix.core.util.EnvStateManager.getWindowSize(r2)
            int r2 = r2.x
            float r2 = (float) r2
            float r2 = r2 * r6
            float r2 = r2 / r1
            int r2 = (int) r2
            int r6 = r8.mDeviceType
            if (r6 != r5) goto L7c
            if (r2 <= r4) goto L7c
            goto L53
        L51:
            if (r2 != r3) goto L7c
        L53:
            int r2 = r8.getTabCount()
            int r4 = r8.getPaddingLeft()
            int r6 = r8.getPaddingRight()
            int r4 = r4 + r6
            int r0 = r0 - r4
            if (r2 > r5) goto L69
            int r3 = r2 * 220
        L65:
            float r3 = (float) r3
            float r3 = r3 * r1
            int r1 = (int) r3
            goto L71
        L69:
            if (r2 != r3) goto L6e
            int r3 = r2 * 180
            goto L65
        L6e:
            int r3 = r2 * 150
            goto L65
        L71:
            int r1 = r1 + r4
            if (r0 < r1) goto L7c
            if (r2 <= 0) goto L7c
            r9 = 1073741824(0x40000000, float:2.0)
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r9)
        L7c:
            super.onMeasure(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.internal.app.widget.SecondarySegmentTabBar.onMeasure(int, int):void");
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageScrolled(int i2, float f2, boolean z2, boolean z3) {
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageSelected(int i2) {
        setFilteredTab(i2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void removeAllTabs() {
        removeAllTabViews();
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void removeTabAt(int i2) {
        removeTabViewAt(i2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setBadgeVisibility(int i2, boolean z2) {
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setParentBlurEnabled(boolean z2) {
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTabBadgeDisappearOnClick(int i2, boolean z2) {
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTabIconWithPosition(int i2, int i3, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTabSelected(int i2) {
        setFilteredTab(i2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTextAppearance(int i2, int i3) {
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void updateTab(int i2) {
    }

    public SecondarySegmentTabBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.segmentTabBarStyle);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void addTab(@NonNull ActionBar.Tab tab, int i2, boolean z2) {
        SecondarySegmentTabView secondarySegmentTabViewCreateTabView = createTabView(tab);
        addTab(secondarySegmentTabViewCreateTabView, tab.getText().toString(), i2, z2);
        if (this.mOnTabClickListener == null) {
            this.mOnTabClickListener = createOnTabClickListener();
        }
        secondarySegmentTabViewCreateTabView.setOnClickListener(this.mOnTabClickListener);
        if (z2) {
            setFilteredTab(secondarySegmentTabViewCreateTabView);
        }
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTextAppearance(int i2, int i3, int i4) {
    }

    public SecondarySegmentTabBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SecondarySegmentTabBar, i2, 0);
        this.mLayoutConfig = typedArrayObtainStyledAttributes.getInt(R.styleable.SecondarySegmentTabBar_segmentTabBarLayoutConfig, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.mDeviceType = DeviceHelper.detectType(context);
    }
}
