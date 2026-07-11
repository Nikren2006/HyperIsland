package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.io.IOException;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.TextInfo;
import miui.systemui.dynamicisland.module.IslandIconViewHolder;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandTextOverIconHolder extends BaseIslandModuleViewHolder {
    private final IslandIconViewHolder iconViewHolder;
    private TextInfo textInfo;
    private TimerTextEffectView title;
    private String titleText;

    public interface Factory {
        IslandTextOverIconHolder create(ViewGroup viewGroup, Function2 function2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandTextOverIconHolder(Context context, ViewGroup rootView, Function2 emitEvent, IslandIconViewHolder.Factory iconViewHolderFactory) {
        super(context, rootView, emitEvent);
        n.g(context, "context");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        n.g(iconViewHolderFactory, "iconViewHolderFactory");
        this.iconViewHolder = iconViewHolderFactory.create(rootView, emitEvent);
    }

    private final boolean isPureInteger(String str) {
        return new f1.e("^\\d+$").a(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00ea  */
    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void bind(miui.systemui.dynamicisland.model.IslandTemplate r10, com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData r11) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.module.IslandTextOverIconHolder.bind(miui.systemui.dynamicisland.model.IslandTemplate, com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData):void");
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
        TimerTextEffectView timerTextEffectView = view2 != null ? (TimerTextEffectView) view2.findViewById(R.id.island_title) : null;
        this.title = timerTextEffectView;
        if (timerTextEffectView == null) {
            return;
        }
        timerTextEffectView.setEnableEffectWithInit(false);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void onDetach() {
        super.onDetach();
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updatePartial(IslandTemplate template, DynamicIslandData dynamicIslandData) throws IOException {
        n.g(template, "template");
        super.updatePartial(template, dynamicIslandData);
        bind(template, dynamicIslandData);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updateWidth(int i2) {
    }
}
