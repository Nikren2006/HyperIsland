package miui.systemui.util;

import android.os.SystemProperties;
import android.view.View;
import miuix.animation.FolmeEase;
import miuix.view.HapticCompat;

/* JADX INFO: loaded from: classes4.dex */
public interface HapticFeedback {
    public static final Companion Companion = Companion.$$INSTANCE;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final boolean IS_SUPPORT_LINEAR_MOTOR_VIBRATE = kotlin.jvm.internal.n.c(FolmeEase.LINEAR, SystemProperties.get("sys.haptic.motor"));
        private static final boolean IS_SUPPORT_HAPTIC_V2 = kotlin.jvm.internal.n.c(HapticCompat.HapticVersion.HAPTIC_VERSION_2, SystemProperties.get("sys.haptic.version", "1.0"));

        private Companion() {
        }

        public final boolean getIS_SUPPORT_HAPTIC_V2() {
            return IS_SUPPORT_HAPTIC_V2;
        }

        public final boolean getIS_SUPPORT_LINEAR_MOTOR_VIBRATE() {
            return IS_SUPPORT_LINEAR_MOTOR_VIBRATE;
        }
    }

    static /* synthetic */ void postHapticFeedback$default(HapticFeedback hapticFeedback, String str, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: postHapticFeedback");
        }
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        hapticFeedback.postHapticFeedback(str, z2);
    }

    void clearAllNotifications();

    void clearNotification();

    void click();

    void extHapticFeedback(int i2, boolean z2, int i3);

    void extLongHapticFeedback(int i2, boolean z2, int i3);

    void extMulHapticFeedback(int i2, int i3, boolean z2, boolean z3, int i4);

    void hapticFeedback(String str, boolean z2);

    boolean hapticFeedback(int i2, String str, boolean z2);

    boolean isSupportExtHapticFeedback(int i2);

    void longClick();

    void meshNormal();

    void postClick();

    void postHapticFeedback(int i2, View view, int i3);

    void postHapticFeedback(String str, boolean z2);

    void postLongClick();

    void postMeshNormal();

    void postPerformHapticFeedback(View view, int i2);
}
