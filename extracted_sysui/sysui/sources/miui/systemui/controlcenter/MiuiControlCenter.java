package miui.systemui.controlcenter;

import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.MiPlayController;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.PluginDependency;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.annotations.Dependencies;
import com.android.systemui.plugins.annotations.DependsOn;
import com.android.systemui.plugins.annotations.Requirements;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.controlcenter.BrightnessControllerBase;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterContent;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterHeader;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterPlugin;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterSecondary;
import com.android.systemui.plugins.miui.dump.PluginDumpManager;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import com.android.systemui.plugins.miui.settings.IUserTracker;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import com.android.systemui.plugins.miui.shade.ShadeHeaderController;
import com.android.systemui.plugins.miui.shade.ShadeSwitchController;
import com.android.systemui.plugins.miui.statusbar.MiuiSecurityController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterPluginInstance;
import miui.systemui.controlcenter.policy.SecurityController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewCreator;
import miui.systemui.dagger.PluginComponentInitializer;
import miui.systemui.plugins.PluginBase;
import miui.systemui.util.BlurUtilsExt;
import miui.systemui.util.MiLinkController;

/* JADX INFO: loaded from: classes.dex */
@Dependencies({@DependsOn(target = MiuiQSHost.class), @DependsOn(target = MiuiQSHost.Callback.class), @DependsOn(target = VolumeDialogController.class), @DependsOn(target = VolumeDialogController.StreamState.class), @DependsOn(target = VolumeDialogController.State.class), @DependsOn(target = VolumeDialogController.Callbacks.class)})
@Requirements({@Requires(target = ControlCenterPlugin.class, version = 1), @Requires(target = ShadeSwitchController.class, version = 1), @Requires(target = StatusBarStateController.class, version = 1), @Requires(target = MiuiQSHost.class, version = 1), @Requires(target = PluginDependency.class, version = 1), @Requires(target = VolumeDialogController.class, version = 1), @Requires(target = ActivityStarter.class, version = 2), @Requires(target = BrightnessControllerBase.class, version = 1), @Requires(target = MiuiSecurityController.class, version = 1), @Requires(target = ControlCenterHeader.class, version = 1), @Requires(target = ShadeHeaderController.class, version = 1), @Requires(target = IUserTracker.class, version = 1), @Requires(target = SuperSaveModeController.class, version = 1), @Requires(target = PluginDumpManager.class, version = 1), @Requires(target = ControlCenterSecondary.class, version = 1)})
public final class MiuiControlCenter extends PluginBase implements ControlCenterPlugin, LifecycleOwner {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "MiuiControlCenter";
    public BlurUtilsExt blurUtilsExt;
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    public MiLinkController miLinkController;
    public SecurityController securityController;
    public ControlCenterWindowViewCreator windowViewCreator;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final BlurUtilsExt getBlurUtilsExt() {
        BlurUtilsExt blurUtilsExt = this.blurUtilsExt;
        if (blurUtilsExt != null) {
            return blurUtilsExt;
        }
        n.w("blurUtilsExt");
        return null;
    }

    public ControlCenterContent getControlCenterContent() throws IllegalAccessException {
        ControlCenterContent controlCenterContentCreateView = getWindowViewCreator().createView();
        if (controlCenterContentCreateView == null) {
            return null;
        }
        Log.e(TAG, "plugin create window view");
        this.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        return controlCenterContentCreateView;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    public final MiLinkController getMiLinkController() {
        MiLinkController miLinkController = this.miLinkController;
        if (miLinkController != null) {
            return miLinkController;
        }
        n.w("miLinkController");
        return null;
    }

    public final SecurityController getSecurityController() {
        SecurityController securityController = this.securityController;
        if (securityController != null) {
            return securityController;
        }
        n.w("securityController");
        return null;
    }

    public final ControlCenterWindowViewCreator getWindowViewCreator() {
        ControlCenterWindowViewCreator controlCenterWindowViewCreator = this.windowViewCreator;
        if (controlCenterWindowViewCreator != null) {
            return controlCenterWindowViewCreator;
        }
        n.w("windowViewCreator");
        return null;
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onCreated() {
        Log.e(TAG, "plugin onCreate");
        ControlCenterPluginInstance.onPluginConnected(this);
        PluginComponentInitializer.getPluginComponent().inject(MiPlayController.INSTANCE);
        PluginComponentInitializer.getPluginComponent().inject(this);
        getMiLinkController().onPluginCreated();
        getSecurityController().onPluginCreated();
        getBlurUtilsExt().onPluginCreated();
        this.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onDestroyed() {
        Log.e(TAG, "plugin onDestroy");
        getWindowViewCreator().onPluginDestroy();
        getMiLinkController().onPluginDestroyed();
        getSecurityController().onPluginDestroyed();
        getBlurUtilsExt().onPluginDestroyed();
        ControlCenterPluginInstance.onPluginDisconnected(this);
        this.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    public final void setBlurUtilsExt(BlurUtilsExt blurUtilsExt) {
        n.g(blurUtilsExt, "<set-?>");
        this.blurUtilsExt = blurUtilsExt;
    }

    public final void setMiLinkController(MiLinkController miLinkController) {
        n.g(miLinkController, "<set-?>");
        this.miLinkController = miLinkController;
    }

    public final void setSecurityController(SecurityController securityController) {
        n.g(securityController, "<set-?>");
        this.securityController = securityController;
    }

    public final void setWindowViewCreator(ControlCenterWindowViewCreator controlCenterWindowViewCreator) {
        n.g(controlCenterWindowViewCreator, "<set-?>");
        this.windowViewCreator = controlCenterWindowViewCreator;
    }
}
