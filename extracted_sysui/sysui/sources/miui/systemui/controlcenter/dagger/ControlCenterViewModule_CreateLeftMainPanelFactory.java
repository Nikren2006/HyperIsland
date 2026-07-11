package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import android.view.LayoutInflater;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_CreateLeftMainPanelFactory implements e {
    private final a layoutInflaterProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_CreateLeftMainPanelFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.layoutInflaterProvider = aVar;
    }

    public static ControlCenterViewModule_CreateLeftMainPanelFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_CreateLeftMainPanelFactory(controlCenterViewModule, aVar);
    }

    public static RecyclerView createLeftMainPanel(ControlCenterViewModule controlCenterViewModule, LayoutInflater layoutInflater) {
        return (RecyclerView) i.d(controlCenterViewModule.createLeftMainPanel(layoutInflater));
    }

    @Override // G0.a
    public RecyclerView get() {
        return createLeftMainPanel(this.module, (LayoutInflater) this.layoutInflaterProvider.get());
    }
}
