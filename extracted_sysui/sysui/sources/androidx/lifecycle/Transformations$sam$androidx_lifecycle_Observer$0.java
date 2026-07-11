package androidx.lifecycle;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class Transformations$sam$androidx_lifecycle_Observer$0 implements Observer, i {
    private final /* synthetic */ Function1 function;

    public Transformations$sam$androidx_lifecycle_Observer$0(Function1 function) {
        n.g(function, "function");
        this.function = function;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof Observer) && (obj instanceof i)) {
            return n.c(getFunctionDelegate(), ((i) obj).getFunctionDelegate());
        }
        return false;
    }

    @Override // kotlin.jvm.internal.i
    public final H0.b getFunctionDelegate() {
        return this.function;
    }

    public final int hashCode() {
        return getFunctionDelegate().hashCode();
    }

    @Override // androidx.lifecycle.Observer
    public final /* synthetic */ void onChanged(Object obj) {
        this.function.invoke(obj);
    }
}
