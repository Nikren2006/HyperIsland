package g;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f4285a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f4286b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f4287c;

    public h(List list) {
        this.f4287c = list;
        this.f4285a = new ArrayList(list.size());
        this.f4286b = new ArrayList(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f4285a.add(((k.h) list.get(i2)).b().a());
            this.f4286b.add(((k.h) list.get(i2)).c().a());
        }
    }

    public List a() {
        return this.f4285a;
    }

    public List b() {
        return this.f4287c;
    }

    public List c() {
        return this.f4286b;
    }
}
