package miui.service.controls;

import android.service.controls.IControlsSubscription;
import com.miui.expose.utils.MethodHolder;
import miui.systemui.notification.focus.Const;

/* JADX INFO: loaded from: classes2.dex */
public class IControlsSubscriptionExpose {
    private static final MethodHolder request = new MethodHolder(IControlsSubscription.class, "request", Long.TYPE);
    private static final MethodHolder cancel = new MethodHolder(IControlsSubscription.class, Const.Param.NOTIFICATION_CANCEL, new Class[0]);

    public static void cancel(IControlsSubscription iControlsSubscription) {
        cancel.invoke(iControlsSubscription, new Object[0]);
    }

    public static void request(IControlsSubscription iControlsSubscription, long j2) {
        request.invoke(iControlsSubscription, Long.valueOf(j2));
    }
}
