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
public class WirelessChargingConfirmActivity extends AppCompatActivity {
    private static final String ACTION_WIRELESS_CHARGING = "miui.intent.action.ACTION_WIRELESS_CHARGING";
    private static final String EXTRA_WIRELESS_CHARGING = "miui.intent.extra.WIRELESS_CHARGING";
    private DialogInterface.OnDismissListener OnFirstDismissListener;
    private DialogInterface.OnDismissListener OnSecondDismissListener;
    private CheckBox mCheckbox;
    private Context mContext;
    private AlertDialog mFirstDialog;
    private AlertDialog mSecondDialog;
    private DialogInterface.OnClickListener onFirstClickListener;
    private DialogInterface.OnClickListener onSecondClickListener;

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

    /* JADX INFO: Access modifiers changed from: private */
    public void showSecondConfirmDiaglog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_WirelessDialog);
        builder.setMessage(getString(R.string.wireless_charging_saver_message)).setCancelable(false).setPositiveButton(getString(R.string.wireless_charging_saver_ok), this.onSecondClickListener).setNegativeButton(getString(R.string.wireless_charging_saver_cancel), this.onSecondClickListener).setOnDismissListener(this.OnSecondDismissListener);
        AlertDialog alertDialogCreate = builder.create();
        this.mSecondDialog = alertDialogCreate;
        alertDialogCreate.show();
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
        this.onFirstClickListener = new DialogInterface.OnClickListener() { // from class: miui.systemui.quicksettings.wireless.WirelessChargingConfirmActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                boolean z2 = i2 == -1;
                Settings.System.putInt(WirelessChargingConfirmActivity.this.getContentResolver(), "disable_show_tips", (z2 && WirelessChargingConfirmActivity.this.mCheckbox.isChecked()) ? 1 : 0);
                if (z2) {
                    if (WirelessChargingConfirmActivity.this.isSaveModeOn()) {
                        WirelessChargingConfirmActivity.this.showSecondConfirmDiaglog();
                        return;
                    }
                    WirelessChargingUtil.setWirelessSwitchEnabled(true, WirelessChargingConfirmActivity.this.mContext);
                    WirelessChargingConfirmActivity.this.sendUpdateStatusBroadCast(0);
                    WirelessChargingConfirmActivity.this.finish();
                }
            }
        };
        this.onSecondClickListener = new DialogInterface.OnClickListener() { // from class: miui.systemui.quicksettings.wireless.WirelessChargingConfirmActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (i2 == -1) {
                    WirelessChargingUtil.setWirelessSwitchEnabled(true, WirelessChargingConfirmActivity.this.mContext);
                    WirelessChargingConfirmActivity.this.sendUpdateStatusBroadCast(0);
                } else {
                    WirelessChargingConfirmActivity.this.sendUpdateStatusBroadCast(1);
                }
                WirelessChargingConfirmActivity.this.finish();
            }
        };
        this.OnFirstDismissListener = new DialogInterface.OnDismissListener() { // from class: miui.systemui.quicksettings.wireless.WirelessChargingConfirmActivity.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (WirelessChargingConfirmActivity.this.isSaveModeOn()) {
                    return;
                }
                WirelessChargingConfirmActivity.this.finish();
            }
        };
        this.OnSecondDismissListener = new DialogInterface.OnDismissListener() { // from class: miui.systemui.quicksettings.wireless.WirelessChargingConfirmActivity.4
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                WirelessChargingConfirmActivity.this.finish();
            }
        };
        if (Settings.System.getInt(getContentResolver(), "disable_show_tips", 0) == 1) {
            if (isSaveModeOn()) {
                showSecondConfirmDiaglog();
                return;
            }
            WirelessChargingUtil.setWirelessSwitchEnabled(true, this.mContext);
            sendUpdateStatusBroadCast(0);
            finish();
            return;
        }
        View viewInflate = View.inflate(this, R.layout.wireless_charging_tip, null);
        this.mCheckbox = (CheckBox) viewInflate.findViewById(R.id.checkbox);
        ((TextView) viewInflate.findViewById(R.id.wireless_charging_message)).setText(getString(R.string.wireless_charging_tips_message, new Object[]{30}));
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_WirelessDialog);
        builder.setTitle(getString(R.string.wireless_charging_tips_title)).setView(viewInflate).setCancelable(false).setPositiveButton(getString(R.string.wireless_charging_ok), this.onFirstClickListener).setOnDismissListener(this.OnFirstDismissListener);
        AlertDialog alertDialogCreate = builder.create();
        this.mFirstDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AlertDialog alertDialog = this.mFirstDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        AlertDialog alertDialog2 = this.mSecondDialog;
        if (alertDialog2 != null) {
            alertDialog2.dismiss();
        }
    }
}
