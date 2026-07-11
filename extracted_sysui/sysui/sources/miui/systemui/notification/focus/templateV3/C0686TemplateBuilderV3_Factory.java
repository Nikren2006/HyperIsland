package miui.systemui.notification.focus.templateV3;

import G0.a;
import android.content.Context;

/* JADX INFO: renamed from: miui.systemui.notification.focus.templateV3.TemplateBuilderV3_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes4.dex */
public final class C0686TemplateBuilderV3_Factory {
    private final a contextProvider;
    private final a moduleViewHolderAdapterProvider;

    public C0686TemplateBuilderV3_Factory(a aVar, a aVar2) {
        this.contextProvider = aVar;
        this.moduleViewHolderAdapterProvider = aVar2;
    }

    public static C0686TemplateBuilderV3_Factory create(a aVar, a aVar2) {
        return new C0686TemplateBuilderV3_Factory(aVar, aVar2);
    }

    public static TemplateBuilderV3 newInstance(Context context, boolean z2, a aVar) {
        return new TemplateBuilderV3(context, z2, aVar);
    }

    public TemplateBuilderV3 get(boolean z2) {
        return newInstance((Context) this.contextProvider.get(), z2, this.moduleViewHolderAdapterProvider);
    }
}
