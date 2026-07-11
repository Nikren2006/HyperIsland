package k;

import j.C0411d;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f4855a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j.h f4856b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0411d f4857c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f4858d;

    public enum a {
        MASK_MODE_ADD,
        MASK_MODE_SUBTRACT,
        MASK_MODE_INTERSECT,
        MASK_MODE_NONE
    }

    public h(a aVar, j.h hVar, C0411d c0411d, boolean z2) {
        this.f4855a = aVar;
        this.f4856b = hVar;
        this.f4857c = c0411d;
        this.f4858d = z2;
    }

    public a a() {
        return this.f4855a;
    }

    public j.h b() {
        return this.f4856b;
    }

    public C0411d c() {
        return this.f4857c;
    }

    public boolean d() {
        return this.f4858d;
    }
}
