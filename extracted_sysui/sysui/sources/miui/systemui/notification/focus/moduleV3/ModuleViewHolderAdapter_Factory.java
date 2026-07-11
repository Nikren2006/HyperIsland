package miui.systemui.notification.focus.moduleV3;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleViewHolderAdapter_Factory implements F0.e {
    private final G0.a contextProvider;
    private final G0.a sysuiContextProvider;

    public ModuleViewHolderAdapter_Factory(G0.a aVar, G0.a aVar2) {
        this.contextProvider = aVar;
        this.sysuiContextProvider = aVar2;
    }

    public static ModuleViewHolderAdapter_Factory create(G0.a aVar, G0.a aVar2) {
        return new ModuleViewHolderAdapter_Factory(aVar, aVar2);
    }

    public static ModuleViewHolderAdapter newInstance(Context context, Context context2) {
        return new ModuleViewHolderAdapter(context, context2);
    }

    @Override // G0.a
    public ModuleViewHolderAdapter get() {
        return newInstance((Context) this.contextProvider.get(), (Context) this.sysuiContextProvider.get());
    }
}
