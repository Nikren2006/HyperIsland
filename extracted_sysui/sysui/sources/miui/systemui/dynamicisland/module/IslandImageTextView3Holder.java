package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.Collection;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.animation.FolmeKt;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.model.BigIslandArea;
import miui.systemui.dynamicisland.model.ImageTextInfo;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.TextInfo;
import miui.systemui.dynamicisland.module.IslandIconViewHolder;
import miui.systemui.dynamicisland.module.IslandRightTextViewHolder;
import miui.systemui.util.CommonUtils;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandImageTextView3Holder extends BaseIslandModuleViewHolder {
    public static final Companion Companion = new Companion(null);
    private static final IslandImageTextView3Holder$Companion$ICON_TRANS_X$1 ICON_TRANS_X = new FloatProperty<IslandImageTextView3Holder>() { // from class: miui.systemui.dynamicisland.module.IslandImageTextView3Holder$Companion$ICON_TRANS_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(IslandImageTextView3Holder holder) {
            n.g(holder, "holder");
            return holder.iconTransX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(IslandImageTextView3Holder holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.iconTransX = f2;
        }
    };
    private static final String TAG = "IslandImageTextView3Holder";
    private Choreographer choreographer;
    private final Choreographer.FrameCallback frameCallback;
    private FrameLayout iconLayout;
    private float iconTransX;
    private final IslandIconViewHolder iconViewHolder;
    private String text;
    private final IslandRightTextViewHolder textViewHolder;
    private TimerTextEffectView titleView;
    private boolean updateScheduled;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface Factory {
        IslandImageTextView3Holder create(ViewGroup viewGroup, Function2 function2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandImageTextView3Holder(Context context, ViewGroup rootView, Function2 emitEvent, IslandIconViewHolder.Factory iconViewHolderFactory, IslandRightTextViewHolder.Factory rightTextViewHolderFactory) {
        super(context, rootView, emitEvent);
        n.g(context, "context");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        n.g(iconViewHolderFactory, "iconViewHolderFactory");
        n.g(rightTextViewHolderFactory, "rightTextViewHolderFactory");
        this.iconViewHolder = iconViewHolderFactory.create(rootView, emitEvent);
        this.textViewHolder = rightTextViewHolderFactory.create(rootView, emitEvent);
        this.choreographer = Choreographer.getInstance();
        this.frameCallback = new Choreographer.FrameCallback() { // from class: miui.systemui.dynamicisland.module.g
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                IslandImageTextView3Holder.frameCallback$lambda$0(this.f5726a, j2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void frameCallback$lambda$0(IslandImageTextView3Holder this$0, long j2) {
        n.g(this$0, "this$0");
        this$0.updateScheduled = false;
        this$0.iconScheduleUpdate();
    }

    private final void iconAnimation(float f2) {
        FolmeKt.getFolme(this).to(new AnimState().add(ICON_TRANS_X, f2, new long[0]), new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.module.IslandImageTextView3Holder.iconAnimation.1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                IslandImageTextView3Holder.this.scheduleUpdate();
            }
        }));
    }

    private final void iconScheduleUpdate() {
        FrameLayout frameLayout = this.iconLayout;
        if (frameLayout == null) {
            return;
        }
        frameLayout.setTranslationX(this.iconTransX);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void bind(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        FrameLayout.LayoutParams layoutParams;
        ImageTextInfo imageTextInfoRight;
        ImageTextInfo imageTextInfoRight2;
        TextInfo textInfo;
        ImageTextInfo imageTextInfoRight3;
        n.g(template, "template");
        IslandRightTextViewHolder islandRightTextViewHolder = this.textViewHolder;
        BigIslandArea bigIslandArea = template.getBigIslandArea();
        islandRightTextViewHolder.setTextInfo((bigIslandArea == null || (imageTextInfoRight3 = bigIslandArea.getImageTextInfoRight()) == null) ? null : imageTextInfoRight3.getTextInfo());
        this.textViewHolder.updatePartial(template, dynamicIslandData);
        BigIslandArea bigIslandArea2 = template.getBigIslandArea();
        this.text = (bigIslandArea2 == null || (imageTextInfoRight2 = bigIslandArea2.getImageTextInfoRight()) == null || (textInfo = imageTextInfoRight2.getTextInfo()) == null) ? null : textInfo.getTitle();
        View view = getView();
        this.titleView = view != null ? (TimerTextEffectView) view.findViewById(R.id.island_title) : null;
        IslandIconViewHolder islandIconViewHolder = this.iconViewHolder;
        BigIslandArea bigIslandArea3 = template.getBigIslandArea();
        islandIconViewHolder.setPicInfo((bigIslandArea3 == null || (imageTextInfoRight = bigIslandArea3.getImageTextInfoRight()) == null) ? null : imageTextInfoRight.getPicInfo());
        this.iconViewHolder.updatePartial(template, dynamicIslandData);
        if (this.iconViewHolder.getPicInfo() == null) {
            View view2 = getView();
            View viewFindViewById = view2 != null ? view2.findViewById(R.id.island_container_module_text) : null;
            if (viewFindViewById == null) {
                return;
            }
            ViewGroup.LayoutParams layoutParams2 = viewFindViewById.getLayoutParams();
            layoutParams = layoutParams2 instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams2 : null;
            if (layoutParams == null) {
                return;
            }
            layoutParams.setMarginStart(0);
            viewFindViewById.setLayoutParams(layoutParams);
            return;
        }
        View view3 = getView();
        View viewFindViewById2 = view3 != null ? view3.findViewById(R.id.island_container_module_text) : null;
        if (viewFindViewById2 == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams3 = viewFindViewById2.getLayoutParams();
        layoutParams = layoutParams3 instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams3 : null;
        if (layoutParams == null) {
            return;
        }
        int width = this.iconViewHolder.getWidth();
        Log.d(TAG, "marginStart:" + width);
        layoutParams.setMarginStart(width);
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
        View view3 = getView();
        this.iconLayout = view3 != null ? (FrameLayout) view3.findViewById(R.id.island_container_module_icon) : null;
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

    public final void scheduleUpdate() {
        if (this.updateScheduled) {
            return;
        }
        this.updateScheduled = true;
        Choreographer choreographer = this.choreographer;
        if (choreographer != null) {
            choreographer.postFrameCallback(this.frameCallback);
        }
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updatePartial(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.updatePartial(template, dynamicIslandData);
        bind(template, dynamicIslandData);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updateWidth(int i2) {
        Log.d(TAG, "originWidth:" + i2);
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.island_area_padding_cutout);
        int width = this.iconViewHolder.getWidth();
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.text_padding);
        int dimensionPixelSize3 = getContext().getResources().getDimensionPixelSize(R.dimen.island_area_padding);
        int textViewWidth = getTextViewWidth(this.titleView, this.text);
        int i3 = (i2 - dimensionPixelSize) - width;
        int i4 = (i3 - dimensionPixelSize2) - dimensionPixelSize3;
        float f2 = textViewWidth * 0.79f;
        if (i4 < f2 || i4 >= textViewWidth) {
            iconAnimation(0.0f);
        } else {
            float f3 = !CommonUtils.isLayoutRtl(getContext()) ? ((i3 - f2) - dimensionPixelSize3) - dimensionPixelSize2 : -(((i3 - f2) - dimensionPixelSize3) - dimensionPixelSize2);
            Log.d(TAG, "iconTranX:" + f3);
            iconAnimation(f3);
        }
        Log.d(TAG, "iconWidth:" + this.iconViewHolder.getWidth());
        IslandRightTextViewHolder islandRightTextViewHolder = this.textViewHolder;
        if (islandRightTextViewHolder != null) {
            islandRightTextViewHolder.updateWidth(i2 - width);
        }
    }
}
