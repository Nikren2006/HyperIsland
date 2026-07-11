package a1;

import java.util.Random;

/* JADX INFO: renamed from: a1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0197a extends c {
    @Override // a1.c
    public int b() {
        return d().nextInt();
    }

    @Override // a1.c
    public int c(int i2) {
        return d().nextInt(i2);
    }

    public abstract Random d();
}
