package androidx.savedstate;

import android.view.View;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ViewKt {
    public static final /* synthetic */ SavedStateRegistryOwner findViewTreeSavedStateRegistryOwner(View view) {
        n.g(view, "<this>");
        return ViewTreeSavedStateRegistryOwner.get(view);
    }
}
