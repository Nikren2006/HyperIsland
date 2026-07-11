package miui.systemui.util;

import kotlin.jvm.functions.Function1;
import miui.systemui.util.AlarmScheduler;

/* JADX INFO: loaded from: classes4.dex */
public final class AlarmScheduler_Factory_Impl implements AlarmScheduler.Factory {
    private final C0695AlarmScheduler_Factory delegateFactory;

    public AlarmScheduler_Factory_Impl(C0695AlarmScheduler_Factory c0695AlarmScheduler_Factory) {
        this.delegateFactory = c0695AlarmScheduler_Factory;
    }

    @Override // miui.systemui.util.AlarmScheduler.Factory
    public AlarmScheduler create(String str, Function1 function1) {
        return this.delegateFactory.get(str, function1);
    }

    public static G0.a create(C0695AlarmScheduler_Factory c0695AlarmScheduler_Factory) {
        return F0.f.a(new AlarmScheduler_Factory_Impl(c0695AlarmScheduler_Factory));
    }
}
