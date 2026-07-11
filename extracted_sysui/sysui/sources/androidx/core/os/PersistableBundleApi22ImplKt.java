package androidx.core.os;

import android.os.PersistableBundle;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(22)
final class PersistableBundleApi22ImplKt {
    public static final PersistableBundleApi22ImplKt INSTANCE = new PersistableBundleApi22ImplKt();

    private PersistableBundleApi22ImplKt() {
    }

    public static final void putBoolean(PersistableBundle persistableBundle, String str, boolean z2) {
        persistableBundle.putBoolean(str, z2);
    }

    public static final void putBooleanArray(PersistableBundle persistableBundle, String str, boolean[] zArr) {
        persistableBundle.putBooleanArray(str, zArr);
    }
}
