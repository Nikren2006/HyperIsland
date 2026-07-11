package miui.systemui.devicecontrols.management;

import android.content.ComponentName;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes3.dex */
public final class EditEmptyGuideWrapper extends ElementWrapper {
    private CharSequence app;
    private ComponentName component;
    private ComponentName panelActivity;
    private boolean visibility;

    public EditEmptyGuideWrapper() {
        this(null, null, null, false, 15, null);
    }

    public static /* synthetic */ EditEmptyGuideWrapper copy$default(EditEmptyGuideWrapper editEmptyGuideWrapper, CharSequence charSequence, ComponentName componentName, ComponentName componentName2, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charSequence = editEmptyGuideWrapper.app;
        }
        if ((i2 & 2) != 0) {
            componentName = editEmptyGuideWrapper.component;
        }
        if ((i2 & 4) != 0) {
            componentName2 = editEmptyGuideWrapper.panelActivity;
        }
        if ((i2 & 8) != 0) {
            z2 = editEmptyGuideWrapper.visibility;
        }
        return editEmptyGuideWrapper.copy(charSequence, componentName, componentName2, z2);
    }

    public final CharSequence component1() {
        return this.app;
    }

    public final ComponentName component2() {
        return this.component;
    }

    public final ComponentName component3() {
        return this.panelActivity;
    }

    public final boolean component4() {
        return this.visibility;
    }

    public final EditEmptyGuideWrapper copy(CharSequence app, ComponentName componentName, ComponentName componentName2, boolean z2) {
        kotlin.jvm.internal.n.g(app, "app");
        return new EditEmptyGuideWrapper(app, componentName, componentName2, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EditEmptyGuideWrapper)) {
            return false;
        }
        EditEmptyGuideWrapper editEmptyGuideWrapper = (EditEmptyGuideWrapper) obj;
        return kotlin.jvm.internal.n.c(this.app, editEmptyGuideWrapper.app) && kotlin.jvm.internal.n.c(this.component, editEmptyGuideWrapper.component) && kotlin.jvm.internal.n.c(this.panelActivity, editEmptyGuideWrapper.panelActivity) && this.visibility == editEmptyGuideWrapper.visibility;
    }

    public final CharSequence getApp() {
        return this.app;
    }

    public final ComponentName getComponent() {
        return this.component;
    }

    public final ComponentName getPanelActivity() {
        return this.panelActivity;
    }

    public final boolean getVisibility() {
        return this.visibility;
    }

    public int hashCode() {
        int iHashCode = this.app.hashCode() * 31;
        ComponentName componentName = this.component;
        int iHashCode2 = (iHashCode + (componentName == null ? 0 : componentName.hashCode())) * 31;
        ComponentName componentName2 = this.panelActivity;
        return ((iHashCode2 + (componentName2 != null ? componentName2.hashCode() : 0)) * 31) + Boolean.hashCode(this.visibility);
    }

    public final void setApp(CharSequence charSequence) {
        kotlin.jvm.internal.n.g(charSequence, "<set-?>");
        this.app = charSequence;
    }

    public final void setComponent(ComponentName componentName) {
        this.component = componentName;
    }

    public final void setPanelActivity(ComponentName componentName) {
        this.panelActivity = componentName;
    }

    public final void setVisibility(boolean z2) {
        this.visibility = z2;
    }

    public String toString() {
        CharSequence charSequence = this.app;
        return "EditEmptyGuideWrapper(app=" + ((Object) charSequence) + ", component=" + this.component + ", panelActivity=" + this.panelActivity + ", visibility=" + this.visibility + ")";
    }

    public /* synthetic */ EditEmptyGuideWrapper(String str, ComponentName componentName, ComponentName componentName2, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? null : componentName, (i2 & 4) != 0 ? null : componentName2, (i2 & 8) != 0 ? false : z2);
    }

    public EditEmptyGuideWrapper(CharSequence app, ComponentName componentName, ComponentName componentName2, boolean z2) {
        kotlin.jvm.internal.n.g(app, "app");
        this.app = app;
        this.component = componentName;
        this.panelActivity = componentName2;
        this.visibility = z2;
    }
}
