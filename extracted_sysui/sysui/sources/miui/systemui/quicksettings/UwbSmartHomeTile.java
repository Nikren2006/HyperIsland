package miui.systemui.quicksettings;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Switch;
import com.android.systemui.miui.volume.VolumePanelViewController;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.qs.MiuiQSTile;
import com.android.systemui.plugins.miui.qs.MiuiQSTilePlugin;
import com.android.systemui.plugins.qs.QSTile;

/* JADX INFO: loaded from: classes4.dex */
@Requires(target = MiuiQSTilePlugin.class, version = 1)
public class UwbSmartHomeTile implements MiuiQSTile {
    private static final boolean DEBUG = false;
    private static final String TAG = "UwbSmartHomeTile";
    private static final String TILE_SPEC = "uwb_shp";
    private static final String UWB_PACKAGENAME = "com.miui.smarthomeplus";
    private Context mPluginContext;
    private QSTile.State mState;
    private Context mSysuiContext;

    public UwbSmartHomeTile(Context context, Context context2) {
        this.mSysuiContext = context;
        this.mPluginContext = context2;
        QSTile.State state = new QSTile.State();
        this.mState = state;
        state.state = 1;
    }

    public static boolean canUseUwb(Context context) {
        PackageInfo packageInfo;
        PackageManager packageManager = context.getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo(UWB_PACKAGENAME, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return (packageInfo == null && getLauncherIntent().resolveActivityInfo(packageManager, 0) == null) ? false : true;
    }

    private void collapseStatusBar(Context context) {
        try {
            Object systemService = context.getSystemService(VolumePanelViewController.SERVICE_STATUS_BAR);
            systemService.getClass().getMethod("collapsePanels", null).invoke(systemService, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static Intent getLauncherIntent() {
        return new Intent().setClassName(UWB_PACKAGENAME, "com.miui.smarthomeplus.UWBEntryActivity");
    }

    public void addCallback(QSTile.Callback callback) {
    }

    public String composeChangeAnnouncement() {
        return null;
    }

    public Intent getLongClickIntent() {
        return new Intent("com.miui.smarthomeplus.uwb_settings");
    }

    public int getMetricsCategory() {
        return 0;
    }

    public QSTile.State getState() {
        return this.mState;
    }

    public String getTileSpec() {
        return TILE_SPEC;
    }

    public void handleClick() {
        collapseStatusBar(this.mPluginContext);
        this.mSysuiContext.startActivity(getLauncherIntent().putExtra("from", "UWBQSTileService").addFlags(268435456));
    }

    public boolean isAvailable() {
        return canUseUwb(this.mPluginContext);
    }

    public QSTile.State newTileState() {
        return this.mState;
    }

    public void refreshState(Object obj) {
        QSTile.State state = this.mState;
        state.state = 1;
        state.label = this.mPluginContext.getString(R.string.quick_settings_uwbsh_label);
        this.mState.icon = new DrawableIcon(this.mPluginContext.getResources().getDrawable(R.drawable.ic_qs_uwb_smarthome, null));
        this.mState.expandedAccessibilityClassName = Switch.class.getName();
        QSTile.State state2 = this.mState;
        state2.contentDescription = state2.label;
    }

    public void removeCallback(QSTile.Callback callback) {
    }

    public void setListening(boolean z2) {
    }
}
