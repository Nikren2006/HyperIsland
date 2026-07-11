package miui.systemui.util;

import android.content.Context;
import android.os.Handler;

/* JADX INFO: loaded from: classes4.dex */
public final class TalkBackUtils_Factory implements F0.e {
    private final G0.a contextProvider;
    private final G0.a mainHandlerProvider;

    public TalkBackUtils_Factory(G0.a aVar, G0.a aVar2) {
        this.contextProvider = aVar;
        this.mainHandlerProvider = aVar2;
    }

    public static TalkBackUtils_Factory create(G0.a aVar, G0.a aVar2) {
        return new TalkBackUtils_Factory(aVar, aVar2);
    }

    public static TalkBackUtils newInstance(Context context, Handler handler) {
        return new TalkBackUtils(context, handler);
    }

    @Override // G0.a
    public TalkBackUtils get() {
        return newInstance((Context) this.contextProvider.get(), (Handler) this.mainHandlerProvider.get());
    }
}
