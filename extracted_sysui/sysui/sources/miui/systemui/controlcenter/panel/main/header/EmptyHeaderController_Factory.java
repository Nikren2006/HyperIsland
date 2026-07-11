package miui.systemui.controlcenter.panel.main.header;

import F0.d;
import F0.e;
import android.content.Context;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.shade.ShadeHeaderController;

/* JADX INFO: loaded from: classes.dex */
public final class EmptyHeaderController_Factory implements e {
    private final G0.a brightnessSliderControllerProvider;
    private final G0.a contextProvider;
    private final G0.a lifecycleProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a shadeHeaderControllerProvider;

    public EmptyHeaderController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        this.contextProvider = aVar;
        this.shadeHeaderControllerProvider = aVar2;
        this.lifecycleProvider = aVar3;
        this.brightnessSliderControllerProvider = aVar4;
        this.secondaryPanelRouterProvider = aVar5;
    }

    public static EmptyHeaderController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        return new EmptyHeaderController_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static EmptyHeaderController newInstance(Context context, ShadeHeaderController shadeHeaderController, Lifecycle lifecycle, E0.a aVar, E0.a aVar2) {
        return new EmptyHeaderController(context, shadeHeaderController, lifecycle, aVar, aVar2);
    }

    @Override // G0.a
    public EmptyHeaderController get() {
        return newInstance((Context) this.contextProvider.get(), (ShadeHeaderController) this.shadeHeaderControllerProvider.get(), (Lifecycle) this.lifecycleProvider.get(), d.a(this.brightnessSliderControllerProvider), d.a(this.secondaryPanelRouterProvider));
    }
}
