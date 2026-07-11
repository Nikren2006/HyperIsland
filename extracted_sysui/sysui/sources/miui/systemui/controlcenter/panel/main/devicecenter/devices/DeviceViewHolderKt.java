package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceViewHolderKt {
    public static final float pxToDp(int i2, Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        return i2 / context.getResources().getDisplayMetrics().density;
    }
}
