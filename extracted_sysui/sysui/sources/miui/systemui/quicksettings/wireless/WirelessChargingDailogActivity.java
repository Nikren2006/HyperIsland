package miui.systemui.quicksettings.wireless;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;
import miui.systemui.quicksettings.R;
import miuix.android.content.MiuiIntent;
import miuix.appcompat.app.AlertDialog;
import miuix.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes4.dex */
public class WirelessChargingDailogActivity extends AppCompatActivity {
    private static final String ACTION_POWER_WIRELESS_REVERSE_LIST = "miui.intent.action.POWER_WIRELESS_REVERSE_LIST";
    private static final String ACTION_WIRELESS_CHARGING = "miui.intent.action.ACTION_WIRELESS_CHARGING";
    private static final int BATTERY_LEVEL_DIALOG = 5;
    private static final int BATTERY_LEVEL_LOW_DIALOG = 4;
    private static final int CONFIRM_DIALOG = 1;
    private static final String DISABLE_SHOW_TIPS = "disable_show_tips";
    private static final String EXTRA_WIRELESS_CHARGING = "miui.intent.extra.WIRELESS_CHARGING";
    private static final int SAVE_MODE_DIALOG = 2;
    private static final int WIRELESS_DAILOG = 3;
    private static final String WIRELESS_REVERSE_CHARGING = "wireless_reverse_charging";
    private AlertDialog mBatteryLevelDialog;
    private AlertDialog mBatteryLevelLowDialog;
    private CheckBox mCheckbox;
    private DialogInterface.OnClickListener mCommonClickListener;
    private DialogInterface.OnDismissListener mCommonDismissListener;
    private DialogInterface.OnClickListener mConfirmClickListener;
    private AlertDialog mConfirmDialog;
    private DialogInterface.OnDismissListener mConfirmDismissListener;
    private Context mContext;
    private DialogInterface.OnClickListener mFgClickListener;
    private DialogInterface.OnClickListener mSaveModeClickListener;
    private AlertDialog mSaveModeDialog;
    private AlertDialog mWirelessDialog;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSaveModeOn() {
        return Settings.System.getInt(getContentResolver(), MiuiIntent.EXTRA_POWER_SAVE_MODE_OPEN, 0) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUpdateStatusBroadCast(int i2) {
        Intent intent = new Intent(ACTION_WIRELESS_CHARGING);
        intent.addFlags(822083584);
        intent.putExtra(EXTRA_WIRELESS_CHARGING, i2);
        sendStickyBroadcastAsUser(intent, UserHandle.ALL);
    }

    private void showBatteryLevelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_WirelessDialog);
        builder.setMessage(getString(R.string.wireless_charging_fg_control_messgae, new Object[]{Integer.valueOf(Settings.Global.getInt(getContentResolver(), WIRELESS_REVERSE_CHARGING, 30))})).setCancelable(false).setPositiveButton(getString(R.string.wireless_charging_fg_control_ok), this.mFgClickListener).setNegativeButton(getString(R.string.wireless_charging_fg_control_cancel), this.mFgClickListener).setOnDismissListener(this.mCommonDismissListener);
        AlertDialog alertDialogCreate = builder.create();
        this.mBatteryLevelDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    private void showBatteryLevelLowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_WirelessDialog);
        builder.setMessage(getString(R.string.wireless_charging_low_battery_level_message, new Object[]{Integer.valueOf(Settings.Global.getInt(getContentResolver(), WIRELESS_REVERSE_CHARGING, 0) > 0 ? 20 : 30)})).setCancelable(true).setPositiveButton(getString(R.string.wireless_charging_ok), this.mCommonClickListener).setOnDismissListener(this.mCommonDismissListener);
        AlertDialog alertDialogCreate = builder.create();
        this.mBatteryLevelLowDialog = alertDialogCreate;
        alertDialogCreate.show();
        sendUpdateStatusBroadCast(1);
    }

    private void showConfirmDialog() {
        if (Settings.System.getInt(getContentResolver(), DISABLE_SHOW_TIPS, 0) == 1) {
            if (isSaveModeOn()) {
                showSaveModeDialog();
                return;
            }
            WirelessChargingUtil.setWirelessSwitchEnabled(true, this.mContext);
            sendUpdateStatusBroadCast(0);
            finish();
            return;
        }
        View viewInflate = View.inflate(this, R.layout.wireless_charging_tip, null);
        this.mCheckbox = (CheckBox) viewInflate.findViewById(R.id.checkbox);
        ((TextView) viewInflate.findViewById(R.id.wireless_charging_message)).setText(getString(R.string.wireless_charging_tips_message, new Object[]{Integer.valueOf(Settings.Global.getInt(getContentResolver(), WIRELESS_REVERSE_CHARGING, 30))}));
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_WirelessDialog);
        builder.setTitle(getString(R.string.wireless_charging_tips_title)).setView(viewInflate).setCancelable(false).setPositiveButton(getString(R.string.wireless_charging_ok), this.mConfirmClickListener).setOnDismissListener(this.mConfirmDismissListener);
        AlertDialog alertDialogCreate = builder.create();
        this.mConfirmDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSaveModeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_WirelessDialog);
        builder.setMessage(getString(R.string.wireless_charging_saver_message)).setCancelable(false).setPositiveButton(getString(R.string.wireless_charging_saver_ok), this.mSaveModeClickListener).setNegativeButton(getString(R.string.wireless_charging_saver_cancel), this.mSaveModeClickListener).setOnDismissListener(this.mCommonDismissListener);
        AlertDialog alertDialogCreate = builder.create();
        this.mSaveModeDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    private void showWirelessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_WirelessDialog);
        builder.setMessage(getString(R.string.wireless_charging_connected_message)).setCancelable(true).setPositiveButton(getString(R.string.wireless_charging_ok), this.mCommonClickListener).setOnDismissListener(this.mCommonDismissListener);
        AlertDialog alertDialogCreate = builder.create();
        this.mWirelessDialog = alertDialogCreate;
        alertDialogCreate.show();
        sendUpdateStatusBroadCast(1);
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        if (getAppCompatActionBar() != null) {
            getAppCompatActionBar().hide();
        }
        getWindow().getDecorView().setAlpha(0.0f);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        getWindow().setAttributes(attributes);
        int intExtra = getIntent().getIntExtra("dialogSelected", -1);
        this.mConfirmClickListener = new DialogInterface.OnClickListener() { // from class: miui.systemui.quicksettings.wireless.WirelessChargingDailogActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                boolean z2 = i2 == -1;
                Settings.System.putInt(WirelessChargingDailogActivity.this.getContentResolver(), WirelessChargingDailogActivity.DISABLE_SHOW_TIPS, (z2 && WirelessChargingDailogActivity.this.mCheckbox.isChecked()) ? 1 : 0);
                if (z2) {
                    if (WirelessChargingDailogActivity.this.isSaveModeOn()) {
                        WirelessChargingDailogActivity.this.showSaveModeDialog();
                        return;
                    }
                    WirelessChargingUtil.setWirelessSwitchEnabled(true, WirelessChargingDailogActivity.this.mContext);
                    WirelessChargingDailogActivity.this.sendUpdateStatusBroadCast(0);
                    WirelessChargingDailogActivity.this.finish();
                }
            }
        };
        this.mSaveModeClickListener = new DialogInterface.OnClickListener() { // from class: miui.systemui.quicksettings.wireless.WirelessChargingDailogActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (i2 == -1) {
                    WirelessChargingUtil.setWirelessSwitchEnabled(true, WirelessChargingDailogActivity.this.mContext);
                    WirelessChargingDailogActivity.this.sendUpdateStatusBroadCast(0);
                } else {
                    WirelessChargingDailogActivity.this.sendUpdateStatusBroadCast(1);
                }
                WirelessChargingDailogActivity.this.finish();
            }
        };
        this.mFgClickListener = new DialogInterface.OnClickListener() { // from class: miui.systemui.quicksettings.wireless.WirelessChargingDailogActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (i2 == -1) {
                    Intent intent = new Intent(WirelessChargingDailogActivity.ACTION_POWER_WIRELESS_REVERSE_LIST);
                    intent.addFlags(268435456);
                    WirelessChargingDailogActivity.this.startActivityAsUser(intent, UserHandle.CURRENT);
                } else {
                    WirelessChargingDailogActivity.this.sendUpdateStatusBroadCast(1);
                }
                WirelessChargingDailogActivity.this.finish();
            }
        };
        this.mCommonClickListener = new DialogInterface.OnClickListener() { // from class: miui.systemui.quicksettings.wireless.WirelessChargingDailogActivity.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                WirelessChargingDailogActivity.this.finish();
            }
        };
        this.mConfirmDismissListener = new DialogInterface.OnDismissListener() { // from class: miui.systemui.quicksettings.wireless.WirelessChargingDailogActivity.5
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (WirelessChargingDailogActivity.this.isSaveModeOn()) {
                    return;
                }
                WirelessChargingDailogActivity.this.finish();
            }
        };
        this.mCommonDismissListener = new DialogInterface.OnDismissListener() { // from class: miui.systemui.quicksettings.wireless.WirelessChargingDailogActivity.6
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                WirelessChargingDailogActivity.this.finish();
            }
        };
        if (intExtra == 1) {
            showConfirmDialog();
            return;
        }
        if (intExtra == 2) {
            showSaveModeDialog();
            return;
        }
        if (intExtra == 3) {
            showWirelessDialog();
        } else if (intExtra == 4) {
            showBatteryLevelLowDialog();
        } else if (intExtra == 5) {
            showBatteryLevelDialog();
        }
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AlertDialog alertDialog = this.mConfirmDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        AlertDialog alertDialog2 = this.mSaveModeDialog;
        if (alertDialog2 != null) {
            alertDialog2.dismiss();
        }
        AlertDialog alertDialog3 = this.mWirelessDialog;
        if (alertDialog3 != null) {
            alertDialog3.dismiss();
        }
        AlertDialog alertDialog4 = this.mBatteryLevelLowDialog;
        if (alertDialog4 != null) {
            alertDialog4.dismiss();
        }
        AlertDialog alertDialog5 = this.mBatteryLevelDialog;
        if (alertDialog5 != null) {
            alertDialog5.dismiss();
        }
    }
}
