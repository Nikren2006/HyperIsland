package systemui.plugin.eventtracking;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.onetrack.Configuration;
import com.xiaomi.onetrack.OneTrack;
import java.util.Map;
import kotlin.jvm.internal.D;
import kotlin.jvm.internal.n;
import miui.os.Build;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;
import systemui.plugin.eventtracking.utils.OaidUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class DeviceCenterTracker implements Tracker {
    private final String appId = "31000000683";
    private final String channel = "circulate";
    private final OneTrack oneTrack;

    public DeviceCenterTracker(Context context) {
        OneTrack oneTrackCreateInstance = OneTrack.createInstance(context, new Configuration.Builder().setAppId("31000000683").setChannel("circulate").setMode(OneTrack.Mode.PLUGIN).setAutoTrackActivityAction(false).build());
        n.f(oneTrackCreateInstance, "createInstance(...)");
        this.oneTrack = oneTrackCreateInstance;
        oneTrackCreateInstance.setEventHook(new OneTrack.IEventHook() { // from class: systemui.plugin.eventtracking.DeviceCenterTracker.1
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
        mapD.put("is_tablet", String.valueOf(Build.IS_TABLET));
        mapD.put("is_advanced_version", String.valueOf(!Build.IS_MIUI_LITE_VERSION));
        this.oneTrack.track(eventID, params);
    }
}
