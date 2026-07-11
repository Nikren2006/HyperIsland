package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.model.BigIslandArea;
import miui.systemui.dynamicisland.model.ImageTextInfo;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.PicInfo;
import miui.systemui.dynamicisland.module.IslandIconViewHolder;
import miui.systemui.dynamicisland.module.IslandRightTextViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandImageTextView2Holder extends BaseIslandModuleViewHolder {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "IslandImageTextViewHolder2";
    private final IslandIconViewHolder iconViewHolder;
    private final IslandRightTextViewHolder textViewHolder;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface Factory {
        IslandImageTextView2Holder create(ViewGroup viewGroup, Function2 function2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandImageTextView2Holder(Context context, ViewGroup rootView, Function2 emitEvent, IslandIconViewHolder.Factory iconViewHolderFactory, IslandRightTextViewHolder.Factory rightTextViewHolderFactory) {
        super(context, rootView, emitEvent);
        n.g(context, "context");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        n.g(iconViewHolderFactory, "iconViewHolderFactory");
        n.g(rightTextViewHolderFactory, "rightTextViewHolderFactory");
        this.iconViewHolder = iconViewHolderFactory.create(rootView, emitEvent);
        this.textViewHolder = rightTextViewHolderFactory.create(rootView, emitEvent);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void bind(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        PicInfo picInfo;
        ImageTextInfo imageTextInfoRight;
        FrameLayout.LayoutParams layoutParams;
        ImageTextInfo imageTextInfoLeft;
        ImageTextInfo imageTextInfoRight2;
        n.g(template, "template");
        IslandRightTextViewHolder islandRightTextViewHolder = this.textViewHolder;
        BigIslandArea bigIslandArea = template.getBigIslandArea();
        islandRightTextViewHolder.setTextInfo((bigIslandArea == null || (imageTextInfoRight2 = bigIslandArea.getImageTextInfoRight()) == null) ? null : imageTextInfoRight2.getTextInfo());
        this.textViewHolder.updatePartial(template, dynamicIslandData);
        IslandIconViewHolder islandIconViewHolder = this.iconViewHolder;
        if (n.c(getModule(), DynamicIslandConstants.MODULE_IMAGE_TEXT_1)) {
            BigIslandArea bigIslandArea2 = template.getBigIslandArea();
            if (bigIslandArea2 == null || (imageTextInfoLeft = bigIslandArea2.getImageTextInfoLeft()) == null || (picInfo = imageTextInfoLeft.getPicInfo()) == null) {
                picInfo = new PicInfo();
            }
        } else {
            BigIslandArea bigIslandArea3 = template.getBigIslandArea();
            picInfo = (bigIslandArea3 == null || (imageTextInfoRight = bigIslandArea3.getImageTextInfoRight()) == null) ? null : imageTextInfoRight.getPicInfo();
        }
        islandIconViewHolder.setPicInfo(picInfo);
        this.iconViewHolder.updatePartial(template, dynamicIslandData);
        if (this.iconViewHolder.getPicInfo() == null) {
            View view = getView();
            View viewFindViewById = view != null ? view.findViewById(R.id.island_container_module_text) : null;
            if (viewFindViewById == null) {
                return;
            }
            ViewGroup.LayoutParams layoutParams2 = viewFindViewById.getLayoutParams();
            layoutParams = layoutParams2 instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams2 : null;
            if (layoutParams == null) {
                return;
            }
            layoutParams.setMarginEnd(0);
            viewFindViewById.setLayoutParams(layoutParams);
            return;
        }
        View view2 = getView();
        View viewFindViewById2 = view2 != null ? view2.findViewById(R.id.island_container_module_text) : null;
        if (viewFindViewById2 == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams3 = viewFindViewById2.getLayoutParams();
        layoutParams = layoutParams3 instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams3 : null;
        if (layoutParams == null) {
            return;
        }
        int width = this.iconViewHolder.getWidth();
        Log.d(TAG, "marginEnd:" + width);
        layoutParams.setMarginEnd(width);
        viewFindViewById2.setLayoutParams(layoutParams);
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
        IslandRightTextViewHolder islandRightTextViewHolder = this.textViewHolder;
        View view2 = getView();
        n.d(view2);
        islandRightTextViewHolder.initLayout(view2);
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
