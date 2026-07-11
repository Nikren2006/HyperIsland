package miui.systemui.dynamicisland.template;

import F0.f;
import G0.a;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.template.IslandTemplateBuilder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandTemplateBuilder_Factory_Impl implements IslandTemplateBuilder.Factory {
    private final C0628IslandTemplateBuilder_Factory delegateFactory;

    public IslandTemplateBuilder_Factory_Impl(C0628IslandTemplateBuilder_Factory c0628IslandTemplateBuilder_Factory) {
        this.delegateFactory = c0628IslandTemplateBuilder_Factory;
    }

    @Override // miui.systemui.dynamicisland.template.IslandTemplateBuilder.Factory
    public IslandTemplateBuilder create(ViewGroup viewGroup, boolean z2, boolean z3, Function2 function2) {
        return this.delegateFactory.get(viewGroup, z2, z3, function2);
    }

    public static a create(C0628IslandTemplateBuilder_Factory c0628IslandTemplateBuilder_Factory) {
        return f.a(new IslandTemplateBuilder_Factory_Impl(c0628IslandTemplateBuilder_Factory));
    }
}
