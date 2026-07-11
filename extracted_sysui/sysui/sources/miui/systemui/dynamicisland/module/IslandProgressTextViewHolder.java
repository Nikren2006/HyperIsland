package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.model.BigIslandArea;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.ProgressInfo;
import miui.systemui.dynamicisland.model.ProgressTextInfo;
import miui.systemui.dynamicisland.module.IslandProgressViewHolder;
import miui.systemui.dynamicisland.module.IslandRightTextViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandProgressTextViewHolder extends BaseIslandModuleViewHolder {
    private final IslandProgressViewHolder progressViewHolder;
    private final IslandRightTextViewHolder textViewHolder;

    public interface Factory {
        IslandProgressTextViewHolder create(ViewGroup viewGroup, Function2 function2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandProgressTextViewHolder(Context context, ViewGroup rootView, Function2 emitEvent, IslandProgressViewHolder.Factory progressViewHolderFactory, IslandRightTextViewHolder.Factory rightTextViewHolder) {
        super(context, rootView, emitEvent);
        n.g(context, "context");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        n.g(progressViewHolderFactory, "progressViewHolderFactory");
        n.g(rightTextViewHolder, "rightTextViewHolder");
        this.progressViewHolder = progressViewHolderFactory.create(rootView, emitEvent);
        this.textViewHolder = rightTextViewHolder.create(rootView, emitEvent);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void bind(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        ProgressTextInfo progressTextInfo;
        ProgressTextInfo progressTextInfo2;
        n.g(template, "template");
        IslandRightTextViewHolder islandRightTextViewHolder = this.textViewHolder;
        BigIslandArea bigIslandArea = template.getBigIslandArea();
        ProgressInfo progressInfo = null;
        islandRightTextViewHolder.setTextInfo((bigIslandArea == null || (progressTextInfo2 = bigIslandArea.getProgressTextInfo()) == null) ? null : progressTextInfo2.getTextInfo());
        this.textViewHolder.updatePartial(template, dynamicIslandData);
        IslandProgressViewHolder islandProgressViewHolder = this.progressViewHolder;
        BigIslandArea bigIslandArea2 = template.getBigIslandArea();
        if (bigIslandArea2 != null && (progressTextInfo = bigIslandArea2.getProgressTextInfo()) != null) {
            progressInfo = progressTextInfo.getProgressInfo();
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
        IslandRightTextViewHolder islandRightTextViewHolder = this.textViewHolder;
        View view2 = getView();
        n.d(view2);
        islandRightTextViewHolder.initLayout(view2);
        this.textViewHolder.initView(module);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updatePartial(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.updatePartial(template, dynamicIslandData);
        bind(template, dynamicIslandData);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updateWidth(int i2) {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.progress_size);
        Log.d("IslandProgressTextViewHolder", "updateWidth: " + i2 + " " + dimensionPixelSize);
        this.textViewHolder.updateWidth(i2 - dimensionPixelSize);
    }
}
