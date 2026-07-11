package i1;

import g1.AbstractC0358a0;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class k {
    public static final void a(s sVar, Throwable th) {
        if (th != null) {
            cancellationExceptionA = th instanceof CancellationException ? (CancellationException) th : null;
            if (cancellationExceptionA == null) {
                cancellationExceptionA = AbstractC0358a0.a("Channel was consumed, consumer had failed", th);
            }
        }
        sVar.a(cancellationExceptionA);
    }
}
