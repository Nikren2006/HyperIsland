package f0;

import H0.i;
import android.util.Log;
import android.util.Size;
import com.mi.widget.core.Origin;
import com.xiaomi.onetrack.util.aa;
import kotlin.jvm.internal.n;

/* JADX INFO: renamed from: f0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0352f {

    /* JADX INFO: renamed from: f0.f$a */
    public /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4235a;

        static {
            int[] iArr = new int[Origin.values().length];
            try {
                iArr[Origin.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Origin.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            f4235a = iArr;
        }
    }

    public static final i a(Origin origin, Size viewSize, float f2, float f3, int i2, String debugName) {
        n.g(origin, "<this>");
        n.g(viewSize, "viewSize");
        n.g(debugName, "debugName");
        int i3 = a.f4235a[origin.ordinal()];
        Origin origin2 = i3 != 1 ? i3 != 2 ? origin : i2 == 1 ? Origin.LEFT : Origin.RIGHT : i2 == 1 ? Origin.RIGHT : Origin.LEFT;
        float width = origin2 == Origin.LEFT ? (f2 / 2.0f) + f3 : (viewSize.getWidth() - f3) - (f2 / 2.0f);
        Log.d(debugName, "applyShader size=" + viewSize + ", icon=[" + f2 + ", " + f3 + "] inOrigin=" + origin + ", origin=" + origin2 + ", luminous-point=[" + width + aa.f3429b + (viewSize.getHeight() / 2) + "]");
        if (width < 0.0f) {
            Log.w(debugName, "applyShader luminous-point x[" + width + "] is negative, Please ensure you input parameters is correct.");
        }
        return new i(Float.valueOf(Math.max(0.0f, width)), Float.valueOf(viewSize.getHeight() / 2.0f));
    }
}
