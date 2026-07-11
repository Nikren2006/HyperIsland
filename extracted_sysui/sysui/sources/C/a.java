package C;

import L.b;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    public static int a(int i2, int i3) {
        return ColorUtils.setAlphaComponent(i2, (Color.alpha(i2) * i3) / 255);
    }

    public static int b(Context context, int i2, int i3) {
        Integer numF = f(context, i2);
        return numF != null ? numF.intValue() : i3;
    }

    public static int c(Context context, int i2, String str) {
        return l(context, b.e(context, i2, str));
    }

    public static int d(View view, int i2) {
        return l(view.getContext(), b.f(view, i2));
    }

    public static int e(View view, int i2, int i3) {
        return b(view.getContext(), i2, i3);
    }

    public static Integer f(Context context, int i2) {
        TypedValue typedValueA = b.a(context, i2);
        if (typedValueA != null) {
            return Integer.valueOf(l(context, typedValueA));
        }
        return null;
    }

    public static ColorStateList g(Context context, int i2) {
        TypedValue typedValueA = b.a(context, i2);
        if (typedValueA == null) {
            return null;
        }
        int i3 = typedValueA.resourceId;
        if (i3 != 0) {
            return ContextCompat.getColorStateList(context, i3);
        }
        int i4 = typedValueA.data;
        if (i4 != 0) {
            return ColorStateList.valueOf(i4);
        }
        return null;
    }

    public static boolean h(int i2) {
        return i2 != 0 && ColorUtils.calculateLuminance(i2) > 0.5d;
    }

    public static int i(int i2, int i3) {
        return ColorUtils.compositeColors(i3, i2);
    }

    public static int j(int i2, int i3, float f2) {
        return i(i2, ColorUtils.setAlphaComponent(i3, Math.round(Color.alpha(i3) * f2)));
    }

    public static int k(View view, int i2, int i3, float f2) {
        return j(d(view, i2), d(view, i3), f2);
    }

    public static int l(Context context, TypedValue typedValue) {
        int i2 = typedValue.resourceId;
        return i2 != 0 ? ContextCompat.getColor(context, i2) : typedValue.data;
    }
}
