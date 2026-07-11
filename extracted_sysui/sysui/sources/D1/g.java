package D1;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f90a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f91b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public i f92c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f93d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f94e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f95f;

    public g(c cVar) {
        this.f90a = cVar;
        a aVarB = cVar.b();
        this.f91b = aVarB;
        i iVar = aVarB.f77a;
        this.f92c = iVar;
        this.f93d = iVar != null ? iVar.f101b : -1;
    }

    @Override // D1.l, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() {
        this.f94e = true;
    }

    @Override // D1.l
    public long g(a aVar, long j2) {
        i iVar;
        i iVar2;
        if (j2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        if (this.f94e) {
            throw new IllegalStateException("closed");
        }
        i iVar3 = this.f92c;
        if (iVar3 != null && (iVar3 != (iVar2 = this.f91b.f77a) || this.f93d != iVar2.f101b)) {
            throw new IllegalStateException("Peek source is invalid because upstream source was used");
        }
        if (j2 == 0) {
            return 0L;
        }
        if (!this.f90a.request(this.f95f + 1)) {
            return -1L;
        }
        if (this.f92c == null && (iVar = this.f91b.f77a) != null) {
            this.f92c = iVar;
            this.f93d = iVar.f101b;
        }
        long jMin = Math.min(j2, this.f91b.f78b - this.f95f);
        this.f91b.d(aVar, this.f95f, jMin);
        this.f95f += jMin;
        return jMin;
    }
}
