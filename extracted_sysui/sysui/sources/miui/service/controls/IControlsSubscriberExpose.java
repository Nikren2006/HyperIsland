package miui.service.controls;

import android.os.IBinder;
import android.service.controls.Control;
import android.service.controls.IControlsSubscriber;
import android.service.controls.IControlsSubscription;
import com.miui.expose.utils.MethodHolder;

/* JADX INFO: loaded from: classes2.dex */
public class IControlsSubscriberExpose {
    private static final MethodHolder onSubscribe = new MethodHolder(IControlsSubscriber.class, "onSubscribe", IBinder.class, IControlsSubscription.class);
    private static final MethodHolder onNext = new MethodHolder(IControlsSubscriber.class, "onNext", IBinder.class, Control.class);
    private static final MethodHolder onError = new MethodHolder(IControlsSubscriber.class, "onError", IBinder.class, String.class);
    private static final MethodHolder onComplete = new MethodHolder(IControlsSubscriber.class, "onComplete", IBinder.class);

    public static void onComplete(IControlsSubscriber iControlsSubscriber, IBinder iBinder) {
        onComplete.invoke(iControlsSubscriber, iBinder);
    }

    public static void onError(IControlsSubscriber iControlsSubscriber, IBinder iBinder, String str) {
        onError.invoke(iControlsSubscriber, iBinder, str);
    }

    public static void onNext(IControlsSubscriber iControlsSubscriber, IBinder iBinder, Control control) {
        onNext.invoke(iControlsSubscriber, iBinder, control);
    }

    public static void onSubscribe(IControlsSubscriber iControlsSubscriber, IBinder iBinder, IControlsSubscription iControlsSubscription) {
        onSubscribe.invoke(iControlsSubscriber, iBinder, iControlsSubscription);
    }
}
