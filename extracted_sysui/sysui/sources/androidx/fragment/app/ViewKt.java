package androidx.fragment.app;

import android.view.View;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ViewKt {
    public static final <F extends Fragment> F findFragment(View findFragment) {
        n.g(findFragment, "$this$findFragment");
        F f2 = (F) FragmentManager.findFragment(findFragment);
        n.f(f2, "FragmentManager.findFragment(this)");
        return f2;
    }
}
