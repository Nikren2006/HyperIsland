package miui.systemui.eventtracking.dagger;

import F0.e;
import F0.i;
import G0.a;
import android.content.Context;
import java.util.Optional;

/* JADX INFO: loaded from: classes3.dex */
public final class EventTrackingModule_ProvidesEventTrackingContextFactory implements e {
    private final EventTrackingModule module;
    private final a pluginContextProvider;
    private final a sysuiContextProvider;

    public EventTrackingModule_ProvidesEventTrackingContextFactory(EventTrackingModule eventTrackingModule, a aVar, a aVar2) {
        this.module = eventTrackingModule;
        this.sysuiContextProvider = aVar;
        this.pluginContextProvider = aVar2;
    }

    public static EventTrackingModule_ProvidesEventTrackingContextFactory create(EventTrackingModule eventTrackingModule, a aVar, a aVar2) {
        return new EventTrackingModule_ProvidesEventTrackingContextFactory(eventTrackingModule, aVar, aVar2);
    }

    public static Context providesEventTrackingContext(EventTrackingModule eventTrackingModule, Optional<Context> optional, Context context) {
        return (Context) i.d(eventTrackingModule.providesEventTrackingContext(optional, context));
    }

    @Override // G0.a
    public Context get() {
        return providesEventTrackingContext(this.module, (Optional) this.sysuiContextProvider.get(), (Context) this.pluginContextProvider.get());
    }
}
