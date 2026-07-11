package miui.systemui.quicksettings.wireless;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.WindowManager;
import miui.systemui.quicksettings.R;
import miuix.appcompat.app.AlertDialog;
import miuix.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes4.dex */
public class WirelessChargingWarningActivity extends AppCompatActivity {
    private static final String ACTION_WIRELESS_CHARGING = "miui.intent.action.ACTION_WIRELESS_CHARGING";
    private static final String EXTRA_WIRELESS_CHARGING = "miui.intent.extra.WIRELESS_CHARGING";
    private DialogInterface.OnClickListener onClickListener;
    private DialogInterface.OnDismissListener onDismissListener;

    private void sendUpdateStatusBroadCast() {
        Intent intent = new Intent(ACTION_WIRELESS_CHARGING);
        intent.addFlags(822083584);
        intent.putExtra(EXTRA_WIRELESS_CHARGING, 1);
        getApplicationContext().sendStickyBroadcastAsUser(intent, UserHandle.ALL);
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getAppCompatActionBar() != null) {
            getAppCompatActionBar().hide();
        }
        getWindow().getDecorView().setAlpha(0.0f);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        getWindow().setAttributes(attributes);
        int intExtra = getIntent().getIntExtra("plugstatus", -1);
        this.onClickListener = new DialogInterface.OnClickListener() { // from class: miui.systemui.quicksettings.wireless.WirelessChargingWarningActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                WirelessChargingWarningActivity.this.finish();
            }
        };
        this.onDismissListener = new DialogInterface.OnDismissListener() { // from class: miui.systemui.quicksettings.wireless.WirelessChargingWarningActivity.2
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                WirelessChargingWarningActivity.this.finish();
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_WirelessDialog);
        builder.setMessage(intExtra == 4 ? getString(R.string.wireless_charging_connected_message) : getString(R.string.wireless_charging_low_battery_level_message, new Object[]{30})).setCancelable(true).setPositiveButton(getString(R.string.wireless_charging_ok), this.onClickListener).setOnDismissListener(this.onDismissListener);
        builder.create().show();
        sendUpdateStatusBroadCast();
    }
}
