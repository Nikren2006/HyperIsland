package miui.systemui.flashlight;

import H0.d;
import H0.e;
import H0.k;
import H0.s;
import N0.f;
import N0.l;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.content.ContextCompat;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import g1.M;
import j1.InterfaceC0419g;
import j1.y;
import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.C0427a;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.n;
import miui.systemui.coroutines.CoroutineScopeKt;
import miui.systemui.flashlight.MiFlashlightActivity$onBackPressedCallback$2;
import miui.systemui.flashlight.utils.TrackUtils;
import miui.systemui.flashlight.view.MiFlashlightLayout;
import miui.systemui.util.BlurUtils;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceUtils;
import miui.systemui.util.MiBlurCompat;
import miuix.animation.Folme;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.ViewProperty;
import miuix.animation.utils.EaseManager;
import miuix.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightActivity extends AppCompatActivity {
    public static final Companion Companion = new Companion(null);
    private static final String PROPERTY_BLUR_PROGRESS = "property_blur_progress";
    private static final String TAG = "MiFlash_MiFlashlightActivity";
    private float blurProgress;
    private BlurUtils blurUtils;
    private final d contentView$delegate;
    private final d flashlightLayout$delegate;
    private final MiFlashlightManager flashlightManager;
    private final d hideBgAnimConfig$delegate;
    private boolean isShowBgAnimRunning;
    private final E mainScope;
    private final d onBackPressedCallback$delegate;
    private final d showBgAnimConfig$delegate;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightActivity$onCreate$1, reason: invalid class name */
    @f(c = "miui.systemui.flashlight.MiFlashlightActivity$onCreate$1", f = "MiFlashlightActivity.kt", l = {87, 100}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightActivity$onCreate$1$1, reason: invalid class name and collision with other inner class name */
        public /* synthetic */ class C01441 implements InterfaceC0419g, i {
            final /* synthetic */ MiFlashlightActivity $tmp0;

            public C01441(MiFlashlightActivity miFlashlightActivity) {
                this.$tmp0 = miFlashlightActivity;
            }

            public final boolean equals(Object obj) {
                if ((obj instanceof InterfaceC0419g) && (obj instanceof i)) {
                    return n.c(getFunctionDelegate(), ((i) obj).getFunctionDelegate());
                }
                return false;
            }

            @Override // kotlin.jvm.internal.i
            public final H0.b getFunctionDelegate() {
                return new C0427a(2, this.$tmp0, MiFlashlightActivity.class, "miFlashlightEventFlowCollect", "miFlashlightEventFlowCollect(Lmiui/systemui/flashlight/MiFlashlightEvent;)V", 4);
            }

            public final int hashCode() {
                return getFunctionDelegate().hashCode();
            }

            @Override // j1.InterfaceC0419g
            public final Object emit(MiFlashlightEvent miFlashlightEvent, L0.d dVar) {
                Object objInvokeSuspend$miFlashlightEventFlowCollect = AnonymousClass1.invokeSuspend$miFlashlightEventFlowCollect(this.$tmp0, miFlashlightEvent, dVar);
                return objInvokeSuspend$miFlashlightEventFlowCollect == M0.c.c() ? objInvokeSuspend$miFlashlightEventFlowCollect : s.f314a;
            }
        }

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ Object invokeSuspend$miFlashlightEventFlowCollect(MiFlashlightActivity miFlashlightActivity, MiFlashlightEvent miFlashlightEvent, L0.d dVar) {
            miFlashlightActivity.miFlashlightEventFlowCollect(miFlashlightEvent);
            return s.f314a;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return MiFlashlightActivity.this.new AnonymousClass1(dVar);
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
                Object systemService = MiFlashlightActivity.this.getApplicationContext().getSystemService("keyguard");
                n.e(systemService, "null cannot be cast to non-null type android.app.KeyguardManager");
                KeyguardManager keyguardManager = (KeyguardManager) systemService;
                Log.d(MiFlashlightActivity.TAG, " keyguardLocked：" + keyguardManager.isKeyguardLocked());
                MiFlashlightActivity.this.setShowWhenLocked(keyguardManager.isKeyguardLocked());
                if (DeviceUtils.isLowEndDevice()) {
                    this.label = 1;
                    if (M.b(350L, this) == objC) {
                        return objC;
                    }
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k.b(obj);
                    throw new H0.c();
                }
                k.b(obj);
            }
            MiFlashlightActivity.this.showBgAnim();
            MiFlashlightActivity.this.getContentView().removeAllViews();
            MiFlashlightActivity.this.getContentView().addView(MiFlashlightActivity.this.getFlashlightLayout());
            OnBackPressedDispatcher onBackPressedDispatcher = MiFlashlightActivity.this.getOnBackPressedDispatcher();
            MiFlashlightActivity miFlashlightActivity = MiFlashlightActivity.this;
            onBackPressedDispatcher.addCallback(miFlashlightActivity, miFlashlightActivity.getOnBackPressedCallback());
            if (DeviceUtils.isLowEndDevice()) {
                MiFlashlightActivity.this.getContentView().setBackgroundColor(ContextCompat.getColor(MiFlashlightActivity.this, R.color.flashlight_background_low_device));
            }
            MiFlashlightActivity.this.trackEnter();
            y miFlashlightEventFlow = MiFlashlightActivity.this.flashlightManager.getMiFlashlightEventFlow();
            C01441 c01441 = new C01441(MiFlashlightActivity.this);
            this.label = 2;
            if (miFlashlightEventFlow.collect(c01441, this) == objC) {
                return objC;
            }
            throw new H0.c();
        }
    }

    public MiFlashlightActivity(MiFlashlightManager flashlightManager) {
        n.g(flashlightManager, "flashlightManager");
        this.flashlightManager = flashlightManager;
        this.mainScope = CoroutineScopeKt.MainScope();
        this.contentView$delegate = e.b(new MiFlashlightActivity$contentView$2(this));
        this.flashlightLayout$delegate = e.b(new MiFlashlightActivity$flashlightLayout$2(this));
        this.onBackPressedCallback$delegate = e.b(new MiFlashlightActivity$onBackPressedCallback$2(this));
        this.showBgAnimConfig$delegate = e.b(new MiFlashlightActivity$showBgAnimConfig$2(this));
        this.hideBgAnimConfig$delegate = e.b(new MiFlashlightActivity$hideBgAnimConfig$2(this));
    }

    private final BlurUtils getBlurUtils() {
        if (this.blurUtils == null) {
            this.blurUtils = new BlurUtils(this);
        }
        BlurUtils blurUtils = this.blurUtils;
        n.d(blurUtils);
        return blurUtils;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FrameLayout getContentView() {
        return (FrameLayout) this.contentView$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MiFlashlightLayout getFlashlightLayout() {
        return (MiFlashlightLayout) this.flashlightLayout$delegate.getValue();
    }

    private final AnimConfig getHideBgAnimConfig() {
        return (AnimConfig) this.hideBgAnimConfig$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MiFlashlightActivity$onBackPressedCallback$2.AnonymousClass1 getOnBackPressedCallback() {
        return (MiFlashlightActivity$onBackPressedCallback$2.AnonymousClass1) this.onBackPressedCallback$delegate.getValue();
    }

    private final AnimConfig getShowBgAnimConfig() {
        return (AnimConfig) this.showBgAnimConfig$delegate.getValue();
    }

    private final void hideBgAnim() {
        Folme.useValue(this).setTo(PROPERTY_BLUR_PROGRESS, Float.valueOf(this.blurProgress)).to(PROPERTY_BLUR_PROGRESS, Float.valueOf(0.0f), getHideBgAnimConfig().setEase(MiFlashlightLayout.Companion.getHideEaseStyle(this)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void miFlashlightEventFlowCollect(MiFlashlightEvent miFlashlightEvent) {
        if (miFlashlightEvent instanceof MiFlashlightWindowHideEvent) {
            MiFlashlightWindowHideEvent miFlashlightWindowHideEvent = (MiFlashlightWindowHideEvent) miFlashlightEvent;
            Log.i(TAG, "MiFlashlightWindowHideEvent " + miFlashlightWindowHideEvent.getFlashlightLayoutHashCode() + " " + getFlashlightLayout().hashCode());
            if (miFlashlightWindowHideEvent.getFlashlightLayoutHashCode() == getFlashlightLayout().hashCode()) {
                finish();
                return;
            }
            return;
        }
        if (!(miFlashlightEvent instanceof MiFlashlightHideEvent)) {
            n.c(miFlashlightEvent, MiFlashlightTempChangeEvent.INSTANCE);
            return;
        }
        MiFlashlightHideEvent miFlashlightHideEvent = (MiFlashlightHideEvent) miFlashlightEvent;
        Log.i(TAG, "MiFlashlightHideEvent " + miFlashlightHideEvent.getFlashlightLayoutHashCode() + " " + getFlashlightLayout().hashCode());
        if (miFlashlightHideEvent.getFlashlightLayoutHashCode() == getFlashlightLayout().hashCode()) {
            hideBgAnim();
        }
    }

    public static /* synthetic */ void onBgAnimUpdate$default(MiFlashlightActivity miFlashlightActivity, float f2, Collection collection, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f2 = 0.0f;
        }
        miFlashlightActivity.onBgAnimUpdate(f2, collection, function1);
    }

    private final void releaseBlurUtils() {
        BlurUtils blurUtils = this.blurUtils;
        if (blurUtils != null) {
            blurUtils.destroy();
        }
        this.blurUtils = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showBgAnim() {
        if (DeviceUtils.isLowEndDevice()) {
            this.isShowBgAnimRunning = true;
        }
        Folme.useValue(this).setTo(PROPERTY_BLUR_PROGRESS, Float.valueOf(this.blurProgress)).to(PROPERTY_BLUR_PROGRESS, Float.valueOf(1.0f), getShowBgAnimConfig().setEase(MiFlashlightLayout.Companion.getShowEaseStyle()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showFlashlightAnim() {
        if (this.isShowBgAnimRunning) {
            return;
        }
        AnimConfig animConfig = new AnimConfig();
        animConfig.addListeners(new TransitionListener() { // from class: miui.systemui.flashlight.MiFlashlightActivity$showFlashlightAnim$config$1$1
            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                this.this$0.getFlashlightLayout().resume();
            }
        });
        animConfig.setEase(EaseManager.getStyle(-2, 1.0f, 0.55f));
        Folme.use((View) getFlashlightLayout()).state().to(new AnimState().add(ViewProperty.ALPHA, 1.0f, new long[0]), animConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trackEnter() {
        int intExtra = getIntent().getIntExtra(TrackUtils.ENTER_FROM, 0);
        Number numberValueOf = !this.flashlightManager.isTorchOn() ? 0 : Float.valueOf(this.flashlightManager.getLogicStrength() * 100);
        TrackUtils trackUtils = TrackUtils.INSTANCE;
        Context baseContext = getBaseContext();
        n.f(baseContext, "getBaseContext(...)");
        trackUtils.trackEnter(baseContext, intExtra, numberValueOf.intValue());
    }

    public final void onBgAnimComplete(Function0 onComplete) {
        n.g(onComplete, "onComplete");
        releaseBlurUtils();
        onComplete.invoke();
    }

    public final void onBgAnimUpdate(float f2, Collection<UpdateInfo> collection, Function1 onUpdate) {
        n.g(onUpdate, "onUpdate");
        UpdateInfo updateInfoFindByName = UpdateInfo.findByName(collection, PROPERTY_BLUR_PROGRESS);
        if (updateInfoFindByName == null) {
            return;
        }
        float floatValue = updateInfoFindByName.getFloatValue();
        this.blurProgress = floatValue;
        Log.i(TAG, "onBgAnimUpdate blurProgress: " + floatValue + "  backgroundBlurOpened: " + MiBlurCompat.getBackgroundBlurOpened(this) + " isLowEndDevice: " + DeviceUtils.isLowEndDevice());
        onUpdate.invoke(Float.valueOf(this.blurProgress));
        if (!MiBlurCompat.getBackgroundBlurOpened(this)) {
            if (DeviceUtils.isLowEndDevice()) {
                getContentView().setAlpha(this.blurProgress);
                return;
            } else {
                getBlurUtils().setBackgroundBlur(getContentView(), Float.valueOf(this.blurProgress * 2.0f), getWindow(), R.color.volume_and_globalActions_blur_dim_color, R.color.flashlight_background_low_device);
                return;
            }
        }
        MiBlurCompat.setMiBackgroundBlurModeCompat(getContentView(), 1);
        MiBlurCompat.setMiBackgroundBlurRadiusCompat(getContentView(), (int) (((getResources().getDisplayMetrics().density * 100) + 60) * this.blurProgress));
        MiBlurCompat.setMiViewBlurModeCompat(getContentView(), 1);
        MiBlurCompat.setPassWindowBlurEnabledCompat(getContentView(), true);
        FrameLayout contentView = getContentView();
        int[] intArray = getResources().getIntArray(R.array.flashlight_window_background_blend_colors);
        n.f(intArray, "getIntArray(...)");
        MiBlurCompat.setMiBackgroundBlendColors(contentView, intArray, this.blurProgress);
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        n.g(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        boolean zIsTinyScreen = CommonUtils.isTinyScreen(this);
        Log.d(TAG, "onConfigurationChanged: isTinyScreen: " + zIsTinyScreen);
        if (CommonUtils.isFlipDevice() && zIsTinyScreen) {
            finish();
        }
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, "onCreate");
        setContentView(getContentView());
        AbstractC0369g.b(this.mainScope, null, null, new AnonymousClass1(null), 3, null);
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        this.flashlightManager.dismiss(getFlashlightLayout().hashCode());
        releaseBlurUtils();
        Folme.clean(this);
        F.e(this.mainScope, null, 1, null);
        finish();
    }
}
