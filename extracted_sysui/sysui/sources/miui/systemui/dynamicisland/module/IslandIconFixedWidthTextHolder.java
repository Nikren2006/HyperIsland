package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.TextInfo;
import miui.systemui.dynamicisland.module.IslandIconViewHolder;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandIconFixedWidthTextHolder extends BaseIslandModuleViewHolder {
    private TimerTextEffectView content;
    private String contentText;
    private final IslandIconViewHolder iconViewHolder;
    private TextInfo textInfo;
    private TimerTextEffectView title;
    private String titleText;

    public interface Factory {
        IslandIconFixedWidthTextHolder create(ViewGroup viewGroup, Function2 function2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandIconFixedWidthTextHolder(Context context, ViewGroup rootView, Function2 emitEvent, IslandIconViewHolder.Factory iconViewHolderFactory) {
        super(context, rootView, emitEvent);
        n.g(context, "context");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        n.g(iconViewHolderFactory, "iconViewHolderFactory");
        this.iconViewHolder = iconViewHolderFactory.create(rootView, emitEvent);
    }

    private final boolean isPureNumber(String str) {
        return new f1.e("\\d+(\\.\\d+)?").a(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00af  */
    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void bind(miui.systemui.dynamicisland.model.IslandTemplate r9, com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData r10) {
        /*
            Method dump skipped, instruction units count: 356
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.module.IslandIconFixedWidthTextHolder.bind(miui.systemui.dynamicisland.model.IslandTemplate, com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData):void");
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
        View view = getView();
        if (view != null) {
            this.iconViewHolder.initLayout(view);
        }
        this.iconViewHolder.initView(module);
        View view2 = getView();
        TimerTextEffectView timerTextEffectView = view2 != null ? (TimerTextEffectView) view2.findViewById(R.id.fixed_width_text) : null;
        this.title = timerTextEffectView;
        if (timerTextEffectView != null) {
            timerTextEffectView.setEnableEffectWithInit(false);
        }
        View view3 = getView();
        TimerTextEffectView timerTextEffectView2 = view3 != null ? (TimerTextEffectView) view3.findViewById(R.id.unit_text) : null;
        this.content = timerTextEffectView2;
        if (timerTextEffectView2 == null) {
            return;
        }
        timerTextEffectView2.setEnableEffectWithInit(false);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void onDetach() {
        super.onDetach();
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updatePartial(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.updatePartial(template, dynamicIslandData);
        bind(template, dynamicIslandData);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updateWidth(int i2) {
    }
}
