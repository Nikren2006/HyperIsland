package miuix.appcompat.widget;

import android.view.View;
import miuix.appcompat.app.AlertDialog;
import miuix.appcompat.widget.dialoganim.IDialogAnim;
import miuix.appcompat.widget.dialoganim.PadDialogAnim;
import miuix.appcompat.widget.dialoganim.PhoneDialogAnim;

/* JADX INFO: loaded from: classes3.dex */
public class DialogAnimHelper {
    private IDialogAnim mDialogAnim;
    private boolean mDiscardImeAnimEnabled = false;

    public interface OnDismiss {
        void end();
    }

    public void cancelAnimator() {
        IDialogAnim iDialogAnim = this.mDialogAnim;
        if (iDialogAnim != null) {
            iDialogAnim.cancelAnimator();
        }
    }

    public void executeDismissAnim(View view, boolean z2, View view2, OnDismiss onDismiss) {
        if (this.mDialogAnim == null) {
            if (z2) {
                this.mDialogAnim = new PadDialogAnim();
            } else {
                this.mDialogAnim = new PhoneDialogAnim();
            }
        }
        this.mDialogAnim.executeDismissAnim(view, view2, onDismiss);
        this.mDialogAnim = null;
    }

    public void executeShowAnim(View view, View view2, boolean z2, boolean z3, AlertDialog.OnDialogShowAnimListener onDialogShowAnimListener) {
        if (this.mDialogAnim == null) {
            if (z2) {
                this.mDialogAnim = new PadDialogAnim();
            } else {
                PhoneDialogAnim phoneDialogAnim = new PhoneDialogAnim();
                this.mDialogAnim = phoneDialogAnim;
                phoneDialogAnim.setDiscardImeAnimEnabled(this.mDiscardImeAnimEnabled);
            }
        }
        this.mDialogAnim.executeShowAnim(view, view2, z3, onDialogShowAnimListener);
    }

    public void setDiscardImeAnimEnabled(boolean z2) {
        this.mDiscardImeAnimEnabled = z2;
    }
}
