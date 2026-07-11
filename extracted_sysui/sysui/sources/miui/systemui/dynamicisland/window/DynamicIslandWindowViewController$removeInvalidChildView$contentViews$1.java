package miui.systemui.dynamicisland.window;

import android.view.View;
import kotlin.jvm.functions.Function1;
import miui.systemui.dynamicisland.DynamicIslandBackgroundView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandWindowViewController$removeInvalidChildView$contentViews$1 extends kotlin.jvm.internal.o implements Function1 {
    public static final DynamicIslandWindowViewController$removeInvalidChildView$contentViews$1 INSTANCE = new DynamicIslandWindowViewController$removeInvalidChildView$contentViews$1();

    public DynamicIslandWindowViewController$removeInvalidChildView$contentViews$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final DynamicIslandContentView invoke(View children) {
        kotlin.jvm.internal.n.g(children, "children");
        boolean z2 = children instanceof DynamicIslandBackgroundView;
        DynamicIslandBackgroundView dynamicIslandBackgroundView = z2 ? (DynamicIslandBackgroundView) children : null;
        if ((dynamicIslandBackgroundView != null ? dynamicIslandBackgroundView.getChildCount() : 0) <= 0) {
            return null;
        }
        DynamicIslandBackgroundView dynamicIslandBackgroundView2 = z2 ? (DynamicIslandBackgroundView) children : null;
        View childAt = dynamicIslandBackgroundView2 != null ? dynamicIslandBackgroundView2.getChildAt(0) : null;
        if (childAt instanceof DynamicIslandContentView) {
            return (DynamicIslandContentView) childAt;
        }
        return null;
    }
}
