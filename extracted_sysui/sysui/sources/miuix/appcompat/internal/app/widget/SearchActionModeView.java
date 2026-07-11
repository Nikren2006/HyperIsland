package miuix.appcompat.internal.app.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miuix.animation.Folme;
import miuix.animation.ITouchStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.utils.EaseManager;
import miuix.appcompat.R;
import miuix.container.ExtraPaddingPolicy;
import miuix.core.util.MiuixUIUtils;
import miuix.core.view.NestedContentInsetObserver;
import miuix.core.view.NestedCoordinatorObserver;
import miuix.internal.util.DeviceHelper;
import miuix.internal.util.ViewUtils;
import miuix.view.ActionModeAnimationListener;
import miuix.view.CompatViewMethod;
import miuix.view.SearchActionMode;
import miuix.view.inputmethod.InputMethodHelper;
import miuix.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes3.dex */
public class SearchActionModeView extends FrameLayout implements Animator.AnimatorListener, ActionModeView, TextWatcher, View.OnClickListener {
    public static final int ANIMATION_DURATION = 400;
    private ActionBarContainer mActionBarContainer;
    private int mActionBarTopMargin;
    private ActionBarView mActionBarView;
    private WeakReference<View> mAnchorParentView;
    private WeakReference<View> mAnchorView;
    private boolean mAnimateToVisible;
    private WeakReference<View> mAnimateView;
    private int mAnimateViewTranslationYLength;
    private int mAnimateViewTranslationYStart;
    private SearchActionMode.AnimatedViewListener mAnimatedViewListener;
    private boolean mAnimationCanceled;
    private List<ActionModeAnimationListener> mAnimationListeners;
    private float mAnimationProgress;
    private ViewUtils.RelativePadding mCancelBtnInitPaddings;
    private int mContentOriginPaddingBottom;
    private int mContentOriginPaddingTop;
    private WeakReference<View> mContentView;
    private ObjectAnimator mCurrentAnimation;
    private FrameLayout mCustomFrameLayout;
    private View mCustomView;
    private View mDimView;
    private int mExtraPadding;
    private boolean mExtraPaddingApplyToAnchorByUser;

    @Nullable
    private ExtraPaddingPolicy mExtraPaddingPolicy;
    private boolean mFirstLayout;
    private boolean mHasSetCustomView;
    private int mHorizontalPaddingDp;
    private boolean mInActionMode;
    private ViewUtils.RelativePadding mInitPaddings;
    private int mInputPaddingRight;
    private int mInputPaddingTop;
    private EditText mInputView;
    private int mLimitTextSizeDp;
    private int[] mLocation;
    private View.OnClickListener mOnBackClickListener;
    private boolean mOriginOverlayMode;
    private float mOriginalAnimateViewTranslationY;
    private int mOriginalPaddingTop;
    private WeakReference<View> mOverlayView;
    private int mParentLocationY;
    private int mPendingInsetTop;
    private WeakReference<View> mResultView;
    private int mResultViewOriginMarginBottom;
    private int mResultViewOriginMarginTop;
    private int mResultViewOriginPaddingBottom;
    private int mResultViewOriginPaddingTop;
    private boolean mResultViewSet;
    private ViewGroup mSearchContainer;
    private int mSearchViewHeight;
    private ActionBarContainer mSplitActionBarContainer;
    private TextView mTextCancel;
    private int mTextLengthBeforeChanged;

    public class ActionBarAnimationProcessor implements ActionModeAnimationListener {
        public ActionBarAnimationProcessor() {
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onStart(boolean z2) {
            if (z2) {
                SearchActionModeView.this.mActionBarContainer.setVisibility(SearchActionModeView.this.mOriginOverlayMode ? 4 : 8);
                return;
            }
            View tabContainer = SearchActionModeView.this.mActionBarContainer.getTabContainer();
            if (tabContainer != null) {
                tabContainer.setVisibility(0);
            }
            SearchActionModeView.this.mActionBarContainer.setVisibility(0);
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onStop(boolean z2) {
            View tabContainer;
            if (!z2 || (tabContainer = SearchActionModeView.this.mActionBarContainer.getTabContainer()) == null) {
                return;
            }
            tabContainer.setVisibility(8);
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onUpdate(boolean z2, float f2) {
        }
    }

    public class ContentViewAnimationProcessor implements ActionModeAnimationListener {
        private ActionBarView mAnimationActionBarView;
        private View mAnimationAnchorView;
        private View mAnimationAnimateView;
        private NestedCoordinatorObserver mAnimationNestedCoordOb;
        private View mAnimationResultView;
        private int mContentViewTranslationYBeforeMode;
        private int mContentViewTranslationYLength;
        private boolean mIsActionBarNestedScrolledBeforeMode;
        private int mModeViewTranslationYBeforeMode;
        private int mModeViewTranslationYLength;
        private int mNestedCoordObTranslationYLength;
        private int mTmpAnchorAccessibilityMode = 0;
        private int mTmpAnimAccessibilityMode = 0;
        private int mTmpResultAccessibilityMode = 0;

        public ContentViewAnimationProcessor() {
        }

        private void updateAnimValues() {
            NestedCoordinatorObserver nestedCoordinatorObserver = this.mAnimationNestedCoordOb;
            if (nestedCoordinatorObserver != null) {
                this.mNestedCoordObTranslationYLength = nestedCoordinatorObserver.getNestedScrollableValue();
            }
            ActionBarView actionBarView = this.mAnimationActionBarView;
            if (actionBarView == null) {
                this.mAnimationAnchorView.getLocationInWindow(SearchActionModeView.this.mLocation);
                int i2 = SearchActionModeView.this.mLocation[1];
                this.mModeViewTranslationYBeforeMode = i2;
                int i3 = i2 - SearchActionModeView.this.mParentLocationY;
                this.mModeViewTranslationYBeforeMode = i3;
                int i4 = -i3;
                this.mModeViewTranslationYLength = i4;
                this.mContentViewTranslationYLength = i4;
                return;
            }
            int top = actionBarView.getTop();
            int collapsedHeight = this.mAnimationActionBarView.getCollapsedHeight();
            int expandedHeight = this.mAnimationActionBarView.getExpandedHeight();
            if (this.mAnimationActionBarView.getExpandState() == 0) {
                top += collapsedHeight;
            } else if (this.mAnimationActionBarView.getExpandState() == 1) {
                top += expandedHeight;
            }
            this.mModeViewTranslationYBeforeMode = top;
            int i5 = -top;
            this.mModeViewTranslationYLength = i5;
            this.mContentViewTranslationYLength = i5 + this.mAnimationActionBarView.getTop();
            if (this.mAnimationNestedCoordOb == null || this.mIsActionBarNestedScrolledBeforeMode || !SearchActionModeView.this.mOriginOverlayMode) {
                return;
            }
            this.mNestedCoordObTranslationYLength += -(expandedHeight - collapsedHeight);
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onStart(boolean z2) {
            ActionBarView actionBarView;
            this.mAnimationActionBarView = SearchActionModeView.this.getActionBarView();
            this.mAnimationAnchorView = SearchActionModeView.this.mAnchorView != null ? (View) SearchActionModeView.this.mAnchorView.get() : null;
            this.mAnimationAnimateView = SearchActionModeView.this.mAnimateView != null ? (View) SearchActionModeView.this.mAnimateView.get() : null;
            this.mAnimationResultView = SearchActionModeView.this.mResultView != null ? (View) SearchActionModeView.this.mResultView.get() : null;
            KeyEvent.Callback callback = SearchActionModeView.this.mAnchorParentView != null ? (View) SearchActionModeView.this.mAnchorParentView.get() : null;
            if (callback instanceof NestedCoordinatorObserver) {
                this.mAnimationNestedCoordOb = (NestedCoordinatorObserver) callback;
            }
            if (SearchActionModeView.this.mParentLocationY == Integer.MAX_VALUE) {
                ((View) SearchActionModeView.this.getParent()).getLocationInWindow(SearchActionModeView.this.mLocation);
                SearchActionModeView searchActionModeView = SearchActionModeView.this;
                searchActionModeView.mParentLocationY = searchActionModeView.mLocation[1];
            }
            View view = this.mAnimationAnchorView;
            if (view != null) {
                view.setAlpha(0.0f);
            }
            if (z2 && (actionBarView = this.mAnimationActionBarView) != null) {
                this.mIsActionBarNestedScrolledBeforeMode = actionBarView.getExpandState() == 0;
            }
            if (this.mAnimationAnchorView != null) {
                updateAnimValues();
            }
            if (!z2) {
                if (SearchActionModeView.this.mAnimatedViewListener != null) {
                    SearchActionModeView.this.mAnimatedViewListener.onInSearchMode(false);
                }
                View view2 = this.mAnimationAnchorView;
                if (view2 != null) {
                    view2.setImportantForAccessibility(this.mTmpAnchorAccessibilityMode);
                }
                View view3 = this.mAnimationAnimateView;
                if (view3 != null) {
                    view3.setImportantForAccessibility(this.mTmpAnimAccessibilityMode);
                }
                View view4 = this.mAnimationResultView;
                if (view4 != null) {
                    view4.setImportantForAccessibility(this.mTmpResultAccessibilityMode);
                }
                if (SearchActionModeView.this.mOriginOverlayMode || this.mAnimationNestedCoordOb == null) {
                    return;
                }
                SearchActionModeView.this.setContentViewTranslation(r4.getViewHeight() + SearchActionModeView.this.mPendingInsetTop);
                this.mAnimationNestedCoordOb.updateCoordinatorHeightGapInfo(0, 0);
                SearchActionModeView.this.setContentViewPadding(0, 0);
                return;
            }
            View view5 = this.mAnimationAnchorView;
            if (view5 != null) {
                this.mTmpAnchorAccessibilityMode = view5.getImportantForAccessibility();
                this.mAnimationAnchorView.setImportantForAccessibility(4);
            }
            View view6 = this.mAnimationAnimateView;
            if (view6 != null) {
                this.mTmpAnimAccessibilityMode = view6.getImportantForAccessibility();
                this.mAnimationAnimateView.setImportantForAccessibility(4);
            }
            View view7 = this.mAnimationResultView;
            if (view7 != null) {
                this.mTmpResultAccessibilityMode = view7.getImportantForAccessibility();
                this.mAnimationResultView.setImportantForAccessibility(1);
            }
            SearchActionModeView.this.setTranslationY(this.mModeViewTranslationYBeforeMode);
            if (SearchActionModeView.this.mOriginOverlayMode) {
                return;
            }
            int i2 = this.mModeViewTranslationYBeforeMode - SearchActionModeView.this.mPendingInsetTop;
            this.mContentViewTranslationYBeforeMode = i2;
            SearchActionModeView.this.setContentViewTranslation(i2);
            SearchActionModeView searchActionModeView2 = SearchActionModeView.this;
            searchActionModeView2.setContentViewPadding(searchActionModeView2.mPendingInsetTop, 0);
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onStop(boolean z2) {
            if (z2) {
                if (SearchActionModeView.this.mAnimatedViewListener != null) {
                    SearchActionModeView.this.mAnimatedViewListener.onUpdateOffsetY(this.mNestedCoordObTranslationYLength);
                    SearchActionModeView.this.mAnimatedViewListener.onInSearchMode(true);
                }
                if (!SearchActionModeView.this.mOriginOverlayMode) {
                    SearchActionModeView.this.setContentViewTranslation(0.0f);
                    NestedCoordinatorObserver nestedCoordinatorObserver = this.mAnimationNestedCoordOb;
                    if (nestedCoordinatorObserver != null) {
                        nestedCoordinatorObserver.updateCoordinatorHeightGapInfo(this.mNestedCoordObTranslationYLength, 0);
                        SearchActionModeView searchActionModeView = SearchActionModeView.this;
                        searchActionModeView.setContentViewPadding(searchActionModeView.mPendingInsetTop + SearchActionModeView.this.getViewHeight(), 0);
                    } else {
                        SearchActionModeView searchActionModeView2 = SearchActionModeView.this;
                        searchActionModeView2.setContentViewPadding(searchActionModeView2.mPendingInsetTop, 0);
                    }
                }
                if (this.mAnimationResultView != null && SearchActionModeView.this.mOriginOverlayMode) {
                    View view = this.mAnimationResultView;
                    view.setPadding(view.getPaddingLeft(), Math.max(SearchActionModeView.this.getViewHeight() + SearchActionModeView.this.mPendingInsetTop, SearchActionModeView.this.mResultViewOriginPaddingTop), this.mAnimationResultView.getPaddingRight(), SearchActionModeView.this.mResultViewOriginPaddingBottom);
                }
            } else {
                if (SearchActionModeView.this.mAnimatedViewListener != null) {
                    SearchActionModeView.this.mAnimatedViewListener.onUpdateOffsetY(0);
                }
                if (!SearchActionModeView.this.mOriginOverlayMode) {
                    NestedCoordinatorObserver nestedCoordinatorObserver2 = this.mAnimationNestedCoordOb;
                    if (nestedCoordinatorObserver2 != null) {
                        nestedCoordinatorObserver2.updateCoordinatorHeightGapInfo(0, 0);
                    }
                    SearchActionModeView.this.setContentViewTranslation(0.0f);
                    SearchActionModeView searchActionModeView3 = SearchActionModeView.this;
                    searchActionModeView3.setContentViewPadding(searchActionModeView3.mContentOriginPaddingTop, SearchActionModeView.this.mContentOriginPaddingBottom);
                }
                if (this.mAnimationResultView != null && SearchActionModeView.this.mOriginOverlayMode) {
                    View view2 = this.mAnimationResultView;
                    view2.setPadding(view2.getPaddingLeft(), SearchActionModeView.this.mResultViewOriginPaddingTop, this.mAnimationResultView.getPaddingRight(), SearchActionModeView.this.mResultViewOriginPaddingBottom);
                }
            }
            SearchActionModeView.this.setTranslationY(this.mModeViewTranslationYBeforeMode + this.mModeViewTranslationYLength);
            SearchActionModeView.this.mDimView.setTranslationY(SearchActionModeView.this.getTranslationY() + SearchActionModeView.this.getHeight());
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onUpdate(boolean z2, float f2) {
            if (!z2) {
                f2 = 1.0f - f2;
            }
            SearchActionModeView.this.setTranslationY(this.mModeViewTranslationYBeforeMode + (this.mModeViewTranslationYLength * f2));
            SearchActionModeView.this.mDimView.setTranslationY(SearchActionModeView.this.getTranslationY() + SearchActionModeView.this.getHeight());
            int i2 = this.mNestedCoordObTranslationYLength;
            int iMax = Math.max(i2, Math.round(i2 * f2));
            if (!SearchActionModeView.this.mOriginOverlayMode) {
                if (z2) {
                    if (this.mAnimationNestedCoordOb != null) {
                        SearchActionModeView.this.setContentViewTranslation(((1.0f - f2) * this.mContentViewTranslationYBeforeMode) + (f2 * SearchActionModeView.this.getViewHeight()));
                        this.mAnimationNestedCoordOb.updateCoordinatorHeightGapInfo(iMax, 0);
                    } else {
                        SearchActionModeView searchActionModeView = SearchActionModeView.this;
                        searchActionModeView.setContentViewTranslation(searchActionModeView.getTranslationY() - ((1.0f - f2) * SearchActionModeView.this.mPendingInsetTop));
                    }
                } else if (this.mAnimationNestedCoordOb != null) {
                    SearchActionModeView.this.setContentViewTranslation((int) (SearchActionModeView.this.getViewHeight() + SearchActionModeView.this.mPendingInsetTop + ((1.0f - f2) * ((this.mModeViewTranslationYBeforeMode - SearchActionModeView.this.getViewHeight()) - SearchActionModeView.this.mPendingInsetTop))));
                    this.mAnimationNestedCoordOb.updateCoordinatorHeightGapInfo(iMax, 0);
                } else {
                    SearchActionModeView searchActionModeView2 = SearchActionModeView.this;
                    searchActionModeView2.setContentViewTranslation(searchActionModeView2.getTranslationY() - ((1.0f - f2) * SearchActionModeView.this.mPendingInsetTop));
                }
            }
            if (SearchActionModeView.this.mAnimatedViewListener != null) {
                SearchActionModeView.this.mAnimatedViewListener.onUpdateOffsetY(iMax);
            }
        }
    }

    public class DimViewAnimationProcessor implements ActionModeAnimationListener {
        public DimViewAnimationProcessor() {
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onStart(boolean z2) {
            if (z2) {
                SearchActionModeView.this.mDimView.setOnClickListener(SearchActionModeView.this);
                SearchActionModeView.this.mDimView.setVisibility(0);
                SearchActionModeView.this.mDimView.setAlpha(0.0f);
            }
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onStop(boolean z2) {
            if (z2) {
                if (SearchActionModeView.this.mInputView.getText().length() > 0) {
                    SearchActionModeView.this.mDimView.setVisibility(8);
                }
            } else {
                SearchActionModeView.this.mDimView.setVisibility(8);
                SearchActionModeView.this.mDimView.setAlpha(1.0f);
                SearchActionModeView.this.mDimView.setTranslationY(0.0f);
            }
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onUpdate(boolean z2, float f2) {
            if (!z2) {
                f2 = 1.0f - f2;
            }
            SearchActionModeView.this.mDimView.setAlpha(f2);
        }
    }

    public class SearchViewAnimationProcessor implements ActionModeAnimationListener {
        public SearchViewAnimationProcessor() {
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onStart(boolean z2) {
            updateCancelView(z2 ? 0.0f : 1.0f, SearchActionModeView.this.mInputPaddingRight);
            if (z2) {
                SearchActionModeView.this.mInputView.getText().clear();
                SearchActionModeView.this.mInputView.addTextChangedListener(SearchActionModeView.this);
            } else {
                SearchActionModeView.this.mInputView.removeTextChangedListener(SearchActionModeView.this);
                SearchActionModeView.this.mInputView.getText().clear();
            }
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onStop(boolean z2) {
            if (!z2) {
                SearchActionModeView.this.mInputView.removeTextChangedListener(SearchActionModeView.this);
                return;
            }
            int i2 = SearchActionModeView.this.mPendingInsetTop;
            SearchActionModeView searchActionModeView = SearchActionModeView.this;
            searchActionModeView.setPaddingRelative(searchActionModeView.getPaddingStart(), SearchActionModeView.this.mOriginalPaddingTop + i2, SearchActionModeView.this.getPaddingEnd(), SearchActionModeView.this.getPaddingBottom());
            ViewGroup.LayoutParams layoutParams = SearchActionModeView.this.getLayoutParams();
            layoutParams.height = SearchActionModeView.this.mSearchViewHeight + i2;
            updateCancelView(1.0f, SearchActionModeView.this.mInputPaddingRight);
            SearchActionModeView.this.setLayoutParams(layoutParams);
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onUpdate(boolean z2, float f2) {
            if (!z2) {
                f2 = 1.0f - f2;
            }
            int iRound = Math.round(SearchActionModeView.this.mPendingInsetTop * f2);
            SearchActionModeView searchActionModeView = SearchActionModeView.this;
            searchActionModeView.setPaddingRelative(searchActionModeView.getPaddingStart(), SearchActionModeView.this.mOriginalPaddingTop + iRound, SearchActionModeView.this.getPaddingEnd(), SearchActionModeView.this.getPaddingBottom());
            ViewGroup.LayoutParams layoutParams = SearchActionModeView.this.getLayoutParams();
            layoutParams.height = SearchActionModeView.this.mSearchViewHeight + iRound;
            updateCancelView(f2, SearchActionModeView.this.mInputPaddingRight);
            SearchActionModeView.this.setLayoutParams(layoutParams);
        }

        public void updateCancelView(float f2, int i2) {
            float f3 = 1.0f - f2;
            if (ViewUtils.isLayoutRtl(SearchActionModeView.this)) {
                f3 = f2 - 1.0f;
            }
            int measuredWidth = SearchActionModeView.this.mTextCancel.getMeasuredWidth();
            if (SearchActionModeView.this.mTextCancel.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) SearchActionModeView.this.mTextCancel.getLayoutParams();
                measuredWidth += marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
            }
            SearchActionModeView.this.mTextCancel.setTranslationX(measuredWidth * f3);
            if (SearchActionModeView.this.mSearchContainer.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) SearchActionModeView.this.mSearchContainer.getLayoutParams();
                marginLayoutParams2.setMarginEnd(Math.max(SearchActionModeView.this.getPaddingStart(), (int) (((measuredWidth - i2) * f2) + i2)));
                SearchActionModeView.this.mSearchContainer.setLayoutParams(marginLayoutParams2);
            }
        }
    }

    public class SplitActionBarAnimationProcessor implements ActionModeAnimationListener {
        public SplitActionBarAnimationProcessor() {
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onStart(boolean z2) {
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onStop(boolean z2) {
        }

        @Override // miuix.view.ActionModeAnimationListener
        public void onUpdate(boolean z2, float f2) {
            if (!z2) {
                f2 = 1.0f - f2;
            }
            ActionBarContainer splitActionBarContainer = SearchActionModeView.this.getSplitActionBarContainer();
            if (splitActionBarContainer != null) {
                splitActionBarContainer.setTranslationY(f2 * splitActionBarContainer.getHeight());
            }
        }
    }

    public SearchActionModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mInActionMode = false;
        this.mInitPaddings = null;
        this.mCancelBtnInitPaddings = null;
        this.mLocation = new int[2];
        this.mFirstLayout = true;
        this.mPendingInsetTop = -1;
        this.mParentLocationY = Integer.MAX_VALUE;
        setAlpha(0.0f);
        this.mSearchViewHeight = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_search_view_default_height);
        this.mInputPaddingTop = context.getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_search_mode_bg_padding_top);
        Resources resources = context.getResources();
        int i2 = R.dimen.miuix_appcompat_search_mode_bg_padding;
        this.mInputPaddingRight = resources.getDimensionPixelOffset(i2);
        this.mHorizontalPaddingDp = MiuixUIUtils.getDefDimen(context, i2);
        this.mLimitTextSizeDp = MiuixUIUtils.isTallFontLang(getContext()) ? 16 : 27;
        this.mExtraPadding = 0;
        this.mExtraPaddingApplyToAnchorByUser = false;
    }

    private View getContentView() {
        WeakReference<View> weakReference = this.mContentView;
        if (weakReference != null && weakReference.get() != null) {
            return this.mContentView.get();
        }
        WeakReference<View> weakReference2 = this.mOverlayView;
        ViewGroup viewGroup = weakReference2 != null ? (ViewGroup) weakReference2.get() : null;
        if (viewGroup == null) {
            return null;
        }
        View viewFindViewById = viewGroup.findViewById(android.R.id.content);
        this.mContentView = new WeakReference<>(viewFindViewById);
        return viewFindViewById;
    }

    private boolean isHostActivityDestroyed() {
        Context baseContext = getContext() instanceof ContextThemeWrapper ? ((ContextThemeWrapper) getContext()).getBaseContext() : null;
        return (baseContext instanceof Activity) && ((Activity) baseContext).isDestroyed();
    }

    private void resetLocationY() {
        this.mParentLocationY = Integer.MAX_VALUE;
    }

    private void resetTextSize(TextView textView, TextView textView2) {
        if (textView == null || textView2 == null) {
            return;
        }
        Context context = textView.getContext();
        float dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_search_edit_text_size);
        float f2 = context.getResources().getDisplayMetrics().density;
        float f3 = dimensionPixelSize / f2;
        int i2 = this.mLimitTextSizeDp;
        if (f3 > i2) {
            textView.setTextSize(1, i2);
        }
        float dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_search_action_mode_cancel_text_size) / f2;
        int i3 = this.mLimitTextSizeDp;
        if (dimensionPixelSize2 > i3) {
            textView2.setTextSize(1, i3);
        }
    }

    private boolean shouldAnimateContent() {
        return this.mAnchorView != null;
    }

    private void updateExtraPadding(float f2) {
        WeakReference<View> weakReference = this.mOverlayView;
        ActionBarOverlayLayout actionBarOverlayLayout = weakReference != null ? (ActionBarOverlayLayout) weakReference.get() : null;
        boolean zIsExtraPaddingApplyToContentEnable = actionBarOverlayLayout != null ? actionBarOverlayLayout.isExtraPaddingApplyToContentEnable() : false;
        ExtraPaddingPolicy extraPaddingPolicy = this.mExtraPaddingPolicy;
        if (extraPaddingPolicy != null && extraPaddingPolicy.isEnable() && (zIsExtraPaddingApplyToContentEnable || this.mExtraPaddingApplyToAnchorByUser)) {
            this.mExtraPadding = (int) (this.mExtraPaddingPolicy.getExtraPaddingDp() * f2);
        } else {
            this.mExtraPadding = 0;
        }
    }

    private void updateOnPaddingTopChanged() {
        setPaddingRelative(getPaddingStart(), this.mOriginalPaddingTop + this.mPendingInsetTop, getPaddingEnd(), getPaddingBottom());
        getLayoutParams().height = this.mSearchViewHeight + this.mPendingInsetTop;
    }

    private void updateResultViewMargin(boolean z2) {
        if (z2) {
            WeakReference<View> weakReference = this.mResultView;
            View view = weakReference != null ? weakReference.get() : null;
            WeakReference<View> weakReference2 = this.mAnchorView;
            View view2 = weakReference2 != null ? weakReference2.get() : null;
            if ((view2 == null || view == null || view2.getParent() == view.getParent()) && view != null && (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) && !this.mOriginOverlayMode) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                marginLayoutParams.topMargin = getViewHeight();
                marginLayoutParams.bottomMargin = 0;
                view.setLayoutParams(marginLayoutParams);
                view.requestLayout();
            }
        }
    }

    private void updateViewPadding(int i2, float f2) {
        setPaddingRelative(((int) (this.mHorizontalPaddingDp * f2)) + i2, getPaddingTop(), getPaddingEnd(), getPaddingBottom());
        TextView textView = this.mTextCancel;
        ViewUtils.RelativePadding relativePadding = this.mCancelBtnInitPaddings;
        textView.setPaddingRelative(relativePadding.start, relativePadding.top, relativePadding.end, relativePadding.bottom);
        int measuredWidth = this.mTextCancel.getMeasuredWidth();
        if (this.mTextCancel.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mTextCancel.getLayoutParams();
            marginLayoutParams.setMarginEnd(getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_search_action_mode_cancel_text_margin_end) + i2);
            this.mTextCancel.setLayoutParams(marginLayoutParams);
            measuredWidth += marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
        }
        if (this.mSearchContainer.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mSearchContainer.getLayoutParams();
            marginLayoutParams2.setMarginEnd(Math.max(getPaddingStart(), measuredWidth));
            this.mSearchContainer.setLayoutParams(marginLayoutParams2);
        }
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void addAnimationListener(ActionModeAnimationListener actionModeAnimationListener) {
        if (actionModeAnimationListener == null) {
            return;
        }
        if (this.mAnimationListeners == null) {
            this.mAnimationListeners = new ArrayList();
        }
        if (this.mAnimationListeners.contains(actionModeAnimationListener)) {
            return;
        }
        this.mAnimationListeners.add(actionModeAnimationListener);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        View view;
        if ((editable == null ? 0 : editable.length()) == 0) {
            View view2 = this.mDimView;
            if (view2 != null) {
                view2.setVisibility(0);
            }
            InputMethodHelper.getInstance(getContext()).showKeyBoard(this.mInputView);
            return;
        }
        if (this.mTextLengthBeforeChanged != 0 || (view = this.mDimView) == null) {
            return;
        }
        view.setVisibility(8);
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void animateToVisibility(boolean z2) {
        pollViews();
        float f2 = getResources().getDisplayMetrics().density;
        updateExtraPadding(f2);
        updateViewPadding(this.mExtraPadding, f2);
        this.mAnimateToVisible = z2;
        this.mCurrentAnimation = makeAnimation();
        if (z2) {
            createAnimationListeners();
            WeakReference<View> weakReference = this.mOverlayView;
            ActionBarOverlayLayout actionBarOverlayLayout = weakReference != null ? (ActionBarOverlayLayout) weakReference.get() : null;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setOverlayMode(true);
            }
        }
        if (isHostActivityDestroyed()) {
            finishAnimation();
            ActionBarView actionBarView = this.mActionBarView;
            if (actionBarView != null) {
                actionBarView.setLifecycleOwner(null);
                this.mActionBarView = null;
            }
            this.mActionBarContainer = null;
            this.mSplitActionBarContainer = null;
        } else {
            notifyAnimationStart(z2);
            this.mCurrentAnimation.start();
        }
        if (!this.mAnimateToVisible) {
            this.mInputView.clearFocus();
            ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mInputView.getWindowToken(), 0);
        } else {
            this.mInputView.setFocusable(true);
            this.mInputView.setFocusableInTouchMode(true);
            InputMethodHelper.getInstance(getContext()).showKeyBoard(this.mInputView);
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        this.mTextLengthBeforeChanged = charSequence == null ? 0 : charSequence.length();
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void closeMode() {
        this.mInputView.setFocusable(false);
        this.mInputView.setFocusableInTouchMode(false);
        ObjectAnimator objectAnimator = this.mCurrentAnimation;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        FrameLayout frameLayout = this.mCustomFrameLayout;
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
    }

    public void createAnimationListeners() {
        if (this.mAnimationListeners == null) {
            this.mAnimationListeners = new ArrayList();
        }
        this.mAnimationListeners.add(new SearchViewAnimationProcessor());
        if (shouldAnimateContent()) {
            this.mAnimationListeners.add(new ContentViewAnimationProcessor());
            this.mAnimationListeners.add(new ActionBarAnimationProcessor());
            this.mAnimationListeners.add(new SplitActionBarAnimationProcessor());
        }
        if (getDimView() != null) {
            this.mAnimationListeners.add(new DimViewAnimationProcessor());
        }
    }

    public void finishAnimation() {
        ObjectAnimator objectAnimator = this.mCurrentAnimation;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
            this.mCurrentAnimation.cancel();
            this.mCurrentAnimation.setTarget(null);
            this.mCurrentAnimation = null;
        }
    }

    public ActionBarContainer getActionBarContainer() {
        if (this.mActionBarContainer == null) {
            WeakReference<View> weakReference = this.mOverlayView;
            ViewGroup viewGroup = weakReference != null ? (ViewGroup) weakReference.get() : null;
            if (viewGroup != null) {
                int i2 = 0;
                while (true) {
                    if (i2 >= viewGroup.getChildCount()) {
                        break;
                    }
                    View childAt = viewGroup.getChildAt(i2);
                    if (childAt.getId() == R.id.action_bar_container && (childAt instanceof ActionBarContainer)) {
                        this.mActionBarContainer = (ActionBarContainer) childAt;
                        break;
                    }
                    i2++;
                }
            }
            ActionBarContainer actionBarContainer = this.mActionBarContainer;
            if (actionBarContainer != null) {
                int i3 = ((ViewGroup.MarginLayoutParams) actionBarContainer.getLayoutParams()).topMargin;
                this.mActionBarTopMargin = i3;
                if (i3 > 0) {
                    setPaddingRelative(getPaddingStart(), this.mOriginalPaddingTop + this.mPendingInsetTop + this.mActionBarTopMargin, getPaddingEnd(), getPaddingBottom());
                }
            }
        }
        return this.mActionBarContainer;
    }

    public ActionBarView getActionBarView() {
        if (this.mActionBarView == null) {
            WeakReference<View> weakReference = this.mOverlayView;
            ViewGroup viewGroup = weakReference != null ? (ViewGroup) weakReference.get() : null;
            if (viewGroup != null) {
                this.mActionBarView = (ActionBarView) viewGroup.findViewById(R.id.action_bar);
            }
        }
        return this.mActionBarView;
    }

    public float getAnimationProgress() {
        return this.mAnimationProgress;
    }

    public View getCustomView() {
        return this.mCustomView;
    }

    public View getDimView() {
        if (this.mDimView == null) {
            WeakReference<View> weakReference = this.mOverlayView;
            ViewStub viewStub = null;
            ViewGroup viewGroup = weakReference != null ? (ViewGroup) weakReference.get() : null;
            if (viewGroup != null) {
                int childCount = viewGroup.getChildCount() - 1;
                while (true) {
                    if (childCount < 0) {
                        break;
                    }
                    if (viewGroup.getChildAt(childCount).getId() == R.id.search_mask_vs) {
                        viewStub = (ViewStub) viewGroup.getChildAt(childCount);
                        break;
                    }
                    childCount--;
                }
                if (viewStub != null) {
                    this.mDimView = viewStub.inflate();
                } else {
                    this.mDimView = viewGroup.findViewById(R.id.search_mask);
                }
            }
        }
        FrameLayout frameLayout = this.mCustomFrameLayout;
        if (frameLayout != null) {
            frameLayout.setVisibility(0);
        }
        return this.mDimView;
    }

    public EditText getSearchInput() {
        return this.mInputView;
    }

    public ActionBarContainer getSplitActionBarContainer() {
        if (this.mSplitActionBarContainer == null) {
            WeakReference<View> weakReference = this.mOverlayView;
            ViewGroup viewGroup = weakReference != null ? (ViewGroup) weakReference.get() : null;
            if (viewGroup != null) {
                int i2 = 0;
                while (true) {
                    if (i2 >= viewGroup.getChildCount()) {
                        break;
                    }
                    View childAt = viewGroup.getChildAt(i2);
                    if (childAt.getId() == R.id.split_action_bar && (childAt instanceof ActionBarContainer)) {
                        this.mSplitActionBarContainer = (ActionBarContainer) childAt;
                        break;
                    }
                    i2++;
                }
            }
        }
        return this.mSplitActionBarContainer;
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public int getViewHeight() {
        return this.mSearchViewHeight;
    }

    public ViewPager getViewPager() {
        WeakReference<View> weakReference = this.mOverlayView;
        ActionBarOverlayLayout actionBarOverlayLayout = weakReference != null ? (ActionBarOverlayLayout) weakReference.get() : null;
        if (actionBarOverlayLayout == null || !((ActionBarImpl) actionBarOverlayLayout.getActionBar()).isFragmentViewPagerMode()) {
            return null;
        }
        return (ViewPager) actionBarOverlayLayout.findViewById(R.id.view_pager);
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void initForMode(ActionMode actionMode) {
        this.mInActionMode = true;
        updateResultViewMargin(true);
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void killMode() {
        finishAnimation();
        this.mInActionMode = false;
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this);
        }
        this.mActionBarContainer = null;
        this.mActionBarView = null;
        List<ActionModeAnimationListener> list = this.mAnimationListeners;
        if (list != null) {
            list.clear();
            this.mAnimationListeners = null;
        }
        if (this.mAnimatedViewListener != null) {
            this.mAnimatedViewListener = null;
        }
        this.mSplitActionBarContainer = null;
    }

    public ObjectAnimator makeAnimation() {
        ObjectAnimator objectAnimator = this.mCurrentAnimation;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
            this.mCurrentAnimation.cancel();
            this.mCurrentAnimation.setTarget(null);
            this.mCurrentAnimation = null;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "AnimationProgress", 0.0f, 1.0f);
        objectAnimatorOfFloat.addListener(this);
        objectAnimatorOfFloat.setDuration(DeviceHelper.isFeatureWholeAnim() ? 400L : 0L);
        objectAnimatorOfFloat.setInterpolator(obtainInterpolator());
        return objectAnimatorOfFloat;
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void notifyAnimationEnd(boolean z2) {
        List<ActionModeAnimationListener> list = this.mAnimationListeners;
        if (list == null) {
            return;
        }
        Iterator<ActionModeAnimationListener> it = list.iterator();
        while (it.hasNext()) {
            it.next().onStop(z2);
        }
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void notifyAnimationStart(boolean z2) {
        List<ActionModeAnimationListener> list = this.mAnimationListeners;
        if (list == null) {
            return;
        }
        Iterator<ActionModeAnimationListener> it = list.iterator();
        while (it.hasNext()) {
            it.next().onStart(z2);
        }
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void notifyAnimationUpdate(boolean z2, float f2) {
        List<ActionModeAnimationListener> list = this.mAnimationListeners;
        if (list == null) {
            return;
        }
        Iterator<ActionModeAnimationListener> it = list.iterator();
        while (it.hasNext()) {
            it.next().onUpdate(z2, f2);
        }
    }

    public TimeInterpolator obtainInterpolator() {
        return EaseManager.getInterpolator(0, 0.98f, 0.75f);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.mAnimationCanceled = true;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        if (this.mAnimationCanceled) {
            return;
        }
        this.mCurrentAnimation = null;
        notifyAnimationEnd(this.mAnimateToVisible);
        if (this.mAnimateToVisible) {
            return;
        }
        WeakReference<View> weakReference = this.mOverlayView;
        ActionBarOverlayLayout actionBarOverlayLayout = weakReference != null ? (ActionBarOverlayLayout) weakReference.get() : null;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setOverlayMode(this.mOriginOverlayMode);
            actionBarOverlayLayout.requestDispatchContentInset();
        }
        WeakReference<View> weakReference2 = this.mAnchorView;
        View view = weakReference2 != null ? weakReference2.get() : null;
        if (view != null) {
            view.setAlpha(1.0f);
        }
        setAlpha(0.0f);
        killMode();
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.mAnimationCanceled = false;
        if (this.mAnimateToVisible) {
            setAlpha(1.0f);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mOnBackClickListener != null) {
            if (view.getId() == R.id.search_text_cancel || view.getId() == R.id.search_mask) {
                this.mOnBackClickListener.onClick(view);
            }
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        resetLocationY();
        this.mFirstLayout = true;
        resetTextSize(this.mInputView, this.mTextCancel);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        finishAnimation();
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.setLifecycleOwner(null);
            this.mActionBarView = null;
        }
        this.mActionBarContainer = null;
        this.mSplitActionBarContainer = null;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mInitPaddings = new ViewUtils.RelativePadding(this);
        Drawable background = getBackground();
        if (background != null) {
            Rect rect = new Rect();
            background.getPadding(rect);
            ViewUtils.RelativePadding relativePadding = this.mInitPaddings;
            relativePadding.top = rect.top;
            relativePadding.bottom = rect.bottom;
        }
        ViewUtils.RelativePadding relativePadding2 = this.mInitPaddings;
        if (relativePadding2.top == 0) {
            relativePadding2.top = this.mInputPaddingTop;
        }
        TextView textView = (TextView) findViewById(R.id.search_text_cancel);
        this.mTextCancel = textView;
        textView.setOnClickListener(this);
        this.mCancelBtnInitPaddings = new ViewUtils.RelativePadding(this.mTextCancel);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.search_container);
        this.mSearchContainer = viewGroup;
        CompatViewMethod.setForceDarkAllowed(viewGroup, false);
        EditText editText = (EditText) findViewById(android.R.id.input);
        this.mInputView = editText;
        resetTextSize(editText, this.mTextCancel);
        Folme.useAt(this.mSearchContainer).touch().setScale(1.0f, new ITouchStyle.TouchType[0]).handleTouchOf(this.mInputView, new AnimConfig[0]);
        this.mOriginalPaddingTop = this.mInitPaddings.top;
        View contentView = getContentView();
        if (contentView != null) {
            this.mContentOriginPaddingTop = contentView.getPaddingTop();
            this.mContentOriginPaddingBottom = contentView.getPaddingBottom();
        }
    }

    public void onFloatingModeChanged() {
        resetLocationY();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        View view = this.mDimView;
        if (view != null) {
            view.setTranslationY((getTranslationY() + i5) - i3);
        }
        ObjectAnimator objectAnimator = this.mCurrentAnimation;
        if (objectAnimator == null || !objectAnimator.isRunning()) {
            float f2 = getResources().getDisplayMetrics().density;
            updateExtraPadding(f2);
            updateViewPadding(this.mExtraPadding, f2);
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public void pollViews() {
        getActionBarView();
        getActionBarContainer();
        getSplitActionBarContainer();
    }

    public void rePaddingAndRelayout(Rect rect) {
        int i2 = this.mPendingInsetTop;
        int i3 = rect.top;
        if (i2 != i3) {
            this.mPendingInsetTop = i3;
            updateOnPaddingTopChanged();
            if (!this.mOriginOverlayMode) {
                WeakReference<View> weakReference = this.mAnchorParentView;
                if ((weakReference != null ? weakReference.get() : null) instanceof NestedCoordinatorObserver) {
                    setContentViewPadding(this.mPendingInsetTop + getViewHeight(), 0);
                } else {
                    setContentViewPadding(this.mPendingInsetTop, 0);
                }
            }
            updateResultViewMargin(this.mInActionMode);
            requestLayout();
        }
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void removeAnimationListener(ActionModeAnimationListener actionModeAnimationListener) {
        List<ActionModeAnimationListener> list;
        if (actionModeAnimationListener == null || (list = this.mAnimationListeners) == null) {
            return;
        }
        list.remove(actionModeAnimationListener);
    }

    public void resetCustomView() {
        if (this.mHasSetCustomView) {
            ViewGroup viewGroup = (ViewGroup) this.mDimView;
            FrameLayout frameLayout = this.mCustomFrameLayout;
            if (frameLayout != null) {
                View view = this.mCustomView;
                if (view != null) {
                    frameLayout.removeView(view);
                }
                viewGroup.removeView(this.mCustomFrameLayout);
            }
            this.mCustomView = null;
            this.mCustomFrameLayout = null;
            this.mHasSetCustomView = false;
        }
    }

    public void setAnchorApplyExtraPaddingByUser(boolean z2) {
        if (this.mExtraPaddingApplyToAnchorByUser != z2) {
            this.mExtraPaddingApplyToAnchorByUser = z2;
            float f2 = getResources().getDisplayMetrics().density;
            updateExtraPadding(f2);
            updateViewPadding(this.mExtraPadding, f2);
        }
    }

    public void setAnchorView(View view) {
        if (view == null || view.findViewById(R.id.search_mode_stub) == null) {
            return;
        }
        this.mAnchorView = new WeakReference<>(view);
        if (view.getParent() != null) {
            this.mAnchorParentView = new WeakReference<>((View) view.getParent());
        }
    }

    public void setAnimateView(View view) {
        if (view != null) {
            this.mAnimateView = new WeakReference<>(view);
        }
    }

    public void setAnimatedViewListener(SearchActionMode.AnimatedViewListener animatedViewListener) {
        this.mAnimatedViewListener = animatedViewListener;
    }

    public void setAnimationProgress(float f2) {
        this.mAnimationProgress = f2;
        notifyAnimationUpdate(this.mAnimateToVisible, f2);
    }

    public void setContentViewPadding(int i2, int i3) {
        View contentView = getContentView();
        if (contentView != null) {
            contentView.setPaddingRelative(contentView.getPaddingStart(), i2 + this.mContentOriginPaddingTop, contentView.getPaddingEnd(), i3 + this.mContentOriginPaddingBottom);
        }
    }

    public void setContentViewTranslation(float f2) {
        View contentView = getContentView();
        if (contentView != null) {
            contentView.setTranslationY(f2);
        }
    }

    public void setCustomView(View view) {
        if (view == null || this.mHasSetCustomView) {
            return;
        }
        this.mCustomView = view;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.mCustomFrameLayout = frameLayout;
        frameLayout.setLayoutParams(layoutParams);
        this.mCustomFrameLayout.setId(R.id.searchActionMode_customFrameLayout);
        this.mCustomFrameLayout.addView(this.mCustomView, layoutParams);
        this.mCustomFrameLayout.setPadding(0, 0, 0, 0);
        getDimView();
        ((ViewGroup) this.mDimView).addView(this.mCustomFrameLayout, layoutParams);
        this.mHasSetCustomView = true;
    }

    public void setExtraPaddingPolicy(ExtraPaddingPolicy extraPaddingPolicy) {
        if (this.mExtraPaddingPolicy != extraPaddingPolicy) {
            this.mExtraPaddingPolicy = extraPaddingPolicy;
            float f2 = getResources().getDisplayMetrics().density;
            updateExtraPadding(f2);
            updateViewPadding(this.mExtraPadding, f2);
        }
    }

    public void setOnBackClickListener(View.OnClickListener onClickListener) {
        this.mOnBackClickListener = onClickListener;
    }

    public void setOverlayModeView(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.mOverlayView = new WeakReference<>(actionBarOverlayLayout);
        this.mOriginOverlayMode = actionBarOverlayLayout.isInOverlayMode();
    }

    public void setResultView(View view) {
        if (view == null || (((View) view.getParent()) instanceof NestedContentInsetObserver)) {
            return;
        }
        this.mResultView = new WeakReference<>(view);
        this.mResultViewOriginPaddingTop = view.getPaddingTop();
        this.mResultViewOriginPaddingBottom = view.getPaddingBottom();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            this.mResultViewOriginMarginTop = marginLayoutParams.topMargin;
            this.mResultViewOriginMarginBottom = marginLayoutParams.bottomMargin;
        }
        this.mResultViewSet = true;
    }

    public void updateBackground(boolean z2) {
        Drawable background = getBackground();
        if (background != null) {
            if (z2) {
                background.setAlpha(0);
            } else {
                background.setAlpha(255);
            }
        }
    }
}
