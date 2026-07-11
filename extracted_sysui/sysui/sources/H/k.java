package H;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.widget.TintTypedArray;
import t.AbstractC0741a;

/* JADX INFO: loaded from: classes2.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f280a = {AbstractC0741a.f6509i};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f281b = {AbstractC0741a.f6510j};

    public static void a(Context context) {
        e(context, f280a, "Theme.AppCompat");
    }

    public static void b(Context context, AttributeSet attributeSet, int i2, int i3) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.j.H6, i2, i3);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(t.j.J6, false);
        typedArrayObtainStyledAttributes.recycle();
        if (z2) {
            TypedValue typedValue = new TypedValue();
            if (!context.getTheme().resolveAttribute(AbstractC0741a.f6517q, typedValue, true) || (typedValue.type == 18 && typedValue.data == 0)) {
                c(context);
            }
        }
        a(context);
    }

    public static void c(Context context) {
        e(context, f281b, "Theme.MaterialComponents");
    }

    public static void d(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3, int... iArr2) {
        boolean zF;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.j.H6, i2, i3);
        if (!typedArrayObtainStyledAttributes.getBoolean(t.j.K6, false)) {
            typedArrayObtainStyledAttributes.recycle();
            return;
        }
        if (iArr2 == null || iArr2.length == 0) {
            zF = typedArrayObtainStyledAttributes.getResourceId(t.j.I6, -1) != -1;
        } else {
            zF = f(context, attributeSet, iArr, i2, i3, iArr2);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!zF) {
            throw new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
        }
    }

    public static void e(Context context, int[] iArr, String str) {
        if (h(context, iArr)) {
            return;
        }
        throw new IllegalArgumentException("The style on this component requires your app theme to be " + str + " (or a descendant).");
    }

    public static boolean f(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3, int... iArr2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, i3);
        for (int i4 : iArr2) {
            if (typedArrayObtainStyledAttributes.getResourceId(i4, -1) == -1) {
                typedArrayObtainStyledAttributes.recycle();
                return false;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        return true;
    }

    public static boolean g(Context context) {
        return L.b.b(context, AbstractC0741a.f6516p, false);
    }

    public static boolean h(Context context, int[] iArr) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (!typedArrayObtainStyledAttributes.hasValue(i2)) {
                typedArrayObtainStyledAttributes.recycle();
                return false;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        return true;
    }

    public static TypedArray i(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3, int... iArr2) {
        b(context, attributeSet, i2, i3);
        d(context, attributeSet, iArr, i2, i3, iArr2);
        return context.obtainStyledAttributes(attributeSet, iArr, i2, i3);
    }

    public static TintTypedArray j(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3, int... iArr2) {
        b(context, attributeSet, i2, i3);
        d(context, attributeSet, iArr, i2, i3, iArr2);
        return TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i2, i3);
    }
}
