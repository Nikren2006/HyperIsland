package miui.systemui.util;

import android.content.Context;
import android.os.Handler;
import kotlin.jvm.functions.Function1;

/* JADX INFO: renamed from: miui.systemui.util.AlarmScheduler_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes4.dex */
public final class C0695AlarmScheduler_Factory {
    private final G0.a contextProvider;
    private final G0.a handlerProvider;

    public C0695AlarmScheduler_Factory(G0.a aVar, G0.a aVar2) {
        this.contextProvider = aVar;
        this.handlerProvider = aVar2;
    }

    public static C0695AlarmScheduler_Factory create(G0.a aVar, G0.a aVar2) {
        return new C0695AlarmScheduler_Factory(aVar, aVar2);
    }

    public static AlarmScheduler newInstance(Context context, String str, Handler handler, Function1 function1) {
        return new AlarmScheduler(context, str, handler, function1);
    }

    public AlarmScheduler get(String str, Function1 function1) {
        return newInstance((Context) this.contextProvider.get(), str, (Handler) this.handlerProvider.get(), function1);
    }
}
