package miui.systemui.devicecontrols.controller;

import I0.K;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.android.systemui.settings.UserContextProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.devicecontrols.ControlStatus;
import miui.systemui.devicecontrols.ControlsServiceInfo;
import miui.systemui.devicecontrols.R;
import miui.systemui.devicecontrols.controller.ControlsController;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;
import miui.systemui.devicecontrols.management.ControlsListingController;
import miui.systemui.util.concurrency.DelayableExecutor;
import miui.systemui.util.settings.SecureSettings;

/* JADX INFO: loaded from: classes3.dex */
@DeviceControlsScope
public final class PrefDeviceControlsController {
    public static final Companion Companion = new Companion(null);
    public static final String PREFS_CONTROLS_FILE = "controls_prefs";
    public static final String PREFS_CONTROLS_SEEDING_COMPLETED = "SeedingCompleted";
    private static final int SEEDING_MAX = 2;
    private static final String TAG = "PreferDeviceControlsController";
    private final DelayableExecutor bgExecutor;
    private Runnable cancelLoadRunnable;
    private final Context context;
    private final ControlsController controlsController;
    private final ControlsListingController controlsListingController;
    private boolean listening;
    private final PrefDeviceControlsController$listingCallback$1 listingCallback;
    private final SecureSettings secureSettings;
    private final Set<ComponentName> seededServices;
    private final UserContextProvider userContextProvider;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [miui.systemui.devicecontrols.controller.PrefDeviceControlsController$listingCallback$1] */
    public PrefDeviceControlsController(Context context, ControlsController controlsController, ControlsListingController controlsListingController, UserContextProvider userContextProvider, SecureSettings secureSettings, @Background DelayableExecutor bgExecutor) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(controlsController, "controlsController");
        kotlin.jvm.internal.n.g(controlsListingController, "controlsListingController");
        kotlin.jvm.internal.n.g(userContextProvider, "userContextProvider");
        kotlin.jvm.internal.n.g(secureSettings, "secureSettings");
        kotlin.jvm.internal.n.g(bgExecutor, "bgExecutor");
        this.context = context;
        this.controlsController = controlsController;
        this.controlsListingController = controlsListingController;
        this.userContextProvider = userContextProvider;
        this.secureSettings = secureSettings;
        this.bgExecutor = bgExecutor;
        this.seededServices = new LinkedHashSet();
        this.listingCallback = new ControlsListingController.ControlsListingCallback() { // from class: miui.systemui.devicecontrols.controller.PrefDeviceControlsController$listingCallback$1
            @Override // miui.systemui.devicecontrols.management.ControlsListingController.ControlsListingCallback
            public void onServicesUpdated(List<? extends ControlsServiceInfo> serviceInfos) {
                kotlin.jvm.internal.n.g(serviceInfos, "serviceInfos");
                if (!serviceInfos.isEmpty()) {
                    this.this$0.seedFavorites(serviceInfos);
                }
                ArrayList arrayList = new ArrayList(I0.n.o(serviceInfos, 10));
                Iterator<T> it = serviceInfos.iterator();
                while (it.hasNext()) {
                    arrayList.add(((ControlsServiceInfo) it.next()).componentName);
                }
                Set setO0 = I0.u.o0(arrayList);
                List<StructureInfo> favorites = this.this$0.controlsController.getFavorites();
                ArrayList arrayList2 = new ArrayList(I0.n.o(favorites, 10));
                Iterator<T> it2 = favorites.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((StructureInfo) it2.next()).getComponentName());
                }
                Set<ComponentName> setH0 = I0.u.h0(I0.u.o0(arrayList2), setO0);
                PrefDeviceControlsController prefDeviceControlsController = this.this$0;
                for (ComponentName componentName : setH0) {
                    Log.d("PreferDeviceControlsController", componentName.getPackageName() + " has uninstall!");
                    ControlsController controlsController2 = prefDeviceControlsController.controlsController;
                    kotlin.jvm.internal.n.d(componentName);
                    controlsController2.removeStructures(componentName);
                }
            }
        };
    }

    private final void addPackageToSeededSet(SharedPreferences sharedPreferences, String str) {
        Set<String> stringSet = sharedPreferences.getStringSet(PREFS_CONTROLS_SEEDING_COMPLETED, K.b());
        kotlin.jvm.internal.n.d(stringSet);
        Set<String> setN0 = I0.u.n0(stringSet);
        setN0.add(str);
        sharedPreferences.edit().putStringSet(PREFS_CONTROLS_SEEDING_COMPLETED, setN0).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void seedFavorites$lambda$7(final PrefDeviceControlsController this$0, final SharedPreferences sharedPreferences, SeedResponse response) {
        final ComponentName componentNameUnflattenFromString;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(response, "response");
        Log.d(TAG, "Controls seeded: " + response);
        if (response.getAccepted()) {
            kotlin.jvm.internal.n.d(sharedPreferences);
            this$0.addPackageToSeededSet(sharedPreferences, response.getPackageName());
        } else {
            if (!kotlin.jvm.internal.n.c("com.xiaomi.smarthome", response.getPackageName()) || (componentNameUnflattenFromString = ComponentName.unflattenFromString("com.xiaomi.smarthome/com.xiaomi.smarthome.controls.MiControlsProviderService")) == null) {
                return;
            }
            this$0.controlsController.loadForComponent(componentNameUnflattenFromString, new Consumer() { // from class: miui.systemui.devicecontrols.controller.A
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PrefDeviceControlsController.seedFavorites$lambda$7$lambda$6$lambda$4(this.f5521a, componentNameUnflattenFromString, sharedPreferences, (ControlsController.LoadData) obj);
                }
            }, new Consumer() { // from class: miui.systemui.devicecontrols.controller.B
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PrefDeviceControlsController.seedFavorites$lambda$7$lambda$6$lambda$5(this.f5524a, (Runnable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void seedFavorites$lambda$7$lambda$6$lambda$4(final PrefDeviceControlsController this$0, final ComponentName componentName, final SharedPreferences sharedPreferences, final ControlsController.LoadData it) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(componentName, "$componentName");
        kotlin.jvm.internal.n.g(it, "it");
        if (it.getErrorOnLoad()) {
            Log.e(TAG, "loadForComponent when seeding ERROR!");
        } else {
            this$0.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.D
                @Override // java.lang.Runnable
                public final void run() {
                    PrefDeviceControlsController.seedFavorites$lambda$7$lambda$6$lambda$4$lambda$3(this.f5527a, it, componentName, sharedPreferences);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void seedFavorites$lambda$7$lambda$6$lambda$4$lambda$3(PrefDeviceControlsController this$0, ControlsController.LoadData it, ComponentName componentName, SharedPreferences sharedPreferences) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(it, "$it");
        kotlin.jvm.internal.n.g(componentName, "$componentName");
        ControlsController controlsController = this$0.controlsController;
        List<ControlStatus> allControls = it.getAllControls();
        ArrayList arrayList = new ArrayList(I0.n.o(allControls, 10));
        Iterator<T> it2 = allControls.iterator();
        while (it2.hasNext()) {
            arrayList.add(((ControlStatus) it2.next()).getControl());
        }
        controlsController.saveFavoritesForComponents(arrayList, componentName);
        if (it.getAllControls().isEmpty()) {
            return;
        }
        kotlin.jvm.internal.n.d(sharedPreferences);
        String packageName = componentName.getPackageName();
        kotlin.jvm.internal.n.f(packageName, "getPackageName(...)");
        this$0.addPackageToSeededSet(sharedPreferences, packageName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void seedFavorites$lambda$7$lambda$6$lambda$5(PrefDeviceControlsController this$0, Runnable runnable) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(runnable, "runnable");
        this$0.cancelLoadRunnable = runnable;
    }

    public final void destroy() {
        Runnable runnable = this.cancelLoadRunnable;
        if (runnable != null) {
            runnable.run();
        }
        this.controlsListingController.removeCallback(this.listingCallback);
        this.controlsListingController.release();
    }

    public final boolean getListening() {
        return this.listening;
    }

    public final void seedFavorites(List<? extends ControlsServiceInfo> serviceInfos) {
        kotlin.jvm.internal.n.g(serviceInfos, "serviceInfos");
        String[] stringArray = this.context.getResources().getStringArray(R.array.config_controlsPreferredPackages);
        final SharedPreferences sharedPreferences = this.userContextProvider.getUserContext().getSharedPreferences(PREFS_CONTROLS_FILE, 0);
        Set<String> stringSet = sharedPreferences.getStringSet(PREFS_CONTROLS_SEEDING_COMPLETED, K.b());
        ArrayList arrayList = new ArrayList();
        ArrayList<ControlsServiceInfo> arrayList2 = new ArrayList();
        for (Object obj : serviceInfos) {
            ControlsServiceInfo controlsServiceInfo = (ControlsServiceInfo) obj;
            boolean zContains = this.seededServices.contains(controlsServiceInfo.componentName);
            Set<ComponentName> set = this.seededServices;
            ComponentName componentName = controlsServiceInfo.componentName;
            kotlin.jvm.internal.n.f(componentName, "componentName");
            set.add(componentName);
            if (!zContains) {
                arrayList2.add(obj);
            }
        }
        for (int i2 = 0; i2 < Math.min(2, stringArray.length); i2++) {
            String str = stringArray[i2];
            Log.d(TAG, "seed packages: " + str);
            for (ControlsServiceInfo controlsServiceInfo2 : arrayList2) {
                if (str.equals(controlsServiceInfo2.componentName.getPackageName())) {
                    if (stringSet.contains(str)) {
                        ControlsController controlsController = this.controlsController;
                        ComponentName componentName2 = controlsServiceInfo2.componentName;
                        kotlin.jvm.internal.n.f(componentName2, "componentName");
                        if (controlsController.getFavoritesForComponent(componentName2).isEmpty()) {
                        }
                    }
                    ControlsController controlsController2 = this.controlsController;
                    ComponentName componentName3 = controlsServiceInfo2.componentName;
                    kotlin.jvm.internal.n.f(componentName3, "componentName");
                    if (controlsController2.countFavoritesForComponent(componentName3) > 0) {
                        kotlin.jvm.internal.n.d(sharedPreferences);
                        kotlin.jvm.internal.n.d(str);
                        addPackageToSeededSet(sharedPreferences, str);
                    } else {
                        ComponentName componentName4 = controlsServiceInfo2.componentName;
                        kotlin.jvm.internal.n.f(componentName4, "componentName");
                        arrayList.add(componentName4);
                    }
                }
            }
        }
        Log.d(TAG, "componentsToSeed size :" + arrayList.size());
        if (arrayList.isEmpty()) {
            return;
        }
        this.controlsController.seedFavoritesForComponents(arrayList, new Consumer() { // from class: miui.systemui.devicecontrols.controller.C
            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                PrefDeviceControlsController.seedFavorites$lambda$7(this.f5525a, sharedPreferences, (SeedResponse) obj2);
            }
        });
    }

    public final void setListening(boolean z2) {
        if (this.listening == z2) {
            return;
        }
        this.listening = z2;
        if (z2) {
            this.controlsListingController.addCallback(this.listingCallback);
        } else {
            this.controlsListingController.removeCallback(this.listingCallback);
        }
    }
}
