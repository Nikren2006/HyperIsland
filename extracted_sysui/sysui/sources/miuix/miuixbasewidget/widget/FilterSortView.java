package miuix.miuixbasewidget.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import java.util.ArrayList;
import java.util.List;
import miuix.animation.Folme;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.property.ViewProperty;
import miuix.miuixbasewidget.R;
import miuix.util.HapticFeedbackCompat;
import miuix.view.CompatViewMethod;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes.dex */
public class FilterSortView extends ConstraintLayout {
    public static final int GONE = 8;
    private static final String TAG = "miuix:FilterSortView";
    public static final int VISIBLE = 0;
    private TabView mBackgroundTabView;
    private boolean mEnabled;
    private final TabView.FilterHoverListener mFilterHoverListener;
    private int mFilteredId;
    private boolean mFilteredUpdated;
    private View mHoverBgView;
    private final TabView.OnFilteredListener mOnFilteredListener;
    private final int mPadding;
    private int mTabCount;
    private final List<Integer> mTabViewChildIds;

    /* JADX INFO: renamed from: miuix.miuixbasewidget.widget.FilterSortView$1, reason: invalid class name */
    public class AnonymousClass1 implements TabView.OnFilteredListener {
        private Runnable mRunnable;

        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFilteredChanged$0(boolean z2, boolean z3, TabView tabView) {
            FilterSortView.this.checkBackgroundTabViewAdded();
            if (z2 && FilterSortView.this.mBackgroundTabView != null && FilterSortView.this.mBackgroundTabView.getVisibility() == 0) {
                if (z3) {
                    Folme.useAt(FilterSortView.this.mBackgroundTabView).state().setFlags(1L).to(new AnimState(TypedValues.AttributesType.S_TARGET).add(ViewProperty.f6001X, tabView.getX()).add(ViewProperty.WIDTH, tabView.getWidth()), new AnimConfig[0]);
                } else {
                    FilterSortView.this.updateFiltered(tabView);
                }
            }
            if (z2) {
                FilterSortView.this.mFilteredId = tabView.getId();
            }
        }

        @Override // miuix.miuixbasewidget.widget.FilterSortView.TabView.OnFilteredListener
        public void onFilteredChanged(final TabView tabView, final boolean z2, final boolean z3) {
            Runnable runnable = this.mRunnable;
            if (runnable != null) {
                tabView.removeCallbacks(runnable);
            }
            Runnable runnable2 = new Runnable() { // from class: miuix.miuixbasewidget.widget.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f6127a.lambda$onFilteredChanged$0(z2, z3, tabView);
                }
            };
            this.mRunnable = runnable2;
            tabView.post(runnable2);
        }
    }

    public static class TabView extends LinearLayout {
        private ImageView mArrow;
        private Drawable mArrowIcon;
        private boolean mDescending;
        private boolean mDescendingEnabled;
        private FilterHoverListener mFilterHoverListener;
        private boolean mFiltered;
        private HapticFeedbackCompat mHapticFeedbackCompat;
        private int mIndicatorVisibility;
        private OnFilteredListener mOnFilteredListener;
        private int mSelectedTextAppearanceId;
        private int mTextAppearanceId;
        private ColorStateList mTextColor;
        private TextView mTextView;

        public interface FilterHoverListener {
            void onHoverEnter();

            void onHoverExit(float f2, float f3);

            void onHoverFilterEnter();

            void onHoverFilterExit();
        }

        public interface OnFilteredListener {
            void onFilteredChanged(TabView tabView, boolean z2, boolean z3);
        }

        public TabView(Context context) {
            this(context, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public HapticFeedbackCompat getHapticFeedbackCompat() {
            if (this.mHapticFeedbackCompat == null) {
                this.mHapticFeedbackCompat = new HapticFeedbackCompat(getContext());
            }
            return this.mHapticFeedbackCompat;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean lambda$initView$0(View view, MotionEvent motionEvent) {
            if (this.mFilterHoverListener == null || motionEvent.getSource() == 4098) {
                return false;
            }
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 9) {
                if (this.mFiltered) {
                    this.mFilterHoverListener.onHoverFilterEnter();
                }
                this.mFilterHoverListener.onHoverEnter();
                return true;
            }
            if (actionMasked != 10) {
                return true;
            }
            if (this.mFiltered) {
                this.mFilterHoverListener.onHoverFilterExit();
            }
            this.mFilterHoverListener.onHoverExit(motionEvent.getX() + getLeft(), motionEvent.getY());
            return true;
        }

        private Drawable parseBackground() {
            return getResources().getDrawable(R.drawable.miuix_appcompat_filter_sort_tab_view_bg_normal);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDescending(boolean z2) {
            this.mDescending = z2;
            if (z2) {
                this.mArrow.setRotationX(0.0f);
            } else {
                this.mArrow.setRotationX(180.0f);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFiltered(boolean z2) {
            setFiltered(z2, true);
        }

        private void updateTextAppearance(boolean z2) {
            TextView textView = this.mTextView;
            if (textView != null) {
                if (z2) {
                    TextViewCompat.setTextAppearance(textView, this.mSelectedTextAppearanceId);
                } else {
                    TextViewCompat.setTextAppearance(textView, this.mTextAppearanceId);
                }
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
            return R.layout.miuix_appcompat_filter_sort_tab_view;
        }

        public TextView getTextView() {
            return this.mTextView;
        }

        public void initView(CharSequence charSequence, boolean z2) {
            updateTextAppearance(false);
            setGravity(17);
            if (getBackground() == null) {
                setBackground(parseBackground());
            }
            this.mArrow.setBackground(this.mArrowIcon);
            ColorStateList colorStateList = this.mTextColor;
            if (colorStateList != null) {
                this.mTextView.setTextColor(colorStateList);
            }
            this.mTextView.setText(charSequence);
            setDescending(z2);
            setOnHoverListener(new View.OnHoverListener() { // from class: miuix.miuixbasewidget.widget.d
                @Override // android.view.View.OnHoverListener
                public final boolean onHover(View view, MotionEvent motionEvent) {
                    return this.f6131a.lambda$initView$0(view, motionEvent);
                }
            });
        }

        public boolean isDescending() {
            return this.mDescending;
        }

        public void setDescendingEnabled(boolean z2) {
            this.mDescendingEnabled = z2;
        }

        @Override // android.view.View
        public void setEnabled(boolean z2) {
            super.setEnabled(z2);
            this.mTextView.setEnabled(z2);
        }

        public void setFilterHoverListener(FilterHoverListener filterHoverListener) {
            this.mFilterHoverListener = filterHoverListener;
        }

        public void setIconView(ImageView imageView) {
            this.mArrow = imageView;
        }

        public void setIndicatorVisibility(int i2) {
            this.mArrow.setVisibility(i2);
        }

        @Override // android.view.View
        public void setOnClickListener(final View.OnClickListener onClickListener) {
            super.setOnClickListener(new View.OnClickListener() { // from class: miuix.miuixbasewidget.widget.FilterSortView.TabView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (!TabView.this.mFiltered) {
                        TabView.this.setFiltered(true);
                    } else if (TabView.this.mDescendingEnabled) {
                        TabView tabView = TabView.this;
                        tabView.setDescending(true ^ tabView.mDescending);
                    }
                    onClickListener.onClick(view);
                    if (HapticCompat.doesSupportHaptic(HapticCompat.HapticVersion.HAPTIC_VERSION_2)) {
                        TabView.this.getHapticFeedbackCompat().lambda$performExtHapticFeedbackAsync$0(204);
                    } else {
                        HapticCompat.performHapticFeedback(view, HapticFeedbackConstants.MIUI_MESH_NORMAL);
                    }
                }
            });
        }

        public void setOnFilteredListener(OnFilteredListener onFilteredListener) {
            this.mOnFilteredListener = onFilteredListener;
        }

        public void setTextView(TextView textView) {
            this.mTextView = textView;
        }

        public TabView(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        private void setFiltered(boolean z2, boolean z3) {
            TabView tabView;
            FilterSortView filterSortView = (FilterSortView) getParent();
            if (z2 && filterSortView != null) {
                int childCount = filterSortView.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = filterSortView.getChildAt(i2);
                    if ((childAt instanceof TabView) && (tabView = (TabView) childAt) != this && tabView.mFiltered) {
                        tabView.setFiltered(false);
                    }
                }
            }
            this.mFiltered = z2;
            updateTextAppearance(z2);
            this.mTextView.setSelected(z2);
            this.mArrow.setSelected(z2);
            setSelected(z2);
            OnFilteredListener onFilteredListener = this.mOnFilteredListener;
            if (onFilteredListener == null || !z2) {
                return;
            }
            onFilteredListener.onFilteredChanged(this, z2, z3);
        }

        public TabView(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            this.mDescendingEnabled = true;
            int tabLayoutResource = getTabLayoutResource();
            LayoutInflater.from(context).inflate(tabLayoutResource, (ViewGroup) this, true);
            this.mTextView = (TextView) findViewById(android.R.id.text1);
            this.mArrow = (ImageView) findViewById(R.id.arrow);
            this.mTextView.setImportantForAccessibility(2);
            this.mArrow.setImportantForAccessibility(2);
            if (tabLayoutResource == R.layout.miuix_appcompat_filter_sort_tab_view) {
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FilterSortTabView, i2, R.style.Widget_FilterSortTabView_DayNight);
                String string = typedArrayObtainStyledAttributes.getString(R.styleable.FilterSortTabView_android_text);
                boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FilterSortTabView_descending, true);
                this.mIndicatorVisibility = typedArrayObtainStyledAttributes.getInt(R.styleable.FilterSortTabView_indicatorVisibility, 0);
                this.mArrowIcon = typedArrayObtainStyledAttributes.getDrawable(R.styleable.FilterSortTabView_arrowFilterSortTabView);
                this.mTextColor = typedArrayObtainStyledAttributes.getColorStateList(R.styleable.FilterSortTabView_filterSortTabViewTextColor);
                this.mTextAppearanceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.FilterSortTabView_filterSortTabViewTabTextAppearance, 0);
                this.mSelectedTextAppearanceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.FilterSortTabView_filterSortTabViewTabActivatedTextAppearance, 0);
                typedArrayObtainStyledAttributes.recycle();
                initView(string, z2);
            }
            this.mArrow.setVisibility(this.mIndicatorVisibility);
            if (getId() == -1) {
                setId(LinearLayout.generateViewId());
            }
            ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: miuix.miuixbasewidget.widget.FilterSortView.TabView.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(@NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                    if (TabView.this.mTextView != null && !TextUtils.isEmpty(TabView.this.mTextView.getText())) {
                        accessibilityNodeInfoCompat.setContentDescription(TabView.this.mTextView.getText());
                    }
                    accessibilityNodeInfoCompat.setSelected(view.isSelected());
                    if (view.isSelected()) {
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

    public FilterSortView(Context context) {
        this(context, null);
    }

    private TabView inflateTabView() {
        return (TabView) LayoutInflater.from(getContext()).inflate(R.layout.layout_filter_tab_view, (ViewGroup) null);
    }

    private void initCoverBg(Drawable drawable) {
        TabView tabViewInflateTabView = inflateTabView();
        this.mBackgroundTabView = tabViewInflateTabView;
        tabViewInflateTabView.setBackground(drawable);
        this.mBackgroundTabView.mArrow.setVisibility(8);
        this.mBackgroundTabView.mTextView.setVisibility(8);
        this.mBackgroundTabView.setVisibility(4);
        this.mBackgroundTabView.setEnabled(this.mEnabled);
        this.mBackgroundTabView.setImportantForAccessibility(2);
        addView(this.mBackgroundTabView);
    }

    private void initHoverBgView() {
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(0, 0);
        View view = new View(getContext());
        this.mHoverBgView = view;
        view.setLayoutParams(layoutParams);
        this.mHoverBgView.setId(View.generateViewId());
        this.mHoverBgView.setBackgroundResource(R.drawable.miuix_appcompat_filter_sort_hover_bg);
        this.mHoverBgView.setAlpha(0.0f);
        addView(this.mHoverBgView);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        constraintSet.connect(this.mHoverBgView.getId(), 3, getId(), 3);
        constraintSet.connect(this.mHoverBgView.getId(), 4, getId(), 4);
        constraintSet.connect(this.mHoverBgView.getId(), 6, getId(), 6);
        constraintSet.connect(this.mHoverBgView.getId(), 7, getId(), 7);
        constraintSet.applyTo(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFiltered$0(ConstraintLayout.LayoutParams layoutParams) {
        this.mBackgroundTabView.setLayoutParams(layoutParams);
    }

    private void refreshTabState() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof TabView) {
                ((TabView) childAt).setEnabled(this.mEnabled);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFiltered(TabView tabView) {
        if (this.mBackgroundTabView.getVisibility() != 0) {
            this.mBackgroundTabView.setVisibility(0);
        }
        final ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mBackgroundTabView.getLayoutParams();
        this.mBackgroundTabView.setX(tabView.getX());
        this.mBackgroundTabView.setY(this.mPadding);
        post(new Runnable() { // from class: miuix.miuixbasewidget.widget.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f6125a.lambda$updateFiltered$0(layoutParams);
            }
        });
    }

    public TabView addTab(CharSequence charSequence) {
        return addTab(charSequence, -1, true);
    }

    public void addTabViewAt(TabView tabView, int i2) {
        if (tabView != null) {
            if (i2 > this.mTabCount || i2 < 0) {
                addView(tabView);
            } else {
                addView(tabView, (getChildCount() - this.mTabCount) + i2);
            }
            this.mTabCount++;
        }
    }

    public void addTabViewChildId(int i2) {
        this.mTabViewChildIds.add(Integer.valueOf(i2));
    }

    public void checkBackgroundTabViewAdded() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if (getChildAt(i2) == this.mBackgroundTabView) {
                return;
            }
        }
        addView(this.mBackgroundTabView, 0);
    }

    public void clearTabViewChildIds() {
        this.mTabViewChildIds.clear();
    }

    public boolean getEnabled() {
        return this.mEnabled;
    }

    public TabView.FilterHoverListener getFilterHoverListener() {
        return this.mFilterHoverListener;
    }

    public TabView.OnFilteredListener getOnFilteredListener() {
        return this.mOnFilteredListener;
    }

    public int getTabCount() {
        return this.mTabCount;
    }

    public TabView getTabViewAt(int i2) {
        if (i2 <= -1) {
            return null;
        }
        View childAt = getChildAt((getChildCount() - this.mTabCount) + i2);
        if (childAt instanceof TabView) {
            return (TabView) childAt;
        }
        return null;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mFilteredUpdated = false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        TabView tabView;
        super.onLayout(z2, i2, i3, i4, i5);
        if (this.mBackgroundTabView.getVisibility() != 8) {
            int left = this.mBackgroundTabView.getLeft();
            int i6 = this.mPadding;
            this.mBackgroundTabView.layout(left, i6, this.mBackgroundTabView.getMeasuredWidth() + left, this.mBackgroundTabView.getMeasuredHeight() + i6);
        }
        int i7 = this.mFilteredId;
        if (i7 == -1 || this.mFilteredUpdated || (tabView = (TabView) findViewById(i7)) == null) {
            return;
        }
        updateFiltered(tabView);
        if (tabView.getWidth() > 0) {
            this.mFilteredUpdated = true;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.mFilteredId == -1 || this.mBackgroundTabView.getVisibility() == 8) {
            return;
        }
        this.mBackgroundTabView.measure(View.MeasureSpec.makeMeasureSpec(((TabView) findViewById(this.mFilteredId)).getMeasuredWidth(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() - (this.mPadding * 2), BasicMeasure.EXACTLY));
    }

    public void removeAllTabViews() {
        removeAllViews();
        this.mTabCount = 0;
    }

    public void removeTabViewAt(int i2) {
        if (i2 <= -1) {
            return;
        }
        int childCount = (getChildCount() - this.mTabCount) + i2;
        if (getChildAt(childCount) instanceof TabView) {
            removeViewAt(childCount);
        }
        this.mTabCount--;
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

    public void setFilteredTab(int i2) {
        TabView tabViewAt = getTabViewAt(i2);
        if (tabViewAt != null) {
            setFilteredTab(tabViewAt);
        }
    }

    public void setFilteredUpdated(boolean z2) {
        this.mFilteredUpdated = z2;
    }

    public void setTabIncatorVisibility(int i2) {
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt instanceof TabView) {
                ((TabView) childAt).setIndicatorVisibility(i2);
            }
        }
    }

    public void updateChildIdsFromXml() {
        if (this.mTabViewChildIds.size() == 0) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt instanceof TabView) {
                    TabView tabView = (TabView) childAt;
                    if (tabView.getId() != this.mBackgroundTabView.getId()) {
                        tabView.setOnFilteredListener(this.mOnFilteredListener);
                        this.mTabViewChildIds.add(Integer.valueOf(tabView.getId()));
                        tabView.setFilterHoverListener(this.mFilterHoverListener);
                    }
                }
            }
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this);
            updateTabViews(constraintSet);
            constraintSet.applyTo(this);
        }
    }

    public void updateTabViews(ConstraintSet constraintSet) {
        int i2 = 0;
        while (i2 < this.mTabViewChildIds.size()) {
            int iIntValue = this.mTabViewChildIds.get(i2).intValue();
            constraintSet.constrainWidth(iIntValue, 0);
            constraintSet.constrainHeight(iIntValue, -2);
            constraintSet.setHorizontalWeight(iIntValue, 1.0f);
            int iIntValue2 = i2 == 0 ? 0 : this.mTabViewChildIds.get(i2 - 1).intValue();
            int iIntValue3 = i2 == this.mTabViewChildIds.size() + (-1) ? 0 : this.mTabViewChildIds.get(i2 + 1).intValue();
            constraintSet.centerVertically(iIntValue, 0);
            constraintSet.connect(iIntValue, 6, iIntValue2, iIntValue2 == 0 ? 6 : 7, iIntValue2 == 0 ? this.mPadding : 0);
            constraintSet.connect(iIntValue, 7, iIntValue3, iIntValue3 == 0 ? 7 : 6, iIntValue3 == 0 ? this.mPadding : 0);
            constraintSet.connect(iIntValue, 3, 0, 3, this.mPadding);
            constraintSet.connect(iIntValue, 4, 0, 4, this.mPadding);
            i2++;
        }
    }

    public FilterSortView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TabView addTab(CharSequence charSequence, int i2) {
        return addTab(charSequence, i2, true);
    }

    public FilterSortView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTabViewChildIds = new ArrayList();
        this.mFilteredId = -1;
        this.mEnabled = true;
        this.mFilteredUpdated = false;
        this.mTabCount = 0;
        this.mOnFilteredListener = new AnonymousClass1();
        this.mFilterHoverListener = new TabView.FilterHoverListener() { // from class: miuix.miuixbasewidget.widget.FilterSortView.2
            @Override // miuix.miuixbasewidget.widget.FilterSortView.TabView.FilterHoverListener
            public void onHoverEnter() {
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(FilterSortView.this.mHoverBgView, "alpha", FilterSortView.this.mHoverBgView.getAlpha(), 1.0f);
                objectAnimatorOfFloat.setDuration(350L);
                objectAnimatorOfFloat.setInterpolator(new DecelerateInterpolator(1.5f));
                objectAnimatorOfFloat.start();
            }

            @Override // miuix.miuixbasewidget.widget.FilterSortView.TabView.FilterHoverListener
            public void onHoverExit(float f2, float f3) {
                if (f2 < FilterSortView.this.mPadding || f3 < 0.0f || f2 > (FilterSortView.this.getRight() - FilterSortView.this.getLeft()) - (FilterSortView.this.mPadding * 2) || f3 > (FilterSortView.this.getBottom() - FilterSortView.this.getTop()) - (FilterSortView.this.mPadding * 2)) {
                    ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(FilterSortView.this.mHoverBgView, "alpha", FilterSortView.this.mHoverBgView.getAlpha(), 0.0f);
                    objectAnimatorOfFloat.setDuration(350L);
                    objectAnimatorOfFloat.setInterpolator(new DecelerateInterpolator(1.5f));
                    objectAnimatorOfFloat.start();
                }
            }

            @Override // miuix.miuixbasewidget.widget.FilterSortView.TabView.FilterHoverListener
            public void onHoverFilterEnter() {
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(FilterSortView.this.mBackgroundTabView, "scaleX", FilterSortView.this.mBackgroundTabView.getScaleX(), 1.05f);
                ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(FilterSortView.this.mBackgroundTabView, "scaleY", FilterSortView.this.mBackgroundTabView.getScaleY(), 1.05f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
                animatorSet.setDuration(350L);
                animatorSet.setInterpolator(new DecelerateInterpolator(1.5f));
                animatorSet.start();
            }

            @Override // miuix.miuixbasewidget.widget.FilterSortView.TabView.FilterHoverListener
            public void onHoverFilterExit() {
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(FilterSortView.this.mBackgroundTabView, "scaleX", FilterSortView.this.mBackgroundTabView.getScaleX(), 1.0f);
                ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(FilterSortView.this.mBackgroundTabView, "scaleY", FilterSortView.this.mBackgroundTabView.getScaleY(), 1.0f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
                animatorSet.setDuration(350L);
                animatorSet.setInterpolator(new DecelerateInterpolator(1.5f));
                animatorSet.start();
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FilterSortView, i2, R.style.Widget_FilterSortView_DayNight);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.FilterSortView_filterSortViewBackground);
        Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(R.styleable.FilterSortView_filterSortTabViewCoverBg);
        this.mEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FilterSortView_android_enabled, true);
        typedArrayObtainStyledAttributes.recycle();
        this.mPadding = getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_filter_sort_view_padding);
        setBackground(drawable);
        initHoverBgView();
        initCoverBg(drawable2);
        CompatViewMethod.setForceDarkAllowed(this, false);
    }

    public TabView addTab(CharSequence charSequence, boolean z2) {
        return addTab(charSequence, -1, z2);
    }

    public void setFilteredTab(TabView tabView) {
        if (this.mFilteredId != tabView.getId()) {
            this.mFilteredId = tabView.getId();
        }
        tabView.setFiltered(true);
        updateChildIdsFromXml();
    }

    public TabView addTab(CharSequence charSequence, int i2, boolean z2) {
        TabView tabViewInflateTabView = inflateTabView();
        addTab(tabViewInflateTabView, charSequence, i2, z2);
        return tabViewInflateTabView;
    }

    public void addTab(@NonNull TabView tabView, CharSequence charSequence, int i2, boolean z2) {
        tabView.setOnFilteredListener(this.mOnFilteredListener);
        tabView.setEnabled(this.mEnabled);
        tabView.setFilterHoverListener(this.mFilterHoverListener);
        this.mFilteredUpdated = false;
        if (i2 >= 0 && i2 <= this.mTabCount) {
            addView(tabView, i2);
        } else {
            addView(tabView, -1);
        }
        this.mTabCount++;
        this.mTabViewChildIds.add(Integer.valueOf(tabView.getId()));
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        updateTabViews(constraintSet);
        constraintSet.applyTo(this);
        tabView.initView(charSequence, z2);
    }
}
