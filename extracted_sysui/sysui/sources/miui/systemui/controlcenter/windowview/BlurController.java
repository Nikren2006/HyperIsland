package miui.systemui.controlcenter.windowview;

import android.os.Handler;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import java.util.Collection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterScreenshot;
import miui.systemui.controls.ExposeUtils;
import miui.systemui.dagger.qualifiers.Main;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class BlurController extends ControlCenterViewController<ControlCenterWindowViewImpl> implements LifecycleOwner {
    private static final String BLUR_SETUP = "blur_setup";
    private static final String BLUR_TAG = "blur_ratio";
    private static final String BLUR_TARGET = "control_center_blur";
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle EASE_SLIDE_COLOR_BLUR;
    private static final String TAG = "BlurController";
    private IStateStyle anim;
    private AnimConfig animConfig;
    private final BlurController$animListener$1 animListener;
    private boolean animating;
    private float blurRatio;
    private final E0.a brightnessSliderController;
    private final E0.a expandController;
    private final LifecycleRegistry lifecycleRegistry;
    private final LifecycleEventObserver mirrorObserver;
    private final BlurController$onScreenshotListener$1 onScreenshotListener;
    private boolean pendingSwitchBlur;
    private final Runnable postSetBlurRunnable;
    private final E0.a screenshot;
    private final Handler uiHandler;
    private boolean usingNotificationBlur;
    private final E0.a windowViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final EaseManager.EaseStyle getEASE_SLIDE_COLOR_BLUR() {
            return BlurController.EASE_SLIDE_COLOR_BLUR;
        }

        private Companion() {
        }
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        EaseManager.EaseStyle easeStyleSpring = FolmeEase.spring(0.95f, 0.35f);
        kotlin.jvm.internal.n.f(easeStyleSpring, "spring(...)");
        EASE_SLIDE_COLOR_BLUR = easeStyleSpring;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v3, types: [miui.systemui.controlcenter.windowview.BlurController$onScreenshotListener$1] */
    /* JADX WARN: Type inference failed for: r2v5, types: [miui.systemui.controlcenter.windowview.BlurController$animListener$1] */
    public BlurController(ControlCenterWindowViewImpl windowView, @Main Handler uiHandler, E0.a screenshot, E0.a brightnessSliderController, E0.a expandController, E0.a windowViewController) {
        super(windowView);
        kotlin.jvm.internal.n.g(windowView, "windowView");
        kotlin.jvm.internal.n.g(uiHandler, "uiHandler");
        kotlin.jvm.internal.n.g(screenshot, "screenshot");
        kotlin.jvm.internal.n.g(brightnessSliderController, "brightnessSliderController");
        kotlin.jvm.internal.n.g(expandController, "expandController");
        kotlin.jvm.internal.n.g(windowViewController, "windowViewController");
        this.uiHandler = uiHandler;
        this.screenshot = screenshot;
        this.brightnessSliderController = brightnessSliderController;
        this.expandController = expandController;
        this.windowViewController = windowViewController;
        this.lifecycleRegistry = new LifecycleRegistry(this);
        this.postSetBlurRunnable = new Runnable() { // from class: miui.systemui.controlcenter.windowview.a
            @Override // java.lang.Runnable
            public final void run() {
                BlurController.postSetBlurRunnable$lambda$0(this.f5494a);
            }
        };
        this.onScreenshotListener = new ControlCenterScreenshot.OnScreenshotListener() { // from class: miui.systemui.controlcenter.windowview.BlurController$onScreenshotListener$1
            @Override // miui.systemui.controlcenter.windowview.ControlCenterScreenshot.OnScreenshotListener
            public void onScreenshot() {
                ((ControlCenterScreenshot) this.this$0.screenshot.get()).addDumpMessage("blurRatio", String.valueOf(this.this$0.getBlurRatio()));
                ((ControlCenterScreenshot) this.this$0.screenshot.get()).addDumpMessage("blur lifecycle state", String.valueOf(this.this$0.getLifecycle().getCurrentState()));
            }
        };
        this.mirrorObserver = new LifecycleEventObserver() { // from class: miui.systemui.controlcenter.windowview.b
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                BlurController.mirrorObserver$lambda$1(this.f5495a, lifecycleOwner, event);
            }
        };
        this.animListener = new TransitionListener() { // from class: miui.systemui.controlcenter.windowview.BlurController$animListener$1
            private boolean traceSection;
            private final String traceName = "BlurController#blurChanging";
            private final int traceCookie = "BlurController#blurChanging".hashCode();

            public final int getTraceCookie() {
                return this.traceCookie;
            }

            public final String getTraceName() {
                return this.traceName;
            }

            public final boolean getTraceSection() {
                return this.traceSection;
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                if (this.traceSection) {
                    return;
                }
                this.traceSection = true;
                Trace.beginAsyncSection(this.traceName, this.traceCookie);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                Log.d("BlurController", "blur animate canceled");
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                Log.d("BlurController", "blur animate complete " + this.this$0.pendingSwitchBlur);
                if (this.traceSection) {
                    this.traceSection = false;
                    Trace.endAsyncSection(this.traceName, this.traceCookie);
                }
                this.this$0.animating = false;
                if (this.this$0.getBlurRatio() <= 0.0f && this.this$0.usingNotificationBlur) {
                    this.this$0.usingNotificationBlur = false;
                }
                this.this$0.lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                UpdateInfo updateInfoFindByName = UpdateInfo.findByName(collection, "blur_ratio");
                if (updateInfoFindByName == null) {
                    return;
                }
                float fH = c1.f.h(updateInfoFindByName.getFloatValue(), 0.0f, 1.0f);
                Log.i("BlurController", "blur animated to " + fH + " " + updateInfoFindByName.velocity + " " + updateInfoFindByName.isCompleted + " " + this.this$0.pendingSwitchBlur);
                this.this$0.setBlurRatio(c1.f.h(fH, 0.0f, 1.0f));
                if (this.this$0.getBlurRatio() >= 0.7f || !this.this$0.pendingSwitchBlur) {
                    return;
                }
                this.this$0.cancelAnim();
                this.this$0.animating = false;
                IStateStyle iStateStyle = this.this$0.anim;
                if (iStateStyle != null) {
                    iStateStyle.setTo("blur_ratio", Float.valueOf(1.0f));
                }
                this.this$0.pendingSwitchBlur = false;
                this.this$0.lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
            }

            public final void setTraceSection(boolean z2) {
                this.traceSection = z2;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelAnim() {
        this.uiHandler.removeCallbacks(this.postSetBlurRunnable);
        IStateStyle iStateStyle = this.anim;
        if (iStateStyle != null) {
            iStateStyle.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mirrorObserver$lambda$1(BlurController this$0, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(lifecycleOwner, "<anonymous parameter 0>");
        kotlin.jvm.internal.n.g(event, "event");
        int i2 = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i2 == 1) {
            showBlur$default(this$0, false, true, false, true, 4, null);
        } else if (i2 == 2 && ((ControlCenterExpandController) this$0.expandController.get()).getInteractive()) {
            showBlur$default(this$0, true, true, false, true, 4, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void postSetBlurRunnable$lambda$0(BlurController this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        float f2 = this$0.blurRatio;
        Log.d(TAG, "post update blur ratio for surface control is not valid. " + f2 + " " + f2);
        if (this$0.pendingSwitchBlur || this$0.usingNotificationBlur || (((ControlCenterWindowViewImpl) this$0.getView()).getBackground() instanceof BackgroundBlurDrawable)) {
            return;
        }
        ((ControlCenterWindowViewController) this$0.windowViewController.get()).setBlurRatio(this$0.blurRatio);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [android.view.View] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void setBlurRatio(float f2) {
        if (this.blurRatio == f2) {
            Log.v(TAG, "updating the same blur ratio " + f2);
        }
        this.blurRatio = f2;
        Log.d(TAG, "updating " + f2 + " " + this.usingNotificationBlur + " " + this.pendingSwitchBlur);
        if (this.pendingSwitchBlur) {
            return;
        }
        this.uiHandler.removeCallbacks(this.postSetBlurRunnable);
        ((ControlCenterWindowViewController) this.windowViewController.get()).setBlurRatio(this.blurRatio);
        ((ControlCenterExpandController) this.expandController.get()).notifyBlurRatioChanged(this.blurRatio);
        if (this.blurRatio == 0.0f) {
            ((ControlCenterExpandController) this.expandController.get()).setSwitchedFromNPV(false);
        }
        if (this.usingNotificationBlur || (((ControlCenterWindowViewImpl) getView()).getBackground() instanceof BackgroundBlurDrawable) || ExposeUtils.INSTANCE.isSurfaceControlValid((View) getView())) {
            return;
        }
        this.uiHandler.post(this.postSetBlurRunnable);
    }

    public static /* synthetic */ void setBlurRatio$default(BlurController blurController, float f2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        blurController.setBlurRatio(f2, z2);
    }

    public static /* synthetic */ void showBlur$default(BlurController blurController, boolean z2, boolean z3, boolean z4, boolean z5, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z4 = true;
        }
        if ((i2 & 8) != 0) {
            z5 = false;
        }
        blurController.showBlur(z2, z3, z4, z5);
    }

    public final float getBlurRatio() {
        return this.blurRatio;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        Folme.getValueTarget(BLUR_TARGET).setMinVisibleChange(BLUR_TAG, 0.06f);
        this.anim = Folme.useValue(BLUR_TARGET).setup(BLUR_SETUP).setTo(BLUR_TAG, Float.valueOf(0.0f));
        AnimConfig ease = new AnimConfig().addListeners(this.animListener).setEase(EaseManager.getStyle(-2, 0.9f, 0.35f));
        kotlin.jvm.internal.n.f(ease, "setEase(...)");
        this.animConfig = ease;
        ((ControlCenterScreenshot) this.screenshot.get()).addOnScreenshotListener(this.onScreenshotListener);
        this.lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        ((BrightnessSliderController) this.brightnessSliderController.get()).getLifecycle().addObserver(this.mirrorObserver);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        AnimConfig animConfig = this.animConfig;
        if (animConfig == null) {
            kotlin.jvm.internal.n.w("animConfig");
            animConfig = null;
        }
        animConfig.clear();
        this.anim = null;
        Folme.clean(BLUR_TARGET);
        ((ControlCenterScreenshot) this.screenshot.get()).removeOnScreenshotListener(this.onScreenshotListener);
        this.lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
        ((BrightnessSliderController) this.brightnessSliderController.get()).getLifecycle().removeObserver(this.mirrorObserver);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onPause() {
        if (this.pendingSwitchBlur) {
            this.pendingSwitchBlur = false;
        }
    }

    public final void showBlur(boolean z2, boolean z3, boolean z4, boolean z5) {
        Log.d(TAG, "show blur " + z2 + " " + z3 + " " + z4);
        float f2 = z2 ? 1.0f : 0.0f;
        AnimConfig animConfig = null;
        if (z4) {
            this.pendingSwitchBlur = false;
            if (!z3) {
                cancelAnim();
                IStateStyle iStateStyle = this.anim;
                if (iStateStyle != null) {
                    Float fValueOf = Float.valueOf(f2);
                    AnimConfig animConfig2 = this.animConfig;
                    if (animConfig2 == null) {
                        kotlin.jvm.internal.n.w("animConfig");
                    } else {
                        animConfig = animConfig2;
                    }
                    iStateStyle.setTo(BLUR_TAG, fValueOf, animConfig);
                }
                setBlurRatio(f2);
                this.animating = false;
                this.lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
                return;
            }
            this.animating = true;
            this.lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
            if (z5) {
                IStateStyle iStateStyle2 = this.anim;
                if (iStateStyle2 != null) {
                    Float fValueOf2 = Float.valueOf(f2);
                    AnimConfig animConfig3 = this.animConfig;
                    if (animConfig3 == null) {
                        kotlin.jvm.internal.n.w("animConfig");
                    } else {
                        animConfig = animConfig3;
                    }
                    iStateStyle2.to(BLUR_TAG, fValueOf2, animConfig.setEase(EASE_SLIDE_COLOR_BLUR));
                    return;
                }
                return;
            }
            IStateStyle iStateStyle3 = this.anim;
            if (iStateStyle3 != null) {
                Float fValueOf3 = Float.valueOf(f2);
                AnimConfig animConfig4 = this.animConfig;
                if (animConfig4 == null) {
                    kotlin.jvm.internal.n.w("animConfig");
                } else {
                    animConfig = animConfig4;
                }
                iStateStyle3.to(BLUR_TAG, fValueOf3, animConfig);
                return;
            }
            return;
        }
        if (z2) {
            this.usingNotificationBlur = true;
            cancelAnim();
            IStateStyle iStateStyle4 = this.anim;
            if (iStateStyle4 != null) {
                Float fValueOf4 = Float.valueOf(f2);
                AnimConfig animConfig5 = this.animConfig;
                if (animConfig5 == null) {
                    kotlin.jvm.internal.n.w("animConfig");
                } else {
                    animConfig = animConfig5;
                }
                iStateStyle4.setTo(BLUR_TAG, fValueOf4, animConfig);
            }
            setBlurRatio(f2);
            this.animating = false;
            this.lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
            return;
        }
        if (z3) {
            this.pendingSwitchBlur = true;
            this.animating = true;
            this.lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
            IStateStyle iStateStyle5 = this.anim;
            if (iStateStyle5 != null) {
                Float fValueOf5 = Float.valueOf(f2);
                AnimConfig animConfig6 = this.animConfig;
                if (animConfig6 == null) {
                    kotlin.jvm.internal.n.w("animConfig");
                } else {
                    animConfig = animConfig6;
                }
                iStateStyle5.to(BLUR_TAG, fValueOf5, animConfig);
                return;
            }
            return;
        }
        cancelAnim();
        IStateStyle iStateStyle6 = this.anim;
        if (iStateStyle6 != null) {
            Float fValueOf6 = Float.valueOf(f2);
            AnimConfig animConfig7 = this.animConfig;
            if (animConfig7 == null) {
                kotlin.jvm.internal.n.w("animConfig");
            } else {
                animConfig = animConfig7;
            }
            iStateStyle6.setTo(BLUR_TAG, fValueOf6, animConfig);
        }
        setBlurRatio(f2);
        this.animating = false;
        this.lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    public final void setBlurRatio(float f2, boolean z2) {
        Log.d(TAG, "set blur ratio " + f2 + ", " + z2);
        if (z2) {
            this.animating = true;
            this.lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
            IStateStyle iStateStyle = this.anim;
            if (iStateStyle != null) {
                Float fValueOf = Float.valueOf(f2);
                AnimConfig animConfig = this.animConfig;
                if (animConfig == null) {
                    kotlin.jvm.internal.n.w("animConfig");
                    animConfig = null;
                }
                iStateStyle.to(BLUR_TAG, fValueOf, animConfig);
                return;
            }
            return;
        }
        this.animating = false;
        cancelAnim();
        IStateStyle iStateStyle2 = this.anim;
        if (iStateStyle2 != null) {
            iStateStyle2.setTo(BLUR_TAG, Float.valueOf(f2));
        }
        setBlurRatio(f2);
        this.lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }
}
