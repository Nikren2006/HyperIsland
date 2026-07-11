package miui.systemui.devicecontrols.controller;

import android.content.ComponentName;
import android.service.controls.Control;
import android.service.controls.actions.ControlAction;
import java.util.List;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;
import miui.systemui.devicecontrols.ControlStatus;
import miui.systemui.devicecontrols.util.UserAwareController;

/* JADX INFO: loaded from: classes3.dex */
public interface ControlsController extends UserAwareController {

    public interface LoadData {
        List<ControlStatus> getAllControls();

        boolean getErrorOnLoad();

        List<ControlStatus> getFavoritesControls();

        List<String> getFavoritesIds();

        List<ControlStatus> getRemovedControls();
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.controller.ControlsController$replaceFavoritesForStructure$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function0 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m112invoke() {
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m112invoke();
            return H0.s.f314a;
        }
    }

    static /* synthetic */ void replaceFavoritesForStructure$default(ControlsController controlsController, StructureInfo structureInfo, Function0 function0, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: replaceFavoritesForStructure");
        }
        if ((i2 & 2) != 0) {
            function0 = AnonymousClass1.INSTANCE;
        }
        controlsController.replaceFavoritesForStructure(structureInfo, function0);
    }

    void action(ComponentName componentName, ControlInfo controlInfo, ControlAction controlAction);

    void addFavorite(ComponentName componentName, CharSequence charSequence, ControlInfo controlInfo);

    boolean addSeedingFavoritesCallback(Consumer<Boolean> consumer);

    int countFavoritesForComponent(ComponentName componentName);

    void create();

    void destroy();

    List<StructureInfo> getFavorites();

    List<StructureInfo> getFavoritesForComponent(ComponentName componentName);

    List<ControlInfo> getFavoritesForStructure(ComponentName componentName, CharSequence charSequence);

    boolean getLoadingData();

    StructureInfo getPreferredStructure();

    void loadForComponent(ComponentName componentName, Consumer<LoadData> consumer, Consumer<Runnable> consumer2);

    void onActionResponse(ComponentName componentName, String str, int i2);

    void refreshStatus(ComponentName componentName, Control control);

    void removeFavorite(ComponentName componentName, CharSequence charSequence, ControlInfo controlInfo);

    void removeStructures(ComponentName componentName);

    void replaceFavoritesForComponent(List<StructureInfo> list, Function0 function0);

    void replaceFavoritesForStructure(StructureInfo structureInfo, Function0 function0);

    void saveFavoritesForComponents(List<Control> list, ComponentName componentName);

    void seedFavoritesForComponents(List<ComponentName> list, Consumer<SeedResponse> consumer);

    void setLoadingData(boolean z2);

    void subscribeToFavorites(StructureInfo structureInfo);

    void unsubscribe();
}
