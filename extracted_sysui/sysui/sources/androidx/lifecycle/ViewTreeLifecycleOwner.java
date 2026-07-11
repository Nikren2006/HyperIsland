package androidx.lifecycle;

import android.view.View;
import androidx.lifecycle.runtime.R;
import e1.AbstractC0345j;
import e1.l;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeLifecycleOwner {
    public static final LifecycleOwner get(View view) {
        n.g(view, "<this>");
        return (LifecycleOwner) l.m(l.p(AbstractC0345j.e(view, ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1.INSTANCE), ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$2.INSTANCE));
    }

    public static final void set(View view, LifecycleOwner lifecycleOwner) {
        n.g(view, "<this>");
        view.setTag(R.id.view_tree_lifecycle_owner, lifecycleOwner);
    }
}
