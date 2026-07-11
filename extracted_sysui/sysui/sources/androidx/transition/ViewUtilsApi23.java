package androidx.transition;

import android.annotation.SuppressLint;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(23)
class ViewUtilsApi23 extends ViewUtilsApi22 {
    private static boolean sTryHiddenSetTransitionVisibility = true;

    @Override // androidx.transition.ViewUtilsBase
    @SuppressLint({"NewApi"})
    public void setTransitionVisibility(@NonNull View view, int i2) {
        if (sTryHiddenSetTransitionVisibility) {
            try {
                view.setTransitionVisibility(i2);
            } catch (NoSuchMethodError unused) {
                sTryHiddenSetTransitionVisibility = false;
            }
        }
    }
}
