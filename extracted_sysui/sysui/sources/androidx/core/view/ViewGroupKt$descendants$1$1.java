package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import e1.InterfaceC0340e;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public final class ViewGroupKt$descendants$1$1 extends kotlin.jvm.internal.o implements Function1 {
    public static final ViewGroupKt$descendants$1$1 INSTANCE = new ViewGroupKt$descendants$1$1();

    public ViewGroupKt$descendants$1$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Iterator<View> invoke(View view) {
        InterfaceC0340e children;
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup == null || (children = ViewGroupKt.getChildren(viewGroup)) == null) {
            return null;
        }
        return children.iterator();
    }
}
