package com.android.systemui.miui.volume;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.IAudioService;
import android.media.RingtoneManager;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.miui.miplay.audio.data.DeviceInfo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import miui.systemui.util.CommonUtils;
import miui.util.HapticFeedbackUtil;
import miuix.animation.FolmeEase;

/* JADX INFO: loaded from: classes2.dex */
public class VolumeUtil {
    public static final String COUNTDOWN_TURN_OFF = "com.android.settings.silentmode.COUNT_DOWN_TURN_OFF";
    public static final String LONG_SCREENSHOT = "com.miui.util.LongScreenshotUtils.LongScreenshot";
    private static final int MIUI_ZEN_MODE_OFF = 0;
    private static final int MIUI_ZEN_MODE_ON = 1;
    private static final String SOUND_RECORDER = "com.android.soundrecorder";
    static final String VOLUME_TAG = "miui_manual";
    private static final Map<String, Boolean> mBlackPkg;
    private static HapticFeedbackUtil mHapticFeedbackUtil;
    private static Context mSystemUIContext;
    private static final boolean IS_SUPPORT_LINEAR_MOTOR_VIBRATE = FolmeEase.LINEAR.equals(SystemProperties.get("sys.haptic.motor"));
    private static IAudioService mAudioService = IAudioService.Stub.asInterface(ServiceManager.getService(DeviceInfo.AUDIO_SUPPORT));

    static {
        HashMap map = new HashMap();
        mBlackPkg = map;
        map.put(SOUND_RECORDER, Boolean.FALSE);
    }

    private VolumeUtil() {
    }

    private static Object InvokeSoundMode(String str, Class[] clsArr, Object[] objArr) {
        try {
            return Class.forName("android.provider.MiuiSettings$SoundMode").getMethod(str, clsArr).invoke(null, objArr);
        } catch (Exception e2) {
            Log.d(VOLUME_TAG, "Invoke error " + str, e2);
            return null;
        }
    }

    public static long caculateSilenceRemainTime(Context context) {
        long silenceRemainTime = getSilenceRemainTime(context) - Calendar.getInstance().getTimeInMillis();
        if (silenceRemainTime > 0) {
            return silenceRemainTime;
        }
        return 0L;
    }

    public static String getMuteAppName() {
        if (mAudioService == null) {
            return "";
        }
        try {
            return (String) IAudioService.class.getDeclaredMethod("getMuteAppName", null).invoke(mAudioService, null);
        } catch (Exception e2) {
            Log.e(VOLUME_TAG, " getMuteAppName ex = " + e2);
            return "";
        }
    }

    public static long getSilenceRemainTime(Context context) {
        return Settings.Secure.getLongForUser(context.getContentResolver(), "remain_time", 0L, -2);
    }

    public static boolean isSilentMode(Context context) {
        Boolean bool = (Boolean) InvokeSoundMode("isSilenceModeOn", new Class[]{Context.class}, new Object[]{context});
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public static synchronized boolean isZenMode(Context context) {
        Boolean bool;
        bool = (Boolean) InvokeSoundMode("isZenModeOn", new Class[]{Context.class}, new Object[]{context});
        return bool == null ? false : bool.booleanValue();
    }

    public static void performHapticFeedback(View view, int i2) {
        if (IS_SUPPORT_LINEAR_MOTOR_VIBRATE) {
            mHapticFeedbackUtil.performHapticFeedback(i2, false);
        } else {
            view.performHapticFeedback(1);
        }
    }

    public static void ringTone(Context context, boolean z2) {
        if (!context.getResources().getBoolean(R.bool.miui_config_enableRingerRelieveSound) || z2) {
            return;
        }
        Util.playRingtoneAsync(context, RingtoneManager.getActualDefaultRingtoneUri(context, 2), 5);
    }

    public static void setBootAudioOn(Context context) {
        try {
            Method declaredMethod = Class.forName("miui.content.res.BootAnimationHelper").getDeclaredMethod("updateBootAudioEnabled", Context.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, context);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
        }
    }

    public static void setSilenceMode(Context context, boolean z2) {
        InvokeSoundMode("setSilenceModeOn", new Class[]{Context.class, Boolean.TYPE}, new Object[]{context, Boolean.valueOf(z2)});
        if (!CommonUtils.IS_KDDI_VERSION) {
            ringTone(context, z2 || isZenMode(context));
        }
        if (z2) {
            return;
        }
        stopCountDownSilence(context);
    }

    public static void setSilenceRemainTime(Context context, long j2) {
        Settings.Secure.putLongForUser(context.getContentResolver(), "remain_time", j2, -2);
    }

    public static void setSystemUICtx(Context context) {
        mSystemUIContext = context;
        mHapticFeedbackUtil = new HapticFeedbackUtil(context, false);
    }

    public static synchronized void setZenMode(Context context, boolean z2) {
        InvokeSoundMode("setZenModeOn", new Class[]{Context.class, Boolean.TYPE, String.class}, new Object[]{context, Boolean.valueOf(z2), VOLUME_TAG});
        if (!CommonUtils.IS_KDDI_VERSION) {
            ringTone(context, z2);
        }
    }

    public static synchronized void setZenModeForDuration(Context context, int i2, int i3) {
        if (i3 != 0) {
            InvokeSoundMode("setZenModeForDuration", new Class[]{Context.class, Integer.TYPE, String.class}, new Object[]{context, Integer.valueOf(i3), VOLUME_TAG});
        } else {
            NotificationManager.from(context).setZenMode(isZenMode(context) ? 1 : 0, null, VOLUME_TAG);
        }
    }

    public static boolean showIslandByPkg() {
        String muteAppName = getMuteAppName();
        Log.d(VOLUME_TAG, " setBootAudioOn = " + muteAppName);
        return !mBlackPkg.containsKey(muteAppName);
    }

    public static void startCountDownSilenceMode(Context context, int i2) {
        startCountDownSilenceMode(context, i2, true);
    }

    public static void stopCountDownSilence(Context context) {
        Context context2 = mSystemUIContext;
        if (context2 == null) {
            Log.d(VOLUME_TAG, " mSystemUIContext is null ");
            return;
        }
        ((AlarmManager) context2.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(mSystemUIContext, 0, new Intent(COUNTDOWN_TURN_OFF), AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL));
        setSilenceRemainTime(context, 0L);
    }

    public static void startCountDownSilenceMode(Context context, int i2, boolean z2) {
        Context context2 = mSystemUIContext;
        if (context2 == null) {
            Log.d(VOLUME_TAG, " mSystemUIContext is null ");
            return;
        }
        AlarmManager alarmManager = (AlarmManager) context2.getSystemService("alarm");
        Intent intent = new Intent(COUNTDOWN_TURN_OFF);
        alarmManager.cancel(PendingIntent.getBroadcast(mSystemUIContext, 0, intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL));
        if (i2 == 0) {
            setSilenceRemainTime(context, 0L);
            return;
        }
        PendingIntent broadcast = PendingIntent.getBroadcast(mSystemUIContext, 0, intent, 201326592);
        long timeInMillis = Calendar.getInstance().getTimeInMillis() + ((long) (i2 * 60000));
        alarmManager.setExact(0, timeInMillis, broadcast);
        if (z2) {
            setSilenceRemainTime(context, timeInMillis);
        }
        setSilenceMode(context, true);
    }
}
