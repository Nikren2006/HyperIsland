package miui.systemui.devicecontrols.management;

import I0.K;
import I0.u;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;
import android.os.UserHandle;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.settings.UserTracker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.devicecontrols.ControlsServiceInfo;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;
import miui.systemui.devicecontrols.management.ControlsListingController;
import miui.systemui.devicecontrols.management.ServiceListing;

/* JADX INFO: loaded from: classes3.dex */
@DeviceControlsScope
public final class ControlsListingControllerImpl implements ControlsListingController {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DControlsListingControllerImpl";
    private Set<ComponentName> availableComponents;
    private List<? extends ServiceInfo> availableServices;
    private final Executor backgroundExecutor;
    private final Set<ControlsListingController.ControlsListingCallback> callbacks;
    private final Context context;
    private int currentUserId;
    private ServiceListing serviceListing;
    private final Function2 serviceListingBuilder;
    private final ServiceListing.Callback serviceListingCallback;
    private AtomicInteger userChangeInProgress;

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.management.ControlsListingControllerImpl$1, reason: invalid class name */
    public /* synthetic */ class AnonymousClass1 extends kotlin.jvm.internal.l implements Function2 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2, ControlsListingControllerImplKt.class, "createServiceListing", "createServiceListing(Landroid/content/Context;Ljava/util/concurrent/Executor;)Lmiui/systemui/devicecontrols/management/ServiceListing;", 1);
        }

        @Override // kotlin.jvm.functions.Function2
        public final ServiceListing invoke(Context p02, Executor p12) {
            kotlin.jvm.internal.n.g(p02, "p0");
            kotlin.jvm.internal.n.g(p12, "p1");
            return ControlsListingControllerImplKt.createServiceListing(p02, p12);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @VisibleForTesting
    public ControlsListingControllerImpl(Context context, @Background Executor backgroundExecutor, Function2 serviceListingBuilder, UserTracker userTracker) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(backgroundExecutor, "backgroundExecutor");
        kotlin.jvm.internal.n.g(serviceListingBuilder, "serviceListingBuilder");
        kotlin.jvm.internal.n.g(userTracker, "userTracker");
        this.context = context;
        this.backgroundExecutor = backgroundExecutor;
        this.serviceListingBuilder = serviceListingBuilder;
        this.serviceListing = (ServiceListing) serviceListingBuilder.invoke(context, backgroundExecutor);
        this.callbacks = new LinkedHashSet();
        this.availableComponents = K.b();
        this.availableServices = I0.m.h();
        this.userChangeInProgress = new AtomicInteger(0);
        this.currentUserId = userTracker.getUserId();
        ServiceListing.Callback callback = new ServiceListing.Callback() { // from class: miui.systemui.devicecontrols.management.b
            @Override // miui.systemui.devicecontrols.management.ServiceListing.Callback
            public final void onServicesReloaded(List list, boolean z2) {
                ControlsListingControllerImpl.serviceListingCallback$lambda$4(this.f5607a, list, z2);
            }
        };
        this.serviceListingCallback = callback;
        Log.d(TAG, "Initializing");
        this.serviceListing.addCallback(callback);
        this.serviceListing.setListening(true);
        this.serviceListing.reload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addCallback$lambda$7(ControlsListingControllerImpl this$0, ControlsListingController.ControlsListingCallback listener) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(listener, "$listener");
        if (this$0.userChangeInProgress.get() > 0) {
            this$0.addCallback(listener);
            return;
        }
        List<ControlsServiceInfo> currentServices = this$0.getCurrentServices();
        Iterator<T> it = currentServices.iterator();
        while (it.hasNext()) {
            ((ControlsServiceInfo) it.next()).resolvePanelActivity(true);
        }
        Log.d(TAG, "Subscribing callback, service count: " + currentServices.size());
        this$0.callbacks.add(listener);
        listener.onServicesUpdated(currentServices);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void changeUser$lambda$5(ControlsListingControllerImpl this$0, UserHandle newUser) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(newUser, "$newUser");
        if (this$0.userChangeInProgress.decrementAndGet() == 0) {
            this$0.currentUserId = newUser.getIdentifier();
            this$0.context.createContextAsUser(newUser, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void removeCallback$lambda$8(ControlsListingControllerImpl this$0, ControlsListingController.ControlsListingCallback listener) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(listener, "$listener");
        Log.d(TAG, "Unsubscribing callback");
        this$0.callbacks.remove(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void serviceListingCallback$lambda$4(final ControlsListingControllerImpl this$0, List list, final boolean z2) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.d(list);
        final List listK0 = u.k0(list);
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = listK0.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((ServiceInfo) it.next()).getComponentName());
        }
        this$0.backgroundExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.management.a
            @Override // java.lang.Runnable
            public final void run() {
                ControlsListingControllerImpl.serviceListingCallback$lambda$4$lambda$3(this.f5603a, linkedHashSet, z2, listK0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void serviceListingCallback$lambda$4$lambda$3(ControlsListingControllerImpl this$0, Set newComponents, boolean z2, List newServices) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(newComponents, "$newComponents");
        kotlin.jvm.internal.n.g(newServices, "$newServices");
        if (this$0.userChangeInProgress.get() > 0) {
            return;
        }
        if (!newComponents.equals(this$0.availableComponents) || z2) {
            Log.d(TAG, "ServiceConfig reloaded, count: " + newComponents.size());
            this$0.availableComponents = newComponents;
            this$0.availableServices = newServices;
            List<ControlsServiceInfo> currentServices = this$0.getCurrentServices();
            Iterator<T> it = currentServices.iterator();
            while (it.hasNext()) {
                ((ControlsServiceInfo) it.next()).resolvePanelActivity(true);
            }
            Iterator<T> it2 = this$0.callbacks.iterator();
            while (it2.hasNext()) {
                ((ControlsListingController.ControlsListingCallback) it2.next()).onServicesUpdated(currentServices);
            }
        }
    }

    @Override // miui.systemui.devicecontrols.util.UserAwareController
    public void changeUser(final UserHandle newUser) {
        kotlin.jvm.internal.n.g(newUser, "newUser");
        this.userChangeInProgress.incrementAndGet();
        this.backgroundExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.management.d
            @Override // java.lang.Runnable
            public final void run() {
                ControlsListingControllerImpl.changeUser$lambda$5(this.f5610a, newUser);
            }
        });
    }

    @Override // miui.systemui.devicecontrols.management.ControlsListingController
    public CharSequence getAppLabel(ComponentName name) {
        Object next;
        kotlin.jvm.internal.n.g(name, "name");
        Iterator<T> it = getCurrentServices().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (kotlin.jvm.internal.n.c(((ControlsServiceInfo) next).componentName, name)) {
                break;
            }
        }
        ControlsServiceInfo controlsServiceInfo = (ControlsServiceInfo) next;
        if (controlsServiceInfo != null) {
            return controlsServiceInfo.loadLabel();
        }
        return null;
    }

    @Override // miui.systemui.devicecontrols.management.ControlsListingController
    public List<ControlsServiceInfo> getCurrentServices() {
        List<? extends ServiceInfo> list = this.availableServices;
        ArrayList arrayList = new ArrayList(I0.n.o(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new ControlsServiceInfo(this.context, (ServiceInfo) it.next()));
        }
        return arrayList;
    }

    @Override // miui.systemui.devicecontrols.util.UserAwareController
    public int getCurrentUserId() {
        return this.currentUserId;
    }

    @Override // miui.systemui.devicecontrols.management.ControlsListingController
    public void release() {
        this.serviceListing.removeCallback(this.serviceListingCallback);
        this.callbacks.clear();
        this.serviceListing.setListening(false);
    }

    @Override // miui.systemui.devicecontrols.management.ControlsListingController
    public void reloadServices(boolean z2) {
        this.serviceListing.addCallback(this.serviceListingCallback);
        this.serviceListing.setListening(true);
        this.serviceListing.reload(z2);
    }

    @Override // miui.systemui.policy.CallbackController
    public void addCallback(final ControlsListingController.ControlsListingCallback listener) {
        kotlin.jvm.internal.n.g(listener, "listener");
        this.backgroundExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.management.c
            @Override // java.lang.Runnable
            public final void run() {
                ControlsListingControllerImpl.addCallback$lambda$7(this.f5608a, listener);
            }
        });
    }

    @Override // miui.systemui.policy.CallbackController
    public void removeCallback(final ControlsListingController.ControlsListingCallback listener) {
        kotlin.jvm.internal.n.g(listener, "listener");
        this.backgroundExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.management.e
            @Override // java.lang.Runnable
            public final void run() {
                ControlsListingControllerImpl.removeCallback$lambda$8(this.f5612a, listener);
            }
        });
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ControlsListingControllerImpl(Context context, Executor executor, UserTracker userTracker) {
        this(context, executor, AnonymousClass1.INSTANCE, userTracker);
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(executor, "executor");
        kotlin.jvm.internal.n.g(userTracker, "userTracker");
    }
}
