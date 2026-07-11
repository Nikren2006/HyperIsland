package miui.systemui.util.settings;

import F0.e;
import G0.a;
import android.content.ContentResolver;

/* JADX INFO: loaded from: classes4.dex */
public final class SystemSettingsImpl_Factory implements e {
    private final a contentResolverProvider;

    public SystemSettingsImpl_Factory(a aVar) {
        this.contentResolverProvider = aVar;
    }

    public static SystemSettingsImpl_Factory create(a aVar) {
        return new SystemSettingsImpl_Factory(aVar);
    }

    public static SystemSettingsImpl newInstance(ContentResolver contentResolver) {
        return new SystemSettingsImpl(contentResolver);
    }

    @Override // G0.a
    public SystemSettingsImpl get() {
        return newInstance((ContentResolver) this.contentResolverProvider.get());
    }
}
