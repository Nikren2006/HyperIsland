package miui.systemui.dynamicisland.window.content;

import H0.i;
import I0.r;
import U.d;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.WindowConfiguration;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.MiuiMultiWindowUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.core.graphics.ColorUtils;
import c1.f;
import com.airbnb.lottie.LottieAnimationView;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandContent;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import j1.AbstractC0420h;
import j1.I;
import j1.K;
import j1.u;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.animation.FolmeKt;
import miui.systemui.dynamicisland.DynamicIslandBackgroundView;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.WindowAnimState;
import miui.systemui.dynamicisland.event.handler.BigIslandStateHandler;
import miui.systemui.dynamicisland.event.handler.ExpandedStateHandler;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.view.DynamicIslandBigIslandView;
import miui.systemui.dynamicisland.view.DynamicIslandExpandedView;
import miui.systemui.dynamicisland.window.DynamicIslandAnimUtils;
import miui.systemui.dynamicisland.window.DynamicIslandViewModel;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.FlipUtils;
import miui.systemui.util.FoldUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.SystemBarUtilsCompat;
import miui.systemui.util.WindowConfigurationCompat;
import miuix.animation.FolmeEase;
import miuix.animation.IFolme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.property.ViewProperty;
import miuix.core.util.MiShadowUtils;

/* JADX INFO: loaded from: classes3.dex */
public class DynamicIslandBaseContentView extends FrameLayout {
    private static final float COMPATIBILITY_VALUE = -1.0f;
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DynamicIslandBaseContentView";
    private DynamicIslandBaseContentViewController<?> _controller;
    private final u _highlightColor;
    private final u _isPressed;
    private final u _islandAppAnimRunning;
    private final u _islandFreeformAnimRunning;
    private DynamicIslandAnimationDelegate animatorDelegate;
    private DynamicIslandBackgroundView backgroundView;
    private float batteryWidth;
    private LinearLayout bigContainer;
    private FrameLayout bigIslandAreaLeft;
    private FrameLayout bigIslandAreaRight;
    private int bigIslandLeftWidth;
    private int bigIslandLeftWidthHasSmallIsland;
    private int bigIslandMinWidth;
    private int bigIslandRightWidth;
    private int bigIslandRightWidthHasSmallIsland;
    private DynamicIslandBigIslandView bigIslandView;
    private int bigIslandViewWidth;
    private int bigIslandViewWidthHasSmallIsland;
    private int bigIslandX;
    private int bigIslandXHasSmallIsland;
    private float clockWidth;
    private FrameLayout container;
    private DynamicIslandData currentIslandData;
    private int cutoutHeight;
    private int cutoutWidth;
    private float cutoutY;
    private DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
    private LottieAnimationView expandedLottieView;
    private DynamicIslandExpandedView expandedView;
    private int expandedViewHeight;
    private int expandedViewMarginHorizontal;
    private int expandedViewMaxHeight;
    private int expandedViewMaxWidth;
    private int expandedViewWidth;
    private int expandedViewY;
    private LinearLayout fakeBigContainer;
    private FrameLayout fakeBigIsland;
    private FrameLayout fakeContainer;
    private FrameLayout fakeExpandedView;
    private FrameLayout fakeFirstLayout;
    private View fakeMask;
    private FrameLayout fakeSecondLayout;
    private FrameLayout fakeSmallIsland;
    private DynamicIslandContentFakeView fakeView;
    private final I highlightColor;
    private final I isPressed;
    private final I islandAppAnimRunning;
    private final I islandFreeformAnimRunning;
    private int islandViewHeight;
    private int islandViewMarginTop;
    private int islandWindowHeight;
    private DynamicIslandState lastState;
    private View.OnAttachStateChangeListener listener;
    private View mask;
    private float maxWidth;
    private float maxWidthTiny;
    private View miniBar;
    private final float miniBarHeight;
    private final float miniBarMarginBottom;
    private boolean miniBarVisible;
    private boolean openAppFromIsland;
    private DynamicIslandContentView realView;
    private final RectF roundedRect;
    private boolean showShade;
    private FrameLayout smallContainer;
    private FrameLayout smallIslandView;
    private int smallIslandViewWidth;
    private final int space;
    private DynamicIslandState state;
    private int statusBarHeight;
    private float swipeDiffX;
    private i swipeInfo;
    private IslandTemplate template;
    private List<String> unsupportedExpandedViewSlidePackages;
    private final DynamicIslandViewModel viewModel;
    private WindowAnimState windowAnimState;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public /* synthetic */ DynamicIslandBaseContentView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    private final float calculateMaxWidthWithSmall(StringBuilder sb) {
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = getContext();
        n.f(context, "getContext(...)");
        int screenWidthOld = dynamicIslandUtils.getScreenWidthOld(context);
        Context context2 = getContext();
        n.f(context2, "getContext(...)");
        int iMin = Math.min(screenWidthOld, dynamicIslandUtils.getScreenHeightOld(context2));
        if (FoldUtils.INSTANCE.isFoldScreenLayoutLarge(this)) {
            return iMin * 0.42f;
        }
        float f2 = this.clockWidth;
        if (f2 != COMPATIBILITY_VALUE) {
            float f3 = this.batteryWidth;
            if (f3 != COMPATIBILITY_VALUE) {
                float f4 = iMin / 2;
                float f5 = f4 - f2;
                float f6 = f4 - f3;
                sb.append("calculateBigIslandWidth screenWidth:" + iMin + ", maxWidthLeft:" + f5 + ", maxWidthRight:" + f6);
                n.f(sb, "append(...)");
                sb.append('\n');
                n.f(sb, "append(...)");
                float fMin = Math.min(f5, f6 - ((float) (this.smallIslandViewWidth / 2)));
                float f7 = (((float) 2) * fMin) + ((float) (this.smallIslandViewWidth / 2));
                sb.append("calculateBigIslandWidth maxWidthLeftWithHalfCutOut:" + fMin + ", maxWidthWithSmall:" + f7);
                n.f(sb, "append(...)");
                sb.append('\n');
                n.f(sb, "append(...)");
                return f7;
            }
        }
        return this.maxWidth;
    }

    private final float calculateSwipeRatio(float f2) {
        float f3 = 1;
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        n.f(getContext(), "getContext(...)");
        return f3 - Math.max(0.0f, f3 - Math.min(1.0f, Math.abs(f2 / dynamicIslandUtils.getScreenWidthOld(r3))));
    }

    private final boolean canExpandedViewSlide(DynamicIslandContentView dynamicIslandContentView) {
        PendingIntent pendingIntent;
        Notification notification;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        HashMap<String, Boolean> pkgSupportFreeform;
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener;
        Notification notification2;
        PendingIntent pendingIntent2;
        Notification notification3;
        Notification notification4;
        Integer windowingModeCompat;
        DynamicIslandWindowView windowView;
        DynamicIslandWindowViewController windowViewController;
        DynamicIslandWindowState windowState;
        u screenPinning;
        Bundle extras;
        Bundle extras2;
        Bundle extras3;
        Bundle extras4;
        DynamicIslandWindowView windowView2;
        DynamicIslandWindowViewController windowViewController2;
        DynamicIslandWindowState windowState2;
        u controlCenterExpanded;
        PendingIntent pendingIntent3 = null;
        if (!((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.Expanded)) {
            Log.d(TAG, "can not Slide: state not expanded");
            return false;
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = this.dynamicIslandEventCoordinator;
        if (dynamicIslandEventCoordinator2 != null && dynamicIslandEventCoordinator2.getKeyguardShowing()) {
            Log.d(TAG, "can not Slide: keyguardShowing");
            return false;
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = this.dynamicIslandEventCoordinator;
        if (dynamicIslandEventCoordinator3 != null && (windowView2 = dynamicIslandEventCoordinator3.getWindowView()) != null && (windowViewController2 = windowView2.getWindowViewController()) != null && (windowState2 = windowViewController2.getWindowState()) != null && (controlCenterExpanded = windowState2.getControlCenterExpanded()) != null && ((Boolean) controlCenterExpanded.getValue()).booleanValue()) {
            Log.d(TAG, "can not Slide: controlCenterExpanded");
            return false;
        }
        DynamicIslandData currentIslandData = dynamicIslandContentView.getCurrentIslandData();
        String string = (currentIslandData == null || (extras4 = currentIslandData.getExtras()) == null) ? null : extras4.getString("miui.pkg.name");
        DynamicIslandData dynamicIslandData = this.currentIslandData;
        Integer numValueOf = (dynamicIslandData == null || (extras3 = dynamicIslandData.getExtras()) == null) ? null : Integer.valueOf(extras3.getInt("miui.user.id"));
        DynamicIslandData dynamicIslandData2 = this.currentIslandData;
        StatusBarNotification statusBarNotification = (dynamicIslandData2 == null || (extras2 = dynamicIslandData2.getExtras()) == null) ? null : (StatusBarNotification) extras2.getParcelable("miui.sbn", StatusBarNotification.class);
        DynamicIslandData currentIslandData2 = dynamicIslandContentView.getCurrentIslandData();
        PendingIntent pendingIntent4 = (currentIslandData2 == null || (extras = currentIslandData2.getExtras()) == null) ? null : (PendingIntent) extras.getParcelable("miui.pending.intent", PendingIntent.class);
        if (statusBarNotification != null) {
            Notification notification5 = statusBarNotification.getNotification();
            pendingIntent = notification5 != null ? notification5.contentIntent : null;
        } else {
            pendingIntent = pendingIntent4;
        }
        Intent intent = pendingIntent != null ? pendingIntent.getIntent() : null;
        if (intent != null) {
            DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
            Context context = getContext();
            n.f(context, "getContext(...)");
            String strIsIntentActivityExist = dynamicIslandUtils.isIntentActivityExist(context, intent, string);
            Log.d(TAG, "pkg: " + string + ", pakName: " + strIsIntentActivityExist);
            string = strIsIntentActivityExist;
        }
        if (I0.u.F(this.unsupportedExpandedViewSlidePackages, string)) {
            Log.d(TAG, "can not Slide: smartHome or aliPayGPhone");
            return false;
        }
        Context context2 = getContext();
        n.f(context2, "getContext(...)");
        if (CommonUtils.isTinyScreen(context2)) {
            Log.d(TAG, "can not Slide: isTinyScreen");
            return false;
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator4 = this.dynamicIslandEventCoordinator;
        if (n.c(dynamicIslandEventCoordinator4 != null ? dynamicIslandEventCoordinator4.getLastFullScreenActivityPkg() : null, string)) {
            Log.d(TAG, "can not Slide: lastFullScreenActivityPkg " + string);
            return false;
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator5 = this.dynamicIslandEventCoordinator;
        if (dynamicIslandEventCoordinator5 != null && (windowView = dynamicIslandEventCoordinator5.getWindowView()) != null && (windowViewController = windowView.getWindowViewController()) != null && (windowState = windowViewController.getWindowState()) != null && (screenPinning = windowState.getScreenPinning()) != null && ((Boolean) screenPinning.getValue()).booleanValue()) {
            Log.d(TAG, "can not Slide: screenPinningActive");
            return false;
        }
        if (!DynamicIslandAnimUtils.INSTANCE.getSUPPORT_FREEFORM()) {
            Log.d(TAG, "can not Slide: device not support freeform");
            return false;
        }
        if (!MiuiMultiWindowUtils.multiFreeFormSupported(getContext())) {
            WindowConfigurationCompat windowConfigurationCompat = WindowConfigurationCompat.INSTANCE;
            Configuration configuration = getContext().getResources().getConfiguration();
            n.f(configuration, "getConfiguration(...)");
            WindowConfiguration windowConfigurationCompat2 = windowConfigurationCompat.getWindowConfigurationCompat(configuration);
            int iIntValue = (windowConfigurationCompat2 == null || (windowingModeCompat = windowConfigurationCompat.getWindowingModeCompat(windowConfigurationCompat2)) == null) ? 0 : windowingModeCompat.intValue();
            if (n.c(windowConfigurationCompat.inMultiWindowMode(iIntValue), Boolean.TRUE)) {
                Log.d(TAG, "can not Slide: inMultiWindowMode " + iIntValue);
                return false;
            }
        }
        if (pendingIntent4 != null) {
            Log.d(TAG, "can not Slide: pendingIntent != null");
            return false;
        }
        if (((statusBarNotification == null || (notification4 = statusBarNotification.getNotification()) == null) ? null : notification4.contentIntent) == null) {
            Log.d(TAG, "can not Slide: intent is null");
            return false;
        }
        if (((statusBarNotification == null || (notification3 = statusBarNotification.getNotification()) == null) ? null : notification3.contentIntent) != null && (notification2 = statusBarNotification.getNotification()) != null && (pendingIntent2 = notification2.contentIntent) != null && !pendingIntent2.isActivity()) {
            Log.d(TAG, "can not Slide: contentIntent is not activity");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString(DynamicIslandConstants.ACTION_KEY, DynamicIslandConstants.ACTION_HAS_PIP_IN_SCREEN);
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator6 = this.dynamicIslandEventCoordinator;
        Bundle bundleOnIslandViewChanged = (dynamicIslandEventCoordinator6 == null || (dynamicIslandViewChangedListener = dynamicIslandEventCoordinator6.getDynamicIslandViewChangedListener()) == null) ? null : dynamicIslandViewChangedListener.onIslandViewChanged(bundle);
        if (n.c(bundleOnIslandViewChanged != null ? bundleOnIslandViewChanged.getString(DynamicIslandConstants.EXTRA_PACKAGE_PIP_IN_SCREEN) : null, string)) {
            Log.d(TAG, "can not Slide: in pip state");
            return false;
        }
        DynamicIslandUtils dynamicIslandUtils2 = DynamicIslandUtils.INSTANCE;
        Context context3 = getContext();
        n.f(context3, "getContext(...)");
        if (dynamicIslandUtils2.isSplitPkg(string, context3)) {
            Log.d(TAG, "can not Slide: isSplitPkg " + string);
            return false;
        }
        if (dynamicIslandUtils2.isPinMode(string, pendingIntent)) {
            Log.d(TAG, "can not Slide: isPinMode");
            return false;
        }
        packageSupportFreeform(string);
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator7 = this.dynamicIslandEventCoordinator;
        if (!((dynamicIslandEventCoordinator7 == null || (pkgSupportFreeform = dynamicIslandEventCoordinator7.getPkgSupportFreeform()) == null) ? false : n.c(pkgSupportFreeform.get(string), Boolean.TRUE))) {
            Log.d(TAG, "can not Slide: pkg notSupportFreeform: " + string);
            return false;
        }
        if (n.c((string == null || (dynamicIslandEventCoordinator = this.dynamicIslandEventCoordinator) == null) ? null : Boolean.valueOf(dynamicIslandEventCoordinator.isDynamicIslandMiniWindowBlackList(string)), Boolean.TRUE)) {
            Context context4 = getContext();
            n.f(context4, "getContext(...)");
            if (statusBarNotification != null && (notification = statusBarNotification.getNotification()) != null) {
                pendingIntent3 = notification.contentIntent;
            }
            if (!dynamicIslandUtils2.activitySupportFreeform(context4, pendingIntent3)) {
                Log.d(TAG, "blackList and activity not isResizeableMode");
                return false;
            }
        }
        Context context5 = getContext();
        n.f(context5, "getContext(...)");
        if (!dynamicIslandUtils2.isAppInstalledForUser(context5, string, numValueOf)) {
            Log.d(TAG, "can not Slide: not isAppInstalledForUser");
            return false;
        }
        if (string != null && numValueOf != null) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator8 = this.dynamicIslandEventCoordinator;
            if (dynamicIslandEventCoordinator8 != null && dynamicIslandEventCoordinator8.isInLockMode(string, numValueOf.intValue())) {
                Log.d(TAG, "can not Slide: in Locked ");
                return false;
            }
        }
        return true;
    }

    private final float computeBigIslandTx(int i2, int i3, int i4, int i5) {
        int iMin;
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.island_area_padding);
        Context context = getContext();
        n.f(context, "getContext(...)");
        if (CommonUtils.isLayoutRtl(context)) {
            int i6 = (i2 + i3) - (i4 + i5);
            iMin = i3 == i5 ? -i6 : i6 != 0 ? dimensionPixelSize : Math.min(dimensionPixelSize, i3 - i5);
            Log.d(TAG, "Rtl updateBigIslandLayout,  diff: " + i6 + ", padding: " + dimensionPixelSize + ", translationX: " + iMin);
        } else {
            int i7 = i2 - i4;
            iMin = i7 != 0 ? -dimensionPixelSize : i3 == i5 ? -i7 : -Math.min(dimensionPixelSize, i3 - i5);
            Log.d(TAG, "updateBigIslandLayout,  diff: " + i7 + ", padding: " + dimensionPixelSize + ", translationX: " + iMin);
        }
        return iMin;
    }

    private final void doBigIslandAnim(final int i2, final int i3, int i4, float f2, FrameLayout frameLayout, FrameLayout frameLayout2, LinearLayout linearLayout, FrameLayout frameLayout3, DynamicIslandContentView dynamicIslandContentView, final boolean z2) {
        IFolme folme;
        IStateStyle iStateStyleState;
        IFolme folme2;
        IStateStyle iStateStyleState2;
        IFolme folme3;
        IStateStyle iStateStyleState3;
        IFolme folme4;
        IStateStyle iStateStyleState4;
        AnimConfig ease = new AnimConfig().setEase(FolmeEase.cubicOut(300L));
        AnimConfig animConfigAddListeners = new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView$doBigIslandAnim$showConfig$1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                this.this$0.getController().getIslandTemplateFactory().updateCutoutWidth(this.this$0.getCurrentIslandData(), this.this$0.getCutoutWidth(), z2);
                this.this$0.getController().getIslandTemplateFactory().updateLeftWidth(this.this$0.getCurrentIslandData(), i2, z2);
                this.this$0.getController().getIslandTemplateFactory().updateRightWidth(this.this$0.getCurrentIslandData(), i3, z2);
            }
        });
        if (frameLayout != null && (folme4 = FolmeKt.getFolme(frameLayout)) != null && (iStateStyleState4 = folme4.state()) != null) {
            iStateStyleState4.to(new AnimState().add(ViewProperty.WIDTH, i2, new long[0]), ease);
        }
        if (frameLayout2 != null && (folme3 = FolmeKt.getFolme(frameLayout2)) != null && (iStateStyleState3 = folme3.state()) != null) {
            iStateStyleState3.to(new AnimState().add(ViewProperty.WIDTH, i3, new long[0]), animConfigAddListeners, ease);
        }
        if (linearLayout != null && (folme2 = FolmeKt.getFolme(linearLayout)) != null && (iStateStyleState2 = folme2.state()) != null) {
            iStateStyleState2.to(new AnimState().add(ViewProperty.WIDTH, i4, new long[0]), ease);
        }
        if (frameLayout3 != null && (folme = FolmeKt.getFolme(frameLayout3)) != null && (iStateStyleState = folme.state()) != null) {
            iStateStyleState.to(new AnimState().add(ViewProperty.TRANSLATION_X, f2, new long[0]), ease);
        }
        DynamicIslandContentFakeView fakeView = dynamicIslandContentView != null ? dynamicIslandContentView.getFakeView() : null;
        if (fakeView == null) {
            return;
        }
        fakeView.setBigIslandTx(f2);
    }

    public static /* synthetic */ Rect getBigIslandRect$default(DynamicIslandBaseContentView dynamicIslandBaseContentView, Boolean bool, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getBigIslandRect");
        }
        if ((i2 & 1) != 0) {
            bool = null;
        }
        return dynamicIslandBaseContentView.getBigIslandRect(bool);
    }

    public static /* synthetic */ int getCurrentBigIslandWidth$default(DynamicIslandBaseContentView dynamicIslandBaseContentView, Boolean bool, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCurrentBigIslandWidth");
        }
        if ((i2 & 1) != 0) {
            bool = null;
        }
        return dynamicIslandBaseContentView.getCurrentBigIslandWidth(bool);
    }

    public static /* synthetic */ int getCurrentBigIslandX$default(DynamicIslandBaseContentView dynamicIslandBaseContentView, Boolean bool, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCurrentBigIslandX");
        }
        if ((i2 & 1) != 0) {
            bool = null;
        }
        return dynamicIslandBaseContentView.getCurrentBigIslandX(bool);
    }

    private final void hideExpandedLottie() {
        Log.d(TAG, "hideIslandLayout " + this.expandedLottieView);
        LottieAnimationView lottieAnimationView = this.expandedLottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.pauseAnimation();
        }
    }

    private final void packageSupportFreeform(String str) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        HashMap<String, Boolean> pkgSupportFreeform;
        if (str != null) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = this.dynamicIslandEventCoordinator;
            if (((dynamicIslandEventCoordinator2 == null || (pkgSupportFreeform = dynamicIslandEventCoordinator2.getPkgSupportFreeform()) == null) ? null : pkgSupportFreeform.get(str)) != null || (dynamicIslandEventCoordinator = this.dynamicIslandEventCoordinator) == null) {
                return;
            }
            Context context = getContext();
            n.f(context, "getContext(...)");
            dynamicIslandEventCoordinator.updatePkgSupportFreeform(str, context);
        }
    }

    private final void reset() {
        this.expandedViewMaxWidth = getContext().getResources().getDimensionPixelSize(R.dimen.expanded_max_width);
        this.expandedViewMaxHeight = getContext().getResources().getDimensionPixelSize(R.dimen.expanded_max_height);
        Resources resources = getContext().getResources();
        int i2 = R.dimen.island_height;
        this.islandViewHeight = resources.getDimensionPixelSize(i2);
        this.smallIslandViewWidth = getContext().getResources().getDimensionPixelSize(R.dimen.small_island_width);
        FrameLayout frameLayout = this.smallContainer;
        ViewGroup.LayoutParams layoutParams = frameLayout != null ? frameLayout.getLayoutParams() : null;
        if (layoutParams != null) {
            layoutParams.width = this.smallIslandViewWidth;
        }
        FrameLayout frameLayout2 = this.smallContainer;
        ViewGroup.LayoutParams layoutParams2 = frameLayout2 != null ? frameLayout2.getLayoutParams() : null;
        if (layoutParams2 != null) {
            layoutParams2.height = this.smallIslandViewWidth;
        }
        LinearLayout linearLayout = this.bigContainer;
        ViewGroup.LayoutParams layoutParams3 = linearLayout != null ? linearLayout.getLayoutParams() : null;
        if (layoutParams3 == null) {
            return;
        }
        layoutParams3.height = getResources().getDimensionPixelSize(i2);
    }

    private final void showExpandedLottie() {
        LottieAnimationView lottieAnimationView;
        Bundle extras;
        DynamicIslandData dynamicIslandData = this.currentIslandData;
        Boolean boolValueOf = (dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) ? null : Boolean.valueOf(extras.getBoolean("miui.focus.lottieView.isPlay"));
        Log.d(TAG, "showExpandedLottie " + this.expandedLottieView);
        if (!n.c(boolValueOf, Boolean.TRUE) || (lottieAnimationView = this.expandedLottieView) == null) {
            return;
        }
        lottieAnimationView.playAnimation();
    }

    private final void updateExpandedLottieView() {
        Integer numValueOf;
        Bundle extras;
        Bundle extras2;
        DynamicIslandData dynamicIslandData = this.currentIslandData;
        LottieAnimationView lottieAnimationView = null;
        if (dynamicIslandData == null || (extras2 = dynamicIslandData.getExtras()) == null) {
            DynamicIslandData dynamicIslandData2 = this.currentIslandData;
            numValueOf = (dynamicIslandData2 == null || (extras = dynamicIslandData2.getExtras()) == null) ? null : Integer.valueOf(extras.getInt("miui.focus.lottieView.fake.id"));
        } else {
            numValueOf = Integer.valueOf(extras2.getInt("miui.focus.lottieView.id"));
        }
        if (numValueOf != null) {
            int iIntValue = numValueOf.intValue();
            FrameLayout frameLayout = this.expandedView;
            if (frameLayout == null) {
                frameLayout = this.fakeExpandedView;
            }
            if (frameLayout != null) {
                lottieAnimationView = (LottieAnimationView) frameLayout.findViewById(iIntValue);
            }
        }
        this.expandedLottieView = lottieAnimationView;
    }

    private static final int updateMedianLuma$getStrokeColor(DynamicIslandBaseContentView dynamicIslandBaseContentView, float f2) {
        return ColorUtils.setAlphaComponent(dynamicIslandBaseContentView.getContext().getResources().getColor(R.color.stroke_color), (int) (Color.alpha(r2) * f.h(1 - f2, 0.0f, 1.0f)));
    }

    private final void updateMiniBarTranslation(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandContentFakeView fakeView;
        I trackingToOpenMW;
        View view = this.miniBar;
        if (view != null) {
            int expandedViewHeight = dynamicIslandContentView != null ? dynamicIslandContentView.getExpandedViewHeight() : 0;
            view.setTranslationX(0.0f);
            if ((dynamicIslandContentView != null ? dynamicIslandContentView.getFakeView() : null) == null || !((fakeView = dynamicIslandContentView.getFakeView()) == null || (trackingToOpenMW = fakeView.getTrackingToOpenMW()) == null || ((Boolean) trackingToOpenMW.getValue()).booleanValue())) {
                view.setTranslationY((expandedViewHeight - this.miniBarMarginBottom) - this.miniBarHeight);
            }
        }
    }

    private final void updateTemplate(DynamicIslandData dynamicIslandData) {
        String tickerData;
        try {
            IslandTemplate islandTemplate = (dynamicIslandData == null || (tickerData = dynamicIslandData.getTickerData()) == null) ? null : (IslandTemplate) new d().j(tickerData, IslandTemplate.class);
            if (islandTemplate != null) {
                this.template = islandTemplate;
                this._highlightColor.setValue(islandTemplate.getHighlightColor());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static /* synthetic */ boolean updateView$default(DynamicIslandBaseContentView dynamicIslandBaseContentView, DynamicIslandData dynamicIslandData, boolean z2, boolean z3, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateView");
        }
        if ((i2 & 4) != 0) {
            z3 = true;
        }
        return dynamicIslandBaseContentView.updateView(dynamicIslandData, z2, z3);
    }

    private final void updateViewLayoutParams(View view, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = i2;
            layoutParams.height = i3;
            view.setLayoutParams(layoutParams);
        }
    }

    public void calculateBigIslandWidth(View view, View view2) {
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        StringBuilder sb = new StringBuilder();
        if (FlipUtils.isFlipTiny()) {
            int cutoutWidth = getCutoutWidth();
            DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
            n.f(getContext(), "getContext(...)");
            float fDpToPx = 810.0f - dynamicIslandUtils.dpToPx(32, r9);
            this.maxWidthTiny = fDpToPx;
            float f2 = cutoutWidth;
            float f3 = 2;
            int i2 = (int) ((fDpToPx - f2) / f3);
            this.bigIslandLeftWidth = i2;
            int i3 = (int) ((fDpToPx - f2) / f3);
            this.bigIslandRightWidth = i3;
            this.bigIslandViewWidth = Math.min(i2 + i3 + cutoutWidth, (int) fDpToPx);
            Context context = getContext();
            n.f(context, "getContext(...)");
            int screenWidthOld = dynamicIslandUtils.getScreenWidthOld(context);
            int i4 = this.bigIslandViewWidth;
            int i5 = (screenWidthOld - i4) / 2;
            this.bigIslandX = i5;
            sb.append("calculateBigIslandWidth isTinyScreen bigIslandLeftWidth=" + this.bigIslandLeftWidth + " bigIslandRightWidth=" + this.bigIslandRightWidth + " bigIslandViewWidth=" + i4 + " bigIslandX=" + i5);
            n.f(sb, "append(...)");
            sb.append('\n');
            n.f(sb, "append(...)");
            updateBigIslandLayout();
            return;
        }
        Context context2 = getContext();
        n.f(context2, "getContext(...)");
        boolean zIsLayoutRtl = CommonUtils.isLayoutRtl(context2);
        int measuredWidth = view != null ? view.getMeasuredWidth() : 0;
        int measuredWidth2 = view2 != null ? view2.getMeasuredWidth() : 0;
        this.bigIslandMinWidth = getContext().getResources().getDimensionPixelSize(R.dimen.big_island_min_width);
        Integer numValueOf = null;
        Integer numValueOf2 = view != null ? Integer.valueOf(view.getWidth()) : null;
        Integer numValueOf3 = view2 != null ? Integer.valueOf(view2.getWidth()) : null;
        Integer numValueOf4 = (view == null || (layoutParams2 = view.getLayoutParams()) == null) ? null : Integer.valueOf(layoutParams2.width);
        if (view2 != null && (layoutParams = view2.getLayoutParams()) != null) {
            numValueOf = Integer.valueOf(layoutParams.width);
        }
        sb.append("calculateBigIslandWidth bigIsland " + this + numValueOf2 + " " + numValueOf3 + " " + numValueOf4 + " " + numValueOf + " " + measuredWidth + " " + measuredWidth2 + " " + this.bigIslandMinWidth);
        n.f(sb, "append(...)");
        sb.append('\n');
        n.f(sb, "append(...)");
        if (this.bigIslandLeftWidth == 0) {
            this.bigIslandLeftWidth = measuredWidth;
        }
        if (this.bigIslandRightWidth == 0) {
            this.bigIslandRightWidth = measuredWidth2;
        }
        sb.append("calculateBigIslandWidth bigIslandLeftWidth=" + this.bigIslandLeftWidth + " bigIslandRightWidth=" + this.bigIslandRightWidth);
        n.f(sb, "append(...)");
        sb.append('\n');
        n.f(sb, "append(...)");
        int cutoutWidth2 = getCutoutWidth();
        float fMin = this.maxWidth;
        if (FoldUtils.INSTANCE.isFoldScreenLayoutLarge(this)) {
            Log.e(TAG, "calculateBigIslandWidth isFoldScreenLayoutLarge");
            DynamicIslandUtils dynamicIslandUtils2 = DynamicIslandUtils.INSTANCE;
            Context context3 = getContext();
            n.f(context3, "getContext(...)");
            int screenWidthOld2 = dynamicIslandUtils2.getScreenWidthOld(context3);
            n.f(getContext(), "getContext(...)");
            fMin = Math.min(screenWidthOld2, dynamicIslandUtils2.getScreenHeightOld(r13)) * 0.42f;
        }
        sb.append("calculateBigIslandWidth cutoutWidth " + cutoutWidth2 + " " + fMin);
        n.f(sb, "append(...)");
        sb.append('\n');
        n.f(sb, "append(...)");
        int iMax = Math.max(measuredWidth, measuredWidth2);
        this.bigIslandLeftWidth = iMax;
        this.bigIslandRightWidth = iMax;
        int iMin = Math.min(iMax + iMax + cutoutWidth2, (int) fMin);
        this.bigIslandViewWidth = iMin;
        this.bigIslandViewWidth = Math.max(iMin, this.bigIslandMinWidth);
        DynamicIslandUtils dynamicIslandUtils3 = DynamicIslandUtils.INSTANCE;
        Context context4 = getContext();
        n.f(context4, "getContext(...)");
        int screenWidthOld3 = dynamicIslandUtils3.getScreenWidthOld(context4);
        int i6 = this.bigIslandViewWidth;
        int i7 = (screenWidthOld3 - i6) / 2;
        this.bigIslandX = i7;
        sb.append("calculateBigIslandWidth bigIslandX " + i7 + " " + this.bigIslandLeftWidth + " " + this.bigIslandRightWidth + " " + i6);
        n.f(sb, "append(...)");
        sb.append('\n');
        n.f(sb, "append(...)");
        int i8 = this.bigIslandViewWidth;
        int i9 = this.bigIslandLeftWidth;
        int i10 = this.bigIslandRightWidth;
        if (i8 < i9 + i10 + cutoutWidth2) {
            int i11 = (((i9 + i10) + cutoutWidth2) - i8) / 2;
            this.bigIslandLeftWidth = i9 - i11;
            this.bigIslandRightWidth = i10 - i11;
        } else {
            int i12 = (((i8 - i9) - i10) - cutoutWidth2) / 2;
            this.bigIslandLeftWidth = i9 + i12;
            this.bigIslandRightWidth = i10 + i12;
        }
        int i13 = this.smallIslandViewWidth;
        sb.append("calculateBigIslandWidth smallWidth:" + i13 + ",space:" + this.space);
        n.f(sb, "append(...)");
        sb.append('\n');
        n.f(sb, "append(...)");
        int i14 = i13 / 2;
        if (measuredWidth - measuredWidth2 > i14) {
            sb.append("calculateBigIslandWidth1");
            n.f(sb, "append(...)");
            sb.append('\n');
            n.f(sb, "append(...)");
            this.bigIslandLeftWidthHasSmallIsland = measuredWidth;
            this.bigIslandRightWidthHasSmallIsland = measuredWidth - i14;
        } else {
            sb.append("calculateBigIslandWidth2");
            n.f(sb, "append(...)");
            sb.append('\n');
            n.f(sb, "append(...)");
            this.bigIslandLeftWidthHasSmallIsland = i14 + measuredWidth2;
            this.bigIslandRightWidthHasSmallIsland = measuredWidth2;
        }
        int i15 = this.bigIslandLeftWidthHasSmallIsland + this.bigIslandRightWidthHasSmallIsland + cutoutWidth2 + this.space + i13;
        sb.append("calculateBigIslandWidth measuredBigIslandWithSmallWidth:" + i15);
        n.f(sb, "append(...)");
        sb.append('\n');
        n.f(sb, "append(...)");
        int iC = f.c(f.f(i15, (int) calculateMaxWidthWithSmall(sb)), this.bigIslandMinWidth + this.space + i13);
        sb.append("calculateBigIslandWidth bigIslandViewWithSmallWidth:" + iC);
        n.f(sb, "append(...)");
        sb.append('\n');
        n.f(sb, "append(...)");
        if (iC < i15) {
            int i16 = (i15 - iC) / 2;
            this.bigIslandLeftWidthHasSmallIsland -= i16;
            this.bigIslandRightWidthHasSmallIsland -= i16;
        } else {
            int i17 = (iC - i15) / 2;
            this.bigIslandLeftWidthHasSmallIsland += i17;
            this.bigIslandRightWidthHasSmallIsland += i17;
        }
        sb.append("calculateBigIslandWidth left1:" + this.bigIslandLeftWidthHasSmallIsland + ",right1:" + this.bigIslandRightWidthHasSmallIsland);
        n.f(sb, "append(...)");
        sb.append('\n');
        n.f(sb, "append(...)");
        this.bigIslandViewWidthHasSmallIsland = this.bigIslandLeftWidthHasSmallIsland + cutoutWidth2 + this.bigIslandRightWidthHasSmallIsland;
        Context context5 = getContext();
        n.f(context5, "getContext(...)");
        int i18 = cutoutWidth2 / 2;
        this.bigIslandXHasSmallIsland = ((dynamicIslandUtils3.getScreenWidthOld(context5) / 2) - i18) - this.bigIslandLeftWidthHasSmallIsland;
        if (zIsLayoutRtl) {
            Context context6 = getContext();
            n.f(context6, "getContext(...)");
            this.bigIslandXHasSmallIsland = (((dynamicIslandUtils3.getScreenWidthOld(context6) / 2) + i18) + this.bigIslandLeftWidthHasSmallIsland) - this.bigIslandViewWidthHasSmallIsland;
        }
        sb.append("calculateBigIslandWidth bigIslandXHasSmallIsland1 " + this.bigIslandXHasSmallIsland + " " + this.bigIslandLeftWidthHasSmallIsland + " " + this.bigIslandRightWidthHasSmallIsland + " " + this.bigIslandViewWidthHasSmallIsland);
        n.f(sb, "append(...)");
        sb.append('\n');
        n.f(sb, "append(...)");
        Log.e(TAG, sb.toString());
        updateBigIslandLayout();
    }

    public final void calculateBigIslandY() {
        reset();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.island_mini_y);
        SystemBarUtilsCompat systemBarUtilsCompat = SystemBarUtilsCompat.INSTANCE;
        Context context = getContext();
        n.f(context, "getContext(...)");
        Integer statusBarHeightCompat = systemBarUtilsCompat.getStatusBarHeightCompat(context);
        this.statusBarHeight = statusBarHeightCompat != null ? statusBarHeightCompat.intValue() : 0;
        int iC = CommonUtils.isNotchScreenDevice() ? 0 : f.c((int) (this.cutoutY - (this.islandViewHeight / 2)), dimensionPixelSize);
        this.islandViewMarginTop = iC;
        int i2 = this.statusBarHeight;
        int i3 = this.islandViewHeight;
        int dimensionPixelSize2 = i2 > iC + i3 ? i2 + getResources().getDimensionPixelSize(R.dimen.island_expanded_padding_top) : getResources().getDimensionPixelSize(R.dimen.island_expanded_padding_top_1) + iC + i3;
        this.expandedViewY = dimensionPixelSize2;
        this.islandWindowHeight = dimensionPixelSize2;
        Log.e(TAG, "calculateBigIslandY " + this.cutoutY + " " + this.statusBarHeight + " " + dimensionPixelSize2 + " " + this.islandViewHeight + " " + this.islandViewMarginTop);
        updateMarginTop();
    }

    public void clearShadow() {
    }

    public final DynamicIslandAnimationDelegate getAnimator(DynamicIslandAnimationDelegate.Factory factory) {
        n.g(factory, "factory");
        if (this.animatorDelegate == null) {
            this.animatorDelegate = factory.create(this);
        }
        return this.animatorDelegate;
    }

    public final DynamicIslandAnimationDelegate getAnimatorDelegate() {
        return this.animatorDelegate;
    }

    public final DynamicIslandBackgroundView getBackgroundView() {
        return this.backgroundView;
    }

    public final LinearLayout getBigContainer() {
        return this.bigContainer;
    }

    public final FrameLayout getBigIslandAreaLeft() {
        return this.bigIslandAreaLeft;
    }

    public final FrameLayout getBigIslandAreaRight() {
        return this.bigIslandAreaRight;
    }

    public final int getBigIslandLeftWidth() {
        return this.bigIslandLeftWidth;
    }

    public final int getBigIslandLeftWidthHasSmallIsland() {
        return this.bigIslandLeftWidthHasSmallIsland;
    }

    public Rect getBigIslandPosition() {
        return new Rect();
    }

    public final Rect getBigIslandRect(Boolean bool) {
        Rect rect = new Rect();
        int currentBigIslandX = getCurrentBigIslandX(bool);
        rect.left = currentBigIslandX;
        rect.top = this.islandViewMarginTop;
        rect.right = currentBigIslandX + getCurrentBigIslandWidth(bool);
        rect.bottom = rect.top + this.islandViewHeight;
        return rect;
    }

    public final int getBigIslandRightWidth() {
        return this.bigIslandRightWidth;
    }

    public final int getBigIslandRightWidthHasSmallIsland() {
        return this.bigIslandRightWidthHasSmallIsland;
    }

    public final DynamicIslandBigIslandView getBigIslandView() {
        return this.bigIslandView;
    }

    public final int getBigIslandViewWidth() {
        return this.bigIslandViewWidth;
    }

    public final int getBigIslandViewWidthHasSmallIsland() {
        return this.bigIslandViewWidthHasSmallIsland;
    }

    public final int getBigIslandX() {
        return this.bigIslandX;
    }

    public final int getBigIslandXHasSmallIsland() {
        return this.bigIslandXHasSmallIsland;
    }

    public final FrameLayout getContainer() {
        return this.container;
    }

    public DynamicIslandBaseContentViewController<?> getController() {
        DynamicIslandBaseContentViewController<?> dynamicIslandBaseContentViewController = this._controller;
        if (dynamicIslandBaseContentViewController != null) {
            return dynamicIslandBaseContentViewController;
        }
        throw new IllegalStateException("Accessing controller when not initialized.");
    }

    public final Integer getCurrentBigIslandViewWidth() {
        BigIslandStateHandler bigIslandStateHandler;
        DynamicIslandContentView current;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.dynamicIslandEventCoordinator;
        if (dynamicIslandEventCoordinator == null || (bigIslandStateHandler = dynamicIslandEventCoordinator.getBigIslandStateHandler()) == null || (current = bigIslandStateHandler.getCurrent()) == null) {
            return null;
        }
        return Integer.valueOf(current.getBigIslandViewWidth());
    }

    public final int getCurrentBigIslandWidth(Boolean bool) {
        return bool != null ? bool.booleanValue() : hasSmallIsland() ? this.bigIslandViewWidthHasSmallIsland : this.bigIslandViewWidth;
    }

    public final int getCurrentBigIslandX(Boolean bool) {
        return bool != null ? bool.booleanValue() : hasSmallIsland() ? this.bigIslandXHasSmallIsland : this.bigIslandX;
    }

    public final DynamicIslandData getCurrentIslandData() {
        return this.currentIslandData;
    }

    public final int getCutoutHeight() {
        DynamicIslandWindowView windowView;
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = getContext();
        n.f(context, "getContext(...)");
        int iDpToPx = dynamicIslandUtils.dpToPx(20, context);
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.dynamicIslandEventCoordinator;
        return (dynamicIslandEventCoordinator == null || (windowView = dynamicIslandEventCoordinator.getWindowView()) == null) ? iDpToPx : windowView.getCutoutHeight();
    }

    public final int getCutoutWidth() {
        DynamicIslandWindowView windowView;
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = getContext();
        n.f(context, "getContext(...)");
        int iDpToPx = dynamicIslandUtils.dpToPx(20, context);
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.dynamicIslandEventCoordinator;
        return (dynamicIslandEventCoordinator == null || (windowView = dynamicIslandEventCoordinator.getWindowView()) == null) ? iDpToPx : windowView.getCutoutWidth();
    }

    public final float getCutoutY() {
        return this.cutoutY;
    }

    public final DynamicIslandEventCoordinator getDynamicIslandEventCoordinator() {
        return this.dynamicIslandEventCoordinator;
    }

    public Rect getExpandedIslandRect() {
        return new Rect();
    }

    public final LottieAnimationView getExpandedLottieView() {
        return this.expandedLottieView;
    }

    public Rect getExpandedPosition() {
        return new Rect();
    }

    public final DynamicIslandExpandedView getExpandedView() {
        return this.expandedView;
    }

    public final int getExpandedViewHeight() {
        return this.expandedViewHeight;
    }

    public final int getExpandedViewMarginHorizontal() {
        return this.expandedViewMarginHorizontal;
    }

    public final int getExpandedViewMaxHeight() {
        return this.expandedViewMaxHeight;
    }

    public final int getExpandedViewMaxWidth() {
        return this.expandedViewMaxWidth;
    }

    public final int getExpandedViewWidth() {
        return this.expandedViewWidth;
    }

    public final int getExpandedViewY() {
        return this.expandedViewY;
    }

    public final LinearLayout getFakeBigContainer() {
        return this.fakeBigContainer;
    }

    public final FrameLayout getFakeBigIsland() {
        return this.fakeBigIsland;
    }

    public final FrameLayout getFakeContainer() {
        return this.fakeContainer;
    }

    public final FrameLayout getFakeExpandedView() {
        return this.fakeExpandedView;
    }

    public final FrameLayout getFakeFirstLayout() {
        return this.fakeFirstLayout;
    }

    public final View getFakeMask() {
        return this.fakeMask;
    }

    public final FrameLayout getFakeSecondLayout() {
        return this.fakeSecondLayout;
    }

    public final FrameLayout getFakeSmallIsland() {
        return this.fakeSmallIsland;
    }

    public final DynamicIslandContentFakeView getFakeView() {
        return this.fakeView;
    }

    public final I getHighlightColor() {
        return this.highlightColor;
    }

    public final I getIslandAppAnimRunning() {
        return this.islandAppAnimRunning;
    }

    public final I getIslandFreeformAnimRunning() {
        return this.islandFreeformAnimRunning;
    }

    public final int getIslandViewHeight() {
        return this.islandViewHeight;
    }

    public final int getIslandViewMarginTop() {
        return this.islandViewMarginTop;
    }

    public final int getIslandWindowHeight() {
        return this.islandWindowHeight;
    }

    public final DynamicIslandState getLastState() {
        return this.lastState;
    }

    public final View.OnAttachStateChangeListener getListener() {
        return this.listener;
    }

    public final View getMask() {
        return this.mask;
    }

    public final View getMiniBar() {
        return this.miniBar;
    }

    public final float getMiniBarMarginBottom() {
        return this.miniBarMarginBottom;
    }

    public final boolean getMiniBarVisible() {
        return this.miniBarVisible;
    }

    public final boolean getOpenAppFromIsland() {
        return this.openAppFromIsland;
    }

    public final String getPkgName() {
        Bundle extras;
        DynamicIslandData dynamicIslandData = this.currentIslandData;
        if (dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) {
            return null;
        }
        return extras.getString("miui.pkg.name");
    }

    public final DynamicIslandContentView getRealView() {
        return this.realView;
    }

    public final RectF getRoundedRect() {
        return this.roundedRect;
    }

    public final boolean getShowShade() {
        return this.showShade;
    }

    public final FrameLayout getSmallContainer() {
        return this.smallContainer;
    }

    public final Rect getSmallIslandRect(int i2) {
        Rect rect = new Rect();
        rect.left = i2;
        int i3 = this.islandViewMarginTop;
        rect.top = i3;
        rect.right = i2 + this.smallIslandViewWidth;
        rect.bottom = i3 + this.islandViewHeight;
        return rect;
    }

    public final FrameLayout getSmallIslandView() {
        return this.smallIslandView;
    }

    public final int getSmallIslandViewWidth() {
        return this.smallIslandViewWidth;
    }

    public final int getSpace() {
        return this.space;
    }

    public final DynamicIslandState getState() {
        return this.state;
    }

    public final int getStatusBarHeight() {
        return this.statusBarHeight;
    }

    public final float getSwipeDiffX() {
        return this.swipeDiffX;
    }

    public final i getSwipeInfo() {
        return this.swipeInfo;
    }

    public final IslandTemplate getTemplate() {
        return this.template;
    }

    public final Integer getUserId() {
        Bundle extras;
        DynamicIslandData dynamicIslandData = this.currentIslandData;
        if (dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) {
            return null;
        }
        return Integer.valueOf(extras.getInt("miui.user.id"));
    }

    public final DynamicIslandViewModel getViewModel() {
        return this.viewModel;
    }

    public final WindowAnimState getWindowAnimState() {
        return this.windowAnimState;
    }

    public final DynamicIslandWindowState getWindowState() {
        DynamicIslandWindowView windowView;
        DynamicIslandWindowViewController windowViewController;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.dynamicIslandEventCoordinator;
        if (dynamicIslandEventCoordinator == null || (windowView = dynamicIslandEventCoordinator.getWindowView()) == null || (windowViewController = windowView.getWindowViewController()) == null) {
            return null;
        }
        return windowViewController.getWindowState();
    }

    public final DynamicIslandBaseContentViewController<?> get_controller() {
        return this._controller;
    }

    public final u get_islandAppAnimRunning() {
        return this._islandAppAnimRunning;
    }

    public final u get_islandFreeformAnimRunning() {
        return this._islandFreeformAnimRunning;
    }

    public final Boolean hasMulti() {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        DynamicIslandWindowView windowView;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2;
        DynamicIslandWindowView windowView2;
        String pkgName = getPkgName();
        Boolean boolValueOf = (pkgName == null || (dynamicIslandEventCoordinator2 = this.dynamicIslandEventCoordinator) == null || (windowView2 = dynamicIslandEventCoordinator2.getWindowView()) == null) ? null : Boolean.valueOf(windowView2.hasSubMiniWindow(pkgName));
        String pkgName2 = getPkgName();
        Boolean boolValueOf2 = (pkgName2 == null || (dynamicIslandEventCoordinator = this.dynamicIslandEventCoordinator) == null || (windowView = dynamicIslandEventCoordinator.getWindowView()) == null) ? null : Boolean.valueOf(windowView.hasSubAppExpanded(pkgName2));
        Boolean bool = Boolean.FALSE;
        if (n.c(boolValueOf2, bool) && n.c(boolValueOf, bool)) {
            return null;
        }
        DynamicIslandState dynamicIslandState = this.lastState;
        if ((dynamicIslandState instanceof DynamicIslandState.AppExpanded) || (dynamicIslandState instanceof DynamicIslandState.SubAppExpanded)) {
            return boolValueOf2;
        }
        if ((dynamicIslandState instanceof DynamicIslandState.MiniWindowExpanded) || (dynamicIslandState instanceof DynamicIslandState.SubMiniWindowExpanded)) {
            return boolValueOf;
        }
        return null;
    }

    public final boolean hasSmallIsland() {
        Boolean boolHasMulti = hasMulti();
        if (boolHasMulti == null) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.dynamicIslandEventCoordinator;
            boolHasMulti = dynamicIslandEventCoordinator != null ? Boolean.valueOf(dynamicIslandEventCoordinator.hasSmallIsland()) : null;
        }
        return n.c(boolHasMulti, Boolean.TRUE);
    }

    public void hideIslandLayout() {
        hideExpandedLottie();
    }

    public final boolean isAnimating() {
        DynamicIslandAnimationDelegate dynamicIslandAnimationDelegate = this.animatorDelegate;
        return dynamicIslandAnimationDelegate != null && dynamicIslandAnimationDelegate.isAnimating();
    }

    public final boolean isExpanded() {
        ExpandedStateHandler expandedStateHandler;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.dynamicIslandEventCoordinator;
        return n.c((dynamicIslandEventCoordinator == null || (expandedStateHandler = dynamicIslandEventCoordinator.getExpandedStateHandler()) == null) ? null : expandedStateHandler.getCurrent(), this);
    }

    @Override // android.view.View
    public final I isPressed() {
        return this.isPressed;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this._isPressed.setValue(Boolean.FALSE);
    }

    public void onIslandClick() {
    }

    public final void onSwipe(float f2, float f3, DynamicIslandContentView view, DynamicIslandState dynamicIslandState, DynamicIslandState dynamicIslandState2) {
        n.g(view, "view");
        calculateSwipeRatio(((Number) this.swipeInfo.d()).floatValue());
        DynamicIslandAnimationDelegate dynamicIslandAnimationDelegate = this.animatorDelegate;
        if (dynamicIslandAnimationDelegate != null) {
            dynamicIslandAnimationDelegate.onSwipe(view, dynamicIslandState, dynamicIslandState2, f2, f3);
        }
    }

    public final void resetSwipe(DynamicIslandContentView view) {
        n.g(view, "view");
        DynamicIslandAnimationDelegate dynamicIslandAnimationDelegate = this.animatorDelegate;
        if (dynamicIslandAnimationDelegate != null) {
            dynamicIslandAnimationDelegate.resetSwipe(view);
        }
    }

    public final void setAnimatorDelegate(DynamicIslandAnimationDelegate dynamicIslandAnimationDelegate) {
        this.animatorDelegate = dynamicIslandAnimationDelegate;
    }

    public final void setBackgroundView(DynamicIslandBackgroundView dynamicIslandBackgroundView) {
        this.backgroundView = dynamicIslandBackgroundView;
    }

    public final void setBigContainer(LinearLayout linearLayout) {
        this.bigContainer = linearLayout;
    }

    public final void setBigIslandAreaLeft(FrameLayout frameLayout) {
        this.bigIslandAreaLeft = frameLayout;
    }

    public final void setBigIslandAreaRight(FrameLayout frameLayout) {
        this.bigIslandAreaRight = frameLayout;
    }

    public final void setBigIslandLeftWidth(int i2) {
        this.bigIslandLeftWidth = i2;
    }

    public final void setBigIslandLeftWidthHasSmallIsland(int i2) {
        this.bigIslandLeftWidthHasSmallIsland = i2;
    }

    public final void setBigIslandRightWidth(int i2) {
        this.bigIslandRightWidth = i2;
    }

    public final void setBigIslandRightWidthHasSmallIsland(int i2) {
        this.bigIslandRightWidthHasSmallIsland = i2;
    }

    public final void setBigIslandView(DynamicIslandBigIslandView dynamicIslandBigIslandView) {
        this.bigIslandView = dynamicIslandBigIslandView;
    }

    public final void setBigIslandViewWidth(int i2) {
        this.bigIslandViewWidth = i2;
    }

    public final void setBigIslandViewWidthHasSmallIsland(int i2) {
        this.bigIslandViewWidthHasSmallIsland = i2;
    }

    public final void setBigIslandX(int i2) {
        this.bigIslandX = i2;
    }

    public final void setBigIslandXHasSmallIsland(int i2) {
        this.bigIslandXHasSmallIsland = i2;
    }

    public final void setContainer(FrameLayout frameLayout) {
        this.container = frameLayout;
    }

    @CallSuper
    public void setController(DynamicIslandBaseContentViewController<?> controller) {
        n.g(controller, "controller");
        this._controller = controller;
    }

    public final void setCurrentIslandData(DynamicIslandData dynamicIslandData) {
        this.currentIslandData = dynamicIslandData;
    }

    public final void setCutoutY(float f2) {
        this.cutoutY = f2;
        Log.e(TAG, "setCutoutY " + f2);
        calculateBigIslandY();
    }

    public final void setDynamicIslandEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        this.dynamicIslandEventCoordinator = dynamicIslandEventCoordinator;
    }

    public final void setEventHandler(DynamicIslandEventCoordinator eventHandler) {
        n.g(eventHandler, "eventHandler");
        this.dynamicIslandEventCoordinator = eventHandler;
    }

    public final void setExpandedLottieView(LottieAnimationView lottieAnimationView) {
        this.expandedLottieView = lottieAnimationView;
    }

    public final void setExpandedView(DynamicIslandExpandedView dynamicIslandExpandedView) {
        this.expandedView = dynamicIslandExpandedView;
    }

    public final void setExpandedViewHeight(int i2) {
        this.expandedViewHeight = i2;
    }

    public final void setExpandedViewMarginHorizontal(int i2) {
        this.expandedViewMarginHorizontal = i2;
    }

    public final void setExpandedViewMaxHeight(int i2) {
        this.expandedViewMaxHeight = i2;
    }

    public final void setExpandedViewMaxWidth(int i2) {
        this.expandedViewMaxWidth = i2;
    }

    public final void setExpandedViewWidth(int i2) {
        this.expandedViewWidth = i2;
    }

    public final void setExpandedViewY(int i2) {
        this.expandedViewY = i2;
    }

    public final void setFakeBigContainer(LinearLayout linearLayout) {
        this.fakeBigContainer = linearLayout;
    }

    public final void setFakeBigIsland(FrameLayout frameLayout) {
        this.fakeBigIsland = frameLayout;
    }

    public final void setFakeContainer(FrameLayout frameLayout) {
        this.fakeContainer = frameLayout;
    }

    public final void setFakeExpandedView(FrameLayout frameLayout) {
        this.fakeExpandedView = frameLayout;
    }

    public final void setFakeFirstLayout(FrameLayout frameLayout) {
        this.fakeFirstLayout = frameLayout;
    }

    public final void setFakeMask(View view) {
        this.fakeMask = view;
    }

    public final void setFakeSecondLayout(FrameLayout frameLayout) {
        this.fakeSecondLayout = frameLayout;
    }

    public final void setFakeSmallIsland(FrameLayout frameLayout) {
        this.fakeSmallIsland = frameLayout;
    }

    public final void setFakeView(DynamicIslandContentFakeView dynamicIslandContentFakeView) {
        this.fakeView = dynamicIslandContentFakeView;
    }

    public final void setIslandViewHeight(int i2) {
        this.islandViewHeight = i2;
    }

    public final void setIslandViewMarginTop(int i2) {
        this.islandViewMarginTop = i2;
    }

    public final void setIslandWindowHeight(int i2) {
        this.islandWindowHeight = i2;
    }

    public final void setLastState(DynamicIslandState dynamicIslandState) {
        this.lastState = dynamicIslandState;
    }

    public final void setListener(View.OnAttachStateChangeListener onAttachStateChangeListener) {
        this.listener = onAttachStateChangeListener;
    }

    public final void setMask(View view) {
        this.mask = view;
    }

    public final void setMaxWidth(float f2, float f3, float f4) {
        this.maxWidth = f2;
        this.clockWidth = f3;
        this.batteryWidth = f4;
        Log.e(TAG, "setMaxWidth maxWidth: " + f2 + ", clockWidth: " + f3 + ", batteryWidth: " + f4);
    }

    public final void setMiniBar(View view) {
        this.miniBar = view;
    }

    public final void setMiniBarVisible(boolean z2) {
        this.miniBarVisible = z2;
    }

    public final void setOpenAppFromIsland(boolean z2) {
        this.openAppFromIsland = z2;
    }

    @Override // android.view.View
    public void setPressed(boolean z2) {
        super.setPressed(z2);
        this._isPressed.setValue(Boolean.valueOf(z2));
    }

    public final void setRealView(DynamicIslandContentView dynamicIslandContentView) {
        this.realView = dynamicIslandContentView;
    }

    public final void setShowShade(boolean z2) {
        this.showShade = z2;
    }

    public final void setSmallContainer(FrameLayout frameLayout) {
        this.smallContainer = frameLayout;
    }

    public final void setSmallIslandView(FrameLayout frameLayout) {
        this.smallIslandView = frameLayout;
    }

    public final void setSmallIslandViewWidth(int i2) {
        this.smallIslandViewWidth = i2;
    }

    public final void setState(DynamicIslandState dynamicIslandState) {
        if (n.c(this.state, dynamicIslandState)) {
            return;
        }
        this.state = dynamicIslandState;
    }

    public final void setStatusBarHeight(int i2) {
        this.statusBarHeight = i2;
    }

    public final void setSwipeDiffX(float f2) {
        this.swipeDiffX = f2;
    }

    public final void setSwipeInfo(i iVar) {
        n.g(iVar, "<set-?>");
        this.swipeInfo = iVar;
    }

    public final void setTemplate(IslandTemplate islandTemplate) {
        this.template = islandTemplate;
    }

    public final void setWindowAnimState(WindowAnimState windowAnimState) {
        n.g(windowAnimState, "<set-?>");
        this.windowAnimState = windowAnimState;
    }

    public final void set_controller(DynamicIslandBaseContentViewController<?> dynamicIslandBaseContentViewController) {
        this._controller = dynamicIslandBaseContentViewController;
    }

    public void showIslandLayout() {
        showExpandedLottie();
    }

    public void showShadowNoANim() {
    }

    public void updateAccessibility(DynamicIslandData dynamicIslandData) {
    }

    public final void updateBackgroundBg(View view) {
        n.g(view, "view");
        Context context = view.getContext();
        n.f(context, "getContext(...)");
        if (!MiBlurCompat.getBackgroundBlurOpened(context) || view.getParent() == null) {
            MiBlurCompat.setMiViewBlurModeCompat(view, 0);
            MiBlurCompat.clearMiBackgroundBlendColorCompat(view);
            view.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.dynamic_island_background));
            return;
        }
        int color = getContext().getResources().getColor(R.color.island_element_blend_shade_color_1);
        int color2 = getContext().getResources().getColor(R.color.island_element_blend_shade_color_2);
        int color3 = getContext().getResources().getColor(R.color.island_element_blend_shade_color_3);
        Log.i(TAG, "updateBackgroundBg color&mode:(" + color + "-100), (" + color2 + "-106), (" + color3 + "-3)");
        MiBlurCompat.setMiViewBlurModeCompat(view, 1);
        MiBlurCompat.clearMiBackgroundBlendColorCompat(view);
        MiBlurCompat.setMiBackgroundBlendColors$default(view, new int[]{color, 100, color2, 106, color3, 3}, 0.0f, 2, (Object) null);
        view.setBackgroundDrawable(null);
    }

    public void updateBigIslandLayout() {
    }

    public final void updateBigIslandLayoutWithAnim(FrameLayout frameLayout, FrameLayout frameLayout2, LinearLayout linearLayout, FrameLayout frameLayout3, DynamicIslandContentView dynamicIslandContentView, boolean z2) {
        int bigIslandViewWidth = dynamicIslandContentView != null ? dynamicIslandContentView.getBigIslandViewWidth() : 0;
        int bigIslandViewWidthHasSmallIsland = dynamicIslandContentView != null ? dynamicIslandContentView.getBigIslandViewWidthHasSmallIsland() : 0;
        int bigIslandLeftWidth = dynamicIslandContentView != null ? dynamicIslandContentView.getBigIslandLeftWidth() : 0;
        int bigIslandRightWidth = dynamicIslandContentView != null ? dynamicIslandContentView.getBigIslandRightWidth() : 0;
        int bigIslandLeftWidthHasSmallIsland = dynamicIslandContentView != null ? dynamicIslandContentView.getBigIslandLeftWidthHasSmallIsland() : 0;
        int bigIslandRightWidthHasSmallIsland = dynamicIslandContentView != null ? dynamicIslandContentView.getBigIslandRightWidthHasSmallIsland() : 0;
        int bigIslandX = dynamicIslandContentView != null ? dynamicIslandContentView.getBigIslandX() : 0;
        int bigIslandXHasSmallIsland = dynamicIslandContentView != null ? dynamicIslandContentView.getBigIslandXHasSmallIsland() : 0;
        Log.d(TAG, "updateBigIslandLayoutWithAnim: bigIslandViewWidth: " + bigIslandViewWidth + ", " + bigIslandLeftWidth + ", " + bigIslandRightWidth + ", bigIslandViewWidthHasSmallIsland: " + bigIslandViewWidthHasSmallIsland + ", " + bigIslandLeftWidthHasSmallIsland + ",  " + bigIslandRightWidthHasSmallIsland + ",  getCutoutWidth : " + getCutoutWidth() + ", hasSmallIsland=" + (dynamicIslandContentView != null ? Boolean.valueOf(dynamicIslandContentView.hasSmallIsland()) : null) + ",  isFakeView: " + z2);
        if (dynamicIslandContentView == null || dynamicIslandContentView.hasSmallIsland()) {
            doBigIslandAnim(bigIslandLeftWidthHasSmallIsland, bigIslandRightWidthHasSmallIsland, bigIslandViewWidthHasSmallIsland, computeBigIslandTx(bigIslandX, bigIslandViewWidth, bigIslandXHasSmallIsland, bigIslandViewWidthHasSmallIsland), frameLayout, frameLayout2, linearLayout, frameLayout3, dynamicIslandContentView, z2);
        } else {
            doBigIslandAnim(bigIslandLeftWidth, bigIslandRightWidth, bigIslandViewWidth, 0.0f, frameLayout, frameLayout2, linearLayout, frameLayout3, dynamicIslandContentView, z2);
        }
    }

    public boolean updateBigIslandView(DynamicIslandData dynamicIslandData, boolean z2) {
        return false;
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public final void updateDarkLightMode(DynamicIslandState dynamicIslandState, String str, boolean z2, boolean z3) {
        if (this.backgroundView == null || dynamicIslandState == null || this.template == null) {
            return;
        }
        if (z2) {
            Drawable drawable = getContext().getResources().getDrawable(R.drawable.dynamic_island_background_island);
            DynamicIslandBackgroundView dynamicIslandBackgroundView = this.backgroundView;
            if (dynamicIslandBackgroundView != null) {
                dynamicIslandBackgroundView.setDrawable(drawable);
            }
        } else {
            Drawable drawable2 = getContext().getResources().getDrawable(R.drawable.dynamic_island_background_big_island_dark);
            DynamicIslandBackgroundView dynamicIslandBackgroundView2 = this.backgroundView;
            if (dynamicIslandBackgroundView2 != null) {
                dynamicIslandBackgroundView2.setDrawable(drawable2);
            }
            if (dynamicIslandState instanceof DynamicIslandState.Expanded) {
                if (drawable2 instanceof GradientDrawable) {
                    ((GradientDrawable) drawable2).setStroke(getContext().getResources().getDimensionPixelSize(R.dimen.island_stroke), getContext().getResources().getColor(R.color.stroke_color));
                }
            } else if (!TextUtils.isEmpty(str)) {
                Integer numValueOf = str != null ? Integer.valueOf(ColorUtils.setAlphaComponent(Color.parseColor(str), (int) (((double) Color.alpha(Color.parseColor(str))) * 0.2d))) : null;
                if ((drawable2 instanceof GradientDrawable) && numValueOf != null) {
                    ((GradientDrawable) drawable2).setStroke(getContext().getResources().getDimensionPixelSize(R.dimen.island_stroke), numValueOf.intValue());
                }
            } else if (drawable2 instanceof GradientDrawable) {
                ((GradientDrawable) drawable2).setStroke(getContext().getResources().getDimensionPixelSize(R.dimen.island_stroke), getContext().getResources().getColor(R.color.stroke_color));
            }
        }
        if (dynamicIslandState instanceof DynamicIslandState.Hidden) {
            DynamicIslandBackgroundView dynamicIslandBackgroundView3 = this.backgroundView;
            if (dynamicIslandBackgroundView3 != null) {
                dynamicIslandBackgroundView3.alphaAnimation(0.0f);
                return;
            }
            return;
        }
        if (z3) {
            DynamicIslandBackgroundView dynamicIslandBackgroundView4 = this.backgroundView;
            if (dynamicIslandBackgroundView4 != null) {
                dynamicIslandBackgroundView4.alphaAnimation(0.0f);
                return;
            }
            return;
        }
        DynamicIslandBackgroundView dynamicIslandBackgroundView5 = this.backgroundView;
        if (dynamicIslandBackgroundView5 != null) {
            dynamicIslandBackgroundView5.alphaAnimation(1.0f);
        }
    }

    public void updateExpandViewBlur(int i2) {
    }

    public final void updateExpandedSize(int i2, int i3, DynamicIslandData dynamicIslandData) {
        View fakeView;
        View view;
        View view2;
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.expanded_min_width);
        if (i2 == 0) {
            this.expandedViewWidth = this.expandedViewMaxWidth;
        } else {
            this.expandedViewWidth = Math.max(Math.min(i2, this.expandedViewMaxWidth), dimensionPixelSize);
        }
        this.expandedViewHeight = Math.max(Math.min(i3, this.expandedViewMaxHeight), getContext().getResources().getDimensionPixelSize(R.dimen.expanded_min_height));
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = getContext();
        n.f(context, "getContext(...)");
        int screenWidthOld = dynamicIslandUtils.getScreenWidthOld(context);
        int i4 = this.expandedViewWidth;
        int i5 = (screenWidthOld - i4) / 2;
        this.expandedViewMarginHorizontal = i5;
        Log.e(TAG, "updateExpandedViewWidth " + i5 + " " + i4 + " " + i2 + " " + this.expandedViewHeight + " " + i3 + " " + ((dynamicIslandData == null || (view2 = dynamicIslandData.getView()) == null) ? null : Integer.valueOf(view2.getHeight())));
        if (dynamicIslandData != null && (view = dynamicIslandData.getView()) != null) {
            updateViewLayoutParams(view, this.expandedViewWidth, this.expandedViewHeight);
        }
        if (dynamicIslandData == null || (fakeView = dynamicIslandData.getFakeView()) == null) {
            return;
        }
        updateViewLayoutParams(fakeView, this.expandedViewWidth, this.expandedViewHeight);
    }

    public void updateExpandedView(DynamicIslandData dynamicIslandData, boolean z2) {
    }

    public void updateMarginTop() {
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateMedianLuma(float r6) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView.updateMedianLuma(float):void");
    }

    public final void updateMiniBar(DynamicIslandContentView dynamicIslandContentView) {
        boolean zCanExpandedViewSlide = canExpandedViewSlide(dynamicIslandContentView);
        this.miniBarVisible = zCanExpandedViewSlide;
        Log.d(TAG, "updateMiniBar visible: " + zCanExpandedViewSlide);
        View view = this.miniBar;
        if (view != null) {
            view.setVisibility(zCanExpandedViewSlide ? 0 : 8);
        }
        if (zCanExpandedViewSlide) {
            updateMiniBarTranslation(dynamicIslandContentView);
        }
    }

    public final void updateShadow(DynamicIslandBaseContentView view, int i2) {
        n.g(view, "view");
        MiShadowUtils.setMiShadow(view, i2, 0.0f, getContext().getResources().getDimensionPixelSize(R.dimen.island_shadow_y), getContext().getResources().getDimensionPixelSize(R.dimen.island_shadow_radius), 1.0f, true);
    }

    public void updateSmallIslandView(DynamicIslandData dynamicIslandData, boolean z2) {
    }

    public final boolean updateView(DynamicIslandData dynamicIslandData, boolean z2, boolean z3) {
        Log.e(DynamicIslandConstants.TAG_DEBUG_TEMPLATE, "updateView: " + dynamicIslandData + " " + z2);
        updateTemplate(dynamicIslandData);
        boolean zUpdateBigIslandView = updateBigIslandView(dynamicIslandData, z2);
        updateSmallIslandView(dynamicIslandData, z2);
        if (z3) {
            updateExpandedView(dynamicIslandData, z2);
            updateExpandedLottieView();
        }
        updateAccessibility(dynamicIslandData);
        return zUpdateBigIslandView;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandBaseContentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
        this.viewModel = new DynamicIslandViewModel();
        Float fValueOf = Float.valueOf(0.0f);
        this.swipeInfo = new i(fValueOf, fValueOf);
        this.expandedViewMaxWidth = context.getResources().getDimensionPixelSize(R.dimen.expanded_max_width);
        this.expandedViewMaxHeight = context.getResources().getDimensionPixelSize(R.dimen.expanded_max_height);
        this.smallIslandViewWidth = context.getResources().getDimensionPixelSize(R.dimen.small_island_width);
        this.islandViewHeight = context.getResources().getDimensionPixelSize(R.dimen.island_height);
        this.space = context.getResources().getDimensionPixelSize(R.dimen.island_space);
        this.windowAnimState = WindowAnimState.Idle.INSTANCE;
        Boolean bool = Boolean.FALSE;
        u uVarA = K.a(bool);
        this._isPressed = uVarA;
        u uVarA2 = K.a(null);
        this._highlightColor = uVarA2;
        this.isPressed = AbstractC0420h.b(uVarA);
        this.highlightColor = AbstractC0420h.b(uVarA2);
        u uVarA3 = K.a(bool);
        this._islandFreeformAnimRunning = uVarA3;
        this.islandFreeformAnimRunning = AbstractC0420h.b(uVarA3);
        u uVarA4 = K.a(bool);
        this._islandAppAnimRunning = uVarA4;
        this.islandAppAnimRunning = AbstractC0420h.b(uVarA4);
        ArrayList arrayList = new ArrayList();
        this.unsupportedExpandedViewSlidePackages = arrayList;
        String[] stringArray = context.getResources().getStringArray(R.array.unsupported_expanded_view_slide_packages);
        n.f(stringArray, "getStringArray(...)");
        r.u(arrayList, stringArray);
        this.roundedRect = new RectF(0.0f, 0.0f, this.bigIslandViewWidth, this.islandViewHeight);
        this.miniBarMarginBottom = getResources().getDimension(R.dimen.mini_window_bar_marginBottom);
        this.miniBarHeight = getResources().getDimension(R.dimen.mini_window_bar_height);
    }

    public final void updateMarginTop(View view) {
        ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
        if (layoutParams == null) {
            return;
        }
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = this.islandViewMarginTop;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = this.islandViewMarginTop;
        view.setLayoutParams(layoutParams);
    }
}
