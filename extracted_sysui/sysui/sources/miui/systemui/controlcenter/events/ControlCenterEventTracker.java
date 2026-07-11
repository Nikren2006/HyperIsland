package miui.systemui.controlcenter.events;

import com.android.systemui.MiPlayDetailViewModel;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import systemui.plugin.eventtracking.EventTracker;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;
import systemui.plugin.eventtracking.utils.EventsUtils;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class ControlCenterEventTracker extends ControlCenterViewController<ControlCenterWindowViewImpl> {
    private static final String ADD_QS = "添加";
    public static final String AUTO_BRIGHTNESS = "自动亮度";
    public static final String BRIGHTNESS_BAR = "亮度条";
    public static final String CONTROLCENTER_BRIGHTNESS_BAR_SECONDARY = "控制中心亮度条二级";
    public static final String CONTROLCENTER_VOLUME_BAR_SECONDARY = "控制中心音量条二级";
    public static final String DARK_PATTERN = "深色模式";
    private static final String DELETE_QS = "删除";
    public static final String ELEMENT_STYLE_DETAIL_SETTINGS = "二级页更多按钮";
    public static final String ELEMENT_STYLE_DETAIL_TOGGLE = "二级页开关";
    public static final String ELEMENT_STYLE_EXPAND = "二级页展开";
    public static final String ELEMENT_STYLE_ITEM = "item";
    public static final String EYE_PROTECTION_PATTERN = "护眼模式";
    public static final String HOMEPAGE = "控制中心主页";
    private static final String IS_EDITED = "是";
    public static final String KEYGURAD_EXPAND = "锁屏下拉";
    public static final String LONG_CLICK_BRIGTHNESSBAR_SECONDARY = "长按亮度条二级";
    public static final String LONG_CLICK_VOLUNEBAR_SECONDARY = "长按音量条二级";
    public static final String NC_SWITCH = "通知中心切换";
    private static final String NOT_EDITED = "否";
    public static final String NOT_KEYGURAD_EXPAND = "非锁屏下拉";
    private static final String ORIENTATION_LANDSCAPE = "横屏";
    private static final String ORIENTATION_PORTRAIT = "竖屏";
    public static final String OTHER_PAGE = "其他二级页";
    public static final String QS_BRIGHTNESS_BAR = "brightness";
    public static final String QS_CLICK = "点击";
    public static final String QS_EXPAND = "展开";
    private static final String QS_STATUS_OFF = "关闭";
    private static final String QS_STATUS_ON = "开启";
    private static final String QS_STATUS_UNAVAILABLE = "不可用";
    public static final String QS_STYLE_BUTTON = "按钮";
    public static final String QS_STYLE_CARD = "卡片";
    public static final String QS_STYLE_SEEKER = "滑动条";
    public static final String QS_VOLUME_BAR = "volume";
    private static final String SAVE_REASON_BACK = "返回";
    private static final String SAVE_REASON_EXIT = "退出控制中心";
    private static final String SAVE_REASON_SAVE = "点击完成按钮";
    private static final String STYLE_COLLAPSED = "2*2";
    private static final String STYLE_EXPANDED = "4*1";
    private static final String TAG = "ControlCenterEventTracker";
    public static final String UNKNOWN = "未知";
    public static final String VOLUME_BAR = "媒体音量条";
    public static final Companion Companion = new Companion(null);
    private static String trackId = "";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTrackId() {
            return ControlCenterEventTracker.trackId;
        }

        public final void trackBrightnessSeekbarAdjustEvent(boolean z2, String screenType, int i2, int i3) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new BrightnessSeekbarAdjustEvent(companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, ControlCenterEventTracker.BRIGHTNESS_BAR, z2 ? ControlCenterEventTracker.CONTROLCENTER_BRIGHTNESS_BAR_SECONDARY : ControlCenterEventTracker.HOMEPAGE, EventsUtils.INSTANCE.getVersionName(), i2, i3, null, 256, null));
        }

        public final void trackBrightnessSeekbarLongClickEvent(String screenType, int i2, String style, String seekBarName) {
            n.g(screenType, "screenType");
            n.g(style, "style");
            n.g(seekBarName, "seekBarName");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new BrightnessSeekbarLongClickEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i2 == 1 ? "竖屏" : "横屏", style, seekBarName, null, 256, null));
        }

        public final void trackControlCenterEvent(boolean z2, boolean z3, String type, int i2) {
            n.g(type, "type");
            if (z3) {
                return;
            }
            if (z2) {
                trackSlideToNpvEvent(type, i2);
            } else {
                trackControlCenterOpenEvent(type, i2, ControlCenterEventTracker.NOT_KEYGURAD_EXPAND);
            }
        }

        public final void trackControlCenterOpenEvent(String screenType, int i2, String openWay) {
            n.g(screenType, "screenType");
            n.g(openWay, "openWay");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new ControlCenterOpenEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i2 == 1 ? "竖屏" : "横屏", openWay, null, 128, null));
        }

        public final void trackEditClickEvent(String screenType) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new EditClickEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), null, 32, null));
        }

        public final void trackEditPanelOpenEvent(String screenType) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new EditPanelOpenEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), null, 32, null));
        }

        public final void trackEditPanelQuitEvent(String screenType, boolean z2, int i2) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new EditPanelQuitEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i2 != 0 ? i2 != 1 ? i2 != 2 ? ControlCenterEventTracker.UNKNOWN : ControlCenterEventTracker.SAVE_REASON_SAVE : ControlCenterEventTracker.SAVE_REASON_EXIT : ControlCenterEventTracker.SAVE_REASON_BACK, z2 ? "是" : "否", null, 128, null));
        }

        public final void trackMiHomeClickEvent(String screenType, int i2, boolean z2, String str, String str2) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new SmartHomeClickEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i2 == 1 ? "竖屏" : "横屏", z2 ? ControlCenterEventTracker.STYLE_COLLAPSED : ControlCenterEventTracker.STYLE_EXPANDED, str == null ? ControlCenterEventTracker.UNKNOWN : str, str2 == null ? ControlCenterEventTracker.UNKNOWN : str2, null, 512, null));
        }

        public final void trackMiPlayClickEvent(String screenType, int i2, boolean z2, String str) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new MiPlayClickEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i2 == 1 ? "竖屏" : "横屏", z2 ? ControlCenterEventTracker.STYLE_COLLAPSED : ControlCenterEventTracker.STYLE_EXPANDED, str, MiPlayDetailViewModel.INSTANCE.getSourcePackage(), null, 512, null));
        }

        public final void trackMiPlayLongClickEvent(String screenType, int i2, boolean z2, String str) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new MiPlayClickEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i2 == 1 ? "竖屏" : "横屏", z2 ? ControlCenterEventTracker.STYLE_COLLAPSED : ControlCenterEventTracker.STYLE_EXPANDED, str, MiPlayDetailViewModel.INSTANCE.getSourcePackage(), null, 512, null));
        }

        public final void trackMiSmartHubClickedEvent(String screenType, int i2, boolean z2) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new MiSmartHubClickEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i2 == 1 ? "竖屏" : "横屏", z2 ? ControlCenterEventTracker.STYLE_COLLAPSED : ControlCenterEventTracker.STYLE_EXPANDED, null, 128, null));
        }

        public final void trackMiSmartHubLongClickedEvent(String screenType, int i2, boolean z2) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new MiSmartHubLongClickEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i2 == 1 ? "竖屏" : "横屏", z2 ? ControlCenterEventTracker.STYLE_COLLAPSED : ControlCenterEventTracker.STYLE_EXPANDED, null, 128, null));
        }

        public final void trackPanelSlideEvent(String screenType, int i2) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new PanelSlideEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, i2 == 1 ? "竖屏" : "横屏", EventsUtils.INSTANCE.getVersionName(), null, 64, null));
        }

        public final void trackQuickSettingsClickEvent(String screenType, String style, int i2, int i3, String qsName, String qsTitle, String elementStyle, String switchFrom, int i4) {
            n.g(screenType, "screenType");
            n.g(style, "style");
            n.g(qsName, "qsName");
            n.g(qsTitle, "qsTitle");
            n.g(elementStyle, "elementStyle");
            n.g(switchFrom, "switchFrom");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new QuickSettingsClickEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), style, i2 + 2, i3 == 1 ? "竖屏" : "横屏", qsName, qsTitle, elementStyle, switchFrom, i4 != 0 ? i4 != 1 ? i4 != 2 ? ControlCenterEventTracker.UNKNOWN : "开启" : "关闭" : ControlCenterEventTracker.QS_STATUS_UNAVAILABLE, null, 8192, null));
        }

        public final void trackQuickSettingsLongClickEvent(String screenType, int i2, String style, String qsName, int i3) {
            n.g(screenType, "screenType");
            n.g(style, "style");
            n.g(qsName, "qsName");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new QuickSettingsLongClickEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i3, i2 == 1 ? "竖屏" : "横屏", style, qsName, null, 512, null));
        }

        public final void trackSecondaryQuickSettingsClickEvent(String screenType, boolean z2, int i2, String qsName, int i3, String elementStyle, int i4, int i5) {
            n.g(screenType, "screenType");
            n.g(qsName, "qsName");
            n.g(elementStyle, "elementStyle");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new SecondaryBrightnessPanelClickEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), z2 ? "按钮" : ControlCenterEventTracker.QS_STYLE_CARD, i3, i2 == 1 ? "竖屏" : "横屏", qsName, i3 != 0 ? i3 != 1 ? ControlCenterEventTracker.EYE_PROTECTION_PATTERN : ControlCenterEventTracker.DARK_PATTERN : ControlCenterEventTracker.AUTO_BRIGHTNESS, elementStyle, i4 != 0 ? i4 != 1 ? ControlCenterEventTracker.HOMEPAGE : ControlCenterEventTracker.LONG_CLICK_BRIGTHNESSBAR_SECONDARY : "长按音量条二级", i5 != 0 ? i5 != 1 ? i5 != 2 ? ControlCenterEventTracker.UNKNOWN : "开启" : "关闭" : ControlCenterEventTracker.QS_STATUS_UNAVAILABLE, null, 8192, null));
        }

        public final void trackSlideToNpvEvent(String screenType, int i2) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new SlideToNpvEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i2 == 1 ? "竖屏" : "横屏", null, 64, null));
        }

        public final void trackSmartHomeClickEvent(String screenType, int i2, boolean z2, String str, String str2) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new SmartHomeClickEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i2 == 1 ? "竖屏" : "横屏", z2 ? ControlCenterEventTracker.STYLE_COLLAPSED : ControlCenterEventTracker.STYLE_EXPANDED, str == null ? ControlCenterEventTracker.UNKNOWN : str, str2 == null ? ControlCenterEventTracker.UNKNOWN : str2, null, 512, null));
        }

        public final void trackSmartHomeLongClickEvent(String screenType, int i2, boolean z2) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new SmartHomeLongClickEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i2 == 1 ? "竖屏" : "横屏", z2 ? ControlCenterEventTracker.STYLE_COLLAPSED : ControlCenterEventTracker.STYLE_EXPANDED, null, 128, null));
        }

        public final void trackTilesEditEvent(String screenType, boolean z2, String name, int i2) {
            n.g(screenType, "screenType");
            n.g(name, "name");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new TilesEditEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), z2 ? "删除" : ControlCenterEventTracker.ADD_QS, name, i2, null, 256, null));
        }

        public final void trackVolumeSeekbarAdjustEvent(int i2, int i3, boolean z2, String screenType, int i4, int i5) {
            n.g(screenType, "screenType");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new VolumeSeekbarAdjustEvent(companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, "媒体音量条", z2 ? "控制中心音量条二级" : ControlCenterEventTracker.HOMEPAGE, EventsUtils.INSTANCE.getVersionName(), i4, i5, companion2.causeExtremumType(i2, i3, i5), null, 512, null));
        }

        public final void trackVolumeSeekbarLongClickEvent(String screenType, int i2, String style, String seekBarName) {
            n.g(screenType, "screenType");
            n.g(style, "style");
            n.g(seekBarName, "seekBarName");
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            String trackId = getTrackId();
            EventTracker.Companion companion2 = EventTracker.Companion;
            companion.track(new VolumeSeekbarLongClickEvent(trackId, companion2.getMODEL_TYPE(), companion2.getPHONE_TYPE(), screenType, EventsUtils.INSTANCE.getVersionName(), i2 == 1 ? "竖屏" : "横屏", style, seekBarName, null, 256, null));
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlCenterEventTracker(ControlCenterWindowViewImpl windowView) {
        super(windowView);
        n.g(windowView, "windowView");
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        trackId = EventsUtils.INSTANCE.getGetDate();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStart() {
        trackId = EventsUtils.INSTANCE.getGetDate();
    }
}
