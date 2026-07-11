package miui.systemui.controlcenter.dagger;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.systemui.plugins.PluginDependency;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.miui.controlcenter.BrightnessControllerBase;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterHeader;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterPlugin;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterSecondary;
import com.android.systemui.plugins.miui.dump.PluginDumpManager;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import com.android.systemui.plugins.miui.settings.IUserTracker;
import com.android.systemui.plugins.miui.shade.ShadeHeaderController;
import com.android.systemui.plugins.miui.shade.ShadeSwitchController;
import com.android.systemui.plugins.miui.statusbar.MiuiSecurityController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;

/* JADX INFO: loaded from: classes.dex */
public abstract class ControlCenterPluginInstance {
    private static BrightnessControllerBase sBrightnessController;
    private static ControlCenterHeader sControlCenterHeader;
    private static ControlCenterSecondary sControlCenterSecondary;
    private static PluginDumpManager sDumpManager;
    private static ControlCenterPlugin sPlugin;
    private static MiuiQSHost sQSHost;
    private static MiuiSecurityController sSecurityController;
    private static ShadeHeaderController sShadeHeaderController;
    private static ShadeSwitchController sShadeSwitchController;
    private static StatusBarStateController sStatusBarStateController;
    private static IUserTracker sUserTracker;
    private static VolumeDialogController sVolumeDialogController;

    public static synchronized ControlCenterPlugin getPluginInstance() {
        return sPlugin;
    }

    public static synchronized void onPluginConnected(@NonNull ControlCenterPlugin controlCenterPlugin) {
        sStatusBarStateController = (StatusBarStateController) PluginDependency.get(controlCenterPlugin, StatusBarStateController.class);
        sQSHost = (MiuiQSHost) PluginDependency.get(controlCenterPlugin, MiuiQSHost.class);
        sShadeSwitchController = (ShadeSwitchController) PluginDependency.get(controlCenterPlugin, ShadeSwitchController.class);
        sSecurityController = (MiuiSecurityController) PluginDependency.get(controlCenterPlugin, MiuiSecurityController.class);
        sBrightnessController = (BrightnessControllerBase) PluginDependency.get(controlCenterPlugin, BrightnessControllerBase.class);
        sControlCenterHeader = (ControlCenterHeader) PluginDependency.get(controlCenterPlugin, ControlCenterHeader.class);
        sShadeHeaderController = (ShadeHeaderController) PluginDependency.get(controlCenterPlugin, ShadeHeaderController.class);
        sUserTracker = (IUserTracker) PluginDependency.get(controlCenterPlugin, IUserTracker.class);
        sDumpManager = (PluginDumpManager) PluginDependency.get(controlCenterPlugin, PluginDumpManager.class);
        sVolumeDialogController = (VolumeDialogController) PluginDependency.get(controlCenterPlugin, VolumeDialogController.class);
        sControlCenterSecondary = (ControlCenterSecondary) PluginDependency.get(controlCenterPlugin, ControlCenterSecondary.class);
    }

    public static synchronized void onPluginDisconnected(@NonNull ControlCenterPlugin controlCenterPlugin) {
        try {
            if (sPlugin != controlCenterPlugin) {
                Log.e("ControlCenterPluginInstance", "Disconnected called by wrong plugin instance, there may exist a mem leak.");
            }
            sPlugin = null;
            sStatusBarStateController = null;
            sQSHost = null;
            sShadeSwitchController = null;
            sSecurityController = null;
            sBrightnessController = null;
            sControlCenterHeader = null;
            sShadeHeaderController = null;
            sUserTracker = null;
            sDumpManager = null;
            sVolumeDialogController = null;
            sControlCenterSecondary = null;
        } catch (Throwable th) {
            throw th;
        }
    }

    public static BrightnessControllerBase provideBrightnessControllerBase() {
        return sBrightnessController;
    }

    @Nullable
    public static ControlCenterPlugin provideCCPlugin() {
        return getPluginInstance();
    }

    public static ControlCenterHeader provideControlCenterHeader() {
        return sControlCenterHeader;
    }

    public static ControlCenterSecondary provideControlCenterSecondary() {
        return sControlCenterSecondary;
    }

    public static MiuiSecurityController provideMiuiSecurityController() {
        return sSecurityController;
    }

    public static PluginDumpManager providePluginDumpManager() {
        return sDumpManager;
    }

    public static MiuiQSHost provideQSHost() {
        return sQSHost;
    }

    public static ShadeHeaderController provideShadeHeaderController() {
        return sShadeHeaderController;
    }

    public static ShadeSwitchController provideShadeSwitchController() {
        return sShadeSwitchController;
    }

    public static StatusBarStateController provideStatusBarStateController() {
        return sStatusBarStateController;
    }

    public static IUserTracker provideUserTracker() {
        return sUserTracker;
    }

    public static VolumeDialogController provideVolumeDialogController() {
        return sVolumeDialogController;
    }
}
