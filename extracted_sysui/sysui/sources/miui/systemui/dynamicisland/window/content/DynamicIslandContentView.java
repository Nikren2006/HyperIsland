package miui.systemui.dynamicisland.window.content;

import H0.s;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewGroupKt;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import com.xiaomi.onetrack.util.aa;
import g1.InterfaceC0380l0;
import j1.u;
import java.util.Arrays;
import java.util.Iterator;
import java.util.WeakHashMap;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.l;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.animation.FolmeKt;
import miui.systemui.dynamicisland.DynamicIslandBackgroundView;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationController;
import miui.systemui.dynamicisland.display.AntiBurnInManager;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.handler.BigIslandStateHandler;
import miui.systemui.dynamicisland.event.handler.ExpandedStateHandler;
import miui.systemui.dynamicisland.event.handler.HiddenStateHandler;
import miui.systemui.dynamicisland.event.handler.SmallIslandStateHandler;
import miui.systemui.dynamicisland.events.DynamicIslandEventTracker;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.dynamicisland.template.IslandTemplateFactory;
import miui.systemui.dynamicisland.view.DynamicIslandBigIslandView;
import miui.systemui.dynamicisland.view.DynamicIslandExpandedView;
import miui.systemui.dynamicisland.window.DynamicIslandAnimUtils;
import miui.systemui.dynamicisland.window.DynamicIslandViewModel;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;
import miui.systemui.widget.CircularProgressBar;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.EaseManager;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;
import miuix.colorful.texteffect.TimerTextEffectView;
import miuix.core.util.MiShadowUtils;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandContentView extends DynamicIslandBaseContentView {
    public static final float BG_ANTI_BURN_IN_ALPHA = 0.6f;
    public static final float BG_NORMAL_ALPHA = 1.0f;
    public static final int BIG_ISLAND_ANTI_BURN_IN_ALPHA = 0;
    public static final int BIG_ISLAND_NORMAL_ALPHA = 255;
    public static final String TAG = "DynamicIslandContentView";
    private final DynamicIslandContentView$SHADOW_ALPHA$1 SHADOW_ALPHA;
    private boolean blockChildrenTouch;
    private boolean blockChildrenTouchOnDown;
    private AntiBurnInManager.BurnInStates burnInState;
    private float containerBgAlpha;
    private InterfaceC0380l0 enterJob;
    private final DynamicIslandContentView$exposeListener$1 exposeListener;
    private long exposedBurnInUnit;
    private long exposedUnit;
    private InterfaceC0380l0 extendJob;
    private boolean hasEverBurnedIn;
    private long remainingBurnInUnit;
    private long remainingUnit;
    private InterfaceC0380l0 restoreJob;
    private final WeakHashMap<View, Integer> shadeBackUp;
    private float shadowAlpha;
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle ANTI_BURN_IN_EASE = FolmeEase.spring(1.0f, 0.2f);
    private static final FloatProperty<DynamicIslandContentView> CONTAINER_BG_ALPHA = new FloatProperty<DynamicIslandContentView>() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentView$Companion$CONTAINER_BG_ALPHA$1
        @Override // miuix.animation.property.FloatProperty
        @RequiresApi(19)
        public float getValue(DynamicIslandContentView view) {
            n.g(view, "view");
            return view.getContainerBgAlpha();
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandContentView view, float f2) {
            n.g(view, "view");
            if (Float.isNaN(f2)) {
                return;
            }
            view.setContainerBgAlpha(f2);
        }
    };

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final EaseManager.EaseStyle getANTI_BURN_IN_EASE() {
            return DynamicIslandContentView.ANTI_BURN_IN_EASE;
        }

        public final FloatProperty<DynamicIslandContentView> getCONTAINER_BG_ALPHA() {
            return DynamicIslandContentView.CONTAINER_BG_ALPHA;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandContentView$inheritWidth$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        public AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final LinearLayout invoke() {
            DynamicIslandBigIslandView bigIslandView = DynamicIslandContentView.this.getBigIslandView();
            if (bigIslandView != null) {
                return (LinearLayout) bigIslandView.findViewById(R.id.big_container);
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandContentView$inheritWidth$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function0 {
        public AnonymousClass2() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final FrameLayout invoke() {
            FrameLayout smallIslandView = DynamicIslandContentView.this.getSmallIslandView();
            if (smallIslandView != null) {
                return (FrameLayout) smallIslandView.findViewById(R.id.small_container);
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandContentView$inheritWidth$3, reason: invalid class name */
    public static final class AnonymousClass3 extends o implements Function0 {
        public AnonymousClass3() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final FrameLayout invoke() {
            DynamicIslandBigIslandView bigIslandView = DynamicIslandContentView.this.getBigIslandView();
            if (bigIslandView != null) {
                return (FrameLayout) bigIslandView.findViewById(R.id.area_left);
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandContentView$inheritWidth$4, reason: invalid class name */
    public static final class AnonymousClass4 extends o implements Function0 {
        public AnonymousClass4() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final FrameLayout invoke() {
            DynamicIslandBigIslandView bigIslandView = DynamicIslandContentView.this.getBigIslandView();
            if (bigIslandView != null) {
                return (FrameLayout) bigIslandView.findViewById(R.id.area_right);
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandContentView$updateSmallIslandView$1, reason: invalid class name and case insensitive filesystem */
    public /* synthetic */ class C06521 extends l implements Function2 {
        public C06521(Object obj) {
            super(2, obj, DynamicIslandContentView.class, "emitEvent", "emitEvent(Lcom/android/systemui/plugins/miui/dynamicisland/DynamicIslandData;Lmiui/systemui/dynamicisland/event/DynamicIslandEvent;)Z", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Boolean invoke(DynamicIslandData dynamicIslandData, DynamicIslandEvent p12) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
            DynamicIslandWindowView windowView;
            DynamicIslandWindowView windowView2;
            DynamicIslandWindowViewController windowViewController;
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator2;
            DynamicIslandWindowView windowView3;
            Bundle extras;
            DynamicIslandWindowView windowView4;
            n.g(p12, "p1");
            DynamicIslandContentView dynamicIslandContentView = (DynamicIslandContentView) this.receiver;
            Log.d(DynamicIslandContentView.TAG, "emitEvent " + p12);
            boolean z2 = false;
            if (p12 instanceof DynamicIslandEvent.DeletedDynamicIsland) {
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = dynamicIslandContentView.getDynamicIslandEventCoordinator();
                StatusBarNotification statusBarNotification = null;
                if (dynamicIslandEventCoordinator3 != null && (windowView4 = dynamicIslandEventCoordinator3.getWindowView()) != null) {
                    DynamicIslandWindowView.removeDynamicIslandData$default(windowView4, dynamicIslandData, false, 2, null);
                }
                if (dynamicIslandData != null && (extras = dynamicIslandData.getExtras()) != null) {
                    statusBarNotification = (StatusBarNotification) extras.getParcelable("miui.sbn", StatusBarNotification.class);
                }
                if (statusBarNotification != null && (dynamicIslandEventCoordinator2 = dynamicIslandContentView.getDynamicIslandEventCoordinator()) != null && (windowView3 = dynamicIslandEventCoordinator2.getWindowView()) != null) {
                    windowView3.removeNotification(statusBarNotification);
                }
                z2 = true;
            } else if ((p12 instanceof DynamicIslandEvent.UpdateDynamicIsland) && dynamicIslandData != null && (dynamicIslandEventCoordinator = dynamicIslandContentView.getDynamicIslandEventCoordinator()) != null && (windowView = dynamicIslandEventCoordinator.getWindowView()) != null) {
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator4 = dynamicIslandContentView.getDynamicIslandEventCoordinator();
                windowView.updateDynamicIslandView(dynamicIslandData, false, (dynamicIslandEventCoordinator4 == null || (windowView2 = dynamicIslandEventCoordinator4.getWindowView()) == null || (windowViewController = windowView2.getWindowViewController()) == null) ? 0.0f : windowViewController.getIslandMaxWidth());
            }
            return Boolean.valueOf(z2);
        }
    }

    public /* synthetic */ DynamicIslandContentView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    private final void addOnAttachStateChangeListenerSafe(View view, View.OnAttachStateChangeListener onAttachStateChangeListener) {
        if (view == null || onAttachStateChangeListener == null) {
            return;
        }
        view.removeOnAttachStateChangeListener(onAttachStateChangeListener);
        view.addOnAttachStateChangeListener(onAttachStateChangeListener);
    }

    private final void applyPreMeasureMode(ViewGroup viewGroup, boolean z2) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof TimerTextEffectView) {
                ((TimerTextEffectView) childAt).enablePreMeasureMode(z2);
            }
            if (childAt instanceof ViewGroup) {
                applyPreMeasureMode((ViewGroup) childAt, z2);
            }
        }
    }

    private final boolean canBigIslandClick(DynamicIslandState dynamicIslandState) {
        DynamicIslandData currentIslandData;
        Bundle extras;
        ExpandedStateHandler expandedStateHandler;
        boolean zC;
        boolean zC2;
        HiddenStateHandler hiddenStateHandler;
        SmallIslandStateHandler smallIslandStateHandler;
        Bundle extras2;
        DynamicIslandData currentIslandData2 = getCurrentIslandData();
        String string = null;
        String string2 = (currentIslandData2 == null || (extras2 = currentIslandData2.getExtras()) == null) ? null : extras2.getString("miui.pkg.name");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator();
        DynamicIslandContentView current = (dynamicIslandEventCoordinator == null || (smallIslandStateHandler = dynamicIslandEventCoordinator.getSmallIslandStateHandler()) == null) ? null : smallIslandStateHandler.getCurrent();
        String pkgName = current != null ? current.getPkgName() : null;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = getDynamicIslandEventCoordinator();
        DynamicIslandContentView current2 = (dynamicIslandEventCoordinator2 == null || (hiddenStateHandler = dynamicIslandEventCoordinator2.getHiddenStateHandler()) == null) ? null : hiddenStateHandler.getCurrent();
        String pkgName2 = current2 != null ? current2.getPkgName() : null;
        if (n.c(string2, pkgName)) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator3 != null && dynamicIslandEventCoordinator3.isIslandWindowAnimating(this)) {
                Log.w(TAG, "case1: can not click on big island -> big island animating");
                return false;
            }
            if (current != null) {
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator4 = getDynamicIslandEventCoordinator();
                zC2 = n.c(dynamicIslandEventCoordinator4 != null ? Boolean.valueOf(dynamicIslandEventCoordinator4.isIslandWindowAnimating(current)) : null, Boolean.TRUE);
            } else {
                zC2 = false;
            }
            if (zC2) {
                Log.w(TAG, "can not click on big island -> small island animating");
                return false;
            }
        }
        if (n.c(string2, pkgName2)) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator5 = getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator5 != null && dynamicIslandEventCoordinator5.isIslandWindowAnimating(this)) {
                Log.w(TAG, "case2: can not click on big island -> big island animating");
                return false;
            }
            if (current2 != null) {
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator6 = getDynamicIslandEventCoordinator();
                zC = n.c(dynamicIslandEventCoordinator6 != null ? Boolean.valueOf(dynamicIslandEventCoordinator6.isIslandWindowAnimating(current2)) : null, Boolean.TRUE);
            } else {
                zC = false;
            }
            if (zC) {
                Log.w(TAG, "can not click on big island -> hidden island animating");
                return false;
            }
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator7 = getDynamicIslandEventCoordinator();
        DynamicIslandContentView current3 = (dynamicIslandEventCoordinator7 == null || (expandedStateHandler = dynamicIslandEventCoordinator7.getExpandedStateHandler()) == null) ? null : expandedStateHandler.getCurrent();
        if (current3 != null && (currentIslandData = current3.getCurrentIslandData()) != null && (extras = currentIslandData.getExtras()) != null) {
            string = extras.getString("miui.pkg.name");
        }
        if (current3 != null) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator8 = getDynamicIslandEventCoordinator();
            if ((dynamicIslandEventCoordinator8 != null && dynamicIslandEventCoordinator8.isIslandWindowAnimating(current3)) && n.c(string2, string)) {
                Log.w(TAG, "can not click on big island -> expanded island animating");
                return false;
            }
        }
        return true;
    }

    private final boolean canClick(DynamicIslandState dynamicIslandState) {
        DynamicIslandData currentIslandData = getCurrentIslandData();
        Log.i(TAG, "try perform click on " + (currentIslandData != null ? currentIslandData.getKey() : null));
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator != null && dynamicIslandEventCoordinator.isTempHidden(this)) {
            Log.w(TAG, "can not click on tempHiddenIsland");
            return false;
        }
        if (dynamicIslandState instanceof DynamicIslandState.BigIsland) {
            return canBigIslandClick(dynamicIslandState);
        }
        if (dynamicIslandState instanceof DynamicIslandState.SmallIsland) {
            return canSmallIslandClick(dynamicIslandState);
        }
        return true;
    }

    private final boolean canSmallIslandClick(DynamicIslandState dynamicIslandState) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        DynamicIslandData currentIslandData;
        Bundle extras;
        ExpandedStateHandler expandedStateHandler;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2;
        DynamicIslandData currentIslandData2;
        Bundle extras2;
        BigIslandStateHandler bigIslandStateHandler;
        Bundle extras3;
        DynamicIslandData currentIslandData3 = getCurrentIslandData();
        String string = null;
        String string2 = (currentIslandData3 == null || (extras3 = currentIslandData3.getExtras()) == null) ? null : extras3.getString("miui.pkg.name");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = getDynamicIslandEventCoordinator();
        DynamicIslandContentView current = (dynamicIslandEventCoordinator3 == null || (bigIslandStateHandler = dynamicIslandEventCoordinator3.getBigIslandStateHandler()) == null) ? null : bigIslandStateHandler.getCurrent();
        String string3 = (current == null || (currentIslandData2 = current.getCurrentIslandData()) == null || (extras2 = currentIslandData2.getExtras()) == null) ? null : extras2.getString("miui.pkg.name");
        if (current != null && (dynamicIslandEventCoordinator2 = getDynamicIslandEventCoordinator()) != null && dynamicIslandEventCoordinator2.isIslandWindowAnimating(current) && n.c(string3, string2)) {
            Log.w(TAG, "can not click on small island -> big island animating");
            return false;
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator4 = getDynamicIslandEventCoordinator();
        DynamicIslandContentView current2 = (dynamicIslandEventCoordinator4 == null || (expandedStateHandler = dynamicIslandEventCoordinator4.getExpandedStateHandler()) == null) ? null : expandedStateHandler.getCurrent();
        if (current2 != null && (currentIslandData = current2.getCurrentIslandData()) != null && (extras = currentIslandData.getExtras()) != null) {
            string = extras.getString("miui.pkg.name");
        }
        if (current2 == null || (dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator()) == null || !dynamicIslandEventCoordinator.isIslandWindowAnimating(current2) || !n.c(string2, string)) {
            return true;
        }
        Log.w(TAG, "can not click on small island -> expanded island animating");
        return false;
    }

    private final String collectAllText4Accessibility(View view, DynamicIslandData dynamicIslandData) {
        if (view == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = getContext();
        n.f(context, "getContext(...)");
        collectAllText4Accessibility$traverse(this, sb, dynamicIslandUtils.getAppName(context, dynamicIslandData) != null, view);
        String string = sb.toString();
        n.f(string, "toString(...)");
        return string;
    }

    private static final void collectAllText4Accessibility$traverse(DynamicIslandContentView dynamicIslandContentView, StringBuilder sb, boolean z2, View view) {
        String string;
        String string2;
        if (view.getVisibility() != 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            Iterator it = ViewGroupKt.getChildren((ViewGroup) view).iterator();
            while (it.hasNext()) {
                collectAllText4Accessibility$traverse(dynamicIslandContentView, sb, z2, (View) it.next());
            }
            return;
        }
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            CharSequence contentDescription = textView.getContentDescription();
            if (contentDescription == null || (string2 = contentDescription.toString()) == null) {
                CharSequence text = textView.getText();
                string2 = text != null ? text.toString() : null;
            }
            if (string2 != null) {
                String str = f1.n.n(string2) ? null : string2;
                if (str != null) {
                    if (sb.length() > 0) {
                        sb.append(aa.f3429b);
                    } else if (z2) {
                        sb.append(MethodCodeHelper.IDENTITY_INFO_SEPARATOR);
                    }
                    sb.append(str);
                    return;
                }
                return;
            }
            return;
        }
        if (view instanceof CircularProgressBar) {
            CircularProgressBar circularProgressBar = (CircularProgressBar) view;
            CharSequence contentDescription2 = circularProgressBar.getContentDescription();
            if (contentDescription2 == null || (string = contentDescription2.toString()) == null) {
                if (circularProgressBar.getMaxProgress() == 0.0f || circularProgressBar.getTargetProgress() == 0.0f) {
                    string = "0.0%";
                } else {
                    string = String.format("%.1f%%", Arrays.copyOf(new Object[]{Float.valueOf((circularProgressBar.getTargetProgress() / circularProgressBar.getMaxProgress()) * 100)}, 1));
                    n.f(string, "format(...)");
                }
            }
            if (sb.length() > 0) {
                sb.append(aa.f3429b);
            } else if (z2) {
                sb.append(MethodCodeHelper.IDENTITY_INFO_SEPARATOR);
            }
            sb.append(string);
        }
    }

    private final <T extends View> T inheritViewWidth(T t2, Function0 function0) {
        ViewGroup.LayoutParams layoutParams;
        int width = t2 != null ? t2.getWidth() : 0;
        T t3 = (T) function0.invoke();
        if (t3 != null) {
            T t4 = (n.c(t2, t3) || width <= 0) ? null : t3;
            if (t4 != null && (layoutParams = t4.getLayoutParams()) != null) {
                layoutParams.width = width;
            }
        }
        return t3;
    }

    private final void inheritWidth() {
        setBigContainer((LinearLayout) inheritViewWidth(getBigContainer(), new AnonymousClass1()));
        setSmallContainer((FrameLayout) inheritViewWidth(getSmallContainer(), new AnonymousClass2()));
        setBigIslandAreaLeft((FrameLayout) inheritViewWidth(getBigIslandAreaLeft(), new AnonymousClass3()));
        setBigIslandAreaRight((FrameLayout) inheritViewWidth(getBigIslandAreaRight(), new AnonymousClass4()));
    }

    private final void initViewModel() {
        getViewModel().registerDynamicIslandViewStateChangeCallback(new DynamicIslandViewModel.DynamicIslandViewStateChangeCallback() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentView.initViewModel.1
            @Override // miui.systemui.dynamicisland.window.DynamicIslandViewModel.DynamicIslandViewStateChangeCallback
            public void onStateChanged(DynamicIslandState dynamicIslandState, DynamicIslandState dynamicIslandState2) {
                DynamicIslandAnimationController animationController;
                DynamicIslandAnimationController animationController2;
                Log.e(DynamicIslandContentView.TAG, "initViewModel1 state: " + dynamicIslandState + " " + (dynamicIslandState != null ? Boolean.valueOf(dynamicIslandState.getTempShow()) : null) + " ->" + dynamicIslandState2 + " " + (dynamicIslandState2 != null ? Boolean.valueOf(dynamicIslandState2.getTempShow()) : null));
                if (dynamicIslandState2 instanceof DynamicIslandState.BigIsland) {
                    DynamicIslandEventCoordinator dynamicIslandEventCoordinator = DynamicIslandContentView.this.getDynamicIslandEventCoordinator();
                    animationController = dynamicIslandEventCoordinator != null ? dynamicIslandEventCoordinator.getAnimationController() : null;
                    if (animationController == null) {
                        return;
                    }
                    animationController.setCurrentBigIsland(dynamicIslandState);
                    return;
                }
                if (!(dynamicIslandState2 instanceof DynamicIslandState.Expanded)) {
                    if (dynamicIslandState2 instanceof DynamicIslandState.Deleted) {
                        DynamicIslandContentView.this.getController().getIslandTemplateFactory().removeTemplate(((DynamicIslandState.Deleted) dynamicIslandState2).getDeleteKey());
                        return;
                    }
                    return;
                }
                DynamicIslandContentViewExtKt.dispatchAutoExpand(DynamicIslandContentView.this);
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = DynamicIslandContentView.this.getDynamicIslandEventCoordinator();
                DynamicIslandAnimationController animationController3 = dynamicIslandEventCoordinator2 != null ? dynamicIslandEventCoordinator2.getAnimationController() : null;
                if (animationController3 != null) {
                    DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = DynamicIslandContentView.this.getDynamicIslandEventCoordinator();
                    animationController3.setLastExpanded((dynamicIslandEventCoordinator3 == null || (animationController2 = dynamicIslandEventCoordinator3.getAnimationController()) == null) ? null : animationController2.getCurrentExpanded());
                }
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator4 = DynamicIslandContentView.this.getDynamicIslandEventCoordinator();
                animationController = dynamicIslandEventCoordinator4 != null ? dynamicIslandEventCoordinator4.getAnimationController() : null;
                if (animationController == null) {
                    return;
                }
                animationController.setCurrentExpanded(dynamicIslandState);
            }

            @Override // miui.systemui.dynamicisland.window.DynamicIslandViewModel.DynamicIslandViewStateChangeCallback
            public void onStateChanged(DynamicIslandContentView dynamicIslandContentView) {
                DynamicIslandAnimationController animationController;
                DynamicIslandState state;
                String deleteKey;
                DynamicIslandState state2;
                DynamicIslandState state3;
                Log.e(DynamicIslandContentView.TAG, "initViewModel2 state: " + (dynamicIslandContentView != null ? dynamicIslandContentView.getLastState() : null) + " " + ((dynamicIslandContentView == null || (state3 = dynamicIslandContentView.getState()) == null) ? null : Boolean.valueOf(state3.getTempShow())) + " ->" + (dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) + " " + ((dynamicIslandContentView == null || (state2 = dynamicIslandContentView.getState()) == null) ? null : Boolean.valueOf(state2.getTempShow())));
                DynamicIslandContentView.this.getController().onStateChange();
                if (((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.Deleted) && (state = DynamicIslandContentView.this.getState()) != null && (deleteKey = state.getDeleteKey()) != null) {
                    DynamicIslandContentView.this.getController().getIslandTemplateFactory().removeTemplate(deleteKey);
                }
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator = DynamicIslandContentView.this.getDynamicIslandEventCoordinator();
                if (dynamicIslandEventCoordinator != null) {
                    dynamicIslandEventCoordinator.alignBurnInStates();
                }
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = DynamicIslandContentView.this.getDynamicIslandEventCoordinator();
                if (dynamicIslandEventCoordinator2 == null || (animationController = dynamicIslandEventCoordinator2.getAnimationController()) == null) {
                    return;
                }
                animationController.onStateChange(dynamicIslandContentView);
            }
        });
    }

    private final void removeByError() {
        DynamicIslandWindowView windowView;
        removeAllViews();
        setState(null);
        Object parent = getParent();
        View view = parent instanceof View ? (View) parent : null;
        if (view != null) {
            View view2 = view.getParent() != null ? view : null;
            if (view2 != null) {
                try {
                    DynamicIslandEventCoordinator dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator();
                    if (dynamicIslandEventCoordinator == null || (windowView = dynamicIslandEventCoordinator.getWindowView()) == null) {
                        return;
                    }
                    windowView.removeView(view2);
                    s sVar = s.f314a;
                } catch (Exception e2) {
                    Log.w(TAG, "Error removing container view", e2);
                }
            }
        }
    }

    private final void resetOpenAppFromIsland() {
        setOpenAppFromIsland(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setShadowAlpha(float f2) {
        if (this.shadowAlpha == f2) {
            return;
        }
        this.shadowAlpha = f2;
        updateShadow(this, Color.argb(Math.round(f2 * 115), 0, 0, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateContentDescriptionIfNeed(View view, DynamicIslandData dynamicIslandData) {
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = getContext();
        n.f(context, "getContext(...)");
        String appName = dynamicIslandUtils.getAppName(context, dynamicIslandData);
        if (appName == null) {
            appName = "";
        }
        if (view == null) {
            return;
        }
        view.setContentDescription(appName + collectAllText4Accessibility(view, dynamicIslandData));
    }

    public final void animBigIslandBurnIn(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandContentViewExtKt.animEnterBurnIn(this, dynamicIslandContentView, true);
    }

    public final void animBigIslandExitBurn() {
        DynamicIslandContentViewExtKt.animExitBurnIn(this, true);
    }

    public final void animExitBurnIn(DynamicIslandContentView view) {
        n.g(view, "view");
        DynamicIslandContentViewExtKt.animBgBurnIn(view, 0.0f, 1.0f);
    }

    public final void animSmallIslandBurnIn(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandContentViewExtKt.animEnterBurnIn(this, dynamicIslandContentView, false);
    }

    public final void animSmallIslandExitBurn() {
        DynamicIslandContentViewExtKt.animExitBurnIn(this, false);
    }

    public final void calculateBigIslandWidth() {
        Log.e(TAG, "calculateBigIslandWidth");
        DynamicIslandBigIslandView bigIslandView = getBigIslandView();
        setBigContainer(bigIslandView != null ? (LinearLayout) bigIslandView.findViewById(R.id.big_container) : null);
        FrameLayout smallIslandView = getSmallIslandView();
        setSmallContainer(smallIslandView != null ? (FrameLayout) smallIslandView.findViewById(R.id.small_container) : null);
        DynamicIslandBigIslandView bigIslandView2 = getBigIslandView();
        setBigIslandAreaLeft(bigIslandView2 != null ? (FrameLayout) bigIslandView2.findViewById(R.id.area_left) : null);
        DynamicIslandBigIslandView bigIslandView3 = getBigIslandView();
        setBigIslandAreaRight(bigIslandView3 != null ? (FrameLayout) bigIslandView3.findViewById(R.id.area_right) : null);
        if (getListener() != null) {
            LinearLayout bigContainer = getBigContainer();
            if (bigContainer != null) {
                addOnAttachStateChangeListenerSafe(bigContainer, getListener());
            }
            FrameLayout bigIslandAreaLeft = getBigIslandAreaLeft();
            if (bigIslandAreaLeft != null) {
                addOnAttachStateChangeListenerSafe(bigIslandAreaLeft, getListener());
            }
            FrameLayout bigIslandAreaRight = getBigIslandAreaRight();
            if (bigIslandAreaRight != null) {
                addOnAttachStateChangeListenerSafe(bigIslandAreaRight, getListener());
            }
        }
        DynamicIslandBigIslandView bigIslandView4 = getBigIslandView();
        if (bigIslandView4 != null) {
            applyPreMeasureMode(bigIslandView4, true);
        }
        FrameLayout bigIslandAreaLeft2 = getBigIslandAreaLeft();
        if (bigIslandAreaLeft2 != null) {
            bigIslandAreaLeft2.measure(0, 0);
        }
        FrameLayout bigIslandAreaRight2 = getBigIslandAreaRight();
        if (bigIslandAreaRight2 != null) {
            bigIslandAreaRight2.measure(0, 0);
        }
        DynamicIslandBigIslandView bigIslandView5 = getBigIslandView();
        if (bigIslandView5 != null) {
            applyPreMeasureMode(bigIslandView5, false);
        }
        if (getBigIslandAreaLeft() != null && getBigIslandAreaRight() != null) {
            super.calculateBigIslandWidth(getBigIslandAreaLeft(), getBigIslandAreaRight());
            return;
        }
        Log.e(TAG, "calculateBigIslandWidth error " + getBigIslandAreaLeft() + " " + getBigIslandAreaRight());
        removeByError();
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void clearShadow() {
        FolmeKt.getFolme(this).cancel(this.SHADOW_ALPHA);
        setShowShade(false);
        MiShadowUtils.clearMiShadow(this);
    }

    public final boolean emitEvent(DynamicIslandData dynamicIslandData, DynamicIslandEvent event) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        DynamicIslandWindowView windowView;
        DynamicIslandWindowView windowView2;
        DynamicIslandWindowViewController windowViewController;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2;
        DynamicIslandWindowView windowView3;
        Bundle extras;
        DynamicIslandWindowView windowView4;
        n.g(event, "event");
        Log.d(TAG, "emitEvent " + event);
        if (!(event instanceof DynamicIslandEvent.DeletedDynamicIsland)) {
            if ((event instanceof DynamicIslandEvent.UpdateDynamicIsland) && dynamicIslandData != null && (dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator()) != null && (windowView = dynamicIslandEventCoordinator.getWindowView()) != null) {
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = getDynamicIslandEventCoordinator();
                windowView.updateDynamicIslandView(dynamicIslandData, false, (dynamicIslandEventCoordinator3 == null || (windowView2 = dynamicIslandEventCoordinator3.getWindowView()) == null || (windowViewController = windowView2.getWindowViewController()) == null) ? 0.0f : windowViewController.getIslandMaxWidth());
            }
            return false;
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator4 = getDynamicIslandEventCoordinator();
        StatusBarNotification statusBarNotification = null;
        if (dynamicIslandEventCoordinator4 != null && (windowView4 = dynamicIslandEventCoordinator4.getWindowView()) != null) {
            DynamicIslandWindowView.removeDynamicIslandData$default(windowView4, dynamicIslandData, false, 2, null);
        }
        if (dynamicIslandData != null && (extras = dynamicIslandData.getExtras()) != null) {
            statusBarNotification = (StatusBarNotification) extras.getParcelable("miui.sbn", StatusBarNotification.class);
        }
        if (statusBarNotification == null || (dynamicIslandEventCoordinator2 = getDynamicIslandEventCoordinator()) == null || (windowView3 = dynamicIslandEventCoordinator2.getWindowView()) == null) {
            return true;
        }
        windowView3.removeNotification(statusBarNotification);
        return true;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public Rect getBigIslandPosition() {
        Rect rect = new Rect();
        rect.set(getBigIslandX(), getIslandViewMarginTop(), getBigIslandX() + getBigIslandViewWidth(), getIslandViewMarginTop() + getIslandViewHeight());
        Log.e(TAG, "getBigIslandPosition " + rect);
        return rect;
    }

    public final boolean getBlockChildrenTouch() {
        return this.blockChildrenTouch;
    }

    public final AntiBurnInManager.BurnInStates getBurnInState() {
        return this.burnInState;
    }

    public final float getContainerBgAlpha() {
        return this.containerBgAlpha;
    }

    public final InterfaceC0380l0 getEnterJob() {
        return this.enterJob;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public Rect getExpandedIslandRect() {
        Rect rect = new Rect();
        rect.left = getExpandedViewMarginHorizontal();
        rect.top = getExpandedViewY();
        rect.right = rect.left + getExpandedViewWidth();
        rect.bottom = rect.top + getExpandedViewHeight();
        return rect;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public Rect getExpandedPosition() {
        Rect rect = new Rect();
        rect.set(getExpandedViewMarginHorizontal(), getExpandedViewY(), getExpandedViewMarginHorizontal() + getExpandedViewWidth(), getExpandedViewY() + getExpandedViewHeight());
        Log.e(TAG, "getExpandedPosition " + rect);
        return rect;
    }

    public final long getExposedBurnInUnit() {
        return this.exposedBurnInUnit;
    }

    public final long getExposedUnit() {
        return this.exposedUnit;
    }

    public final InterfaceC0380l0 getExtendJob() {
        return this.extendJob;
    }

    public final boolean getHasEverBurnedIn() {
        return this.hasEverBurnedIn;
    }

    public final String getIslandKey() {
        DynamicIslandData currentIslandData = getCurrentIslandData();
        if (currentIslandData != null) {
            return currentIslandData.getKey();
        }
        return null;
    }

    public final Integer getIslandProp() {
        DynamicIslandData currentIslandData = getCurrentIslandData();
        if (currentIslandData != null) {
            return currentIslandData.getProperties();
        }
        return null;
    }

    public final long getRemainingBurnInUnit() {
        return this.remainingBurnInUnit;
    }

    public final long getRemainingUnit() {
        return this.remainingUnit;
    }

    public final InterfaceC0380l0 getRestoreJob() {
        return this.restoreJob;
    }

    public final WeakHashMap<View, Integer> getShadeBackUp() {
        return this.shadeBackUp;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void hideIslandLayout() {
        super.hideIslandLayout();
        DynamicIslandContentFakeView fakeView = getFakeView();
        if (fakeView != null) {
            fakeView.hideIslandLayout();
        }
        getController().getIslandTemplateFactory().hideView(getCurrentIslandData(), false);
    }

    public final boolean inBurnIn() {
        return !(this.containerBgAlpha == 1.0f);
    }

    public final void initGlowEffect$miui_dynamicisland_release(ViewGroup topContainer, ViewGroup bottomContainer) {
        n.g(topContainer, "topContainer");
        n.g(bottomContainer, "bottomContainer");
        DynamicIslandExpandedView expandedView = getExpandedView();
        if (expandedView == null) {
            throw new IllegalStateException("Required value was null.");
        }
        expandedView.initGlowEffect$miui_dynamicisland_release(topContainer, bottomContainer);
        DynamicIslandBigIslandView bigIslandView = getBigIslandView();
        if (bigIslandView == null) {
            throw new IllegalStateException("Required value was null.");
        }
        bigIslandView.initGlowEffect$miui_dynamicisland_release(topContainer, bottomContainer);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        getController().onViewAttached(true);
        getViewTreeObserver().addOnPreDrawListener(this.exposeListener);
        super.onAttachedToWindow();
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        getController().onViewAttached(false);
        getViewTreeObserver().removeOnPreDrawListener(this.exposeListener);
        InterfaceC0380l0 interfaceC0380l0 = this.enterJob;
        if (interfaceC0380l0 != null) {
            InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
        }
        InterfaceC0380l0 interfaceC0380l02 = this.restoreJob;
        if (interfaceC0380l02 != null) {
            InterfaceC0380l0.a.a(interfaceC0380l02, null, 1, null);
        }
        InterfaceC0380l0 interfaceC0380l03 = this.extendJob;
        if (interfaceC0380l03 != null) {
            InterfaceC0380l0.a.a(interfaceC0380l03, null, 1, null);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setContainer((FrameLayout) findViewById(R.id.container));
        setExpandedView((DynamicIslandExpandedView) findViewById(R.id.expanded_view));
        setBigIslandView((DynamicIslandBigIslandView) findViewById(R.id.big_island_view));
        setSmallIslandView((FrameLayout) findViewById(R.id.small_island_view));
        setMask(findViewById(R.id.island_mask));
        setMiniBar(findViewById(R.id.mini_window_bar));
        DynamicIslandExpandedView expandedView = getExpandedView();
        n.e(expandedView, "null cannot be cast to non-null type android.view.View");
        updateBackgroundBg(expandedView);
        setListener(new View.OnAttachStateChangeListener() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentView.onFinishInflate.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v2) {
                n.g(v2, "v");
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v2) {
                n.g(v2, "v");
                Folme.clean(v2);
            }
        });
        DynamicIslandBigIslandView bigIslandView = getBigIslandView();
        if (bigIslandView != null) {
            addOnAttachStateChangeListenerSafe(bigIslandView, getListener());
        }
        initViewModel();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getActionMasked() == 0) {
            this.blockChildrenTouchOnDown = this.blockChildrenTouch;
        }
        if (this.blockChildrenTouchOnDown) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void onIslandClick() {
        DynamicIslandContentFakeView fakeView;
        Bundle extras;
        BigIslandStateHandler bigIslandStateHandler;
        DynamicIslandWindowView windowView;
        DynamicIslandWindowView windowView2;
        DynamicIslandWindowViewController windowViewController;
        DynamicIslandWindowState windowState;
        DynamicIslandWindowView windowView3;
        DynamicIslandWindowViewController windowViewController2;
        DynamicIslandWindowView windowView4;
        DynamicIslandWindowViewController windowViewController3;
        u screenPinning;
        Notification notification;
        Bundle extras2;
        Bundle extras3;
        Bundle extras4;
        boolean zCanClick = canClick(getState());
        DynamicIslandState state = getState();
        DynamicIslandData currentIslandData = getCurrentIslandData();
        String key = currentIslandData != null ? currentIslandData.getKey() : null;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator();
        Boolean boolValueOf = dynamicIslandEventCoordinator != null ? Boolean.valueOf(dynamicIslandEventCoordinator.getWindowAnimRunning()) : null;
        DynamicIslandData currentIslandData2 = getCurrentIslandData();
        Log.d(TAG, "onIslandClick " + state + ", " + key + "  animRunning: " + boolValueOf + ". \ncanExpand=" + zCanClick + ", currentViewIsNull:" + ((currentIslandData2 != null ? currentIslandData2.getView() : null) == null));
        DynamicIslandData currentIslandData3 = getCurrentIslandData();
        StatusBarNotification statusBarNotification = (currentIslandData3 == null || (extras4 = currentIslandData3.getExtras()) == null) ? null : (StatusBarNotification) extras4.getParcelable("miui.sbn", StatusBarNotification.class);
        DynamicIslandState state2 = getState();
        if (!(state2 instanceof DynamicIslandState.Expanded)) {
            if (!(state2 instanceof DynamicIslandState.SmallIsland)) {
                if ((state2 instanceof DynamicIslandState.BigIsland) && zCanClick) {
                    resetOpenAppFromIsland();
                    DynamicIslandData currentIslandData4 = getCurrentIslandData();
                    if ((currentIslandData4 != null ? currentIslandData4.getView() : null) != null) {
                        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = getDynamicIslandEventCoordinator();
                        if (dynamicIslandEventCoordinator2 != null) {
                            dynamicIslandEventCoordinator2.dispatchEvent(DynamicIslandEvent.ClickDynamicIsland.INSTANCE, this);
                        }
                        DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = getDynamicIslandEventCoordinator();
                        if (dynamicIslandEventCoordinator3 != null) {
                            dynamicIslandEventCoordinator3.setUserExpanded(true);
                        }
                    }
                    DynamicIslandEventTracker.Companion companion = DynamicIslandEventTracker.Companion;
                    Context context = getContext();
                    n.f(context, "getContext(...)");
                    EventTracker.Companion companion2 = EventTracker.Companion;
                    Context context2 = getContext();
                    n.f(context2, "getContext(...)");
                    companion.trackSummaryClick(context, statusBarNotification, DynamicIslandEventsConstants.Other.VALUE_ISLAND_FORM_BIG_ISLAND, companion2.getScreenType(context2));
                    return;
                }
                return;
            }
            if (zCanClick) {
                resetOpenAppFromIsland();
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator4 = getDynamicIslandEventCoordinator();
                DynamicIslandContentView current = (dynamicIslandEventCoordinator4 == null || (bigIslandStateHandler = dynamicIslandEventCoordinator4.getBigIslandStateHandler()) == null) ? null : bigIslandStateHandler.getCurrent();
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator5 = getDynamicIslandEventCoordinator();
                if (dynamicIslandEventCoordinator5 != null && dynamicIslandEventCoordinator5.islandAppAnimating(current)) {
                    DynamicIslandContentFakeView fakeView2 = current != null ? current.getFakeView() : null;
                    if (fakeView2 != null) {
                        fakeView2.setBigIslandViewNeedAnim(true);
                    }
                }
                DynamicIslandData currentIslandData5 = getCurrentIslandData();
                if ((currentIslandData5 != null ? currentIslandData5.getView() : null) != null) {
                    DynamicIslandEventCoordinator dynamicIslandEventCoordinator6 = getDynamicIslandEventCoordinator();
                    if (dynamicIslandEventCoordinator6 != null) {
                        dynamicIslandEventCoordinator6.dispatchEvent(DynamicIslandEvent.ClickDynamicIsland.INSTANCE, this);
                    }
                    DynamicIslandEventCoordinator dynamicIslandEventCoordinator7 = getDynamicIslandEventCoordinator();
                    if (dynamicIslandEventCoordinator7 != null) {
                        dynamicIslandEventCoordinator7.setUserExpanded(true);
                    }
                }
                if (current != null && (fakeView = current.getFakeView()) != null && fakeView.getBigIslandViewNeedAnim()) {
                    DynamicIslandContentFakeView fakeView3 = current.getFakeView();
                    if (fakeView3 != null) {
                        fakeView3.setBigIslandViewNeedAnim(false);
                    }
                    Rect bigIslandRect$default = DynamicIslandBaseContentView.getBigIslandRect$default(current, null, 1, null);
                    DynamicIslandData currentIslandData6 = current.getCurrentIslandData();
                    if (currentIslandData6 != null && (extras = currentIslandData6.getExtras()) != null) {
                        extras.putParcelable("position", bigIslandRect$default);
                    }
                    Log.d(TAG, "smallToExpand, big positionChanged: " + bigIslandRect$default);
                    DynamicIslandEventCoordinator dynamicIslandEventCoordinator8 = current.getDynamicIslandEventCoordinator();
                    if (dynamicIslandEventCoordinator8 != null) {
                        DynamicIslandData currentIslandData7 = current.getCurrentIslandData();
                        dynamicIslandEventCoordinator8.positionChanged(currentIslandData7 != null ? currentIslandData7.getExtras() : null);
                    }
                }
                DynamicIslandEventTracker.Companion companion3 = DynamicIslandEventTracker.Companion;
                Context context3 = getContext();
                n.f(context3, "getContext(...)");
                EventTracker.Companion companion4 = EventTracker.Companion;
                Context context4 = getContext();
                n.f(context4, "getContext(...)");
                companion3.trackSummaryClick(context3, statusBarNotification, DynamicIslandEventsConstants.Other.VALUE_ISLAND_FORM_SMALL_ISLAND, companion4.getScreenType(context4));
                return;
            }
            return;
        }
        DynamicIslandData currentIslandData8 = getCurrentIslandData();
        Log.e(TAG, "onIslandClick: click open app" + (currentIslandData8 != null ? currentIslandData8.getKey() : null));
        DynamicIslandData currentIslandData9 = getCurrentIslandData();
        PendingIntent pendingIntent = (currentIslandData9 == null || (extras3 = currentIslandData9.getExtras()) == null) ? null : (PendingIntent) extras3.getParcelable("miui.pending.intent", PendingIntent.class);
        DynamicIslandData currentIslandData10 = getCurrentIslandData();
        String string = (currentIslandData10 == null || (extras2 = currentIslandData10.getExtras()) == null) ? null : extras2.getString("miui.pkg.name");
        Log.e(TAG, "onIslandClick: click open app " + string);
        if (((statusBarNotification == null || (notification = statusBarNotification.getNotification()) == null) ? null : notification.contentIntent) == null && pendingIntent == null) {
            return;
        }
        DynamicIslandWindowState windowState2 = getWindowState();
        if (windowState2 != null && (screenPinning = windowState2.getScreenPinning()) != null && ((Boolean) screenPinning.getValue()).booleanValue()) {
            Log.e(TAG, "onIslandClick: click open app " + string + " but screen pinning is active");
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator9 = getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator9 != null) {
                dynamicIslandEventCoordinator9.setUserExpanded(false);
            }
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator10 = getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator10 != null) {
                DynamicIslandEventCoordinator.dispatchEvent$default(dynamicIslandEventCoordinator10, DynamicIslandEvent.Collapse.INSTANCE, null, 2, null);
                return;
            }
            return;
        }
        DynamicIslandEventTracker.Companion companion5 = DynamicIslandEventTracker.Companion;
        Context context5 = getContext();
        n.f(context5, "getContext(...)");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator11 = getDynamicIslandEventCoordinator();
        String str = (dynamicIslandEventCoordinator11 == null || !dynamicIslandEventCoordinator11.getUserExpanded()) ? DynamicIslandEventsConstants.Values.EXPAND_TYPE_PASSIVE : DynamicIslandEventsConstants.Values.EXPAND_TYPE_ACTIVE;
        EventTracker.Companion companion6 = EventTracker.Companion;
        Context context6 = getContext();
        n.f(context6, "getContext(...)");
        companion5.trackExpandedClick(context5, statusBarNotification, str, companion6.getScreenType(context6));
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator12 = getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator12 != null && (windowView2 = dynamicIslandEventCoordinator12.getWindowView()) != null && (windowViewController = windowView2.getWindowViewController()) != null && (windowState = windowViewController.getWindowState()) != null) {
            Context context7 = getContext();
            n.f(context7, "getContext(...)");
            if (windowState.isAdaptDesktopAnimation(context7)) {
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator13 = getDynamicIslandEventCoordinator();
                if (dynamicIslandEventCoordinator13 != null && (windowView4 = dynamicIslandEventCoordinator13.getWindowView()) != null && (windowViewController3 = windowView4.getWindowViewController()) != null) {
                    windowViewController3.runDesktopAnim(true);
                }
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator14 = getDynamicIslandEventCoordinator();
                if (dynamicIslandEventCoordinator14 != null && (windowView3 = dynamicIslandEventCoordinator14.getWindowView()) != null && (windowViewController2 = windowView3.getWindowViewController()) != null) {
                    windowViewController2.runDesktopAnim(false);
                }
            }
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator15 = getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator15 != null) {
            dynamicIslandEventCoordinator15.updateView(this);
        }
        if (DynamicIslandAnimUtils.INSTANCE.supportShowElementAndFreeformAnim()) {
            setOpenAppFromIsland(true);
        } else {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator16 = getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator16 != null && (windowView = dynamicIslandEventCoordinator16.getWindowView()) != null) {
                windowView.appEnter(this);
            }
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator17 = getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator17 != null) {
            Context context8 = getContext();
            n.f(context8, "getContext(...)");
            dynamicIslandEventCoordinator17.collapseStatusBar(context8);
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator18 = getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator18 != null) {
            dynamicIslandEventCoordinator18.setUserExpanded(false);
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator19 = getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator19 != null) {
            DynamicIslandData currentIslandData11 = getCurrentIslandData();
            dynamicIslandEventCoordinator19.openApp(currentIslandData11 != null ? currentIslandData11.getExtras() : null);
        }
        if (statusBarNotification != null) {
            DynamicIslandUtils.INSTANCE.clearIslandNotification(statusBarNotification);
        }
    }

    @Override // android.view.View
    public void onVisibilityAggregated(boolean z2) {
        getController().onVisAggregated(z2);
        super.onVisibilityAggregated(z2);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z2) {
        getController().onWindowFocusChanged(z2);
        super.onWindowFocusChanged(z2);
    }

    @Override // android.view.View
    public void setAlpha(float f2) {
        super.setAlpha(f2);
        DynamicIslandBackgroundView backgroundView = getBackgroundView();
        if (backgroundView == null) {
            return;
        }
        backgroundView.setAlpha(f2);
    }

    public final void setBlockChildrenTouch(boolean z2) {
        this.blockChildrenTouch = z2;
    }

    public final void setBurnInState(AntiBurnInManager.BurnInStates burnInStates) {
        n.g(burnInStates, "<set-?>");
        this.burnInState = burnInStates;
    }

    public final void setContainerBgAlpha(float f2) {
        this.containerBgAlpha = f2;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void setController(DynamicIslandBaseContentViewController<?> controller) {
        n.g(controller, "controller");
        if (!(controller instanceof DynamicIslandContentViewController)) {
            throw new IllegalAccessException("setting controller is not DynamicIslandContentViewController.");
        }
        super.setController(controller);
    }

    public final void setEnterJob(InterfaceC0380l0 interfaceC0380l0) {
        this.enterJob = interfaceC0380l0;
    }

    public final void setExposedBurnInUnit(long j2) {
        this.exposedBurnInUnit = j2;
    }

    public final void setExposedUnit(long j2) {
        this.exposedUnit = j2;
    }

    public final void setExtendJob(InterfaceC0380l0 interfaceC0380l0) {
        this.extendJob = interfaceC0380l0;
    }

    public final void setHasEverBurnedIn(boolean z2) {
        this.hasEverBurnedIn = z2;
    }

    public final void setRemainingBurnInUnit(long j2) {
        this.remainingBurnInUnit = j2;
    }

    public final void setRemainingUnit(long j2) {
        this.remainingUnit = j2;
    }

    public final void setRestoreJob(InterfaceC0380l0 interfaceC0380l0) {
        this.restoreJob = interfaceC0380l0;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void showIslandLayout() {
        super.showIslandLayout();
        DynamicIslandContentFakeView fakeView = getFakeView();
        if (fakeView != null) {
            fakeView.showIslandLayout();
        }
        getController().getIslandTemplateFactory().showView(getCurrentIslandData(), false);
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void showShadowNoANim() {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        if (getShowShade() || (dynamicIslandEventCoordinator = getDynamicIslandEventCoordinator()) == null || dynamicIslandEventCoordinator.islandAppAnimating(this)) {
            return;
        }
        FolmeKt.getFolme(this).cancel(this.SHADOW_ALPHA);
        setShowShade(true);
        updateShadow(this, getContext().getResources().getColor(R.color.shader_color));
    }

    public final void showShadowWithAnim() {
        if (!getShowShade() && (getState() instanceof DynamicIslandState.Expanded)) {
            setShowShade(true);
            FolmeKt.getFolme(this).setTo(this.SHADOW_ALPHA, Float.valueOf(0.0f)).to(this.SHADOW_ALPHA, Float.valueOf(1.0f));
        }
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void updateAccessibility(final DynamicIslandData dynamicIslandData) {
        FrameLayout smallIslandView = getSmallIslandView();
        if (smallIslandView != null) {
            DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
            Context context = getContext();
            n.f(context, "getContext(...)");
            smallIslandView.setContentDescription(dynamicIslandUtils.getAppName(context, dynamicIslandData));
        }
        updateContentDescriptionIfNeed(getBigIslandView(), dynamicIslandData);
        FrameLayout smallIslandView2 = getSmallIslandView();
        if (smallIslandView2 != null) {
            smallIslandView2.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentView.updateAccessibility.1
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    n.g(host, "host");
                    n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setClickable(true);
                }
            });
        }
        DynamicIslandBigIslandView bigIslandView = getBigIslandView();
        if (bigIslandView != null) {
            bigIslandView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentView.updateAccessibility.2
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    n.g(host, "host");
                    n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setClickable(true);
                    DynamicIslandContentView dynamicIslandContentView = DynamicIslandContentView.this;
                    dynamicIslandContentView.updateContentDescriptionIfNeed(dynamicIslandContentView.getBigIslandView(), dynamicIslandData);
                }
            });
        }
        DynamicIslandExpandedView expandedView = getExpandedView();
        if (expandedView == null) {
            return;
        }
        expandedView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentView.updateAccessibility.3
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                n.g(host, "host");
                n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClickable(true);
            }
        });
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void updateBigIslandLayout() {
        Log.e(TAG, "updateBigIslandLayout,  bigIslandViewWidth: " + getBigIslandViewWidth() + ", bigIslandViewWidthHasSmallIsland: " + getBigIslandViewWidthHasSmallIsland() + " getCutoutWidth : " + getCutoutWidth());
        updateBigIslandLayoutWithAnim(getBigIslandAreaLeft(), getBigIslandAreaRight(), getBigContainer(), getBigIslandView(), this, false);
        DynamicIslandContentFakeView fakeView = getFakeView();
        if (fakeView != null) {
            fakeView.updateBigIslandLayout();
        }
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public boolean updateBigIslandView(DynamicIslandData dynamicIslandData, boolean z2) {
        String key;
        String key2;
        ViewGroup contentView$miui_dynamicisland_release;
        Log.e(TAG, "updateBigIslandView " + getBigIslandView() + " " + getStatusBarHeight() + " " + getIslandWindowHeight() + " " + getIslandViewHeight());
        setCurrentIslandData(dynamicIslandData);
        if (!z2) {
            DynamicIslandBigIslandView bigIslandView = getBigIslandView();
            if (bigIslandView != null && (contentView$miui_dynamicisland_release = bigIslandView.getContentView$miui_dynamicisland_release()) != null) {
                contentView$miui_dynamicisland_release.removeAllViews();
            }
            if (dynamicIslandData != null && (key2 = dynamicIslandData.getKey()) != null) {
                getController().getIslandTemplateFactory().removeTemplate(key2);
            }
        }
        IslandTemplateFactory islandTemplateFactory = getController().getIslandTemplateFactory();
        Context context = getContext();
        n.f(context, "getContext(...)");
        DynamicIslandBigIslandView bigIslandView2 = getBigIslandView();
        ViewGroup contentView$miui_dynamicisland_release2 = bigIslandView2 != null ? bigIslandView2.getContentView$miui_dynamicisland_release() : null;
        if (contentView$miui_dynamicisland_release2 == null) {
            throw new IllegalStateException("Required value was null.");
        }
        if (islandTemplateFactory.createBigIslandTemplate(context, dynamicIslandData, contentView$miui_dynamicisland_release2, false, new DynamicIslandContentView$updateBigIslandView$view$1(this)) != null) {
            inheritWidth();
            OneShotPreDrawListener.add(this, new Runnable() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentView$updateBigIslandView$$inlined$doOnPreDraw$1
                @Override // java.lang.Runnable
                public final void run() {
                    this.calculateBigIslandWidth();
                }
            });
            return true;
        }
        if (dynamicIslandData != null && (key = dynamicIslandData.getKey()) != null) {
            getController().getIslandTemplateFactory().removeTemplate(key);
        }
        removeByError();
        return false;
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void updateExpandViewBlur(final int i2) {
        DynamicIslandExpandedView expandedView = getExpandedView();
        if (expandedView == null || expandedView.getParent() == null) {
            return;
        }
        expandedView.setOutlineProvider(new ViewOutlineProvider() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentView$updateExpandViewBlur$1$1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                n.g(view, "view");
                n.g(outline, "outline");
                view.getRenderNode().setPosition(new Rect(view.getLeft(), view.getTop(), view.getRight(), i2));
                outline.setRoundRect(0, 0, view.getRight(), i2, 0.0f);
            }
        });
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void updateExpandedView(DynamicIslandData dynamicIslandData, boolean z2) {
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        View view;
        View view2;
        View view3;
        ViewGroup.LayoutParams layoutParams3;
        View view4;
        View view5;
        View view6;
        ViewGroup.LayoutParams layoutParams4;
        Log.e(TAG, "updateExpandedView： islandData?.view=" + ((dynamicIslandData == null || (view6 = dynamicIslandData.getView()) == null || (layoutParams4 = view6.getLayoutParams()) == null) ? null : Integer.valueOf(layoutParams4.height)) + ", " + ((dynamicIslandData == null || (view5 = dynamicIslandData.getView()) == null) ? null : Integer.valueOf(view5.getHeight())) + " " + ((dynamicIslandData == null || (view4 = dynamicIslandData.getView()) == null) ? null : Integer.valueOf(view4.getMeasuredHeight())) + " " + ((dynamicIslandData == null || (view3 = dynamicIslandData.getView()) == null || (layoutParams3 = view3.getLayoutParams()) == null) ? null : Integer.valueOf(layoutParams3.width)) + ", " + ((dynamicIslandData == null || (view2 = dynamicIslandData.getView()) == null) ? null : Integer.valueOf(view2.getWidth())) + " " + ((dynamicIslandData == null || (view = dynamicIslandData.getView()) == null) ? null : Integer.valueOf(view.getMeasuredWidth())));
        if ((dynamicIslandData != null ? dynamicIslandData.getView() : null) == null) {
            return;
        }
        View view7 = dynamicIslandData.getView();
        if ((view7 != null ? view7.getParent() : null) != null) {
            View view8 = dynamicIslandData.getView();
            ViewParent parent = view8 != null ? view8.getParent() : null;
            n.e(parent, "null cannot be cast to non-null type android.view.ViewGroup");
            ((ViewGroup) parent).removeView(dynamicIslandData.getView());
        }
        View view9 = dynamicIslandData.getView();
        if (view9 != null && (layoutParams2 = view9.getLayoutParams()) != null && layoutParams2.width == -1) {
            View view10 = dynamicIslandData.getView();
            ViewGroup.LayoutParams layoutParams5 = view10 != null ? view10.getLayoutParams() : null;
            if (layoutParams5 != null) {
                layoutParams5.width = getExpandedViewMaxWidth();
            }
        }
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.expanded_min_height);
        View view11 = dynamicIslandData.getView();
        int i2 = (view11 == null || (layoutParams = view11.getLayoutParams()) == null) ? 0 : layoutParams.height;
        if (1 <= i2 && i2 < dimensionPixelSize) {
            View view12 = dynamicIslandData.getView();
            ViewGroup.LayoutParams layoutParams6 = view12 != null ? view12.getLayoutParams() : null;
            if (layoutParams6 != null) {
                layoutParams6.height = dimensionPixelSize;
            }
        }
        DynamicIslandExpandedView expandedView = getExpandedView();
        if (expandedView != null) {
            expandedView.removeContentView$miui_dynamicisland_release();
        }
        DynamicIslandExpandedView expandedView2 = getExpandedView();
        if (expandedView2 != null) {
            expandedView2.setContentView$miui_dynamicisland_release(dynamicIslandData.getView());
        }
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void updateMarginTop() {
        updateMarginTop(getSmallIslandView());
        updateMarginTop(getBigIslandView());
        updateMarginTop(getExpandedView());
        updateMarginTop(getMiniBar());
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public void updateSmallIslandView(DynamicIslandData dynamicIslandData, boolean z2) {
        FrameLayout smallIslandView;
        if (!z2 && (smallIslandView = getSmallIslandView()) != null) {
            smallIslandView.removeAllViews();
        }
        IslandTemplateFactory islandTemplateFactory = getController().getIslandTemplateFactory();
        Context context = getContext();
        n.f(context, "getContext(...)");
        FrameLayout smallIslandView2 = getSmallIslandView();
        n.e(smallIslandView2, "null cannot be cast to non-null type android.view.ViewGroup");
        islandTemplateFactory.createSmallIslandTemplate(context, dynamicIslandData, smallIslandView2, false, new C06521(this));
    }

    public final void updateViewStateWhenCloseEnd() {
        FrameLayout smallIslandView;
        setVisibility(0);
        if (getState() instanceof DynamicIslandState.BigIsland) {
            DynamicIslandBigIslandView bigIslandView = getBigIslandView();
            if (bigIslandView == null) {
                return;
            }
            bigIslandView.setVisibility(0);
            return;
        }
        if (!(getState() instanceof DynamicIslandState.SmallIsland) || (smallIslandView = getSmallIslandView()) == null) {
            return;
        }
        smallIslandView.setVisibility(0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r3v6, types: [miui.systemui.dynamicisland.window.content.DynamicIslandContentView$exposeListener$1] */
    /* JADX WARN: Type inference failed for: r3v7, types: [miui.systemui.dynamicisland.window.content.DynamicIslandContentView$SHADOW_ALPHA$1] */
    public DynamicIslandContentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
        AntiBurnInManager.Companion companion = AntiBurnInManager.Companion;
        this.remainingUnit = companion.getMAX_EXPOSE_TIME_OUT();
        this.remainingBurnInUnit = companion.getEXTEND_EXPOSE_TIME();
        this.containerBgAlpha = 1.0f;
        this.burnInState = AntiBurnInManager.BurnInStates.Normal;
        this.shadeBackUp = new WeakHashMap<>();
        this.exposeListener = new ViewTreeObserver.OnPreDrawListener() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentView$exposeListener$1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                this.this$0.getController().onPreDraw();
                return true;
            }
        };
        this.SHADOW_ALPHA = new FloatProperty<DynamicIslandContentView>() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentView$SHADOW_ALPHA$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(DynamicIslandContentView view) {
                n.g(view, "view");
                return view.shadowAlpha;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(DynamicIslandContentView view, float f2) {
                n.g(view, "view");
                if (Float.isNaN(f2)) {
                    return;
                }
                view.setShadowAlpha(f2);
            }
        };
    }

    @Override // miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView
    public DynamicIslandContentViewController getController() {
        DynamicIslandBaseContentViewController<?> controller = super.getController();
        n.e(controller, "null cannot be cast to non-null type miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController");
        return (DynamicIslandContentViewController) controller;
    }
}
