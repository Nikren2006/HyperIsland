package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.model.CombinePicInfo;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.ProgressInfo;
import miui.systemui.dynamicisland.model.SmallIslandArea;
import miui.systemui.dynamicisland.module.IslandIconViewHolder;
import miui.systemui.dynamicisland.module.IslandProgressViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandCombineImageViewHolder extends BaseIslandModuleViewHolder {
    private final IslandIconViewHolder iconViewHolder;
    private final IslandProgressViewHolder progressViewHolder;
    private final IslandIconViewHolder smallIconViewHolder;

    public interface Factory {
        IslandCombineImageViewHolder create(ViewGroup viewGroup, Function2 function2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandCombineImageViewHolder(Context context, ViewGroup rootView, Function2 emitEvent, IslandProgressViewHolder.Factory progressViewHolderFactory, IslandIconViewHolder.Factory iconViewHolderFactory) {
        super(context, rootView, emitEvent);
        n.g(context, "context");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        n.g(progressViewHolderFactory, "progressViewHolderFactory");
        n.g(iconViewHolderFactory, "iconViewHolderFactory");
        this.progressViewHolder = progressViewHolderFactory.create(rootView, emitEvent);
        this.iconViewHolder = iconViewHolderFactory.create(rootView, emitEvent);
        this.smallIconViewHolder = iconViewHolderFactory.create(rootView, emitEvent);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void bind(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        CombinePicInfo combinePicInfo;
        CombinePicInfo combinePicInfo2;
        CombinePicInfo combinePicInfo3;
        n.g(template, "template");
        IslandIconViewHolder islandIconViewHolder = this.iconViewHolder;
        SmallIslandArea smallIslandArea = template.getSmallIslandArea();
        ProgressInfo progressInfo = null;
        islandIconViewHolder.setPicInfo((smallIslandArea == null || (combinePicInfo3 = smallIslandArea.getCombinePicInfo()) == null) ? null : combinePicInfo3.getPicInfo());
        this.iconViewHolder.updatePartial(template, dynamicIslandData);
        IslandIconViewHolder islandIconViewHolder2 = this.smallIconViewHolder;
        SmallIslandArea smallIslandArea2 = template.getSmallIslandArea();
        islandIconViewHolder2.setPicInfo((smallIslandArea2 == null || (combinePicInfo2 = smallIslandArea2.getCombinePicInfo()) == null) ? null : combinePicInfo2.getSmallPicInfo());
        this.smallIconViewHolder.updatePartial(template, dynamicIslandData);
        IslandProgressViewHolder islandProgressViewHolder = this.progressViewHolder;
        SmallIslandArea smallIslandArea3 = template.getSmallIslandArea();
        if (smallIslandArea3 != null && (combinePicInfo = smallIslandArea3.getCombinePicInfo()) != null) {
            progressInfo = combinePicInfo.getProgressInfo();
        }
        islandProgressViewHolder.setProgressInfo(progressInfo);
        this.progressViewHolder.setStrokeWidth(DynamicIslandUtils.INSTANCE.dpToPx(2, getContext()));
        this.progressViewHolder.updatePartial(template, dynamicIslandData);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void diff(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.diff(template, dynamicIslandData);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void initView(String module) {
        n.g(module, "module");
        super.initView(module);
        IslandProgressViewHolder islandProgressViewHolder = this.progressViewHolder;
        View view = getView();
        n.d(view);
        islandProgressViewHolder.initLayout(view);
        this.progressViewHolder.initView(module);
        IslandIconViewHolder islandIconViewHolder = this.iconViewHolder;
        View view2 = getView();
        View viewFindViewById = view2 != null ? view2.findViewById(R.id.island_icon_1) : null;
        n.d(viewFindViewById);
        islandIconViewHolder.initLayout(viewFindViewById);
        this.iconViewHolder.initView(module);
        IslandIconViewHolder islandIconViewHolder2 = this.smallIconViewHolder;
        View view3 = getView();
        View viewFindViewById2 = view3 != null ? view3.findViewById(R.id.island_icon_2) : null;
        n.d(viewFindViewById2);
        islandIconViewHolder2.initLayout(viewFindViewById2);
        this.smallIconViewHolder.initView(module);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updatePartial(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.updatePartial(template, dynamicIslandData);
        bind(template, dynamicIslandData);
    }
}
