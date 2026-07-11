package miui.systemui.devicecontrols.management;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes3.dex */
public /* synthetic */ class ControlInfoWrapper$customIconGetter$1 extends kotlin.jvm.internal.l implements Function2 {
    public static final ControlInfoWrapper$customIconGetter$1 INSTANCE = new ControlInfoWrapper$customIconGetter$1();

    public ControlInfoWrapper$customIconGetter$1() {
        super(2, ControlsModelKt.class, "nullIconGetter", "nullIconGetter(Landroid/content/ComponentName;Ljava/lang/String;)Landroid/graphics/drawable/Icon;", 1);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Icon invoke(ComponentName p02, String p12) {
        kotlin.jvm.internal.n.g(p02, "p0");
        kotlin.jvm.internal.n.g(p12, "p1");
        return ControlsModelKt.nullIconGetter(p02, p12);
    }
}
