package systemui.plugin.eventtracking.trackers;

import kotlin.jvm.internal.n;
import miui.os.Build;
import systemui.plugin.eventtracking.EventTracker;
import systemui.plugin.eventtracking.events.ControlsDeviceClickEvent;
import systemui.plugin.eventtracking.events.ControlsDeviceLongClickEvent;
import systemui.plugin.eventtracking.events.ControlsEditClickEvent;
import systemui.plugin.eventtracking.events.ControlsEditOperationEvent;
import systemui.plugin.eventtracking.events.ControlsScanEvent;
import systemui.plugin.eventtracking.events.ControlsSetupExposedClickEvent;
import systemui.plugin.eventtracking.events.ControlsSetupExposedDismissClickEvent;
import systemui.plugin.eventtracking.events.ControlsSetupExposedDismissDialogEvent;
import systemui.plugin.eventtracking.events.ControlsSetupExposedEvent;
import systemui.plugin.eventtracking.events.SecondaryVolumeQSClickEvent;
import systemui.plugin.eventtracking.events.SecondaryVolumeSeekerAdjustEvent;
import systemui.plugin.eventtracking.events.SecondaryVolumeTimerAdjustEvent;
import systemui.plugin.eventtracking.events.SmartHomeClickEvent;
import systemui.plugin.eventtracking.events.SmartHomeExposeEvent;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;
import systemui.plugin.eventtracking.utils.EventsUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class ControlsEventTracker {
    public static final String BELL_VOLUME_BAR = "铃声音量条";
    public static final String CLOCK_VOLUME_BAR = "闹钟音量条";
    public static final String COMPONENT_MIHOME = "mihome";
    public static final String DND = "勿扰模式";
    public static final String DND_SLIDER_TIMING = "勿扰模式滑动定时";
    public static final String EDIT_OP_ADD = "add";
    public static final String EDIT_OP_DRAG = "drag";
    public static final String EDIT_OP_REMOVE = "remove";
    public static final String EVENT_SMART_HOME_CLICK_TIP = " 178.1.1.1.18769";
    public static final String EVENT_SMART_HOME_EXPOSE_TIP = "178.1.1.1.18768";
    public static final ControlsEventTracker INSTANCE = new ControlsEventTracker();
    public static final String JUMP_TO_APP = "跳转至app";
    public static final String JUMP_TO_SECOND_PAGE = "跳转至二级页";
    public static final String LONG_CLICK_VOLUNEBAR_SECONDARY = "长按音量条二级";
    public static final String MEDIA_VOLUME_BAR = "媒体音量条";
    public static final String MUTE_SLIDER_TIMING = "静音滑动定时";
    public static final String NOTIFY_VOLUME_BAR = "通知音量条";
    public static final String ORIENTATION_LANDSCAPE = "横屏";
    public static final String ORIENTATION_PORTRAIT = "竖屏";
    public static final String QS_NAME_DND = "DND";
    public static final String QS_NAME_SLIENT = "Slient";
    private static final String QS_STATUS_OFF = "关闭";
    private static final String QS_STATUS_ON = "开启";
    public static final String QS_STYLE_BUTTON = "按钮";
    public static final String QS_STYLE_Seekbar = "滑动条";
    public static final String QUIT = "静音";
    public static final String Secondary_MORE_BUTTON = "二级页更多按钮";
    public static final String Secondary_Volume_open = "二级页展开";
    private static final String TAG = "ControlsEventTracker";
    public static final String VOLUME_BAR_SECONDARY = "控制中心音量条二级";

    private ControlsEventTracker() {
    }

    public static final void trackControlCenterSecondaryVolumeQSClickEvent(String screenType, int i2, boolean z2, boolean z3) {
        n.g(screenType, "screenType");
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        EventsUtils eventsUtils = EventsUtils.INSTANCE;
        String getDate = eventsUtils.getGetDate();
        EventTracker.Companion companion2 = EventTracker.Companion;
        companion.track(new SecondaryVolumeQSClickEvent(getDate, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, eventsUtils.getVersionName(), "按钮", z2 ? 1 : 0, i2 == 1 ? ORIENTATION_PORTRAIT : ORIENTATION_LANDSCAPE, z2 ? QS_NAME_DND : QS_NAME_SLIENT, "二级页更多按钮", "长按音量条二级", z2 ? DND : QUIT, z3 ? "开启" : "关闭", null, 8192, null));
    }

    public static final void trackControlCenterSecondaryVolumeTimerEvent(String screenType, boolean z2, double d2, double d3) {
        n.g(screenType, "screenType");
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        EventTracker.Companion companion2 = EventTracker.Companion;
        companion.track(new SecondaryVolumeTimerAdjustEvent(companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), d2, d3, z2 ? DND_SLIDER_TIMING : MUTE_SLIDER_TIMING, "控制中心音量条二级", null, 256, null));
    }

    public static final void trackSecondaryVolumeSeekerAdjustEvent(boolean z2, int i2, String screenType, int i3, int i4, int i5) {
        n.g(screenType, "screenType");
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        EventTracker.Companion companion2 = EventTracker.Companion;
        companion.track(new SecondaryVolumeSeekerAdjustEvent(companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i4, i5, i3 != 2 ? i3 != 3 ? i3 != 4 ? NOTIFY_VOLUME_BAR : CLOCK_VOLUME_BAR : "媒体音量条" : BELL_VOLUME_BAR, companion2.causeExtremumTypeInPanel(z2, i2, i3, i4, i5), "控制中心音量条二级", null, 512, null));
    }

    public final void trackControlsScanEvent(int i2, int i3, String str) {
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        n.d(str);
        companion.track(new ControlsScanEvent(i2, i3, str));
    }

    public final void trackDeviceClickEvent(int i2, int i3, String str) {
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        n.d(str);
        companion.track(new ControlsDeviceClickEvent(i2, i3, str));
    }

    public final void trackDeviceLongClickEvent(int i2, int i3, String str) {
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        n.d(str);
        companion.track(new ControlsDeviceLongClickEvent(i2, i3, str));
    }

    public final void trackEditClickEvent(boolean z2, String str) {
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        if (z2) {
            str = "self";
        } else {
            n.d(str);
        }
        companion.track(new ControlsEditClickEvent(str));
    }

    public final void trackEditOperationEvent(int i2, String str) {
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        n.d(str);
        companion.track(new ControlsEditOperationEvent(i2, str));
    }

    public final void trackSetupExposedClickEvent(String str) {
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        String str2 = Build.IS_INTERNATIONAL_BUILD ? "global" : "cn";
        n.d(str);
        companion.track(new ControlsSetupExposedClickEvent(str2, str));
    }

    public final void trackSetupExposedDismissDialogEvent(boolean z2) {
        BaseEventTracker.Companion.get().track(new ControlsSetupExposedDismissDialogEvent(z2 ? "Positive" : "Negative"));
    }

    public final void trackSetupExposedDismissEvent(String str) {
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        String str2 = Build.IS_INTERNATIONAL_BUILD ? "global" : "cn";
        n.d(str);
        companion.track(new ControlsSetupExposedDismissClickEvent(str2, str));
    }

    public final void trackSetupExposedEvent(String str) {
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        String str2 = Build.IS_INTERNATIONAL_BUILD ? "global" : "cn";
        n.d(str);
        companion.track(new ControlsSetupExposedEvent(str2, str));
    }

    public final void trackSmartHomeClickEvent(int i2, boolean z2, String str, String str2) {
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        EventsUtils eventsUtils = EventsUtils.INSTANCE;
        String getDate = eventsUtils.getGetDate();
        String versionName = eventsUtils.getVersionName();
        String str3 = i2 == 1 ? ORIENTATION_PORTRAIT : ORIENTATION_LANDSCAPE;
        String str4 = z2 ? JUMP_TO_APP : JUMP_TO_SECOND_PAGE;
        n.d(str);
        n.d(str2);
        companion.track(new SmartHomeClickEvent(getDate, versionName, str3, str4, str, str2, EVENT_SMART_HOME_CLICK_TIP));
    }

    public final void trackSmartHomeExposeEvent(int i2, boolean z2, String str, String str2) {
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        EventsUtils eventsUtils = EventsUtils.INSTANCE;
        String getDate = eventsUtils.getGetDate();
        String versionName = eventsUtils.getVersionName();
        String str3 = i2 == 1 ? ORIENTATION_PORTRAIT : ORIENTATION_LANDSCAPE;
        String str4 = z2 ? JUMP_TO_APP : JUMP_TO_SECOND_PAGE;
        n.d(str);
        n.d(str2);
        companion.track(new SmartHomeExposeEvent(getDate, versionName, str3, str4, str, str2, EVENT_SMART_HOME_EXPOSE_TIP));
    }
}
