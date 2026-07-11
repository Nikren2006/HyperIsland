package com.miui.circulate.device.api;

import android.net.Uri;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.jvm.internal.n;
import miui.systemui.notification.focus.Const;

/* JADX INFO: loaded from: classes2.dex */
public final class Constant {
    public static final String AUDIO_GROUP_ID = "audio_group";
    public static final String AUTHORITY = "com.milink.service.device";
    public static final long CACHE_EXPIRED_DURATION = 86400000;
    private static final Uri CATEGORY_DEVICE_LIST_URI;
    public static final String CATEGORY_PATH = "category";
    public static final long DATABASE_AUTO_CLOSE_DURATION = 15;
    private static final Uri DEVICE_META_LIST_URI;
    public static final String DEVICE_META_PATH = "device";
    private static final Uri EXPORT_LIST_URI;
    public static final String EXPORT_PATH = "export";
    private static final Uri HEALTH_DEVICE_LIST_URI;
    public static final Constant INSTANCE = new Constant();
    private static final Uri KEY_VALUE_URI;
    public static final String KV_PATH = "kv";
    public static final long MAX_DISCOVERY_WAIT_DURATION = 15000;
    public static final int MAX_EXPORT_DEVICE_COUNT = 7;
    public static final int MAX_MIJIA_DEVICE_COUNT = 12;
    public static final int MAX_STR_LENGTH = 5000;
    public static final long MAX_WORK_THREAD_LIVE_TIME = 300000;
    private static final Uri METHOD_CALL;
    public static final String METHOD_PATH = "method";
    public static final String METHOD_START_SEARCH = "start_search";
    public static final String METHOD_STOP_SEARCH = "stop_search";
    private static final Uri MIJIA_ALL_URI;
    private static final Uri MIJIA_SHOW_URI;
    private static final Uri NEARBY_DEVICE_LIST_URI;
    public static final String PARAM_FROM = "from";
    public static final String PARAM_SEARCH = "search";
    public static final String PIN_PATH = "pin";
    private static final Uri PIN_URI;
    public static final String SOUND_COMPOSE_ID = "sound_compose";
    public static final String TAG = "MDC";
    public static final String TV_COMPOSE_ID = "tv_compose";

    public static final class DeviceCategory {
        public static final String HEALTH = "health";
        public static final DeviceCategory INSTANCE = new DeviceCategory();
        public static final String MIJIA = "mijia";
        public static final String NEARBY = "nearby";

        private DeviceCategory() {
        }
    }

    public static final class DeviceType {
        public static final String ANDROID_PAD = "AndroidPad";
        public static final String ANDROID_PHONE = "AndroidPhone";
        public static final String ANDROID_TV = "TV";
        public static final String AUDIOGLASSES = "AudioGlasses";
        public static final String AUDIO_GROUP = "audio_group";
        public static final String AUDIO_STEREO = "audio_stereo";
        public static final String BAND = "band";
        public static final String BLUETOOTH = "bluetooth";
        public static final String BLUETOOTH_CAR = "bluetooth_car";
        public static final String CAMERA = "Camera";
        public static final String CAMERAGLASSES = "CameraGlasses";
        public static final String CAR = "Car";
        public static final String COMPOSE_SOUND = "compose_sound";
        public static final String COMPOSE_TV = "compose_tv";
        public static final String Car = "Car";
        public static final String GLASSES = "glasses";
        public static final String HEADSET = "headset";
        public static final DeviceType INSTANCE = new DeviceType();
        public static final String MIJIA_IOT = "mijia_iot";
        public static final String MIJIA_IOT_CONTROL = "mijia_iot_control";
        public static final String SCREEN_SOUND = "ScreenSound";
        public static final String SOUND = "Sound";
        public static final String THIRD_ANDROID_TV = "third_TV";
        public static final String THIRD_CAR_KIT = "third_car_kit";
        public static final String THIRD_HEADSET = "third_headset";
        public static final String THIRD_HEARING_AID = "third_hearing_aid";
        public static final String THIRD_OTHER = "third_other";
        public static final String THIRD_SPEAKER = "third_speaker";
        public static final String THIRD_WATCH = "third_watch";
        public static final String UNKNOWN = "unknown";
        public static final String WATCH = "watch";
        public static final String WEARABLE = "wearable";
        public static final String WINDOWS_PC = "Windows";

        private DeviceType() {
        }

        public static /* synthetic */ void getCar$annotations() {
        }

        public static /* synthetic */ void getWEARABLE$annotations() {
        }
    }

    public static final class KeyValue {
        public static final KeyValue INSTANCE = new KeyValue();
        public static final String KEY_COLUMN = "k";
        public static final String VALUE_COLUMN = "v";

        private KeyValue() {
        }
    }

    public static final class MethodCallResult {
        public static final MethodCallResult INSTANCE = new MethodCallResult();
        public static final int NO_PERMISSION = -1;
        public static final int OK = 1;
        public static final String RESULT_KEY = "result";

        private MethodCallResult() {
        }
    }

    public static final class QueryParameter {
        public static final String EXPORT = "export";
        public static final String FORCE_UPDATE = "force_update";
        public static final String IGNORE_EVALUATE = "ignore_evaluate";
        public static final QueryParameter INSTANCE = new QueryParameter();
        public static final String LIMIT = "limit";
        public static final String MAX_COUNT = "max_count";
        public static final String QUERY_ID = "query_id";
        public static final String SEARCH_URI_LIST = "search_uri_list";
        public static final String UPDATE_TYPE = "update_type";

        private QueryParameter() {
        }
    }

    public static final class State {
        public static final State INSTANCE = new State();
        public static final int STATE_APP_CONTINUITY = 512;
        public static final int STATE_AUDIO_CAST = 128;
        public static final int STATE_CALL_SYNERGY = 16;
        public static final int STATE_CAMERA_SYNERGY = 4;
        public static final int STATE_CELLULAR_SYNERGY = 4096;
        public static final int STATE_COMPUTING_POWER = 16384;
        public static final int STATE_HID_SYNERGY = 32;
        public static final String STATE_MASK_KEY = "state_mask";
        public static final int STATE_MIRROR = 8;
        public static final int STATE_MUSIC_PLAYING = 256;
        public static final int STATE_ONLINE = 1;
        public static final int STATE_OPEN = 64;
        public static final int STATE_SCREEN_SYNERGY = 2;
        public static final int STATE_TAKE_PHOTO = 1024;
        public static final int STATE_VIDEO_CAST = 2048;

        private State() {
        }

        public final int add(int i2, int i3) {
            return i2 | i3;
        }

        public final String binary(int i2) {
            String binaryString = Integer.toBinaryString(i2);
            n.f(binaryString, "toBinaryString(this)");
            return binaryString;
        }

        public final boolean check(int i2, int i3) {
            return (i2 & i3) == i3;
        }

        public final int remove(int i2, int i3) {
            return (~i3) & i2;
        }

        public final boolean synergyStateChange(int i2, int i3) {
            return i2 != i3;
        }

        public final int update(int i2, int i3, boolean z2) {
            return z2 ? add(i2, i3) : remove(i2, i3);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface StateMask {
    }

    public static final class UpdateType {
        public static final UpdateType INSTANCE = new UpdateType();
        public static final String UPDATE_NORMAL = "update_normal";
        public static final String UPDATE_STATE = "update_state";

        private UpdateType() {
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface UpdateTypeDef {
    }

    static {
        Uri uriBuild = new Uri.Builder().scheme(Const.Param.CONTENT).authority(AUTHORITY).appendPath(METHOD_PATH).build();
        n.f(uriBuild, "Builder()\n        .schem…OD_PATH)\n        .build()");
        METHOD_CALL = uriBuild;
        Uri uriBuild2 = new Uri.Builder().scheme(Const.Param.CONTENT).authority(AUTHORITY).appendPath("export").build();
        n.f(uriBuild2, "Builder()\n        .schem…RT_PATH)\n        .build()");
        EXPORT_LIST_URI = uriBuild2;
        Uri uriBuild3 = new Uri.Builder().scheme(Const.Param.CONTENT).authority(AUTHORITY).appendPath("device").build();
        n.f(uriBuild3, "Builder()\n        .schem…TA_PATH)\n        .build()");
        DEVICE_META_LIST_URI = uriBuild3;
        Uri uriBuild4 = new Uri.Builder().scheme(Const.Param.CONTENT).authority(AUTHORITY).appendPath(PIN_PATH).build();
        n.f(uriBuild4, "Builder()\n        .schem…IN_PATH)\n        .build()");
        PIN_URI = uriBuild4;
        Uri uriBuild5 = new Uri.Builder().scheme(Const.Param.CONTENT).authority(AUTHORITY).appendPath("category").appendPath(DeviceCategory.MIJIA).build();
        n.f(uriBuild5, "Builder()\n        .schem…y.MIJIA)\n        .build()");
        MIJIA_SHOW_URI = uriBuild5;
        Uri uriBuild6 = new Uri.Builder().scheme(Const.Param.CONTENT).authority(AUTHORITY).appendPath("category").appendPath(DeviceCategory.MIJIA).build();
        n.f(uriBuild6, "Builder()\n        .schem…y.MIJIA)\n        .build()");
        MIJIA_ALL_URI = uriBuild6;
        Uri uriBuild7 = new Uri.Builder().scheme(Const.Param.CONTENT).authority(AUTHORITY).appendPath("category").appendPath(DeviceCategory.HEALTH).build();
        n.f(uriBuild7, "Builder()\n        .schem….HEALTH)\n        .build()");
        HEALTH_DEVICE_LIST_URI = uriBuild7;
        Uri uriBuild8 = new Uri.Builder().scheme(Const.Param.CONTENT).authority(AUTHORITY).appendPath(KV_PATH).build();
        n.f(uriBuild8, "Builder()\n        .schem…KV_PATH)\n        .build()");
        KEY_VALUE_URI = uriBuild8;
        Uri uriBuild9 = new Uri.Builder().scheme(Const.Param.CONTENT).authority(AUTHORITY).appendPath("category").build();
        n.f(uriBuild9, "Builder()\n        .schem…RY_PATH)\n        .build()");
        CATEGORY_DEVICE_LIST_URI = uriBuild9;
        Uri uriBuild10 = new Uri.Builder().scheme(Const.Param.CONTENT).authority(AUTHORITY).appendPath("category").appendPath(DeviceCategory.NEARBY).build();
        n.f(uriBuild10, "Builder()\n        .schem….NEARBY)\n        .build()");
        NEARBY_DEVICE_LIST_URI = uriBuild10;
    }

    private Constant() {
    }

    public static /* synthetic */ void getMIJIA_ALL_URI$annotations() {
    }

    public final Uri getCATEGORY_DEVICE_LIST_URI() {
        return CATEGORY_DEVICE_LIST_URI;
    }

    public final Uri getDEVICE_META_LIST_URI() {
        return DEVICE_META_LIST_URI;
    }

    public final Uri getEXPORT_LIST_URI() {
        return EXPORT_LIST_URI;
    }

    public final Uri getHEALTH_DEVICE_LIST_URI() {
        return HEALTH_DEVICE_LIST_URI;
    }

    public final Uri getKEY_VALUE_URI() {
        return KEY_VALUE_URI;
    }

    public final Uri getMETHOD_CALL() {
        return METHOD_CALL;
    }

    public final Uri getMIJIA_ALL_URI() {
        return MIJIA_ALL_URI;
    }

    public final Uri getMIJIA_SHOW_URI() {
        return MIJIA_SHOW_URI;
    }

    public final Uri getNEARBY_DEVICE_LIST_URI() {
        return NEARBY_DEVICE_LIST_URI;
    }

    public final Uri getPIN_URI() {
        return PIN_URI;
    }
}
