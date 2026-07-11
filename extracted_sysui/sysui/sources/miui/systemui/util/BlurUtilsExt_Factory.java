package miui.systemui.util;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public final class BlurUtilsExt_Factory implements F0.e {
    private final G0.a contextProvider;

    public BlurUtilsExt_Factory(G0.a aVar) {
        this.contextProvider = aVar;
    }

    public static BlurUtilsExt_Factory create(G0.a aVar) {
        return new BlurUtilsExt_Factory(aVar);
    }

    public static BlurUtilsExt newInstance(Context context) {
        return new BlurUtilsExt(context);
    }

    @Override // G0.a
    public BlurUtilsExt get() {
        return newInstance((Context) this.contextProvider.get());
    }
}
