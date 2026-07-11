package miui.systemui.globalactions;

import android.content.Context;
import android.util.Log;
import com.android.systemui.miui.globalactions.MiuiGlobalActionsDialog;
import com.android.systemui.plugins.GlobalActions;
import com.android.systemui.plugins.annotations.DependsOn;
import com.android.systemui.plugins.annotations.Requires;
import miui.systemui.plugins.PluginBase;

/* JADX INFO: loaded from: classes3.dex */
@DependsOn(target = GlobalActions.GlobalActionsManager.class)
@Requires(target = GlobalActions.class, version = 1)
public class GlobalActionsPlugin extends PluginBase implements GlobalActions {
    private static final String TAG = "GlobalActionsPlugin";
    private MiuiGlobalActionsDialog mGlobalActions;
    private Context mPluginContext;
    private Context mSysUIContext;

    @Override // miui.systemui.plugins.PluginBase
    public void onCreated() {
        this.mSysUIContext = getSysuiContext();
        this.mPluginContext = getPluginContext();
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onDestroyed() {
        MiuiGlobalActionsDialog miuiGlobalActionsDialog = this.mGlobalActions;
        if (miuiGlobalActionsDialog != null) {
            miuiGlobalActionsDialog.destroy();
        }
        this.mGlobalActions = null;
    }

    public void showGlobalActions(GlobalActions.GlobalActionsManager globalActionsManager) {
        Log.d(TAG, "showGlobalActions");
        if (this.mGlobalActions == null) {
            this.mGlobalActions = new MiuiGlobalActionsDialog(this.mPluginContext, this.mSysUIContext, globalActionsManager);
        }
        this.mGlobalActions.showDialog();
    }
}
