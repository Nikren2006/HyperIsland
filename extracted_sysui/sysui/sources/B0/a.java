package B0;

import B0.c;
import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f35a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.a f36b;

    public a(Context context, c.a aVar) {
        c cVarA = new C0.a().a(context);
        this.f35a = cVarA;
        this.f36b = aVar;
        cVarA.a(aVar);
    }

    @Override // B0.c
    public void a(c.a aVar) {
        this.f35a.a(aVar);
    }

    @Override // B0.c
    public boolean b() {
        return this.f35a.b();
    }

    @Override // B0.c
    public void c(c.a aVar) {
        this.f35a.c(aVar);
    }

    @Override // B0.c
    public boolean d(String str) {
        return b() && this.f35a.d(str);
    }

    public void e() {
        this.f35a.c(this.f36b);
    }
}
