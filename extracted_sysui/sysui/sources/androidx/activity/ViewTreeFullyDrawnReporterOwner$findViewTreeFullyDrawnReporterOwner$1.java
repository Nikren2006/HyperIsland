package androidx.activity;

import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$1 extends o implements Function1 {
    public static final ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$1 INSTANCE = new ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$1();

    public ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$1() {
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
