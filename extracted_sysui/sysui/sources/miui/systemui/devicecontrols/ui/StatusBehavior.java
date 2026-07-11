package miui.systemui.devicecontrols.ui;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.service.controls.Control;
import android.view.View;
import android.widget.Toast;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class StatusBehavior implements Behavior {
    public ControlViewHolder cvh;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$0(StatusBehavior this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Toast.makeText(this$0.getCvh().getLayout().getContext(), this$0.getCvh().getLayout().getContext().getString(R.string.controls_offline_device_toast), 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$1(StatusBehavior this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Toast.makeText(this$0.getCvh().getLayout().getContext(), kotlin.jvm.internal.n.c(this$0.getCvh().getCws().getComponentName().getPackageName(), "com.xiaomi.smarthome") ? this$0.getCvh().getLayout().getContext().getString(R.string.controls_not_found_device_toast_mihome) : this$0.getCvh().getLayout().getContext().getString(R.string.controls_not_found_device_toast), 0).show();
    }

    private final void showNotFoundDialog(final ControlViewHolder controlViewHolder, final ControlWithState controlWithState) {
        PackageManager packageManager = controlViewHolder.getContext().getPackageManager();
        CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(controlWithState.getComponentName().getPackageName(), 128));
        final AlertDialog.Builder builder = new AlertDialog.Builder(controlViewHolder.getContext(), android.R.style.Theme.DeviceDefault.Dialog.Alert);
        Resources resources = controlViewHolder.getContext().getResources();
        builder.setTitle(resources.getString(R.string.controls_error_removed_title));
        builder.setMessage(resources.getString(R.string.controls_error_removed_message, controlViewHolder.getTitle().getText(), applicationLabel));
        builder.setPositiveButton(R.string.controls_open_app, new DialogInterface.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.C
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                StatusBehavior.showNotFoundDialog$lambda$4$lambda$2(controlWithState, builder, controlViewHolder, dialogInterface, i2);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.D
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.getWindow().setType(2020);
        alertDialogCreate.show();
        controlViewHolder.setVisibleDialog(alertDialogCreate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showNotFoundDialog$lambda$4$lambda$2(ControlWithState cws, AlertDialog.Builder this_apply, ControlViewHolder cvh, DialogInterface dialogInterface, int i2) {
        PendingIntent appIntent;
        kotlin.jvm.internal.n.g(cws, "$cws");
        kotlin.jvm.internal.n.g(this_apply, "$this_apply");
        kotlin.jvm.internal.n.g(cvh, "$cvh");
        try {
            Control control = cws.getControl();
            if (control != null && (appIntent = control.getAppIntent()) != null) {
                appIntent.send();
            }
            this_apply.getContext().sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        } catch (PendingIntent.CanceledException unused) {
            cvh.setErrorStatus();
        }
        dialogInterface.dismiss();
    }

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void bind(ControlWithState cws, int i2) {
        int i3;
        kotlin.jvm.internal.n.g(cws, "cws");
        Control control = cws.getControl();
        int status = control != null ? control.getStatus() : 0;
        if (status == 3) {
            i3 = R.string.controls_error_generic;
        } else if (status != 4) {
            getCvh().setLoading(true);
            i3 = R.string.loading;
        } else {
            i3 = kotlin.jvm.internal.n.c(cws.getComponentName().getPackageName(), "com.xiaomi.smarthome") ? R.string.offline : R.string.disabled;
        }
        ControlViewHolder cvh = getCvh();
        String string = getCvh().getContext().getString(i3);
        kotlin.jvm.internal.n.f(string, "getString(...)");
        ControlViewHolder.setStatusText$default(cvh, string, false, 2, null);
        ControlViewHolder.applyRenderInfo$miui_devicecontrols_release$default(getCvh(), false, i2, false, 4, null);
        if (status == 2) {
            getCvh().getLayout().setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.F
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StatusBehavior.bind$lambda$1(this.f5643a, view);
                }
            });
        } else {
            if (status != 4) {
                return;
            }
            getCvh().getStatus().setTextColor(getCvh().getContext().getColor(R.color.controls_offline_text_color));
            getCvh().getStatus().setAlpha(1.0f);
            getCvh().getLayout().setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.E
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StatusBehavior.bind$lambda$0(this.f5642a, view);
                }
            });
        }
    }

    public final ControlViewHolder getCvh() {
        ControlViewHolder controlViewHolder = this.cvh;
        if (controlViewHolder != null) {
            return controlViewHolder;
        }
        kotlin.jvm.internal.n.w("cvh");
        return null;
    }

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void initialize(ControlViewHolder cvh) {
        kotlin.jvm.internal.n.g(cvh, "cvh");
        setCvh(cvh);
    }

    public final void setCvh(ControlViewHolder controlViewHolder) {
        kotlin.jvm.internal.n.g(controlViewHolder, "<set-?>");
        this.cvh = controlViewHolder;
    }
}
