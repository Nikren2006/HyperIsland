package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandIconViewHolder;
import miui.systemui.dynamicisland.module.IslandRightTextViewHolder;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.module.IslandImageTextView2Holder_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0612IslandImageTextView2Holder_Factory {
    private final G0.a contextProvider;
    private final G0.a iconViewHolderFactoryProvider;
    private final G0.a rightTextViewHolderFactoryProvider;

    public C0612IslandImageTextView2Holder_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.contextProvider = aVar;
        this.iconViewHolderFactoryProvider = aVar2;
        this.rightTextViewHolderFactoryProvider = aVar3;
    }

    public static C0612IslandImageTextView2Holder_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new C0612IslandImageTextView2Holder_Factory(aVar, aVar2, aVar3);
    }

    public static IslandImageTextView2Holder newInstance(Context context, ViewGroup viewGroup, Function2 function2, IslandIconViewHolder.Factory factory, IslandRightTextViewHolder.Factory factory2) {
        return new IslandImageTextView2Holder(context, viewGroup, function2, factory, factory2);
    }

    public IslandImageTextView2Holder get(ViewGroup viewGroup, Function2 function2) {
        return newInstance((Context) this.contextProvider.get(), viewGroup, function2, (IslandIconViewHolder.Factory) this.iconViewHolderFactoryProvider.get(), (IslandRightTextViewHolder.Factory) this.rightTextViewHolderFactoryProvider.get());
    }
}
