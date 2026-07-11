package miui.systemui.devicecontrols.controller;

import android.service.controls.Control;
import androidx.annotation.RequiresApi;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlInfo {
    public static final Companion Companion = new Companion(null);
    private static final String SEPARATOR = ":";
    private final String controlId;
    private final CharSequence controlSubtitle;
    private final CharSequence controlTitle;
    private final int deviceType;
    private final CharSequence zone;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @RequiresApi(30)
        public final ControlInfo fromControl(Control control) {
            kotlin.jvm.internal.n.g(control, "control");
            String controlId = control.getControlId();
            kotlin.jvm.internal.n.f(controlId, "getControlId(...)");
            CharSequence title = control.getTitle();
            kotlin.jvm.internal.n.f(title, "getTitle(...)");
            CharSequence subtitle = control.getSubtitle();
            kotlin.jvm.internal.n.f(subtitle, "getSubtitle(...)");
            return new ControlInfo(controlId, title, subtitle, control.getZone(), control.getDeviceType());
        }

        private Companion() {
        }
    }

    public ControlInfo(String controlId, CharSequence controlTitle, CharSequence controlSubtitle, CharSequence charSequence, int i2) {
        kotlin.jvm.internal.n.g(controlId, "controlId");
        kotlin.jvm.internal.n.g(controlTitle, "controlTitle");
        kotlin.jvm.internal.n.g(controlSubtitle, "controlSubtitle");
        this.controlId = controlId;
        this.controlTitle = controlTitle;
        this.controlSubtitle = controlSubtitle;
        this.zone = charSequence;
        this.deviceType = i2;
    }

    public static /* synthetic */ ControlInfo copy$default(ControlInfo controlInfo, String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = controlInfo.controlId;
        }
        if ((i3 & 2) != 0) {
            charSequence = controlInfo.controlTitle;
        }
        CharSequence charSequence4 = charSequence;
        if ((i3 & 4) != 0) {
            charSequence2 = controlInfo.controlSubtitle;
        }
        CharSequence charSequence5 = charSequence2;
        if ((i3 & 8) != 0) {
            charSequence3 = controlInfo.zone;
        }
        CharSequence charSequence6 = charSequence3;
        if ((i3 & 16) != 0) {
            i2 = controlInfo.deviceType;
        }
        return controlInfo.copy(str, charSequence4, charSequence5, charSequence6, i2);
    }

    public final String component1() {
        return this.controlId;
    }

    public final CharSequence component2() {
        return this.controlTitle;
    }

    public final CharSequence component3() {
        return this.controlSubtitle;
    }

    public final CharSequence component4() {
        return this.zone;
    }

    public final int component5() {
        return this.deviceType;
    }

    public final ControlInfo copy(String controlId, CharSequence controlTitle, CharSequence controlSubtitle, CharSequence charSequence, int i2) {
        kotlin.jvm.internal.n.g(controlId, "controlId");
        kotlin.jvm.internal.n.g(controlTitle, "controlTitle");
        kotlin.jvm.internal.n.g(controlSubtitle, "controlSubtitle");
        return new ControlInfo(controlId, controlTitle, controlSubtitle, charSequence, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlInfo)) {
            return false;
        }
        ControlInfo controlInfo = (ControlInfo) obj;
        return kotlin.jvm.internal.n.c(this.controlId, controlInfo.controlId) && kotlin.jvm.internal.n.c(this.controlTitle, controlInfo.controlTitle) && kotlin.jvm.internal.n.c(this.controlSubtitle, controlInfo.controlSubtitle) && kotlin.jvm.internal.n.c(this.zone, controlInfo.zone) && this.deviceType == controlInfo.deviceType;
    }

    public final String getControlId() {
        return this.controlId;
    }

    public final CharSequence getControlSubtitle() {
        return this.controlSubtitle;
    }

    public final CharSequence getControlTitle() {
        return this.controlTitle;
    }

    public final int getDeviceType() {
        return this.deviceType;
    }

    public final CharSequence getZone() {
        return this.zone;
    }

    public int hashCode() {
        int iHashCode = ((((this.controlId.hashCode() * 31) + this.controlTitle.hashCode()) * 31) + this.controlSubtitle.hashCode()) * 31;
        CharSequence charSequence = this.zone;
        return ((iHashCode + (charSequence == null ? 0 : charSequence.hashCode())) * 31) + Integer.hashCode(this.deviceType);
    }

    public String toString() {
        String str = this.controlId;
        CharSequence charSequence = this.controlTitle;
        CharSequence charSequence2 = this.zone;
        return ":" + str + ":" + ((Object) charSequence) + ":" + ((Object) charSequence2) + ":" + this.deviceType;
    }
}
