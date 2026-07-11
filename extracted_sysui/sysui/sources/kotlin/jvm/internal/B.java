package kotlin.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f5033a;

    public B(int i2) {
        this.f5033a = new ArrayList(i2);
    }

    public void a(Object obj) {
        this.f5033a.add(obj);
    }

    public void b(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            if (objArr.length > 0) {
                ArrayList arrayList = this.f5033a;
                arrayList.ensureCapacity(arrayList.size() + objArr.length);
                Collections.addAll(this.f5033a, objArr);
                return;
            }
            return;
        }
        if (obj instanceof Collection) {
            this.f5033a.addAll((Collection) obj);
            return;
        }
        if (obj instanceof Iterable) {
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                this.f5033a.add(it.next());
            }
            return;
        }
        if (obj instanceof Iterator) {
            Iterator it2 = (Iterator) obj;
            while (it2.hasNext()) {
                this.f5033a.add(it2.next());
            }
        } else {
            throw new UnsupportedOperationException("Don't know how to spread " + obj.getClass());
        }
    }

    public int c() {
        return this.f5033a.size();
    }

    public Object[] d(Object[] objArr) {
        return this.f5033a.toArray(objArr);
    }
}
