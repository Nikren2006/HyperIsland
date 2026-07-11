package miui.systemui.devicecontrols.controller;

import android.service.controls.IControlsActionCallback;
import android.service.controls.IControlsSubscriber;
import android.service.controls.IControlsSubscription;
import android.service.controls.actions.ControlAction;
import android.service.controls.actions.ControlActionWrapper;
import android.util.Log;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.service.controls.IControlsProvider;
import miui.systemui.controls.ExposeUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class ServiceWrapper {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ServiceWrapper";
    private final Object service;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public ServiceWrapper(Object service) {
        kotlin.jvm.internal.n.g(service, "service");
        this.service = service;
    }

    private final boolean callThroughService(Function0 function0) {
        try {
            function0.invoke();
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "Caught exception from ControlsProviderService", e2);
            return false;
        }
    }

    public final boolean action(String controlId, ControlAction action, IControlsActionCallback cb) {
        kotlin.jvm.internal.n.g(controlId, "controlId");
        kotlin.jvm.internal.n.g(action, "action");
        kotlin.jvm.internal.n.g(cb, "cb");
        try {
            IControlsProvider iControlsProvider = IControlsProvider.INSTANCE;
            Object obj = this.service;
            ControlActionWrapper ControlActionWrapper = ExposeUtils.INSTANCE.ControlActionWrapper(action);
            kotlin.jvm.internal.n.f(ControlActionWrapper, "ControlActionWrapper(...)");
            iControlsProvider.action(obj, controlId, ControlActionWrapper, cb);
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "Caught exception from ControlsProviderService", e2);
            return false;
        }
    }

    public final boolean cancel(IControlsSubscription subscription) {
        kotlin.jvm.internal.n.g(subscription, "subscription");
        try {
            subscription.cancel();
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "Caught exception from ControlsProviderService", e2);
            return false;
        }
    }

    public final Object getService() {
        return this.service;
    }

    public final boolean load(IControlsSubscriber subscriber) {
        kotlin.jvm.internal.n.g(subscriber, "subscriber");
        try {
            IControlsProvider.INSTANCE.load(this.service, subscriber);
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "Caught exception from ControlsProviderService", e2);
            return false;
        }
    }

    public final boolean loadSuggested(IControlsSubscriber subscriber) {
        kotlin.jvm.internal.n.g(subscriber, "subscriber");
        try {
            IControlsProvider.INSTANCE.loadSuggested(this.service, subscriber);
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "Caught exception from ControlsProviderService", e2);
            return false;
        }
    }

    public final boolean request(IControlsSubscription subscription, long j2) {
        kotlin.jvm.internal.n.g(subscription, "subscription");
        try {
            subscription.request(j2);
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "Caught exception from ControlsProviderService", e2);
            return false;
        }
    }

    public final boolean subscribe(List<String> controlIds, IControlsSubscriber subscriber) {
        kotlin.jvm.internal.n.g(controlIds, "controlIds");
        kotlin.jvm.internal.n.g(subscriber, "subscriber");
        try {
            IControlsProvider.INSTANCE.subscribe(this.service, controlIds, subscriber);
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "Caught exception from ControlsProviderService", e2);
            return false;
        }
    }
}
