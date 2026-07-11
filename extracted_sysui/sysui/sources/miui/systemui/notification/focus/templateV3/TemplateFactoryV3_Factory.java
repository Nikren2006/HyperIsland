package miui.systemui.notification.focus.templateV3;

import F0.e;
import G0.a;
import miui.systemui.notification.focus.FocusNotifUtils;
import miui.systemui.notification.focus.templateV3.TemplateBuilderV3;
import miui.systemui.notification.focus.templateV3.TemplateDecoBuilderV3;
import miui.systemui.notification.focus.templateV3.TemplateDecoLandBuilderV3;
import miui.systemui.notification.focus.templateV3.TemplateTinyBuilderV3;

/* JADX INFO: loaded from: classes4.dex */
public final class TemplateFactoryV3_Factory implements e {
    private final a focusNotifUtilsProvider;
    private final a templateBuilderV3FactoryProvider;
    private final a templateDecoBuilderV3FactoryProvider;
    private final a templateDecoLandBuilderV3FactoryProvider;
    private final a templateTinyBuilderV3FactoryProvider;

    public TemplateFactoryV3_Factory(a aVar, a aVar2, a aVar3, a aVar4, a aVar5) {
        this.focusNotifUtilsProvider = aVar;
        this.templateBuilderV3FactoryProvider = aVar2;
        this.templateDecoBuilderV3FactoryProvider = aVar3;
        this.templateDecoLandBuilderV3FactoryProvider = aVar4;
        this.templateTinyBuilderV3FactoryProvider = aVar5;
    }

    public static TemplateFactoryV3_Factory create(a aVar, a aVar2, a aVar3, a aVar4, a aVar5) {
        return new TemplateFactoryV3_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static TemplateFactoryV3 newInstance(FocusNotifUtils focusNotifUtils, TemplateBuilderV3.Factory factory, TemplateDecoBuilderV3.Factory factory2, TemplateDecoLandBuilderV3.Factory factory3, TemplateTinyBuilderV3.Factory factory4) {
        return new TemplateFactoryV3(focusNotifUtils, factory, factory2, factory3, factory4);
    }

    @Override // G0.a
    public TemplateFactoryV3 get() {
        return newInstance((FocusNotifUtils) this.focusNotifUtilsProvider.get(), (TemplateBuilderV3.Factory) this.templateBuilderV3FactoryProvider.get(), (TemplateDecoBuilderV3.Factory) this.templateDecoBuilderV3FactoryProvider.get(), (TemplateDecoLandBuilderV3.Factory) this.templateDecoLandBuilderV3FactoryProvider.get(), (TemplateTinyBuilderV3.Factory) this.templateTinyBuilderV3FactoryProvider.get());
    }
}
