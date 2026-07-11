package androidx.core.content.res;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import androidx.annotation.RequiresApi;
import androidx.annotation.StyleableRes;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(26)
final class TypedArrayApi26ImplKt {
    public static final TypedArrayApi26ImplKt INSTANCE = new TypedArrayApi26ImplKt();

    private TypedArrayApi26ImplKt() {
    }

    public static final Typeface getFont(TypedArray typedArray, @StyleableRes int i2) {
        Typeface font = typedArray.getFont(i2);
        n.d(font);
        return font;
    }
}
