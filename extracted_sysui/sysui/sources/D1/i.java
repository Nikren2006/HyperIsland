package D1;

/* JADX INFO: loaded from: classes5.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f100a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f101b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f102c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f103d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f104e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public i f105f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public i f106g;

    public i() {
        this.f100a = new byte[8192];
        this.f104e = true;
        this.f103d = false;
    }

    public final void a() {
        i iVar = this.f106g;
        if (iVar == this) {
            throw new IllegalStateException();
        }
        if (iVar.f104e) {
            int i2 = this.f102c - this.f101b;
            if (i2 > (8192 - iVar.f102c) + (iVar.f103d ? 0 : iVar.f101b)) {
                return;
            }
            f(iVar, i2);
            b();
            j.a(this);
        }
    }

    public final i b() {
        i iVar = this.f105f;
        i iVar2 = iVar != this ? iVar : null;
        i iVar3 = this.f106g;
        iVar3.f105f = iVar;
        this.f105f.f106g = iVar3;
        this.f105f = null;
        this.f106g = null;
        return iVar2;
    }

    public final i c(i iVar) {
        iVar.f106g = this;
        iVar.f105f = this.f105f;
        this.f105f.f106g = iVar;
        this.f105f = iVar;
        return iVar;
    }

    public final i d() {
        this.f103d = true;
        return new i(this.f100a, this.f101b, this.f102c, true, false);
    }

    public final i e(int i2) {
        i iVarB;
        if (i2 <= 0 || i2 > this.f102c - this.f101b) {
            throw new IllegalArgumentException();
        }
        if (i2 >= 1024) {
            iVarB = d();
        } else {
            iVarB = j.b();
            System.arraycopy(this.f100a, this.f101b, iVarB.f100a, 0, i2);
        }
        iVarB.f102c = iVarB.f101b + i2;
        this.f101b += i2;
        this.f106g.c(iVarB);
        return iVarB;
    }

    public final void f(i iVar, int i2) {
        if (!iVar.f104e) {
            throw new IllegalArgumentException();
        }
        int i3 = iVar.f102c;
        if (i3 + i2 > 8192) {
            if (iVar.f103d) {
                throw new IllegalArgumentException();
            }
            int i4 = iVar.f101b;
            if ((i3 + i2) - i4 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = iVar.f100a;
            System.arraycopy(bArr, i4, bArr, 0, i3 - i4);
            iVar.f102c -= iVar.f101b;
            iVar.f101b = 0;
        }
        System.arraycopy(this.f100a, this.f101b, iVar.f100a, iVar.f102c, i2);
        iVar.f102c += i2;
        this.f101b += i2;
    }

    public i(byte[] bArr, int i2, int i3, boolean z2, boolean z3) {
        this.f100a = bArr;
        this.f101b = i2;
        this.f102c = i3;
        this.f103d = z2;
        this.f104e = z3;
    }
}
