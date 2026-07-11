package i;

import androidx.collection.LruCache;
import d.C0307h;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final g f4527b = new g();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LruCache f4528a = new LruCache(20);

    public static g b() {
        return f4527b;
    }

    public C0307h a(String str) {
        if (str == null) {
            return null;
        }
        return (C0307h) this.f4528a.get(str);
    }

    public void c(String str, C0307h c0307h) {
        if (str == null) {
            return;
        }
        this.f4528a.put(str, c0307h);
    }
}
