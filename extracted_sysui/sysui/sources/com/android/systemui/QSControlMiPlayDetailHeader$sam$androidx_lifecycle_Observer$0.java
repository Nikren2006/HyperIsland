package com.android.systemui;

import androidx.lifecycle.Observer;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public final class QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0 implements Observer, kotlin.jvm.internal.i {
    private final /* synthetic */ Function1 function;

    public QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0(Function1 function) {
        kotlin.jvm.internal.n.g(function, "function");
        this.function = function;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof Observer) && (obj instanceof kotlin.jvm.internal.i)) {
            return kotlin.jvm.internal.n.c(getFunctionDelegate(), ((kotlin.jvm.internal.i) obj).getFunctionDelegate());
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
