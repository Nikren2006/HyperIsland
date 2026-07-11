package systemui.plugin.eventtracking.trackers;

import android.util.Log;
import com.miui.misight.MiEvent;
import com.miui.misight.MiSight;
import systemui.plugin.eventtracking.events.AdjustVolumeStreamEvent;
import systemui.plugin.eventtracking.events.DismissDialogEvent;
import systemui.plugin.eventtracking.events.ExpandClickEvent;
import systemui.plugin.eventtracking.events.RingerModeClickEvent;
import systemui.plugin.eventtracking.events.RingerModeExpandClickEvent;
import systemui.plugin.eventtracking.events.ShowDialogEvent;
import systemui.plugin.eventtracking.events.TimerEvent;

/* JADX INFO: loaded from: classes5.dex */
public class VolumeEventTracker {
    private static final String ACTION_ENTER_DND = "enter_dnd";
    private static final String ACTION_ENTER_SILENT = "enter_silent";
    private static final String ACTION_EXIT_DND = "exit_dnd";
    private static final String ACTION_EXIT_SILENT = "exit_silent";
    private static final String ACTION_EXPAND = "expand";
    private static final String ACTION_UNKNOWN = "unknown";
    public static final String CLICK_TYPE_DND = "dnd";
    public static final String CLICK_TYPE_SILENT = "silent";
    public static final int ERROR_022001 = 923022001;
    public static final int ERROR_022001_TYPE_1 = 1;
    public static final int ERROR_022001_TYPE_2 = 2;
    public static final String STREAM_ALARM = "stream_alarm";
    public static final String STREAM_MEDIA = "stream_music";
    public static final String STREAM_NOTIFICATION = "stream_notification";
    public static final String STREAM_OTHER = "stream_other";
    public static final String STREAM_RING = "stream_ring";
    private static final String TAG = "VolumeEventTracker";
    private static final String TYPE_DND_TIMER = "dnd_timer";
    public static final String TYPE_FROM_KEY_DOWN = "from_key_down";
    public static final String TYPE_FROM_KEY_UP = "from_key_up";
    private static final String TYPE_SILENT_TIMER = "silent_timer";

    private VolumeEventTracker() {
    }

    public static void trackAdjustVolumeStream(String str) {
        BaseEventTracker.get().track(new AdjustVolumeStreamEvent(str));
    }

    public static void trackClickExpandRingerBtn(boolean z2) {
        BaseEventTracker.get().track(new RingerModeExpandClickEvent(z2 ? CLICK_TYPE_DND : "silent"));
    }

    public static void trackClickRingerBtn(boolean z2, boolean z3) {
        BaseEventTracker.get().track(new RingerModeClickEvent(z2 ? z3 ? ACTION_ENTER_DND : ACTION_EXIT_DND : z3 ? ACTION_ENTER_SILENT : ACTION_EXIT_SILENT));
    }

    public static void trackError(int i2, int i3) {
        Log.e(TAG, "trackError: id=" + i2 + " type=" + i3);
        MiEvent miEvent = new MiEvent(i2);
        miEvent.addInt("type", i3);
        MiSight.sendEvent(miEvent);
    }

    public static void trackExpandBtn() {
        BaseEventTracker.get().track(new ExpandClickEvent(ACTION_EXPAND));
    }

    public static void trackTimerDuration(String str) {
        BaseEventTracker.get().track(new TimerEvent(TYPE_DND_TIMER, str));
    }

    public static void trackVolumeDismiss(boolean z2, String str) {
        BaseEventTracker.get().track(new DismissDialogEvent(z2 ? ACTION_EXPAND : "collpased", str));
    }

    public static void trackVolumeShow(String str) {
        BaseEventTracker.get().track(new ShowDialogEvent(str));
    }
}
