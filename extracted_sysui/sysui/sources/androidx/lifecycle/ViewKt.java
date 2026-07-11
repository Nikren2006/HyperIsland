package androidx.lifecycle;

import android.view.View;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ViewKt {
    public static final /* synthetic */ LifecycleOwner findViewTreeLifecycleOwner(View view) {
        n.g(view, "<this>");
        return ViewTreeLifecycleOwner.get(view);
    }
}
