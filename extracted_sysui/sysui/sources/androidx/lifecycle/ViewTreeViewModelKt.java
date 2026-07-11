package androidx.lifecycle;

import android.view.View;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeViewModelKt {
    public static final /* synthetic */ ViewModelStoreOwner findViewTreeViewModelStoreOwner(View view) {
        n.g(view, "view");
        return ViewTreeViewModelStoreOwner.get(view);
    }
}
