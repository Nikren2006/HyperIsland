package miui.service.controls;

import android.service.controls.IControlsActionCallback;
import android.service.controls.IControlsSubscriber;
import android.service.controls.actions.ControlActionWrapper;
import java.util.List;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class IControlsProvider {
    public static final IControlsProvider INSTANCE = new IControlsProvider();

    public static final class Stub {
        public static final Stub INSTANCE = new Stub();

        private Stub() {
        }

        public final Object asInterface(Object binder) {
            n.g(binder, "binder");
            android.service.controls.IControlsProvider iControlsProviderAsInterface = IControlsProviderExpose.asInterface(binder);
            n.f(iControlsProviderAsInterface, "asInterface(...)");
            return iControlsProviderAsInterface;
        }
    }

    private IControlsProvider() {
    }

    public final void action(Object obj, String controlId, ControlActionWrapper actionWrapper, IControlsActionCallback cb) {
        n.g(obj, "<this>");
        n.g(controlId, "controlId");
        n.g(actionWrapper, "actionWrapper");
        n.g(cb, "cb");
        IControlsProviderExpose.action(obj, controlId, actionWrapper, cb);
    }

    public final void load(Object obj, IControlsSubscriber subscriber) {
        n.g(obj, "<this>");
        n.g(subscriber, "subscriber");
        IControlsProviderExpose.load(obj, subscriber);
    }

    public final void loadSuggested(Object obj, IControlsSubscriber subscriber) {
        n.g(obj, "<this>");
        n.g(subscriber, "subscriber");
        IControlsProviderExpose.loadSuggested(obj, subscriber);
    }

    public final void subscribe(Object obj, List<String> controlIds, IControlsSubscriber subscriber) {
        n.g(obj, "<this>");
        n.g(controlIds, "controlIds");
        n.g(subscriber, "subscriber");
        IControlsProviderExpose.subscribe(obj, controlIds, subscriber);
    }
}
