package com.android.systemui.miui.volume;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: loaded from: classes2.dex */
public class VolumePanelDialog extends Dialog {
    private static String TAG = "VolumePanelDialog";
    private DialogEventListener mDialogEventListener;

    public interface DialogEventListener {
        void dismiss(int i2);

        void onStop();

        void rescheduleTimeout();
    }

    public VolumePanelDialog(Context context) {
        super(context, R.style.Theme_MiuiVolumeDialog);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        DialogEventListener dialogEventListener;
        if (keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (keyEvent.getAction() == 1 && (dialogEventListener = this.mDialogEventListener) != null) {
            dialogEventListener.dismiss(7);
        }
        return true;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        DialogEventListener dialogEventListener = this.mDialogEventListener;
        if (dialogEventListener != null) {
            dialogEventListener.rescheduleTimeout();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        DialogEventListener dialogEventListener = this.mDialogEventListener;
        if (dialogEventListener != null) {
            dialogEventListener.onStop();
        }
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isShowing()) {
            return false;
        }
        if (motionEvent.getAction() != 4 && motionEvent.getAction() != 0) {
            return false;
        }
        DialogEventListener dialogEventListener = this.mDialogEventListener;
        if (dialogEventListener != null) {
            dialogEventListener.dismiss(1);
        }
        return true;
    }

    public void setDialogEventListener(DialogEventListener dialogEventListener) {
        this.mDialogEventListener = dialogEventListener;
    }
}
