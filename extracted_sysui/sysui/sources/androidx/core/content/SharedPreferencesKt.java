package androidx.core.content;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public final class SharedPreferencesKt {
    @SuppressLint({"ApplySharedPref"})
    public static final void edit(SharedPreferences sharedPreferences, boolean z2, Function1 function1) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        function1.invoke(editorEdit);
        if (z2) {
            editorEdit.commit();
        } else {
            editorEdit.apply();
        }
    }

    public static /* synthetic */ void edit$default(SharedPreferences sharedPreferences, boolean z2, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        function1.invoke(editorEdit);
        if (z2) {
            editorEdit.commit();
        } else {
            editorEdit.apply();
        }
    }
}
