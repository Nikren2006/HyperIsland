package miui.systemui.handles;

import android.view.View;
import g1.E;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* JADX INFO: renamed from: miui.systemui.handles.RegionSamplingHelperRefactor_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0669RegionSamplingHelperRefactor_Factory {
    private final G0.a bgExecutorProvider;
    private final G0.a scopeProvider;
    private final G0.a uiExecutorProvider;

    public C0669RegionSamplingHelperRefactor_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.scopeProvider = aVar;
        this.uiExecutorProvider = aVar2;
        this.bgExecutorProvider = aVar3;
    }

    public static C0669RegionSamplingHelperRefactor_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new C0669RegionSamplingHelperRefactor_Factory(aVar, aVar2, aVar3);
    }

    public static RegionSamplingHelperRefactor newInstance(View view, Function1 function1, E e2, Executor executor, Executor executor2) {
        return new RegionSamplingHelperRefactor(view, function1, e2, executor, executor2);
    }

    public RegionSamplingHelperRefactor get(View view, Function1 function1) {
        return newInstance(view, function1, (E) this.scopeProvider.get(), (Executor) this.uiExecutorProvider.get(), (Executor) this.bgExecutorProvider.get());
    }
}
