package miui.systemui.devicecontrols.management;

import android.content.Context;
import com.android.systemui.settings.UserTracker;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsListingControllerImpl_Factory implements F0.e {
    private final G0.a contextProvider;
    private final G0.a executorProvider;
    private final G0.a userTrackerProvider;

    public ControlsListingControllerImpl_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.contextProvider = aVar;
        this.executorProvider = aVar2;
        this.userTrackerProvider = aVar3;
    }

    public static ControlsListingControllerImpl_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new ControlsListingControllerImpl_Factory(aVar, aVar2, aVar3);
    }

    public static ControlsListingControllerImpl newInstance(Context context, Executor executor, UserTracker userTracker) {
        return new ControlsListingControllerImpl(context, executor, userTracker);
    }

    @Override // G0.a
    public ControlsListingControllerImpl get() {
        return newInstance((Context) this.contextProvider.get(), (Executor) this.executorProvider.get(), (UserTracker) this.userTrackerProvider.get());
    }
}
