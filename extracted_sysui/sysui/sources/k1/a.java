package k1;

import j1.InterfaceC0419g;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends CancellationException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final transient InterfaceC0419g f4948a;

    public a(InterfaceC0419g interfaceC0419g) {
        super("Flow was aborted, no more elements needed");
        this.f4948a = interfaceC0419g;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
