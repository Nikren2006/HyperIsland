package L;

import android.graphics.Typeface;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Typeface f367a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0013a f368b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f369c;

    /* JADX INFO: renamed from: L.a$a, reason: collision with other inner class name */
    public interface InterfaceC0013a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0013a interfaceC0013a, Typeface typeface) {
        this.f367a = typeface;
        this.f368b = interfaceC0013a;
    }

    @Override // L.f
    public void a(int i2) {
        d(this.f367a);
    }

    @Override // L.f
    public void b(Typeface typeface, boolean z2) {
        d(typeface);
    }

    public void c() {
        this.f369c = true;
    }

    public final void d(Typeface typeface) {
        if (this.f369c) {
            return;
        }
        this.f368b.a(typeface);
    }
}
