package miui.systemui.handles;

import android.graphics.Rect;
import android.view.View;
import g1.AbstractC0369g;
import miui.systemui.handles.RegionSamplingHelper;

/* JADX INFO: loaded from: classes3.dex */
public final class RegionSamplingHelperRefactor$samplingCallback$1 implements RegionSamplingHelper.SamplingCallback {
    final /* synthetic */ RegionSamplingHelperRefactor this$0;

    public RegionSamplingHelperRefactor$samplingCallback$1(RegionSamplingHelperRefactor regionSamplingHelperRefactor) {
        this.this$0 = regionSamplingHelperRefactor;
    }

    @Override // miui.systemui.handles.RegionSamplingHelper.SamplingCallback
    public Rect getSampledRegion(View view) {
        if (view != null) {
            return (Rect) this.this$0.sampledRegion.invoke(view);
        }
        return null;
    }

    @Override // miui.systemui.handles.RegionSamplingHelper.SamplingCallback
    public void onMedianLumaChanged(float f2) {
        AbstractC0369g.b(this.this$0.scope, null, null, new RegionSamplingHelperRefactor$samplingCallback$1$onMedianLumaChanged$1(this.this$0, f2, null), 3, null);
    }

    @Override // miui.systemui.handles.RegionSamplingHelper.SamplingCallback
    public void onRegionDarknessChanged(boolean z2) {
        AbstractC0369g.b(this.this$0.scope, null, null, new RegionSamplingHelperRefactor$samplingCallback$1$onRegionDarknessChanged$1(this.this$0, z2, null), 3, null);
    }
}
