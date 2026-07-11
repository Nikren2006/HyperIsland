package androidx.core.os;

import android.os.PersistableBundle;
import androidx.annotation.RequiresApi;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(21)
final class PersistableBundleApi21ImplKt {
    public static final PersistableBundleApi21ImplKt INSTANCE = new PersistableBundleApi21ImplKt();

    private PersistableBundleApi21ImplKt() {
    }

    public static final PersistableBundle createPersistableBundle(int i2) {
        return new PersistableBundle(i2);
    }

    public static final void putValue(PersistableBundle persistableBundle, String str, Object obj) {
        if (obj == null) {
            persistableBundle.putString(str, null);
            return;
        }
        if (obj instanceof Boolean) {
            PersistableBundleApi22ImplKt.putBoolean(persistableBundle, str, ((Boolean) obj).booleanValue());
            return;
        }
        if (obj instanceof Double) {
            persistableBundle.putDouble(str, ((Number) obj).doubleValue());
            return;
        }
        if (obj instanceof Integer) {
            persistableBundle.putInt(str, ((Number) obj).intValue());
            return;
        }
        if (obj instanceof Long) {
            persistableBundle.putLong(str, ((Number) obj).longValue());
            return;
        }
        if (obj instanceof String) {
            persistableBundle.putString(str, (String) obj);
            return;
        }
        if (obj instanceof PersistableBundle) {
            persistableBundle.putPersistableBundle(str, (PersistableBundle) obj);
            return;
        }
        if (obj instanceof boolean[]) {
            PersistableBundleApi22ImplKt.putBooleanArray(persistableBundle, str, (boolean[]) obj);
            return;
        }
        if (obj instanceof double[]) {
            persistableBundle.putDoubleArray(str, (double[]) obj);
            return;
        }
        if (obj instanceof int[]) {
            persistableBundle.putIntArray(str, (int[]) obj);
            return;
        }
        if (obj instanceof long[]) {
            persistableBundle.putLongArray(str, (long[]) obj);
            return;
        }
        if (!(obj instanceof Object[])) {
            throw new IllegalArgumentException("Unsupported value type " + obj.getClass().getCanonicalName() + " for key \"" + str + '\"');
        }
        Class<?> componentType = obj.getClass().getComponentType();
        n.d(componentType);
        if (String.class.isAssignableFrom(componentType)) {
            n.e(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            persistableBundle.putStringArray(str, (String[]) obj);
            return;
        }
        throw new IllegalArgumentException("Unsupported value array type " + componentType.getCanonicalName() + " for key \"" + str + '\"');
    }
}
