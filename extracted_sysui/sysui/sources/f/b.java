package f;

import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f4094a = new ArrayList();

    public void a(u uVar) {
        this.f4094a.add(uVar);
    }

    public void b(Path path) {
        for (int size = this.f4094a.size() - 1; size >= 0; size--) {
            p.h.b(path, (u) this.f4094a.get(size));
        }
    }
}
