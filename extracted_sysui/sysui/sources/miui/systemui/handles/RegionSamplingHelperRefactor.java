package miui.systemui.handles;

import android.view.View;
import g1.E;
import j1.AbstractC0420h;
import j1.I;
import j1.K;
import j1.u;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.Plugin;

/* JADX INFO: loaded from: classes3.dex */
public final class RegionSamplingHelperRefactor {
    private final u _isRegionDark;
    private final u _medianLuma;
    private final I isRegionDark;
    private final I medianLuma;
    private boolean regionSampling;
    private final RegionSamplingHelper regionSamplingHelper;
    private final Function1 sampledRegion;
    private final View sampledView;
    private final RegionSamplingHelperRefactor$samplingCallback$1 samplingCallback;
    private final E scope;
    private boolean started;

    public interface Factory {
        RegionSamplingHelperRefactor create(View view, Function1 function1);
    }

    public RegionSamplingHelperRefactor(View sampledView, Function1 sampledRegion, @Plugin E scope, @Main Executor uiExecutor, @Background Executor bgExecutor) {
        n.g(sampledView, "sampledView");
        n.g(sampledRegion, "sampledRegion");
        n.g(scope, "scope");
        n.g(uiExecutor, "uiExecutor");
        n.g(bgExecutor, "bgExecutor");
        this.sampledView = sampledView;
        this.sampledRegion = sampledRegion;
        this.scope = scope;
        u uVarA = K.a(Float.valueOf(1.0f));
        this._medianLuma = uVarA;
        u uVarA2 = K.a(Boolean.FALSE);
        this._isRegionDark = uVarA2;
        this.medianLuma = AbstractC0420h.b(uVarA);
        this.isRegionDark = AbstractC0420h.b(uVarA2);
        RegionSamplingHelperRefactor$samplingCallback$1 regionSamplingHelperRefactor$samplingCallback$1 = new RegionSamplingHelperRefactor$samplingCallback$1(this);
        this.samplingCallback = regionSamplingHelperRefactor$samplingCallback$1;
        this.regionSamplingHelper = new RegionSamplingHelper(sampledView, regionSamplingHelperRefactor$samplingCallback$1, uiExecutor, bgExecutor);
    }

    private final void start() {
        this.regionSamplingHelper.setWindowVisible(true);
        if (this.started) {
            return;
        }
        this.regionSamplingHelper.start(this.samplingCallback.getSampledRegion(this.sampledView));
        this.started = true;
    }

    private final void stop() {
        this.regionSamplingHelper.setWindowVisible(false);
    }

    public final I getMedianLuma() {
        return this.medianLuma;
    }

    public final boolean getRegionSampling() {
        return this.regionSampling;
    }

    public final I isRegionDark() {
        return this.isRegionDark;
    }

    public final void setRegionSampling(boolean z2) {
        if (this.regionSampling == z2) {
            return;
        }
        this.regionSampling = z2;
        if (z2) {
            start();
        } else {
            stop();
        }
    }

    public final void updateSamplingRect() {
        this.regionSamplingHelper.updateSamplingRect();
    }
}
