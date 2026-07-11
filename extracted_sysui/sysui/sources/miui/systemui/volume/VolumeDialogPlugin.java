package miui.systemui.volume;

import H0.d;
import H0.e;
import H0.k;
import H0.s;
import M0.c;
import N0.b;
import N0.f;
import N0.l;
import V0.n;
import android.content.Context;
import android.util.Log;
import com.android.systemui.miui.volume.VolumePanelViewController;
import com.android.systemui.plugins.PluginDependency;
import com.android.systemui.plugins.VolumeDialog;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.annotations.Dependencies;
import com.android.systemui.plugins.annotations.DependsOn;
import com.android.systemui.plugins.annotations.Requirements;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import g1.InterfaceC0380l0;
import j1.AbstractC0420h;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.controlcenter.data.repository.ControlCenterExpandRepository;
import miui.systemui.dagger.PluginComponentInitializer;
import miui.systemui.plugins.PluginBase;

/* JADX INFO: loaded from: classes4.dex */
@Dependencies({@DependsOn(target = VolumeDialogController.StreamState.class), @DependsOn(target = VolumeDialogController.State.class), @DependsOn(target = VolumeDialogController.Callbacks.class)})
@DependsOn(target = VolumeDialog.Callback.class)
@Requirements({@Requires(target = VolumeDialog.class, version = 1), @Requires(target = VolumeDialogController.class, version = 1), @Requires(target = PluginDependency.class, version = 1), @Requires(target = StatusBarStateController.class, version = 1)})
public final class VolumeDialogPlugin extends PluginBase implements VolumeDialog {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "VolumeDialogPlugin";
    public ControlCenterExpandRepository controlCenterExpandRepository;
    private VolumeDialogController mController;
    private StatusBarStateController mStatusBarStateController;
    private VolumePanelViewController mVolumePanelViewController;
    private final d mainScop$delegate = e.b(VolumeDialogPlugin$mainScop$2.INSTANCE);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.volume.VolumeDialogPlugin$observeControlCenterStateChange$1, reason: invalid class name */
    @f(c = "miui.systemui.volume.VolumeDialogPlugin$observeControlCenterStateChange$1", f = "VolumeDialogPlugin.kt", l = {81}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        /* JADX INFO: renamed from: miui.systemui.volume.VolumeDialogPlugin$observeControlCenterStateChange$1$1, reason: invalid class name and collision with other inner class name */
        @f(c = "miui.systemui.volume.VolumeDialogPlugin$observeControlCenterStateChange$1$1", f = "VolumeDialogPlugin.kt", l = {}, m = "invokeSuspend")
        public static final class C01551 extends l implements n {
            /* synthetic */ boolean Z$0;
            /* synthetic */ boolean Z$1;
            /* synthetic */ boolean Z$2;
            int label;

            public C01551(L0.d dVar) {
                super(4, dVar);
            }

            @Override // V0.n
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue(), ((Boolean) obj3).booleanValue(), (L0.d) obj4);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
                boolean z2 = this.Z$0;
                boolean z3 = this.Z$1;
                boolean z4 = this.Z$2;
                Log.i(VolumeDialogPlugin.TAG, "Control Center state change! appearance: " + z2 + " mainPanelAppearance: " + z3 + " mainPanelIsEditMode: " + z4);
                return b.a(z2 && z3 && !z4);
            }

            public final Object invoke(boolean z2, boolean z3, boolean z4, L0.d dVar) {
                C01551 c01551 = new C01551(dVar);
                c01551.Z$0 = z2;
                c01551.Z$1 = z3;
                c01551.Z$2 = z4;
                return c01551.invokeSuspend(s.f314a);
            }
        }

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return VolumeDialogPlugin.this.new AnonymousClass1(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                InterfaceC0418f interfaceC0418fI = AbstractC0420h.i(VolumeDialogPlugin.this.getControlCenterExpandRepository().getAppearance(), VolumeDialogPlugin.this.getControlCenterExpandRepository().getMainPanelAppearance(), VolumeDialogPlugin.this.getControlCenterExpandRepository().getMainPanelIsEditMode(), new C01551(null));
                final VolumeDialogPlugin volumeDialogPlugin = VolumeDialogPlugin.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.volume.VolumeDialogPlugin.observeControlCenterStateChange.1.2
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, L0.d dVar) {
                        VolumePanelViewController volumePanelViewController = volumeDialogPlugin.mVolumePanelViewController;
                        if (volumePanelViewController == null) {
                            kotlin.jvm.internal.n.w("mVolumePanelViewController");
                            volumePanelViewController = null;
                        }
                        volumePanelViewController.setDisableVolumeDialog(z2);
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (interfaceC0418fI.collect(interfaceC0419g, this) == objC) {
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

    private final E getMainScop() {
        return (E) this.mainScop$delegate.getValue();
    }

    private final InterfaceC0380l0 observeControlCenterStateChange() {
        return AbstractC0369g.b(getMainScop(), null, null, new AnonymousClass1(null), 3, null);
    }

    public void destroy() {
        VolumePanelViewController volumePanelViewController = this.mVolumePanelViewController;
        if (volumePanelViewController == null) {
            kotlin.jvm.internal.n.w("mVolumePanelViewController");
            volumePanelViewController = null;
        }
        volumePanelViewController.destroy();
    }

    public final ControlCenterExpandRepository getControlCenterExpandRepository() {
        ControlCenterExpandRepository controlCenterExpandRepository = this.controlCenterExpandRepository;
        if (controlCenterExpandRepository != null) {
            return controlCenterExpandRepository;
        }
        kotlin.jvm.internal.n.w("controlCenterExpandRepository");
        return null;
    }

    public void init(int i2, VolumeDialog.Callback callback) {
        VolumePanelViewController volumePanelViewController = this.mVolumePanelViewController;
        if (volumePanelViewController == null) {
            kotlin.jvm.internal.n.w("mVolumePanelViewController");
            volumePanelViewController = null;
        }
        volumePanelViewController.init(2020);
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onCreated() {
        Object obj = PluginDependency.get(this, VolumeDialogController.class);
        kotlin.jvm.internal.n.f(obj, "get(...)");
        this.mController = (VolumeDialogController) obj;
        Object obj2 = PluginDependency.get(this, StatusBarStateController.class);
        kotlin.jvm.internal.n.f(obj2, "get(...)");
        this.mStatusBarStateController = (StatusBarStateController) obj2;
        Context pluginContext = getPluginContext();
        Context sysuiContext = getSysuiContext();
        VolumeDialogController volumeDialogController = this.mController;
        StatusBarStateController statusBarStateController = null;
        if (volumeDialogController == null) {
            kotlin.jvm.internal.n.w("mController");
            volumeDialogController = null;
        }
        StatusBarStateController statusBarStateController2 = this.mStatusBarStateController;
        if (statusBarStateController2 == null) {
            kotlin.jvm.internal.n.w("mStatusBarStateController");
        } else {
            statusBarStateController = statusBarStateController2;
        }
        this.mVolumePanelViewController = new VolumePanelViewController(pluginContext, sysuiContext, volumeDialogController, statusBarStateController);
        PluginComponentInitializer.getPluginComponent().inject(this);
        observeControlCenterStateChange();
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onDestroyed() {
        F.e(getMainScop(), null, 1, null);
    }

    public final void setControlCenterExpandRepository(ControlCenterExpandRepository controlCenterExpandRepository) {
        kotlin.jvm.internal.n.g(controlCenterExpandRepository, "<set-?>");
        this.controlCenterExpandRepository = controlCenterExpandRepository;
    }
}
