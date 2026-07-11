package miui.systemui.quicksettings.soundeffect.dirac;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
public class AudioEffectCenter {
    public static final String ACTION_EFFECT_REFRESH = "miui.intent.action.ACTION_AUDIO_EFFECT_REFRESH";
    public static String EFFECT_DOLBY = null;
    public static String EFFECT_MISOUND = null;
    public static String EFFECT_NONE = null;
    public static String EFFECT_SPATIAL_AUDIO = null;
    public static String EFFECT_SURROUND = null;
    public static final String KEY_BUNDLE = "bundle";
    public static final String KEY_DOLBY_ACTIVE = "dolby_active";
    public static final String KEY_DOLBY_AVAILABLE = "dolby_available";
    public static final String KEY_MISOUND_ACTIVE = "misound_active";
    public static final String KEY_MISOUND_AVAILABLE = "misound_available";
    public static final String KEY_NONE_AVAILABLE = "none_available";
    public static final String KEY_SPATIAL_ACTIVE = "spatial_active";
    public static final String KEY_SPATIAL_AVAILABLE = "spatial_available";
    public static final String KEY_SURROUND_ACTIVE = "surround_active";
    public static final String KEY_SURROUND_AVAILABLE = "surround_available";
    private static final String TAG = "miui.systemui.quicksettings.soundeffect.dirac.AudioEffectCenter";
    private static AudioEffectCenter mAudioEffectCenter;
    private Class<?> mAudioEffectCenterClass;
    private Object mAudioEffectCenterObject;
    private Field mDolbyEffectConstantField;
    private Method mIsEffectActive;
    private Method mIsEffectAvailable;
    private Method mIsEffectSupported;
    private Field mMisoundEffectConstantField;
    private Field mNoneEffectConstantField;
    private Method mReleaseAudioEffectCenter;
    private Method mSetEffectActive;
    private Field mSpatialEffectConstantField;
    private Field mSurroundEffectConstantField;

    public static AudioEffectCenter getInstance() {
        if (mAudioEffectCenter == null) {
            synchronized (AudioEffectCenter.class) {
                try {
                    if (mAudioEffectCenter == null) {
                        mAudioEffectCenter = new AudioEffectCenter();
                    }
                } finally {
                }
            }
        }
        return mAudioEffectCenter;
    }

    public void init(Context context) {
        try {
            String str = TAG;
            Log.d(str, "AudioEffectCenter load");
            Class<?> cls = Class.forName("android.media.audiofx.AudioEffectCenter");
            this.mAudioEffectCenterClass = cls;
            Field field = cls.getField("EFFECT_DOLBY");
            this.mDolbyEffectConstantField = field;
            EFFECT_DOLBY = (String) field.get(null);
            Field field2 = this.mAudioEffectCenterClass.getField("EFFECT_MISOUND");
            this.mMisoundEffectConstantField = field2;
            EFFECT_MISOUND = (String) field2.get(null);
            Field field3 = this.mAudioEffectCenterClass.getField("EFFECT_NONE");
            this.mNoneEffectConstantField = field3;
            EFFECT_NONE = (String) field3.get(null);
            Field field4 = this.mAudioEffectCenterClass.getField("EFFECT_SPATIAL_AUDIO");
            this.mSpatialEffectConstantField = field4;
            EFFECT_SPATIAL_AUDIO = (String) field4.get(null);
            Field field5 = this.mAudioEffectCenterClass.getField("EFFECT_SURROUND");
            this.mSurroundEffectConstantField = field5;
            EFFECT_SURROUND = (String) field5.get(null);
            this.mIsEffectSupported = this.mAudioEffectCenterClass.getMethod("isEffectSupported", String.class);
            this.mIsEffectAvailable = this.mAudioEffectCenterClass.getMethod("isEffectAvailable", String.class);
            this.mIsEffectActive = this.mAudioEffectCenterClass.getMethod("isEffectActive", String.class);
            this.mSetEffectActive = this.mAudioEffectCenterClass.getMethod("setEffectActive", String.class, Boolean.TYPE);
            this.mReleaseAudioEffectCenter = this.mAudioEffectCenterClass.getMethod("release", null);
            this.mAudioEffectCenterObject = this.mAudioEffectCenterClass.getDeclaredMethod("getInstance", Context.class).invoke(null, context.getApplicationContext());
            Log.d(str, "AudioEffectCenter load success");
        } catch (ClassNotFoundException e2) {
            Log.e(TAG, "AudioEffectCenter ClassNotFoundException");
            e2.printStackTrace();
        } catch (IllegalAccessException | InvocationTargetException e3) {
            Log.e(TAG, "AudioEffectCenter InvocationTargetException | IllegalAccessException | InstantiationException");
            e3.printStackTrace();
        } catch (NoSuchFieldException e4) {
            Log.e(TAG, "AudioEffectCenter NoSuchFieldException");
            e4.printStackTrace();
        } catch (NoSuchMethodException e5) {
            Log.e(TAG, "AudioEffectCenter NoSuchMethodException");
            e5.printStackTrace();
        }
    }

    public boolean isEffectActive(String str) {
        try {
            return ((Boolean) this.mIsEffectActive.invoke(this.mAudioEffectCenterObject, str)).booleanValue();
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "isEffectActive IllegalAccessException");
            e2.printStackTrace();
            return false;
        } catch (NullPointerException e3) {
            Log.e(TAG, "isEffectActive NullPointerException ");
            e3.printStackTrace();
            return false;
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "isEffectActive InvocationTargetException");
            e4.printStackTrace();
            return false;
        }
    }

    public boolean isEffectAvailable(String str) {
        try {
            return ((Boolean) this.mIsEffectAvailable.invoke(this.mAudioEffectCenterObject, str)).booleanValue();
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "isEffectAvailable IllegalAccessException");
            e2.printStackTrace();
            return false;
        } catch (NullPointerException e3) {
            Log.e(TAG, "isEffectAvailable NullPointerException ");
            e3.printStackTrace();
            return false;
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "isEffectAvailable InvocationTargetException");
            e4.printStackTrace();
            return false;
        }
    }

    public boolean isEffectSupported(String str) {
        try {
            return ((Boolean) this.mIsEffectSupported.invoke(this.mAudioEffectCenterObject, str)).booleanValue();
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "isEffectSupported IllegalAccessException");
            e2.printStackTrace();
            return false;
        } catch (NullPointerException e3) {
            Log.e(TAG, "isEffectSupported NullPointerException");
            e3.printStackTrace();
            return false;
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "isEffectSupported InvocationTargetException");
            e4.printStackTrace();
            return false;
        }
    }

    public void release() {
        try {
            this.mReleaseAudioEffectCenter.invoke(this.mAudioEffectCenterObject, null);
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "AudioEffectCenter release IllegalAccessException");
            e2.printStackTrace();
        } catch (NullPointerException e3) {
            Log.e(TAG, "AudioEffectCenter release NullPointerException ");
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "AudioEffectCenter release InvocationTargetException");
            e4.printStackTrace();
        }
    }

    public void setEffectActive(String str, boolean z2) {
        try {
            this.mSetEffectActive.invoke(this.mAudioEffectCenterObject, str, Boolean.valueOf(z2));
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "setEffectActive IllegalAccessException");
            e2.printStackTrace();
        } catch (NullPointerException e3) {
            Log.e(TAG, "setEffectActive NullPointerException ");
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "setEffectActive InvocationTargetException");
            e4.printStackTrace();
        }
    }
}
