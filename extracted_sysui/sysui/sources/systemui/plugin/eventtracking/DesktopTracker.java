package systemui.plugin.eventtracking;

import android.content.Context;
import com.xiaomi.onetrack.Configuration;
import com.xiaomi.onetrack.OneTrack;
import java.util.Map;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes5.dex */
public final class DesktopTracker implements Tracker {
    private final String appId = "31000000538";
    private final OneTrack oneTrack;

    public DesktopTracker(Context context) {
        OneTrack oneTrackCreateInstance = OneTrack.createInstance(context, new Configuration.Builder().setAppId("31000000538").setAutoTrackActivityAction(false).build());
        n.f(oneTrackCreateInstance, "createInstance(...)");
        this.oneTrack = oneTrackCreateInstance;
        OneTrack.setUseSystemNetTrafficOnly();
    }

    @Override // systemui.plugin.eventtracking.Tracker
    public void track(String eventID, Map<String, ? extends Object> params) {
        n.g(eventID, "eventID");
        n.g(params, "params");
        this.oneTrack.track(eventID, params);
    }
}
