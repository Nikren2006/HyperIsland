package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "quit")
public final class QsFlipQuitNotSaveEvent {

    @EventKey(key = "if_edit")
    private final String ifEdit;

    @EventKey(key = "tip")
    private final String tip;

    public QsFlipQuitNotSaveEvent(String ifEdit, String tip) {
        n.g(ifEdit, "ifEdit");
        n.g(tip, "tip");
        this.ifEdit = ifEdit;
        this.tip = tip;
    }

    public static /* synthetic */ QsFlipQuitNotSaveEvent copy$default(QsFlipQuitNotSaveEvent qsFlipQuitNotSaveEvent, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = qsFlipQuitNotSaveEvent.ifEdit;
        }
        if ((i2 & 2) != 0) {
            str2 = qsFlipQuitNotSaveEvent.tip;
        }
        return qsFlipQuitNotSaveEvent.copy(str, str2);
    }

    public final String component1() {
        return this.ifEdit;
    }

    public final String component2() {
        return this.tip;
    }

    public final QsFlipQuitNotSaveEvent copy(String ifEdit, String tip) {
        n.g(ifEdit, "ifEdit");
        n.g(tip, "tip");
        return new QsFlipQuitNotSaveEvent(ifEdit, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QsFlipQuitNotSaveEvent)) {
            return false;
        }
        QsFlipQuitNotSaveEvent qsFlipQuitNotSaveEvent = (QsFlipQuitNotSaveEvent) obj;
        return n.c(this.ifEdit, qsFlipQuitNotSaveEvent.ifEdit) && n.c(this.tip, qsFlipQuitNotSaveEvent.tip);
    }

    public final String getIfEdit() {
        return this.ifEdit;
    }

    public final String getTip() {
        return this.tip;
    }

    public int hashCode() {
        return (this.ifEdit.hashCode() * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "QsFlipQuitNotSaveEvent(ifEdit=" + this.ifEdit + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ QsFlipQuitNotSaveEvent(String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? "178.6.2.1.40410" : str2);
    }
}
