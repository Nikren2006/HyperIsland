package miui.systemui.devicecontrols.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.service.controls.actions.BooleanAction;
import android.service.controls.actions.CommandAction;
import android.service.controls.actions.ControlAction;
import android.service.controls.actions.FloatAction;
import android.service.controls.actions.ModeAction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import kotlin.jvm.functions.Function0;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ChallengeDialogs {
    public static final ChallengeDialogs INSTANCE = new ChallengeDialogs();
    private static final int STYLE = 16974545;
    private static final int WINDOW_TYPE = 2020;

    private ChallengeDialogs() {
    }

    private final ControlAction addChallengeValue(ControlAction controlAction, String str) {
        String templateId = controlAction.getTemplateId();
        if (controlAction instanceof BooleanAction) {
            return new BooleanAction(templateId, ((BooleanAction) controlAction).getNewState(), str);
        }
        if (controlAction instanceof FloatAction) {
            return new FloatAction(templateId, ((FloatAction) controlAction).getNewValue(), str);
        }
        if (controlAction instanceof CommandAction) {
            return new CommandAction(templateId, str);
        }
        if (controlAction instanceof ModeAction) {
            return new ModeAction(templateId, ((ModeAction) controlAction).getNewMode(), str);
        }
        throw new IllegalStateException("'action' is not a known type: " + controlAction);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createConfirmationDialog$lambda$8$lambda$6(ControlViewHolder cvh, ControlAction controlAction, DialogInterface dialogInterface, int i2) {
        kotlin.jvm.internal.n.g(cvh, "$cvh");
        cvh.action(INSTANCE.addChallengeValue(controlAction, com.xiaomi.onetrack.util.a.f3424i));
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createConfirmationDialog$lambda$8$lambda$7(Function0 onCancel, DialogInterface dialogInterface, int i2) {
        kotlin.jvm.internal.n.g(onCancel, "$onCancel");
        onCancel.invoke();
        dialogInterface.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createPinDialog$lambda$5$lambda$0(ControlViewHolder cvh, ControlAction controlAction, DialogInterface dialogInterface, int i2) {
        kotlin.jvm.internal.n.g(cvh, "$cvh");
        if (dialogInterface instanceof Dialog) {
            Dialog dialog = (Dialog) dialogInterface;
            int i3 = R.id.controls_pin_input;
            dialog.requireViewById(i3);
            cvh.action(INSTANCE.addChallengeValue(controlAction, ((EditText) dialog.requireViewById(i3)).getText().toString()));
            dialogInterface.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createPinDialog$lambda$5$lambda$1(Function0 onCancel, DialogInterface dialogInterface, int i2) {
        kotlin.jvm.internal.n.g(onCancel, "$onCancel");
        onCancel.invoke();
        dialogInterface.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createPinDialog$lambda$5$lambda$4(AnonymousClass1 this_apply, int i2, boolean z2, DialogInterface dialogInterface) {
        kotlin.jvm.internal.n.g(this_apply, "$this_apply");
        final EditText editText = (EditText) this_apply.requireViewById(R.id.controls_pin_input);
        editText.setHint(i2);
        int i3 = R.id.controls_pin_use_alpha;
        final CheckBox checkBox = (CheckBox) this_apply.requireViewById(i3);
        checkBox.setChecked(z2);
        ChallengeDialogs challengeDialogs = INSTANCE;
        kotlin.jvm.internal.n.d(editText);
        challengeDialogs.setInputType(editText, checkBox.isChecked());
        ((CheckBox) this_apply.requireViewById(i3)).setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChallengeDialogs.createPinDialog$lambda$5$lambda$4$lambda$3(editText, checkBox, view);
            }
        });
        editText.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createPinDialog$lambda$5$lambda$4$lambda$3(EditText editText, CheckBox checkBox, View view) {
        ChallengeDialogs challengeDialogs = INSTANCE;
        kotlin.jvm.internal.n.d(editText);
        challengeDialogs.setInputType(editText, checkBox.isChecked());
    }

    private final void setInputType(EditText editText, boolean z2) {
        if (z2) {
            editText.setInputType(129);
        } else {
            editText.setInputType(18);
        }
    }

    public final Dialog createConfirmationDialog(final ControlViewHolder cvh, final Function0 onCancel) {
        kotlin.jvm.internal.n.g(cvh, "cvh");
        kotlin.jvm.internal.n.g(onCancel, "onCancel");
        final ControlAction lastAction = cvh.getLastAction();
        if (lastAction == null) {
            Log.e("ControlsUiController", "Confirmation Dialog attempted but no last action is set. Will not show");
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(cvh.getContext(), 16974545);
        builder.setTitle(cvh.getContext().getResources().getString(R.string.controls_confirmation_message, cvh.getTitle().getText()));
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.d
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                ChallengeDialogs.createConfirmationDialog$lambda$8$lambda$6(cvh, lastAction, dialogInterface, i2);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.e
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                ChallengeDialogs.createConfirmationDialog$lambda$8$lambda$7(onCancel, dialogInterface, i2);
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.getWindow().setType(WINDOW_TYPE);
        return alertDialogCreate;
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [android.app.AlertDialog, android.app.Dialog, miui.systemui.devicecontrols.ui.ChallengeDialogs$createPinDialog$1] */
    public final Dialog createPinDialog(final ControlViewHolder cvh, final boolean z2, boolean z3, final Function0 onCancel) {
        kotlin.jvm.internal.n.g(cvh, "cvh");
        kotlin.jvm.internal.n.g(onCancel, "onCancel");
        final ControlAction lastAction = cvh.getLastAction();
        if (lastAction == null) {
            Log.e("ControlsUiController", "PIN Dialog attempted but no last action is set. Will not show");
            return null;
        }
        Resources resources = cvh.getContext().getResources();
        H0.i iVar = z3 ? new H0.i(resources.getString(R.string.controls_pin_wrong), Integer.valueOf(R.string.controls_pin_instructions_retry)) : new H0.i(resources.getString(R.string.controls_pin_verify, cvh.getTitle().getText()), Integer.valueOf(R.string.controls_pin_instructions));
        String str = (String) iVar.a();
        final int iIntValue = ((Number) iVar.b()).intValue();
        final ?? r3 = new AlertDialog(cvh.getContext()) { // from class: miui.systemui.devicecontrols.ui.ChallengeDialogs.createPinDialog.1
            @Override // android.app.Dialog, android.content.DialogInterface
            public void dismiss() {
                View decorView;
                InputMethodManager inputMethodManager;
                Window window = getWindow();
                if (window != null && (decorView = window.getDecorView()) != null && (inputMethodManager = (InputMethodManager) decorView.getContext().getSystemService(InputMethodManager.class)) != null) {
                    inputMethodManager.hideSoftInputFromWindow(decorView.getWindowToken(), 0);
                }
                super.dismiss();
            }
        };
        r3.setTitle(str);
        r3.setView(LayoutInflater.from(r3.getContext()).inflate(R.layout.controls_dialog_pin, (ViewGroup) null));
        r3.setButton(-1, r3.getContext().getText(android.R.string.ok), new DialogInterface.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.a
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                ChallengeDialogs.createPinDialog$lambda$5$lambda$0(cvh, lastAction, dialogInterface, i2);
            }
        });
        r3.setButton(-2, r3.getContext().getText(android.R.string.cancel), new DialogInterface.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.b
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                ChallengeDialogs.createPinDialog$lambda$5$lambda$1(onCancel, dialogInterface, i2);
            }
        });
        Window window = r3.getWindow();
        window.setType(WINDOW_TYPE);
        window.setSoftInputMode(4);
        r3.setOnShowListener(new DialogInterface.OnShowListener() { // from class: miui.systemui.devicecontrols.ui.c
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                ChallengeDialogs.createPinDialog$lambda$5$lambda$4(r3, iIntValue, z2, dialogInterface);
            }
        });
        return r3;
    }
}
