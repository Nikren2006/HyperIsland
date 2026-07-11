package miui.systemui.devicecontrols.ui;

import android.content.ComponentName;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import miui.systemui.devicecontrols.ControlsServiceInfo;
import miui.systemui.devicecontrols.management.ControlsListingController;

/* JADX INFO: loaded from: classes3.dex */
public final class MiuiControlsUiControllerImpl$listingCallback$1 implements ControlsListingController.ControlsListingCallback {
    final /* synthetic */ MiuiControlsUiControllerImpl this$0;

    public MiuiControlsUiControllerImpl$listingCallback$1(MiuiControlsUiControllerImpl miuiControlsUiControllerImpl) {
        this.this$0 = miuiControlsUiControllerImpl;
    }

    @Override // miui.systemui.devicecontrols.management.ControlsListingController.ControlsListingCallback
    public void onServicesUpdated(List<? extends ControlsServiceInfo> serviceInfos) {
        Object next;
        kotlin.jvm.internal.n.g(serviceInfos, "serviceInfos");
        MiuiControlsUiControllerImpl miuiControlsUiControllerImpl = this.this$0;
        List<ControlsServiceInfo> listG0 = I0.u.g0(serviceInfos, miuiControlsUiControllerImpl.appComparator);
        ArrayList arrayList = new ArrayList();
        for (ControlsServiceInfo controlsServiceInfo : listG0) {
            int i2 = controlsServiceInfo.getServiceInfo().applicationInfo.uid;
            CharSequence charSequenceLoadLabel = controlsServiceInfo.loadLabel();
            CharSequence charSequence = charSequenceLoadLabel == null ? "" : charSequenceLoadLabel;
            Drawable drawableLoadNormalIcon = controlsServiceInfo.loadNormalIcon();
            kotlin.jvm.internal.n.f(drawableLoadNormalIcon, "loadNormalIcon(...)");
            ComponentName componentName = controlsServiceInfo.componentName;
            kotlin.jvm.internal.n.f(componentName, "componentName");
            arrayList.add(new SelectionItem(charSequence, "", drawableLoadNormalIcon, componentName, i2, controlsServiceInfo.getPanelActivity()));
        }
        miuiControlsUiControllerImpl.selectionItems = arrayList;
        Set set = this.this$0.entryInfoCallbacks;
        MiuiControlsUiControllerImpl miuiControlsUiControllerImpl2 = this.this$0;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(miuiControlsUiControllerImpl2.buildDCEntryInfo());
        }
        MiuiControlsUiControllerImpl miuiControlsUiControllerImpl3 = this.this$0;
        miuiControlsUiControllerImpl3.loadStructure(new MiuiControlsUiControllerImpl$listingCallback$1$onServicesUpdated$3(miuiControlsUiControllerImpl3));
        Iterator<T> it2 = serviceInfos.iterator();
        while (true) {
            if (it2.hasNext()) {
                next = it2.next();
                if (kotlin.jvm.internal.n.c(((ControlsServiceInfo) next).componentName.getPackageName(), "com.xiaomi.smarthome")) {
                    break;
                }
            } else {
                next = null;
                break;
            }
        }
        if (((ControlsServiceInfo) next) == null) {
            MiuiControlsUiControllerImpl miuiControlsUiControllerImpl4 = this.this$0;
            if (!TextUtils.isEmpty(miuiControlsUiControllerImpl4.getCurrentMiHome())) {
                miuiControlsUiControllerImpl4.setCurrentMiHome("");
                miuiControlsUiControllerImpl4.getSharedPreferences().edit().remove("controls_mihome_structure").apply();
            }
            H0.s sVar = H0.s.f314a;
        }
    }
}
