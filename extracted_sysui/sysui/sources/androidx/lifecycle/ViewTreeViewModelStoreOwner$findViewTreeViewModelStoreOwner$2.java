package androidx.lifecycle;

import android.view.View;
import androidx.lifecycle.viewmodel.R;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$2 extends o implements Function1 {
    public static final ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$2 INSTANCE = new ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$2();

    public ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final ViewModelStoreOwner invoke(View view) {
        n.g(view, "view");
        Object tag = view.getTag(R.id.view_tree_view_model_store_owner);
        if (tag instanceof ViewModelStoreOwner) {
            return (ViewModelStoreOwner) tag;
        }
        return null;
    }
}
