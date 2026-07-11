package miui.systemui.quicksettings.soundeffect.dirac;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.miui.miplay.audio.data.DeviceInfo;
import com.xiaomi.onetrack.util.a;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import miui.systemui.notification.NotificationUtil;

/* JADX INFO: loaded from: classes4.dex */
public abstract class DiracUtils {
    protected static final int DIRAC_OFF = 0;
    protected static final int DIRAC_ON = 1;
    public static final int HIFI_MODE_OFF = 0;
    public static final int HIFI_MODE_ON = 1;
    public static final int ID_EM001 = 13;
    public static final int ID_EM006 = 16;
    public static final int ID_EM007 = 14;
    public static final int ID_EM013 = 17;
    public static final int ID_EM015 = 18;
    public static final int ID_EM017 = 19;
    public static final int ID_EM018 = 20;
    public static final int ID_EM019 = 21;
    public static final int ID_EM031 = 24;
    public static final int ID_EM033 = 25;
    public static final int ID_EM035 = 26;
    public static final int ID_EM037 = 27;
    public static final int ID_EM303 = 11;
    public static final int ID_EM304 = 12;
    public static final int ID_GENERAL_EARBUDS = 6;
    public static final int ID_GENERAL_INEAR = 7;
    public static final int ID_HM004 = 15;
    public static final int ID_MEP100 = 0;
    public static final int ID_MEP200 = 1;
    public static final int ID_MK101 = 4;
    public static final int ID_MK301 = 5;
    public static final int ID_MK303 = 8;
    public static final int ID_MO701 = 9;
    public static final int ID_MR102 = 10;
    public static final int ID_PISTON_100 = 2;
    private static final String IS_TRANSMIT_SUPPORT = "sound_transmit_support";
    private static final String KEY_LAST_HEADSET_TYPE = "dirac_last_headset_type";
    protected static final String PARAM_KEY_DIRAC = "dirac";
    protected static final String PARAM_KEY_DIRAC_ENABLED = "dirac_enabled";
    private static final String PROPERTY_AUDIO_EFFECT = "persist.audio.soundfx.type";
    private static final String PROPERTY_AUDIO_EFFECT_RO = "ro.audio.soundfx.type";
    public static final String PROPERTY_AUDIO_HIFI = "ro.audio.hifi";
    public static final String PROPERTY_EARCOMPENSATION = "ro.vendor.audio.sfx.earadj";
    private static final String PROPERTY_IS_SUPPORT_TYPEC_EFFECT = "ro.audio.soundfx.usb";
    private static final String PROPERTY_IS_USING_SONG_DIRAC_UTILS = "ro.audio.soundfx.dirac";
    private static final String P_PROPERTY_AUDIO_EFFECT = "ro.vendor.audio.soundfx.type";
    public static final String P_PROPERTY_AUDIO_HIFI = "ro.vendor.audio.hifi";
    private static final String P_PROPERTY_IS_SUPPORT_TYPEC_EFFECT = "ro.vendor.audio.soundfx.usb";
    private static final int P_VERSION_CODE = 28;
    private static final String SUPPORT_TRANSMIT = "sound_transmit_support=true";
    static final String TAG = "DiracUtils";
    private static final String VALUE_AUDIO_EFFECT_DIRAC = "dirac";
    private static final String VALUE_AUDIO_EFFECT_MI = "mi";
    protected static final int VAL_DEFAULT_HEADSET = 5;
    public static final int VAL_EARBUDS = 1;
    public static final int VAL_EM001 = 15;
    public static final int VAL_EM006 = 18;
    public static final int VAL_EM007 = 16;
    public static final int VAL_EM013 = 19;
    public static final int VAL_EM015 = 20;
    public static final int VAL_EM017 = 21;
    public static final int VAL_EM018 = 22;
    public static final int VAL_EM019 = 23;
    public static final int VAL_EM031 = 24;
    public static final int VAL_EM033 = 25;
    public static final int VAL_EM035 = 26;
    public static final int VAL_EM037 = 27;
    public static final int VAL_EM303 = 13;
    public static final int VAL_EM304 = 14;
    public static final int VAL_GENERAL_EARBUDS = 5;
    public static final int VAL_GENERAL_INEAR = 6;
    public static final int VAL_HEADSET_MAX = 27;
    public static final int VAL_HEADSET_MIN = 0;
    public static final int VAL_HM004 = 17;
    public static final int VAL_IN_EAR = 2;
    public static final int VAL_MK101 = 7;
    public static final int VAL_MK301 = 8;
    public static final int VAL_MK303 = 9;
    public static final int VAL_MO701 = 10;
    public static final int VAL_MR102 = 11;
    public static final int VAL_OFF = 0;
    public static final int VAL_PISTON_100 = 3;
    public static final int VAL_PISTON_200 = 4;
    public static boolean sCanInitialize = true;
    private static List<Pair<Integer, Integer>> sHeadsetIdsAndTypes;

    public static class BuildHelper {
        private static final String DEVICE_NAME_GEMINI = "gemini";
        private static final String DEVICE_NAME_ROSY = "rosy";
        private static final String DEVICE_NAME_SAKURA = "sakura";
        private static final String DEVICE_NAME_VINCE = "vince";
        public static boolean IS_ALPHA_BUILD = false;
        public static boolean IS_GEMINI = false;
        public static boolean IS_HONGMI_TWO = false;
        public static boolean IS_HONGMI_TWOS_LTE_MTK = false;
        public static boolean IS_HONGMI_TWO_A = false;
        public static boolean IS_HONGMI_TWO_S = false;
        public static boolean IS_MI2A = false;
        public static boolean IS_ROSY = false;
        public static boolean IS_SAKURA = false;
        public static boolean IS_SONG = false;
        public static boolean IS_VINCE = false;

        static {
            Class<?> cls;
            try {
                cls = Class.forName("miui.os.Build");
            } catch (Exception e2) {
                e2.printStackTrace();
                cls = null;
            }
            if (cls != null) {
                IS_MI2A = getStaticBooleanField(cls, "IS_MI2A", IS_MI2A);
                IS_HONGMI_TWO = getStaticBooleanField(cls, "IS_HONGMI_TWO", IS_HONGMI_TWO);
                IS_HONGMI_TWO_A = getStaticBooleanField(cls, "IS_HONGMI_TWO_A", IS_HONGMI_TWO_A);
                IS_HONGMI_TWO_S = getStaticBooleanField(cls, "IS_HONGMI_TWO_S", IS_HONGMI_TWO_S);
                IS_HONGMI_TWOS_LTE_MTK = getStaticBooleanField(cls, "IS_HONGMI_TWOS_LTE_MTK", IS_HONGMI_TWOS_LTE_MTK);
                String str = Build.DEVICE;
                IS_SONG = "meri".equals(str);
                IS_ALPHA_BUILD = getSystemProp(NotificationUtil.PRODUCT_MODE_DEVICE, "").contains("alpha");
                IS_GEMINI = DEVICE_NAME_GEMINI.equals(str);
                IS_VINCE = DEVICE_NAME_VINCE.equals(str);
                IS_ROSY = DEVICE_NAME_ROSY.equals(str);
                IS_SAKURA = DEVICE_NAME_SAKURA.equals(str);
            }
        }

        private BuildHelper() {
        }

        private static boolean getStaticBooleanField(Class<?> cls, String str, boolean z2) {
            try {
                Field field = cls.getField(str);
                field.setAccessible(true);
                return field.getBoolean(null);
            } catch (Exception e2) {
                Log.e(DiracUtils.TAG, "", e2);
                return z2;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String getSystemProp(String str, String str2) {
            try {
                String str3 = (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, str);
                return !TextUtils.isEmpty(str3) ? str3 : str2;
            } catch (Exception e2) {
                Log.e(DiracUtils.TAG, "", e2);
                return str2;
            }
        }
    }

    public static String formatStd(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    private static int getDiracState(Context context) {
        String parameters = ((AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT)).getParameters("dirac");
        Log.i(TAG, "get parameter " + parameters);
        String value = getValue(parameters);
        if (value != null) {
            try {
                return Integer.valueOf(value).intValue();
            } catch (NumberFormatException e2) {
                Log.e(TAG, "refreshDiracState", e2);
            }
        }
        return 0;
    }

    private static String getSoundPropertyString() {
        return BuildHelper.getSystemProp(P_PROPERTY_AUDIO_EFFECT, "");
    }

    public static String getValue(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOf = str.indexOf("=");
        return iIndexOf >= 0 ? str.substring(iIndexOf + 1) : "";
    }

    public static boolean isA1() {
        return BuildHelper.IS_GEMINI;
    }

    public static boolean isHeadsetType(int i2) {
        return i2 >= 0 && i2 <= 27;
    }

    public static boolean isSupportDirac(Context context) {
        return sCanInitialize;
    }

    public static boolean isSupportEarcompensation() {
        return a.f3424i.equals(BuildHelper.getSystemProp(PROPERTY_EARCOMPENSATION, "false"));
    }

    public static boolean isSupportHiFi() {
        return SystemProperties.getBoolean(PROPERTY_AUDIO_HIFI, false) || SystemProperties.getBoolean(P_PROPERTY_AUDIO_HIFI, false);
    }

    public static boolean isSupportSoundTransmission(Context context) {
        String parameters = ((AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT)).getParameters(IS_TRANSMIT_SUPPORT);
        return parameters != null && parameters.length() >= 1 && SUPPORT_TRANSMIT.equals(parameters);
    }

    public static boolean isSupportTypeC() {
        return isSupportTypeCInternal(P_PROPERTY_IS_SUPPORT_TYPEC_EFFECT);
    }

    private static boolean isSupportTypeCInternal(String str) {
        return a.f3424i.equals(BuildHelper.getSystemProp(str, "false"));
    }

    public static boolean isUsingMiSound() {
        return VALUE_AUDIO_EFFECT_MI.equals(getSoundPropertyString());
    }

    public static boolean isUsingSongDiracUtils() {
        if (BuildHelper.IS_SONG || BuildHelper.IS_VINCE || BuildHelper.IS_ROSY || BuildHelper.IS_SAKURA) {
            return true;
        }
        return a.f3424i.equals(BuildHelper.getSystemProp(PROPERTY_IS_USING_SONG_DIRAC_UTILS, "false"));
    }

    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    public static DiracUtils newInstance() {
        if (isUsingMiSound()) {
            Log.i(TAG, "new MiSoundUtils.");
            return new MiSoundUtils();
        }
        if (BuildHelper.IS_MI2A) {
            Log.i(TAG, "new TaurusDiracUtils.");
            return new TaurusDiracUtils();
        }
        if (!BuildHelper.IS_HONGMI_TWO_A && (BuildHelper.IS_HONGMI_TWO || BuildHelper.IS_HONGMI_TWO_S || BuildHelper.IS_HONGMI_TWOS_LTE_MTK)) {
            Log.i(TAG, "new WtDiracUtils.");
            return new WtDiracUtils();
        }
        if (isUsingSongDiracUtils()) {
            Log.i(TAG, "new SongDiracUtils.");
            return new SongDiracUtils();
        }
        Log.i(TAG, "new PiscesDiracUtils.");
        return new PiscesDiracUtils();
    }

    private static int restoreLastHeadsetType(Context context) {
        String string = Settings.Global.getString(context.getContentResolver(), KEY_LAST_HEADSET_TYPE);
        if (string != null) {
            return Integer.parseInt(string);
        }
        return 5;
    }

    private static void saveLastHeadsetType(Context context, int i2) {
        Settings.Global.putString(context.getContentResolver(), KEY_LAST_HEADSET_TYPE, Integer.toString(i2));
    }

    private static void setDiracState(Context context, int i2) {
        Log.i(TAG, "set dirac state: " + i2);
        if (!isHeadsetType(i2) && i2 != 0) {
            throw new IllegalArgumentException("bad value, value=" + i2);
        }
        AudioManager audioManager = (AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT);
        String parameter = toParameter("dirac", i2);
        Log.i(TAG, "set parameter " + parameter);
        audioManager.setParameters(parameter);
    }

    public static String toParameter(String str, int i2) {
        return formatStd("%s=%d", str, Integer.valueOf(i2));
    }

    public List<Pair<Integer, Integer>> getHeadseIdsAndTypes() {
        if (sHeadsetIdsAndTypes == null) {
            ArrayList arrayListNewArrayList = newArrayList();
            sHeadsetIdsAndTypes = arrayListNewArrayList;
            arrayListNewArrayList.add(new Pair(6, 5));
            sHeadsetIdsAndTypes.add(new Pair<>(0, 1));
            sHeadsetIdsAndTypes.add(new Pair<>(1, 2));
            sHeadsetIdsAndTypes.add(new Pair<>(2, 3));
        }
        return sHeadsetIdsAndTypes;
    }

    public int getHeadsetType(Context context) {
        int diracState = getDiracState(context);
        return isHeadsetType(diracState) ? diracState : restoreLastHeadsetType(context);
    }

    public boolean hasInitialized() {
        return true;
    }

    public void initialize() {
    }

    public boolean isEnabled(Context context) {
        return getDiracState(context) != 0;
    }

    public void release() {
    }

    public void setEnabled(Context context, boolean z2) {
        setDiracState(context, z2 ? getHeadsetType(context) : 0);
    }

    public void setHeadsetType(Context context, int i2) {
        Log.i(TAG, "set headset type: " + i2);
        if (isHeadsetType(i2)) {
            setDiracState(context, i2);
            saveLastHeadsetType(context, i2);
        } else {
            throw new IllegalArgumentException("bad value, value=" + i2);
        }
    }

    public void setHifiMode(int i2) {
    }

    public void setLevel(Context context, int i2, float f2) {
        AudioManager audioManager = (AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT);
        String std = formatStd("diracband=%d;value=%f", Integer.valueOf(i2), Float.valueOf(f2));
        audioManager.setParameters(std);
        Log.i(TAG, "set EQ Level: " + std);
    }
}
