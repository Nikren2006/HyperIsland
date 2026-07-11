package miui.systemui.devicecontrols.ui;

import kotlin.jvm.functions.Function1;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class RenderInfoKt$deviceIconMap$1 extends kotlin.jvm.internal.o implements Function1 {
    public static final RenderInfoKt$deviceIconMap$1 INSTANCE = new RenderInfoKt$deviceIconMap$1();

    public RenderInfoKt$deviceIconMap$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final Integer invoke(int i2) {
        return Integer.valueOf(R.drawable.ic_device_unknown);
    }
}
