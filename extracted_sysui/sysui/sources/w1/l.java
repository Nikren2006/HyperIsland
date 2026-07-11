package w1;

import java.util.Arrays;
import s1.f;

/* JADX INFO: loaded from: classes2.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object[] f6994a = new Object[8];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f6995b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f6996c;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f6997a = new a();
    }

    public l() {
        int[] iArr = new int[8];
        for (int i2 = 0; i2 < 8; i2++) {
            iArr[i2] = -1;
        }
        this.f6995b = iArr;
        this.f6996c = -1;
    }

    public final String a() {
        StringBuilder sb = new StringBuilder();
        sb.append("$");
        int i2 = this.f6996c + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            Object obj = this.f6994a[i3];
            if (obj instanceof s1.c) {
                s1.c cVar = (s1.c) obj;
                if (!kotlin.jvm.internal.n.c(cVar.c(), f.b.f6481a)) {
                    int i4 = this.f6995b[i3];
                    if (i4 >= 0) {
                        sb.append(".");
                        sb.append(cVar.e(i4));
                    }
                } else if (this.f6995b[i3] != -1) {
                    sb.append("[");
                    sb.append(this.f6995b[i3]);
                    sb.append("]");
                }
            } else if (obj != a.f6997a) {
                sb.append("[");
                sb.append("'");
                sb.append(obj);
                sb.append("'");
                sb.append("]");
            }
        }
        String string = sb.toString();
        kotlin.jvm.internal.n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final void b() {
        int i2 = this.f6996c;
        int[] iArr = this.f6995b;
        if (iArr[i2] == -2) {
            iArr[i2] = -1;
            this.f6996c = i2 - 1;
        }
        int i3 = this.f6996c;
        if (i3 != -1) {
            this.f6996c = i3 - 1;
        }
    }

    public final void c(s1.c sd) {
        kotlin.jvm.internal.n.g(sd, "sd");
        int i2 = this.f6996c + 1;
        this.f6996c = i2;
        if (i2 == this.f6994a.length) {
            e();
        }
        this.f6994a[i2] = sd;
    }

    public final void d() {
        int[] iArr = this.f6995b;
        int i2 = this.f6996c;
        if (iArr[i2] == -2) {
            this.f6994a[i2] = a.f6997a;
        }
    }

    public final void e() {
        int i2 = this.f6996c * 2;
        Object[] objArrCopyOf = Arrays.copyOf(this.f6994a, i2);
        kotlin.jvm.internal.n.f(objArrCopyOf, "copyOf(this, newSize)");
        this.f6994a = objArrCopyOf;
        int[] iArrCopyOf = Arrays.copyOf(this.f6995b, i2);
        kotlin.jvm.internal.n.f(iArrCopyOf, "copyOf(this, newSize)");
        this.f6995b = iArrCopyOf;
    }

    public final void f(Object obj) {
        int[] iArr = this.f6995b;
        int i2 = this.f6996c;
        if (iArr[i2] != -2) {
            int i3 = i2 + 1;
            this.f6996c = i3;
            if (i3 == this.f6994a.length) {
                e();
            }
        }
        Object[] objArr = this.f6994a;
        int i4 = this.f6996c;
        objArr[i4] = obj;
        this.f6995b[i4] = -2;
    }

    public final void g(int i2) {
        this.f6995b[this.f6996c] = i2;
    }

    public String toString() {
        return a();
    }
}
