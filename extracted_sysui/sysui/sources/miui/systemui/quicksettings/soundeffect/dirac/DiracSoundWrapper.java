package miui.systemui.quicksettings.soundeffect.dirac;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
class DiracSoundWrapper {
    private static final String DIRAC_SOUND_CLASS_NAME = "android.media.audiofx.DiracSound";
    static final String FILED_HEADSET_ID_EM001 = "DIRACSOUND_HEADSET_EM001";
    static final String FILED_HEADSET_ID_EM006 = "DIRACSOUND_HEADSET_EM006";
    static final String FILED_HEADSET_ID_EM007 = "DIRACSOUND_HEADSET_EM007";
    static final String FILED_HEADSET_ID_EM013 = "DIRACSOUND_HEADSET_EM013";
    static final String FILED_HEADSET_ID_EM015 = "DIRACSOUND_HEADSET_EM015";
    static final String FILED_HEADSET_ID_EM017 = "DIRACSOUND_HEADSET_EM017";
    static final String FILED_HEADSET_ID_EM018 = "DIRACSOUND_HEADSET_EM018";
    static final String FILED_HEADSET_ID_EM019 = "DIRACSOUND_HEADSET_EM019";
    static final String FILED_HEADSET_ID_HM004 = "DIRACSOUND_HEADSET_HM004";
    private static final String METHOD_GET_HEADSET_TYPE = "getHeadsetType";
    private static final String METHOD_GET_MUSIC = "getMusic";
    private static final String METHOD_RELEASE = "release";
    private static final String METHOD_SET_HEADSET_TYPE = "setHeadsetType";
    private static final String METHOD_SET_HIFI_MODE = "setHifiMode";
    private static final String METHOD_SET_LEVEL = "setLevel";
    private static final String METHOD_SET_MUSIC = "setMusic";
    private static final String TAG = "DiracSoundWrapper";
    private final Object mDiracSoundInstance;
    private Method mMethodGetHeadsetType;
    private Method mMethodGetMusic;
    private Method mMethodRealese;
    private Method mMethodSetHeadsetType;
    private Method mMethodSetHifiMode;
    private Method mMethodSetLevel;
    private Method mMethodSetMusic;

    public DiracSoundWrapper(int i2, int i3) {
        try {
            Class<?> cls = Class.forName(DIRAC_SOUND_CLASS_NAME);
            Class cls2 = Integer.TYPE;
            this.mDiracSoundInstance = cls.getConstructor(cls2, cls2).newInstance(Integer.valueOf(i2), Integer.valueOf(i3));
            try {
                this.mMethodRealese = cls.getMethod("release", null);
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
                this.mMethodSetLevel = cls.getMethod(METHOD_SET_LEVEL, Integer.TYPE, Float.TYPE);
            } catch (NoSuchMethodException e7) {
                Log.e(TAG, "", e7);
            }
            try {
                this.mMethodSetHifiMode = cls.getMethod(METHOD_SET_HIFI_MODE, Integer.TYPE);
            } catch (NoSuchMethodException e8) {
                Log.e(TAG, "", e8);
            }
        } catch (ClassNotFoundException e9) {
            throw new RuntimeException("Not found android.media.audiofx.DiracSound", e9);
        } catch (Exception e10) {
            throw new RuntimeException(e10);
        }
    }

    public int getHeadsetType() {
        Method method = this.mMethodGetHeadsetType;
        if (method == null) {
            return 0;
        }
        try {
            return ((Integer) method.invoke(this.mDiracSoundInstance, null)).intValue();
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
            return ((Integer) method.invoke(this.mDiracSoundInstance, null)).intValue();
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

    public boolean isSupportType(String str) {
        Object obj = this.mDiracSoundInstance;
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
        Method method = this.mMethodRealese;
        if (method == null) {
            return;
        }
        try {
            method.invoke(this.mDiracSoundInstance, null);
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

    public void setHeadsetType(int i2) {
        Method method = this.mMethodSetHeadsetType;
        if (method == null) {
            return;
        }
        try {
            method.invoke(this.mDiracSoundInstance, Integer.valueOf(i2));
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
            method.invoke(this.mDiracSoundInstance, Integer.valueOf(i2));
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
            method.invoke(this.mDiracSoundInstance, Integer.valueOf(i2), Float.valueOf(f2));
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
            method.invoke(this.mDiracSoundInstance, Integer.valueOf(i2));
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
