package miui.systemui.controlcenter.panel.main.qs;

import H0.s;
import android.view.View;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.HapticFeedback;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
public final class QSRecord$clickAction$1 extends o implements Function1 {
    final /* synthetic */ QSRecord this$0;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MainPanelController.Mode.values().length];
            try {
                iArr[MainPanelController.Mode.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MainPanelController.Mode.EDIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSRecord$clickAction$1(QSRecord qSRecord) {
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
        View view2;
        QSTile.State state;
        CharSequence tileLabel;
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.this$0.mode.ordinal()];
        int i3 = 2;
        if (i2 != 1) {
            if (i2 != 2) {
                this.this$0.markClickAction.invoke(view);
                return;
            }
            if (!this.this$0.getAdded()) {
                this.this$0.qsController.addTile(this.this$0);
                if (CommonUtils.isTinyScreen(this.this$0.context)) {
                    return;
                }
                ControlCenterEventTracker.Companion.trackTilesEditEvent(EventTracker.Companion.getScreenType(this.this$0.context), false, this.this$0.getSpec(), this.this$0.host.indexOf(this.this$0.getSpec()));
                return;
            }
            if (this.this$0.getRemovable()) {
                this.this$0.qsController.removeTile(this.this$0);
            }
            if (CommonUtils.isTinyScreen(this.this$0.context)) {
                return;
            }
            ControlCenterEventTracker.Companion.trackTilesEditEvent(EventTracker.Companion.getScreenType(this.this$0.context), true, this.this$0.getSpec(), this.this$0.host.indexOf(this.this$0.getSpec()));
            return;
        }
        ControlCenterEventTracker.Companion companion = ControlCenterEventTracker.Companion;
        String screenType = EventTracker.Companion.getScreenType(this.this$0.context);
        String str = this.this$0.getType() == 2273 ? ControlCenterEventTracker.QS_STYLE_CARD : "按钮";
        int iIndexOf = this.this$0.host.indexOf(this.this$0.getSpec());
        int i4 = this.this$0.context.getResources().getConfiguration().orientation;
        String spec = this.this$0.getSpec();
        QSTile tile = this.this$0.getTile();
        if (tile == null || (tileLabel = tile.getTileLabel()) == null || (string = tileLabel.toString()) == null) {
            string = "";
        }
        String str2 = string;
        QSTile tile2 = this.this$0.getTile();
        if (tile2 != null && (state = tile2.getState()) != null) {
            i3 = state.state;
        }
        companion.trackQuickSettingsClickEvent(screenType, str, iIndexOf, i4, spec, str2, "item", ControlCenterEventTracker.HOMEPAGE, i3);
        if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
            this.this$0.hapticFeedback.postClick();
        } else {
            MainPanelItemViewHolder holder = this.this$0.getHolder();
            if (holder != null && (view2 = holder.itemView) != null) {
                view2.performHapticFeedback(1);
            }
        }
        QSTile tile3 = this.this$0.getTile();
        if (tile3 != null) {
            tile3.click();
        }
    }
}
