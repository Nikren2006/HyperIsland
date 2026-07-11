package androidx.core.view;

import android.view.ViewParent;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public /* synthetic */ class ViewKt$ancestors$1 extends kotlin.jvm.internal.l implements Function1 {
    public static final ViewKt$ancestors$1 INSTANCE = new ViewKt$ancestors$1();

    public ViewKt$ancestors$1() {
        super(1, ViewParent.class, "getParent", "getParent()Landroid/view/ViewParent;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final ViewParent invoke(ViewParent viewParent) {
        return viewParent.getParent();
    }
}
