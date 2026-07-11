package miui.systemui.controlcenter.panel.secondary.media;

import F0.d;
import F0.e;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class MediaPanelAnimator_Factory implements e {
    private final G0.a contentControllerProvider;
    private final G0.a contextProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a panelControllerProvider;
    private final G0.a windowViewControllerProvider;

    public MediaPanelAnimator_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        this.contextProvider = aVar;
        this.panelControllerProvider = aVar2;
        this.contentControllerProvider = aVar3;
        this.windowViewControllerProvider = aVar4;
        this.mainPanelControllerProvider = aVar5;
    }

    public static MediaPanelAnimator_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5) {
        return new MediaPanelAnimator_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static MediaPanelAnimator newInstance(Context context, E0.a aVar, MediaPanelDelegate mediaPanelDelegate, E0.a aVar2, E0.a aVar3) {
        return new MediaPanelAnimator(context, aVar, mediaPanelDelegate, aVar2, aVar3);
    }

    @Override // G0.a
    public MediaPanelAnimator get() {
        return newInstance((Context) this.contextProvider.get(), d.a(this.panelControllerProvider), (MediaPanelDelegate) this.contentControllerProvider.get(), d.a(this.windowViewControllerProvider), d.a(this.mainPanelControllerProvider));
    }
}
