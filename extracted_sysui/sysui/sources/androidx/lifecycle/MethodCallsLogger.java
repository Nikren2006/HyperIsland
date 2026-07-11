package androidx.lifecycle;

import androidx.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class MethodCallsLogger {
    private final Map<String, Integer> calledMethods = new HashMap();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public boolean approveCall(String name, int i2) {
        n.g(name, "name");
        Integer num = this.calledMethods.get(name);
        int iIntValue = num != null ? num.intValue() : 0;
        boolean z2 = (iIntValue & i2) != 0;
        this.calledMethods.put(name, Integer.valueOf(i2 | iIntValue));
        return !z2;
    }
}
