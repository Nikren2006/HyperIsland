package d;

import androidx.collection.ArraySet;
import androidx.core.util.Pair;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import p.C0726f;

/* JADX INFO: loaded from: classes.dex */
public class O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3822a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set f3823b = new ArraySet();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map f3824c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Comparator f3825d = new a();

    public class a implements Comparator {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Pair pair, Pair pair2) {
            float fFloatValue = ((Float) pair.second).floatValue();
            float fFloatValue2 = ((Float) pair2.second).floatValue();
            if (fFloatValue2 > fFloatValue) {
                return 1;
            }
            return fFloatValue > fFloatValue2 ? -1 : 0;
        }
    }

    public void a(String str, float f2) {
        if (this.f3822a) {
            C0726f c0726f = (C0726f) this.f3824c.get(str);
            if (c0726f == null) {
                c0726f = new C0726f();
                this.f3824c.put(str, c0726f);
            }
            c0726f.a(f2);
            if (str.equals("__container")) {
                Iterator it = this.f3823b.iterator();
                if (it.hasNext()) {
                    android.support.v4.media.a.a(it.next());
                    throw null;
                }
            }
        }
    }

    public void b(boolean z2) {
        this.f3822a = z2;
    }
}
