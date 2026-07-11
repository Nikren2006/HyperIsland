package androidx.window.layout;

import androidx.window.sidecar.SidecarDisplayFeature;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class SidecarAdapter$translate$checkedFeature$2 extends o implements Function1 {
    public static final SidecarAdapter$translate$checkedFeature$2 INSTANCE = new SidecarAdapter$translate$checkedFeature$2();

    public SidecarAdapter$translate$checkedFeature$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(SidecarDisplayFeature require) {
        n.g(require, "$this$require");
        return Boolean.valueOf((require.getRect().width() == 0 && require.getRect().height() == 0) ? false : true);
    }
}
