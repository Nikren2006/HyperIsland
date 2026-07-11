package T;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends Future {
    void addListener(Runnable runnable, Executor executor);
}
