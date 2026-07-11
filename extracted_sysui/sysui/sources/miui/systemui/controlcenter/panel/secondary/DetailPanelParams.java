package miui.systemui.controlcenter.panel.secondary;

import com.android.systemui.plugins.qs.DetailAdapter;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class DetailPanelParams implements SecondaryParams {
    private final DetailAdapter adapter;
    private final DetailFromView fromView;
    private final boolean rightOrLeft;
    private final boolean useSpecificHeight;

    public DetailPanelParams(DetailAdapter detailAdapter, DetailFromView detailFromView, boolean z2, boolean z3) {
        this.adapter = detailAdapter;
        this.fromView = detailFromView;
        this.rightOrLeft = z2;
        this.useSpecificHeight = z3;
    }

    public final DetailAdapter getAdapter() {
        return this.adapter;
    }

    public final DetailFromView getFromView() {
        return this.fromView;
    }

    public final boolean getRightOrLeft() {
        return this.rightOrLeft;
    }

    public final boolean getUseSpecificHeight() {
        return this.useSpecificHeight;
    }

    public /* synthetic */ DetailPanelParams(DetailAdapter detailAdapter, DetailFromView detailFromView, boolean z2, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(detailAdapter, detailFromView, (i2 & 4) != 0 ? true : z2, (i2 & 8) != 0 ? false : z3);
    }
}
