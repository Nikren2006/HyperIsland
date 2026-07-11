package miui.systemui.notification.focus.templateV3;

import F0.f;
import G0.a;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.templateV3.TemplateDecoBuilderV3;

/* JADX INFO: loaded from: classes4.dex */
public final class TemplateDecoBuilderV3_Factory_Impl implements TemplateDecoBuilderV3.Factory {
    private final C0689TemplateDecoBuilderV3_Factory delegateFactory;

    public TemplateDecoBuilderV3_Factory_Impl(C0689TemplateDecoBuilderV3_Factory c0689TemplateDecoBuilderV3_Factory) {
        this.delegateFactory = c0689TemplateDecoBuilderV3_Factory;
    }

    @Override // miui.systemui.notification.focus.templateV3.TemplateDecoBuilderV3.Factory
    public TemplateDecoBuilderV3 create(Template template, boolean z2) {
        return this.delegateFactory.get(template, z2);
    }

    public static a create(C0689TemplateDecoBuilderV3_Factory c0689TemplateDecoBuilderV3_Factory) {
        return f.a(new TemplateDecoBuilderV3_Factory_Impl(c0689TemplateDecoBuilderV3_Factory));
    }
}
