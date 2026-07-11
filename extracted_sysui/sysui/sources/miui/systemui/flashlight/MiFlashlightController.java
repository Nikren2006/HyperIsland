package miui.systemui.flashlight;

import H0.d;
import H0.e;
import H0.k;
import H0.s;
import N0.f;
import N0.l;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.lifecycle.Lifecycle;
import com.airbnb.lottie.LottieAnimationView;
import d1.InterfaceC0330i;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import g1.InterfaceC0380l0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.q;
import kotlin.jvm.internal.z;
import miui.systemui.coroutines.CoroutineScopeKt;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.flashlight.MiFlashlightController$mTorchCallback$2;
import miui.systemui.flashlight.MiFlashlightController$screenOffListener$2;
import miui.systemui.flashlight.controller.LifecycleController;
import miui.systemui.flashlight.dagger.MiFlashlightScope;
import miui.systemui.flashlight.effect.FlashlightRender;
import miui.systemui.flashlight.effect.MiFlashlightUiOpenGl;
import miui.systemui.flashlight.utils.TrackUtils;
import miui.systemui.flashlight.view.MiBottomDrawUpView;
import miui.systemui.flashlight.view.MiFlashlightLayout;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceStateManagerCompat;
import miui.systemui.util.DeviceUtils;
import miui.systemui.util.HapticConstants;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.TalkBackUtils;
import miuix.animation.Folme;
import miuix.animation.base.AnimConfig;
import miuix.mgl.math.Math;

/* JADX INFO: loaded from: classes3.dex */
@MiFlashlightScope
public final class MiFlashlightController extends LifecycleController {
    static final /* synthetic */ InterfaceC0330i[] $$delegatedProperties = {z.d(new q(MiFlashlightController.class, "switchStatus", "getSwitchStatus()Ljava/lang/Boolean;", 0))};
    public static final Companion Companion = new Companion(null);
    private static final String PROPERTY_RENDER_PROGRESS = "property_render_progress";
    private static final String PROPERTY_SWITCH_PROGRESS = "property_switch_progress";
    private static final String SET_CAMERA_FLASH_NOTIFICATION = "camera_flash_notification";
    private static final String TAG = "MiFlash_MiFlashlightController";
    private final d bottomDrawUpView$delegate;
    private int cameraFlashNotificationState;
    private final Context context;
    private final d deviceStateManager$delegate;
    private final d foldStateListener$delegate;
    private final HapticFeedback hapticFeedback;
    private float logicProgress;
    private final d logicProgressAnimConfig$delegate;
    private final d mTorchCallback$delegate;
    private final Handler mainHandler;
    private final E mainScope;
    private final MiFlashlightLayout miFlashlightLayout;
    private final MiFlashlightManager miFlashlightManager;
    private final MiFlashlightUiOpenGl miFlashlightUi;
    private InterfaceC0380l0 miFlashlightUiInitJob;
    private InterfaceC0380l0 miFlashlightUiPauseJob;
    private InterfaceC0380l0 miFlashlightUiResumeJob;
    private int operationNotificationFlag;
    private final d screenOffListener$delegate;
    private final d seekBarBg$delegate;
    private final d seekBarOnTouchListener$delegate;
    private final d seekBarTouchLength$delegate;
    private final d seekbar$delegate;
    private final d seekbarTouchView$delegate;
    private final d seekbarTouchViewOnChangeListener$delegate;
    private final d switchAnimConfig$delegate;
    private final d switchLottieView$delegate;
    private final Z0.c switchStatus$delegate;
    public TalkBackUtils talkBackUtils;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightController$onPause$1, reason: invalid class name */
    @f(c = "miui.systemui.flashlight.MiFlashlightController$onPause$1", f = "MiFlashlightController.kt", l = {573}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return MiFlashlightController.this.new AnonymousClass1(dVar);
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
                k.b(obj);
                InterfaceC0380l0 interfaceC0380l0 = MiFlashlightController.this.miFlashlightUiResumeJob;
                if (interfaceC0380l0 != null) {
                    this.label = 1;
                    if (interfaceC0380l0.c(this) == objC) {
                        return objC;
                    }
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            MiFlashlightController.this.miFlashlightUi.pause();
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightController$onResume$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.flashlight.MiFlashlightController$onResume$1", f = "MiFlashlightController.kt", l = {562}, m = "invokeSuspend")
    public static final class C06611 extends l implements Function2 {
        int label;

        public C06611(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return MiFlashlightController.this.new C06611(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06611) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                InterfaceC0380l0 interfaceC0380l0 = MiFlashlightController.this.miFlashlightUiInitJob;
                if (interfaceC0380l0 != null) {
                    this.label = 1;
                    if (interfaceC0380l0.c(this) == objC) {
                        return objC;
                    }
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            MiFlashlightController.this.miFlashlightUi.resume();
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightController$onStart$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.flashlight.MiFlashlightController$onStart$1", f = "MiFlashlightController.kt", l = {466, 475, 492}, m = "invokeSuspend")
    public static final class C06621 extends l implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightController$onStart$1$1, reason: invalid class name and collision with other inner class name */
        public /* synthetic */ class C01451 extends kotlin.jvm.internal.l implements Function1 {
            public C01451(Object obj) {
                super(1, obj, MiFlashlightController.class, "onDispatchKeyEventListener", "onDispatchKeyEventListener(Landroid/view/KeyEvent;)Z", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(KeyEvent keyEvent) {
                return Boolean.valueOf(((MiFlashlightController) this.receiver).onDispatchKeyEventListener(keyEvent));
            }
        }

        /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightController$onStart$1$2, reason: invalid class name */
        public /* synthetic */ class AnonymousClass2 extends kotlin.jvm.internal.l implements Function1 {
            public AnonymousClass2(Object obj) {
                super(1, obj, MiFlashlightController.class, "onBottomDrawUpListener", "onBottomDrawUpListener(Landroid/view/View;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((View) obj);
                return s.f314a;
            }

            public final void invoke(View p02) {
                n.g(p02, "p0");
                ((MiFlashlightController) this.receiver).onBottomDrawUpListener(p02);
            }
        }

        /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightController$onStart$1$3, reason: invalid class name */
        @f(c = "miui.systemui.flashlight.MiFlashlightController$onStart$1$3", f = "MiFlashlightController.kt", l = {}, m = "invokeSuspend")
        public static final class AnonymousClass3 extends l implements Function2 {
            int label;
            final /* synthetic */ MiFlashlightController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(MiFlashlightController miFlashlightController, L0.d dVar) {
                super(2, dVar);
                this.this$0 = miFlashlightController;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                return new AnonymousClass3(this.this$0, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, L0.d dVar) {
                return ((AnonymousClass3) create(e2, dVar)).invokeSuspend(s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                M0.c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
                MiFlashlightUiOpenGl miFlashlightUiOpenGl = this.this$0.miFlashlightUi;
                Context context = this.this$0.context;
                boolean zIsTorchOn = this.this$0.miFlashlightManager.isTorchOn();
                ViewStub viewStub = (ViewStub) this.this$0.findViewById(R.id.mi_fl_view);
                final MiFlashlightController miFlashlightController = this.this$0;
                miFlashlightUiOpenGl.init(context, zIsTorchOn, viewStub, new FlashlightRender.OnFirstFrameDrawListener() { // from class: miui.systemui.flashlight.c
                    @Override // miui.systemui.flashlight.effect.FlashlightRender.OnFirstFrameDrawListener
                    public final void onFirstFrameDraw() {
                        miFlashlightController.onFirstFrameDraw();
                    }
                });
                return s.f314a;
            }
        }

        public C06621(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            C06621 c06621 = MiFlashlightController.this.new C06621(dVar);
            c06621.L$0 = obj;
            return c06621;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06621) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0132 A[RETURN] */
        @Override // N0.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 313
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: miui.systemui.flashlight.MiFlashlightController.C06621.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightController$onStop$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.flashlight.MiFlashlightController$onStop$1", f = "MiFlashlightController.kt", l = {589}, m = "invokeSuspend")
    public static final class C06631 extends l implements Function2 {
        int label;

        public C06631(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return MiFlashlightController.this.new C06631(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06631) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                InterfaceC0380l0 interfaceC0380l0 = MiFlashlightController.this.miFlashlightUiPauseJob;
                if (interfaceC0380l0 != null) {
                    this.label = 1;
                    if (interfaceC0380l0.c(this) == objC) {
                        return objC;
                    }
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            MiFlashlightController.this.miFlashlightManager.dismiss(MiFlashlightController.this.miFlashlightLayout.hashCode());
            MiFlashlightController.this.getTalkBackUtils().unregister();
            MiFlashlightController.this.miFlashlightLayout.setDispatchKeyEventListener(null);
            MiFlashlightController.this.miFlashlightManager.unregisterTorchCallback(MiFlashlightController.this.getMTorchCallback());
            DeviceStateManagerCompat.INSTANCE.unregisterCallbackCompat(MiFlashlightController.this.getDeviceStateManager(), MiFlashlightController.this.getFoldStateListener());
            try {
                MiFlashlightController.this.context.unregisterReceiver(MiFlashlightController.this.getScreenOffListener());
            } catch (IllegalArgumentException e2) {
                Log.e(MiFlashlightController.TAG, String.valueOf(e2.getMessage()));
            }
            F.e(MiFlashlightController.this.mainScope, null, 1, null);
            return s.f314a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController(Context context, MiFlashlightLayout miFlashlightLayout, Lifecycle lifecycle, MiFlashlightManager miFlashlightManager, @Main Handler mainHandler, MiFlashlightUiOpenGl miFlashlightUi, HapticFeedback hapticFeedback) {
        super(lifecycle);
        n.g(context, "context");
        n.g(miFlashlightLayout, "miFlashlightLayout");
        n.g(lifecycle, "lifecycle");
        n.g(miFlashlightManager, "miFlashlightManager");
        n.g(mainHandler, "mainHandler");
        n.g(miFlashlightUi, "miFlashlightUi");
        n.g(hapticFeedback, "hapticFeedback");
        this.context = context;
        this.miFlashlightLayout = miFlashlightLayout;
        this.miFlashlightManager = miFlashlightManager;
        this.mainHandler = mainHandler;
        this.miFlashlightUi = miFlashlightUi;
        this.hapticFeedback = hapticFeedback;
        this.mainScope = CoroutineScopeKt.MainScope();
        this.deviceStateManager$delegate = e.b(MiFlashlightController$deviceStateManager$2.INSTANCE);
        this.foldStateListener$delegate = e.b(new MiFlashlightController$foldStateListener$2(this));
        this.operationNotificationFlag = -1;
        this.logicProgressAnimConfig$delegate = e.b(new MiFlashlightController$logicProgressAnimConfig$2(this));
        this.switchLottieView$delegate = e.b(new MiFlashlightController$switchLottieView$2(this));
        this.switchAnimConfig$delegate = e.b(new MiFlashlightController$switchAnimConfig$2(this));
        this.seekbar$delegate = e.b(new MiFlashlightController$seekbar$2(this));
        this.seekBarTouchLength$delegate = e.b(new MiFlashlightController$seekBarTouchLength$2(this));
        this.seekBarOnTouchListener$delegate = e.b(new MiFlashlightController$seekBarOnTouchListener$2(this));
        this.seekBarBg$delegate = e.b(new MiFlashlightController$seekBarBg$2(this));
        this.seekbarTouchView$delegate = e.b(new MiFlashlightController$seekbarTouchView$2(this));
        this.seekbarTouchViewOnChangeListener$delegate = e.b(new MiFlashlightController$seekbarTouchViewOnChangeListener$2(this));
        this.bottomDrawUpView$delegate = e.b(new MiFlashlightController$bottomDrawUpView$2(this));
        Z0.a aVar = Z0.a.f970a;
        final Object obj = null;
        this.switchStatus$delegate = new Z0.b(obj) { // from class: miui.systemui.flashlight.MiFlashlightController$special$$inlined$observable$1
            @Override // Z0.b
            public void afterChange(InterfaceC0330i property, Boolean bool, Boolean bool2) {
                n.g(property, "property");
                Boolean bool3 = bool;
                if (!n.c(bool2, Boolean.TRUE)) {
                    this.operationNotificationFlag = 2;
                    this.getSwitchLottieView().setContentDescription(this.context.getString(R.string.flashlight_button_open));
                    MiFlashlightController.setUiLogicProgress$default(this, 0.0f, false, 2, null);
                    if (DeviceUtils.isLowEndDevice()) {
                        return;
                    }
                    MiFlashlightController.updateSwitchProcess$default(this, 1.0f, false, 2, null);
                    return;
                }
                this.operationNotificationFlag = 1;
                this.getSwitchLottieView().setContentDescription(this.context.getString(R.string.flashlight_button_close));
                this.setUiLogicProgress(this.miFlashlightManager.getLogicStrength(), bool3 != null);
                if (DeviceUtils.isLowEndDevice()) {
                    return;
                }
                MiFlashlightController.updateSwitchProcess$default(this, 0.0f, false, 2, null);
            }
        };
        this.screenOffListener$delegate = e.b(new MiFlashlightController$screenOffListener$2(this));
        this.mTorchCallback$delegate = e.b(new MiFlashlightController$mTorchCallback$2(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T extends View> T findViewById(int i2) {
        T t2 = (T) this.miFlashlightLayout.findViewById(i2);
        n.f(t2, "findViewById(...)");
        return t2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MiBottomDrawUpView getBottomDrawUpView() {
        return (MiBottomDrawUpView) this.bottomDrawUpView$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getDeviceStateManager() {
        return this.deviceStateManager$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getFoldStateListener() {
        return this.foldStateListener$delegate.getValue();
    }

    private final AnimConfig getLogicProgressAnimConfig() {
        return (AnimConfig) this.logicProgressAnimConfig$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MiFlashlightController$mTorchCallback$2.AnonymousClass1 getMTorchCallback() {
        return (MiFlashlightController$mTorchCallback$2.AnonymousClass1) this.mTorchCallback$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MiFlashlightController$screenOffListener$2.AnonymousClass1 getScreenOffListener() {
        return (MiFlashlightController$screenOffListener$2.AnonymousClass1) this.screenOffListener$delegate.getValue();
    }

    private final ImageView getSeekBarBg() {
        return (ImageView) this.seekBarBg$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final View.OnTouchListener getSeekBarOnTouchListener() {
        return (View.OnTouchListener) this.seekBarOnTouchListener$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSeekBarTouchLength() {
        return ((Number) this.seekBarTouchLength$delegate.getValue()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SeekBar getSeekbar() {
        return (SeekBar) this.seekbar$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SeekBar getSeekbarTouchView() {
        return (SeekBar) this.seekbarTouchView$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SeekBar.OnSeekBarChangeListener getSeekbarTouchViewOnChangeListener() {
        return (SeekBar.OnSeekBarChangeListener) this.seekbarTouchViewOnChangeListener$delegate.getValue();
    }

    private final AnimConfig getSwitchAnimConfig() {
        return (AnimConfig) this.switchAnimConfig$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LottieAnimationView getSwitchLottieView() {
        return (LottieAnimationView) this.switchLottieView$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Boolean getSwitchStatus() {
        return (Boolean) this.switchStatus$delegate.getValue(this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hapticWhenSeekBarToEdge(int i2) {
        if (i2 == getSeekbarTouchView().getMin() || i2 == getSeekbarTouchView().getMax()) {
            performHapticFeedback(getSeekbarTouchView());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBottomDrawUpListener(View view) {
        Log.i(TAG, "On bottom draw up event!");
        this.miFlashlightManager.hideFlashlight();
        performHapticFeedback(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean onDispatchKeyEventListener(KeyEvent keyEvent) {
        if (keyEvent == null || keyEvent.getAction() != 1 || keyEvent.getKeyCode() != 4) {
            return false;
        }
        Log.i(TAG, "On back press event!");
        this.miFlashlightManager.hideFlashlight();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFirstFrameDraw() {
        Log.d(TAG, "onFirstFrameDraw");
        this.miFlashlightLayout.showView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onSwitchLottieViewClick(View view) {
        Log.d(TAG, "-- switch LottieView Click --");
        performHapticFeedback(view);
        MiFlashlightManager miFlashlightManager = this.miFlashlightManager;
        Boolean switchStatus = getSwitchStatus();
        Boolean bool = Boolean.TRUE;
        MiFlashlightManager.asyncOperate$default(miFlashlightManager, !n.c(switchStatus, bool), null, 2, null);
        TrackUtils.INSTANCE.trackClick(this.context, !n.c(getSwitchStatus(), bool), TrackUtils.CLICK_LOCATION_SET);
    }

    private final void performHapticFeedback(View view) {
        if (this.hapticFeedback.hapticFeedback(HapticConstants.INSTANCE.getEFFECT_ID_BUTTON_LIGHT(), "flick", false)) {
            return;
        }
        view.performHapticFeedback(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFlashlightStatus(float f2) {
        Log.d(TAG, "updateFlashlight switchStatus: " + getSwitchStatus() + " percentage: " + f2);
        Boolean switchStatus = getSwitchStatus();
        Boolean bool = Boolean.TRUE;
        if (n.c(switchStatus, bool) && f2 != 0.0f) {
            MiFlashlightManager.asyncToggleFlashLight$default(this.miFlashlightManager, f2, null, 2, null);
            this.miFlashlightManager.saveLogicStrength(f2);
        } else if (n.c(getSwitchStatus(), bool) && f2 == 0.0f) {
            this.miFlashlightManager.saveLogicStrength(1.0f);
            MiFlashlightManager.asyncOperate$default(this.miFlashlightManager, false, null, 2, null);
        } else {
            if (!n.c(getSwitchStatus(), Boolean.FALSE) || f2 <= 0.0f) {
                return;
            }
            this.miFlashlightManager.saveLogicStrength(f2);
            MiFlashlightManager.asyncOperate$default(this.miFlashlightManager, true, null, 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"ClickableViewAccessibility"})
    public final void setSeekbar(boolean z2) {
        getSeekbar().setVisibility(z2 ? 0 : 8);
        getSeekBarBg().setVisibility(z2 ? 0 : 8);
        getSeekbarTouchView().setVisibility(z2 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSwitchLottieView(boolean z2) {
        CommonUtils.INSTANCE.setMarginBottom(getSwitchLottieView(), this.context.getResources().getDimensionPixelSize(z2 ? R.dimen.mi_switch_btn_margin_bottom_1 : R.dimen.mi_switch_btn_margin_bottom_2), true);
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(z2 ? R.dimen.mi_switch_btn_size : R.dimen.mi_no_seekbar_switch_btn_size);
        ViewGroup.LayoutParams layoutParams = getSwitchLottieView().getLayoutParams();
        layoutParams.height = dimensionPixelSize;
        layoutParams.width = dimensionPixelSize;
        getSwitchLottieView().setLayoutParams(layoutParams);
        getSwitchLottieView().setVisibility(0);
        getSwitchLottieView().setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.flashlight.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f5760a.onSwitchLottieViewClick(view);
            }
        });
        updateSwitchProcess(1.0f, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSwitchStatus(Boolean bool) {
        this.switchStatus$delegate.setValue(this, $$delegatedProperties[0], bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setUiLogicProgress(float f2, boolean z2) {
        getSeekbarTouchView().setProgress((int) (getSeekbarTouchView().getMax() * f2));
        Folme.useValue(this).setTo(PROPERTY_RENDER_PROGRESS, Float.valueOf(z2 ? this.logicProgress : f2)).to(PROPERTY_RENDER_PROGRESS, Float.valueOf(f2), getLogicProgressAnimConfig());
    }

    public static /* synthetic */ void setUiLogicProgress$default(MiFlashlightController miFlashlightController, float f2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        miFlashlightController.setUiLogicProgress(f2, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFlashlightUi(float f2) {
        this.miFlashlightUi.setProgress(Math.Companion.clamp(f2, n.c(getSwitchStatus(), Boolean.TRUE) ? 0.15f : 0.0f, 1.0f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSeekBarProcess(float f2) {
        if (getSeekbar().getVisibility() == 8) {
            return;
        }
        int max = (int) (getSeekbar().getMax() * f2);
        SeekBar seekbar = getSeekbar();
        Boolean switchStatus = getSwitchStatus();
        Boolean bool = Boolean.TRUE;
        if (n.c(switchStatus, bool) && max < 5) {
            max = 5;
        } else if (!n.c(getSwitchStatus(), bool) && max < 5) {
            max = 0;
        }
        seekbar.setProgress(max);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSwitchProcess(float f2, boolean z2) {
        if (z2) {
            Folme.useValue(this).setTo(PROPERTY_SWITCH_PROGRESS, Float.valueOf(getSwitchLottieView().getProgress())).to(PROPERTY_SWITCH_PROGRESS, Float.valueOf(f2), getSwitchAnimConfig());
        } else {
            getSwitchLottieView().setProgress(f2);
        }
    }

    public static /* synthetic */ void updateSwitchProcess$default(MiFlashlightController miFlashlightController, float f2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        miFlashlightController.updateSwitchProcess(f2, z2);
    }

    public final TalkBackUtils getTalkBackUtils() {
        TalkBackUtils talkBackUtils = this.talkBackUtils;
        if (talkBackUtils != null) {
            return talkBackUtils;
        }
        n.w("talkBackUtils");
        return null;
    }

    @Override // miui.systemui.flashlight.controller.LifecycleController
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        this.miFlashlightUiPauseJob = AbstractC0369g.b(this.mainScope, null, null, new AnonymousClass1(null), 3, null);
    }

    @Override // miui.systemui.flashlight.controller.LifecycleController
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        this.miFlashlightUiResumeJob = AbstractC0369g.b(this.mainScope, null, null, new C06611(null), 3, null);
    }

    @Override // miui.systemui.flashlight.controller.LifecycleController
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        this.cameraFlashNotificationState = Settings.System.getInt(this.context.getContentResolver(), SET_CAMERA_FLASH_NOTIFICATION, 0);
        Settings.System.putInt(this.context.getContentResolver(), SET_CAMERA_FLASH_NOTIFICATION, 0);
        AbstractC0369g.b(this.mainScope, null, null, new C06621(null), 3, null);
    }

    @Override // miui.systemui.flashlight.controller.LifecycleController
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        Settings.System.putInt(this.context.getContentResolver(), SET_CAMERA_FLASH_NOTIFICATION, this.cameraFlashNotificationState);
        if (!this.miFlashlightManager.isTorchOn()) {
            this.miFlashlightManager.removeFocusNotification();
        }
        Folme.clean(this);
        AbstractC0369g.b(this.mainScope, null, null, new C06631(null), 3, null);
    }

    public final void setTalkBackUtils(TalkBackUtils talkBackUtils) {
        n.g(talkBackUtils, "<set-?>");
        this.talkBackUtils = talkBackUtils;
    }
}
