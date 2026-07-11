package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import android.view.LayoutInflater;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_CreateRightMainPanelFactory implements e {
    private final a layoutInflaterProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_CreateRightMainPanelFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.layoutInflaterProvider = aVar;
    }

    public static ControlCenterViewModule_CreateRightMainPanelFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_CreateRightMainPanelFactory(controlCenterViewModule, aVar);
    }

    public static RecyclerView createRightMainPanel(ControlCenterViewModule controlCenterViewModule, LayoutInflater layoutInflater) {
        return (RecyclerView) i.d(controlCenterViewModule.createRightMainPanel(layoutInflater));
    }

    @Override // G0.a
    public RecyclerView get() {
        return createRightMainPanel(this.module, (LayoutInflater) this.layoutInflaterProvider.get());
    }
}
