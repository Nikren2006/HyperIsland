package S;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.view.ContextThemeWrapper;
import t.AbstractC0741a;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f697a = {R.attr.theme, AbstractC0741a.f6499N};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f698b = {AbstractC0741a.f6522v};

    public static int a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f697a);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId != 0 ? resourceId : resourceId2;
    }

    public static int b(Context context, AttributeSet attributeSet, int i2, int i3) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f698b, i2, i3);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }

    public static Context c(Context context, AttributeSet attributeSet, int i2, int i3) {
        int iB = b(context, attributeSet, i2, i3);
        boolean z2 = (context instanceof ContextThemeWrapper) && ((ContextThemeWrapper) context).getThemeResId() == iB;
        if (iB == 0 || z2) {
            return context;
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, iB);
        int iA = a(context, attributeSet);
        if (iA != 0) {
            contextThemeWrapper.getTheme().applyStyle(iA, true);
        }
        return contextThemeWrapper;
    }
}
