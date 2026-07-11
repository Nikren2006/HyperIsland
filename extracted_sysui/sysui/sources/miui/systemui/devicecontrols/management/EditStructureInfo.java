package miui.systemui.devicecontrols.management;

import android.content.ComponentName;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.devicecontrols.ControlInterface;

/* JADX INFO: loaded from: classes3.dex */
public final class EditStructureInfo {
    private final Map<String, ControlInterface> added;
    private final Map<String, ControlInterface> all;
    private final ComponentName componentName;
    private boolean edit;
    private final CharSequence structure;

    public EditStructureInfo(ComponentName componentName, CharSequence structure, Map<String, ControlInterface> added, Map<String, ControlInterface> all, boolean z2) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(structure, "structure");
        kotlin.jvm.internal.n.g(added, "added");
        kotlin.jvm.internal.n.g(all, "all");
        this.componentName = componentName;
        this.structure = structure;
        this.added = added;
        this.all = all;
        this.edit = z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EditStructureInfo copy$default(EditStructureInfo editStructureInfo, ComponentName componentName, CharSequence charSequence, Map map, Map map2, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            componentName = editStructureInfo.componentName;
        }
        if ((i2 & 2) != 0) {
            charSequence = editStructureInfo.structure;
        }
        CharSequence charSequence2 = charSequence;
        if ((i2 & 4) != 0) {
            map = editStructureInfo.added;
        }
        Map map3 = map;
        if ((i2 & 8) != 0) {
            map2 = editStructureInfo.all;
        }
        Map map4 = map2;
        if ((i2 & 16) != 0) {
            z2 = editStructureInfo.edit;
        }
        return editStructureInfo.copy(componentName, charSequence2, map3, map4, z2);
    }

    public final ComponentName component1() {
        return this.componentName;
    }

    public final CharSequence component2() {
        return this.structure;
    }

    public final Map<String, ControlInterface> component3() {
        return this.added;
    }

    public final Map<String, ControlInterface> component4() {
        return this.all;
    }

    public final boolean component5() {
        return this.edit;
    }

    public final EditStructureInfo copy(ComponentName componentName, CharSequence structure, Map<String, ControlInterface> added, Map<String, ControlInterface> all, boolean z2) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(structure, "structure");
        kotlin.jvm.internal.n.g(added, "added");
        kotlin.jvm.internal.n.g(all, "all");
        return new EditStructureInfo(componentName, structure, added, all, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EditStructureInfo)) {
            return false;
        }
        EditStructureInfo editStructureInfo = (EditStructureInfo) obj;
        return kotlin.jvm.internal.n.c(this.componentName, editStructureInfo.componentName) && kotlin.jvm.internal.n.c(this.structure, editStructureInfo.structure) && kotlin.jvm.internal.n.c(this.added, editStructureInfo.added) && kotlin.jvm.internal.n.c(this.all, editStructureInfo.all) && this.edit == editStructureInfo.edit;
    }

    public final Map<String, ControlInterface> getAdded() {
        return this.added;
    }

    public final Map<String, ControlInterface> getAll() {
        return this.all;
    }

    public final ComponentName getComponentName() {
        return this.componentName;
    }

    public final boolean getEdit() {
        return this.edit;
    }

    public final CharSequence getStructure() {
        return this.structure;
    }

    public int hashCode() {
        return (((((((this.componentName.hashCode() * 31) + this.structure.hashCode()) * 31) + this.added.hashCode()) * 31) + this.all.hashCode()) * 31) + Boolean.hashCode(this.edit);
    }

    public final void setEdit(boolean z2) {
        this.edit = z2;
    }

    public String toString() {
        ComponentName componentName = this.componentName;
        CharSequence charSequence = this.structure;
        return "EditStructureInfo(componentName=" + componentName + ", structure=" + ((Object) charSequence) + ", added=" + this.added + ", all=" + this.all + ", edit=" + this.edit + ")";
    }

    public /* synthetic */ EditStructureInfo(ComponentName componentName, CharSequence charSequence, Map map, Map map2, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(componentName, charSequence, map, map2, (i2 & 16) != 0 ? false : z2);
    }
}
