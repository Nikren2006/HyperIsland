package miui.systemui.notification.focus;

import H0.s;
import android.util.Log;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;
import miui.systemui.util.AlarmScheduler;

/* JADX INFO: loaded from: classes4.dex */
public final class HideDeletedFocusController$alarmScheduler$2 extends o implements Function0 {
    final /* synthetic */ AlarmScheduler.Factory $alarmSchedulerFactory;
    final /* synthetic */ HideDeletedFocusController this$0;

    /* JADX INFO: renamed from: miui.systemui.notification.focus.HideDeletedFocusController$alarmScheduler$2$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        final /* synthetic */ HideDeletedFocusController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(HideDeletedFocusController hideDeletedFocusController) {
            super(1);
            this.this$0 = hideDeletedFocusController;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((String) obj);
            return s.f314a;
        }

        public final void invoke(String str) {
            Log.d(HideDeletedFocusController.TAG, "onReceive, key=" + str);
            this.this$0.getMDeletedNotifs().remove(str);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HideDeletedFocusController$alarmScheduler$2(AlarmScheduler.Factory factory, HideDeletedFocusController hideDeletedFocusController) {
        super(0);
        this.$alarmSchedulerFactory = factory;
        this.this$0 = hideDeletedFocusController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final AlarmScheduler invoke() {
        return this.$alarmSchedulerFactory.create(HideDeletedFocusController.ACTION_NOTIFICATION_TIMEOUT, new AnonymousClass1(this.this$0));
    }
}
