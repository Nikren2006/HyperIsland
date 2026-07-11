package com.android.systemui.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.android.systemui.miplay.R;
import miuix.appcompat.app.AlertDialog;
import miuix.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothAudioShareActivity extends AppCompatActivity {
    public static final String ACTION_CLOSE_ACT = "com.miui.miplay.close.bluetooth_audio_share_act";
    private AlertDialog mAlertDialog;
    private BroadcastReceiver mScreenStatusReceiver = new BroadcastReceiver() { // from class: com.android.systemui.activity.BluetoothAudioShareActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_OFF".equals(action) || BluetoothAudioShareActivity.ACTION_CLOSE_ACT.equals(action)) {
                BluetoothAudioShareActivity.this.finish();
            }
        }
    };

    private int getOverlayFlag() {
        try {
            return ((Integer) WindowManager.LayoutParams.class.getField("PRIVATE_FLAG_TRUSTED_OVERLAY").get(null)).intValue();
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSafeWarningDialog$0(DialogInterface dialogInterface) {
        finish();
    }

    private void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction(ACTION_CLOSE_ACT);
        registerReceiver(this.mScreenStatusReceiver, intentFilter, 2);
    }

    @SuppressLint({"InflateParams"})
    private void showSafeWarningDialog() {
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.qs_control_bluetooth_share_layout, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.des)).setText(getResources().getString(R.string.miplay_bluetooth_share_des_text, 2));
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BluetoothShareDialogStyle);
        builder.setView(viewInflate);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.systemui.activity.a
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f1449a.lambda$showSafeWarningDialog$0(dialogInterface);
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        this.mAlertDialog = alertDialogCreate;
        Window window = alertDialogCreate.getWindow();
        window.setType(2020);
        window.addFlags(655360);
        window.addPrivateFlags(getOverlayFlag());
        this.mAlertDialog.show();
    }

    private void unregisterBroadcastReceiver() {
        unregisterReceiver(this.mScreenStatusReceiver);
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        finish();
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getAppCompatActionBar() != null) {
            getAppCompatActionBar().hide();
        }
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        getWindow().setAttributes(attributes);
        getWindow().getDecorView().setAlpha(0.0f);
        showSafeWarningDialog();
        registerBroadcastReceiver();
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.mAlertDialog = null;
        unregisterBroadcastReceiver();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }
}
