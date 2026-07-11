package miui.systemui.dynamicisland.event;

import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public class State {
    private boolean deleteNoAnimation;
    private boolean tempShow;
    private String deleteKey = "";
    private String updateKey = "";
    private Long time = 0L;
    private boolean expanded = true;
    private Boolean updateOrder = Boolean.FALSE;

    public final String getDeleteKey() {
        return this.deleteKey;
    }

    public final boolean getDeleteNoAnimation() {
        return this.deleteNoAnimation;
    }

    public final boolean getExpanded() {
        return this.expanded;
    }

    public final boolean getTempShow() {
        return this.tempShow;
    }

    public final Long getTime() {
        return this.time;
    }

    public final String getUpdateKey() {
        return this.updateKey;
    }

    public final Boolean getUpdateOrder() {
        return this.updateOrder;
    }

    public final void setDeleteKey(String str) {
        n.g(str, "<set-?>");
        this.deleteKey = str;
    }

    public final void setDeleteNoAnimation(boolean z2) {
        this.deleteNoAnimation = z2;
    }

    public final void setExpanded(boolean z2) {
        this.expanded = z2;
    }

    public final void setTempShow(boolean z2) {
        this.tempShow = z2;
    }

    public final void setTime(Long l2) {
        this.time = l2;
    }

    public final void setUpdateKey(String str) {
        n.g(str, "<set-?>");
        this.updateKey = str;
    }

    public final void setUpdateOrder(Boolean bool) {
        this.updateOrder = bool;
    }

    public String toString() {
        return "[" + getClass().getSimpleName() + "] expanded " + this.expanded + ", tempShow " + this.tempShow;
    }
}
