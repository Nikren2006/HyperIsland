package miui.systemui.devicecontrols.controller;

import java.util.List;
import miui.systemui.devicecontrols.ControlStatus;
import miui.systemui.devicecontrols.controller.ControlsController;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsControllerKt {
    public static final ControlsController.LoadData createLoadDataObject(List<ControlStatus> allControls, List<ControlStatus> controlsWithFavorite, List<String> favorites, boolean z2) {
        kotlin.jvm.internal.n.g(allControls, "allControls");
        kotlin.jvm.internal.n.g(controlsWithFavorite, "controlsWithFavorite");
        kotlin.jvm.internal.n.g(favorites, "favorites");
        return new ControlsController.LoadData(allControls, controlsWithFavorite, favorites, z2) { // from class: miui.systemui.devicecontrols.controller.ControlsControllerKt.createLoadDataObject.1
            private final List<ControlStatus> allControls;
            private final boolean errorOnLoad;
            private final List<ControlStatus> favoritesControls;
            private final List<String> favoritesIds;
            private final List<ControlStatus> removedControls;

            {
                this.allControls = allControls;
                this.removedControls = I0.u.Y(allControls, controlsWithFavorite);
                this.favoritesControls = controlsWithFavorite;
                this.favoritesIds = favorites;
                this.errorOnLoad = z2;
            }

            @Override // miui.systemui.devicecontrols.controller.ControlsController.LoadData
            public List<ControlStatus> getAllControls() {
                return this.allControls;
            }

            @Override // miui.systemui.devicecontrols.controller.ControlsController.LoadData
            public boolean getErrorOnLoad() {
                return this.errorOnLoad;
            }

            @Override // miui.systemui.devicecontrols.controller.ControlsController.LoadData
            public List<ControlStatus> getFavoritesControls() {
                return this.favoritesControls;
            }

            @Override // miui.systemui.devicecontrols.controller.ControlsController.LoadData
            public List<String> getFavoritesIds() {
                return this.favoritesIds;
            }

            @Override // miui.systemui.devicecontrols.controller.ControlsController.LoadData
            public List<ControlStatus> getRemovedControls() {
                return this.removedControls;
            }
        };
    }

    public static /* synthetic */ ControlsController.LoadData createLoadDataObject$default(List list, List list2, List list3, boolean z2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z2 = false;
        }
        return createLoadDataObject(list, list2, list3, z2);
    }
}
