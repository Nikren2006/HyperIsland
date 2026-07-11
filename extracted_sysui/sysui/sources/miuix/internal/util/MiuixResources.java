package miuix.internal.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.annotation.StyleableRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TintTypedArray;
import miuix.appcompat.R;

/* JADX INFO: loaded from: classes3.dex */
public class MiuixResources {
    private MiuixResources() {
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Context context, @NonNull TypedArray typedArray, @StyleableRes int i2) {
        int resourceId;
        ColorStateList colorStateList;
        return (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0 || (colorStateList = AppCompatResources.getColorStateList(context, resourceId)) == null) ? typedArray.getColorStateList(i2) : colorStateList;
    }

    private static int getComplexUnit(TypedValue typedValue) {
        return typedValue.getComplexUnit();
    }

    public static int getDimensionPixelSize(@NonNull Context context, @NonNull TypedArray typedArray, @StyleableRes int i2, int i3) {
        TypedValue typedValue = new TypedValue();
        if (!typedArray.getValue(i2, typedValue) || typedValue.type != 2) {
            return typedArray.getDimensionPixelSize(i2, i3);
        }
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, i3);
        typedArrayObtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @NonNull TypedArray typedArray, @StyleableRes int i2) {
        int resourceId;
        Drawable drawable;
        return (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0 || (drawable = AppCompatResources.getDrawable(context, resourceId)) == null) ? typedArray.getDrawable(i2) : drawable;
    }

    @StyleableRes
    public static int getIndexWithValue(@NonNull TypedArray typedArray, @StyleableRes int i2, @StyleableRes int i3) {
        return typedArray.hasValue(i2) ? i2 : i3;
    }

    public static int getUnscaledTextSize(@NonNull Context context, @StyleRes int i2, int i3) {
        if (i2 == 0) {
            return i3;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i2, R.styleable.TextAppearance);
        TypedValue typedValue = new TypedValue();
        boolean value = typedArrayObtainStyledAttributes.getValue(R.styleable.TextAppearance_android_textSize, typedValue);
        typedArrayObtainStyledAttributes.recycle();
        return !value ? i3 : getComplexUnit(typedValue) == 2 ? Math.round(TypedValue.complexToFloat(typedValue.data) * context.getResources().getDisplayMetrics().density) : TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Context context, @NonNull TintTypedArray tintTypedArray, @StyleableRes int i2) {
        int resourceId;
        ColorStateList colorStateList;
        return (!tintTypedArray.hasValue(i2) || (resourceId = tintTypedArray.getResourceId(i2, 0)) == 0 || (colorStateList = AppCompatResources.getColorStateList(context, resourceId)) == null) ? tintTypedArray.getColorStateList(i2) : colorStateList;
    }
}
