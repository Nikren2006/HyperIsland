package miui.systemui.notification.focus.templateV3;

import G0.a;
import android.content.Context;

/* JADX INFO: renamed from: miui.systemui.notification.focus.templateV3.TemplateTinyBuilderV3_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes4.dex */
public final class C0694TemplateTinyBuilderV3_Factory {
    private final a contextProvider;
    private final a moduleViewHolderAdapterProvider;

    public C0694TemplateTinyBuilderV3_Factory(a aVar, a aVar2) {
        this.contextProvider = aVar;
        this.moduleViewHolderAdapterProvider = aVar2;
    }

    public static C0694TemplateTinyBuilderV3_Factory create(a aVar, a aVar2) {
        return new C0694TemplateTinyBuilderV3_Factory(aVar, aVar2);
    }

    public static TemplateTinyBuilderV3 newInstance(Context context, boolean z2, a aVar) {
        return new TemplateTinyBuilderV3(context, z2, aVar);
    }

    public TemplateTinyBuilderV3 get(boolean z2) {
        return newInstance((Context) this.contextProvider.get(), z2, this.moduleViewHolderAdapterProvider);
    }
}
