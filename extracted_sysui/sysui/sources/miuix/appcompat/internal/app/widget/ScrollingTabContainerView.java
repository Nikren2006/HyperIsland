package miuix.appcompat.internal.app.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import miuix.animation.Folme;
import miuix.animation.IHoverStyle;
import miuix.animation.base.AnimConfig;
import miuix.appcompat.app.ActionBar;
import miuix.appcompat.internal.view.ActionBarPolicy;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.ViewUtils;
import miuix.miuixbasewidget.R;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemClickListener, ActionBar.FragmentViewPagerChangeListener {
    public static final int MODE_COLLAPSE = 0;
    public static final int MODE_MOVABLE = 1;
    public static final int MODE_SECONDARY = 2;
    private boolean mAllowCollapse;
    private int mContentHeight;
    private final LayoutInflater mInflater;
    private boolean mIsScrolledX;
    private int mLastSelectedPosition;
    int mMaxTabWidth;
    private Paint mPaint;
    private int mSelectedTabIndex;
    int mStackedTabMaxWidth;
    private TabClickListener mTabClickListener;
    private Bitmap mTabIndicatorBitmap;
    private float mTabIndicatorPosition;
    protected LinearLayout mTabLayout;
    Runnable mTabSelector;
    private Spinner mTabSpinner;
    private WeakHashMap<TextView, Integer> mTextStyleMap;
    private float mTouchInitX;
    private final int mTouchSlop;
    private boolean mTranslucentIndicator;

    public class TabAdapter extends BaseAdapter {
        private TabAdapter() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return ScrollingTabContainerView.this.mTabLayout.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return ((TabView) ScrollingTabContainerView.this.mTabLayout.getChildAt(i2)).getTab();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                return ScrollingTabContainerView.this.createTabView((ActionBar.Tab) getItem(i2), true);
            }
            ((TabView) view).bindTab((ActionBar.Tab) getItem(i2));
            return view;
        }
    }

    public static class TabClickListener implements View.OnClickListener {
        private WeakReference<ScrollingTabContainerView> mRefs;

        public TabClickListener(ScrollingTabContainerView scrollingTabContainerView) {
            this.mRefs = new WeakReference<>(scrollingTabContainerView);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            WeakReference<ScrollingTabContainerView> weakReference = this.mRefs;
            ScrollingTabContainerView scrollingTabContainerView = weakReference != null ? weakReference.get() : null;
            if (scrollingTabContainerView == null) {
                return;
            }
            ((TabView) view).getTab().select();
            int childCount = scrollingTabContainerView.mTabLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = scrollingTabContainerView.mTabLayout.getChildAt(i2);
                childAt.setSelected(childAt == view);
            }
        }
    }

    public static class TabSelectorRunnable implements Runnable {
        private int mPosition;
        private WeakReference<ScrollingTabContainerView> mRefs;

        public TabSelectorRunnable(ScrollingTabContainerView scrollingTabContainerView, int i2) {
            this.mRefs = new WeakReference<>(scrollingTabContainerView);
            this.mPosition = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            View childAt;
            WeakReference<ScrollingTabContainerView> weakReference = this.mRefs;
            ScrollingTabContainerView scrollingTabContainerView = weakReference != null ? weakReference.get() : null;
            if (scrollingTabContainerView == null || (childAt = scrollingTabContainerView.mTabLayout.getChildAt(this.mPosition)) == null) {
                return;
            }
            scrollingTabContainerView.smoothScrollTo(childAt.getLeft() - ((scrollingTabContainerView.getWidth() - childAt.getWidth()) / 2), 0);
            scrollingTabContainerView.mTabSelector = null;
        }
    }

    public static class TabView extends LinearLayout {
        private ImageView mBadgeView;
        private View mCustomView;
        private ImageView mIconView;
        private ScrollingTabContainerView mParent;
        private ActionBar.Tab mTab;
        private TextView mTextView;

        public TabView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            Folme.useAt(this).hover().setEffect(IHoverStyle.HoverEffect.FLOATED_WRAPPED).handleHoverOf(this, new AnimConfig[0]);
            setAccessibilityDelegate();
        }

        private void setAccessibilityDelegate() {
            ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: miuix.appcompat.internal.app.widget.ScrollingTabContainerView.TabView.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(@NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
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

        public void attach(ScrollingTabContainerView scrollingTabContainerView, ActionBar.Tab tab, boolean z2) {
            this.mParent = scrollingTabContainerView;
            this.mTab = tab;
            if (z2) {
                setGravity(8388627);
            }
            update();
        }

        public void bindTab(ActionBar.Tab tab) {
            this.mTab = tab;
            update();
        }

        public ActionBar.Tab getTab() {
            return this.mTab;
        }

        public TextView getTextView() {
            return this.mTextView;
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            TextView textView = this.mTextView;
            if (textView != null) {
                this.mTextView.setTextAppearance(this.mParent.getTabTextStyleId(textView));
            }
            ImageView imageView = this.mIconView;
            if (imageView != null) {
                imageView.setImageDrawable(this.mTab.getIcon());
            }
        }

        public void update() {
            Drawable drawableResolveDrawable;
            int intrinsicHeight;
            int lineHeight;
            ActionBar.Tab tab = this.mTab;
            View customView = tab.getCustomView();
            if (customView != null) {
                this.mCustomView = this.mParent.updateCustomTabView(this, customView, this.mTextView, this.mIconView);
            } else {
                View view = this.mCustomView;
                if (view != null) {
                    removeView(view);
                    this.mCustomView = null;
                }
                Context context = getContext();
                Drawable icon = tab.getIcon();
                CharSequence text = tab.getText();
                if (icon != null) {
                    if (this.mIconView == null) {
                        ImageView imageView = new ImageView(context);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                        layoutParams.gravity = 16;
                        imageView.setLayoutParams(layoutParams);
                        addView(imageView, 0);
                        this.mIconView = imageView;
                    }
                    this.mIconView.setImageDrawable(icon);
                    this.mIconView.setVisibility(0);
                } else {
                    ImageView imageView2 = this.mIconView;
                    if (imageView2 != null) {
                        imageView2.setVisibility(8);
                        this.mIconView.setImageDrawable(null);
                    }
                }
                if (text != null) {
                    if (this.mTextView == null) {
                        ScrollingTabTextView scrollingTabTextView = new ScrollingTabTextView(context, null, this.mParent.getDefaultTabTextStyle());
                        scrollingTabTextView.setEllipsize(TextUtils.TruncateAt.END);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                        layoutParams2.gravity = 16;
                        scrollingTabTextView.setLayoutParams(layoutParams2);
                        addView(scrollingTabTextView);
                        this.mTextView = scrollingTabTextView;
                    }
                    this.mTextView.setText(text);
                    this.mTextView.setVisibility(0);
                    if (this.mBadgeView == null && (drawableResolveDrawable = AttributeResolver.resolveDrawable(context, miuix.appcompat.R.attr.actionBarTabBadgeIcon)) != null) {
                        ImageView imageView3 = new ImageView(context);
                        imageView3.setImageDrawable(drawableResolveDrawable);
                        imageView3.setVisibility(8);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
                        layoutParams3.gravity = 48;
                        Drawable background = getBackground();
                        if (background != null && (intrinsicHeight = background.getIntrinsicHeight()) > (lineHeight = this.mTextView.getLineHeight())) {
                            layoutParams3.topMargin = (intrinsicHeight - lineHeight) / 2;
                        }
                        imageView3.setLayoutParams(layoutParams3);
                        addView(imageView3);
                        this.mBadgeView = imageView3;
                    }
                } else {
                    TextView textView = this.mTextView;
                    if (textView != null) {
                        textView.setVisibility(8);
                        this.mTextView.setText((CharSequence) null);
                    }
                }
                ImageView imageView4 = this.mIconView;
                if (imageView4 != null) {
                    imageView4.setContentDescription(tab.getContentDescription());
                }
            }
            this.mParent.updateTabTextStyle(this.mTextView);
        }
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        this.mPaint = new Paint();
        this.mLastSelectedPosition = -1;
        this.mIsScrolledX = false;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        this.mInflater = layoutInflaterFrom;
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(context);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, miuix.appcompat.R.styleable.ActionBar, android.R.attr.actionBarStyle, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(miuix.appcompat.R.styleable.ActionBar_tabIndicator);
        this.mTranslucentIndicator = typedArrayObtainStyledAttributes.getBoolean(miuix.appcompat.R.styleable.ActionBar_translucentTabIndicator, true);
        this.mTabIndicatorBitmap = getTabIndicatorBitmap(drawable);
        typedArrayObtainStyledAttributes.recycle();
        if (this.mTranslucentIndicator) {
            this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }
        setHorizontalScrollBarEnabled(false);
        this.mStackedTabMaxWidth = actionBarPolicy.getStackedTabMaxWidth();
        LinearLayout linearLayout = (LinearLayout) layoutInflaterFrom.inflate(getTabBarLayoutRes(), (ViewGroup) this, false);
        this.mTabLayout = linearLayout;
        addView(linearLayout, new FrameLayout.LayoutParams(-2, -2));
        this.mTouchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TabView createTabView(ActionBar.Tab tab, boolean z2) {
        TabView tabView = (TabView) this.mInflater.inflate(getTabViewLayoutRes(), (ViewGroup) this.mTabLayout, false);
        tabView.attach(this, tab, z2);
        if (z2) {
            tabView.setBackground(null);
            tabView.setLayoutParams(new LinearLayout.LayoutParams(-1, this.mContentHeight));
        } else {
            tabView.setFocusable(true);
            if (this.mTabClickListener == null) {
                this.mTabClickListener = new TabClickListener(this);
            }
            tabView.setOnClickListener(this.mTabClickListener);
        }
        if (this.mTabLayout.getChildCount() != 0) {
            ((LinearLayout.LayoutParams) tabView.getLayoutParams()).setMarginStart(getTabViewMarginHorizontal());
        }
        return tabView;
    }

    private Bitmap getTabIndicatorBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = this.mTranslucentIndicator ? Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ALPHA_8) : Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    private boolean isCollapsed() {
        Spinner spinner = this.mTabSpinner;
        return spinner != null && spinner.getParent() == this;
    }

    private boolean performExpand() {
        if (!isCollapsed()) {
            return false;
        }
        removeView(this.mTabSpinner);
        addView(this.mTabLayout, new ViewGroup.LayoutParams(-2, -2));
        setTabSelected(this.mTabSpinner.getSelectedItemPosition());
        return false;
    }

    public void addTab(ActionBar.Tab tab, boolean z2) {
        TabView tabViewCreateTabView = createTabView(tab, false);
        this.mTabLayout.addView(tabViewCreateTabView);
        Spinner spinner = this.mTabSpinner;
        if (spinner != null) {
            ((TabAdapter) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (z2) {
            tabViewCreateTabView.setSelected(true);
            this.mLastSelectedPosition = this.mTabLayout.getChildCount() - 1;
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void animateToTab(int i2) {
        Runnable runnable = this.mTabSelector;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        TabSelectorRunnable tabSelectorRunnable = new TabSelectorRunnable(this, i2);
        this.mTabSelector = tabSelectorRunnable;
        post(tabSelectorRunnable);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Bitmap bitmap = this.mTabIndicatorBitmap;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, this.mTabIndicatorPosition, getHeight() - this.mTabIndicatorBitmap.getHeight(), this.mPaint);
        }
    }

    public abstract int getDefaultTabTextStyle();

    public abstract int getTabBarLayoutRes();

    public abstract int getTabContainerHeight();

    public float getTabIndicatorPosition() {
        return this.mTabIndicatorPosition;
    }

    public int getTabTextStyleId(TextView textView) {
        WeakHashMap<TextView, Integer> weakHashMap;
        return (textView == null || (weakHashMap = this.mTextStyleMap) == null || !weakHashMap.containsKey(textView)) ? AttributeResolver.resolve(getContext(), getDefaultTabTextStyle()) : this.mTextStyleMap.get(textView).intValue();
    }

    public abstract int getTabViewLayoutRes();

    public abstract int getTabViewMarginHorizontal();

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.mTabSelector;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(getContext());
        setContentHeight(getTabContainerHeight());
        this.mStackedTabMaxWidth = actionBarPolicy.getStackedTabMaxWidth();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.mTabSelector;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            this.mTouchInitX = motionEvent.getX();
            this.mIsScrolledX = false;
        } else if (motionEvent.getActionMasked() == 2) {
            this.mIsScrolledX = Math.abs(motionEvent.getX() - this.mTouchInitX) > ((float) this.mTouchSlop);
        } else if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
            this.mIsScrolledX = false;
        }
        if (!this.mIsScrolledX) {
            return false;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.setAction(0);
        super.onInterceptTouchEvent(motionEventObtain);
        return true;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        ((TabView) view).getTab().select();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        if (this.mTabLayout.getChildAt(this.mSelectedTabIndex) != null) {
            setTabIndicatorPosition(this.mSelectedTabIndex);
            scrollToTab(this.mSelectedTabIndex);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        boolean z2 = mode == 1073741824;
        setFillViewport(z2);
        int childCount = this.mTabLayout.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.mMaxTabWidth = -1;
        } else {
            if (childCount > 2) {
                this.mMaxTabWidth = (int) (View.MeasureSpec.getSize(i2) * 0.4f);
            } else {
                this.mMaxTabWidth = (int) (View.MeasureSpec.getSize(i2) * 0.6f);
            }
            this.mMaxTabWidth = Math.min(this.mMaxTabWidth, this.mStackedTabMaxWidth);
        }
        int i4 = this.mContentHeight;
        if (i4 != -2) {
            i3 = View.MeasureSpec.makeMeasureSpec(i4, BasicMeasure.EXACTLY);
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i2, i3);
        int measuredWidth2 = getMeasuredWidth();
        if (!z2 || measuredWidth == measuredWidth2) {
            return;
        }
        setTabSelected(this.mSelectedTabIndex);
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageScrolled(int i2, float f2, boolean z2, boolean z3) {
        setTabIndicatorPosition(i2, f2);
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageSelected(int i2) {
        TabView tabView = (TabView) this.mTabLayout.getChildAt(i2);
        if (tabView != null) {
            tabView.sendAccessibilityEvent(4);
        }
        setTabIndicatorPosition(i2);
        int i3 = this.mLastSelectedPosition;
        if (i3 != -1) {
            boolean z2 = true;
            if (Math.abs(i3 - i2) == 1) {
                TabView tabView2 = (TabView) this.mTabLayout.getChildAt(this.mLastSelectedPosition);
                ScrollingTabTextView scrollingTabTextView = tabView2 != null ? (ScrollingTabTextView) tabView2.getTextView() : null;
                ScrollingTabTextView scrollingTabTextView2 = tabView != null ? (ScrollingTabTextView) tabView.getTextView() : null;
                if (scrollingTabTextView != null && scrollingTabTextView2 != null) {
                    if (ViewUtils.isLayoutRtl(this)) {
                        z2 = false;
                        scrollingTabTextView.startScrollAnimation(z2);
                        scrollingTabTextView2.startScrollAnimation(z2);
                    } else {
                        z2 = false;
                        scrollingTabTextView.startScrollAnimation(z2);
                        scrollingTabTextView2.startScrollAnimation(z2);
                    }
                }
            }
        }
        this.mLastSelectedPosition = i2;
    }

    public void removeAllTabs() {
        this.mTabLayout.removeAllViews();
        Spinner spinner = this.mTabSpinner;
        if (spinner != null) {
            ((TabAdapter) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void removeTabAt(int i2) {
        LinearLayout linearLayout = this.mTabLayout;
        if (linearLayout != null && linearLayout.getChildAt(i2) != null) {
            this.mTabLayout.removeViewAt(i2);
        }
        Spinner spinner = this.mTabSpinner;
        if (spinner != null) {
            ((TabAdapter) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void scrollToTab(int i2) {
        View childAt = this.mTabLayout.getChildAt(i2);
        scrollTo(childAt.getLeft() - ((getWidth() - childAt.getWidth()) / 2), 0);
    }

    public void setAllowCollapse(boolean z2) {
        this.mAllowCollapse = z2;
    }

    public void setBadgeVisibility(int i2, boolean z2) {
        ImageView imageView;
        if (i2 <= this.mTabLayout.getChildCount() - 1 && (imageView = ((TabView) this.mTabLayout.getChildAt(i2)).mBadgeView) != null) {
            if (z2) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    public void setContentHeight(int i2) {
        if (this.mContentHeight != i2) {
            this.mContentHeight = i2;
            requestLayout();
        }
    }

    public void setEmbeded(boolean z2) {
        setHorizontalFadingEdgeEnabled(true);
    }

    public void setTabIconWithPosition(int i2, int i3, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        TextView textView;
        if (i2 <= this.mTabLayout.getChildCount() - 1 && (textView = ((TabView) this.mTabLayout.getChildAt(i2)).mTextView) != null) {
            textView.setGravity(16);
            textView.setCompoundDrawablePadding(i3);
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        }
    }

    public void setTabIndicatorPosition(int i2) {
        setTabIndicatorPosition(i2, 0.0f);
    }

    public void setTabSelected(int i2) {
        setTabSelected(i2, true);
    }

    public void setTextAppearance(int i2, int i3) {
        TextView textView;
        if (i2 < 0 || i2 > this.mTabLayout.getChildCount() - 1 || (textView = ((TabView) this.mTabLayout.getChildAt(i2)).mTextView) == null) {
            return;
        }
        if (this.mTextStyleMap == null) {
            this.mTextStyleMap = new WeakHashMap<>();
        }
        this.mTextStyleMap.put(textView, Integer.valueOf(i3));
        textView.setTextAppearance(textView.getContext(), i3);
    }

    public View updateCustomTabView(ViewGroup viewGroup, View view, TextView textView, ImageView imageView) {
        return null;
    }

    public void updateTab(int i2) {
        ((TabView) this.mTabLayout.getChildAt(i2)).update();
        Spinner spinner = this.mTabSpinner;
        if (spinner != null) {
            ((TabAdapter) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void updateTabTextStyle(TextView textView) {
    }

    public void setTabIndicatorPosition(int i2, float f2) {
        if (this.mTabIndicatorBitmap != null) {
            View childAt = this.mTabLayout.getChildAt(i2);
            this.mTabIndicatorPosition = childAt.getLeft() + ((childAt.getWidth() - this.mTabIndicatorBitmap.getWidth()) / 2) + ((this.mTabLayout.getChildAt(i2 + 1) == null ? childAt.getWidth() : (childAt.getWidth() + r4.getWidth()) / 2.0f) * f2);
            invalidate();
        }
    }

    public void setTabSelected(int i2, boolean z2) {
        this.mSelectedTabIndex = i2;
        int childCount = this.mTabLayout.getChildCount();
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = this.mTabLayout.getChildAt(i3);
            boolean z3 = i3 == i2;
            childAt.setSelected(z3);
            if (z3) {
                if (z2) {
                    animateToTab(i2);
                } else {
                    scrollToTab(i2);
                }
            }
            i3++;
        }
    }

    public void addTab(ActionBar.Tab tab, int i2, boolean z2) {
        TabView tabViewCreateTabView = createTabView(tab, false);
        this.mTabLayout.addView(tabViewCreateTabView, i2);
        Spinner spinner = this.mTabSpinner;
        if (spinner != null) {
            ((TabAdapter) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (z2) {
            tabViewCreateTabView.setSelected(true);
            this.mLastSelectedPosition = this.mTabLayout.getChildCount() - 1;
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }
}
