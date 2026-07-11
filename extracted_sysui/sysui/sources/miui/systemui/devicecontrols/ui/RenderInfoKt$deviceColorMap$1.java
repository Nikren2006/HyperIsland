package miui.systemui.devicecontrols.ui;

import kotlin.jvm.functions.Function1;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class RenderInfoKt$deviceColorMap$1 extends kotlin.jvm.internal.o implements Function1 {
    public static final RenderInfoKt$deviceColorMap$1 INSTANCE = new RenderInfoKt$deviceColorMap$1();

    public RenderInfoKt$deviceColorMap$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final H0.i invoke(int i2) {
        return new H0.i(Integer.valueOf(R.color.control_foreground), Integer.valueOf(R.color.control_enabled_default_background));
    }
}
