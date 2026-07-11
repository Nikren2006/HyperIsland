package androidx.savedstate;

import android.view.View;
import e1.AbstractC0345j;
import e1.l;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeSavedStateRegistryOwner {
    public static final SavedStateRegistryOwner get(View view) {
        n.g(view, "<this>");
        return (SavedStateRegistryOwner) l.m(l.p(AbstractC0345j.e(view, ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$1.INSTANCE), ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$2.INSTANCE));
    }

    public static final void set(View view, SavedStateRegistryOwner savedStateRegistryOwner) {
        n.g(view, "<this>");
        view.setTag(R.id.view_tree_saved_state_registry_owner, savedStateRegistryOwner);
    }
}
