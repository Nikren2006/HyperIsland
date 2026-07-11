package miui.systemui.controlcenter.events;

import F0.e;
import G0.a;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterEventTracker_Factory implements e {
    private final a windowViewProvider;

    public ControlCenterEventTracker_Factory(a aVar) {
        this.windowViewProvider = aVar;
    }

    public static ControlCenterEventTracker_Factory create(a aVar) {
        return new ControlCenterEventTracker_Factory(aVar);
    }

    public static ControlCenterEventTracker newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl) {
        return new ControlCenterEventTracker(controlCenterWindowViewImpl);
    }

    @Override // G0.a
    public ControlCenterEventTracker get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get());
    }
}
