package miui.systemui.dagger;

import F0.i;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class ViewAttributeProvider_ProvideContextFactory implements F0.e {
    private final ViewAttributeProvider module;

    public ViewAttributeProvider_ProvideContextFactory(ViewAttributeProvider viewAttributeProvider) {
        this.module = viewAttributeProvider;
    }

    public static ViewAttributeProvider_ProvideContextFactory create(ViewAttributeProvider viewAttributeProvider) {
        return new ViewAttributeProvider_ProvideContextFactory(viewAttributeProvider);
    }

    public static Context provideContext(ViewAttributeProvider viewAttributeProvider) {
        return (Context) i.d(viewAttributeProvider.provideContext());
    }

    @Override // G0.a
    public Context get() {
        return provideContext(this.module);
    }
}
