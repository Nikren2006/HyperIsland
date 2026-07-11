package com.android.systemui.miui.volume;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.android.systemui.miui.volume.widget.VolumeBlurFrameLayout;
import miui.systemui.util.BlurUtils;
import miui.systemui.util.FlipUtils;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.util.ThemeUtils;
import miuix.theme.token.ColorBlendToken;

/* JADX INFO: loaded from: classes2.dex */
public final class VolumeColumnRes {
    public static final VolumeColumnRes INSTANCE = new VolumeColumnRes();

    private VolumeColumnRes() {
    }

    public static final int getHeight(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        return getHeight$default(context, false, false, 6, null);
    }

    public static /* synthetic */ int getHeight$default(Context context, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        if ((i2 & 4) != 0) {
            z3 = false;
        }
        return getHeight(context, z2, z3);
    }

    public static final ColorBlendToken getIconBlendColor(boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        return (z4 || z5) ? (z3 || !z6) ? (z3 && z2) ? MiuiColorBlendToken.INSTANCE.getVC_ICON_EXPANDED() : z2 ? MiuiColorBlendToken.INSTANCE.getVC_ICON_CC() : MiuiColorBlendToken.INSTANCE.getVC_ICON() : MiuiColorBlendToken.INSTANCE.getVC_ICON_CC_MAIN() : MiuiColorBlendToken.INSTANCE.getVC_ICON_DOMINANT_COLOR();
    }

    public static final int getIconMarginBottom(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize((z3 && z2) ? R.dimen.o3_miui_volume_icon_margin_bottom_expended : z3 ? R.dimen.o3_miui_volume_icon_margin_bottom : R.dimen.o3_miui_volume_icon_margin_bottom_cc);
    }

    public static final int getIconSize(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize(z2 ? R.dimen.o3_miui_volume_icon_size : R.dimen.o3_miui_volume_icon_size_cc);
    }

    public static final int getMarginRight(Context context, boolean z2, boolean z3, boolean z4, int i2) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize((!z2 || i2 == (z4 ? 5 : 4)) ? R.dimen.miui_volume_column_margin_horizontal : z3 ? R.dimen.miui_volume_column_margin_horizontal_expanded : z4 ? R.dimen.miui_volume_column_margin_horizontal_expanded_4stream_cc : R.dimen.miui_volume_column_margin_horizontal_expanded_cc);
    }

    public static final ColorBlendToken getProgressViewBlendColor(boolean z2, boolean z3, boolean z4) {
        return (z4 && z2 && z3) ? MiuiColorBlendToken.INSTANCE.getVC_PROGRESS_MUTE_EXPANDED() : (z4 && z2) ? MiuiColorBlendToken.INSTANCE.getVC_PROGRESS_MUTE_CC() : z4 ? MiuiColorBlendToken.INSTANCE.getVC_PROGRESS_MUTE() : (z2 && z3) ? MiuiColorBlendToken.INSTANCE.getVC_PROGRESS_EXPANDED() : z2 ? MiuiColorBlendToken.INSTANCE.getVC_PROGRESS_CC() : MiuiColorBlendToken.INSTANCE.getVC_PROGRESS();
    }

    public static final int getRadius(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        return getRadius$default(context, false, false, 6, null);
    }

    public static /* synthetic */ int getRadius$default(Context context, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        if ((i2 & 4) != 0) {
            z3 = false;
        }
        return getRadius(context, z2, z3);
    }

    public static final int getSliderBackgroundResId(View view, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(view, "view");
        return (BlurUtils.isLowEndDevice() || z2 || !((((VolumeBlurFrameLayout) view).isBlurEnabledAndSupported() || Util.isSupportBlurS()) && ThemeUtils.INSTANCE.getDefaultPluginTheme())) ? (z2 && z3) ? R.drawable.o3_miui_volume_seekbar_backgroud_expand : z2 ? R.drawable.o3_miui_volume_seekbar_backgroud_cc : R.drawable.o3_miui_volume_seekbar_backgroud : R.drawable.o3_miui_volume_seekbar_backgroud_blur;
    }

    public static final ColorBlendToken getSliderBlendColor(boolean z2, boolean z3) {
        return (z2 && z3) ? MiuiColorBlendToken.INSTANCE.getVC_SLIDER_BG_EXPANDED() : z2 ? MiuiColorBlendToken.INSTANCE.getVC_SLIDER_BG_CC() : MiuiColorBlendToken.INSTANCE.getVC_SLIDER_BG();
    }

    public static final ColorStateList getSliderMutedColorList(Context context, boolean z2, boolean z3, Boolean bool) {
        kotlin.jvm.internal.n.g(context, "context");
        Boolean bool2 = Boolean.TRUE;
        if (kotlin.jvm.internal.n.c(bool, bool2) && z2 && z3) {
            return context.getResources().getColorStateList(R.color.miui_volume_disabled_color, null);
        }
        if (kotlin.jvm.internal.n.c(bool, bool2) && z2) {
            return context.getResources().getColorStateList(R.color.miui_volume_disabled_color_cc, null);
        }
        if (kotlin.jvm.internal.n.c(bool, bool2)) {
            return context.getResources().getColorStateList(R.color.miui_volume_disabled_color_collapse, null);
        }
        return null;
    }

    public static /* synthetic */ ColorStateList getSliderMutedColorList$default(Context context, boolean z2, boolean z3, Boolean bool, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        if ((i2 & 4) != 0) {
            z3 = false;
        }
        if ((i2 & 8) != 0) {
            bool = Boolean.FALSE;
        }
        return getSliderMutedColorList(context, z2, z3, bool);
    }

    public static final Drawable getSliderProgressDrawableBlend(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return z2 ? ContextCompat.getDrawable(context, R.drawable.o3_miui_volume_progress_transparent_drawable_expanded) : ContextCompat.getDrawable(context, R.drawable.o3_miui_volume_progress_transparent_drawable);
    }

    public static final int getSuperVolumeTextMarginTo(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return context.getResources().getDimensionPixelSize(z2 ? R.dimen.miui_super_volume_margin_top_cc : R.dimen.miui_super_volume_margin_top);
    }

    public static final int getWidth(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        return getWidth$default(context, false, false, false, 14, null);
    }

    public static /* synthetic */ int getWidth$default(Context context, boolean z2, boolean z3, boolean z4, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        if ((i2 & 4) != 0) {
            z3 = false;
        }
        if ((i2 & 8) != 0) {
            z4 = false;
        }
        return getWidth(context, z2, z3, z4);
    }

    public final int getIconColorRes(boolean z2, boolean z3, boolean z4) {
        return (z4 && z3) ? R.color.miui_volume_expand_button_color_blur_light : (z4 && z2) ? R.color.miui_volume_expand_button_color_blur : (z3 || z2) ? R.color.miui_volume_expand_button_color_cc : R.color.vp_o3_volume_icon_normal;
    }

    public static final int getHeight(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return getHeight$default(context, z2, false, 4, null);
    }

    public static final int getRadius(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return getRadius$default(context, z2, false, 4, null);
    }

    public static final int getWidth(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        return getWidth$default(context, z2, false, false, 12, null);
    }

    public static final int getHeight(Context context, boolean z2, boolean z3) {
        int i2;
        kotlin.jvm.internal.n.g(context, "context");
        Resources resources = context.getResources();
        if (z3 && z2) {
            i2 = R.dimen.o3_miui_volume_expend_height;
        } else if (z3) {
            i2 = R.dimen.o3_miui_volume_cc_height;
        } else {
            i2 = FlipUtils.isFlipTiny() ? R.dimen.flip_tiny_miui_volume_column_height : R.dimen.miui_volume_column_height;
        }
        return resources.getDimensionPixelSize(i2);
    }

    public static final int getRadius(Context context, boolean z2, boolean z3) {
        int i2;
        kotlin.jvm.internal.n.g(context, "context");
        Resources resources = context.getResources();
        if (!z2 && !z3) {
            i2 = R.dimen.o3_miui_cc_volume_radius;
        } else {
            i2 = FlipUtils.isFlipTiny() ? R.dimen.o3_miui_tiny_volume_radius : R.dimen.o3_miui_volume_radius;
        }
        return resources.getDimensionPixelSize(i2);
    }

    public static final int getWidth(Context context, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(context, "context");
        return getWidth$default(context, z2, z3, false, 8, null);
    }

    public static final int getWidth(Context context, boolean z2, boolean z3, boolean z4) {
        int i2;
        kotlin.jvm.internal.n.g(context, "context");
        Resources resources = context.getResources();
        if (z3 && z2 && z4) {
            i2 = R.dimen.o3_miui_volume_notify_width;
        } else if (z3 && z2) {
            i2 = R.dimen.o3_miui_volume_expend_width;
        } else if (z3 && z4) {
            i2 = R.dimen.o3_miui_volume_cc_notify_width;
        } else if (z3) {
            i2 = R.dimen.o3_miui_volume_cc_width;
        } else {
            i2 = FlipUtils.isFlipTiny() ? R.dimen.flip_tiny_miui_volume_column_width : R.dimen.miui_volume_column_width;
        }
        return resources.getDimensionPixelSize(i2);
    }
}
