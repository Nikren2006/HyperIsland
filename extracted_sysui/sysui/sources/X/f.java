package X;

import c0.C0228c;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class f extends C0228c {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Writer f848p = new a();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final U.k f849q = new U.k("closed");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final List f850m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f851n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public U.f f852o;

    public class a extends Writer {
        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            throw new AssertionError();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i2, int i3) {
            throw new AssertionError();
        }
    }

    public f() {
        super(f848p);
        this.f850m = new ArrayList();
        this.f852o = U.h.f743a;
    }

    @Override // c0.C0228c
    public C0228c A() {
        T(U.h.f743a);
        return this;
    }

    @Override // c0.C0228c
    public C0228c K(double d2) {
        if (u() || !(Double.isNaN(d2) || Double.isInfinite(d2))) {
            T(new U.k(Double.valueOf(d2)));
            return this;
        }
        throw new IllegalArgumentException("JSON forbids NaN and infinities: " + d2);
    }

    @Override // c0.C0228c
    public C0228c L(long j2) {
        T(new U.k(Long.valueOf(j2)));
        return this;
    }

    @Override // c0.C0228c
    public C0228c M(Boolean bool) {
        if (bool == null) {
            return A();
        }
        T(new U.k(bool));
        return this;
    }

    @Override // c0.C0228c
    public C0228c N(Number number) {
        if (number == null) {
            return A();
        }
        if (!u()) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        T(new U.k(number));
        return this;
    }

    @Override // c0.C0228c
    public C0228c O(String str) {
        if (str == null) {
            return A();
        }
        T(new U.k(str));
        return this;
    }

    @Override // c0.C0228c
    public C0228c P(boolean z2) {
        T(new U.k(Boolean.valueOf(z2)));
        return this;
    }

    public U.f R() {
        if (this.f850m.isEmpty()) {
            return this.f852o;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.f850m);
    }

    public final U.f S() {
        return (U.f) this.f850m.get(r1.size() - 1);
    }

    public final void T(U.f fVar) {
        if (this.f851n != null) {
            if (!fVar.e() || r()) {
                ((U.i) S()).h(this.f851n, fVar);
            }
            this.f851n = null;
            return;
        }
        if (this.f850m.isEmpty()) {
            this.f852o = fVar;
            return;
        }
        U.f fVarS = S();
        if (!(fVarS instanceof U.e)) {
            throw new IllegalStateException();
        }
        ((U.e) fVarS).h(fVar);
    }

    @Override // c0.C0228c, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.f850m.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.f850m.add(f849q);
    }

    @Override // c0.C0228c
    public C0228c d() {
        U.e eVar = new U.e();
        T(eVar);
        this.f850m.add(eVar);
        return this;
    }

    @Override // c0.C0228c
    public C0228c e() {
        U.i iVar = new U.i();
        T(iVar);
        this.f850m.add(iVar);
        return this;
    }

    @Override // c0.C0228c, java.io.Flushable
    public void flush() {
    }

    @Override // c0.C0228c
    public C0228c l() {
        if (this.f850m.isEmpty() || this.f851n != null) {
            throw new IllegalStateException();
        }
        if (!(S() instanceof U.e)) {
            throw new IllegalStateException();
        }
        this.f850m.remove(r0.size() - 1);
        return this;
    }

    @Override // c0.C0228c
    public C0228c n() {
        if (this.f850m.isEmpty() || this.f851n != null) {
            throw new IllegalStateException();
        }
        if (!(S() instanceof U.i)) {
            throw new IllegalStateException();
        }
        this.f850m.remove(r0.size() - 1);
        return this;
    }

    @Override // c0.C0228c
    public C0228c x(String str) {
        Objects.requireNonNull(str, "name == null");
        if (this.f850m.isEmpty() || this.f851n != null) {
            throw new IllegalStateException();
        }
        if (!(S() instanceof U.i)) {
            throw new IllegalStateException();
        }
        this.f851n = str;
        return this;
    }
}
