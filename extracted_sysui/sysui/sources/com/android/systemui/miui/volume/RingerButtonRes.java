package com.android.systemui.miui.volume;

import android.content.Context;
import androidx.core.content.ContextCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miuix.theme.token.ColorBlendToken;

/* JADX INFO: loaded from: classes2.dex */
public final class RingerButtonRes {
    public static final RingerButtonRes INSTANCE = new RingerButtonRes();

    private RingerButtonRes() {
    }

    public static final int getBlurViewBg(boolean z2, boolean z3) {
        return (z2 && z3) ? R.drawable.o3_miui_volume_ringer_bg_blur_epanded : z2 ? R.drawable.o3_miui_volume_ringer_bg_blur : R.drawable.o3_miui_volume_ringer_bg_blur_cc;
    }

    public static final ColorBlendToken getButtonBgBlendColor(boolean z2, boolean z3, boolean z4) {
        return (z2 && z3 && z4) ? MiuiColorBlendToken.INSTANCE.getRINGER_BG_ON_EXPANDED() : (z2 && z3) ? MiuiColorBlendToken.INSTANCE.getRINGER_BG_OFF_EXPANDED() : (z2 && z4) ? MiuiColorBlendToken.INSTANCE.getRINGER_BG_ON_CC() : z2 ? MiuiColorBlendToken.INSTANCE.getRINGER_BG_OFF_CC() : z4 ? MiuiColorBlendToken.INSTANCE.getRINGER_BG_ON() : MiuiColorBlendToken.INSTANCE.getRINGER_BG_OFF();
    }

    public static final int getButtonBgId(boolean z2, boolean z3, boolean z4) {
        return (z2 && z3) ? R.drawable.o3_miui_volume_ringer_btn_first_bg : z2 ? R.drawable.o3_miui_volume_ringer_btn_first_bg_cc : z4 ? R.drawable.o3_miui_volume_ringer_btn_first_bg_blur : R.drawable.o3_miui_volume_ringer_btn_first_bg_collapsed;
    }

    public static final int getButtonRadius(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize((z2 && z3) ? R.dimen.o3_miui_ringer_btn_radius_expended : z2 ? R.dimen.o3_miui_ringer_btn_radius_cc : R.dimen.o3_miui_ringer_btn_radius);
    }

    public static final int getCountDownProgressColor(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return ContextCompat.getColor(context, z2 ? R.color.vp_o3_count_down_progress_dnd : R.color.vp_o3_count_down_progress_ringer);
    }

    public static final int getHeight(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize((z2 && z3) ? R.dimen.o3_miui_ringer_btn_height_expended : z2 ? R.dimen.o3_miui_ringer_btn_height_cc : R.dimen.o3_miui_ringer_btn_height);
    }

    public static final int getIconResId(boolean z2, boolean z3) {
        return (z2 && z3) ? R.drawable.ic_miui_volume_dnd_on_collpased : z2 ? R.drawable.ic_miui_volume_dnd_off_collpased : z3 ? R.drawable.ic_miui_silent_mode_on_collpased : R.drawable.ic_miui_silent_mode_off_collpased;
    }

    public static final int getIconSize(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize((z2 && z3) ? R.dimen.o3_miui_ringer_icon_size_expended : z2 ? R.dimen.o3_miui_ringer_icon_size_cc : R.dimen.o3_miui_ringer_icon_size);
    }

    public static final int getWidth(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize((z2 && z3) ? R.dimen.o3_miui_ringer_btn_width_expended : z2 ? R.dimen.o3_miui_ringer_btn_width_cc : R.dimen.o3_miui_ringer_btn_width);
    }
}
