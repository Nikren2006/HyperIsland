package miuix.miuixbasewidget.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import java.util.ArrayList;
import miuix.miuixbasewidget.R;
import miuix.miuixbasewidget.widget.internal.TabViewContainerView;
import miuix.os.DeviceHelper;
import miuix.util.HapticFeedbackCompat;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes.dex */
public class FilterSortView2 extends HorizontalScrollView {
    private final int mDeviceType;
    private boolean mEnabled;
    private int mFilteredId;
    protected boolean mIsParentApplyBlur;

    @FilterSortView2LayoutConfig
    private int mLayoutConfig;
    private int mTabCount;
    private final ArrayList<Integer> mTabViewChildIds;
    private TabViewContainerView mTabViewContainerView;

    public static class TabView extends FrameLayout {
        private int mActivatedTextAppearanceId;
        private ImageView mArrow;
        private Drawable mArrowIcon;
        private boolean mDescending;
        private boolean mDescendingEnabled;
        private boolean mFiltered;
        private HapticFeedbackCompat mHapticFeedbackCompat;
        private int mIndicatorVisibility;
        private OnFilteredListener mOnFilteredListener;
        private int mTextAppearanceId;
        private TextView mTextView;

        public interface OnFilteredListener {
            void onFilteredChanged(TabView tabView, boolean z2);
        }

        public TabView(Context context) {
            this(context, null);
        }

        private HapticFeedbackCompat getHapticFeedbackCompat() {
            if (this.mHapticFeedbackCompat == null) {
                this.mHapticFeedbackCompat = new HapticFeedbackCompat(getContext());
            }
            return this.mHapticFeedbackCompat;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$setFiltered$0() {
            OnFilteredListener onFilteredListener = this.mOnFilteredListener;
            if (onFilteredListener != null) {
                onFilteredListener.onFilteredChanged(this, true);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$setOnClickListener$1(View.OnClickListener onClickListener, View view) {
            if (!this.mFiltered) {
                setFiltered(true);
            } else if (this.mDescendingEnabled) {
                setDescending(!this.mDescending);
            }
            onClickListener.onClick(view);
            if (HapticCompat.doesSupportHaptic(HapticCompat.HapticVersion.HAPTIC_VERSION_2)) {
                getHapticFeedbackCompat().lambda$performExtHapticFeedbackAsync$0(204);
            } else {
                HapticCompat.performHapticFeedback(view, HapticFeedbackConstants.MIUI_MESH_NORMAL);
            }
        }

        private void setDescending(boolean z2) {
            this.mDescending = z2;
            if (z2) {
                this.mArrow.setRotationX(0.0f);
            } else {
                this.mArrow.setRotationX(180.0f);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFiltered(boolean z2) {
            TabView tabView;
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (z2 && viewGroup != null) {
                int childCount = viewGroup.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = viewGroup.getChildAt(i2);
                    if ((childAt instanceof TabView) && (tabView = (TabView) childAt) != this && tabView.mFiltered) {
                        tabView.setFiltered(false);
                    }
                }
            }
            this.mFiltered = z2;
            updateTextAppearance();
            this.mTextView.setActivated(z2);
            this.mArrow.setActivated(z2);
            setActivated(z2);
            if (viewGroup != null && z2) {
                viewGroup.post(new Runnable() { // from class: miuix.miuixbasewidget.widget.f
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f6134a.lambda$setFiltered$0();
                    }
                });
            }
        }

        private void updateTextAppearance() {
            if (this.mTextView != null) {
                if (isFiltered()) {
                    TextViewCompat.setTextAppearance(this.mTextView, this.mActivatedTextAppearanceId);
                } else {
                    TextViewCompat.setTextAppearance(this.mTextView, this.mTextAppearanceId);
                }
                requestLayout();
            }
        }

        public View getArrowView() {
            return this.mArrow;
        }

        public boolean getDescendingEnabled() {
            return this.mDescendingEnabled;
        }

        public ImageView getIconView() {
            return this.mArrow;
        }

        public int getTabLayoutResource() {
            return R.layout.miuix_appcompat_filter_sort_tab_view_2;
        }

        public TextView getTextView() {
            return this.mTextView;
        }

        public void initView(CharSequence charSequence, boolean z2) {
            this.mArrow.setBackground(this.mArrowIcon);
            this.mTextView.setText(charSequence);
            this.mArrow.setVisibility(this.mIndicatorVisibility);
            setDescending(z2);
            updateTextAppearance();
        }

        public boolean isDescending() {
            return this.mDescending;
        }

        public boolean isFiltered() {
            return this.mFiltered;
        }

        public void setActivatedTextAppearance(int i2) {
            this.mActivatedTextAppearanceId = i2;
            updateTextAppearance();
        }

        public void setDescendingEnabled(boolean z2) {
            this.mDescendingEnabled = z2;
        }

        @Override // android.view.View
        public void setEnabled(boolean z2) {
            super.setEnabled(z2);
            this.mTextView.setEnabled(z2);
        }

        public void setIconView(ImageView imageView) {
            this.mArrow = imageView;
        }

        public void setIndicatorVisibility(int i2) {
            this.mArrow.setVisibility(i2);
        }

        @Override // android.view.View
        public void setOnClickListener(final View.OnClickListener onClickListener) {
            super.setOnClickListener(new View.OnClickListener() { // from class: miuix.miuixbasewidget.widget.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f6132a.lambda$setOnClickListener$1(onClickListener, view);
                }
            });
        }

        public void setOnFilteredListener(OnFilteredListener onFilteredListener) {
            this.mOnFilteredListener = onFilteredListener;
        }

        public void setTextAppearance(int i2) {
            this.mTextAppearanceId = i2;
            updateTextAppearance();
        }

        public void setTextView(TextView textView) {
            this.mTextView = textView;
        }

        public TabView(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R.attr.filterSortTabView2Style);
        }

        public TabView(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            this.mDescendingEnabled = true;
            LayoutInflater.from(context).inflate(getTabLayoutResource(), (ViewGroup) this, true);
            TextView textView = (TextView) findViewById(android.R.id.text1);
            this.mTextView = textView;
            textView.setMaxLines(1);
            this.mTextView.setEllipsize(TextUtils.TruncateAt.END);
            this.mArrow = (ImageView) findViewById(R.id.arrow);
            if (attributeSet != null) {
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FilterSortTabView2, i2, R.style.Widget_FilterSortTabView2_DayNight);
                String string = typedArrayObtainStyledAttributes.getString(R.styleable.FilterSortTabView2_android_text);
                boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FilterSortTabView2_descending, true);
                this.mIndicatorVisibility = typedArrayObtainStyledAttributes.getInt(R.styleable.FilterSortTabView2_indicatorVisibility, 0);
                this.mArrowIcon = typedArrayObtainStyledAttributes.getDrawable(R.styleable.FilterSortTabView2_arrowFilterSortTabView);
                setBackground(typedArrayObtainStyledAttributes.getDrawable(R.styleable.FilterSortTabView2_filterSortTabViewBackground));
                setForeground(typedArrayObtainStyledAttributes.getDrawable(R.styleable.FilterSortTabView2_filterSortTabViewForeground));
                int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FilterSortTabView2_filterSortTabViewHorizontalPadding, R.dimen.miuix_appcompat_filter_sort_tab_view2_padding_horizontal);
                int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FilterSortTabView2_filterSortTabViewVerticalPadding, R.dimen.miuix_appcompat_filter_sort_tab_view2_padding_vertical);
                findViewById(R.id.container).setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
                this.mTextAppearanceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.FilterSortTabView2_filterSortTabViewTabTextAppearance, 0);
                this.mActivatedTextAppearanceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.FilterSortTabView2_filterSortTabViewTabActivatedTextAppearance, 0);
                typedArrayObtainStyledAttributes.recycle();
                initView(string, z2);
            }
            if (getId() == -1) {
                setId(FrameLayout.generateViewId());
            }
            setImportantForAccessibility(1);
            ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: miuix.miuixbasewidget.widget.FilterSortView2.TabView.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(@NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                    accessibilityNodeInfoCompat.setSelected(view.isActivated());
                    TextView textView2 = (TextView) view.findViewById(android.R.id.text1);
                    if (textView2 != null && !TextUtils.isEmpty(textView2.getText())) {
                        accessibilityNodeInfoCompat.setContentDescription(textView2.getText());
                    }
                    if (view.isActivated()) {
                        accessibilityNodeInfoCompat.setClickable(false);
                        accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                    } else {
                        accessibilityNodeInfoCompat.setClickable(true);
                        accessibilityNodeInfoCompat.setStateDescription(TabView.this.getContext().getResources().getString(R.string.accessibility_tab_state_description_unselect));
                    }
                }
            });
        }
    }

    public FilterSortView2(Context context) {
        this(context, null);
    }

    private void checkView(View view) {
        if (!(view instanceof TabView)) {
            throw new IllegalArgumentException("Illegal View! Only support TabView!");
        }
    }

    private TabView inflateTabView() {
        return (TabView) LayoutInflater.from(getContext()).inflate(R.layout.layout_filter_tab_view2, (ViewGroup) null);
    }

    private void initContentView() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        TabViewContainerView tabViewContainerView = new TabViewContainerView(getContext());
        this.mTabViewContainerView = tabViewContainerView;
        tabViewContainerView.setLayoutParams(layoutParams);
        this.mTabViewContainerView.setHorizontalScrollBarEnabled(false);
        addView(this.mTabViewContainerView);
    }

    private void refreshTabState() {
        for (int i2 = 0; i2 < this.mTabViewContainerView.getChildCount(); i2++) {
            View childAt = this.mTabViewContainerView.getChildAt(i2);
            if (childAt instanceof TabView) {
                ((TabView) childAt).setEnabled(this.mEnabled);
            }
        }
    }

    public TabView addTab(CharSequence charSequence) {
        return addTab(charSequence, true);
    }

    public void addTabViewAt(TabView tabView, int i2) {
        if (tabView != null) {
            if (i2 > this.mTabCount || i2 < 0) {
                this.mTabViewContainerView.addView(tabView, -1, new FrameLayout.LayoutParams(-2, -2));
            } else {
                this.mTabViewContainerView.addView(tabView, i2, new FrameLayout.LayoutParams(-2, -2));
            }
            this.mTabCount++;
        }
    }

    public void addTabViewChildId(int i2) {
        this.mTabViewChildIds.add(Integer.valueOf(i2));
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        addView(view, -1);
    }

    public boolean canScrollHorizontally() {
        View childAt = getChildAt(0);
        return childAt != null && childAt.getWidth() > getWidth();
    }

    public void clearTabViewChildIds() {
        this.mTabViewChildIds.clear();
    }

    public boolean getEnabled() {
        return this.mEnabled;
    }

    public int getTabCount() {
        return this.mTabCount;
    }

    public TabView getTabViewAt(int i2) {
        if (i2 <= -1) {
            return null;
        }
        View childAt = this.mTabViewContainerView.getChildAt((this.mTabViewContainerView.getChildCount() - this.mTabCount) + i2);
        if (childAt instanceof TabView) {
            return (TabView) childAt;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005a  */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
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
            r3 = 640(0x280, float:8.97E-43)
            r4 = 2
            r5 = 1065353216(0x3f800000, float:1.0)
            r6 = 1
            r7 = 0
            if (r2 != 0) goto L3a
            float r0 = (float) r0
            float r0 = r0 * r5
            float r0 = r0 / r1
            int r0 = (int) r0
            android.content.Context r2 = r8.getContext()
            android.graphics.Point r2 = miuix.core.util.EnvStateManager.getWindowSize(r2)
            int r2 = r2.x
            float r2 = (float) r2
            float r2 = r2 * r5
            float r2 = r2 / r1
            int r1 = (int) r2
            int r2 = r8.mDeviceType
            if (r2 != r4) goto L5a
            r2 = 410(0x19a, float:5.75E-43)
            if (r0 <= r2) goto L5a
            if (r1 <= r3) goto L5a
            goto L54
        L3a:
            if (r2 != r6) goto L51
            android.content.Context r0 = r8.getContext()
            android.graphics.Point r0 = miuix.core.util.EnvStateManager.getWindowSize(r0)
            int r0 = r0.x
            float r0 = (float) r0
            float r0 = r0 * r5
            float r0 = r0 / r1
            int r0 = (int) r0
            int r1 = r8.mDeviceType
            if (r1 != r4) goto L5a
            if (r0 <= r3) goto L5a
            goto L54
        L51:
            r0 = 3
            if (r2 != r0) goto L56
        L54:
            r4 = r6
            goto L5b
        L56:
            r0 = 4
            if (r2 != r0) goto L5a
            goto L5b
        L5a:
            r4 = r7
        L5b:
            miuix.miuixbasewidget.widget.internal.TabViewContainerView r0 = r8.mTabViewContainerView
            r0.setTabViewLayoutMode(r4)
            super.onMeasure(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.miuixbasewidget.widget.FilterSortView2.onMeasure(int, int):void");
    }

    public void removeAllTabViews() {
        this.mTabViewContainerView.removeAllViews();
        clearTabViewChildIds();
        this.mTabCount = 0;
    }

    public void removeTabViewAt(int i2) {
        if (i2 <= -1) {
            return;
        }
        View childAt = this.mTabViewContainerView.getChildAt(i2);
        if (childAt instanceof TabView) {
            this.mTabViewContainerView.removeView(childAt);
            this.mTabCount--;
            removeTabViewChildId(childAt.getId());
        }
    }

    public void removeTabViewChildId(int i2) {
        this.mTabViewChildIds.remove(Integer.valueOf(i2));
    }

    @Override // android.view.View
    public void setEnabled(boolean z2) {
        super.setEnabled(z2);
        if (this.mEnabled != z2) {
            this.mEnabled = z2;
            refreshTabState();
        }
    }

    public void setFilteredTab(TabView tabView) {
        if (this.mFilteredId != tabView.getId()) {
            this.mFilteredId = tabView.getId();
        }
        tabView.setFiltered(true);
        updateChildIdsFromXml();
    }

    public void setLayoutConfig(@FilterSortView2LayoutConfig int i2) {
        if (this.mLayoutConfig != i2) {
            this.mLayoutConfig = i2;
            requestLayout();
        }
    }

    public void setParentApplyBlur(boolean z2) {
        if (this.mIsParentApplyBlur != z2) {
            this.mIsParentApplyBlur = z2;
        }
        TabViewContainerView tabViewContainerView = this.mTabViewContainerView;
        if (tabViewContainerView != null) {
            int childCount = tabViewContainerView.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = tabViewContainerView.getChildAt(i2);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).setSelected(z2);
                }
            }
        }
    }

    public void setTabIndicatorVisibility(int i2) {
        for (int i3 = 0; i3 < this.mTabViewContainerView.getChildCount(); i3++) {
            View childAt = this.mTabViewContainerView.getChildAt(i3);
            if (childAt instanceof TabView) {
                ((TabView) childAt).setIndicatorVisibility(i2);
            }
        }
    }

    public void updateChildIdsFromXml() {
        if (this.mTabViewChildIds.isEmpty()) {
            int childCount = this.mTabViewContainerView.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.mTabViewContainerView.getChildAt(i2);
                if (childAt instanceof TabView) {
                    this.mTabViewChildIds.add(Integer.valueOf(((TabView) childAt).getId()));
                }
            }
            requestLayout();
        }
    }

    public FilterSortView2(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.filterSortView2Style);
    }

    public TabView addTab(CharSequence charSequence, boolean z2) {
        TabView tabViewInflateTabView = inflateTabView();
        addTab(tabViewInflateTabView);
        tabViewInflateTabView.initView(charSequence, z2);
        return tabViewInflateTabView;
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2) {
        if (view == null) {
            throw new IllegalArgumentException("Cannot add a null child view to a ViewGroup");
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
        }
        addView(view, i2, layoutParams);
    }

    public FilterSortView2(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTabViewChildIds = new ArrayList<>();
        this.mFilteredId = -1;
        this.mIsParentApplyBlur = false;
        this.mTabCount = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FilterSortView2, i2, R.style.Widget_FilterSortView2_DayNight);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.FilterSortView2_filterSortViewBackground);
        this.mEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FilterSortView2_android_enabled, true);
        this.mLayoutConfig = typedArrayObtainStyledAttributes.getInt(R.styleable.FilterSortView2_layoutConfig, 0);
        typedArrayObtainStyledAttributes.recycle();
        initContentView();
        setBackground(drawable);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mDeviceType = DeviceHelper.detectType(context);
        setOverScrollMode(2);
    }

    private void addTab(TabView tabView) {
        addTab(tabView, -1);
    }

    public void setFilteredTab(int i2) {
        TabView tabViewAt = getTabViewAt(i2);
        if (tabViewAt != null) {
            if (this.mFilteredId != tabViewAt.getId()) {
                this.mFilteredId = tabViewAt.getId();
            }
            tabViewAt.setFiltered(true);
        }
        updateChildIdsFromXml();
    }

    private void addTab(TabView tabView, int i2) {
        tabView.setEnabled(this.mEnabled);
        tabView.setSelected(this.mIsParentApplyBlur);
        addTabViewAt(tabView, i2);
        this.mTabViewChildIds.add(Integer.valueOf(tabView.getId()));
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addView(view, -1, (ViewGroup.LayoutParams) null);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (this.mTabViewContainerView == view) {
            super.addView(view, i2, layoutParams);
        } else {
            checkView(view);
            addTab((TabView) view, i2);
        }
    }
}
