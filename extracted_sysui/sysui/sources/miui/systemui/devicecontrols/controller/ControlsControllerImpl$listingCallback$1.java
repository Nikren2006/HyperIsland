package miui.systemui.devicecontrols.controller;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import miui.systemui.devicecontrols.ControlsServiceInfo;
import miui.systemui.devicecontrols.management.ControlsListingController;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsControllerImpl$listingCallback$1 implements ControlsListingController.ControlsListingCallback {
    final /* synthetic */ ControlsControllerImpl this$0;

    public ControlsControllerImpl$listingCallback$1(ControlsControllerImpl controlsControllerImpl) {
        this.this$0 = controlsControllerImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServicesUpdated$lambda$6(List serviceInfos, ControlsControllerImpl this$0) {
        kotlin.jvm.internal.n.g(serviceInfos, "$serviceInfos");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        ArrayList arrayList = new ArrayList(I0.n.o(serviceInfos, 10));
        Iterator it = serviceInfos.iterator();
        while (it.hasNext()) {
            arrayList.add(((ControlsServiceInfo) it.next()).componentName);
        }
        Set setO0 = I0.u.o0(arrayList);
        if (setO0.isEmpty()) {
            return;
        }
        List<StructureInfo> allStructures = Favorites.INSTANCE.getAllStructures();
        ArrayList arrayList2 = new ArrayList(I0.n.o(allStructures, 10));
        Iterator<T> it2 = allStructures.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((StructureInfo) it2.next()).getComponentName());
        }
        Set setO02 = I0.u.o0(arrayList2);
        boolean z2 = false;
        SharedPreferences sharedPreferences = this$0.userStructure.getUserContext().getSharedPreferences(PrefDeviceControlsController.PREFS_CONTROLS_FILE, 0);
        Set<String> stringSet = sharedPreferences.getStringSet(PrefDeviceControlsController.PREFS_CONTROLS_SEEDING_COMPLETED, new LinkedHashSet());
        ArrayList arrayList3 = new ArrayList(I0.n.o(setO0, 10));
        Iterator it3 = setO0.iterator();
        while (it3.hasNext()) {
            arrayList3.add(((ComponentName) it3.next()).getPackageName());
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        kotlin.jvm.internal.n.d(stringSet);
        editorEdit.putStringSet(PrefDeviceControlsController.PREFS_CONTROLS_SEEDING_COMPLETED, I0.u.P(stringSet, arrayList3)).apply();
        for (ComponentName componentName : I0.u.h0(setO02, setO0)) {
            Log.d("ControlsControllerImpl", componentName.getPackageName() + " has changed!");
            Favorites favorites = Favorites.INSTANCE;
            kotlin.jvm.internal.n.d(componentName);
            favorites.removeStructures(componentName);
            this$0.bindingController.onComponentRemoved(componentName);
            z2 = true;
        }
        if (!this$0.getAuxiliaryPersistenceWrapper$miui_devicecontrols_release().getFavorites().isEmpty()) {
            for (ComponentName componentName2 : I0.u.h0(setO0, setO02)) {
                AuxiliaryPersistenceWrapper auxiliaryPersistenceWrapper$miui_devicecontrols_release = this$0.getAuxiliaryPersistenceWrapper$miui_devicecontrols_release();
                kotlin.jvm.internal.n.d(componentName2);
                List<StructureInfo> cachedFavoritesAndRemoveFor = auxiliaryPersistenceWrapper$miui_devicecontrols_release.getCachedFavoritesAndRemoveFor(componentName2);
                if (!cachedFavoritesAndRemoveFor.isEmpty()) {
                    Iterator<T> it4 = cachedFavoritesAndRemoveFor.iterator();
                    while (it4.hasNext()) {
                        Favorites.INSTANCE.replaceControls((StructureInfo) it4.next());
                    }
                    z2 = true;
                }
            }
            for (ComponentName componentName3 : I0.u.P(setO0, setO02)) {
                AuxiliaryPersistenceWrapper auxiliaryPersistenceWrapper$miui_devicecontrols_release2 = this$0.getAuxiliaryPersistenceWrapper$miui_devicecontrols_release();
                kotlin.jvm.internal.n.d(componentName3);
                auxiliaryPersistenceWrapper$miui_devicecontrols_release2.getCachedFavoritesAndRemoveFor(componentName3);
            }
        }
        if (z2) {
            Log.d("ControlsControllerImpl", "Detected change in available services, storing updated favorites");
            this$0.persistenceWrapper.storeFavorites(Favorites.INSTANCE.getAllStructures());
        }
    }

    @Override // miui.systemui.devicecontrols.management.ControlsListingController.ControlsListingCallback
    public void onServicesUpdated(final List<? extends ControlsServiceInfo> serviceInfos) {
        kotlin.jvm.internal.n.g(serviceInfos, "serviceInfos");
        DelayableExecutor delayableExecutor = this.this$0.bgExecutor;
        final ControlsControllerImpl controlsControllerImpl = this.this$0;
        delayableExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.q
            @Override // java.lang.Runnable
            public final void run() {
                ControlsControllerImpl$listingCallback$1.onServicesUpdated$lambda$6(serviceInfos, controlsControllerImpl);
            }
        });
    }
}
