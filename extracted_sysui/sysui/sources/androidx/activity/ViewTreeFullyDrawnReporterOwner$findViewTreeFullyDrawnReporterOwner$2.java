package androidx.activity;

import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$2 extends o implements Function1 {
    public static final ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$2 INSTANCE = new ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$2();

    public ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final FullyDrawnReporterOwner invoke(View it) {
        n.g(it, "it");
        Object tag = it.getTag(R.id.report_drawn);
        if (tag instanceof FullyDrawnReporterOwner) {
            return (FullyDrawnReporterOwner) tag;
        }
        return null;
    }
}
