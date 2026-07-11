package miui.systemui.dynamicisland.window;

import H0.s;
import android.app.PendingIntent;
import android.os.Bundle;
import android.util.Log;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import g1.AbstractC0369g;
import g1.E;
import j1.I;
import j1.InterfaceC0419g;
import j1.u;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.D;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dynamicisland.DynamicIslandBackgroundView;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.WindowAnimState;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;
import miui.systemui.util.ConvenienceExtensionsKt;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandSafeguardsController implements DynamicIslandStartable {
    public static final Companion Companion = new Companion(null);
    private static final long DELAY_TIME = 150;
    private static final long DELAY_TIME_WHEN_WINDOW_ANIMATION = 1000;
    private static final String TAG = "DynamicIslandSafeguardsController";
    private String currentDelayEnterAppPkg;
    private String currentDelayEnterMiniWindowPkg;
    private String currentDelayExitAppPkg;
    private String currentDelayExitMiniWindowPkg;
    private String fullScreenPkg;
    private HashMap<String, Runnable> onDelayCancelMap;
    private Runnable onDelayCollapsed;
    private Runnable onDelayEnterApp;
    private Runnable onDelayEnterMiniWindow;
    private Runnable onDelayExitApp;
    private HashMap<String, Runnable> onDelayExitMiniWindowMap;
    private Runnable onResetWindowAnimationState;
    private final E scope;
    private DelayableExecutor uiExecutor;
    private final DynamicIslandWindowView windowView;
    private final H0.d windowViewController$delegate;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandSafeguardsController$start$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandSafeguardsController$start$1", f = "DynamicIslandSafeguardsController.kt", l = {68}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandSafeguardsController.this.new AnonymousClass1(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                I iIsFreeformAnimRunning = DynamicIslandSafeguardsController.this.getWindowViewController().isFreeformAnimRunning();
                final DynamicIslandSafeguardsController dynamicIslandSafeguardsController = DynamicIslandSafeguardsController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandSafeguardsController.start.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, L0.d dVar) {
                        Log.d(DynamicIslandSafeguardsController.TAG, "isFreeformAnimRunning " + z2);
                        if (z2) {
                            dynamicIslandSafeguardsController.delayResetWindowAnimation(5L);
                        } else {
                            dynamicIslandSafeguardsController.cancelDelayResetWindowAnimation();
                        }
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (iIsFreeformAnimRunning.collect(interfaceC0419g, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandSafeguardsController$start$2, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandSafeguardsController$start$2", f = "DynamicIslandSafeguardsController.kt", l = {78}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends N0.l implements Function2 {
        int label;

        public AnonymousClass2(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandSafeguardsController.this.new AnonymousClass2(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass2) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                I iIsAppAnimRunning = DynamicIslandSafeguardsController.this.getWindowViewController().isAppAnimRunning();
                final DynamicIslandSafeguardsController dynamicIslandSafeguardsController = DynamicIslandSafeguardsController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandSafeguardsController.start.2.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, L0.d dVar) {
                        Log.d(DynamicIslandSafeguardsController.TAG, "isAppAnimating " + z2);
                        if (z2) {
                            dynamicIslandSafeguardsController.delayResetWindowAnimation(5L);
                        } else {
                            dynamicIslandSafeguardsController.cancelDelayResetWindowAnimation();
                        }
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (iIsAppAnimRunning.collect(interfaceC0419g, this) == objC) {
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

    public DynamicIslandSafeguardsController(DynamicIslandWindowView windowView, @Main DelayableExecutor uiExecutor, @DynamicIsland E scope, E0.a windowViewControllerLazy) {
        kotlin.jvm.internal.n.g(windowView, "windowView");
        kotlin.jvm.internal.n.g(uiExecutor, "uiExecutor");
        kotlin.jvm.internal.n.g(scope, "scope");
        kotlin.jvm.internal.n.g(windowViewControllerLazy, "windowViewControllerLazy");
        this.windowView = windowView;
        this.uiExecutor = uiExecutor;
        this.scope = scope;
        this.windowViewController$delegate = ConvenienceExtensionsKt.getKotlinLazy(windowViewControllerLazy);
        this.onDelayExitMiniWindowMap = new HashMap<>();
        this.onDelayCancelMap = new HashMap<>();
        this.fullScreenPkg = "";
    }

    private final void cancelDelayDeleted(String str) {
        Log.d(TAG, "cancelDelayDeleted");
        Runnable runnable = this.onDelayCancelMap.get(str);
        if (runnable != null) {
            runnable.run();
        }
        D.d(this.onDelayCancelMap).remove(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void delayCollapsed$lambda$3(DynamicIslandSafeguardsController this$0) throws PendingIntent.CanceledException {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "onDelayCollapsed" + this$0.windowView.isUserExpanded());
        if (this$0.windowView.isUserExpanded()) {
            return;
        }
        this$0.windowView.collapse("delay");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void delayDeleted$lambda$1(String str, DynamicIslandSafeguardsController this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (str != null) {
            DynamicIslandWindowViewController.DynamicIslandCallback dynamicIslandCallback = this$0.getWindowViewController().getDynamicIslandCallback();
            if (dynamicIslandCallback != null) {
                dynamicIslandCallback.onDynamicIslandTimeoutRemoved(str);
            }
            this$0.getWindowViewController().removeDynamicIslandView(str);
        }
    }

    public static /* synthetic */ void delayEnterApp$default(DynamicIslandSafeguardsController dynamicIslandSafeguardsController, DynamicIslandContentView dynamicIslandContentView, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        dynamicIslandSafeguardsController.delayEnterApp(dynamicIslandContentView, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void delayEnterApp$lambda$18(String str, DynamicIslandSafeguardsController this$0) {
        DynamicIslandContentFakeView fakeView;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "onDelayEnterApp " + str + " " + this$0.currentDelayEnterAppPkg);
        if (this$0.getWindowViewController().windowAnimating()) {
            Log.d(TAG, "onDelayEnterApp windowAnimating");
            return;
        }
        String str2 = this$0.currentDelayEnterAppPkg;
        List<DynamicIslandContentView> listRequestHasIsland = str2 != null ? this$0.windowView.requestHasIsland(str2) : null;
        if (listRequestHasIsland != null) {
            for (DynamicIslandContentView dynamicIslandContentView : listRequestHasIsland) {
                if (dynamicIslandContentView != null && !(dynamicIslandContentView.getState() instanceof DynamicIslandState.AppExpanded)) {
                    if ((dynamicIslandContentView.getState() instanceof DynamicIslandState.MiniWindowExpanded) && (fakeView = dynamicIslandContentView.getFakeView()) != null) {
                        DynamicIslandContentFakeView.updateViewStateWhenCloseEnd$default(fakeView, true, null, 2, null);
                    }
                    dynamicIslandContentView.setWindowAnimState(new WindowAnimState.Opened(false, false, false));
                    Log.d(TAG, "onDelayEnterApp success");
                    this$0.windowView.appEnter(dynamicIslandContentView);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void delayEnterMiniWidow$lambda$21(String pkg, DynamicIslandSafeguardsController this$0) {
        DynamicIslandContentFakeView fakeView;
        kotlin.jvm.internal.n.g(pkg, "$pkg");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "onDelayEnterMiniWidow " + pkg + " " + this$0.currentDelayEnterMiniWindowPkg);
        if (this$0.getWindowViewController().windowAnimating()) {
            Log.d(TAG, "onDelayEnterMiniWidow windowAnimating");
            return;
        }
        String str = this$0.currentDelayEnterMiniWindowPkg;
        List<DynamicIslandContentView> listRequestHasIsland = str != null ? this$0.windowView.requestHasIsland(str) : null;
        if (listRequestHasIsland != null) {
            for (DynamicIslandContentView dynamicIslandContentView : listRequestHasIsland) {
                if (dynamicIslandContentView != null && !(dynamicIslandContentView.getWindowAnimState() instanceof WindowAnimState.Closing) && !(dynamicIslandContentView.getState() instanceof DynamicIslandState.MiniWindowExpanded)) {
                    if ((dynamicIslandContentView.getState() instanceof DynamicIslandState.AppExpanded) && (fakeView = dynamicIslandContentView.getFakeView()) != null) {
                        DynamicIslandContentFakeView.updateViewStateWhenCloseEnd$default(fakeView, false, null, 2, null);
                    }
                    Log.d(TAG, "onDelayEnterMiniWidow success animState = " + dynamicIslandContentView.getWindowAnimState());
                    dynamicIslandContentView.setWindowAnimState(new WindowAnimState.Opened(true, false, false));
                    this$0.windowView.enterMiniWindow(dynamicIslandContentView);
                }
            }
        }
    }

    public static /* synthetic */ void delayExitApp$default(DynamicIslandSafeguardsController dynamicIslandSafeguardsController, String str, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        dynamicIslandSafeguardsController.delayExitApp(str, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void delayExitApp$lambda$15(DynamicIslandSafeguardsController this$0, String str) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        ArrayList<DynamicIslandContentView> arrayList = new ArrayList();
        DynamicIslandContentView mainAppExpanded = this$0.windowView.getMainAppExpanded();
        DynamicIslandContentView dynamicIslandContentView = null;
        if (mainAppExpanded != null) {
            if (kotlin.jvm.internal.n.c(mainAppExpanded.getPkgName(), this$0.fullScreenPkg) || kotlin.jvm.internal.n.c(mainAppExpanded.getPkgName(), this$0.currentDelayExitAppPkg)) {
                mainAppExpanded = null;
            }
            if (mainAppExpanded != null) {
                arrayList.add(mainAppExpanded);
            }
        }
        DynamicIslandContentView subAppExpanded = this$0.windowView.getSubAppExpanded();
        if (subAppExpanded != null) {
            if (!kotlin.jvm.internal.n.c(subAppExpanded.getPkgName(), this$0.fullScreenPkg) && !kotlin.jvm.internal.n.c(subAppExpanded.getPkgName(), this$0.currentDelayExitAppPkg)) {
                dynamicIslandContentView = subAppExpanded;
            }
            if (dynamicIslandContentView != null) {
                arrayList.add(dynamicIslandContentView);
            }
        }
        Log.d(TAG, "delayExitApp: " + this$0.fullScreenPkg + " " + str + " count = " + arrayList.size());
        if (arrayList.isEmpty()) {
            return;
        }
        if (DynamicIslandAnimUtils.INSTANCE.featureDynamicIslandNoElement()) {
            this$0.getWindowViewController().calculateAppClosePosition(((DynamicIslandContentView) arrayList.get(0)).getPkgName(), false);
        }
        for (DynamicIslandContentView dynamicIslandContentView2 : arrayList) {
            dynamicIslandContentView2.setVisibility(0);
            DynamicIslandBackgroundView backgroundView = dynamicIslandContentView2.getBackgroundView();
            if (backgroundView != null) {
                backgroundView.setVisibility(0);
            }
            dynamicIslandContentView2.setWindowAnimState(new WindowAnimState.Closed(false, false, false));
            DynamicIslandWindowView.updateViewStateWhenCloseEnd$default(this$0.windowView, dynamicIslandContentView2, false, null, 4, null);
            this$0.windowView.updateIslandWindowAnimRunningState(false, dynamicIslandContentView2, false);
            this$0.windowView.appExit(dynamicIslandContentView2);
        }
        this$0.windowView.setAnimRunning(false, false);
        u uVar = this$0.getWindowViewController().get_isFreeformAnimRunning();
        Boolean bool = Boolean.FALSE;
        uVar.setValue(bool);
        this$0.getWindowViewController().get_isAppAnimRunning().setValue(bool);
    }

    public static /* synthetic */ void delayExitMiniWindow$default(DynamicIslandSafeguardsController dynamicIslandSafeguardsController, String str, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        dynamicIslandSafeguardsController.delayExitMiniWindow(str, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void delayExitMiniWindow$lambda$8(DynamicIslandSafeguardsController this$0, String pkg) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(pkg, "$pkg");
        ArrayList<DynamicIslandContentView> arrayList = new ArrayList();
        DynamicIslandContentView mainMiniWindow = this$0.windowView.getMainMiniWindow(pkg);
        if (mainMiniWindow != null) {
            arrayList.add(mainMiniWindow);
        }
        DynamicIslandContentView subMiniWindow = this$0.windowView.getSubMiniWindow(pkg);
        if (subMiniWindow != null) {
            arrayList.add(subMiniWindow);
        }
        Log.d(TAG, "delayExitMiniWindow: " + pkg + " count = " + arrayList.size());
        if (arrayList.isEmpty()) {
            return;
        }
        for (DynamicIslandContentView dynamicIslandContentView : arrayList) {
            dynamicIslandContentView.setVisibility(0);
            DynamicIslandBackgroundView backgroundView = dynamicIslandContentView.getBackgroundView();
            if (backgroundView != null) {
                backgroundView.setVisibility(0);
            }
            dynamicIslandContentView.setWindowAnimState(new WindowAnimState.Closed(true, false, false));
            DynamicIslandWindowView.updateViewStateWhenCloseEnd$default(this$0.windowView, dynamicIslandContentView, true, null, 4, null);
            this$0.windowView.updateIslandWindowAnimRunningState(false, dynamicIslandContentView, true);
            this$0.windowView.exitMiniWindow(dynamicIslandContentView);
        }
        this$0.windowView.setAnimRunning(false, true);
        u uVar = this$0.getWindowViewController().get_isFreeformAnimRunning();
        Boolean bool = Boolean.FALSE;
        uVar.setValue(bool);
        this$0.getWindowViewController().get_isAppAnimRunning().setValue(bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void delayResetWindowAnimation$lambda$4(DynamicIslandSafeguardsController this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "onDelayResetWindowAnimation");
        this$0.getWindowViewController().resetWindowAnimationState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DynamicIslandWindowViewController getWindowViewController() {
        return (DynamicIslandWindowViewController) this.windowViewController$delegate.getValue();
    }

    public final void cancelDelayCollapsed() {
        Runnable runnable = this.onDelayCollapsed;
        if (runnable != null) {
            runnable.run();
        }
        this.onDelayCollapsed = null;
    }

    public final void cancelDelayEnterApp(String str) {
        Log.d(TAG, "cancelDelayEnterApp " + str + " " + this.currentDelayEnterAppPkg);
        Runnable runnable = this.onDelayEnterApp;
        if (runnable != null) {
            runnable.run();
        }
        this.onDelayEnterApp = null;
    }

    public final void cancelDelayEnterMiniWindow(String str) {
        Log.d(TAG, "cancelDelayEnterMiniWindow " + getWindowViewController().getTopActivityPkg() + " " + str + " " + this.currentDelayEnterMiniWindowPkg);
        Runnable runnable = this.onDelayEnterMiniWindow;
        if (runnable != null) {
            runnable.run();
        }
        this.onDelayEnterMiniWindow = null;
    }

    public final void cancelDelayExitApp(String str) {
        Log.d(TAG, "cancelDelayExitApp " + str + " " + this.currentDelayExitAppPkg);
        if (kotlin.jvm.internal.n.c(str, this.currentDelayExitAppPkg)) {
            Runnable runnable = this.onDelayExitApp;
            if (runnable != null) {
                runnable.run();
            }
            this.onDelayExitApp = null;
        }
    }

    public final void cancelDelayExitMiniWindow(String str) {
        Log.d(TAG, "cancelDelayExitMiniWindow " + str + " " + this.currentDelayExitMiniWindowPkg);
        Runnable runnable = this.onDelayExitMiniWindowMap.get(str);
        if (runnable != null) {
            runnable.run();
        }
        D.d(this.onDelayExitMiniWindowMap).remove(str);
    }

    public final void cancelDelayResetWindowAnimation() {
        Log.d(TAG, "cancelDelayResetWindowAnimation");
        Runnable runnable = this.onResetWindowAnimationState;
        if (runnable != null) {
            runnable.run();
        }
        this.onResetWindowAnimationState = null;
    }

    public final void delayCollapsed(long j2) {
        Log.d(TAG, "delayCollapsed");
        this.onDelayCollapsed = this.uiExecutor.executeDelayed(new Runnable() { // from class: miui.systemui.dynamicisland.window.f
            @Override // java.lang.Runnable
            public final void run() throws PendingIntent.CanceledException {
                DynamicIslandSafeguardsController.delayCollapsed$lambda$3(this.f5743a);
            }
        }, j2, TimeUnit.SECONDS);
    }

    public final void delayDeleted(final String str, long j2) {
        Log.d(TAG, "delayDeleted " + str + ", time:" + j2);
        cancelDelayDeleted(str);
        Runnable runnableExecuteDelayed = this.uiExecutor.executeDelayed(new Runnable() { // from class: miui.systemui.dynamicisland.window.e
            @Override // java.lang.Runnable
            public final void run() {
                DynamicIslandSafeguardsController.delayDeleted$lambda$1(str, this);
            }
        }, j2, TimeUnit.SECONDS);
        if (str != null) {
            this.onDelayCancelMap.put(str, runnableExecuteDelayed);
        }
    }

    public final void delayEnterApp(DynamicIslandContentView dynamicIslandContentView, boolean z2) {
        DynamicIslandData currentIslandData;
        Bundle extras;
        final String string = (dynamicIslandContentView == null || (currentIslandData = dynamicIslandContentView.getCurrentIslandData()) == null || (extras = currentIslandData.getExtras()) == null) ? null : extras.getString("miui.pkg.name");
        this.currentDelayEnterAppPkg = string;
        Log.d(TAG, "delayEnterApp " + string);
        cancelDelayEnterApp(this.currentDelayEnterAppPkg);
        this.onDelayEnterApp = this.uiExecutor.executeDelayed(new Runnable() { // from class: miui.systemui.dynamicisland.window.j
            @Override // java.lang.Runnable
            public final void run() {
                DynamicIslandSafeguardsController.delayEnterApp$lambda$18(string, this);
            }
        }, z2 ? 0L : getWindowViewController().windowAnimating() ? 1000L : DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    public final void delayEnterMiniWidow(final String pkg) {
        kotlin.jvm.internal.n.g(pkg, "pkg");
        Log.d(TAG, "delayEnterMiniWidow " + pkg);
        this.currentDelayEnterMiniWindowPkg = pkg;
        cancelDelayEnterMiniWindow(pkg);
        this.onDelayEnterMiniWindow = this.uiExecutor.executeDelayed(new Runnable() { // from class: miui.systemui.dynamicisland.window.g
            @Override // java.lang.Runnable
            public final void run() {
                DynamicIslandSafeguardsController.delayEnterMiniWidow$lambda$21(pkg, this);
            }
        }, DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    public final void delayExitApp(final String str, boolean z2) {
        Log.d(TAG, "delayExitApp " + str);
        this.currentDelayExitAppPkg = str;
        cancelDelayExitApp(str);
        this.onDelayExitApp = this.uiExecutor.executeDelayed(new Runnable() { // from class: miui.systemui.dynamicisland.window.d
            @Override // java.lang.Runnable
            public final void run() {
                DynamicIslandSafeguardsController.delayExitApp$lambda$15(this.f5739a, str);
            }
        }, z2 ? 0L : DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    public final void delayExitMiniWindow(final String pkg, boolean z2) {
        kotlin.jvm.internal.n.g(pkg, "pkg");
        Log.d(TAG, "delayExitMiniWindow " + pkg);
        this.currentDelayExitMiniWindowPkg = pkg;
        cancelDelayExitMiniWindow(pkg);
        Runnable runnableExecuteDelayed = this.uiExecutor.executeDelayed(new Runnable() { // from class: miui.systemui.dynamicisland.window.i
            @Override // java.lang.Runnable
            public final void run() {
                DynamicIslandSafeguardsController.delayExitMiniWindow$lambda$8(this.f5747a, pkg);
            }
        }, z2 ? 0L : DELAY_TIME, TimeUnit.MILLISECONDS);
        String str = this.currentDelayExitMiniWindowPkg;
        if (str != null) {
            this.onDelayExitMiniWindowMap.put(str, runnableExecuteDelayed);
        }
    }

    public final void delayResetWindowAnimation(long j2) {
        Log.d(TAG, "delayResetWindowAnimation");
        cancelDelayResetWindowAnimation();
        this.onResetWindowAnimationState = this.uiExecutor.executeDelayed(new Runnable() { // from class: miui.systemui.dynamicisland.window.h
            @Override // java.lang.Runnable
            public final void run() {
                DynamicIslandSafeguardsController.delayResetWindowAnimation$lambda$4(this.f5746a);
            }
        }, j2, TimeUnit.SECONDS);
    }

    public final String getFullScreenPkg() {
        return this.fullScreenPkg;
    }

    public final DelayableExecutor getUiExecutor() {
        return this.uiExecutor;
    }

    public final void removeEnterMiniWindow(String str) {
        Log.d(TAG, "removeEnterMiniWindow: " + str + "  " + this.currentDelayEnterMiniWindowPkg);
        if (str == null || !kotlin.jvm.internal.n.c(str, this.currentDelayEnterMiniWindowPkg)) {
            return;
        }
        Runnable runnable = this.onDelayEnterMiniWindow;
        if (runnable != null) {
            runnable.run();
        }
        this.onDelayEnterMiniWindow = null;
    }

    public final void setFullScreenPkg(String str) {
        kotlin.jvm.internal.n.g(str, "<set-?>");
        this.fullScreenPkg = str;
    }

    public final void setUiExecutor(DelayableExecutor delayableExecutor) {
        kotlin.jvm.internal.n.g(delayableExecutor, "<set-?>");
        this.uiExecutor = delayableExecutor;
    }

    @Override // miui.systemui.dynamicisland.DynamicIslandStartable
    public void start() {
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(null), 3, null);
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass2(null), 3, null);
    }
}
