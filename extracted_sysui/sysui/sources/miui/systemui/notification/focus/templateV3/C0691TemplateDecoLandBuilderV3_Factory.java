package miui.systemui.notification.focus.templateV3;

import G0.a;
import android.content.Context;
import miui.systemui.notification.focus.model.Template;

/* JADX INFO: renamed from: miui.systemui.notification.focus.templateV3.TemplateDecoLandBuilderV3_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes4.dex */
public final class C0691TemplateDecoLandBuilderV3_Factory {
    private final a contextProvider;
    private final a moduleViewHolderAdapterProvider;

    public C0691TemplateDecoLandBuilderV3_Factory(a aVar, a aVar2) {
        this.contextProvider = aVar;
        this.moduleViewHolderAdapterProvider = aVar2;
    }

    public static C0691TemplateDecoLandBuilderV3_Factory create(a aVar, a aVar2) {
        return new C0691TemplateDecoLandBuilderV3_Factory(aVar, aVar2);
    }

    public static TemplateDecoLandBuilderV3 newInstance(Context context, Template template, boolean z2, a aVar) {
        return new TemplateDecoLandBuilderV3(context, template, z2, aVar);
    }

    public TemplateDecoLandBuilderV3 get(Template template, boolean z2) {
        return newInstance((Context) this.contextProvider.get(), template, z2, this.moduleViewHolderAdapterProvider);
    }
}
