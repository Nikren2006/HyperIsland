package androidx.activity;

import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeOnBackPressedDispatcherOwner$findViewTreeOnBackPressedDispatcherOwner$1 extends o implements Function1 {
    public static final ViewTreeOnBackPressedDispatcherOwner$findViewTreeOnBackPressedDispatcherOwner$1 INSTANCE = new ViewTreeOnBackPressedDispatcherOwner$findViewTreeOnBackPressedDispatcherOwner$1();

    public ViewTreeOnBackPressedDispatcherOwner$findViewTreeOnBackPressedDispatcherOwner$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final View invoke(View it) {
        n.g(it, "it");
        Object parent = it.getParent();
        if (parent instanceof View) {
            return (View) parent;
        }
        return null;
    }
}
