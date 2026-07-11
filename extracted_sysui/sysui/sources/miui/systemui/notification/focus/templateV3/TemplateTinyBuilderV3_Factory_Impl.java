package miui.systemui.notification.focus.templateV3;

import F0.f;
import G0.a;
import miui.systemui.notification.focus.templateV3.TemplateTinyBuilderV3;

/* JADX INFO: loaded from: classes4.dex */
public final class TemplateTinyBuilderV3_Factory_Impl implements TemplateTinyBuilderV3.Factory {
    private final C0694TemplateTinyBuilderV3_Factory delegateFactory;

    public TemplateTinyBuilderV3_Factory_Impl(C0694TemplateTinyBuilderV3_Factory c0694TemplateTinyBuilderV3_Factory) {
        this.delegateFactory = c0694TemplateTinyBuilderV3_Factory;
    }

    @Override // miui.systemui.notification.focus.templateV3.TemplateTinyBuilderV3.Factory
    public TemplateTinyBuilderV3 create(boolean z2) {
        return this.delegateFactory.get(z2);
    }

    public static a create(C0694TemplateTinyBuilderV3_Factory c0694TemplateTinyBuilderV3_Factory) {
        return f.a(new TemplateTinyBuilderV3_Factory_Impl(c0694TemplateTinyBuilderV3_Factory));
    }
}
