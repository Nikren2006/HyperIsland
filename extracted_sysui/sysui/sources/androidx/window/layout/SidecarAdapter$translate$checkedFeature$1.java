package androidx.window.layout;

import androidx.window.sidecar.SidecarDisplayFeature;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class SidecarAdapter$translate$checkedFeature$1 extends o implements Function1 {
    public static final SidecarAdapter$translate$checkedFeature$1 INSTANCE = new SidecarAdapter$translate$checkedFeature$1();

    public SidecarAdapter$translate$checkedFeature$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(SidecarDisplayFeature require) {
        n.g(require, "$this$require");
        boolean z2 = true;
        if (require.getType() != 1 && require.getType() != 2) {
            z2 = false;
        }
        return Boolean.valueOf(z2);
    }
}
