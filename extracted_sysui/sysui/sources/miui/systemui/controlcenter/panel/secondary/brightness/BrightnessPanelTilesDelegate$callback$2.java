package miui.systemui.controlcenter.panel.secondary.brightness;

import com.android.systemui.plugins.qs.QSTile;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.qs.tileview.QSTileItemView;

/* JADX INFO: loaded from: classes.dex */
public final class BrightnessPanelTilesDelegate$callback$2 extends o implements Function0 {
    final /* synthetic */ BrightnessPanelTilesDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BrightnessPanelTilesDelegate$callback$2(BrightnessPanelTilesDelegate brightnessPanelTilesDelegate) {
        super(0);
        this.this$0 = brightnessPanelTilesDelegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1(final BrightnessPanelTilesDelegate this$0, final QSTile.State state) {
        n.g(this$0, "this$0");
        this$0.mainHandler.post(new Runnable() { // from class: miui.systemui.controlcenter.panel.secondary.brightness.c
            @Override // java.lang.Runnable
            public final void run() {
                BrightnessPanelTilesDelegate$callback$2.invoke$lambda$1$lambda$0(this$0, state);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1$lambda$0(BrightnessPanelTilesDelegate this$0, QSTile.State state) {
        n.g(this$0, "this$0");
        QSTileItemView qSTileItemView = (QSTileItemView) this$0.getTileViews().get(state.spec);
        if (qSTileItemView != null) {
            qSTileItemView.updateState(state, false, this$0.animator.isExpanded());
        }
    }

    @Override // kotlin.jvm.functions.Function0
    public final QSTile.Callback invoke() {
        final BrightnessPanelTilesDelegate brightnessPanelTilesDelegate = this.this$0;
        return new QSTile.Callback() { // from class: miui.systemui.controlcenter.panel.secondary.brightness.b
            public final void onStateChanged(QSTile.State state) {
                BrightnessPanelTilesDelegate$callback$2.invoke$lambda$1(brightnessPanelTilesDelegate, state);
            }
        };
    }
}
