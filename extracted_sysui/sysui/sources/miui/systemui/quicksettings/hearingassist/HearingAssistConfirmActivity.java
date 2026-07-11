package miui.systemui.quicksettings.hearingassist;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import miui.systemui.quicksettings.R;
import miuix.appcompat.app.ActionBar;
import miuix.appcompat.app.AlertDialog;
import miuix.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes4.dex */
public class HearingAssistConfirmActivity extends AppCompatActivity {
    private static final String TAG = "HearingAssistConfirmActivity";
    private AlertDialog mBtDialog;
    private boolean mBtDialogShowing = false;
    private AlertDialog mFirstDialog;

    private void StartHearingAssistService() {
        Intent intent = new Intent();
        intent.setClassName(HearingAssistConstant.MISOUND_PACKAGE_NAME, HearingAssistConstant.MISOUND_SERVICE_NAME);
        intent.putExtra(HearingAssistConstant.KEY_CONSTANT_FROM_APP, "miui.systemui.plugin");
        startForegroundService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showFirstDialog$0(DialogInterface dialogInterface, int i2) {
        Settings.System.putInt(getContentResolver(), HearingAssistConstant.DISABLE_SHOW_TIPS, 1);
        boolean zCheckRecordPermission = HearingAssistUtil.checkRecordPermission(getApplicationContext());
        Log.d(TAG, "handleClick: permission granted " + zCheckRecordPermission);
        if (!zCheckRecordPermission) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(HearingAssistConstant.MISOUND_PACKAGE_NAME, HearingAssistConstant.MISOUND_HEARING_ASSIST_ACTIVITY));
            intent.putExtra(HearingAssistConstant.KEY_CONSTANT_FROM_APP, "miui.systemui.plugin");
            startActivity(intent);
            finish();
            return;
        }
        if (HearingAssistUtil.isBluetoothConnected()) {
            Log.d(TAG, "onClick StartHearingAssistService");
            StartHearingAssistService();
        } else {
            Log.d(TAG, "onClick showOpenBTConfirmDialog");
            this.mBtDialogShowing = true;
            showOpenBTConfirmDialog();
        }
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showFirstDialog$1(DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showFirstDialog$2(DialogInterface dialogInterface) {
        if (this.mBtDialogShowing) {
            return;
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showOpenBTConfirmDialog$3(DialogInterface dialogInterface, int i2) {
        openBluetooth();
        dialogInterface.dismiss();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showOpenBTConfirmDialog$4(DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showOpenBTConfirmDialog$5(DialogInterface dialogInterface) {
        finish();
    }

    private void openBluetooth() {
        Intent intent = new Intent();
        intent.setAction("android.settings.BLUETOOTH_SETTINGS");
        intent.putExtra("from", "miui.systemui.plugin");
        startActivity(intent);
    }

    private void showFirstDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_HearingAssistDialog);
        builder.setTitle(getString(R.string.hearing_assist_transmission_tips_title)).setMessage(getString(R.string.hearing_assist_transmission_tips_summary)).setPositiveButton(getString(R.string.hearing_assist_transmission_tips_continue), new DialogInterface.OnClickListener() { // from class: miui.systemui.quicksettings.hearingassist.d
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                this.f5886a.lambda$showFirstDialog$0(dialogInterface, i2);
            }
        }).setNegativeButton(getString(R.string.hearing_assist_transmission_tips_cancel), new DialogInterface.OnClickListener() { // from class: miui.systemui.quicksettings.hearingassist.e
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                this.f5887a.lambda$showFirstDialog$1(dialogInterface, i2);
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: miui.systemui.quicksettings.hearingassist.f
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f5888a.lambda$showFirstDialog$2(dialogInterface);
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        this.mFirstDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    private void showOpenBTConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_HearingAssistDialog);
        builder.setTitle(getString(R.string.hearing_assist_bluetooth_enable_tips_title)).setMessage(getString(R.string.hearing_assist_bluetooth_enable_tips_summary)).setPositiveButton(getString(R.string.hearing_assist_bluetooth_enable_tips_start), new DialogInterface.OnClickListener() { // from class: miui.systemui.quicksettings.hearingassist.a
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                this.f5883a.lambda$showOpenBTConfirmDialog$3(dialogInterface, i2);
            }
        }).setNegativeButton(getString(R.string.hearing_assist_bluetooth_enable_tips_cancle), new DialogInterface.OnClickListener() { // from class: miui.systemui.quicksettings.hearingassist.b
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                this.f5884a.lambda$showOpenBTConfirmDialog$4(dialogInterface, i2);
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: miui.systemui.quicksettings.hearingassist.c
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f5885a.lambda$showOpenBTConfirmDialog$5(dialogInterface);
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        this.mBtDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar appCompatActionBar = getAppCompatActionBar();
        if (appCompatActionBar != null) {
            appCompatActionBar.hide();
        }
        getWindow().getDecorView().setAlpha(0.0f);
        if (Settings.System.getInt(getContentResolver(), HearingAssistConstant.DISABLE_SHOW_TIPS, 0) != 1) {
            showFirstDialog();
        } else {
            if (HearingAssistUtil.isBluetoothConnected()) {
                return;
            }
            showOpenBTConfirmDialog();
        }
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AlertDialog alertDialog = this.mFirstDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        AlertDialog alertDialog2 = this.mBtDialog;
        if (alertDialog2 != null) {
            alertDialog2.dismiss();
        }
    }
}
