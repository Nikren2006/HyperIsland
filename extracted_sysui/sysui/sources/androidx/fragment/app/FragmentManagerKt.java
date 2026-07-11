package androidx.fragment.app;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentManagerKt {
    public static final void commit(FragmentManager commit, boolean z2, Function1 body) {
        n.g(commit, "$this$commit");
        n.g(body, "body");
        FragmentTransaction fragmentTransactionBeginTransaction = commit.beginTransaction();
        n.f(fragmentTransactionBeginTransaction, "beginTransaction()");
        body.invoke(fragmentTransactionBeginTransaction);
        if (z2) {
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        } else {
            fragmentTransactionBeginTransaction.commit();
        }
    }

    public static /* synthetic */ void commit$default(FragmentManager commit, boolean z2, Function1 body, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        n.g(commit, "$this$commit");
        n.g(body, "body");
        FragmentTransaction fragmentTransactionBeginTransaction = commit.beginTransaction();
        n.f(fragmentTransactionBeginTransaction, "beginTransaction()");
        body.invoke(fragmentTransactionBeginTransaction);
        if (z2) {
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        } else {
            fragmentTransactionBeginTransaction.commit();
        }
    }

    public static final void commitNow(FragmentManager commitNow, boolean z2, Function1 body) {
        n.g(commitNow, "$this$commitNow");
        n.g(body, "body");
        FragmentTransaction fragmentTransactionBeginTransaction = commitNow.beginTransaction();
        n.f(fragmentTransactionBeginTransaction, "beginTransaction()");
        body.invoke(fragmentTransactionBeginTransaction);
        if (z2) {
            fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
        } else {
            fragmentTransactionBeginTransaction.commitNow();
        }
    }

    public static /* synthetic */ void commitNow$default(FragmentManager commitNow, boolean z2, Function1 body, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        n.g(commitNow, "$this$commitNow");
        n.g(body, "body");
        FragmentTransaction fragmentTransactionBeginTransaction = commitNow.beginTransaction();
        n.f(fragmentTransactionBeginTransaction, "beginTransaction()");
        body.invoke(fragmentTransactionBeginTransaction);
        if (z2) {
            fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
        } else {
            fragmentTransactionBeginTransaction.commitNow();
        }
    }

    public static final void transaction(FragmentManager transaction, boolean z2, boolean z3, Function1 body) {
        n.g(transaction, "$this$transaction");
        n.g(body, "body");
        FragmentTransaction fragmentTransactionBeginTransaction = transaction.beginTransaction();
        n.f(fragmentTransactionBeginTransaction, "beginTransaction()");
        body.invoke(fragmentTransactionBeginTransaction);
        if (z2) {
            if (z3) {
                fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
                return;
            } else {
                fragmentTransactionBeginTransaction.commitNow();
                return;
            }
        }
        if (z3) {
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        } else {
            fragmentTransactionBeginTransaction.commit();
        }
    }

    public static /* synthetic */ void transaction$default(FragmentManager transaction, boolean z2, boolean z3, Function1 body, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        if ((i2 & 2) != 0) {
            z3 = false;
        }
        n.g(transaction, "$this$transaction");
        n.g(body, "body");
        FragmentTransaction fragmentTransactionBeginTransaction = transaction.beginTransaction();
        n.f(fragmentTransactionBeginTransaction, "beginTransaction()");
        body.invoke(fragmentTransactionBeginTransaction);
        if (z2) {
            if (z3) {
                fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
                return;
            } else {
                fragmentTransactionBeginTransaction.commitNow();
                return;
            }
        }
        if (z3) {
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        } else {
            fragmentTransactionBeginTransaction.commit();
        }
    }
}
