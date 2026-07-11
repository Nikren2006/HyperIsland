package miuix.util;

import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.os.VibrationAttributes;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import miui.util.HapticFeedbackUtil;
import miuix.HapticLog;
import miuix.core.util.SystemProperties;
import miuix.view.HapticCompat;
import miuix.view.PlatformConstants;

/* JADX INFO: loaded from: classes5.dex */
public class HapticFeedbackCompat {
    private static final String PHYSICAL_EMULATION_REASON = "USAGE_PHYSICAL_EMULATION";
    private static final int RTP_MIN_VALUE = 0;
    private static final int RTP_V1_MAX_VALUE = 160;
    private static final String TAG = "HapticFeedbackCompat";
    private static boolean mAvailable;
    private static boolean mCanCheckExtHaptic;
    private static boolean mCanStop;
    private static boolean mExtHapticAlways;
    private static boolean mIsSupportExtHapticWithReason;
    private static boolean mIsSupportHapticWithReason;
    private static boolean mPerformExtHapticFeedbackThreeParamsMethodExist;
    private static boolean mPerformExtHapticFeedbackTwoParamsMethodExist;
    private static boolean mPerformHapticFeedbackFourParamsMethod1Exist;
    private static boolean mPerformHapticFeedbackFourParamsMethod2Exist;
    private static final Executor sSingleThread = Executors.newSingleThreadExecutor();
    private HapticFeedbackUtil mHapticFeedbackUtil;

    static {
        if (PlatformConstants.VERSION >= 1) {
            try {
                mAvailable = HapticFeedbackUtil.isSupportLinearMotorVibrate();
            } catch (Throwable th) {
                Log.w(TAG, "MIUI Haptic Implementation is not available", th);
                mAvailable = false;
            }
            if (mAvailable) {
                try {
                    HapticFeedbackUtil.class.getMethod("performHapticFeedback", Integer.TYPE, Double.TYPE, String.class);
                    mIsSupportHapticWithReason = true;
                } catch (Throwable th2) {
                    Log.w(TAG, "Not support haptic with reason", th2);
                    mIsSupportHapticWithReason = false;
                }
                try {
                    HapticFeedbackUtil.class.getMethod("isSupportExtHapticFeedback", Integer.TYPE);
                    mCanCheckExtHaptic = true;
                } catch (Throwable unused) {
                    mCanCheckExtHaptic = false;
                }
                try {
                    HapticFeedbackUtil.class.getMethod("performExtHapticFeedback", Integer.TYPE, Boolean.TYPE);
                    mExtHapticAlways = true;
                } catch (Throwable unused2) {
                    mExtHapticAlways = false;
                }
                try {
                    HapticFeedbackUtil.class.getMethod("stop", null);
                    mCanStop = true;
                } catch (Throwable unused3) {
                    mCanStop = false;
                }
                try {
                    HapticFeedbackUtil.class.getMethod("performExtHapticFeedback", Integer.TYPE, Double.TYPE, String.class);
                    mIsSupportExtHapticWithReason = true;
                } catch (Throwable th3) {
                    Log.w(TAG, "Not support ext haptic with reason", th3);
                    mIsSupportExtHapticWithReason = false;
                }
            }
        }
        if (PlatformConstants.romHapticVersion >= 1.2d) {
            try {
                HapticFeedbackUtil.class.getMethod("performExtHapticFeedback", VibrationAttributes.class, Integer.TYPE);
                mPerformExtHapticFeedbackTwoParamsMethodExist = true;
            } catch (Exception unused4) {
            }
            try {
                HapticFeedbackUtil.class.getMethod("performExtHapticFeedback", VibrationAttributes.class, Integer.TYPE, Boolean.TYPE);
                mPerformExtHapticFeedbackThreeParamsMethodExist = true;
            } catch (Exception unused5) {
            }
            try {
                Class cls = Integer.TYPE;
                HapticFeedbackUtil.class.getMethod("performHapticFeedback", VibrationAttributes.class, cls, Boolean.TYPE, cls);
                mPerformHapticFeedbackFourParamsMethod1Exist = true;
            } catch (Exception unused6) {
            }
            try {
                HapticFeedbackUtil.class.getMethod("performHapticFeedback", VibrationAttributes.class, Integer.TYPE, Double.TYPE, String.class);
                mPerformHapticFeedbackFourParamsMethod2Exist = true;
            } catch (Exception unused7) {
            }
        }
    }

    @Deprecated
    public HapticFeedbackCompat(Context context, boolean z2) {
        if (PlatformConstants.VERSION < 1) {
            Log.w(TAG, "MiuiHapticFeedbackConstants not found or not compatible for LinearVibrator.");
        } else if (mAvailable) {
            this.mHapticFeedbackUtil = new HapticFeedbackUtil(context, z2);
        } else {
            Log.w(TAG, "linear motor is not supported in this platform.");
        }
    }

    public boolean isSupportExtHapticFeedback(int i2) {
        HapticFeedbackUtil hapticFeedbackUtil = this.mHapticFeedbackUtil;
        if (hapticFeedbackUtil != null) {
            return mCanCheckExtHaptic ? hapticFeedbackUtil.isSupportExtHapticFeedback(i2) : i2 >= 0 && i2 <= RTP_V1_MAX_VALUE;
        }
        return false;
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performEmulationExtHaptic(int i2, double d2) {
        return performExtHapticFeedback(i2, d2, PHYSICAL_EMULATION_REASON);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performEmulationHaptic(int i2, double d2) {
        return performHapticFeedback(i2, d2, PHYSICAL_EMULATION_REASON);
    }

    @RequiresPermission("android.permission.VIBRATE")
    /* JADX INFO: renamed from: performExtHapticFeedback, reason: merged with bridge method [inline-methods] */
    public boolean lambda$performExtHapticFeedbackAsync$0(int i2) {
        if (this.mHapticFeedbackUtil == null) {
            return false;
        }
        HapticLog.printTrace("performExtHapticFeedback: " + i2);
        return this.mHapticFeedbackUtil.performExtHapticFeedback(i2);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public void performExtHapticFeedbackAsync(final int i2) {
        if (this.mHapticFeedbackUtil == null) {
            return;
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            sSingleThread.execute(new Runnable() { // from class: miuix.util.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f6167a.lambda$performExtHapticFeedbackAsync$0(i2);
                }
            });
        } else {
            lambda$performExtHapticFeedbackAsync$0(i2);
        }
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performHapticFeedback(int i2, int i3, boolean z2) {
        return performHapticFeedback((VibrationAttributes) null, i2, i3, z2);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public void performHapticFeedbackAsync(final int i2) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            sSingleThread.execute(new Runnable() { // from class: miuix.util.HapticFeedbackCompat.1
                @Override // java.lang.Runnable
                @RequiresPermission("android.permission.VIBRATE")
                public void run() {
                    HapticFeedbackCompat.this.performHapticFeedback(i2);
                }
            });
        } else {
            performHapticFeedback(i2);
        }
    }

    @Deprecated
    public void release() {
        HapticFeedbackUtil hapticFeedbackUtil = this.mHapticFeedbackUtil;
        if (hapticFeedbackUtil != null) {
            hapticFeedbackUtil.release();
        }
    }

    public void stop() {
        HapticFeedbackUtil hapticFeedbackUtil = this.mHapticFeedbackUtil;
        if (hapticFeedbackUtil != null) {
            if (mCanStop) {
                hapticFeedbackUtil.stop();
            } else {
                hapticFeedbackUtil.release();
            }
        }
    }

    public boolean supportKeyboardIntensity() {
        return SystemProperties.getBoolean("sys.haptic.intensityforkeyboard", false);
    }

    public boolean supportLinearMotor() {
        return mAvailable;
    }

    public boolean supportLinearMotorWithReason() {
        return mIsSupportHapticWithReason;
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performHapticFeedback(VibrationAttributes vibrationAttributes, int i2, int i3, boolean z2) {
        int iObtainFeedBack;
        if (this.mHapticFeedbackUtil == null || (iObtainFeedBack = HapticCompat.obtainFeedBack(i2)) == -1) {
            return false;
        }
        try {
            HapticLog.printTrace("performHapticFeedback: attributes: " + vibrationAttributes + ", effectId: " + i2 + ", effectStrength: " + i3 + ", always: " + z2);
            return (PlatformConstants.romHapticVersion < 1.2d || !mPerformHapticFeedbackFourParamsMethod1Exist) ? this.mHapticFeedbackUtil.performHapticFeedback(iObtainFeedBack, z2, i3) : this.mHapticFeedbackUtil.performHapticFeedback(vibrationAttributes, i2, z2, i3);
        } catch (Exception e2) {
            Log.e(TAG, "Failed to perform haptic!", e2);
            return false;
        }
    }

    @RequiresPermission("android.permission.VIBRATE")
    @RequiresApi(31)
    public boolean performExtHapticFeedback(int i2, int i3) {
        if (this.mHapticFeedbackUtil == null) {
            return false;
        }
        HapticLog.printTrace("performExtHapticFeedback: audioAttributesUsage: " + i2 + ", effectId: " + i3);
        if (PlatformConstants.romHapticVersion >= 1.1d) {
            return this.mHapticFeedbackUtil.performExtHapticFeedback(i2, i3);
        }
        return this.mHapticFeedbackUtil.performExtHapticFeedback(i3);
    }

    public HapticFeedbackCompat(Context context) {
        this(context, true);
    }

    @RequiresPermission("android.permission.VIBRATE")
    @RequiresApi(33)
    public boolean performExtHapticFeedback(VibrationAttributes vibrationAttributes, int i2) {
        if (this.mHapticFeedbackUtil == null) {
            return false;
        }
        try {
            HapticLog.printTrace("performExtHapticFeedback: attributes: " + vibrationAttributes + ", effectId: " + i2);
            if (PlatformConstants.romHapticVersion >= 1.2d && mPerformExtHapticFeedbackTwoParamsMethodExist) {
                return this.mHapticFeedbackUtil.performExtHapticFeedback(vibrationAttributes, i2);
            }
            return this.mHapticFeedbackUtil.performExtHapticFeedback(i2);
        } catch (Exception e2) {
            Log.e(TAG, "Failed to perform ext haptic!", e2);
            return false;
        }
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performHapticFeedback(int i2, int i3) {
        return performHapticFeedback((VibrationAttributes) null, i2, i3);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performHapticFeedback(VibrationAttributes vibrationAttributes, int i2, int i3) {
        int iObtainFeedBack;
        if (this.mHapticFeedbackUtil != null && (iObtainFeedBack = HapticCompat.obtainFeedBack(i2)) != -1) {
            try {
                HapticLog.printTrace("performHapticFeedback: attributes: " + vibrationAttributes + ", effectId: " + i2 + ", effectStrength: " + i3);
                if (PlatformConstants.romHapticVersion >= 1.2d && mPerformHapticFeedbackFourParamsMethod1Exist) {
                    return this.mHapticFeedbackUtil.performHapticFeedback(vibrationAttributes, iObtainFeedBack, false, i3);
                }
                return this.mHapticFeedbackUtil.performHapticFeedback(iObtainFeedBack, false, i3);
            } catch (Exception e2) {
                Log.e(TAG, "Failed to perform haptic!", e2);
            }
        }
        return false;
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performExtHapticFeedback(int i2, boolean z2) {
        if (this.mHapticFeedbackUtil == null) {
            return false;
        }
        HapticLog.printTrace("performExtHapticFeedback: effectId: " + i2 + ", always: " + z2);
        if (mExtHapticAlways && z2) {
            return this.mHapticFeedbackUtil.performExtHapticFeedback(i2, true);
        }
        return this.mHapticFeedbackUtil.performExtHapticFeedback(i2);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performHapticFeedback(int i2, boolean z2) {
        return performHapticFeedback((VibrationAttributes) null, i2, z2);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performHapticFeedback(VibrationAttributes vibrationAttributes, int i2, boolean z2) {
        int iObtainFeedBack;
        if (this.mHapticFeedbackUtil == null || (iObtainFeedBack = HapticCompat.obtainFeedBack(i2)) == -1) {
            return false;
        }
        try {
            HapticLog.printTrace("performHapticFeedback: attributes: " + vibrationAttributes + ", effectId: " + i2 + ", always: " + z2);
            if (PlatformConstants.romHapticVersion >= 1.2d && mPerformExtHapticFeedbackThreeParamsMethodExist) {
                return this.mHapticFeedbackUtil.performHapticFeedback(vibrationAttributes, iObtainFeedBack, z2);
            }
            return this.mHapticFeedbackUtil.performHapticFeedback(iObtainFeedBack, z2);
        } catch (Exception e2) {
            Log.e(TAG, "Failed to perform haptic!", e2);
            return false;
        }
    }

    @RequiresPermission("android.permission.VIBRATE")
    @RequiresApi(31)
    public boolean performExtHapticFeedback(int i2, int i3, boolean z2) {
        if (PlatformConstants.romHapticVersion >= 1.1d) {
            if (this.mHapticFeedbackUtil == null) {
                return false;
            }
            HapticLog.printTrace("performExtHapticFeedback: audioAttributesUsage: " + i2 + ", effectId: " + i3 + ", always: " + z2);
            return this.mHapticFeedbackUtil.performExtHapticFeedback(i2, i3, z2);
        }
        return performExtHapticFeedback(i3, z2);
    }

    @RequiresPermission("android.permission.VIBRATE")
    @RequiresApi(33)
    public boolean performExtHapticFeedback(VibrationAttributes vibrationAttributes, int i2, boolean z2) {
        try {
            if (PlatformConstants.romHapticVersion >= 1.2d && mPerformExtHapticFeedbackThreeParamsMethodExist) {
                if (this.mHapticFeedbackUtil == null) {
                    return false;
                }
                HapticLog.printTrace("performExtHapticFeedback: attributes: " + vibrationAttributes + ", effectId: " + i2 + ", always: " + z2);
                return this.mHapticFeedbackUtil.performExtHapticFeedback(vibrationAttributes, i2, z2);
            }
            return performExtHapticFeedback(i2, z2);
        } catch (Exception e2) {
            Log.e(TAG, "Failed to perform ext haptic!", e2);
            return false;
        }
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performHapticFeedback(int i2) {
        return performHapticFeedback((VibrationAttributes) null, i2);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performHapticFeedback(VibrationAttributes vibrationAttributes, int i2) {
        return performHapticFeedback(vibrationAttributes, i2, false);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performHapticFeedback(int i2, double d2, String str) {
        return performHapticFeedback((VibrationAttributes) null, i2, d2, str);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performHapticFeedback(VibrationAttributes vibrationAttributes, int i2, double d2, String str) {
        int iObtainFeedBack;
        if (this.mHapticFeedbackUtil == null || !mIsSupportHapticWithReason || (iObtainFeedBack = HapticCompat.obtainFeedBack(i2)) == -1) {
            return false;
        }
        try {
            HapticLog.printTrace("performHapticFeedback: attributes: " + vibrationAttributes + ", effectId: " + i2 + ", suitIntensity: " + d2 + ", reason: " + str);
            if (PlatformConstants.romHapticVersion >= 1.2d && mPerformHapticFeedbackFourParamsMethod2Exist) {
                return this.mHapticFeedbackUtil.performHapticFeedback(vibrationAttributes, iObtainFeedBack, d2, str);
            }
            return this.mHapticFeedbackUtil.performHapticFeedback(iObtainFeedBack, d2, str);
        } catch (Exception e2) {
            Log.e(TAG, "Failed to perform haptic!", e2);
            return false;
        }
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performExtHapticFeedback(int i2, double d2, String str) {
        if (this.mHapticFeedbackUtil == null || !mIsSupportExtHapticWithReason) {
            return false;
        }
        HapticLog.printTrace("performExtHapticFeedback: effectId: " + i2 + ", suitIntensity: " + d2 + ", reason: " + str);
        return this.mHapticFeedbackUtil.performExtHapticFeedback(i2, d2, str);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performExtHapticFeedback(Uri uri, boolean z2) {
        if (this.mHapticFeedbackUtil == null) {
            return false;
        }
        HapticLog.printTrace("performExtHapticFeedback: uri: " + uri + ", always: " + z2);
        if (mCanCheckExtHaptic && z2) {
            return this.mHapticFeedbackUtil.performExtHapticFeedback(uri, true);
        }
        return this.mHapticFeedbackUtil.performExtHapticFeedback(uri);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public boolean performExtHapticFeedback(Uri uri) {
        if (this.mHapticFeedbackUtil == null) {
            return false;
        }
        HapticLog.printTrace("performExtHapticFeedback: uri: " + uri);
        return this.mHapticFeedbackUtil.performExtHapticFeedback(uri);
    }
}
