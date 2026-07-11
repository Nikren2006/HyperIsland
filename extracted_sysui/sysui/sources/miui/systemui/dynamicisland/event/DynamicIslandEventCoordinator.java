package miui.systemui.dynamicisland.event;

import H0.d;
import H0.i;
import H0.k;
import H0.s;
import I0.m;
import M0.c;
import N0.f;
import N0.l;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import com.android.systemui.miui.volume.VolumePanelViewController;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandContent;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import g1.AbstractC0369g;
import g1.E;
import g1.M;
import j1.AbstractC0420h;
import j1.I;
import j1.K;
import j1.u;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.D;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationCallback;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationCallbackType;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationController;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationType;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.display.AntiBurnInManager;
import miui.systemui.dynamicisland.display.AvoidScreenBurnInHelper;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.handler.AppStateHandler;
import miui.systemui.dynamicisland.event.handler.BigIslandStateHandler;
import miui.systemui.dynamicisland.event.handler.ExpandedStateHandler;
import miui.systemui.dynamicisland.event.handler.HiddenStateHandler;
import miui.systemui.dynamicisland.event.handler.MiniWindowStateHandler;
import miui.systemui.dynamicisland.event.handler.SmallIslandStateHandler;
import miui.systemui.dynamicisland.view.DynamicGlowEffectView;
import miui.systemui.dynamicisland.view.DynamicIslandBigIslandView;
import miui.systemui.dynamicisland.view.DynamicIslandExpandedView;
import miui.systemui.dynamicisland.window.AppLockController;
import miui.systemui.dynamicisland.window.DynamicIslandAnimUtils;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;
import miui.systemui.notification.NotificationSettingsManager;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ConvenienceExtensionsKt;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandEventCoordinator implements DynamicIslandAnimationController.DynamicIslandAnimationCallback {
    public static final Companion Companion = new Companion(null);
    private static final Region NON_INTERACTIVE_REGION = new Region();
    public static final String TAG = "DynamicIslandEventCoordinator";
    public static final String TAG_ERROR = "DynamicIslandEventCoordinator ERROR";
    private final u _bigIslandRegion;
    private final u _expandedState;
    private final u _expandedViewRegion;
    private u _headsUpZone;
    private final u _islandRegion;
    private final u _state;
    private u _stateBarVisible;
    private final u _touchRegion;
    private boolean _userExpanded;
    private final d addEventCoordinator$delegate;
    private final d animationController$delegate;
    private final AntiBurnInManager antiBurnInManager;
    private final d appEventCoordinator$delegate;
    private final AppLockController appLockController;
    private final AppStateHandler appStateHandler;
    private final d avoidScreenBurnInEventCoordinator$delegate;
    private final AvoidScreenBurnInHelper avoidScreenBurnInHelper;
    private final I bigIslandRegion;
    private final BigIslandStateHandler bigIslandStateHandler;
    private final d clickEventCoordinator$delegate;
    private boolean collapseAnimationRunning;
    private int collapseAnimationRunningCount;
    private final d collapseEventCoordinator$delegate;
    private final d configChangedEventCoordinator$delegate;
    private final Context ctx;
    private final d deletedEventCoordinator$delegate;
    private DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener;
    private final DynamicIslandWindowState dynamicIslandWindowState;
    private boolean enterMiniWindow;
    private final ConcurrentLinkedQueue<DynamicIslandEvent> eventQueue;
    private final I expandedState;
    private final ExpandedStateHandler expandedStateHandler;
    private final I expandedViewRegion;
    private int freeformBottom;
    private DynamicIslandEvent handlingEvent;
    private boolean hasAppExpandedState;
    private boolean hasResetPress;
    private final I headsUpZone;
    private ArrayList<DynamicIslandContentView> hiddenList;
    private final HiddenStateHandler hiddenStateHandler;
    private boolean horizontalSwipeDispatched;
    private boolean isAnimationRunning;
    private boolean isExpandedChanged;
    private final I islandRegion;
    private final d islandTempHiddenEventCoordinator$delegate;
    private boolean keyguardShowing;
    private String lastFullScreenActivityPkg;
    private final d miniWindowEventCoordinator$delegate;
    private final MiniWindowStateHandler miniWindowStateHandler;
    private HashMap<String, Boolean> needExtendLifetime;
    private final d notifySettingsManager$delegate;
    private HashMap<String, Boolean> pkgSupportFreeform;
    private final E scope;
    private final SmallIslandStateHandler smallIslandStateHandler;
    private final I state;
    private final I statusBarVisible;
    private final SuperSaveModeController superSaveModeController;
    private final d swipeEventCoordinator$delegate;
    private boolean swiping;
    private final I touchRegion;
    private final d updateEventCoordinator$delegate;
    private boolean verticalSwipeDispatched;
    private boolean windowAnimRunning;
    private final DynamicIslandWindowView windowView;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator$2, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator$2", f = "DynamicIslandEventCoordinator.kt", l = {180}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends l implements Function2 {
        int label;

        public AnonymousClass2(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandEventCoordinator.this.new AnonymousClass2(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass2) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                DynamicIslandEventCoordinator.this.avoidScreenBurnInHelper.setEventCoordinator(DynamicIslandEventCoordinator.this);
                this.label = 1;
                if (M.a(this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator$3, reason: invalid class name */
    public static final class AnonymousClass3 extends o implements Function1 {
        public AnonymousClass3() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return s.f314a;
        }

        public final void invoke(Throwable th) {
            DynamicIslandEventCoordinator eventCoordinator = DynamicIslandEventCoordinator.this.avoidScreenBurnInHelper.getEventCoordinator();
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator = DynamicIslandEventCoordinator.this;
            if (eventCoordinator == dynamicIslandEventCoordinator) {
                dynamicIslandEventCoordinator.avoidScreenBurnInHelper.setEventCoordinator(null);
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator$initBigIslandEffectShader$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        public AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((String) obj);
            return s.f314a;
        }

        public final void invoke(String it) {
            DynamicIslandData currentIslandData;
            Integer properties;
            DynamicIslandContentView currentTempShow;
            DynamicIslandBigIslandView bigIslandView;
            n.g(it, "it");
            DynamicIslandContentView currentTempShow2 = DynamicIslandEventCoordinator.this.getBigIslandStateHandler().getCurrentTempShow();
            if (currentTempShow2 == null || (currentIslandData = currentTempShow2.getCurrentIslandData()) == null || (properties = currentIslandData.getProperties()) == null || properties.intValue() != 0) {
                return;
            }
            DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
            DynamicIslandContentView currentTempShow3 = DynamicIslandEventCoordinator.this.getBigIslandStateHandler().getCurrentTempShow();
            if (!dynamicIslandUtils.isGlowEffectEnabledForBigState$miui_dynamicisland_release(currentTempShow3 != null ? currentTempShow3.getCurrentIslandData() : null) || (currentTempShow = DynamicIslandEventCoordinator.this.getBigIslandStateHandler().getCurrentTempShow()) == null || (bigIslandView = currentTempShow.getBigIslandView()) == null) {
                return;
            }
            bigIslandView.startGlowEffect$miui_dynamicisland_release();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator$initBigIslandEffectShader$2, reason: invalid class name and case insensitive filesystem */
    public static final class C06042 extends o implements Function1 {
        public C06042() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((String) obj);
            return s.f314a;
        }

        public final void invoke(String it) {
            DynamicIslandBigIslandView bigIslandView;
            n.g(it, "it");
            DynamicIslandContentView lastTempShow = DynamicIslandEventCoordinator.this.getBigIslandStateHandler().getLastTempShow();
            if (lastTempShow != null && (bigIslandView = lastTempShow.getBigIslandView()) != null) {
                DynamicGlowEffectView.stopGlowEffect$miui_dynamicisland_release$default(bigIslandView, false, 1, null);
            }
            DynamicIslandEventCoordinator.this.getBigIslandStateHandler().setLastTempShow(null);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator$initExpandEffectShader$1, reason: invalid class name and case insensitive filesystem */
    public static final class C06051 extends o implements Function1 {
        public C06051() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((String) obj);
            return s.f314a;
        }

        public final void invoke(String it) {
            DynamicIslandExpandedView expandedView;
            n.g(it, "it");
            DynamicIslandContentView current = DynamicIslandEventCoordinator.this.getExpandedStateHandler().getCurrent();
            if (current != null && (expandedView = current.getExpandedView()) != null) {
                expandedView.startCallingEffectIfNeeded$miui_dynamicisland_release();
            }
            if (DynamicIslandEventCoordinator.this.getExpandedStateHandler().getCurrent() == null) {
                Log.w(DynamicIslandEventCoordinator.TAG, "Anim TO_EXPANDED:ANIM_FINISH expandedStateHandler.current is null");
            }
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator$initExpandEffectShader$2, reason: invalid class name and case insensitive filesystem */
    public static final class C06062 extends o implements Function1 {
        public C06062() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((String) obj);
            return s.f314a;
        }

        public final void invoke(String it) {
            DynamicIslandContentView current;
            DynamicIslandExpandedView expandedView;
            n.g(it, "it");
            DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
            DynamicIslandContentView current2 = DynamicIslandEventCoordinator.this.getExpandedStateHandler().getCurrent();
            if (!dynamicIslandUtils.isGlowEffectEnabledForExpandState$miui_dynamicisland_release(current2 != null ? current2.getCurrentIslandData() : null) || (current = DynamicIslandEventCoordinator.this.getExpandedStateHandler().getCurrent()) == null || (expandedView = current.getExpandedView()) == null) {
                return;
            }
            expandedView.startGlowEffect$miui_dynamicisland_release();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DynamicIslandEventCoordinator(@DynamicIsland E scope, Context ctx, DynamicIslandWindowView windowView, AvoidScreenBurnInHelper avoidScreenBurnInHelper, ExpandedStateHandler expandedStateHandler, BigIslandStateHandler bigIslandStateHandler, SmallIslandStateHandler smallIslandStateHandler, HiddenStateHandler hiddenStateHandler, AppStateHandler appStateHandler, MiniWindowStateHandler miniWindowStateHandler, DynamicIslandWindowState dynamicIslandWindowState, SuperSaveModeController superSaveModeController, AppLockController appLockController, AntiBurnInManager antiBurnInManager, E0.a addEventCoordinator, E0.a clickEventCoordinator, E0.a deletedEventCoordinator, E0.a collapseEventCoordinator, E0.a swipeEventCoordinator, E0.a updateEventCoordinator, E0.a appEventCoordinator, E0.a miniWindowEventCoordinator, E0.a islandTempHiddenEventCoordinator, E0.a configChangedEventCoordinator, E0.a avoidScreenBurnInEventCoordinator, E0.a animController, E0.a notificationSettingsManager) {
        n.g(scope, "scope");
        n.g(ctx, "ctx");
        n.g(windowView, "windowView");
        n.g(avoidScreenBurnInHelper, "avoidScreenBurnInHelper");
        n.g(expandedStateHandler, "expandedStateHandler");
        n.g(bigIslandStateHandler, "bigIslandStateHandler");
        n.g(smallIslandStateHandler, "smallIslandStateHandler");
        n.g(hiddenStateHandler, "hiddenStateHandler");
        n.g(appStateHandler, "appStateHandler");
        n.g(miniWindowStateHandler, "miniWindowStateHandler");
        n.g(dynamicIslandWindowState, "dynamicIslandWindowState");
        n.g(superSaveModeController, "superSaveModeController");
        n.g(appLockController, "appLockController");
        n.g(antiBurnInManager, "antiBurnInManager");
        n.g(addEventCoordinator, "addEventCoordinator");
        n.g(clickEventCoordinator, "clickEventCoordinator");
        n.g(deletedEventCoordinator, "deletedEventCoordinator");
        n.g(collapseEventCoordinator, "collapseEventCoordinator");
        n.g(swipeEventCoordinator, "swipeEventCoordinator");
        n.g(updateEventCoordinator, "updateEventCoordinator");
        n.g(appEventCoordinator, "appEventCoordinator");
        n.g(miniWindowEventCoordinator, "miniWindowEventCoordinator");
        n.g(islandTempHiddenEventCoordinator, "islandTempHiddenEventCoordinator");
        n.g(configChangedEventCoordinator, "configChangedEventCoordinator");
        n.g(avoidScreenBurnInEventCoordinator, "avoidScreenBurnInEventCoordinator");
        n.g(animController, "animController");
        n.g(notificationSettingsManager, "notificationSettingsManager");
        this.scope = scope;
        this.ctx = ctx;
        this.windowView = windowView;
        this.avoidScreenBurnInHelper = avoidScreenBurnInHelper;
        this.expandedStateHandler = expandedStateHandler;
        this.bigIslandStateHandler = bigIslandStateHandler;
        this.smallIslandStateHandler = smallIslandStateHandler;
        this.hiddenStateHandler = hiddenStateHandler;
        this.appStateHandler = appStateHandler;
        this.miniWindowStateHandler = miniWindowStateHandler;
        this.dynamicIslandWindowState = dynamicIslandWindowState;
        this.superSaveModeController = superSaveModeController;
        this.appLockController = appLockController;
        this.antiBurnInManager = antiBurnInManager;
        this.addEventCoordinator$delegate = ConvenienceExtensionsKt.getKotlinLazy(addEventCoordinator);
        this.clickEventCoordinator$delegate = ConvenienceExtensionsKt.getKotlinLazy(clickEventCoordinator);
        this.deletedEventCoordinator$delegate = ConvenienceExtensionsKt.getKotlinLazy(deletedEventCoordinator);
        this.collapseEventCoordinator$delegate = ConvenienceExtensionsKt.getKotlinLazy(collapseEventCoordinator);
        this.swipeEventCoordinator$delegate = ConvenienceExtensionsKt.getKotlinLazy(swipeEventCoordinator);
        this.updateEventCoordinator$delegate = ConvenienceExtensionsKt.getKotlinLazy(updateEventCoordinator);
        this.appEventCoordinator$delegate = ConvenienceExtensionsKt.getKotlinLazy(appEventCoordinator);
        this.miniWindowEventCoordinator$delegate = ConvenienceExtensionsKt.getKotlinLazy(miniWindowEventCoordinator);
        this.islandTempHiddenEventCoordinator$delegate = ConvenienceExtensionsKt.getKotlinLazy(islandTempHiddenEventCoordinator);
        this.configChangedEventCoordinator$delegate = ConvenienceExtensionsKt.getKotlinLazy(configChangedEventCoordinator);
        this.avoidScreenBurnInEventCoordinator$delegate = ConvenienceExtensionsKt.getKotlinLazy(avoidScreenBurnInEventCoordinator);
        this.eventQueue = new ConcurrentLinkedQueue<>();
        this.animationController$delegate = ConvenienceExtensionsKt.getKotlinLazy(animController);
        this.notifySettingsManager$delegate = ConvenienceExtensionsKt.getKotlinLazy(notificationSettingsManager);
        u uVarA = K.a(0);
        this._state = uVarA;
        this.state = AbstractC0420h.b(uVarA);
        u uVarA2 = K.a(getDefaultIslandTouchRegion());
        this._touchRegion = uVarA2;
        this.touchRegion = AbstractC0420h.b(uVarA2);
        u uVarA3 = K.a(new Region());
        this._islandRegion = uVarA3;
        this.islandRegion = AbstractC0420h.b(uVarA3);
        u uVarA4 = K.a(new Region());
        this._bigIslandRegion = uVarA4;
        this.bigIslandRegion = AbstractC0420h.b(uVarA4);
        u uVarA5 = K.a(new Region());
        this._expandedViewRegion = uVarA5;
        this.expandedViewRegion = AbstractC0420h.b(uVarA5);
        Boolean bool = Boolean.FALSE;
        u uVarA6 = K.a(bool);
        this._expandedState = uVarA6;
        this.expandedState = AbstractC0420h.b(uVarA6);
        u uVarA7 = K.a(new i(0, 0));
        this._headsUpZone = uVarA7;
        this.headsUpZone = AbstractC0420h.b(uVarA7);
        u uVarA8 = K.a(bool);
        this._stateBarVisible = uVarA8;
        this.statusBarVisible = AbstractC0420h.b(uVarA8);
        this.hiddenList = new ArrayList<>();
        this.pkgSupportFreeform = new HashMap<>();
        this.needExtendLifetime = new HashMap<>();
        this.lastFullScreenActivityPkg = "";
        initStateHandler();
        ArrayList<DynamicIslandContentView> arrayList = this.hiddenList;
        DynamicIslandContentView dynamicIslandContentView = new DynamicIslandContentView(ctx, null, 2, null == true ? 1 : 0);
        dynamicIslandContentView.setState(new DynamicIslandState.Empty());
        arrayList.add(dynamicIslandContentView);
        AbstractC0369g.b(scope, null, null, new AnonymousClass2(null), 3, null).l(new AnonymousClass3());
        appLockController.init();
        initExpandEffectShader();
        initBigIslandEffectShader();
    }

    private final void checkError(DynamicIslandEvent dynamicIslandEvent) {
        DynamicIslandData currentIslandData;
        String key;
        DynamicIslandData currentIslandData2;
        if (this.smallIslandStateHandler.getCurrent() != null && this.bigIslandStateHandler.getCurrent() == null) {
            Log.e(TAG_ERROR, "当前事件:" + dynamicIslandEvent + " 导致状态错乱，单独有小岛");
            if (CommonUtils.isUserRoot() || CommonUtils.isUserDebug()) {
                Toast.makeText(this.ctx, "出错了，单独有小岛, 截图抓日志, 之后测试需重启SystemUI", 0).show();
            }
        }
        ArrayList<DynamicIslandContentView> arrayList = new ArrayList();
        arrayList.add(this.expandedStateHandler.getCurrent());
        arrayList.add(this.bigIslandStateHandler.getCurrent());
        arrayList.add(this.smallIslandStateHandler.getCurrent());
        arrayList.addAll(this.hiddenList);
        ArrayList<DynamicIslandContentView> currentList = this.appStateHandler.getCurrentList();
        if (currentList != null) {
            arrayList.addAll(currentList);
        }
        Iterator<Map.Entry<String, ArrayList<DynamicIslandContentView>>> it = this.miniWindowStateHandler.getCurrentMap().entrySet().iterator();
        while (it.hasNext()) {
            ArrayList<DynamicIslandContentView> value = it.next().getValue();
            if (value != null) {
                arrayList.addAll(value);
            }
        }
        if (!(dynamicIslandEvent instanceof DynamicIslandEvent.DeletedDynamicIsland)) {
            for (DynamicIslandContentView dynamicIslandContentView : arrayList) {
                Boolean boolValueOf = dynamicIslandContentView != null ? Boolean.valueOf(dynamicIslandContentView.isAttachedToWindow()) : null;
                ViewParent parent = dynamicIslandContentView != null ? dynamicIslandContentView.getParent() : null;
                Object parent2 = dynamicIslandContentView != null ? dynamicIslandContentView.getParent() : null;
                View view = parent2 instanceof View ? (View) parent2 : null;
                ViewParent parent3 = view != null ? view.getParent() : null;
                Object parent4 = dynamicIslandContentView != null ? dynamicIslandContentView.getParent() : null;
                View view2 = parent4 instanceof View ? (View) parent4 : null;
                Log.e(TAG_ERROR, "checkError " + boolValueOf + " " + parent + " " + parent3 + " " + (view2 != null ? Boolean.valueOf(view2.isAttachedToWindow()) : null) + " " + ((dynamicIslandContentView == null || (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData2.getKey()));
                if ((dynamicIslandContentView != null ? dynamicIslandContentView.getParent() : null) != null) {
                    Object parent5 = dynamicIslandContentView.getParent();
                    n.e(parent5, "null cannot be cast to non-null type android.view.View");
                    if (((View) parent5).getParent() != null) {
                        Object parent6 = dynamicIslandContentView.getParent();
                        n.e(parent6, "null cannot be cast to non-null type android.view.View");
                        if (!((View) parent6).isAttachedToWindow()) {
                        }
                    }
                    Log.e(TAG_ERROR, "checkError has Error ");
                    dispatchEvent(DynamicIslandEvent.DeletedDynamicIsland.INSTANCE, dynamicIslandContentView);
                    DynamicIslandWindowView dynamicIslandWindowView = this.windowView;
                    DynamicIslandData currentIslandData3 = dynamicIslandContentView.getCurrentIslandData();
                    DynamicIslandData currentIslandData4 = dynamicIslandContentView.getCurrentIslandData();
                    dynamicIslandWindowView.clearAfterDelete(currentIslandData3, currentIslandData4 != null ? currentIslandData4.getKey() : null, true);
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (DynamicIslandContentView dynamicIslandContentView2 : arrayList) {
            if (dynamicIslandContentView2 != null && (currentIslandData = dynamicIslandContentView2.getCurrentIslandData()) != null && (key = currentIslandData.getKey()) != null && !hashSet.add(key)) {
                DynamicIslandData currentIslandData5 = dynamicIslandContentView2.getCurrentIslandData();
                Log.e(TAG_ERROR, "当前事件:" + dynamicIslandEvent + " 导致状态错乱，" + (currentIslandData5 != null ? currentIslandData5.getKey() : null) + " 有重复状态");
                if (CommonUtils.isUserRoot() || CommonUtils.isUserDebug()) {
                    Toast.makeText(this.ctx, "出错了，有重复状态, 截图抓日志, 之后测试需重启SystemUI", 0).show();
                }
            }
        }
    }

    public static /* synthetic */ void dispatchEvent$default(DynamicIslandEventCoordinator dynamicIslandEventCoordinator, DynamicIslandEvent dynamicIslandEvent, DynamicIslandContentView dynamicIslandContentView, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            dynamicIslandContentView = null;
        }
        dynamicIslandEventCoordinator.dispatchEvent(dynamicIslandEvent, dynamicIslandContentView);
    }

    private final AddEventCoordinator getAddEventCoordinator() {
        return (AddEventCoordinator) this.addEventCoordinator$delegate.getValue();
    }

    private final AppEventCoordinator getAppEventCoordinator() {
        return (AppEventCoordinator) this.appEventCoordinator$delegate.getValue();
    }

    private final AvoidScreenBurnInEventCoordinator getAvoidScreenBurnInEventCoordinator() {
        return (AvoidScreenBurnInEventCoordinator) this.avoidScreenBurnInEventCoordinator$delegate.getValue();
    }

    private final ClickEventCoordinator getClickEventCoordinator() {
        return (ClickEventCoordinator) this.clickEventCoordinator$delegate.getValue();
    }

    private final CollapseEventCoordinator getCollapseEventCoordinator() {
        return (CollapseEventCoordinator) this.collapseEventCoordinator$delegate.getValue();
    }

    private final ConfigChangedEventCoordinator getConfigChangedEventCoordinator() {
        return (ConfigChangedEventCoordinator) this.configChangedEventCoordinator$delegate.getValue();
    }

    private final DeletedEventCoordinator getDeletedEventCoordinator() {
        return (DeletedEventCoordinator) this.deletedEventCoordinator$delegate.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Region getFakeExpandedTrackingRegion(Region region) {
        DynamicIslandContentFakeView fakeView;
        I startEnterMiniWindowBeforeAnimation;
        I trackingToOpenMW;
        I startEnterMiniWindowBeforeAnimation2;
        I trackingToOpenMW2;
        DynamicIslandContentView current = this.expandedStateHandler.getCurrent();
        Object fullScreenRegion = null;
        if (current != null) {
            DynamicIslandContentFakeView fakeView2 = current.getFakeView();
            Boolean bool = (fakeView2 == null || (trackingToOpenMW2 = fakeView2.getTrackingToOpenMW()) == null) ? null : (Boolean) trackingToOpenMW2.getValue();
            DynamicIslandContentFakeView fakeView3 = current.getFakeView();
            if (fakeView3 != null && (startEnterMiniWindowBeforeAnimation2 = fakeView3.getStartEnterMiniWindowBeforeAnimation()) != null) {
                fullScreenRegion = (Boolean) startEnterMiniWindowBeforeAnimation2.getValue();
            }
            Log.d(DynamicIslandConstants.TAG_DEBUG_TOUCH, "getFakeExpandedTrackingRegion " + bool + " " + fullScreenRegion);
            DynamicIslandContentFakeView fakeView4 = current.getFakeView();
            fullScreenRegion = ((fakeView4 == null || (trackingToOpenMW = fakeView4.getTrackingToOpenMW()) == null || !((Boolean) trackingToOpenMW.getValue()).booleanValue()) && ((fakeView = current.getFakeView()) == null || (startEnterMiniWindowBeforeAnimation = fakeView.getStartEnterMiniWindowBeforeAnimation()) == null || !((Boolean) startEnterMiniWindowBeforeAnimation.getValue()).booleanValue())) ? region : getFullScreenRegion();
        }
        return fullScreenRegion == null ? region : fullScreenRegion;
    }

    private final IslandTempHiddenEventCoordinator getIslandTempHiddenEventCoordinator() {
        return (IslandTempHiddenEventCoordinator) this.islandTempHiddenEventCoordinator$delegate.getValue();
    }

    private final MiniWindowEventCoordinator getMiniWindowEventCoordinator() {
        return (MiniWindowEventCoordinator) this.miniWindowEventCoordinator$delegate.getValue();
    }

    private final int getNavBarFrameHeight() {
        try {
            Resources system = Resources.getSystem();
            n.f(system, "getSystem(...)");
            int identifier = system.getIdentifier("navigation_bar_frame_height", "dimen", SystemMediaRouteProvider.PACKAGE_NAME);
            if (identifier != 0) {
                return system.getDimensionPixelSize(identifier);
            }
            return 0;
        } catch (Exception e2) {
            Log.e(TAG, "getNavBarFrameHeight: " + e2);
            return 0;
        }
    }

    private final NotificationSettingsManager getNotifySettingsManager() {
        return (NotificationSettingsManager) this.notifySettingsManager$delegate.getValue();
    }

    private final Region getSmallBigIslandRegion() {
        i iVarA;
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        int screenWidthOld = dynamicIslandUtils.getScreenWidthOld(this.ctx) / 2;
        float islandMaxWidth = this.windowView.getWindowViewController().getIslandMaxWidth();
        DynamicIslandContentView current = this.smallIslandStateHandler.getCurrent();
        DynamicIslandContentView current2 = this.bigIslandStateHandler.getCurrent();
        DynamicIslandContentView current3 = this.expandedStateHandler.getCurrent();
        int iDpToPx = screenWidthOld - dynamicIslandUtils.dpToPx(100, this.ctx);
        int iDpToPx2 = dynamicIslandUtils.dpToPx(42, this.ctx);
        int iDpToPx3 = dynamicIslandUtils.dpToPx(10, this.ctx);
        int iDpToPx4 = screenWidthOld + dynamicIslandUtils.dpToPx(100, this.ctx);
        if (current2 == null) {
            if (current != null) {
                Log.e(DynamicIslandConstants.TAG_DEBUG_TOUCH, "error small island only");
            }
            if (current3 != null) {
                return new Region();
            }
            Region region = new Region();
            region.set(iDpToPx, iDpToPx3, iDpToPx4, iDpToPx2);
            return region;
        }
        int currentBigIslandX$default = DynamicIslandBaseContentView.getCurrentBigIslandX$default(current2, null, 1, null);
        int currentBigIslandWidth$default = DynamicIslandBaseContentView.getCurrentBigIslandWidth$default(current2, null, 1, null);
        int smallIslandViewWidth = current != null ? current.getSmallIslandViewWidth() : 0;
        int iDpToPx5 = dynamicIslandUtils.dpToPx(10, this.ctx);
        i iVarA2 = CommonUtils.isLayoutRtl(this.ctx) ? H0.o.a(Integer.valueOf(getSmallXInRtl()), Integer.valueOf(currentBigIslandX$default + currentBigIslandWidth$default)) : H0.o.a(Integer.valueOf(currentBigIslandX$default), Integer.valueOf(currentBigIslandX$default + currentBigIslandWidth$default + smallIslandViewWidth));
        float fAbs = Math.abs(((Number) iVarA2.e()).intValue() - ((Number) iVarA2.d()).intValue()) + (iDpToPx5 * 2);
        if (fAbs <= islandMaxWidth || islandMaxWidth <= 0.0f) {
            iVarA = H0.o.a(Integer.valueOf(((Number) iVarA2.d()).intValue() - iDpToPx5), Integer.valueOf(((Number) iVarA2.e()).intValue() + iDpToPx5));
        } else {
            float f2 = (fAbs - islandMaxWidth) / 2;
            iVarA = H0.o.a(Float.valueOf(((Number) iVarA2.d()).floatValue() - f2), Float.valueOf(((Number) iVarA2.e()).floatValue() + f2));
        }
        Object objA = iVarA.a();
        Object objB = iVarA.b();
        Region region2 = new Region();
        region2.set(((Number) objA).intValue(), current2.getIslandViewMarginTop() - dynamicIslandUtils.dpToPx(4, this.ctx), ((Number) objB).intValue(), current2.getIslandViewMarginTop() + current2.getIslandViewHeight() + dynamicIslandUtils.dpToPx(4, this.ctx));
        return region2;
    }

    private final int getSmallXInRtl() {
        DynamicIslandContentView current = this.bigIslandStateHandler.getCurrent();
        int currentBigIslandX$default = current != null ? DynamicIslandBaseContentView.getCurrentBigIslandX$default(current, null, 1, null) : 0;
        DynamicIslandContentView current2 = this.smallIslandStateHandler.getCurrent();
        int smallIslandViewWidth = current2 != null ? current2.getSmallIslandViewWidth() : 0;
        DynamicIslandContentView current3 = this.bigIslandStateHandler.getCurrent();
        return currentBigIslandX$default - (smallIslandViewWidth > 0 ? (current3 != null ? current3.getSpace() : 0) + smallIslandViewWidth : 0);
    }

    private final SwipeEventCoordinator getSwipeEventCoordinator() {
        return (SwipeEventCoordinator) this.swipeEventCoordinator$delegate.getValue();
    }

    private final UpdateEventCoordinator getUpdateEventCoordinator() {
        return (UpdateEventCoordinator) this.updateEventCoordinator$delegate.getValue();
    }

    private final void handleEvent(DynamicIslandEvent dynamicIslandEvent, DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandData currentIslandData;
        DynamicIslandState state;
        DynamicIslandData currentIslandData2;
        DynamicIslandContentView current = this.expandedStateHandler.getCurrent();
        String key = null;
        String key2 = (current == null || (currentIslandData2 = current.getCurrentIslandData()) == null) ? null : currentIslandData2.getKey();
        Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "handleEventStart: " + dynamicIslandEvent);
        preHandleEvent(dynamicIslandEvent);
        this.antiBurnInManager.handleEvent(dynamicIslandEvent, dynamicIslandContentView);
        getAddEventCoordinator().handleAppEvent(dynamicIslandEvent, dynamicIslandContentView, this.hiddenList);
        getClickEventCoordinator().handleAppEvent(dynamicIslandEvent, dynamicIslandContentView, this.hiddenList);
        getCollapseEventCoordinator().handleAppEvent(dynamicIslandEvent, dynamicIslandContentView, this.hiddenList);
        getDeletedEventCoordinator().handleAppEvent(dynamicIslandEvent, dynamicIslandContentView, this.hiddenList);
        getSwipeEventCoordinator().handleAppEvent(dynamicIslandEvent, dynamicIslandContentView, this.hiddenList);
        if (!this.swiping || (this.bigIslandStateHandler.getCurrentTempShow() != null && dynamicIslandContentView != null && (state = dynamicIslandContentView.getState()) != null && state.getTempShow())) {
            getUpdateEventCoordinator().handleAppEvent(dynamicIslandEvent, dynamicIslandContentView, this.hiddenList);
        }
        getAppEventCoordinator().handleAppEvent(dynamicIslandEvent, dynamicIslandContentView, this.hiddenList);
        getMiniWindowEventCoordinator().handleAppEvent(dynamicIslandEvent, dynamicIslandContentView, this.hiddenList);
        getIslandTempHiddenEventCoordinator().handleAppEvent(dynamicIslandEvent, dynamicIslandContentView, this.hiddenList);
        getConfigChangedEventCoordinator().handleAppEvent(dynamicIslandEvent, dynamicIslandContentView, this.hiddenList);
        getAvoidScreenBurnInEventCoordinator().handleAppEvent(dynamicIslandEvent, dynamicIslandContentView, this.hiddenList);
        printCurrentDynamicIslandSituation(dynamicIslandEvent, dynamicIslandContentView);
        checkError(dynamicIslandEvent);
        DynamicIslandContentView current2 = this.expandedStateHandler.getCurrent();
        if (current2 != null && (currentIslandData = current2.getCurrentIslandData()) != null) {
            key = currentIslandData.getKey();
        }
        this.isExpandedChanged = !n.c(key, key2);
        Log.e(DynamicIslandConstants.TAG_DEBUG_EVENT, "handleEventStop: " + dynamicIslandEvent);
    }

    private final void initBigIslandEffectShader() {
        DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = getAnimationController().getAnimationCallback$miui_dynamicisland_release();
        DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.INIT_TO_BIG;
        DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
        animationCallback$miui_dynamicisland_release.addAnimationCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, new AnonymousClass1());
        getAnimationController().getAnimationCallback$miui_dynamicisland_release().addAnimationCallback(DynamicIslandAnimationType.BIG_TO_DELETED, dynamicIslandAnimationCallbackType, new C06042());
    }

    private final void initExpandEffectShader() {
        DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = getAnimationController().getAnimationCallback$miui_dynamicisland_release();
        DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.TO_EXPANDED;
        animationCallback$miui_dynamicisland_release.addAnimationCallback(dynamicIslandAnimationType, DynamicIslandAnimationCallbackType.ANIM_FINISH, new C06051());
        getAnimationController().getAnimationCallback$miui_dynamicisland_release().addAnimationCallback(dynamicIslandAnimationType, DynamicIslandAnimationCallbackType.ANIM_START, new C06062());
        for (DynamicIslandAnimationType dynamicIslandAnimationType2 : m.j(DynamicIslandAnimationType.EXPANDED_TO_BIG, DynamicIslandAnimationType.EXPANDED_TO_SMALL, DynamicIslandAnimationType.EXPANDED_TO_TEMP_HIDDEN, DynamicIslandAnimationType.EXPANDED_TO_HIDDEN, DynamicIslandAnimationType.EXPANDED_TO_DELETED)) {
            if (dynamicIslandAnimationType2 == DynamicIslandAnimationType.EXPANDED_TO_BIG || dynamicIslandAnimationType2 == DynamicIslandAnimationType.EXPANDED_TO_SMALL) {
                getAnimationController().getAnimationCallback$miui_dynamicisland_release().addAnimationCallback(dynamicIslandAnimationType2, DynamicIslandAnimationCallbackType.ANIM_START, new DynamicIslandEventCoordinator$initExpandEffectShader$3$1(this));
            } else {
                getAnimationController().getAnimationCallback$miui_dynamicisland_release().addAnimationCallback(dynamicIslandAnimationType2, DynamicIslandAnimationCallbackType.ANIM_START, new DynamicIslandEventCoordinator$initExpandEffectShader$3$2(this));
            }
            getAnimationController().getAnimationCallback$miui_dynamicisland_release().addAnimationCallback(dynamicIslandAnimationType2, DynamicIslandAnimationCallbackType.ANIM_START, new DynamicIslandEventCoordinator$initExpandEffectShader$3$3(this));
            getAnimationController().getAnimationCallback$miui_dynamicisland_release().addAnimationCallback(dynamicIslandAnimationType2, DynamicIslandAnimationCallbackType.ANIM_FINISH, new DynamicIslandEventCoordinator$initExpandEffectShader$3$4(this));
            getAnimationController().getAnimationCallback$miui_dynamicisland_release().addAnimationCallback(dynamicIslandAnimationType2, DynamicIslandAnimationCallbackType.ANIM_CANCEL, new DynamicIslandEventCoordinator$initExpandEffectShader$3$5(this));
        }
    }

    private final void initStateHandler() {
        this.expandedStateHandler.setNext(this.bigIslandStateHandler);
        this.bigIslandStateHandler.setNext(this.smallIslandStateHandler);
        this.smallIslandStateHandler.setNext(this.hiddenStateHandler);
    }

    private final Region mergeTouchRegions(Region... regionArr) {
        Region region = new Region();
        for (Region region2 : regionArr) {
            region.op(region2, Region.Op.UNION);
        }
        return region;
    }

    private final void preHandleEvent(DynamicIslandEvent dynamicIslandEvent) {
        if (this.avoidScreenBurnInHelper.isFadedBefore()) {
            DynamicIslandContentView current = this.bigIslandStateHandler.getCurrent();
            if (current != null) {
                this.avoidScreenBurnInHelper.resetFadedView(current);
            }
            DynamicIslandContentView current2 = this.smallIslandStateHandler.getCurrent();
            if (current2 != null) {
                this.avoidScreenBurnInHelper.resetFadedView(current2);
            }
        }
        if (dynamicIslandEvent instanceof DynamicIslandEvent.IslandTempHiddenChanged) {
            DynamicIslandEvent.IslandTempHiddenChanged islandTempHiddenChanged = (DynamicIslandEvent.IslandTempHiddenChanged) dynamicIslandEvent;
            Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "event.type: " + islandTempHiddenChanged.getType() + " " + islandTempHiddenChanged.getHide());
            if (islandTempHiddenChanged.getType() != DynamicIslandWindowState.TempHiddenType.SCREEN_LOCKED && islandTempHiddenChanged.getType() != DynamicIslandWindowState.TempHiddenType.SHOW_NOTIFICATION_ICONS) {
                this.dynamicIslandWindowState.setScreenLockedChange(false);
            }
        } else {
            if (!(dynamicIslandEvent instanceof DynamicIslandEvent.Collapse)) {
                this.dynamicIslandWindowState.setScreenLockedChange(false);
            }
            this.dynamicIslandWindowState.setTempHiddenChange(Boolean.FALSE);
        }
        if (dynamicIslandEvent instanceof DynamicIslandEvent.ConfigChanged) {
            return;
        }
        this.dynamicIslandWindowState.setConfigChange(Boolean.FALSE);
    }

    private final void printCurrentDynamicIslandSituation(DynamicIslandEvent dynamicIslandEvent, DynamicIslandContentView dynamicIslandContentView) {
        Iterator<Map.Entry<String, ArrayList<DynamicIslandContentView>>> it;
        Integer priority;
        Iterator it2;
        Long l2;
        DynamicIslandState state;
        DynamicIslandData currentIslandData;
        DynamicIslandData currentIslandData2;
        DynamicIslandData currentIslandData3;
        DynamicIslandData currentIslandData4;
        Iterator it3;
        Long time;
        DynamicIslandState state2;
        DynamicIslandData currentIslandData5;
        DynamicIslandData currentIslandData6;
        DynamicIslandData currentIslandData7;
        DynamicIslandData currentIslandData8;
        DynamicIslandState state3;
        DynamicIslandData currentIslandData9;
        DynamicIslandData currentIslandData10;
        DynamicIslandData currentIslandData11;
        DynamicIslandData currentIslandData12;
        DynamicIslandState state4;
        DynamicIslandData currentIslandData13;
        DynamicIslandData currentIslandData14;
        DynamicIslandData currentIslandData15;
        DynamicIslandData currentIslandData16;
        DynamicIslandData currentIslandData17;
        DynamicIslandState state5;
        DynamicIslandData currentIslandData18;
        DynamicIslandData currentIslandData19;
        DynamicIslandData currentIslandData20;
        DynamicIslandData currentIslandData21;
        DynamicIslandData currentIslandData22;
        DynamicIslandState state6;
        DynamicIslandData currentIslandData23;
        DynamicIslandData currentIslandData24;
        DynamicIslandData currentIslandData25;
        DynamicIslandData currentIslandData26;
        DynamicIslandData currentIslandData27;
        DynamicIslandData currentIslandData28;
        DynamicIslandState state7 = dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null;
        String key = (dynamicIslandContentView == null || (currentIslandData28 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData28.getKey();
        String tickerData = (dynamicIslandContentView == null || (currentIslandData27 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData27.getTickerData();
        DynamicIslandContentView current = this.expandedStateHandler.getCurrent();
        String key2 = (current == null || (currentIslandData26 = current.getCurrentIslandData()) == null) ? null : currentIslandData26.getKey();
        DynamicIslandContentView current2 = this.expandedStateHandler.getCurrent();
        String tickerData2 = (current2 == null || (currentIslandData25 = current2.getCurrentIslandData()) == null) ? null : currentIslandData25.getTickerData();
        DynamicIslandContentView current3 = this.expandedStateHandler.getCurrent();
        Integer properties = (current3 == null || (currentIslandData24 = current3.getCurrentIslandData()) == null) ? null : currentIslandData24.getProperties();
        DynamicIslandContentView current4 = this.expandedStateHandler.getCurrent();
        Integer priority2 = (current4 == null || (currentIslandData23 = current4.getCurrentIslandData()) == null) ? null : currentIslandData23.getPriority();
        DynamicIslandContentView current5 = this.expandedStateHandler.getCurrent();
        Long time2 = (current5 == null || (state6 = current5.getState()) == null) ? null : state6.getTime();
        DynamicIslandContentView current6 = this.expandedStateHandler.getCurrent();
        boolean z2 = (current6 != null ? current6.getState() : null) instanceof DynamicIslandState.Empty;
        DynamicIslandContentView current7 = this.bigIslandStateHandler.getCurrent();
        String key3 = (current7 == null || (currentIslandData22 = current7.getCurrentIslandData()) == null) ? null : currentIslandData22.getKey();
        DynamicIslandContentView current8 = this.bigIslandStateHandler.getCurrent();
        String tickerData3 = (current8 == null || (currentIslandData21 = current8.getCurrentIslandData()) == null) ? null : currentIslandData21.getTickerData();
        DynamicIslandContentView current9 = this.bigIslandStateHandler.getCurrent();
        Integer properties2 = (current9 == null || (currentIslandData20 = current9.getCurrentIslandData()) == null) ? null : currentIslandData20.getProperties();
        DynamicIslandContentView current10 = this.bigIslandStateHandler.getCurrent();
        Integer properties3 = (current10 == null || (currentIslandData19 = current10.getCurrentIslandData()) == null) ? null : currentIslandData19.getProperties();
        DynamicIslandContentView current11 = this.bigIslandStateHandler.getCurrent();
        Integer priority3 = (current11 == null || (currentIslandData18 = current11.getCurrentIslandData()) == null) ? null : currentIslandData18.getPriority();
        DynamicIslandContentView current12 = this.bigIslandStateHandler.getCurrent();
        Long time3 = (current12 == null || (state5 = current12.getState()) == null) ? null : state5.getTime();
        DynamicIslandContentView current13 = this.bigIslandStateHandler.getCurrent();
        boolean z3 = (current13 != null ? current13.getState() : null) instanceof DynamicIslandState.Empty;
        DynamicIslandContentView currentTempShow = this.bigIslandStateHandler.getCurrentTempShow();
        String key4 = (currentTempShow == null || (currentIslandData17 = currentTempShow.getCurrentIslandData()) == null) ? null : currentIslandData17.getKey();
        DynamicIslandContentView current14 = this.smallIslandStateHandler.getCurrent();
        String key5 = (current14 == null || (currentIslandData16 = current14.getCurrentIslandData()) == null) ? null : currentIslandData16.getKey();
        DynamicIslandContentView current15 = this.smallIslandStateHandler.getCurrent();
        String tickerData4 = (current15 == null || (currentIslandData15 = current15.getCurrentIslandData()) == null) ? null : currentIslandData15.getTickerData();
        DynamicIslandContentView current16 = this.smallIslandStateHandler.getCurrent();
        Integer properties4 = (current16 == null || (currentIslandData14 = current16.getCurrentIslandData()) == null) ? null : currentIslandData14.getProperties();
        DynamicIslandContentView current17 = this.smallIslandStateHandler.getCurrent();
        Integer priority4 = (current17 == null || (currentIslandData13 = current17.getCurrentIslandData()) == null) ? null : currentIslandData13.getPriority();
        DynamicIslandContentView current18 = this.smallIslandStateHandler.getCurrent();
        Long time4 = (current18 == null || (state4 = current18.getState()) == null) ? null : state4.getTime();
        DynamicIslandContentView current19 = this.smallIslandStateHandler.getCurrent();
        boolean z4 = (current19 != null ? current19.getState() : null) instanceof DynamicIslandState.Empty;
        DynamicIslandContentView current20 = this.hiddenStateHandler.getCurrent();
        String key6 = (current20 == null || (currentIslandData12 = current20.getCurrentIslandData()) == null) ? null : currentIslandData12.getKey();
        DynamicIslandContentView current21 = this.hiddenStateHandler.getCurrent();
        String tickerData5 = (current21 == null || (currentIslandData11 = current21.getCurrentIslandData()) == null) ? null : currentIslandData11.getTickerData();
        DynamicIslandContentView current22 = this.hiddenStateHandler.getCurrent();
        Integer properties5 = (current22 == null || (currentIslandData10 = current22.getCurrentIslandData()) == null) ? null : currentIslandData10.getProperties();
        DynamicIslandContentView current23 = this.hiddenStateHandler.getCurrent();
        Integer priority5 = (current23 == null || (currentIslandData9 = current23.getCurrentIslandData()) == null) ? null : currentIslandData9.getPriority();
        DynamicIslandContentView current24 = this.hiddenStateHandler.getCurrent();
        Long time5 = (current24 == null || (state3 = current24.getState()) == null) ? null : state3.getTime();
        int size = this.hiddenList.size();
        DynamicIslandContentView current25 = this.hiddenStateHandler.getCurrent();
        Log.d(TAG, "\n----------------------------------当前岛的状态----------------------------------------\n当前事件:" + dynamicIslandEvent + ", currentEventState:" + state7 + "event.state.dynamicIslandData?.key:" + key + ",event.state.dynamicIslandData?.tickerData:" + tickerData + " \n--------------------------------------------------------------------------\n 展开态: key : " + key2 + ", tickerData : " + tickerData2 + " properties :" + properties + ", priority: " + priority2 + ", time: " + time2 + " isEmpty: " + z2 + "\n--------------------------------------------------------------------------\n 大岛态: key : " + key3 + ", tickerData : " + tickerData3 + " properties :" + properties2 + ", properties :" + properties3 + ", priority: " + priority3 + ", time: " + time3 + " isEmpty: " + z3 + " tempShow: " + key4 + "\n--------------------------------------------------------------------------\n 小岛态: key : " + key5 + ", tickerData : " + tickerData4 + " properties :" + properties4 + ", priority: " + priority4 + ", time: " + time4 + " isEmpty: " + z4 + "\n--------------------------------------------------------------------------\n 隐藏态: key : " + key6 + ", tickerData : " + tickerData5 + " properties :" + properties5 + ", priority: " + priority5 + ", time: " + time5 + ", hiddenListSize:" + size + " isEmpty: " + ((current25 != null ? current25.getState() : null) instanceof DynamicIslandState.Empty) + "\n------------------------------------应用态 小窗态 隐藏列表大小" + this.hiddenList.size() + ",详请如下:--------------------------------------\n");
        ArrayList<DynamicIslandContentView> currentList = this.appStateHandler.getCurrentList();
        if (currentList != null) {
            Iterator it4 = currentList.iterator();
            int i2 = 0;
            while (it4.hasNext()) {
                Object next = it4.next();
                int i3 = i2 + 1;
                if (i2 < 0) {
                    m.n();
                }
                DynamicIslandContentView dynamicIslandContentView2 = (DynamicIslandContentView) next;
                String key7 = (dynamicIslandContentView2 == null || (currentIslandData8 = dynamicIslandContentView2.getCurrentIslandData()) == null) ? null : currentIslandData8.getKey();
                String tickerData6 = (dynamicIslandContentView2 == null || (currentIslandData7 = dynamicIslandContentView2.getCurrentIslandData()) == null) ? null : currentIslandData7.getTickerData();
                Integer properties6 = (dynamicIslandContentView2 == null || (currentIslandData6 = dynamicIslandContentView2.getCurrentIslandData()) == null) ? null : currentIslandData6.getProperties();
                Integer priority6 = (dynamicIslandContentView2 == null || (currentIslandData5 = dynamicIslandContentView2.getCurrentIslandData()) == null) ? null : currentIslandData5.getPriority();
                if (dynamicIslandContentView2 == null || (state2 = dynamicIslandContentView2.getState()) == null) {
                    it3 = it4;
                    time = null;
                } else {
                    it3 = it4;
                    time = state2.getTime();
                }
                Log.d(TAG, " index : " + i2 + ", 应用态 key : " + key7 + ", tickerData : " + tickerData6 + ", properties :" + properties6 + ", priority: " + priority6 + ", time: " + time + " isEmpty: " + ((dynamicIslandContentView2 != null ? dynamicIslandContentView2.getState() : null) instanceof DynamicIslandState.Empty));
                it4 = it3;
                i2 = i3;
            }
        }
        Iterator<Map.Entry<String, ArrayList<DynamicIslandContentView>>> it5 = this.miniWindowStateHandler.getCurrentMap().entrySet().iterator();
        while (it5.hasNext()) {
            ArrayList<DynamicIslandContentView> value = it5.next().getValue();
            if (value != null) {
                Iterator it6 = value.iterator();
                int i4 = 0;
                while (it6.hasNext()) {
                    Object next2 = it6.next();
                    int i5 = i4 + 1;
                    if (i4 < 0) {
                        m.n();
                    }
                    DynamicIslandContentView dynamicIslandContentView3 = (DynamicIslandContentView) next2;
                    String key8 = (dynamicIslandContentView3 == null || (currentIslandData4 = dynamicIslandContentView3.getCurrentIslandData()) == null) ? null : currentIslandData4.getKey();
                    String tickerData7 = (dynamicIslandContentView3 == null || (currentIslandData3 = dynamicIslandContentView3.getCurrentIslandData()) == null) ? null : currentIslandData3.getTickerData();
                    Integer properties7 = (dynamicIslandContentView3 == null || (currentIslandData2 = dynamicIslandContentView3.getCurrentIslandData()) == null) ? null : currentIslandData2.getProperties();
                    if (dynamicIslandContentView3 == null || (currentIslandData = dynamicIslandContentView3.getCurrentIslandData()) == null) {
                        it = it5;
                        priority = null;
                    } else {
                        it = it5;
                        priority = currentIslandData.getPriority();
                    }
                    if (dynamicIslandContentView3 == null || (state = dynamicIslandContentView3.getState()) == null) {
                        it2 = it6;
                        l2 = null;
                    } else {
                        Long time6 = state.getTime();
                        it2 = it6;
                        l2 = time6;
                    }
                    Log.d(TAG, " index : " + i4 + ", 小窗态 key : " + key8 + ", tickerData : " + tickerData7 + ", properties :" + properties7 + ", priority: " + priority + ", time: " + l2 + " isEmpty: " + ((dynamicIslandContentView3 != null ? dynamicIslandContentView3.getState() : null) instanceof DynamicIslandState.Empty));
                    it5 = it;
                    it6 = it2;
                    i4 = i5;
                }
            }
            it5 = it5;
        }
        Iterator it7 = this.hiddenList.iterator();
        int i6 = 0;
        while (it7.hasNext()) {
            Object next3 = it7.next();
            int i7 = i6 + 1;
            if (i6 < 0) {
                m.n();
            }
            DynamicIslandContentView dynamicIslandContentView4 = (DynamicIslandContentView) next3;
            DynamicIslandData currentIslandData29 = dynamicIslandContentView4.getCurrentIslandData();
            String key9 = currentIslandData29 != null ? currentIslandData29.getKey() : null;
            DynamicIslandData currentIslandData30 = dynamicIslandContentView4.getCurrentIslandData();
            String tickerData8 = currentIslandData30 != null ? currentIslandData30.getTickerData() : null;
            DynamicIslandData currentIslandData31 = dynamicIslandContentView4.getCurrentIslandData();
            Integer properties8 = currentIslandData31 != null ? currentIslandData31.getProperties() : null;
            DynamicIslandData currentIslandData32 = dynamicIslandContentView4.getCurrentIslandData();
            Integer priority7 = currentIslandData32 != null ? currentIslandData32.getPriority() : null;
            DynamicIslandState state8 = dynamicIslandContentView4.getState();
            Long time7 = state8 != null ? state8.getTime() : null;
            Log.d(TAG, "隐藏列表 index : " + i6 + ", key : " + key9 + ", tickerData : " + tickerData8 + ", properties :" + properties8 + ", priority: " + priority7 + ", time: " + time7 + " isEmpty: " + (dynamicIslandContentView4.getState() instanceof DynamicIslandState.Empty));
            it7 = it7;
            i6 = i7;
        }
    }

    private final boolean regionChanged(Region region) {
        new Region(region).op((Region) this._touchRegion.getValue(), Region.Op.XOR);
        return !r0.isEmpty();
    }

    private final void resetStateForExitApp() {
        Bundle extras;
        Iterator it = new ArrayList(getAppEventCoordinator().getExitAppStateList()).iterator();
        n.f(it, "iterator(...)");
        while (it.hasNext()) {
            DynamicIslandContentView dynamicIslandContentView = (DynamicIslandContentView) it.next();
            DynamicIslandData currentIslandData = dynamicIslandContentView.getCurrentIslandData();
            Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "resetStateForExitApp : " + ((currentIslandData == null || (extras = currentIslandData.getExtras()) == null) ? null : extras.getString("miui.pkg.name")));
            dynamicIslandContentView.setOpenAppFromIsland(false);
            DynamicIslandContentFakeView fakeView = dynamicIslandContentView.getFakeView();
            if (fakeView != null) {
                DynamicIslandContentFakeView.updateViewStateWhenCloseEnd$default(fakeView, false, null, 3, null);
            }
        }
        getAppEventCoordinator().getExitAppStateList().clear();
    }

    private final void setShadow() {
        DynamicIslandContentFakeView fakeView;
        DynamicIslandContentFakeView fakeView2;
        DynamicIslandContentView current = this.expandedStateHandler.getCurrent();
        if (current != null) {
            current.showShadowNoANim();
            DynamicIslandContentFakeView fakeView3 = current.getFakeView();
            if (fakeView3 != null) {
                fakeView3.showShadowNoANim();
            }
        }
        DynamicIslandContentView current2 = this.bigIslandStateHandler.getCurrent();
        if (current2 != null) {
            current2.clearShadow();
            DynamicIslandContentFakeView fakeView4 = current2.getFakeView();
            if (fakeView4 != null) {
                fakeView4.clearShadow();
            }
        }
        DynamicIslandContentView current3 = this.smallIslandStateHandler.getCurrent();
        if (current3 != null) {
            current3.clearShadow();
            DynamicIslandContentFakeView fakeView5 = current3.getFakeView();
            if (fakeView5 != null) {
                fakeView5.clearShadow();
            }
        }
        Iterator<Map.Entry<String, ArrayList<DynamicIslandContentView>>> it = this.miniWindowStateHandler.getCurrentMap().entrySet().iterator();
        while (it.hasNext()) {
            ArrayList<DynamicIslandContentView> value = it.next().getValue();
            if (value != null) {
                for (DynamicIslandContentView dynamicIslandContentView : value) {
                    if (dynamicIslandContentView != null) {
                        dynamicIslandContentView.clearShadow();
                    }
                    if (dynamicIslandContentView != null && (fakeView2 = dynamicIslandContentView.getFakeView()) != null) {
                        fakeView2.clearShadow();
                    }
                }
            }
        }
        ArrayList<DynamicIslandContentView> currentList = this.appStateHandler.getCurrentList();
        if (currentList != null) {
            for (DynamicIslandContentView dynamicIslandContentView2 : currentList) {
                if (dynamicIslandContentView2 != null) {
                    dynamicIslandContentView2.clearShadow();
                }
                if (dynamicIslandContentView2 != null && (fakeView = dynamicIslandContentView2.getFakeView()) != null) {
                    fakeView.clearShadow();
                }
            }
        }
    }

    private final void updateWindowHeight() {
        DynamicIslandContentView current = this.bigIslandStateHandler.getCurrent();
        DynamicIslandContentView currentTempShow = this.bigIslandStateHandler.getCurrentTempShow();
        DynamicIslandContentView current2 = this.expandedStateHandler.getCurrent();
        boolean zBooleanValue = ((Boolean) getWindowState().getTempHidden().getValue()).booleanValue();
        if (this.windowAnimRunning) {
            if (DynamicIslandAnimUtils.INSTANCE.supportShowElementAndFreeformAnim()) {
                Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "windowAnimRunning");
                this._state.setValue(Integer.valueOf(DynamicIslandUtils.INSTANCE.getScreenHeightOld(this.ctx)));
                return;
            }
            return;
        }
        if (this.collapseAnimationRunning || this.collapseAnimationRunningCount > 0) {
            Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "collapseAnimation");
            this._state.setValue(Integer.valueOf(DynamicIslandUtils.INSTANCE.getScreenHeightOld(this.ctx)));
            return;
        }
        if (current2 != null) {
            Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "expanded: " + (current2.getExpandedViewY() + current2.getExpandedViewHeight()));
            this._state.setValue(Integer.valueOf(DynamicIslandUtils.INSTANCE.getScreenHeightOld(this.ctx)));
            return;
        }
        if (current != null && !zBooleanValue) {
            Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "bigIsland: " + current.getY() + " " + current.getHeight());
            this._state.setValue(Integer.valueOf(current.getIslandWindowHeight()));
            return;
        }
        if (currentTempShow == null) {
            Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "other");
            this._state.setValue(0);
            return;
        }
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "bigIslandCurrentTempShow: " + currentTempShow.getY() + " " + currentTempShow.getHeight());
        this._state.setValue(Integer.valueOf(currentTempShow.getIslandWindowHeight()));
    }

    private final void updateWindowHeightAnimStart(boolean z2, DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandContentView current = this.bigIslandStateHandler.getCurrent();
        DynamicIslandContentView currentTempShow = this.bigIslandStateHandler.getCurrentTempShow();
        DynamicIslandAnimUtils dynamicIslandAnimUtils = DynamicIslandAnimUtils.INSTANCE;
        if ((dynamicIslandAnimUtils.featureDynamicIslandIsMiddle() || dynamicIslandAnimUtils.featureDynamicIslandNoElementButFreeform()) && islandFreeformAnimating(dynamicIslandContentView)) {
            updateWindowHeightForFreeform(this.freeformBottom);
            return;
        }
        if (z2 || this.collapseAnimationRunningCount > 0 || ((((Number) this._state.getValue()).intValue() == 0 && current != null) || (((Number) this._state.getValue()).intValue() == 0 && currentTempShow != null))) {
            this._state.setValue(Integer.valueOf(DynamicIslandUtils.INSTANCE.getScreenHeightOld(this.ctx)));
        }
    }

    public final void addDynamicIslandViewChangedListener(DynamicIslandContent.DynamicIslandViewChangedListener listener) {
        n.g(listener, "listener");
        this.dynamicIslandViewChangedListener = listener;
    }

    public final void alignBurnInStates() {
        this.antiBurnInManager.alignBurnInStates();
    }

    public final void alreadyCloseAppEnd(Bundle bundle) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onDynamicPluginCallback("onDynamicPluginCallback_alreadyCloseAppEnd", bundle);
        }
    }

    public final void bigToExpanded(Bundle bundle) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onDynamicPluginCallback("onDynamicPluginCallback_bigToExpanded", bundle);
        }
    }

    public final void bigToSmall(Bundle bundle) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onDynamicPluginCallback("onDynamicPluginCallback_bigToSmall", bundle);
        }
    }

    public final DynamicIslandState calculateCollapse(DynamicIslandContentView dynamicIslandContentView) {
        return this.bigIslandStateHandler.calculateCollapse(dynamicIslandContentView);
    }

    public final boolean canExpanded(boolean z2, View view) {
        return this.windowView.canExpanded(z2, view);
    }

    public final boolean canExpandedViewTrack(DynamicIslandContentView dynamicIslandContentView) {
        return getAnimationController().canExpandedViewTrack(dynamicIslandContentView);
    }

    public final void collapseStatusBar(Context context) {
        n.g(context, "context");
        try {
            Object systemService = context.getSystemService(VolumePanelViewController.SERVICE_STATUS_BAR);
            systemService.getClass().getMethod("collapsePanels", null).invoke(systemService, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void dispatchEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView) {
        n.g(event, "event");
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "dispatchEvent: " + event);
        this.handlingEvent = event;
        handleEvent(event, dynamicIslandContentView);
    }

    public final void dispatchPress(boolean z2, boolean z3, boolean z4) {
        this.hasResetPress = false;
        if (z2) {
            getAnimationController().onPress(this.bigIslandStateHandler.getCurrent());
        } else if (z3) {
            getAnimationController().onPress(this.smallIslandStateHandler.getCurrent());
        } else if (z4) {
            getAnimationController().onPress(this.expandedStateHandler.getCurrent());
        }
    }

    public final void dispatchSwipe(float f2, float f3, boolean z2) {
        this.swiping = true;
        if (!this.horizontalSwipeDispatched && !this.verticalSwipeDispatched) {
            if (Math.abs(f2) > Math.abs(f3)) {
                this.horizontalSwipeDispatched = true;
            } else if (Math.abs(f2) < Math.abs(f3)) {
                this.verticalSwipeDispatched = true;
            }
        }
        getSwipeEventCoordinator().handleSwipe(f2, f3, z2, this.hiddenList);
    }

    public final void expandedToBig(Bundle bundle) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onDynamicPluginCallback("onDynamicPluginCallback_expandedToBig", bundle);
        }
    }

    public final void expandedToSmall(Bundle bundle) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onDynamicPluginCallback("onDynamicPluginCallback_expandedToSmall", bundle);
        }
    }

    public final DynamicIslandAnimationController getAnimationController() {
        return (DynamicIslandAnimationController) this.animationController$delegate.getValue();
    }

    public final AppLockController getAppLockController() {
        return this.appLockController;
    }

    public final AppStateHandler getAppStateHandler() {
        return this.appStateHandler;
    }

    public final List<DynamicIslandContentView> getBigIsLandAndSmallIsLandList() {
        DynamicIslandData currentIslandData;
        Bundle extras;
        DynamicIslandData currentIslandData2;
        Bundle extras2;
        ArrayList arrayList = new ArrayList();
        DynamicIslandContentView current = this.bigIslandStateHandler.getCurrent();
        String string = null;
        if (((current == null || (currentIslandData2 = current.getCurrentIslandData()) == null || (extras2 = currentIslandData2.getExtras()) == null) ? null : extras2.getString("miui.pkg.name")) != null) {
            arrayList.add(this.bigIslandStateHandler.getCurrent());
        }
        DynamicIslandContentView current2 = this.smallIslandStateHandler.getCurrent();
        if (current2 != null && (currentIslandData = current2.getCurrentIslandData()) != null && (extras = currentIslandData.getExtras()) != null) {
            string = extras.getString("miui.pkg.name");
        }
        if (string != null) {
            arrayList.add(this.smallIslandStateHandler.getCurrent());
        }
        return arrayList;
    }

    public final I getBigIslandRegion() {
        return this.bigIslandRegion;
    }

    public final BigIslandStateHandler getBigIslandStateHandler() {
        return this.bigIslandStateHandler;
    }

    public final Region getDefaultIslandTouchRegion() {
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        int screenWidthOld = dynamicIslandUtils.getScreenWidthOld(this.ctx) / 2;
        return new Region(screenWidthOld - dynamicIslandUtils.dpToPx(100, this.ctx), dynamicIslandUtils.dpToPx(10, this.ctx), screenWidthOld + dynamicIslandUtils.dpToPx(100, this.ctx), dynamicIslandUtils.dpToPx(42, this.ctx));
    }

    public final DynamicIslandContent.DynamicIslandViewChangedListener getDynamicIslandViewChangedListener() {
        return this.dynamicIslandViewChangedListener;
    }

    public final DynamicIslandWindowState getDynamicIslandWindowState() {
        return this.dynamicIslandWindowState;
    }

    public final boolean getEnterMiniWindow() {
        return this.enterMiniWindow;
    }

    public final Region getExpandedIslandRegion() {
        Region region = new Region();
        DynamicIslandContentView current = this.expandedStateHandler.getCurrent();
        if (current != null) {
            region.set(current.getExpandedViewMarginHorizontal(), current.getExpandedViewY(), current.getExpandedViewMarginHorizontal() + current.getExpandedViewWidth(), current.getExpandedViewY() + current.getExpandedViewHeight());
        }
        return region;
    }

    public final I getExpandedState() {
        return this.expandedState;
    }

    public final ExpandedStateHandler getExpandedStateHandler() {
        return this.expandedStateHandler;
    }

    public final I getExpandedViewRegion() {
        return this.expandedViewRegion;
    }

    public final Region getFullScreenRegion() {
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        int screenWidthOld = dynamicIslandUtils.getScreenWidthOld(this.ctx);
        int screenHeightOld = dynamicIslandUtils.getScreenHeightOld(this.ctx);
        int navBarFrameHeight = getNavBarFrameHeight();
        if (navBarFrameHeight == 0) {
            navBarFrameHeight = dynamicIslandUtils.dpToPx(48, this.ctx);
        }
        Log.d(TAG, "getFullScreenRegion: " + screenWidthOld + ", " + screenHeightOld + ", " + navBarFrameHeight);
        return new Region(82, 0, screenWidthOld - 82, screenHeightOld - navBarFrameHeight);
    }

    public final DynamicIslandEvent getHandlingEvent() {
        return this.handlingEvent;
    }

    public final boolean getHasAppExpandedState() {
        return this.hasAppExpandedState;
    }

    public final I getHeadsUpZone() {
        return this.headsUpZone;
    }

    public final HiddenStateHandler getHiddenStateHandler() {
        return this.hiddenStateHandler;
    }

    public final boolean getHorizontalSwipeDispatched() {
        return this.horizontalSwipeDispatched;
    }

    public final I getIslandRegion() {
        return this.islandRegion;
    }

    public final boolean getKeyguardShowing() {
        return this.keyguardShowing;
    }

    public final String getLastFullScreenActivityPkg() {
        return this.lastFullScreenActivityPkg;
    }

    public final MiniWindowStateHandler getMiniWindowStateHandler() {
        return this.miniWindowStateHandler;
    }

    public final HashMap<String, Boolean> getPkgSupportFreeform() {
        return this.pkgSupportFreeform;
    }

    public final SmallIslandStateHandler getSmallIslandStateHandler() {
        return this.smallIslandStateHandler;
    }

    public final I getState() {
        return this.state;
    }

    public final I getStatusBarVisible() {
        return this.statusBarVisible;
    }

    public final boolean getSwiping() {
        return this.swiping;
    }

    public final DynamicIslandContentView getTempShow() {
        return this.bigIslandStateHandler.getCurrentTempShow();
    }

    public final I getTouchRegion() {
        return this.touchRegion;
    }

    public final DynamicIslandContentView getUpdateState(String str, String str2) {
        DynamicIslandData currentIslandData;
        DynamicIslandData currentIslandData2;
        DynamicIslandData currentIslandData3;
        DynamicIslandData currentIslandData4;
        DynamicIslandData currentIslandData5;
        DynamicIslandData currentIslandData6;
        DynamicIslandContentView dynamicIslandContentView;
        DynamicIslandData currentIslandData7;
        Iterator<T> it = this.hiddenList.iterator();
        do {
            if (!it.hasNext()) {
                DynamicIslandContentView current = this.expandedStateHandler.getCurrent();
                if (n.c((current == null || (currentIslandData6 = current.getCurrentIslandData()) == null) ? null : currentIslandData6.getKey(), str)) {
                    return this.expandedStateHandler.getCurrent();
                }
                DynamicIslandContentView current2 = this.bigIslandStateHandler.getCurrent();
                if (n.c((current2 == null || (currentIslandData5 = current2.getCurrentIslandData()) == null) ? null : currentIslandData5.getKey(), str)) {
                    return this.bigIslandStateHandler.getCurrent();
                }
                DynamicIslandContentView currentTempShow = this.bigIslandStateHandler.getCurrentTempShow();
                if (n.c((currentTempShow == null || (currentIslandData4 = currentTempShow.getCurrentIslandData()) == null) ? null : currentIslandData4.getKey(), str)) {
                    return this.bigIslandStateHandler.getCurrentTempShow();
                }
                DynamicIslandContentView current3 = this.smallIslandStateHandler.getCurrent();
                if (n.c((current3 == null || (currentIslandData3 = current3.getCurrentIslandData()) == null) ? null : currentIslandData3.getKey(), str)) {
                    return this.smallIslandStateHandler.getCurrent();
                }
                ArrayList<DynamicIslandContentView> currentList = this.appStateHandler.getCurrentList();
                if (currentList != null) {
                    for (DynamicIslandContentView dynamicIslandContentView2 : currentList) {
                        if (n.c((dynamicIslandContentView2 == null || (currentIslandData2 = dynamicIslandContentView2.getCurrentIslandData()) == null) ? null : currentIslandData2.getKey(), str)) {
                            return dynamicIslandContentView2;
                        }
                    }
                }
                ArrayList<DynamicIslandContentView> arrayList = this.miniWindowStateHandler.getCurrentMap().get(str2);
                if (arrayList != null) {
                    for (DynamicIslandContentView dynamicIslandContentView3 : arrayList) {
                        if (n.c((dynamicIslandContentView3 == null || (currentIslandData = dynamicIslandContentView3.getCurrentIslandData()) == null) ? null : currentIslandData.getKey(), str)) {
                            return dynamicIslandContentView3;
                        }
                    }
                }
                return null;
            }
            dynamicIslandContentView = (DynamicIslandContentView) it.next();
            currentIslandData7 = dynamicIslandContentView.getCurrentIslandData();
        } while (!n.c(currentIslandData7 != null ? currentIslandData7.getKey() : null, str));
        return dynamicIslandContentView;
    }

    public final boolean getUserExpanded() {
        return this._userExpanded;
    }

    public final boolean getVerticalSwipeDispatched() {
        return this.verticalSwipeDispatched;
    }

    public final boolean getWindowAnimRunning() {
        return this.windowAnimRunning;
    }

    public final DynamicIslandWindowState getWindowState() {
        return this.dynamicIslandWindowState;
    }

    public final DynamicIslandWindowView getWindowView() {
        return this.windowView;
    }

    public final void handleExpandGestureListener(Region region) {
        Rect bounds;
        Bundle bundle = new Bundle();
        boolean z2 = false;
        if (region != null && (bounds = region.getBounds()) != null && !bounds.isEmpty()) {
            z2 = true;
        }
        bundle.putBoolean(DynamicIslandConstants.EXTRA_REGISTER_MIUI_GESTURE_LISTENER, z2);
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onDynamicPluginCallback("onDynamicPluginCallback_handleGesture", bundle);
        }
    }

    public final void handleScreenBurnInStrategy(int i2) {
        DynamicIslandContentView current = this.bigIslandStateHandler.getCurrent();
        if (current != null) {
            this.avoidScreenBurnInHelper.updateViewForAvoidingScreenBurnIn(current, i2);
        }
        DynamicIslandContentView current2 = this.smallIslandStateHandler.getCurrent();
        if (current2 != null) {
            this.avoidScreenBurnInHelper.updateViewForAvoidingScreenBurnIn(current2, i2);
        }
    }

    public final List<DynamicIslandContentView> hasIsland(String pkg) {
        Bundle extras;
        DynamicIslandData currentIslandData;
        Bundle extras2;
        DynamicIslandData currentIslandData2;
        Bundle extras3;
        DynamicIslandData currentIslandData3;
        Bundle extras4;
        DynamicIslandData currentIslandData4;
        Bundle extras5;
        n.g(pkg, "pkg");
        ArrayList arrayList = new ArrayList();
        ArrayList<DynamicIslandContentView> currentList = this.appStateHandler.getCurrentList();
        if (currentList != null) {
            for (DynamicIslandContentView dynamicIslandContentView : currentList) {
                if (n.c((dynamicIslandContentView == null || (currentIslandData4 = dynamicIslandContentView.getCurrentIslandData()) == null || (extras5 = currentIslandData4.getExtras()) == null) ? null : extras5.getString("miui.pkg.name"), pkg)) {
                    arrayList.add(dynamicIslandContentView);
                }
            }
        }
        ArrayList<DynamicIslandContentView> arrayList2 = this.miniWindowStateHandler.getCurrentMap().get(pkg);
        if (arrayList2 != null) {
            arrayList.addAll(arrayList2);
        }
        DynamicIslandContentView current = this.expandedStateHandler.getCurrent();
        if (n.c((current == null || (currentIslandData3 = current.getCurrentIslandData()) == null || (extras4 = currentIslandData3.getExtras()) == null) ? null : extras4.getString("miui.pkg.name"), pkg)) {
            arrayList.add(this.expandedStateHandler.getCurrent());
        }
        DynamicIslandContentView current2 = this.bigIslandStateHandler.getCurrent();
        if (n.c((current2 == null || (currentIslandData2 = current2.getCurrentIslandData()) == null || (extras3 = currentIslandData2.getExtras()) == null) ? null : extras3.getString("miui.pkg.name"), pkg)) {
            arrayList.add(this.bigIslandStateHandler.getCurrent());
        }
        DynamicIslandContentView current3 = this.smallIslandStateHandler.getCurrent();
        if (n.c((current3 == null || (currentIslandData = current3.getCurrentIslandData()) == null || (extras2 = currentIslandData.getExtras()) == null) ? null : extras2.getString("miui.pkg.name"), pkg)) {
            arrayList.add(this.smallIslandStateHandler.getCurrent());
        }
        for (DynamicIslandContentView dynamicIslandContentView2 : this.hiddenList) {
            DynamicIslandData currentIslandData5 = dynamicIslandContentView2.getCurrentIslandData();
            if (n.c((currentIslandData5 == null || (extras = currentIslandData5.getExtras()) == null) ? null : extras.getString("miui.pkg.name"), pkg)) {
                arrayList.add(dynamicIslandContentView2);
            }
        }
        return arrayList;
    }

    public final boolean hasSamePackageIsland(String pkg) {
        n.g(pkg, "pkg");
        return hasIsland(pkg).size() > 1;
    }

    public final boolean hasSmallIsland() {
        DynamicIslandData currentIslandData;
        if (this.smallIslandStateHandler.getCurrent() != null) {
            DynamicIslandWindowState dynamicIslandWindowState = this.dynamicIslandWindowState;
            DynamicIslandContentView current = this.smallIslandStateHandler.getCurrent();
            if (!dynamicIslandWindowState.isTempHidden((current == null || (currentIslandData = current.getCurrentIslandData()) == null) ? null : currentIslandData.getProperties())) {
                return true;
            }
        }
        return false;
    }

    public final boolean hasTempShow() {
        return getTempShow() != null;
    }

    public final boolean isDynamicIslandMiniWindowBlackList(String pkg) {
        n.g(pkg, "pkg");
        return getNotifySettingsManager().isDynamicIslandMiniWindowBlackList(pkg);
    }

    public final boolean isExpandedChanged() {
        return this.isExpandedChanged;
    }

    public final boolean isInLockMode(String packageName, int i2) {
        n.g(packageName, "packageName");
        return this.appLockController.isInLockState(packageName, i2);
    }

    public final boolean isIslandWindowAnimating(DynamicIslandContentView dynamicIslandContentView) {
        return getAnimationController().isWindowAnimating(dynamicIslandContentView);
    }

    public final boolean isTempHidden(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandData currentIslandData;
        return this.dynamicIslandWindowState.isTempHidden((dynamicIslandContentView == null || (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData.getProperties());
    }

    public final boolean isTinyScreen() {
        return this.dynamicIslandWindowState.isTinyScreen();
    }

    public final boolean islandAppAnimating(DynamicIslandContentView dynamicIslandContentView) {
        return getAnimationController().islandAppAnimating(dynamicIslandContentView);
    }

    public final boolean islandFreeformAnimating(DynamicIslandContentView dynamicIslandContentView) {
        return getAnimationController().islandFreeformAnimating(dynamicIslandContentView);
    }

    public final boolean lastHiddenListItemIsNull() {
        DynamicIslandContentView dynamicIslandContentView = (DynamicIslandContentView) I0.u.W(this.hiddenList);
        if (dynamicIslandContentView != null) {
            return dynamicIslandContentView.getState() instanceof DynamicIslandState.Empty;
        }
        return false;
    }

    public final boolean needExtendLifetime(String key) {
        n.g(key, "key");
        return n.c(this.needExtendLifetime.get(key), Boolean.TRUE);
    }

    @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimationController.DynamicIslandAnimationCallback
    public void onAnimationFinished() {
        this.isAnimationRunning = false;
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "onAnimationFinished");
        updateTouchRegion();
        updateWindowHeight();
        resetStateForExitApp();
        this._expandedState.setValue(Boolean.valueOf(this.expandedStateHandler.getCurrent() != null));
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x021f  */
    @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimationController.DynamicIslandAnimationCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onAnimationStart(boolean r8, miui.systemui.dynamicisland.window.content.DynamicIslandContentView r9) {
        /*
            Method dump skipped, instruction units count: 787
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator.onAnimationStart(boolean, miui.systemui.dynamicisland.window.content.DynamicIslandContentView):void");
    }

    public final void onDynamicPluginCallback(String action, Bundle bundle) {
        n.g(action, "action");
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onDynamicPluginCallback(action, bundle);
        }
    }

    public final void onHeightChangedFirst() {
        this._state.setValue(Integer.valueOf(DynamicIslandUtils.INSTANCE.getScreenHeightOld(this.ctx)));
    }

    public final void onKeyguardShowing(boolean z2) {
        this.keyguardShowing = z2;
    }

    public final void onNormalIslandViewCallback(DynamicIslandContentFakeView dynamicIslandContentFakeView, Bundle bundle) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onNormalIslandViewCallback(dynamicIslandContentFakeView, bundle);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimationController.DynamicIslandAnimationCallback
    public void onStateChange(String type, DynamicIslandContentView view) {
        n.g(type, "type");
        n.g(view, "view");
        switch (type.hashCode()) {
            case -1868319000:
                if (!type.equals(DynamicIslandConstants.HIDDEN_TO_EXPANDED)) {
                    return;
                }
                view.showIslandLayout();
                return;
            case -1748135057:
                if (!type.equals(DynamicIslandConstants.BIG_TO_HIDDEN)) {
                    return;
                }
                break;
            case -1502217327:
                if (!type.equals(DynamicIslandConstants.SUB_APP_TO_HIDDEN)) {
                    return;
                }
                break;
            case -1218686664:
                if (!type.equals(DynamicIslandConstants.HIDDEN_TO_SMALL)) {
                    return;
                }
                view.showIslandLayout();
                return;
            case -1110560792:
                if (!type.equals(DynamicIslandConstants.EXPANDED_TO_HIDDEN)) {
                    return;
                }
                break;
            case -821344717:
                if (!type.equals(DynamicIslandConstants.SHOW_TO_TEMP_HIDDEN)) {
                    return;
                }
                break;
            case -503419768:
                if (!type.equals(DynamicIslandConstants.SUB_MINI_WINDOW_TO_HIDDEN)) {
                    return;
                }
                break;
            case 16293191:
                if (!type.equals(DynamicIslandConstants.MINI_WINDOW_TO_HIDDEN)) {
                    return;
                }
                break;
            case 456153936:
                if (!type.equals(DynamicIslandConstants.APP_TO_HIDDEN)) {
                    return;
                }
                break;
            case 836144822:
                if (!type.equals(DynamicIslandConstants.SMALL_TO_HIDDEN)) {
                    return;
                }
                break;
            case 847875730:
                if (!type.equals(DynamicIslandConstants.HIDDEN_TO_APP)) {
                    return;
                }
                break;
            case 847876465:
                if (!type.equals(DynamicIslandConstants.HIDDEN_TO_BIG)) {
                    return;
                }
                view.showIslandLayout();
                return;
            case 1040440439:
                if (!type.equals(DynamicIslandConstants.TEMP_HIDDEN_TO_SHOW)) {
                    return;
                }
                view.showIslandLayout();
                return;
            case 1184965706:
                if (!type.equals(DynamicIslandConstants.HIDDEN_TO_SUB_MINI_WINDOW)) {
                    return;
                }
                break;
            case 1344768073:
                if (!type.equals(DynamicIslandConstants.HIDDEN_TO_MINI_WINDOW)) {
                    return;
                }
                break;
            case 1597750163:
                if (!type.equals(DynamicIslandConstants.HIDDEN_TO_SUB_APP)) {
                    return;
                }
                break;
            default:
                return;
        }
        view.hideIslandLayout();
    }

    public final void onWindowAnimExtendLifetimeEnd(Bundle bundle) {
        if (bundle != null) {
            StatusBarNotification statusBarNotification = (StatusBarNotification) bundle.getParcelable("miui.sbn", StatusBarNotification.class);
            if (this.needExtendLifetime.get(statusBarNotification != null ? statusBarNotification.getKey() : null) != null) {
                D.d(this.needExtendLifetime).remove(statusBarNotification != null ? statusBarNotification.getKey() : null);
                Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "onWindowAnimExtendLifetimeEnd " + (statusBarNotification != null ? statusBarNotification.getKey() : null));
                DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
                if (dynamicIslandViewChangedListener != null) {
                    dynamicIslandViewChangedListener.onDynamicPluginCallback("onDynamicPluginCallback_onWindowAnimEnd", bundle);
                }
            }
        }
    }

    public final void onWindowAnimExtendLifetimeStart(Bundle bundle) {
        String key;
        if (bundle != null) {
            StatusBarNotification statusBarNotification = (StatusBarNotification) bundle.getParcelable("miui.sbn", StatusBarNotification.class);
            if (statusBarNotification != null && (key = statusBarNotification.getKey()) != null) {
                n.d(key);
                this.needExtendLifetime.put(key, Boolean.TRUE);
                Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "onWindowAnimExtendLifetimeStart " + key);
            }
            bundle.putBoolean(DynamicIslandConstants.EXTRA_HAS_FORCE_END_LIFE_EXTEND, true);
            DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
            if (dynamicIslandViewChangedListener != null) {
                dynamicIslandViewChangedListener.onDynamicPluginCallback("onDynamicPluginCallback_onWindowAnimStart", bundle);
            }
        }
    }

    public final void openApp(Bundle bundle) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onDynamicPluginCallback("onDynamicPluginCallback_openApp", bundle);
        }
    }

    public final void openFreeForm(Bundle bundle) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onDynamicPluginCallback("onDynamicPluginCallback_openFreeform", bundle);
        }
    }

    public final void positionChanged(Bundle bundle) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onDynamicPluginCallback("onDynamicPluginCallback_positionChanged", bundle);
        }
    }

    public final void removeExtendLifetime(String key) {
        n.g(key, "key");
        this.needExtendLifetime.remove(key);
    }

    public final void reparentFreeformFakeView(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandContentFakeView fakeView;
        ViewParent parent;
        if (dynamicIslandContentView == null || (fakeView = dynamicIslandContentView.getFakeView()) == null) {
            return;
        }
        ViewParent parent2 = dynamicIslandContentView.getParent();
        ViewParent parent3 = parent2 != null ? parent2.getParent() : null;
        n.e(parent3, "null cannot be cast to non-null type android.view.ViewGroup");
        ViewGroup viewGroup = (ViewGroup) parent3;
        if (n.c(fakeView.getParent(), viewGroup)) {
            Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "reparentFreeformFakeView: fakeView " + dynamicIslandContentView.getFakeView() + ", parent: " + fakeView.getParent() + ", return");
            return;
        }
        DynamicIslandContentFakeView fakeView2 = dynamicIslandContentView.getFakeView();
        DynamicIslandContentFakeView fakeView3 = dynamicIslandContentView.getFakeView();
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "reparentFreeformFakeView: fakeView " + fakeView2 + ", parent: " + (fakeView3 != null ? fakeView3.getParent() : null));
        DynamicIslandContentFakeView fakeView4 = dynamicIslandContentView.getFakeView();
        if (fakeView4 != null && (parent = fakeView4.getParent()) != null) {
            n.d(parent);
            ((ViewGroup) parent).removeView(dynamicIslandContentView.getFakeView());
        }
        viewGroup.addView(dynamicIslandContentView.getFakeView(), new FrameLayout.LayoutParams(-1, -1));
    }

    public final void reparentOpenAppFakeView(DynamicIslandContentFakeView dynamicIslandContentFakeView, Bundle bundle) {
        ViewParent parent;
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "reparentOpenAppFakeView: fakeView " + dynamicIslandContentFakeView + ", parent: " + (dynamicIslandContentFakeView != null ? dynamicIslandContentFakeView.getParent() : null));
        if (dynamicIslandContentFakeView != null && (parent = dynamicIslandContentFakeView.getParent()) != null) {
            ((ViewGroup) parent).removeView(dynamicIslandContentFakeView);
        }
        updateOpenAppFakeViewCallback(dynamicIslandContentFakeView, bundle);
    }

    public final void resetContainerAlpha(DynamicIslandContentView dynamicIslandContentView) {
        getAnimationController().resetContainerAlpha(dynamicIslandContentView);
    }

    public final void resetFakeViewAnimState(DynamicIslandContentView dynamicIslandContentView, boolean z2) {
        getAnimationController().resetFakeViewAnimState(dynamicIslandContentView, z2);
    }

    public final void resetPress(boolean z2, boolean z3, boolean z4) {
        if (this.hasResetPress) {
            return;
        }
        this.hasResetPress = true;
        if (z2) {
            getAnimationController().resetPress(this.bigIslandStateHandler.getCurrent());
        } else if (z3) {
            getAnimationController().resetPress(this.smallIslandStateHandler.getCurrent());
        } else if (z4) {
            getAnimationController().resetPress(this.expandedStateHandler.getCurrent());
        }
    }

    public final void resetSwipe(float f2, float f3) {
        getSwipeEventCoordinator().resetSwipe(f2, f3, this.hiddenList);
    }

    public final void setDynamicIslandViewChangedListener(DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener) {
        this.dynamicIslandViewChangedListener = dynamicIslandViewChangedListener;
    }

    public final void setEnterMiniWindow(boolean z2) {
        this.enterMiniWindow = z2;
    }

    public final void setExpandedChanged(boolean z2) {
        this.isExpandedChanged = z2;
    }

    public final void setHandlingEvent(DynamicIslandEvent dynamicIslandEvent) {
        this.handlingEvent = dynamicIslandEvent;
    }

    public final void setHasAppExpandedState(boolean z2) {
        this.hasAppExpandedState = z2;
    }

    public final void setHorizontalSwipeDispatched(boolean z2) {
        this.horizontalSwipeDispatched = z2;
    }

    public final void setKeyguardShowing(boolean z2) {
        this.keyguardShowing = z2;
    }

    public final void setLastFullScreenActivityPkg(String str) {
        n.g(str, "<set-?>");
        this.lastFullScreenActivityPkg = str;
    }

    public final void setPkgSupportFreeform(HashMap<String, Boolean> map) {
        n.g(map, "<set-?>");
        this.pkgSupportFreeform = map;
    }

    public final void setSwiping(boolean z2) {
        this.swiping = z2;
    }

    public final void setUserExpanded(boolean z2) {
        this._userExpanded = z2;
        DynamicIslandWindowView dynamicIslandWindowView = this.windowView;
        dynamicIslandWindowView.updateHeadsUpZone(dynamicIslandWindowView.getHeadsUpHeight());
    }

    public final void setVerticalSwipeDispatched(boolean z2) {
        this.verticalSwipeDispatched = z2;
    }

    public final void setWindowAnimRunning(boolean z2) {
        this.windowAnimRunning = z2;
    }

    public final void smallToBig(Bundle bundle) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onDynamicPluginCallback("onDynamicPluginCallback_smallToBig", bundle);
        }
    }

    public final void smallToExpanded(Bundle bundle) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onDynamicPluginCallback("onDynamicPluginCallback_smallToExpanded", bundle);
        }
    }

    public final void stopSwipe() {
        this.swiping = false;
        this.horizontalSwipeDispatched = false;
        this.verticalSwipeDispatched = false;
    }

    public final void updateAppExpandedStateWhenAnimStart(DynamicIslandContentView dynamicIslandContentView) {
        getAnimationController().updateAppExpandedStateWhenAnimStart(dynamicIslandContentView);
    }

    public final void updateFreeformFakeView(DynamicIslandContentFakeView dynamicIslandContentFakeView, DynamicIslandContentView dynamicIslandContentView, Bundle bundle) {
        reparentFreeformFakeView(dynamicIslandContentView);
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onFreeformFakeViewCallback(dynamicIslandContentFakeView, (View) null, dynamicIslandContentFakeView != null ? dynamicIslandContentFakeView.getFakeBigIsland() : null, dynamicIslandContentFakeView != null ? dynamicIslandContentFakeView.getFakeSmallIsland() : null, dynamicIslandContentFakeView != null ? dynamicIslandContentFakeView.getFakeExpandedView() : null, bundle);
        }
    }

    public final void updateHeadsUpZone(i zone) {
        n.g(zone, "zone");
        this._headsUpZone.setValue(zone);
    }

    public final void updateIslandWindowAnimRunning(boolean z2, DynamicIslandContentView dynamicIslandContentView, boolean z3) {
        getAnimationController().updateIslandWindowAnimRunning(z2, dynamicIslandContentView, z3);
    }

    public final void updateMiniBar(DynamicIslandContentView dynamicIslandContentView) {
        getAnimationController().updateExpandedViewMiniBar(dynamicIslandContentView);
    }

    public final void updateOpenAppFakeViewCallback(DynamicIslandContentFakeView dynamicIslandContentFakeView, Bundle bundle) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
        if (dynamicIslandViewChangedListener != null) {
            dynamicIslandViewChangedListener.onNormalFakeViewCallback(dynamicIslandContentFakeView, dynamicIslandContentFakeView != null ? dynamicIslandContentFakeView.getFakeContainer() : null, bundle);
        }
    }

    public final void updatePkgSupportFreeform(String pkg, Context context) {
        n.g(pkg, "pkg");
        n.g(context, "context");
        boolean zPackageSupportFreeform = DynamicIslandUtils.INSTANCE.packageSupportFreeform(context, pkg);
        Log.e(TAG, "updatePkgSupportFreeform : " + pkg + ", " + zPackageSupportFreeform);
        HashMap<String, Boolean> map = this.pkgSupportFreeform;
        if (map != null) {
            map.put(pkg, Boolean.valueOf(zPackageSupportFreeform));
        }
    }

    public final void updateStatusBarVisible(boolean z2) {
        this._stateBarVisible.setValue(Boolean.valueOf(z2));
    }

    public final void updateTouchRegion() {
        DynamicIslandWindowState windowState = this.windowView.getWindowViewController().getWindowState();
        boolean zBooleanValue = ((Boolean) windowState.getNotificationAppearance().getValue()).booleanValue();
        boolean z2 = ((Boolean) windowState.getStatusBarDisappearance().getValue()).booleanValue() || !((Boolean) windowState.getShowNotificationIcons().getValue()).booleanValue() || ((Boolean) windowState.getBouncerShowing().getValue()).booleanValue();
        boolean zBooleanValue2 = ((Boolean) windowState.getControlCenterExpanded().getValue()).booleanValue();
        boolean zBooleanValue3 = ((Boolean) windowState.getScreenPinning().getValue()).booleanValue();
        boolean zBooleanValue4 = ((Boolean) windowState.getMiPlayShow().getValue()).booleanValue();
        Region expandedIslandRegion = getExpandedIslandRegion();
        Region smallBigIslandRegion = getSmallBigIslandRegion();
        Region regionMergeTouchRegions = mergeTouchRegions(expandedIslandRegion, smallBigIslandRegion);
        if (zBooleanValue4) {
            regionMergeTouchRegions = NON_INTERACTIVE_REGION;
        }
        if (zBooleanValue && !zBooleanValue2) {
            regionMergeTouchRegions = NON_INTERACTIVE_REGION;
        }
        if (z2 && !zBooleanValue3 && !this.superSaveModeController.isActive()) {
            regionMergeTouchRegions = this.expandedStateHandler.getCurrent() != null ? expandedIslandRegion : NON_INTERACTIVE_REGION;
        }
        if (this.windowView.hasNoActiveDynamicIsland()) {
            regionMergeTouchRegions = NON_INTERACTIVE_REGION;
        }
        if (regionMergeTouchRegions.isEmpty() && hasTempShow()) {
            regionMergeTouchRegions = smallBigIslandRegion;
        }
        Region fakeExpandedTrackingRegion = getFakeExpandedTrackingRegion(regionMergeTouchRegions);
        if (regionChanged(fakeExpandedTrackingRegion)) {
            Log.d(DynamicIslandConstants.TAG_DEBUG_TOUCH, "touchRegion updated to: " + fakeExpandedTrackingRegion + "; smallBig:" + smallBigIslandRegion + ", expanded: " + expandedIslandRegion);
            this._touchRegion.setValue(fakeExpandedTrackingRegion);
        }
    }

    public final void updateView(DynamicIslandContentView view) {
        n.g(view, "view");
        if (DynamicIslandAnimUtils.INSTANCE.supportShowElementAndFreeformAnim()) {
            DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener = this.dynamicIslandViewChangedListener;
            if (dynamicIslandViewChangedListener != null) {
                dynamicIslandViewChangedListener.onUpdateAnimView(view.getBigIslandView(), view.getSmallIslandView(), view.getExpandedView());
            }
            try {
                DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener2 = this.dynamicIslandViewChangedListener;
                if (dynamicIslandViewChangedListener2 != null) {
                    DynamicIslandData currentIslandData = view.getCurrentIslandData();
                    dynamicIslandViewChangedListener2.onNormalIslandViewCallback(view, currentIslandData != null ? currentIslandData.getExtras() : null);
                }
            } catch (Throwable th) {
                Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "onNormalIslandViewCallback: " + th);
            }
            DynamicIslandContentFakeView fakeView = view.getFakeView();
            DynamicIslandData currentIslandData2 = view.getCurrentIslandData();
            reparentOpenAppFakeView(fakeView, currentIslandData2 != null ? currentIslandData2.getExtras() : null);
        }
    }

    public final void updateWindowHeightForFreeform(int i2) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "freeform bottom: " + i2);
        this.freeformBottom = i2;
        this._state.setValue(Integer.valueOf(i2));
    }

    public final void updateWindowHeightInAnimState(boolean z2, boolean z3) {
        this.windowAnimRunning = z2;
        if (z2 && DynamicIslandAnimUtils.INSTANCE.supportShowElementAndFreeformAnim()) {
            this._state.setValue(Integer.valueOf(DynamicIslandUtils.INSTANCE.getScreenHeightOld(this.ctx)));
        } else {
            updateWindowHeight();
        }
    }

    private final Region getBigIslandRegion(DynamicIslandContentView dynamicIslandContentView) {
        Region region = new Region();
        Integer numValueOf = dynamicIslandContentView != null ? Integer.valueOf(DynamicIslandBaseContentView.getCurrentBigIslandX$default(dynamicIslandContentView, null, 1, null)) : null;
        n.d(numValueOf);
        region.set(numValueOf.intValue(), dynamicIslandContentView.getIslandViewMarginTop(), DynamicIslandBaseContentView.getCurrentBigIslandX$default(dynamicIslandContentView, null, 1, null) + DynamicIslandBaseContentView.getCurrentBigIslandWidth$default(dynamicIslandContentView, null, 1, null), dynamicIslandContentView.getIslandViewMarginTop() + dynamicIslandContentView.getIslandViewHeight());
        return region;
    }
}
