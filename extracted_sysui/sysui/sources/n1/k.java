package n1;

import g1.I;

/* JADX INFO: loaded from: classes2.dex */
public final class k extends h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Runnable f6275c;

    public k(Runnable runnable, long j2, i iVar) {
        super(j2, iVar);
        this.f6275c = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f6275c.run();
        } finally {
            this.f6273b.a();
        }
    }

    public String toString() {
        return "Task[" + I.a(this.f6275c) + '@' + I.b(this.f6275c) + ", " + this.f6272a + ", " + this.f6273b + ']';
    }
}
