package androidx.fragment.app;

import android.os.Bundle;
import androidx.annotation.IdRes;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentTransactionKt {
    public static final /* synthetic */ <F extends Fragment> FragmentTransaction add(FragmentTransaction add, @IdRes int i2, String str, Bundle bundle) {
        n.g(add, "$this$add");
        n.l(4, "F");
        FragmentTransaction fragmentTransactionAdd = add.add(i2, Fragment.class, bundle, str);
        n.f(fragmentTransactionAdd, "add(containerViewId, F::class.java, args, tag)");
        return fragmentTransactionAdd;
    }

    public static /* synthetic */ FragmentTransaction add$default(FragmentTransaction add, int i2, String str, Bundle bundle, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            str = null;
        }
        if ((i3 & 4) != 0) {
            bundle = null;
        }
        n.g(add, "$this$add");
        n.l(4, "F");
        FragmentTransaction fragmentTransactionAdd = add.add(i2, Fragment.class, bundle, str);
        n.f(fragmentTransactionAdd, "add(containerViewId, F::class.java, args, tag)");
        return fragmentTransactionAdd;
    }

    public static final /* synthetic */ <F extends Fragment> FragmentTransaction replace(FragmentTransaction replace, @IdRes int i2, String str, Bundle bundle) {
        n.g(replace, "$this$replace");
        n.l(4, "F");
        FragmentTransaction fragmentTransactionReplace = replace.replace(i2, Fragment.class, bundle, str);
        n.f(fragmentTransactionReplace, "replace(containerViewId, F::class.java, args, tag)");
        return fragmentTransactionReplace;
    }

    public static /* synthetic */ FragmentTransaction replace$default(FragmentTransaction replace, int i2, String str, Bundle bundle, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            str = null;
        }
        if ((i3 & 4) != 0) {
            bundle = null;
        }
        n.g(replace, "$this$replace");
        n.l(4, "F");
        FragmentTransaction fragmentTransactionReplace = replace.replace(i2, Fragment.class, bundle, str);
        n.f(fragmentTransactionReplace, "replace(containerViewId, F::class.java, args, tag)");
        return fragmentTransactionReplace;
    }

    public static final /* synthetic */ <F extends Fragment> FragmentTransaction add(FragmentTransaction add, String tag, Bundle bundle) {
        n.g(add, "$this$add");
        n.g(tag, "tag");
        n.l(4, "F");
        FragmentTransaction fragmentTransactionAdd = add.add(Fragment.class, bundle, tag);
        n.f(fragmentTransactionAdd, "add(F::class.java, args, tag)");
        return fragmentTransactionAdd;
    }

    public static /* synthetic */ FragmentTransaction add$default(FragmentTransaction add, String tag, Bundle bundle, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bundle = null;
        }
        n.g(add, "$this$add");
        n.g(tag, "tag");
        n.l(4, "F");
        FragmentTransaction fragmentTransactionAdd = add.add(Fragment.class, bundle, tag);
        n.f(fragmentTransactionAdd, "add(F::class.java, args, tag)");
        return fragmentTransactionAdd;
    }
}
