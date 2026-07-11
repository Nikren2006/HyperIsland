package H0;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes2.dex */
public final class t implements d, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Function0 f315a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f316b;

    public t(Function0 initializer) {
        kotlin.jvm.internal.n.g(initializer, "initializer");
        this.f315a = initializer;
        this.f316b = q.f313a;
    }

    public boolean a() {
        return this.f316b != q.f313a;
    }

    @Override // H0.d
    public Object getValue() {
        if (this.f316b == q.f313a) {
            Function0 function0 = this.f315a;
            kotlin.jvm.internal.n.d(function0);
            this.f316b = function0.invoke();
            this.f315a = null;
        }
        return this.f316b;
    }

    public String toString() {
        return a() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
