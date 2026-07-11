package miui.systemui.dynamicisland.window;

import H0.s;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.graphics.Region;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import androidx.core.view.ViewGroupKt;
import b.BinderC0222a;
import com.android.systemui.SystemVolumeController;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandContent;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandGlowEffectLayer;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import j1.AbstractC0420h;
import j1.E;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import j1.K;
import j1.u;
import j1.y;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.D;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.autodensity.AutoDensityController;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.drawable.AppIconsManager;
import miui.systemui.dynamicisland.DynamicIslandBackgroundView;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository;
import miui.systemui.dynamicisland.data.repository.DynamicIslandSizeRepository;
import miui.systemui.dynamicisland.display.AvoidScreenBurnInHelper;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.WindowAnimState;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.touch.TouchEvent;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;
import miui.systemui.notification.LottieProgressManager;
import miui.systemui.notification.NotificationChronometerManager;
import miui.systemui.notification.TaskStackChangeListener;
import miui.systemui.notification.TaskStackChangeListeners;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ConvenienceExtensionsKt;
import miui.systemui.util.DeviceStateManagerCompat;
import miui.systemui.util.FlipUtils;
import miui.systemui.util.FoldUtils;
import miui.systemui.util.OneHandModeUtils;
import miui.systemui.util.ViewController;
import miui.systemui.util.concurrency.DelayableExecutor;
import miui.systemui.util.coroutines.flow.FlowConflatedKt;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
@SuppressLint({"ClickableViewAccessibility"})
public final class DynamicIslandWindowViewController extends ViewController<DynamicIslandWindowView> implements DynamicIslandContent, DynamicIslandStartable, AutoDensityController.OnDensityChangeListener {
    private static final String AOD_FULL_SCREEN = "full_screen_aod_on";
    private static final String AOD_MODE = "doze_always_on";
    private static final float COMPATIBILITY_VALUE = -1.0f;
    public static final Companion Companion = new Companion(null);
    private static final long DELAY_TIME = 100;
    private static final long DELAY_TIME_WHEN_WINDOW_ANIMATION = 1000;
    private static final double ONE_HEAD_MODE_PRE = 0.4d;
    public static final String OPEN_APP_ERROR = "open_app_error";
    private static final String TAG = "DynamicIslandWindowViewController";
    private final u _freeformAnimRegion;
    private final u _isAppAnimRunning;
    private final u _isFreeformAnimRunning;
    private int appAnimSmallX;
    private String appCloseKey;
    private HashMap<String, Rect> appCloseRect;
    private AutoDensityController autoDensityController;
    private E0.a avoidScreenBurnInHelper;
    private final Handler bgHandler;
    private final y children;
    private String closeAppKey;
    private final BroadcastReceiver collapsedReceiver;
    private Configuration configuration;
    private final ContentResolver contentResolver;
    private final y contentViews;
    private int currentHeight;
    private final Object deviceStateManager;
    private int displayOrientation;
    private DynamicIslandCallback dynamicIslandCallback;
    private DynamicIslandDesktopStateController dynamicIslandDesktopStateController;
    private DynamicIslandGlowEffectLayer dynamicIslandGlowEffectLayer;
    private DynamicIslandSafeguardsController dynamicIslandSafeguardsController;
    private final H0.d externalStateRepository$delegate;
    private final H0.d externalTouchHandler$delegate;
    private final ArrayList<DeviceStateManagerCompat.FoldStateCallback> foldStateCallbacks;
    private final Object foldStateListener;
    private final I freeformAnimRegion;
    private String fullScreenPkg;
    private BinderC0222a hyperDropInfoNotifierService;
    private HashMap<String, Boolean> inSmallWindowMap;
    private final E ioScope;
    private boolean isAodOn;
    private final I isAppAnimRunning;
    private final I isFreeformAnimRunning;
    private boolean isFullAodOn;
    private boolean isPendingTopChanged;
    private boolean isRegisteredCollapsedReceiver;
    private boolean isSupportFreeformAnim;
    private HashMap<String, DynamicIslandData> islandData;
    private int lastDisplayOrientation;
    private String lastEventKey;
    private String lastTopActivityPkg;
    private DynamicIslandContent.DynamicIslandViewChangedListener listener;
    private E0.a lottieProgressManager;
    private final BroadcastReceiver miPlayShowStateChangedBroadcastReceiver;
    private boolean miPlayShowStateChangedBroadcastRegistered;
    private final H0.d notificationChronometerManager$delegate;
    private String openAppKey;
    private final BroadcastReceiver packagesBroadcastReceiver;
    private boolean packagesBroadcastRegistered;
    private HashMap<DynamicIslandData, Boolean> pendingData;
    private ArrayList<String> pinOperateList;
    private boolean requestPosition;
    private boolean requestPositionByClosePosition;
    private final DynamicIslandWindowViewController$settingsObserver$1 settingsObserver;
    private boolean shouldDelayExitApp;
    private final DynamicIslandSizeRepository sizeRepository;
    private final TaskStackChangeListener taskStackListener;
    private String topActivityPkg;
    private DelayableExecutor uiExecutor;
    private final E uiScope;
    private final E uiScopeImmediate;
    private DynamicIslandWindowState windowState;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface DynamicIslandCallback {
        void onDynamicIslandConfigChange();

        void onDynamicIslandTimeoutRemoved(String str);

        void removeNotification(StatusBarNotification statusBarNotification);
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$onTopActivityChange$2, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$onTopActivityChange$2", f = "DynamicIslandWindowViewController.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends N0.l implements Function2 {
        int label;

        public AnonymousClass2(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewController.this.new AnonymousClass2(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass2) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            DynamicIslandWindowViewController dynamicIslandWindowViewController = DynamicIslandWindowViewController.this;
            boolean zIsInMiniWindow = dynamicIslandWindowViewController.isInMiniWindow(dynamicIslandWindowViewController.getLastTopActivityPkg(), N0.b.c(DynamicIslandWindowViewController.access$getView(DynamicIslandWindowViewController.this).getCurrentUserId()));
            Log.d(DynamicIslandWindowViewController.TAG, "isInMiniWindow " + zIsInMiniWindow);
            DynamicIslandWindowViewController dynamicIslandWindowViewController2 = DynamicIslandWindowViewController.this;
            if (dynamicIslandWindowViewController2.needExitMiniWindow(zIsInMiniWindow, dynamicIslandWindowViewController2.getLastTopActivityPkg())) {
                DynamicIslandWindowViewController dynamicIslandWindowViewController3 = DynamicIslandWindowViewController.this;
                dynamicIslandWindowViewController3.calculateAppClosePosition(dynamicIslandWindowViewController3.getLastTopActivityPkg(), true);
                String lastTopActivityPkg = DynamicIslandWindowViewController.this.getLastTopActivityPkg();
                if (lastTopActivityPkg != null) {
                    DynamicIslandSafeguardsController.delayExitMiniWindow$default(DynamicIslandWindowViewController.this.getDynamicIslandSafeguardsController(), lastTopActivityPkg, false, 2, null);
                }
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$openAppError$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$openAppError$1", f = "DynamicIslandWindowViewController.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        final /* synthetic */ String $pkg;
        int label;
        final /* synthetic */ DynamicIslandWindowViewController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, DynamicIslandWindowViewController dynamicIslandWindowViewController, L0.d dVar) {
            super(2, dVar);
            this.$pkg = str;
            this.this$0 = dynamicIslandWindowViewController;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new AnonymousClass1(this.$pkg, this.this$0, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            List<DynamicIslandContentView> listRequestHasIsland;
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            String str = this.$pkg;
            if (str != null && (listRequestHasIsland = DynamicIslandWindowViewController.access$getView(this.this$0).requestHasIsland(str)) != null) {
                DynamicIslandWindowViewController dynamicIslandWindowViewController = this.this$0;
                Iterator<T> it = listRequestHasIsland.iterator();
                while (it.hasNext()) {
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).updateViewStateWhenCloseEnd((DynamicIslandContentView) it.next(), true, DynamicIslandWindowViewController.OPEN_APP_ERROR);
                }
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$registerTempHiddenChanged$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$registerTempHiddenChanged$1", f = "DynamicIslandWindowViewController.kt", l = {368}, m = "invokeSuspend")
    public static final class C06401 extends N0.l implements Function2 {
        int label;

        public C06401(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewController.this.new C06401(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06401) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                I tempHidden = DynamicIslandWindowViewController.this.getWindowState().getTempHidden();
                final DynamicIslandWindowViewController dynamicIslandWindowViewController = DynamicIslandWindowViewController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.registerTempHiddenChanged.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, L0.d dVar) {
                        Log.e(DynamicIslandWindowViewController.TAG, "collect tempHidden:" + z2 + " " + dynamicIslandWindowViewController.getWindowState().getTempHiddenType());
                        dynamicIslandWindowViewController.getWindowState().setTempHiddenChange(N0.b.a(true));
                        DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).onIslandTempHide(z2, dynamicIslandWindowViewController.getWindowState().getTempHiddenType());
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (tempHidden.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$registerTempHiddenChanged$2, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$registerTempHiddenChanged$2", f = "DynamicIslandWindowViewController.kt", l = {375}, m = "invokeSuspend")
    public static final class C06412 extends N0.l implements Function2 {
        int label;

        public C06412(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewController.this.new C06412(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06412) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                u statusBarViewShowing = DynamicIslandWindowViewController.this.getWindowState().getStatusBarViewShowing();
                final DynamicIslandWindowViewController dynamicIslandWindowViewController = DynamicIslandWindowViewController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.registerTempHiddenChanged.2.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, L0.d dVar) {
                        dynamicIslandWindowViewController.getWindowState().setTempHiddenChange(N0.b.a(true));
                        DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).onIslandTempHide(((Boolean) dynamicIslandWindowViewController.getWindowState().getTempHidden().getValue()).booleanValue(), dynamicIslandWindowViewController.getWindowState().getTempHiddenType());
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (statusBarViewShowing.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$registerTempHiddenChanged$3, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$registerTempHiddenChanged$3", f = "DynamicIslandWindowViewController.kt", l = {381}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends N0.l implements Function2 {
        int label;

        public AnonymousClass3(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewController.this.new AnonymousClass3(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass3) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                u bouncerShowing = DynamicIslandWindowViewController.this.getWindowState().getBouncerShowing();
                final DynamicIslandWindowViewController dynamicIslandWindowViewController = DynamicIslandWindowViewController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.registerTempHiddenChanged.3.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, L0.d dVar) {
                        Log.e(DynamicIslandWindowViewController.TAG, "collect bouncerShowing:" + z2);
                        dynamicIslandWindowViewController.getWindowState().setTempHiddenChange(N0.b.a(true));
                        DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).onIslandTempHide(((Boolean) dynamicIslandWindowViewController.getWindowState().getTempHidden().getValue()).booleanValue(), dynamicIslandWindowViewController.getWindowState().getTempHiddenType());
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (bouncerShowing.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$registerTempHiddenChanged$4, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$registerTempHiddenChanged$4", f = "DynamicIslandWindowViewController.kt", l = {388}, m = "invokeSuspend")
    public static final class AnonymousClass4 extends N0.l implements Function2 {
        int label;

        public AnonymousClass4(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewController.this.new AnonymousClass4(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass4) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                I iIsDeviceInteractive = DynamicIslandWindowViewController.this.getExternalStateRepository().isDeviceInteractive();
                final DynamicIslandWindowViewController dynamicIslandWindowViewController = DynamicIslandWindowViewController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.registerTempHiddenChanged.4.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, L0.d dVar) {
                        Log.d(DynamicIslandWindowViewController.TAG, "collect isDeviceInteractive:" + z2 + ", windowState.tempHidden.value=" + dynamicIslandWindowViewController.getWindowState().getTempHidden().getValue());
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (iIsDeviceInteractive.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$registerTempHiddenChanged$5, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$registerTempHiddenChanged$5", f = "DynamicIslandWindowViewController.kt", l = {395}, m = "invokeSuspend")
    public static final class AnonymousClass5 extends N0.l implements Function2 {
        int label;

        public AnonymousClass5(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewController.this.new AnonymousClass5(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass5) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                I iIsKeyguardShowing = DynamicIslandWindowViewController.this.getExternalStateRepository().isKeyguardShowing();
                final DynamicIslandWindowViewController dynamicIslandWindowViewController = DynamicIslandWindowViewController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.registerTempHiddenChanged.5.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, L0.d dVar) throws PendingIntent.CanceledException {
                        Log.d(DynamicIslandWindowViewController.TAG, "collect isKeyguardShowing:" + z2 + ", windowState.tempHidden.value=" + dynamicIslandWindowViewController.getWindowState().getTempHidden().getValue());
                        dynamicIslandWindowViewController.getWindowState().setKeyguardShowing(z2);
                        dynamicIslandWindowViewController.lockScreen(z2);
                        DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).onKeyguardShowing(z2);
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (iIsKeyguardShowing.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$16, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$16", f = "DynamicIslandWindowViewController.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass16 extends N0.l implements Function2 {
        final /* synthetic */ String $pkg;
        int label;
        final /* synthetic */ DynamicIslandWindowViewController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass16(String str, DynamicIslandWindowViewController dynamicIslandWindowViewController, L0.d dVar) {
            super(2, dVar);
            this.$pkg = str;
            this.this$0 = dynamicIslandWindowViewController;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new AnonymousClass16(this.$pkg, this.this$0, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass16) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            DynamicIslandData currentIslandData;
            String key;
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            String str = this.$pkg;
            List<DynamicIslandContentView> currentMiniWindowState = str != null ? DynamicIslandWindowViewController.access$getView(this.this$0).getCurrentMiniWindowState(str) : null;
            this.this$0.cancelDelayOperate(this.$pkg);
            if (currentMiniWindowState != null) {
                DynamicIslandWindowViewController dynamicIslandWindowViewController = this.this$0;
                for (DynamicIslandContentView dynamicIslandContentView : currentMiniWindowState) {
                    ArrayList arrayList = dynamicIslandWindowViewController.pinOperateList;
                    DynamicIslandData currentIslandData2 = dynamicIslandContentView.getCurrentIslandData();
                    if (!I0.u.F(arrayList, currentIslandData2 != null ? currentIslandData2.getKey() : null) && (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) != null && (key = currentIslandData.getKey()) != null) {
                        N0.b.a(dynamicIslandWindowViewController.pinOperateList.add(key));
                    }
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).exitMiniWindow(dynamicIslandContentView);
                    DynamicIslandContentFakeView fakeView = dynamicIslandContentView.getFakeView();
                    if (fakeView != null) {
                        fakeView.setOpenAppFromIsland(false);
                    }
                    dynamicIslandContentView.setVisibility(0);
                    DynamicIslandBackgroundView backgroundView = dynamicIslandContentView.getBackgroundView();
                    if (backgroundView != null) {
                        backgroundView.setVisibility(0);
                    }
                }
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$17, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$17", f = "DynamicIslandWindowViewController.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass17 extends N0.l implements Function2 {
        final /* synthetic */ boolean $isFreeform;
        final /* synthetic */ String $pkg;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass17(String str, boolean z2, L0.d dVar) {
            super(2, dVar);
            this.$pkg = str;
            this.$isFreeform = z2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewController.this.new AnonymousClass17(this.$pkg, this.$isFreeform, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass17) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            DynamicIslandData currentIslandData;
            String key;
            DynamicIslandData currentIslandData2;
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            DynamicIslandWindowViewController.this.cancelDelayOperate(this.$pkg);
            String str = this.$pkg;
            List<DynamicIslandContentView> listRequestHasIsland = str != null ? DynamicIslandWindowViewController.access$getView(DynamicIslandWindowViewController.this).requestHasIsland(str) : null;
            if (listRequestHasIsland != null) {
                DynamicIslandWindowViewController dynamicIslandWindowViewController = DynamicIslandWindowViewController.this;
                boolean z2 = this.$isFreeform;
                for (DynamicIslandContentView dynamicIslandContentView : listRequestHasIsland) {
                    Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "freeform_pin_exit state = " + dynamicIslandContentView);
                    if (!I0.u.F(dynamicIslandWindowViewController.pinOperateList, (dynamicIslandContentView == null || (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData2.getKey()) && dynamicIslandContentView != null && (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) != null && (key = currentIslandData.getKey()) != null) {
                        N0.b.a(dynamicIslandWindowViewController.pinOperateList.add(key));
                    }
                    DynamicIslandWindowView.updateViewStateWhenCloseEnd$default(DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController), dynamicIslandContentView, z2, null, 4, null);
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).enterMiniWindow(dynamicIslandContentView);
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).onWindowAnimExtendLifetimeEnd(dynamicIslandContentView);
                }
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$18, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$18", f = "DynamicIslandWindowViewController.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass18 extends N0.l implements Function2 {
        final /* synthetic */ boolean $isFreeform;
        final /* synthetic */ String $pkg;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass18(String str, boolean z2, L0.d dVar) {
            super(2, dVar);
            this.$pkg = str;
            this.$isFreeform = z2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewController.this.new AnonymousClass18(this.$pkg, this.$isFreeform, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass18) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            DynamicIslandWindowViewController.this.cancelDelayOperate(this.$pkg);
            String str = this.$pkg;
            List<DynamicIslandContentView> listRequestHasIsland = str != null ? DynamicIslandWindowViewController.access$getView(DynamicIslandWindowViewController.this).requestHasIsland(str) : null;
            if (listRequestHasIsland != null) {
                DynamicIslandWindowViewController dynamicIslandWindowViewController = DynamicIslandWindowViewController.this;
                boolean z2 = this.$isFreeform;
                for (DynamicIslandContentView dynamicIslandContentView : listRequestHasIsland) {
                    Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "fullscreen_to_mini_freeform state = " + dynamicIslandContentView);
                    DynamicIslandWindowView.updateViewStateWhenCloseEnd$default(DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController), dynamicIslandContentView, z2, null, 4, null);
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).enterMiniWindow(dynamicIslandContentView);
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).onWindowAnimExtendLifetimeEnd(dynamicIslandContentView);
                }
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$19, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$19", f = "DynamicIslandWindowViewController.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass19 extends N0.l implements Function2 {
        final /* synthetic */ String $event;
        final /* synthetic */ boolean $isFreeform;
        final /* synthetic */ String $pkg;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass19(String str, boolean z2, String str2, L0.d dVar) {
            super(2, dVar);
            this.$pkg = str;
            this.$isFreeform = z2;
            this.$event = str2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewController.this.new AnonymousClass19(this.$pkg, this.$isFreeform, this.$event, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass19) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            DynamicIslandWindowViewController.this.cancelDelayOperate(this.$pkg);
            String str = this.$pkg;
            List<DynamicIslandContentView> currentMiniWindowState = str != null ? DynamicIslandWindowViewController.access$getView(DynamicIslandWindowViewController.this).getCurrentMiniWindowState(str) : null;
            if (currentMiniWindowState != null) {
                boolean z2 = this.$isFreeform;
                DynamicIslandWindowViewController dynamicIslandWindowViewController = DynamicIslandWindowViewController.this;
                for (DynamicIslandContentView dynamicIslandContentView : currentMiniWindowState) {
                    DynamicIslandContentFakeView fakeView = dynamicIslandContentView.getFakeView();
                    if (fakeView != null) {
                        DynamicIslandContentFakeView.updateViewStateWhenCloseEnd$default(fakeView, z2, null, 2, null);
                    }
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).appEnter(dynamicIslandContentView);
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).onWindowAnimExtendLifetimeEnd(dynamicIslandContentView);
                }
            }
            String str2 = this.$pkg;
            if (str2 != null) {
                if (!kotlin.jvm.internal.n.c(this.$event, "freeform_to_fullscreen")) {
                    str2 = null;
                }
                if (str2 != null) {
                    DynamicIslandWindowViewController dynamicIslandWindowViewController2 = DynamicIslandWindowViewController.this;
                    DynamicIslandEventCoordinator eventCoordinator = DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController2).getEventCoordinator();
                    if (eventCoordinator != null) {
                        eventCoordinator.setLastFullScreenActivityPkg(str2);
                    }
                    dynamicIslandWindowViewController2.getDynamicIslandSafeguardsController().setFullScreenPkg(str2);
                    dynamicIslandWindowViewController2.setFullScreenPkg(str2);
                }
            }
            if (DynamicIslandWindowViewController.this.hasAppState(null)) {
                DynamicIslandWindowViewController.this.getDynamicIslandSafeguardsController().delayExitApp(this.$pkg, true);
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$24, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$24", f = "DynamicIslandWindowViewController.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass24 extends N0.l implements Function2 {
        final /* synthetic */ boolean $isFreeform;
        final /* synthetic */ String $pkg;
        int label;
        final /* synthetic */ DynamicIslandWindowViewController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass24(boolean z2, DynamicIslandWindowViewController dynamicIslandWindowViewController, String str, L0.d dVar) {
            super(2, dVar);
            this.$isFreeform = z2;
            this.this$0 = dynamicIslandWindowViewController;
            this.$pkg = str;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new AnonymousClass24(this.$isFreeform, this.this$0, this.$pkg, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass24) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            if (this.$isFreeform) {
                this.this$0.get_isFreeformAnimRunning().setValue(N0.b.a(false));
            } else {
                this.this$0.get_isAppAnimRunning().setValue(N0.b.a(false));
            }
            WindowAnimState.Closed closed = new WindowAnimState.Closed(this.$isFreeform, false, false, 4, null);
            String str = this.$pkg;
            List<DynamicIslandContentView> listRequestHasIsland = str != null ? DynamicIslandWindowViewController.access$getView(this.this$0).requestHasIsland(str) : null;
            if (listRequestHasIsland == null || listRequestHasIsland.isEmpty()) {
                DynamicIslandWindowViewController.access$getView(this.this$0).hideAllElementSurface();
            }
            if (listRequestHasIsland != null) {
                DynamicIslandWindowViewController dynamicIslandWindowViewController = this.this$0;
                boolean z2 = this.$isFreeform;
                for (DynamicIslandContentView dynamicIslandContentView : listRequestHasIsland) {
                    Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "update_surface_info_finish state = " + dynamicIslandContentView + ", " + dynamicIslandWindowViewController.shouldDelayExitApp + ", " + dynamicIslandWindowViewController.requestPositionByClosePosition);
                    if (dynamicIslandContentView != null) {
                        dynamicIslandContentView.setWindowAnimState(closed);
                    }
                    if (dynamicIslandContentView != null) {
                        dynamicIslandContentView.setVisibility(0);
                    }
                    DynamicIslandBackgroundView backgroundView = dynamicIslandContentView != null ? dynamicIslandContentView.getBackgroundView() : null;
                    if (backgroundView != null) {
                        backgroundView.setVisibility(0);
                    }
                    DynamicIslandWindowView.updateViewStateWhenCloseEnd$default(DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController), dynamicIslandContentView, z2, null, 4, null);
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).updateIslandWindowAnimRunningState(false, dynamicIslandContentView, z2);
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).onWindowAnimExtendLifetimeEnd(dynamicIslandContentView);
                    if (dynamicIslandContentView != null) {
                        dynamicIslandContentView.setOpenAppFromIsland(false);
                    }
                }
            }
            if (this.$pkg != null && !this.$isFreeform && !kotlin.jvm.internal.n.c(this.this$0.getTopActivityPkg(), this.$pkg) && this.this$0.hasAppState(this.$pkg)) {
                DynamicIslandSafeguardsController.delayExitApp$default(this.this$0.getDynamicIslandSafeguardsController(), this.this$0.getTopActivityPkg(), false, 2, null);
            }
            DynamicIslandWindowViewController.setPositionValue$default(this.this$0, false, false, 2, null);
            DynamicIslandWindowViewController.access$getView(this.this$0).setAnimRunning(false, this.$isFreeform);
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$25, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$25", f = "DynamicIslandWindowViewController.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass25 extends N0.l implements Function2 {
        final /* synthetic */ String $pkg;
        int label;
        final /* synthetic */ DynamicIslandWindowViewController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass25(String str, DynamicIslandWindowViewController dynamicIslandWindowViewController, L0.d dVar) {
            super(2, dVar);
            this.$pkg = str;
            this.this$0 = dynamicIslandWindowViewController;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new AnonymousClass25(this.$pkg, this.this$0, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass25) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            String str = this.$pkg;
            List<DynamicIslandContentView> listRequestHasIsland = str != null ? DynamicIslandWindowViewController.access$getView(this.this$0).requestHasIsland(str) : null;
            if (listRequestHasIsland != null) {
                DynamicIslandWindowViewController dynamicIslandWindowViewController = this.this$0;
                for (DynamicIslandContentView dynamicIslandContentView : listRequestHasIsland) {
                    Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "fullscreen_to_pip state = " + dynamicIslandContentView);
                    DynamicIslandEventCoordinator eventCoordinator = DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).getEventCoordinator();
                    if (eventCoordinator != null) {
                        eventCoordinator.updateMiniBar(dynamicIslandContentView);
                    }
                }
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$9, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForLinkage$9", f = "DynamicIslandWindowViewController.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass9 extends N0.l implements Function2 {
        final /* synthetic */ boolean $isFreeform;
        final /* synthetic */ String $pkg;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass9(boolean z2, String str, L0.d dVar) {
            super(2, dVar);
            this.$isFreeform = z2;
            this.$pkg = str;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewController.this.new AnonymousClass9(this.$isFreeform, this.$pkg, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass9) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            DynamicIslandWindowViewController.setPositionValue$default(DynamicIslandWindowViewController.this, false, false, 2, null);
            if (this.$isFreeform) {
                DynamicIslandWindowViewController.this.get_isFreeformAnimRunning().setValue(N0.b.a(false));
            } else {
                DynamicIslandWindowViewController.this.get_isAppAnimRunning().setValue(N0.b.a(false));
            }
            String str = this.$pkg;
            List<DynamicIslandContentView> listRequestHasIsland = str != null ? DynamicIslandWindowViewController.access$getView(DynamicIslandWindowViewController.this).requestHasIsland(str) : null;
            if (listRequestHasIsland == null || listRequestHasIsland.isEmpty()) {
                DynamicIslandWindowViewController.access$getView(DynamicIslandWindowViewController.this).hideAllElementSurface();
            }
            WindowAnimState.Closed closed = new WindowAnimState.Closed(this.$isFreeform, false, false, 4, null);
            if (listRequestHasIsland != null) {
                DynamicIslandWindowViewController dynamicIslandWindowViewController = DynamicIslandWindowViewController.this;
                String str2 = this.$pkg;
                boolean z2 = this.$isFreeform;
                for (DynamicIslandContentView dynamicIslandContentView : listRequestHasIsland) {
                    Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "close_app_no_anim state = " + dynamicIslandContentView);
                    if (dynamicIslandContentView != null) {
                        dynamicIslandContentView.setWindowAnimState(closed);
                    }
                    dynamicIslandWindowViewController.getAppCloseRealIslandRect(DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).calculateCollapse(dynamicIslandContentView), dynamicIslandContentView, str2, z2);
                    if (dynamicIslandContentView != null) {
                        dynamicIslandContentView.setVisibility(0);
                    }
                    DynamicIslandBackgroundView backgroundView = dynamicIslandContentView != null ? dynamicIslandContentView.getBackgroundView() : null;
                    if (backgroundView != null) {
                        backgroundView.setVisibility(0);
                    }
                    DynamicIslandWindowView.updateViewStateWhenCloseEnd$default(DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController), dynamicIslandContentView, z2, null, 4, null);
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).updateIslandWindowAnimRunningState(false, dynamicIslandContentView, z2);
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).onWindowAnimExtendLifetimeEnd(dynamicIslandContentView);
                    if (z2) {
                        DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).exitMiniWindow(dynamicIslandContentView);
                        DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).enterMiniWindowEnd();
                    } else {
                        DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).appExit(dynamicIslandContentView);
                        if (dynamicIslandContentView != null) {
                            dynamicIslandContentView.setOpenAppFromIsland(false);
                        }
                    }
                }
            }
            DynamicIslandWindowViewController.access$getView(DynamicIslandWindowViewController.this).setAnimRunning(false, this.$isFreeform);
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForMiddle$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$sendWindowAnimEventForMiddle$1", f = "DynamicIslandWindowViewController.kt", l = {}, m = "invokeSuspend")
    public static final class C06421 extends N0.l implements Function2 {
        final /* synthetic */ boolean $isFreeform;
        final /* synthetic */ String $pkg;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06421(String str, boolean z2, L0.d dVar) {
            super(2, dVar);
            this.$pkg = str;
            this.$isFreeform = z2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewController.this.new C06421(this.$pkg, this.$isFreeform, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06421) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            DynamicIslandWindowViewController.this.cancelDelayOperate(this.$pkg);
            String str = this.$pkg;
            List<DynamicIslandContentView> currentMiniWindowState = str != null ? DynamicIslandWindowViewController.access$getView(DynamicIslandWindowViewController.this).getCurrentMiniWindowState(str) : null;
            if (currentMiniWindowState != null) {
                boolean z2 = this.$isFreeform;
                DynamicIslandWindowViewController dynamicIslandWindowViewController = DynamicIslandWindowViewController.this;
                for (DynamicIslandContentView dynamicIslandContentView : currentMiniWindowState) {
                    DynamicIslandContentFakeView fakeView = dynamicIslandContentView.getFakeView();
                    if (fakeView != null) {
                        DynamicIslandContentFakeView.updateViewStateWhenCloseEnd$default(fakeView, z2, null, 2, null);
                    }
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).appEnter(dynamicIslandContentView);
                    DynamicIslandWindowViewController.access$getView(dynamicIslandWindowViewController).onWindowAnimExtendLifetimeEnd(dynamicIslandContentView);
                }
            }
            if (DynamicIslandWindowViewController.this.hasAppState(null)) {
                DynamicIslandWindowViewController.this.getDynamicIslandSafeguardsController().delayExitApp(this.$pkg, true);
            }
            return s.f314a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [android.database.ContentObserver, miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$settingsObserver$1] */
    public DynamicIslandWindowViewController(final DynamicIslandWindowView view, @DynamicIsland E scope, @Main DelayableExecutor uiExecutor, E0.a notificationChronometerManager, E0.a avoidScreenBurnInHelper, DynamicIslandWindowState windowState, E0.a externalTouchHandler, DynamicIslandDesktopStateController dynamicIslandDesktopStateController, DynamicIslandSafeguardsController dynamicIslandSafeguardsController, E0.a externalStateRepository, AutoDensityController autoDensityController, E0.a lottieProgressManager, BinderC0222a hyperDropInfoNotifierService, DynamicIslandSizeRepository sizeRepository, @Background final Handler bgHandler) {
        super(view);
        kotlin.jvm.internal.n.g(view, "view");
        kotlin.jvm.internal.n.g(scope, "scope");
        kotlin.jvm.internal.n.g(uiExecutor, "uiExecutor");
        kotlin.jvm.internal.n.g(notificationChronometerManager, "notificationChronometerManager");
        kotlin.jvm.internal.n.g(avoidScreenBurnInHelper, "avoidScreenBurnInHelper");
        kotlin.jvm.internal.n.g(windowState, "windowState");
        kotlin.jvm.internal.n.g(externalTouchHandler, "externalTouchHandler");
        kotlin.jvm.internal.n.g(dynamicIslandDesktopStateController, "dynamicIslandDesktopStateController");
        kotlin.jvm.internal.n.g(dynamicIslandSafeguardsController, "dynamicIslandSafeguardsController");
        kotlin.jvm.internal.n.g(externalStateRepository, "externalStateRepository");
        kotlin.jvm.internal.n.g(autoDensityController, "autoDensityController");
        kotlin.jvm.internal.n.g(lottieProgressManager, "lottieProgressManager");
        kotlin.jvm.internal.n.g(hyperDropInfoNotifierService, "hyperDropInfoNotifierService");
        kotlin.jvm.internal.n.g(sizeRepository, "sizeRepository");
        kotlin.jvm.internal.n.g(bgHandler, "bgHandler");
        this.uiExecutor = uiExecutor;
        this.avoidScreenBurnInHelper = avoidScreenBurnInHelper;
        this.windowState = windowState;
        this.dynamicIslandDesktopStateController = dynamicIslandDesktopStateController;
        this.dynamicIslandSafeguardsController = dynamicIslandSafeguardsController;
        this.autoDensityController = autoDensityController;
        this.lottieProgressManager = lottieProgressManager;
        this.hyperDropInfoNotifierService = hyperDropInfoNotifierService;
        this.sizeRepository = sizeRepository;
        this.bgHandler = bgHandler;
        this.externalTouchHandler$delegate = ConvenienceExtensionsKt.getKotlinLazy(externalTouchHandler);
        this.externalStateRepository$delegate = ConvenienceExtensionsKt.getKotlinLazy(externalStateRepository);
        this.notificationChronometerManager$delegate = ConvenienceExtensionsKt.getKotlinLazy(notificationChronometerManager);
        InterfaceC0418f interfaceC0418fConflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new DynamicIslandWindowViewController$children$1(view, null));
        E.a aVar = j1.E.f4648a;
        final y yVarZ = AbstractC0420h.z(interfaceC0418fConflatedCallbackFlow, scope, aVar.c(), 1);
        this.children = yVarZ;
        this.contentViews = AbstractC0420h.z(AbstractC0420h.n(new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$special$$inlined$map$1

            /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$special$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements InterfaceC0419g {
                final /* synthetic */ InterfaceC0419g $this_unsafeFlow;

                /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$special$$inlined$map$1$2$1, reason: invalid class name */
                @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$special$$inlined$map$1$2", f = "DynamicIslandWindowViewController.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends N0.d {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(L0.d dVar) {
                        super(dVar);
                    }

                    @Override // N0.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(InterfaceC0419g interfaceC0419g) {
                    this.$this_unsafeFlow = interfaceC0419g;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // j1.InterfaceC0419g
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r7, L0.d r8) throws java.lang.Throwable {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$special$$inlined$map$1$2$1 r0 = (miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$special$$inlined$map$1$2$1 r0 = new miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$special$$inlined$map$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        java.lang.Object r1 = M0.c.c()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        H0.k.b(r8)
                        goto L75
                    L29:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L31:
                        H0.k.b(r8)
                        j1.g r6 = r6.$this_unsafeFlow
                        java.util.List r7 = (java.util.List) r7
                        java.util.ArrayList r8 = new java.util.ArrayList
                        r8.<init>()
                        java.util.Iterator r7 = r7.iterator()
                    L41:
                        boolean r2 = r7.hasNext()
                        if (r2 == 0) goto L6c
                        java.lang.Object r2 = r7.next()
                        android.view.View r2 = (android.view.View) r2
                        boolean r4 = r2 instanceof miui.systemui.dynamicisland.DynamicIslandBackgroundView
                        r5 = 0
                        if (r4 == 0) goto L55
                        miui.systemui.dynamicisland.DynamicIslandBackgroundView r2 = (miui.systemui.dynamicisland.DynamicIslandBackgroundView) r2
                        goto L56
                    L55:
                        r2 = r5
                    L56:
                        if (r2 == 0) goto L5e
                        r4 = 0
                        android.view.View r2 = r2.getChildAt(r4)
                        goto L5f
                    L5e:
                        r2 = r5
                    L5f:
                        boolean r4 = r2 instanceof miui.systemui.dynamicisland.window.content.DynamicIslandContentView
                        if (r4 == 0) goto L66
                        r5 = r2
                        miui.systemui.dynamicisland.window.content.DynamicIslandContentView r5 = (miui.systemui.dynamicisland.window.content.DynamicIslandContentView) r5
                    L66:
                        if (r5 == 0) goto L41
                        r8.add(r5)
                        goto L41
                    L6c:
                        r0.label = r3
                        java.lang.Object r6 = r6.emit(r8, r0)
                        if (r6 != r1) goto L75
                        return r1
                    L75:
                        H0.s r6 = H0.s.f314a
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                Object objCollect = yVarZ.collect(new AnonymousClass2(interfaceC0419g), dVar);
                return objCollect == M0.c.c() ? objCollect : s.f314a;
            }
        }), scope, aVar.c(), 1);
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        this.uiScope = F.g(scope, dispatchers.getMain());
        this.uiScopeImmediate = F.g(scope, dispatchers.getMain().z());
        this.ioScope = F.g(scope, dispatchers.getIO());
        this.inSmallWindowMap = new HashMap<>();
        this.islandData = new HashMap<>();
        this.pendingData = new HashMap<>();
        this.topActivityPkg = DynamicIslandConstants.MIUI_HOME;
        this.lastTopActivityPkg = DynamicIslandConstants.MIUI_HOME;
        Boolean bool = Boolean.FALSE;
        u uVarA = K.a(bool);
        this._isFreeformAnimRunning = uVarA;
        this.isFreeformAnimRunning = AbstractC0420h.b(uVarA);
        this.fullScreenPkg = "";
        u uVarA2 = K.a(new Region());
        this._freeformAnimRegion = uVarA2;
        this.freeformAnimRegion = AbstractC0420h.b(uVarA2);
        u uVarA3 = K.a(bool);
        this._isAppAnimRunning = uVarA3;
        this.isAppAnimRunning = AbstractC0420h.b(uVarA3);
        this.appCloseRect = new HashMap<>();
        this.isSupportFreeformAnim = true;
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$collapsedReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) throws PendingIntent.CanceledException {
                kotlin.jvm.internal.n.g(context, "context");
                kotlin.jvm.internal.n.g(intent, "intent");
                Log.d("DynamicIslandWindowViewController", "onReceive   : " + intent.getAction());
                if (kotlin.jvm.internal.n.c(DynamicIslandConstants.ACTION_COLLAPSE_ISLAND, intent.getAction())) {
                    view.collapse("receive collapse");
                }
            }
        };
        this.collapsedReceiver = broadcastReceiver;
        BroadcastReceiver broadcastReceiver2 = new BroadcastReceiver() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$packagesBroadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Uri data;
                kotlin.jvm.internal.n.g(context, "context");
                kotlin.jvm.internal.n.g(intent, "intent");
                String action = intent.getAction();
                if ((kotlin.jvm.internal.n.c("android.intent.action.PACKAGE_ADDED", action) || kotlin.jvm.internal.n.c(SystemVolumeController.ACTION_PACKAGE_REPLACED, action) || kotlin.jvm.internal.n.c("android.intent.action.PACKAGE_REMOVED", action)) && (data = intent.getData()) != null) {
                    String schemeSpecificPart = data.getSchemeSpecificPart();
                    DynamicIslandWindowView dynamicIslandWindowView = view;
                    kotlin.jvm.internal.n.d(schemeSpecificPart);
                    dynamicIslandWindowView.updatePkgSupportFreeform(schemeSpecificPart);
                }
            }
        };
        this.packagesBroadcastReceiver = broadcastReceiver2;
        BroadcastReceiver broadcastReceiver3 = new BroadcastReceiver() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$miPlayShowStateChangedBroadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                kotlin.jvm.internal.n.g(context, "context");
                kotlin.jvm.internal.n.g(intent, "intent");
                if (kotlin.jvm.internal.n.c(DynamicIslandConstants.ACTION_MIPLAY_SHOW_STATE_CHANGED, intent.getAction())) {
                    this.this$0.handleMiPlayShowState(intent.getBooleanExtra(DynamicIslandConstants.EXTRA_MIPLAY_SHOW_STATE, true));
                }
            }
        };
        this.miPlayShowStateChangedBroadcastReceiver = broadcastReceiver3;
        DeviceStateManagerCompat deviceStateManagerCompat = DeviceStateManagerCompat.INSTANCE;
        Object deviceStateManagerInstance = deviceStateManagerCompat.getDeviceStateManagerInstance();
        this.deviceStateManager = deviceStateManagerInstance;
        this.foldStateCallbacks = new ArrayList<>();
        Object foldStateListenerInstance = deviceStateManagerCompat.getFoldStateListenerInstance(getContext(), new Consumer() { // from class: miui.systemui.dynamicisland.window.p
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DynamicIslandWindowViewController.foldStateListener$lambda$3(this.f5758a, (Boolean) obj);
            }
        });
        this.foldStateListener = foldStateListenerInstance;
        TaskStackChangeListener taskStackChangeListener = new TaskStackChangeListener() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$taskStackListener$1
            @Override // miui.systemui.notification.TaskStackChangeListener
            public void onLockTaskModeChanged(int i2) {
                this.this$0.screenPinningActive(i2 == 2);
                Log.d("DynamicIslandWindowViewController", "onLockTaskModeChanged: screenPinningActive:" + (i2 == 2));
            }
        };
        this.taskStackListener = taskStackChangeListener;
        ContentResolver contentResolver = getContext().getContentResolver();
        this.contentResolver = contentResolver;
        ?? r10 = new ContentObserver(bgHandler) { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$settingsObserver$1
            @Override // android.database.ContentObserver
            public void onChange(boolean z2) {
                if (z2) {
                    return;
                }
                boolean z3 = Settings.Secure.getInt(this.this$0.contentResolver, "full_screen_aod_on", 0) == 1;
                boolean z4 = Settings.Secure.getInt(this.this$0.contentResolver, "doze_always_on", 0) == 1;
                this.this$0.isFullAodOn = z3;
                this.this$0.isAodOn = z4;
                Log.d("DynamicIslandWindowViewController", "isFullAodOn: " + this.this$0.isFullAodOn + " isAodOn: " + this.this$0.isAodOn);
            }
        };
        this.settingsObserver = r10;
        this.configuration = new Configuration(getResources().getConfiguration());
        updateWindowState();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DynamicIslandConstants.ACTION_COLLAPSE_ISLAND);
        this.isSupportFreeformAnim = DynamicIslandUtils.INSTANCE.isSupportFreeFormAnim(getContext());
        getContext().registerReceiverAsUser(broadcastReceiver, UserHandle.ALL, intentFilter, DynamicIslandConstants.PERMISSION_SYSTEMUI_NOTIFICATION, null, 2);
        this.isRegisteredCollapsedReceiver = true;
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter2.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter2.addAction(SystemVolumeController.ACTION_PACKAGE_REPLACED);
        intentFilter2.addDataScheme("package");
        getContext().registerReceiver(broadcastReceiver2, intentFilter2, 2);
        this.packagesBroadcastRegistered = true;
        Context context = getContext();
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction(DynamicIslandConstants.ACTION_MIPLAY_SHOW_STATE_CHANGED);
        s sVar = s.f314a;
        context.registerReceiver(broadcastReceiver3, intentFilter3, 4);
        this.miPlayShowStateChangedBroadcastRegistered = true;
        this.dynamicIslandDesktopStateController.start();
        deviceStateManagerCompat.registerCallbackCompat(deviceStateManagerInstance, this.uiExecutor, foldStateListenerInstance);
        this.autoDensityController.addOnDensityChangeListener(this);
        TaskStackChangeListeners.getInstance().registerTaskStackListener(taskStackChangeListener);
        OneHandModeUtils.INSTANCE.registerOneHandSettingsObserver(getContext());
        registerTempHiddenChanged();
        DynamicIslandAnimUtils.INSTANCE.registerHomeDynamicIslandContentObserver(getContext());
        contentResolver.registerContentObserver(Settings.Secure.getUriFor(AOD_FULL_SCREEN), true, r10);
        this.isFullAodOn = Settings.Secure.getInt(contentResolver, AOD_FULL_SCREEN, 0) == 1;
        contentResolver.registerContentObserver(Settings.Secure.getUriFor(AOD_MODE), true, r10);
        this.isAodOn = Settings.Secure.getInt(contentResolver, AOD_MODE, 0) == 1;
        this.pinOperateList = new ArrayList<>();
    }

    public static final /* synthetic */ DynamicIslandWindowView access$getView(DynamicIslandWindowViewController dynamicIslandWindowViewController) {
        return dynamicIslandWindowViewController.getView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelDelayOperate(String str) {
        this.dynamicIslandSafeguardsController.cancelDelayEnterApp(str);
        this.dynamicIslandSafeguardsController.cancelDelayEnterMiniWindow(str);
        this.dynamicIslandSafeguardsController.cancelDelayExitApp(str);
        this.dynamicIslandSafeguardsController.cancelDelayExitMiniWindow(str);
    }

    private final boolean checkFlipTiny(Integer num) {
        return num != null && FlipUtils.isFlip() && FlipUtils.isFlipTiny() && num.intValue() != 0;
    }

    private final void commandQueueDisable(int i2) {
        boolean z2 = (i2 & 131072) == 0;
        this.windowState.setTempHiddenType(DynamicIslandWindowState.TempHiddenType.SHOW_NOTIFICATION_ICONS);
        this.windowState.getShowNotificationIcons().setValue(Boolean.valueOf(z2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void foldStateListener$lambda$3(DynamicIslandWindowViewController this$0, Boolean folded) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(folded, "folded");
        Log.d(TAG, "onFoldStateChanged: " + folded);
        if (this$0.foldStateCallbacks.isEmpty()) {
            return;
        }
        Iterator<T> it = this$0.foldStateCallbacks.iterator();
        while (it.hasNext()) {
            ((DeviceStateManagerCompat.FoldStateCallback) it.next()).onFoldStateChanged(folded.booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:70:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.Rect getAppCloseRealIslandRect(miui.systemui.dynamicisland.event.DynamicIslandState r3, miui.systemui.dynamicisland.window.content.DynamicIslandContentView r4, java.lang.String r5, boolean r6) {
        /*
            Method dump skipped, instruction units count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.getAppCloseRealIslandRect(miui.systemui.dynamicisland.event.DynamicIslandState, miui.systemui.dynamicisland.window.content.DynamicIslandContentView, java.lang.String, boolean):android.graphics.Rect");
    }

    private final Rect getBigIslandRect(DynamicIslandContentView dynamicIslandContentView, Boolean bool) {
        if (dynamicIslandContentView != null) {
            return dynamicIslandContentView.getBigIslandRect(bool);
        }
        return null;
    }

    public static /* synthetic */ Rect getBigIslandRect$default(DynamicIslandWindowViewController dynamicIslandWindowViewController, DynamicIslandContentView dynamicIslandContentView, Boolean bool, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bool = null;
        }
        return dynamicIslandWindowViewController.getBigIslandRect(dynamicIslandContentView, bool);
    }

    private final Rect getCutoutRect() {
        return getView().getCutoutRect();
    }

    public static /* synthetic */ void getCutoutY$annotations() {
    }

    private final Rect getExpandedIslandRect(DynamicIslandContentView dynamicIslandContentView) {
        if (dynamicIslandContentView != null) {
            return dynamicIslandContentView.getExpandedIslandRect();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DynamicIslandExternalStateRepository getExternalStateRepository() {
        return (DynamicIslandExternalStateRepository) this.externalStateRepository$delegate.getValue();
    }

    private final DynamicIslandExternalTouchInteractor getExternalTouchHandler() {
        return (DynamicIslandExternalTouchInteractor) this.externalTouchHandler$delegate.getValue();
    }

    private final Rect getFakeExpandedViewRect(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandContentFakeView fakeView = dynamicIslandContentView.getFakeView();
        if (fakeView != null) {
            return fakeView.getExpandedIslandRect();
        }
        return null;
    }

    public static /* synthetic */ void getIslandMaxWidth$annotations() {
    }

    private final NotificationChronometerManager getNotificationChronometerManager() {
        return (NotificationChronometerManager) this.notificationChronometerManager$delegate.getValue();
    }

    private final Rect getSmallIslandRect(DynamicIslandContentView dynamicIslandContentView, DynamicIslandContentView dynamicIslandContentView2) {
        int smallIslandX = getSmallIslandX(dynamicIslandContentView2);
        if (dynamicIslandContentView != null) {
            return dynamicIslandContentView.getSmallIslandRect(smallIslandX);
        }
        return null;
    }

    public static /* synthetic */ Rect getSmallIslandRect$default(DynamicIslandWindowViewController dynamicIslandWindowViewController, DynamicIslandContentView dynamicIslandContentView, DynamicIslandContentView dynamicIslandContentView2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            dynamicIslandContentView2 = null;
        }
        return dynamicIslandWindowViewController.getSmallIslandRect(dynamicIslandContentView, dynamicIslandContentView2);
    }

    private final int getSmallIslandX(DynamicIslandContentView dynamicIslandContentView) {
        Context context;
        if (dynamicIslandContentView == null) {
            dynamicIslandContentView = getView().getCurrentBigIslandState();
        }
        if (dynamicIslandContentView != null && (context = dynamicIslandContentView.getContext()) != null && CommonUtils.isLayoutRtl(context)) {
            int currentBigIslandX$default = (DynamicIslandBaseContentView.getCurrentBigIslandX$default(dynamicIslandContentView, null, 1, null) - dynamicIslandContentView.getSpace()) - dynamicIslandContentView.getSmallIslandViewWidth();
            this.appAnimSmallX = currentBigIslandX$default;
            return currentBigIslandX$default;
        }
        int currentBigIslandX = (dynamicIslandContentView != null ? dynamicIslandContentView.getCurrentBigIslandX(Boolean.TRUE) : 0) + (dynamicIslandContentView != null ? dynamicIslandContentView.getCurrentBigIslandWidth(Boolean.TRUE) : 0) + (dynamicIslandContentView != null ? dynamicIslandContentView.getSpace() : 0);
        this.appAnimSmallX = currentBigIslandX;
        return currentBigIslandX;
    }

    public static /* synthetic */ int getSmallIslandX$default(DynamicIslandWindowViewController dynamicIslandWindowViewController, DynamicIslandContentView dynamicIslandContentView, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            dynamicIslandContentView = null;
        }
        return dynamicIslandWindowViewController.getSmallIslandX(dynamicIslandContentView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleMiPlayShowState(boolean z2) {
        notificationMiPlayShow(z2);
        getExternalStateRepository().notifyMiPlayShowStateChanged(z2);
        Log.d(TAG, "miPlayShowStateChangedBroadcastReceiver: miplayShowState " + z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasAppState(String str) {
        if (str != null) {
            DynamicIslandContentView mainAppExpanded = getView().getMainAppExpanded();
            if (kotlin.jvm.internal.n.c(mainAppExpanded != null ? mainAppExpanded.getPkgName() : null, str)) {
                return true;
            }
            DynamicIslandContentView subAppExpanded = getView().getSubAppExpanded();
            if (kotlin.jvm.internal.n.c(subAppExpanded != null ? subAppExpanded.getPkgName() : null, str)) {
                return true;
            }
        } else if (getView().getMainAppExpanded() != null || getView().getSubAppExpanded() != null) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void lockScreen(boolean z2) throws PendingIntent.CanceledException {
        boolean zIsTempHidden = this.windowState.isTempHidden(1);
        this.windowState.setTempHiddenType(DynamicIslandWindowState.TempHiddenType.SCREEN_LOCKED);
        DynamicIslandWindowState dynamicIslandWindowState = this.windowState;
        dynamicIslandWindowState.setScreenLockedChange(((Boolean) dynamicIslandWindowState.getScreenLocked().getValue()).booleanValue() != z2);
        this.windowState.getScreenLocked().setValue(Boolean.valueOf(z2));
        this.windowState.setFullAodOn(this.isFullAodOn);
        this.windowState.setAodOn(this.isAodOn);
        Log.d(TAG, "lockScreen: screenLockedChange:" + this.windowState.getScreenLockedChange() + ", lockScreen " + z2);
        if (z2) {
            if (zIsTempHidden) {
                getView().collapse("lockscreen");
            }
            getView().hideAllElementSurface();
            getView().resetHeadsUpLocation();
            removeInvalidChildView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean needExitMiniWindow(boolean z2, String str) {
        return (!z2 && isNotSupportFreeFormAnim()) || (str != null && getView().getMainMiniWindow(str) == null && getView().getSubMiniWindow(str) != null);
    }

    private final void notificationAppearance(boolean z2) {
        ((AvoidScreenBurnInHelper) this.avoidScreenBurnInHelper.get()).updateNotificationPanelExpanded(z2);
        this.windowState.setTempHiddenType(DynamicIslandWindowState.TempHiddenType.NOTIFICATION_APPEARANCE);
        this.windowState.getNotificationAppearance().setValue(Boolean.valueOf(z2));
        getView().updateTouchRegion();
    }

    private final void notificationMiPlayShow(boolean z2) {
        this.windowState.setTempHiddenType(DynamicIslandWindowState.TempHiddenType.MIPLAY_SHOW);
        this.windowState.getMiPlayShow().setValue(Boolean.valueOf(z2));
    }

    private final void notificationVisible(boolean z2) {
        this.windowState.setNotificationVisible(z2);
    }

    private final void onConfigurationChanged(Configuration configuration) {
        updateWindowState();
        if (configuration == null) {
            return;
        }
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        Configuration configuration2 = this.configuration;
        if (configuration2 == null) {
            kotlin.jvm.internal.n.w("configuration");
            configuration2 = null;
        }
        configUtils.update(configuration2, configuration);
        getView().onConfigChanged(configuration);
    }

    private final void onTopActivityChange(ComponentName componentName, boolean z2, boolean z3, boolean z4, boolean z5) {
        DynamicIslandContentView dynamicIslandContentView;
        DynamicIslandData currentIslandData;
        DynamicIslandData currentIslandData2;
        Bundle extras;
        List<DynamicIslandContentView> listRequestHasIsland;
        DynamicIslandContentFakeView fakeView;
        DynamicIslandContentFakeView fakeView2;
        Log.d(TAG, "onTopActivityChange: topActivity " + componentName + ", inSmallWindow " + z2 + ", isSupportPip " + z3 + ", isFocus " + z4 + " fullScreenPkg " + this.fullScreenPkg);
        Boolean boolValueOf = Boolean.valueOf(z2);
        String packageName = componentName != null ? componentName.getPackageName() : null;
        this.dynamicIslandSafeguardsController.setFullScreenPkg(this.fullScreenPkg);
        if (kotlin.jvm.internal.n.c(packageName, this.topActivityPkg) && kotlin.jvm.internal.n.c(boolValueOf, this.inSmallWindowMap.get(packageName))) {
            return;
        }
        String str = this.topActivityPkg;
        this.lastTopActivityPkg = str;
        Boolean bool = this.inSmallWindowMap.get(str);
        this.topActivityPkg = packageName;
        this.windowState.setTopActivityPkg(packageName);
        this.inSmallWindowMap.clear();
        String str2 = this.topActivityPkg;
        if (str2 != null) {
            this.inSmallWindowMap.put(str2, Boolean.valueOf(kotlin.jvm.internal.n.c(boolValueOf, Boolean.TRUE)));
        }
        DynamicIslandContentView currentExpandedState = getView().getCurrentExpandedState();
        Boolean boolValueOf2 = (currentExpandedState == null || (fakeView2 = currentExpandedState.getFakeView()) == null) ? null : Boolean.valueOf(fakeView2.getOpenAppFromIsland());
        Boolean boolValueOf3 = currentExpandedState != null ? Boolean.valueOf(currentExpandedState.getOpenAppFromIsland()) : null;
        Log.d(TAG, "openAppFromIsland: " + currentExpandedState + ", " + boolValueOf2 + ", " + boolValueOf3 + " " + this.requestPosition);
        cancelDelayOperate(this.topActivityPkg);
        this.shouldDelayExitApp = false;
        StringBuilder sb = new StringBuilder();
        sb.append("lastTopActivityIsSmallWindow ");
        sb.append(bool);
        Log.d(TAG, sb.toString());
        if (kotlin.jvm.internal.n.c(bool, Boolean.TRUE)) {
            AbstractC0369g.b(this.uiScope, null, null, new AnonymousClass2(null), 3, null);
        } else if (!kotlin.jvm.internal.n.c(this.lastTopActivityPkg, this.fullScreenPkg)) {
            if (this.requestPosition) {
                this.shouldDelayExitApp = this.requestPositionByClosePosition;
            } else {
                DynamicIslandSafeguardsController.delayExitApp$default(this.dynamicIslandSafeguardsController, this.topActivityPkg, false, 2, null);
                this.shouldDelayExitApp = false;
            }
        }
        if (currentExpandedState == null || (fakeView = currentExpandedState.getFakeView()) == null || !fakeView.getOpenAppFromIsland()) {
            if (currentExpandedState == null || !currentExpandedState.getOpenAppFromIsland()) {
                if (!kotlin.jvm.internal.n.c(this.topActivityPkg, this.fullScreenPkg) && (listRequestHasIsland = getView().requestHasIsland(this.fullScreenPkg)) != null && (!listRequestHasIsland.isEmpty())) {
                    int size = listRequestHasIsland.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        List<DynamicIslandContentView> listRequestHasIsland2 = getView().requestHasIsland(this.fullScreenPkg);
                        if (listRequestHasIsland2 != null) {
                            for (DynamicIslandContentView dynamicIslandContentView2 : listRequestHasIsland2) {
                                if (canEnterAppState(dynamicIslandContentView2)) {
                                    this.dynamicIslandSafeguardsController.delayEnterApp(dynamicIslandContentView2, true);
                                }
                            }
                        }
                    }
                }
                String str3 = this.topActivityPkg;
                List<DynamicIslandContentView> listRequestHasIsland3 = str3 != null ? getView().requestHasIsland(str3) : null;
                if (listRequestHasIsland3 == null || !(!listRequestHasIsland3.isEmpty())) {
                    return;
                }
                int size2 = listRequestHasIsland3.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    String str4 = this.topActivityPkg;
                    List<DynamicIslandContentView> listRequestHasIsland4 = str4 != null ? getView().requestHasIsland(str4) : null;
                    if (listRequestHasIsland4 != null) {
                        for (DynamicIslandContentView dynamicIslandContentView3 : listRequestHasIsland4) {
                            if (kotlin.jvm.internal.n.c(boolValueOf, Boolean.TRUE)) {
                                if (!I0.u.F(this.pinOperateList, this.topActivityPkg)) {
                                    String string = (dynamicIslandContentView3 == null || (currentIslandData2 = dynamicIslandContentView3.getCurrentIslandData()) == null || (extras = currentIslandData2.getExtras()) == null) ? null : extras.getString("miui.pkg.name");
                                    if (string != null) {
                                        this.dynamicIslandSafeguardsController.delayEnterMiniWidow(string);
                                    }
                                }
                            } else if (canEnterAppState(dynamicIslandContentView3)) {
                                this.dynamicIslandSafeguardsController.delayEnterApp(dynamicIslandContentView3, z5);
                                setPositionValue$default(this, false, false, 2, null);
                            }
                        }
                    }
                    if (kotlin.jvm.internal.n.c(boolValueOf, Boolean.TRUE)) {
                        D.a(this.pinOperateList).remove((listRequestHasIsland4 == null || (dynamicIslandContentView = listRequestHasIsland4.get(i3)) == null || (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData.getKey());
                    }
                }
            }
        }
    }

    public static /* synthetic */ void onTopActivityChange$default(DynamicIslandWindowViewController dynamicIslandWindowViewController, ComponentName componentName, boolean z2, boolean z3, boolean z4, boolean z5, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            z5 = false;
        }
        dynamicIslandWindowViewController.onTopActivityChange(componentName, z2, z3, z4, z5);
    }

    private final void openAppError(boolean z2, String str) {
        if (z2) {
            AbstractC0369g.b(this.uiScope, null, null, new AnonymousClass1(str, this, null), 3, null);
        }
    }

    private final void registerTempHiddenChanged() {
        AbstractC0369g.b(this.uiScopeImmediate, null, null, new C06401(null), 3, null);
        AbstractC0369g.b(this.uiScopeImmediate, null, null, new C06412(null), 3, null);
        AbstractC0369g.b(this.uiScopeImmediate, null, null, new AnonymousClass3(null), 3, null);
        AbstractC0369g.b(this.uiScopeImmediate, null, null, new AnonymousClass4(null), 3, null);
        AbstractC0369g.b(this.uiScopeImmediate, null, null, new AnonymousClass5(null), 3, null);
    }

    private final void removeInvalidChildView() {
        Iterator it = e1.l.q(e1.l.p(ViewGroupKt.getChildren(getView()), DynamicIslandWindowViewController$removeInvalidChildView$contentViews$1.INSTANCE), getView().getContentViewList()).iterator();
        while (it.hasNext()) {
            removeInvalidViewFromWindow((DynamicIslandContentView) it.next());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void removeInvalidViewFromWindow(miui.systemui.dynamicisland.window.content.DynamicIslandContentView r6) {
        /*
            r5 = this;
            java.lang.String r0 = "DynamicIslandWindowViewController"
            r1 = 0
            if (r6 == 0) goto L12
            com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData r2 = r6.getCurrentIslandData()     // Catch: java.lang.Exception -> L10
            if (r2 == 0) goto L12
            java.lang.String r2 = r2.getKey()     // Catch: java.lang.Exception -> L10
            goto L13
        L10:
            r5 = move-exception
            goto L4a
        L12:
            r2 = r1
        L13:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L10
            r3.<init>()     // Catch: java.lang.Exception -> L10
            java.lang.String r4 = "removeInvalidViewFromWindow "
            r3.append(r4)     // Catch: java.lang.Exception -> L10
            r3.append(r2)     // Catch: java.lang.Exception -> L10
            java.lang.String r2 = r3.toString()     // Catch: java.lang.Exception -> L10
            android.util.Log.d(r0, r2)     // Catch: java.lang.Exception -> L10
            if (r6 == 0) goto L3a
            android.view.ViewParent r2 = r6.getParent()     // Catch: java.lang.Exception -> L10
            if (r2 == 0) goto L3a
            android.view.View r3 = r5.getView()     // Catch: java.lang.Exception -> L10
            miui.systemui.dynamicisland.window.DynamicIslandWindowView r3 = (miui.systemui.dynamicisland.window.DynamicIslandWindowView) r3     // Catch: java.lang.Exception -> L10
            android.view.View r2 = (android.view.View) r2     // Catch: java.lang.Exception -> L10
            r3.removeView(r2)     // Catch: java.lang.Exception -> L10
        L3a:
            android.view.View r5 = r5.getView()     // Catch: java.lang.Exception -> L10
            miui.systemui.dynamicisland.window.DynamicIslandWindowView r5 = (miui.systemui.dynamicisland.window.DynamicIslandWindowView) r5     // Catch: java.lang.Exception -> L10
            if (r6 == 0) goto L46
            miui.systemui.dynamicisland.event.DynamicIslandState r1 = r6.getState()     // Catch: java.lang.Exception -> L10
        L46:
            r5.preRemoveDynamicIsland(r6, r1)     // Catch: java.lang.Exception -> L10
            goto L63
        L4a:
            r5.printStackTrace()
            H0.s r5 = H0.s.f314a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r1 = "removeInvalidViewFromWindow error + "
            r6.append(r1)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            android.util.Log.e(r0, r5)
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.removeInvalidViewFromWindow(miui.systemui.dynamicisland.window.content.DynamicIslandContentView):void");
    }

    private final boolean requestMaxWidth(DynamicIslandData dynamicIslandData, boolean z2) {
        if (getIslandMaxWidth() != 0.0f) {
            return false;
        }
        this.pendingData.put(dynamicIslandData, Boolean.valueOf(z2));
        Bundle extras = dynamicIslandData.getExtras();
        String string = extras != null ? extras.getString("miui.pkg.name") : null;
        Bundle extras2 = dynamicIslandData.getExtras();
        getView().notifyAddIsland(string, extras2 != null ? Integer.valueOf(extras2.getInt("miui.user.id")) : null, dynamicIslandData.getKey(), dynamicIslandData.getProperties());
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:172:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x04d2  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x05be  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x08eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final android.os.Bundle sendWindowAnimEventForLinkage(java.lang.String r26, boolean r27, boolean r28, android.os.Bundle r29) {
        /*
            Method dump skipped, instruction units count: 3346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.sendWindowAnimEventForLinkage(java.lang.String, boolean, boolean, android.os.Bundle):android.os.Bundle");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0060, code lost:
    
        if (r19.equals("split_to_dismiss") == false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0291, code lost:
    
        if (r19.equals("anim_finished") == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:?, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0075, code lost:
    
        if (r19.equals("request_close_position") == false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007e, code lost:
    
        if (r19.equals("app_to_recent") == false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00e2, code lost:
    
        if (r19.equals("request_open_position") == false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e6, code lost:
    
        setPositionValue(true, true);
        calculateAppClosePosition(r10, r20);
        r0 = getView().getCutoutRect();
        android.util.Log.d(miui.systemui.dynamicisland.DynamicIslandConstants.TAG_DEBUG_ANIM, "sendWindowAnimEventForMiddle: rect : " + r0 + "}");
        r1 = new android.os.Bundle();
        r1.putParcelable("position", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0119, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x011e, code lost:
    
        if (r19.equals("update_surface_info_finish") == false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0128, code lost:
    
        if (r19.equals("close_app_no_anim") == false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x012c, code lost:
    
        if (r10 == null) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x012e, code lost:
    
        r2 = getView().requestHasIsland(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0139, code lost:
    
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x013a, code lost:
    
        if (r2 == null) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x013c, code lost:
    
        r16 = r2.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0144, code lost:
    
        if (r16.hasNext() == false) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0146, code lost:
    
        r7 = (miui.systemui.dynamicisland.window.content.DynamicIslandContentView) r16.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x014d, code lost:
    
        if (r7 == null) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x014f, code lost:
    
        r2 = r7.getState();
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0154, code lost:
    
        r2 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0155, code lost:
    
        android.util.Log.d(miui.systemui.dynamicisland.DynamicIslandConstants.TAG_DEBUG_ANIM, "sendWindowAnimEventForMiddle: currentState = " + r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0167, code lost:
    
        if (r7 != null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x016a, code lost:
    
        r7.setVisibility(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x016d, code lost:
    
        if (r7 == null) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x016f, code lost:
    
        r2 = r7.getBackgroundView();
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0174, code lost:
    
        r2 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0175, code lost:
    
        if (r2 != 0) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0178, code lost:
    
        r2.setVisibility(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x017b, code lost:
    
        miui.systemui.dynamicisland.window.DynamicIslandWindowView.updateViewStateWhenCloseEnd$default(getView(), r7, r20, null, 4, null);
        getView().updateIslandWindowAnimRunningState(false, r7, r20);
        getView().onWindowAnimExtendLifetimeEnd(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01a0, code lost:
    
        if (r20 == false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01a2, code lost:
    
        getView().exitMiniWindow(r7);
        getView().enterMiniWindowEnd();
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01b9, code lost:
    
        if (kotlin.jvm.internal.n.c(r19, "update_surface_info_finish") == false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01bb, code lost:
    
        if (r10 == null) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01bd, code lost:
    
        if (r20 != false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01c5, code lost:
    
        if (kotlin.jvm.internal.n.c(r18.topActivityPkg, r10) != false) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01cb, code lost:
    
        if (hasAppState(r10) == false) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01cd, code lost:
    
        miui.systemui.dynamicisland.window.DynamicIslandSafeguardsController.delayExitApp$default(r18.dynamicIslandSafeguardsController, r18.topActivityPkg, false, 2, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01d6, code lost:
    
        getView().appExit(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01df, code lost:
    
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01e2, code lost:
    
        r1 = r9;
        setPositionValue$default(r18, false, false, 2, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01ee, code lost:
    
        if (r19.equals("open_app_no_anim") == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01f3, code lost:
    
        r1 = null;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34, types: [miui.systemui.dynamicisland.DynamicIslandBackgroundView] */
    /* JADX WARN: Type inference failed for: r2v59 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final android.os.Bundle sendWindowAnimEventForMiddle(java.lang.String r19, boolean r20, boolean r21, android.os.Bundle r22) {
        /*
            Method dump skipped, instruction units count: 716
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.sendWindowAnimEventForMiddle(java.lang.String, boolean, boolean, android.os.Bundle):android.os.Bundle");
    }

    private final void setCancelTimeout(DynamicIslandData dynamicIslandData, IslandTemplate islandTemplate) {
        if (islandTemplate.getIslandTimeout() != 0) {
            this.dynamicIslandSafeguardsController.delayDeleted(dynamicIslandData.getKey(), islandTemplate.getIslandTimeout());
            return;
        }
        DynamicIslandSafeguardsController dynamicIslandSafeguardsController = this.dynamicIslandSafeguardsController;
        String key = dynamicIslandData.getKey();
        Integer properties = dynamicIslandData.getProperties();
        dynamicIslandSafeguardsController.delayDeleted(key, (properties != null && properties.intValue() == 0) ? 5L : 3600L);
    }

    private final void setPositionValue(boolean z2, boolean z3) {
        this.requestPosition = z2;
        this.requestPositionByClosePosition = z3;
    }

    public static /* synthetic */ void setPositionValue$default(DynamicIslandWindowViewController dynamicIslandWindowViewController, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z3 = false;
        }
        dynamicIslandWindowViewController.setPositionValue(z2, z3);
    }

    private final void statusBarAppearance(boolean z2) {
        this.windowState.setTempHiddenType(DynamicIslandWindowState.TempHiddenType.STATUS_BAR_DISAPPEARANCE);
        this.windowState.getStatusBarDisappearance().setValue(Boolean.valueOf(z2));
        getView().updateStatusBarVisible(!z2);
    }

    private final void statusBarVisible(Bundle bundle) {
        boolean z2 = bundle.getBoolean(DynamicIslandConstants.EXTRA_STATUS_BAR_WINDOW_VISIBLE);
        statusBarAppearance(!z2);
        Log.d(TAG, "visible: " + z2);
    }

    private final void updateFlipResources() {
        if (CommonUtils.isFlipDevice()) {
            boolean zIsTinyScreen = this.windowState.isTinyScreen();
            int tinyScreenInsetLeft = zIsTinyScreen ? DynamicIslandUtils.INSTANCE.getTinyScreenInsetLeft() : 0;
            int tinyScreenInsetRight = zIsTinyScreen ? DynamicIslandUtils.INSTANCE.getTinyScreenInsetRight() : 0;
            int tinyScreenInsetTop = zIsTinyScreen ? DynamicIslandUtils.INSTANCE.getTinyScreenInsetTop() : 0;
            int tinyScreenInsetBottom = zIsTinyScreen ? DynamicIslandUtils.INSTANCE.getTinyScreenInsetBottom() : 0;
            getView().setPadding(tinyScreenInsetLeft, 0, tinyScreenInsetRight, 0);
            Log.d(TAG, "update flip setPadding " + tinyScreenInsetLeft + " " + tinyScreenInsetTop + " " + tinyScreenInsetRight + " " + tinyScreenInsetBottom + " islandMaxWidth=" + getIslandMaxWidth());
        }
    }

    public void addDynamicIslandGlowEffectLayer(DynamicIslandGlowEffectLayer glowEffectLayer) {
        kotlin.jvm.internal.n.g(glowEffectLayer, "glowEffectLayer");
        this.dynamicIslandGlowEffectLayer = glowEffectLayer;
    }

    public final void addDynamicIslandTimeoutRemovedCallback(DynamicIslandCallback callback) {
        kotlin.jvm.internal.n.g(callback, "callback");
        this.dynamicIslandCallback = callback;
    }

    public void addDynamicIslandView(DynamicIslandData dynamicIslandData, boolean z2) {
        kotlin.jvm.internal.n.g(dynamicIslandData, "dynamicIslandData");
        Log.d(TAG, "addDynamicIslandView ,key: " + this.islandData.get(dynamicIslandData.getKey()) + " expand:" + z2);
        Bundle extras = dynamicIslandData.getExtras();
        if (extras != null) {
            extras.putString(DynamicIslandConstants.EXTRA_MIUI_KEY, dynamicIslandData.getKey());
        }
        if (requestMaxWidth(dynamicIslandData, z2)) {
            return;
        }
        IslandTemplate islandTemplate = getIslandTemplate(dynamicIslandData);
        if (islandTemplate == null) {
            String key = dynamicIslandData.getKey();
            kotlin.jvm.internal.n.d(key);
            removeDynamicIslandView(key);
            Log.e(TAG, "addDynamicIslandView: template is null");
            return;
        }
        Integer priority = dynamicIslandData.getPriority();
        int iIntValue = priority != null ? priority.intValue() : 1;
        Integer properties = dynamicIslandData.getProperties();
        int iIntValue2 = properties != null ? properties.intValue() : 1;
        Integer islandPriority = islandTemplate.getIslandPriority();
        if (islandPriority == null) {
            islandPriority = Integer.valueOf(iIntValue);
        }
        dynamicIslandData.setPriority(islandPriority);
        Integer islandProperty = islandTemplate.getIslandProperty();
        if (islandProperty == null) {
            islandProperty = Integer.valueOf(iIntValue2);
        }
        dynamicIslandData.setProperties(islandProperty);
        if (checkFlipTiny(dynamicIslandData.getProperties())) {
            Log.d(TAG, "addDynamicIslandView: skip in flip tiny");
            return;
        }
        DynamicIslandWindowView.addDynamicIslandData$default(getView(), dynamicIslandData, z2, getIslandMaxWidth(), getCutoutY(), false, 16, null);
        String key2 = dynamicIslandData.getKey();
        if (key2 != null) {
            this.islandData.put(key2, dynamicIslandData);
        }
        if (z2) {
            this.dynamicIslandSafeguardsController.cancelDelayCollapsed();
            if (islandTemplate.getExpandedTime() != 0) {
                this.dynamicIslandSafeguardsController.delayCollapsed(islandTemplate.getExpandedTime());
            } else {
                this.dynamicIslandSafeguardsController.delayCollapsed(5L);
            }
        }
        setCancelTimeout(dynamicIslandData, islandTemplate);
    }

    public final void addLottieAnimView(DynamicIslandContentView dynamicIslandContentView, DynamicIslandBaseContentView dynamicIslandBaseContentView, String str) {
        DynamicIslandData currentIslandData;
        Integer properties;
        if (dynamicIslandContentView == null || (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) == null || (properties = currentIslandData.getProperties()) == null || properties.intValue() != 0) {
            ((LottieProgressManager) this.lottieProgressManager.get()).addIslandLottie(str, R.id.island_lottie, dynamicIslandContentView != null ? dynamicIslandContentView.getBigIslandView() : null, dynamicIslandContentView != null ? dynamicIslandContentView.getSmallIslandView() : null, dynamicIslandBaseContentView != null ? dynamicIslandBaseContentView.getFakeBigIsland() : null, dynamicIslandBaseContentView != null ? dynamicIslandBaseContentView.getFakeSmallIsland() : null);
        }
    }

    public void addOnDynamicIslandViewChangedListener(DynamicIslandContent.DynamicIslandViewChangedListener listener) {
        kotlin.jvm.internal.n.g(listener, "listener");
        this.listener = listener;
        getView().addOnDynamicIslandViewChangedListener(listener);
    }

    public final void calculateAppClosePosition(String str, boolean z2) {
        List<DynamicIslandContentView> listRequestHasIsland = str != null ? getView().requestHasIsland(str) : null;
        if (listRequestHasIsland != null) {
            for (DynamicIslandContentView dynamicIslandContentView : listRequestHasIsland) {
                if (z2) {
                    if (!((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.MiniWindowExpanded)) {
                        if (!((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.Expanded)) {
                        }
                    }
                    DynamicIslandState dynamicIslandStateCalculateCollapse = getView().calculateCollapse(dynamicIslandContentView);
                    Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "newState = " + dynamicIslandStateCalculateCollapse);
                    getAppCloseRealIslandRect(dynamicIslandStateCalculateCollapse, dynamicIslandContentView, str, z2);
                } else {
                    if (!((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.AppExpanded)) {
                        if (!((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.Expanded)) {
                        }
                    }
                    DynamicIslandState dynamicIslandStateCalculateCollapse2 = getView().calculateCollapse(dynamicIslandContentView);
                    Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "newState = " + dynamicIslandStateCalculateCollapse2);
                    getAppCloseRealIslandRect(dynamicIslandStateCalculateCollapse2, dynamicIslandContentView, str, z2);
                }
            }
        }
    }

    public final boolean canEnterAppState(DynamicIslandContentView dynamicIslandContentView) {
        IslandTemplate template;
        String business = null;
        String pkgName = dynamicIslandContentView != null ? dynamicIslandContentView.getPkgName() : null;
        if (dynamicIslandContentView != null && (template = dynamicIslandContentView.getTemplate()) != null) {
            business = template.getBusiness();
        }
        return (kotlin.jvm.internal.n.c(business, "face_recognition") || kotlin.jvm.internal.n.c(pkgName, DynamicIslandConstants.MIUI_PERSONAL_ASSISTANT) || kotlin.jvm.internal.n.c(pkgName, DynamicIslandConstants.MIUI_SHARE_CONNECTIVITY) || kotlin.jvm.internal.n.c(pkgName, DynamicIslandConstants.MIUI_AON)) ? false : true;
    }

    public final void controlCenterExpanded(boolean z2) throws PendingIntent.CanceledException {
        this.windowState.setTempHiddenType(DynamicIslandWindowState.TempHiddenType.CONTROL_CENTER_EXPANDED);
        this.windowState.getControlCenterExpanded().setValue(Boolean.valueOf(z2));
        getView().updateTouchRegion();
        getView().collapse("cc expand");
    }

    public final void destroy() {
        if (this.isRegisteredCollapsedReceiver) {
            this.isRegisteredCollapsedReceiver = false;
            getContext().unregisterReceiver(this.collapsedReceiver);
        }
        if (this.packagesBroadcastRegistered) {
            this.packagesBroadcastRegistered = false;
            getContext().unregisterReceiver(this.packagesBroadcastReceiver);
        }
        if (this.miPlayShowStateChangedBroadcastRegistered) {
            this.miPlayShowStateChangedBroadcastRegistered = false;
            getContext().unregisterReceiver(this.miPlayShowStateChangedBroadcastReceiver);
        }
        this.dynamicIslandDesktopStateController.stop();
        DeviceStateManagerCompat.INSTANCE.unregisterCallbackCompat(this.deviceStateManager, this.foldStateListener);
        this.autoDensityController.removeOnDensityChangeListener(this);
        TaskStackChangeListeners.getInstance().unregisterTaskStackListener(this.taskStackListener);
        AppIconsManager.INSTANCE.clearAllIconCache();
        this.contentResolver.unregisterContentObserver(this.settingsObserver);
    }

    public final int getAppAnimSmallX() {
        return this.appAnimSmallX;
    }

    public final String getAppCloseKey() {
        return this.appCloseKey;
    }

    public final HashMap<String, Rect> getAppCloseRect() {
        return this.appCloseRect;
    }

    public final float getBatteryWidth() {
        return ((Number) this.sizeRepository.getIslandBatteryWidth().getValue()).floatValue();
    }

    public final y getChildren() {
        return this.children;
    }

    public final float getClockWidth() {
        return ((Number) this.sizeRepository.getIslandClockWidth().getValue()).floatValue();
    }

    public final y getContentViews() {
        return this.contentViews;
    }

    public final int getCurrentHeight() {
        return this.currentHeight;
    }

    public final float getCutoutY() {
        return ((Number) this.sizeRepository.getCutoutY().getValue()).floatValue();
    }

    public final int getDisplayOrientation() {
        return this.displayOrientation;
    }

    public final DynamicIslandCallback getDynamicIslandCallback() {
        return this.dynamicIslandCallback;
    }

    public final DynamicIslandSafeguardsController getDynamicIslandSafeguardsController() {
        return this.dynamicIslandSafeguardsController;
    }

    public final I getFreeformAnimRegion() {
        return this.freeformAnimRegion;
    }

    public final String getFullScreenPkg() {
        return this.fullScreenPkg;
    }

    public final BinderC0222a getHyperDropInfoNotifierService() {
        return this.hyperDropInfoNotifierService;
    }

    public final HashMap<String, Boolean> getInSmallWindowMap() {
        return this.inSmallWindowMap;
    }

    public final g1.E getIoScope() {
        return this.ioScope;
    }

    public final float getIslandMaxWidth() {
        return ((Number) this.sizeRepository.getIslandMaxWidth().getValue()).floatValue();
    }

    public final IslandTemplate getIslandTemplate(DynamicIslandData dynamicIslandData) {
        try {
            return (IslandTemplate) new U.d().j(dynamicIslandData != null ? dynamicIslandData.getTickerData() : null, IslandTemplate.class);
        } catch (Exception e2) {
            Log.e(TAG, "getIslandTemplate: " + e2);
            return null;
        }
    }

    public final int getLastDisplayOrientation() {
        return this.lastDisplayOrientation;
    }

    public final String getLastTopActivityPkg() {
        return this.lastTopActivityPkg;
    }

    public final E0.a getLottieProgressManager() {
        return this.lottieProgressManager;
    }

    public final String getTopActivityPkg() {
        return this.topActivityPkg;
    }

    public final DelayableExecutor getUiExecutor() {
        return this.uiExecutor;
    }

    public final g1.E getUiScope() {
        return this.uiScope;
    }

    public final DynamicIslandWindowState getWindowState() {
        return this.windowState;
    }

    public final u get_isAppAnimRunning() {
        return this._isAppAnimRunning;
    }

    public final u get_isFreeformAnimRunning() {
        return this._isFreeformAnimRunning;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public Bundle handleDynamicIsland(Bundle bundle) throws PendingIntent.CanceledException {
        int[] intArray;
        Boolean boolValueOf;
        Boolean boolValueOf2;
        List<DynamicIslandContentView> listRequestHasIsland;
        boolean z2;
        kotlin.jvm.internal.n.g(bundle, "bundle");
        String string = bundle.getString(DynamicIslandConstants.ACTION_KEY);
        Log.d(TAG, "handleDynamicIsland: " + string);
        Bundle bundle2 = new Bundle();
        if (string != null) {
            switch (string.hashCode()) {
                case -2079074337:
                    if (string.equals(DynamicIslandConstants.ACTION_NOTIFICATION_PANEL_APPEARANCE)) {
                        boolean z3 = bundle.getBoolean(DynamicIslandConstants.EXTRA_NOTIFICATION_PANEL_APPEARANCE);
                        notificationAppearance(z3);
                        getExternalStateRepository().notifyNotificationAppearanceChanged(z3);
                        Log.d(TAG, "handleDynamicIsland: notif appearance " + z3);
                    }
                    break;
                case -2078329320:
                    if (string.equals(DynamicIslandConstants.ACTION_AVOID_SCREEN_BURN_IN_PARAMS_UPDATE)) {
                        ((AvoidScreenBurnInHelper) this.avoidScreenBurnInHelper.get()).updateTranParams(bundle.getInt(DynamicIslandConstants.EXTRA_AVOID_SCREEN_BURN_IN_INTERVAL), bundle.getInt(DynamicIslandConstants.EXTRA_AVOID_SCREEN_BURN_IN_STEP));
                    }
                    break;
                case -1980753131:
                    if (string.equals(DynamicIslandConstants.ACTION_KEYGUARD_SHOWING)) {
                        boolean z4 = bundle.getBoolean(DynamicIslandConstants.EXTRA_KEYGUARD_SHOWING);
                        getExternalStateRepository().notifyKeyguardShowingChanged(z4);
                        Log.d(TAG, "isKeyguardShowing: " + z4);
                    }
                    break;
                case -1174102325:
                    if (string.equals(DynamicIslandConstants.ACTION_SET_STATUS_BAR_VIEW_VISIBLE)) {
                        boolean z5 = bundle.getBoolean(DynamicIslandConstants.EXTRA_STATUS_BAR_VIEW_VISIBLE, true);
                        if (!z5) {
                            this.windowState.setTempHiddenType(DynamicIslandWindowState.TempHiddenType.STATUS_BAR_DISAPPEARANCE);
                        }
                        this.windowState.getStatusBarViewShowing().setValue(Boolean.valueOf(z5));
                        Log.d(TAG, "statusBar view visible: " + z5);
                    }
                    break;
                case -1080314869:
                    if (string.equals(DynamicIslandConstants.ACTION_HEADS_UP_HEIGHT_CHANGED)) {
                        String string2 = bundle.getString(DynamicIslandConstants.EXTRA_HEADS_UP_ENTRY);
                        bundle.getBoolean(DynamicIslandConstants.EXTRA_HAS_HEADS_UP);
                        int i2 = bundle.getInt(DynamicIslandConstants.EXTRA_HEADSUP_HEIGHT);
                        Log.d(DynamicIslandConstants.TAG_DEBUG_TOUCH, "entry: " + string2 + ", headsUpHeight: " + i2);
                        getView().updateHeadsUpZone(i2);
                    }
                    break;
                case -819194794:
                    if (string.equals(DynamicIslandConstants.ACTION_SET_STATUS_BAR_WINDOW_VISIBLE)) {
                        statusBarVisible(bundle);
                    }
                    break;
                case -774194398:
                    if (string.equals(DynamicIslandConstants.ACTION_DEVICE_INTERACTIVE)) {
                        boolean z6 = bundle.getBoolean(DynamicIslandConstants.EXTRA_IS_DEVICE_INTERACTIVE);
                        getExternalStateRepository().notifyDeviceInteractive(z6);
                        Log.d(TAG, "isDeviceInteractive: " + z6);
                    }
                    break;
                case -761916606:
                    if (string.equals(DynamicIslandConstants.ACTION_COLLAPSE_EXPANDED)) {
                        getView().collapse("action collapse");
                    }
                    break;
                case -700004145:
                    if (string.equals(DynamicIslandConstants.ACTION_EXPAND_STATE_MONITORED_TOUCH) && (intArray = bundle.getIntArray(DynamicIslandConstants.EXTRA_EXPAND_STATE_MONITORED_TOUCH)) != null) {
                        int i3 = intArray[0];
                        int i4 = intArray[1];
                        if (OneHandModeUtils.INSTANCE.isOneHandMode()) {
                            getView().maybeCollapseExpand(i3, (int) (((double) i4) - (((double) DynamicIslandUtils.INSTANCE.getScreenHeightOld(getContext())) * ONE_HEAD_MODE_PRE)));
                        } else {
                            getView().maybeCollapseExpand(i3, i4);
                        }
                    }
                    break;
                case -396620393:
                    if (string.equals(DynamicIslandConstants.ACTION_TOP_ACTIVITY_CHANGED)) {
                        this.isPendingTopChanged = false;
                        ComponentName componentName = (ComponentName) bundle.getParcelable(DynamicIslandConstants.EXTRA_TOP_ACTIVITY_COMPONENT_NAME, ComponentName.class);
                        boolean z7 = bundle.getBoolean(DynamicIslandConstants.EXTRA_TOP_ACTIVITY_IN_SMALL_WINDOW);
                        boolean z8 = bundle.getBoolean(DynamicIslandConstants.EXTRA_TOP_ACTIVITY_IS_FOCUS);
                        boolean z9 = bundle.getBoolean(DynamicIslandConstants.EXTRA_TOP_ACTIVITY_IS_SUPPORT_PIP);
                        String string3 = bundle.getString(DynamicIslandConstants.EXTRA_LAST_FULL_SCREEN_ACTIVITY_PKG, "");
                        kotlin.jvm.internal.n.f(string3, "getString(...)");
                        this.fullScreenPkg = string3;
                        DynamicIslandEventCoordinator eventCoordinator = getView().getEventCoordinator();
                        if (eventCoordinator != null) {
                            eventCoordinator.setLastFullScreenActivityPkg(this.fullScreenPkg);
                        }
                        onTopActivityChange$default(this, componentName, z7, z9, z8, false, 16, null);
                    }
                    break;
                case -307225225:
                    if (string.equals(DynamicIslandConstants.ACTION_LOCK_SCREEN)) {
                        boolean z10 = bundle.getBoolean(DynamicIslandConstants.EXTRA_LOCK_SCREEN);
                        Log.d(TAG, "lockScreen: " + z10);
                        lockScreen(z10);
                    }
                    break;
                case -268543001:
                    if (string.equals(DynamicIslandConstants.ACTION_REMOVE_ISLAND)) {
                        String string4 = bundle.getString(DynamicIslandConstants.EXTRA_ISLAND_DELETED_KEY);
                        boolean z11 = bundle.getBoolean(DynamicIslandConstants.EXTRA_ISLAND_DELETED_HAS_ANIMATION);
                        Log.d(TAG, "removeIsland: " + string4 + ", hasAnimation: " + z11);
                        if (string4 != null) {
                            removeDynamicIslandView(string4, !z11);
                        }
                    }
                    break;
                case -167388683:
                    if (string.equals(DynamicIslandConstants.ACTION_AVOID_SCREEN_BURN_IN_TRAN)) {
                        ((AvoidScreenBurnInHelper) this.avoidScreenBurnInHelper.get()).updateScreenHelperInfo(bundle.getInt(DynamicIslandConstants.EXTRA_AVOID_SCREEN_BURN_IN_DIR), bundle.getBoolean(DynamicIslandConstants.EXTRA_AVOID_SCREEN_BURN_IN_STATUS));
                    }
                    break;
                case -151023397:
                    if (string.equals(DynamicIslandConstants.ACTION_KEYGUARD_OCCLUDED)) {
                        boolean z12 = bundle.getBoolean(DynamicIslandConstants.EXTRA_KEYGUARD_OCCLUDED);
                        getExternalStateRepository().notifyKeyguardOccluded(z12);
                        Log.d(TAG, "isKeyguardOccluded: " + z12);
                    }
                    break;
                case -91961204:
                    if (string.equals(DynamicIslandConstants.ACTION_NOTIFICATION_PANEL_VISIBLE)) {
                        boolean z13 = bundle.getBoolean(DynamicIslandConstants.EXTRA_NOTIFICATION_PANEL_VISIBLE);
                        notificationVisible(z13);
                        getExternalStateRepository().notifyNotificationVisibleChanged(z13);
                        Log.d(TAG, "handleDynamicIsland: notif visible " + z13);
                    }
                    break;
                case 143528042:
                    if (string.equals(DynamicIslandConstants.ACTION_SET_ISLAND_MAX_WIDTH)) {
                        float f2 = bundle.getFloat(DynamicIslandConstants.EXTRA_ISLAND_MAX_WIDTH);
                        float f3 = bundle.getFloat(DynamicIslandConstants.EXTRA_ISLAND_CLOCK_WIDTH, COMPATIBILITY_VALUE);
                        float f4 = bundle.getFloat(DynamicIslandConstants.EXTRA_ISLAND_BATTERY_WIDTH, COMPATIBILITY_VALUE);
                        if (getIslandMaxWidth() == f2 && getClockWidth() == f3 && getBatteryWidth() == f4) {
                            return null;
                        }
                        this.sizeRepository.updateIslandMaxWidth(f2, f3, f4);
                        Iterator<T> it = getView().getContentViewList().iterator();
                        while (it.hasNext()) {
                            DynamicIslandData currentIslandData = ((DynamicIslandContentView) it.next()).getCurrentIslandData();
                            if (currentIslandData != null) {
                                getView().updateDynamicIslandView(currentIslandData, false, f2);
                            }
                        }
                        if (!this.pendingData.isEmpty() && f2 > 0.0f) {
                            HashMap map = new HashMap(this.pendingData);
                            this.pendingData.clear();
                            for (Map.Entry entry : map.entrySet()) {
                                Object key = entry.getKey();
                                kotlin.jvm.internal.n.f(key, "<get-key>(...)");
                                Object value = entry.getValue();
                                kotlin.jvm.internal.n.f(value, "<get-value>(...)");
                                addDynamicIslandView((DynamicIslandData) key, ((Boolean) value).booleanValue());
                            }
                        }
                        Log.d(TAG, "handleDynamicIsland: islandMaxWidth " + f2);
                    }
                    break;
                case 348121011:
                    if (string.equals(DynamicIslandConstants.ACTION_REQUEST_HAS_ISLAND)) {
                        String string5 = bundle.getString(DynamicIslandConstants.EXTRA_PKG);
                        if (string5 == null || (listRequestHasIsland = getView().requestHasIsland(string5)) == null) {
                            boolValueOf = null;
                        } else if (listRequestHasIsland.isEmpty()) {
                            z2 = false;
                            boolValueOf = Boolean.valueOf(z2);
                        } else {
                            for (DynamicIslandContentView dynamicIslandContentView : listRequestHasIsland) {
                                if (!((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.SubAppExpanded)) {
                                    if (!((dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null) instanceof DynamicIslandState.SubMiniWindowExpanded)) {
                                        z2 = true;
                                        boolValueOf = Boolean.valueOf(z2);
                                    }
                                }
                            }
                            z2 = false;
                            boolValueOf = Boolean.valueOf(z2);
                        }
                        boolean zIsAdaptDesktopAnimation = this.windowState.isAdaptDesktopAnimation(getContext());
                        boolean z14 = getView().getMainAppExpanded() != null;
                        Boolean boolValueOf3 = string5 != null ? Boolean.valueOf(getView().getMainMiniWindow(string5) != null) : null;
                        if (boolValueOf != null) {
                            if (zIsAdaptDesktopAnimation && kotlin.jvm.internal.n.c(boolValueOf3, Boolean.TRUE) && z14) {
                                zBooleanValue = boolValueOf.booleanValue();
                            } else if (boolValueOf.booleanValue() && (!zIsAdaptDesktopAnimation || !z14)) {
                                zBooleanValue = true;
                            }
                            boolValueOf2 = Boolean.valueOf(zBooleanValue);
                        } else {
                            boolValueOf2 = null;
                        }
                        Log.d(TAG, "handleDynamicIsland: hasIsland " + boolValueOf + " " + zIsAdaptDesktopAnimation + " " + z14 + " hasIslandResult: " + boolValueOf2);
                        bundle2.putBoolean(DynamicIslandConstants.EXTRA_HAS_ISLAND, kotlin.jvm.internal.n.c(boolValueOf2, Boolean.TRUE));
                    }
                    break;
                case 403534581:
                    if (string.equals(DynamicIslandConstants.ACTION_FULL_SCREEN_UPDATE)) {
                        DynamicIslandEventCoordinator eventCoordinator2 = getView().getEventCoordinator();
                        String string6 = bundle.getString(DynamicIslandConstants.EXTRA_LAST_FULL_SCREEN_ACTIVITY_PKG, eventCoordinator2 != null ? eventCoordinator2.getLastFullScreenActivityPkg() : null);
                        DynamicIslandEventCoordinator eventCoordinator3 = getView().getEventCoordinator();
                        if (eventCoordinator3 != null) {
                            kotlin.jvm.internal.n.d(string6);
                            eventCoordinator3.setLastFullScreenActivityPkg(string6);
                        }
                    }
                    break;
                case 468393469:
                    if (string.equals(DynamicIslandConstants.ACTION_ENTER_MODAL)) {
                        ((LottieProgressManager) this.lottieProgressManager.get()).focusModalShift(bundle.getString(DynamicIslandConstants.EXTRA_MODAL_FOCUS_SHIFT_KEY), bundle.getBoolean(DynamicIslandConstants.EXTRA_ENTER_MODAL, false));
                    }
                    break;
                case 578578045:
                    if (string.equals(DynamicIslandConstants.ACTION_COMMAND_QUEUE_DISABLE)) {
                        int i5 = bundle.getInt(DynamicIslandConstants.EXTRA_COMMAND_QUEUE_DISABLE_1);
                        int i6 = bundle.getInt(DynamicIslandConstants.EXTRA_COMMAND_QUEUE_DISABLE_2);
                        commandQueueDisable(i5);
                        getExternalStateRepository().notifyCommandQueueDisableChanged(new H0.i(Integer.valueOf(i5), Integer.valueOf(i6)));
                    }
                    break;
                case 793172543:
                    if (string.equals(DynamicIslandConstants.ACTION_PENDING_TOP_ACTIVITY_CHANGED)) {
                        String string7 = bundle.getString(DynamicIslandConstants.EXTRA_PENDING_PKG, "");
                        boolean z15 = bundle.getBoolean(DynamicIslandConstants.EXTRA_PENDING_FREE_FORM);
                        boolean z16 = bundle.getBoolean(DynamicIslandConstants.EXTRA_PENDING_PIP);
                        boolean z17 = bundle.getBoolean(DynamicIslandConstants.EXTRA_IS_PENDING_TASK, true);
                        String string8 = bundle.getString(DynamicIslandConstants.EXTRA_LAST_FULL_SCREEN_ACTIVITY_PKG, "");
                        kotlin.jvm.internal.n.f(string8, "getString(...)");
                        this.fullScreenPkg = string8;
                        DynamicIslandEventCoordinator eventCoordinator4 = getView().getEventCoordinator();
                        if (eventCoordinator4 != null) {
                            eventCoordinator4.setLastFullScreenActivityPkg(this.fullScreenPkg);
                        }
                        DynamicIslandWindowView view = getView();
                        kotlin.jvm.internal.n.d(string7);
                        List<DynamicIslandContentView> listRequestHasIsland2 = view.requestHasIsland(string7);
                        boolean z18 = TextUtils.isEmpty(string7) || kotlin.jvm.internal.n.c(string7, this.topActivityPkg) || (z17 && (listRequestHasIsland2 == null || listRequestHasIsland2.isEmpty()));
                        Log.d(TAG, "handleDynamicIsland: top = " + this.topActivityPkg + " pending = " + string7 + " hasIsland = " + listRequestHasIsland2);
                        if (z18) {
                            string7 = null;
                        }
                        if (string7 != null) {
                            ComponentName componentName2 = new ComponentName(string7, "");
                            if (z17) {
                                onTopActivityChange(componentName2, z15, z16, true, true);
                                this.isPendingTopChanged = true;
                            } else if (!z17 && this.isPendingTopChanged) {
                                onTopActivityChange(componentName2, z15, z16, true, false);
                                this.isPendingTopChanged = false;
                            }
                        } else {
                            this.isPendingTopChanged = false;
                        }
                    }
                    break;
                case 1043971575:
                    if (string.equals(DynamicIslandConstants.ACTION_EXTERNAL_INTERCEPT)) {
                        MotionEvent motionEvent = (MotionEvent) bundle.getParcelable(DynamicIslandConstants.EXTRA_MOTION_EVENT, MotionEvent.class);
                        if (motionEvent == null) {
                            throw new IllegalStateException("handling null motion event.");
                        }
                        String string9 = bundle.getString(DynamicIslandConstants.EXTRA_MOTION_EVENT_SOURCE);
                        if (string9 == null) {
                            throw new IllegalStateException("handling null motion event source.");
                        }
                        bundle2.putInt(DynamicIslandConstants.EXTRA_MOTION_EVENT_RESULT, TouchEvent.Companion.getToInt(getExternalTouchHandler().handleExternalIntercept(motionEvent, string9)));
                    }
                    break;
                case 1194338499:
                    if (string.equals(DynamicIslandConstants.ACTION_KEYGUARD_GOING_AWAY)) {
                        boolean z19 = bundle.getBoolean(DynamicIslandConstants.EXTRA_KEYGUARD_GOING_AWAY);
                        getExternalStateRepository().notifyKeyguardGoingAway(z19);
                        Log.d(TAG, "isKeyguardGoingAway: " + z19);
                    }
                    break;
                case 1372849839:
                    if (string.equals(DynamicIslandConstants.ACTION_SET_CUTOUT_Y)) {
                        float tinyScreenInsetTop = bundle.getFloat(DynamicIslandConstants.EXTRA_CUTOUT_Y);
                        if (FlipUtils.isFlip()) {
                            tinyScreenInsetTop += DynamicIslandUtils.INSTANCE.getTinyScreenInsetTop();
                        }
                        this.sizeRepository.updateCutoutY(tinyScreenInsetTop);
                    }
                    break;
                case 1648189172:
                    if (string.equals(DynamicIslandConstants.ACTION_EXTERNAL_TOUCH)) {
                        MotionEvent motionEvent2 = (MotionEvent) bundle.getParcelable(DynamicIslandConstants.EXTRA_MOTION_EVENT, MotionEvent.class);
                        if (motionEvent2 == null) {
                            throw new IllegalStateException("handling null motion event.");
                        }
                        String string10 = bundle.getString(DynamicIslandConstants.EXTRA_MOTION_EVENT_SOURCE);
                        if (string10 == null) {
                            throw new IllegalStateException("handling null motion event source.");
                        }
                        bundle2.putInt(DynamicIslandConstants.EXTRA_MOTION_EVENT_RESULT, TouchEvent.Companion.getToInt(getExternalTouchHandler().handleExternalTouch(motionEvent2, string10)));
                    }
                    break;
                case 1708099977:
                    if (string.equals(DynamicIslandConstants.ACTION_UPDATE_LIGHT)) {
                        boolean z20 = bundle.getBoolean(DynamicIslandConstants.EXTRA_IS_LIGHT);
                        Log.d(TAG, "handleDynamicIsland: isLight " + z20);
                        getView().updateDarkLightMode(z20);
                    }
                    break;
                case 1786126183:
                    if (string.equals(DynamicIslandConstants.ACTION_BOUNCER_SHOWING)) {
                        boolean z21 = bundle.getBoolean(DynamicIslandConstants.EXTRA_BOUNCER_SHOWING);
                        this.windowState.setTempHiddenType(DynamicIslandWindowState.TempHiddenType.BOUNCER_SHOWING);
                        this.windowState.getBouncerShowing().setValue(Boolean.valueOf(z21));
                        getExternalStateRepository().notifyBouncerIsOrWillBeShowing(z21);
                        Log.d(TAG, "bouncerIsOrWillBeShowing: " + z21);
                    }
                    break;
                case 2025487220:
                    if (string.equals(DynamicIslandConstants.ACTION_REQUEST_NEED_RETURN_ISLAND)) {
                        boolean zIsFoldScreenLayoutLarge = FoldUtils.INSTANCE.isFoldScreenLayoutLarge(getView());
                        CommonUtils commonUtils = CommonUtils.INSTANCE;
                        Context context = getView().getContext();
                        kotlin.jvm.internal.n.f(context, "getContext(...)");
                        bundle2.putBoolean(DynamicIslandConstants.EXTRA_NEED_RETURN_ISLAND, commonUtils.getInVerticalMode(context) || zIsFoldScreenLayoutLarge);
                    }
                    break;
            }
        }
        return bundle2;
    }

    public final I isAppAnimRunning() {
        return this.isAppAnimRunning;
    }

    public final I isFreeformAnimRunning() {
        return this.isFreeformAnimRunning;
    }

    public final boolean isInMiniWindow(String str, Integer num) {
        if (str == null || num == null) {
            return false;
        }
        return this.dynamicIslandDesktopStateController.getMiniWindowDataRepo().isInMiniWindow(str, num.intValue());
    }

    public final boolean isNotSupportFreeFormAnim() {
        return DynamicIslandAnimUtils.INSTANCE.featureDynamicIslandNoElementButFreeform() || !this.isSupportFreeformAnim;
    }

    @Override // miui.systemui.autodensity.AutoDensityController.OnDensityChangeListener
    public void onConfigChanged(Configuration config) {
        kotlin.jvm.internal.n.g(config, "config");
        onConfigurationChanged(getResources().getConfiguration());
    }

    public void removeDynamicIslandGlowEffectLayer() {
        this.dynamicIslandGlowEffectLayer = null;
    }

    public void removeDynamicIslandView(String key, boolean z2) {
        Bundle extras;
        kotlin.jvm.internal.n.g(key, "key");
        Log.d(TAG, "removeDynamicIslandView " + z2 + " key:" + key);
        DynamicIslandData dynamicIslandDataRemove = this.islandData.remove(key);
        DynamicIslandWindowView.removeDynamicIslandData$default(getView(), key, (dynamicIslandDataRemove == null || (extras = dynamicIslandDataRemove.getExtras()) == null) ? null : extras.getString("miui.pkg.name"), false, z2, 4, null);
        ((LottieProgressManager) this.lottieProgressManager.get()).removeIslandLottie(key);
        this.appCloseRect.remove(key);
        this.pinOperateList.remove(key);
    }

    public void removeOnDynamicIslandViewChangedListener(DynamicIslandContent.DynamicIslandViewChangedListener listener) {
        kotlin.jvm.internal.n.g(listener, "listener");
    }

    public Bundle requestDynamicIslandPosition(String key) {
        kotlin.jvm.internal.n.g(key, "key");
        Log.d(TAG, "requestDynamicIslandPosition key = " + key);
        Bundle bundle = new Bundle();
        bundle.putParcelable("position", new Rect(0, 0, 100, 100));
        return bundle;
    }

    public final void resetWindowAnimationState() {
        u uVar = this._isFreeformAnimRunning;
        Boolean bool = Boolean.FALSE;
        uVar.setValue(bool);
        this._isAppAnimRunning.setValue(bool);
        setPositionValue$default(this, false, false, 2, null);
        getView().setAnimRunning(false, false);
        getView().enterMiniWindowEnd();
    }

    public final void runDesktopAnim(boolean z2) {
        this.dynamicIslandDesktopStateController.runDesktopAnim(z2);
    }

    public final void screenPinningActive(boolean z2) {
        this.windowState.setTempHiddenType(DynamicIslandWindowState.TempHiddenType.SCREEN_PINNING_ACTIVE);
        this.windowState.getScreenPinning().setValue(Boolean.valueOf(z2));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0058, code lost:
    
        if (r5.equals("request_close_position") == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0061, code lost:
    
        if (r5.equals("freeform_final_position") != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0068, code lost:
    
        if (r5.equals("close_app_no_anim") == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006f, code lost:
    
        if (r5.equals("close_app_start") == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x007a, code lost:
    
        return sendWindowAnimEventForMiddle(r5, r6, r7, r8);
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.os.Bundle sendWindowAnimEvent(java.lang.String r5, boolean r6, boolean r7, android.os.Bundle r8) {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.sendWindowAnimEvent(java.lang.String, boolean, boolean, android.os.Bundle):android.os.Bundle");
    }

    public final void setAppAnimSmallX(int i2) {
        this.appAnimSmallX = i2;
    }

    public final void setAppCloseKey(String str) {
        this.appCloseKey = str;
    }

    public final void setAppCloseRect(HashMap<String, Rect> map) {
        kotlin.jvm.internal.n.g(map, "<set-?>");
        this.appCloseRect = map;
    }

    public final void setCurrentHeight(int i2) {
        int i3 = this.currentHeight;
        if (i2 != i3) {
            Log.e(TAG, "currentHeight: " + i2 + " " + i3);
            this.currentHeight = i2;
        }
    }

    public final void setDisplayOrientation(int i2) {
        this.displayOrientation = i2;
    }

    public final void setDynamicIslandCallback(DynamicIslandCallback dynamicIslandCallback) {
        this.dynamicIslandCallback = dynamicIslandCallback;
    }

    public final void setDynamicIslandSafeguardsController(DynamicIslandSafeguardsController dynamicIslandSafeguardsController) {
        kotlin.jvm.internal.n.g(dynamicIslandSafeguardsController, "<set-?>");
        this.dynamicIslandSafeguardsController = dynamicIslandSafeguardsController;
    }

    public final void setFullScreenPkg(String str) {
        kotlin.jvm.internal.n.g(str, "<set-?>");
        this.fullScreenPkg = str;
    }

    public void setGlowEffectRect(Rect glowRect) {
        kotlin.jvm.internal.n.g(glowRect, "glowRect");
    }

    public final void setHyperDropInfoNotifierService(BinderC0222a binderC0222a) {
        kotlin.jvm.internal.n.g(binderC0222a, "<set-?>");
        this.hyperDropInfoNotifierService = binderC0222a;
    }

    public final void setInSmallWindowMap(HashMap<String, Boolean> map) {
        kotlin.jvm.internal.n.g(map, "<set-?>");
        this.inSmallWindowMap = map;
    }

    public final void setLastDisplayOrientation(int i2) {
        this.lastDisplayOrientation = i2;
    }

    public final void setLastTopActivityPkg(String str) {
        this.lastTopActivityPkg = str;
    }

    public final void setLottieProgressManager(E0.a aVar) {
        kotlin.jvm.internal.n.g(aVar, "<set-?>");
        this.lottieProgressManager = aVar;
    }

    public final void setTopActivityPkg(String str) {
        this.topActivityPkg = str;
    }

    public final void setUiExecutor(DelayableExecutor delayableExecutor) {
        kotlin.jvm.internal.n.g(delayableExecutor, "<set-?>");
        this.uiExecutor = delayableExecutor;
    }

    public final void setWindowState(DynamicIslandWindowState dynamicIslandWindowState) {
        kotlin.jvm.internal.n.g(dynamicIslandWindowState, "<set-?>");
        this.windowState = dynamicIslandWindowState;
    }

    @Override // miui.systemui.dynamicisland.DynamicIslandStartable
    public void start() {
        init();
    }

    public final void updateAppRect(DynamicIslandContentView dynamicIslandContentView, boolean z2) {
        DynamicIslandData currentIslandData;
        DynamicIslandData currentIslandData2;
        DynamicIslandData currentIslandData3;
        DynamicIslandData currentIslandData4;
        DynamicIslandState dynamicIslandStateCalculateCollapse = getView().calculateCollapse(dynamicIslandContentView);
        String key = null;
        Rect appCloseRealIslandRect = getAppCloseRealIslandRect(dynamicIslandStateCalculateCollapse, dynamicIslandContentView, dynamicIslandContentView != null ? dynamicIslandContentView.getPkgName() : null, z2);
        DynamicIslandState state = dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null;
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "oldState = " + dynamicIslandContentView + " " + state + ", newState: " + dynamicIslandStateCalculateCollapse + ", currentRect = " + appCloseRealIslandRect + ", appCloseRect = " + this.appCloseRect);
        if (kotlin.jvm.internal.n.c(this.appCloseKey, (dynamicIslandContentView == null || (currentIslandData4 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData4.getKey())) {
            if (appCloseRealIslandRect != null) {
                if (appCloseRealIslandRect.equals(this.appCloseRect.get((dynamicIslandContentView == null || (currentIslandData3 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData3.getKey()))) {
                    return;
                }
            }
            getView().updateAppCloseRect(appCloseRealIslandRect, dynamicIslandContentView);
            if (appCloseRealIslandRect != null) {
                this.appCloseKey = (dynamicIslandContentView == null || (currentIslandData2 = dynamicIslandContentView.getCurrentIslandData()) == null) ? null : currentIslandData2.getKey();
                HashMap<String, Rect> map = this.appCloseRect;
                if (dynamicIslandContentView != null && (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) != null) {
                    key = currentIslandData.getKey();
                }
                map.put(key, appCloseRealIslandRect);
            }
        }
    }

    public final void updateChronometersIn(DynamicIslandBaseContentView dynamicIslandBaseContentView, DynamicIslandBaseContentView dynamicIslandBaseContentView2, DynamicIslandData data) {
        kotlin.jvm.internal.n.g(data, "data");
        if (dynamicIslandBaseContentView != null) {
            String key = data.getKey();
            int i2 = R.id.island_chronometer;
            Bundle extras = data.getExtras();
            int i3 = extras != null ? extras.getInt("timerType", 0) : 0;
            boolean zNeedUpdateIslandTimeInfo = getNotificationChronometerManager().needUpdateIslandTimeInfo(key);
            if (i3 == 0) {
                if (zNeedUpdateIslandTimeInfo) {
                    getNotificationChronometerManager().removeTimeKeeper(key);
                    return;
                }
                return;
            }
            getNotificationChronometerManager().addDynamicIslandChronometer(dynamicIslandBaseContentView.getBigIslandView(), dynamicIslandBaseContentView2 != null ? dynamicIslandBaseContentView2.getFakeBigIsland() : null, key, i2);
            if (zNeedUpdateIslandTimeInfo) {
                NotificationChronometerManager notificationChronometerManager = getNotificationChronometerManager();
                Bundle extras2 = data.getExtras();
                Long lValueOf = extras2 != null ? Long.valueOf(extras2.getLong("timerWhen")) : null;
                Bundle extras3 = data.getExtras();
                long j2 = extras3 != null ? extras3.getLong("timerTotal", 0L) : 0L;
                Bundle extras4 = data.getExtras();
                notificationChronometerManager.updateTimeKeeperForIsland(key, i3, lValueOf, j2, extras4 != null ? Long.valueOf(extras4.getLong("timerSystemCurrent")) : null);
            }
        }
    }

    public void updateDynamicIslandView(DynamicIslandData dynamicIslandData, boolean z2) {
        kotlin.jvm.internal.n.g(dynamicIslandData, "dynamicIslandData");
        Log.d(TAG, "updateDynamicIslandView, key:" + dynamicIslandData.getKey() + ", expanded:" + z2 + " ");
        Bundle extras = dynamicIslandData.getExtras();
        if (extras != null) {
            extras.putString(DynamicIslandConstants.EXTRA_MIUI_KEY, dynamicIslandData.getKey());
        }
        if (requestMaxWidth(dynamicIslandData, z2)) {
            return;
        }
        IslandTemplate islandTemplate = getIslandTemplate(dynamicIslandData);
        Log.e(TAG, "updateDynamicIslandView: dismissIsland:" + (islandTemplate != null ? Boolean.valueOf(islandTemplate.getDismissIsland()) : null));
        if (islandTemplate == null) {
            String key = dynamicIslandData.getKey();
            if (key != null) {
                removeDynamicIslandView(key);
                return;
            }
            return;
        }
        String key2 = dynamicIslandData.getKey();
        if (key2 != null) {
            this.islandData.put(key2, dynamicIslandData);
        }
        if (islandTemplate.getDismissIsland()) {
            String key3 = dynamicIslandData.getKey();
            if (key3 != null) {
                removeDynamicIslandView(key3);
                return;
            }
            return;
        }
        Integer priority = dynamicIslandData.getPriority();
        int iIntValue = priority != null ? priority.intValue() : 1;
        Integer properties = dynamicIslandData.getProperties();
        int iIntValue2 = properties != null ? properties.intValue() : 1;
        Integer islandPriority = islandTemplate.getIslandPriority();
        if (islandPriority == null) {
            islandPriority = Integer.valueOf(iIntValue);
        }
        dynamicIslandData.setPriority(islandPriority);
        Integer islandProperty = islandTemplate.getIslandProperty();
        if (islandProperty == null) {
            islandProperty = Integer.valueOf(iIntValue2);
        }
        dynamicIslandData.setProperties(islandProperty);
        if (checkFlipTiny(dynamicIslandData.getProperties())) {
            Log.d(TAG, "updateDynamicIslandView: skip in flip tiny");
            return;
        }
        setCancelTimeout(dynamicIslandData, islandTemplate);
        getView().updateDynamicIslandView(dynamicIslandData, z2, getIslandMaxWidth());
        if (z2) {
            this.dynamicIslandSafeguardsController.cancelDelayCollapsed();
            if (islandTemplate.getExpandedTime() != 0) {
                this.dynamicIslandSafeguardsController.delayCollapsed(islandTemplate.getExpandedTime());
            } else {
                this.dynamicIslandSafeguardsController.delayCollapsed(5L);
            }
        }
    }

    public final void updateWindowState() {
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        dynamicIslandUtils.updateBoundingRect(getContext());
        dynamicIslandUtils.updateFlipOutInsetRight(getContext());
        DynamicIslandWindowState dynamicIslandWindowState = this.windowState;
        dynamicIslandWindowState.setLastTinyScreenStatus(dynamicIslandWindowState.isTinyScreen());
        this.windowState.setTinyScreen(CommonUtils.isTinyScreen(getContext()));
        this.lastDisplayOrientation = this.displayOrientation;
        Display display = getContext().getDisplay();
        if (display != null) {
            this.displayOrientation = display.getRotation();
        }
        updateFlipResources();
    }

    public final boolean windowAnimating() {
        return ((Boolean) this._isFreeformAnimRunning.getValue()).booleanValue() || ((Boolean) this._isAppAnimRunning.getValue()).booleanValue();
    }

    public void removeDynamicIslandView(String key) {
        kotlin.jvm.internal.n.g(key, "key");
        Log.d(TAG, "removeDynamicIslandView key :" + key);
        removeDynamicIslandView(key, false);
    }
}
