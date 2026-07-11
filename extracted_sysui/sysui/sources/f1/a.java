package f1;

import c1.C0232d;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    public static final int a(int i2) {
        if (new C0232d(2, 36).h(i2)) {
            return i2;
        }
        throw new IllegalArgumentException("radix " + i2 + " was not in valid range " + new C0232d(2, 36));
    }

    public static final int b(char c2, int i2) {
        return Character.digit((int) c2, i2);
    }

    public static final boolean c(char c2) {
        return Character.isWhitespace(c2) || Character.isSpaceChar(c2);
    }
}
