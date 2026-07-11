package p;

/* JADX INFO: renamed from: p.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0726f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f6337a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6338b;

    public void a(float f2) {
        float f3 = this.f6337a + f2;
        this.f6337a = f3;
        int i2 = this.f6338b + 1;
        this.f6338b = i2;
        if (i2 == Integer.MAX_VALUE) {
            this.f6337a = f3 / 2.0f;
            this.f6338b = i2 / 2;
        }
    }
}
