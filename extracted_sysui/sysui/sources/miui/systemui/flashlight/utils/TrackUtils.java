package miui.systemui.flashlight.utils;

import android.content.Context;
import kotlin.jvm.internal.n;
import miui.os.Build;
import miui.systemui.util.CommonUtils;
import systemui.plugin.eventtracking.EventTracker;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackUtils {
    private static final String CLICK_AFTER_STATUS_OFF = "关闭";
    private static final String CLICK_AFTER_STATUS_ON = "打开";
    public static final String CLICK_LOCATION_KEYGUARD = "锁屏通知栏";
    public static final String CLICK_LOCATION_NOTIFICATION = "桌面通知栏";
    public static final String CLICK_LOCATION_SET = "设置页";
    private static final String COMMON_PAR_MODEL_TYPE_PHONE = "手机";
    private static final String COMMON_PAR_MODEL_TYPE_TABLE = "平板";
    private static final String COMMON_PAR_PHONE_TYPE_COM = "直板";
    private static final String COMMON_PAR_PHONE_TYPE_FLIP = "flip";
    private static final String COMMON_PAR_PHONE_TYPE_FOLD = "fold";
    private static final String COMMON_PAR_PHONE_TYPE_NULL = "无";
    private static final String COMMON_PAR_SCREEN_TYPE_INNER = "内屏";
    private static final String COMMON_PAR_SCREEN_TYPE_NULL = "无";
    private static final String COMMON_PAR_SCREEN_TYPE_OUTER = "外屏";
    public static final String ENTER_FROM = "flashlight_enter_from";
    private static final String ENTER_WAY_CENTER = "控制中心长按";
    private static final String ENTER_WAY_KEYGUARD = "锁屏滑动";
    private static final String ENTER_WAY_NOTIFICATION = "通知";
    private static final String ENTER_WAY_NULL = "无";
    public static final TrackUtils INSTANCE = new TrackUtils();

    private TrackUtils() {
    }

    private final BaseEventTracker getBaseEventTracker(Context context) {
        BaseEventTracker.Companion companion = BaseEventTracker.Companion;
        if (companion.get().getEventTracker() == null) {
            BaseEventTracker.Companion companion2 = companion.get();
            Context applicationContext = context.getApplicationContext();
            n.f(applicationContext, "getApplicationContext(...)");
            companion2.setEventTracker(new EventTracker(applicationContext));
        }
        return companion.get();
    }

    private final String getModelType() {
        return Build.IS_TABLET ? COMMON_PAR_MODEL_TYPE_TABLE : COMMON_PAR_MODEL_TYPE_PHONE;
    }

    private final String getPhoneType() {
        return CommonUtils.INSTANCE.getIS_FOLD() ? "fold" : CommonUtils.isFlipDevice() ? "flip" : !Build.IS_TABLET ? "直板" : "无";
    }

    private final String getScreenType(Context context) {
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        return (commonUtils.getIS_FOLD() && CommonUtils.isScreenLayoutLarge(context)) ? COMMON_PAR_SCREEN_TYPE_INNER : ((!commonUtils.getIS_FOLD() || CommonUtils.isScreenLayoutLarge(context)) && !(CommonUtils.isFlipDevice() && CommonUtils.isTinyScreen(context))) ? (!CommonUtils.isFlipDevice() || CommonUtils.isTinyScreen(context)) ? "无" : COMMON_PAR_SCREEN_TYPE_INNER : COMMON_PAR_SCREEN_TYPE_OUTER;
    }

    public final void trackClick(Context context, boolean z2, String location) {
        n.g(context, "context");
        n.g(location, "location");
        getBaseEventTracker(context).track(new FlashlightClickEvent(getModelType(), getPhoneType(), getScreenType(context), z2 ? CLICK_AFTER_STATUS_ON : "关闭", location, null, 32, null));
    }

    public final void trackEnter(Context context, int i2, int i3) {
        n.g(context, "context");
        getBaseEventTracker(context).track(new FlashlightEnterEvent(getModelType(), getPhoneType(), getScreenType(context), i2 != 0 ? i2 != 1 ? i2 != 2 ? "无" : ENTER_WAY_KEYGUARD : ENTER_WAY_NOTIFICATION : ENTER_WAY_CENTER, i3, null, 32, null));
    }

    public final void trackSlide(Context context, int i2, int i3) {
        n.g(context, "context");
        getBaseEventTracker(context).track(new FlashlightSlideEvent(getModelType(), getPhoneType(), getScreenType(context), i2, i3, null, 32, null));
    }
}
