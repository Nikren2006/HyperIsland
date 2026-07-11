package com.android.systemui.miui.volume;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewRootImpl;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.internal.statusbar.IStatusBarService;
import com.miui.blur.sdk.backdrop.BlurManager;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import miui.systemui.util.FoldUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiMathUtils;
import miui.systemui.util.ReflectBuilderUtil;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;
import miuix.theme.token.ColorBlendToken;
import miuix.view.animation.QuadraticEaseOutInterpolator;

/* JADX INFO: loaded from: classes2.dex */
public class Util {
    public static boolean DEBUG = false;
    public static boolean IS_MTK = false;
    public static boolean IS_MUILT_DISPLAY = false;
    public static final boolean IS_NOTCH;
    public static final boolean IS_TABLET;
    private static final String KEY_SELECTTOSPEAK = "com.google.android.marvin.talkback/com.google.android.accessibility.selecttospeak.SelectToSpeakService";
    private static final String KEY_SETTINGS_SUPERSAVE_MODE = "power_supersave_mode_open";
    private static final String KEY_TALKBACK = "com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService";
    public static final String MIUI_VOICE_ASSIST = "com.miui.voiceassist";
    public static final String NOTIFICATION_SINGLE_CONTROL_STATE = "android.settings.NOTIFICATION_SINGLE_CONTROL_STATE";
    private static final String PREF_DND_COUNT_DOWN_TIME = "dnd_last_count_down_time";
    private static final String PREF_SLIENT_COUNT_DOWN_TIME = "slient_last_count_down_time";
    public static final int PRIVATE_FLAG_SHOW_FOR_ALL_USERS = 16;
    public static final int PRIVATE_FLAG_TRUSTED_OVERLAY;
    private static final String SETTINGS_CHANGE_DND_REASON = "MiuiDndSettingsDetailFragment";
    public static final int STATE_DRAG_ANIMATING = 3;
    public static final int STATE_IDLE = 0;
    public static final int STATE_KEY_ANIMATING = 2;
    public static final int STATE_RESTORING = 4;
    private static final String ZEN_NUMBEL = "ZEN_NUMBER";
    public static boolean sIsNotificationSingle;
    private static IStatusBarService sStatusBarService;
    public static final String SILENT_MODE_ACTION = "com.android.settings/com.android.settings.Settings$SoundSettingsActivity";
    public static final Interpolator DECELERATE_QUART = new DecelerateInterpolator(2.0f);
    public static final Interpolator QUART_EASE_OUT = new QuadraticEaseOutInterpolator();
    public static final Interpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();

    static {
        int iIntValue = 0;
        IS_NOTCH = SystemProperties.getInt("ro.miui.notch", 0) == 1;
        IS_MTK = "MTK".equals(SystemProperties.get("Build.BRAND", ""));
        IS_MUILT_DISPLAY = SystemProperties.getInt(FoldUtils.MUILT_DISPLAY_TYPE, 0) == 2;
        DEBUG = Log.isLoggable("volume", 3);
        IS_TABLET = isTablet();
        sStatusBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService(VolumePanelViewController.SERVICE_STATUS_BAR));
        try {
            iIntValue = ((Integer) WindowManager.LayoutParams.class.getField("PRIVATE_FLAG_TRUSTED_OVERLAY").get(null)).intValue();
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            e2.printStackTrace();
        }
        PRIVATE_FLAG_TRUSTED_OVERLAY = iIntValue;
    }

    private Util() {
    }

    public static int blurRadiusOfRatio(float f2, float f3) {
        if (f3 == 0.0f) {
            return 0;
        }
        return (int) MiuiMathUtils.INSTANCE.lerp(0.0f, f2, f3);
    }

    public static void clearMiBgBlur(View view) {
        MiBlurCompat.setMiBackgroundBlurModeCompat(view, 0);
    }

    public static float constrain(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    public static float getCornerRadius(boolean z2, Context context, boolean z3) {
        return (z2 && z3) ? context.getResources().getDimension(R.dimen.miui_volume_blur_bg_radius) : z2 ? context.getResources().getDimension(R.dimen.miui_volume_bg_radius_expanded) : context.getResources().getDimension(R.dimen.miui_volume_bg_radius);
    }

    public static int getLastTotalCountDownTime(Context context, boolean z2) {
        return Settings.System.getIntForUser(context.getContentResolver(), z2 ? PREF_DND_COUNT_DOWN_TIME : PREF_SLIENT_COUNT_DOWN_TIME, 0, ActivityManager.getCurrentUser());
    }

    public static Intent getSilentModeIntent() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(ComponentName.unflattenFromString(SILENT_MODE_ACTION));
        intent.setFlags(335544320);
        return intent;
    }

    public static boolean isBackdropBlurSupported() {
        return BlurManager.c();
    }

    public static boolean isDNDChangedFromSettings(Context context) {
        return SETTINGS_CHANGE_DND_REASON.equals(Settings.Secure.getString(context.getContentResolver(), "REASON"));
    }

    public static boolean isDarkTheme(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static boolean isEnableAccessibility(Context context) {
        String stringForUser = Settings.Secure.getStringForUser(context.getContentResolver(), "enabled_accessibility_services", -2);
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(KEY_SELECTTOSPEAK);
        ComponentName componentNameUnflattenFromString2 = ComponentName.unflattenFromString("com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService");
        if (stringForUser != null && !"".equals(stringForUser)) {
            for (String str : stringForUser.split(MethodCodeHelper.IDENTITY_INFO_SEPARATOR)) {
                ComponentName componentNameUnflattenFromString3 = ComponentName.unflattenFromString(str);
                if (componentNameUnflattenFromString.equals(componentNameUnflattenFromString3) || componentNameUnflattenFromString2.equals(componentNameUnflattenFromString3)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isFirstTimeTurnOnDND(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), ZEN_NUMBEL, 0) == 1;
    }

    public static boolean isLargeDisplay(Context context) {
        return context.getResources().getInteger(R.integer.miui_volume_dialog_large_display_orientation) == 1;
    }

    public static boolean isNotificationSingle(Context context, int i2) {
        boolean z2 = Settings.System.getIntForUser(context.getContentResolver(), NOTIFICATION_SINGLE_CONTROL_STATE, 0, i2) == 1;
        sIsNotificationSingle = z2;
        return z2;
    }

    public static boolean isPowerSuperSaveOpen(Context context) {
        return Settings.System.getInt(context.getContentResolver(), KEY_SETTINGS_SUPERSAVE_MODE, 0) == 1;
    }

    public static boolean isSupportBlurS() {
        return true;
    }

    public static boolean isSupportSuperXiaoai(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(MIUI_VOICE_ASSIST, 128).metaData;
            if (bundle != null) {
                return bundle.getBoolean("is_super_ai");
            }
            return false;
        } catch (Throwable unused) {
            Log.e("Util", "Error getting application info com.miui.voiceassist");
            return false;
        }
    }

    private static boolean isTablet() {
        return "tablet".equals(SystemProperties.get("ro.build.characteristics", "default"));
    }

    public static String logTag(Class<?> cls) {
        String str = "vol." + cls.getSimpleName();
        return str.length() < 23 ? str : str.substring(0, 23);
    }

    @SuppressLint({"ShowToast"})
    private static Toast makeSystemOverlayToast(Context context, String str, int i2) {
        Toast toastMakeText = Toast.makeText(context, str, i2);
        toastMakeText.setType(2024);
        if (toastMakeText.getWindowParams() != null) {
            toastMakeText.getWindowParams().privateFlags |= 16;
        }
        return toastMakeText;
    }

    public static void playRingtoneAsync(Context context, File file) {
        playRingtoneAsync(context, file, -1);
    }

    public static void reparentChildren(ViewGroup viewGroup, ViewGroup viewGroup2) {
        ArrayList<View> arrayList = new ArrayList();
        HashMap map = new HashMap();
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            arrayList.add(childAt);
            map.put(childAt, childAt.getLayoutParams());
        }
        for (View view : arrayList) {
            ((ViewGroup) view.getParent()).removeView(view);
            viewGroup2.addView(view, (ViewGroup.LayoutParams) map.get(view));
        }
    }

    public static void setLastTotalCountDownTime(Context context, int i2, boolean z2) {
        Settings.System.putIntForUser(context.getContentResolver(), z2 ? PREF_DND_COUNT_DOWN_TIME : PREF_SLIENT_COUNT_DOWN_TIME, i2, ActivityManager.getCurrentUser());
    }

    public static void setMiBgBlur(View view, int i2) {
        MiBlurCompat.setPassWindowBlurEnabledCompat(view, true);
        MiBlurCompat.setMiBackgroundBlurModeCompat(view, 1);
        MiBlurCompat.setMiBackgroundBlurRadiusCompat(view, i2);
    }

    public static void setMiViewBlurAndBlendColor(View view, boolean z2, Context context, int i2, int[] iArr, boolean z3) {
        if (i2 == 1) {
            setRoundRect(view, getCornerRadius(z2, context, z3));
        }
        MiBlurCompat.setMiViewBlurModeCompat(view, i2);
        MiBlurCompat.setMiBackgroundBlendColors(view, iArr);
    }

    public static void setRoundRect(View view, final float f2) {
        view.setClipToOutline(true);
        view.setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.systemui.miui.volume.Util.3
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view2, Outline outline) {
                outline.setRoundRect(0, 0, view2.getWidth(), view2.getHeight(), f2);
            }
        });
    }

    public static final void setSizeOrWrap(View view, boolean z2, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!z2) {
            i2 = -2;
        }
        layoutParams.width = i2;
        if (!z2) {
            i3 = -2;
        }
        layoutParams.height = i3;
    }

    public static void setViewBlurForS(View view, Context context, boolean z2, boolean z3) {
        int color = Color.parseColor("#77626262");
        float cornerRadius = getCornerRadius(z2, context, z3);
        try {
            ViewRootImpl viewRootImpl = (ViewRootImpl) ReflectBuilderUtil.callObjectMethod(view, "getViewRootImpl", null, null);
            if (viewRootImpl != null) {
                Drawable drawable = (Drawable) ReflectBuilderUtil.callObjectMethod(viewRootImpl, "createBackgroundBlurDrawable", null, null);
                Class cls = Integer.TYPE;
                ReflectBuilderUtil.callObjectMethod(drawable, "setBlurRadius", new Class[]{cls}, 100);
                Class cls2 = Float.TYPE;
                ReflectBuilderUtil.callObjectMethod(drawable, "setCornerRadius", new Class[]{cls2, cls2, cls2, cls2}, Float.valueOf(cornerRadius), Float.valueOf(cornerRadius), Float.valueOf(cornerRadius), Float.valueOf(cornerRadius));
                ReflectBuilderUtil.callObjectMethod(drawable, "setColor", new Class[]{cls}, Integer.valueOf(color));
                view.setBackground(drawable);
            } else {
                view.setBackgroundResource(R.drawable.miui_volume_seekbar_backgroud);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
        }
    }

    public static final void setVisOrGone(View view, boolean z2) {
        if (view != null) {
            if (view.getVisibility() == (z2 ? 0 : 8)) {
                return;
            }
            view.setVisibility(z2 ? 0 : 8);
        }
    }

    public static final void setVisOrInvis(View view, boolean z2) {
        if (view != null) {
            if (view.getVisibility() == (z2 ? 0 : 4)) {
                return;
            }
            view.setVisibility(z2 ? 0 : 4);
        }
    }

    public static void showPinningEscapeToast() {
        try {
            sStatusBarService.showPinningEscapeToast();
        } catch (RemoteException e2) {
            Log.e("Util", "showPinningEscapeToast fail:" + e2);
        }
    }

    public static Toast showSystemOverlayToast(Context context, int i2, int i3) {
        return showSystemOverlayToast(context, context.getString(i2), i3);
    }

    public static void updateIcon(final ImageView imageView, final int i2) {
        Drawable drawable = imageView.getDrawable();
        if (!(drawable instanceof AnimatedVectorDrawable)) {
            imageView.setImageResource(i2);
            return;
        }
        AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
        animatedVectorDrawable.mutate();
        if (animatedVectorDrawable.isRunning()) {
            animatedVectorDrawable.stop();
            animatedVectorDrawable.reset();
        }
        animatedVectorDrawable.start();
        animatedVectorDrawable.registerAnimationCallback(new Animatable2.AnimationCallback() { // from class: com.android.systemui.miui.volume.Util.2
            @Override // android.graphics.drawable.Animatable2.AnimationCallback
            public void onAnimationEnd(Drawable drawable2) {
                imageView.setImageResource(i2);
            }
        });
    }

    public static int constrain(int i2, int i3, int i4) {
        return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
    }

    public static void playRingtoneAsync(Context context, File file, int i2) {
        playRingtoneAsync(context, Uri.fromFile(file), i2);
    }

    public static Toast showSystemOverlayToast(Context context, String str, int i2) {
        Toast toastMakeSystemOverlayToast = makeSystemOverlayToast(context, str, i2);
        toastMakeSystemOverlayToast.show();
        return toastMakeSystemOverlayToast;
    }

    public static long constrain(long j2, long j3, long j4) {
        return j2 < j3 ? j3 : j2 > j4 ? j4 : j2;
    }

    public static void playRingtoneAsync(Context context, Uri uri) {
        playRingtoneAsync(context, uri, -1);
    }

    public static final void setVisOrInvis(Drawable drawable, boolean z2) {
        if (drawable == null) {
            return;
        }
        drawable.setAlpha(z2 ? 255 : 0);
    }

    public static void playRingtoneAsync(final Context context, final Uri uri, final int i2) {
        AsyncTask.execute(new Runnable() { // from class: com.android.systemui.miui.volume.Util.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Ringtone ringtone = RingtoneManager.getRingtone(context, uri);
                    if (ringtone != null) {
                        int i3 = i2;
                        if (i3 >= 0) {
                            ringtone.setStreamType(i3);
                        }
                        ringtone.play();
                    }
                } catch (Exception e2) {
                    Log.e("Util", "error playing ringtone " + uri, e2);
                }
            }
        });
    }

    public static void setMiViewBlurAndBlendColor(View view, int i2, int[] iArr) {
        MiBlurCompat.setMiViewBlurModeCompat(view, i2);
        MiBlurCompat.setMiBackgroundBlendColors(view, iArr);
    }

    public static void setMiViewBlurAndBlendColor(View view, int i2, ColorBlendToken colorBlendToken) {
        MiBlurCompat.setMiViewBlurModeCompat(view, i2);
        MiBlurCompat.setMiBackgroundBlendColors(view, colorBlendToken);
    }

    public static void setViewBlurForS(View view, int i2) {
        int color = Color.parseColor("#77626262");
        try {
            ViewRootImpl viewRootImpl = (ViewRootImpl) ReflectBuilderUtil.callObjectMethod(view, "getViewRootImpl", null, null);
            if (viewRootImpl != null) {
                Drawable drawable = (Drawable) ReflectBuilderUtil.callObjectMethod(viewRootImpl, "createBackgroundBlurDrawable", null, null);
                Class cls = Integer.TYPE;
                ReflectBuilderUtil.callObjectMethod(drawable, "setBlurRadius", new Class[]{cls}, 100);
                Class cls2 = Float.TYPE;
                ReflectBuilderUtil.callObjectMethod(drawable, "setCornerRadius", new Class[]{cls2, cls2, cls2, cls2}, Integer.valueOf(i2), Integer.valueOf(i2), Integer.valueOf(i2), Integer.valueOf(i2));
                ReflectBuilderUtil.callObjectMethod(drawable, "setColor", new Class[]{cls}, Integer.valueOf(color));
                view.setBackground(drawable);
            } else {
                view.setBackgroundResource(R.drawable.miui_volume_seekbar_backgroud);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
        }
    }
}
