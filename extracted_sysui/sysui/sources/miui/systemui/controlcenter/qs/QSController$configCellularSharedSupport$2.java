package miui.systemui.controlcenter.qs;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class QSController$configCellularSharedSupport$2 extends o implements Function0 {
    final /* synthetic */ QSController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSController$configCellularSharedSupport$2(QSController qSController) {
        super(0);
        this.this$0 = qSController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Boolean invoke() {
        boolean z2;
        try {
            z2 = this.this$0.getContext().getResources().getBoolean(this.this$0.getContext().getResources().getIdentifier("config_celluar_shared_support", "bool", "android.miui"));
        } catch (Throwable unused) {
            z2 = false;
        }
        return Boolean.valueOf(z2);
    }
}
