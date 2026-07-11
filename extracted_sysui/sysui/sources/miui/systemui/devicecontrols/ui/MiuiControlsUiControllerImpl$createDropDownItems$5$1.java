package miui.systemui.devicecontrols.ui;

import android.text.TextUtils;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes3.dex */
public final class MiuiControlsUiControllerImpl$createDropDownItems$5$1 extends kotlin.jvm.internal.o implements Function1 {
    public static final MiuiControlsUiControllerImpl$createDropDownItems$5$1 INSTANCE = new MiuiControlsUiControllerImpl$createDropDownItems$5$1();

    public MiuiControlsUiControllerImpl$createDropDownItems$5$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(SelectionItem it) {
        kotlin.jvm.internal.n.g(it, "it");
        return Boolean.valueOf(TextUtils.equals("com.xiaomi.smarthome", it.getComponentName().getPackageName()));
    }
}
