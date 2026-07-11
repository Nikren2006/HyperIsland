package miui.systemui.devicecontrols.controller;

import I0.G;
import android.content.ComponentName;
import android.service.controls.Control;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class Favorites {
    public static final Favorites INSTANCE = new Favorites();
    private static Map<ComponentName, ? extends List<StructureInfo>> favMap = G.f();

    private Favorites() {
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean addFavorite(android.content.ComponentName r17, java.lang.CharSequence r18, miui.systemui.devicecontrols.controller.ControlInfo r19) {
        /*
            r16 = this;
            r1 = r17
            r2 = r18
            r7 = r19
            java.lang.String r0 = "componentName"
            kotlin.jvm.internal.n.g(r1, r0)
            java.lang.String r0 = "structureName"
            kotlin.jvm.internal.n.g(r2, r0)
            java.lang.String r0 = "controlInfo"
            kotlin.jvm.internal.n.g(r7, r0)
            java.util.List r0 = r16.getControlsForComponent(r17)
            if (r0 == 0) goto L22
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L22
            goto L42
        L22:
            java.util.Iterator r0 = r0.iterator()
        L26:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L42
            java.lang.Object r3 = r0.next()
            miui.systemui.devicecontrols.controller.ControlInfo r3 = (miui.systemui.devicecontrols.controller.ControlInfo) r3
            java.lang.String r3 = r3.getControlId()
            java.lang.String r4 = r19.getControlId()
            boolean r3 = kotlin.jvm.internal.n.c(r3, r4)
            if (r3 == 0) goto L26
            r0 = 0
            return r0
        L42:
            java.util.Map<android.content.ComponentName, ? extends java.util.List<miui.systemui.devicecontrols.controller.StructureInfo>> r0 = miui.systemui.devicecontrols.controller.Favorites.favMap
            java.lang.Object r0 = r0.get(r1)
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L6f
            java.util.Iterator r0 = r0.iterator()
        L50:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L68
            java.lang.Object r3 = r0.next()
            r4 = r3
            miui.systemui.devicecontrols.controller.StructureInfo r4 = (miui.systemui.devicecontrols.controller.StructureInfo) r4
            java.lang.CharSequence r4 = r4.getStructure()
            boolean r4 = kotlin.jvm.internal.n.c(r4, r2)
            if (r4 == 0) goto L50
            goto L69
        L68:
            r3 = 0
        L69:
            miui.systemui.devicecontrols.controller.StructureInfo r3 = (miui.systemui.devicecontrols.controller.StructureInfo) r3
            if (r3 == 0) goto L6f
            r9 = r3
            goto L82
        L6f:
            miui.systemui.devicecontrols.controller.StructureInfo r8 = new miui.systemui.devicecontrols.controller.StructureInfo
            java.util.List r3 = I0.m.h()
            r5 = 8
            r6 = 0
            r4 = 0
            r0 = r8
            r1 = r17
            r2 = r18
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r9 = r8
        L82:
            java.util.List r0 = r9.getControls()
            java.util.List r12 = I0.u.b0(r0, r7)
            r14 = 11
            r15 = 0
            r10 = 0
            r11 = 0
            r13 = 0
            miui.systemui.devicecontrols.controller.StructureInfo r0 = miui.systemui.devicecontrols.controller.StructureInfo.copy$default(r9, r10, r11, r12, r13, r14, r15)
            r1 = r16
            r1.replaceControls(r0)
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.devicecontrols.controller.Favorites.addFavorite(android.content.ComponentName, java.lang.CharSequence, miui.systemui.devicecontrols.controller.ControlInfo):boolean");
    }

    public final void clear() {
        favMap = G.f();
    }

    public final List<StructureInfo> getAllStructures() {
        Map<ComponentName, ? extends List<StructureInfo>> map = favMap;
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<ComponentName, ? extends List<StructureInfo>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            I0.r.t(arrayList, it.next().getValue());
        }
        return arrayList;
    }

    public final List<ControlInfo> getControlsForComponent(ComponentName componentName) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        List<StructureInfo> structuresForComponent = getStructuresForComponent(componentName);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = structuresForComponent.iterator();
        while (it.hasNext()) {
            I0.r.t(arrayList, ((StructureInfo) it.next()).getControls());
        }
        return arrayList;
    }

    public final List<ControlInfo> getControlsForStructure(StructureInfo structure) {
        Object next;
        List<ControlInfo> controls;
        kotlin.jvm.internal.n.g(structure, "structure");
        Iterator<T> it = getStructuresForComponent(structure.getComponentName()).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (kotlin.jvm.internal.n.c(((StructureInfo) next).getStructure(), structure.getStructure())) {
                break;
            }
        }
        StructureInfo structureInfo = (StructureInfo) next;
        return (structureInfo == null || (controls = structureInfo.getControls()) == null) ? I0.m.h() : controls;
    }

    public final List<StructureInfo> getStructuresForComponent(ComponentName componentName) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        List<StructureInfo> list = favMap.get(componentName);
        return list == null ? I0.m.h() : list;
    }

    public final void load(List<StructureInfo> structures) {
        kotlin.jvm.internal.n.g(structures, "structures");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : structures) {
            ComponentName componentName = ((StructureInfo) obj).getComponentName();
            Object arrayList = linkedHashMap.get(componentName);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(componentName, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        favMap = linkedHashMap;
    }

    public final boolean removeFavorite(ComponentName componentName, CharSequence structureName, ControlInfo controlInfo) {
        Object next;
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(structureName, "structureName");
        kotlin.jvm.internal.n.g(controlInfo, "controlInfo");
        List<ControlInfo> controlsForComponent = getControlsForComponent(componentName);
        if (controlsForComponent == null || !controlsForComponent.isEmpty()) {
            Iterator<T> it = controlsForComponent.iterator();
            while (it.hasNext()) {
                if (kotlin.jvm.internal.n.c(((ControlInfo) it.next()).getControlId(), controlInfo.getControlId())) {
                    return false;
                }
            }
        }
        List<StructureInfo> list = favMap.get(componentName);
        if (list != null) {
            Iterator<T> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    next = null;
                    break;
                }
                next = it2.next();
                if (kotlin.jvm.internal.n.c(((StructureInfo) next).getStructure(), structureName)) {
                    break;
                }
            }
            StructureInfo structureInfo = (StructureInfo) next;
            if (structureInfo != null) {
                replaceControls(StructureInfo.copy$default(structureInfo, null, null, I0.u.Z(structureInfo.getControls(), controlInfo), false, 11, null));
                return true;
            }
        }
        return false;
    }

    public final void removeStructures(ComponentName componentName) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        Map<ComponentName, ? extends List<StructureInfo>> mapP = G.p(favMap);
        mapP.remove(componentName);
        favMap = mapP;
    }

    public final void replaceControls(StructureInfo updatedStructure) {
        kotlin.jvm.internal.n.g(updatedStructure, "updatedStructure");
        Map<ComponentName, ? extends List<StructureInfo>> mapP = G.p(favMap);
        ArrayList arrayList = new ArrayList();
        ComponentName componentName = updatedStructure.getComponentName();
        if (TextUtils.isEmpty(updatedStructure.getComponentName().getClassName())) {
            return;
        }
        boolean z2 = false;
        for (StructureInfo structureInfo : getStructuresForComponent(componentName)) {
            if (kotlin.jvm.internal.n.c(structureInfo.getStructure(), updatedStructure.getStructure())) {
                z2 = true;
                structureInfo = updatedStructure;
            }
            arrayList.add(structureInfo);
        }
        if (!z2) {
            arrayList.add(updatedStructure);
        }
        mapP.put(componentName, arrayList);
        favMap = mapP;
    }

    public final void replaceStructures(List<StructureInfo> updatedStructures) {
        kotlin.jvm.internal.n.g(updatedStructures, "updatedStructures");
        if (updatedStructures.isEmpty()) {
            return;
        }
        ComponentName componentName = updatedStructures.get(0).getComponentName();
        if (TextUtils.isEmpty(componentName.getClassName())) {
            return;
        }
        Map<ComponentName, ? extends List<StructureInfo>> mapP = G.p(favMap);
        mapP.put(componentName, updatedStructures);
        favMap = mapP;
    }

    public final boolean updateControls(ComponentName componentName, List<Control> controls) {
        H0.i iVar;
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(controls, "controls");
        LinkedHashMap linkedHashMap = new LinkedHashMap(c1.f.c(I0.F.c(I0.n.o(controls, 10)), 16));
        for (Object obj : controls) {
            linkedHashMap.put(((Control) obj).getControlId(), obj);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        boolean z2 = false;
        for (StructureInfo structureInfo : getStructuresForComponent(componentName)) {
            for (ControlInfo controlInfoCopy$default : structureInfo.getControls()) {
                Control control = (Control) linkedHashMap.get(controlInfoCopy$default.getControlId());
                if (control != null) {
                    if (!kotlin.jvm.internal.n.c(control.getTitle(), controlInfoCopy$default.getControlTitle()) || !kotlin.jvm.internal.n.c(control.getSubtitle(), controlInfoCopy$default.getControlSubtitle()) || control.getDeviceType() != controlInfoCopy$default.getDeviceType()) {
                        CharSequence title = control.getTitle();
                        kotlin.jvm.internal.n.f(title, "getTitle(...)");
                        CharSequence subtitle = control.getSubtitle();
                        kotlin.jvm.internal.n.f(subtitle, "getSubtitle(...)");
                        controlInfoCopy$default = ControlInfo.copy$default(controlInfoCopy$default, null, title, subtitle, null, control.getDeviceType(), 9, null);
                        z2 = true;
                    }
                    CharSequence structure = control.getStructure();
                    if (structure == null) {
                        structure = "";
                    } else {
                        kotlin.jvm.internal.n.d(structure);
                    }
                    if (!kotlin.jvm.internal.n.c(structureInfo.getStructure(), structure) && !kotlin.jvm.internal.n.c(componentName.getPackageName(), "com.xiaomi.smarthome")) {
                        z2 = true;
                    }
                    iVar = new H0.i(structure, controlInfoCopy$default);
                } else {
                    iVar = new H0.i(structureInfo.getStructure(), controlInfoCopy$default);
                }
                CharSequence charSequence = (CharSequence) iVar.a();
                ControlInfo controlInfo = (ControlInfo) iVar.b();
                Object arrayList = linkedHashMap2.get(charSequence);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap2.put(charSequence, arrayList);
                }
                ((List) arrayList).add(controlInfo);
            }
        }
        if (!z2) {
            return false;
        }
        ArrayList arrayList2 = new ArrayList(linkedHashMap2.size());
        for (Map.Entry entry : linkedHashMap2.entrySet()) {
            arrayList2.add(new StructureInfo(componentName, (CharSequence) entry.getKey(), (List) entry.getValue(), false, 8, null));
        }
        Map<ComponentName, ? extends List<StructureInfo>> mapP = G.p(favMap);
        mapP.put(componentName, arrayList2);
        favMap = mapP;
        return true;
    }
}
