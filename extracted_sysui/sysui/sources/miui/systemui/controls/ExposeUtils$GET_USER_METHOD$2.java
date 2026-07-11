package miui.systemui.controls;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class ExposeUtils$GET_USER_METHOD$2 extends o implements Function0 {
    public static final ExposeUtils$GET_USER_METHOD$2 INSTANCE = new ExposeUtils$GET_USER_METHOD$2();

    public ExposeUtils$GET_USER_METHOD$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            return Context.class.getDeclaredMethod("getUser", null);
        } catch (Throwable th) {
            Log.e("ExposeUtils", "get Context.getUser method failed.", th);
            return null;
        }
    }
}
