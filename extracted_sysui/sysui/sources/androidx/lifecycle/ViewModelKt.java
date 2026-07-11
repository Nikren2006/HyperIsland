package androidx.lifecycle;

import g1.E;
import g1.F0;
import g1.Q;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ViewModelKt {
    private static final String JOB_KEY = "androidx.lifecycle.ViewModelCoroutineScope.JOB_KEY";

    public static final E getViewModelScope(ViewModel viewModel) {
        n.g(viewModel, "<this>");
        E e2 = (E) viewModel.getTag(JOB_KEY);
        if (e2 != null) {
            return e2;
        }
        Object tagIfAbsent = viewModel.setTagIfAbsent(JOB_KEY, new CloseableCoroutineScope(F0.b(null, 1, null).plus(Q.c().z())));
        n.f(tagIfAbsent, "setTagIfAbsent(\n        …Main.immediate)\n        )");
        return (E) tagIfAbsent;
    }
}
