package miui.systemui.notification.focus.templateV3;

import F0.f;
import G0.a;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.templateV3.TemplateDecoLandBuilderV3;

/* JADX INFO: loaded from: classes4.dex */
public final class TemplateDecoLandBuilderV3_Factory_Impl implements TemplateDecoLandBuilderV3.Factory {
    private final C0691TemplateDecoLandBuilderV3_Factory delegateFactory;

    public TemplateDecoLandBuilderV3_Factory_Impl(C0691TemplateDecoLandBuilderV3_Factory c0691TemplateDecoLandBuilderV3_Factory) {
        this.delegateFactory = c0691TemplateDecoLandBuilderV3_Factory;
    }

    @Override // miui.systemui.notification.focus.templateV3.TemplateDecoLandBuilderV3.Factory
    public TemplateDecoLandBuilderV3 create(Template template, boolean z2) {
        return this.delegateFactory.get(template, z2);
    }

    public static a create(C0691TemplateDecoLandBuilderV3_Factory c0691TemplateDecoLandBuilderV3_Factory) {
        return f.a(new TemplateDecoLandBuilderV3_Factory_Impl(c0691TemplateDecoLandBuilderV3_Factory));
    }
}
