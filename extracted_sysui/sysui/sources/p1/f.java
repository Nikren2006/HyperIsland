package p1;

import L0.g;
import java.util.concurrent.atomic.AtomicReferenceArray;
import l1.C;

/* JADX INFO: loaded from: classes2.dex */
public final class f extends C {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AtomicReferenceArray f6412e;

    public f(long j2, f fVar, int i2) {
        super(j2, fVar, i2);
        this.f6412e = new AtomicReferenceArray(e.f6411f);
    }

    @Override // l1.C
    public int n() {
        return e.f6411f;
    }

    @Override // l1.C
    public void o(int i2, Throwable th, g gVar) {
        r().set(i2, e.f6410e);
        p();
    }

    public final AtomicReferenceArray r() {
        return this.f6412e;
    }

    public String toString() {
        return "SemaphoreSegment[id=" + this.f5193c + ", hashCode=" + hashCode() + ']';
    }
}
