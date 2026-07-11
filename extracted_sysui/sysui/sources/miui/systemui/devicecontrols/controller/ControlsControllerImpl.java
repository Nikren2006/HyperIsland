package miui.systemui.devicecontrols.controller;

import I0.AbstractC0184l;
import I0.L;
import android.app.PendingIntent;
import android.app.backup.BackupManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.UserHandle;
import android.service.controls.Control;
import android.service.controls.actions.ControlAction;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.android.systemui.settings.UserTracker;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.devicecontrols.ControlStatus;
import miui.systemui.devicecontrols.controller.ControlsBindingController;
import miui.systemui.devicecontrols.controller.ControlsController;
import miui.systemui.devicecontrols.controller.ControlsControllerImpl;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;
import miui.systemui.devicecontrols.management.ControlsListingController;
import miui.systemui.devicecontrols.ui.MiuiControlsUiController;
import miui.systemui.devicecontrols.util.ControlsUtils;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
@DeviceControlsScope
public final class ControlsControllerImpl implements ControlsController {
    public static final Companion Companion = new Companion(null);
    private static final int DEFAULT_ENABLED = 1;
    private static final String MIHOME_STRUCTURE_CHANGED_ACTION = "com.xiaomi.smarthome/homeChange";
    private static final String PERMISSION_SELF = "com.android.systemui.permission.SELF";
    public static final int SUGGESTED_CONTROLS_PER_STRUCTURE = 16;
    public static final int SUGGESTED_NORMAL_CONTROLS_PER_STRUCTURE = 50;
    public static final int SUGGESTED_SENSE_CONTROLS_PER_STRUCTURE = 4;
    private static final String TAG = "ControlsControllerImpl";
    private static final long USER_CHANGE_RETRY_DELAY = 500;
    private AuxiliaryPersistenceWrapper auxiliaryPersistenceWrapper;
    private final DelayableExecutor bgExecutor;
    private final ControlsBindingController bindingController;
    private final BroadcastDispatcher broadcastDispatcher;
    private final Context context;
    private UserHandle currentUser;
    private final ControlsControllerImpl$homeChangeReceiver$1 homeChangeReceiver;
    private String lastStructure;
    private final ControlsControllerImpl$listingCallback$1 listingCallback;
    private final ControlsListingController listingController;
    private boolean loadingData;
    private final DelayableExecutor mainExecutor;
    private final ControlsFavoritePersistenceWrapper persistenceWrapper;
    private final BroadcastReceiver restoreFinishedReceiver;
    private final List<Consumer<Boolean>> seedingCallbacks;
    private boolean seedingInProgress;
    private final ContentObserver settingObserver;
    private final Context sysUIContext;
    private final MiuiControlsUiController uiController;
    private boolean userChanging;
    private UserStructure userStructure;
    private final ControlsControllerImpl$userSwitchReceiver$1 userSwitchReceiver;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.controller.ControlsControllerImpl$loadForComponent$2, reason: invalid class name */
    public static final class AnonymousClass2 implements ControlsBindingController.LoadCallback {
        final /* synthetic */ ComponentName $componentName;
        final /* synthetic */ Consumer<ControlsController.LoadData> $dataCallback;

        public AnonymousClass2(ComponentName componentName, Consumer<ControlsController.LoadData> consumer) {
            this.$componentName = componentName;
            this.$dataCallback = consumer;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void accept$lambda$5(ComponentName componentName, List controls, ControlsControllerImpl this$0, Consumer dataCallback) {
            kotlin.jvm.internal.n.g(componentName, "$componentName");
            kotlin.jvm.internal.n.g(controls, "$controls");
            kotlin.jvm.internal.n.g(this$0, "this$0");
            kotlin.jvm.internal.n.g(dataCallback, "$dataCallback");
            List<ControlInfo> controlsForComponent = Favorites.INSTANCE.getControlsForComponent(componentName);
            ArrayList arrayList = new ArrayList(I0.n.o(controlsForComponent, 10));
            Iterator<T> it = controlsForComponent.iterator();
            while (it.hasNext()) {
                arrayList.add(((ControlInfo) it.next()).getControlId());
            }
            Favorites favorites = Favorites.INSTANCE;
            if (favorites.updateControls(componentName, controls)) {
                this$0.persistenceWrapper.storeFavorites(favorites.getAllStructures());
            }
            ArrayList arrayList2 = new ArrayList(I0.n.o(controls, 10));
            Iterator it2 = controls.iterator();
            while (it2.hasNext()) {
                arrayList2.add(new ControlStatus((Control) it2.next(), componentName, false, false, 8, null));
            }
            this$0.findRemoved(I0.u.o0(arrayList), controls);
            ArrayList arrayList3 = new ArrayList();
            for (Object obj : arrayList2) {
                if (arrayList.contains(((ControlStatus) obj).getControlId())) {
                    arrayList3.add(obj);
                }
            }
            Iterator it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                ((ControlStatus) it3.next()).setFavorite(true);
            }
            ArrayList arrayList4 = new ArrayList();
            Iterator it4 = controls.iterator();
            while (it4.hasNext()) {
                Control control = (Control) it4.next();
                if (!arrayList.contains(control.getControlId())) {
                    arrayList4.add(new ControlStatus(control, componentName, false, false, 8, null));
                }
            }
            dataCallback.accept(ControlsControllerKt.createLoadDataObject$default(arrayList2, arrayList3, arrayList, false, 8, null));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void error$lambda$9(ComponentName componentName, Consumer dataCallback, ControlsControllerImpl this$0) {
            kotlin.jvm.internal.n.g(componentName, "$componentName");
            kotlin.jvm.internal.n.g(dataCallback, "$dataCallback");
            kotlin.jvm.internal.n.g(this$0, "this$0");
            List<StructureInfo> structuresForComponent = Favorites.INSTANCE.getStructuresForComponent(componentName);
            ArrayList arrayList = new ArrayList();
            for (StructureInfo structureInfo : structuresForComponent) {
                List<ControlInfo> controls = structureInfo.getControls();
                ArrayList arrayList2 = new ArrayList(I0.n.o(controls, 10));
                Iterator<T> it = controls.iterator();
                while (it.hasNext()) {
                    arrayList2.add(this$0.createRemovedStatus(componentName, (ControlInfo) it.next(), structureInfo.getStructure(), false));
                }
                I0.r.t(arrayList, arrayList2);
            }
            ArrayList arrayList3 = new ArrayList(I0.n.o(arrayList, 10));
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                arrayList3.add(((ControlStatus) it2.next()).getControl().getControlId());
            }
            dataCallback.accept(ControlsControllerKt.createLoadDataObject(arrayList, arrayList, arrayList3, true));
        }

        @Override // java.util.function.Consumer
        public /* bridge */ /* synthetic */ void accept(List<? extends Control> list) {
            accept2((List<Control>) list);
        }

        @Override // miui.systemui.devicecontrols.controller.ControlsBindingController.LoadCallback
        public void error(String message) {
            kotlin.jvm.internal.n.g(message, "message");
            DelayableExecutor delayableExecutor = ControlsControllerImpl.this.bgExecutor;
            final ComponentName componentName = this.$componentName;
            final Consumer<ControlsController.LoadData> consumer = this.$dataCallback;
            final ControlsControllerImpl controlsControllerImpl = ControlsControllerImpl.this;
            delayableExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.s
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsControllerImpl.AnonymousClass2.error$lambda$9(componentName, consumer, controlsControllerImpl);
                }
            });
        }

        /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
        public void accept2(final List<Control> controls) {
            kotlin.jvm.internal.n.g(controls, "controls");
            DelayableExecutor delayableExecutor = ControlsControllerImpl.this.bgExecutor;
            final ComponentName componentName = this.$componentName;
            final ControlsControllerImpl controlsControllerImpl = ControlsControllerImpl.this;
            final Consumer<ControlsController.LoadData> consumer = this.$dataCallback;
            delayableExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.r
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsControllerImpl.AnonymousClass2.accept$lambda$5(componentName, controls, controlsControllerImpl, consumer);
                }
            });
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.controller.ControlsControllerImpl$startSeeding$1, reason: invalid class name */
    public static final class AnonymousClass1 implements ControlsBindingController.LoadCallback {
        final /* synthetic */ Consumer<SeedResponse> $callback;
        final /* synthetic */ ComponentName $componentName;
        final /* synthetic */ boolean $didAnyFail;
        final /* synthetic */ List<ComponentName> $remaining;

        public AnonymousClass1(Consumer<SeedResponse> consumer, ComponentName componentName, List<ComponentName> list, boolean z2) {
            this.$callback = consumer;
            this.$componentName = componentName;
            this.$remaining = list;
            this.$didAnyFail = z2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void accept$lambda$0(List controls, Consumer callback, ComponentName componentName, ControlsControllerImpl this$0, List remaining, boolean z2) {
            kotlin.jvm.internal.n.g(controls, "$controls");
            kotlin.jvm.internal.n.g(callback, "$callback");
            kotlin.jvm.internal.n.g(componentName, "$componentName");
            kotlin.jvm.internal.n.g(this$0, "this$0");
            kotlin.jvm.internal.n.g(remaining, "$remaining");
            if (controls.isEmpty()) {
                String packageName = componentName.getPackageName();
                kotlin.jvm.internal.n.f(packageName, "getPackageName(...)");
                callback.accept(new SeedResponse(packageName, false));
                this$0.startSeeding(remaining, callback, z2);
                return;
            }
            this$0.saveFavoritesForComponents(controls, componentName);
            String packageName2 = componentName.getPackageName();
            kotlin.jvm.internal.n.f(packageName2, "getPackageName(...)");
            callback.accept(new SeedResponse(packageName2, true));
            this$0.startSeeding(remaining, callback, z2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void error$lambda$1(Consumer callback, ComponentName componentName, ControlsControllerImpl this$0, List remaining) {
            kotlin.jvm.internal.n.g(callback, "$callback");
            kotlin.jvm.internal.n.g(componentName, "$componentName");
            kotlin.jvm.internal.n.g(this$0, "this$0");
            kotlin.jvm.internal.n.g(remaining, "$remaining");
            String packageName = componentName.getPackageName();
            kotlin.jvm.internal.n.f(packageName, "getPackageName(...)");
            callback.accept(new SeedResponse(packageName, false));
            this$0.startSeeding(remaining, callback, true);
        }

        @Override // java.util.function.Consumer
        public /* bridge */ /* synthetic */ void accept(List<? extends Control> list) {
            accept2((List<Control>) list);
        }

        @Override // miui.systemui.devicecontrols.controller.ControlsBindingController.LoadCallback
        public void error(String message) {
            kotlin.jvm.internal.n.g(message, "message");
            Log.e(ControlsControllerImpl.TAG, "Unable to seed favorites: " + message);
            DelayableExecutor delayableExecutor = ControlsControllerImpl.this.bgExecutor;
            final Consumer<SeedResponse> consumer = this.$callback;
            final ComponentName componentName = this.$componentName;
            final ControlsControllerImpl controlsControllerImpl = ControlsControllerImpl.this;
            final List<ComponentName> list = this.$remaining;
            delayableExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.u
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsControllerImpl.AnonymousClass1.error$lambda$1(consumer, componentName, controlsControllerImpl, list);
                }
            });
        }

        /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
        public void accept2(final List<Control> controls) {
            kotlin.jvm.internal.n.g(controls, "controls");
            DelayableExecutor delayableExecutor = ControlsControllerImpl.this.bgExecutor;
            final Consumer<SeedResponse> consumer = this.$callback;
            final ComponentName componentName = this.$componentName;
            final ControlsControllerImpl controlsControllerImpl = ControlsControllerImpl.this;
            final List<ComponentName> list = this.$remaining;
            final boolean z2 = this.$didAnyFail;
            delayableExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.v
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsControllerImpl.AnonymousClass1.accept$lambda$0(controls, consumer, componentName, controlsControllerImpl, list, z2);
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [miui.systemui.devicecontrols.controller.ControlsControllerImpl$userSwitchReceiver$1] */
    /* JADX WARN: Type inference failed for: r2v6, types: [miui.systemui.devicecontrols.controller.ControlsControllerImpl$homeChangeReceiver$1] */
    public ControlsControllerImpl(@SystemUI Context sysUIContext, Context context, @Background DelayableExecutor bgExecutor, @Main DelayableExecutor mainExecutor, MiuiControlsUiController uiController, ControlsBindingController bindingController, ControlsListingController listingController, BroadcastDispatcher broadcastDispatcher, Optional<ControlsFavoritePersistenceWrapper> optionalWrapper, UserTracker userTracker) {
        kotlin.jvm.internal.n.g(sysUIContext, "sysUIContext");
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(bgExecutor, "bgExecutor");
        kotlin.jvm.internal.n.g(mainExecutor, "mainExecutor");
        kotlin.jvm.internal.n.g(uiController, "uiController");
        kotlin.jvm.internal.n.g(bindingController, "bindingController");
        kotlin.jvm.internal.n.g(listingController, "listingController");
        kotlin.jvm.internal.n.g(broadcastDispatcher, "broadcastDispatcher");
        kotlin.jvm.internal.n.g(optionalWrapper, "optionalWrapper");
        kotlin.jvm.internal.n.g(userTracker, "userTracker");
        this.sysUIContext = sysUIContext;
        this.context = context;
        this.bgExecutor = bgExecutor;
        this.mainExecutor = mainExecutor;
        this.uiController = uiController;
        this.bindingController = bindingController;
        this.listingController = listingController;
        this.broadcastDispatcher = broadcastDispatcher;
        this.userChanging = true;
        this.seedingCallbacks = new ArrayList();
        UserHandle userHandle = userTracker.getUserHandle();
        this.currentUser = userHandle;
        this.userChanging = false;
        this.userStructure = new UserStructure(sysUIContext, context, userHandle);
        ControlsFavoritePersistenceWrapper controlsFavoritePersistenceWrapperOrElseGet = optionalWrapper.orElseGet(new Supplier() { // from class: miui.systemui.devicecontrols.controller.l
            @Override // java.util.function.Supplier
            public final Object get() {
                return ControlsControllerImpl._init_$lambda$0(this.f5562a);
            }
        });
        kotlin.jvm.internal.n.f(controlsFavoritePersistenceWrapperOrElseGet, "orElseGet(...)");
        this.persistenceWrapper = controlsFavoritePersistenceWrapperOrElseGet;
        this.auxiliaryPersistenceWrapper = new AuxiliaryPersistenceWrapper(this.userStructure.getAuxiliaryFile(), bgExecutor);
        this.userSwitchReceiver = new BroadcastReceiver() { // from class: miui.systemui.devicecontrols.controller.ControlsControllerImpl$userSwitchReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                kotlin.jvm.internal.n.g(context2, "context");
                kotlin.jvm.internal.n.g(intent, "intent");
                if (kotlin.jvm.internal.n.c(intent.getAction(), "android.intent.action.USER_SWITCHED")) {
                    this.this$0.userChanging = true;
                    UserHandle userHandleOf = UserHandle.of(intent.getIntExtra("android.intent.extra.user_handle", getSendingUserId()));
                    if (kotlin.jvm.internal.n.c(this.this$0.currentUser, userHandleOf)) {
                        this.this$0.userChanging = false;
                        return;
                    }
                    ControlsControllerImpl controlsControllerImpl = this.this$0;
                    kotlin.jvm.internal.n.d(userHandleOf);
                    controlsControllerImpl.setValuesForUser(userHandleOf);
                }
            }
        };
        this.homeChangeReceiver = new BroadcastReceiver() { // from class: miui.systemui.devicecontrols.controller.ControlsControllerImpl$homeChangeReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                kotlin.jvm.internal.n.g(context2, "context");
                kotlin.jvm.internal.n.g(intent, "intent");
                if ("com.xiaomi.smarthome/homeChange".equals(intent.getAction())) {
                    boolean booleanExtra = intent.getBooleanExtra("loading", false);
                    String stringExtra = intent.getStringExtra("structure");
                    if (CommonUtils.INSTANCE.getDEBUG()) {
                        Log.i("ControlsControllerImpl", " MiHome Change to " + stringExtra + " , loading: " + booleanExtra);
                    }
                    this.this$0.setLoadingData(booleanExtra);
                    if (booleanExtra) {
                        this.this$0.uiController.show(1);
                    } else {
                        if (kotlin.jvm.internal.n.c(this.this$0.getLastStructure(), stringExtra)) {
                            return;
                        }
                        this.this$0.handleMiHomeChange(stringExtra);
                    }
                }
            }
        };
        this.restoreFinishedReceiver = new ControlsControllerImpl$restoreFinishedReceiver$1(this);
        this.settingObserver = new ContentObserver() { // from class: miui.systemui.devicecontrols.controller.ControlsControllerImpl$settingObserver$1
            {
                super(null);
            }

            public void onChange(boolean z2, Collection<? extends Uri> uris, int i2, int i3) {
                kotlin.jvm.internal.n.g(uris, "uris");
                if (this.this$0.userChanging || i3 != this.this$0.getCurrentUserId()) {
                    return;
                }
                this.this$0.resetFavorites();
            }
        };
        this.listingCallback = new ControlsControllerImpl$listingCallback$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ControlsFavoritePersistenceWrapper _init_$lambda$0(ControlsControllerImpl this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        return new ControlsFavoritePersistenceWrapper(this$0.userStructure.getFile(), this$0.bgExecutor, new BackupManager(this$0.userStructure.getUserContext()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addFavorite$lambda$14(ComponentName componentName, CharSequence structureName, ControlInfo controlInfo, ControlsControllerImpl this$0) {
        kotlin.jvm.internal.n.g(componentName, "$componentName");
        kotlin.jvm.internal.n.g(structureName, "$structureName");
        kotlin.jvm.internal.n.g(controlInfo, "$controlInfo");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Favorites favorites = Favorites.INSTANCE;
        if (favorites.addFavorite(componentName, structureName, controlInfo)) {
            this$0.persistenceWrapper.storeFavorites(favorites.getAllStructures());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addSeedingFavoritesCallback$lambda$6(ControlsControllerImpl this$0, Consumer callback) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(callback, "$callback");
        if (this$0.seedingInProgress) {
            this$0.seedingCallbacks.add(callback);
        } else {
            callback.accept(Boolean.FALSE);
        }
    }

    private final boolean confirmAvailability() {
        if (!this.userChanging) {
            return true;
        }
        Log.w(TAG, "Controls not available while user is changing");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ControlStatus createRemovedStatus(ComponentName componentName, ControlInfo controlInfo, CharSequence charSequence, boolean z2) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(componentName.getPackageName());
        Control controlBuild = new Control.StatelessBuilder(controlInfo.getControlId(), PendingIntent.getActivity(this.context, componentName.hashCode(), intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL)).setTitle(controlInfo.getControlTitle()).setSubtitle(controlInfo.getControlSubtitle()).setStructure(charSequence).setDeviceType(controlInfo.getDeviceType()).build();
        kotlin.jvm.internal.n.d(controlBuild);
        return new ControlStatus(controlBuild, componentName, true, z2);
    }

    public static /* synthetic */ ControlStatus createRemovedStatus$default(ControlsControllerImpl controlsControllerImpl, ComponentName componentName, ControlInfo controlInfo, CharSequence charSequence, boolean z2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z2 = true;
        }
        return controlsControllerImpl.createRemovedStatus(componentName, controlInfo, charSequence, z2);
    }

    private final void endSeedingCall(boolean z2) {
        this.seedingInProgress = false;
        Log.d(TAG, "seeding end!");
        Iterator<T> it = this.seedingCallbacks.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(Boolean.valueOf(z2));
        }
        this.seedingCallbacks.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<String> findRemoved(Set<String> set, List<Control> list) {
        ArrayList arrayList = new ArrayList(I0.n.o(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Control) it.next()).getControlId());
        }
        return L.e(set, arrayList);
    }

    @VisibleForTesting
    public static /* synthetic */ void getAuxiliaryPersistenceWrapper$miui_devicecontrols_release$annotations() {
    }

    private final ContentResolver getContentResolver() {
        ContentResolver contentResolver = this.context.getContentResolver();
        kotlin.jvm.internal.n.f(contentResolver, "getContentResolver(...)");
        return contentResolver;
    }

    @VisibleForTesting
    public static /* synthetic */ void getRestoreFinishedReceiver$miui_devicecontrols_release$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void getSettingObserver$miui_devicecontrols_release$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleMiHomeChange(String str) {
        this.lastStructure = str;
        if (str != null) {
            final ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString("com.xiaomi.smarthome/com.xiaomi.smarthome.controls.MiControlsProviderService");
            kotlin.jvm.internal.n.d(componentNameUnflattenFromString);
            if (TextUtils.isEmpty(str)) {
                this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.i
                    @Override // java.lang.Runnable
                    public final void run() {
                        ControlsControllerImpl.handleMiHomeChange$lambda$3$lambda$2(componentNameUnflattenFromString, this);
                    }
                });
            } else {
                this.uiController.switchAppOrStructure(str, componentNameUnflattenFromString);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleMiHomeChange$lambda$3$lambda$2(final ComponentName componentName, final ControlsControllerImpl this$0) {
        kotlin.jvm.internal.n.g(componentName, "$componentName");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Favorites favorites = Favorites.INSTANCE;
        favorites.removeStructures(componentName);
        this$0.persistenceWrapper.storeFavorites(favorites.getAllStructures());
        this$0.mainExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.p
            @Override // java.lang.Runnable
            public final void run() {
                ControlsControllerImpl.handleMiHomeChange$lambda$3$lambda$2$lambda$1(this.f5573a, componentName);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleMiHomeChange$lambda$3$lambda$2$lambda$1(ControlsControllerImpl this$0, ComponentName componentName) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(componentName, "$componentName");
        this$0.uiController.switchAppOrStructure("", componentName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadForComponent$lambda$5(ControlsControllerImpl this$0, ComponentName componentName, Consumer dataCallback, Consumer cancelWrapper) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(componentName, "$componentName");
        kotlin.jvm.internal.n.g(dataCallback, "$dataCallback");
        kotlin.jvm.internal.n.g(cancelWrapper, "$cancelWrapper");
        this$0.loadForComponent(componentName, dataCallback, cancelWrapper);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void refreshStatus$lambda$18(ComponentName componentName, Control control, ControlsControllerImpl this$0) {
        kotlin.jvm.internal.n.g(componentName, "$componentName");
        kotlin.jvm.internal.n.g(control, "$control");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Favorites favorites = Favorites.INSTANCE;
        if (favorites.updateControls(componentName, AbstractC0184l.d(control))) {
            this$0.persistenceWrapper.storeFavorites(favorites.getAllStructures());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void removeFavorite$lambda$15(ComponentName componentName, CharSequence structureName, ControlInfo controlInfo, ControlsControllerImpl this$0) {
        kotlin.jvm.internal.n.g(componentName, "$componentName");
        kotlin.jvm.internal.n.g(structureName, "$structureName");
        kotlin.jvm.internal.n.g(controlInfo, "$controlInfo");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Favorites favorites = Favorites.INSTANCE;
        if (favorites.removeFavorite(componentName, structureName, controlInfo)) {
            this$0.persistenceWrapper.storeFavorites(favorites.getAllStructures());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void removeStructures$lambda$4(ComponentName componentName, ControlsControllerImpl this$0) {
        kotlin.jvm.internal.n.g(componentName, "$componentName");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Favorites favorites = Favorites.INSTANCE;
        favorites.removeStructures(componentName);
        this$0.persistenceWrapper.storeFavorites(favorites.getAllStructures());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void replaceFavoritesForComponent$lambda$17(List structures, Function0 runnable, ControlsControllerImpl this$0) {
        kotlin.jvm.internal.n.g(structures, "$structures");
        kotlin.jvm.internal.n.g(runnable, "$runnable");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Favorites favorites = Favorites.INSTANCE;
        favorites.replaceStructures(structures);
        runnable.invoke();
        this$0.persistenceWrapper.storeFavorites(favorites.getAllStructures());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void replaceFavoritesForStructure$lambda$16(StructureInfo structureInfo, Function0 runnable, ControlsControllerImpl this$0) {
        kotlin.jvm.internal.n.g(structureInfo, "$structureInfo");
        kotlin.jvm.internal.n.g(runnable, "$runnable");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Favorites favorites = Favorites.INSTANCE;
        favorites.replaceControls(structureInfo);
        runnable.invoke();
        this$0.persistenceWrapper.storeFavorites(favorites.getAllStructures());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetFavorites() {
        Favorites favorites = Favorites.INSTANCE;
        favorites.clear();
        favorites.load(this.persistenceWrapper.readFavorites());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void seedFavoritesForComponents$lambda$7(ControlsControllerImpl this$0, List componentNames, Consumer callback) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(componentNames, "$componentNames");
        kotlin.jvm.internal.n.g(callback, "$callback");
        this$0.seedFavoritesForComponents(componentNames, callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setValuesForUser(UserHandle userHandle) {
        Log.d(TAG, "Changing to user: " + userHandle);
        this.currentUser = userHandle;
        UserStructure userStructure = new UserStructure(this.sysUIContext, this.context, userHandle);
        this.userStructure = userStructure;
        this.persistenceWrapper.changeFileAndBackupManager(userStructure.getFile(), new BackupManager(this.userStructure.getUserContext()));
        this.auxiliaryPersistenceWrapper.changeFile(this.userStructure.getAuxiliaryFile());
        resetFavorites();
        this.bindingController.changeUser(userHandle);
        this.listingController.changeUser(userHandle);
        this.userChanging = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startSeeding(List<ComponentName> list, Consumer<SeedResponse> consumer, boolean z2) {
        if (list.isEmpty()) {
            endSeedingCall(!z2);
            return;
        }
        ComponentName componentName = list.get(0);
        Log.d(TAG, "Beginning request to seed favorites for: " + componentName);
        this.bindingController.bindAndLoadSuggested(componentName, new AnonymousClass1(consumer, componentName, I0.u.G(list, 1), z2));
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void action(ComponentName componentName, ControlInfo controlInfo, ControlAction action) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(controlInfo, "controlInfo");
        kotlin.jvm.internal.n.g(action, "action");
        if (confirmAvailability()) {
            this.bindingController.action(componentName, controlInfo, action);
        }
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void addFavorite(final ComponentName componentName, final CharSequence structureName, final ControlInfo controlInfo) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(structureName, "structureName");
        kotlin.jvm.internal.n.g(controlInfo, "controlInfo");
        if (confirmAvailability()) {
            this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.g
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsControllerImpl.addFavorite$lambda$14(componentName, structureName, controlInfo, this);
                }
            });
        }
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public boolean addSeedingFavoritesCallback(final Consumer<Boolean> callback) {
        kotlin.jvm.internal.n.g(callback, "callback");
        if (!this.seedingInProgress) {
            return false;
        }
        this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.f
            @Override // java.lang.Runnable
            public final void run() {
                ControlsControllerImpl.addSeedingFavoritesCallback$lambda$6(this.f5546a, callback);
            }
        });
        return true;
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public int countFavoritesForComponent(ComponentName componentName) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        return Favorites.INSTANCE.getControlsForComponent(componentName).size();
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void create() {
        resetFavorites();
        BroadcastDispatcher broadcastDispatcher = this.broadcastDispatcher;
        ControlsControllerImpl$userSwitchReceiver$1 controlsControllerImpl$userSwitchReceiver$1 = this.userSwitchReceiver;
        IntentFilter intentFilter = new IntentFilter("android.intent.action.USER_SWITCHED");
        DelayableExecutor delayableExecutor = this.bgExecutor;
        UserHandle ALL = UserHandle.ALL;
        kotlin.jvm.internal.n.f(ALL, "ALL");
        broadcastDispatcher.registerReceiver(controlsControllerImpl$userSwitchReceiver$1, intentFilter, delayableExecutor, ALL);
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.homeChangeReceiver, new IntentFilter(MIHOME_STRUCTURE_CHANGED_ACTION), null, null, 12, null);
        this.listingController.addCallback(this.listingCallback);
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void destroy() {
        this.bindingController.unbind();
        this.broadcastDispatcher.unregisterReceiver(this.userSwitchReceiver);
        this.broadcastDispatcher.unregisterReceiver(this.homeChangeReceiver);
        this.listingController.removeCallback(this.listingCallback);
        this.listingController.release();
        this.seedingInProgress = false;
        setLoadingData(false);
    }

    public final AuxiliaryPersistenceWrapper getAuxiliaryPersistenceWrapper$miui_devicecontrols_release() {
        return this.auxiliaryPersistenceWrapper;
    }

    @Override // miui.systemui.devicecontrols.util.UserAwareController
    public int getCurrentUserId() {
        return this.currentUser.getIdentifier();
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public List<StructureInfo> getFavorites() {
        return Favorites.INSTANCE.getAllStructures();
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public List<StructureInfo> getFavoritesForComponent(ComponentName componentName) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        return Favorites.INSTANCE.getStructuresForComponent(componentName);
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public List<ControlInfo> getFavoritesForStructure(ComponentName componentName, CharSequence structureName) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(structureName, "structureName");
        return Favorites.INSTANCE.getControlsForStructure(new StructureInfo(componentName, structureName, I0.m.h(), false, 8, null));
    }

    public final String getLastStructure() {
        return this.lastStructure;
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public boolean getLoadingData() {
        return this.loadingData;
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public StructureInfo getPreferredStructure() {
        return this.uiController.getPreferredStructure(getFavorites());
    }

    public final BroadcastReceiver getRestoreFinishedReceiver$miui_devicecontrols_release() {
        return this.restoreFinishedReceiver;
    }

    public final ContentObserver getSettingObserver$miui_devicecontrols_release() {
        return this.settingObserver;
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void loadForComponent(final ComponentName componentName, final Consumer<ControlsController.LoadData> dataCallback, final Consumer<Runnable> cancelWrapper) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(dataCallback, "dataCallback");
        kotlin.jvm.internal.n.g(cancelWrapper, "cancelWrapper");
        if (!confirmAvailability()) {
            if (this.userChanging) {
                this.bgExecutor.executeDelayed(new Runnable() { // from class: miui.systemui.devicecontrols.controller.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        ControlsControllerImpl.loadForComponent$lambda$5(this.f5542a, componentName, dataCallback, cancelWrapper);
                    }
                }, 500L, TimeUnit.MILLISECONDS);
            }
            dataCallback.accept(ControlsControllerKt.createLoadDataObject(I0.m.h(), I0.m.h(), I0.m.h(), true));
        }
        cancelWrapper.accept(this.bindingController.bindAndLoad(componentName, new AnonymousClass2(componentName, dataCallback)));
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void onActionResponse(ComponentName componentName, String controlId, int i2) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(controlId, "controlId");
        if (confirmAvailability()) {
            this.uiController.onActionResponse(componentName, controlId, i2);
        }
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void refreshStatus(final ComponentName componentName, final Control control) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(control, "control");
        if (!confirmAvailability()) {
            Log.d(TAG, "Controls not available");
            return;
        }
        if (control.getStatus() == 1) {
            this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.j
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsControllerImpl.refreshStatus$lambda$18(componentName, control, this);
                }
            });
        }
        this.uiController.onRefreshState(componentName, AbstractC0184l.d(control));
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void removeFavorite(final ComponentName componentName, final CharSequence structureName, final ControlInfo controlInfo) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(structureName, "structureName");
        kotlin.jvm.internal.n.g(controlInfo, "controlInfo");
        if (confirmAvailability()) {
            this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.o
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsControllerImpl.removeFavorite$lambda$15(componentName, structureName, controlInfo, this);
                }
            });
        }
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void removeStructures(final ComponentName componentName) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.h
            @Override // java.lang.Runnable
            public final void run() {
                ControlsControllerImpl.removeStructures$lambda$4(componentName, this);
            }
        });
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void replaceFavoritesForComponent(final List<StructureInfo> structures, final Function0 runnable) {
        kotlin.jvm.internal.n.g(structures, "structures");
        kotlin.jvm.internal.n.g(runnable, "runnable");
        if (confirmAvailability()) {
            this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.n
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsControllerImpl.replaceFavoritesForComponent$lambda$17(structures, runnable, this);
                }
            });
        }
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void replaceFavoritesForStructure(final StructureInfo structureInfo, final Function0 runnable) {
        kotlin.jvm.internal.n.g(structureInfo, "structureInfo");
        kotlin.jvm.internal.n.g(runnable, "runnable");
        if (confirmAvailability()) {
            this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.m
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsControllerImpl.replaceFavoritesForStructure$lambda$16(structureInfo, runnable, this);
                }
            });
        }
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void saveFavoritesForComponents(List<Control> controls, ComponentName componentName) {
        kotlin.jvm.internal.n.g(controls, "controls");
        kotlin.jvm.internal.n.g(componentName, "componentName");
        ArrayMap arrayMap = new ArrayMap();
        int i2 = 0;
        for (Control control : controls) {
            CharSequence structure = control.getStructure();
            if (structure == null) {
                structure = "";
            } else {
                kotlin.jvm.internal.n.d(structure);
            }
            List arrayList = (List) arrayMap.get(structure);
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            kotlin.jvm.internal.n.d(arrayList);
            ControlsUtils controlsUtils = ControlsUtils.INSTANCE;
            String controlId = control.getControlId();
            kotlin.jvm.internal.n.f(controlId, "getControlId(...)");
            if (controlsUtils.checkSenseType(controlId)) {
                if (i2 < 4) {
                    i2++;
                }
            }
            if (arrayList.size() < i2 + 50) {
                String controlId2 = control.getControlId();
                kotlin.jvm.internal.n.f(controlId2, "getControlId(...)");
                CharSequence title = control.getTitle();
                kotlin.jvm.internal.n.f(title, "getTitle(...)");
                CharSequence subtitle = control.getSubtitle();
                kotlin.jvm.internal.n.f(subtitle, "getSubtitle(...)");
                arrayList.add(new ControlInfo(controlId2, title, subtitle, control.getZone(), control.getDeviceType()));
                arrayMap.put(structure, arrayList);
            }
        }
        for (Map.Entry entry : arrayMap.entrySet()) {
            CharSequence charSequence = (CharSequence) entry.getKey();
            List list = (List) entry.getValue();
            Favorites favorites = Favorites.INSTANCE;
            kotlin.jvm.internal.n.d(charSequence);
            kotlin.jvm.internal.n.d(list);
            favorites.replaceControls(new StructureInfo(componentName, charSequence, list, false, 8, null));
        }
        this.persistenceWrapper.storeFavorites(Favorites.INSTANCE.getAllStructures());
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void seedFavoritesForComponents(final List<ComponentName> componentNames, final Consumer<SeedResponse> callback) {
        kotlin.jvm.internal.n.g(componentNames, "componentNames");
        kotlin.jvm.internal.n.g(callback, "callback");
        if (componentNames.isEmpty()) {
            return;
        }
        if (confirmAvailability()) {
            this.seedingInProgress = true;
            startSeeding(componentNames, callback, false);
        } else {
            if (this.userChanging) {
                this.bgExecutor.executeDelayed(new Runnable() { // from class: miui.systemui.devicecontrols.controller.k
                    @Override // java.lang.Runnable
                    public final void run() {
                        ControlsControllerImpl.seedFavoritesForComponents$lambda$7(this.f5559a, componentNames, callback);
                    }
                }, 500L, TimeUnit.MILLISECONDS);
                return;
            }
            Iterator<T> it = componentNames.iterator();
            while (it.hasNext()) {
                String packageName = ((ComponentName) it.next()).getPackageName();
                kotlin.jvm.internal.n.f(packageName, "getPackageName(...)");
                callback.accept(new SeedResponse(packageName, false));
            }
        }
    }

    public final void setAuxiliaryPersistenceWrapper$miui_devicecontrols_release(AuxiliaryPersistenceWrapper auxiliaryPersistenceWrapper) {
        kotlin.jvm.internal.n.g(auxiliaryPersistenceWrapper, "<set-?>");
        this.auxiliaryPersistenceWrapper = auxiliaryPersistenceWrapper;
    }

    public final void setLastStructure(String str) {
        this.lastStructure = str;
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void setLoadingData(boolean z2) {
        this.loadingData = z2;
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void subscribeToFavorites(StructureInfo structureInfo) {
        kotlin.jvm.internal.n.g(structureInfo, "structureInfo");
        if (confirmAvailability()) {
            this.bindingController.subscribe(structureInfo);
        }
    }

    @Override // miui.systemui.devicecontrols.controller.ControlsController
    public void unsubscribe() {
        if (confirmAvailability()) {
            this.bindingController.unsubscribe();
        }
    }
}
