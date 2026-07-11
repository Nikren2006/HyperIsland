package miuix.view;

import android.os.Looper;
import android.util.Log;
import android.view.View;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import miuix.HapticLog;
import miuix.core.util.SystemProperties;

/* JADX INFO: loaded from: classes5.dex */
public class HapticCompat {
    static final String TAG = "HapticCompat";
    public static String CURRENT_HAPTIC_VERSION = SystemProperties.get("sys.haptic.version", "1.0");
    private static List<HapticFeedbackProvider> sProviders = new ArrayList();
    private static final Executor sSingleThread = Executors.newSingleThreadExecutor();

    @Retention(RetentionPolicy.SOURCE)
    public @interface HapticVersion {
        public static final String HAPTIC_VERSION_1 = "1.0";
        public static final String HAPTIC_VERSION_2 = "2.0";
    }

    public static class WeakReferenceHandler implements Runnable {
        private final int mFeedbackConstant;
        private final WeakReference<View> mViewReference;

        public WeakReferenceHandler(View view, int i2) {
            this.mViewReference = new WeakReference<>(view);
            this.mFeedbackConstant = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            View view = this.mViewReference.get();
            if (view == null || !view.isAttachedToWindow()) {
                return;
            }
            try {
                HapticCompat.performHapticFeedback(view, this.mFeedbackConstant);
            } catch (Exception unused) {
            }
        }
    }

    static {
        loadProviders("miuix.view.LinearVibrator", "miuix.view.ExtendedVibrator");
    }

    private static boolean checkHapticVersion1FeedBackConstant(int i2) {
        int i3 = HapticFeedbackConstants.MIUI_HAPTIC_VERSION_1_START;
        if (i2 >= i3 && i2 <= HapticFeedbackConstants.MIUI_HAPTIC_VERSION_1_END) {
            return true;
        }
        Log.e(TAG, String.format("Illegal haptic version 1 feedback constant, should be in range [0x%08x..0x%08x]", Integer.valueOf(i3), Integer.valueOf(HapticFeedbackConstants.MIUI_HAPTIC_VERSION_1_END)));
        return false;
    }

    private static boolean checkHapticVersion2FeedBackConstant(int i2) {
        int i3 = HapticFeedbackConstants.MIUI_HAPTIC_VERSION_2_START;
        if (i2 >= i3 && i2 <= HapticFeedbackConstants.MIUI_HAPTIC_VERSION_2_END) {
            return true;
        }
        Log.e(TAG, String.format("Illegal haptic version 2 feedback constant, should be in range [0x%08x..0x%08x]", Integer.valueOf(i3), Integer.valueOf(HapticFeedbackConstants.MIUI_HAPTIC_VERSION_2_END)));
        return false;
    }

    public static boolean doesSupportHaptic(String str) {
        return CURRENT_HAPTIC_VERSION.equals(str);
    }

    private static void loadProviders(String... strArr) {
        for (String str : strArr) {
            Log.i(TAG, "loading provider: " + str);
            try {
                Class.forName(str, true, HapticCompat.class.getClassLoader());
            } catch (ClassNotFoundException e2) {
                Log.w(TAG, String.format("load provider %s failed.", str), e2);
            }
        }
    }

    public static int obtainFeedBack(int i2) {
        for (HapticFeedbackProvider hapticFeedbackProvider : sProviders) {
            if (hapticFeedbackProvider instanceof LinearVibrator) {
                return ((LinearVibrator) hapticFeedbackProvider).obtainFeedBack(i2);
            }
        }
        return -1;
    }

    @Keep
    public static boolean performHapticFeedback(@NonNull View view, int i2) {
        if (view == null) {
            Log.e(TAG, "performHapticFeedback: view is null!");
            return false;
        }
        if (i2 < 268435456) {
            Log.i(TAG, String.format("perform haptic: 0x%08x", Integer.valueOf(i2)));
            HapticLog.printTrace("performHapticFeedback view: " + view + ", feedbackConstant: " + i2);
            return view.performHapticFeedback(i2);
        }
        int i3 = HapticFeedbackConstants.MIUI_HAPTIC_END;
        if (i2 > i3) {
            Log.w(TAG, String.format("illegal feedback constant, should be in range [0x%08x..0x%08x]", 268435456, Integer.valueOf(i3)));
            return false;
        }
        Iterator<HapticFeedbackProvider> it = sProviders.iterator();
        while (it.hasNext()) {
            if (it.next().performHapticFeedback(view, i2)) {
                return true;
            }
        }
        return false;
    }

    @Keep
    public static void performHapticFeedbackAsync(@NonNull View view, int i2) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            sSingleThread.execute(new WeakReferenceHandler(view, i2));
        } else {
            performHapticFeedback(view, i2);
        }
    }

    @Keep
    public static void registerProvider(HapticFeedbackProvider hapticFeedbackProvider) {
        sProviders.add(hapticFeedbackProvider);
    }

    public static boolean supportLinearMotor(int i2) {
        if (i2 < 268435456) {
            Log.i(TAG, String.format("perform haptic: 0x%08x", Integer.valueOf(i2)));
            return false;
        }
        int i3 = HapticFeedbackConstants.MIUI_HAPTIC_END;
        if (i2 > i3) {
            Log.w(TAG, String.format("illegal feedback constant, should be in range [0x%08x..0x%08x]", 268435456, Integer.valueOf(i3)));
            return false;
        }
        for (HapticFeedbackProvider hapticFeedbackProvider : sProviders) {
            if ((hapticFeedbackProvider instanceof LinearVibrator) && ((LinearVibrator) hapticFeedbackProvider).supportLinearMotor(i2)) {
                return true;
            }
        }
        return false;
    }

    @Keep
    public static void performHapticFeedbackAsync(@NonNull View view, int i2, int i3) {
        if (doesSupportHaptic(HapticVersion.HAPTIC_VERSION_2)) {
            if (checkHapticVersion2FeedBackConstant(i2)) {
                performHapticFeedbackAsync(view, i2);
            }
        } else if (doesSupportHaptic("1.0")) {
            if (checkHapticVersion1FeedBackConstant(i3)) {
                performHapticFeedbackAsync(view, i3);
            }
        } else {
            Log.e(TAG, "Unexpected haptic version: " + CURRENT_HAPTIC_VERSION);
        }
    }

    public static boolean performHapticFeedback(@NonNull View view, int i2, int i3) {
        if (doesSupportHaptic(HapticVersion.HAPTIC_VERSION_2)) {
            if (checkHapticVersion2FeedBackConstant(i2)) {
                return performHapticFeedback(view, i2);
            }
            return false;
        }
        if (doesSupportHaptic("1.0")) {
            if (checkHapticVersion1FeedBackConstant(i3)) {
                return performHapticFeedback(view, i3);
            }
            return false;
        }
        Log.e(TAG, "Unexpected haptic version: " + CURRENT_HAPTIC_VERSION);
        return false;
    }
}
