package miui.systemui.util;

import I0.J;
import I0.K;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.airbnb.lottie.LottieAnimationView;
import java.util.Set;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.notification.focus.Const;
import miui.systemui.quicksettings.common.R;

/* JADX INFO: loaded from: classes4.dex */
public final class LottieResUtils {
    public static final LottieResUtils INSTANCE = new LottieResUtils();
    private static final Set<String> targetResources = J.a(DynamicIslandConstants.MEDIA_SHARE_BITMAP);
    private static final Set<String> targetLottieRes = K.d("musicWave", "musicPause");

    private LottieResUtils() {
    }

    public static /* synthetic */ void cancelLottieAnimate$default(LottieResUtils lottieResUtils, LottieAnimationView lottieAnimationView, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        lottieResUtils.cancelLottieAnimate(lottieAnimationView, z2);
    }

    public static /* synthetic */ int getLottieRes$default(LottieResUtils lottieResUtils, String str, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = -1;
        }
        return lottieResUtils.getLottieRes(str, i2);
    }

    public static /* synthetic */ boolean isNoNeedToGetLottieRes$default(LottieResUtils lottieResUtils, String str, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = -1;
        }
        return lottieResUtils.isNoNeedToGetLottieRes(str, i2);
    }

    public final void cancelLottieAnimate(final LottieAnimationView lottieAnimationView, boolean z2) {
        if (CommonUtils.NOT_SUPPORT_LOTTIE || lottieAnimationView == null) {
            return;
        }
        lottieAnimationView.removeAllAnimatorListeners();
        lottieAnimationView.cancelAnimation();
        lottieAnimationView.clearAnimation();
        if (z2) {
            if (lottieAnimationView.isAttachedToWindow()) {
                lottieAnimationView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: miui.systemui.util.LottieResUtils$cancelLottieAnimate$lambda$1$$inlined$doOnDetach$1
                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View view) {
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewDetachedFromWindow(View view) {
                        lottieAnimationView.removeOnAttachStateChangeListener(this);
                        lottieAnimationView.setImageDrawable(null);
                    }
                });
            } else {
                lottieAnimationView.setImageDrawable(null);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final int getLottieRes(String str, int i2) {
        if (isNoNeedToGetLottieRes(str, i2)) {
            return -1;
        }
        if (str != null) {
            switch (str.hashCode()) {
                case -1618876223:
                    if (str.equals("broadcast")) {
                        return R.raw.broadcast;
                    }
                    break;
                case -1414900190:
                    if (str.equals("wifi_connecting")) {
                        return R.raw.wifi_connecting;
                    }
                    break;
                case -1197932781:
                    if (str.equals("disturb_mode_off")) {
                        return R.raw.lottie_disturb_mode_off;
                    }
                    break;
                case -967838410:
                    if (str.equals("alarmSmall")) {
                        return R.raw.alarm_small;
                    }
                    break;
                case -882969647:
                    if (str.equals("car_loading")) {
                        return R.raw.car_loading;
                    }
                    break;
                case -869926981:
                    if (str.equals("disturb_mode_on")) {
                        return R.raw.lottie_disturb_mode_on;
                    }
                    break;
                case -794405167:
                    if (str.equals("musicPause")) {
                        return R.raw.music_pause;
                    }
                    break;
                case -389327587:
                    if (str.equals("face_recognition_small")) {
                        return R.raw.face_recognition_small;
                    }
                    break;
                case -251047271:
                    if (str.equals(Const.FACE_RECOGNITION.FACE_RECOGNITION_SUCCESS)) {
                        return R.raw.face_recognition_success;
                    }
                    break;
                case -197802577:
                    if (str.equals(DynamicIslandConstants.FACE_RECOGNITION_FAILED_SMALL)) {
                        return R.raw.face_recognition_failed_small;
                    }
                    break;
                case -1029170:
                    if (str.equals("stopwatch_big")) {
                        return R.raw.mark_timing_big;
                    }
                    break;
                case 390224574:
                    if (str.equals("musicWave")) {
                        return R.raw.music_wave;
                    }
                    break;
                case 432723687:
                    if (str.equals(Const.FACE_RECOGNITION.FACE_RECOGNITION_FAILED)) {
                        return R.raw.face_recognition_failed;
                    }
                    break;
                case 665617053:
                    if (str.equals("quick_scan_code")) {
                        return R.raw.lottie_quick_scan_code;
                    }
                    break;
                case 904608125:
                    if (str.equals("silent_mode_off")) {
                        return R.raw.lottie_silent_mode_off;
                    }
                    break;
                case 1007624216:
                    if (str.equals("hourglass")) {
                        return R.raw.hourglass;
                    }
                    break;
                case 1137559569:
                    if (str.equals("silent_mode_on")) {
                        return R.raw.lottie_silent_mode_on;
                    }
                    break;
                case 1173011893:
                    if (str.equals("voiceWaveBig")) {
                        return R.raw.voice_wave_big;
                    }
                    break;
                case 1224578480:
                    if (str.equals("thinking")) {
                        return R.raw.thinking;
                    }
                    break;
                case 1266758625:
                    if (str.equals(DynamicIslandConstants.FACE_RECOGNITION_SUCCESS_SMALL)) {
                        return R.raw.face_recognition_success_small;
                    }
                    break;
                case 1407455445:
                    if (str.equals("face_recognition")) {
                        return R.raw.face_recognition;
                    }
                    break;
                case 1500650735:
                    if (str.equals("alarmBig")) {
                        return R.raw.alarm_big;
                    }
                    break;
                case 1627258969:
                    if (str.equals("hourglass_big")) {
                        return R.raw.hourglass_big;
                    }
                    break;
                case 1651731981:
                    if (str.equals(NotificationCompat.CATEGORY_STOPWATCH)) {
                        return R.raw.mark_timing;
                    }
                    break;
                case 1998814332:
                    if (str.equals("voiceWaveSmall")) {
                        return R.raw.voice_wave_small;
                    }
                    break;
            }
        }
        return 0;
    }

    public final boolean isNeedToGetLottieColor(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return targetResources.contains(str);
    }

    public final boolean isNeedUpdateColor(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return targetLottieRes.contains(str);
    }

    public final boolean isNoNeedToGetLottieRes(String str, int i2) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return (!CommonUtils.NOT_SUPPORT_LOTTIE || isNeedUpdateColor(str) || i2 == 1 || f1.o.v(str, "face_recognition", false, 2, null)) ? false : true;
    }
}
