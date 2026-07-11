package miui.systemui.controlcenter.panel.main.recyclerview;

import android.content.Context;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.shade.ShadeHeaderController;
import miui.systemui.controlcenter.panel.main.anim.SpreadRowsAnimator;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelAdapter_Factory_Factory implements F0.e {
    private final G0.a contextProvider;
    private final G0.a expandControllerProvider;
    private final G0.a frameCallbackProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a mirrorLifecycleProvider;
    private final G0.a qsListControllerProvider;
    private final G0.a shadeHeaderControllerProvider;
    private final G0.a spreadRowsAnimatorProvider;

    public MainPanelAdapter_Factory_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9) {
        this.contextProvider = aVar;
        this.spreadRowsAnimatorProvider = aVar2;
        this.lifecycleProvider = aVar3;
        this.frameCallbackProvider = aVar4;
        this.mainPanelControllerProvider = aVar5;
        this.shadeHeaderControllerProvider = aVar6;
        this.qsListControllerProvider = aVar7;
        this.expandControllerProvider = aVar8;
        this.mirrorLifecycleProvider = aVar9;
    }

    public static MainPanelAdapter_Factory_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9) {
        return new MainPanelAdapter_Factory_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9);
    }

    public static MainPanelAdapter.Factory newInstance(Context context, SpreadRowsAnimator spreadRowsAnimator, Lifecycle lifecycle, E0.a aVar, E0.a aVar2, ShadeHeaderController shadeHeaderController, QSListController qSListController, E0.a aVar3, Lifecycle lifecycle2) {
        return new MainPanelAdapter.Factory(context, spreadRowsAnimator, lifecycle, aVar, aVar2, shadeHeaderController, qSListController, aVar3, lifecycle2);
    }

    @Override // G0.a
    public MainPanelAdapter.Factory get() {
        return newInstance((Context) this.contextProvider.get(), (SpreadRowsAnimator) this.spreadRowsAnimatorProvider.get(), (Lifecycle) this.lifecycleProvider.get(), F0.d.a(this.frameCallbackProvider), F0.d.a(this.mainPanelControllerProvider), (ShadeHeaderController) this.shadeHeaderControllerProvider.get(), (QSListController) this.qsListControllerProvider.get(), F0.d.a(this.expandControllerProvider), (Lifecycle) this.mirrorLifecycleProvider.get());
    }
}
