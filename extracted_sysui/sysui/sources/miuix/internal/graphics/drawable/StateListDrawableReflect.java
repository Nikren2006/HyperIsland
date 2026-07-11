package miuix.internal.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

/* JADX INFO: loaded from: classes3.dex */
public class StateListDrawableReflect {
    private StateListDrawableReflect() {
    }

    public static int getStateCount(StateListDrawable stateListDrawable) {
        return stateListDrawable.getStateCount();
    }

    public static Drawable getStateDrawable(StateListDrawable stateListDrawable, int i2) {
        return stateListDrawable.getStateDrawable(i2);
    }

    public static int[] getStateSet(StateListDrawable stateListDrawable, int i2) {
        return stateListDrawable.getStateSet(i2);
    }
}
