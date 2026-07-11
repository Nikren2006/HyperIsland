package miui.service.controls;

import android.os.IBinder;
import android.service.controls.IControlsActionCallback;
import android.service.controls.IControlsProvider;
import android.service.controls.IControlsSubscriber;
import android.service.controls.actions.ControlActionWrapper;
import com.miui.expose.utils.MethodHolder;
import com.xiaomi.onetrack.api.a;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class IControlsProviderExpose {
    private static final MethodHolder asInterface = new MethodHolder(IControlsProvider.Stub.class, "asInterface", IBinder.class);
    private static final MethodHolder action = new MethodHolder(android.service.controls.IControlsProvider.class, a.f2741a, String.class, ControlActionWrapper.class, IControlsActionCallback.class);
    private static final MethodHolder load = new MethodHolder(android.service.controls.IControlsProvider.class, "load", IControlsSubscriber.class);
    private static final MethodHolder loadSuggested = new MethodHolder(android.service.controls.IControlsProvider.class, "loadSuggested", IControlsSubscriber.class);
    private static final MethodHolder subscribe = new MethodHolder(android.service.controls.IControlsProvider.class, "subscribe", List.class, IControlsSubscriber.class);

    public static void action(Object obj, String str, ControlActionWrapper controlActionWrapper, IControlsActionCallback iControlsActionCallback) {
        action.invoke((android.service.controls.IControlsProvider) obj, str, controlActionWrapper, iControlsActionCallback);
    }

    public static android.service.controls.IControlsProvider asInterface(Object obj) {
        return (android.service.controls.IControlsProvider) asInterface.invoke(null, (IBinder) obj);
    }

    public static void load(Object obj, IControlsSubscriber iControlsSubscriber) {
        load.invoke((android.service.controls.IControlsProvider) obj, iControlsSubscriber);
    }

    public static void loadSuggested(Object obj, IControlsSubscriber iControlsSubscriber) {
        loadSuggested.invoke((android.service.controls.IControlsProvider) obj, iControlsSubscriber);
    }

    public static void subscribe(Object obj, List<String> list, IControlsSubscriber iControlsSubscriber) {
        subscribe.invoke((android.service.controls.IControlsProvider) obj, list, iControlsSubscriber);
    }
}
