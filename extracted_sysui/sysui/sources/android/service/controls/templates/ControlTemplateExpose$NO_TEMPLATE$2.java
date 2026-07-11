package android.service.controls.templates;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class ControlTemplateExpose$NO_TEMPLATE$2 extends o implements Function0 {
    public static final ControlTemplateExpose$NO_TEMPLATE$2 INSTANCE = new ControlTemplateExpose$NO_TEMPLATE$2();

    public ControlTemplateExpose$NO_TEMPLATE$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final ControlTemplate invoke() throws IllegalAccessException {
        Object obj = ControlTemplate.class.getDeclaredField("NO_TEMPLATE").get(null);
        n.e(obj, "null cannot be cast to non-null type android.service.controls.templates.ControlTemplate");
        return (ControlTemplate) obj;
    }
}
