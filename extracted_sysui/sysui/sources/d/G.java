package d;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
public class G {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3766a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3767b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f3768c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f3769d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f3770e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Bitmap f3771f;

    public G(int i2, int i3, String str, String str2, String str3) {
        this.f3766a = i2;
        this.f3767b = i3;
        this.f3768c = str;
        this.f3769d = str2;
        this.f3770e = str3;
    }

    public Bitmap a() {
        return this.f3771f;
    }

    public String b() {
        return this.f3769d;
    }

    public int c() {
        return this.f3767b;
    }

    public String d() {
        return this.f3768c;
    }

    public int e() {
        return this.f3766a;
    }

    public void f(Bitmap bitmap) {
        this.f3771f = bitmap;
    }
}
