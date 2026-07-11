package miui.systemui.controlcenter.panel.main.qs;

import android.view.View;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.util.HapticFeedback;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
public final class QSRecord$longClickAction$1 extends o implements Function1 {
    final /* synthetic */ QSRecord this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSRecord$longClickAction$1(QSRecord qSRecord) {
        super(1);
        this.this$0 = qSRecord;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(View view) {
        View view2;
        QSTile tile;
        QSTile.State state;
        boolean z2 = false;
        if (this.this$0.mode == MainPanelController.Mode.NORMAL) {
            ControlCenterEventTracker.Companion.trackQuickSettingsLongClickEvent(EventTracker.Companion.getScreenType(this.this$0.context), this.this$0.context.getResources().getConfiguration().orientation, this.this$0.getType() == 2273 ? ControlCenterEventTracker.QS_STYLE_CARD : "按钮", this.this$0.getSpec(), this.this$0.host.indexOf(this.this$0.getSpec()));
            if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
                this.this$0.hapticFeedback.postLongClick();
            } else {
                MainPanelItemViewHolder holder = this.this$0.getHolder();
                if (holder != null && (view2 = holder.itemView) != null) {
                    view2.performHapticFeedback(0);
                }
            }
            z2 = true;
            if (!this.this$0.qsController.getLongClickToDetail() || (tile = this.this$0.getTile()) == null || (state = tile.getState()) == null || !state.dualTarget) {
                QSTile tile2 = this.this$0.getTile();
                if (tile2 != null) {
                    tile2.longClick();
                }
            } else {
                QSTile tile3 = this.this$0.getTile();
                if (tile3 != null) {
                    tile3.secondaryClick();
                }
            }
        }
        return Boolean.valueOf(z2);
    }
}
