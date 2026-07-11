package w1;

/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f6987a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final char[] f6988b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final byte[] f6989c;

    static {
        c cVar = new c();
        f6987a = cVar;
        f6988b = new char[117];
        f6989c = new byte[126];
        cVar.f();
        cVar.e();
    }

    public final void a(char c2, char c3) {
        b(c2, c3);
    }

    public final void b(int i2, char c2) {
        if (c2 != 'u') {
            f6988b[c2] = (char) i2;
        }
    }

    public final void c(char c2, byte b2) {
        d(c2, b2);
    }

    public final void d(int i2, byte b2) {
        f6989c[i2] = b2;
    }

    public final void e() {
        for (int i2 = 0; i2 < 33; i2++) {
            d(i2, (byte) 127);
        }
        d(9, (byte) 3);
        d(10, (byte) 3);
        d(13, (byte) 3);
        d(32, (byte) 3);
        c(',', (byte) 4);
        c(':', (byte) 5);
        c('{', (byte) 6);
        c('}', (byte) 7);
        c('[', (byte) 8);
        c(']', (byte) 9);
        c('\"', (byte) 1);
        c('\\', (byte) 2);
    }

    public final void f() {
        for (int i2 = 0; i2 < 32; i2++) {
            b(i2, 'u');
        }
        b(8, 'b');
        b(9, 't');
        b(10, 'n');
        b(12, 'f');
        b(13, 'r');
        a('/', '/');
        a('\"', '\"');
        a('\\', '\\');
    }
}
