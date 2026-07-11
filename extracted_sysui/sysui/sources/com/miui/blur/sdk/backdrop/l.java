package com.miui.blur.sdk.backdrop;

/* JADX INFO: loaded from: classes2.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f2525a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2526b;

    public l(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f2525a = new Object[i2];
    }

    public Object a() {
        int i2 = this.f2526b;
        if (i2 <= 0) {
            return null;
        }
        int i3 = i2 - 1;
        Object[] objArr = this.f2525a;
        Object obj = objArr[i3];
        objArr[i3] = null;
        this.f2526b = i2 - 1;
        return obj;
    }

    public final boolean b(Object obj) {
        for (int i2 = 0; i2 < this.f2526b; i2++) {
            if (this.f2525a[i2] == obj) {
                return true;
            }
        }
        return false;
    }

    public boolean c(Object obj) {
        if (b(obj)) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i2 = this.f2526b;
        Object[] objArr = this.f2525a;
        if (i2 >= objArr.length) {
            return false;
        }
        objArr[i2] = obj;
        this.f2526b = i2 + 1;
        return true;
    }
}
