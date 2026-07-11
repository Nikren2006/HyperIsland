package com.android.systemui.miui.volume;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import com.miui.miplay.audio.data.DeviceInfo;
import java.util.function.Consumer;
import miui.systemui.notification.focus.Const;
import miui.systemui.util.BlurUtils;
import miui.systemui.util.DeviceStateManagerCompat;
import miui.systemui.util.ThreadUtils;
import miuix.appcompat.app.AlertDialog;
import miuix.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes2.dex */
public class SafeWarningActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
    private static String TAG = "SafeWarningActivity";
    private AlertDialog mAlertDialog;
    private AudioManager mAudioManager;
    private Object mDeviceStateManager;
    private Object mFoldStateListener;
    private Boolean mIsFolded;

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissDialog() {
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(Boolean bool) {
        updateFoldState(bool.booleanValue());
    }

    private void showSafeWarningDialog() {
        dismissDialog();
        this.mAlertDialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_Volume_Dialog_Alert);
        builder.setMessage(getString(R.string.safe_media_volume_warning)).setCancelable(false).setEnableEnterAnim(false).setPositiveButton(getString(android.R.string.yes), this).setNegativeButton(getString(android.R.string.no), this).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.systemui.miui.volume.SafeWarningActivity.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (dialogInterface == SafeWarningActivity.this.mAlertDialog) {
                    SafeWarningActivity.this.mAlertDialog = null;
                    SafeWarningActivity.this.finish();
                }
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        this.mAlertDialog = alertDialogCreate;
        Window window = alertDialogCreate.getWindow();
        window.setType(2020);
        window.addFlags(655360);
        window.addPrivateFlags(Util.PRIVATE_FLAG_TRUSTED_OVERLAY);
        this.mAlertDialog.show();
    }

    private void updateFoldState(boolean z2) {
        Boolean bool = this.mIsFolded;
        if (bool == null || bool.booleanValue() == z2) {
            this.mIsFolded = Boolean.valueOf(z2);
        } else {
            this.mIsFolded = Boolean.valueOf(z2);
            dismissDialog();
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        if (i2 == -1) {
            Log.i(TAG, "onClick -- disableSafeMediaVolume");
            this.mAudioManager.disableSafeMediaVolume();
        }
        dismissDialog();
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        dismissDialog();
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getAppCompatActionBar() != null) {
            getAppCompatActionBar().hide();
        }
        getWindow().getDecorView().setAlpha(0.0f);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        getWindow().setAttributes(attributes);
        if (Util.IS_MUILT_DISPLAY || BlurUtils.isFlipDevice(this)) {
            DeviceStateManagerCompat deviceStateManagerCompat = DeviceStateManagerCompat.INSTANCE;
            this.mDeviceStateManager = deviceStateManagerCompat.getDeviceStateManagerInstance();
            Object foldStateListenerInstance = deviceStateManagerCompat.getFoldStateListenerInstance(this, new Consumer() { // from class: com.android.systemui.miui.volume.m
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f1501a.lambda$onCreate$0((Boolean) obj);
                }
            });
            this.mFoldStateListener = foldStateListenerInstance;
            if (foldStateListenerInstance != null) {
                deviceStateManagerCompat.registerCallbackCompat(this.mDeviceStateManager, new HandlerExecutor(ThreadUtils.getUiThreadHandler()), this.mFoldStateListener);
            }
        }
        showSafeWarningDialog();
        this.mAudioManager = (AudioManager) getSystemService(DeviceInfo.AUDIO_SUPPORT);
        new Handler().postDelayed(new Runnable() { // from class: com.android.systemui.miui.volume.n
            @Override // java.lang.Runnable
            public final void run() {
                this.f1502a.dismissDialog();
            }
        }, getIntent().getIntExtra(Const.Param.TIMEOUT_MIN, 0));
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Object obj;
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null) {
            alertDialog.dismissWithoutAnimation();
        }
        if ((Util.IS_MUILT_DISPLAY || BlurUtils.isFlipDevice(this)) && (obj = this.mFoldStateListener) != null) {
            DeviceStateManagerCompat.INSTANCE.unregisterCallbackCompat(this.mDeviceStateManager, obj);
        }
        super.onDestroy();
    }
}
