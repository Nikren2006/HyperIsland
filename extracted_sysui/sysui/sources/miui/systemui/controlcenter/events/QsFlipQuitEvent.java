package miui.systemui.controlcenter.events;

import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes.dex */
@EventID(id = "quit")
public final class QsFlipQuitEvent {

    @EventKey(key = "after_edit_list")
    private final List<Map<String, String>> afterEditList;

    @EventKey(key = "before_edit_list")
    private final List<Map<String, String>> beforeEditList;

    @EventKey(key = "if_edit")
    private final String ifEdit;

    @EventKey(key = "tip")
    private final String tip;

    /* JADX WARN: Multi-variable type inference failed */
    public QsFlipQuitEvent(String ifEdit, List<? extends Map<String, String>> beforeEditList, List<? extends Map<String, String>> afterEditList, String tip) {
        n.g(ifEdit, "ifEdit");
        n.g(beforeEditList, "beforeEditList");
        n.g(afterEditList, "afterEditList");
        n.g(tip, "tip");
        this.ifEdit = ifEdit;
        this.beforeEditList = beforeEditList;
        this.afterEditList = afterEditList;
        this.tip = tip;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ QsFlipQuitEvent copy$default(QsFlipQuitEvent qsFlipQuitEvent, String str, List list, List list2, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = qsFlipQuitEvent.ifEdit;
        }
        if ((i2 & 2) != 0) {
            list = qsFlipQuitEvent.beforeEditList;
        }
        if ((i2 & 4) != 0) {
            list2 = qsFlipQuitEvent.afterEditList;
        }
        if ((i2 & 8) != 0) {
            str2 = qsFlipQuitEvent.tip;
        }
        return qsFlipQuitEvent.copy(str, list, list2, str2);
    }

    public final String component1() {
        return this.ifEdit;
    }

    public final List<Map<String, String>> component2() {
        return this.beforeEditList;
    }

    public final List<Map<String, String>> component3() {
        return this.afterEditList;
    }

    public final String component4() {
        return this.tip;
    }

    public final QsFlipQuitEvent copy(String ifEdit, List<? extends Map<String, String>> beforeEditList, List<? extends Map<String, String>> afterEditList, String tip) {
        n.g(ifEdit, "ifEdit");
        n.g(beforeEditList, "beforeEditList");
        n.g(afterEditList, "afterEditList");
        n.g(tip, "tip");
        return new QsFlipQuitEvent(ifEdit, beforeEditList, afterEditList, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QsFlipQuitEvent)) {
            return false;
        }
        QsFlipQuitEvent qsFlipQuitEvent = (QsFlipQuitEvent) obj;
        return n.c(this.ifEdit, qsFlipQuitEvent.ifEdit) && n.c(this.beforeEditList, qsFlipQuitEvent.beforeEditList) && n.c(this.afterEditList, qsFlipQuitEvent.afterEditList) && n.c(this.tip, qsFlipQuitEvent.tip);
    }

    public final List<Map<String, String>> getAfterEditList() {
        return this.afterEditList;
    }

    public final List<Map<String, String>> getBeforeEditList() {
        return this.beforeEditList;
    }

    public final String getIfEdit() {
        return this.ifEdit;
    }

    public final String getTip() {
        return this.tip;
    }

    public int hashCode() {
        return (((((this.ifEdit.hashCode() * 31) + this.beforeEditList.hashCode()) * 31) + this.afterEditList.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "QsFlipQuitEvent(ifEdit=" + this.ifEdit + ", beforeEditList=" + this.beforeEditList + ", afterEditList=" + this.afterEditList + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ QsFlipQuitEvent(String str, List list, List list2, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, list2, (i2 & 8) != 0 ? "178.6.2.1.40410" : str2);
    }
}
