package g0;

import android.util.Log;
import f1.m;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class h {
    public static final int a(String name, int i2) {
        n.g(name, "name");
        try {
            Object objInvoke = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, name);
            n.e(objInvoke, "null cannot be cast to non-null type kotlin.String");
            Integer numF = m.f((String) objInvoke);
            return numF != null ? numF.intValue() : i2;
        } catch (Exception e2) {
            Log.e("SystemProperty", "getIntValue name=" + name + ", def=" + i2 + ". exception: " + e2.getMessage());
            return i2;
        }
    }
}
