package systemui.plugin.eventtracking;

import android.content.Context;
import com.xiaomi.onetrack.Configuration;
import com.xiaomi.onetrack.OneTrack;
import java.util.Map;
import kotlin.jvm.internal.D;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes5.dex */
public final class DynamicIslandTracker implements Tracker {
    private final String appId = "31000402443";
    private final OneTrack oneTrack;

    public DynamicIslandTracker(Context context) {
        OneTrack oneTrackCreateInstance = OneTrack.createInstance(context, new Configuration.Builder().setAppId("31000402443").setAutoTrackActivityAction(false).build());
        n.f(oneTrackCreateInstance, "createInstance(...)");
        this.oneTrack = oneTrackCreateInstance;
        OneTrack.setUseSystemNetTrafficOnly();
    }

    @Override // systemui.plugin.eventtracking.Tracker
    public void track(String eventID, Map<String, ? extends Object> params) {
        n.g(eventID, "eventID");
        n.g(params, "params");
        D.d(params).put("data_version", 202509);
        this.oneTrack.track(eventID, params);
    }
}
