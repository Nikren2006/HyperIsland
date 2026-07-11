package miui.systemui.dynamicisland.display;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import j1.AbstractC0420h;
import j1.I;
import j1.InterfaceC0419g;
import j1.K;
import j1.u;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.dynamicisland.DynamicFeatureConfig;
import miui.systemui.dynamicisland.DynamicIslandBackgroundView;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
public final class AvoidScreenBurnInHelper {
    public static final int AVOID_SCREEN_BURN_IN_COUNT = 4;
    public static final int AVOID_SCREEN_BURN_IN_DOWN = 3;
    public static final int AVOID_SCREEN_BURN_IN_FADED = 5;
    public static final long AVOID_SCREEN_BURN_IN_FADE_INTERVAL = 600000;
    public static final int AVOID_SCREEN_BURN_IN_LEFT = 0;
    public static final int AVOID_SCREEN_BURN_IN_RIGHT = 2;
    public static final int AVOID_SCREEN_BURN_IN_TRAN_INTERVAL = 60000;
    public static final int AVOID_SCREEN_BURN_IN_UP = 1;
    public static final int AVOID_SCREEN_BURN_PAUSED = -1;
    private static final int CONSTANT_STEP_PX = 4;
    public static final Companion Companion = new Companion(null);
    private static final int MSG_ALPHA = 11;
    private static final int MSG_TRAN = 10;
    private static final String TAG = "AvoidScreenBurnInHelper";
    private final u _dynamicIsLandFaded;
    private final u _notificationPanelExpanded;
    private final u _screenBurnInGuard;
    private final boolean debugEnabled;
    private DynamicIslandEventCoordinator eventCoordinator;
    private final Handler handler;
    private boolean initialized;
    private final E mainScope;
    private boolean mode;
    private final I notificationPanelExpanded;
    private boolean ready;
    private boolean recoverBackgroundAlpha;
    private final I screenBurnInStrategy;
    private long translationInterval;
    private int translationStep;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.display.AvoidScreenBurnInHelper$initCollect$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.display.AvoidScreenBurnInHelper$initCollect$1", f = "AvoidScreenBurnInHelper.kt", l = {235}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        public AnonymousClass1(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return AvoidScreenBurnInHelper.this.new AnonymousClass1(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                I screenBurnInStrategy = AvoidScreenBurnInHelper.this.getScreenBurnInStrategy();
                final AvoidScreenBurnInHelper avoidScreenBurnInHelper = AvoidScreenBurnInHelper.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.display.AvoidScreenBurnInHelper.initCollect.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                        return emit(((Number) obj2).intValue(), dVar);
                    }

                    public final Object emit(int i3, d dVar) {
                        Log.d(AvoidScreenBurnInHelper.TAG, " flow screenBurnInStrategy  " + i3 + " ");
                        DynamicIslandEventCoordinator eventCoordinator = avoidScreenBurnInHelper.getEventCoordinator();
                        if (eventCoordinator != null) {
                            eventCoordinator.handleScreenBurnInStrategy(i3);
                        }
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (screenBurnInStrategy.collect(interfaceC0419g, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.display.AvoidScreenBurnInHelper$initCollect$2, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.display.AvoidScreenBurnInHelper$initCollect$2", f = "AvoidScreenBurnInHelper.kt", l = {241}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends l implements Function2 {
        int label;

        /* JADX INFO: renamed from: miui.systemui.dynamicisland.display.AvoidScreenBurnInHelper$initCollect$2$1, reason: invalid class name */
        @f(c = "miui.systemui.dynamicisland.display.AvoidScreenBurnInHelper$initCollect$2$1", f = "AvoidScreenBurnInHelper.kt", l = {}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends l implements Function2 {
            /* synthetic */ boolean Z$0;
            int label;
            final /* synthetic */ AvoidScreenBurnInHelper this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AvoidScreenBurnInHelper avoidScreenBurnInHelper, d dVar) {
                super(2, dVar);
                this.this$0 = avoidScreenBurnInHelper;
            }

            @Override // N0.a
            public final d create(Object obj, d dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, dVar);
                anonymousClass1.Z$0 = ((Boolean) obj).booleanValue();
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke(((Boolean) obj).booleanValue(), (d) obj2);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
                boolean z2 = this.Z$0;
                this.this$0.debugLog(AvoidScreenBurnInHelper.TAG, " flow notificationPanelExpanded  " + z2 + " ");
                if (z2) {
                    this.this$0.stop();
                } else {
                    this.this$0.start();
                }
                return s.f314a;
            }

            public final Object invoke(boolean z2, d dVar) {
                return ((AnonymousClass1) create(Boolean.valueOf(z2), dVar)).invokeSuspend(s.f314a);
            }
        }

        public AnonymousClass2(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return AvoidScreenBurnInHelper.this.new AnonymousClass2(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass2) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                I notificationPanelExpanded = AvoidScreenBurnInHelper.this.getNotificationPanelExpanded();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(AvoidScreenBurnInHelper.this, null);
                this.label = 1;
                if (AbstractC0420h.h(notificationPanelExpanded, anonymousClass1, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            return s.f314a;
        }
    }

    public AvoidScreenBurnInHelper(@Plugin E scope) {
        n.g(scope, "scope");
        E eG = F.g(scope, Dispatchers.INSTANCE.getMain());
        this.mainScope = eG;
        u uVarA = K.a(-1);
        this._screenBurnInGuard = uVarA;
        Boolean bool = Boolean.FALSE;
        u uVarA2 = K.a(bool);
        this._dynamicIsLandFaded = uVarA2;
        u uVarA3 = K.a(bool);
        this._notificationPanelExpanded = uVarA3;
        this.notificationPanelExpanded = AbstractC0420h.b(uVarA3);
        boolean debug_avoid_screen_burn_in = DynamicFeatureConfig.INSTANCE.getDEBUG_AVOID_SCREEN_BURN_IN();
        this.debugEnabled = debug_avoid_screen_burn_in;
        this.translationInterval = 60000L;
        this.translationStep = 4;
        this.screenBurnInStrategy = AbstractC0420h.B(AbstractC0420h.n(AbstractC0420h.l(uVarA, uVarA2, new AvoidScreenBurnInHelper$screenBurnInStrategy$1(this, null))), eG, j1.E.f4648a.c(), -1);
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: miui.systemui.dynamicisland.display.AvoidScreenBurnInHelper$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                n.g(msg, "msg");
                int i2 = msg.what;
            }
        };
        Log.d(TAG, "init AvoidScreenBurnInHelper debug=" + debug_avoid_screen_burn_in + " tranParams=(" + this.translationStep + "/" + this.translationInterval + ")");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void debugLog(String str, String str2) {
        if (this.debugEnabled) {
            Log.d(str, str2);
        }
    }

    private final void fadeBackground() {
        setMode(true);
        this._dynamicIsLandFaded.setValue(Boolean.TRUE);
    }

    private final void initCollect() {
        AbstractC0369g.b(this.mainScope, null, null, new AnonymousClass1(null), 3, null);
        AbstractC0369g.b(this.mainScope, null, null, new AnonymousClass2(null), 3, null);
    }

    private final void reset() {
        stop();
        this._screenBurnInGuard.setValue(-1);
    }

    private final void resetView() {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.eventCoordinator;
        if (dynamicIslandEventCoordinator != null) {
            DynamicIslandEventCoordinator.dispatchEvent$default(dynamicIslandEventCoordinator, DynamicIslandEvent.AvoidScreenReset.INSTANCE, null, 2, null);
        }
    }

    private final void setMode(boolean z2) {
        this.mode = z2;
        Log.d(TAG, "switch to FadeBackground Mode=" + z2);
    }

    private final void updateAlpha(DynamicIslandContentView dynamicIslandContentView, float f2) {
        DynamicIslandBackgroundView backgroundView;
        if (dynamicIslandContentView != null) {
            dynamicIslandContentView.setTransitionAlpha(f2);
        }
        if (dynamicIslandContentView == null || (backgroundView = dynamicIslandContentView.getBackgroundView()) == null) {
            return;
        }
        backgroundView.setTransitionAlpha(f2);
    }

    private final void updateTranslationX(DynamicIslandContentView dynamicIslandContentView, boolean z2) {
        DynamicIslandBackgroundView backgroundView;
        if (dynamicIslandContentView != null) {
            int i2 = this.translationStep;
            if (z2) {
                i2 = -i2;
            }
            dynamicIslandContentView.setTranslationX(dynamicIslandContentView.getTranslationX() + i2);
        }
        if (dynamicIslandContentView == null || (backgroundView = dynamicIslandContentView.getBackgroundView()) == null) {
            return;
        }
        int i3 = this.translationStep;
        if (z2) {
            i3 = -i3;
        }
        int actualLeft = backgroundView.getActualLeft();
        DynamicIslandBackgroundView backgroundView2 = dynamicIslandContentView.getBackgroundView();
        if (backgroundView2 != null) {
            backgroundView2.setActualLeft(actualLeft + i3);
        }
        DynamicIslandBackgroundView backgroundView3 = dynamicIslandContentView.getBackgroundView();
        if (backgroundView3 != null) {
            DynamicIslandBackgroundView backgroundView4 = dynamicIslandContentView.getBackgroundView();
            backgroundView3.setActualWidth(backgroundView4 != null ? backgroundView4.getActualWidth() + i3 : 0);
        }
        DynamicIslandBackgroundView backgroundView5 = dynamicIslandContentView.getBackgroundView();
        if (backgroundView5 != null) {
            backgroundView5.invalidate();
        }
    }

    private final void updateTranslationY(DynamicIslandContentView dynamicIslandContentView, boolean z2) {
        DynamicIslandBackgroundView backgroundView;
        if (dynamicIslandContentView != null) {
            int i2 = this.translationStep;
            if (z2) {
                i2 = -i2;
            }
            dynamicIslandContentView.setTranslationY(dynamicIslandContentView.getTranslationY() + i2);
        }
        if (dynamicIslandContentView == null || (backgroundView = dynamicIslandContentView.getBackgroundView()) == null) {
            return;
        }
        int i3 = this.translationStep;
        if (z2) {
            i3 = -i3;
        }
        int actualTop = backgroundView.getActualTop();
        DynamicIslandBackgroundView backgroundView2 = dynamicIslandContentView.getBackgroundView();
        if (backgroundView2 != null) {
            backgroundView2.setActualTop(actualTop + i3);
        }
        DynamicIslandBackgroundView backgroundView3 = dynamicIslandContentView.getBackgroundView();
        if (backgroundView3 != null) {
            DynamicIslandBackgroundView backgroundView4 = dynamicIslandContentView.getBackgroundView();
            backgroundView3.setActualHeight(backgroundView4 != null ? backgroundView4.getActualHeight() + i3 : 0);
        }
        DynamicIslandBackgroundView backgroundView5 = dynamicIslandContentView.getBackgroundView();
        if (backgroundView5 != null) {
            backgroundView5.invalidate();
        }
    }

    public final DynamicIslandEventCoordinator getEventCoordinator() {
        return this.eventCoordinator;
    }

    public final I getNotificationPanelExpanded() {
        return this.notificationPanelExpanded;
    }

    public final I getScreenBurnInStrategy() {
        return this.screenBurnInStrategy;
    }

    public final boolean isFadedBefore() {
        if (!this.mode) {
            if (this.handler.hasMessages(11)) {
                this.handler.removeMessages(11);
                this.handler.sendEmptyMessageDelayed(11, AVOID_SCREEN_BURN_IN_FADE_INTERVAL);
            }
            return false;
        }
        this.recoverBackgroundAlpha = true;
        setMode(false);
        this._dynamicIsLandFaded.setValue(Boolean.FALSE);
        start();
        return true;
    }

    public final void resetFadedView(DynamicIslandContentView dynamicIslandContentView) {
        if (dynamicIslandContentView != null) {
            updateAlpha(dynamicIslandContentView, 1.0f);
        }
    }

    public final void setEventCoordinator(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        this.eventCoordinator = dynamicIslandEventCoordinator;
        if (dynamicIslandEventCoordinator == null || this.initialized) {
            return;
        }
        initCollect();
        this.initialized = true;
    }

    public final void start() {
        debugLog(TAG, "start");
    }

    public final void stop() {
        debugLog(TAG, "stop");
    }

    public final void updateNotificationPanelExpanded(boolean z2) {
        this._notificationPanelExpanded.setValue(Boolean.valueOf(z2));
    }

    public final void updateScreenHelperInfo(int i2, boolean z2) {
        this.ready = z2;
        this._screenBurnInGuard.setValue(Integer.valueOf(i2));
    }

    public final void updateTranParams(int i2, int i3) {
        long j2 = i2;
        this.translationInterval = j2;
        this.translationStep = i3;
        debugLog(TAG, "updateTranParams   : " + j2 + " " + i3);
    }

    public final void updateViewForAvoidingScreenBurnIn(DynamicIslandContentView dynamicIslandContentView, int i2) {
        if (dynamicIslandContentView != null) {
            if ((dynamicIslandContentView.getState() instanceof DynamicIslandState.BigIsland) || (dynamicIslandContentView.getState() instanceof DynamicIslandState.SmallIsland)) {
                debugLog(TAG, "avoidScreenBurnInAnimation " + i2);
                if (i2 == -1) {
                    resetView();
                    return;
                }
                if (i2 == 0) {
                    updateTranslationX(dynamicIslandContentView, true);
                    return;
                }
                if (i2 == 1) {
                    updateTranslationY(dynamicIslandContentView, true);
                    return;
                }
                if (i2 == 2) {
                    updateTranslationX(dynamicIslandContentView, false);
                } else if (i2 == 3) {
                    updateTranslationY(dynamicIslandContentView, false);
                } else {
                    if (i2 != 5) {
                        return;
                    }
                    updateAlpha(dynamicIslandContentView, 0.5f);
                }
            }
        }
    }
}
