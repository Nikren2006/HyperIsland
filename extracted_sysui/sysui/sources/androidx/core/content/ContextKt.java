package androidx.core.content;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.AttrRes;
import androidx.annotation.StyleRes;
import androidx.exifinterface.media.ExifInterface;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public final class ContextKt {
    public static final /* synthetic */ <T> T getSystemService(Context context) {
        kotlin.jvm.internal.n.l(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) ContextCompat.getSystemService(context, Object.class);
    }

    public static final void withStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr, @AttrRes int i2, @StyleRes int i3, Function1 function1) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, i3);
        function1.invoke(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static /* synthetic */ void withStyledAttributes$default(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3, Function1 function1, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            attributeSet = null;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = 0;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, i3);
        function1.invoke(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static final void withStyledAttributes(Context context, @StyleRes int i2, int[] iArr, Function1 function1) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i2, iArr);
        function1.invoke(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }
}
