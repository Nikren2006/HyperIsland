package H0;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public final class l implements d, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f301d = new a(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f302e = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "b");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Function0 f303a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile Object f304b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f305c;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    public l(Function0 initializer) {
        kotlin.jvm.internal.n.g(initializer, "initializer");
        this.f303a = initializer;
        q qVar = q.f313a;
        this.f304b = qVar;
        this.f305c = qVar;
    }

    public boolean a() {
        return this.f304b != q.f313a;
    }

    @Override // H0.d
    public Object getValue() {
        Object obj = this.f304b;
        q qVar = q.f313a;
        if (obj != qVar) {
            return obj;
        }
        Function0 function0 = this.f303a;
        if (function0 != null) {
            Object objInvoke = function0.invoke();
            if (f302e.compareAndSet(this, qVar, objInvoke)) {
                this.f303a = null;
                return objInvoke;
            }
        }
        return this.f304b;
    }

    public String toString() {
        return a() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
