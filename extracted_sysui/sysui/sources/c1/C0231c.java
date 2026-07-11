package c1;

import I0.A;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: c1.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0231c extends A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1355a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1356b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1357c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1358d;

    public C0231c(int i2, int i3, int i4) {
        this.f1355a = i4;
        this.f1356b = i3;
        boolean z2 = false;
        if (i4 <= 0 ? i2 >= i3 : i2 <= i3) {
            z2 = true;
        }
        this.f1357c = z2;
        this.f1358d = z2 ? i2 : i3;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f1357c;
    }

    @Override // I0.A
    public int nextInt() {
        int i2 = this.f1358d;
        if (i2 != this.f1356b) {
            this.f1358d = this.f1355a + i2;
        } else {
            if (!this.f1357c) {
                throw new NoSuchElementException();
            }
            this.f1357c = false;
        }
        return i2;
    }
}
