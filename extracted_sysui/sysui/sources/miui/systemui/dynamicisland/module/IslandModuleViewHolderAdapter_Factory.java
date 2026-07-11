package miui.systemui.dynamicisland.module;

import android.content.Context;
import miui.systemui.dynamicisland.module.IslandCombineImageViewHolder;
import miui.systemui.dynamicisland.module.IslandFixedWidthDigitViewHolder;
import miui.systemui.dynamicisland.module.IslandIconFixedWidthTextHolder;
import miui.systemui.dynamicisland.module.IslandIconViewHolder;
import miui.systemui.dynamicisland.module.IslandImageTextView2Holder;
import miui.systemui.dynamicisland.module.IslandImageTextView3Holder;
import miui.systemui.dynamicisland.module.IslandImageTextView4Holder;
import miui.systemui.dynamicisland.module.IslandImageTextViewHolder;
import miui.systemui.dynamicisland.module.IslandProgressTextViewHolder;
import miui.systemui.dynamicisland.module.IslandRightTextViewHolder;
import miui.systemui.dynamicisland.module.IslandSameWidthDigitViewHolder;
import miui.systemui.dynamicisland.module.IslandTextOverIconHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandModuleViewHolderAdapter_Factory implements F0.e {
    private final G0.a combineImageViewHolderFactoryProvider;
    private final G0.a contextProvider;
    private final G0.a fixedWidthDigitViewHolderFactoryProvider;
    private final G0.a iconFixedWidthTextHolderFactoryProvider;
    private final G0.a iconViewHolderFactoryProvider;
    private final G0.a imageTextView2HolderFactoryProvider;
    private final G0.a imageTextView3HolderFactoryProvider;
    private final G0.a imageTextView4HolderFactoryProvider;
    private final G0.a imageTextViewHolderFactoryProvider;
    private final G0.a progressTextViewHolderFactoryProvider;
    private final G0.a rightTextViewHolderFactoryProvider;
    private final G0.a sameWidthDigitViewHolderFactoryProvider;
    private final G0.a textOverIconHolderFactoryProvider;

    public IslandModuleViewHolderAdapter_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13) {
        this.contextProvider = aVar;
        this.iconViewHolderFactoryProvider = aVar2;
        this.imageTextViewHolderFactoryProvider = aVar3;
        this.imageTextView2HolderFactoryProvider = aVar4;
        this.imageTextView3HolderFactoryProvider = aVar5;
        this.imageTextView4HolderFactoryProvider = aVar6;
        this.progressTextViewHolderFactoryProvider = aVar7;
        this.rightTextViewHolderFactoryProvider = aVar8;
        this.fixedWidthDigitViewHolderFactoryProvider = aVar9;
        this.sameWidthDigitViewHolderFactoryProvider = aVar10;
        this.combineImageViewHolderFactoryProvider = aVar11;
        this.iconFixedWidthTextHolderFactoryProvider = aVar12;
        this.textOverIconHolderFactoryProvider = aVar13;
    }

    public static IslandModuleViewHolderAdapter_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13) {
        return new IslandModuleViewHolderAdapter_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13);
    }

    public static IslandModuleViewHolderAdapter newInstance(Context context, IslandIconViewHolder.Factory factory, IslandImageTextViewHolder.Factory factory2, IslandImageTextView2Holder.Factory factory3, IslandImageTextView3Holder.Factory factory4, IslandImageTextView4Holder.Factory factory5, IslandProgressTextViewHolder.Factory factory6, IslandRightTextViewHolder.Factory factory7, IslandFixedWidthDigitViewHolder.Factory factory8, IslandSameWidthDigitViewHolder.Factory factory9, IslandCombineImageViewHolder.Factory factory10, IslandIconFixedWidthTextHolder.Factory factory11, IslandTextOverIconHolder.Factory factory12) {
        return new IslandModuleViewHolderAdapter(context, factory, factory2, factory3, factory4, factory5, factory6, factory7, factory8, factory9, factory10, factory11, factory12);
    }

    @Override // G0.a
    public IslandModuleViewHolderAdapter get() {
        return newInstance((Context) this.contextProvider.get(), (IslandIconViewHolder.Factory) this.iconViewHolderFactoryProvider.get(), (IslandImageTextViewHolder.Factory) this.imageTextViewHolderFactoryProvider.get(), (IslandImageTextView2Holder.Factory) this.imageTextView2HolderFactoryProvider.get(), (IslandImageTextView3Holder.Factory) this.imageTextView3HolderFactoryProvider.get(), (IslandImageTextView4Holder.Factory) this.imageTextView4HolderFactoryProvider.get(), (IslandProgressTextViewHolder.Factory) this.progressTextViewHolderFactoryProvider.get(), (IslandRightTextViewHolder.Factory) this.rightTextViewHolderFactoryProvider.get(), (IslandFixedWidthDigitViewHolder.Factory) this.fixedWidthDigitViewHolderFactoryProvider.get(), (IslandSameWidthDigitViewHolder.Factory) this.sameWidthDigitViewHolderFactoryProvider.get(), (IslandCombineImageViewHolder.Factory) this.combineImageViewHolderFactoryProvider.get(), (IslandIconFixedWidthTextHolder.Factory) this.iconFixedWidthTextHolderFactoryProvider.get(), (IslandTextOverIconHolder.Factory) this.textOverIconHolderFactoryProvider.get());
    }
}
