package miui.systemui.quicksettings.wireless;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;
import com.android.systemui.miui.volume.VolumePanelViewController;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.qs.MiuiQSTile;
import com.android.systemui.plugins.miui.qs.MiuiQSTilePlugin;
import com.android.systemui.plugins.qs.QSTile;
import java.util.ArrayList;
import java.util.Iterator;
import miui.systemui.quicksettings.DrawableIcon;
import miui.systemui.quicksettings.R;

/* JADX INFO: loaded from: classes4.dex */
@Requires(target = MiuiQSTilePlugin.class, version = 1)
public class MiuiWirelessPowerTile implements MiuiQSTile {
    private static final String ACTION_POWER_WIRELESS_REVERSE_LIST = "miui.intent.action.POWER_WIRELESS_REVERSE_LIST";
    private static final String ACTION_WIRELESS_CHARGING = "miui.intent.action.ACTION_WIRELESS_CHARGING";
    private static final String ACTION_WIRELESS_CHG_CONFIRM_ACTIVITY = "miui.intent.action.ACTIVITY_WIRELESS_CHG_DIALOG";
    private static final int BATTERY_LEVEL_DIALOG = 5;
    private static final int BATTERY_LEVEL_LOW_DIALOG = 4;
    private static final int CONFIRM_DIALOG = 1;
    private static final boolean DEBUG = false;
    private static final String EXTRA_WIRELESS_CHARGING = "miui.intent.extra.WIRELESS_CHARGING";
    private static final int GAME_PHONE_CASE_TYPE = 3;
    private static final String KEY_FOR_PHONE_CASE_STATE = "phone_case_status";
    private static final int SAVE_MODE_DIALOG = 2;
    private static final String TAG = "MiuiWirelessPowerTile";
    private static final String TILE_SPEC = "wirelesspower";
    private static final int WIRELESS_DAILOG = 3;
    private static final String WIRELESS_REVERSE_CHARGING = "wireless_reverse_charging";
    private static final String WIRELESS_REVERSE_CHG_SETTINGS = "com.miui.securitycenter.action.POWER_SETTINGS";
    private ArrayList<QSTile.Callback> mCallbacks;
    private ContentResolver mContentResolver;
    private boolean mListening;
    private Context mPluginContext;
    private final Receiver mReceiver = new Receiver();
    private QSTile.State mState;
    private boolean mSupportWirelessCharge;
    private Context mSysuiContext;

    public final class Receiver extends BroadcastReceiver {
        private boolean mRegistered;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MiuiWirelessPowerTile.this.fireCallback();
        }

        public void setListening(boolean z2) {
            if (z2 && !this.mRegistered) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(MiuiWirelessPowerTile.ACTION_WIRELESS_CHARGING);
                MiuiWirelessPowerTile.this.mPluginContext.registerReceiver(this, intentFilter, 2);
                this.mRegistered = true;
                return;
            }
            if (z2 || !this.mRegistered) {
                return;
            }
            MiuiWirelessPowerTile.this.mPluginContext.unregisterReceiver(this);
            this.mRegistered = false;
        }

        private Receiver() {
        }
    }

    public MiuiWirelessPowerTile(Context context, Context context2) {
        this.mSysuiContext = context;
        this.mPluginContext = context2;
        QSTile.State state = new QSTile.State();
        this.mState = state;
        state.state = 1;
        this.mCallbacks = new ArrayList<>();
        this.mSupportWirelessCharge = WirelessChargingUtil.isWirelessChargingSupported();
        this.mContentResolver = this.mPluginContext.getContentResolver();
    }

    private void collapseStatusBar(Context context) {
        try {
            Object systemService = context.getSystemService(VolumePanelViewController.SERVICE_STATUS_BAR);
            systemService.getClass().getMethod("collapsePanels", null).invoke(systemService, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fireCallback() {
        synchronized (this.mCallbacks) {
            try {
                Iterator<QSTile.Callback> it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    it.next().onStateChanged(this.mState);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private boolean isLowBatteryLevelOrWirelessCharged() {
        Intent intentRegisterReceiver = this.mPluginContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"), 2);
        if (intentRegisterReceiver != null) {
            int intExtra = intentRegisterReceiver.getIntExtra("plugged", -1);
            int intExtra2 = intentRegisterReceiver.getIntExtra(com.xiaomi.onetrack.b.a.f3024d, -1);
            if (4 == intExtra) {
                showConfirmDialog(3);
                return true;
            }
            if (intExtra <= 0) {
                int i2 = Settings.Global.getInt(this.mContentResolver, WIRELESS_REVERSE_CHARGING, 0);
                if (i2 > 0) {
                    if (intExtra2 < 20) {
                        showConfirmDialog(4);
                        return true;
                    }
                    if (intExtra2 < i2) {
                        showConfirmDialog(5);
                        return true;
                    }
                } else if (intExtra2 < 30) {
                    showConfirmDialog(4);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isPhoneCaseOn() {
        if (Settings.Global.getInt(this.mContentResolver, KEY_FOR_PHONE_CASE_STATE, -1) != 3) {
            return false;
        }
        Log.d(TAG, "not allow wls reverse charge start cause phone case on");
        return true;
    }

    private void showConfirmDialog(int i2) {
        try {
            Intent intent = new Intent(ACTION_WIRELESS_CHG_CONFIRM_ACTIVITY);
            intent.addFlags(268435456);
            intent.putExtra("dialogSelected", i2);
            this.mPluginContext.startActivityAsUser(intent, UserHandle.CURRENT);
        } catch (Exception e2) {
            Log.e(TAG, "show confirm dialog error" + e2);
        }
    }

    private void showPhoneCaseDisallowWlsReverseChgToast() {
        Toast.makeText(this.mPluginContext, R.string.phone_case_disassembly_prompt, 0).show();
    }

    private void showWiredChargingNotSupportDialog() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.powercenter.wirelesscharge.WirelessChargingDialogActivity"));
            intent.addFlags(268435456);
            intent.putExtra("dialogSelected", 5);
            this.mPluginContext.startActivityAsUser(intent, UserHandle.CURRENT);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void addCallback(QSTile.Callback callback) {
        synchronized (this.mCallbacks) {
            try {
                if (!this.mCallbacks.contains(callback)) {
                    this.mCallbacks.add(callback);
                    callback.onStateChanged(this.mState);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String composeChangeAnnouncement() {
        return null;
    }

    public Intent getLongClickIntent() {
        return Settings.Global.getInt(this.mContentResolver, WIRELESS_REVERSE_CHARGING, 0) > 0 ? new Intent(ACTION_POWER_WIRELESS_REVERSE_LIST) : new Intent("com.miui.securitycenter.action.POWER_SETTINGS");
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
        setWirelessChargingEnabled(!WirelessChargingUtil.isWirelessChargingEnabled());
        collapseStatusBar(this.mPluginContext);
        refreshState(null);
    }

    public boolean isAvailable() {
        return this.mSupportWirelessCharge;
    }

    public QSTile.State newTileState() {
        return this.mState;
    }

    public void refreshState(Object obj) {
        this.mState.label = this.mPluginContext.getString(R.string.quick_settings_wireless_label);
        if (WirelessChargingUtil.isWirelessChargingEnabled()) {
            this.mState.icon = new DrawableIcon(this.mPluginContext.getResources().getDrawable(R.drawable.ic_qs_wireless_chg_enabled));
            this.mState.state = 2;
        } else {
            this.mState.icon = new DrawableIcon(this.mPluginContext.getResources().getDrawable(R.drawable.ic_qs_wireless_chg_disabled));
            this.mState.state = 1;
        }
        this.mState.expandedAccessibilityClassName = Switch.class.getName();
        QSTile.State state = this.mState;
        state.contentDescription = state.label;
    }

    public void removeCallback(QSTile.Callback callback) {
        synchronized (this.mCallbacks) {
            this.mCallbacks.remove(callback);
        }
    }

    public void sendUpdateStatusBroadCast(int i2) {
        Intent intent = new Intent(ACTION_WIRELESS_CHARGING);
        intent.addFlags(822083584);
        intent.putExtra(EXTRA_WIRELESS_CHARGING, i2);
        this.mPluginContext.sendBroadcastAsUser(intent, UserHandle.ALL);
    }

    public void setListening(boolean z2) {
        if (this.mListening == z2) {
            return;
        }
        this.mListening = z2;
        if (!z2) {
            this.mReceiver.setListening(false);
        } else {
            this.mReceiver.setListening(true);
            refreshState(null);
        }
    }

    public void setWirelessChargingEnabled(boolean z2) {
        if (!z2) {
            WirelessChargingUtil.setWirelessSwitchEnabled(false, this.mPluginContext);
            sendUpdateStatusBroadCast(1);
        } else {
            if (isPhoneCaseOn()) {
                showPhoneCaseDisallowWlsReverseChgToast();
                return;
            }
            if (isLowBatteryLevelOrWirelessCharged()) {
                return;
            }
            if (WirelessChargingUtil.isSupportNoReverseBox() && WirelessChargingUtil.isWiredCharging(this.mPluginContext)) {
                showWiredChargingNotSupportDialog();
            } else {
                showConfirmDialog(1);
            }
        }
    }
}
