package android.service.controls.templates;

import H0.d;
import H0.e;
import android.annotation.SuppressLint;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi", "BlockedPrivateApi"})
public final class ControlTemplateExpose {
    public static final ControlTemplateExpose INSTANCE = new ControlTemplateExpose();
    private static final d NO_TEMPLATE$delegate = e.b(ControlTemplateExpose$NO_TEMPLATE$2.INSTANCE);

    private ControlTemplateExpose() {
    }

    public final ControlTemplate getNO_TEMPLATE() {
        return (ControlTemplate) NO_TEMPLATE$delegate.getValue();
    }
}
