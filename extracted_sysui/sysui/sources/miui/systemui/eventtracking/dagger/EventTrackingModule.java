package miui.systemui.eventtracking.dagger;

import X0.a;
import android.content.Context;
import java.util.Optional;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.dagger.qualifiers.SystemUI;
import systemui.plugin.eventtracking.dagger.qualifiers.EventTracking;

/* JADX INFO: loaded from: classes3.dex */
public final class EventTrackingModule {
    @EventTracking
    public final Context providesEventTrackingContext(@SystemUI Optional<Context> sysuiContext, @Plugin Context pluginContext) {
        n.g(sysuiContext, "sysuiContext");
        n.g(pluginContext, "pluginContext");
        return ((Context) a.a(sysuiContext, pluginContext)).getApplicationContext();
    }
}
