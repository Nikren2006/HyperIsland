package com.android.systemui.miui.volume;

import android.content.Context;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.FlipUtils;
import miui.systemui.util.MiuiColorBlendToken;
import miuix.theme.token.ColorBlendToken;

/* JADX INFO: loaded from: classes2.dex */
public final class MiuiVolumeDialogRes {
    public static final MiuiVolumeDialogRes INSTANCE = new MiuiVolumeDialogRes();

    private MiuiVolumeDialogRes() {
    }

    public static final ColorBlendToken getBgBlandColor(boolean z2) {
        return z2 ? MiuiColorBlendToken.INSTANCE.getVOLUME_DIALOG_BG_EXPANDED() : MiuiColorBlendToken.INSTANCE.getVOLUME_DIALOG_BG_CC();
    }

    public static final int getBgRadius(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize(R.dimen.o3_miui_volume_bg_radius);
    }

    public static final int getBgRes(boolean z2) {
        return z2 ? R.drawable.o3_miui_volume_expand_background : R.drawable.o3_miui_volume_expand_background_cc;
    }

    public static final int getBgWithContentPadding(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize(z2 ? R.dimen.miui_volume_background_padding : R.dimen.miui_volume_background_padding_cc);
    }

    public static final int getBlandBlurRadius(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize(R.dimen.mi_blur_max_radius_collapsed);
    }

    public static final ColorBlendToken getExpandedIconBlandColor() {
        return MiuiColorBlendToken.INSTANCE.getVC_EXPANDED_BTN_ICON();
    }

    public static final int getExpandedIconColorRes(boolean z2) {
        return z2 ? R.color.miui_volume_expand_button_color_blur_light : R.color.miui_volume_expand_button_color_blur;
    }

    public static final int getHeight(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize((z2 && z3) ? R.dimen.o3_miui_volume_background_height_expanded : z2 ? R.dimen.o3_miui_volume_background_height : R.dimen.miui_volume_background_height_cc);
    }

    public static final int getMarginRight(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize((FlipUtils.isFlipTiny() && FlipUtils.isFlipTinyLand()) ? R.dimen.flip_tiny_land_miui_volume_offset_end : FlipUtils.isFlipTiny() ? R.dimen.flip_tiny_miui_volume_offset_end : (z2 && z3) ? R.dimen.miui_volume_offset_end_expand : z2 ? R.dimen.miui_volume_offset_end : R.dimen.miui_volume_offset_end_cc);
    }

    public static final int getMarginTop(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        return getMarginTop$default(context, false, false, 0, 0, 30, null);
    }

    public static /* synthetic */ int getMarginTop$default(Context context, boolean z2, boolean z3, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            z2 = false;
        }
        if ((i4 & 4) != 0) {
            z3 = false;
        }
        if ((i4 & 8) != 0) {
            i2 = 0;
        }
        if ((i4 & 16) != 0) {
            i3 = 0;
        }
        return getMarginTop(context, z2, z3, i2, i3);
    }

    public static final int getRingerModeLayoutDividerHeight(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize(z2 ? R.dimen.o3_miui_volume_ringer_divider_height : R.dimen.o3_miui_volume_ringer_divider_height_cc);
    }

    public static final int getRingerModeLayoutMarginTop(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize(z2 ? R.dimen.miui_volume_footer_margin_top_expanded : R.dimen.miui_volume_footer_margin_top_expanded_cc);
    }

    public static final int getRingerModeLayoutWidth(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize((z2 && z3) ? R.dimen.miui_volume_ringer_layout_width_expanded_4stream : z2 ? R.dimen.miui_volume_ringer_layout_width_expanded : R.dimen.miui_volume_ringer_layout_width_expanded_cc);
    }

    public static final int getShadowHeight(Context context, boolean z2, boolean z3, int i2, boolean z4) {
        kotlin.jvm.internal.n.g(context, "context");
        if (z3) {
            return i2 + context.getResources().getDimensionPixelSize(R.dimen.miui_volume_shadow_padding_top_expanded) + context.getResources().getDimensionPixelSize(R.dimen.miui_volume_shadow_padding_bottom_expanded);
        }
        return context.getResources().getDimensionPixelSize((FlipUtils.isFlipTiny() && z4) ? R.dimen.flip_tiny_miui_volume_dialog_shadow_height_dual : FlipUtils.isFlipTiny() ? R.dimen.flip_tiny_miui_volume_dialog_shadow_height : z2 ? R.dimen.miui_volume_dialog_shadow_height : R.dimen.miui_volume_dialog_shadow_height_no_footer);
    }

    public static final int getShadowMarginEnd(Context context, boolean z2, int i2, int i3, int i4, int i5, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return z2 ? i2 - context.getResources().getDimensionPixelSize(R.dimen.miui_volume_shadow_padding_left_expanded) : i5 - ((getShadowWidth(context, z2, z3, i3) - i4) >> 1);
    }

    public static final int getShadowMarginTop(Context context, int i2, boolean z2, boolean z3, int i3, boolean z4) {
        kotlin.jvm.internal.n.g(context, "context");
        if (z3) {
            return i3 - context.getResources().getDimensionPixelSize(R.dimen.miui_volume_shadow_padding_top_expanded);
        }
        int shadowHeight = getShadowHeight(context, z2, false, 0, z4);
        int height = VolumeColumnRes.getHeight(context, true, z3);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.miui_volume_footer_margin_top);
        int height2 = RingerButtonRes.getHeight(context, z3, true);
        if (z2) {
            height = height + (dimensionPixelSize * 2) + (height2 * 2);
        }
        return i2 - ((shadowHeight - height) >> 1);
    }

    public static final int getShadowResource(boolean z2, boolean z3, boolean z4) {
        return (z3 && z4) ? R.drawable.shadow_background_dual_ringer : z3 ? R.drawable.shadow_background_dual : z2 ? R.drawable.shadow_background_expand : R.drawable.shadow_background;
    }

    public static final int getShadowWidth(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return getShadowWidth$default(context, z2, z3, 0, 8, null);
    }

    public static /* synthetic */ int getShadowWidth$default(Context context, boolean z2, boolean z3, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return getShadowWidth(context, z2, z3, i2);
    }

    public static final int getSuperVolumeMarginEnd(Context context, int i2) {
        kotlin.jvm.internal.n.g(context, "context");
        int superVolumeWidth = getSuperVolumeWidth(context);
        return getMarginRight(context, true, false) + ((VolumeColumnRes.getWidth$default(context, false, false, false, 14, null) - superVolumeWidth) >> 1) + i2;
    }

    public static final int getSuperVolumeMarginTop(Context context, boolean z2, boolean z3, int i2) {
        kotlin.jvm.internal.n.g(context, "context");
        int height = getHeight(context, z2, z3);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.miui_super_volume_text_height_collapsed);
        return (getMarginTop(context, z2, z3, i2, height) - dimensionPixelSize) - context.getResources().getDimensionPixelSize(R.dimen.miui_super_volume_text_margin_bottom);
    }

    public static /* synthetic */ int getSuperVolumeMarginTop$default(Context context, boolean z2, boolean z3, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        if ((i3 & 4) != 0) {
            z3 = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return getSuperVolumeMarginTop(context, z2, z3, i2);
    }

    public static final int getSuperVolumeWidth(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize(R.dimen.miui_super_volume_text_width_collapsed);
    }

    public static final int getTempColumnContainerMarginLeft(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize((FlipUtils.isFlipTiny() && FlipUtils.isFlipTinyLand()) ? R.dimen.flip_tiny_land_miui_volume_temp_margin_active : FlipUtils.isFlipTiny() ? R.dimen.flip_tiny_miui_volume_temp_margin_active : R.dimen.miui_volume_temp_margin_active);
    }

    public static final int getVolumeViewWidth(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize((z2 && z3) ? R.dimen.miui_volume_content_width_expanded_4stream : z2 ? R.dimen.miui_volume_content_width_expanded : R.dimen.miui_volume_content_width_expanded_cc);
    }

    public static final int getWidth(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize((z2 && z3) ? R.dimen.miui_volume_background_width_4stream : z2 ? R.dimen.miui_volume_background_width : R.dimen.miui_volume_background_width_cc);
    }

    public static final int getMarginTop(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return getMarginTop$default(context, z2, false, 0, 0, 28, null);
    }

    public static final int getShadowWidth(Context context, boolean z2, boolean z3, int i2) {
        kotlin.jvm.internal.n.g(context, "context");
        return z2 ? i2 + (context.getResources().getDimensionPixelSize(R.dimen.miui_volume_shadow_padding_left_expanded) * 2) : (FlipUtils.isFlipTiny() && z3) ? context.getResources().getDimensionPixelSize(R.dimen.miui_volume_dialog_shadow_width_flip_tiny_dual) : FlipUtils.isFlipTiny() ? context.getResources().getDimensionPixelSize(R.dimen.miui_volume_dialog_shadow_width_flip_tiny) : z3 ? context.getResources().getDimensionPixelSize(R.dimen.miui_volume_dialog_shadow_width_dual) : context.getResources().getDimensionPixelSize(R.dimen.miui_volume_dialog_shadow_width);
    }

    public static final int getMarginTop(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return getMarginTop$default(context, z2, z3, 0, 0, 24, null);
    }

    public static final int getMarginTop(Context context, boolean z2, boolean z3, int i2) {
        kotlin.jvm.internal.n.g(context, "context");
        return getMarginTop$default(context, z2, z3, i2, 0, 16, null);
    }

    public static final int getMarginTop(Context context, boolean z2, boolean z3, int i2, int i3) {
        kotlin.jvm.internal.n.g(context, "context");
        boolean z4 = CommonUtils.INSTANCE.getIS_FOLD() && CommonUtils.isScreenLayoutLarge(context);
        if (FlipUtils.isFlipTiny() && FlipUtils.isFlipTinyLand()) {
            return context.getResources().getDimensionPixelSize(R.dimen.flip_tiny_land_miui_volume_offset_top_collapsed);
        }
        if (FlipUtils.isFlipTiny()) {
            return context.getResources().getDimensionPixelSize(R.dimen.flip_tiny_miui_volume_offset_top_collapsed);
        }
        if (!z2 || context.getResources().getConfiguration().orientation == 2) {
            return (i2 - i3) >> 1;
        }
        if (z4 && z3) {
            return context.getResources().getDimensionPixelSize(R.dimen.o3_miui_large_screen_volume_margin_top_expanded);
        }
        if (z4) {
            return context.getResources().getDimensionPixelSize(R.dimen.o3_miui_large_screen_volume_margin_top);
        }
        if (z3) {
            return context.getResources().getDimensionPixelSize(R.dimen.o3_miui_volume_background_margin_top_expanded);
        }
        return context.getResources().getDimensionPixelSize(R.dimen.o3_miui_volume_background_margin_top);
    }
}
