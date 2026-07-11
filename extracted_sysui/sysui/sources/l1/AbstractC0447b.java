package l1;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: renamed from: l1.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0447b extends y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f5213a = AtomicReferenceFieldUpdater.newUpdater(AbstractC0447b.class, Object.class, "_consensus");
    private volatile Object _consensus = AbstractC0446a.f5212a;

    @Override // l1.y
    public final Object a(Object obj) {
        Object objC = f5213a.get(this);
        if (objC == AbstractC0446a.f5212a) {
            objC = c(d(obj));
        }
        b(obj, objC);
        return objC;
    }

    public abstract void b(Object obj, Object obj2);

    public final Object c(Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5213a;
        Object obj2 = atomicReferenceFieldUpdater.get(this);
        Object obj3 = AbstractC0446a.f5212a;
        return obj2 != obj3 ? obj2 : atomicReferenceFieldUpdater.compareAndSet(this, obj3, obj) ? obj : atomicReferenceFieldUpdater.get(this);
    }

    public abstract Object d(Object obj);
}
