package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.module.IslandIconViewHolder;
import miui.systemui.dynamicisland.module.IslandTextViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandImageTextViewHolder extends BaseIslandModuleViewHolder {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "IslandImageTextViewHolder";
    private final IslandIconViewHolder iconViewHolder;
    private final IslandTextViewHolder textViewHolder;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface Factory {
        IslandImageTextViewHolder create(ViewGroup viewGroup, Function2 function2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandImageTextViewHolder(Context context, ViewGroup rootView, Function2 emitEvent, IslandIconViewHolder.Factory iconViewHolderFactory, IslandTextViewHolder.Factory textViewHolderFactory) {
        super(context, rootView, emitEvent);
        n.g(context, "context");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        n.g(iconViewHolderFactory, "iconViewHolderFactory");
        n.g(textViewHolderFactory, "textViewHolderFactory");
        this.iconViewHolder = iconViewHolderFactory.create(rootView, emitEvent);
        this.textViewHolder = textViewHolderFactory.create(rootView, emitEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void bind(miui.systemui.dynamicisland.model.IslandTemplate r5, com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData r6) {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.module.IslandImageTextViewHolder.bind(miui.systemui.dynamicisland.model.IslandTemplate, com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData):void");
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
        IslandIconViewHolder islandIconViewHolder = this.iconViewHolder;
        View view = getView();
        n.d(view);
        islandIconViewHolder.initLayout(view);
        this.iconViewHolder.initView(module);
        IslandTextViewHolder islandTextViewHolder = this.textViewHolder;
        View view2 = getView();
        n.d(view2);
        islandTextViewHolder.initLayout(view2);
        this.textViewHolder.initView(module);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void onDetach() {
        super.onDetach();
        this.iconViewHolder.onDetach();
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void onHidden() {
        super.onHidden();
        this.iconViewHolder.onHidden();
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void onShow() {
        super.onShow();
        this.iconViewHolder.onShow();
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updatePartial(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.updatePartial(template, dynamicIslandData);
        bind(template, dynamicIslandData);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updateWidth(int i2) {
        Log.d(TAG, "updateWidth: " + i2);
        this.textViewHolder.updateWidth(i2 - this.iconViewHolder.getWidth());
    }
}
