package miui.systemui.util.settings;

import F0.e;
import G0.a;
import android.content.ContentResolver;

/* JADX INFO: loaded from: classes4.dex */
public final class SecureSettingsImpl_Factory implements e {
    private final a contentResolverProvider;

    public SecureSettingsImpl_Factory(a aVar) {
        this.contentResolverProvider = aVar;
    }

    public static SecureSettingsImpl_Factory create(a aVar) {
        return new SecureSettingsImpl_Factory(aVar);
    }

    public static SecureSettingsImpl newInstance(ContentResolver contentResolver) {
        return new SecureSettingsImpl(contentResolver);
    }

    @Override // G0.a
    public SecureSettingsImpl get() {
        return newInstance((ContentResolver) this.contentResolverProvider.get());
    }
}
