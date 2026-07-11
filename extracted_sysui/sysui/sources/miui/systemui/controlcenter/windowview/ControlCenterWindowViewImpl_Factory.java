package miui.systemui.controlcenter.windowview;

import android.content.Context;
import android.util.AttributeSet;
import miui.systemui.controlcenter.dagger.ControlCenterViewComponent;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterWindowViewImpl_Factory implements F0.e {
    private final G0.a attributeSetProvider;
    private final G0.a componentFactoryProvider;
    private final G0.a contextProvider;

    public ControlCenterWindowViewImpl_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.contextProvider = aVar;
        this.attributeSetProvider = aVar2;
        this.componentFactoryProvider = aVar3;
    }

    public static ControlCenterWindowViewImpl_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new ControlCenterWindowViewImpl_Factory(aVar, aVar2, aVar3);
    }

    public static ControlCenterWindowViewImpl newInstance(Context context, AttributeSet attributeSet, ControlCenterViewComponent.Factory factory) {
        return new ControlCenterWindowViewImpl(context, attributeSet, factory);
    }

    @Override // G0.a
    public ControlCenterWindowViewImpl get() {
        return newInstance((Context) this.contextProvider.get(), (AttributeSet) this.attributeSetProvider.get(), (ControlCenterViewComponent.Factory) this.componentFactoryProvider.get());
    }
}
