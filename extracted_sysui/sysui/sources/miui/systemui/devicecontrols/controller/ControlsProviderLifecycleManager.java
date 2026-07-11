package miui.systemui.devicecontrols.controller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.controls.IControlsActionCallback;
import android.service.controls.IControlsSubscriber;
import android.service.controls.IControlsSubscription;
import android.service.controls.actions.ControlAction;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.service.controls.IControlsProvider;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsProviderLifecycleManager implements IBinder.DeathRecipient {
    private static final long BIND_RETRY_DELAY = 1000;
    private static final boolean DEBUG = true;
    private static final long LOAD_TIMEOUT_SECONDS = 20;
    private static final int MAX_BIND_RETRIES = 5;
    private final String TAG;
    private final IControlsActionCallback.Stub actionCallbackService;
    private int bindTryCount;
    private final ComponentName componentName;
    private final Context context;
    private final DelayableExecutor executor;
    private final Intent intent;
    private Runnable onLoadCanceller;

    @GuardedBy({"queuedServiceMethods"})
    private final Set<ServiceMethod> queuedServiceMethods;
    private boolean requiresBound;
    private final ControlsProviderLifecycleManager$serviceConnection$1 serviceConnection;
    private IBinder token;
    private final UserHandle user;
    private ServiceWrapper wrapper;
    public static final Companion Companion = new Companion(null);
    private static final int BIND_FLAGS = 67109121;

    public final class Action extends ServiceMethod {
        private final ControlAction action;
        private final String id;
        final /* synthetic */ ControlsProviderLifecycleManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Action(ControlsProviderLifecycleManager controlsProviderLifecycleManager, String id, ControlAction action) {
            super();
            kotlin.jvm.internal.n.g(id, "id");
            kotlin.jvm.internal.n.g(action, "action");
            this.this$0 = controlsProviderLifecycleManager;
            this.id = id;
            this.action = action;
        }

        @Override // miui.systemui.devicecontrols.controller.ControlsProviderLifecycleManager.ServiceMethod
        public boolean callWrapper$miui_devicecontrols_release() {
            Log.d(this.this$0.TAG, "onAction " + this.this$0.getComponentName() + " - " + this.id);
            ServiceWrapper serviceWrapper = this.this$0.wrapper;
            if (serviceWrapper != null) {
                return serviceWrapper.action(this.id, this.action, this.this$0.actionCallbackService);
            }
            return false;
        }

        public final ControlAction getAction() {
            return this.action;
        }

        public final String getId() {
            return this.id;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final class Load extends ServiceMethod {
        private final IControlsSubscriber.Stub subscriber;
        final /* synthetic */ ControlsProviderLifecycleManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Load(ControlsProviderLifecycleManager controlsProviderLifecycleManager, IControlsSubscriber.Stub subscriber) {
            super();
            kotlin.jvm.internal.n.g(subscriber, "subscriber");
            this.this$0 = controlsProviderLifecycleManager;
            this.subscriber = subscriber;
        }

        @Override // miui.systemui.devicecontrols.controller.ControlsProviderLifecycleManager.ServiceMethod
        public boolean callWrapper$miui_devicecontrols_release() {
            Log.d(this.this$0.TAG, "load " + this.this$0.getComponentName());
            ServiceWrapper serviceWrapper = this.this$0.wrapper;
            if (serviceWrapper != null) {
                return serviceWrapper.load(this.subscriber);
            }
            return false;
        }

        public final IControlsSubscriber.Stub getSubscriber() {
            return this.subscriber;
        }
    }

    public abstract class ServiceMethod {
        public ServiceMethod() {
        }

        public abstract boolean callWrapper$miui_devicecontrols_release();

        public final void run() {
            if (callWrapper$miui_devicecontrols_release()) {
                return;
            }
            ControlsProviderLifecycleManager.this.queueServiceMethod(this);
            ControlsProviderLifecycleManager.this.binderDied();
        }
    }

    public final class Subscribe extends ServiceMethod {
        private final List<String> list;
        private final IControlsSubscriber subscriber;
        final /* synthetic */ ControlsProviderLifecycleManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Subscribe(ControlsProviderLifecycleManager controlsProviderLifecycleManager, List<String> list, IControlsSubscriber subscriber) {
            super();
            kotlin.jvm.internal.n.g(list, "list");
            kotlin.jvm.internal.n.g(subscriber, "subscriber");
            this.this$0 = controlsProviderLifecycleManager;
            this.list = list;
            this.subscriber = subscriber;
        }

        @Override // miui.systemui.devicecontrols.controller.ControlsProviderLifecycleManager.ServiceMethod
        public boolean callWrapper$miui_devicecontrols_release() {
            Log.d(this.this$0.TAG, "subscribe " + this.this$0.getComponentName() + " - " + this.list);
            ServiceWrapper serviceWrapper = this.this$0.wrapper;
            if (serviceWrapper != null) {
                return serviceWrapper.subscribe(this.list, this.subscriber);
            }
            return false;
        }

        public final List<String> getList() {
            return this.list;
        }

        public final IControlsSubscriber getSubscriber() {
            return this.subscriber;
        }
    }

    public final class Suggest extends ServiceMethod {
        private final IControlsSubscriber.Stub subscriber;
        final /* synthetic */ ControlsProviderLifecycleManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Suggest(ControlsProviderLifecycleManager controlsProviderLifecycleManager, IControlsSubscriber.Stub subscriber) {
            super();
            kotlin.jvm.internal.n.g(subscriber, "subscriber");
            this.this$0 = controlsProviderLifecycleManager;
            this.subscriber = subscriber;
        }

        @Override // miui.systemui.devicecontrols.controller.ControlsProviderLifecycleManager.ServiceMethod
        public boolean callWrapper$miui_devicecontrols_release() {
            Log.d(this.this$0.TAG, "suggest " + this.this$0.getComponentName());
            ServiceWrapper serviceWrapper = this.this$0.wrapper;
            if (serviceWrapper != null) {
                return serviceWrapper.loadSuggested(this.subscriber);
            }
            return false;
        }

        public final IControlsSubscriber.Stub getSubscriber() {
            return this.subscriber;
        }
    }

    /* JADX WARN: Type inference failed for: r2v6, types: [miui.systemui.devicecontrols.controller.ControlsProviderLifecycleManager$serviceConnection$1] */
    public ControlsProviderLifecycleManager(Context context, DelayableExecutor executor, IControlsActionCallback.Stub actionCallbackService, UserHandle user, ComponentName componentName) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(executor, "executor");
        kotlin.jvm.internal.n.g(actionCallbackService, "actionCallbackService");
        kotlin.jvm.internal.n.g(user, "user");
        kotlin.jvm.internal.n.g(componentName, "componentName");
        this.context = context;
        this.executor = executor;
        this.actionCallbackService = actionCallbackService;
        this.user = user;
        this.componentName = componentName;
        this.token = new Binder();
        this.queuedServiceMethods = new ArraySet();
        this.TAG = ControlsProviderLifecycleManager.class.getSimpleName();
        Intent intent = new Intent();
        intent.setComponent(componentName);
        Bundle bundle = new Bundle();
        bundle.putBinder("CALLBACK_TOKEN", this.token);
        H0.s sVar = H0.s.f314a;
        intent.putExtra("CALLBACK_BUNDLE", bundle);
        this.intent = intent;
        this.serviceConnection = new ServiceConnection() { // from class: miui.systemui.devicecontrols.controller.ControlsProviderLifecycleManager$serviceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                kotlin.jvm.internal.n.g(name, "name");
                kotlin.jvm.internal.n.g(service, "service");
                Log.d(this.this$0.TAG, "onServiceConnected " + name);
                this.this$0.bindTryCount = 0;
                this.this$0.wrapper = new ServiceWrapper(IControlsProvider.Stub.INSTANCE.asInterface(service));
                try {
                    service.linkToDeath(this.this$0, 0);
                } catch (RemoteException unused) {
                }
                this.this$0.handlePendingServiceMethods();
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName2) {
                Log.d(this.this$0.TAG, "onServiceDisconnected " + componentName2);
                this.this$0.wrapper = null;
                this.this$0.bindService(false);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bindService(final boolean z2) {
        this.executor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.z
            @Override // java.lang.Runnable
            public final void run() {
                ControlsProviderLifecycleManager.bindService$lambda$3(this.f5601a, z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindService$lambda$3(ControlsProviderLifecycleManager this$0, boolean z2) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.requiresBound = z2;
        if (!z2) {
            Log.d(this$0.TAG, "Unbinding service " + this$0.intent);
            this$0.bindTryCount = 0;
            if (this$0.wrapper != null) {
                this$0.context.unbindService(this$0.serviceConnection);
            }
            this$0.wrapper = null;
            return;
        }
        if (this$0.bindTryCount != 5) {
            Log.d(this$0.TAG, "Binding service " + this$0.intent);
            this$0.bindTryCount = this$0.bindTryCount + 1;
            try {
                this$0.context.bindServiceAsUser(this$0.intent, this$0.serviceConnection, BIND_FLAGS, this$0.user);
            } catch (SecurityException e2) {
                Log.e(this$0.TAG, "Failed to bind to service", e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handlePendingServiceMethods() {
        ArraySet arraySet;
        synchronized (this.queuedServiceMethods) {
            arraySet = new ArraySet(this.queuedServiceMethods);
            this.queuedServiceMethods.clear();
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            ((ServiceMethod) it.next()).run();
        }
    }

    private final void invokeOrQueue(ServiceMethod serviceMethod) {
        H0.s sVar;
        if (this.wrapper != null) {
            serviceMethod.run();
            sVar = H0.s.f314a;
        } else {
            sVar = null;
        }
        if (sVar == null) {
            queueServiceMethod(serviceMethod);
            bindService(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void maybeBindAndLoad$lambda$10(ControlsProviderLifecycleManager this$0, IControlsSubscriber.Stub subscriber) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(subscriber, "$subscriber");
        Log.d(this$0.TAG, "Timeout waiting onLoad for " + this$0.componentName);
        subscriber.onError(this$0.token, "Timeout waiting onLoad");
        this$0.unbindService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void maybeBindAndLoadSuggested$lambda$11(ControlsProviderLifecycleManager this$0, IControlsSubscriber.Stub subscriber) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(subscriber, "$subscriber");
        Log.d(this$0.TAG, "Timeout waiting onLoadSuggested for " + this$0.componentName);
        subscriber.onError(this$0.token, "Timeout waiting onLoadSuggested");
        this$0.unbindService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void queueServiceMethod(ServiceMethod serviceMethod) {
        synchronized (this.queuedServiceMethods) {
            this.queuedServiceMethods.add(serviceMethod);
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        if (this.wrapper == null) {
            return;
        }
        this.wrapper = null;
        if (this.requiresBound) {
            Log.d(this.TAG, "binderDied");
        }
    }

    public final void cancelLoadTimeout() {
        Runnable runnable = this.onLoadCanceller;
        if (runnable != null) {
            runnable.run();
        }
        this.onLoadCanceller = null;
    }

    public final void cancelSubscription(IControlsSubscription subscription) {
        kotlin.jvm.internal.n.g(subscription, "subscription");
        Log.d(this.TAG, "cancelSubscription: " + subscription);
        ServiceWrapper serviceWrapper = this.wrapper;
        if (serviceWrapper != null) {
            serviceWrapper.cancel(subscription);
        }
    }

    public final ComponentName getComponentName() {
        return this.componentName;
    }

    public final IBinder getToken() {
        return this.token;
    }

    public final UserHandle getUser() {
        return this.user;
    }

    public final void maybeBindAndLoad(final IControlsSubscriber.Stub subscriber) {
        kotlin.jvm.internal.n.g(subscriber, "subscriber");
        this.onLoadCanceller = this.executor.executeDelayed(new Runnable() { // from class: miui.systemui.devicecontrols.controller.x
            @Override // java.lang.Runnable
            public final void run() {
                ControlsProviderLifecycleManager.maybeBindAndLoad$lambda$10(this.f5597a, subscriber);
            }
        }, LOAD_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        invokeOrQueue(new Load(this, subscriber));
    }

    public final void maybeBindAndLoadSuggested(final IControlsSubscriber.Stub subscriber) {
        kotlin.jvm.internal.n.g(subscriber, "subscriber");
        this.onLoadCanceller = this.executor.executeDelayed(new Runnable() { // from class: miui.systemui.devicecontrols.controller.y
            @Override // java.lang.Runnable
            public final void run() {
                ControlsProviderLifecycleManager.maybeBindAndLoadSuggested$lambda$11(this.f5599a, subscriber);
            }
        }, LOAD_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        invokeOrQueue(new Suggest(this, subscriber));
    }

    public final void maybeBindAndSendAction(String controlId, ControlAction action) {
        kotlin.jvm.internal.n.g(controlId, "controlId");
        kotlin.jvm.internal.n.g(action, "action");
        invokeOrQueue(new Action(this, controlId, action));
    }

    public final void maybeBindAndSubscribe(List<String> controlIds, IControlsSubscriber subscriber) {
        kotlin.jvm.internal.n.g(controlIds, "controlIds");
        kotlin.jvm.internal.n.g(subscriber, "subscriber");
        invokeOrQueue(new Subscribe(this, controlIds, subscriber));
    }

    public final void setToken(IBinder iBinder) {
        kotlin.jvm.internal.n.g(iBinder, "<set-?>");
        this.token = iBinder;
    }

    public final void startSubscription(IControlsSubscription subscription, long j2) {
        kotlin.jvm.internal.n.g(subscription, "subscription");
        Log.d(this.TAG, "startSubscription: " + subscription);
        ServiceWrapper serviceWrapper = this.wrapper;
        if (serviceWrapper != null) {
            serviceWrapper.request(subscription, j2);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ControlsProviderLifecycleManager(");
        sb.append("component=" + this.componentName);
        sb.append(", user=" + this.user);
        sb.append(")");
        String string = sb.toString();
        kotlin.jvm.internal.n.f(string, "toString(...)");
        return string;
    }

    public final void unbindService() {
        Runnable runnable = this.onLoadCanceller;
        if (runnable != null) {
            runnable.run();
        }
        this.onLoadCanceller = null;
        bindService(false);
    }

    public final void bindService() {
        bindService(true);
    }
}
