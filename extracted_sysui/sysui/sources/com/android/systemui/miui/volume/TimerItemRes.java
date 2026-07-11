package com.android.systemui.miui.volume;

import android.content.Context;
import miui.systemui.util.MiuiColorBlendToken;
import miuix.theme.token.ColorBlendToken;

/* JADX INFO: loaded from: classes2.dex */
public final class TimerItemRes {
    public static final TimerItemRes INSTANCE = new TimerItemRes();

    private TimerItemRes() {
    }

    public static final int getHeight(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize(z2 ? R.dimen.miui_volume_timer_seekbar_height : R.dimen.miui_volume_timer_seekbar_height_cc);
    }

    public static final int getMarginLeft(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize(z2 ? R.dimen.miui_volume_timer_margin_left : R.dimen.miui_volume_timer_margin_left_cc);
    }

    public static final ColorBlendToken getProgressBlendColor(boolean z2) {
        return z2 ? MiuiColorBlendToken.INSTANCE.getTIMER_PROGRESS_EXPANDED() : MiuiColorBlendToken.INSTANCE.getTIMER_PROGRESS_CC();
    }

    public static final int getRadius(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize(z2 ? R.dimen.miui_volume_bg_radius : R.dimen.miui_volume_timer_corner_radius);
    }

    public static final int getTimerBgRes(boolean z2) {
        return z2 ? R.drawable.o3_miui_volume_timer_bg : R.drawable.o3_miui_volume_timer_bg_cc;
    }

    public static final ColorBlendToken getTimerBlendColor(boolean z2) {
        return z2 ? MiuiColorBlendToken.INSTANCE.getTIMER_BG_EXPANDED() : MiuiColorBlendToken.INSTANCE.getTIMER_BG_CC();
    }

    public static final int getWidth(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize((z2 && z3) ? R.dimen.miui_volume_timer_seekbar_width_4stream : z2 ? R.dimen.miui_volume_timer_seekbar_width : R.dimen.miui_volume_timer_seekbar_width_cc);
    }
}
