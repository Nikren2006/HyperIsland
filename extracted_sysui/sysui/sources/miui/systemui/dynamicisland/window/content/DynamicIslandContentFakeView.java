package miui.systemui.dynamicisland.window.content;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import c1.f;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import j1.AbstractC0420h;
import j1.I;
import j1.K;
import j1.u;
import java.util.Collection;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.x;
import miui.systemui.animation.FolmeKt;
import miui.systemui.dynamicisland.DynamicIslandBackgroundView;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.handler.BigIslandStateHandler;
import miui.systemui.dynamicisland.template.IslandTemplateFactory;
import miui.systemui.dynamicisland.window.DynamicIslandAnimUtils;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;
import miui.systemui.notification.LottieProgressManager;
import miui.systemui.util.BoostHelper;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;
import miuix.animation.FolmeObject;
import miuix.animation.IFolme;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.utils.EaseManager;
import miuix.core.util.MiShadowUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandContentFakeView extends DynamicIslandBaseContentView {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "DynamicIslandContentFakeView";
    private final u _startEnterMiniWindowBeforeAnimation;
    private final u _trackingToOpenMW;
    private boolean appClosingToExpanded;
    private float bigIslandTx;
    private boolean bigIslandViewNeedAnim;
    private boolean blockedByError;
    private boolean closingAppFromFreeform;
    private boolean closingToExpanded;
    private IFolme folme;
    private final FolmeObject folmeObject;
    private boolean forceUpdateBigIslandView;
    private boolean isLight;
    private final EaseManager.InterpolateEaseStyle mAlphaEease;
    private final AnimConfig mAnimConfig;
    private boolean mCanTrackingEnterMW;
    private int mFakeViewBottom;
    private int mFakeViewHeight;
    private int mFakeViewLeft;
    private int mFakeViewRight;
    private int mFakeViewTop;
    private FakeViewTrackingParams mFakeViewTrackingParams;
    private int mFakeViewWidth;
    private float mInitialTouchY;
    private float mLastTouchY;
    private float mMaxTriggerThreshold;
    private boolean mStartEnterMiniWindow;
    private final int mTouchSlop;
    private final EaseManager.EaseStyle mTrackingEase;
    private float mTriggerThreshold;
    private boolean miniWindowAlreadyOpen;
    private boolean miniWindowClosingToExpanded;
    private boolean needResetState;
    private boolean openFromRealExpandViewRect;
    private final float radius;
    private final I startEnterMiniWindowBeforeAnimation;
    private int startTop;
    private boolean suppressBigIslandLayout;
    private final I trackingToOpenMW;
    private final VelocityTracker velocityTracker;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView$updateSmallIslandView$1, reason: invalid class name and case insensitive filesystem */
    public static final class C06461 extends o implements Function2 {
        public static final C06461 INSTANCE = new C06461();

        public C06461() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Boolean invoke(DynamicIslandData dynamicIslandData, DynamicIslandEvent dynamicIslandEvent) {
            n.g(dynamicIslandEvent, "<anonymous parameter 1>");
            return Boolean.FALSE;
        }
    }

    public /* synthetic */ DynamicIslandContentFakeView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    private final float afterFriction(float f2, float f3) {
        float fE = f.e(Math.abs(f2) / Math.abs(f3), f3);
        float f4 = fE * fE;
        return ((((f4 * fE) / 3) - f4) + fE) * f3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFakeViewTrackingParamsUpdated() {
        updateOutline(this.mFakeViewTrackingParams.getBottom() - this.mFakeViewTrackingParams.getTop(), this.mFakeViewTrackingParams.getRight() - this.mFakeViewTrackingParams.getLeft(), false);
    }

    private final void onTrackingFakeViewDown() {
        if (this.appClosingToExpanded) {
            DynamicIslandContentView realView = getRealView();
            if (realView != null) {
                realView.setVisibility(0);
            }
            DynamicIslandContentView realView2 = getRealView();
            DynamicIslandBackgroundView backgroundView = realView2 != null ? realView2.getBackgroundView() : null;
            if (backgroundView != null) {
                backgroundView.setVisibility(0);
            }
        }
        DynamicIslandContentView realView3 = getRealView();
        if (realView3 != null) {
            this.mFakeViewWidth = realView3.getExpandedViewWidth();
        }
        DynamicIslandContentView realView4 = getRealView();
        if (realView4 != null) {
            this.mFakeViewHeight = realView4.getExpandedViewHeight();
        }
        DynamicIslandContentView realView5 = getRealView();
        if (realView5 != null) {
            this.mFakeViewLeft = realView5.getExpandedViewMarginHorizontal();
        }
        DynamicIslandContentView realView6 = getRealView();
        if (realView6 != null) {
            this.mFakeViewTop = realView6.getExpandedViewY();
        }
        int i2 = this.mFakeViewLeft;
        int i3 = this.mFakeViewWidth;
        int i4 = i2 + i3;
        this.mFakeViewRight = i4;
        int i5 = this.mFakeViewTop;
        int i6 = this.mFakeViewHeight;
        int i7 = i5 + i6;
        this.mFakeViewBottom = i7;
        this.blockedByError = false;
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "onTrackingFakeViewDown: mFakeViewWidth = " + i3 + ", mFakeViewHeight = " + i6 + ", mFakeViewLeft = " + i2 + ", mFakeViewTop = " + i5 + ", mFakeViewRight = " + i4 + ", mFakeViewBottom = " + i7);
    }

    private final void onTrackingFakeViewEnd() {
        boolean z2;
        DynamicIslandData currentIslandData;
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "onTrackingFakeViewEnd:  _trackingToOpenMW.value:" + this._trackingToOpenMW.getValue() + ", mStartEnterMiniWindow:" + this.mStartEnterMiniWindow + ", miniWindowClosingToExpanded：" + this.miniWindowClosingToExpanded);
        if (((Boolean) this._trackingToOpenMW.getValue()).booleanValue() && (!(z2 = this.mStartEnterMiniWindow) || (z2 && this.miniWindowClosingToExpanded))) {
            this.velocityTracker.computeCurrentVelocity(1000);
            int bottom = (this.mFakeViewTrackingParams.getBottom() - this.mFakeViewTrackingParams.getTop()) - this.mFakeViewHeight;
            if (this.velocityTracker.getYVelocity(0) <= 1000.0f || bottom <= this.mTriggerThreshold) {
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator();
                if (dynamicIslandEventCoordinator != null) {
                    DynamicIslandContentView realView = getRealView();
                    dynamicIslandEventCoordinator.onWindowAnimExtendLifetimeEnd((realView == null || (currentIslandData = realView.getCurrentIslandData()) == null) ? null : currentIslandData.getExtras());
                }
                this.needResetState = true;
                this.folme.to("alpha", Float.valueOf(1.0f), "left", Integer.valueOf(this.mFakeViewTrackingParams.getLeft()), "right", Integer.valueOf(this.mFakeViewTrackingParams.getRight()), "top", Integer.valueOf(this.mFakeViewTop), "bottom", Integer.valueOf(this.mFakeViewTop + this.mFakeViewHeight), this.mAnimConfig.setEase(this.mTrackingEase).setSpecial("alpha", this.mAlphaEease, new float[0]));
            } else {
                startEnterAndLaunchMiniWindow();
                startSpeedAnimation();
            }
        } else if (((Boolean) this._trackingToOpenMW.getValue()).booleanValue() && this.mStartEnterMiniWindow && !this.miniWindowAlreadyOpen) {
            startSpeedAnimation();
        }
        this.mCanTrackingEnterMW = false;
        this._trackingToOpenMW.setValue(Boolean.FALSE);
        this.velocityTracker.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onTrackingFakeViewReset() {
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "onTrackingFakeViewReset");
        this.mStartEnterMiniWindow = false;
        updateExpandedFakeViewToReal();
    }

    private final void onTrackingFakeViewStart() {
        DynamicIslandData currentIslandData;
        DynamicIslandData currentIslandData2;
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "onTrackingFakeViewStart");
        this._trackingToOpenMW.setValue(Boolean.TRUE);
        this.mFakeViewTrackingParams.setLeft(this.mFakeViewLeft);
        this.mFakeViewTrackingParams.setTop(this.mFakeViewTop);
        this.mFakeViewTrackingParams.setRight(this.mFakeViewRight);
        this.mFakeViewTrackingParams.setAlpha(1.0f);
        if (getRealView() != null) {
            updateMiniBar(getRealView());
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator != null) {
            DynamicIslandContentView realView = getRealView();
            DynamicIslandContentView realView2 = getRealView();
            dynamicIslandEventCoordinator.updateFreeformFakeView(this, realView, (realView2 == null || (currentIslandData2 = realView2.getCurrentIslandData()) == null) ? null : currentIslandData2.getExtras());
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator2 != null) {
            DynamicIslandContentView realView3 = getRealView();
            dynamicIslandEventCoordinator2.onWindowAnimExtendLifetimeStart((realView3 == null || (currentIslandData = realView3.getCurrentIslandData()) == null) ? null : currentIslandData.getExtras());
        }
        if (this.miniWindowClosingToExpanded) {
            return;
        }
        syncLottieProgress(false);
        setVisibility(0);
        setAlpha(1.0f);
        DynamicIslandContentView realView4 = getRealView();
        DynamicIslandBackgroundView backgroundView = realView4 != null ? realView4.getBackgroundView() : null;
        if (backgroundView != null) {
            backgroundView.setVisibility(4);
        }
        DynamicIslandContentView realView5 = getRealView();
        if (realView5 == null) {
            return;
        }
        realView5.setVisibility(4);
    }

    private final void onTrackingFakeViewUpdate(float f2) {
        if (f2 < 0.0f) {
            return;
        }
        float fH = 1 - f.h(f2 / this.mMaxTriggerThreshold, 0.0f, 1.0f);
        Context context = getContext();
        n.f(context, "getContext(...)");
        int screenWidth = getScreenWidth(context);
        Context context2 = getContext();
        n.f(context2, "getContext(...)");
        float fMin = Math.min(screenWidth, getScreenHeight(context2));
        Context context3 = getContext();
        n.f(context3, "getContext(...)");
        int screenWidth2 = getScreenWidth(context3);
        Context context4 = getContext();
        n.f(context4, "getContext(...)");
        float fMax = Math.max(screenWidth2, getScreenHeight(context4));
        if (2 != getContext().getResources().getConfiguration().orientation) {
            fMin = fMax;
        }
        Number numberValueOf = f2 == 0.0f ? 0 : Float.valueOf(afterFriction(f2, fMin) * 0.5f);
        float fAfterFriction = f2 == 0.0f ? 0.0f : afterFriction(f2, fMin) * 0.5f;
        int iB = this.startTop + this.mFakeViewHeight + ((int) f.b(f2, 0.0f)) + numberValueOf.intValue();
        if (f2 == 0.0f) {
            this.startTop = this.mFakeViewTrackingParams.getTop();
        } else {
            iB = this.startTop + this.mFakeViewHeight + ((int) f.b(fAfterFriction, 0.0f)) + numberValueOf.intValue();
        }
        int iIntValue = this.startTop + numberValueOf.intValue();
        this.folme.setTo("alpha", Float.valueOf(fH), "left", Integer.valueOf(this.mFakeViewTrackingParams.getLeft()), "right", Integer.valueOf(this.mFakeViewTrackingParams.getRight()), "top", Integer.valueOf(iIntValue), "bottom", Integer.valueOf(iB));
        this.mFakeViewTrackingParams.setAlpha(fH);
        this.mFakeViewTrackingParams.setTop(iIntValue);
        this.mFakeViewTrackingParams.setBottom(iB);
        onFakeViewTrackingParamsUpdated();
        if (f2 > this.mMaxTriggerThreshold) {
            startEnterAndLaunchMiniWindow();
        }
    }

    private final void resetExpandedViewScaleForFreeform() {
        Log.e(TAG, "resetExpandedViewScaleForFreeform");
        FrameLayout fakeExpandedView = getFakeExpandedView();
        if (fakeExpandedView != null) {
            fakeExpandedView.setPivotX(this.mFakeViewWidth / 2.0f);
        }
        FrameLayout fakeExpandedView2 = getFakeExpandedView();
        if (fakeExpandedView2 != null) {
            fakeExpandedView2.setPivotY(this.mFakeViewHeight / 2.0f);
        }
        FrameLayout fakeExpandedView3 = getFakeExpandedView();
        if (fakeExpandedView3 != null) {
            fakeExpandedView3.setScaleX(1.0f);
        }
        FrameLayout fakeExpandedView4 = getFakeExpandedView();
        if (fakeExpandedView4 == null) {
            return;
        }
        fakeExpandedView4.setScaleY(1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setVisibility$lambda$10(DynamicIslandContentFakeView this$0) {
        DynamicIslandData currentIslandData;
        n.g(this$0, "this$0");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this$0.getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator != null) {
            DynamicIslandContentView realView = this$0.getRealView();
            dynamicIslandEventCoordinator.alreadyCloseAppEnd((realView == null || (currentIslandData = realView.getCurrentIslandData()) == null) ? null : currentIslandData.getExtras());
        }
    }

    private final void startEnterAndLaunchMiniWindow() {
        DynamicIslandData currentIslandData;
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "startEnterAndLaunchMiniWindow , mStartEnterMiniWindow=" + this.mStartEnterMiniWindow + ", miniWindowClosingToExpanded:" + this.miniWindowClosingToExpanded + ", block=" + this.blockedByError);
        boolean z2 = this.mStartEnterMiniWindow;
        if ((!z2 || this.miniWindowClosingToExpanded) && !this.blockedByError) {
            if (z2 && this.miniWindowClosingToExpanded) {
                this.openFromRealExpandViewRect = true;
            }
            this.miniWindowClosingToExpanded = false;
            this.mStartEnterMiniWindow = true;
            this._startEnterMiniWindowBeforeAnimation.setValue(Boolean.TRUE);
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator != null) {
                dynamicIslandEventCoordinator.setEnterMiniWindow(true);
            }
            setOpenAppFromIsland(true);
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator2 != null) {
                dynamicIslandEventCoordinator2.setUserExpanded(false);
            }
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator3 != null) {
                DynamicIslandContentView realView = getRealView();
                dynamicIslandEventCoordinator3.openFreeForm((realView == null || (currentIslandData = realView.getCurrentIslandData()) == null) ? null : currentIslandData.getExtras());
            }
        }
    }

    private final void startSpeedAnimation() {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "startSpeedAnimation");
        Context context = getContext();
        n.f(context, "getContext(...)");
        int screenWidth = getScreenWidth(context);
        Context context2 = getContext();
        n.f(context2, "getContext(...)");
        float fMin = Math.min(screenWidth, getScreenHeight(context2));
        Context context3 = getContext();
        n.f(context3, "getContext(...)");
        int screenWidth2 = getScreenWidth(context3);
        Context context4 = getContext();
        n.f(context4, "getContext(...)");
        float fMax = Math.max(screenWidth2, getScreenHeight(context4));
        if (2 != getContext().getResources().getConfiguration().orientation) {
            fMin = fMax;
        }
        float fAfterFriction = afterFriction(fMin, fMin) * 0.5f;
        int iB = this.startTop + this.mFakeViewHeight + ((int) f.b(afterFriction(fMin, fMin) * 0.5f, 0.0f));
        int i2 = (int) fAfterFriction;
        this.folme.to("alpha", Float.valueOf(0.0f), "left", Integer.valueOf(this.mFakeViewTrackingParams.getLeft()), "right", Integer.valueOf(this.mFakeViewTrackingParams.getRight()), "top", Integer.valueOf(this.startTop + i2), "bottom", Integer.valueOf(iB + i2), this.mAnimConfig.setEase(this.mTrackingEase).setSpecial("alpha", this.mAlphaEease, new float[0]));
    }

    private final void syncIslandLottieProgress(boolean z2) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        DynamicIslandWindowView windowView;
        DynamicIslandWindowViewController windowViewController;
        E0.a lottieProgressManager;
        LottieProgressManager lottieProgressManager2;
        DynamicIslandWindowView windowView2;
        DynamicIslandWindowViewController windowViewController2;
        E0.a lottieProgressManager3;
        LottieProgressManager lottieProgressManager4;
        DynamicIslandContentView realView = getRealView();
        DynamicIslandState state = realView != null ? realView.getState() : null;
        if (state instanceof DynamicIslandState.BigIsland) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator2 == null || (windowView2 = dynamicIslandEventCoordinator2.getWindowView()) == null || (windowViewController2 = windowView2.getWindowViewController()) == null || (lottieProgressManager3 = windowViewController2.getLottieProgressManager()) == null || (lottieProgressManager4 = (LottieProgressManager) lottieProgressManager3.get()) == null) {
                return;
            }
            DynamicIslandData currentIslandData = getCurrentIslandData();
            LottieProgressManager.islandShift$default(lottieProgressManager4, currentIslandData != null ? currentIslandData.getKey() : null, true, false, 4, null);
            return;
        }
        if (!(state instanceof DynamicIslandState.SmallIsland) || (dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator()) == null || (windowView = dynamicIslandEventCoordinator.getWindowView()) == null || (windowViewController = windowView.getWindowViewController()) == null || (lottieProgressManager = windowViewController.getLottieProgressManager()) == null || (lottieProgressManager2 = (LottieProgressManager) lottieProgressManager.get()) == null) {
            return;
        }
        DynamicIslandData currentIslandData2 = getCurrentIslandData();
        LottieProgressManager.islandShift$default(lottieProgressManager2, currentIslandData2 != null ? currentIslandData2.getKey() : null, false, false, 4, null);
    }

    private final void syncLottieProgress(boolean z2) {
        DynamicIslandWindowView windowView;
        DynamicIslandWindowViewController windowViewController;
        E0.a lottieProgressManager;
        LottieProgressManager lottieProgressManager2;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator == null || (windowView = dynamicIslandEventCoordinator.getWindowView()) == null || (windowViewController = windowView.getWindowViewController()) == null || (lottieProgressManager = windowViewController.getLottieProgressManager()) == null || (lottieProgressManager2 = (LottieProgressManager) lottieProgressManager.get()) == null) {
            return;
        }
        DynamicIslandData currentIslandData = getCurrentIslandData();
        lottieProgressManager2.islandExpandShift(currentIslandData != null ? currentIslandData.getKey() : null, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateExpandViewPivot() {
        DynamicIslandContentView realView = getRealView();
        int expandedViewWidth = realView != null ? realView.getExpandedViewWidth() : 0;
        DynamicIslandContentView realView2 = getRealView();
        int expandedViewHeight = realView2 != null ? realView2.getExpandedViewHeight() : 0;
        FrameLayout fakeExpandedView = getFakeExpandedView();
        if (fakeExpandedView != null) {
            fakeExpandedView.setPivotX(expandedViewWidth / 2.0f);
        }
        FrameLayout fakeExpandedView2 = getFakeExpandedView();
        if (fakeExpandedView2 == null) {
            return;
        }
        fakeExpandedView2.setPivotY(expandedViewHeight / 2.0f);
    }

    private final void updateExpandedFakeViewToReal() {
        DynamicIslandData currentIslandData;
        syncLottieProgress(true);
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator != null) {
            DynamicIslandContentView realView = getRealView();
            dynamicIslandEventCoordinator.onWindowAnimExtendLifetimeEnd((realView == null || (currentIslandData = realView.getCurrentIslandData()) == null) ? null : currentIslandData.getExtras());
        }
        setVisibility(4);
        DynamicIslandContentView realView2 = getRealView();
        if (realView2 != null) {
            realView2.setVisibility(0);
        }
        DynamicIslandContentView realView3 = getRealView();
        DynamicIslandBackgroundView backgroundView = realView3 != null ? realView3.getBackgroundView() : null;
        if (backgroundView != null) {
            backgroundView.setVisibility(0);
        }
        updateOutline(this.mFakeViewHeight, this.mFakeViewWidth, true);
    }

    private final void updateOutline(int i2, int i3, boolean z2) {
        DynamicIslandContentView realView = getRealView();
        final int expandedViewMarginHorizontal = realView != null ? realView.getExpandedViewMarginHorizontal() : 0;
        DynamicIslandContentView realView2 = getRealView();
        final int islandViewMarginTop = realView2 != null ? realView2.getIslandViewMarginTop() : 0;
        final x xVar = new x();
        if (z2) {
            i2 += islandViewMarginTop;
        }
        xVar.f5058a = i2;
        int i4 = this.mFakeViewHeight;
        if (i2 < i4 + islandViewMarginTop) {
            xVar.f5058a = i4 + islandViewMarginTop;
        }
        setOutlineProvider(new ViewOutlineProvider() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView.updateOutline.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                n.g(view, "view");
                n.g(outline, "outline");
                DynamicIslandContentFakeView.this.updateExpandViewBlur(xVar.f5058a, true, false);
                outline.setRoundRect(expandedViewMarginHorizontal, islandViewMarginTop, DynamicIslandContentFakeView.this.mFakeViewTrackingParams.getRight(), xVar.f5058a, DynamicIslandContentFakeView.this.radius);
            }
        });
        setClipToOutline(true);
        View miniBar = getMiniBar();
        if (miniBar != null) {
            miniBar.setTranslationY(((xVar.f5058a - miniBar.getTop()) - getMiniBarMarginBottom()) - miniBar.getMeasuredHeight());
        }
    }

    public static /* synthetic */ void updateViewStateWhenCloseEnd$default(DynamicIslandContentFakeView dynamicIslandContentFakeView, boolean z2, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        if ((i2 & 2) != 0) {
            str = "";
        }
        dynamicIslandContentFakeView.updateViewStateWhenCloseEnd(z2, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateViewStateWhenCloseEnd$lambda$7(DynamicIslandContentFakeView this$0) {
        DynamicIslandData currentIslandData;
        n.g(this$0, "this$0");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this$0.getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator != null) {
            DynamicIslandContentView realView = this$0.getRealView();
            dynamicIslandEventCoordinator.alreadyCloseAppEnd((realView == null || (currentIslandData = realView.getCurrentIslandData()) == null) ? null : currentIslandData.getExtras());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateViewStateWhenOpenAnimStart$lambda$9(final DynamicIslandContentFakeView this$0, long j2) {
        n.g(this$0, "this$0");
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "updateViewStateWhenOpenAnimStart");
        this$0.setTranslationX(0.0f);
        this$0.setTranslationY(0.0f);
        FrameLayout fakeExpandedView = this$0.getFakeExpandedView();
        if (fakeExpandedView != null) {
            fakeExpandedView.setTranslationX(0.0f);
        }
        this$0.setLeft(0);
        this$0.setTop(0);
        this$0.setVisibility(0);
        this$0.setAlpha(1.0f);
        DynamicIslandContentView realView = this$0.getRealView();
        DynamicIslandBackgroundView backgroundView = realView != null ? realView.getBackgroundView() : null;
        if (backgroundView != null) {
            backgroundView.setVisibility(4);
        }
        DynamicIslandContentView realView2 = this$0.getRealView();
        if (realView2 != null) {
            realView2.setVisibility(4);
        }
        this$0.setOutlineProvider(new ViewOutlineProvider() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView$updateViewStateWhenOpenAnimStart$1$1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                n.g(view, "view");
                n.g(outline, "outline");
                this.this$0.updateExpandViewPivot();
                this.this$0.updateExpandViewBlur(0, false, true);
                DynamicIslandContentFakeView dynamicIslandContentFakeView = this.this$0;
                Context context = dynamicIslandContentFakeView.getContext();
                n.f(context, "getContext(...)");
                int screenWidth = dynamicIslandContentFakeView.getScreenWidth(context);
                DynamicIslandContentFakeView dynamicIslandContentFakeView2 = this.this$0;
                Context context2 = dynamicIslandContentFakeView2.getContext();
                n.f(context2, "getContext(...)");
                outline.setRoundRect(0, 0, screenWidth, dynamicIslandContentFakeView2.getScreenHeight(context2), this.this$0.radius);
            }
        });
        this$0.setClipToOutline(true);
    }

    public final void alreadyOpenApp() {
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "alreadyOpenApp");
        this.mStartEnterMiniWindow = false;
        setOpenAppFromIsland(false);
    }

    public final void cancelExpandViewTrackingAnim() {
        Log.d(TAG, "cancelExpandViewTrackingAnim");
        IFolme iFolme = this.folme;
        if (iFolme != null) {
            iFolme.cancel();
        }
        View miniBar = getMiniBar();
        if (miniBar != null) {
            miniBar.setVisibility(4);
        }
        DynamicIslandContentView realView = getRealView();
        DynamicIslandBackgroundView backgroundView = realView != null ? realView.getBackgroundView() : null;
        if (backgroundView != null) {
            backgroundView.setVisibility(4);
        }
        DynamicIslandContentView realView2 = getRealView();
        if (realView2 != null) {
            realView2.setVisibility(4);
        }
        this._startEnterMiniWindowBeforeAnimation.setValue(Boolean.FALSE);
        this.miniWindowAlreadyOpen = true;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void clearShadow() {
        MiShadowUtils.clearMiShadow(this);
    }

    public final boolean freeFormGestureBack() {
        DynamicIslandData currentIslandData;
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = getContext();
        n.f(context, "getContext(...)");
        if (!dynamicIslandUtils.isSupportFreeFormAnim(context) || !DynamicIslandAnimUtils.INSTANCE.supportShowElementAndFreeformAnim()) {
            return false;
        }
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "onTouchEvent: 向上滑动");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator == null) {
            return true;
        }
        DynamicIslandContentView realView = getRealView();
        dynamicIslandEventCoordinator.onDynamicPluginCallback("onDynamicPluginCallback_freefrom_gesture_back", (realView == null || (currentIslandData = realView.getCurrentIslandData()) == null) ? null : currentIslandData.getExtras());
        return true;
    }

    public final boolean getAppClosingToExpanded() {
        return this.appClosingToExpanded;
    }

    public final float getBigIslandTx() {
        return this.bigIslandTx;
    }

    public final boolean getBigIslandViewNeedAnim() {
        return this.bigIslandViewNeedAnim;
    }

    public final boolean getClosingAppFromFreeform() {
        return this.closingAppFromFreeform;
    }

    public final boolean getClosingToExpanded() {
        return this.closingToExpanded;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public Rect getExpandedIslandRect() {
        Rect rect = new Rect();
        rect.left = this.mFakeViewLeft;
        rect.top = this.mFakeViewTop;
        rect.right = this.mFakeViewTrackingParams.getRight();
        rect.bottom = this.mFakeViewTrackingParams.getBottom();
        if (this.openFromRealExpandViewRect) {
            rect.bottom = this.mFakeViewBottom;
            this.openFromRealExpandViewRect = false;
        }
        return rect;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public Rect getExpandedPosition() {
        Rect rect = new Rect();
        rect.set(this.mFakeViewLeft, this.mFakeViewTop, this.mFakeViewRight, this.mFakeViewTrackingParams.getBottom() - this.mFakeViewTrackingParams.getTop());
        return rect;
    }

    public final boolean getForceUpdateBigIslandView() {
        return this.forceUpdateBigIslandView;
    }

    public final boolean getMiniWindowClosingToExpanded() {
        return this.miniWindowClosingToExpanded;
    }

    public final I getStartEnterMiniWindowBeforeAnimation() {
        return this.startEnterMiniWindowBeforeAnimation;
    }

    public final I getTrackingToOpenMW() {
        return this.trackingToOpenMW;
    }

    public final boolean handleTouchEvent(MotionEvent motionEvent, boolean z2) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        DynamicIslandWindowView windowView;
        DynamicIslandWindowViewController windowViewController;
        I iIsFreeformAnimRunning;
        DynamicIslandWindowView windowView2;
        DynamicIslandWindowViewController windowViewController2;
        I iIsAppAnimRunning;
        this.velocityTracker.addMovement(motionEvent);
        Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getActionMasked()) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            if (!getMiniBarVisible()) {
                return false;
            }
            DynamicIslandAnimUtils dynamicIslandAnimUtils = DynamicIslandAnimUtils.INSTANCE;
            if ((dynamicIslandAnimUtils.featureDynamicIslandIsMiddle() || dynamicIslandAnimUtils.featureDynamicIslandNoElementButFreeform()) && this.closingAppFromFreeform) {
                return false;
            }
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = getDynamicIslandEventCoordinator();
            boolean zBooleanValue = (dynamicIslandEventCoordinator2 == null || (windowView2 = dynamicIslandEventCoordinator2.getWindowView()) == null || (windowViewController2 = windowView2.getWindowViewController()) == null || (iIsAppAnimRunning = windowViewController2.isAppAnimRunning()) == null) ? false : ((Boolean) iIsAppAnimRunning.getValue()).booleanValue();
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = getDynamicIslandEventCoordinator();
            boolean zIslandAppAnimating = dynamicIslandEventCoordinator3 != null ? dynamicIslandEventCoordinator3.islandAppAnimating(getRealView()) : false;
            boolean z3 = zBooleanValue && !zIslandAppAnimating;
            Log.d(TAG, "isOtherIslandAppAnimRunning : " + zBooleanValue + ",  " + zIslandAppAnimating);
            if (z3) {
                return false;
            }
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator4 = getDynamicIslandEventCoordinator();
            boolean zBooleanValue2 = (dynamicIslandEventCoordinator4 == null || (windowView = dynamicIslandEventCoordinator4.getWindowView()) == null || (windowViewController = windowView.getWindowViewController()) == null || (iIsFreeformAnimRunning = windowViewController.isFreeformAnimRunning()) == null) ? false : ((Boolean) iIsFreeformAnimRunning.getValue()).booleanValue();
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator5 = getDynamicIslandEventCoordinator();
            boolean zIslandFreeformAnimating = dynamicIslandEventCoordinator5 != null ? dynamicIslandEventCoordinator5.islandFreeformAnimating(getRealView()) : false;
            boolean z4 = zBooleanValue2 && !zIslandFreeformAnimating;
            Log.d(TAG, "isOtherIslandFreeformAnimRunning : " + zBooleanValue2 + ",  " + zIslandFreeformAnimating);
            if (z4) {
                return false;
            }
            this.mLastTouchY = motionEvent.getY();
            this.mInitialTouchY = motionEvent.getY();
            DynamicIslandContentView realView = getRealView();
            if (((realView != null ? realView.getState() : null) instanceof DynamicIslandState.Expanded) && (dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator()) != null && dynamicIslandEventCoordinator.canExpandedViewTrack(getRealView())) {
                this.mCanTrackingEnterMW = true;
                this._trackingToOpenMW.setValue(Boolean.FALSE);
                this.miniWindowAlreadyOpen = false;
                onTrackingFakeViewDown();
            }
            BoostHelper.getInstance().setDynamicVIPTaskIfNeeded(1000, this);
            BoostHelper.getInstance().requestDynamicIslandThreadLevelPriority(1000, this);
            return this.mCanTrackingEnterMW;
        }
        if (numValueOf != null && numValueOf.intValue() == 2) {
            float y2 = motionEvent.getY() - this.mLastTouchY;
            if (this.mCanTrackingEnterMW && !((Boolean) this._trackingToOpenMW.getValue()).booleanValue() && y2 > this.mTouchSlop) {
                this.mLastTouchY = motionEvent.getY();
                if (getRealView() != null) {
                    updateFakeExpandedViewState();
                }
                onTrackingFakeViewStart();
                onTrackingFakeViewUpdate(0.0f);
            } else if (((Boolean) this._trackingToOpenMW.getValue()).booleanValue() && !this.miniWindowAlreadyOpen) {
                onTrackingFakeViewUpdate(y2);
            }
            return ((Boolean) this._trackingToOpenMW.getValue()).booleanValue();
        }
        if ((numValueOf == null || numValueOf.intValue() != 1) && (numValueOf == null || numValueOf.intValue() != 3)) {
            Log.e(TAG, "fakeView finally handledTouchEvent: " + this.mCanTrackingEnterMW + ", " + (motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null) + ", " + (motionEvent != null ? Integer.valueOf(motionEvent.getActionMasked()) : null));
            return false;
        }
        if (!(this.mCanTrackingEnterMW && ((Boolean) this._trackingToOpenMW.getValue()).booleanValue()) && this.mStartEnterMiniWindow) {
            return false;
        }
        if (z2 && this.mInitialTouchY - motionEvent.getY() > 100.0f) {
            freeFormGestureBack();
        }
        onTrackingFakeViewEnd();
        Log.d(TAG, "fakeView handled Touch UP/CANCEL: (" + this.mStartEnterMiniWindow + " || " + this.needResetState + ")");
        return this.mStartEnterMiniWindow || this.needResetState;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void hideIslandLayout() {
        super.hideIslandLayout();
        getController().getIslandTemplateFactory().hideView(getCurrentIslandData(), true);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setFakeContainer((FrameLayout) findViewById(R.id.fake_container));
        setFakeExpandedView((FrameLayout) findViewById(R.id.fake_expanded_view));
        setFakeBigIsland((FrameLayout) findViewById(R.id.fake_big_island_view));
        setFakeSmallIsland((FrameLayout) findViewById(R.id.fake_small_island_view));
        setFakeMask(findViewById(R.id.fake_island_mask));
        setMiniBar(findViewById(R.id.mini_window_bar));
        FrameLayout fakeExpandedView = getFakeExpandedView();
        n.e(fakeExpandedView, "null cannot be cast to non-null type android.view.View");
        updateBackgroundBg(fakeExpandedView);
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void onIslandClick() {
        super.onIslandClick();
    }

    public final void onStateChanged(DynamicIslandContentView view) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        DynamicIslandData currentIslandData;
        DynamicIslandData currentIslandData2;
        Bundle extras;
        DynamicIslandContentView current;
        int space;
        DynamicIslandData currentIslandData3;
        Bundle extras2;
        BigIslandStateHandler bigIslandStateHandler;
        Bundle extras3;
        DynamicIslandContentView current2;
        int space2;
        Bundle extras4;
        BigIslandStateHandler bigIslandStateHandler2;
        Bundle extras5;
        Bundle extras6;
        n.g(view, "view");
        DynamicIslandState state = view.getState();
        if (state instanceof DynamicIslandState.AppExpanded) {
            this.suppressBigIslandLayout = false;
        }
        DynamicIslandState lastState = view.getLastState();
        if (lastState instanceof DynamicIslandState.SmallIsland) {
            if (state instanceof DynamicIslandState.BigIsland) {
                Rect bigIslandRect$default = DynamicIslandBaseContentView.getBigIslandRect$default(view, null, 1, null);
                DynamicIslandData currentIslandData4 = view.getCurrentIslandData();
                if (currentIslandData4 != null && (extras6 = currentIslandData4.getExtras()) != null) {
                    extras6.putParcelable("position", bigIslandRect$default);
                }
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = getDynamicIslandEventCoordinator();
                if (dynamicIslandEventCoordinator2 != null) {
                    DynamicIslandData currentIslandData5 = view.getCurrentIslandData();
                    dynamicIslandEventCoordinator2.smallToBig(currentIslandData5 != null ? currentIslandData5.getExtras() : null);
                    return;
                }
                return;
            }
            if (state instanceof DynamicIslandState.Expanded) {
                Rect expandedIslandRect = view.getExpandedIslandRect();
                DynamicIslandData currentIslandData6 = view.getCurrentIslandData();
                if (currentIslandData6 != null && (extras5 = currentIslandData6.getExtras()) != null) {
                    extras5.putParcelable("position", expandedIslandRect);
                }
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = getDynamicIslandEventCoordinator();
                if (dynamicIslandEventCoordinator3 != null) {
                    DynamicIslandData currentIslandData7 = view.getCurrentIslandData();
                    dynamicIslandEventCoordinator3.smallToExpanded(currentIslandData7 != null ? currentIslandData7.getExtras() : null);
                    return;
                }
                return;
            }
            return;
        }
        if (lastState instanceof DynamicIslandState.BigIsland) {
            if (!(state instanceof DynamicIslandState.SmallIsland)) {
                if (state instanceof DynamicIslandState.Expanded) {
                    Rect expandedIslandRect2 = view.getExpandedIslandRect();
                    DynamicIslandData currentIslandData8 = view.getCurrentIslandData();
                    if (currentIslandData8 != null && (extras3 = currentIslandData8.getExtras()) != null) {
                        extras3.putParcelable("position", expandedIslandRect2);
                    }
                    DynamicIslandEventCoordinator dynamicIslandEventCoordinator4 = getDynamicIslandEventCoordinator();
                    if (dynamicIslandEventCoordinator4 != null) {
                        DynamicIslandData currentIslandData9 = view.getCurrentIslandData();
                        dynamicIslandEventCoordinator4.bigToExpanded(currentIslandData9 != null ? currentIslandData9.getExtras() : null);
                        return;
                    }
                    return;
                }
                return;
            }
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator5 = getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator5 == null || (bigIslandStateHandler2 = dynamicIslandEventCoordinator5.getBigIslandStateHandler()) == null || (current2 = bigIslandStateHandler2.getCurrent()) == null) {
                current2 = view;
            }
            Context context = getContext();
            n.f(context, "getContext(...)");
            if (CommonUtils.isLayoutRtl(context)) {
                space2 = (DynamicIslandBaseContentView.getCurrentBigIslandX$default(current2, null, 1, null) - view.getSpace()) - view.getSmallIslandViewWidth();
            } else {
                Boolean bool = Boolean.TRUE;
                space2 = view.getSpace() + current2.getCurrentBigIslandX(bool) + current2.getCurrentBigIslandWidth(bool);
            }
            Rect smallIslandRect = view.getSmallIslandRect(space2);
            DynamicIslandData currentIslandData10 = view.getCurrentIslandData();
            if (currentIslandData10 != null && (extras4 = currentIslandData10.getExtras()) != null) {
                extras4.putParcelable("position", smallIslandRect);
            }
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator6 = getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator6 != null) {
                DynamicIslandData currentIslandData11 = view.getCurrentIslandData();
                dynamicIslandEventCoordinator6.bigToSmall(currentIslandData11 != null ? currentIslandData11.getExtras() : null);
            }
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator7 = getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator7 == null || !dynamicIslandEventCoordinator7.isIslandWindowAnimating(view)) {
                return;
            }
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator8 = getDynamicIslandEventCoordinator();
            setClosingToExpanded(dynamicIslandEventCoordinator8 != null ? dynamicIslandEventCoordinator8.islandFreeformAnimating(view) : false, true);
            return;
        }
        if (lastState instanceof DynamicIslandState.Expanded) {
            if (state instanceof DynamicIslandState.SmallIsland) {
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator9 = getDynamicIslandEventCoordinator();
                if (dynamicIslandEventCoordinator9 == null || (bigIslandStateHandler = dynamicIslandEventCoordinator9.getBigIslandStateHandler()) == null || (current = bigIslandStateHandler.getCurrent()) == null) {
                    current = view;
                }
                Context context2 = getContext();
                n.f(context2, "getContext(...)");
                if (CommonUtils.isLayoutRtl(context2)) {
                    space = (DynamicIslandBaseContentView.getCurrentBigIslandX$default(current, null, 1, null) - view.getSpace()) - view.getSmallIslandViewWidth();
                } else {
                    Boolean bool2 = Boolean.TRUE;
                    space = view.getSpace() + current.getCurrentBigIslandX(bool2) + current.getCurrentBigIslandWidth(bool2);
                }
                Rect smallIslandRect2 = view.getSmallIslandRect(space);
                DynamicIslandData currentIslandData12 = view.getCurrentIslandData();
                if (currentIslandData12 != null && (extras2 = currentIslandData12.getExtras()) != null) {
                    extras2.putParcelable("position", smallIslandRect2);
                }
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator10 = getDynamicIslandEventCoordinator();
                if (dynamicIslandEventCoordinator10 != null) {
                    DynamicIslandData currentIslandData13 = view.getCurrentIslandData();
                    dynamicIslandEventCoordinator10.expandedToSmall(currentIslandData13 != null ? currentIslandData13.getExtras() : null);
                }
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator11 = getDynamicIslandEventCoordinator();
                if (dynamicIslandEventCoordinator11 != null) {
                    DynamicIslandContentView realView = getRealView();
                    if (realView != null && (currentIslandData3 = realView.getCurrentIslandData()) != null) {
                        extras = currentIslandData3.getExtras();
                    }
                    dynamicIslandEventCoordinator11.onWindowAnimExtendLifetimeEnd(extras);
                }
            } else if (state instanceof DynamicIslandState.BigIsland) {
                Rect bigIslandRect$default2 = DynamicIslandBaseContentView.getBigIslandRect$default(view, null, 1, null);
                DynamicIslandData currentIslandData14 = view.getCurrentIslandData();
                if (currentIslandData14 != null && (extras = currentIslandData14.getExtras()) != null) {
                    extras.putParcelable("position", bigIslandRect$default2);
                }
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator12 = getDynamicIslandEventCoordinator();
                if (dynamicIslandEventCoordinator12 != null) {
                    DynamicIslandData currentIslandData15 = view.getCurrentIslandData();
                    dynamicIslandEventCoordinator12.expandedToBig(currentIslandData15 != null ? currentIslandData15.getExtras() : null);
                }
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator13 = getDynamicIslandEventCoordinator();
                if (dynamicIslandEventCoordinator13 != null) {
                    DynamicIslandContentView realView2 = getRealView();
                    if (realView2 != null && (currentIslandData2 = realView2.getCurrentIslandData()) != null) {
                        extras = currentIslandData2.getExtras();
                    }
                    dynamicIslandEventCoordinator13.onWindowAnimExtendLifetimeEnd(extras);
                }
            } else if ((state instanceof DynamicIslandState.Hidden) && (dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator()) != null) {
                DynamicIslandContentView realView3 = getRealView();
                if (realView3 != null && (currentIslandData = realView3.getCurrentIslandData()) != null) {
                    extras = currentIslandData.getExtras();
                }
                dynamicIslandEventCoordinator.onWindowAnimExtendLifetimeEnd(extras);
            }
            if ((state instanceof DynamicIslandState.Expanded) || (state instanceof DynamicIslandState.MiniWindowExpanded) || !((Boolean) this.trackingToOpenMW.getValue()).booleanValue()) {
                return;
            }
            Log.d(TAG, "tracking intercept by expand state change to other state.");
            onTrackingFakeViewEnd();
        }
    }

    public final void resetFakeViewState() {
        FrameLayout fakeBigIsland = getFakeBigIsland();
        if (fakeBigIsland != null) {
            fakeBigIsland.setScaleX(1.0f);
        }
        FrameLayout fakeBigIsland2 = getFakeBigIsland();
        if (fakeBigIsland2 != null) {
            fakeBigIsland2.setScaleY(1.0f);
        }
        FrameLayout fakeBigIsland3 = getFakeBigIsland();
        if (fakeBigIsland3 != null) {
            fakeBigIsland3.setTranslationY(0.0f);
        }
        FrameLayout fakeSmallIsland = getFakeSmallIsland();
        if (fakeSmallIsland != null) {
            fakeSmallIsland.setScaleX(1.0f);
        }
        FrameLayout fakeSmallIsland2 = getFakeSmallIsland();
        if (fakeSmallIsland2 != null) {
            fakeSmallIsland2.setScaleY(1.0f);
        }
        FrameLayout fakeSmallIsland3 = getFakeSmallIsland();
        if (fakeSmallIsland3 != null) {
            fakeSmallIsland3.setTranslationY(0.0f);
        }
        FrameLayout fakeSmallIsland4 = getFakeSmallIsland();
        if (fakeSmallIsland4 != null) {
            fakeSmallIsland4.setTranslationX(0.0f);
        }
        updateExpandViewPivot();
        FrameLayout fakeExpandedView = getFakeExpandedView();
        if (fakeExpandedView != null) {
            fakeExpandedView.setScaleX(1.0f);
        }
        FrameLayout fakeExpandedView2 = getFakeExpandedView();
        if (fakeExpandedView2 != null) {
            fakeExpandedView2.setScaleY(1.0f);
        }
        FrameLayout fakeExpandedView3 = getFakeExpandedView();
        if (fakeExpandedView3 != null) {
            fakeExpandedView3.setTranslationX(0.0f);
        }
        FrameLayout fakeExpandedView4 = getFakeExpandedView();
        if (fakeExpandedView4 != null) {
            fakeExpandedView4.setTranslationY(0.0f);
        }
        View miniBar = getMiniBar();
        if (miniBar != null) {
            miniBar.setScaleX(1.0f);
        }
        View miniBar2 = getMiniBar();
        if (miniBar2 == null) {
            return;
        }
        miniBar2.setScaleY(1.0f);
    }

    public final void resetTrackOpenMW() {
        this._trackingToOpenMW.setValue(Boolean.FALSE);
    }

    public final void setAppClosingToExpanded(boolean z2) {
        this.appClosingToExpanded = z2;
    }

    public final void setBigIslandTx(float f2) {
        this.bigIslandTx = f2;
    }

    public final void setBigIslandViewNeedAnim(boolean z2) {
        this.bigIslandViewNeedAnim = z2;
    }

    public final void setClosingAppFromFreeform(boolean z2) {
        this.closingAppFromFreeform = z2;
    }

    public final void setClosingToExpanded(boolean z2) {
        if (this.closingToExpanded == z2) {
            return;
        }
        Log.d(TAG, "closingToExpanded: " + z2);
        this.closingToExpanded = z2;
    }

    public final void setForceUpdateBigIslandView(boolean z2) {
        this.forceUpdateBigIslandView = z2;
    }

    public final void setMiniWindowClosingToExpanded(boolean z2) {
        this.miniWindowClosingToExpanded = z2;
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        DynamicIslandData currentIslandData;
        int visibility = getVisibility();
        DynamicIslandContentView realView = getRealView();
        Bundle extras = null;
        if ((realView != null ? realView.getState() : null) != null && visibility == 0 && i2 == 4) {
            DynamicIslandContentView realView2 = getRealView();
            if (!((realView2 != null ? realView2.getState() : null) instanceof DynamicIslandState.AppExpanded)) {
                DynamicIslandContentView realView3 = getRealView();
                if (!((realView3 != null ? realView3.getState() : null) instanceof DynamicIslandState.MiniWindowExpanded) && (dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator()) != null && dynamicIslandEventCoordinator.isIslandWindowAnimating(getRealView())) {
                    Log.d(TAG, "hide surface because fakeView visibility change");
                    syncIslandLottieProgress(true);
                    DynamicIslandContentView realView4 = getRealView();
                    if (realView4 != null) {
                        realView4.setVisibility(0);
                    }
                    DynamicIslandContentView realView5 = getRealView();
                    DynamicIslandBackgroundView backgroundView = realView5 != null ? realView5.getBackgroundView() : null;
                    if (backgroundView != null) {
                        backgroundView.setVisibility(0);
                    }
                    DynamicIslandContentView realView6 = getRealView();
                    if (realView6 != null) {
                        realView6.postDelayed(new Runnable() { // from class: miui.systemui.dynamicisland.window.content.c
                            @Override // java.lang.Runnable
                            public final void run() {
                                DynamicIslandContentFakeView.setVisibility$lambda$10(this.f5738a);
                            }
                        }, 50L);
                    }
                    DynamicIslandContentView realView7 = getRealView();
                    if (realView7 != null) {
                        realView7.setOpenAppFromIsland(false);
                    }
                    DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = getDynamicIslandEventCoordinator();
                    if (dynamicIslandEventCoordinator2 != null) {
                        dynamicIslandEventCoordinator2.updateIslandWindowAnimRunning(false, getRealView(), false);
                    }
                    DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = getDynamicIslandEventCoordinator();
                    if (dynamicIslandEventCoordinator3 != null) {
                        DynamicIslandContentView realView8 = getRealView();
                        if (realView8 != null && (currentIslandData = realView8.getCurrentIslandData()) != null) {
                            extras = currentIslandData.getExtras();
                        }
                        dynamicIslandEventCoordinator3.onWindowAnimExtendLifetimeEnd(extras);
                    }
                }
            }
        }
        super.setVisibility(i2);
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void showIslandLayout() {
        super.showIslandLayout();
        getController().getIslandTemplateFactory().showView(getCurrentIslandData(), true);
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void showShadowNoANim() {
        updateShadow(this, getContext().getResources().getColor(R.color.shader_color));
    }

    public final void suppressBigIslandChange(boolean z2) {
        this.suppressBigIslandLayout = z2;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void updateBigIslandLayout() {
        DynamicIslandContentView realView;
        Log.d(TAG, "updateBigIslandLayout,  " + this.suppressBigIslandLayout + ", " + this.bigIslandViewNeedAnim + " " + this.forceUpdateBigIslandView);
        if (this.bigIslandViewNeedAnim && (realView = getRealView()) != null && realView.hasSmallIsland()) {
            this.bigIslandViewNeedAnim = false;
        }
        if (!this.suppressBigIslandLayout || this.bigIslandViewNeedAnim || this.forceUpdateBigIslandView) {
            updateBigIslandLayoutWithAnim(getFakeFirstLayout(), getFakeSecondLayout(), getFakeBigContainer(), getFakeBigIsland(), getRealView(), true);
        }
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public boolean updateBigIslandView(DynamicIslandData dynamicIslandData, boolean z2) {
        FrameLayout fakeBigIsland;
        Log.e(TAG, "updateBigIslandView");
        setCurrentIslandData(dynamicIslandData);
        if (!z2 && (fakeBigIsland = getFakeBigIsland()) != null) {
            fakeBigIsland.removeAllViews();
        }
        IslandTemplateFactory islandTemplateFactory = getController().getIslandTemplateFactory();
        Context context = getContext();
        n.f(context, "getContext(...)");
        FrameLayout fakeBigIsland2 = getFakeBigIsland();
        n.e(fakeBigIsland2, "null cannot be cast to non-null type android.view.ViewGroup");
        if (islandTemplateFactory.createBigIslandTemplate(context, dynamicIslandData, fakeBigIsland2, true, DynamicIslandContentFakeView$updateBigIslandView$view$1.INSTANCE) == null) {
            return false;
        }
        FrameLayout fakeBigIsland3 = getFakeBigIsland();
        setFakeBigContainer(fakeBigIsland3 != null ? (LinearLayout) fakeBigIsland3.findViewById(R.id.fake_big_container) : null);
        FrameLayout fakeBigIsland4 = getFakeBigIsland();
        setFakeFirstLayout(fakeBigIsland4 != null ? (FrameLayout) fakeBigIsland4.findViewById(R.id.fake_area_left) : null);
        FrameLayout fakeBigIsland5 = getFakeBigIsland();
        setFakeSecondLayout(fakeBigIsland5 != null ? (FrameLayout) fakeBigIsland5.findViewById(R.id.fake_area_right) : null);
        return true;
    }

    public final void updateExpandViewBlur(final int i2, final boolean z2, final boolean z3) {
        FrameLayout fakeExpandedView = getFakeExpandedView();
        if (fakeExpandedView != null) {
            Context context = fakeExpandedView.getContext();
            n.f(context, "getContext(...)");
            if (!MiBlurCompat.getBackgroundBlurOpened(context) || fakeExpandedView.getParent() == null) {
                return;
            }
            fakeExpandedView.setOutlineProvider(new ViewOutlineProvider() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView$updateExpandViewBlur$1$1
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view, Outline outline) {
                    int right;
                    int bottom;
                    n.g(view, "view");
                    n.g(outline, "outline");
                    if (z2 || !z3) {
                        right = view.getRight();
                    } else {
                        DynamicIslandContentFakeView dynamicIslandContentFakeView = this;
                        Context context2 = dynamicIslandContentFakeView.getContext();
                        n.f(context2, "getContext(...)");
                        right = dynamicIslandContentFakeView.getScreenWidth(context2);
                    }
                    int i3 = right;
                    if (z2) {
                        bottom = i2;
                    } else if (z3) {
                        DynamicIslandContentFakeView dynamicIslandContentFakeView2 = this;
                        Context context3 = dynamicIslandContentFakeView2.getContext();
                        n.f(context3, "getContext(...)");
                        bottom = dynamicIslandContentFakeView2.getScreenHeight(context3);
                    } else {
                        bottom = view.getBottom();
                    }
                    int i4 = bottom;
                    view.getRenderNode().setPosition(new Rect(view.getLeft(), view.getTop(), i3, i4));
                    outline.setRoundRect(0, 0, i3, i4, this.radius);
                }
            });
        }
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void updateExpandedView(DynamicIslandData dynamicIslandData, boolean z2) {
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        Log.e(TAG, "updateExpandedView, islandData?.fakeView=" + (dynamicIslandData != null ? dynamicIslandData.getFakeView() : null) + " ");
        if ((dynamicIslandData != null ? dynamicIslandData.getFakeView() : null) == null) {
            return;
        }
        View fakeView = dynamicIslandData.getFakeView();
        if ((fakeView != null ? fakeView.getParent() : null) != null) {
            View fakeView2 = dynamicIslandData.getFakeView();
            ViewParent parent = fakeView2 != null ? fakeView2.getParent() : null;
            n.e(parent, "null cannot be cast to non-null type android.view.ViewGroup");
            ((ViewGroup) parent).removeView(dynamicIslandData.getFakeView());
        }
        View fakeView3 = dynamicIslandData.getFakeView();
        if (fakeView3 != null && (layoutParams2 = fakeView3.getLayoutParams()) != null && layoutParams2.width == -1) {
            View fakeView4 = dynamicIslandData.getFakeView();
            ViewGroup.LayoutParams layoutParams3 = fakeView4 != null ? fakeView4.getLayoutParams() : null;
            if (layoutParams3 != null) {
                layoutParams3.width = getExpandedViewMaxWidth();
            }
        }
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.expanded_min_height);
        View fakeView5 = dynamicIslandData.getFakeView();
        int i2 = (fakeView5 == null || (layoutParams = fakeView5.getLayoutParams()) == null) ? 0 : layoutParams.height;
        if (1 <= i2 && i2 < dimensionPixelSize) {
            View fakeView6 = dynamicIslandData.getFakeView();
            ViewGroup.LayoutParams layoutParams4 = fakeView6 != null ? fakeView6.getLayoutParams() : null;
            if (layoutParams4 != null) {
                layoutParams4.height = dimensionPixelSize;
            }
        }
        FrameLayout fakeExpandedView = getFakeExpandedView();
        if (fakeExpandedView != null) {
            fakeExpandedView.removeAllViews();
        }
        FrameLayout fakeExpandedView2 = getFakeExpandedView();
        if (fakeExpandedView2 != null) {
            fakeExpandedView2.addView(dynamicIslandData.getFakeView());
        }
    }

    public final void updateExpandedViewScaleForFreeform(float f2, boolean z2) {
        if (z2) {
            resetExpandedViewScaleForFreeform();
            return;
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator == null || !dynamicIslandEventCoordinator.islandFreeformAnimating(getRealView())) {
            return;
        }
        int i2 = this.mFakeViewWidth;
        float f3 = 1.0f;
        if (i2 > 0.0f && f2 > 0.0f) {
            float f4 = f2 / i2;
            if (!Float.isInfinite(f4) && !Float.isNaN(f4)) {
                f3 = f4;
            }
        }
        FrameLayout fakeExpandedView = getFakeExpandedView();
        if (fakeExpandedView != null) {
            fakeExpandedView.setPivotX(0.0f);
        }
        FrameLayout fakeExpandedView2 = getFakeExpandedView();
        if (fakeExpandedView2 != null) {
            fakeExpandedView2.setPivotY(0.0f);
        }
        FrameLayout fakeExpandedView3 = getFakeExpandedView();
        if (fakeExpandedView3 != null) {
            fakeExpandedView3.setScaleX(f3);
        }
        FrameLayout fakeExpandedView4 = getFakeExpandedView();
        if (fakeExpandedView4 != null) {
            fakeExpandedView4.setScaleY(f3);
        }
        updateExpandViewBlur(getTop() + getHeight(), true, false);
    }

    public final void updateFakeExpandedViewState() {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "updateFakeExpandedViewState: " + this.appClosingToExpanded + ", " + this.closingToExpanded);
        if (this.appClosingToExpanded) {
            resetFakeViewState();
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator2 != null) {
                dynamicIslandEventCoordinator2.resetFakeViewAnimState(getRealView(), true);
            }
        }
        if (!this.miniWindowClosingToExpanded || (dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator()) == null) {
            return;
        }
        dynamicIslandEventCoordinator.resetFakeViewAnimState(getRealView(), false);
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void updateMarginTop() {
        updateMarginTop(getFakeExpandedView());
        updateMarginTop(getFakeBigIsland());
        updateMarginTop(getFakeSmallIsland());
        updateMarginTop(getMiniBar());
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void updateSmallIslandView(DynamicIslandData dynamicIslandData, boolean z2) {
        FrameLayout fakeSmallIsland;
        Log.e(TAG, "updateSmallIslandView");
        if (!z2 && (fakeSmallIsland = getFakeSmallIsland()) != null) {
            fakeSmallIsland.removeAllViews();
        }
        IslandTemplateFactory islandTemplateFactory = getController().getIslandTemplateFactory();
        Context context = getContext();
        n.f(context, "getContext(...)");
        FrameLayout fakeSmallIsland2 = getFakeSmallIsland();
        n.e(fakeSmallIsland2, "null cannot be cast to non-null type android.view.ViewGroup");
        islandTemplateFactory.createSmallIslandTemplate(context, dynamicIslandData, fakeSmallIsland2, true, C06461.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c0, code lost:
    
        if (((r2 != null ? r2.getState() : null) instanceof miui.systemui.dynamicisland.event.DynamicIslandState.MiniWindowExpanded) != false) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateViewStateWhenCloseEnd(boolean r7, java.lang.String r8) {
        /*
            Method dump skipped, instruction units count: 335
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView.updateViewStateWhenCloseEnd(boolean, java.lang.String):void");
    }

    public final void updateViewStateWhenOpenAnimStart() {
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() { // from class: miui.systemui.dynamicisland.window.content.b
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                DynamicIslandContentFakeView.updateViewStateWhenOpenAnimStart$lambda$9(this.f5737a, j2);
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandContentFakeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
        this.velocityTracker = VelocityTracker.obtain();
        this.radius = context.getResources().getDimension(R.dimen.island_radius);
        Boolean bool = Boolean.FALSE;
        u uVarA = K.a(bool);
        this._trackingToOpenMW = uVarA;
        this.trackingToOpenMW = AbstractC0420h.b(uVarA);
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mMaxTriggerThreshold = context.getResources().getDimension(R.dimen.mini_window_max_trigger_threshold);
        this.mTriggerThreshold = context.getResources().getDimension(R.dimen.mini_window_trigger_threshold);
        this.mFakeViewTrackingParams = new FakeViewTrackingParams();
        u uVarA2 = K.a(bool);
        this._startEnterMiniWindowBeforeAnimation = uVarA2;
        this.startEnterMiniWindowBeforeAnimation = AbstractC0420h.b(uVarA2);
        FolmeObject FolmeObject = FolmeKt.FolmeObject();
        FolmeKt.cleanWhenViewDetached(FolmeObject, this);
        this.folmeObject = FolmeObject;
        this.folme = FolmeKt.getFolme(FolmeObject);
        EaseManager.EaseStyle style = EaseManager.getStyle(-2, 0.95f, 0.4f);
        n.f(style, "getStyle(...)");
        this.mTrackingEase = style;
        this.mAlphaEease = new EaseManager.InterpolateEaseStyle(15).setDuration(150L);
        this.mAnimConfig = new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView$mAnimConfig$1
            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                this.this$0.needResetState = false;
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                if (this.this$0.needResetState) {
                    this.this$0.needResetState = false;
                    this.this$0.onTrackingFakeViewReset();
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                UpdateInfo updateInfoFindByName;
                UpdateInfo updateInfoFindByName2;
                super.onUpdate(obj, collection);
                UpdateInfo updateInfoFindByName3 = UpdateInfo.findByName(collection, "alpha");
                if (updateInfoFindByName3 == null || (updateInfoFindByName = UpdateInfo.findByName(collection, "top")) == null || (updateInfoFindByName2 = UpdateInfo.findByName(collection, "bottom")) == null) {
                    return;
                }
                this.this$0.mFakeViewTrackingParams.setAlpha(updateInfoFindByName3.getFloatValue());
                this.this$0.mFakeViewTrackingParams.setTop(updateInfoFindByName.getIntValue());
                this.this$0.mFakeViewTrackingParams.setBottom(updateInfoFindByName2.getIntValue());
                this.this$0.onFakeViewTrackingParamsUpdated();
            }
        });
    }

    public final void setClosingToExpanded(boolean z2, boolean z3) {
        setClosingToExpanded(z3);
        if (z2) {
            this.miniWindowClosingToExpanded = z3;
        } else {
            this.appClosingToExpanded = z3;
        }
    }
}
