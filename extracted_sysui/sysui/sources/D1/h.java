package D1;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import miuix.animation.internal.TransitionInfo;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final D1.a f96a = new D1.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l f97b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f98c;

    public h(l lVar) {
        if (lVar == null) {
            throw new NullPointerException("source == null");
        }
        this.f97b = lVar;
    }

    public long a(d dVar, long j2) {
        if (this.f98c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jL = this.f96a.l(dVar, j2);
            if (jL != -1) {
                return jL;
            }
            D1.a aVar = this.f96a;
            long j3 = aVar.f78b;
            if (this.f97b.g(aVar, 8192L) == -1) {
                return -1L;
            }
            j2 = Math.max(j2, (j3 - ((long) dVar.j())) + 1);
        }
    }

    @Override // D1.c
    public D1.a b() {
        return this.f96a;
    }

    public long c(d dVar, long j2) {
        if (this.f98c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jN = this.f96a.n(dVar, j2);
            if (jN != -1) {
                return jN;
            }
            D1.a aVar = this.f96a;
            long j3 = aVar.f78b;
            if (this.f97b.g(aVar, 8192L) == -1) {
                return -1L;
            }
            j2 = Math.max(j2, j3);
        }
    }

    @Override // D1.l, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() {
        if (this.f98c) {
            return;
        }
        this.f98c = true;
        this.f97b.close();
        this.f96a.a();
    }

    public void d(long j2) throws EOFException {
        if (!request(j2)) {
            throw new EOFException();
        }
    }

    @Override // D1.l
    public long g(D1.a aVar, long j2) {
        if (aVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        if (this.f98c) {
            throw new IllegalStateException("closed");
        }
        D1.a aVar2 = this.f96a;
        if (aVar2.f78b == 0 && this.f97b.g(aVar2, 8192L) == -1) {
            return -1L;
        }
        return this.f96a.g(aVar, Math.min(j2, this.f96a.f78b));
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f98c;
    }

    @Override // D1.c
    public int p(f fVar) throws EOFException {
        if (this.f98c) {
            throw new IllegalStateException("closed");
        }
        do {
            int iD = this.f96a.D(fVar, true);
            if (iD == -1) {
                return -1;
            }
            if (iD != -2) {
                this.f96a.F(fVar.f88a[iD].j());
                return iD;
            }
        } while (this.f97b.g(this.f96a, 8192L) != -1);
        return -1;
    }

    @Override // D1.c
    public c peek() {
        return e.a(new g(this));
    }

    @Override // D1.c
    public long q(d dVar) {
        return a(dVar, 0L);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        D1.a aVar = this.f96a;
        if (aVar.f78b == 0 && this.f97b.g(aVar, 8192L) == -1) {
            return -1;
        }
        return this.f96a.read(byteBuffer);
    }

    @Override // D1.c
    public byte readByte() throws EOFException {
        d(1L);
        return this.f96a.readByte();
    }

    @Override // D1.c
    public boolean request(long j2) {
        D1.a aVar;
        if (j2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        if (this.f98c) {
            throw new IllegalStateException("closed");
        }
        do {
            aVar = this.f96a;
            if (aVar.f78b >= j2) {
                return true;
            }
        } while (this.f97b.g(aVar, 8192L) != -1);
        return false;
    }

    public String toString() {
        return "buffer(" + this.f97b + ")";
    }

    @Override // D1.c
    public long v(d dVar) {
        return c(dVar, 0L);
    }

    @Override // D1.c
    public InputStream y() {
        return new a();
    }

    public class a extends InputStream {
        public a() {
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            h hVar = h.this;
            if (hVar.f98c) {
                throw new IOException("closed");
            }
            return (int) Math.min(hVar.f96a.f78b, 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            h.this.close();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            h hVar = h.this;
            if (hVar.f98c) {
                throw new IOException("closed");
            }
            D1.a aVar = hVar.f96a;
            if (aVar.f78b == 0 && hVar.f97b.g(aVar, 8192L) == -1) {
                return -1;
            }
            return h.this.f96a.readByte() & TransitionInfo.INIT;
        }

        public String toString() {
            return h.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) throws IOException {
            if (!h.this.f98c) {
                n.b(bArr.length, i2, i3);
                h hVar = h.this;
                D1.a aVar = hVar.f96a;
                if (aVar.f78b == 0 && hVar.f97b.g(aVar, 8192L) == -1) {
                    return -1;
                }
                return h.this.f96a.read(bArr, i2, i3);
            }
            throw new IOException("closed");
        }
    }
}
