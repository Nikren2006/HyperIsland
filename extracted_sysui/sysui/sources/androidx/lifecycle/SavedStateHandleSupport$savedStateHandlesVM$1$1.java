package androidx.lifecycle;

import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandleSupport$savedStateHandlesVM$1$1 extends o implements Function1 {
    public static final SavedStateHandleSupport$savedStateHandlesVM$1$1 INSTANCE = new SavedStateHandleSupport$savedStateHandlesVM$1$1();

    public SavedStateHandleSupport$savedStateHandlesVM$1$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final SavedStateHandlesVM invoke(CreationExtras initializer) {
        n.g(initializer, "$this$initializer");
        return new SavedStateHandlesVM();
    }
}
