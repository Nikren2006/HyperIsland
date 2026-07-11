package miui.service.controls;

import android.os.IBinder;
import android.service.controls.IControlsActionCallback;
import com.miui.expose.utils.MethodHolder;

/* JADX INFO: loaded from: classes2.dex */
public class IControlsActionCallbackExpose {
    private static final MethodHolder accept = new MethodHolder(IControlsActionCallback.class, "accept", IBinder.class, String.class, Integer.TYPE);

    public static void accept(IControlsActionCallback iControlsActionCallback, IBinder iBinder, String str, int i2) {
        accept.invoke(iControlsActionCallback, iBinder, str, Integer.valueOf(i2));
    }
}
