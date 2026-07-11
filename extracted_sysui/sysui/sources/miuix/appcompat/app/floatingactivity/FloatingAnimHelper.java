package miuix.appcompat.app.floatingactivity;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import miuix.appcompat.R;
import miuix.appcompat.app.AppCompatActivity;
import miuix.autodensity.IDensity;
import miuix.core.util.WindowUtils;

/* JADX INFO: loaded from: classes2.dex */
public class FloatingAnimHelper {
    private static final String TAG = "FloatingAnimHelper";
    private static boolean sTransWithClipAnimSupported = false;

    static {
        try {
            Class.forName("android.view.animation.TranslateWithClipAnimation");
            sTransWithClipAnimSupported = true;
        } catch (ClassNotFoundException e2) {
            Log.w(TAG, "Failed to get isSupportTransWithClipAnim attributes", e2);
        }
    }

    public static void clearFloatingWindowAnim(@NonNull AppCompatActivity appCompatActivity) {
    }

    public static void execFloatingWindowEnterAnimRomNormal(@NonNull AppCompatActivity appCompatActivity) {
        appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_enter_anim_normal_rom_enter, R.anim.miuix_appcompat_floating_window_enter_anim_normal_rom_exit);
    }

    public static void execFloatingWindowExitAnimRomNormal(@NonNull AppCompatActivity appCompatActivity) {
        appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_exit_anim_normal_rom_enter, R.anim.miuix_appcompat_floating_window_exit_anim_normal_rom_exit);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean getAutoDensitySupportStatus(AppCompatActivity appCompatActivity) {
        if (appCompatActivity instanceof IDensity) {
            return ((IDensity) appCompatActivity).shouldAdaptAutoDensity();
        }
        if (appCompatActivity.getApplication() instanceof IDensity) {
            return ((IDensity) appCompatActivity.getApplication()).shouldAdaptAutoDensity();
        }
        return false;
    }

    private static boolean isPortrait(@NonNull Context context) {
        return WindowUtils.isPortrait(context);
    }

    public static boolean isSupportTransWithClipAnim() {
        return sTransWithClipAnimSupported;
    }

    public static void markedPageIndex(@NonNull AppCompatActivity appCompatActivity, int i2) {
        appCompatActivity.getWindow().getDecorView().setTag(R.id.miuix_appcompat_floating_window_index, Integer.valueOf(i2));
    }

    public static int obtainPageIndex(@NonNull AppCompatActivity appCompatActivity) {
        Object tag = appCompatActivity.getWindow().getDecorView().getTag(R.id.miuix_appcompat_floating_window_index);
        if (tag instanceof Integer) {
            return ((Integer) tag).intValue();
        }
        return -1;
    }

    public static void preformFloatingEnterAnimWithClip(@NonNull AppCompatActivity appCompatActivity, boolean z2) {
        if (sTransWithClipAnimSupported) {
            if (!z2) {
                appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_anim_in_full_screen, R.anim.miuix_appcompat_floating_window_anim_out_full_screen);
                return;
            }
            if (getAutoDensitySupportStatus(appCompatActivity)) {
                if (isPortrait(appCompatActivity)) {
                    appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_enter_anim_auto_dpi, R.anim.miuix_appcompat_floating_window_exit_anim_auto_dpi);
                    return;
                } else {
                    appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_enter_anim_auto_dpi_land, R.anim.miuix_appcompat_floating_window_exit_anim_auto_dpi_land);
                    return;
                }
            }
            if (isPortrait(appCompatActivity)) {
                appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_enter_anim, R.anim.miuix_appcompat_floating_window_exit_anim);
            } else {
                appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_enter_anim_land, R.anim.miuix_appcompat_floating_window_exit_anim_land);
            }
        }
    }

    public static void preformFloatingExitAnimWithClip(@NonNull AppCompatActivity appCompatActivity, boolean z2) {
        if (sTransWithClipAnimSupported) {
            if (!z2) {
                appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_anim_in_full_screen, R.anim.miuix_appcompat_floating_window_anim_out_full_screen);
                return;
            }
            if (getAutoDensitySupportStatus(appCompatActivity)) {
                if (isPortrait(appCompatActivity)) {
                    appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_enter_anim_auto_dpi, R.anim.miuix_appcompat_floating_window_exit_anim_auto_dpi);
                    return;
                } else {
                    appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_enter_anim_auto_dpi_land, R.anim.miuix_appcompat_floating_window_exit_anim_auto_dpi_land);
                    return;
                }
            }
            if (isPortrait(appCompatActivity)) {
                appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_enter_anim, R.anim.miuix_appcompat_floating_window_exit_anim);
            } else {
                appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_enter_anim_land, R.anim.miuix_appcompat_floating_window_exit_anim_land);
            }
        }
    }

    public static void singleAppFloatingActivityEnter(@NonNull AppCompatActivity appCompatActivity) {
        if (sTransWithClipAnimSupported) {
            preformFloatingExitAnimWithClip(appCompatActivity, appCompatActivity.isInFloatingWindowMode());
        } else {
            appCompatActivity.executeOpenEnterAnimation();
        }
    }

    public static void singleAppFloatingActivityExit(@NonNull AppCompatActivity appCompatActivity) {
        if (sTransWithClipAnimSupported) {
            if (!appCompatActivity.isInFloatingWindowMode()) {
                appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_anim_in_full_screen, R.anim.miuix_appcompat_floating_window_anim_out_full_screen);
                return;
            }
            if (getAutoDensitySupportStatus(appCompatActivity)) {
                if (isPortrait(appCompatActivity)) {
                    appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_enter_anim_auto_dpi, R.anim.miuix_appcompat_floating_window_exit_anim_auto_dpi);
                    return;
                } else {
                    appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_enter_anim_auto_dpi_land, R.anim.miuix_appcompat_floating_window_exit_anim_auto_dpi_land);
                    return;
                }
            }
            if (isPortrait(appCompatActivity)) {
                appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_enter_anim, R.anim.miuix_appcompat_floating_window_exit_anim);
            } else {
                appCompatActivity.overridePendingTransition(R.anim.miuix_appcompat_floating_window_enter_anim_land, R.anim.miuix_appcompat_floating_window_exit_anim_land);
            }
        }
    }
}
