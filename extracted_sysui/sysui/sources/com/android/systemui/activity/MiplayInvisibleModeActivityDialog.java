package com.android.systemui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import com.android.systemui.InvisibleModeUtil;
import miuix.appcompat.app.AlertDialog;
import miuix.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes.dex */
public class MiplayInvisibleModeActivityDialog extends AppCompatActivity {
    private AlertDialog mAlertDialog;

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getAppCompatActionBar() != null) {
            getAppCompatActionBar().hide();
        }
        getWindow().getDecorView().setAlpha(0.0f);
        AlertDialog invisibleModeHintDialog = InvisibleModeUtil.getInvisibleModeHintDialog(this);
        this.mAlertDialog = invisibleModeHintDialog;
        if (invisibleModeHintDialog == null) {
            finish();
        } else {
            invisibleModeHintDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.systemui.activity.MiplayInvisibleModeActivityDialog.1
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    MiplayInvisibleModeActivityDialog.this.finish();
                }
            });
        }
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.mAlertDialog = null;
        super.onDestroy();
    }
}
