package miui.systemui.util;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class HapticFeedbackImpl_Factory implements F0.e {
    private final G0.a contextProvider;
    private final G0.a uiBgExecutorProvider;

    public HapticFeedbackImpl_Factory(G0.a aVar, G0.a aVar2) {
        this.contextProvider = aVar;
        this.uiBgExecutorProvider = aVar2;
    }

    public static HapticFeedbackImpl_Factory create(G0.a aVar, G0.a aVar2) {
        return new HapticFeedbackImpl_Factory(aVar, aVar2);
    }

    public static HapticFeedbackImpl newInstance(Context context, Executor executor) {
        return new HapticFeedbackImpl(context, executor);
    }

    @Override // G0.a
    public HapticFeedbackImpl get() {
        return newInstance((Context) this.contextProvider.get(), (Executor) this.uiBgExecutorProvider.get());
    }
}
