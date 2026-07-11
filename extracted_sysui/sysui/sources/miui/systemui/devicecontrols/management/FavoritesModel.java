package miui.systemui.devicecontrols.management;

import I0.AbstractC0184l;
import I0.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.devicecontrols.controller.ControlInfo;
import miui.systemui.devicecontrols.ui.ControlWithState;
import miui.systemui.devicecontrols.util.ControlsUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class FavoritesModel implements ControlsModel {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "FavoritesModel";
    private ControlAdapter adapter;
    private final List<ElementWrapper> elements;
    private final List<ControlWithState> favoriteCws;
    private final CharSequence structure;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public FavoritesModel(CharSequence structure, List<ControlWithState> favoriteCws) {
        kotlin.jvm.internal.n.g(structure, "structure");
        kotlin.jvm.internal.n.g(favoriteCws, "favoriteCws");
        this.structure = structure;
        this.favoriteCws = favoriteCws;
        this.elements = u.m0(createWrappers());
    }

    private final List<ElementWrapper> createWrappers() {
        if (this.favoriteCws.isEmpty()) {
            return I0.m.h();
        }
        List<ControlWithState> list = this.favoriteCws;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (ControlsUtils.INSTANCE.checkSenseType(((ControlWithState) obj).getCi().getControlId())) {
                arrayList.add(obj);
            }
        }
        List listY = u.Y(this.favoriteCws, arrayList);
        return u.a0(u.b0(u.a0(AbstractC0184l.d(new TitleWrapper(this.structure)), arrayList), new DividerWrapper(false, (arrayList.isEmpty() || listY.isEmpty()) ? false : true)), listY);
    }

    @Override // miui.systemui.devicecontrols.management.ControlsModel
    public void attachAdapter(ControlAdapter adapter) {
        kotlin.jvm.internal.n.g(adapter, "adapter");
        this.adapter = adapter;
    }

    @Override // miui.systemui.devicecontrols.management.ControlsModel
    public List<ElementWrapper> getElements() {
        return this.elements;
    }

    public final List<ControlWithState> getFavoriteCws() {
        return this.favoriteCws;
    }

    @Override // miui.systemui.devicecontrols.management.ControlsModel
    public List<ControlInfo> getFavorites() {
        List<ControlWithState> list = this.favoriteCws;
        ArrayList arrayList = new ArrayList(I0.n.o(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((ControlWithState) it.next()).getCi());
        }
        return arrayList;
    }

    public final CharSequence getStructure() {
        return this.structure;
    }
}
