package miui.systemui.util;

import android.view.View;
import androidx.core.app.NotificationCompat;
import com.mi.widget.core.Origin;
import com.mi.widget.shader.CallingShader;

/* JADX INFO: loaded from: classes4.dex */
public final class ShaderUtil {
    public static final ShaderUtil INSTANCE = new ShaderUtil();

    private ShaderUtil() {
    }

    public static /* synthetic */ CallingShader setShader$default(ShaderUtil shaderUtil, String str, View view, float f2, float f3, Origin origin, boolean z2, int i2, Object obj) {
        if ((i2 & 32) != 0) {
            z2 = true;
        }
        return shaderUtil.setShader(str, view, f2, f3, origin, z2);
    }

    public final CallingShader<View> setShader(String str, View view, float f2, float f3, Origin origin) {
        kotlin.jvm.internal.n.g(view, "view");
        kotlin.jvm.internal.n.g(origin, "origin");
        return setShader$default(this, str, view, f2, f3, origin, false, 32, null);
    }

    public final CallingShader<View> setShader(String str, View view, float f2, float f3, Origin origin, boolean z2) {
        kotlin.jvm.internal.n.g(view, "view");
        kotlin.jvm.internal.n.g(origin, "origin");
        if (!kotlin.jvm.internal.n.c(str, NotificationCompat.CATEGORY_CALL)) {
            return null;
        }
        CallingShader<View> callingShader = new CallingShader<>(view, z2, null, 4, null);
        callingShader.setGlowIconOrigin(origin);
        callingShader.setGlowIconWidth(f2);
        callingShader.setGlowIconOffset(f3);
        return callingShader;
    }
}
