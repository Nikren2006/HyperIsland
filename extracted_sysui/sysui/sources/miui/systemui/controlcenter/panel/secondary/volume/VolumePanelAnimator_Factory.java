package miui.systemui.controlcenter.panel.secondary.volume;

import F0.d;
import F0.e;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class VolumePanelAnimator_Factory implements e {
    private final G0.a contentControllerProvider;
    private final G0.a contextProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a volumePanelControllerProvider;
    private final G0.a windowViewControllerProvider;

    public VolumePanelAnimator_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        this.contextProvider = aVar;
        this.volumePanelControllerProvider = aVar2;
        this.contentControllerProvider = aVar3;
        this.windowViewControllerProvider = aVar4;
        this.mainPanelControllerProvider = aVar5;
    }

    public static VolumePanelAnimator_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        return new VolumePanelAnimator_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static VolumePanelAnimator newInstance(Context context, E0.a aVar, E0.a aVar2, E0.a aVar3, E0.a aVar4) {
        return new VolumePanelAnimator(context, aVar, aVar2, aVar3, aVar4);
    }

    @Override // G0.a
    public VolumePanelAnimator get() {
        return newInstance((Context) this.contextProvider.get(), d.a(this.volumePanelControllerProvider), d.a(this.contentControllerProvider), d.a(this.windowViewControllerProvider), d.a(this.mainPanelControllerProvider));
    }
}
