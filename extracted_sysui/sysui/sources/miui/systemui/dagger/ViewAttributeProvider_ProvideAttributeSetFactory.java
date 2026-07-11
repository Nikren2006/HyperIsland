package miui.systemui.dagger;

import F0.i;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public final class ViewAttributeProvider_ProvideAttributeSetFactory implements F0.e {
    private final ViewAttributeProvider module;

    public ViewAttributeProvider_ProvideAttributeSetFactory(ViewAttributeProvider viewAttributeProvider) {
        this.module = viewAttributeProvider;
    }

    public static ViewAttributeProvider_ProvideAttributeSetFactory create(ViewAttributeProvider viewAttributeProvider) {
        return new ViewAttributeProvider_ProvideAttributeSetFactory(viewAttributeProvider);
    }

    public static AttributeSet provideAttributeSet(ViewAttributeProvider viewAttributeProvider) {
        return (AttributeSet) i.d(viewAttributeProvider.provideAttributeSet());
    }

    @Override // G0.a
    public AttributeSet get() {
        return provideAttributeSet(this.module);
    }
}
