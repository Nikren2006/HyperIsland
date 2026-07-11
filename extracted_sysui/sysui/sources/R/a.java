package R;

import L.b;
import L.c;
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import t.AbstractC0741a;
import t.j;

/* JADX INFO: loaded from: classes2.dex */
public class a extends AppCompatTextView {
    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public static boolean b(Context context) {
        return b.b(context, AbstractC0741a.f6497L, true);
    }

    public static int c(Resources.Theme theme, AttributeSet attributeSet, int i2, int i3) {
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, j.u3, i2, i3);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(j.v3, -1);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }

    public static int e(Context context, TypedArray typedArray, int... iArr) {
        int iC = -1;
        for (int i2 = 0; i2 < iArr.length && iC < 0; i2++) {
            iC = c.c(context, typedArray, iArr[i2], -1);
        }
        return iC;
    }

    public static boolean f(Context context, Resources.Theme theme, AttributeSet attributeSet, int i2, int i3) {
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, j.u3, i2, i3);
        int iE = e(context, typedArrayObtainStyledAttributes, j.w3, j.x3);
        typedArrayObtainStyledAttributes.recycle();
        return iE != -1;
    }

    public final void a(Resources.Theme theme, int i2) {
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(i2, j.q3);
        int iE = e(getContext(), typedArrayObtainStyledAttributes, j.s3, j.t3);
        typedArrayObtainStyledAttributes.recycle();
        if (iE >= 0) {
            setLineHeight(iE);
        }
    }

    public final void d(AttributeSet attributeSet, int i2, int i3) {
        int iC;
        Context context = getContext();
        if (b(context)) {
            Resources.Theme theme = context.getTheme();
            if (f(context, theme, attributeSet, i2, i3) || (iC = c(theme, attributeSet, i2, i3)) == -1) {
                return;
            }
            a(theme, iC);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        if (b(context)) {
            a(context.getTheme(), i2);
        }
    }

    public a(Context context, AttributeSet attributeSet, int i2) {
        super(S.a.c(context, attributeSet, i2, 0), attributeSet, i2);
        d(attributeSet, i2, 0);
    }
}
