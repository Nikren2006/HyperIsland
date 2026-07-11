package miui.systemui.devicecontrols.management;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes3.dex */
public final class DividerWrapper extends ElementWrapper {
    private boolean showDivider;
    private boolean showNone;

    /* JADX WARN: Illegal instructions before constructor call */
    public DividerWrapper() {
        boolean z2 = false;
        this(z2, z2, 3, null);
    }

    public static /* synthetic */ DividerWrapper copy$default(DividerWrapper dividerWrapper, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = dividerWrapper.showNone;
        }
        if ((i2 & 2) != 0) {
            z3 = dividerWrapper.showDivider;
        }
        return dividerWrapper.copy(z2, z3);
    }

    public final boolean component1() {
        return this.showNone;
    }

    public final boolean component2() {
        return this.showDivider;
    }

    public final DividerWrapper copy(boolean z2, boolean z3) {
        return new DividerWrapper(z2, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DividerWrapper)) {
            return false;
        }
        DividerWrapper dividerWrapper = (DividerWrapper) obj;
        return this.showNone == dividerWrapper.showNone && this.showDivider == dividerWrapper.showDivider;
    }

    public final boolean getShowDivider() {
        return this.showDivider;
    }

    public final boolean getShowNone() {
        return this.showNone;
    }

    public int hashCode() {
        return (Boolean.hashCode(this.showNone) * 31) + Boolean.hashCode(this.showDivider);
    }

    public final void setShowDivider(boolean z2) {
        this.showDivider = z2;
    }

    public final void setShowNone(boolean z2) {
        this.showNone = z2;
    }

    public String toString() {
        return "DividerWrapper(showNone=" + this.showNone + ", showDivider=" + this.showDivider + ")";
    }

    public /* synthetic */ DividerWrapper(boolean z2, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z2, (i2 & 2) != 0 ? false : z3);
    }

    public DividerWrapper(boolean z2, boolean z3) {
        this.showNone = z2;
        this.showDivider = z3;
    }
}
