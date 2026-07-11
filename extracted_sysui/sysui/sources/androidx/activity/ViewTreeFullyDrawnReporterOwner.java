package androidx.activity;

import android.view.View;
import e1.AbstractC0345j;
import e1.l;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeFullyDrawnReporterOwner {
    public static final FullyDrawnReporterOwner get(View view) {
        n.g(view, "<this>");
        return (FullyDrawnReporterOwner) l.m(l.p(AbstractC0345j.e(view, ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$1.INSTANCE), ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$2.INSTANCE));
    }

    public static final void set(View view, FullyDrawnReporterOwner fullyDrawnReporterOwner) {
        n.g(view, "<this>");
        n.g(fullyDrawnReporterOwner, "fullyDrawnReporterOwner");
        view.setTag(R.id.report_drawn, fullyDrawnReporterOwner);
    }
}
