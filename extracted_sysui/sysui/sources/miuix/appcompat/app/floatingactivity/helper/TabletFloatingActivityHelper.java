package miuix.appcompat.app.floatingactivity.helper;

import android.R;
import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.util.Collection;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.ViewProperty;
import miuix.appcompat.app.AppCompatActivity;
import miuix.appcompat.app.floatingactivity.FloatingAnimHelper;
import miuix.appcompat.app.floatingactivity.FloatingSwitcherAnimHelper;
import miuix.appcompat.app.floatingactivity.OnFloatingActivityCallback;
import miuix.appcompat.app.floatingactivity.OnFloatingCallback;
import miuix.appcompat.widget.dialoganim.DimAnimator;
import miuix.core.util.IntentUtils;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.ViewUtils;
import miuix.internal.widget.RoundFrameLayout;
import miuix.theme.token.DimToken;
import miuix.view.CompatViewMethod;

/* JADX INFO: loaded from: classes2.dex */
public abstract class TabletFloatingActivityHelper extends BaseFloatingActivityHelper {
    private static final String ANIM_TAG_DISMISS = "dismiss";
    private static final String ANIM_TAG_INIT = "init";
    private static final int GESTURE_ENABLE_DELAY_TIME = 500;
    private static final float MOVE_DISTANCE_RATIO = 0.5f;
    private static final int PANEL_SHOW_DELAY_TIME = 90;
    protected AppCompatActivity mActivity;
    private View mBg;
    private final Drawable mDefaultPanelBg;
    private ViewGroup.LayoutParams mFloatingLayoutParam;
    private float mFloatingRadius;
    private View mFloatingRoot;
    private View mHandle;
    private float mLastMoveY;
    private float mMoveMaxY;
    private float mOffsetY;
    private OnFloatingActivityCallback mOnFloatingActivityCallback;
    private OnFloatingCallback mOnFloatingCallback;
    private View mPanel;
    private View mPanelParent;
    private GestureDetector mRootViewGestureDetector;
    private RoundFrameLayout mRoundFrameLayout;
    private float mTouchDownY;
    private float mBgAlpha = 1.0f;
    private boolean mEnableSwipeToDismiss = true;
    private final Handler mFloatingActivitySlidDownHandler = new Handler(Looper.getMainLooper());
    private boolean mAnimationDoing = false;
    private boolean mIsFloatingWindow = true;
    private boolean mIsBorderEnable = false;
    private int mFloatingActivityFinishingFlag = 0;

    public static class FinishFloatingActivityDelegate implements Runnable {
        private WeakReference<AppCompatActivity> mActivity;
        private WeakReference<TabletFloatingActivityHelper> mRefs;

        public FinishFloatingActivityDelegate(TabletFloatingActivityHelper tabletFloatingActivityHelper, AppCompatActivity appCompatActivity) {
            this.mRefs = new WeakReference<>(tabletFloatingActivityHelper);
            this.mActivity = new WeakReference<>(appCompatActivity);
        }

        private void activityExitActuator(AppCompatActivity appCompatActivity, TabletFloatingActivityHelper tabletFloatingActivityHelper, boolean z2, int i2, boolean z3) {
            if (tabletFloatingActivityHelper.isFirstPageExitAnimExecuteEnable()) {
                tabletFloatingActivityHelper.singleFloatingSlipExit(z2, i2);
            } else if (appCompatActivity != null) {
                appCompatActivity.realFinish();
                preformFloatingExitAnimWithClip(appCompatActivity, tabletFloatingActivityHelper, z3);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void delegatePadPhoneFinishFloatingActivity(boolean z2) {
            TabletFloatingActivityHelper tabletFloatingActivityHelper = this.mRefs.get();
            if (tabletFloatingActivityHelper != null) {
                tabletFloatingActivityHelper.updateFloatingActivityFinishingFlag(3);
            }
            AppCompatActivity appCompatActivity = this.mActivity.get();
            if (tabletFloatingActivityHelper != null) {
                activityExitActuator(appCompatActivity, tabletFloatingActivityHelper, true, 3, z2);
            }
        }

        private void preformFloatingExitAnimWithClip(AppCompatActivity appCompatActivity, TabletFloatingActivityHelper tabletFloatingActivityHelper, boolean z2) {
            if (z2) {
                FloatingAnimHelper.preformFloatingExitAnimWithClip(appCompatActivity, tabletFloatingActivityHelper.mIsFloatingWindow);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            delegatePadPhoneFinishFloatingActivity(false);
        }
    }

    public static class FloatingAnimTransitionListener extends TransitionListener {
        private boolean mAllActivityFinished;
        private boolean mDismiss;
        private WeakReference<TabletFloatingActivityHelper> mRefs;
        private int mTranslationY;
        private int mType;

        @Override // miuix.animation.listener.TransitionListener
        public void onCancel(Object obj) {
            super.onCancel(obj);
            WeakReference<TabletFloatingActivityHelper> weakReference = this.mRefs;
            TabletFloatingActivityHelper tabletFloatingActivityHelper = weakReference == null ? null : weakReference.get();
            if (tabletFloatingActivityHelper != null) {
                tabletFloatingActivityHelper.onEnd(obj);
            }
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onComplete(Object obj) {
            super.onComplete(obj);
            WeakReference<TabletFloatingActivityHelper> weakReference = this.mRefs;
            TabletFloatingActivityHelper tabletFloatingActivityHelper = weakReference == null ? null : weakReference.get();
            if (tabletFloatingActivityHelper != null) {
                tabletFloatingActivityHelper.onEnd(obj);
            }
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
            UpdateInfo updateInfoFindBy = UpdateInfo.findBy(collection, ViewProperty.TRANSLATION_Y);
            if (!this.mDismiss || updateInfoFindBy == null) {
                return;
            }
            TabletFloatingActivityHelper tabletFloatingActivityHelper = this.mRefs.get();
            if (this.mAllActivityFinished || updateInfoFindBy.getFloatValue() <= this.mTranslationY * 0.6f || tabletFloatingActivityHelper == null) {
                return;
            }
            this.mAllActivityFinished = true;
            tabletFloatingActivityHelper.finishAllPage();
        }

        private FloatingAnimTransitionListener(TabletFloatingActivityHelper tabletFloatingActivityHelper, boolean z2, int i2, int i3) {
            this.mAllActivityFinished = false;
            this.mRefs = new WeakReference<>(tabletFloatingActivityHelper);
            this.mType = i3;
            this.mDismiss = z2;
            this.mTranslationY = i2;
        }
    }

    public TabletFloatingActivityHelper(AppCompatActivity appCompatActivity) {
        this.mActivity = appCompatActivity;
        this.mDefaultPanelBg = AttributeResolver.resolveDrawable(appCompatActivity, R.attr.windowBackground);
    }

    private void backOneByOne(int i2) {
        updateFloatingActivityFinishingFlag(i2);
        if (!isFirstPageExitAnimExecuteEnable()) {
            this.mActivity.realFinish();
            FloatingAnimHelper.singleAppFloatingActivityExit(this.mActivity);
        } else if (!this.mAnimationDoing) {
            triggerBottomExit(i2);
        }
        execExitAnim();
    }

    private boolean delegateFinishTransWithClipAnimInternal() {
        new FinishFloatingActivityDelegate(this, this.mActivity).delegatePadPhoneFinishFloatingActivity(true);
        return true;
    }

    private void dimBg(float f2) {
        this.mBg.setAlpha(this.mBgAlpha * (1.0f - Math.max(0.0f, Math.min(f2, 1.0f))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: executeFolme, reason: merged with bridge method [inline-methods] */
    public void lambda$executeFolme$4(final boolean z2, final int i2) {
        float f2;
        String str;
        int i3;
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            this.mActivity.runOnUiThread(new Runnable() { // from class: miuix.appcompat.app.floatingactivity.helper.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f6030a.lambda$executeFolme$4(z2, i2);
                }
            });
            return;
        }
        if (this.mAnimationDoing && z2) {
            return;
        }
        this.mAnimationDoing = true;
        if (z2) {
            i3 = (int) this.mMoveMaxY;
            str = ANIM_TAG_DISMISS;
            f2 = 0.0f;
        } else {
            f2 = this.mBgAlpha;
            str = ANIM_TAG_INIT;
            i3 = 0;
        }
        AnimConfig animConfig = FloatingSwitcherAnimHelper.getAnimConfig(z2 ? 2 : 1, null);
        animConfig.addListeners(new FloatingAnimTransitionListener(z2, i3, i2));
        AnimState animStateAdd = new AnimState(str).add(ViewProperty.TRANSLATION_Y, i3);
        AnimState animStateAdd2 = new AnimState(str).add(ViewProperty.ALPHA, f2);
        Folme.useAt(getAnimPanel()).state().to(animStateAdd, animConfig);
        Folme.useAt(this.mBg).state().to(animStateAdd2, new AnimConfig[0]);
    }

    private void firstFloatingTranslationTop() {
        this.mPanel.post(new Runnable() { // from class: miuix.appcompat.app.floatingactivity.helper.f
            @Override // java.lang.Runnable
            public final void run() {
                this.f6038a.lambda$firstFloatingTranslationTop$5();
            }
        });
    }

    private void folmeShow() {
        View animPanel = getAnimPanel();
        int height = animPanel.getHeight() + ((this.mFloatingRoot.getHeight() - animPanel.getHeight()) / 2);
        IStateStyle iStateStyleState = Folme.useAt(animPanel).state();
        ViewProperty viewProperty = ViewProperty.TRANSLATION_Y;
        iStateStyleState.setTo(viewProperty, Integer.valueOf(height)).to(viewProperty, 0, FloatingSwitcherAnimHelper.getAnimConfig(1, null));
        DimAnimator.show(this.mBg);
    }

    private View getAnimPanel() {
        View view = this.mPanelParent;
        return view == null ? this.mPanel : view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSnapShotAndSetPanel() {
        OnFloatingCallback onFloatingCallback;
        if (FloatingAnimHelper.isSupportTransWithClipAnim() || (onFloatingCallback = this.mOnFloatingCallback) == null || !this.mEnableSwipeToDismiss) {
            return;
        }
        onFloatingCallback.getSnapShotAndSetPanel(this.mActivity);
    }

    private void handleFingerMove(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            notifyDragStart();
            float rawY = motionEvent.getRawY();
            this.mTouchDownY = rawY;
            this.mLastMoveY = rawY;
            this.mOffsetY = 0.0f;
            makeDownMoveMaxY();
            return;
        }
        if (action == 1) {
            boolean z2 = motionEvent.getRawY() - this.mTouchDownY > ((float) this.mPanel.getHeight()) * 0.5f;
            updateFloatingActivityFinishingFlag(1);
            if (!z2) {
                lambda$executeFolme$4(false, 1);
                return;
            }
            getSnapShotAndSetPanel();
            OnFloatingCallback onFloatingCallback = this.mOnFloatingCallback;
            lambda$executeFolme$4(onFloatingCallback == null || !onFloatingCallback.onFinish(1), 1);
            return;
        }
        if (action != 2) {
            return;
        }
        float rawY2 = motionEvent.getRawY();
        float f2 = this.mOffsetY + (rawY2 - this.mLastMoveY);
        this.mOffsetY = f2;
        if (f2 >= 0.0f) {
            movePanel(f2);
            dimBg(this.mOffsetY / this.mMoveMaxY);
        }
        this.mLastMoveY = rawY2;
    }

    private boolean isEnableFirstFloatingTranslationY() {
        return this.mIsFloatingWindow && isFirstPageEnterAnimExecuteEnable();
    }

    private boolean isFirstPageEnterAnimExecuteEnable() {
        OnFloatingCallback onFloatingCallback = this.mOnFloatingCallback;
        if (onFloatingCallback == null) {
            return true;
        }
        return onFloatingCallback.isFirstPageEnterAnimExecuteEnable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFirstPageExitAnimExecuteEnable() {
        OnFloatingCallback onFloatingCallback;
        return this.mIsFloatingWindow && ((onFloatingCallback = this.mOnFloatingCallback) == null || onFloatingCallback.isFirstPageExitAnimExecuteEnable());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$firstFloatingTranslationTop$5() {
        if (isEnableFirstFloatingTranslationY()) {
            markActivityOpenEnterAnimExecuted();
            folmeShow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$init$1(View view, MotionEvent motionEvent) {
        this.mRootViewGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$2() {
        this.mFloatingRoot.setOnTouchListener(new View.OnTouchListener() { // from class: miuix.appcompat.app.floatingactivity.helper.e
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f6037a.lambda$init$1(view, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$init$3(View view, MotionEvent motionEvent) {
        if (!this.mEnableSwipeToDismiss) {
            return true;
        }
        handleFingerMove(motionEvent);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$panelDelayShow$0(float f2) {
        this.mRoundFrameLayout.setAlpha(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void makeDownMoveMaxY() {
        View animPanel = getAnimPanel();
        this.mMoveMaxY = animPanel.getHeight() + ((this.mFloatingRoot.getHeight() - animPanel.getHeight()) / 2);
    }

    private void markActivityOpenEnterAnimExecuted() {
        OnFloatingCallback onFloatingCallback = this.mOnFloatingCallback;
        if (onFloatingCallback != null) {
            onFloatingCallback.markActivityOpenEnterAnimExecuted(this.mActivity);
        }
    }

    private void movePanel(float f2) {
        getAnimPanel().setTranslationY(f2);
    }

    private void notifyDragEnd() {
        Runnable runnable = new Runnable() { // from class: miuix.appcompat.app.floatingactivity.helper.TabletFloatingActivityHelper.3
            @Override // java.lang.Runnable
            public void run() {
                if (TabletFloatingActivityHelper.this.mOnFloatingCallback != null) {
                    TabletFloatingActivityHelper.this.mOnFloatingCallback.onDragEnd();
                }
            }
        };
        View view = this.mBg;
        if (view != null) {
            view.post(runnable);
        } else {
            runnable.run();
        }
    }

    private void notifyDragStart() {
        OnFloatingCallback onFloatingCallback = this.mOnFloatingCallback;
        if (onFloatingCallback != null) {
            onFloatingCallback.onDragStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyPageHide() {
        OnFloatingCallback onFloatingCallback = this.mOnFloatingCallback;
        if (onFloatingCallback != null) {
            onFloatingCallback.onHideBehindPage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEnd(Object obj) {
        if (TextUtils.equals(ANIM_TAG_DISMISS, obj.toString())) {
            View view = this.mBg;
            if (view != null) {
                view.post(new Runnable() { // from class: miuix.appcompat.app.floatingactivity.helper.TabletFloatingActivityHelper.4
                    @Override // java.lang.Runnable
                    public void run() {
                        TabletFloatingActivityHelper.this.mActivity.realFinish();
                    }
                });
            } else {
                this.mActivity.realFinish();
            }
        } else if (TextUtils.equals(ANIM_TAG_INIT, obj.toString())) {
            notifyDragEnd();
        }
        this.mAnimationDoing = false;
    }

    private void panelDelayShow() {
        if (this.mIsFloatingWindow) {
            final float alpha = this.mRoundFrameLayout.getAlpha();
            this.mRoundFrameLayout.setAlpha(0.0f);
            this.mRoundFrameLayout.postDelayed(new Runnable() { // from class: miuix.appcompat.app.floatingactivity.helper.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f6035a.lambda$panelDelayShow$0(alpha);
                }
            }, 90L);
        }
    }

    private void setPanelParent(View view) {
        this.mPanelParent = view;
    }

    private void setRoundFrameLayoutBorder(@NonNull RoundFrameLayout roundFrameLayout) {
        if (this.mIsFloatingWindow && this.mIsBorderEnable) {
            roundFrameLayout.setBorder(this.mActivity.getResources().getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_floating_window_background_border_width), AttributeResolver.resolveColor(this.mActivity, miuix.appcompat.R.attr.miuixAppcompatFloatingWindowBorderColor, 0));
        } else {
            roundFrameLayout.setBorder(0.0f, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void singleFloatingSlipExit(boolean z2, int i2) {
        if (!z2 || this.mAnimationDoing) {
            return;
        }
        makeDownMoveMaxY();
        notifyPageHide();
        lambda$executeFolme$4(true, i2);
    }

    private void triggerBottomExit(int i2) {
        makeDownMoveMaxY();
        notifyPageHide();
        lambda$executeFolme$4(true, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void triggerFinishCallback(boolean z2, int i2) {
        updateFloatingActivityFinishingFlag(i2);
        if (!z2) {
            lambda$executeFolme$4(false, i2);
            return;
        }
        OnFloatingActivityCallback onFloatingActivityCallback = this.mOnFloatingActivityCallback;
        if (onFloatingActivityCallback != null && onFloatingActivityCallback.onFinish(i2)) {
            lambda$executeFolme$4(false, i2);
        } else {
            OnFloatingCallback onFloatingCallback = this.mOnFloatingCallback;
            lambda$executeFolme$4(onFloatingCallback == null || !onFloatingCallback.onFinish(i2), i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFloatingActivityFinishingFlag(int i2) {
        this.mFloatingActivityFinishingFlag = i2;
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public boolean delegateFinishFloatingActivityInternal() {
        if (FloatingAnimHelper.isSupportTransWithClipAnim()) {
            return delegateFinishTransWithClipAnimInternal();
        }
        if (this.mIsFloatingWindow) {
            getSnapShotAndSetPanel();
            this.mFloatingActivitySlidDownHandler.postDelayed(new FinishFloatingActivityDelegate(this, this.mActivity), 110L);
            return true;
        }
        this.mActivity.realFinish();
        execExitAnim();
        return true;
    }

    public void execExitAnim() {
    }

    @Override // miuix.appcompat.app.floatingactivity.IActivitySwitcherAnimation
    public void executeCloseEnterAnimation() {
        if (this.mIsFloatingWindow) {
            FloatingSwitcherAnimHelper.executeCloseEnterAnimation(this.mPanel);
        }
    }

    @Override // miuix.appcompat.app.floatingactivity.IActivitySwitcherAnimation
    public void executeCloseExitAnimation() {
        if (this.mIsFloatingWindow) {
            FloatingSwitcherAnimHelper.executeCloseExitAnimation(this.mPanel);
        }
    }

    @Override // miuix.appcompat.app.floatingactivity.IActivitySwitcherAnimation
    public void executeOpenEnterAnimation() {
        if (this.mIsFloatingWindow) {
            FloatingSwitcherAnimHelper.executeOpenEnterAnimation(this.mPanel);
        }
    }

    @Override // miuix.appcompat.app.floatingactivity.IActivitySwitcherAnimation
    public void executeOpenExitAnimation() {
        if (this.mIsFloatingWindow) {
            FloatingSwitcherAnimHelper.executeOpenExitAnimation(this.mPanel);
        }
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public void exitFloatingActivityAll() {
        getSnapShotAndSetPanel();
        makeDownMoveMaxY();
        notifyPageHide();
        triggerFinishCallback(true, 0);
    }

    public void finishAllPage() {
        Runnable runnable = new Runnable() { // from class: miuix.appcompat.app.floatingactivity.helper.TabletFloatingActivityHelper.2
            @Override // java.lang.Runnable
            public void run() {
                if (TabletFloatingActivityHelper.this.mOnFloatingCallback != null) {
                    TabletFloatingActivityHelper.this.mOnFloatingCallback.closeAllPage();
                }
            }
        };
        View view = this.mBg;
        if (view != null) {
            view.post(runnable);
        } else {
            runnable.run();
        }
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public View getFloatingBrightPanel() {
        return this.mPanel;
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public ViewGroup.LayoutParams getFloatingLayoutParam() {
        return this.mFloatingLayoutParam;
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public void hideFloatingBrightPanel() {
        this.mPanel.setVisibility(8);
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public void hideFloatingDimBackground() {
        this.mBg.setVisibility(8);
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    @SuppressLint({"ClickableViewAccessibility"})
    public void init(View view, boolean z2) {
        this.mHandle = view.findViewById(miuix.appcompat.R.id.sliding_drawer_handle);
        View viewFindViewById = view.findViewById(miuix.appcompat.R.id.action_bar_overlay_bg);
        this.mBg = viewFindViewById;
        viewFindViewById.setVisibility(z2 ? 0 : 8);
        float f2 = AttributeResolver.resolveBoolean(view.getContext(), R.attr.isLightTheme, true) ? DimToken.DIM_LIGHT : DimToken.DIM_DARK;
        this.mBgAlpha = f2;
        this.mBg.setAlpha(f2);
        this.mPanel = view.findViewById(miuix.appcompat.R.id.action_bar_overlay_layout);
        this.mFloatingRoot = view.findViewById(miuix.appcompat.R.id.action_bar_overlay_floating_root);
        this.mIsFloatingWindow = z2;
        this.mRootViewGestureDetector = new GestureDetector(view.getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: miuix.appcompat.app.floatingactivity.helper.TabletFloatingActivityHelper.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                if (TabletFloatingActivityHelper.this.mEnableSwipeToDismiss && !TabletFloatingActivityHelper.this.mAnimationDoing && !TabletFloatingActivityHelper.this.mActivity.isFinishing()) {
                    TabletFloatingActivityHelper.this.getSnapShotAndSetPanel();
                    TabletFloatingActivityHelper.this.makeDownMoveMaxY();
                    TabletFloatingActivityHelper.this.notifyPageHide();
                    TabletFloatingActivityHelper.this.triggerFinishCallback(true, 2);
                }
                return true;
            }
        });
        this.mFloatingRoot.postDelayed(new Runnable() { // from class: miuix.appcompat.app.floatingactivity.helper.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f6033a.lambda$init$2();
            }
        }, 500L);
        this.mHandle.setOnTouchListener(new View.OnTouchListener() { // from class: miuix.appcompat.app.floatingactivity.helper.c
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return this.f6034a.lambda$init$3(view2, motionEvent);
            }
        });
        firstFloatingTranslationTop();
        this.mActivity.getWindow().setBackgroundDrawableResource(miuix.appcompat.R.color.miuix_appcompat_transparent);
        if (this.mIsFloatingWindow || !ViewUtils.isNightMode(this.mActivity)) {
            this.mPanel.setBackground(this.mDefaultPanelBg);
        } else {
            this.mPanel.setBackground(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
        }
        if (this.mEnableSwipeToDismiss && this.mIsFloatingWindow) {
            this.mHandle.setVisibility(0);
        } else {
            this.mHandle.setVisibility(8);
        }
    }

    public boolean isFloatingWindow() {
        return this.mIsFloatingWindow;
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public void onBackPressed() {
        if (this.mIsFloatingWindow && !FloatingAnimHelper.isSupportTransWithClipAnim()) {
            getSnapShotAndSetPanel();
        }
        backOneByOne(4);
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public ViewGroup replaceSubDecor(View view, boolean z2) {
        ViewGroup viewGroup = (ViewGroup) View.inflate(this.mActivity, miuix.appcompat.R.layout.miuix_appcompat_screen_floating_window, null);
        View viewFindViewById = viewGroup.findViewById(miuix.appcompat.R.id.action_bar_overlay_layout);
        View viewFindViewById2 = viewGroup.findViewById(miuix.appcompat.R.id.sliding_drawer_handle);
        if (viewFindViewById2 != null && (viewFindViewById2.getParent() instanceof ViewGroup)) {
            ((ViewGroup) viewFindViewById2.getParent()).removeView(viewFindViewById2);
        }
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).addView(viewFindViewById2);
        }
        ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height, 17);
        this.mFloatingLayoutParam = layoutParams2;
        if (z2) {
            ((ViewGroup.LayoutParams) layoutParams2).height = -2;
            ((ViewGroup.LayoutParams) layoutParams2).width = -2;
        } else {
            ((ViewGroup.LayoutParams) layoutParams2).width = -1;
            ((ViewGroup.LayoutParams) layoutParams2).height = -1;
        }
        viewGroup.removeView(viewFindViewById);
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
        this.mFloatingRadius = this.mActivity.getResources().getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_floating_window_background_radius);
        RoundFrameLayout roundFrameLayout = new RoundFrameLayout(this.mActivity);
        this.mRoundFrameLayout = roundFrameLayout;
        roundFrameLayout.setLayoutParams(this.mFloatingLayoutParam);
        this.mRoundFrameLayout.addView(view);
        this.mRoundFrameLayout.setRadius(z2 ? this.mFloatingRadius : 0.0f);
        setRoundFrameLayoutBorder(this.mRoundFrameLayout);
        panelDelayShow();
        viewGroup.addView(this.mRoundFrameLayout);
        setPanelParent(this.mRoundFrameLayout);
        return viewGroup;
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public void setEnableSwipToDismiss(boolean z2) {
        this.mEnableSwipeToDismiss = z2;
        if (z2 && this.mIsFloatingWindow) {
            this.mHandle.setVisibility(0);
        } else {
            this.mHandle.setVisibility(8);
        }
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public void setFloatingWindowBorderEnable(boolean z2) {
        this.mIsBorderEnable = z2;
        RoundFrameLayout roundFrameLayout = this.mRoundFrameLayout;
        if (roundFrameLayout != null) {
            setRoundFrameLayoutBorder(roundFrameLayout);
        }
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public void setFloatingWindowMode(boolean z2) {
        this.mIsFloatingWindow = z2;
        if (!IntentUtils.isIntentFromSettingsSplit(this.mActivity.getIntent())) {
            CompatViewMethod.setActivityTranslucent(this.mActivity, true);
        }
        if (this.mBg != null && this.mOnFloatingCallback.isFirstPage()) {
            this.mBg.setVisibility(z2 ? 0 : 8);
        }
        if (this.mRoundFrameLayout != null) {
            float dimensionPixelSize = this.mActivity.getResources().getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_floating_window_background_radius);
            this.mFloatingRadius = dimensionPixelSize;
            RoundFrameLayout roundFrameLayout = this.mRoundFrameLayout;
            if (!z2) {
                dimensionPixelSize = 0.0f;
            }
            roundFrameLayout.setRadius(dimensionPixelSize);
            setRoundFrameLayoutBorder(this.mRoundFrameLayout);
        }
        if (this.mPanel != null) {
            if (z2 || !ViewUtils.isNightMode(this.mActivity)) {
                this.mPanel.setBackground(this.mDefaultPanelBg);
            } else {
                this.mPanel.setBackground(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
            }
        }
        View view = this.mHandle;
        if (view != null) {
            if (this.mEnableSwipeToDismiss && this.mIsFloatingWindow) {
                view.setVisibility(0);
            } else {
                view.setVisibility(8);
            }
        }
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public void setOnFloatingCallback(OnFloatingCallback onFloatingCallback) {
        this.mOnFloatingCallback = onFloatingCallback;
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public void setOnFloatingWindowCallback(OnFloatingActivityCallback onFloatingActivityCallback) {
        this.mOnFloatingActivityCallback = onFloatingActivityCallback;
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public boolean shouldInterceptBack() {
        return true;
    }

    @Override // miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper
    public void showFloatingBrightPanel() {
        this.mPanel.setVisibility(0);
    }
}
