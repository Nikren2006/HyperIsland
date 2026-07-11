package miui.systemui.util;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public final class SystemUIResourcesHelperImpl_Factory implements F0.e {
    private final G0.a contextProvider;

    public SystemUIResourcesHelperImpl_Factory(G0.a aVar) {
        this.contextProvider = aVar;
    }

    public static SystemUIResourcesHelperImpl_Factory create(G0.a aVar) {
        return new SystemUIResourcesHelperImpl_Factory(aVar);
    }

    public static SystemUIResourcesHelperImpl newInstance(Context context) {
        return new SystemUIResourcesHelperImpl(context);
    }

    @Override // G0.a
    public SystemUIResourcesHelperImpl get() {
        return newInstance((Context) this.contextProvider.get());
    }
}
