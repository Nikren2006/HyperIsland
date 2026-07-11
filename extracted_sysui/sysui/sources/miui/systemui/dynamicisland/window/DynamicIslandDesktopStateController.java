package miui.systemui.dynamicisland.window;

import H0.s;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import g1.AbstractC0369g;
import g1.E;
import g1.InterfaceC0380l0;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import j1.u;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function2;
import miui.app.MiuiFreeFormManager;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.util.concurrency.DelayableExecutor;
import miui.systemui.util.coroutines.flow.FlowConflatedKt;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandDesktopStateController {
    private final String TAG;
    private final Executor bgExecutor;
    private final Context context;
    private final DynamicIslandDesktopStateController$desktopReceiver$1 desktopReceiver;
    private boolean desktopRegistered;
    private final InterfaceC0418f freeFormStackMode;
    private final DynamicMiniWindowDataRepo miniWindowDataRepo;
    private InterfaceC0380l0 registerJob;
    private final E scope;
    private final Context sysUIContext;
    private DelayableExecutor uiExecutor;
    private final DynamicIslandWindowView windowView;
    private final WeakReference<DynamicIslandWindowView> windowViewWeakReference;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandDesktopStateController$registerFreeformStackModeChanged$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandDesktopStateController$registerFreeformStackModeChanged$1", f = "DynamicIslandDesktopStateController.kt", l = {119}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandDesktopStateController.this.new AnonymousClass1(dVar);
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
                InterfaceC0418f interfaceC0418f = DynamicIslandDesktopStateController.this.freeFormStackMode;
                final DynamicIslandDesktopStateController dynamicIslandDesktopStateController = DynamicIslandDesktopStateController.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandDesktopStateController.registerFreeformStackModeChanged.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Number) obj2).intValue(), dVar);
                    }

                    public final Object emit(int i3, L0.d dVar) {
                        Log.d(dynamicIslandDesktopStateController.TAG, "registerFreeformStackModeChanged it:" + i3);
                        if (i3 == 0 || i3 == 10) {
                            dynamicIslandDesktopStateController.runDesktopAnim(false);
                        }
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (interfaceC0418f.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            return s.f314a;
        }
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [miui.systemui.dynamicisland.window.DynamicIslandDesktopStateController$desktopReceiver$1] */
    public DynamicIslandDesktopStateController(Context context, @SystemUI Context sysUIContext, DynamicIslandWindowView windowView, @Main DelayableExecutor uiExecutor, @DynamicIsland E scope, @Background Executor bgExecutor) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(sysUIContext, "sysUIContext");
        kotlin.jvm.internal.n.g(windowView, "windowView");
        kotlin.jvm.internal.n.g(uiExecutor, "uiExecutor");
        kotlin.jvm.internal.n.g(scope, "scope");
        kotlin.jvm.internal.n.g(bgExecutor, "bgExecutor");
        this.context = context;
        this.sysUIContext = sysUIContext;
        this.windowView = windowView;
        this.uiExecutor = uiExecutor;
        this.scope = scope;
        this.bgExecutor = bgExecutor;
        this.TAG = "DesktopStateController";
        this.windowViewWeakReference = new WeakReference<>(windowView);
        this.miniWindowDataRepo = new DynamicMiniWindowDataRepo(bgExecutor);
        this.desktopReceiver = new BroadcastReceiver() { // from class: miui.systemui.dynamicisland.window.DynamicIslandDesktopStateController$desktopReceiver$1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Code restructure failed: missing block: B:33:0x008f, code lost:
            
                if (r4.equals("crossSafeArea") == false) goto L54;
             */
            /* JADX WARN: Code restructure failed: missing block: B:44:0x00b6, code lost:
            
                if (r4.equals("taskSnapshot") == false) goto L54;
             */
            /* JADX WARN: Code restructure failed: missing block: B:47:0x00bf, code lost:
            
                if (r4.equals("startControlAppWindow") == false) goto L54;
             */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // android.content.BroadcastReceiver
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onReceive(android.content.Context r4, android.content.Intent r5) {
                /*
                    Method dump skipped, instruction units count: 294
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.DynamicIslandDesktopStateController$desktopReceiver$1.onReceive(android.content.Context, android.content.Intent):void");
            }
        };
        this.freeFormStackMode = FlowConflatedKt.conflatedCallbackFlow(new DynamicIslandDesktopStateController$freeFormStackMode$1(this, null));
    }

    private final void registerFreeformStackModeChanged() {
        InterfaceC0380l0 interfaceC0380l0 = this.registerJob;
        if (interfaceC0380l0 == null || !interfaceC0380l0.isActive()) {
            this.registerJob = AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runDesktopAnim$lambda$0(DynamicIslandDesktopStateController this$0) {
        DynamicIslandWindowViewController windowViewController;
        DynamicIslandWindowState windowState;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        DynamicIslandWindowView dynamicIslandWindowView = this$0.windowViewWeakReference.get();
        u deskTopAnimating = (dynamicIslandWindowView == null || (windowViewController = dynamicIslandWindowView.getWindowViewController()) == null || (windowState = windowViewController.getWindowState()) == null) ? null : windowState.getDeskTopAnimating();
        if (deskTopAnimating == null) {
            return;
        }
        deskTopAnimating.setValue(Boolean.FALSE);
    }

    public final void exitFreeFrom(MiuiFreeFormManager.MiuiFreeFormStackInfo miuiFreeFormStackInfo) {
        String str;
        DynamicIslandWindowViewController windowViewController;
        boolean z2 = (miuiFreeFormStackInfo == null || miuiFreeFormStackInfo.isInFreeFormMode() || miuiFreeFormStackInfo.isInMiniFreeFormMode()) ? false : true;
        DynamicIslandWindowView dynamicIslandWindowView = this.windowViewWeakReference.get();
        Boolean boolValueOf = (dynamicIslandWindowView == null || (windowViewController = dynamicIslandWindowView.getWindowViewController()) == null) ? null : Boolean.valueOf(windowViewController.isNotSupportFreeFormAnim());
        Log.d(this.TAG, "exitFreeFrom " + z2 + " " + (miuiFreeFormStackInfo != null ? miuiFreeFormStackInfo.packageName : null) + " " + boolValueOf);
        if (!z2 || !kotlin.jvm.internal.n.c(boolValueOf, Boolean.TRUE) || miuiFreeFormStackInfo == null || (str = miuiFreeFormStackInfo.packageName) == null) {
            return;
        }
        AbstractC0369g.b(this.scope, null, null, new DynamicIslandDesktopStateController$exitFreeFrom$1$1(this, str, null), 3, null);
    }

    public final DynamicMiniWindowDataRepo getMiniWindowDataRepo() {
        return this.miniWindowDataRepo;
    }

    public final DelayableExecutor getUiExecutor() {
        return this.uiExecutor;
    }

    public final void runDesktopAnim(boolean z2) {
        DynamicIslandWindowViewController windowViewController;
        DynamicIslandWindowState windowState;
        DynamicIslandWindowViewController windowViewController2;
        DynamicIslandWindowView dynamicIslandWindowView = this.windowViewWeakReference.get();
        u deskTopAnimating = null;
        DynamicIslandWindowState windowState2 = (dynamicIslandWindowView == null || (windowViewController2 = dynamicIslandWindowView.getWindowViewController()) == null) ? null : windowViewController2.getWindowState();
        if (windowState2 != null) {
            windowState2.setTempHiddenType(DynamicIslandWindowState.TempHiddenType.DESKTOP_ANIMATING);
        }
        if (!z2) {
            this.uiExecutor.executeDelayed(new Runnable() { // from class: miui.systemui.dynamicisland.window.c
                @Override // java.lang.Runnable
                public final void run() {
                    DynamicIslandDesktopStateController.runDesktopAnim$lambda$0(this.f5735a);
                }
            }, 1000L);
            return;
        }
        DynamicIslandWindowView dynamicIslandWindowView2 = this.windowViewWeakReference.get();
        if (dynamicIslandWindowView2 != null && (windowViewController = dynamicIslandWindowView2.getWindowViewController()) != null && (windowState = windowViewController.getWindowState()) != null) {
            deskTopAnimating = windowState.getDeskTopAnimating();
        }
        if (deskTopAnimating == null) {
            return;
        }
        deskTopAnimating.setValue(Boolean.TRUE);
    }

    public final void setUiExecutor(DelayableExecutor delayableExecutor) {
        kotlin.jvm.internal.n.g(delayableExecutor, "<set-?>");
        this.uiExecutor = delayableExecutor;
    }

    public final void start() {
        if (this.desktopRegistered) {
            return;
        }
        this.miniWindowDataRepo.start();
        this.sysUIContext.registerReceiver(this.desktopReceiver, new IntentFilter(DynamicIslandConstants.ACTION_FULLSCREEN_STATE_CHANGE), 2);
        this.desktopRegistered = true;
        registerFreeformStackModeChanged();
    }

    public final void stop() {
        InterfaceC0380l0 interfaceC0380l0 = this.registerJob;
        if (interfaceC0380l0 != null) {
            InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
        }
        this.registerJob = null;
        if (this.desktopRegistered) {
            this.miniWindowDataRepo.stop();
            this.desktopRegistered = false;
            this.sysUIContext.unregisterReceiver(this.desktopReceiver);
        }
    }
}
