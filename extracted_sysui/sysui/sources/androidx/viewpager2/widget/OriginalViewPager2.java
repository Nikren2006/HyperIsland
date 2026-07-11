package androidx.viewpager2.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import miuix.androidbasewidget.adapter.StatefulAdapter;
import miuix.spring.view.SpringStateListener;

/* JADX INFO: loaded from: classes.dex */
public class OriginalViewPager2 extends ViewGroup {
    public static final int OFFSCREEN_PAGE_LIMIT_DEFAULT = -1;
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "OriginalViewPager2";
    static boolean sFeatureEnhancedA11yEnabled = true;
    AccessibilityProvider mAccessibilityProvider;
    int mCurrentItem;
    private RecyclerView.AdapterDataObserver mCurrentItemDataSetChangeObserver;
    boolean mCurrentItemDirty;
    private OriginalCompositeOnPageChangeCallback mExternalPageChangeCallbacks;
    private OriginalFakeDrag mFakeDragger;
    private LinearLayoutManager mLayoutManager;
    private int mOffscreenPageLimit;
    private OriginalCompositeOnPageChangeCallback mPageChangeEventDispatcher;
    private OriginalPageTransformerAdapter mPageTransformerAdapter;
    private PagerSnapHelper mPagerSnapHelper;
    private Parcelable mPendingAdapterState;
    private int mPendingCurrentItem;
    miuix.recyclerview.widget.RecyclerView mRecyclerView;
    private RecyclerView.ItemAnimator mSavedItemAnimator;
    private boolean mSavedItemAnimatorPresent;
    OriginalScrollEventAdapter mScrollEventAdapter;
    private final Rect mTmpChildRect;
    private final Rect mTmpContainerRect;
    private boolean mUserInputEnabled;
    private int savedLastWidth;

    public abstract class AccessibilityProvider {
        private AccessibilityProvider() {
        }

        public boolean handlesGetAccessibilityClassName() {
            return false;
        }

        public boolean handlesLmPerformAccessibilityAction(int i2) {
            return false;
        }

        public boolean handlesPerformAccessibilityAction(int i2, Bundle bundle) {
            return false;
        }

        public boolean handlesRvGetAccessibilityClassName() {
            return false;
        }

        public void onAttachAdapter(@Nullable RecyclerView.Adapter<?> adapter) {
        }

        public void onDetachAdapter(@Nullable RecyclerView.Adapter<?> adapter) {
        }

        public String onGetAccessibilityClassName() {
            throw new IllegalStateException("Not implemented.");
        }

        public void onInitialize(@NonNull OriginalCompositeOnPageChangeCallback originalCompositeOnPageChangeCallback, @NonNull miuix.recyclerview.widget.RecyclerView recyclerView) {
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        }

        public void onLmInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        }

        public boolean onLmPerformAccessibilityAction(int i2) {
            throw new IllegalStateException("Not implemented.");
        }

        public boolean onPerformAccessibilityAction(int i2, Bundle bundle) {
            throw new IllegalStateException("Not implemented.");
        }

        public void onRestorePendingState() {
        }

        public CharSequence onRvGetAccessibilityClassName() {
            throw new IllegalStateException("Not implemented.");
        }

        public void onRvInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
        }

        public void onSetLayoutDirection() {
        }

        public void onSetNewCurrentItem() {
        }

        public void onSetOrientation() {
        }

        public void onSetUserInputEnabled() {
        }
    }

    public class BasicAccessibilityProvider extends AccessibilityProvider {
        public BasicAccessibilityProvider() {
            super();
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public boolean handlesLmPerformAccessibilityAction(int i2) {
            return (i2 == 8192 || i2 == 4096) && !OriginalViewPager2.this.isUserInputEnabled();
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public boolean handlesRvGetAccessibilityClassName() {
            return true;
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public void onLmInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (OriginalViewPager2.this.isUserInputEnabled()) {
                return;
            }
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
            accessibilityNodeInfoCompat.setScrollable(false);
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public boolean onLmPerformAccessibilityAction(int i2) {
            if (handlesLmPerformAccessibilityAction(i2)) {
                return false;
            }
            throw new IllegalStateException();
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public CharSequence onRvGetAccessibilityClassName() {
            if (handlesRvGetAccessibilityClassName()) {
                return "androidx.viewpager.widget.ViewPager";
            }
            throw new IllegalStateException();
        }
    }

    public static abstract class DataSetChangeObserver extends RecyclerView.AdapterDataObserver {
        private DataSetChangeObserver() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public abstract void onChanged();

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i2, int i3) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeInserted(int i2, int i3) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeMoved(int i2, int i3, int i4) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeRemoved(int i2, int i3) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i2, int i3, @Nullable Object obj) {
            onChanged();
        }
    }

    public class LinearLayoutManagerImpl extends LinearLayoutManager {
        public LinearLayoutManagerImpl(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void calculateExtraLayoutSpace(@NonNull RecyclerView.State state, @NonNull int[] iArr) {
            int offscreenPageLimit = OriginalViewPager2.this.getOffscreenPageLimit();
            if (offscreenPageLimit == -1) {
                super.calculateExtraLayoutSpace(state, iArr);
                return;
            }
            int pageSize = OriginalViewPager2.this.getPageSize() * offscreenPageLimit;
            iArr[0] = pageSize;
            iArr[1] = pageSize;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onInitializeAccessibilityNodeInfo(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(recycler, state, accessibilityNodeInfoCompat);
            OriginalViewPager2.this.mAccessibilityProvider.onLmInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean performAccessibilityAction(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, int i2, @Nullable Bundle bundle) {
            return OriginalViewPager2.this.mAccessibilityProvider.handlesLmPerformAccessibilityAction(i2) ? OriginalViewPager2.this.mAccessibilityProvider.onLmPerformAccessibilityAction(i2) : super.performAccessibilityAction(recycler, state, i2, bundle);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean requestChildRectangleOnScreen(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull Rect rect, boolean z2, boolean z3) {
            return false;
        }
    }

    @IntRange(from = 1)
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface OffscreenPageLimit {
    }

    public static abstract class OnPageChangeCallback {
        public void onPageScrollStateChanged(int i2) {
        }

        public void onPageScrolled(int i2, float f2, @Px int i3) {
        }

        public void onPageSelected(int i2) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface Orientation {
    }

    public class PageAwareAccessibilityProvider extends AccessibilityProvider {
        private final AccessibilityViewCommand mActionPageBackward;
        private final AccessibilityViewCommand mActionPageForward;
        private RecyclerView.AdapterDataObserver mAdapterDataObserver;

        public PageAwareAccessibilityProvider() {
            super();
            this.mActionPageForward = new AccessibilityViewCommand() { // from class: androidx.viewpager2.widget.OriginalViewPager2.PageAwareAccessibilityProvider.1
                @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                public boolean perform(@NonNull View view, @Nullable AccessibilityViewCommand.CommandArguments commandArguments) {
                    PageAwareAccessibilityProvider.this.setCurrentItemFromAccessibilityCommand(((OriginalViewPager2) view).getCurrentItem() + 1);
                    return true;
                }
            };
            this.mActionPageBackward = new AccessibilityViewCommand() { // from class: androidx.viewpager2.widget.OriginalViewPager2.PageAwareAccessibilityProvider.2
                @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                public boolean perform(@NonNull View view, @Nullable AccessibilityViewCommand.CommandArguments commandArguments) {
                    PageAwareAccessibilityProvider.this.setCurrentItemFromAccessibilityCommand(((OriginalViewPager2) view).getCurrentItem() - 1);
                    return true;
                }
            };
        }

        private void addCollectionInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            int itemCount;
            int itemCount2;
            if (OriginalViewPager2.this.getAdapter() == null) {
                itemCount = 0;
                itemCount2 = 0;
            } else if (OriginalViewPager2.this.getOrientation() == 1) {
                itemCount = OriginalViewPager2.this.getAdapter().getItemCount();
                itemCount2 = 0;
            } else {
                itemCount2 = OriginalViewPager2.this.getAdapter().getItemCount();
                itemCount = 0;
            }
            AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(itemCount, itemCount2, false, 0));
        }

        private void addScrollActions(AccessibilityNodeInfo accessibilityNodeInfo) {
            int itemCount;
            RecyclerView.Adapter adapter = OriginalViewPager2.this.getAdapter();
            if (adapter == null || (itemCount = adapter.getItemCount()) == 0 || !OriginalViewPager2.this.isUserInputEnabled()) {
                return;
            }
            if (OriginalViewPager2.this.mCurrentItem > 0) {
                accessibilityNodeInfo.addAction(8192);
            }
            if (OriginalViewPager2.this.mCurrentItem < itemCount - 1) {
                accessibilityNodeInfo.addAction(4096);
            }
            accessibilityNodeInfo.setScrollable(true);
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public boolean handlesGetAccessibilityClassName() {
            return true;
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public boolean handlesPerformAccessibilityAction(int i2, Bundle bundle) {
            return i2 == 8192 || i2 == 4096;
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public void onAttachAdapter(@Nullable RecyclerView.Adapter<?> adapter) {
            updatePageAccessibilityActions();
            if (adapter != null) {
                adapter.registerAdapterDataObserver(this.mAdapterDataObserver);
            }
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public void onDetachAdapter(@Nullable RecyclerView.Adapter<?> adapter) {
            if (adapter != null) {
                adapter.unregisterAdapterDataObserver(this.mAdapterDataObserver);
            }
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public String onGetAccessibilityClassName() {
            if (handlesGetAccessibilityClassName()) {
                return "androidx.viewpager.widget.ViewPager";
            }
            throw new IllegalStateException();
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public void onInitialize(@NonNull OriginalCompositeOnPageChangeCallback originalCompositeOnPageChangeCallback, @NonNull miuix.recyclerview.widget.RecyclerView recyclerView) {
            ViewCompat.setImportantForAccessibility(recyclerView, 2);
            this.mAdapterDataObserver = new DataSetChangeObserver() { // from class: androidx.viewpager2.widget.OriginalViewPager2.PageAwareAccessibilityProvider.3
                @Override // androidx.viewpager2.widget.OriginalViewPager2.DataSetChangeObserver, androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void onChanged() {
                    PageAwareAccessibilityProvider.this.updatePageAccessibilityActions();
                }
            };
            if (ViewCompat.getImportantForAccessibility(OriginalViewPager2.this) == 0) {
                ViewCompat.setImportantForAccessibility(OriginalViewPager2.this, 1);
            }
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            addCollectionInfo(accessibilityNodeInfo);
            addScrollActions(accessibilityNodeInfo);
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public boolean onPerformAccessibilityAction(int i2, Bundle bundle) {
            if (!handlesPerformAccessibilityAction(i2, bundle)) {
                throw new IllegalStateException();
            }
            setCurrentItemFromAccessibilityCommand(i2 == 8192 ? OriginalViewPager2.this.getCurrentItem() - 1 : OriginalViewPager2.this.getCurrentItem() + 1);
            return true;
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public void onRestorePendingState() {
            updatePageAccessibilityActions();
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public void onRvInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
            accessibilityEvent.setSource(OriginalViewPager2.this);
            accessibilityEvent.setClassName(onGetAccessibilityClassName());
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public void onSetLayoutDirection() {
            updatePageAccessibilityActions();
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public void onSetNewCurrentItem() {
            updatePageAccessibilityActions();
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public void onSetOrientation() {
            updatePageAccessibilityActions();
        }

        @Override // androidx.viewpager2.widget.OriginalViewPager2.AccessibilityProvider
        public void onSetUserInputEnabled() {
            updatePageAccessibilityActions();
        }

        public void setCurrentItemFromAccessibilityCommand(int i2) {
            if (OriginalViewPager2.this.isUserInputEnabled()) {
                OriginalViewPager2.this.setCurrentItemInternal(i2, true, false);
            }
        }

        public void updatePageAccessibilityActions() {
            int itemCount;
            OriginalViewPager2 originalViewPager2 = OriginalViewPager2.this;
            int i2 = R.id.accessibilityActionPageLeft;
            ViewCompat.removeAccessibilityAction(originalViewPager2, R.id.accessibilityActionPageLeft);
            ViewCompat.removeAccessibilityAction(originalViewPager2, R.id.accessibilityActionPageRight);
            ViewCompat.removeAccessibilityAction(originalViewPager2, R.id.accessibilityActionPageUp);
            ViewCompat.removeAccessibilityAction(originalViewPager2, R.id.accessibilityActionPageDown);
            if (OriginalViewPager2.this.getAdapter() == null || (itemCount = OriginalViewPager2.this.getAdapter().getItemCount()) == 0 || !OriginalViewPager2.this.isUserInputEnabled()) {
                return;
            }
            if (OriginalViewPager2.this.getOrientation() != 0) {
                if (OriginalViewPager2.this.mCurrentItem < itemCount - 1) {
                    ViewCompat.replaceAccessibilityAction(originalViewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.accessibilityActionPageDown, null), null, this.mActionPageForward);
                }
                if (OriginalViewPager2.this.mCurrentItem > 0) {
                    ViewCompat.replaceAccessibilityAction(originalViewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.accessibilityActionPageUp, null), null, this.mActionPageBackward);
                    return;
                }
                return;
            }
            boolean zIsRtl = OriginalViewPager2.this.isRtl();
            int i3 = zIsRtl ? 16908360 : 16908361;
            if (zIsRtl) {
                i2 = 16908361;
            }
            if (OriginalViewPager2.this.mCurrentItem < itemCount - 1) {
                ViewCompat.replaceAccessibilityAction(originalViewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i3, null), null, this.mActionPageForward);
            }
            if (OriginalViewPager2.this.mCurrentItem > 0) {
                ViewCompat.replaceAccessibilityAction(originalViewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i2, null), null, this.mActionPageBackward);
            }
        }
    }

    public interface PageTransformer {
        void transformPage(@NonNull View view, float f2);
    }

    public class PagerSnapHelperImpl extends PagerSnapHelper {
        public PagerSnapHelperImpl() {
        }

        @Override // androidx.recyclerview.widget.PagerSnapHelper, androidx.recyclerview.widget.SnapHelper
        @Nullable
        public View findSnapView(RecyclerView.LayoutManager layoutManager) {
            if (OriginalViewPager2.this.isFakeDragging()) {
                return null;
            }
            return super.findSnapView(layoutManager);
        }
    }

    public class RecyclerViewImpl extends miuix.recyclerview.widget.RecyclerView {
        private float initX;
        private float initY;
        private boolean mIsScrolledX;
        private boolean mIsScrolledY;
        private int mTouchSlop;

        public RecyclerViewImpl(@NonNull Context context) {
            super(context);
            this.mIsScrolledX = false;
            this.mIsScrolledY = false;
            this.mTouchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        @RequiresApi(23)
        public CharSequence getAccessibilityClassName() {
            return OriginalViewPager2.this.mAccessibilityProvider.handlesRvGetAccessibilityClassName() ? OriginalViewPager2.this.mAccessibilityProvider.onRvGetAccessibilityClassName() : super.getAccessibilityClassName();
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setFromIndex(OriginalViewPager2.this.mCurrentItem);
            accessibilityEvent.setToIndex(OriginalViewPager2.this.mCurrentItem);
            OriginalViewPager2.this.mAccessibilityProvider.onRvInitializeAccessibilityEvent(accessibilityEvent);
        }

        @Override // androidx.recyclerview.widget.SpringRecyclerView, androidx.recyclerview.widget.RemixRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (!OriginalViewPager2.this.isUserInputEnabled()) {
                return false;
            }
            if (motionEvent.getActionMasked() == 0) {
                this.initX = motionEvent.getX();
                this.initY = motionEvent.getY();
                this.mIsScrolledX = false;
                this.mIsScrolledY = false;
            } else if (motionEvent.getActionMasked() == 2) {
                float fAbs = Math.abs(motionEvent.getX() - this.initX);
                float fAbs2 = Math.abs(motionEvent.getY() - this.initY);
                int i2 = this.mTouchSlop;
                if (fAbs > i2 && fAbs * 0.5f > fAbs2 && !this.mIsScrolledY) {
                    this.mIsScrolledX = true;
                } else if (fAbs2 > i2) {
                    this.mIsScrolledX = false;
                    this.mIsScrolledY = true;
                }
            } else if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
                this.mIsScrolledX = false;
                this.mIsScrolledY = false;
            }
            if (this.mIsScrolledY || !this.mIsScrolledX) {
                return false;
            }
            MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
            motionEventObtain.setAction(0);
            super.onInterceptTouchEvent(motionEventObtain);
            return true;
        }

        @Override // androidx.recyclerview.widget.SpringRecyclerView, androidx.recyclerview.widget.RemixRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return OriginalViewPager2.this.isUserInputEnabled() && super.onTouchEvent(motionEvent);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface ScrollState {
    }

    public static class SmoothScrollToPosition implements Runnable {
        private final int mPosition;
        private final miuix.recyclerview.widget.RecyclerView mRecyclerView;

        public SmoothScrollToPosition(int i2, miuix.recyclerview.widget.RecyclerView recyclerView) {
            this.mPosition = i2;
            this.mRecyclerView = recyclerView;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mRecyclerView.smoothScrollToPosition(this.mPosition);
        }
    }

    public OriginalViewPager2(@NonNull Context context) {
        super(context);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new OriginalCompositeOnPageChangeCallback(3);
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new DataSetChangeObserver() { // from class: androidx.viewpager2.widget.OriginalViewPager2.1
            @Override // androidx.viewpager2.widget.OriginalViewPager2.DataSetChangeObserver, androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                OriginalViewPager2 originalViewPager2 = OriginalViewPager2.this;
                originalViewPager2.mCurrentItemDirty = true;
                originalViewPager2.mScrollEventAdapter.notifyDataSetChangeHappened();
            }
        };
        this.mPendingCurrentItem = -1;
        this.mSavedItemAnimator = null;
        this.mSavedItemAnimatorPresent = false;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        initialize(context, null);
    }

    private RecyclerView.OnChildAttachStateChangeListener enforceChildFillListener() {
        return new RecyclerView.OnChildAttachStateChangeListener() { // from class: androidx.viewpager2.widget.OriginalViewPager2.4
            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewAttachedToWindow(@NonNull View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) layoutParams).width != -1 || ((ViewGroup.MarginLayoutParams) layoutParams).height != -1) {
                    throw new IllegalStateException("Pages must fill the whole ViewPager2 (use match_parent)");
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewDetachedFromWindow(@NonNull View view) {
            }
        };
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        this.mAccessibilityProvider = sFeatureEnhancedA11yEnabled ? new PageAwareAccessibilityProvider() : new BasicAccessibilityProvider();
        RecyclerViewImpl recyclerViewImpl = new RecyclerViewImpl(context);
        this.mRecyclerView = recyclerViewImpl;
        recyclerViewImpl.setId(ViewCompat.generateViewId());
        this.mRecyclerView.setDescendantFocusability(131072);
        LinearLayoutManagerImpl linearLayoutManagerImpl = new LinearLayoutManagerImpl(context);
        this.mLayoutManager = linearLayoutManagerImpl;
        this.mRecyclerView.setLayoutManager(linearLayoutManagerImpl);
        this.mRecyclerView.setScrollingTouchSlop(1);
        setOrientation(context, attributeSet);
        this.mRecyclerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mRecyclerView.addOnChildAttachStateChangeListener(enforceChildFillListener());
        OriginalScrollEventAdapter originalScrollEventAdapter = new OriginalScrollEventAdapter(this);
        this.mScrollEventAdapter = originalScrollEventAdapter;
        this.mFakeDragger = new OriginalFakeDrag(this, originalScrollEventAdapter, this.mRecyclerView);
        PagerSnapHelperImpl pagerSnapHelperImpl = new PagerSnapHelperImpl();
        this.mPagerSnapHelper = pagerSnapHelperImpl;
        pagerSnapHelperImpl.attachToRecyclerView(this.mRecyclerView);
        this.mRecyclerView.addOnScrollListener(this.mScrollEventAdapter);
        OriginalCompositeOnPageChangeCallback originalCompositeOnPageChangeCallback = new OriginalCompositeOnPageChangeCallback(3);
        this.mPageChangeEventDispatcher = originalCompositeOnPageChangeCallback;
        this.mScrollEventAdapter.setOnPageChangeCallback(originalCompositeOnPageChangeCallback);
        OnPageChangeCallback onPageChangeCallback = new OnPageChangeCallback() { // from class: androidx.viewpager2.widget.OriginalViewPager2.2
            @Override // androidx.viewpager2.widget.OriginalViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i2) {
                if (i2 == 0) {
                    OriginalViewPager2.this.updateCurrentItem();
                }
            }

            @Override // androidx.viewpager2.widget.OriginalViewPager2.OnPageChangeCallback
            public void onPageSelected(int i2) {
                OriginalViewPager2 originalViewPager2 = OriginalViewPager2.this;
                if (originalViewPager2.mCurrentItem != i2) {
                    originalViewPager2.mCurrentItem = i2;
                    originalViewPager2.mAccessibilityProvider.onSetNewCurrentItem();
                }
            }
        };
        OnPageChangeCallback onPageChangeCallback2 = new OnPageChangeCallback() { // from class: androidx.viewpager2.widget.OriginalViewPager2.3
            @Override // androidx.viewpager2.widget.OriginalViewPager2.OnPageChangeCallback
            public void onPageSelected(int i2) {
                OriginalViewPager2.this.clearFocus();
                if (OriginalViewPager2.this.hasFocus()) {
                    OriginalViewPager2.this.mRecyclerView.requestFocus(2);
                }
            }
        };
        this.mPageChangeEventDispatcher.addOnPageChangeCallback(onPageChangeCallback);
        this.mPageChangeEventDispatcher.addOnPageChangeCallback(onPageChangeCallback2);
        this.mAccessibilityProvider.onInitialize(this.mPageChangeEventDispatcher, this.mRecyclerView);
        this.mPageChangeEventDispatcher.addOnPageChangeCallback(this.mExternalPageChangeCallbacks);
        OriginalPageTransformerAdapter originalPageTransformerAdapter = new OriginalPageTransformerAdapter(this.mLayoutManager);
        this.mPageTransformerAdapter = originalPageTransformerAdapter;
        this.mPageChangeEventDispatcher.addOnPageChangeCallback(originalPageTransformerAdapter);
        miuix.recyclerview.widget.RecyclerView recyclerView = this.mRecyclerView;
        attachViewToParent(recyclerView, 0, recyclerView.getLayoutParams());
    }

    private void registerCurrentItemDataSetTracker(@Nullable RecyclerView.Adapter<?> adapter) {
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.mCurrentItemDataSetChangeObserver);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void restorePendingState() {
        RecyclerView.Adapter adapter;
        if (this.mPendingCurrentItem == -1 || (adapter = getAdapter()) == 0) {
            return;
        }
        Parcelable parcelable = this.mPendingAdapterState;
        if (parcelable != null) {
            if (adapter instanceof StatefulAdapter) {
                ((StatefulAdapter) adapter).restoreState(parcelable);
            }
            this.mPendingAdapterState = null;
        }
        int iMax = Math.max(0, Math.min(this.mPendingCurrentItem, adapter.getItemCount() - 1));
        this.mCurrentItem = iMax;
        this.mPendingCurrentItem = -1;
        this.mRecyclerView.scrollToPosition(iMax);
        this.mAccessibilityProvider.onRestorePendingState();
    }

    private void setOrientation(Context context, AttributeSet attributeSet) {
        int[] iArr = miuix.viewpager2.R.styleable.ViewPager2;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        try {
            setOrientation(typedArrayObtainStyledAttributes.getInt(miuix.viewpager2.R.styleable.ViewPager2_android_orientation, 0));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private void unregisterCurrentItemDataSetTracker(@Nullable RecyclerView.Adapter<?> adapter) {
        if (adapter != null) {
            adapter.unregisterAdapterDataObserver(this.mCurrentItemDataSetChangeObserver);
        }
    }

    public void addItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration) {
        this.mRecyclerView.addItemDecoration(itemDecoration);
    }

    public void addSpringStateListener(SpringStateListener springStateListener) {
        this.mRecyclerView.addSpringStateListener(springStateListener);
    }

    public boolean beginFakeDrag() {
        return this.mFakeDragger.beginFakeDrag();
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        return this.mRecyclerView.canScrollHorizontally(i2);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i2) {
        return this.mRecyclerView.canScrollVertically(i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        Parcelable parcelable = sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i2 = ((SavedState) parcelable).mRecyclerViewId;
            sparseArray.put(this.mRecyclerView.getId(), sparseArray.get(i2));
            sparseArray.remove(i2);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        restorePendingState();
    }

    public boolean endFakeDrag() {
        return this.mFakeDragger.endFakeDrag();
    }

    public boolean fakeDragBy(@Px @SuppressLint({"SupportAnnotationUsage"}) float f2) {
        return this.mFakeDragger.fakeDragBy(f2);
    }

    @Override // android.view.ViewGroup, android.view.View
    @RequiresApi(23)
    public CharSequence getAccessibilityClassName() {
        return this.mAccessibilityProvider.handlesGetAccessibilityClassName() ? this.mAccessibilityProvider.onGetAccessibilityClassName() : super.getAccessibilityClassName();
    }

    @Nullable
    public RecyclerView.Adapter getAdapter() {
        return this.mRecyclerView.getAdapter();
    }

    public int getCurrentItem() {
        return this.mCurrentItem;
    }

    @NonNull
    public RecyclerView.ItemDecoration getItemDecorationAt(int i2) {
        return this.mRecyclerView.getItemDecorationAt(i2);
    }

    public int getItemDecorationCount() {
        return this.mRecyclerView.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getOrientation() {
        return this.mLayoutManager.getOrientation();
    }

    public int getPageSize() {
        int height;
        int paddingBottom;
        miuix.recyclerview.widget.RecyclerView recyclerView = this.mRecyclerView;
        if (getOrientation() == 0) {
            height = recyclerView.getWidth() - recyclerView.getPaddingLeft();
            paddingBottom = recyclerView.getPaddingRight();
        } else {
            height = recyclerView.getHeight() - recyclerView.getPaddingTop();
            paddingBottom = recyclerView.getPaddingBottom();
        }
        return height - paddingBottom;
    }

    public int getScrollState() {
        return this.mScrollEventAdapter.getScrollState();
    }

    public float getSpringX() {
        return this.mRecyclerView.getSpringX();
    }

    public float getSpringY() {
        return this.mRecyclerView.getSpringY();
    }

    public void invalidateItemDecorations() {
        this.mRecyclerView.invalidateItemDecorations();
    }

    public boolean isFakeDragging() {
        return this.mFakeDragger.isFakeDragging();
    }

    public boolean isRtl() {
        return this.mLayoutManager.getLayoutDirection() == 1;
    }

    public boolean isUserInputEnabled() {
        return this.mUserInputEnabled;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setCurrentItemInternal(getCurrentItem(), false, true);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        this.mAccessibilityProvider.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int measuredWidth = this.mRecyclerView.getMeasuredWidth();
        int measuredHeight = this.mRecyclerView.getMeasuredHeight();
        this.mTmpContainerRect.left = getPaddingLeft();
        int i6 = i4 - i2;
        this.mTmpContainerRect.right = i6 - getPaddingRight();
        this.mTmpContainerRect.top = getPaddingTop();
        this.mTmpContainerRect.bottom = (i5 - i3) - getPaddingBottom();
        Gravity.apply(8388659, measuredWidth, measuredHeight, this.mTmpContainerRect, this.mTmpChildRect);
        miuix.recyclerview.widget.RecyclerView recyclerView = this.mRecyclerView;
        Rect rect = this.mTmpChildRect;
        recyclerView.layout(rect.left, rect.top, rect.right, rect.bottom);
        if (this.mCurrentItemDirty) {
            updateCurrentItem();
        }
        if (z2 && isRtl()) {
            if (this.savedLastWidth != i6) {
                setCurrentItemInternal(getCurrentItem(), false, true);
            }
            this.savedLastWidth = i6;
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        measureChild(this.mRecyclerView, i2, i3);
        int measuredWidth = this.mRecyclerView.getMeasuredWidth();
        int measuredHeight = this.mRecyclerView.getMeasuredHeight();
        int measuredState = this.mRecyclerView.getMeasuredState();
        int paddingLeft = measuredWidth + getPaddingLeft() + getPaddingRight();
        int paddingTop = measuredHeight + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(ViewGroup.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i2, measuredState), ViewGroup.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i3, measuredState << 16));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mPendingCurrentItem = savedState.mCurrentItem;
        this.mPendingAdapterState = savedState.mAdapterState;
    }

    @Override // android.view.View
    @Nullable
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.mRecyclerViewId = this.mRecyclerView.getId();
        int i2 = this.mPendingCurrentItem;
        if (i2 == -1) {
            i2 = this.mCurrentItem;
        }
        savedState.mCurrentItem = i2;
        Parcelable parcelable = this.mPendingAdapterState;
        if (parcelable != null) {
            savedState.mAdapterState = parcelable;
        } else {
            Object adapter = this.mRecyclerView.getAdapter();
            if (adapter instanceof StatefulAdapter) {
                savedState.mAdapterState = ((StatefulAdapter) adapter).saveState();
            }
        }
        return savedState;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        throw new IllegalStateException(getClass().getSimpleName() + " does not support direct child views");
    }

    @Override // android.view.View
    @RequiresApi(16)
    public boolean performAccessibilityAction(int i2, Bundle bundle) {
        return this.mAccessibilityProvider.handlesPerformAccessibilityAction(i2, bundle) ? this.mAccessibilityProvider.onPerformAccessibilityAction(i2, bundle) : super.performAccessibilityAction(i2, bundle);
    }

    public void registerOnPageChangeCallback(@NonNull OnPageChangeCallback onPageChangeCallback) {
        this.mExternalPageChangeCallbacks.addOnPageChangeCallback(onPageChangeCallback);
    }

    public void removeItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration) {
        this.mRecyclerView.removeItemDecoration(itemDecoration);
    }

    public void removeItemDecorationAt(int i2) {
        this.mRecyclerView.removeItemDecorationAt(i2);
    }

    public void removeSpringStateListener(SpringStateListener springStateListener) {
        this.mRecyclerView.removeSpringStateListener(springStateListener);
    }

    public void requestTransform() {
        if (this.mPageTransformerAdapter.getPageTransformer() == null) {
            return;
        }
        double relativeScrollPosition = this.mScrollEventAdapter.getRelativeScrollPosition();
        int i2 = (int) relativeScrollPosition;
        float f2 = (float) (relativeScrollPosition - ((double) i2));
        this.mPageTransformerAdapter.onPageScrolled(i2, f2, Math.round(getPageSize() * f2));
    }

    public void setAdapter(@Nullable RecyclerView.Adapter adapter) {
        RecyclerView.Adapter adapter2 = this.mRecyclerView.getAdapter();
        this.mAccessibilityProvider.onDetachAdapter(adapter2);
        unregisterCurrentItemDataSetTracker(adapter2);
        this.mRecyclerView.setAdapter(adapter);
        this.mCurrentItem = 0;
        restorePendingState();
        this.mAccessibilityProvider.onAttachAdapter(adapter);
        registerCurrentItemDataSetTracker(adapter);
    }

    public void setCurrentItem(int i2) {
        setCurrentItem(i2, true);
    }

    public void setCurrentItemInternal(int i2, boolean z2, boolean z3) {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter == null) {
            if (this.mPendingCurrentItem != -1) {
                this.mPendingCurrentItem = Math.max(i2, 0);
                return;
            }
            return;
        }
        if (adapter.getItemCount() <= 0) {
            return;
        }
        int iMin = Math.min(Math.max(i2, 0), adapter.getItemCount() - 1);
        if (z3 && iMin == this.mCurrentItem) {
            this.mRecyclerView.scrollToPosition(iMin);
            return;
        }
        if (iMin == this.mCurrentItem && this.mScrollEventAdapter.isIdle()) {
            return;
        }
        int i3 = this.mCurrentItem;
        if (iMin == i3 && z2) {
            return;
        }
        double relativeScrollPosition = i3;
        this.mCurrentItem = iMin;
        this.mAccessibilityProvider.onSetNewCurrentItem();
        if (!this.mScrollEventAdapter.isIdle()) {
            relativeScrollPosition = this.mScrollEventAdapter.getRelativeScrollPosition();
        }
        this.mScrollEventAdapter.notifyProgrammaticScroll(iMin, z2);
        if (!z2) {
            this.mRecyclerView.scrollToPosition(iMin);
            return;
        }
        double d2 = iMin;
        if (Math.abs(d2 - relativeScrollPosition) <= 3.0d) {
            this.mRecyclerView.smoothScrollToPosition(iMin);
            return;
        }
        this.mRecyclerView.scrollToPosition(d2 > relativeScrollPosition ? iMin - 3 : iMin + 3);
        miuix.recyclerview.widget.RecyclerView recyclerView = this.mRecyclerView;
        recyclerView.post(new SmoothScrollToPosition(iMin, recyclerView));
    }

    @Override // android.view.View
    @RequiresApi(17)
    public void setLayoutDirection(int i2) {
        super.setLayoutDirection(i2);
        this.mAccessibilityProvider.onSetLayoutDirection();
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1 && i2 != -1) {
            throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
        }
        this.mOffscreenPageLimit = i2;
        this.mRecyclerView.requestLayout();
    }

    public void setPageTransformer(@Nullable PageTransformer pageTransformer) {
        if (pageTransformer != null) {
            if (!this.mSavedItemAnimatorPresent) {
                this.mSavedItemAnimator = this.mRecyclerView.getItemAnimator();
                this.mSavedItemAnimatorPresent = true;
            }
            this.mRecyclerView.setItemAnimator(null);
        } else if (this.mSavedItemAnimatorPresent) {
            this.mRecyclerView.setItemAnimator(this.mSavedItemAnimator);
            this.mSavedItemAnimator = null;
            this.mSavedItemAnimatorPresent = false;
        }
        if (pageTransformer == this.mPageTransformerAdapter.getPageTransformer()) {
            return;
        }
        this.mPageTransformerAdapter.setPageTransformer(pageTransformer);
        requestTransform();
    }

    public void setSpringEnabled(boolean z2) {
        this.mRecyclerView.setSpringEnabled(z2);
    }

    public void setUserInputEnabled(boolean z2) {
        this.mUserInputEnabled = z2;
        this.mAccessibilityProvider.onSetUserInputEnabled();
    }

    public void snapToPage() {
        View viewFindSnapView = this.mPagerSnapHelper.findSnapView(this.mLayoutManager);
        if (viewFindSnapView == null) {
            return;
        }
        int[] iArrCalculateDistanceToFinalSnap = this.mPagerSnapHelper.calculateDistanceToFinalSnap(this.mLayoutManager, viewFindSnapView);
        int i2 = iArrCalculateDistanceToFinalSnap[0];
        if (i2 == 0 && iArrCalculateDistanceToFinalSnap[1] == 0) {
            return;
        }
        this.mRecyclerView.smoothScrollBy(i2, iArrCalculateDistanceToFinalSnap[1]);
    }

    public void unregisterOnPageChangeCallback(@NonNull OnPageChangeCallback onPageChangeCallback) {
        this.mExternalPageChangeCallbacks.removeOnPageChangeCallback(onPageChangeCallback);
    }

    public void updateCurrentItem() {
        PagerSnapHelper pagerSnapHelper = this.mPagerSnapHelper;
        if (pagerSnapHelper == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        View viewFindSnapView = pagerSnapHelper.findSnapView(this.mLayoutManager);
        if (viewFindSnapView == null) {
            return;
        }
        int position = this.mLayoutManager.getPosition(viewFindSnapView);
        if (position != this.mCurrentItem && getScrollState() == 0) {
            this.mPageChangeEventDispatcher.onPageSelected(position);
        }
        this.mCurrentItemDirty = false;
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.viewpager2.widget.OriginalViewPager2.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return createFromParcel(parcel, (ClassLoader) null);
            }
        };
        Parcelable mAdapterState;
        int mCurrentItem;
        int mRecyclerViewId;

        @RequiresApi(24)
        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            readValues(parcel, classLoader);
        }

        private void readValues(Parcel parcel, ClassLoader classLoader) {
            this.mRecyclerViewId = parcel.readInt();
            this.mCurrentItem = parcel.readInt();
            this.mAdapterState = parcel.readParcelable(classLoader);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.mRecyclerViewId);
            parcel.writeInt(this.mCurrentItem);
            parcel.writeParcelable(this.mAdapterState, i2);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            readValues(parcel, null);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public void addItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration, int i2) {
        this.mRecyclerView.addItemDecoration(itemDecoration, i2);
    }

    public void setCurrentItem(int i2, boolean z2) {
        if (isFakeDragging()) {
            throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
        }
        setCurrentItemInternal(i2, z2, false);
    }

    public void setOrientation(int i2) {
        this.mLayoutManager.setOrientation(i2);
        this.mAccessibilityProvider.onSetOrientation();
    }

    public OriginalViewPager2(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new OriginalCompositeOnPageChangeCallback(3);
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new DataSetChangeObserver() { // from class: androidx.viewpager2.widget.OriginalViewPager2.1
            @Override // androidx.viewpager2.widget.OriginalViewPager2.DataSetChangeObserver, androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                OriginalViewPager2 originalViewPager2 = OriginalViewPager2.this;
                originalViewPager2.mCurrentItemDirty = true;
                originalViewPager2.mScrollEventAdapter.notifyDataSetChangeHappened();
            }
        };
        this.mPendingCurrentItem = -1;
        this.mSavedItemAnimator = null;
        this.mSavedItemAnimatorPresent = false;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        initialize(context, attributeSet);
    }

    public OriginalViewPager2(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new OriginalCompositeOnPageChangeCallback(3);
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new DataSetChangeObserver() { // from class: androidx.viewpager2.widget.OriginalViewPager2.1
            @Override // androidx.viewpager2.widget.OriginalViewPager2.DataSetChangeObserver, androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                OriginalViewPager2 originalViewPager2 = OriginalViewPager2.this;
                originalViewPager2.mCurrentItemDirty = true;
                originalViewPager2.mScrollEventAdapter.notifyDataSetChangeHappened();
            }
        };
        this.mPendingCurrentItem = -1;
        this.mSavedItemAnimator = null;
        this.mSavedItemAnimatorPresent = false;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        initialize(context, attributeSet);
    }

    @RequiresApi(21)
    public OriginalViewPager2(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new OriginalCompositeOnPageChangeCallback(3);
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new DataSetChangeObserver() { // from class: androidx.viewpager2.widget.OriginalViewPager2.1
            @Override // androidx.viewpager2.widget.OriginalViewPager2.DataSetChangeObserver, androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                OriginalViewPager2 originalViewPager2 = OriginalViewPager2.this;
                originalViewPager2.mCurrentItemDirty = true;
                originalViewPager2.mScrollEventAdapter.notifyDataSetChangeHappened();
            }
        };
        this.mPendingCurrentItem = -1;
        this.mSavedItemAnimator = null;
        this.mSavedItemAnimatorPresent = false;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        initialize(context, attributeSet);
    }
}
