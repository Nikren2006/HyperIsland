package miui.systemui.handles;

import F0.f;
import android.view.View;
import kotlin.jvm.functions.Function1;
import miui.systemui.handles.RegionSamplingHelperRefactor;

/* JADX INFO: loaded from: classes3.dex */
public final class RegionSamplingHelperRefactor_Factory_Impl implements RegionSamplingHelperRefactor.Factory {
    private final C0669RegionSamplingHelperRefactor_Factory delegateFactory;

    public RegionSamplingHelperRefactor_Factory_Impl(C0669RegionSamplingHelperRefactor_Factory c0669RegionSamplingHelperRefactor_Factory) {
        this.delegateFactory = c0669RegionSamplingHelperRefactor_Factory;
    }

    @Override // miui.systemui.handles.RegionSamplingHelperRefactor.Factory
    public RegionSamplingHelperRefactor create(View view, Function1 function1) {
        return this.delegateFactory.get(view, function1);
    }

    public static G0.a create(C0669RegionSamplingHelperRefactor_Factory c0669RegionSamplingHelperRefactor_Factory) {
        return f.a(new RegionSamplingHelperRefactor_Factory_Impl(c0669RegionSamplingHelperRefactor_Factory));
    }
}
