package miui.systemui.quicksettings.soundeffect.dirac;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
public class MiSoundWrapper {
    static final String FILED_HEADSET_ID_EM001 = "MISOUND_HEADSET_EM001";
    static final String FILED_HEADSET_ID_EM006 = "MISOUND_HEADSET_EM006";
    static final String FILED_HEADSET_ID_EM007 = "MISOUND_HEADSET_EM007";
    static final String FILED_HEADSET_ID_EM013 = "MISOUND_HEADSET_EM013";
    static final String FILED_HEADSET_ID_EM015 = "MISOUND_HEADSET_EM015";
    static final String FILED_HEADSET_ID_EM017 = "MISOUND_HEADSET_EM017";
    static final String FILED_HEADSET_ID_EM018 = "MISOUND_HEADSET_EM018";
    static final String FILED_HEADSET_ID_EM019 = "MISOUND_HEADSET_EM019";
    static final String FILED_HEADSET_ID_EM031 = "MISOUND_HEADSET_EM031";
    static final String FILED_HEADSET_ID_EM033 = "MISOUND_HEADSET_EM033";
    static final String FILED_HEADSET_ID_EM035 = "MISOUND_HEADSET_EM035";
    static final String FILED_HEADSET_ID_EM037 = "MISOUND_HEADSET_EM037";
    static final String FILED_HEADSET_ID_HM004 = "MISOUND_HEADSET_HM004";
    private static final String METHOD_EFFECT_ENABLE = "setEnabled";
    private static final String METHOD_GET_HEADSET_LIST = "getHeadsetList";
    private static final String METHOD_GET_HEADSET_TYPE = "getHeadsetType";
    private static final String METHOD_GET_MUSIC = "getMusic";
    private static final String METHOD_GET_SCENARIO = "getScenario";
    private static final String METHOD_HAS_CONTROL = "hasControl";
    private static final String METHOD_RELEASE = "release";
    private static final String METHOD_SET_HEADSET_TYPE = "setHeadsetType";
    private static final String METHOD_SET_HIFI_MODE = "setHifiMode";
    private static final String METHOD_SET_LEVEL = "setLevel";
    private static final String METHOD_SET_MOVIE_MODE_ENABLE = "setMovieModeEnable";
    private static final String METHOD_SET_MOVIE_SURROUND_LEVEL = "setMovieSurroundLevel";
    private static final String METHOD_SET_MOVIE_VOCAL_LEVEL = "setMovieVocalLevel";
    private static final String METHOD_SET_MUSIC = "setMusic";
    private static final String METHOD_SET_SCENARIO = "setScenario";
    private static final String MI_SOUND_CLASS_NAME = "android.media.audiofx.MiSound";
    private static final String TAG = "MiSoundWrapper";
    private Method mMethodEffectEnable;
    private Method mMethodGetEarsCompensationOn;
    private Method mMethodGetHeadsetList;
    private Method mMethodGetHeadsetType;
    private Method mMethodGetMusic;
    private Method mMethodGetScenario;
    private Method mMethodHasControl;
    private Method mMethodRelease;
    private Method mMethodSetEarsCompensationEQLeft;
    private Method mMethodSetEarsCompensationEQRight;
    private Method mMethodSetEarsCompensationOn;
    private Method mMethodSetHeadsetType;
    private Method mMethodSetHifiMode;
    private Method mMethodSetLevel;
    private Method mMethodSetMovieModeEnable;
    private Method mMethodSetMovieSurroundLevel;
    private Method mMethodSetMovieVocalLevel;
    private Method mMethodSetMusic;
    private Method mMethodSetScenario;
    private final Object mMiSoundInstance;

    public MiSoundWrapper(int i2, int i3) {
        try {
            Class<?> cls = Class.forName(MI_SOUND_CLASS_NAME);
            Class cls2 = Integer.TYPE;
            this.mMiSoundInstance = cls.getConstructor(cls2, cls2).newInstance(Integer.valueOf(i2), Integer.valueOf(i3));
            try {
                this.mMethodRelease = cls.getMethod("release", null);
            } catch (NoSuchMethodException e2) {
                Log.e(TAG, "", e2);
            }
            try {
                this.mMethodSetMusic = cls.getMethod(METHOD_SET_MUSIC, Integer.TYPE);
            } catch (NoSuchMethodException e3) {
                Log.e(TAG, "", e3);
            }
            try {
                this.mMethodGetMusic = cls.getMethod(METHOD_GET_MUSIC, null);
            } catch (NoSuchMethodException e4) {
                Log.e(TAG, "", e4);
            }
            try {
                this.mMethodSetHeadsetType = cls.getMethod(METHOD_SET_HEADSET_TYPE, Integer.TYPE);
            } catch (NoSuchMethodException e5) {
                Log.e(TAG, "", e5);
            }
            try {
                this.mMethodGetHeadsetType = cls.getMethod(METHOD_GET_HEADSET_TYPE, null);
            } catch (NoSuchMethodException e6) {
                Log.e(TAG, "", e6);
            }
            try {
                this.mMethodGetHeadsetList = cls.getMethod(METHOD_GET_HEADSET_LIST, null);
            } catch (NoSuchMethodException e7) {
                Log.e(TAG, "", e7);
            }
            try {
                this.mMethodSetLevel = cls.getMethod(METHOD_SET_LEVEL, Integer.TYPE, Float.TYPE);
            } catch (NoSuchMethodException e8) {
                Log.e(TAG, "", e8);
            }
            try {
                this.mMethodSetHifiMode = cls.getMethod(METHOD_SET_HIFI_MODE, Integer.TYPE);
            } catch (NoSuchMethodException e9) {
                Log.e(TAG, "", e9);
            }
            try {
                this.mMethodSetScenario = cls.getMethod(METHOD_SET_SCENARIO, Integer.TYPE);
            } catch (NoSuchMethodException e10) {
                Log.e(TAG, "", e10);
            }
            try {
                this.mMethodSetMovieSurroundLevel = cls.getMethod(METHOD_SET_MOVIE_SURROUND_LEVEL, Integer.TYPE);
            } catch (NoSuchMethodException e11) {
                Log.e(TAG, "", e11);
            }
            try {
                this.mMethodSetMovieVocalLevel = cls.getMethod(METHOD_SET_MOVIE_VOCAL_LEVEL, Integer.TYPE);
            } catch (NoSuchMethodException e12) {
                Log.e(TAG, "", e12);
            }
            try {
                this.mMethodSetMovieModeEnable = cls.getMethod(METHOD_SET_MOVIE_MODE_ENABLE, Integer.TYPE);
            } catch (NoSuchMethodException e13) {
                Log.e(TAG, "", e13);
            }
            try {
                this.mMethodGetScenario = cls.getMethod(METHOD_GET_SCENARIO, null);
            } catch (NoSuchMethodException e14) {
                Log.e(TAG, "", e14);
            }
            try {
                this.mMethodEffectEnable = cls.getMethod(METHOD_EFFECT_ENABLE, Boolean.TYPE);
            } catch (NoSuchMethodException e15) {
                Log.e(TAG, "", e15);
            }
            try {
                this.mMethodHasControl = cls.getMethod(METHOD_HAS_CONTROL, null);
            } catch (NoSuchMethodException e16) {
                Log.e(TAG, "", e16);
            }
        } catch (ClassNotFoundException e17) {
            throw new RuntimeException("Not found android.media.audiofx.MiSound", e17);
        } catch (Exception e18) {
            throw new RuntimeException(e18);
        }
    }

    public int[] getHeadsetList() {
        Method method = this.mMethodGetHeadsetList;
        if (method == null) {
            return new int[0];
        }
        try {
            return (int[]) method.invoke(this.mMiSoundInstance, null);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        }
    }

    public int getHeadsetType() {
        Method method = this.mMethodGetHeadsetType;
        if (method == null) {
            return 0;
        }
        try {
            return ((Integer) method.invoke(this.mMiSoundInstance, null)).intValue();
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "", e3);
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "", e4);
            throw new RuntimeException(e4);
        }
    }

    public int getMusic() {
        Method method = this.mMethodGetMusic;
        if (method == null) {
            return 0;
        }
        try {
            return ((Integer) method.invoke(this.mMiSoundInstance, null)).intValue();
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "", e3);
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "", e4);
            throw new RuntimeException(e4);
        }
    }

    public int getScenario() {
        try {
            return ((Integer) this.mMethodGetScenario.invoke(this.mMiSoundInstance, null)).intValue();
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "", e3);
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "", e4);
            throw new RuntimeException(e4);
        }
    }

    public boolean hasControl() {
        Method method = this.mMethodHasControl;
        if (method == null) {
            return false;
        }
        try {
            return ((Boolean) method.invoke(this.mMiSoundInstance, null)).booleanValue();
        } catch (IllegalAccessException | IllegalArgumentException | NullPointerException | InvocationTargetException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        }
    }

    public boolean isSupportType(String str) {
        Object obj = this.mMiSoundInstance;
        if (obj == null) {
            return false;
        }
        try {
            obj.getClass().getField(str);
            return true;
        } catch (NoSuchFieldException e2) {
            Log.e(TAG, "", e2);
            return false;
        }
    }

    public void release() {
        Method method = this.mMethodRelease;
        if (method == null) {
            return;
        }
        try {
            method.invoke(this.mMiSoundInstance, null);
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "", e3);
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "", e4);
            throw new RuntimeException(e4);
        }
    }

    public void setEnable(boolean z2) {
        Method method = this.mMethodEffectEnable;
        if (method == null) {
            return;
        }
        try {
            method.invoke(this.mMiSoundInstance, Boolean.valueOf(z2));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        }
    }

    public void setHeadsetType(int i2) {
        Method method = this.mMethodSetHeadsetType;
        if (method == null) {
            return;
        }
        try {
            method.invoke(this.mMiSoundInstance, Integer.valueOf(i2));
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "", e3);
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "", e4);
            throw new RuntimeException(e4);
        }
    }

    public void setHifiMode(int i2) {
        Method method = this.mMethodSetHifiMode;
        if (method == null) {
            return;
        }
        try {
            method.invoke(this.mMiSoundInstance, Integer.valueOf(i2));
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "", e3);
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "", e4);
            throw new RuntimeException(e4);
        }
    }

    public void setLevel(int i2, float f2) {
        Method method = this.mMethodSetLevel;
        if (method == null) {
            return;
        }
        try {
            method.invoke(this.mMiSoundInstance, Integer.valueOf(i2), Float.valueOf(f2));
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "", e3);
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "", e4);
            throw new RuntimeException(e4);
        }
    }

    public void setMovieModeEnable(int i2) {
        Method method = this.mMethodSetMovieModeEnable;
        if (method == null) {
            return;
        }
        try {
            method.invoke(this.mMiSoundInstance, Integer.valueOf(i2));
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "", e3);
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "", e4);
            throw new RuntimeException(e4);
        }
    }

    public void setMovieSurroundLevel(int i2) {
        Method method = this.mMethodSetMovieSurroundLevel;
        if (method == null) {
            return;
        }
        try {
            method.invoke(this.mMiSoundInstance, Integer.valueOf(i2));
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "", e3);
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "", e4);
            throw new RuntimeException(e4);
        }
    }

    public void setMovieVocalLevel(int i2) {
        Method method = this.mMethodSetMovieVocalLevel;
        if (method == null) {
            return;
        }
        try {
            method.invoke(this.mMiSoundInstance, Integer.valueOf(i2));
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "", e3);
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "", e4);
            throw new RuntimeException(e4);
        }
    }

    public void setMusic(int i2) {
        Method method = this.mMethodSetMusic;
        if (method == null) {
            return;
        }
        try {
            method.invoke(this.mMiSoundInstance, Integer.valueOf(i2));
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "", e3);
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "", e4);
            throw new RuntimeException(e4);
        }
    }

    public void setScenario(int i2) {
        Method method = this.mMethodSetScenario;
        if (method == null) {
            return;
        }
        try {
            method.invoke(this.mMiSoundInstance, Integer.valueOf(i2));
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "", e2);
            throw new RuntimeException(e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "", e3);
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "", e4);
            throw new RuntimeException(e4);
        }
    }
}
