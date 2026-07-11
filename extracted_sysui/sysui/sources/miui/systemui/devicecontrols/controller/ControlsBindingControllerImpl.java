package miui.systemui.devicecontrols.controller;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import android.os.UserHandle;
import android.service.controls.Control;
import android.service.controls.IControlsActionCallback;
import android.service.controls.IControlsSubscriber;
import android.service.controls.IControlsSubscription;
import android.service.controls.actions.ControlAction;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.android.systemui.settings.UserTracker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.devicecontrols.controller.ControlsBindingController;
import miui.systemui.devicecontrols.controller.ControlsBindingControllerImpl;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
@DeviceControlsScope
@VisibleForTesting
public class ControlsBindingControllerImpl implements ControlsBindingController {
    private static final long MAX_CONTROLS_REQUEST = 100000;
    private static final long SUGGESTED_CONTROLS_REQUEST = 300;
    private static final long SUGGESTED_STRUCTURES = 6;
    private static final String TAG = "ControlsBindingControllerImpl";
    private final ControlsBindingControllerImpl$actionCallbackService$1 actionCallbackService;
    private final DelayableExecutor backgroundExecutor;
    private final Context context;
    private ControlsProviderLifecycleManager currentProvider;
    private UserHandle currentUser;
    private final E0.a lazyController;
    private LoadSubscriber loadSubscriber;
    private StatefulControlSubscriber statefulControlSubscriber;
    public static final Companion Companion = new Companion(null);
    private static final ControlsBindingControllerImpl$Companion$emptyCallback$1 emptyCallback = new ControlsBindingController.LoadCallback() { // from class: miui.systemui.devicecontrols.controller.ControlsBindingControllerImpl$Companion$emptyCallback$1
        /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
        public void accept2(List<Control> controls) {
            kotlin.jvm.internal.n.g(controls, "controls");
        }

        @Override // miui.systemui.devicecontrols.controller.ControlsBindingController.LoadCallback
        public void error(String message) {
            kotlin.jvm.internal.n.g(message, "message");
        }

        @Override // java.util.function.Consumer
        public /* bridge */ /* synthetic */ void accept(List<? extends Control> list) {
            accept2((List<Control>) list);
        }
    };

    public abstract class CallbackRunnable implements Runnable {
        private final ControlsProviderLifecycleManager provider;
        final /* synthetic */ ControlsBindingControllerImpl this$0;
        private final IBinder token;

        public CallbackRunnable(ControlsBindingControllerImpl controlsBindingControllerImpl, IBinder token) {
            kotlin.jvm.internal.n.g(token, "token");
            this.this$0 = controlsBindingControllerImpl;
            this.token = token;
            this.provider = controlsBindingControllerImpl.currentProvider;
        }

        public abstract void doRun();

        public final ControlsProviderLifecycleManager getProvider() {
            return this.provider;
        }

        public final IBinder getToken() {
            return this.token;
        }

        @Override // java.lang.Runnable
        public void run() {
            ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.provider;
            if (controlsProviderLifecycleManager == null) {
                Log.e(ControlsBindingControllerImpl.TAG, "No current provider set");
                return;
            }
            if (!kotlin.jvm.internal.n.c(controlsProviderLifecycleManager.getUser(), this.this$0.currentUser)) {
                Log.e(ControlsBindingControllerImpl.TAG, "User " + this.provider.getUser() + " is not current user");
                return;
            }
            if (!kotlin.jvm.internal.n.c(this.token, this.provider.getToken())) {
                Log.e(ControlsBindingControllerImpl.TAG, "Provider for token:" + this.token + " does not exist anymore");
                this.provider.setToken(this.token);
            }
            doRun();
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final class LoadSubscriber extends IControlsSubscriber.Stub {
        private Function0 _loadCancelInternal;
        private ControlsBindingController.LoadCallback callback;
        private AtomicBoolean isTerminated;
        private final ArrayList<Control> loadedControls;
        private final long requestLimit;
        private IControlsSubscription subscription;
        final /* synthetic */ ControlsBindingControllerImpl this$0;

        public LoadSubscriber(ControlsBindingControllerImpl controlsBindingControllerImpl, ControlsBindingController.LoadCallback callback, long j2) {
            kotlin.jvm.internal.n.g(callback, "callback");
            this.this$0 = controlsBindingControllerImpl;
            this.callback = callback;
            this.requestLimit = j2;
            this.loadedControls = new ArrayList<>();
            this.isTerminated = new AtomicBoolean(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void loadCancel$lambda$1(LoadSubscriber this$0) {
            kotlin.jvm.internal.n.g(this$0, "this$0");
            Function0 function0 = this$0._loadCancelInternal;
            if (function0 != null) {
                Log.d(ControlsBindingControllerImpl.TAG, "Canceling loadSubscribtion");
                function0.invoke();
            }
        }

        private final void maybeTerminateAndRun(final Runnable runnable) {
            if (this.isTerminated.get()) {
                return;
            }
            this._loadCancelInternal = ControlsBindingControllerImpl$LoadSubscriber$maybeTerminateAndRun$1.INSTANCE;
            this.callback = ControlsBindingControllerImpl.emptyCallback;
            ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.this$0.currentProvider;
            if (controlsProviderLifecycleManager != null) {
                controlsProviderLifecycleManager.cancelLoadTimeout();
            }
            this.this$0.backgroundExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.d
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsBindingControllerImpl.LoadSubscriber.maybeTerminateAndRun$lambda$3(this.f5540a, runnable);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void maybeTerminateAndRun$lambda$3(LoadSubscriber this$0, Runnable postTerminateFn) {
            kotlin.jvm.internal.n.g(this$0, "this$0");
            kotlin.jvm.internal.n.g(postTerminateFn, "$postTerminateFn");
            this$0.isTerminated.compareAndSet(false, true);
            postTerminateFn.run();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onNext$lambda$2(LoadSubscriber this$0, Control c2, ControlsBindingControllerImpl this$1, IBinder token) {
            kotlin.jvm.internal.n.g(this$0, "this$0");
            kotlin.jvm.internal.n.g(c2, "$c");
            kotlin.jvm.internal.n.g(this$1, "this$1");
            kotlin.jvm.internal.n.g(token, "$token");
            if (this$0.isTerminated.get()) {
                return;
            }
            this$0.loadedControls.add(c2);
            if (this$0.loadedControls.size() >= this$0.requestLimit) {
                ArrayList<Control> arrayList = this$0.loadedControls;
                IControlsSubscription iControlsSubscription = this$0.subscription;
                if (iControlsSubscription == null) {
                    kotlin.jvm.internal.n.w("subscription");
                    iControlsSubscription = null;
                }
                this$0.maybeTerminateAndRun(new OnCancelAndLoadRunnable(this$1, token, arrayList, iControlsSubscription, this$0.callback));
            }
        }

        public final ControlsBindingController.LoadCallback getCallback() {
            return this.callback;
        }

        public final ArrayList<Control> getLoadedControls() {
            return this.loadedControls;
        }

        public final long getRequestLimit() {
            return this.requestLimit;
        }

        public final Runnable loadCancel() {
            return new Runnable() { // from class: miui.systemui.devicecontrols.controller.b
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsBindingControllerImpl.LoadSubscriber.loadCancel$lambda$1(this.f5535a);
                }
            };
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onComplete(IBinder token) {
            kotlin.jvm.internal.n.g(token, "token");
            maybeTerminateAndRun(new OnLoadRunnable(this.this$0, token, this.loadedControls, this.callback));
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onError(IBinder token, String s2) {
            kotlin.jvm.internal.n.g(token, "token");
            kotlin.jvm.internal.n.g(s2, "s");
            maybeTerminateAndRun(new OnLoadErrorRunnable(this.this$0, token, s2, this.callback));
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onNext(final IBinder token, final Control c2) {
            kotlin.jvm.internal.n.g(token, "token");
            kotlin.jvm.internal.n.g(c2, "c");
            DelayableExecutor delayableExecutor = this.this$0.backgroundExecutor;
            final ControlsBindingControllerImpl controlsBindingControllerImpl = this.this$0;
            delayableExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.c
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsBindingControllerImpl.LoadSubscriber.onNext$lambda$2(this.f5536a, c2, controlsBindingControllerImpl, token);
                }
            });
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onSubscribe(IBinder token, IControlsSubscription subs) {
            kotlin.jvm.internal.n.g(token, "token");
            kotlin.jvm.internal.n.g(subs, "subs");
            this.subscription = subs;
            this._loadCancelInternal = new ControlsBindingControllerImpl$LoadSubscriber$onSubscribe$1(this.this$0, this);
            this.this$0.backgroundExecutor.execute(new OnSubscribeRunnable(this.this$0, token, subs, this.requestLimit));
        }

        public final void setCallback(ControlsBindingController.LoadCallback loadCallback) {
            kotlin.jvm.internal.n.g(loadCallback, "<set-?>");
            this.callback = loadCallback;
        }
    }

    public final class OnActionResponseRunnable extends CallbackRunnable {
        private final String controlId;
        private final int response;
        final /* synthetic */ ControlsBindingControllerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OnActionResponseRunnable(ControlsBindingControllerImpl controlsBindingControllerImpl, IBinder token, String controlId, int i2) {
            super(controlsBindingControllerImpl, token);
            kotlin.jvm.internal.n.g(token, "token");
            kotlin.jvm.internal.n.g(controlId, "controlId");
            this.this$0 = controlsBindingControllerImpl;
            this.controlId = controlId;
            this.response = i2;
        }

        @Override // miui.systemui.devicecontrols.controller.ControlsBindingControllerImpl.CallbackRunnable
        public void doRun() {
            ControlsProviderLifecycleManager provider = getProvider();
            if (provider != null) {
                ((ControlsController) this.this$0.lazyController.get()).onActionResponse(provider.getComponentName(), this.controlId, this.response);
            }
        }

        public final String getControlId() {
            return this.controlId;
        }

        public final int getResponse() {
            return this.response;
        }
    }

    public final class OnCancelAndLoadRunnable extends CallbackRunnable {
        private final ControlsBindingController.LoadCallback callback;
        private final List<Control> list;
        private final IControlsSubscription subscription;
        final /* synthetic */ ControlsBindingControllerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OnCancelAndLoadRunnable(ControlsBindingControllerImpl controlsBindingControllerImpl, IBinder token, List<Control> list, IControlsSubscription subscription, ControlsBindingController.LoadCallback callback) {
            super(controlsBindingControllerImpl, token);
            kotlin.jvm.internal.n.g(token, "token");
            kotlin.jvm.internal.n.g(list, "list");
            kotlin.jvm.internal.n.g(subscription, "subscription");
            kotlin.jvm.internal.n.g(callback, "callback");
            this.this$0 = controlsBindingControllerImpl;
            this.list = list;
            this.subscription = subscription;
            this.callback = callback;
        }

        @Override // miui.systemui.devicecontrols.controller.ControlsBindingControllerImpl.CallbackRunnable
        public void doRun() {
            Log.d(ControlsBindingControllerImpl.TAG, "LoadSubscription: Canceling and loading controls");
            ControlsProviderLifecycleManager provider = getProvider();
            if (provider != null) {
                provider.cancelSubscription(this.subscription);
            }
            this.callback.accept(this.list);
        }

        public final ControlsBindingController.LoadCallback getCallback() {
            return this.callback;
        }

        public final List<Control> getList() {
            return this.list;
        }

        public final IControlsSubscription getSubscription() {
            return this.subscription;
        }
    }

    public final class OnLoadErrorRunnable extends CallbackRunnable {
        private final ControlsBindingController.LoadCallback callback;
        private final String error;
        final /* synthetic */ ControlsBindingControllerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OnLoadErrorRunnable(ControlsBindingControllerImpl controlsBindingControllerImpl, IBinder token, String error, ControlsBindingController.LoadCallback callback) {
            super(controlsBindingControllerImpl, token);
            kotlin.jvm.internal.n.g(token, "token");
            kotlin.jvm.internal.n.g(error, "error");
            kotlin.jvm.internal.n.g(callback, "callback");
            this.this$0 = controlsBindingControllerImpl;
            this.error = error;
            this.callback = callback;
        }

        @Override // miui.systemui.devicecontrols.controller.ControlsBindingControllerImpl.CallbackRunnable
        public void doRun() {
            this.callback.error(this.error);
            ControlsProviderLifecycleManager provider = getProvider();
            if (provider != null) {
                Log.e(ControlsBindingControllerImpl.TAG, "onError receive from '" + provider.getComponentName() + "': " + this.error);
            }
        }

        public final ControlsBindingController.LoadCallback getCallback() {
            return this.callback;
        }

        public final String getError() {
            return this.error;
        }
    }

    public final class OnLoadRunnable extends CallbackRunnable {
        private final ControlsBindingController.LoadCallback callback;
        private final List<Control> list;
        final /* synthetic */ ControlsBindingControllerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OnLoadRunnable(ControlsBindingControllerImpl controlsBindingControllerImpl, IBinder token, List<Control> list, ControlsBindingController.LoadCallback callback) {
            super(controlsBindingControllerImpl, token);
            kotlin.jvm.internal.n.g(token, "token");
            kotlin.jvm.internal.n.g(list, "list");
            kotlin.jvm.internal.n.g(callback, "callback");
            this.this$0 = controlsBindingControllerImpl;
            this.list = list;
            this.callback = callback;
        }

        @Override // miui.systemui.devicecontrols.controller.ControlsBindingControllerImpl.CallbackRunnable
        public void doRun() {
            Log.d(ControlsBindingControllerImpl.TAG, "LoadSubscription: Complete and loading controls " + this.list.size());
            this.callback.accept(this.list);
        }

        public final ControlsBindingController.LoadCallback getCallback() {
            return this.callback;
        }

        public final List<Control> getList() {
            return this.list;
        }
    }

    public final class OnSubscribeRunnable extends CallbackRunnable {
        private final long requestLimit;
        private final IControlsSubscription subscription;
        final /* synthetic */ ControlsBindingControllerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OnSubscribeRunnable(ControlsBindingControllerImpl controlsBindingControllerImpl, IBinder token, IControlsSubscription subscription, long j2) {
            super(controlsBindingControllerImpl, token);
            kotlin.jvm.internal.n.g(token, "token");
            kotlin.jvm.internal.n.g(subscription, "subscription");
            this.this$0 = controlsBindingControllerImpl;
            this.subscription = subscription;
            this.requestLimit = j2;
        }

        @Override // miui.systemui.devicecontrols.controller.ControlsBindingControllerImpl.CallbackRunnable
        public void doRun() {
            Log.d(ControlsBindingControllerImpl.TAG, "LoadSubscription: Starting subscription");
            ControlsProviderLifecycleManager provider = getProvider();
            if (provider != null) {
                provider.startSubscription(this.subscription, this.requestLimit);
            }
        }

        public final long getRequestLimit() {
            return this.requestLimit;
        }

        public final IControlsSubscription getSubscription() {
            return this.subscription;
        }
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [miui.systemui.devicecontrols.controller.ControlsBindingControllerImpl$actionCallbackService$1] */
    public ControlsBindingControllerImpl(Context context, @Background DelayableExecutor backgroundExecutor, E0.a lazyController, UserTracker userTracker) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(backgroundExecutor, "backgroundExecutor");
        kotlin.jvm.internal.n.g(lazyController, "lazyController");
        kotlin.jvm.internal.n.g(userTracker, "userTracker");
        this.context = context;
        this.backgroundExecutor = backgroundExecutor;
        this.lazyController = lazyController;
        this.currentUser = userTracker.getUserHandle();
        this.actionCallbackService = new IControlsActionCallback.Stub() { // from class: miui.systemui.devicecontrols.controller.ControlsBindingControllerImpl$actionCallbackService$1
            @Override // android.service.controls.IControlsActionCallback
            public void accept(IBinder token, String controlId, int i2) {
                kotlin.jvm.internal.n.g(token, "token");
                kotlin.jvm.internal.n.g(controlId, "controlId");
                this.this$0.backgroundExecutor.execute(new ControlsBindingControllerImpl.OnActionResponseRunnable(this.this$0, token, controlId, i2));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onComponentRemoved$lambda$2(ControlsBindingControllerImpl this$0, ComponentName componentName) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(componentName, "$componentName");
        ControlsProviderLifecycleManager controlsProviderLifecycleManager = this$0.currentProvider;
        if (controlsProviderLifecycleManager == null || !kotlin.jvm.internal.n.c(controlsProviderLifecycleManager.getComponentName(), componentName)) {
            return;
        }
        this$0.unbind();
    }

    private final ControlsProviderLifecycleManager retrieveLifecycleManager(ComponentName componentName) {
        ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.currentProvider;
        if (controlsProviderLifecycleManager != null) {
            if (!kotlin.jvm.internal.n.c(controlsProviderLifecycleManager != null ? controlsProviderLifecycleManager.getComponentName() : null, componentName)) {
                unbind();
            }
        }
        ControlsProviderLifecycleManager controlsProviderLifecycleManagerCreateProviderManager$miui_devicecontrols_release = this.currentProvider;
        if (controlsProviderLifecycleManagerCreateProviderManager$miui_devicecontrols_release == null) {
            controlsProviderLifecycleManagerCreateProviderManager$miui_devicecontrols_release = createProviderManager$miui_devicecontrols_release(componentName);
        }
        this.currentProvider = controlsProviderLifecycleManagerCreateProviderManager$miui_devicecontrols_release;
        return controlsProviderLifecycleManagerCreateProviderManager$miui_devicecontrols_release;
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsBindingController
    public void action(ComponentName componentName, ControlInfo controlInfo, ControlAction action) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(controlInfo, "controlInfo");
        kotlin.jvm.internal.n.g(action, "action");
        if (this.statefulControlSubscriber == null) {
            Log.w(TAG, "No actions can occur outside of an active subscription. Ignoring.");
        } else {
            retrieveLifecycleManager(componentName).maybeBindAndSendAction(controlInfo.getControlId(), action);
        }
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsBindingController
    public Runnable bindAndLoad(ComponentName component, ControlsBindingController.LoadCallback callback) {
        kotlin.jvm.internal.n.g(component, "component");
        kotlin.jvm.internal.n.g(callback, "callback");
        LoadSubscriber loadSubscriber = this.loadSubscriber;
        if (loadSubscriber != null) {
            loadSubscriber.loadCancel();
        }
        LoadSubscriber loadSubscriber2 = new LoadSubscriber(this, callback, MAX_CONTROLS_REQUEST);
        this.loadSubscriber = loadSubscriber2;
        retrieveLifecycleManager(component).maybeBindAndLoad(loadSubscriber2);
        return loadSubscriber2.loadCancel();
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsBindingController
    public void bindAndLoadSuggested(ComponentName component, ControlsBindingController.LoadCallback callback) {
        kotlin.jvm.internal.n.g(component, "component");
        kotlin.jvm.internal.n.g(callback, "callback");
        LoadSubscriber loadSubscriber = this.loadSubscriber;
        if (loadSubscriber != null) {
            loadSubscriber.loadCancel();
        }
        LoadSubscriber loadSubscriber2 = new LoadSubscriber(this, callback, 300L);
        this.loadSubscriber = loadSubscriber2;
        retrieveLifecycleManager(component).maybeBindAndLoadSuggested(loadSubscriber2);
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsBindingController
    public void bindService(ComponentName component) {
        kotlin.jvm.internal.n.g(component, "component");
        retrieveLifecycleManager(component).bindService();
    }

    @Override // miui.systemui.devicecontrols.util.UserAwareController
    public void changeUser(UserHandle newUser) {
        kotlin.jvm.internal.n.g(newUser, "newUser");
        if (kotlin.jvm.internal.n.c(newUser, this.currentUser)) {
            return;
        }
        unbind();
        this.currentUser = newUser;
    }

    @VisibleForTesting
    public ControlsProviderLifecycleManager createProviderManager$miui_devicecontrols_release(ComponentName component) {
        kotlin.jvm.internal.n.g(component, "component");
        return new ControlsProviderLifecycleManager(this.context, this.backgroundExecutor, this.actionCallbackService, this.currentUser, component);
    }

    @Override // miui.systemui.devicecontrols.util.UserAwareController
    public int getCurrentUserId() {
        return this.currentUser.getIdentifier();
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsBindingController
    public void onComponentRemoved(final ComponentName componentName) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        this.backgroundExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.a
            @Override // java.lang.Runnable
            public final void run() {
                ControlsBindingControllerImpl.onComponentRemoved$lambda$2(this.f5533a, componentName);
            }
        });
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsBindingController
    public void subscribe(StructureInfo structureInfo) {
        kotlin.jvm.internal.n.g(structureInfo, "structureInfo");
        unsubscribe();
        ControlsProviderLifecycleManager controlsProviderLifecycleManagerRetrieveLifecycleManager = retrieveLifecycleManager(structureInfo.getComponentName());
        Object obj = this.lazyController.get();
        kotlin.jvm.internal.n.f(obj, "get(...)");
        StatefulControlSubscriber statefulControlSubscriber = new StatefulControlSubscriber((ControlsController) obj, controlsProviderLifecycleManagerRetrieveLifecycleManager, this.backgroundExecutor, MAX_CONTROLS_REQUEST);
        this.statefulControlSubscriber = statefulControlSubscriber;
        List<ControlInfo> controls = structureInfo.getControls();
        ArrayList arrayList = new ArrayList(I0.n.o(controls, 10));
        Iterator<T> it = controls.iterator();
        while (it.hasNext()) {
            arrayList.add(((ControlInfo) it.next()).getControlId());
        }
        controlsProviderLifecycleManagerRetrieveLifecycleManager.maybeBindAndSubscribe(arrayList, statefulControlSubscriber);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("  ControlsBindingController:\n");
        sb.append("    currentUser=" + this.currentUser + "\n");
        sb.append("    StatefulControlSubscriber=" + this.statefulControlSubscriber);
        sb.append("    Providers=" + this.currentProvider + "\n");
        String string = sb.toString();
        kotlin.jvm.internal.n.f(string, "toString(...)");
        return string;
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsBindingController
    public void unbind() {
        unsubscribe();
        LoadSubscriber loadSubscriber = this.loadSubscriber;
        if (loadSubscriber != null) {
            loadSubscriber.loadCancel();
        }
        this.loadSubscriber = null;
        ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.currentProvider;
        if (controlsProviderLifecycleManager != null) {
            controlsProviderLifecycleManager.unbindService();
        }
        this.currentProvider = null;
        Log.d(TAG, "unbind");
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsBindingController
    public void unsubscribe() {
        StatefulControlSubscriber statefulControlSubscriber = this.statefulControlSubscriber;
        if (statefulControlSubscriber != null) {
            statefulControlSubscriber.cancel();
        }
        this.statefulControlSubscriber = null;
    }
}
