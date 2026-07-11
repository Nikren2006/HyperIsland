package miui.systemui.controlcenter.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.flashlight.utils.TrackUtilsKt;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = TrackUtilsKt.EVENT_ENTER)
public final class QsFlipEnterEvent {

    @EventKey(key = "tip")
    private final String tip;

    /* JADX WARN: Multi-variable type inference failed */
    public QsFlipEnterEvent() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ QsFlipEnterEvent copy$default(QsFlipEnterEvent qsFlipEnterEvent, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = qsFlipEnterEvent.tip;
        }
        return qsFlipEnterEvent.copy(str);
    }

    public final String component1() {
        return this.tip;
    }

    public final QsFlipEnterEvent copy(String tip) {
        n.g(tip, "tip");
        return new QsFlipEnterEvent(tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof QsFlipEnterEvent) && n.c(this.tip, ((QsFlipEnterEvent) obj).tip);
    }

    public final String getTip() {
        return this.tip;
    }

    public int hashCode() {
        return this.tip.hashCode();
    }

    public String toString() {
        return "QsFlipEnterEvent(tip=" + this.tip + ")";
    }

    public QsFlipEnterEvent(String tip) {
        n.g(tip, "tip");
        this.tip = tip;
    }

    public /* synthetic */ QsFlipEnterEvent(String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "178.6.1.1.40409" : str);
    }
}
