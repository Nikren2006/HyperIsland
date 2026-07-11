package miui.systemui.notification.focus.templateV3;

import F0.f;
import G0.a;
import miui.systemui.notification.focus.templateV3.TemplateBuilderV3;

/* JADX INFO: loaded from: classes4.dex */
public final class TemplateBuilderV3_Factory_Impl implements TemplateBuilderV3.Factory {
    private final C0686TemplateBuilderV3_Factory delegateFactory;

    public TemplateBuilderV3_Factory_Impl(C0686TemplateBuilderV3_Factory c0686TemplateBuilderV3_Factory) {
        this.delegateFactory = c0686TemplateBuilderV3_Factory;
    }

    @Override // miui.systemui.notification.focus.templateV3.TemplateBuilderV3.Factory
    public TemplateBuilderV3 create(boolean z2) {
        return this.delegateFactory.get(z2);
    }

    public static a create(C0686TemplateBuilderV3_Factory c0686TemplateBuilderV3_Factory) {
        return f.a(new TemplateBuilderV3_Factory_Impl(c0686TemplateBuilderV3_Factory));
    }
}
