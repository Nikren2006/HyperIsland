package androidx.core.os;

import H0.i;
import android.os.PersistableBundle;
import androidx.annotation.RequiresApi;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class PersistableBundleKt {
    @RequiresApi(21)
    public static final PersistableBundle persistableBundleOf(i... iVarArr) {
        PersistableBundle persistableBundleCreatePersistableBundle = PersistableBundleApi21ImplKt.createPersistableBundle(iVarArr.length);
        for (i iVar : iVarArr) {
            PersistableBundleApi21ImplKt.putValue(persistableBundleCreatePersistableBundle, (String) iVar.a(), iVar.b());
        }
        return persistableBundleCreatePersistableBundle;
    }

    @RequiresApi(21)
    public static final PersistableBundle toPersistableBundle(Map<String, ? extends Object> map) {
        PersistableBundle persistableBundleCreatePersistableBundle = PersistableBundleApi21ImplKt.createPersistableBundle(map.size());
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            PersistableBundleApi21ImplKt.putValue(persistableBundleCreatePersistableBundle, entry.getKey(), entry.getValue());
        }
        return persistableBundleCreatePersistableBundle;
    }

    @RequiresApi(21)
    public static final PersistableBundle persistableBundleOf() {
        return PersistableBundleApi21ImplKt.createPersistableBundle(0);
    }
}
