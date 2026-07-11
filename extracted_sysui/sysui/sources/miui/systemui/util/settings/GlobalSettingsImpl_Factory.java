package miui.systemui.util.settings;

import F0.e;
import G0.a;
import android.content.ContentResolver;

/* JADX INFO: loaded from: classes4.dex */
public final class GlobalSettingsImpl_Factory implements e {
    private final a contentResolverProvider;

    public GlobalSettingsImpl_Factory(a aVar) {
        this.contentResolverProvider = aVar;
    }

    public static GlobalSettingsImpl_Factory create(a aVar) {
        return new GlobalSettingsImpl_Factory(aVar);
    }

    public static GlobalSettingsImpl newInstance(ContentResolver contentResolver) {
        return new GlobalSettingsImpl(contentResolver);
    }

    @Override // G0.a
    public GlobalSettingsImpl get() {
        return newInstance((ContentResolver) this.contentResolverProvider.get());
    }
}
