package miui.systemui.controlcenter.panel.devicecontrol;

import H0.s;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.lifecycle.Lifecycle;
import java.util.Optional;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.l;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.customize.CustomizeAdapter;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.databinding.SmartHomePanelBinding;
import miui.systemui.controlcenter.panel.SecondaryPanelBase;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.qs.TileSpecsKt;
import miui.systemui.controlcenter.windowview.ControlCenterScreenshot;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.devicecontrols.DeviceControlsPresenter;
import miui.systemui.util.OnClickListenerEx;
import miuix.animation.IStateStyle;
import miuix.animation.controller.AnimState;
import miuix.animation.property.ViewProperty;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class DeviceControlPanelController extends SecondaryPanelBase<FrameLayout, Object> {
    private static final float ALPHA_CLIP_THRESHOLD_EXPANDING = 0.1f;
    public static final Companion Companion = new Companion(null);
    private float bgAlphaValue;
    private final Optional<DeviceControlsPresenter> deviceControlsPresenter;
    private final AnimState hideAnim;
    private final E0.a mainPanelController;
    private final DeviceControlPanelController$onScreenshotListener$1 onScreenshotListener;
    private final E0.a screenshot;
    private final E0.a secondaryPanelRouter;
    private final AnimState showAnim;
    private final E0.a windowViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController$onConfigurationChanged$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        final /* synthetic */ int $changes;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(int i2) {
            super(1);
            this.$changes = i2;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DeviceControlsPresenter) obj);
            return s.f314a;
        }

        public final void invoke(DeviceControlsPresenter it) {
            n.g(it, "it");
            it.onConfigurationChanged(this.$changes);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController$onCreate$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04771 extends o implements Function1 {
        public C04771() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((View) obj);
            return s.f314a;
        }

        public final void invoke(View view) {
            ((SecondaryPanelRouter) DeviceControlPanelController.this.secondaryPanelRouter.get()).routeToMain();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController$onDestroy$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04781 extends o implements Function1 {
        public static final C04781 INSTANCE = new C04781();

        public C04781() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DeviceControlsPresenter) obj);
            return s.f314a;
        }

        public final void invoke(DeviceControlsPresenter it) {
            n.g(it, "it");
            it.destroy();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController$onHidden$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04791 extends o implements Function1 {
        public static final C04791 INSTANCE = new C04791();

        public C04791() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DeviceControlsPresenter) obj);
            return s.f314a;
        }

        public final void invoke(DeviceControlsPresenter it) {
            n.g(it, "it");
            it.onDCPreHideFinished();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController$onShown$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04801 extends o implements Function1 {
        public static final C04801 INSTANCE = new C04801();

        public C04801() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DeviceControlsPresenter) obj);
            return s.f314a;
        }

        public final void invoke(DeviceControlsPresenter it) {
            n.g(it, "it");
            it.onDCShowFinished();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController$onStartOnce$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04811 extends o implements Function1 {

        /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController$onStartOnce$1$1, reason: invalid class name and collision with other inner class name */
        public /* synthetic */ class C01091 extends l implements Function0 {
            public C01091(Object obj) {
                super(0, obj, DeviceControlPanelController.class, "back", "back()V", 0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m87invoke();
                return s.f314a;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m87invoke() {
                ((DeviceControlPanelController) this.receiver).back();
            }
        }

        /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController$onStartOnce$1$2, reason: invalid class name */
        public /* synthetic */ class AnonymousClass2 extends l implements Function1 {
            public AnonymousClass2(Object obj) {
                super(1, obj, DeviceControlPanelController.class, TileSpecsKt.TILE_SPEC_EDIT, "edit(Lmiui/systemui/controlcenter/customize/CustomizeAdapter;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((CustomizeAdapter) obj);
                return s.f314a;
            }

            public final void invoke(CustomizeAdapter p02) {
                n.g(p02, "p0");
                ((DeviceControlPanelController) this.receiver).edit(p02);
            }
        }

        /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController$onStartOnce$1$3, reason: invalid class name */
        public /* synthetic */ class AnonymousClass3 extends l implements Function0 {
            public AnonymousClass3(Object obj) {
                super(0, obj, DeviceControlPanelController.class, "hideCustomize", "hideCustomize()V", 0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m88invoke();
                return s.f314a;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m88invoke() {
                ((DeviceControlPanelController) this.receiver).hideCustomize();
            }
        }

        public C04811() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DeviceControlsPresenter) obj);
            return s.f314a;
        }

        public final void invoke(DeviceControlsPresenter presenter) {
            n.g(presenter, "presenter");
            presenter.create(true);
            View orCreateControlsView = presenter.getOrCreateControlsView(new C01091(DeviceControlPanelController.this), new AnonymousClass2(DeviceControlPanelController.this), new AnonymousClass3(DeviceControlPanelController.this));
            if (orCreateControlsView != null) {
                DeviceControlPanelController deviceControlPanelController = DeviceControlPanelController.this;
                if (orCreateControlsView.getParent() instanceof ViewGroup) {
                    ViewParent parent = orCreateControlsView.getParent();
                    n.e(parent, "null cannot be cast to non-null type android.view.ViewGroup");
                    ((ViewGroup) parent).removeView(orCreateControlsView);
                }
                DeviceControlPanelController.access$getView(deviceControlPanelController).addView(orCreateControlsView, new FrameLayout.LayoutParams(deviceControlPanelController.getContext().getResources().getDimensionPixelSize(R.dimen.control_center_panel_width), -1, 17));
            }
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController$prepareShow$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04821 extends o implements Function1 {
        public static final C04821 INSTANCE = new C04821();

        public C04821() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DeviceControlsPresenter) obj);
            return s.f314a;
        }

        public final void invoke(DeviceControlsPresenter it) {
            n.g(it, "it");
            DeviceControlsPresenter.onDCPreShow$default(it, null, 1, null);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r2v6, types: [miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController$onScreenshotListener$1] */
    public DeviceControlPanelController(SmartHomePanelBinding binding, E0.a secondaryPanelRouter, E0.a screenshot, Optional<DeviceControlsPresenter> deviceControlsPresenter, E0.a mainPanelController, E0.a windowViewController) {
        n.g(binding, "binding");
        n.g(secondaryPanelRouter, "secondaryPanelRouter");
        n.g(screenshot, "screenshot");
        n.g(deviceControlsPresenter, "deviceControlsPresenter");
        n.g(mainPanelController, "mainPanelController");
        n.g(windowViewController, "windowViewController");
        miui.systemui.widget.FrameLayout root = binding.getRoot();
        n.f(root, "getRoot(...)");
        super(root, mainPanelController);
        this.secondaryPanelRouter = secondaryPanelRouter;
        this.screenshot = screenshot;
        this.deviceControlsPresenter = deviceControlsPresenter;
        this.mainPanelController = mainPanelController;
        this.windowViewController = windowViewController;
        AnimState animState = new AnimState("STATE_SHOW");
        ViewProperty viewProperty = ViewProperty.ALPHA;
        AnimState animStateAdd = animState.add(viewProperty, 1.0f, new long[0]);
        n.f(animStateAdd, "add(...)");
        this.showAnim = animStateAdd;
        AnimState animStateAdd2 = new AnimState("STATE_HIDE").add(viewProperty, 0.0f, new long[0]);
        n.f(animStateAdd2, "add(...)");
        this.hideAnim = animStateAdd2;
        this.onScreenshotListener = new ControlCenterScreenshot.OnScreenshotListener() { // from class: miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController$onScreenshotListener$1
            @Override // miui.systemui.controlcenter.windowview.ControlCenterScreenshot.OnScreenshotListener
            public void onScreenshot() {
                ((ControlCenterScreenshot) this.this$0.screenshot.get()).addDumpMessage("smartHomeVisibility", String.valueOf(DeviceControlPanelController.access$getView(this.this$0).getVisibility()));
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final /* synthetic */ FrameLayout access$getView(DeviceControlPanelController deviceControlPanelController) {
        return (FrameLayout) deviceControlPanelController.getView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void back() {
        ((SecondaryPanelRouter) this.secondaryPanelRouter.get()).routeToMain();
    }

    private final void doUpdateClipHeaderCheck(float f2) {
        if (f2 > 0.1f && this.bgAlphaValue < 0.1f) {
            ((ControlCenterWindowViewController) this.windowViewController.get()).updateClip(false);
        }
        this.bgAlphaValue = f2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void edit(CustomizeAdapter customizeAdapter) {
        ((SecondaryPanelRouter) this.secondaryPanelRouter.get()).routeToCustomize(customizeAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideCustomize() {
        ((SecondaryPanelRouter) this.secondaryPanelRouter.get()).routeToSmartHome(((ControlCenterWindowViewController) this.windowViewController.get()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onConfigurationChanged$lambda$1(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDestroy$lambda$5(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onHidden$lambda$4(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onShown$lambda$3(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStartOnce$lambda$0(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void prepareShow$lambda$2(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase
    public AnimState getHideAnim() {
        return this.hideAnim;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase
    public AnimState getShowAnim() {
        return this.showAnim;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase
    public void onAnimUpdate(boolean z2) {
        if (z2) {
            doUpdateClipHeaderCheck(((FrameLayout) getView()).getAlpha());
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        super.onConfigurationChanged(i2);
        Optional<DeviceControlsPresenter> optional = this.deviceControlsPresenter;
        final AnonymousClass1 anonymousClass1 = new AnonymousClass1(i2);
        optional.ifPresent(new Consumer() { // from class: miui.systemui.controlcenter.panel.devicecontrol.e
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DeviceControlPanelController.onConfigurationChanged$lambda$1(anonymousClass1, obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.view.View] */
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
    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        IStateStyle anim = getAnim();
        if (anim != null) {
            anim.setTo(getHideAnim());
        }
        OnClickListenerEx.INSTANCE.setOnClickListenerEx(getView(), new C04771());
        ((ControlCenterScreenshot) this.screenshot.get()).addOnScreenshotListener(this.onScreenshotListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        ((FrameLayout) getView()).removeAllViews();
        Optional<DeviceControlsPresenter> optional = this.deviceControlsPresenter;
        final C04781 c04781 = C04781.INSTANCE;
        optional.ifPresent(new Consumer() { // from class: miui.systemui.controlcenter.panel.devicecontrol.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DeviceControlPanelController.onDestroy$lambda$5(c04781, obj);
            }
        });
        ((ControlCenterScreenshot) this.screenshot.get()).removeOnScreenshotListener(this.onScreenshotListener);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void onHidden(SecondaryPanelRouter.SecondaryPanel<?> secondaryPanel) {
        super.onHidden(secondaryPanel);
        Optional<DeviceControlsPresenter> optional = this.deviceControlsPresenter;
        final C04791 c04791 = C04791.INSTANCE;
        optional.ifPresent(new Consumer() { // from class: miui.systemui.controlcenter.panel.devicecontrol.d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DeviceControlPanelController.onHidden$lambda$4(c04791, obj);
            }
        });
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public Boolean onKeyEvent(KeyEvent event) {
        n.g(event, "event");
        if (event.getAction() != 1) {
            return null;
        }
        if (event.getKeyCode() != 4 && event.getKeyCode() != 82) {
            return null;
        }
        ((SecondaryPanelRouter) this.secondaryPanelRouter.get()).routeToMain();
        return Boolean.TRUE;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void onShown() {
        super.onShown();
        Optional<DeviceControlsPresenter> optional = this.deviceControlsPresenter;
        final C04801 c04801 = C04801.INSTANCE;
        optional.ifPresent(new Consumer() { // from class: miui.systemui.controlcenter.panel.devicecontrol.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DeviceControlPanelController.onShown$lambda$3(c04801, obj);
            }
        });
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStartOnce() {
        super.onStartOnce();
        Optional<DeviceControlsPresenter> optional = this.deviceControlsPresenter;
        final C04811 c04811 = new C04811();
        optional.ifPresent(new Consumer() { // from class: miui.systemui.controlcenter.panel.devicecontrol.f
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DeviceControlPanelController.onStartOnce$lambda$0(c04811, obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        if (((FrameLayout) getView()).getVisibility() != 8) {
            ((FrameLayout) getView()).setVisibility(8);
            Log.w(getTag(), "view visibility is not GONE when panel collapsed.");
        }
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public boolean prepareShow(Object obj) {
        Optional<DeviceControlsPresenter> optional = this.deviceControlsPresenter;
        final C04821 c04821 = C04821.INSTANCE;
        optional.ifPresent(new Consumer() { // from class: miui.systemui.controlcenter.panel.devicecontrol.c
            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                DeviceControlPanelController.prepareShow$lambda$2(c04821, obj2);
            }
        });
        return super.prepareShow(obj);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void startShow(Function0 completeAction) {
        n.g(completeAction, "completeAction");
        this.bgAlphaValue = 0.0f;
        super.startShow(completeAction);
    }
}
