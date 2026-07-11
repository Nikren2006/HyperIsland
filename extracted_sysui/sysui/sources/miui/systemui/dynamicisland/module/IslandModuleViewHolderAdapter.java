package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.model.IslandTemplate;
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
public final class IslandModuleViewHolderAdapter {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "IslandModuleViewHolderAdapter";
    private final IslandCombineImageViewHolder.Factory combineImageViewHolderFactory;
    private final Context context;
    private final Map<String, IslandTemplate> dataMap;
    private final IslandFixedWidthDigitViewHolder.Factory fixedWidthDigitViewHolderFactory;
    private final Map<String, BaseIslandModuleViewHolder> holders;
    private final IslandIconFixedWidthTextHolder.Factory iconFixedWidthTextHolderFactory;
    private final IslandIconViewHolder.Factory iconViewHolderFactory;
    private final IslandImageTextView2Holder.Factory imageTextView2HolderFactory;
    private final IslandImageTextView3Holder.Factory imageTextView3HolderFactory;
    private final IslandImageTextView4Holder.Factory imageTextView4HolderFactory;
    private final IslandImageTextViewHolder.Factory imageTextViewHolderFactory;
    private final IslandProgressTextViewHolder.Factory progressTextViewHolderFactory;
    private final IslandRightTextViewHolder.Factory rightTextViewHolderFactory;
    private final IslandSameWidthDigitViewHolder.Factory sameWidthDigitViewHolderFactory;
    private final IslandTextOverIconHolder.Factory textOverIconHolderFactory;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public IslandModuleViewHolderAdapter(Context context, IslandIconViewHolder.Factory iconViewHolderFactory, IslandImageTextViewHolder.Factory imageTextViewHolderFactory, IslandImageTextView2Holder.Factory imageTextView2HolderFactory, IslandImageTextView3Holder.Factory imageTextView3HolderFactory, IslandImageTextView4Holder.Factory imageTextView4HolderFactory, IslandProgressTextViewHolder.Factory progressTextViewHolderFactory, IslandRightTextViewHolder.Factory rightTextViewHolderFactory, IslandFixedWidthDigitViewHolder.Factory fixedWidthDigitViewHolderFactory, IslandSameWidthDigitViewHolder.Factory sameWidthDigitViewHolderFactory, IslandCombineImageViewHolder.Factory combineImageViewHolderFactory, IslandIconFixedWidthTextHolder.Factory iconFixedWidthTextHolderFactory, IslandTextOverIconHolder.Factory textOverIconHolderFactory) {
        n.g(context, "context");
        n.g(iconViewHolderFactory, "iconViewHolderFactory");
        n.g(imageTextViewHolderFactory, "imageTextViewHolderFactory");
        n.g(imageTextView2HolderFactory, "imageTextView2HolderFactory");
        n.g(imageTextView3HolderFactory, "imageTextView3HolderFactory");
        n.g(imageTextView4HolderFactory, "imageTextView4HolderFactory");
        n.g(progressTextViewHolderFactory, "progressTextViewHolderFactory");
        n.g(rightTextViewHolderFactory, "rightTextViewHolderFactory");
        n.g(fixedWidthDigitViewHolderFactory, "fixedWidthDigitViewHolderFactory");
        n.g(sameWidthDigitViewHolderFactory, "sameWidthDigitViewHolderFactory");
        n.g(combineImageViewHolderFactory, "combineImageViewHolderFactory");
        n.g(iconFixedWidthTextHolderFactory, "iconFixedWidthTextHolderFactory");
        n.g(textOverIconHolderFactory, "textOverIconHolderFactory");
        this.context = context;
        this.iconViewHolderFactory = iconViewHolderFactory;
        this.imageTextViewHolderFactory = imageTextViewHolderFactory;
        this.imageTextView2HolderFactory = imageTextView2HolderFactory;
        this.imageTextView3HolderFactory = imageTextView3HolderFactory;
        this.imageTextView4HolderFactory = imageTextView4HolderFactory;
        this.progressTextViewHolderFactory = progressTextViewHolderFactory;
        this.rightTextViewHolderFactory = rightTextViewHolderFactory;
        this.fixedWidthDigitViewHolderFactory = fixedWidthDigitViewHolderFactory;
        this.sameWidthDigitViewHolderFactory = sameWidthDigitViewHolderFactory;
        this.combineImageViewHolderFactory = combineImageViewHolderFactory;
        this.iconFixedWidthTextHolderFactory = iconFixedWidthTextHolderFactory;
        this.textOverIconHolderFactory = textOverIconHolderFactory;
        this.holders = new LinkedHashMap();
        this.dataMap = new LinkedHashMap();
    }

    public final void bindData(String moduleType, DynamicIslandData dynamicIslandData) {
        BaseIslandModuleViewHolder baseIslandModuleViewHolder;
        n.g(moduleType, "moduleType");
        Log.e(TAG, "bindData " + moduleType + this.holders.size());
        IslandTemplate islandTemplate = this.dataMap.get(moduleType);
        if (islandTemplate == null || (baseIslandModuleViewHolder = this.holders.get(moduleType)) == null) {
            return;
        }
        baseIslandModuleViewHolder.bind(islandTemplate, dynamicIslandData);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void createModuleViewHolder(IslandTemplate template, String moduleType, ViewGroup rootView, Function2 emitEvent) {
        BaseIslandModuleViewHolder baseIslandModuleViewHolderCreate;
        n.g(template, "template");
        n.g(moduleType, "moduleType");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        Log.e(TAG, "createModuleViewHolder " + moduleType);
        switch (moduleType.hashCode()) {
            case -1848403042:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_PIC_SMALL_ISLAND)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.iconViewHolderFactory.create(rootView, emitEvent);
                    View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_icon_1, rootView);
                    n.f(viewInflate, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate);
                }
                break;
            case -1552094338:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_PIC)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.iconViewHolderFactory.create(rootView, emitEvent);
                    View viewInflate2 = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_icon_1, rootView);
                    n.f(viewInflate2, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate2);
                }
                break;
            case -1472124073:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_COMBINE_PIC)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.combineImageViewHolderFactory.create(rootView, emitEvent);
                    View viewInflate3 = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_combine_image, rootView);
                    n.f(viewInflate3, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate3);
                }
                break;
            case -962289255:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_SAME_WIDTH_DIGIT)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.sameWidthDigitViewHolderFactory.create(rootView, emitEvent);
                    View viewInflate4 = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_same_width_digit, rootView);
                    n.f(viewInflate4, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate4);
                }
                break;
            case -870168135:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_TEXT)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.rightTextViewHolderFactory.create(rootView, emitEvent);
                    View viewInflate5 = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_right_text, rootView);
                    n.f(viewInflate5, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate5);
                }
                break;
            case 150578382:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_IMAGE_TEXT_1)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.imageTextViewHolderFactory.create(rootView, emitEvent);
                    View viewInflate6 = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_image_text_1, rootView);
                    n.f(viewInflate6, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate6);
                }
                break;
            case 150578383:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_IMAGE_TEXT_2)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.imageTextView2HolderFactory.create(rootView, emitEvent);
                    View viewInflate7 = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_image_text_2, rootView);
                    n.f(viewInflate7, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate7);
                }
                break;
            case 150578384:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_IMAGE_TEXT_3)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.imageTextView3HolderFactory.create(rootView, emitEvent);
                    View viewInflate8 = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_image_text_3, rootView);
                    n.f(viewInflate8, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate8);
                }
                break;
            case 150578385:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_IMAGE_TEXT_4)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.imageTextView4HolderFactory.create(rootView, emitEvent);
                    View viewInflate9 = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_image_text_4, rootView);
                    n.f(viewInflate9, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate9);
                }
                break;
            case 496193380:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_ICON_FIXED_WIDTH_TEXT)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.iconFixedWidthTextHolderFactory.create(rootView, emitEvent);
                    View viewInflate10 = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_icon_fixed_width_text, rootView);
                    n.f(viewInflate10, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate10);
                }
                break;
            case 1170007381:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_SMALL_TEXT_OVER_ICON)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.textOverIconHolderFactory.create(rootView, emitEvent);
                    View viewInflate11 = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_text_over_icon, rootView);
                    n.f(viewInflate11, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate11);
                }
                break;
            case 1279391119:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_FIXED_WIDTH_DIGIT)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.fixedWidthDigitViewHolderFactory.create(rootView, emitEvent);
                    View viewInflate12 = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_fixed_width_digit, rootView);
                    n.f(viewInflate12, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate12);
                }
                break;
            case 1434791964:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_PROGRESS_TEXT)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.progressTextViewHolderFactory.create(rootView, emitEvent);
                    View viewInflate13 = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_progress_text, rootView);
                    n.f(viewInflate13, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate13);
                }
                break;
            case 1453082694:
                if (!moduleType.equals(DynamicIslandConstants.MODULE_TEXT_OVER_ICON)) {
                    baseIslandModuleViewHolderCreate = null;
                } else {
                    baseIslandModuleViewHolderCreate = this.textOverIconHolderFactory.create(rootView, emitEvent);
                    View viewInflate14 = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_module_text_over_icon, rootView);
                    n.f(viewInflate14, "inflate(...)");
                    baseIslandModuleViewHolderCreate.initLayout(viewInflate14);
                }
                break;
            default:
                baseIslandModuleViewHolderCreate = null;
                break;
        }
        View view = baseIslandModuleViewHolderCreate != null ? baseIslandModuleViewHolderCreate.getView() : null;
        if (view != null) {
            view.setLayoutDirection(this.context.getResources().getConfiguration().getLayoutDirection());
        }
        if (baseIslandModuleViewHolderCreate != null) {
            baseIslandModuleViewHolderCreate.initView(moduleType);
        }
        this.holders.put(moduleType, baseIslandModuleViewHolderCreate);
        this.dataMap.put(moduleType, template);
    }

    public final void hiddenView(String str) {
        BaseIslandModuleViewHolder baseIslandModuleViewHolder = this.holders.get(str);
        if (baseIslandModuleViewHolder != null) {
            baseIslandModuleViewHolder.onHidden();
        }
    }

    public final void removeView(String moduleType) {
        n.g(moduleType, "moduleType");
        BaseIslandModuleViewHolder baseIslandModuleViewHolderRemove = this.holders.remove(moduleType);
        if (baseIslandModuleViewHolderRemove != null) {
            baseIslandModuleViewHolderRemove.onDetach();
        }
    }

    public final void showView(String str) {
        BaseIslandModuleViewHolder baseIslandModuleViewHolder = this.holders.get(str);
        if (baseIslandModuleViewHolder != null) {
            baseIslandModuleViewHolder.onShow();
        }
    }

    public final void updateLeftWidth(String str, int i2) {
        Log.e(TAG, "updateLeftWidth" + str + this.holders.size());
        BaseIslandModuleViewHolder baseIslandModuleViewHolder = this.holders.get(str);
        if (baseIslandModuleViewHolder != null) {
            baseIslandModuleViewHolder.updateWidth(i2);
        }
    }

    public final void updateRightWidth(String str, int i2) {
        Log.e(TAG, "updateRightWidth" + str + this.holders.size());
        BaseIslandModuleViewHolder baseIslandModuleViewHolder = this.holders.get(str);
        if (baseIslandModuleViewHolder != null) {
            baseIslandModuleViewHolder.updateWidth(i2);
        }
    }

    public final void updateView(String moduleType, IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(moduleType, "moduleType");
        n.g(template, "template");
        this.dataMap.put(moduleType, template);
        BaseIslandModuleViewHolder baseIslandModuleViewHolder = this.holders.get(moduleType);
        if (baseIslandModuleViewHolder != null) {
            baseIslandModuleViewHolder.updatePartial(template, dynamicIslandData);
        }
        Log.e(TAG, "updateView" + moduleType + this.holders.size());
    }
}
