package l1;

/* JADX INFO: renamed from: l1.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0454i extends RuntimeException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final transient L0.g f5220a;

    public C0454i(L0.g gVar) {
        this.f5220a = gVar;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    public String getLocalizedMessage() {
        return this.f5220a.toString();
    }
}
