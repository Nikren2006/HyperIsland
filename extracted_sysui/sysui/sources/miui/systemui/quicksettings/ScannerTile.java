package miui.systemui.quicksettings;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.widget.Button;
import com.android.systemui.miui.volume.VolumePanelViewController;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.annotations.Requirements;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.qs.MiuiQSTile;
import com.android.systemui.plugins.miui.qs.MiuiQSTilePlugin;
import com.android.systemui.plugins.qs.QSTile;
import miui.os.Build;
import miui.systemui.quicksettings.hearingassist.HearingAssistConstant;
import miui.systemui.util.ReflectBuilderUtil;

/* JADX INFO: loaded from: classes4.dex */
@Requirements({@Requires(target = MiuiQSTilePlugin.class, version = 1), @Requires(target = ActivityStarter.class, version = 2)})
public class ScannerTile implements MiuiQSTile {
    private static final String DELETED_SYSTEM_APPS_SETTINGS = "com.miui.home.settings.action.DELETED_SYSTEM_APPS_SETTINGS";
    private static final String PACKAGE_NAME_MARKET = "com.xiaomi.market";
    private static final String PACKAGE_NAME_SCANNER = "com.xiaomi.scanner";
    private static final String TAG = "ScannerTile";
    private static final String TILE_SPEC = "scanner";
    private E0.a mActivityStarter;
    private Context mPluginContext;
    private QSTile.State mState;
    private Context mSysuiContext;

    public ScannerTile(Context context, Context context2, E0.a aVar) {
        this.mSysuiContext = context;
        this.mPluginContext = context2;
        this.mActivityStarter = aVar;
        QSTile.State state = new QSTile.State();
        this.mState = state;
        state.state = 1;
    }

    private void collapseStatusBar(Context context) {
        try {
            Object systemService = context.getSystemService(VolumePanelViewController.SERVICE_STATUS_BAR);
            systemService.getClass().getMethod("collapsePanels", null).invoke(systemService, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private Intent genDownloadIntent() {
        ApplicationInfo applicationInfo;
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.xiaomi.scanner"));
        try {
            applicationInfo = this.mPluginContext.getPackageManager().getApplicationInfo(PACKAGE_NAME_MARKET, 0);
        } catch (Exception unused) {
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            intent = new Intent(DELETED_SYSTEM_APPS_SETTINGS);
        }
        intent.addFlags(268435456);
        return intent;
    }

    private Intent genStartScannerIntent() {
        Intent intent = new Intent();
        intent.setClassName(PACKAGE_NAME_SCANNER, "com.xiaomi.scanner.app.ScanActivity");
        intent.setAction("android.intent.action.MAIN");
        intent.setFlags(270532608);
        intent.putExtra(HearingAssistConstant.KEY_CONSTANT_FROM_APP, "miui.systemui.plugin");
        return intent;
    }

    public void addCallback(QSTile.Callback callback) {
    }

    public String composeChangeAnnouncement() {
        return null;
    }

    public Intent getLongClickIntent() {
        return null;
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
        refreshState(null);
        ActivityStarter activityStarter = (ActivityStarter) this.mActivityStarter.get();
        try {
            int currentUserId = ReflectBuilderUtil.getCurrentUserId();
            UserHandle userHandle = ReflectBuilderUtil.getUserHandle(currentUserId);
            PackageManager packageManager = this.mSysuiContext.getPackageManager();
            Class cls = Integer.TYPE;
            Intent intentGenDownloadIntent = ReflectBuilderUtil.callObjectMethod(packageManager, "resolveActivityAsUser", new Class[]{Intent.class, cls, cls}, new Intent("miui.intent.action.scanner"), 65536, Integer.valueOf(currentUserId)) == null ? genDownloadIntent() : genStartScannerIntent();
            if (activityStarter == null) {
                ReflectBuilderUtil.callObjectMethod(this.mSysuiContext, "startActivityAsUser", new Class[]{Intent.class, Bundle.class, UserHandle.class}, intentGenDownloadIntent, intentGenDownloadIntent.getExtras(), userHandle);
            } else {
                activityStarter.postStartActivityDismissingKeyguard(intentGenDownloadIntent, 0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            Intent intentGenDownloadIntent2 = genDownloadIntent();
            if (activityStarter == null) {
                this.mSysuiContext.startActivity(intentGenDownloadIntent2);
            } else {
                activityStarter.postStartActivityDismissingKeyguard(intentGenDownloadIntent2, 0);
            }
        }
    }

    public boolean isAvailable() {
        if (!Build.IS_INTERNATIONAL_BUILD) {
            return true;
        }
        try {
            return this.mSysuiContext.getPackageManager().getPackageInfo(PACKAGE_NAME_SCANNER, 0) != null;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e(TAG, "isAvailable() exception: ", e2);
            return false;
        }
    }

    public QSTile.State newTileState() {
        return this.mState;
    }

    public void refreshState(Object obj) {
        QSTile.State state = this.mState;
        state.state = 1;
        state.label = this.mPluginContext.getString(R.string.quick_settings_scanner_label);
        this.mState.icon = new DrawableIcon(this.mPluginContext.getResources().getDrawable(R.drawable.ic_qs_scanner, null));
        this.mState.expandedAccessibilityClassName = Button.class.getName();
        QSTile.State state2 = this.mState;
        state2.contentDescription = state2.label;
    }

    public void removeCallback(QSTile.Callback callback) {
    }

    public void setListening(boolean z2) {
    }
}
