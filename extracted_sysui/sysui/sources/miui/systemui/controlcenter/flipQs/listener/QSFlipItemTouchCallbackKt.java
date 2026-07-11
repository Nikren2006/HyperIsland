package miui.systemui.controlcenter.flipQs.listener;

import java.util.List;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class QSFlipItemTouchCallbackKt {
    public static final <T> void swap(List<T> list, int i2, int i3) {
        n.g(list, "<this>");
        T t2 = list.get(i2);
        list.set(i2, list.get(i3));
        list.set(i3, t2);
    }
}
