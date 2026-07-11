package androidx.lifecycle;

import android.view.View;
import androidx.lifecycle.viewmodel.R;
import e1.AbstractC0345j;
import e1.l;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeViewModelStoreOwner {
    public static final ViewModelStoreOwner get(View view) {
        n.g(view, "<this>");
        return (ViewModelStoreOwner) l.m(l.p(AbstractC0345j.e(view, ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$1.INSTANCE), ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$2.INSTANCE));
    }

    public static final void set(View view, ViewModelStoreOwner viewModelStoreOwner) {
        n.g(view, "<this>");
        view.setTag(R.id.view_tree_view_model_store_owner, viewModelStoreOwner);
    }
}
