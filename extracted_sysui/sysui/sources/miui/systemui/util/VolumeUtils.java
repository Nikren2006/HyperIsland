package miui.systemui.util;

import android.content.Context;
import android.media.AudioManager;
import android.os.SystemProperties;
import android.telecom.TelecomManager;
import android.util.Log;
import com.miui.miplay.audio.data.DeviceInfo;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import miui.systemui.quicksettings.common.R;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class VolumeUtils {
    private static final String ACTION_CALL_VOLUME_BOOST;
    private static final boolean FEATURE_DYNAMIC_ISLAND;
    public static final VolumeUtils INSTANCE = new VolumeUtils();
    private static final boolean IS_N17;
    private static final int MEDIA_VOL_STEPLESS;
    private static final int MEDIA_VOL_STEPS;
    public static final boolean MEDIA_VOL_STEPS_IS_15;
    private static final int MIUI_BASIC_MEDIA_VOL_STEPS = 15;
    public static final int SEEKBAR_PROGRESS_MULTIPLE = 1000;
    private static final int SUPER_VOLUME_INDEX_ADD;
    private static final String SUPER_VOLUME_PERCENT_JSON;
    private static final Map<Integer, Integer> SUPER_VOLUME_PERCENT_MAP;
    public static final int SUPER_VOLUME_STEP_TRANSFORM;
    private static final int SUPER_VOLUME_STREAMTYPE;
    private static final boolean SUPER_VOLUME_SUPPORTED;
    private static final String SUPER_VOLUME_VERSION;
    private static final boolean SUPER_VOLUME_VERSION_P;
    private static final boolean SUPER_VOLUME_VOICE_CALL_SUPPORTED;
    private static final String TAG = "VolumeUtils";
    private static final int base = 1;
    private static final int indexAdd;
    private static boolean isInitial;
    private static final Boolean[] supportSuperVolumeStreamType;

    static {
        int i2;
        int i3 = SystemProperties.getInt("ro.vendor.audio.volume_super_index_add", 0);
        SUPER_VOLUME_INDEX_ADD = i3;
        SUPER_VOLUME_SUPPORTED = i3 != 0;
        String str = SystemProperties.get("ro.vendor.audio.volume_super_version", "");
        kotlin.jvm.internal.n.f(str, "get(...)");
        SUPER_VOLUME_VERSION = str;
        SUPER_VOLUME_VERSION_P = kotlin.jvm.internal.n.c(str, "type_p_1");
        int i4 = SystemProperties.getInt("ro.config.media_vol_steps", 15);
        MEDIA_VOL_STEPS = i4;
        int i5 = SystemProperties.getInt("ro.vendor.audio.media_vol_stepless", 15);
        MEDIA_VOL_STEPLESS = i5;
        indexAdd = i3 / 10;
        MEDIA_VOL_STEPS_IS_15 = i4 == 15 && i5 == 15;
        if (i4 % 15 != 0 || i5 % 15 != 0) {
            Log.e(TAG, "set SUPER_VOLUME_STEP failed: " + i4 + " " + i5);
            i2 = 1;
        } else if (i5 / 15 > 1) {
            Log.i(TAG, "set SUPER_VOLUME_STEP MEDIA_VOL_STEPLESS: " + i5);
            i2 = i5 / 15;
        } else {
            Log.i(TAG, "set SUPER_VOLUME_STEP MEDIA_VOL_STEPS: " + i4);
            i2 = i4 / 15;
        }
        SUPER_VOLUME_STEP_TRANSFORM = i2;
        isInitial = true;
        FEATURE_DYNAMIC_ISLAND = SystemProperties.getBoolean("persist.sys.feature.island", true);
        String str2 = SystemProperties.get("ro.vendor.audio.volume_super_percent_extensible", "{}");
        kotlin.jvm.internal.n.f(str2, "get(...)");
        SUPER_VOLUME_PERCENT_JSON = str2;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            Log.d(TAG, "init SUPER_VOLUME_PERCENT_MAP from json " + str2);
            JSONObject jSONObject = new JSONObject(str2);
            Iterator<String> itKeys = jSONObject.keys();
            kotlin.jvm.internal.n.f(itKeys, "keys(...)");
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                kotlin.jvm.internal.n.d(next);
                linkedHashMap.put(Integer.valueOf(Integer.parseInt(next)), Integer.valueOf(jSONObject.getInt(next)));
            }
        } catch (JSONException e2) {
            Log.e(TAG, "parse super_volume_percent json fail ", e2);
        }
        SUPER_VOLUME_PERCENT_MAP = linkedHashMap;
        int i6 = SystemProperties.getInt("ro.vendor.audio.volume_super_streamtype", 0);
        SUPER_VOLUME_STREAMTYPE = i6;
        supportSuperVolumeStreamType = new Boolean[]{Boolean.valueOf((i6 & 1) != 0), Boolean.valueOf((i6 & 2) != 0), Boolean.valueOf((i6 & 4) != 0), Boolean.valueOf((i6 & 8) != 0), Boolean.valueOf((i6 & 16) != 0), Boolean.valueOf((i6 & 32) != 0), Boolean.valueOf((i6 & 64) != 0), Boolean.valueOf((i6 & 128) != 0), Boolean.valueOf((i6 & 256) != 0), Boolean.valueOf((i6 & 512) != 0), Boolean.valueOf((i6 & 1024) != 0), Boolean.valueOf((i6 & 2048) != 0)};
        IS_N17 = SystemProperties.get("ro.product.device", " ").equals("gold");
        SUPER_VOLUME_VOICE_CALL_SUPPORTED = SystemProperties.getInt("ro.vendor.audio.volume.boost.support", 0) != 0;
        ACTION_CALL_VOLUME_BOOST = "miui.intent.action.CALL_VOLUME_BOOST_ON";
    }

    private VolumeUtils() {
    }

    public static final boolean deviceIsEarpiece(int i2) {
        return (i2 & 1) != 0;
    }

    public static final boolean deviceIsHeadset(int i2) {
        return ((i2 & 4) == 0 && (i2 & 8) == 0 && (131072 & i2) == 0 && (67108864 & i2) == 0 && (i2 & 16) == 0 && (i2 & 32) == 0 && (i2 & 128) == 0 && (i2 & 256) == 0 && (536870912 & i2) == 0 && (i2 & 16384) == 0 && i2 != 536870914) ? false : true;
    }

    public static final boolean deviceIsSpeaker(int i2) {
        return (i2 & 2) != 0;
    }

    public static final String getACTION_CALL_VOLUME_BOOST() {
        return ACTION_CALL_VOLUME_BOOST;
    }

    public static /* synthetic */ void getACTION_CALL_VOLUME_BOOST$annotations() {
    }

    public static final boolean getFEATURE_DYNAMIC_ISLAND() {
        return FEATURE_DYNAMIC_ISLAND;
    }

    public static /* synthetic */ void getFEATURE_DYNAMIC_ISLAND$annotations() {
    }

    public static final boolean getIS_N17() {
        return IS_N17;
    }

    public static /* synthetic */ void getIS_N17$annotations() {
    }

    public static final int getIndexAdd() {
        return indexAdd;
    }

    public static /* synthetic */ void getIndexAdd$annotations() {
    }

    public static final Integer[] getInitialMaxVolume(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        Object systemService = context.getSystemService(DeviceInfo.AUDIO_SUPPORT);
        kotlin.jvm.internal.n.e(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        AudioManager audioManager = (AudioManager) systemService;
        int streamMaxVolume = audioManager.getStreamMaxVolume(0);
        int i2 = indexAdd;
        return new Integer[]{Integer.valueOf(streamMaxVolume - i2), Integer.valueOf(audioManager.getStreamMaxVolume(1) - i2), Integer.valueOf(audioManager.getStreamMaxVolume(2) - i2), Integer.valueOf(MEDIA_VOL_STEPS_IS_15 ? audioManager.getStreamMaxVolume(3) - i2 : audioManager.getStreamMaxVolume(3) - (SUPER_VOLUME_STEP_TRANSFORM * i2)), Integer.valueOf(audioManager.getStreamMaxVolume(4) - i2), Integer.valueOf(audioManager.getStreamMaxVolume(5) - i2), Integer.valueOf(audioManager.getStreamMaxVolume(6) - i2), Integer.valueOf(audioManager.getStreamMaxVolume(7) - i2), Integer.valueOf(audioManager.getStreamMaxVolume(8) - i2), Integer.valueOf(audioManager.getStreamMaxVolume(9) - i2), Integer.valueOf(audioManager.getStreamMaxVolume(10) - i2), Integer.valueOf(audioManager.getStreamMaxVolume(11) - i2)};
    }

    public static final int getMEDIA_VOL_STEPS() {
        return MEDIA_VOL_STEPS;
    }

    public static /* synthetic */ void getMEDIA_VOL_STEPS$annotations() {
    }

    public static final int getSUPER_VOLUME_INDEX_ADD() {
        return SUPER_VOLUME_INDEX_ADD;
    }

    public static /* synthetic */ void getSUPER_VOLUME_INDEX_ADD$annotations() {
    }

    public static final int getSUPER_VOLUME_PERCENT() {
        return SystemProperties.getInt("ro.vendor.audio.volume_super_percent", 200);
    }

    public static /* synthetic */ void getSUPER_VOLUME_PERCENT$annotations() {
    }

    public static final String getSUPER_VOLUME_PERCENT_JSON() {
        return SUPER_VOLUME_PERCENT_JSON;
    }

    public static /* synthetic */ void getSUPER_VOLUME_PERCENT_JSON$annotations() {
    }

    public static final Map<Integer, Integer> getSUPER_VOLUME_PERCENT_MAP() {
        return SUPER_VOLUME_PERCENT_MAP;
    }

    public static /* synthetic */ void getSUPER_VOLUME_PERCENT_MAP$annotations() {
    }

    public static final int getSUPER_VOLUME_STREAMTYPE() {
        return SUPER_VOLUME_STREAMTYPE;
    }

    public static /* synthetic */ void getSUPER_VOLUME_STREAMTYPE$annotations() {
    }

    public static final boolean getSUPER_VOLUME_SUPPORTED() {
        return SUPER_VOLUME_SUPPORTED;
    }

    public static /* synthetic */ void getSUPER_VOLUME_SUPPORTED$annotations() {
    }

    public static final String getSUPER_VOLUME_VERSION() {
        return SUPER_VOLUME_VERSION;
    }

    public static /* synthetic */ void getSUPER_VOLUME_VERSION$annotations() {
    }

    public static final boolean getSUPER_VOLUME_VERSION_P() {
        return SUPER_VOLUME_VERSION_P;
    }

    public static /* synthetic */ void getSUPER_VOLUME_VERSION_P$annotations() {
    }

    public static final boolean getSUPER_VOLUME_VOICE_CALL_SUPPORTED() {
        return SUPER_VOLUME_VOICE_CALL_SUPPORTED;
    }

    public static /* synthetic */ void getSUPER_VOLUME_VOICE_CALL_SUPPORTED$annotations() {
    }

    public static final String getSuperVolumePercent(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        return getSuperVolumePercent(context, indexAdd);
    }

    public static final Boolean[] getSupportSuperVolumeStreamType() {
        return supportSuperVolumeStreamType;
    }

    public static /* synthetic */ void getSupportSuperVolumeStreamType$annotations() {
    }

    public static final boolean isInitial() {
        return isInitial;
    }

    public static /* synthetic */ void isInitial$annotations() {
    }

    public static final boolean isPhoneCall(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        Object systemService = context.getSystemService("telecom");
        kotlin.jvm.internal.n.e(systemService, "null cannot be cast to non-null type android.telecom.TelecomManager");
        return ((TelecomManager) systemService).isInCall();
    }

    public static final int progressToLevel(int i2, int i3) {
        int i4 = i2 / 1000;
        int i5 = i4 - 1;
        if (i3 == 0) {
            return 0;
        }
        return i3 == i2 ? i4 : ((int) ((i3 / i2) * i5)) + 1;
    }

    public static final void setInitial(boolean z2) {
        isInitial = z2;
    }

    public static final int updateMaxVolume(Context context, int i2) {
        int streamMaxVolume;
        int i3;
        kotlin.jvm.internal.n.g(context, "context");
        Object systemService = context.getSystemService(DeviceInfo.AUDIO_SUPPORT);
        kotlin.jvm.internal.n.e(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        AudioManager audioManager = (AudioManager) systemService;
        if (i2 != 3 || MEDIA_VOL_STEPS_IS_15) {
            streamMaxVolume = audioManager.getStreamMaxVolume(i2);
            i3 = indexAdd;
        } else {
            streamMaxVolume = audioManager.getStreamMaxVolume(i2);
            i3 = indexAdd * SUPER_VOLUME_STEP_TRANSFORM;
        }
        return streamMaxVolume - i3;
    }

    public static final boolean voiceSupportSuperVolume() {
        return SystemProperties.getInt("ro.vendor.audio.voice_volume_super_index_add", 0) == 10;
    }

    public static final String getSuperVolumePercent(Context context, int i2) {
        kotlin.jvm.internal.n.g(context, "context");
        Integer num = SUPER_VOLUME_PERCENT_MAP.get(Integer.valueOf(i2));
        if (num != null) {
            return num + context.getResources().getString(R.string.super_volume_text_percent);
        }
        if (i2 != indexAdd) {
            String string = context.getString(R.string.super_volume_text);
            kotlin.jvm.internal.n.f(string, "getString(...)");
            return string;
        }
        return getSUPER_VOLUME_PERCENT() + context.getResources().getString(R.string.super_volume_text_percent);
    }
}
