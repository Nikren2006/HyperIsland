package miui.systemui.controlcenter.panel.main.header;

import F0.d;
import F0.e;
import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.shade.ShadeHeaderController;
import miui.systemui.controlcenter.databinding.MainPanelMsgHeaderBinding;

/* JADX INFO: loaded from: classes.dex */
public final class MessageHeaderController_Factory implements e {
    private final G0.a bindingProvider;
    private final G0.a expandControllerProvider;
    private final G0.a headerControllerProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a shadeHeaderControllerProvider;
    private final G0.a uiHandlerProvider;

    public MessageHeaderController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8) {
        this.bindingProvider = aVar;
        this.shadeHeaderControllerProvider = aVar2;
        this.uiHandlerProvider = aVar3;
        this.headerControllerProvider = aVar4;
        this.expandControllerProvider = aVar5;
        this.mainPanelControllerProvider = aVar6;
        this.lifecycleProvider = aVar7;
        this.secondaryPanelRouterProvider = aVar8;
    }

    public static MessageHeaderController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8) {
        return new MessageHeaderController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8);
    }

    public static MessageHeaderController newInstance(MainPanelMsgHeaderBinding mainPanelMsgHeaderBinding, ShadeHeaderController shadeHeaderController, Handler handler, E0.a aVar, E0.a aVar2, E0.a aVar3, Lifecycle lifecycle, E0.a aVar4) {
        return new MessageHeaderController(mainPanelMsgHeaderBinding, shadeHeaderController, handler, aVar, aVar2, aVar3, lifecycle, aVar4);
    }

    @Override // G0.a
    public MessageHeaderController get() {
        return newInstance((MainPanelMsgHeaderBinding) this.bindingProvider.get(), (ShadeHeaderController) this.shadeHeaderControllerProvider.get(), (Handler) this.uiHandlerProvider.get(), d.a(this.headerControllerProvider), d.a(this.expandControllerProvider), d.a(this.mainPanelControllerProvider), (Lifecycle) this.lifecycleProvider.get(), d.a(this.secondaryPanelRouterProvider));
    }
}
