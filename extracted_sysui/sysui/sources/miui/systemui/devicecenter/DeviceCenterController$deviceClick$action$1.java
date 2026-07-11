package miui.systemui.devicecenter;

import H0.s;
import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.devicecenter.devices.DeviceInfoWrapper;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceCenterController$deviceClick$action$1 extends o implements Function0 {
    final /* synthetic */ int $darkRes;
    final /* synthetic */ View $view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceCenterController$deviceClick$action$1(View view, int i2) {
        super(0);
        this.$view = view;
        this.$darkRes = i2;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m108invoke();
        return s.f314a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m108invoke() {
        if (this.$view.getTag() instanceof DeviceInfoWrapper) {
            Object tag = this.$view.getTag();
            n.e(tag, "null cannot be cast to non-null type miui.systemui.devicecenter.devices.DeviceInfoWrapper");
            if (!((DeviceInfoWrapper) tag).getOpen()) {
                CommonUtils.setBackgroundResourceEx$default(CommonUtils.INSTANCE, this.$view, this.$darkRes, false, 2, null);
            }
        }
        DeviceCenterController.Companion.removeAction(this.$view);
    }
}
