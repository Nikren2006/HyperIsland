package com.android.systemui.volume;

import android.content.Context;
import android.media.AudioSystem;
import android.util.Log;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.plugins.VolumeDialogController;
import java.util.Arrays;
import miui.systemui.controlcenter.flipQs.utils.QSFlipUtils;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.notification.focus.Const;
import systemui.plugin.eventtracking.events.DeviceCenterEventsKt;

/* JADX INFO: loaded from: classes2.dex */
public class Events {
    public static final int DISMISS_REASON_BACK_CLICKED = 7;
    public static final int DISMISS_REASON_CONFIGURATION_CHANGED = 8;
    public static final int DISMISS_REASON_CONTROL_CENTER_COLLAPSE = 9;
    public static final int DISMISS_REASON_DONE_CLICKED = 6;
    public static final int DISMISS_REASON_SCREEN_OFF = 4;
    public static final int DISMISS_REASON_SETTINGS_CLICKED = 5;
    public static final int DISMISS_REASON_TIMEOUT = 3;
    public static final int DISMISS_REASON_TOUCH_OUTSIDE = 1;
    public static final int DISMISS_REASON_UNKNOWN = 0;
    public static final int DISMISS_REASON_VOLUME_CONTROLLER = 2;
    public static final int EVENT_ACTIVE_STREAM_CHANGED = 2;
    public static final int EVENT_COLLECTION_STARTED = 5;
    public static final int EVENT_COLLECTION_STOPPED = 6;
    public static final int EVENT_DISMISS_DIALOG = 1;
    public static final int EVENT_EXPAND = 3;
    public static final int EVENT_EXTERNAL_RINGER_MODE_CHANGED = 12;
    public static final int EVENT_ICON_CLICK = 7;
    public static final int EVENT_INTERNAL_RINGER_MODE_CHANGED = 11;
    public static final int EVENT_KEY = 4;
    public static final int EVENT_LEVEL_CHANGED = 10;
    public static final int EVENT_MUTE_CHANGED = 15;
    public static final int EVENT_SETTINGS_CLICK = 8;
    public static final int EVENT_SHOW_DIALOG = 0;
    public static final int EVENT_SUPPRESSOR_CHANGED = 14;
    public static final int EVENT_TOUCH_LEVEL_CHANGED = 9;
    public static final int EVENT_TOUCH_LEVEL_DONE = 16;
    public static final int EVENT_ZEN_MODE_CHANGED = 13;
    public static final int ICON_STATE_MUTE = 2;
    public static final int ICON_STATE_UNKNOWN = 0;
    public static final int ICON_STATE_UNMUTE = 1;
    public static final int ICON_STATE_VIBRATE = 3;
    public static final int SHOW_REASON_CONTROL_CENTER_EXPAND = 4;
    public static final int SHOW_REASON_REMOTE_VOLUME_CHANGED = 2;
    public static final int SHOW_REASON_UNKNOWN = 0;
    public static final int SHOW_REASON_VOLUME_CHANGED = 1;
    private static final String TAG = "Events";
    public static Callback sCallback;
    public static final String[] DISMISS_REASONS = {"unknown", "touch_outside", "volume_controller", Const.Param.TIMEOUT_MIN, "screen_off", "settings_clicked", "done_clicked", "back_clicked", "config_changed", "control_center_collapse"};
    public static final String[] SHOW_REASONS = {"unknown", "volume_changed", "remote_volume_changed", "usb_temperature_above_threshold", DeviceCenterEventsKt.PAGE_DEVICE_CENTER_EXPOSE};
    private static final String[] EVENT_TAGS = {"show_dialog", "dismiss_dialog", "active_stream_changed", "expand", "key", "collection_started", "collection_stopped", "icon_click", "settings_click", "touch_level_changed", "level_changed", "internal_ringer_mode_changed", "external_ringer_mode_changed", "zen_mode_changed", "suppressor_changed", "mute_changed", "touch_level_done"};

    public interface Callback {
        void writeEvent(long j2, int i2, Object[] objArr);

        void writeState(long j2, VolumeDialogController.State state);
    }

    private static String iconStateToString(int i2) {
        if (i2 == 1) {
            return "unmute";
        }
        if (i2 == 2) {
            return "mute";
        }
        if (i2 == 3) {
            return QSFlipUtils.TILE_TYPE_VIBRATE;
        }
        return "unknown_state_" + i2;
    }

    private static String ringerModeToString(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? "unknown" : DynamicIslandEventsConstants.Values.VALUE_CHANNEL_TYPE_NORMAL : QSFlipUtils.TILE_TYPE_VIBRATE : "silent";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void writeEvent(Context context, int i2, Object... objArr) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("writeEvent ");
        sb.append(EVENT_TAGS[i2]);
        if (objArr != null && objArr.length > 0) {
            sb.append(" ");
            switch (i2) {
                case 0:
                    MetricsLogger.visible(context, MetricsEvent.VOLUME_DIALOG);
                    MetricsLogger.histogram(context, "volume_from_keyguard", ((Boolean) objArr[1]).booleanValue() ? 1 : 0);
                    sb.append(SHOW_REASONS[((Integer) objArr[0]).intValue()]);
                    sb.append(" keyguard=");
                    sb.append(objArr[1]);
                    break;
                case 1:
                    MetricsLogger.hidden(context, MetricsEvent.VOLUME_DIALOG);
                    sb.append(DISMISS_REASONS[((Integer) objArr[0]).intValue()]);
                    break;
                case 2:
                    MetricsLogger.action(context, MetricsEvent.ACTION_VOLUME_STREAM, ((Integer) objArr[0]).intValue());
                    sb.append(AudioSystem.streamToString(((Integer) objArr[0]).intValue()));
                    break;
                case 3:
                    MetricsLogger.visibility(context, MetricsEvent.VOLUME_DIALOG_DETAILS, ((Boolean) objArr[0]).booleanValue());
                    sb.append(objArr[0]);
                    break;
                case 4:
                    MetricsLogger.action(context, MetricsEvent.ACTION_VOLUME_KEY, ((Integer) objArr[1]).intValue());
                    sb.append(AudioSystem.streamToString(((Integer) objArr[0]).intValue()));
                    sb.append(' ');
                    sb.append(objArr[1]);
                    break;
                case 5:
                case 6:
                case 8:
                default:
                    sb.append(Arrays.asList(objArr));
                    break;
                case 7:
                    MetricsLogger.action(context, MetricsEvent.ACTION_VOLUME_ICON, ((Integer) objArr[1]).intValue());
                    sb.append(AudioSystem.streamToString(((Integer) objArr[0]).intValue()));
                    sb.append(' ');
                    sb.append(iconStateToString(((Integer) objArr[1]).intValue()));
                    break;
                case 9:
                case 10:
                case 15:
                    sb.append(AudioSystem.streamToString(((Integer) objArr[0]).intValue()));
                    sb.append(' ');
                    sb.append(objArr[1]);
                    break;
                case 11:
                    sb.append(ringerModeToString(((Integer) objArr[0]).intValue()));
                    break;
                case 12:
                    MetricsLogger.action(context, MetricsEvent.ACTION_RINGER_MODE, ((Integer) objArr[0]).intValue());
                    sb.append(ringerModeToString(((Integer) objArr[0]).intValue()));
                    break;
                case 13:
                    sb.append(zenModeToString(((Integer) objArr[0]).intValue()));
                    break;
                case 14:
                    sb.append(objArr[0]);
                    sb.append(' ');
                    sb.append(objArr[1]);
                    break;
                case 16:
                    MetricsLogger.action(context, MetricsEvent.ACTION_VOLUME_SLIDER, ((Integer) objArr[1]).intValue());
                    sb.append(AudioSystem.streamToString(((Integer) objArr[0]).intValue()));
                    sb.append(' ');
                    sb.append(objArr[1]);
                    break;
            }
        }
        Log.i(TAG, sb.toString());
        Callback callback = sCallback;
        if (callback != null) {
            callback.writeEvent(jCurrentTimeMillis, i2, objArr);
        }
    }

    public static void writeState(long j2, VolumeDialogController.State state) {
        Callback callback = sCallback;
        if (callback != null) {
            callback.writeState(j2, state);
        }
    }

    private static String zenModeToString(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? "unknown" : "alarms" : "no_interruptions" : "important_interruptions" : "off";
    }
}
