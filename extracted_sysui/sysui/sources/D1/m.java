package D1;

import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes5.dex */
public class m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final m f111c = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f112a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f113b;

    public class a extends m {
        @Override // D1.m
        public void a() {
        }
    }

    public void a() throws InterruptedIOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.f112a && this.f113b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
