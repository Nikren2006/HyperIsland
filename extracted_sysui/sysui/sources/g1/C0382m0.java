package g1;

import java.util.concurrent.CancellationException;

/* JADX INFO: renamed from: g1.m0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0382m0 extends CancellationException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final transient InterfaceC0380l0 f4433a;

    public C0382m0(String str, Throwable th, InterfaceC0380l0 interfaceC0380l0) {
        super(str);
        this.f4433a = interfaceC0380l0;
        if (th != null) {
            initCause(th);
        }
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof C0382m0) {
                C0382m0 c0382m0 = (C0382m0) obj;
                if (!kotlin.jvm.internal.n.c(c0382m0.getMessage(), getMessage()) || !kotlin.jvm.internal.n.c(c0382m0.f4433a, this.f4433a) || !kotlin.jvm.internal.n.c(c0382m0.getCause(), getCause())) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public int hashCode() {
        String message = getMessage();
        kotlin.jvm.internal.n.d(message);
        int iHashCode = ((message.hashCode() * 31) + this.f4433a.hashCode()) * 31;
        Throwable cause = getCause();
        return iHashCode + (cause != null ? cause.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.toString() + "; job=" + this.f4433a;
    }
}
