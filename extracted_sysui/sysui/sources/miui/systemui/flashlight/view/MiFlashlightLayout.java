package miui.systemui.flashlight.view;

import H0.d;
import H0.e;
import android.app.KeyguardManager;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.miui.volume.VolumePanelViewController;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.util.BlurUtils;
import miui.systemui.widget.FrameLayout;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.property.ViewProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightLayout extends FrameLayout implements LifecycleOwner {
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle HIDE_EASE;
    private static final EaseManager.EaseStyle HIDE_EASE_KEYGUARD;
    private static final AnimState HIDE_STATE;
    private static final String PROPERTY_BLUR_PROGRESS = "property_blur_progress";
    private static final EaseManager.EaseStyle SHOW_BLUE_EASE;
    private static final EaseManager.EaseStyle SHOW_EASE;
    private static final AnimState SHOW_STATE;
    public static final String TAG = "MiFlash_MiFlashlightLayout";
    private final d animConfig$delegate;
    private final d blurAnimConfig$delegate;
    private float blurProgress;
    private BlurUtils blurUtils;
    private Function1 dispatchKeyEventListener;
    private boolean isAlphaAnimRunning;
    private boolean isBlurAnimRunning;
    private boolean isNeedAnim;
    private boolean isShowAnim;
    private final d lifecycleRegistry$delegate;
    private Function0 onViewShowListener;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final EaseManager.EaseStyle getHideEaseStyle(Context context) {
            n.g(context, "context");
            Object systemService = context.getSystemService("keyguard");
            n.e(systemService, "null cannot be cast to non-null type android.app.KeyguardManager");
            KeyguardManager keyguardManager = (KeyguardManager) systemService;
            Log.i(MiFlashlightLayout.TAG, "getHideEaseStyle keyguardLocked：" + keyguardManager.isKeyguardLocked());
            return keyguardManager.isKeyguardLocked() ? MiFlashlightLayout.HIDE_EASE_KEYGUARD : MiFlashlightLayout.HIDE_EASE;
        }

        public final EaseManager.EaseStyle getShowEaseStyle() {
            return MiFlashlightLayout.SHOW_BLUE_EASE;
        }

        private Companion() {
        }
    }

    static {
        AnimState animState = new AnimState();
        ViewProperty viewProperty = ViewProperty.ALPHA;
        SHOW_STATE = animState.add(viewProperty, 1.0f, new long[0]);
        HIDE_STATE = new AnimState().add(viewProperty, 0.0f, new long[0]);
        EaseManager.EaseStyle style = EaseManager.getStyle(-2, 0.99f, 0.45f);
        n.f(style, "getStyle(...)");
        SHOW_EASE = style;
        EaseManager.EaseStyle style2 = EaseManager.getStyle(-2, 0.95f, 0.3f);
        n.f(style2, "getStyle(...)");
        SHOW_BLUE_EASE = style2;
        EaseManager.EaseStyle style3 = EaseManager.getStyle(-2, 0.95f, 0.35f);
        n.f(style3, "getStyle(...)");
        HIDE_EASE = style3;
        EaseManager.EaseStyle easeStyleSineOut = FolmeEase.sineOut(200L);
        n.f(easeStyleSineOut, "sineOut(...)");
        HIDE_EASE_KEYGUARD = easeStyleSineOut;
    }

    public /* synthetic */ MiFlashlightLayout(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    private final void disableStatusBar() {
        try {
            Object systemService = getContext().getSystemService(VolumePanelViewController.SERVICE_STATUS_BAR);
            n.f(systemService, "getSystemService(...)");
            Class.forName("android.app.StatusBarManager").getMethod("disable", Integer.TYPE).invoke(systemService, 16777216);
        } catch (Throwable th) {
            Log.e(TAG, "disableStatusBar failed.", th);
        }
    }

    private final void enableStatusBar() {
        try {
            Object systemService = getContext().getSystemService(VolumePanelViewController.SERVICE_STATUS_BAR);
            n.f(systemService, "getSystemService(...)");
            Class.forName("android.app.StatusBarManager").getMethod("disable", Integer.TYPE).invoke(systemService, 0);
        } catch (Throwable th) {
            Log.e(TAG, "enableStatusBar failed.", th);
        }
    }

    private final AnimConfig getAnimConfig() {
        return (AnimConfig) this.animConfig$delegate.getValue();
    }

    private final AnimConfig getBlurAnimConfig() {
        return (AnimConfig) this.blurAnimConfig$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BlurUtils getBlurUtils() {
        if (this.blurUtils == null) {
            this.blurUtils = new BlurUtils(getContext());
        }
        BlurUtils blurUtils = this.blurUtils;
        n.d(blurUtils);
        return blurUtils;
    }

    private final LifecycleRegistry getLifecycleRegistry() {
        return (LifecycleRegistry) this.lifecycleRegistry$delegate.getValue();
    }

    private final void onAdd() {
        getLifecycleRegistry().setCurrentState(Lifecycle.State.STARTED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onAnimEnd() {
        Log.i(TAG, "onAnimEnd isAlphaAnimRunning=" + this.isAlphaAnimRunning + " isBlurAnimRunning=" + this.isBlurAnimRunning);
        if (this.isAlphaAnimRunning || this.isBlurAnimRunning) {
            return;
        }
        if (this.isShowAnim) {
            resume();
        } else {
            getLifecycleRegistry().setCurrentState(Lifecycle.State.CREATED);
        }
    }

    private final void onRemove() {
        releaseBlurUtils();
        getLifecycleRegistry().setCurrentState(Lifecycle.State.DESTROYED);
    }

    private final void releaseBlurUtils() {
        BlurUtils blurUtils = this.blurUtils;
        if (blurUtils != null) {
            blurUtils.destroy();
        }
        this.blurUtils = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Function1 function1 = this.dispatchKeyEventListener;
        if (function1 != null) {
            ((Boolean) function1.invoke(keyEvent)).booleanValue();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public final Function1 getDispatchKeyEventListener() {
        return this.dispatchKeyEventListener;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return getLifecycleRegistry();
    }

    public final Function0 getOnViewShowListener() {
        return this.onViewShowListener;
    }

    public final void hideView() {
        pause();
        if (this.isNeedAnim) {
            IStateStyle to = Folme.useValue(PROPERTY_BLUR_PROGRESS).setTo(PROPERTY_BLUR_PROGRESS, Float.valueOf(this.blurProgress));
            Float fValueOf = Float.valueOf(0.0f);
            AnimConfig blurAnimConfig = getBlurAnimConfig();
            Companion companion = Companion;
            Context context = getContext();
            n.f(context, "getContext(...)");
            to.to(PROPERTY_BLUR_PROGRESS, fValueOf, blurAnimConfig.setEase(companion.getHideEaseStyle(context)));
            this.isShowAnim = false;
            IStateStyle iStateStyleState = Folme.useAt(this).state();
            AnimState animState = HIDE_STATE;
            AnimConfig animConfig = getAnimConfig();
            Context context2 = getContext();
            n.f(context2, "getContext(...)");
            iStateStyleState.to(animState, animConfig.setEase(companion.getHideEaseStyle(context2)));
        }
    }

    public final boolean isNeedAnim() {
        return this.isNeedAnim;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow");
        disableStatusBar();
        onAdd();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow");
        enableStatusBar();
        onRemove();
    }

    public final void pause() {
        getLifecycleRegistry().setCurrentState(Lifecycle.State.STARTED);
    }

    public final void preShowAnim() {
        if (this.isNeedAnim) {
            Folme.useAt(this).state().setTo(HIDE_STATE);
        }
    }

    public final void resume() {
        getLifecycleRegistry().setCurrentState(Lifecycle.State.RESUMED);
    }

    public final void setDispatchKeyEventListener(Function1 function1) {
        this.dispatchKeyEventListener = function1;
    }

    public final void setNeedAnim(boolean z2) {
        this.isNeedAnim = z2;
    }

    public final void setOnViewShowListener(Function0 function0) {
        this.onViewShowListener = function0;
    }

    public final void showView() {
        if (this.isNeedAnim) {
            this.isShowAnim = true;
            Folme.useValue(PROPERTY_BLUR_PROGRESS).setTo(PROPERTY_BLUR_PROGRESS, Float.valueOf(this.blurProgress)).to(PROPERTY_BLUR_PROGRESS, Float.valueOf(1.0f), getBlurAnimConfig().setEase(Companion.getShowEaseStyle()));
            Folme.useAt(this).state().to(SHOW_STATE, getAnimConfig().setEase(SHOW_EASE));
        }
        Function0 function0 = this.onViewShowListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0, 12, null);
        n.g(context, "context");
        this.lifecycleRegistry$delegate = e.b(new MiFlashlightLayout$lifecycleRegistry$2(this));
        getLifecycleRegistry().setCurrentState(Lifecycle.State.CREATED);
        this.isNeedAnim = true;
        this.isShowAnim = true;
        this.animConfig$delegate = e.b(new MiFlashlightLayout$animConfig$2(this));
        this.blurAnimConfig$delegate = e.b(new MiFlashlightLayout$blurAnimConfig$2(this, context));
    }
}
