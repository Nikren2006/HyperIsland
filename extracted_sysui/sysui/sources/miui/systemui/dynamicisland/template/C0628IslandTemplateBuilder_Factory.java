package miui.systemui.dynamicisland.template;

import G0.a;
import android.content.Context;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandModuleViewHolderAdapter;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.template.IslandTemplateBuilder_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0628IslandTemplateBuilder_Factory {
    private final a contextProvider;
    private final a islandAdapterProvider;

    public C0628IslandTemplateBuilder_Factory(a aVar, a aVar2) {
        this.contextProvider = aVar;
        this.islandAdapterProvider = aVar2;
    }

    public static C0628IslandTemplateBuilder_Factory create(a aVar, a aVar2) {
        return new C0628IslandTemplateBuilder_Factory(aVar, aVar2);
    }

    public static IslandTemplateBuilder newInstance(Context context, ViewGroup viewGroup, boolean z2, boolean z3, Function2 function2, IslandModuleViewHolderAdapter islandModuleViewHolderAdapter) {
        return new IslandTemplateBuilder(context, viewGroup, z2, z3, function2, islandModuleViewHolderAdapter);
    }

    public IslandTemplateBuilder get(ViewGroup viewGroup, boolean z2, boolean z3, Function2 function2) {
        return newInstance((Context) this.contextProvider.get(), viewGroup, z2, z3, function2, (IslandModuleViewHolderAdapter) this.islandAdapterProvider.get());
    }
}
