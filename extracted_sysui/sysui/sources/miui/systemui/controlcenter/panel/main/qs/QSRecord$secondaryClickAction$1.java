package miui.systemui.controlcenter.panel.main.qs;

import H0.s;
import android.view.View;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
public final class QSRecord$secondaryClickAction$1 extends o implements Function1 {
    final /* synthetic */ QSRecord this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSRecord$secondaryClickAction$1(QSRecord qSRecord) {
        super(1);
        this.this$0 = qSRecord;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return s.f314a;
    }

    public final void invoke(View view) {
        String string;
        QSTile.State state;
        CharSequence tileLabel;
        if (this.this$0.mode == MainPanelController.Mode.NORMAL) {
            ControlCenterEventTracker.Companion companion = ControlCenterEventTracker.Companion;
            String screenType = EventTracker.Companion.getScreenType(this.this$0.context);
            String str = this.this$0.getType() == 2273 ? ControlCenterEventTracker.QS_STYLE_CARD : "按钮";
            int iIndexOf = this.this$0.host.indexOf(this.this$0.getSpec());
            int i2 = this.this$0.context.getResources().getConfiguration().orientation;
            String spec = this.this$0.getSpec();
            QSTile tile = this.this$0.getTile();
            if (tile == null || (tileLabel = tile.getTileLabel()) == null || (string = tileLabel.toString()) == null) {
                string = "";
            }
            String str2 = string;
            QSTile tile2 = this.this$0.getTile();
            companion.trackQuickSettingsClickEvent(screenType, str, iIndexOf, i2, spec, str2, "二级页展开", ControlCenterEventTracker.OTHER_PAGE, (tile2 == null || (state = tile2.getState()) == null) ? 2 : state.state);
            QSTile tile3 = this.this$0.getTile();
            if (tile3 != null) {
                tile3.secondaryClick();
            }
        }
    }
}
