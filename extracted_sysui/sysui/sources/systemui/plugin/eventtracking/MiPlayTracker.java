package systemui.plugin.eventtracking;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.onetrack.Configuration;
import com.xiaomi.onetrack.OneTrack;
import java.util.Map;
import kotlin.jvm.internal.D;
import kotlin.jvm.internal.n;
import miui.os.Build;
import miuix.view.HapticCompat;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;
import systemui.plugin.eventtracking.utils.EventsUtils;
import systemui.plugin.eventtracking.utils.OaidUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class MiPlayTracker implements Tracker {
    private final String appId = "31000000505";
    private final String channel = "miplay-systemui";
    private final OneTrack oneTrack;

    public MiPlayTracker(Context context) {
        OneTrack oneTrackCreateInstance = OneTrack.createInstance(context, new Configuration.Builder().setAppId("31000000505").setChannel("miplay-systemui").setMode(OneTrack.Mode.APP).setAutoTrackActivityAction(false).build());
        n.f(oneTrackCreateInstance, "createInstance(...)");
        this.oneTrack = oneTrackCreateInstance;
        oneTrackCreateInstance.setEventHook(new OneTrack.IEventHook() { // from class: systemui.plugin.eventtracking.MiPlayTracker.1
            @Override // com.xiaomi.onetrack.OneTrack.IEventHook
            public boolean isCustomDauEvent(String str) {
                return TextUtils.equals(str, MiPlayEventsKt.EVENT_ENTERTAINMENT_DAU);
            }

            @Override // com.xiaomi.onetrack.OneTrack.IEventHook
            public boolean isRecommendEvent(String str) {
                return false;
            }
        });
        OneTrack.setUseSystemNetTrafficOnly();
        OaidUtils.INSTANCE.initOaid(context, oneTrackCreateInstance);
    }

    @Override // systemui.plugin.eventtracking.Tracker
    public void track(String eventID, Map<String, ? extends Object> params) {
        n.g(eventID, "eventID");
        n.g(params, "params");
        Map mapD = D.d(params);
        mapD.put("is_tablet", Boolean.valueOf(Build.IS_TABLET));
        EventsUtils eventsUtils = EventsUtils.INSTANCE;
        mapD.put("plugin_version_name", eventsUtils.getVersionName());
        mapD.put("plugin_version_code", Long.valueOf(eventsUtils.getVersionCode()));
        mapD.put("miaobo_version", HapticCompat.HapticVersion.HAPTIC_VERSION_2);
        this.oneTrack.track(eventID, params);
    }
}
