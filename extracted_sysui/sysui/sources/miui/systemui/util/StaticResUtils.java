package miui.systemui.util;

import androidx.core.app.NotificationCompat;
import miui.systemui.quicksettings.common.R;

/* JADX INFO: loaded from: classes4.dex */
public final class StaticResUtils {
    public static final StaticResUtils INSTANCE = new StaticResUtils();

    private StaticResUtils() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final int getStaticRes(String str) {
        if (str != null) {
            switch (str.hashCode()) {
                case -967838410:
                    if (str.equals("alarmSmall")) {
                        return R.drawable.alarm_small;
                    }
                    break;
                case -1029170:
                    if (str.equals("stopwatch_big")) {
                        return R.drawable.mark_timing_big;
                    }
                    break;
                case 1007624216:
                    if (str.equals("hourglass")) {
                        return R.drawable.hourglass;
                    }
                    break;
                case 1173011893:
                    if (str.equals("voiceWaveBig")) {
                        return R.drawable.voice_wave_big;
                    }
                    break;
                case 1500650735:
                    if (str.equals("alarmBig")) {
                        return R.drawable.alarm_big;
                    }
                    break;
                case 1627258969:
                    if (str.equals("hourglass_big")) {
                        return R.drawable.hourglass_big;
                    }
                    break;
                case 1651731981:
                    if (str.equals(NotificationCompat.CATEGORY_STOPWATCH)) {
                        return R.drawable.mark_timing;
                    }
                    break;
                case 1998814332:
                    if (str.equals("voiceWaveSmall")) {
                        return R.drawable.voice_wave_small;
                    }
                    break;
            }
        }
        return 0;
    }
}
