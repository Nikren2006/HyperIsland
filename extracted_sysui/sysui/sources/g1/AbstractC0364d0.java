package g1;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: renamed from: g1.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0364d0 {
    public static final B a(Executor executor) {
        return new C0362c0(executor);
    }

    public static final AbstractC0360b0 b(ExecutorService executorService) {
        return new C0362c0(executorService);
    }
}
