package miui.systemui.controlcenter.panel.main.header;

import F0.d;
import F0.e;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.shade.ShadeHeaderController;
import miui.systemui.controlcenter.databinding.MainPanelCustomizeHeaderBinding;
import miui.systemui.controlcenter.qs.QSController;

/* JADX INFO: loaded from: classes.dex */
public final class CustomizeHeaderController_Factory implements e {
    private final G0.a bindingProvider;
    private final G0.a headerControllerProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a qsControllerProvider;
    private final G0.a shadeHeaderControllerProvider;

    public CustomizeHeaderController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.bindingProvider = aVar;
        this.lifecycleProvider = aVar2;
        this.shadeHeaderControllerProvider = aVar3;
        this.mainPanelControllerProvider = aVar4;
        this.qsControllerProvider = aVar5;
        this.headerControllerProvider = aVar6;
    }

    public static CustomizeHeaderController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new CustomizeHeaderController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static CustomizeHeaderController newInstance(MainPanelCustomizeHeaderBinding mainPanelCustomizeHeaderBinding, Lifecycle lifecycle, ShadeHeaderController shadeHeaderController, E0.a aVar, QSController qSController, E0.a aVar2) {
        return new CustomizeHeaderController(mainPanelCustomizeHeaderBinding, lifecycle, shadeHeaderController, aVar, qSController, aVar2);
    }

    @Override // G0.a
    public CustomizeHeaderController get() {
        return newInstance((MainPanelCustomizeHeaderBinding) this.bindingProvider.get(), (Lifecycle) this.lifecycleProvider.get(), (ShadeHeaderController) this.shadeHeaderControllerProvider.get(), d.a(this.mainPanelControllerProvider), (QSController) this.qsControllerProvider.get(), d.a(this.headerControllerProvider));
    }
}
