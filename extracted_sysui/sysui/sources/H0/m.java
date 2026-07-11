package H0;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public final class m implements d, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Function0 f306a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile Object f307b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f308c;

    public m(Function0 initializer, Object obj) {
        kotlin.jvm.internal.n.g(initializer, "initializer");
        this.f306a = initializer;
        this.f307b = q.f313a;
        this.f308c = obj == null ? this : obj;
    }

    public boolean a() {
        return this.f307b != q.f313a;
    }

    @Override // H0.d
    public Object getValue() {
        Object objInvoke;
        Object obj = this.f307b;
        q qVar = q.f313a;
        if (obj != qVar) {
            return obj;
        }
        synchronized (this.f308c) {
            objInvoke = this.f307b;
            if (objInvoke == qVar) {
                Function0 function0 = this.f306a;
                kotlin.jvm.internal.n.d(function0);
                objInvoke = function0.invoke();
                this.f307b = objInvoke;
                this.f306a = null;
            }
        }
        return objInvoke;
    }

    public String toString() {
        return a() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    public /* synthetic */ m(Function0 function0, Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, (i2 & 2) != 0 ? null : obj);
    }
}
