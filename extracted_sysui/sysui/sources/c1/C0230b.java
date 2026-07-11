package c1;

import I0.A;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: renamed from: c1.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0230b implements Iterable, W0.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f1351d = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1352a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1353b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1354c;

    /* JADX INFO: renamed from: c1.b$a */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final C0230b a(int i2, int i3, int i4) {
            return new C0230b(i2, i3, i4);
        }

        public a() {
        }
    }

    public C0230b(int i2, int i3, int i4) {
        if (i4 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i4 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f1352a = i2;
        this.f1353b = P0.c.b(i2, i3, i4);
        this.f1354c = i4;
    }

    public final int c() {
        return this.f1352a;
    }

    public final int d() {
        return this.f1353b;
    }

    public final int e() {
        return this.f1354c;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0230b) {
            if (!isEmpty() || !((C0230b) obj).isEmpty()) {
                C0230b c0230b = (C0230b) obj;
                if (this.f1352a != c0230b.f1352a || this.f1353b != c0230b.f1353b || this.f1354c != c0230b.f1354c) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public A iterator() {
        return new C0231c(this.f1352a, this.f1353b, this.f1354c);
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return this.f1354c + (((this.f1352a * 31) + this.f1353b) * 31);
    }

    public boolean isEmpty() {
        if (this.f1354c > 0) {
            if (this.f1352a <= this.f1353b) {
                return false;
            }
        } else if (this.f1352a >= this.f1353b) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb;
        int i2;
        if (this.f1354c > 0) {
            sb = new StringBuilder();
            sb.append(this.f1352a);
            sb.append("..");
            sb.append(this.f1353b);
            sb.append(" step ");
            i2 = this.f1354c;
        } else {
            sb = new StringBuilder();
            sb.append(this.f1352a);
            sb.append(" downTo ");
            sb.append(this.f1353b);
            sb.append(" step ");
            i2 = -this.f1354c;
        }
        sb.append(i2);
        return sb.toString();
    }
}
