package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import android.content.Context;
import android.view.LayoutInflater;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideLayoutInflaterFactory implements e {
    private final a contextProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideLayoutInflaterFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.contextProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideLayoutInflaterFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideLayoutInflaterFactory(controlCenterViewModule, aVar);
    }

    public static LayoutInflater provideLayoutInflater(ControlCenterViewModule controlCenterViewModule, Context context) {
        return (LayoutInflater) i.d(controlCenterViewModule.provideLayoutInflater(context));
    }

    @Override // G0.a
    public LayoutInflater get() {
        return provideLayoutInflater(this.module, (Context) this.contextProvider.get());
    }
}
