package miui.systemui.devicecontrols.ui;

import android.content.ComponentName;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes3.dex */
public final class SelectionItem {
    private final CharSequence appName;
    private final ComponentName componentName;
    private final Drawable icon;
    private final ComponentName panelActivity;
    private final CharSequence structure;
    private final int uid;

    public SelectionItem(CharSequence appName, CharSequence structure, Drawable icon, ComponentName componentName, int i2, ComponentName componentName2) {
        kotlin.jvm.internal.n.g(appName, "appName");
        kotlin.jvm.internal.n.g(structure, "structure");
        kotlin.jvm.internal.n.g(icon, "icon");
        kotlin.jvm.internal.n.g(componentName, "componentName");
        this.appName = appName;
        this.structure = structure;
        this.icon = icon;
        this.componentName = componentName;
        this.uid = i2;
        this.panelActivity = componentName2;
    }

    public static /* synthetic */ SelectionItem copy$default(SelectionItem selectionItem, CharSequence charSequence, CharSequence charSequence2, Drawable drawable, ComponentName componentName, int i2, ComponentName componentName2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            charSequence = selectionItem.appName;
        }
        if ((i3 & 2) != 0) {
            charSequence2 = selectionItem.structure;
        }
        CharSequence charSequence3 = charSequence2;
        if ((i3 & 4) != 0) {
            drawable = selectionItem.icon;
        }
        Drawable drawable2 = drawable;
        if ((i3 & 8) != 0) {
            componentName = selectionItem.componentName;
        }
        ComponentName componentName3 = componentName;
        if ((i3 & 16) != 0) {
            i2 = selectionItem.uid;
        }
        int i4 = i2;
        if ((i3 & 32) != 0) {
            componentName2 = selectionItem.panelActivity;
        }
        return selectionItem.copy(charSequence, charSequence3, drawable2, componentName3, i4, componentName2);
    }

    public final CharSequence component1() {
        return this.appName;
    }

    public final CharSequence component2() {
        return this.structure;
    }

    public final Drawable component3() {
        return this.icon;
    }

    public final ComponentName component4() {
        return this.componentName;
    }

    public final int component5() {
        return this.uid;
    }

    public final ComponentName component6() {
        return this.panelActivity;
    }

    public final SelectionItem copy(CharSequence appName, CharSequence structure, Drawable icon, ComponentName componentName, int i2, ComponentName componentName2) {
        kotlin.jvm.internal.n.g(appName, "appName");
        kotlin.jvm.internal.n.g(structure, "structure");
        kotlin.jvm.internal.n.g(icon, "icon");
        kotlin.jvm.internal.n.g(componentName, "componentName");
        return new SelectionItem(appName, structure, icon, componentName, i2, componentName2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SelectionItem)) {
            return false;
        }
        SelectionItem selectionItem = (SelectionItem) obj;
        return kotlin.jvm.internal.n.c(this.appName, selectionItem.appName) && kotlin.jvm.internal.n.c(this.structure, selectionItem.structure) && kotlin.jvm.internal.n.c(this.icon, selectionItem.icon) && kotlin.jvm.internal.n.c(this.componentName, selectionItem.componentName) && this.uid == selectionItem.uid && kotlin.jvm.internal.n.c(this.panelActivity, selectionItem.panelActivity);
    }

    public final CharSequence getAppName() {
        return this.appName;
    }

    public final ComponentName getComponentName() {
        return this.componentName;
    }

    public final Drawable getIcon() {
        return this.icon;
    }

    public final ComponentName getPanelActivity() {
        return this.panelActivity;
    }

    public final CharSequence getStructure() {
        return this.structure;
    }

    public final CharSequence getTitle() {
        CharSequence charSequence = this.structure;
        return charSequence.length() == 0 ? this.appName : charSequence;
    }

    public final int getUid() {
        return this.uid;
    }

    public int hashCode() {
        int iHashCode = ((((((((this.appName.hashCode() * 31) + this.structure.hashCode()) * 31) + this.icon.hashCode()) * 31) + this.componentName.hashCode()) * 31) + Integer.hashCode(this.uid)) * 31;
        ComponentName componentName = this.panelActivity;
        return iHashCode + (componentName == null ? 0 : componentName.hashCode());
    }

    public String toString() {
        CharSequence charSequence = this.appName;
        CharSequence charSequence2 = this.structure;
        return "SelectionItem(appName=" + ((Object) charSequence) + ", structure=" + ((Object) charSequence2) + ", icon=" + this.icon + ", componentName=" + this.componentName + ", uid=" + this.uid + ", panelActivity=" + this.panelActivity + ")";
    }
}
