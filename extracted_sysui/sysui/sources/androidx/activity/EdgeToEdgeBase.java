package androidx.activity;

import android.view.View;
import android.view.Window;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
final class EdgeToEdgeBase implements EdgeToEdgeImpl {
    @Override // androidx.activity.EdgeToEdgeImpl
    public void setUp(SystemBarStyle statusBarStyle, SystemBarStyle navigationBarStyle, Window window, View view, boolean z2, boolean z3) {
        n.g(statusBarStyle, "statusBarStyle");
        n.g(navigationBarStyle, "navigationBarStyle");
        n.g(window, "window");
        n.g(view, "view");
    }
}
