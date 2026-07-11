package miui.systemui.controlcenter.panel.secondary.brightness;

import H0.s;
import android.view.View;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.qs.tileview.QSTileItemView;

/* JADX INFO: loaded from: classes.dex */
public final class BrightnessPanelTilesDelegate$addQSTileItemView$1$2 extends o implements Function1 {
    final /* synthetic */ QSTile $tile;
    final /* synthetic */ QSTileItemView $tileView;
    final /* synthetic */ BrightnessPanelTilesDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BrightnessPanelTilesDelegate$addQSTileItemView$1$2(BrightnessPanelTilesDelegate brightnessPanelTilesDelegate, QSTile qSTile, QSTileItemView qSTileItemView) {
        super(1);
        this.this$0 = brightnessPanelTilesDelegate;
        this.$tile = qSTile;
        this.$tileView = qSTileItemView;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return s.f314a;
    }

    public final void invoke(View view) {
        this.this$0.onClickInternal(this.$tile, this.$tileView);
    }
}
