package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.ProgressInfo;
import miui.systemui.widget.CircularProgressBar;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandProgressViewHolder extends BaseIslandModuleViewHolder {
    private final String defaultReachColor;
    private final String defaultUnReachColor;
    private CircularProgressBar progress;
    private ProgressInfo progressInfo;
    private int strokeWidth;

    public interface Factory {
        IslandProgressViewHolder create(ViewGroup viewGroup, Function2 function2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandProgressViewHolder(Context context, ViewGroup rootView, Function2 emitEvent) {
        super(context, rootView, emitEvent);
        n.g(context, "context");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        this.defaultReachColor = "#4788FF";
        this.defaultUnReachColor = "#33FFFFFF";
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void bind(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        String colorReach;
        int color;
        int color2;
        Integer progress;
        Boolean boolIsCCW;
        n.g(template, "template");
        super.bind(template, dynamicIslandData);
        ProgressInfo progressInfo = this.progressInfo;
        if (progressInfo == null) {
            CircularProgressBar circularProgressBar = this.progress;
            if (circularProgressBar == null) {
                return;
            }
            circularProgressBar.setVisibility(8);
            return;
        }
        if (progressInfo != null) {
            try {
                colorReach = progressInfo.getColorReach();
            } catch (Exception unused) {
                color = Color.parseColor(this.defaultReachColor);
            }
        } else {
            colorReach = null;
        }
        color = Color.parseColor(colorReach);
        try {
            ProgressInfo progressInfo2 = this.progressInfo;
            color2 = Color.parseColor(progressInfo2 != null ? progressInfo2.getColorUnReach() : null);
        } catch (Exception unused2) {
            color2 = Color.parseColor(this.defaultUnReachColor);
        }
        CircularProgressBar circularProgressBar2 = this.progress;
        if (circularProgressBar2 != null) {
            circularProgressBar2.setUp(this.strokeWidth, color2, color, 100.0f);
        }
        CircularProgressBar circularProgressBar3 = this.progress;
        if (circularProgressBar3 != null) {
            ProgressInfo progressInfo3 = this.progressInfo;
            circularProgressBar3.isCCW((progressInfo3 == null || (boolIsCCW = progressInfo3.isCCW()) == null) ? false : boolIsCCW.booleanValue());
        }
        CircularProgressBar circularProgressBar4 = this.progress;
        if (circularProgressBar4 != null) {
            ProgressInfo progressInfo4 = this.progressInfo;
            circularProgressBar4.setProgress((progressInfo4 == null || (progress = progressInfo4.getProgress()) == null) ? 0.0f : progress.intValue());
        }
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void diff(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.diff(template, dynamicIslandData);
    }

    public final ProgressInfo getProgressInfo() {
        return this.progressInfo;
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void initView(String module) {
        n.g(module, "module");
        super.initView(module);
        View view = getView();
        this.progress = view != null ? (CircularProgressBar) view.findViewById(R.id.island_progress) : null;
    }

    public final void setProgressInfo(ProgressInfo progressInfo) {
        this.progressInfo = progressInfo;
    }

    public final void setStrokeWidth(int i2) {
        this.strokeWidth = i2;
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updatePartial(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.updatePartial(template, dynamicIslandData);
        bind(template, dynamicIslandData);
    }
}
