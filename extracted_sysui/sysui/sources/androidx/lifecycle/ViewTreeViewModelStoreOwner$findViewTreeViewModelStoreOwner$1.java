package androidx.lifecycle;

import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$1 extends o implements Function1 {
    public static final ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$1 INSTANCE = new ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$1();

    public ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final View invoke(View view) {
        n.g(view, "view");
        Object parent = view.getParent();
        if (parent instanceof View) {
            return (View) parent;
        }
        return null;
    }
}
