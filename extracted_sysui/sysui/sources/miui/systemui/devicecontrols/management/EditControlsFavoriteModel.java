package miui.systemui.devicecontrols.management;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function2;
import miui.systemui.devicecontrols.ControlInterface;
import miui.systemui.devicecontrols.controller.ControlInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class EditControlsFavoriteModel extends EditControlsModel {
    private boolean availableForAction = true;

    @Override // miui.systemui.devicecontrols.management.EditControlsModel
    public void addToElements(ControlInterface target, Function2 updateMark) {
        kotlin.jvm.internal.n.g(target, "target");
        kotlin.jvm.internal.n.g(updateMark, "updateMark");
        super.addToElements(target, updateMark);
        boolean z2 = getSenseControls().size() >= 4;
        boolean z3 = getNormalControls().size() >= 50;
        if (z2 || z3) {
            updateMark.invoke(Boolean.valueOf(!z2), Boolean.valueOf(!z3));
        }
    }

    @Override // miui.systemui.devicecontrols.management.EditControlsModel
    public boolean getAvailableForAction() {
        return this.availableForAction;
    }

    @Override // miui.systemui.devicecontrols.management.ControlsModel
    public List<ControlInfo> getFavorites() {
        List<ElementWrapper> elements = getElements();
        ArrayList arrayList = new ArrayList();
        for (Object obj : elements) {
            if (obj instanceof ControlStatusWrapper) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(I0.n.o(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(ControlInfo.Companion.fromControl(((ControlStatusWrapper) it.next()).getControlStatus().getControl()));
        }
        return arrayList2;
    }

    @Override // miui.systemui.devicecontrols.management.EditControlsModel
    public void removeFromElements(ControlInterface target, Function2 updateMark) {
        kotlin.jvm.internal.n.g(target, "target");
        kotlin.jvm.internal.n.g(updateMark, "updateMark");
        super.removeFromElements(target, updateMark);
        boolean z2 = getSenseControls().size() < 4;
        boolean z3 = getNormalControls().size() < 50;
        if (z2 || z3) {
            updateMark.invoke(Boolean.valueOf(z2), Boolean.valueOf(z3));
        }
    }

    @Override // miui.systemui.devicecontrols.management.EditControlsModel
    public void setAvailableForAction(boolean z2) {
        this.availableForAction = z2;
    }
}
