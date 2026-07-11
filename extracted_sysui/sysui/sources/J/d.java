package J;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.TypedValue;
import android.view.animation.AnimationUtils;
import androidx.core.graphics.PathParser;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.xiaomi.onetrack.util.aa;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d {
    public static float a(String[] strArr, int i2) {
        float f2 = Float.parseFloat(strArr[i2]);
        if (f2 >= 0.0f && f2 <= 1.0f) {
            return f2;
        }
        throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + f2);
    }

    public static String b(String str, String str2) {
        return str.substring(str2.length() + 1, str.length() - 1);
    }

    public static TimeInterpolator c(String str) {
        if (!e(str, "cubic-bezier")) {
            if (e(str, "path")) {
                return PathInterpolatorCompat.create(PathParser.createPathFromPathData(b(str, "path")));
            }
            throw new IllegalArgumentException("Invalid motion easing type: " + str);
        }
        String[] strArrSplit = b(str, "cubic-bezier").split(aa.f3429b);
        if (strArrSplit.length == 4) {
            return PathInterpolatorCompat.create(a(strArrSplit, 0), a(strArrSplit, 1), a(strArrSplit, 2), a(strArrSplit, 3));
        }
        throw new IllegalArgumentException("Motion easing theme attribute must have 4 control points if using bezier curve format; instead got: " + strArrSplit.length);
    }

    public static boolean d(String str) {
        return e(str, "cubic-bezier") || e(str, "path");
    }

    public static boolean e(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("(");
        return str.startsWith(sb.toString()) && str.endsWith(")");
    }

    public static int f(Context context, int i2, int i3) {
        return L.b.c(context, i2, i3);
    }

    public static TimeInterpolator g(Context context, int i2, TimeInterpolator timeInterpolator) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return timeInterpolator;
        }
        if (typedValue.type != 3) {
            throw new IllegalArgumentException("Motion easing theme attribute must be an @interpolator resource for ?attr/motionEasing*Interpolator attributes or a string for ?attr/motionEasing* attributes.");
        }
        String strValueOf = String.valueOf(typedValue.string);
        return d(strValueOf) ? c(strValueOf) : AnimationUtils.loadInterpolator(context, typedValue.resourceId);
    }
}
