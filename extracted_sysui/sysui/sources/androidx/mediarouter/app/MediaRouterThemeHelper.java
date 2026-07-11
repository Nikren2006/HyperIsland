package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.mediarouter.R;

/* JADX INFO: loaded from: classes.dex */
final class MediaRouterThemeHelper {
    static final int COLOR_DARK_ON_LIGHT_BACKGROUND = -570425344;
    private static final int COLOR_DARK_ON_LIGHT_BACKGROUND_RES_ID = R.color.mr_dynamic_dialog_icon_light;
    static final int COLOR_WHITE_ON_DARK_BACKGROUND = -1;
    private static final float MIN_CONTRAST = 3.0f;

    private MediaRouterThemeHelper() {
    }

    public static Context createThemedButtonContext(Context context) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, getRouterThemeId(context));
        int themeResource = getThemeResource(contextThemeWrapper, R.attr.mediaRouteTheme);
        return themeResource != 0 ? new ContextThemeWrapper(contextThemeWrapper, themeResource) : contextThemeWrapper;
    }

    public static Context createThemedDialogContext(Context context, int i2, boolean z2) {
        if (i2 == 0) {
            i2 = getThemeResource(context, !z2 ? androidx.appcompat.R.attr.dialogTheme : androidx.appcompat.R.attr.alertDialogTheme);
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i2);
        return getThemeResource(contextThemeWrapper, R.attr.mediaRouteTheme) != 0 ? new ContextThemeWrapper(contextThemeWrapper, getRouterThemeId(contextThemeWrapper)) : contextThemeWrapper;
    }

    public static int createThemedDialogStyle(Context context) {
        int themeResource = getThemeResource(context, R.attr.mediaRouteTheme);
        return themeResource == 0 ? getRouterThemeId(context) : themeResource;
    }

    public static int getButtonTextColor(Context context) {
        int themeColor = getThemeColor(context, 0, androidx.appcompat.R.attr.colorPrimary);
        return ColorUtils.calculateContrast(themeColor, getThemeColor(context, 0, android.R.attr.colorBackground)) < 3.0d ? getThemeColor(context, 0, androidx.appcompat.R.attr.colorAccent) : themeColor;
    }

    public static Drawable getCheckBoxDrawableIcon(Context context) {
        return getIconByDrawableId(context, R.drawable.mr_cast_checkbox);
    }

    public static int getControllerColor(Context context, int i2) {
        if (ColorUtils.calculateContrast(-1, getThemeColor(context, i2, androidx.appcompat.R.attr.colorPrimary)) >= 3.0d) {
            return -1;
        }
        return COLOR_DARK_ON_LIGHT_BACKGROUND;
    }

    public static Drawable getDefaultDrawableIcon(Context context) {
        return getIconByAttrId(context, R.attr.mediaRouteDefaultIconDrawable);
    }

    public static float getDisabledAlpha(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.disabledAlpha, typedValue, true)) {
            return typedValue.getFloat();
        }
        return 0.5f;
    }

    private static Drawable getIconByAttrId(Context context, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i2});
        Drawable drawableWrap = DrawableCompat.wrap(AppCompatResources.getDrawable(context, typedArrayObtainStyledAttributes.getResourceId(0, 0)));
        if (isLightTheme(context)) {
            DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(context, COLOR_DARK_ON_LIGHT_BACKGROUND_RES_ID));
        }
        typedArrayObtainStyledAttributes.recycle();
        return drawableWrap;
    }

    private static Drawable getIconByDrawableId(Context context, int i2) {
        Drawable drawableWrap = DrawableCompat.wrap(AppCompatResources.getDrawable(context, i2));
        if (isLightTheme(context)) {
            DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(context, COLOR_DARK_ON_LIGHT_BACKGROUND_RES_ID));
        }
        return drawableWrap;
    }

    public static Drawable getMuteButtonDrawableIcon(Context context) {
        return getIconByDrawableId(context, R.drawable.mr_cast_mute_button);
    }

    private static int getRouterThemeId(Context context) {
        return isLightTheme(context) ? getControllerColor(context, 0) == COLOR_DARK_ON_LIGHT_BACKGROUND ? R.style.Theme_MediaRouter_Light : R.style.Theme_MediaRouter_Light_DarkControlPanel : getControllerColor(context, 0) == COLOR_DARK_ON_LIGHT_BACKGROUND ? R.style.Theme_MediaRouter_LightControlPanel : R.style.Theme_MediaRouter;
    }

    public static Drawable getSpeakerDrawableIcon(Context context) {
        return getIconByAttrId(context, R.attr.mediaRouteSpeakerIconDrawable);
    }

    public static Drawable getSpeakerGroupDrawableIcon(Context context) {
        return getIconByAttrId(context, R.attr.mediaRouteSpeakerGroupIconDrawable);
    }

    public static TypedArray getStyledAttributes(Context context) {
        return context.obtainStyledAttributes(new int[]{R.attr.mediaRouteDefaultIconDrawable, R.attr.mediaRouteTvIconDrawable, R.attr.mediaRouteSpeakerIconDrawable, R.attr.mediaRouteSpeakerGroupIconDrawable});
    }

    private static int getThemeColor(Context context, int i2, int i3) {
        if (i2 != 0) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i2, new int[]{i3});
            int color = typedArrayObtainStyledAttributes.getColor(0, 0);
            typedArrayObtainStyledAttributes.recycle();
            if (color != 0) {
                return color;
            }
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i3, typedValue, true);
        return typedValue.resourceId != 0 ? context.getResources().getColor(typedValue.resourceId) : typedValue.data;
    }

    public static int getThemeResource(Context context, int i2) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return typedValue.resourceId;
        }
        return 0;
    }

    public static Drawable getTvDrawableIcon(Context context) {
        return getIconByAttrId(context, R.attr.mediaRouteTvIconDrawable);
    }

    private static boolean isLightTheme(Context context) {
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(androidx.appcompat.R.attr.isLightTheme, typedValue, true) && typedValue.data != 0;
    }

    public static void setDialogBackgroundColor(Context context, Dialog dialog) {
        dialog.getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(context, isLightTheme(context) ? R.color.mr_dynamic_dialog_background_light : R.color.mr_dynamic_dialog_background_dark));
    }

    public static void setIndeterminateProgressBarColor(Context context, ProgressBar progressBar) {
        if (progressBar.isIndeterminate()) {
            progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(context, isLightTheme(context) ? R.color.mr_cast_progressbar_progress_and_thumb_light : R.color.mr_cast_progressbar_progress_and_thumb_dark), PorterDuff.Mode.SRC_IN);
        }
    }

    public static void setMediaControlsBackgroundColor(Context context, View view, View view2, boolean z2) {
        int themeColor = getThemeColor(context, 0, androidx.appcompat.R.attr.colorPrimary);
        int themeColor2 = getThemeColor(context, 0, androidx.appcompat.R.attr.colorPrimaryDark);
        if (z2 && getControllerColor(context, 0) == COLOR_DARK_ON_LIGHT_BACKGROUND) {
            themeColor2 = themeColor;
            themeColor = -1;
        }
        view.setBackgroundColor(themeColor);
        view2.setBackgroundColor(themeColor2);
        view.setTag(Integer.valueOf(themeColor));
        view2.setTag(Integer.valueOf(themeColor2));
    }

    public static void setVolumeSliderColor(Context context, MediaRouteVolumeSlider mediaRouteVolumeSlider, View view) {
        int controllerColor = getControllerColor(context, 0);
        if (Color.alpha(controllerColor) != 255) {
            controllerColor = ColorUtils.compositeColors(controllerColor, ((Integer) view.getTag()).intValue());
        }
        mediaRouteVolumeSlider.setColor(controllerColor);
    }

    public static void setVolumeSliderColor(Context context, MediaRouteVolumeSlider mediaRouteVolumeSlider) {
        int color;
        int color2;
        if (isLightTheme(context)) {
            color = ContextCompat.getColor(context, R.color.mr_cast_progressbar_progress_and_thumb_light);
            color2 = ContextCompat.getColor(context, R.color.mr_cast_progressbar_background_light);
        } else {
            color = ContextCompat.getColor(context, R.color.mr_cast_progressbar_progress_and_thumb_dark);
            color2 = ContextCompat.getColor(context, R.color.mr_cast_progressbar_background_dark);
        }
        mediaRouteVolumeSlider.setColor(color, color2);
    }
}
