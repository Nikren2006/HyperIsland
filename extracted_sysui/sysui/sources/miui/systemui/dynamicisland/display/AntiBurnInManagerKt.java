package miui.systemui.dynamicisland.display;

import I0.C0177e;
import Y0.b;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class AntiBurnInManagerKt {
    public static final List<View> findAllViewsById(ViewGroup viewGroup, int i2) {
        n.g(viewGroup, "<this>");
        ArrayList arrayList = new ArrayList();
        C0177e c0177e = new C0177e();
        c0177e.add(viewGroup);
        while (!c0177e.isEmpty()) {
            View view = (View) c0177e.removeFirst();
            if (view.getId() == i2) {
                arrayList.add(view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup2 = (ViewGroup) view;
                int childCount = viewGroup2.getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = viewGroup2.getChildAt(i3);
                    n.f(childAt, "getChildAt(...)");
                    c0177e.add(childAt);
                }
            }
        }
        return arrayList;
    }

    public static final int toExposedUnit(long j2) {
        return b.a(j2 / 1000.0d);
    }
}
