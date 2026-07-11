package miui.systemui.dynamicisland.anim;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miui.systemui.dynamicisland.DynamicIslandConstants;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandAnimationController$Companion$setMiSelfBlurMethod$2 extends o implements Function0 {
    public static final DynamicIslandAnimationController$Companion$setMiSelfBlurMethod$2 INSTANCE = new DynamicIslandAnimationController$Companion$setMiSelfBlurMethod$2();

    public DynamicIslandAnimationController$Companion$setMiSelfBlurMethod$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            return Class.forName("android.view.View").getMethod("setMiSelfBlur", Integer.TYPE, ArrayList.class);
        } catch (Throwable th) {
            Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "Get setMiSelfBlur method failed.", th);
            return null;
        }
    }
}
