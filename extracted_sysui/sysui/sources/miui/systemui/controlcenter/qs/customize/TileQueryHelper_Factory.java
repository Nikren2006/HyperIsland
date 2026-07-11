package miui.systemui.controlcenter.qs.customize;

import F0.d;
import F0.e;
import android.content.Context;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import com.android.systemui.settings.UserTracker;
import miui.systemui.util.SystemUIResourcesHelper;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes.dex */
public final class TileQueryHelper_Factory implements e {
    private final G0.a bgExecutorProvider;
    private final G0.a contextProvider;
    private final G0.a hostProvider;
    private final G0.a qsControllerProvider;
    private final G0.a sysUIContextProvider;
    private final G0.a sysUIResHelperProvider;
    private final G0.a userTrackerProvider;

    public TileQueryHelper_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        this.contextProvider = aVar;
        this.sysUIResHelperProvider = aVar2;
        this.sysUIContextProvider = aVar3;
        this.bgExecutorProvider = aVar4;
        this.qsControllerProvider = aVar5;
        this.hostProvider = aVar6;
        this.userTrackerProvider = aVar7;
    }

    public static TileQueryHelper_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        return new TileQueryHelper_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7);
    }

    public static TileQueryHelper newInstance(Context context, SystemUIResourcesHelper systemUIResourcesHelper, Context context2, DelayableExecutor delayableExecutor, E0.a aVar, MiuiQSHost miuiQSHost, UserTracker userTracker) {
        return new TileQueryHelper(context, systemUIResourcesHelper, context2, delayableExecutor, aVar, miuiQSHost, userTracker);
    }

    @Override // G0.a
    public TileQueryHelper get() {
        return newInstance((Context) this.contextProvider.get(), (SystemUIResourcesHelper) this.sysUIResHelperProvider.get(), (Context) this.sysUIContextProvider.get(), (DelayableExecutor) this.bgExecutorProvider.get(), d.a(this.qsControllerProvider), (MiuiQSHost) this.hostProvider.get(), (UserTracker) this.userTrackerProvider.get());
    }
}
